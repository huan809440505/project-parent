package com.hyl.rock.base;

import lombok.Getter;

public enum MessageEnum {
    SUCCESS(200, "SUCCESS"),
    FAIL(500, "FAIL"),
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
