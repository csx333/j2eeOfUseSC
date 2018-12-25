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

public class RegisterAction {
    private static Logger logger = LogManager.getLogger(RegisterAction.class.getName());
    public String handleRegister(HttpServletRequest request, HttpServletResponse response, ActionBean actionBean)
            throws ServletException, IOException {
        logger.info("RegisterRequest's PathInfo>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+request.getPathInfo());
        AccountBean accountBean = new AccountBean();
        //创建session变量，在session期间传递消息
        HttpSession session = request.getSession();
        //判断输入框是否为空
        if(request.getParameter("account")!="" && request.getParameter("password")!= ""){
            //交由accountBean处理注册请求
            if (accountBean.signIn(request)) {
                session.setAttribute("registerMessage", "用户已存在！");
                logger.info("RegisterRequest>>>>>>>>>>>>>>>>>>>>>>>>>>>"+session.getAttribute("registerMessage"));
                return "failure";
            } else {
                accountBean.registerIn();
                session.setAttribute("account", accountBean.getUserName());
                logger.info("RegisterRequest>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+session.getAttribute("account"));
                return "success";
            }
        }else {
            session.setAttribute("registerMessage", "用户名或者密码为空");
            logger.info("RegisterRequest>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+session.getAttribute("registerMessage"));
            return "failure";
        }
    }
}
