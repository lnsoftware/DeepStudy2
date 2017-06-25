package jdbc;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import io.swagger.models.auth.In;
import java.sql.Connection;
import java.util.Map;

/**
 * Created by hg on 2017/6/25.
 */
public class ConnUtil {

    public static ConnStatus getStatus(Connection connection){
        ConnStatus status = new ConnStatus();
        try {
            status.setTransactionIsolation(transMap.get(connection.getTransactionIsolation()));
            status.setAutoCommit(connection.getAutoCommit());
            status.setReadOnly(connection.isReadOnly());
        }catch (Exception e){
            e.printStackTrace();
        }
        return status;
    }

    public static void printInfo(Connection connection){
        System.out.println(JSON.toJSONString(getStatus(connection)));
    }

    static Map<Integer,String> transMap = Maps.newHashMap();
    static {
        transMap.put(Connection.TRANSACTION_NONE,"TRANSACTION_NONE");
        transMap.put(Connection.TRANSACTION_READ_UNCOMMITTED,"TRANSACTION_READ_UNCOMMITTED");
        transMap.put(Connection.TRANSACTION_READ_COMMITTED,"TRANSACTION_READ_COMMITTED");
        transMap.put(Connection.TRANSACTION_REPEATABLE_READ,"TRANSACTION_REPEATABLE_READ");
        transMap.put(Connection.TRANSACTION_SERIALIZABLE,"TRANSACTION_SERIALIZABLE");
    }
}
