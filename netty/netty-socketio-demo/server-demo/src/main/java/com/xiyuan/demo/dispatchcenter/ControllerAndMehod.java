package com.xiyuan.demo.dispatchcenter;

import java.lang.reflect.Method;

public class ControllerAndMehod {

    public BaseController controller;

    public Method method;

    public ControllerAndMehod(BaseController controller, Method method) {
        this.controller = controller;
        this.method = method;
    }

}
