package com.ghost.common.utils;

import lombok.Data;

import java.io.Serializable;


@Data
public class GhostResult implements Serializable {

    private Integer code;

    private String message;

    private Object data;

    private GhostResult() {

    }

    public GhostResult(ResultCode resultCode, Object data) {
        this.code = resultCode.code();
        this.message = resultCode.message();
        this.data = data;
    }

    private void setResultCode(ResultCode resultCode) {
        this.code = resultCode.code();
        this.message = resultCode.message();
    }

    // 返回成功
    public static GhostResult success() {
        GhostResult result = new GhostResult();
        result.setResultCode(ResultCode.SUCCESS);
        return result;
    }

    // 返回成功
    public static GhostResult success(Object data) {
        GhostResult result = new GhostResult();
        result.setResultCode(ResultCode.SUCCESS);
        result.setData(data);
        return result;
    }

    // 返回失败
    public static GhostResult fail(Integer code, String message) {
        GhostResult result = new GhostResult();
        result.setCode(code);
        result.setMessage(message);
        return result;
    }

    // 返回失败
    public static GhostResult fail(ResultCode resultCode) {
        GhostResult result = new GhostResult();
        result.setResultCode(resultCode);
        return result;
    }
}
