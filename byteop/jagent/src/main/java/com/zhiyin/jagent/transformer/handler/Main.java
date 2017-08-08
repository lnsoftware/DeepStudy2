//package com.zhiyin.jagent.transformer.handler;
//
//import ch.qos.logback.classic.Logger;
//import ch.qos.logback.classic.LoggerContext;
//import ch.qos.logback.classic.encoder.PatternLayoutEncoder;
//import ch.qos.logback.core.FileAppender;
//import ch.qos.logback.core.util.StatusPrinter;
//import org.slf4j.LoggerFactory;
//
//public class Main {
//  public static void addAppender(String[] args) {
//    LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
//
//    FileAppender fileAppender = new FileAppender();
//    fileAppender.setContext(loggerContext);
//    System.out.println("log.base.path" + loggerContext.getProperty("log.base.path"));
//    fileAppender.setName("timestamp");
//    // set the file name
//    fileAppender.setFile("log/" + System.currentTimeMillis()+".log");
//
//    PatternLayoutEncoder encoder = new PatternLayoutEncoder();
//    encoder.setContext(loggerContext);
//    encoder.setPattern("%r %thread %level - %msg%n");
//    encoder.start();
//
//    fileAppender.setEncoder(encoder);
//    fileAppender.start();
//
//    // attach the rolling file appender to the logger of your choice
//    Logger logbackLogger = loggerContext.getLogger("Main");
//    logbackLogger.addAppender(fileAppender);
//
//    // OPTIONAL: print logback internal status messages
//    StatusPrinter.print(loggerContext);
//
//    // log something
//    logbackLogger.info("hello");
//
//    org.slf4j.Logger log = LoggerFactory.getLogger("Main");
//    log.info("ssss");
//    System.out.println("log.base.path" + loggerContext.getProperty("log.base.path"));
//
//
//  }
//}