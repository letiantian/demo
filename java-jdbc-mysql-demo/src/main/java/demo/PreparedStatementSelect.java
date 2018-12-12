package demo;

import java.sql.*;

public class PreparedStatementSelect {


    private static final String USER = "root";
    private static final String PASSWORD = "123456";

    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/bank";

    public static void select(String name) throws ClassNotFoundException, SQLException {
        Class.forName(JDBC_DRIVER);
        Connection conn =  DriverManager.getConnection(DB_URL, USER, PASSWORD);
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement("SELECT * FROM user_balance WHERE name=?");
            pstmt.setString(1, name);
            ResultSet resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                System.out.printf("id: %s, name: %s, balance: %s\n",
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getLong("balance"));
            }
            resultSet.close();
        } finally {
            if (pstmt != null) {
                pstmt.close();
            }
            conn.close();
        }
    }


    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        select("letian");
        select("xiaosi");
        /**
         * 输出：
         *
         * id: 1, name: letian, balance: 1000
         * id: 2, name: xiaosi, balance: 1001
         */
    }

}
