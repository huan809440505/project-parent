package com.hyl.rock.base;

import lombok.Data;

@Data
public class Result<T> {

    private Integer code;
    private String msg;
    private T data;

    public Result() {}

    public Result(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Result(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
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

    public static <T> Result<T> fail(String msg) {
        return new Result(MessageEnum.FAIL, msg);
    }

    public static <T> Result<T> fail(Integer code, String msg) {
        return new Result(code, msg);
    }

}
