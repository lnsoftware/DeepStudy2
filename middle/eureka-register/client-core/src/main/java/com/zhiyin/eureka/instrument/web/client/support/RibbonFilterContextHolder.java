/**
 * Copyright (c) 2015 the original author or authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.zhiyin.eureka.instrument.web.client.support;


import com.zhiyin.eureka.instrument.web.client.api.RibbonFilterContext;

import java.util.List;

/**
 * The Ribbon filter context holder.
 *
 * @author Jakub Narloch
 */
public class RibbonFilterContextHolder {

    /**
     * Stores the {@link RibbonFilterContext} for current thread.
     */
    private static final ThreadLocal<RibbonFilterContext> contextHolder = new InheritableThreadLocal<RibbonFilterContext>() {
        @Override
        protected RibbonFilterContext initialValue() {
            return new DefaultRibbonFilterContext();
        }
    };

    private static final ThreadLocal<List<String>> markMap = new InheritableThreadLocal<List<String>>();

    /**
     * Retrieves the current thread bound instance of {@link RibbonFilterContext}.
     *
     * @return the current context
     */
    public static RibbonFilterContext getCurrentContext() {
        return contextHolder.get();
    }

    /**
     * Clears the current context.
     */
    public static void clearCurrentContext() {
        contextHolder.remove();
    }

    public static void setMarkMap(List<String> list){
        markMap.set(list);
    }

    public static List<String> getMark(){
        return markMap.get();
    }
}
