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

public class RegisterAction {
    private static Logger logger = LogManager.getLogger(RegisterAction.class.getName());
    public String handleRegister(HttpServletRequest request, HttpServletResponse response, ActionBean actionBean)
            throws ServletException, IOException {
        logger.info("RegisterRequest>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+request.getPathInfo());
        String account = request.getParameter("account");
        String password = request.getParameter("password");
        AccountDaoTool registerDao = new AccountDaoTool();
        HttpSession session = request.getSession();
        logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+account+">>>>>>>>"+password);
        if(account!="" && password != ""){
            List<List<String>> list = registerDao.queryAccount(account,password);
            if (list != null && !list.isEmpty()) {
                session.setAttribute("registerMessage", "用户已存在！");
                logger.info("RegisterRequest>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+session.getAttribute("registerMessage"));
                return "failure";
            } else {
                registerDao.insertAccount(account, password);
                session.setAttribute("account", account);
                logger.info("RegisterRequest>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+session.getAttribute("account"));
                return "success";
            }
        }else {
            session.setAttribute("registerMessage", "用户名或者账户为空");
            logger.info("RegisterRequest>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+session.getAttribute("registerMessage"));
            return "failure";
        }
    }
}
