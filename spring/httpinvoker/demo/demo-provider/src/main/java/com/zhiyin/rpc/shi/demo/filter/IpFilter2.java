//package com.zhiyin.rpc.shi.demo.filter;
//
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.net.util.SubnetUtils;
//import org.springframework.web.filter.RequestContextFilter;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//@Slf4j
////@WebFilter
//public class IpFilter2 extends RequestContextFilter {
//    // Get the user's real IP address, accounting for Cloudflare and Heroku proxying.
//    public static String getForwardedIp(HttpServletRequest request) {
//        String ip = null;
//
//        // Believe the Cloudflare header only if the last IP was from Cloudflare.
//        if (ipIsInList(getForwardedIp(request, 1), cloudflareIps))
//            ip = request.getHeader("CF-Connecting-IP");
//
//        // If there is no Cloudflare header, return the last known reliable IP.
//        if (ip == null) ip = getForwardedIp(request, 1);
//        return ip;
//    }
//
//    // Get the n-th latest IP from the X-Forwarded-For header.
//    public static String getForwardedIp(HttpServletRequest request, int degree) {
//        if (degree == 0) {
//            return request.getRemoteAddr();
//        }
//
//        // Believe the X-Forwarded-For header only if it came from a trusted internal IP.
//        if (!ipIsInList(request.getRemoteAddr(), internalIps)) {
//            System.err.println("Last IP address was " + request.getRemoteAddr() + ", which is not a trusted internal IP.");
//            return null;
//        }
//
//        // Return null if the X-Forwarded-For header was not found.
//        String header = request.getHeader("X-Forwarded-For");
//        if (header == null) return null;
//
//        String[] ips = header.split(",");
//        if (degree > ips.length) return null;
//        return ips[ips.length - degree].trim();
//    }
//
//    // Check if the IP falls within any of the CIDR IP ranges in the list.
//    public static boolean ipIsInList(String ip, String[] list) {
//        if (ip == null) return false;
//
//        int failed = 0;
//        for (String listIp : list) {
//            SubnetUtils subnet = new SubnetUtils(listIp);
//            subnet.setInclusiveHostCount(true);
//
//            if (!subnet.getInfo().isInRange(ip))
//                failed++;
//        }
//
//        return failed < list.length;
//    }
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//
//        log.info("ip filter.");
//
//        if(!"application/x-java-serialized-object".equals(request.getContentType()) ){
//            filterChain.doFilter(request, response);
//            return;
//        }
//
//        // Do not block localhost.
//        if (request.getRemoteAddr().equals("127.0.0.1")) {
//            filterChain.doFilter(request, response);
//            return;
//        }
//
//        boolean allowed = true;
//
//        // Refuse connections that circumvented Cloudflare.
//        // Checking the latest IP from the X-Forwarded-For header on Heroku, since request.getRemoteAddr() seems to return an IP in Heroku's internal network.
//        if (!ipIsInList(request.getRemoteAddr(), internalIps) || (getForwardedIp(request, 1) != null && !ipIsInList(getForwardedIp(request, 1), bypassCloudflareIps) && !ipIsInList(getForwardedIp(request, 1), cloudflareIps)))
//            allowed = false;
//
//        if (allowed) {
//            // Check if the IP before Cloudflare is blacklisted.
//            String proxiedIp = getForwardedIp(request, 2);
//
//            if (proxiedIp != null) {
//                for (String ip : blacklistIps) {
//                    SubnetUtils subnet = new SubnetUtils(ip);
//                    subnet.setInclusiveHostCount(true);
//                    if (!subnet.getInfo().isInRange(proxiedIp)) {
//                        allowed = false;
//                        break;
//                    }
//                }
//            }
//        }
//
//        // If the request failed one of the tests, send an error response and do not continue processing the request.
//        if (!allowed) {
//            response.sendError(HttpServletResponse.SC_SERVICE_UNAVAILABLE);
//            return;
//        }
//
//        // If the request passed the tests, allow it to be processed normally.
//        filterChain.doFilter(request, response);
//    }
//
//    public static final String[] cloudflareIps = {
////            "204.93.240.0/24",
////            "204.93.177.0/24",
////            "199.27.128.0/21",
////            "173.245.48.0/20",
////            "103.21.244.0/22",
////            "103.22.200.0/22",
////            "103.31.4.0/22",
////            "141.101.64.0/18",
////            "108.162.192.0/18",
////            "190.93.240.0/20",
////            "188.114.96.0/20",
////            "197.234.240.0/22",
////            "198.41.128.0/17",
////            "162.158.0.0/15",
//    };
//
//    public static final String[] bypassCloudflareIps = {
//            // UptimeRobot IPs
////            "74.86.158.106/32",
////            "74.86.158.107/32",
////            "74.86.179.130/32",
////            "74.86.179.131/32",
////            "46.137.190.132/32",
////            "122.248.234.23/32",
//    };
//
//    public static final String[] internalIps = {
//            // Heroku internal IPs
////            "10.0.0.0/8",
////            "172.16.0.0/12",
////            "192.168.0.0/16",
////            "127.0.0.1/32",
//    };
//
//    public static final String[] blacklistIps = {
//            "172.17.0.0/8",
//    };
//}