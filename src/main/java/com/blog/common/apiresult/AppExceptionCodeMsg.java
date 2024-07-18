package com.blog.common.apiresult;

/**
 * 描述:      异常枚举类
 */
public enum AppExceptionCodeMsg {
    ADD_USER_ERR(1000, "添加用户失败"),
    USERNAME_ALREADY_EXIST(1001, "用户名已存在"),
    USER_NOT_EXIST(1002, "用户不存在"),
    NOT_LOGIN(1003, "还未登录"),
    NOT_POWER_UPDATE(1004, "没有权限修改"),
    NOT_POWER_DELETE(1005, "没有权限删除"),
    POST_NOT_EXIST(1006, "文章不存在"),
    LOGIN_STATUS_OVERDUE(1007, "登录状态已过期"),
    REQUEST_PARAM_ERROR(10000, "参数错误"),
    REQUEST_CONTENT_TYPE_NOT_JSON(10001, "请求数据格式不是JSON"),
    SYSTEM_ERROR(500, "服务端异常");;

    private int code;
    private String msg;

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }


    AppExceptionCodeMsg(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
