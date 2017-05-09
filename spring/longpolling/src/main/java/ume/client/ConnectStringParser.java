package ume.client;

import com.google.common.collect.Lists;
import java.net.InetSocketAddress;
import java.util.ArrayList;


public final class ConnectStringParser {
    private static final int DEFAULT_PORT = 2181;

    private final ArrayList<String> serverAddresses = Lists.newArrayList();
    /**
     * 
     * @throws IllegalArgumentException
     *             for an invalid chroot path.
     */
    public ConnectStringParser(String connectString) {

        String hostsList[] = connectString.split(",");
        for (String host : hostsList) {
            serverAddresses.add( host );
        }
    }

    public ArrayList<String> getServerAddresses() {
        return serverAddresses;
    }
}