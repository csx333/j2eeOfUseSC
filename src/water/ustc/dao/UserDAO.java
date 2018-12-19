package water.ustc.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sc.ustc.dao.BaseDAO;
import water.ustc.bean.AccountBean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author : csx
 * @description :
 * @date : 2018/12/16 21:15
 */
public class UserDAO extends BaseDAO{

    public Connection conn;
    ResultSet rs= null;
    PreparedStatement ps=null;
    private static Logger logger = LogManager.getLogger(UserDAO.class.getName());
    private final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";

    private final String DB_URL = "jdbc:mysql://localhost:3306/com?useUnicode=true&"
            + "characterEncoding=utf8&useSSL=false&serverTimezone=UTC"
            + "&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false";
    private final String USER = "root";
    private final String PASSWORD = "123456";
    AccountBean accountBean = null;

    public void initDataBase()throws ClassNotFoundException, SQLException{
        setDriver(JDBC_DRIVER);
        setUrl(DB_URL);
        setUserName(USER);
        setUserPassword(PASSWORD);
        conn= openDBConnection();
    }
    @Override
    public  Object query(String sql){
        try {
            logger.info("查询数据库中>>>>>>>>>>>>>>>>>>>>");
            // 创建prepareStatement
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                accountBean = new AccountBean();
                accountBean.setUserName(rs.getString("name"));
                accountBean.setUserPassword(rs.getString("password"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            closeDBConnection(rs,ps,conn);
        }
        return accountBean;
    }
    @Override
    public  boolean insert(String sql) {
        try {
            // 创建prepareStatement
            ps = conn.prepareStatement(sql);
            // 执行操作
            ps.executeUpdate();
            return true;
    }catch ( Exception e){
            e.printStackTrace();
            return false;
        }finally {
            closeDBConnection(rs,ps,conn);
        }
    }
    @Override
    public  boolean update(String sql){
        return false;
    }
    @Override
    public  boolean delete(String sql){
        return false;
    }
}
