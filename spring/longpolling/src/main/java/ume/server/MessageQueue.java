package ume.server;

import com.google.common.collect.Queues;
import java.util.concurrent.BlockingQueue;
import org.springframework.web.context.request.async.DeferredResult;
import ume.TableChangeMeta;

/**
 * Created by hg on 2017/5/17.
 */
public class MessageQueue {

    public static BlockingQueue<TableChangeMeta> queue = Queues.newLinkedBlockingQueue(1000);

}
