//package com.zhiyin.byteop.demo.interceptor;
//
//import net.bytebuddy.agent.builder.AgentBuilder;
//import net.bytebuddy.asm.Advice;
//import net.bytebuddy.description.field.FieldDescription;
//import net.bytebuddy.description.method.ParameterList;
//
//public class ServletInstrumentation {
//
//  public AgentBuilder instrument(AgentBuilder agentBuilder) {
//    return AdviceRegistry.subTypesOf(HttpServlet.class)
//        .advice((method) -> {
//            ParameterList<FieldDescription.InDefinedShape> parameters = method.getParameters();
//            return method.getInternalName().equals("service")
//                && parameters.size() == 2
//                && parameters.get(0).getType().isAssignableTo(HttpServletRequest.class)
//                && parameters.get(1).getType().isAssignableTo(HttpServletResponse.class);
//        }, ServletAdvice.class).register(agentBuilder);
//  }
//
//  private static class ServletAdvice {
//    @Advice.OnMethodEnter
//    private static void enter(@Advice.Argument(0) HttpServletRequest request, @Tag int tag) {
//      String method = request.getMethod();
//      String requestUri = request.getRequestURI();
//      String traceId = request.getHeader(TracingHeaders.TRACE_ID);
//      Callbacks.find(ServletInstrumentation.class).call(1, method, requestUri, traceId);
//    }
//
//    @Advice.OnMethodExit
//    private static void exit(@Advice.Argument(1) HttpServletResponse response) {
//      Callbacks.find(ServletInstrumentation.class).call(2, response);
//    }
//  }
//}