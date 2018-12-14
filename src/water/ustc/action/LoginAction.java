package water.ustc.action;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sc.ustc.bean.ActionBean;
import water.ustc.dao.AccountDaoTool;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class LoginAction {
    private static Logger logger = LogManager.getLogger(LoginAction.class.getName());

    public String handleLogin(HttpServletRequest request, HttpServletResponse response, ActionBean actionBean)
            throws ServletException, IOException {
        logger.info("LoginRequest>>>>>>>>>>>>>>>>>>>>>>>>>>"+request.getPathInfo());
        String account = request.getParameter("account");
        String password = request.getParameter("password");
        logger.info("LoginRequest其他信息>>>>>>>>>>>>>>>>>>>>>>>>>>"+account+password);
        AccountDaoTool loginDao = new AccountDaoTool();
        HttpSession session = request.getSession();
        List<List<String>> list = loginDao.queryAccount(account,password);
        if(list != null && !list.isEmpty()){
            session.setAttribute("account" , account);
            logger.info("LoginSession>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+session.getAttribute("account"));
            return "success";
        }else{
            session.setAttribute("loginMessage","您的账户或者密码错了！！");
            logger.info("LoginSession>>>>>>>>>>>>>>>>>>>>>>>>>>>"+session.getAttribute("loginMessage"));
            return "failure";
        }
    }

}
