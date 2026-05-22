package com.hyl.rock.base;

import lombok.Getter;

public enum MessageEnum {
    SUCCESS(10000, "操作成功"),
    FAIL(99999, "系统繁忙，请稍后重试"),

    /*自定义异常*/
    USER_NOT_FOUND(10001, "用户不存在"),//用户不存在
    PARAM_ERROR(10002, "参数验证失败"),
    ;

    @Getter
    private final Integer code;

    @Getter
    private final String message;

    MessageEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
