package com.hyl.test.base;

import lombok.Getter;

public enum MessageEnum {
    SUCCESS("10000", "SUCCESS")
    ;

    @Getter
    private String code;

    @Getter
    private String message;

    MessageEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
