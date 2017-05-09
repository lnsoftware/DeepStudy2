package discovery;

import org.codehaus.jackson.map.annotate.JsonRootName;



/**
 * Created by hupeng on 2014/9/16.
 */
@JsonRootName("details")
public class InstanceDetails {

    private String id;

    private String listenAddress;

    private int listenPort;

    private String interfaceName;

    public InstanceDetails(String id, String listenAddress, int listenPort,String interfaceName) {
        this.id = id;
        this.listenAddress = listenAddress;
        this.listenPort = listenPort;
        this.interfaceName = interfaceName;
    }

    public InstanceDetails() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getListenAddress() {
        return listenAddress;
    }

    public void setListenAddress(String listenAddress) {
        this.listenAddress = listenAddress;
    }

    public int getListenPort() {
        return listenPort;
    }

    public void setListenPort(int listenPort) {
        this.listenPort = listenPort;
    }

    public String getInterfaceName() {
        return interfaceName;
    }

    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
    }

    @Override
    public String toString() {
        return "InstanceDetails{" +
                "id='" + id + '\'' +
                ", listenAddress='" + listenAddress + '\'' +
                ", listenPort=" + listenPort +
                ", interfaceName='" + interfaceName + '\'' +
                '}';
    }
}