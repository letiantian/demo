package demo;

import java.sql.*;

/**
 * 创建数据库和表格，并查询表格信息
 */
public class CreateDbAndTableExample {

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

    /**
     * 主函数
     * @param args
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Connection conn = getConnection();
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            // 已有则删除
            stmt.execute("DROP DATABASE IF EXISTS `bank`");
            // 创建库
            stmt.execute("CREATE DATABASE `bank`");
            // 切换到 bank 库
            conn.setCatalog("bank");
            // 获取当前数据库名称
            System.out.println("当前数据库：" + conn.getCatalog()); // 若未选择数据库，则 getCatalog 返回空
            conn.getCatalog();
            // 需要需要重新获取stmt
            stmt.close();
            stmt = conn.createStatement();
            // 创建表
            stmt.execute("CREATE TABLE `user_balance` (\n" +
                    "  `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT ,\n" +
                    "  `name` VARCHAR(45) NOT NULL ,\n" +
                    "  `balance` BIGINT NOT NULL ,\n" +
                    "  PRIMARY KEY (`id`)\n" +
                    ")\n" +
                    "ENGINE = InnoDB\n" +
                    "DEFAULT CHARACTER SET = utf8mb4\n" +
                    "COLLATE = utf8mb4_general_ci;");

            // 查看表
            ResultSet tablesResultSet = stmt.executeQuery("SHOW TABLES");
            // 获取查询结果的元信息，例如获取列的数量，列名
            int columnCount = tablesResultSet.getMetaData().getColumnCount();
            System.out.println("查询结果中列的数量: " + columnCount);
            for (int i=1; i<=columnCount; ++i) {  // 注意，index是从1开始
                String columnLabel = tablesResultSet.getMetaData().getColumnLabel(i);
                String columnClassName = tablesResultSet.getMetaData().getColumnClassName(i);
                System.out.println(String.format("第%s列，列名是 %s，类型是 %s", i, columnLabel, columnClassName));
            }
            // 从查询结果中获取所有表名。从上面的结果中能看出只有1列，列名是Tables_in_bank，类型是String
            while (tablesResultSet.next()) {
                System.out.println("第1种方式获取表名：" + tablesResultSet.getString("Tables_in_bank"));
                System.out.println("第2种方式获取表名：" + tablesResultSet.getString(1));
            }

            // 查询表的创建语句
            ResultSet createSqlResultSet = stmt.executeQuery("SHOW CREATE TABLE `user_balance`");
            while (createSqlResultSet.next()) {
                System.out.println("创建语句：" + createSqlResultSet.getString(2)); // 创建语句在查询结果的第2列
            }

            tablesResultSet.close();
            createSqlResultSet.close();
        } finally {
            if (stmt != null) {
                stmt.close();
            }
            conn.close();
        }
    }

}
