Spring的Transactional注解是如何在mybatis中生效的？
Spring通过控制mybatis获取的connection来起作用。

### mybatis的数据库连接是从Mybatis的事务中获取的
    
```java
package org.apache.ibatis.executor;
    public abstract class BaseExecutor{
        protected Connection getConnection(Log statementLog) throws SQLException {
            Connection connection = transaction.getConnection();
            if (statementLog.isDebugEnabled()) {
              return ConnectionLogger.newInstance(connection, statementLog, queryStack);
            } else {
              return connection;
            }
        }
    }
```
### mybatis默认的事务工厂为

org.mybatis.spring.transaction.SpringManagedTransactionFactory

```java
    package org.mybatis.spring;
    public class SqlSessionFactoryBean{
        protected SqlSessionFactory buildSqlSessionFactory(){
            //部分代码略..........
            if (this.transactionFactory == null) {
                this.transactionFactory = new SpringManagedTransactionFactory();
            //部分代码略.............
        }
        }
    }
```

### SpringManagedTransaction的getConnection会调用到openConnection函数。

```java
    private void openConnection() throws SQLException {
        this.connection = DataSourceUtils.getConnection(this.dataSource);
        this.autoCommit = this.connection.getAutoCommit();
        this.isConnectionTransactional = DataSourceUtils.isConnectionTransactional(this.connection, this.dataSource);

        if (logger.isDebugEnabled()) {
          logger.debug(
              "JDBC Connection ["
                  + this.connection
                  + "] will"
                  + (this.isConnectionTransactional ? " " : " not ")
                  + "be managed by Spring");
        }
    }
```

### DataSourceUtils.getConnection最终会调用到DataSourceUtils.doGetConnection函数

```java

    public static Connection doGetConnection(DataSource dataSource) throws SQLException {
        Assert.notNull(dataSource, "No DataSource specified");

        ConnectionHolder conHolder = (ConnectionHolder) TransactionSynchronizationManager.getResource(dataSource);
        if (conHolder != null && (conHolder.hasConnection() || conHolder.isSynchronizedWithTransaction())) {
            conHolder.requested();
            if (!conHolder.hasConnection()) {
                logger.debug("Fetching resumed JDBC Connection from DataSource");
                conHolder.setConnection(dataSource.getConnection());
            }
            return conHolder.getConnection();
        }
        // Else we either got no holder or an empty thread-bound holder here.

        logger.debug("Fetching JDBC Connection from DataSource");
        Connection con = dataSource.getConnection();

        if (TransactionSynchronizationManager.isSynchronizationActive()) {
            logger.debug("Registering transaction synchronization for JDBC Connection");
            // Use same Connection for further JDBC actions within the transaction.
            // Thread-bound object will get removed by synchronization at transaction completion.
            ConnectionHolder holderToUse = conHolder;
            if (holderToUse == null) {
                holderToUse = new ConnectionHolder(con);
            }
            else {
                holderToUse.setConnection(con);
            }
            holderToUse.requested();
            TransactionSynchronizationManager.registerSynchronization(
                    new ConnectionSynchronization(holderToUse, dataSource));
            holderToUse.setSynchronizedWithTransaction(true);
            if (holderToUse != conHolder) {
                TransactionSynchronizationManager.bindResource(dataSource, holderToUse);
            }
        }

        return con;
    }
```


http://www.jianshu.com/p/42ce0f9250f5