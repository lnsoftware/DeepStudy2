//package com.zhiyin.hystrix.account.base;
//
//
//
//import com.zhiyin.hystrix.account.HystrixSaveAccount;
//import com.zhiyin.hystrix.account.SimpleSaveAccount;
//
//import javax.ws.rs.ApplicationPath;
//import javax.ws.rs.core.Application;
//import java.util.HashSet;
//import java.util.Set;
//
//// tag::classdef[]
//@ApplicationPath("/api")
//public class HystrixApplication extends Application {
//    private Set<Object> singletons = new HashSet<Object>();
//
//    public HystrixApplication() {
//        singletons.add(new SimpleSaveAccount());
//        singletons.add(new HystrixSaveAccount());
//        singletons.add(new ValidationExceptionMapper());
//        singletons.add(new InterruptedExceptionMapper());
//    }
//
//    @Override
//    public Set<Object> getSingletons() {
//        return singletons;
//    }
//}
//// end::classdef[]
