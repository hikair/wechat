package com.entity;

import com.alibaba.fastjson.JSON;

/**
 *    "taskcard" : {
 *             "title" : "赵明登的礼物申请",
 *             "description" : "礼品：A31茶具套装<br>用途：赠与小黑科技张总经理",
 *             "url" : "URL",
 *             "task_id" : "taskid123",
 *             "btn":[
 *                 {
 *                     "key": "key111",
 *                     "name": "批准",
 *                     "replace_name": "已批准",
 *                     "color":"red",
 *                     "is_bold": true
 *                 },
 *                 {
 *                     "key": "key222",
 *                     "name": "驳回",
 *                     "replace_name": "已驳回"
 *                 }
 *             ]
 *    }
 */
public class WeiXinTaskCard extends WeiXinAbstract{

    private Object taskcard;

    public WeiXinTaskCard(String touser, String toparty, String totag, String msgtype, Integer agentId, Object taskcard) {
        super(touser, toparty, totag, msgtype, agentId);
        this.taskcard = taskcard;
    }

    public WeiXinTaskCard(String touser, String toparty, String totag, String msgtype, Integer agentId, Object taskcard, Integer enable_duplicate_check, Integer duplicate_check_interval) {
        super(touser, toparty, totag, msgtype, agentId, enable_duplicate_check, duplicate_check_interval);
        this.taskcard = taskcard;
    }

    @Override
    public String toJsonString() {
        return JSON.toJSONString(this);
    }

    public Object getTaskcard() {
        return taskcard;
    }

    public void setTaskcard(Object taskcard) {
        this.taskcard = taskcard;
    }
}
