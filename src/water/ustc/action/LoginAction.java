package water.ustc.action;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sc.ustc.bean.ActionBean;
import water.ustc.bean.AccountBean;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginAction {
    private static Logger logger = LogManager.getLogger(LoginAction.class.getName());

    public String handleLogin(HttpServletRequest request, HttpServletResponse response, ActionBean actionBean)
            throws ServletException, IOException {
        logger.info("LoginRequest>>>>>>>>>>>>>>>>>>>>>>>>>>"+request.getPathInfo());

        AccountBean accountBean = new AccountBean();
        HttpSession session = request.getSession();

        if(accountBean.signIn(request)){
            session.setAttribute("account" , accountBean.getUserName());
            logger.info("LoginSession>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+session.getAttribute("account"));
            return "success";
        }else{
            session.setAttribute("loginMessage","您的账户或者密码错了！！");
            logger.info("LoginSession>>>>>>>>>>>>>>>>>>>>>>>>>>>"+session.getAttribute("loginMessage"));
            return "failure";
        }
    }
}
