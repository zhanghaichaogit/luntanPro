package com.admin.luntan.base;

import com.admin.luntan.util.StringUtil;

/**
 * Created by zhanghaichao on 2018/1/20.
 */
public class ServiceResult<T> extends BaseEntity{

    private static final long serialVersionUID = -8293095062123847356L;
    private T result;

    private String code = StringUtil.EMPTY;

    private String message = StringUtil.EMPTY;
    private boolean success = false;


    /**
     * 获取一个错误的响应结果
     * @param errMsg 错误信息
     * @return 响应结果
     */
    public static ServiceResult<Boolean> getErrorResult(final String errMsg) {
        ServiceResult<Boolean> result = new ServiceResult<>();
        result.setError(errMsg);
        result.setResult(false);
        return result;
    }

    public void setError(final String message) {
        this.code = StringUtil.EMPTY;
        this.message = message;
        this.success = false;
    }

    public boolean getSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
