package com.zhiyin.jagent.agent.example.buddy;

import com.zhiyin.jagent.AgentCreator;
import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;

import static net.bytebuddy.matcher.ElementMatchers.any;
import static net.bytebuddy.matcher.ElementMatchers.nameMatches;
import static net.bytebuddy.matcher.ElementMatchers.named;

/**
 * Created by wangqinghui on 2016/12/9.
 */
public class AopMethodAgent  implements AgentCreator {

    @Override
    public AgentBuilder createAgent(String... agentArguments) {
//        if (agentArguments.length != 3) {
//            throw new IllegalArgumentException(
//                    "Missing agent parameters: class name, method name");
//        }

        return new AgentBuilder.Default().type( any() )
                .transform(new AgentBuilder.Transformer() {

                    @Override
                    public DynamicType.Builder<?> transform(DynamicType.Builder<?> builder,
                                                            TypeDescription typeDescription,
                                                            ClassLoader classLoader) {

                        System.out.println("tttt" + typeDescription.getName());

                        return builder.method( any() )
                                .intercept(
                                        MethodDelegation
                                                .to(MethodCallInterceptor.class));
                    }
                });
    }
}

