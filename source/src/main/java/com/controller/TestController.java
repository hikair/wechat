package com.controller;

import com.aes.AesException;
import com.aes.WXBizMsgCrypt;
import com.utils.WeiXinApiUtils;
import com.utils.XMLUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.Arrays;

/**
 * @author zpq
 */
@RestController
public class TestController {

    @RequestMapping(value = "callback")
    public void connect(HttpServletRequest request, HttpServletResponse response){

        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(request.getInputStream());
            // 企业微信传过来的是加密后的xml
            String xmlString = XMLUtils.getXmlString(doc);
            // 微信加密签名
            String msg_signature = request.getParameter("msg_signature");
            // 时间戳
            String timestamp = request.getParameter("timestamp");
            // 随机数
            String nonce = request.getParameter("nonce");
            // 三个参数从企业微信后台应用管理获取
            String contacts_token = "JOe3CIZ2wbiHU27GGIqqslPk2nFyE";
            String contacts_encodingaeskey = "TxlLVXNbDGOWXt65nbxD6bU3sJBG3E4trPcbukBWAgX";
            String corpId = "xxxxxxxxxxxxxxxxx";
            WXBizMsgCrypt wxcpt = new WXBizMsgCrypt(contacts_token,contacts_encodingaeskey,corpId);

            // 获取到明文xml
            String realXml = wxcpt.DecryptMsg(msg_signature, timestamp, nonce, xmlString);

            String fromUserName = XMLUtils.getValueByTagName(realXml, "FromUserName");
            String eventKey = XMLUtils.getValueByTagName(realXml, "EventKey");
            if(StringUtils.isEmpty(eventKey) || StringUtils.isEmpty(fromUserName)){
                return;
            }

            // 回复消息
            String replyMessage = "";
            // 给领导发消息
            if("urge".equals(eventKey)){
                replyMessage = "催办领导成功";
                //
                // WeiXinApiUtils.sendMarkDownMsg();
            }
            // 给IT部发消息
            if("feedback".equals(eventKey)){
                replyMessage = "已联系IT报修";
            }
            // WeiXinApiUtils.sendTextMsg(Arrays.asList(fromUserName),replyMessage);

        } catch (ParserConfigurationException | SAXException | IOException | AesException e) {
            e.printStackTrace();
        }

    }
}
