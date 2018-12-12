package water.ustc.action;

import water.ustc.dao.AccountDaoTool;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class RegisterAction {

    public String handleRegister(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("RegisterRequest"+request.getPathInfo());
        String account = request.getParameter("account");
        String password = request.getParameter("password");
        AccountDaoTool registerDao = new AccountDaoTool();
        HttpSession session = request.getSession();
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+account+">>>>>>>>"+password);
        if(account!="" && password != ""){
            List<List<String>> list = registerDao.queryAccount(account,password);
            if (list != null && !list.isEmpty()) {
                session.setAttribute("registerMessage", "用户已存在！");
                System.out.println("RegisterRequest>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+session.getAttribute("registerMessage"));
                return "failure";
            } else {
                registerDao.insertAccount(account, password);
                session.setAttribute("account", account);
                System.out.println("RegisterRequest>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+session.getAttribute("account"));
                return "success";
            }
        }else {
            session.setAttribute("registerMessage", "用户名或者账户为空");
            System.out.println("RegisterRequest>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+session.getAttribute("registerMessage"));
            return "failure";
        }
    }
}
