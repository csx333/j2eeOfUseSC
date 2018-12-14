package water.ustc.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 * AccountDaoTool class
 *
 * @author csx
 * @date 2016/12/12
 */
public class AccountDaoTool {
    /**
     * JDBC 驱动名
     */
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    /**
     * DB_URL 数据库 URL
     */
    static final String DB_URL = "jdbc:mysql://localhost:3306/com?useUnicode=true&"
            + "characterEncoding=utf8&useSSL=false&serverTimezone=UTC"
            + "&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false";

    static final String USER = "root";
    static final String PASSWORD = "123456";
    private static Logger logger = LogManager.getLogger(AccountDaoTool.class.getName());

    public List<List<String>> queryAccount(String account, String password) {
        String sql = "select * from com.useraccount where name = ? and password = ?";
        ResultSet rs= null;
        PreparedStatement ps=null;
        Connection conn =null;
        List<List<String>> listList = new ArrayList();
        List<String> list = new ArrayList();
        try {
            logger.info("查询数据库中>>>>>>>>>>>>>>>>>>>>");
            conn= connectMySQL();
            // 创建prepareStatement
            ps = conn.prepareStatement(sql);
            ps.setString(1, account);
            ps.setString(2, password);
            logger.info("查询数据库中>>>>>>>>>>>>>>>>>>>>"+account+"   " + password);
            // 执行操作
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(rs.getString("name"));
                list.add(rs.getString("password"));
                listList.add(list);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return listList;
    }

    public void insertAccount(String account, String password) {

        String sql = "insert into com.useraccount(name,password) values(?,?)";

        Connection conn = null;
        PreparedStatement ps = null;
        try {
            logger.info("插入数据库中>>>>>>>>>>>>>>>>>>>>"+account+password);
            conn= connectMySQL();
            // 创建prepareStatement
            ps = conn.prepareStatement(sql);
            ps.setString(1, account);
            ps.setString(2, password);
            // 执行操作
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private Connection connectMySQL() throws ClassNotFoundException,SQLException{
        Connection conn = null;
        Class.forName(JDBC_DRIVER);
        logger.info("驱动加载成功");
        conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
        logger.info("连接成功");
        return conn;
    }

}
