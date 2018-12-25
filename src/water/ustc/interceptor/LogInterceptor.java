package water.ustc.interceptor;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sc.ustc.bean.ActionBean;
import water.ustc.bean.ActionLogBean;
import water.ustc.tools.AddLogInfoToXml;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LogInterceptor {

    /**
     * tl 存储ActionLogBean的线程共享变量
     */
    private static ThreadLocal<ActionLogBean> tl = new ThreadLocal<>();
    private static Logger logger = LogManager.getLogger(LogInterceptor.class.getName());
    /**
     * @Description : 记录每次客户端请求的 action 名称<name>、访问开始时间<s-time>，
     * @param ： request web的request
     * @param ： response web的response
     * @param ： actionBean action节点bean类
     * @Return : void
     * @Author : csx
     * @Date : 2018/12/23 15:55
     */
    public void preAction(HttpServletRequest request, HttpServletResponse response,
                          ActionBean actionBean){
        logger.info("来到preAction方法体内了>>>>>>>>>>>>>>>>>>>");
        ActionLogBean actionLog = new ActionLogBean();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date d = new Date();
        actionLog.setStartTime(sdf.format(d));
        actionLog.setActionName(actionBean.getActionName());
        //将ActionLogBean的存入线程共享变量
        tl.set(actionLog);
    }
    /**
     * @Description : 记录每次客户端请求的 action 访问结束时间<e-time>、请求返回结果<result>，
     * 并将信息追加至日志文件 log.xml，保存在 PC 磁盘上。
     * @param ： request
     * @param ： response
     * @param ： result action返回的result结果
     * @Return : void
     * @Author : csx
     * @Date : 2018/12/23 15:58
     */
    public void afterAction(HttpServletRequest request, HttpServletResponse response,
                            String result){
        logger.info("来到afterAction方法体内了>>>>>>>>>>>>>>>>>>>");
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ActionLogBean actionLog = tl.get();
        actionLog.setEndTime(sdf.format(d));
        actionLog.setResult(result);
        File file = new File("D://log.xml");
        try {
            //追加至日志文件 log.xml
            AddLogInfoToXml.parseXml(actionLog.getActionName(), actionLog.getStartTime(),
                    actionLog.getEndTime(), actionLog.getResult(), file);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
