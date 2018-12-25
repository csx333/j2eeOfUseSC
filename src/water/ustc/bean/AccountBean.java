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
    private String userName = null;
    private String userPassword= null;
    private String userId =null;
    private AccountMoney accountMoney;
    private static Logger logger = LogManager.getLogger(AccountBean.class.getName());
    UserDAO userDAO = new UserDAO();

    public AccountBean( String userName){
        this.userName = userName;
    }

    public AccountBean(){

    }
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
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

    public AccountMoney getAccountMoney() {
        return accountMoney;
    }

    public void setAccountMoney(AccountMoney accountMoney) {
        this.accountMoney = accountMoney;
    }

    @Override
    public String toString() {
        return "AccountBean{" +
                "userName='" + userName + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", userId='" + userId + '\'' +
                ", accountMoney=" + accountMoney +
                '}'+this.hashCode();
    }

    /**
     * @Description :处理账户的登陆逻辑，验证账户信息
     * @param ： request
     * @Return : boolean
     * @Author : csx
     * @Date : 2018/12/23 21:10
     */
    public boolean signIn(HttpServletRequest request) {
        setUserName(request.getParameter("account"));
        setUserPassword(request.getParameter("password"));
        String sql =  "select * from useraccount where name =\""+userName+"\" " +
                "and password =\""+ userPassword+"\"";
        logger.info("登陆ing>>>>>>>>>>>>>>>>>>>>>>>>>>"+sql);
        try {
            //初始化数据库
            userDAO.initDataBase();
        }catch (Exception e){
            e.printStackTrace();
        }
        AccountBean accountBean = (AccountBean)userDAO.query(sql);
        //返回accountBean不为null且密码正确返回true
        if(accountBean!=null && accountBean.getUserPassword().equals(userPassword)) {
            return true;
        }else {
            return false;
        }
    }
    /**
     * @Description :处理账户的注册逻辑，验证账户信息，若为新用户将存储至数据库
     * @param ：
     * @Return : boolean
     * @Author : csx
     * @Date : 2018/12/23 21:10
     */
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