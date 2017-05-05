package com.zhiyin.rpc.shi.demo.filter;

import com.google.common.base.Strings;
import org.apache.commons.net.util.SubnetUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


@WebFilter
public class IpFilter implements Filter {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private FilterConfig config;

    private String IpFilterFile = "ipfilter.properties";

    public void init(FilterConfig filterConfig) {
        this.config = filterConfig;
        // optionally you can get regex from init parameter overwriting the class' private variable
        String tmpFile = config.getInitParameter("IpFilterFile");

        if(tmpFile == null){
            System.out.println("ip filter file is null, user default file:"+IpFilterFile);
        }else{
            IpFilterFile = tmpFile;
            System.out.println("ip filter file:"+IpFilterFile);
        }


        InputStream input = IpFilter.class
                .getResourceAsStream("/" + IpFilterFile);

        Properties prop = new Properties();
        try {

            if( input != null ){
                prop.load(input);
            }else{
                log.error("ip filter file not found, file name:{}",IpFilterFile);
            }

        } catch (IOException e) {
            e.printStackTrace();
            log.error("read ip filter file error. ",e);
        }

        // 黑名单
        String blacklistIpsStr = prop.getProperty("blacklistIps");
        if(!Strings.isNullOrEmpty(blacklistIpsStr)){
            blacklistIps = blacklistIpsStr.split(",");
            System.out.println("filter ip is:"+ blacklistIpsStr);
        }

        // 白名单
        String whitelistIpsStr = prop.getProperty("whitelistIps");
        if(!Strings.isNullOrEmpty(whitelistIpsStr)){
            whitelistIps = whitelistIpsStr.split(",");
            System.out.println("filter ip is:"+ whitelistIpsStr);
        }

    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        if (request instanceof HttpServletRequest ){
            HttpServletRequest httpReq = (HttpServletRequest) request;
            if (response instanceof HttpServletResponse){
                HttpServletResponse httpResp = (HttpServletResponse) response;
                doFilterInternal(httpReq,httpResp,chain);
                return;
            }
        }

        chain.doFilter(request,response);
        return;
    }

    @Override
    public void destroy() {

    }



    // Check if the IP falls within any of the CIDR IP ranges in the list.
//    public static boolean ipIsInList(String ip, String[] list) {
//        if (ip == null) return false;
//
//        int failed = 0;
//        for (String listIp : list) {
//            SubnetUtils subnet = new SubnetUtils(listIp);
//            subnet.setInclusiveHostCount(true);
//            if (!subnet.getInfo().isInRange(ip))
//                failed++;
//        }
//
//        return failed < list.length;
//    }


    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        // httpinvoker contenttype
        if(!"application/x-java-serialized-object".equals(request.getContentType()) ){
            filterChain.doFilter(request, response);
            return;
        }

        // Do not block localhost.
        if (request.getRemoteAddr().equals("127.0.0.1")) {
            filterChain.doFilter(request, response);
            return;
        }


        boolean inwhite = false;

        String realIp = request.getRemoteAddr();
        for ( String ip : whitelistIps ) {
            SubnetUtils subnet = new SubnetUtils(ip);
            subnet.setInclusiveHostCount(true);
            if (!subnet.getInfo().isInRange(realIp)) {
                inwhite = true;
            }
        }

        boolean allowed = true;
        if ( !inwhite ) {

            if (realIp != null) {
                for (String ip : blacklistIps) {
                    SubnetUtils subnet = new SubnetUtils(ip);
                    subnet.setInclusiveHostCount(true);
                    if (subnet.getInfo().isInRange( realIp)) {
                        allowed = false;
                        System.out.println(ip + "is not allowed.");
                        break;
                    }
                }
            }
        }


//        basePath:http://localhost:8080/test/
//        getContextPath:/test
//        getServletPath:/test.jsp
//        getRequestURI:/test/test.jsp
//        getRequestURL:http://localhost:8080/test/test.jsp
//        getRealPath:D:\Tomcat 6.0\webapps\test\
//        getServletContext().getRealPath:D:\Tomcat 6.0\webapps\test\
//        getQueryString:p=fuck
        String path = request.getServletPath();
//        String url = request.getRequestURI();
        
        stat(realIp,path,allowed);


        // If the request failed one of the tests, send an error response and do not continue processing the request.
        if ( !allowed ) {
            response.sendError(HttpServletResponse.SC_SERVICE_UNAVAILABLE);
            return;
        }

        // If the request passed the tests, allow it to be processed normally.
        filterChain.doFilter(request, response);
    }

    public void stat(String ip,String path ,boolean allow){

        System.out.println( ip + " " + path + " " + allow );

    }


    public static  String[] blacklistIps = {
    };

    public static  String[] whitelistIps = {
    };
}