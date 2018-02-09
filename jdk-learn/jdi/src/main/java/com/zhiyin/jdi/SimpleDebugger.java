package com.zhiyin.jdi;

import com.sun.jdi.*;
import com.sun.jdi.connect.Connector;
import com.sun.jdi.event.*;
import com.sun.jdi.request.BreakpointRequest;
import com.sun.jdi.request.EventRequestManager;
import com.sun.tools.jdi.SocketAttachingConnector;
import sun.jvm.hotspot.jdi.SAPIDAttachingConnector;

import java.util.List;
import java.util.Map;

/**
 * Created by blues on 31/01/15.
 */
public class SimpleDebugger {

    private static final String CLASS_NAME = "com.zhiyin.devtools.demo.controller.HelloUtil";
    private static final int LINE = 10;
    private static final String VAR_NAME = "random";

    public static final String HOST = "localhost";
    public static final String PORT = "5005";

    public static void main(String[] args) {
        List<Connector> connectors = Bootstrap.virtualMachineManager().allConnectors();
        SocketAttachingConnector sac = null;
        SAPIDAttachingConnector sapac = null;
        for (Connector connector : connectors) {
            if(connector instanceof SocketAttachingConnector) {
                sac = (SocketAttachingConnector)connector;
            } else if(connector instanceof SAPIDAttachingConnector) {
                sapac = (SAPIDAttachingConnector)connector;
            }
        }
        boolean sa = System.getProperty("kis.jdi.sa") != null;
        try {

            // 1. 使用不同SP提供的connector attach到目标VM上面
            VirtualMachine vm = null;
            if(sa) {
                if(args.length != 1) {
                    // usage();
                    return;
                }
                if(sapac != null) {
                    Map<String, Connector.Argument> defaultArguments = sapac.defaultArguments();
                    Connector.Argument pidArg = defaultArguments.get("pid"); // SAPIDAttachingConnector#ARG_PID
                    pidArg.setValue(args[0]);
                    vm = sapac.attach(defaultArguments);
                    System.out.println("using sa...");
                    System.out.println(vm.allThreads());
                }
            } else {
                if(sac != null) {
                    Map<String, Connector.Argument> defaultArguments = sac.defaultArguments();
                    Connector.Argument hostArg = defaultArguments.get("hostname"); // SocketAttachingConnector#ARG_HOST
                    Connector.Argument portArg = defaultArguments.get("port"); // SocketAttachingConnector#ARG_PORT
                    hostArg.setValue(HOST);
                    portArg.setValue(PORT);
                    vm = sac.attach(defaultArguments);
                }
            }
            // process = vm.process();
            if(vm == null) {
                return;
            }

            // 2. 发送请求告诉目标VM我们需要关心哪些事件
            EventRequestManager requestManager = vm.eventRequestManager();
            List<ReferenceType> referenceTypes = vm.classesByName(CLASS_NAME);
            List<Location> locations = referenceTypes.get(0).locationsOfLine(LINE);
            BreakpointRequest breakpointRequest =
                    requestManager.createBreakpointRequest(locations.get(0));
            breakpointRequest.enable();

            // 3. 事件监听以及处理
            EventQueue eventQueue = vm.eventQueue();
            boolean disconnected = false;
            while(true) {
                if(disconnected) break;
                EventSet eventSet = eventQueue.remove();
                EventIterator eventIterator = eventSet.eventIterator();
                while(eventIterator.hasNext()) {
                    Event event = eventIterator.nextEvent();
                    if(event instanceof BreakpointEvent) {
                        System.out.println("Reach line " + LINE + " of " + CLASS_NAME);
                        BreakpointEvent breakpointEvent = (BreakpointEvent) event;
                        ThreadReference threadReference = breakpointEvent.thread();
                        StackFrame stackFrame = threadReference.frame(0);
                        LocalVariable localVariable = stackFrame
                                .visibleVariableByName(VAR_NAME);
                        Value value = stackFrame.getValue(localVariable);
                        int i = ((IntegerValue) value).value();
                        System.out.println("The local variable " + VAR_NAME + " is " + i);
                        eventSet.resume();
                    } else if (event instanceof VMDisconnectEvent) {
                        System.out.println("VM disconnected.");
                        disconnected = true;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}



