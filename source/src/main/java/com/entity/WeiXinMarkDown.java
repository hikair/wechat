package com.entity;

import com.alibaba.fastjson.JSON;

/**
 * "markdown": {
 *         "content": "您的会议室已经预定，稍后会同步到`邮箱`
 *                 >**事项详情**
 *                 >事　项：<font color=\"info\">开会</font>
 *                 >组织者：@miglioguan
 *                 >参与者：@miglioguan、@kunliu、@jamdeezhou、@kanexiong、@kisonwang
 *                 >
 *                 >会议室：<font color=\"info\">广州TIT 1楼 301</font>
 *                 >日　期：<font color=\"warning\">2018年5月18日</font>
 *                 >时　间：<font color=\"comment\">上午9:00-11:00</font>
 *                 >
 *                 >请准时参加会议。
 *                 >
 *                 >如需修改会议信息，请点击：[修改会议信息](https://work.weixin.qq.com)"
 *    }
 */
public class WeiXinMarkDown extends WeiXinAbstract {

    private Object markdown;

    public WeiXinMarkDown(String touser, String toparty, String totag, String msgtype, Integer agentId, Object markdown) {
        super(touser, toparty, totag, msgtype, agentId);
        this.markdown = markdown;
    }

    public WeiXinMarkDown(String touser, String toparty, String totag, String msgtype, Integer agentId, Object markdown, Integer enable_duplicate_check, Integer duplicate_check_interval) {
        super(touser, toparty, totag, msgtype, agentId, enable_duplicate_check, duplicate_check_interval);
        this.markdown = markdown;
    }

    @Override
    public String toJsonString() {
        return JSON.toJSONString(this);
    }

    public Object getMarkdown() {
        return markdown;
    }

    public void setMarkdown(Object markdown) {
        this.markdown = markdown;
    }
}
