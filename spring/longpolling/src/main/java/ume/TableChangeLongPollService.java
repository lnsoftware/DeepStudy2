package ume;

import com.google.common.base.Joiner;
import com.google.common.collect.Maps;
import com.google.common.escape.Escaper;
import com.google.common.net.UrlEscapers;
import com.google.common.reflect.TypeToken;
import com.google.common.util.concurrent.RateLimiter;
import java.lang.reflect.Type;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ume.client.ConnectStringParser;
import ume.client.StaticHostProvider;

/**
 * @author Jason Song(song_s@ctrip.com)
 */
public class TableChangeLongPollService {
    private static final Logger logger = LoggerFactory.getLogger(TableChangeLongPollService.class);
    private static final Joiner.MapJoiner MAP_JOINER = Joiner.on("&").withKeyValueSeparator("=");
    private static final Escaper queryParamEscaper = UrlEscapers.urlFormParameterEscaper();
    private static final long INIT_NOTIFICATION_ID = -1;
    private final ExecutorService m_longPollingService;
    private final AtomicBoolean m_longPollingStopped;
    private RateLimiter m_longPollRateLimiter;
    private final AtomicBoolean m_longPollStarted;
    private final ConcurrentMap<String, Long> m_notifications;
    private Type m_responseType;

    private HttpUtil m_httpUtil = new HttpUtil();

    private String servers = "";
    StaticHostProvider hostProvider;

    public TableChangeLongPollService(String servers) {

        ConnectStringParser connectStringParser = new ConnectStringParser(servers);
        hostProvider = new StaticHostProvider(
           connectStringParser.getServerAddresses());

        m_longPollingStopped = new AtomicBoolean(false);
        m_longPollingService = Executors.newSingleThreadExecutor(
            ApolloThreadFactory.create("RemoteConfigLongPollService", true));
        m_longPollStarted = new AtomicBoolean(false);

        m_notifications = Maps.newConcurrentMap();

        m_responseType = new TypeToken<List<TableChangeMeta>>() {
        }.getType();

        this.servers = servers;



        initialize();
        startLongPolling();
    }

    public void initialize() {
        m_longPollRateLimiter = RateLimiter.create(2);
    }

    private void startLongPolling() {
        if (!m_longPollStarted.compareAndSet(false, true)) {
            //already started
            return;
        }
        try {
            m_longPollingService.submit(new Runnable() {
                @Override
                public void run() {
                    doLongPollingRefresh();
                }
            });
        } catch (Throwable ex) {
        }
    }

    void stopLongPollingRefresh() {
        this.m_longPollingStopped.compareAndSet(false, true);
    }

    private void doLongPollingRefresh() {
        final Random random = new Random();
        while (!m_longPollingStopped.get() && !Thread.currentThread().isInterrupted()) {
            if (!m_longPollRateLimiter.tryAcquire(5, TimeUnit.SECONDS)) {
                //wait at most 5 seconds
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                }
            }

            String url = hostProvider.next(5000);

            try {
                logger.info("Long polling from {}", url);
                HttpRequest request = new HttpRequest(url);
                //longer timeout for read - 10 minutes
                request.setReadTimeout(10 * 60 * 1000);

                final HttpResponse<List<TableChangeMeta>> response =
                    m_httpUtil.doGet(request, m_responseType);

                logger.info("Long polling response: {}, url: {}", response.getStatusCode(), url);
                if (response.getStatusCode() == 200 && response.getBody() != null) {
                    processBiz( response.getBody() );
                }

                //try to load balance
                if (response.getStatusCode() == 304 && random.nextBoolean()) {

                }

            } catch (Throwable ex) {

                logger.info("lang poll error,",ex);

                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException ie) {
                    //ignore
                }
            } finally {
            }
        }
    }

    private void processBiz(List<TableChangeMeta> body) {
        for (TableChangeMeta meta : body) {
            System.out.println(meta.getTableName());
        }
    }

}
