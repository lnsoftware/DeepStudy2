/**
 * Copyright (c) 2015 the original author or authors
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.zhiyin.eureka.instrument.web.client.rule;

import com.netflix.loadbalancer.Server;
import com.zhiyin.eureka.instrument.web.client.ServerInvokerConst;
import com.zhiyin.eureka.instrument.web.client.predicate.DiscoveryEnabledPredicate;
import com.zhiyin.eureka.instrument.web.client.predicate.MetadataAwarePredicate;
import com.zhiyin.eureka.instrument.web.client.support.RibbonFilterContextHolder;
import lombok.Data;

import java.util.List;

@Data
public class MetadataAwareRule extends DiscoveryEnabledRule {

    private List<String> candidateServerMarks;

    public MetadataAwareRule() {
        this(new MetadataAwarePredicate());
    }

    public MetadataAwareRule(DiscoveryEnabledPredicate predicate) {
        super(predicate);
    }

    @Override
    public Server choose(Object key) {

//        List<Server> server = getLoadBalancer().getAllServers();
//        if


        Server ret = super.choose(key);

        if (ret != null) {
            return ret;
        }

        candidateServerMarks = RibbonFilterContextHolder.getMark();
        if (candidateServerMarks == null || candidateServerMarks.size() == 0) {
            return ret;
        }
        for (String markMap : candidateServerMarks) {
            RibbonFilterContextHolder.getCurrentContext().add(ServerInvokerConst.SERVER_MARK_NAME, markMap);
            if( "*".equals(markMap) ){
                RibbonFilterContextHolder.getCurrentContext().remove(ServerInvokerConst.SERVER_MARK_NAME);
            }
            ret = super.choose(key);
            // 如果markMap为*,不需要进行下面判断
            if (ret != null || "*".equals(markMap)) {
                return ret;
            }
        }
        return ret;
    }

}
