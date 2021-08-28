package com.entity;

import com.alibaba.fastjson.JSON;

/**
 *  "file" : {
 *         "media_id" : "1Yv-zXfHjSjU-7LH-GwtYqDGS-zz6w22KmWAT5COgP7o"
 *    }
 */
public class WeiXinFile extends WeiXinAbstract{

    private Object file;

    public WeiXinFile(String touser, String toparty, String totag, String msgtype, Integer agentId, Object file) {
        super(touser, toparty, totag, msgtype, agentId);
        this.file = file;
    }

    public WeiXinFile(String touser, String toparty, String totag, String msgtype, Integer agentId, Object file, Integer enable_duplicate_check, Integer duplicate_check_interval) {
        super(touser, toparty, totag, msgtype, agentId, enable_duplicate_check, duplicate_check_interval);
        this.file = file;
    }

    @Override
    public String toJsonString() {
        return JSON.toJSONString(this);
    }

    public Object getFile() {
        return file;
    }

    public void setFile(Object file) {
        this.file = file;
    }
}
