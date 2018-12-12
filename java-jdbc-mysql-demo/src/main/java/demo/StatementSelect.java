package demo;

import java.sql.*;

/**
 * 使用 Statement 查询数据
 */
public class StatementSelect {

    private static final String USER = "root";
    private static final String PASSWORD = "123456";

    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/bank";

    public static void select(String name) throws ClassNotFoundException, SQLException {
        Class.forName(JDBC_DRIVER);
        Connection conn =  DriverManager.getConnection(DB_URL, USER, PASSWORD);
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            String sql = String.format("SELECT * FROM user_balance WHERE name='%s'", name);
            ResultSet resultSet = stmt.executeQuery(sql);
            while (resultSet.next()) {
                System.out.printf("id: %s, name: %s, balance: %s\n",
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getLong("balance"));
            }
            resultSet.close();
        } finally {
            if (stmt != null) {
                stmt.close();
            }
            conn.close();
        }
    }


    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        select("letian");
        select("xiaosi");
    }
}
