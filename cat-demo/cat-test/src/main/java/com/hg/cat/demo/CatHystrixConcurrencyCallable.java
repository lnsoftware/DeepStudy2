package com.hg.cat.demo;

import com.dianping.cat.Cat;
import com.dianping.cat.CatConstants;
import com.dianping.cat.message.Event;
import com.dianping.cat.message.spi.MessageTree;
import java.util.concurrent.Callable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CatHystrixConcurrencyCallable<K> implements Callable<K> {

    private Logger logger = LoggerFactory.getLogger(CatHystrixConcurrencyCallable.class);

    private final Callable<K> actual;
    private String messageId;
    private String rootId;
    private String parentId;
    private boolean succ = false;
    public CatHystrixConcurrencyCallable(Callable<K> actual) {
        this.actual = actual;
        try {
            MessageTree tree = Cat.getManager().getThreadLocalMessageTree();
            messageId = tree.getMessageId();
            rootId = tree.getRootMessageId();
            parentId = tree.getParentMessageId();


            messageId = tree.getMessageId();

            if (messageId == null) {
                messageId = Cat.createMessageId();
                tree.setMessageId(messageId);
            }

            rootId = tree.getRootMessageId();

            if (rootId == null) {
                rootId = messageId;
            }

            succ = true;
        }catch (Exception e){
            logger.error("get context error.",e);
        }
    }

    @Override
    public K call() throws Exception {

        if(succ){
            try {
                MessageTree tree = Cat.getManager().getThreadLocalMessageTree();
                if (messageId != null) {
                    tree.setMessageId(messageId);
                }
                if (parentId != null) {
                    tree.setParentMessageId(parentId);
                }
                if (rootId != null) {
                    tree.setRootMessageId(rootId);
                }
            }catch (Exception e){

            }
        }

        try {
            return actual.call();
        } finally {

        }
    }
}