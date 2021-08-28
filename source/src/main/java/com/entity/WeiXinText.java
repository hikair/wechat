package com.entity;

import com.alibaba.fastjson.JSON;

/**
 *    "text" : {
 *        "content" : "你的快递已到，请携带工卡前往邮件中心领取。\n出发前可查看<a href=\"http://work.weixin.qq.com\">邮件中心视频实况</a>，聪明避开排队。"
 *    }
 */
public class WeiXinText extends WeiXinAbstract {

    private Object text;

    public WeiXinText(String touser, String toparty, String totag, String msgtype, Integer agentId, Object obj) {
        super(touser, toparty, totag, msgtype, agentId);
        this.text = obj;
    }

    public WeiXinText(String touser, String toparty, String totag, String msgtype, Integer agentId, Object obj, Integer enable_duplicate_check, Integer duplicate_check_interval) {
        super(touser, toparty, totag, msgtype, agentId, enable_duplicate_check, duplicate_check_interval);
        this.text = obj;
    }

    @Override
    public String toJsonString() {
        return JSON.toJSONString(this);
    }

    public Object getText() {
        return text;
    }

    public void setText(Object text) {
        this.text = text;
    }
}
