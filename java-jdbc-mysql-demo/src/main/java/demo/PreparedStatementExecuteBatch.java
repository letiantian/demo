package demo;

import java.sql.*;
import java.util.Arrays;

/**
 * 使用 PreparedStatement executeBatch 进行批处理
 */
public class PreparedStatementExecuteBatch {

    private static final String USER = "root";
    private static final String PASSWORD = "123456";

    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/bank";

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Class.forName(JDBC_DRIVER);
        Connection conn =  DriverManager.getConnection(DB_URL, USER, PASSWORD);
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement("INSERT INTO user_balance(name, balance) VALUES(?, ?)");
            pstmt.setString(1, "letian");
            pstmt.setLong(2, 1000L);
            pstmt.addBatch();
            pstmt.setString(1, "xiaosi");
            pstmt.setLong(2, 1001L);
            pstmt.addBatch();
            int[] affectRowsArray = pstmt.executeBatch();
            System.out.println("影响的行数：" + Arrays.toString(affectRowsArray));
        } finally {
            if (pstmt != null) {
                pstmt.close();
            }
            conn.close();
        }
    }
}
