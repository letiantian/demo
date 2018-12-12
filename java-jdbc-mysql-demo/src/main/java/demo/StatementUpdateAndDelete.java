package demo;

import java.sql.*;

/**
 * 使用 Statement 更新和删除数据
 */
public class StatementUpdateAndDelete {

    private static final String USER = "root";
    private static final String PASSWORD = "123456";

    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/bank";

    /**
     * 根据name更新balance
     */
    public static void update(String name, Long balance) throws ClassNotFoundException, SQLException {
        Class.forName(JDBC_DRIVER);
        Connection conn =  DriverManager.getConnection(DB_URL, USER, PASSWORD);
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            String sql = String.format("UPDATE user_balance SET balance=%s WHERE name='%s'", balance, name);
            int affectRowsNum = stmt.executeUpdate(sql);
            System.out.println("影响的行数：" + affectRowsNum);
        } finally {
            if (stmt != null) {
                stmt.close();
            }
            conn.close();
        }
    }

    /**
     * 根据 name 删除数据
     */
    public static void delete(String name) throws ClassNotFoundException, SQLException {
        Class.forName(JDBC_DRIVER);
        Connection conn =  DriverManager.getConnection(DB_URL, USER, PASSWORD);
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            String sql = String.format("DELETE FROM user_balance where name='%s'", name);
            int affectRowsNum = stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
            System.out.println("影响的行数：" + affectRowsNum);
        } finally {
            if (stmt != null) {
                stmt.close();
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
