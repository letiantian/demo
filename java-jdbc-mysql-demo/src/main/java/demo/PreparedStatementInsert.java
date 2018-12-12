package demo;

import java.sql.*;

/**
 *  使用 PreparedStatement 查询(添加、增加)数据
 */
public class PreparedStatementInsert {

    private static final String USER = "root";
    private static final String PASSWORD = "123456";

    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/bank";

    public static void insertV1(String name, Long balance) throws ClassNotFoundException, SQLException {
        Class.forName(JDBC_DRIVER);
        Connection conn =  DriverManager.getConnection(DB_URL, USER, PASSWORD);
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement("INSERT INTO user_balance(name, balance) VALUES(?, ?)");
            pstmt.setString(1, name);
            pstmt.setLong(2, balance);
            int affectRowsNum = pstmt.executeUpdate();
            System.out.println("影响的行数：" + affectRowsNum);
        } finally {
            if (pstmt != null) {
                pstmt.close();
            }
            conn.close();
        }
    }

    public static void insertV2(String name, Long balance) throws ClassNotFoundException, SQLException {
        Class.forName(JDBC_DRIVER);
        Connection conn =  DriverManager.getConnection(DB_URL, USER, PASSWORD);
        PreparedStatement pstmt = null;
        try {
            // 指定第2个参数为Statement.RETURN_GENERATED_KEYS，可以获取生成的主键id值
            pstmt = conn.prepareStatement("INSERT INTO user_balance(name, balance) VALUES(?, ?)", Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, name);
            pstmt.setLong(2, balance);
            int affectRowsNum = pstmt.executeUpdate();
            System.out.println("影响的行数：" + affectRowsNum);
            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    System.out.println("生成的主键ID是：" + generatedKeys.getLong(1));
                }
            }
        } finally {
            if (pstmt != null) {
                pstmt.close();
            }
            conn.close();
        }
    }


    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        insertV1("letian", 1000L);
        insertV2("xiaosi", 1001L);
//        insertV2("xiaosi😆", 1001L); // 会成功

        /**
         * 结果：
         *
         * 影响的行数：1
         * 影响的行数：1
         * 生成的主键ID是：2
         */
    }

}
