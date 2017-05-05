package io.github.hengyunabc.metrics;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DaemonRunner implements Runnable {

	KafkaReporter kafkaReporter;
    public DaemonRunner( KafkaReporter kafkaReporter){
			this.kafkaReporter = kafkaReporter;
		}

		// 连续失败次数
		public int failNum = 0;
	public void run() {
    	log.info("DaemonRunner");
		while (true) {
			try {
				// 有失败
				if(kafkaReporter.getFailCount().get() > 0){
					kafkaReporter.getFailSwitchOpen().set(true);
					kafkaReporter.getFailCount().set(0);
					log.info("fail too much.");
					failNum++;
				}else{
					failNum = 0;
				}

				if( kafkaReporter.getFailSwitchOpen().get() ){
					// 失败惩罚,最大为1分钟
					int pu = 2000 * failNum;
					if(pu > 60 * 1000){
						pu = 60*1000;
					}
					Thread.sleep(pu);
					log.info("continue fail num {}",failNum);
					//关闭开关，尝试放流量
					kafkaReporter.getFailSwitchOpen().set(false);
					Thread.sleep(1000);
				}else{

					// 检测频率
					Thread.sleep(10000);
				}

			}catch (Exception e){
				log.error("error",e);
			}


		}
		}
	}
