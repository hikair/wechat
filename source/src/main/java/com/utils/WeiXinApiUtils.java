package com.utils;

import com.alibaba.fastjson.JSONObject;
import com.entity.*;
import com.factory.WeiXinApiFactory;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.*;

public class WeiXinApiUtils {

    private static final String CONTENT_TYPE = "Content-Type";
    private static CloseableHttpClient httpClient;
    // 用于获得登录后的页面
    private static HttpGet httpGet;
    private static HttpPost httpPost;
    // token
    private static String accessToken;
    // 企业id
    private static String corpId;
    // 应用密钥
    private static String contactSecret;
    // 应用id
    public static Integer agentId;
    // accessToken错误码
    private static String ERRCODE = "40014";
    // accessToken过期码
    private static String ERRCODE2 = "42001";
    // 获取token的url
    private static String ACCESSTOKENURL = "https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid=ID&corpsecret=SECRET";
    // 上传临时文件的url
    private static String UPLOADTEMPMATERIAURL = "https://qyapi.weixin.qq.com/cgi-bin/media/upload?access_token=ACCESS_TOKEN&type=TYPE";
    // 发送文本消息的url
    private static String SENDMSGURL = "https://qyapi.weixin.qq.com/cgi-bin/message/send?access_token=ACCESS_TOKEN";
    // 获取模板详情的url
    private static String GETTEMPLATEDETAILURL = "https://qyapi.weixin.qq.com/cgi-bin/oa/gettemplatedetail?access_token=ACCESS_TOKEN";
    // 提交审批的url
    private static String APPLYEVENTURL = "https://qyapi.weixin.qq.com/cgi-bin/oa/applyevent?access_token=ACCESS_TOKEN";
    // 获取汇报单id的url
    private static String GETRECORDLISTURL = "https://qyapi.weixin.qq.com//cgi-bin/oa/journal/get_record_list?access_token=ACCESS_TOKEN";
    // 获取汇报单详情的url
    private static String GETRECORDDETAILURL = "https://qyapi.weixin.qq.com/cgi-bin/oa/journal/get_record_detail?access_token=ACCESS_TOKEN";
    // 中信汇报模板id
    private static String REPORTTEMPLATEID = "3TmkwGvfmq2oPffou8d7MespP4dF4U5Rgtf1XUyA";


    static {
        corpId = "xxxxxxxxxxxxxxxxx";
        agentId = 1000012;
        contactSecret = "CShwFwhMItoZBZ_3I5Ly6d-aN_16-ASzxH7ixABOvrE";
    }

    public static void main(String[] args) {
        // 发送文本消息
        Map map = new HashMap(2);
        map.put("content","hello");
        // sendMsg("","","2","text",map);
        sendMsg("18768100679","","","markdown",map);
    }

    /**
     * 发送各种消息
     * @param userId 发送到指定用户,多个用户用|隔开
     * @param partId 发送到指定部门,多个部门用|隔开
     * @param tagId 发送到指定标签,多个标签用|隔开
     * @param msgType 消息类型 类型有：text、image、voice、video、file、news、mpnews、markdown、textcard、taskcard。 miniprogram_notice（小程序消息暂不支持）
     * @param obj 消息内容 就是一个特定格式的map，详见WeiXinAbstract子类
     * @throws IOException
     */
    public static void sendMsg(String userId, String partId, String tagId, String msgType, Object obj) {
        // post请求体
        String postData = WeiXinApiFactory.createWeiXinData(userId, partId, tagId, msgType, agentId, obj);
        // 请求地址
        String url = SENDMSGURL.replace("ACCESS_TOKEN",getAccessToken());
        try {
            String response = post("utf-8", CONTENT_TYPE, url, postData);
            JSONObject jsonObject = JSONObject.parseObject(response);
            Map<String,Object> map = jsonObject.toJavaObject(Map.class);
            String errcode = map.get("errcode").toString();
            if(ERRCODE2.equals(errcode)){
                setAccessToken();
                sendMsg(userId, partId, tagId, msgType, obj);
            }
            if("0".equals(errcode)){
                System.out.println("发送提醒成功");
                return;
            }
            System.out.println("发送提醒失败,错误码:"+errcode);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 执行get请求
     * @param getTokenUrl
     * @return 带有errcode,access_token等的响应文本
     * @throws IOException
     */
    private static String toAuth(String getTokenUrl) throws IOException {
        httpClient = HttpClients.createDefault();
        httpGet = new HttpGet(getTokenUrl);
        CloseableHttpResponse response = httpClient.execute(httpGet);
        System.out.println(response.toString());
        String resp;
        try {
            HttpEntity entity = response.getEntity();
            System.out.println(response.getAllHeaders());
            resp = EntityUtils.toString(entity, "utf-8");
            EntityUtils.consume(entity);
        } finally {
            response.close();
        }
        return resp;
    }

    /**
     * 编辑流程的markdown内容
     * @param map
     * @return
     */
    private static String getContent(Map<String, Object> map) {
        StringBuffer content= new StringBuffer();
        List<UserEntity> sentUsers = (List<UserEntity>) map.get("sendUsers");
        StringBuffer userNames = new StringBuffer();
        for(int i=0;i<sentUsers.size();i++){
            String userName = sentUsers.get(i).getUserName();
            userNames.append(userName);
            if(i<sentUsers.size()-1){
                userNames.append("|");
            }
        }

        String str = (String) map.get("presentationSubject");
        String[] split = str.split("-");
        String task = split[0];
        String name = split[1];
        String date = str.substring(str.indexOf("-", str.indexOf("-")+1)+1);
        content.append("您有一个审批待确认：\n" +
                "> **审批详情** \n" +
                ">\n" +
                "> 流程信息：\n" +
                ">\n" +
                "> 发起人：" +name+"\n"+
                ">\n" +
                "> 发起流程：" +task+"\n"+
                ">\n" +
                "> 发起时间：" +date+"\n"+
                ">\n" +
                "> 当前审批人："+userNames.toString()+"\n" +
                ">\n" +
                "> 详情请点击：[查看详情](https://cloud.cesgroup.com.cn:8888/oa/index)");
        return content.toString();
    }

    /**
     * POST请求
     */
    private static String post(String charset, String contentType, String url, String data) throws IOException {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        httpPost = new HttpPost(url);
        httpPost.setHeader(CONTENT_TYPE, contentType);
        httpPost.setEntity(new StringEntity(data, charset));
        CloseableHttpResponse response = httpclient.execute(httpPost);
        String resp;
        try {
            HttpEntity entity = response.getEntity();
            resp = EntityUtils.toString(entity, charset);
            EntityUtils.consume(entity);
        } finally {
            response.close();
        }
        return resp;
    }

    /**
     * 获取token
     * @return
     */
    private static String getAccessToken() {
        if(null == accessToken){
            setAccessToken();
        }
        return accessToken;
    }

    /**
     * 重新获取token
     */
    private static void setAccessToken() {
        try {
            String url = ACCESSTOKENURL.replace("ID",corpId).replace("SECRET",contactSecret);
            JSONObject jsonObject = JSONObject.parseObject(toAuth(url));
            Map<String,Object> map = jsonObject.toJavaObject(Map.class);
            accessToken = map.get("access_token").toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
