package com.hyl.rock.base;

import lombok.Data;

@Data
public class Result<T> {

    private Integer code;
    private String message;
    private T data;

    public Result() {}

    public Result(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Result(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public Result(MessageEnum messageEnum) {
        this(messageEnum.getCode(),messageEnum.getMessage());
    }

    public Result(MessageEnum messageEnum, T data) {
        this(messageEnum.getCode(),messageEnum.getMessage(),data);
    }


    public static <T> Result<T> success() {
        return new Result(MessageEnum.SUCCESS);
    }

    public static <T> Result<T> success(T data) {
        return new Result(MessageEnum.SUCCESS, data);
    }

    public static <T> Result<T> fail() {
        return new Result(MessageEnum.FAIL);
    }

    public static <T> Result<T> fail(String message) {
        return new Result(MessageEnum.FAIL.getCode(), message);
    }

    public static <T> Result<T> fail(Integer code, String msg) {
        return new Result(code, msg);
    }

}
