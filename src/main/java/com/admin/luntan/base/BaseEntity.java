package com.admin.luntan.base;

import com.admin.luntan.entity.Pager;
import com.admin.luntan.util.JsonUtil;
import com.admin.luntan.util.StringUtil;
import com.alibaba.fastjson.annotation.JSONField;

/**
 * 实体基类
 *
 * @author zhc
 */
public class BaseEntity extends BaseBean {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 6200590410975321590L;

    /**
     * 排序字符串
     * <p>在数据库的XML文件中，通过${}直接写入原文</p>
     * <p style="color:red">这里要格外小心，必须所有字段进行逐一检查。防止SQL注入。</p>
     */
    private String orderSql = StringUtil.EMPTY;

    /**
     * 前端脚本BootGrid使用的行级状态
     * <ul><li>0 - success</li>
     * <li>1 - info</li>
     * <li>2 - warning</li>
     * <li>3 - danger</li></ul>
     */
    private String rowStatus = null;


    /**
     * 分页类
     */
    private Pager pager = null;

    /**
     * 获取分页对象
     *
     * @return 分页对象
     */
    public Pager getPager() {
        return pager;
    }

    /**
     * 设置分页对象
     *
     * @param pager 分页对象
     */
    public void setPager(Pager pager) {
        this.pager = pager;
    }

    /**
     * 获取排序的SQL
     *
     * @return 排序的SQL
     */
    @JSONField(serialize = false)
    public String getOrderSql() {
        return orderSql;
    }

    /**
     * 设置排序的SQL
     * <p>在数据库的XML文件中，通过${}直接写入原文</p>
     * <p style="color:red">这里要格外小心，必须所有字段进行逐一检查。防止SQL注入。</p>
     *
     * @param orderSql 排序的SQL
     */
    public void setOrderSql(String orderSql) {
        this.orderSql = orderSql;
    }

    /**
     * 获取行级状态
     *
     * @return 行级状态
     */
    @JSONField(name = "rowstatus")
    public String getRowStatus() {
        return rowStatus;
    }

    /**
     * 设置前端脚本BootGrid使用的行级状态
     * <ul><li>0 - success</li>
     * <li>1 - info</li>
     * <li>2 - warning</li>
     * <li>3 - danger</li></ul>
     *
     * @param rowStatus 行级状态
     */
    public void setRowStatus(String rowStatus) {
        this.rowStatus = rowStatus;
    }


    public String toJson() {
        return JsonUtil.toJson(this);
    }

    @Override
    public String toString() {
        return JsonUtil.toJson(this);
    }
}
