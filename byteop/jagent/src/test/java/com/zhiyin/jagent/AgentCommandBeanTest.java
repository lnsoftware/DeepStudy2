package com.zhiyin.jagent;

import org.junit.Test;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;

import static org.junit.Assert.*;

/**
 * Created by wangqinghui on 2017/1/10.
 */
public class AgentCommandBeanTest {

    @Test
    public void test(){

        String str = "-packageStrategy white -includePackage com.zhiyin.test";
        AgentCommandBean bean = new AgentCommandBean();
        CmdLineParser parser = new CmdLineParser(bean);

        String[] args = str.split("\\s+?");

        try {
            parser.parseArgument(args);

        } catch (CmdLineException e) {
            // handling of wrong arguments
            System.err.println(e.getMessage());
            parser.printUsage(System.err);
        }
        System.out.println(bean.toString());
    }
}