package com.hg.test.jmeter.helpers;

import org.apache.jmeter.protocol.java.control.gui.JavaTestSamplerGui;
import org.apache.jmeter.protocol.java.sampler.JavaSampler;
import org.apache.jmeter.testelement.TestElement;

/**
 * Created by seany on 20/03/15.
 */
public class SamplerHelper {


    public static JavaSampler createSampler(String name, String className) {
        JavaSampler sampler = new JavaSampler();
        sampler.setName(name);
        sampler.setClassname(className);

        // for Charles Proxy
//        sampler.setProperty(HTTPSamplerBase.PROXYHOST, "127.0.0.1");
//        sampler.setProperty(HTTPSamplerBase.PROXYPORT, "8888");

        sampler.setProperty(TestElement.TEST_CLASS, JavaSampler.class.getName());
        sampler.setProperty(TestElement.GUI_CLASS, JavaTestSamplerGui.class.getName());
        sampler.setEnabled(true);
        sampler.setProperty(TestElement.TEST_CLASS, className);
        sampler.setProperty(TestElement.GUI_CLASS, JavaTestSamplerGui.class.getName());

        return sampler;
    }

    public static void setObjectProperty(org.apache.jmeter.threads.ThreadGroup threadGroup) {
        // for Charles Proxy
//        threadGroup.setProperty(HTTPSamplerBase.PROXYHOST, "127.0.0.1");
//        threadGroup.setProperty(HTTPSamplerBase.PROXYPORT, "8888");
    }
}
