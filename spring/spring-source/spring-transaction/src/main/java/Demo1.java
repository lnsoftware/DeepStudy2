//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.SQLException;
//import java.sql.Savepoint;
//
//public class Demo1 {
//
//    public void test1() {
//
//        Connection conn = null;
//
//        PreparedStatement ps1 = null;
//
//        PreparedStatement ps2 = null;
//
//        Savepoint sp = null;
//
//        try {
//
//            Class.forName("com.MySQL.jdbc.Driver");
//
//            conn = DriverManager.getConnection("jdbc:mysql:///day11", "root", "root");
//
//            conn.setTransactionIsolation();
//            conn.setAutoCommit(false);
//
//            ps1 = conn.prepareStatement("update account set money = money+100 where name=?");
//
//            ps1.setString(1, "b");
//
//            ps1.executeUpdate();
//
////int i = 1/0;
//
//            ps2 = conn.prepareStatement("update account set money = money-100 where name=?");
//
//            ps2.setString(1, "a");
//
//            ps2.executeUpdate();
//
//            sp = conn.setSavepoint();
//
////-----------------------------------
//
//            ps1 = conn.prepareStatement("update account set money = money+100 where name=?");
//
//            ps1.setString(1, "b");
//
//            ps1.executeUpdate();
//
//            int i = 1 / 0;
//
//            ps2 = conn.prepareStatement("update account set money = money-100 where name=?");
//
//            ps2.setString(1, "a");
//
//            ps2.executeUpdate();
//
//            conn.commit();
//
//        } catch (Exception e) {
//
//            e.printStackTrace();
//
//            try {
//
//                if (sp != null) {
//
//                    conn.rollback(sp);
//
//                    conn.commit();
//
//                } else {
//
//                    conn.rollback();
//
//                }
//
//            } catch (SQLException e1) {
//
//// TODO Auto-generated catch block
//
//                e1.printStackTrace();
//
//            }
//
//        } finally {
//
//            DaoUtil.close(conn, ps1, null);
//
//            DaoUtil.close(conn, ps2, null);
//
//        }
//
//    }
//
//}
//
