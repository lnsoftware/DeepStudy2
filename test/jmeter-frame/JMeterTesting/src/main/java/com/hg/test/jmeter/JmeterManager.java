package com.hg.test.jmeter;

import com.hg.test.jmeter.helpers.SamplerConstants;
import com.hg.test.jmeter.util.HttpSamplerUtil;
import org.apache.jmeter.control.LoopController;
import org.apache.jmeter.engine.StandardJMeterEngine;
import org.apache.jmeter.protocol.http.sampler.HTTPSampler;
import org.apache.jmeter.reporters.ResultCollector;
import org.apache.jmeter.reporters.Summariser;
import org.apache.jmeter.samplers.AbstractSampler;
import org.apache.jmeter.save.SaveService;
import org.apache.jmeter.testelement.TestElement;
import org.apache.jmeter.testelement.TestPlan;
import org.apache.jmeter.threads.ThreadGroup;
import org.apache.jmeter.util.JMeterUtils;
import org.apache.jmeter.visualizers.ViewResultsFullVisualizer;
import org.apache.jorphan.collections.HashTree;

import java.io.FileOutputStream;
import java.io.IOException;

public class JmeterManager {
    public StandardJMeterEngine jmeter;

    String jmeterPath = SamplerConstants.JMETER_HOME + "bin/";
    String resultPath = JmeterManager.class.getResource("/").getPath();

    public JmeterManager() {

        jmeter = new StandardJMeterEngine();

        JMeterUtils.setJMeterHome(SamplerConstants.JMETER_HOME); // 初始化jmeter文件位置

        JMeterUtils.loadJMeterProperties(JmeterManager.class.getResource("/").getPath() + "jmeter.properties");

        JMeterUtils.initLogging(); // you can comment this line out to see extra log messages of i.e. DEBUG level

        JMeterUtils.initLocale();

    }

    public void runSuite(HashTree testPlan) {
        jmeter.configure(testPlan);
        setSummary(testPlan);
        jmeter.run();
    }

    public ThreadGroup getThreadGroup(String name) {
        ThreadGroup threadGroup = new ThreadGroup();
        threadGroup.setName(name);
        threadGroup.setNumThreads(10);
        threadGroup.setRampUp(1);
        return threadGroup;
    }

    public HTTPSampler getHttpSampler() {
        HTTPSampler httpSampler = new HTTPSampler();
        httpSampler.setDomain("google.com");
        httpSampler.setPort(80);
        httpSampler.setPath("/");
        httpSampler.setMethod("GET");
        return httpSampler;
    }

    public AbstractSampler getJavaSampler() {

//        return JavaSamplerUtil.demoFast();
        return HttpSamplerUtil.getBaidu();
    }

    public LoopController getLoopController() {
        LoopController loopController = new LoopController();
        loopController.setLoops(1);

        loopController.setFirst(true);
        loopController.initialize();
        return loopController;
    }

    public HashTree generateTestPlan(String testPlanName, String threadGroupName) {
        HashTree testPlanTree = new HashTree();
        LoopController loopController = getLoopController();
        ThreadGroup threadGroup = getThreadGroup(threadGroupName);
        threadGroup.setSamplerController(loopController);


//        threadGroup.addTestElement(getJavaSampler());
//        threadGroup.addTestElement(getHttpSampler());


        TestPlan testPlan = new TestPlan(testPlanName);
//        testPlanTree.add("testPlan", testPlan);
        testPlanTree.add("loopController", loopController);
        testPlanTree.add("threadGroup", threadGroup);
//        testPlanTree.add("httpSampler", getJavaSampler());
//        testPlanTree.add("httpSampler2", getHttpSampler());

        HashTree threadGroupHashTree = testPlanTree.add(testPlan, threadGroup);
        threadGroupHashTree.add(HttpSamplerUtil.getBaidu());
        threadGroupHashTree.add(HttpSamplerUtil.getBaidu());

        return testPlanTree;
    }


    public void setSummary(HashTree testPlanTree) {

        Summariser summer = null;
        String summariserName = JMeterUtils.getPropDefault("summariser.name", "summary");
        if (summariserName.length() > 0) {
            summer = new Summariser(summariserName);
        }

        // Store execution results into a .jtl file
        String logFile = "jmeterSampler.jtl";
        ResultCollector logger = new ResultCollector(summer);
        logger.setName("SampleLogger");
        logger.setFilename(resultPath + logFile);
        testPlanTree.add(testPlanTree.getArray()[0], logger);
        logger.setProperty(TestElement.GUI_CLASS, ViewResultsFullVisualizer.class.getName());

        // Save to jmx file
        try {
            SaveService.saveTree(testPlanTree, new FileOutputStream(resultPath + "jmeterJava.jmx"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}