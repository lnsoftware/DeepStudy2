package com.zhiyin.devtools.demo.controller;

/**
 * Created by wangqinghui on 2017/12/30.
 */
public class DebugPortUtil {

    private static final String JDWP_ADDRESS_PROPERTY = "sun.jdwp.listenerAddress";

    public static void main(String[] args) {
        int port = getRemoteDebugPort();
        System.out.println(port);
    }

    public static int getRemoteDebugPort() {
        String property = sun.misc.VMSupport.getAgentProperties()
                .getProperty(JDWP_ADDRESS_PROPERTY);
        try {
            if (property != null && property.contains(":")) {
                return Integer.valueOf(property.split(":")[1]);
            }
        } catch (Exception ex) {

        }
        return -1;
    }
}
