package com.zhiyin.jagent;

import com.google.common.base.Optional;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import lombok.Data;
import org.kohsuke.args4j.Option;

import java.util.Arrays;
import java.util.List;

/**
 * Created by wangqinghui on 2017/1/5.
 */
@Data
public class AgentCommandBean {

    @Option(name="-excludePackage",usage="Sets a file if that is present")
    private String excludePackage;

    @Option(name="-includePackage",usage="Sets a file if that is present")
    private String includePackage;

    @Option(name="-packageStrategy",usage="Sets a file if that is present")
    private String packageStrategy;

    public List<String> getExcludePackages(){

        if(Strings.isNullOrEmpty(excludePackage)){
            return Lists.newArrayList();
        }
        return Arrays.asList( excludePackage.trim().split(","));

    }

    public List<String> getIncludePackages(){

        if(Strings.isNullOrEmpty(includePackage)){
            return Lists.newArrayList();
        }
        return Arrays.asList( includePackage.trim().split(","));
    }

    public String getPackageStrategy(){
        String tmp = Optional.fromNullable(packageStrategy).or("black");
        if(AgentConfig.PackageStrategyWhite.equals(tmp)){
            return AgentConfig.PackageStrategyWhite;
        }
        return AgentConfig.PackageStrategyBlack;
    }

    public static AgentCommandBean getDefault(){
        AgentCommandBean agentCommandBean = new AgentCommandBean();
        agentCommandBean.setExcludePackage("");

        return agentCommandBean;
    }


}
