package jdbc;

import lombok.Data;

/**
 * Created by hg on 2017/6/25.
 */
@Data
public class ConnStatus {
    String transactionIsolation;
    boolean autoCommit;
    boolean isReadOnly;
}
