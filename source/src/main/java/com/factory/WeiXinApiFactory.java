package com.factory;

import com.entity.WeiXinMarkDown;
import com.entity.WeiXinTaskCard;
import com.entity.WeiXinText;

/**
 * 工厂类
 * @author zpq
 */
public class WeiXinApiFactory {

    /**
     * 用于返回传输数据的json字符串
     */
    public static String createWeiXinData(String toUser, String toParty, String toTag, String msgType, Integer agentId, Object obj){
        if("text".equals(msgType)){
            WeiXinText weiXinText = new WeiXinText(toUser, toParty, toTag, msgType, agentId, obj);
            return weiXinText.toJsonString();
        }
        if("markdown".equals(msgType)){
            WeiXinMarkDown weiXinMarkDown = new WeiXinMarkDown(toUser, toParty, toTag, msgType, agentId, obj);
            return weiXinMarkDown.toJsonString();
        }
        if("taskcard".equals(msgType)){
            WeiXinTaskCard weiXinTaskCard = new WeiXinTaskCard(toUser, toParty, toTag, msgType, agentId, obj);
            return weiXinTaskCard.toJsonString();
        }
        return null;
    }

}
