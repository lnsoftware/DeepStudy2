package com.zhiyin.rpc.shi.demo.config;

import com.baidu.disconf.client.common.annotations.DisconfFile;
import com.baidu.disconf.client.common.annotations.DisconfFileItem;
import com.baidu.disconf.client.common.annotations.DisconfUpdateService;
import com.baidu.disconf.client.common.update.IDisconfUpdate;
import com.zhiyin.rpc.shi.demo.rpc.CommandKeyUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope("singleton")
@DisconfFile(filename = "hystrix.properties")
@DisconfUpdateService(classes = {HystrixParmConfig.class})
public class HystrixParmConfig implements IDisconfUpdate {

    protected static final Logger LOGGER = LoggerFactory.getLogger(HystrixParmConfig.class);

    private String conf;

    @DisconfFileItem(name = "conf", associateField = "conf")
    public String getConf() {
        return conf;
    }

    public void setConf(String conf) {
        this.conf = conf;
    }


    public void reload() throws Exception {
        LOGGER.info("hostchange: " + conf + this.getConf()) ;
        CommandKeyUtil.setIndex( conf );
    }
}
