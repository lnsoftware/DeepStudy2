package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class JDBCExample2 {

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/study_mysql";
    static final String USER = "root";
    static final String PASS = "root";

    public static void main(String[] args) throws Exception {

        Connection conn = null;
        Statement stmt = null;

        Class.forName("com.mysql.jdbc.Driver");
        conn = DriverManager.getConnection(DB_URL, USER, PASS);

        ConnUtil.printInfo(conn);
        conn.close();

    }
}
