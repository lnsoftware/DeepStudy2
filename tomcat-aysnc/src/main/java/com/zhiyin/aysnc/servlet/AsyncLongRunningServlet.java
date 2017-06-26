package com.zhiyin.aysnc.servlet;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.ThreadPoolExecutor;

@WebServlet(urlPatterns = "/AsyncLongRunningServlet", asyncSupported = true)
public class AsyncLongRunningServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        long startTime = System.currentTimeMillis();
        System.out.println("AsyncLongRunningServlet Start::Name="
                + Thread.currentThread().getName() + "::ID="
                + Thread.currentThread().getId());

        request.setAttribute("org.apache.catalina.ASYNC_SUPPORTED", true);

        String time = request.getParameter("workTime");
        String timeoutStr = request.getParameter("timeout");
        int workTime = 10000;
        int timeout = 10000;
        try {
            workTime = Integer.valueOf(time);
            timeout = Integer.valueOf(timeoutStr);
        } catch (Exception e) {
            workTime = 10000;
            timeout = 10000;
        }

        AsyncContext asyncContext = request.startAsync();
        asyncContext.addListener(new AppAsyncListener());
        asyncContext.setTimeout(timeout);
        ThreadPoolExecutor executor = (ThreadPoolExecutor) request
                .getServletContext().getAttribute("executor");
        executor.execute(new AsyncRequestProcessor(asyncContext, workTime));
        long endTime = System.currentTimeMillis();
        System.out.println("AsyncLongRunningServlet End::Name="
                + Thread.currentThread().getName() + "::ID="
                + Thread.currentThread().getId() + "::Time Taken="
                + (endTime - startTime) + " ms.");
    }

}
