package io.github.hengyunabc.metrics;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.SortedMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;

import com.codahale.metrics.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.zhiyin.frame.metrics.JsonMetrics;
import com.zhiyin.frame.metrics.MetricsMapper;
import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.codahale.metrics.json.MetricsModule;
import com.fasterxml.jackson.databind.ObjectMapper;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

import static com.codahale.metrics.MetricRegistry.name;

/**
 * 
 * @author hengyunabc
 * 
 *
 */
@Slf4j
@Data
public class KafkaReporter extends ScheduledReporter {

	String topic;
	ProducerConfig config;
	Producer<String, String> producer;
	ExecutorService kafkaExecutor;

	private String prefix;
	private String hostName;
	private String ip;

	private String kafkaKey;

	public Clock clock = Clock.defaultClock();

	private Map<String,Object> additionalFields = new HashMap<String,Object>();

	int count = 0;
	
	ObjectMapper mapper;

	private AtomicLong failCount = new AtomicLong(0);
	private AtomicBoolean failSwitchOpen = new AtomicBoolean(false);


	private KafkaReporter(MetricRegistry registry, String name,
			TimeUnit rateUnit, TimeUnit durationUnit, boolean showSamples, MetricFilter filter,
			String topic, ProducerConfig config, String prefix,
			String hostName, String ip) {
		super(registry, name, filter, rateUnit, durationUnit);
		this.topic = topic;
		this.config = config;
		this.prefix = prefix;
		this.hostName = hostName;
		this.ip = ip;
		this.kafkaKey = HostUtil.inetAddressToInt() + "";
		
		this.mapper = new ObjectMapper().registerModule(new MetricsModule(rateUnit,
                durationUnit,
                showSamples));

		producer = new Producer<String, String>(config);

		kafkaExecutor = Executors
				.newSingleThreadExecutor(new ThreadFactoryBuilder()
						.setNameFormat("kafka-producer-%d").build());


		Thread daemonThread = new Thread(new DaemonRunner(this));
		// 设置为守护进程
		daemonThread.setDaemon(true);
		daemonThread.start();

	}

	public static Builder forRegistry(MetricRegistry registry) {
		return new Builder(registry);
	}


	public static class Builder {
		private final MetricRegistry registry;
		private String name = "kafka-reporter";
		private TimeUnit rateUnit;
		private TimeUnit durationUnit;
		
		private boolean showSamples;
		
		private MetricFilter filter;
		
		private String prefix = "";
		private String hostName;
		private String ip;
		private Map<String,Object> additionalFields = new HashMap<String,Object>();


		private String topic;
		private ProducerConfig config;

		public Builder(MetricRegistry registry) {
			this.registry = registry;

			this.rateUnit = TimeUnit.SECONDS;
			this.durationUnit = TimeUnit.MILLISECONDS;
			this.filter = MetricFilter.ALL;
		}

		/**
		 * Convert rates to the given time unit.
		 *
		 * @param rateUnit
		 *            a unit of time
		 * @return {@code this}
		 */
		public Builder convertRatesTo(TimeUnit rateUnit) {
			this.rateUnit = rateUnit;
			return this;
		}

		/**
		 * Convert durations to the given time unit.
		 *
		 * @param durationUnit
		 *            a unit of time
		 * @return {@code this}
		 */
		public Builder convertDurationsTo(TimeUnit durationUnit) {
			this.durationUnit = durationUnit;
			return this;
		}
		
		public Builder showSamples(boolean showSamples) {
			this.showSamples = showSamples;
			return this;
		}

		/**
		 * Only report metrics which match the given filter.
		 *
		 * @param filter
		 *            a {@link MetricFilter}
		 * @return {@code this}
		 */
		public Builder filter(MetricFilter filter) {
			this.filter = filter;
			return this;
		}

		/**
		 * default register name is "kafka-reporter".
		 * 
		 * @param name
		 * @return
		 */
		public Builder name(String name) {
			this.name = name;
			return this;
		}

		public Builder topic(String topic) {
			this.topic = topic;
			return this;
		}

		public Builder config(ProducerConfig config) {
			this.config = config;
			return this;
		}

		public Builder prefix(String prefix) {
			this.prefix = prefix;
			return this;
		}

		public Builder hostName(String hostName) {
			this.hostName = hostName;
			return this;
		}

		public Builder ip(String ip) {
			this.ip = ip;
			return this;
		}

		public Builder   additionalFields (HashMap<String,Object> map ){
			this.additionalFields  = map;
			return this;
		}


		/**
		 * Builds a {@link KafkaReporter} with the given properties.
		 *
		 * @return a {@link KafkaReporter}
		 */
		public KafkaReporter build() {
			if (hostName == null) {
				hostName = HostUtil.getHostName();
				log.info(name + " detect hostName: " + hostName);
			}
			if (ip == null) {
				ip = HostUtil.getHostAddress();
				log.info(name + " detect ip: " + ip);
			}

			return new KafkaReporter(registry, name, rateUnit, durationUnit, showSamples,
					filter, topic, config, prefix, hostName, ip);
		}
	}

	private Map<String, Object> addPrefix(SortedMap<String,?> map){
		Map<String, Object> result = new HashMap<String, Object>(map.size());
		for (Entry<String, ?> entry : map.entrySet()) {
			result.put(prefix + entry.getKey(), entry.getValue());
		}
		return result;
	}

//	@SuppressWarnings("rawtypes")
//	@Override
//	public void report(SortedMap<String, Gauge> gauges,
//			SortedMap<String, Counter> counters,
//			SortedMap<String, Histogram> histograms,
//			SortedMap<String, Meter> meters, SortedMap<String, Timer> timers) {
//
//		final Map<String, Object> result = new HashMap<String, Object>(16);
//
//		result.put("hostName", hostName);
//		result.put("ip", ip);
//		result.put("rateUnit", getRateUnit());
//		result.put("durationUnit", getDurationUnit());
//
//		result.put("gauges", addPrefix(gauges));
//		result.put("counters", addPrefix(counters));
//		result.put("histograms", addPrefix(histograms));
//		result.put("meters", addPrefix(meters));
//		result.put("timers", addPrefix(timers));
//
//		result.put("clock", System.currentTimeMillis());
//
//		kafkaExecutor.execute(new Runnable() {
//			@Override
//			public void run() {
//				try {
//				KeyedMessage<String, String> message = new KeyedMessage<String, String>(
//						topic, "" + count++, mapper.writeValueAsString(result));
//					producer.send(message);
//				} catch (Exception e) {
//					logger.error("send metrics to kafka error!", e);
//				}
//			}
//		});
//	}

	@SuppressWarnings("rawtypes")
	@Override
	public void report(SortedMap<String, Gauge> gauges,
					   SortedMap<String, Counter> counters,
					   SortedMap<String, Histogram> histograms,
					   SortedMap<String, Meter> meters, SortedMap<String, Timer> timers) {

		kafkaExecutor.execute(new Runnable() {
			@Override
			public void run() {
				try {

					// 失败数量太多触发
					if(failSwitchOpen.get()){
						return;
					}

					final long timestamp = clock.getTime() / 1000;

					for (Map.Entry<String, Gauge> entry : gauges.entrySet()) {
						if (entry.getValue().getValue() != null) {

							boolean notNum = (entry.getValue().getValue() instanceof String);
							if (notNum) {
								continue;
							}

							// 转化boolean to int
							final Object val = entry.getValue().getValue();
							Gauge<Integer> newVal = null;

							if (val instanceof Boolean) {
								newVal = new Gauge<Integer>() {
									@Override
									public Integer getValue() {
										if (val == Boolean.FALSE) {
											return 0;
										} else {
											return 1;
										}
									}
								};
							}

							JsonMetrics.JsonMetric jsonMetric = null;
							if (newVal != null) {
								jsonMetric = new JsonMetrics.JsonGauge(name(prefix, entry.getKey()), timestamp, newVal);
							} else {
								jsonMetric = new JsonMetrics.JsonGauge(name(prefix, entry.getKey()), timestamp, entry.getValue());
							}

							sendStr(jsonMetric);
						}

					}


					if (count % 10000 == 0) {
						log.info("process metrics num:{}",count);
					}

				} catch (Exception e) {
					log.error("send metrics to kafka error!" + failCount.get());

					failCount.incrementAndGet();

//					if(failCount%1000 ==0 ){
//						autoSwitch = true;
//					}
				}
			}
		});
	}

	public void sendStr(JsonMetrics.JsonMetric  jsonMetric){

		count ++;
		try {
			KeyedMessage<String, String> message = new KeyedMessage<String, String>(
					topic, "" + count, MetricsMapper.getInstance().getObjectMapper().writeValueAsString(jsonMetric));
			producer.send(message);
			//System.out.println( jsonMetric );

		}catch (Exception e){
			failCount.incrementAndGet();
		//e.printStackTrace();
		}

	}


}
