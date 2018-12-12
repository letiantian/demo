package demo;

import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TransactionExample {

    private static final String USER = "root";
    private static final String PASSWORD = "123456";

    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/bank";

    public Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName(JDBC_DRIVER);
        return  DriverManager.getConnection(DB_URL, USER, PASSWORD);
    }

    public void insert(Connection connection, String name, Long balance) throws SQLException {

        PreparedStatement pstmt = null;
        try {
            pstmt = connection.prepareStatement("INSERT INTO user_balance(name, balance) VALUES(?, ?)");
            pstmt.setString(1, name);
            pstmt.setLong(2, balance);
            int affectRowsNum = pstmt.executeUpdate();
            System.out.println("影响的行数：" + affectRowsNum);
        } finally {
            if (pstmt != null) {
                pstmt.close();
            }
        }
    }

    public void raiseException(boolean raise) {
        if (raise) {
            throw new RuntimeException("异常");
        }
    }

    @Test
    public void test01() throws SQLException, ClassNotFoundException {
        Connection connection = getConnection();
        connection.setAutoCommit(false);
        insert(connection, "letian", 10000L);
    }

    @Test
    public void test02() throws SQLException, ClassNotFoundException {
        Connection connection = getConnection();
        connection.setAutoCommit(false);
        try {
            insert(connection, "letian", 10000L);
            insert(connection, "xiaosi", 10000L);
            raiseException(true); // 产生一个异常
            connection.commit();
        } catch (Exception ex) {
            System.out.println("rollback");
            connection.rollback();
        }
    }

    @Test
    public void test03() throws SQLException, ClassNotFoundException {
        Connection connection = getConnection();
        connection.setAutoCommit(false);
        try {
            insert(connection, "letian", 10000L);
            insert(connection, "xiaosi", 10000L);
            raiseException(false); // 不产生异常
            connection.commit();
        } catch (Exception ex) {
            System.out.println("rollback");
            connection.rollback();
        }
    }

}
