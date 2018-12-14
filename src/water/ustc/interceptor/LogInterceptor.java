package water.ustc.interceptor;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sc.ustc.bean.ActionBean;
import water.ustc.tools.ActionLog;
import water.ustc.tools.AddLogInfoToXml;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LogInterceptor {
    private static ThreadLocal<ActionLog> tl = new ThreadLocal<>();
    private static Logger logger = LogManager.getLogger(LogInterceptor.class.getName());

    public void preAction(HttpServletRequest request, HttpServletResponse response,
                          ActionBean actionBean){
        logger.info("来到preAction方法体内了>>>>>>>>>>>>>>>>>>>");
        ActionLog actionLog = new ActionLog();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date d = new Date();
        actionLog.setStartTime(sdf.format(d));
        actionLog.setName(actionBean.getActionName());
        tl.set(actionLog);
    }

    public void afterAction(HttpServletRequest request, HttpServletResponse response,
                            String result){
        logger.info("来到afterAction方法体内了>>>>>>>>>>>>>>>>>>>");
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ActionLog actionLog = tl.get();
        actionLog.setEndTime(sdf.format(d));
        actionLog.setResult(result);
        File file = new File("D://log.xml");
        try {
            AddLogInfoToXml.parseXml(actionLog.getName(), actionLog.getStartTime(), actionLog.getEndTime(), actionLog.getResult(), file);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
