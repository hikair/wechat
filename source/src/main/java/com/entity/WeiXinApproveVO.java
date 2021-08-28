package com.entity;

/**
 * 用于方便json转换
 * 企业微信提交审批 请求体参照格式
 * {
 *     "creator_userid": "WangXiaoMing",
 *     "template_id": "3Tka1eD6v6JfzhDMqPd3aMkFdxqtJMc2ZRioeFXkaaa",
 *     "use_template_approver":0,
 *     "approver": [
 *         {
 *             "attr": 2,
 *             "userid": ["WuJunJie","WangXiaoMing"]
 *         },
 *         {
 *             "attr": 1,
 *             "userid": ["LiuXiaoGang"]
 *         }
 *     ],
 *     "notifyer":[ "WuJunJie","WangXiaoMing" ],
 *     "notify_type" : 1,
 *     "apply_data": {
 *          "contents": [
 *                 {
 *                     "control": "Text",
 *                     "id": "Text-15111111111",
 *                     "value": {
 *                         "text": "文本填写的内容"
 *                     }
 *                 }
 *             ]
 *     },
 *     "summary_list": [
 *         {
 *             "summary_info": [{
 *                 "text": "摘要第1行",
 *                 "lang": "zh_CN"
 *             }]
 *         },
 *         {
 *             "summary_info": [{
 *                 "text": "摘要第2行",
 *                 "lang": "zh_CN"
 *             }]
 *         },
 *         {
 *             "summary_info": [{
 *                 "text": "摘要第3行",
 *                 "lang": "zh_CN"
 *             }]
 *         }
 *     ]
 * }
 */
public class WeiXinApproveVO {

    /**
     * 申请人userid，此审批申请将以此员工身份提交，申请人需在应用可见范围内
     */
    private String creator_userid;

    /**
     * 模板id。可在“获取审批申请详情”、“审批状态变化回调通知”中获得，也可在审批模板的模板编辑页面链接中获得。暂不支持通过接口提交[打卡补卡][调班]模板审批单。
     */
    private String template_id;

    /**
     * 审批人模式：0-通过接口指定审批人、抄送人（此时approver、notifyer等参数可用）; 1-使用此模板在管理后台设置的审批流程，支持条件审批。默认为0
     */
    private Integer use_template_approver;

    /**
     * 审批申请数据，可定义审批申请中各个控件的值，其中必填项必须有值，选填项可为空，数据结构同“获取审批申请详情”接口返回值中同名参数“apply_data”
     */
    private Object apply_data;

    /**
     * 摘要信息，用于显示在审批通知卡片、审批列表的摘要信息，最多3行
     */
    private Object summary_list;

    public String getCreator_userid() {
        return creator_userid;
    }

    public void setCreator_userid(String creator_userid) {
        this.creator_userid = creator_userid;
    }

    public String getTemplate_id() {
        return template_id;
    }

    public void setTemplate_id(String template_id) {
        this.template_id = template_id;
    }

    public Integer getUse_template_approver() {
        return use_template_approver;
    }

    public void setUse_template_approver(Integer use_template_approver) {
        this.use_template_approver = use_template_approver;
    }

    public Object getApply_data() {
        return apply_data;
    }

    public void setApply_data(Object apply_data) {
        this.apply_data = apply_data;
    }

    public Object getSummary_list() {
        return summary_list;
    }

    public void setSummary_list(Object summary_list) {
        this.summary_list = summary_list;
    }
}
