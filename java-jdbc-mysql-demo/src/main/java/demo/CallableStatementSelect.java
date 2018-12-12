package demo;

import java.sql.*;

/**
 * 存储过程
 */
public class CallableStatementSelect {

    private static final String USER = "root";
    private static final String PASSWORD = "123456";

    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/bank";

    public static void selectById(Long id) throws ClassNotFoundException, SQLException {
        Class.forName(JDBC_DRIVER);
        Connection conn =  DriverManager.getConnection(DB_URL, USER, PASSWORD);
        CallableStatement cstmt = null;
        try {
            cstmt = conn.prepareCall("{call select_by_id(?)}");
            cstmt.setLong(1, id);
            ResultSet resultSet = cstmt.executeQuery();
            while (resultSet.next()) {
                System.out.printf("id: %s, name: %s, balance: %s\n",
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getLong("balance"));
            }
            resultSet.close();
        } finally {
            if (cstmt != null) {
                cstmt.close();
            }
            conn.close();
        }
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        selectById(1L);
    }

}
