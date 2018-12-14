package water.ustc.tools;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class AddLogInfoToXml {

    private static Logger logger = LogManager.getLogger(AddLogInfoToXml.class.getName());

    public static void parseXml(String name,String sTime,String eTime,String result,File file) throws DocumentException, IOException {
        Document document = null;
        Element root = null;
        if(file.exists()){
            logger.info("log.xml存在，路径是"+file);
            SAXReader reader=new SAXReader();
            document = reader.read(file);
            //得到根节点
            root = document.getRootElement();
        }else{
            file.createNewFile();
            logger.info("log.xml不存在，创造路径>>>>>>>>>>>>>>>>>>"+file);
            document = DocumentHelper.createDocument();
            root=document.addElement("log");
        }
        Element actionEle = root.addElement("action");
        Element nameEle = actionEle.addElement("name");
        Element sTimeEle = actionEle.addElement("s-time");
        Element eTimeEle = actionEle.addElement("e-time");
        Element resultEle = actionEle.addElement("result");
        nameEle.setText(name);
        sTimeEle.setText(sTime);
        eTimeEle.setText(eTime);
        resultEle.setText(result);
        // 创建文件输出的时候，自动缩进的格式
        OutputFormat format = OutputFormat.createPrettyPrint();
        //设置编码
        format.setEncoding("UTF-8");
        XMLWriter writer = new XMLWriter(new FileWriter(file),format);
        writer.write(document);
        writer.close();
        logger.info("log.xml写入完成>>>>>>>>>>>>>>>>>>");
    }
}
