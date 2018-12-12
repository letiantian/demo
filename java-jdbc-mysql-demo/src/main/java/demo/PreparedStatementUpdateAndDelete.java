package demo;

import java.sql.*;

/**
 * 使用 PreparedStatement 更新(修改)和删除数据
 */
public class PreparedStatementUpdateAndDelete {

    private static final String USER = "root";
    private static final String PASSWORD = "123456";

    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/bank";

    public static void update(String name, Long balance) throws ClassNotFoundException, SQLException {
        Class.forName(JDBC_DRIVER);
        Connection conn =  DriverManager.getConnection(DB_URL, USER, PASSWORD);
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement("UPDATE user_balance SET balance=? WHERE name=?");
            pstmt.setLong(1, balance);
            pstmt.setString(2, name);
            int affectRowsNum = pstmt.executeUpdate();
            System.out.println("影响的行数：" + affectRowsNum);
        } finally {
            if (pstmt != null) {
                pstmt.close();
            }
            conn.close();
        }
    }

    public static void delete(String name) throws ClassNotFoundException, SQLException {
        Class.forName(JDBC_DRIVER);
        Connection conn =  DriverManager.getConnection(DB_URL, USER, PASSWORD);
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement("DELETE FROM user_balance where name=?");
            pstmt.setString(1, name);
            int affectRowsNum = pstmt.executeUpdate();
            System.out.println("影响的行数：" + affectRowsNum);
        } finally {
            if (pstmt != null) {
                pstmt.close();
            }
            conn.close();
        }
    }


    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        update("letian", 1002L);
        delete("xiaosi");

        /**
         * 结果：
         *
         * 影响的行数：1
         * 影响的行数：1
         */
    }
}
