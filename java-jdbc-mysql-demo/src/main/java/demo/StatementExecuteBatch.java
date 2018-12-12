package demo;

import java.sql.*;
import java.util.Arrays;

/**
 * 使用 Statement executeBatch 进行批处理
 */
public class StatementExecuteBatch {

    private static final String USER = "root";
    private static final String PASSWORD = "123456";

    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/bank";

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Class.forName(JDBC_DRIVER);
        Connection conn =  DriverManager.getConnection(DB_URL, USER, PASSWORD);
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            stmt.addBatch("INSERT INTO user_balance(name, balance) VALUES('letian', 1000)");
            stmt.addBatch("INSERT INTO user_balance(name, balance) VALUES('xiaosi', 1001)");
            int[] affectRowsArray = stmt.executeBatch();
            System.out.println("影响的行数：" + Arrays.toString(affectRowsArray));
        } finally {
            if (stmt != null) {
                stmt.close();
            }
            conn.close();
        }
    }
}
