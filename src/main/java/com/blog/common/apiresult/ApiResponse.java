package com.blog.common.apiresult;

/**
 * 描述:      统一返回格式类
 */
public class ApiResponse {
    //服务端返回的错误码
    private int code = 200;
    //服务端返回的错误信息
    private String msg = "success";
    //我们服务端返回的数据
    private Object data;

    private ApiResponse(int code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static ApiResponse success(Object data) {
        ApiResponse resp = new ApiResponse(200, "success", data);
        return resp;
    }

    public static ApiResponse error(AppExceptionCodeMsg appExceptionCodeMsg) {
        ApiResponse resp = new ApiResponse(appExceptionCodeMsg.getCode(), appExceptionCodeMsg.getMsg(), null);
        return resp;
    }

    public static <T> ApiResponse error(int code, String msg) {
        ApiResponse resp = new ApiResponse(code, msg, null);
        return resp;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public Object getData() {
        return data;
    }
}
