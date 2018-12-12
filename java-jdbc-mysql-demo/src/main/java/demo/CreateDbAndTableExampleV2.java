package demo;

import org.junit.Test;

import java.sql.*;

/**
 * 创建数据库和表格，并查询表格信息
 */
public class CreateDbAndTableExampleV2 {

    private static final String USER = "root";
    private static final String PASSWORD = "123456";

    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://127.0.0.1:3306"; // 注意，这里未指定数据库


    /**
     * 获取数据库连接
     */
    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName(JDBC_DRIVER);  // 注册 MySQL JDBC 驱动
        return DriverManager.getConnection(DB_URL, USER, PASSWORD);
    }

    @Test
    public void test01() throws SQLException, ClassNotFoundException {
        Connection conn = getConnection();
        Statement stmt = null;
        boolean result;
        try {
            stmt = conn.createStatement();
            // 已有则删除
            result = stmt.execute("DROP DATABASE IF EXISTS `bank`");
            System.out.println("drop database: " + result);
            // 创建库
            result = stmt.execute("CREATE DATABASE `bank`");
            System.out.println("create database: " + result);
            // 切换到 bank 库
            conn.setCatalog("bank");
            // 需要需要重新获取stmt
            stmt.close();
            stmt = conn.createStatement();
            // 创建表
            result = stmt.execute("CREATE TABLE `user_balance` (\n" +
                    "  `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT ,\n" +
                    "  `name` VARCHAR(45) NOT NULL ,\n" +
                    "  `balance` BIGINT NOT NULL ,\n" +
                    "  PRIMARY KEY (`id`)\n" +
                    ")\n" +
                    "ENGINE = InnoDB\n" +
                    "DEFAULT CHARACTER SET = utf8mb4\n" +
                    "COLLATE = utf8mb4_general_ci;");
            System.out.println("create table: " + result);
            // 删除表
            result = stmt.execute("DROP TABLE `user_balance`");
            System.out.println("drop table: " + result);
        } finally {
            if (stmt != null) {
                stmt.close();
            }
            conn.close();
        }
    }

    @Test
    public void test02() throws SQLException, ClassNotFoundException {
        Connection conn = getConnection();
        Statement stmt = null;
        int affected;
        try {
            stmt = conn.createStatement();
            // 已有则删除
            affected = stmt.executeUpdate("DROP DATABASE IF EXISTS `bank`");
            System.out.println("drop db: " + affected);
            // 创建库
            affected = stmt.executeUpdate("CREATE DATABASE `bank`");
            System.out.println("create db: " + affected);
            // 切换到 bank 库
            conn.setCatalog("bank");
            // 需要需要重新获取stmt
            stmt.close();
            stmt = conn.createStatement();
            // 创建表
            affected = stmt.executeUpdate("CREATE TABLE `user_balance` (\n" +
                    "  `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT ,\n" +
                    "  `name` VARCHAR(45) NOT NULL ,\n" +
                    "  `balance` BIGINT NOT NULL ,\n" +
                    "  PRIMARY KEY (`id`)\n" +
                    ")\n" +
                    "ENGINE = InnoDB\n" +
                    "DEFAULT CHARACTER SET = utf8mb4\n" +
                    "COLLATE = utf8mb4_general_ci;");
            System.out.println("create table: " + affected);
            // 删除表
            affected = stmt.executeUpdate("DROP TABLE `user_balance`");
            System.out.println("drop table: " + affected);
        } finally {
            if (stmt != null) {
                stmt.close();
            }
            conn.close();
        }
    }

}
