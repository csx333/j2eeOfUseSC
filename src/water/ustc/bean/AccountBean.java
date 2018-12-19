package water.ustc.bean;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import water.ustc.dao.UserDAO;

import javax.servlet.http.HttpServletRequest;

/**
 * @author : csx
 * @description :
 * @date : 2018/12/16 10:43
 */
public class AccountBean {
    private String userName;
    private String userPassword;
    private String userId;
    private static Logger logger = LogManager.getLogger(AccountBean.class.getName());
    UserDAO userDAO = new UserDAO();

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    private String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public boolean signIn(HttpServletRequest request) {
        setUserName(request.getParameter("account"));
        setUserPassword(request.getParameter("password"));
        String sql =  "select * from com.useraccount where name =\""+userName+"\" and password =\""+ userPassword+"\"";
        logger.info("登陆ing>>>>>>>>>>>>>>>>>>>>>>>>>>"+sql);
        try {
            userDAO.initDataBase();
        }catch (Exception e){
            e.printStackTrace();
        }
        AccountBean accountBean = (AccountBean)userDAO.query(sql);
        if(accountBean!=null) {
            return true;
        }else {
            return false;
        }
    }
    public boolean registerIn(){
        String sql = "insert into com.useraccount(name,password) values(\""+userName+"\",\""+ userPassword+"\")";
        logger.info("注册ing>>>>>>>>>>>>>>>>>>>>>>>>>>"+sql);
        try {
            userDAO.initDataBase();
        }catch (Exception e){
            e.printStackTrace();
        }
        if(userDAO.insert(sql)){
            return true;
        }else{
            return  false;
        }
    }
}