package com.entity;

/**
 * 企业微信获取指定汇报模板的汇报单id 请求体参照格式
 *{
 *     "starttime": 1609084800,
 *     "endtime": 1609171199,
 *     "cursor": 0,
 *     "limit": 10,
 *     "filters": [
 *         {
 *             "key": "template_id",
 *             "value": "3TmkwGvfmq2oPffou8d7MespP4dF4U5Rgtf1XUyA"
 *         }
 *     ]
 * }
 */
public class WeiXinRecordListVO {
    private Long starttime;
    private Long endtime;
    private Integer cursor;
    private Integer limit;
    private Object filters;

    public Long getStarttime() {
        return starttime;
    }

    public void setStarttime(Long starttime) {
        this.starttime = starttime;
    }

    public Long getEndtime() {
        return endtime;
    }

    public void setEndtime(Long endtime) {
        this.endtime = endtime;
    }

    public Integer getCursor() {
        return cursor;
    }

    public void setCursor(Integer cursor) {
        this.cursor = cursor;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Object getFilters() {
        return filters;
    }

    public void setFilters(Object filters) {
        this.filters = filters;
    }
}
