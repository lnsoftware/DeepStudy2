package com.zhiyin.rpc.shi.demo.filter;

import com.google.common.base.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CatContextManager {
    private static final Logger log = LoggerFactory.getLogger(CatContextManager.class);

    private static final ThreadLocal<String> rootId = new ThreadLocal<String>();
    private static final ThreadLocal<String> parentId = new ThreadLocal<String>();
    private static final ThreadLocal<String> childId = new ThreadLocal<String>();

    public static void setContext(String rootIdStr, String parentIdStr, String childIdStr) {
        rootId.set(rootIdStr);
        parentId.set(parentIdStr);
        childId.set(childIdStr);
    }

    public static void removeContext() {
        rootId.remove();
        parentId.remove();
        childId.remove();
    }

    public static boolean hasContext() {

        if (Strings.isNullOrEmpty(getRootId()) || Strings.isNullOrEmpty(getParentId()) || Strings.isNullOrEmpty(geteChildId())) {
            return false;
        }

        return true;
    }

    public static String getRootId() {
        return rootId.get();
    }

    public static String getParentId() {
        return parentId.get();
    }

    public static String geteChildId() {
        return childId.get();
    }

    public static void printCatInfo() {
        log.info("cat info: " + CatContextManager.getRootId() + " " + CatContextManager.getParentId() + " " + CatContextManager.geteChildId());
    }

    public static void main(String[] args) {
        log.info(CatContextManager.getRootId());
        log.info("has context:" + CatContextManager.hasContext());

        CatContextManager.setContext("1", "2", "3");
        log.info("has context:" + CatContextManager.hasContext());

    }

}