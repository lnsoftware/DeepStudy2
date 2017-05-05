package net.shaneconnolly.jmeter;

import net.shaneconnolly.jmeter.helpers.SamplerConstants;
import net.shaneconnolly.jmeter.helpers.SamplerHelper;
import net.shaneconnolly.jmeter.samplers.GetHomeSampler;
import net.shaneconnolly.jmeter.samplers.PostLoginSampler;
import org.apache.jmeter.config.Arguments;
import org.apache.jmeter.config.gui.ArgumentsPanel;
import org.apache.jmeter.control.LoopController;
import org.apache.jmeter.control.gui.LoopControlPanel;
import org.apache.jmeter.control.gui.TestPlanGui;
import org.apache.jmeter.engine.StandardJMeterEngine;
import org.apache.jmeter.reporters.ResultCollector;
import org.apache.jmeter.reporters.Summariser;
import org.apache.jmeter.save.SaveService;
import org.apache.jmeter.testelement.TestElement;
import org.apache.jmeter.testelement.TestPlan;
import org.apache.jmeter.threads.gui.ThreadGroupGui;
import org.apache.jmeter.util.JMeterUtils;
import org.apache.jmeter.visualizers.ViewResultsFullVisualizer;
import org.apache.jorphan.collections.ListedHashTree;

import java.io.File;
import java.io.FileOutputStream;


/**
 * @author connollys
 *         25/02/15.
 */
public class JMeterRunner {


    private static int THREADS = 1;
    private static int RAMP_UP = 1;
    private static int LOOPS = 1;
    private static StandardJMeterEngine jmeter;

    public static void main(String[] argv) throws Exception {
        File jmeterHome = new File(SamplerConstants.JMETER_HOME);

        if (jmeterHome.exists()) {
            File jmeterProperties = new File(jmeterHome.getPath() + "/bin/jmeter.properties");

            if (jmeterProperties.exists()) {
                //JMeter Engine
                jmeter = new StandardJMeterEngine();

                //JMeter initialization (properties, log levels, locale, etc)
                JMeterUtils.setJMeterHome(jmeterHome.getPath());
                JMeterUtils.loadJMeterProperties(jmeterProperties.getPath());
                JMeterUtils.initLogging();                      // you can comment this line out to see extra log messages of i.e. DEBUG level
                JMeterUtils.initLocale();

                // JMeter Test Plan, basically JOrphan HashTree
                ListedHashTree testPlanTree = new ListedHashTree();


                // Loop Controller
                LoopController loopController = new LoopController();
                loopController.setName("Main Loop Controller");
                loopController.setLoops(LOOPS);
                loopController.setFirst(true);
                loopController.setProperty(TestElement.TEST_CLASS, LoopController.class.getName());
                loopController.setProperty(TestElement.GUI_CLASS, LoopControlPanel.class.getName());
                loopController.initialize();

                // Thread Group
                org.apache.jmeter.threads.ThreadGroup threadGroup = new org.apache.jmeter.threads.ThreadGroup();
                threadGroup.setName("Main User Thread Group");
                threadGroup.setNumThreads(THREADS);
                threadGroup.setRampUp(RAMP_UP);
                threadGroup.setSamplerController(loopController);
                SamplerHelper.setObjectProperty(threadGroup);
                threadGroup.setProperty(TestElement.TEST_CLASS, ThreadGroup.class.getName());
                threadGroup.setProperty(TestElement.GUI_CLASS, ThreadGroupGui.class.getName());
                threadGroup.setEnabled(true);

                // Test Plan
                TestPlan testPlan = new TestPlan("Sandbox Test Plan");
                testPlan.setProperty(TestElement.TEST_CLASS, TestPlan.class.getName());
                testPlan.setProperty(TestElement.GUI_CLASS, TestPlanGui.class.getName());
                testPlan.setUserDefinedVariables((Arguments) new ArgumentsPanel().createTestElement());
                testPlan.addThreadGroup(threadGroup);
                testPlan.setEnabled(true);


                // Samplers
                ListedHashTree threadGroupHashTree = (ListedHashTree) testPlanTree.add(testPlan, threadGroup);
                threadGroupHashTree.add(SamplerHelper.createSampler("homeSampler", GetHomeSampler.class.getName()));
                threadGroupHashTree.add(SamplerHelper.createSampler("loginSampler", PostLoginSampler.class.getName()));
                // add extra samplers here
             //   threadGroupHashTree.add(SamplerHelper.createSampler("loginSampler", PostLoginSampler.class.getName()));


                Summariser summer = null;
                String summariserName = JMeterUtils.getPropDefault("summariser.name", "summary");
                if (summariserName.length() > 0) {
                    summer = new Summariser(summariserName);
                }


                // Store execution results into a .jtl file
                String logFile = "jmeterSampler.jtl";
                ResultCollector logger = new ResultCollector(summer);
                logger.setName("SampleLogger");
                logger.setFilename(logFile);
                testPlanTree.add(logger);
                logger.setProperty(TestElement.GUI_CLASS, ViewResultsFullVisualizer.class.getName());

                // Save to jmx file
                SaveService.saveTree(testPlanTree, new FileOutputStream(SamplerConstants.JMETER_HOME + "/jmeterJava.jmx"));

                // Run Test Plan
                jmeter.configure(testPlanTree);


                // comment out just to create jmx and not run
                jmeter.run();

                System.exit(0);

            }
        }

        System.err.println("jmeter.home property is not set or pointing to incorrect location");
        System.exit(1);


    }
    


}
