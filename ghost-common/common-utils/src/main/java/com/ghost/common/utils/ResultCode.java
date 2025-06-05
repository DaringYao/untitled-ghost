package com.ghost.common.utils;


public enum ResultCode {

	/* 成功状态码 */
    SUCCESS(200, "成功"),
    /* 错误状态码 */
    PARAM_IS_INVALID(201, "参数无效"),
    PARAM_IS_BLANK(202, "参数为空"),
    PARAM_TYPE_BIND_ERROR(401, "参数类型错误"),
    PARAM_NOT_COMPLETE(402, "参数缺失"),
    USER_NOT_LOGGED_IN(501, "用户未登录"),
    USER_LOGIN_ERROR(502, "账号不存在或密码错误"),
    SYSTEM_ERROR(500, "系统异常，请稍后重试");

    private final Integer code;
    private final String message;

    ResultCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer code() {
        return this.code;
    }

    public String message() {
        return this.message;
    }
}
