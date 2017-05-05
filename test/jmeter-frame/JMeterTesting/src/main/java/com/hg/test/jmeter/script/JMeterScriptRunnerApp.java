package com.hg.test.jmeter.script;

import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;
import org.kohsuke.args4j.OptionHandlerFilter;

import java.io.File;
import java.io.IOException;

public class JMeterScriptRunnerApp {

    @Option(name = "-h", required = true, usage = "JMeter home directory")
    private File jmeterPath;

    @Option(name = "-t", required = true, usage = "JMX file to execute")
    private File jmxFile;

    @Option(name = "-l", required = false, usage = "JTL file to log results")
    private File logFile;

    private void doMain(String[] args) throws IOException {
        CmdLineParser parser = new CmdLineParser(this);

        try {
            parser.parseArgument(args);

            System.out.println("JMeter home : " + jmeterPath.getAbsolutePath());
            System.out.println("File to execute : " + jmxFile.getAbsolutePath());
            System.out.println("Log file : " + logFile.getAbsolutePath());

            JMeterScriptRunner.aJMeterRunner()
                    .home(jmeterPath)
                    .from(jmxFile)
                    .logTo(logFile)
                    .doExecute();

        } catch (CmdLineException e) {
            System.err.println(e.getMessage());
            System.err.println("Usage : ");
            parser.printUsage(System.err);
            System.err.println();
            System.err.println("Exemple : " + parser.printExample(OptionHandlerFilter.ALL));
        }

    }

    public static void main(String[] args) throws IOException {
        new JMeterScriptRunnerApp().doMain(args);
    }
}