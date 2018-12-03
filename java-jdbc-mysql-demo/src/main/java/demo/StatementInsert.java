package demo;

import java.sql.*;

/**
 * 插入数据
 */
public class StatementInsert {

    private static final String USER = "root";
    private static final String PASSWORD = "123456";

    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/bank";

    public static void insertV1(String name, Long balance) throws ClassNotFoundException, SQLException {
        Class.forName(JDBC_DRIVER);
        Connection conn =  DriverManager.getConnection(DB_URL, USER, PASSWORD);
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            String sql = String.format("INSERT INTO user_balance(name, balance) VALUES('%s', %s)", name, balance);
            int affectRowsNum = stmt.executeUpdate(sql);
            System.out.println("影响的行数：" + affectRowsNum);
        } finally {
            if (stmt != null) {
                stmt.close();
            }
            conn.close();
        }
    }

    public static void insertV2(String name, Long balance) throws ClassNotFoundException, SQLException {
        Class.forName(JDBC_DRIVER);
        Connection conn =  DriverManager.getConnection(DB_URL, USER, PASSWORD);
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            String sql = String.format("INSERT INTO user_balance(name, balance) VALUES('%s', %s)", name, balance);
            // 指定第2个参数为Statement.RETURN_GENERATED_KEYS，可以获取生成的主键id值
            int affectRowsNum = stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
            System.out.println("影响的行数：" + affectRowsNum);
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    System.out.println("生成的主键ID是：" + generatedKeys.getLong(1));
                }
            }
        } finally {
            if (stmt != null) {
                stmt.close();
            }
            conn.close();
        }
    }


    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        insertV1("letian", 1000L);
        insertV2("xiaosi", 1001L);

        /**
         * 结果：
         *
         * 影响的行数：1
         * 影响的行数：1
         * 生成的主键ID是：2
         */
    }
}
