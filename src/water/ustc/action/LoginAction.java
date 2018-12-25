package water.ustc.action;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sc.ustc.bean.ActionBean;
import sc.ustc.dao.Configuration;
import sc.ustc.dao.Conversation;
import water.ustc.bean.AccountBean;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class LoginAction {

    private static Logger logger = LogManager.getLogger(LoginAction.class.getName());
    private AccountBean accountBean;

    public String handleLogin(HttpServletRequest request, HttpServletResponse response, ActionBean actionBean)
            throws ServletException, IOException {
        logger.info("LoginRequest's PathInfo>>>>>>>>>>>>>>>>>>>>>>>>>>"+request.getPathInfo());
        //创建session变量，在session期间传递消息
        HttpSession session = request.getSession();
        List<Object> accountBeans ;
        try {
            Configuration configuration = new Configuration(
                    new File(request.getSession().getServletContext().getRealPath("WEB-INF/classes/or_maping.xml")));
            Conversation conversation = configuration.createConversation();
            //建立数据库联系
            conversation.openConversation();
            //传入对象取得数据库查询结果
            accountBeans = conversation.get(new AccountBean(request.getParameter("account")));
            accountBean = (AccountBean) accountBeans.get(0);
            logger.info("执行完conversation返回了查询对象>>>>>>>>>>>>>>>>>>>>>>>>>>"+accountBean);
            logger.info("accountBeans的数量>>>>>>>>>>>>>>>>>>>>>>>>>>"+accountBeans.size());
        } catch (Exception e) {
            e.printStackTrace();
            return "failure";
        }
        String m = accountBean.getAccountMoney().getMoney();
        logger.info("accountBean>>>>>>>>>>>>>>>>>>>>>>>>>>"+accountBean);

        //由accountBean处理登陆请求
        //accountBean.signIn(request)
        if(accountBean!= null){
            //给视图返回用户名
            session.setAttribute("account" , accountBean.getUserName());
            logger.info("成功返回LoginSession的内容>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+session.getAttribute("account"));
            return "success";
        }else{
            //给视图返回错误信息
            session.setAttribute("loginMessage","您的账户或者密码错了！！");
            logger.info("LoginSession>>>>>>>>>>>>>>>>>>>>>>>>>>>"+session.getAttribute("loginMessage"));
            return "failure";
        }
    }
}
