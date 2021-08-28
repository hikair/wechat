package com.utils;

import org.apache.commons.lang.StringUtils;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Administrator
 */
public class XMLUtils {

    public static void main(String[] args) {
        String xml = "<xml><ToUserName><![CDATA[ww9c8cda7ddd32f0fb]]></ToUserName><FromUserName><![CDATA[18768100679]]></FromUserName><MsgType><![CDATA[event]]></MsgType><Event><![CDATA[taskcard_click]]></Event><CreateTime>1611804919</CreateTime><EventKey><![CDATA[key111]]></EventKey><TaskId><![CDATA[5ee2a69f715145c6a29afe97d363e48b]]></TaskId><AgentId>1000012</AgentId></xml>";
        //String taskId = getValueByTagName(xml, "TaskId");
        String createTime = getValueByTagName(xml, "CreateTime");
        Date date = new Date(Long.valueOf(createTime));
        String format = new SimpleDateFormat("yyyy-MM-dd hh:mm").format(date);
        System.out.println(date);
        System.out.println(format);
    }

    /**
     * 获取xml某个节点的内容
     * @param doc
     * @param tagName
     * @return
     */
    public static String getValueByTagName(Document doc, String tagName){
        if(doc == null || StringUtils.isEmpty(tagName)){
            return "";
        }
        NodeList pl = doc.getElementsByTagName(tagName);
        if(pl != null && pl.getLength() > 0){
            return pl.item(0).getTextContent();
        }
        return "";
    }

    /**
     * 获取xml某个节点的内容
     * @param xml
     * @param tagName
     * @return
     */
    public static String getValueByTagName(String xml, String tagName){
        if(StringUtils.isEmpty(xml)){
            return  "";
        }
        DocumentBuilderFactory dbf= DocumentBuilderFactory.newInstance();
        InputStream is = null;
        try {
            DocumentBuilder db = dbf.newDocumentBuilder();
            is = new ByteArrayInputStream(xml.getBytes());
            Document doc = db.parse(is);
            return getValueByTagName(doc,tagName);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        } finally {
            if(is != null){
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return "";
    }

    /**
     * XML转字符串   原样取出
     * @param doc
     * @return
     */
    public static String getXmlString(Document doc){
        TransformerFactory tf = TransformerFactory.newInstance();
        try {
            Transformer t = tf.newTransformer();
            t.setOutputProperty(OutputKeys.ENCODING,"UTF-8");
            t.setOutputProperty(OutputKeys.METHOD, "html");
            t.setOutputProperty(OutputKeys.VERSION, "4.0");
            t.setOutputProperty(OutputKeys.INDENT, "no");
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            t.transform(new DOMSource(doc), new StreamResult(bos));
            return bos.toString();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
        return "";
    }
}
