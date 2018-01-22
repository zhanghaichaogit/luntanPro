package com.admin.luntan.base;

import com.admin.luntan.util.JsonUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.Serializable;

/**
 * 微信实体基础类
 *
 * @author BBF
 */
public abstract class BaseBean implements Serializable {
    private static final long serialVersionUID = -1805283690606975260L;
    protected static final Logger LOGGER = LogManager.getLogger(BaseBean.class);

    /**
     * 转换成JSON字符串
     *
     * @return JSON字符串
     */
    public String toJson() {
        return JsonUtil.toJson(this);
    }

    /**
     * 重写toString方法
     *
     * @return JSON字符串
     * @see Object#toString()
     */
    @Override
    public String toString() {
        return this.toJson();
    }
}
