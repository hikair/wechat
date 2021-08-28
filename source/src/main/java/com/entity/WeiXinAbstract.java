package com.entity;

import com.utils.WeiXinApiUtils;

/**
 * 对接企业微信需用传输的数据
 * @author zpq
 */
public abstract class WeiXinAbstract {
    /**
     * 非必须
     * 成员ID列表（消息接收者，多个接收者用‘|’分隔，最多支持1000个）。特殊情况：指定为@all，则向关注该企业应用的全部成员发送
     */
    protected String touser;

    /**
     * 非必须
     * 部门ID列表，多个接收者用‘|’分隔，最多支持100个。当touser为@all时忽略本参数
     */
    protected String toparty;

    /**
     * 非必须
     * 标签ID列表，多个接收者用‘|’分隔，最多支持100个。当touser为@all时忽略本参数
     */
    protected String totag;

    /**
     * 必须
     * 消息类型，类型有：text、image、voice、video、file、news、mpnews、markdown、textcard、taskcard。
     * miniprogram_notice（小程序消息暂不支持）
     */
    protected String msgtype;

    /**
     * 必须
     * 企业应用的id，整型。企业内部开发，可在应用的设置页面查看；第三方服务商，可通过接口 获取企业授权信息 获取该参数值
     */
    protected Integer agentid;

    /**
     * 非必须
     * 表示是否开启重复消息检查，0表示否，1表示是，默认0
     */
    protected Integer enable_duplicate_check;

    /**
     * 非必须
     * 表示是否重复消息检查的时间间隔，默认1800s，最大不超过4小时
     */
    protected Integer duplicate_check_interval;

    /**
     * 将对象转换成json字符串
     * @return
     */
    public abstract String toJsonString();

    public WeiXinAbstract(String touser, String toparty, String totag, String msgtype, Integer agentid){
        this(touser,toparty,totag,msgtype,agentid,0,1800);
    }

    public WeiXinAbstract(String touser, String toparty, String totag, String msgtype,Integer agentId, Integer enable_duplicate_check,Integer duplicate_check_interval){
        this.touser = touser;
        this.toparty = toparty;
        this.totag = totag;
        this.msgtype = msgtype;
        this.enable_duplicate_check = enable_duplicate_check;
        this.duplicate_check_interval = duplicate_check_interval;
        this.agentid = agentId;
    }

    public String getTouser() {
        return touser;
    }

    public void setTouser(String touser) {
        this.touser = touser;
    }

    public String getToparty() {
        return toparty;
    }

    public void setToparty(String toparty) {
        this.toparty = toparty;
    }

    public String getTotag() {
        return totag;
    }

    public void setTotag(String totag) {
        this.totag = totag;
    }

    public String getMsgtype() {
        return msgtype;
    }

    public void setMsgtype(String msgtype) {
        this.msgtype = msgtype;
    }

    public Integer getAgentid() {
        return agentid;
    }

    public void setAgentid(Integer agentid) {
        this.agentid = agentid;
    }

    public Integer getEnable_duplicate_check() {
        return enable_duplicate_check;
    }

    public void setEnable_duplicate_check(Integer enable_duplicate_check) {
        this.enable_duplicate_check = enable_duplicate_check;
    }

    public Integer getDuplicate_check_interval() {
        return duplicate_check_interval;
    }

    public void setDuplicate_check_interval(Integer duplicate_check_interval) {
        this.duplicate_check_interval = duplicate_check_interval;
    }
}
