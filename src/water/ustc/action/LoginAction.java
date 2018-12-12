package water.ustc.action;

import water.ustc.dao.AccountDaoTool;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class LoginAction {
    public String handleLogin(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("LoginRequest"+request.getPathInfo());
        String account = request.getParameter("account");
        String password = request.getParameter("password");
        AccountDaoTool loginDao = new AccountDaoTool();
        HttpSession session = request.getSession();
        List<List<String>> list = loginDao.queryAccount(account,password);
        if(list != null && !list.isEmpty()){
            session.setAttribute("account" , account);
            System.out.println("LoginSession>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+session.getAttribute("account"));
            return "success";
        }else{
            session.setAttribute("loginMessage","您的账户或者密码错了！！");
            System.out.println("LoginSession>>>>>>>>>>>>>>>>>>>>>>>>>>>"+session.getAttribute("loginMessage"));
            return "failure";
        }
    }

}
