package com.zhiyin.byteop.demo.ttl;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.zhiyin.byteop.demo.ConstConfig;
import com.zhiyin.byteop.demo.advice.ProfiledAdvice;
import com.zhiyin.byteop.demo.agent.ErrorListener;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.agent.ByteBuddyAgent;
import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.asm.Advice;
import net.bytebuddy.dynamic.ClassFileLocator;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.dynamic.loading.ClassLoadingStrategy;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;
import net.bytebuddy.pool.TypePool;
import org.junit.Test;

import java.io.File;
import java.util.concurrent.*;

import static net.bytebuddy.matcher.ElementMatchers.named;
import static net.bytebuddy.matcher.ElementMatchers.takesArguments;

/**
 * Created by wangqinghui on 2017/2/20.
 */
public class TestThreadPoolArgChange {

    @Test
    public void hello() throws Exception {

        DynamicType.Unloaded<MineThreadPoolExecutor> build = new ByteBuddy()
                .subclass(MineThreadPoolExecutor.class)
                .name("Demo3")
                .method(
                        named("submit")
                        .and(takesArguments(Runnable.class))
                )
                .intercept(
                        MethodDelegation.to(RunnableIntercept.class)
//                                .andThen(SuperMethodCall.INSTANCE)
//                        Advice.to(RunnableAdvice.class)
                )
                .make();
        build.saveIn(new File(ConstConfig.SavePath));

        Class<?> dynamicType = build.load(getClass().getClassLoader()).getLoaded();

//        String ret = dynamicType.newInstance().toString();
//        System.out.println(ret);
//        assertThat(dynamicType.newInstance().toString(), is("Hello World!"));

    }

        @Test
        public void test() throws Exception {

            TypePool typePool = TypePool.Default.ofClassPath();
            new ByteBuddy()
                    .rebase(typePool.describe(MineThreadPoolExecutor.class.getName()).resolve(),
                            ClassFileLocator.ForClassLoader.ofClassPath())
                    .method( named("submit")
                            .and(takesArguments(Runnable.class))
            )
                    .intercept(
//                            MethodDelegation.to(RunnableChangeIntercept.class)
                            Advice.to(RunnableAdvice.class)
                    ).make()
                    .load(ClassLoader.getSystemClassLoader(), ClassLoadingStrategy.Default.INJECTION);

            TransmittableThreadLocal<String> parent = new TransmittableThreadLocal<String>();
            parent.set("value-set-in-parent");

            ExecutorService executorService = Executors.newFixedThreadPool(3);
            Future<?> submit = executorService.submit(new Runnable() {
                @Override
                public void run() {

                    System.out.println("exec");
                }
            });

            submit.get();
        }



    @Test
    public void testAgent() throws ExecutionException, InterruptedException {
        ExecutorService executorService22 = Executors.newFixedThreadPool(3);
        ByteBuddyAgent.install();
        new ThreadPoolExecutor(1, 1,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>());
        new AgentBuilder.Default()
                .with(new ErrorListener())
                .with(AgentBuilder.RedefinitionStrategy.RETRANSFORMATION)
                .with(AgentBuilder.InitializationStrategy.NoOp.INSTANCE)
                .with(AgentBuilder.TypeStrategy.Default.REDEFINE)
                .type(ElementMatchers.named(ThreadPoolExecutor.class.getName()))
                .transform((builder, typeDescription, classLoader) -> builder
//                        .visit(
//                                Advice.to(RunnableAdvice.class).on(
//                                        named("submit")
//                                                .and(takesArguments(Runnable.class))
//
//                                )
//                        )
                        .visit(Advice.to(ProfiledAdvice.class).on(ElementMatchers.any()))
                )
                .installOnByteBuddyAgent();


        ExecutorService executorService = Executors.newFixedThreadPool(3);
        Future<?> submit = executorService.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println(this.getClass().getName());

                System.out.println("exec2");
            }
        });

        submit.get();
    }



}

