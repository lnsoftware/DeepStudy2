package io.github.hengyunabc.metrics;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * http://stackoverflow.com/questions/7348711/recommended-way-to-get-hostname-in-java
 * @author hengyunabc
 *
 */
@Slf4j
public class HostUtil {
	private static final Logger logger = LoggerFactory
			.getLogger(HostUtil.class);

	public static void main(String[] args) {
		System.err.println(getHostName());
		System.err.println(getHostAddress());
		System.err.println(getNotLoopbackAddress());

        System.out.println( ipInt( getHostAddress()));
        System.out.println(inetAddressToInt());
    }

	public static String getHostName() {
		try {
			String hostName = InetAddress.getLocalHost().getHostName();
			if (hostName != null && !hostName.isEmpty()) {
				return hostName;
			}
		} catch (UnknownHostException e) {
			logger.error("get hostName error!", e);
		}

		String host = System.getenv("COMPUTERNAME");
		if (host != null)
			return host;
		host = System.getenv("HOSTNAME");
		if (host != null)
			return host;

		return null;
	}

	public static String getNotLoopbackAddress() {
		String hostName = null;
		Enumeration<NetworkInterface> interfaces;
		try {
			interfaces = NetworkInterface.getNetworkInterfaces();
			while (interfaces.hasMoreElements()) {
				NetworkInterface nic = interfaces.nextElement();
				Enumeration<InetAddress> addresses = nic.getInetAddresses();
				while (hostName == null && addresses.hasMoreElements()) {
					InetAddress address = addresses.nextElement();
					if (!address.isLoopbackAddress()) {
						hostName = address.getHostName();
					}
				}
			}
		} catch (SocketException e) {
			logger.error("getNotLoopbackAddress error!", e);
		}
		return hostName;
	}

	public static String getHostAddress() {
		try {
			return InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			logger.error("get hostAddress error!", e);
		}

		return null;
	}



	public static long ipInt(String addr){

	    if(addr == null || addr.trim().equals("")){
	        return 0;
        }

        int[] ip = new int[4];
        String[] parts = addr.split("\\.");

        for (int i = 0; i < 4; i++) {
            ip[i] = Integer.parseInt(parts[i]);
        }

        long ipNumbers = 0;
        for (int i = 0; i < 4; i++) {
            ipNumbers += ip[i] << (24 - (8 * i));
        }

        return ipNumbers;
    }


    public static int inetAddressToInt( )
              {

                  try {
                      byte[] addr = InetAddress.getLocalHost().getAddress();

                      if (addr.length != 4) {
                          throw new IllegalArgumentException("Not an IPv4 address");
                      }
                      return ((addr[3] & 0xff) << 24) | ((addr[2] & 0xff) << 16) |
                              ((addr[1] & 0xff) << 8) | (addr[0] & 0xff);
                  }catch (Exception e){
                      log.error("get host ip error",e);
                  }
                  return 0;
    }

}
