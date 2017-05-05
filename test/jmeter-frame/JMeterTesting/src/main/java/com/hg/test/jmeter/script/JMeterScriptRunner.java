package com.hg.test.jmeter.script;

import org.apache.jmeter.engine.StandardJMeterEngine;
import org.apache.jmeter.reporters.ResultCollector;
import org.apache.jmeter.reporters.Summariser;
import org.apache.jmeter.save.SaveService;
import org.apache.jmeter.util.JMeterUtils;
import org.apache.jorphan.collections.HashTree;

import java.io.File;
import java.io.IOException;

public class JMeterScriptRunner {

    private File jmeterHome;
    private File jmxFile;
    private File logFile;

    public static JMeterScriptRunner aJMeterRunner() {
        return new JMeterScriptRunner();
    }

    public JMeterScriptRunner home(File jmeterHome) {
        this.jmeterHome = jmeterHome;
        return this;
    }

    public JMeterScriptRunner from(File jmxFile) {
        this.jmxFile = jmxFile;
        return this;
    }

    public JMeterScriptRunner logTo(File logFile) {
        this.logFile = logFile;
        return this;
    }

    public void doExecute() throws IOException {

        // JMeter Engine
        StandardJMeterEngine jmeter = new StandardJMeterEngine();

        // Initialize Properties, logging, locale, etc.
        JMeterUtils.loadJMeterProperties(jmeterHome.getAbsolutePath() + "/bin/jmeter.properties");
        JMeterUtils.setJMeterHome(jmeterHome.getAbsolutePath());
        JMeterUtils.initLogging();
        JMeterUtils.initLocale();

        // Initialize JMeter SaveService
        SaveService.loadProperties();

        // Load existing .jmx Test Plan
        HashTree testPlanTree = SaveService.loadTree(jmxFile);

        // Configure log file
        if (hasToLogResult()) {
            ResultCollector logger = configureLogger();
            testPlanTree.add(testPlanTree.getArray()[0], logger);
        }

        // Run JMeter Test
        jmeter.configure(testPlanTree);
        jmeter.run();
    }

    private boolean hasToLogResult() {
        return logFile != null;
    }

    private ResultCollector configureLogger() {
        Summariser summer = null;
        String summariserName = JMeterUtils.getPropDefault("summariser.name", "summary");
        if (summariserName.length() > 0) {
            summer = new Summariser(summariserName);
        }

        ResultCollector logger = new ResultCollector(summer);
        logger.setFilename(logFile.getPath());
        return logger;
    }
}