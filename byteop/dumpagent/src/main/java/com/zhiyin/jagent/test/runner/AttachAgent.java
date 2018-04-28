package com.zhiyin.jagent.test.runner;

import com.sun.tools.attach.VirtualMachine;
import pid.PidUitl;

public class AttachAgent {

    public static void attach(Class clzz) throws Exception {

        // server id
        int pid = PidUitl.getProcess(clzz);
        System.out.println(pid);

        VirtualMachine vm = VirtualMachine.attach( pid + "");

        String arg = "-packageStrategy white -includePackage com.zhiyin.jagent.test";
        String agentPath = "jagent-0.0.1-SNAPSHOT.jar";
        agentPath = "/Users/hg/Github/DeepStudy2/byteop/jagent/target/jagent-0.0.1.jar";
        agentPath = "/Users/hg/Github/DeepStudy2/byteop/instrument/target/instrument-0.0.1.jar";
        vm.loadAgent( agentPath,arg );
    }

}