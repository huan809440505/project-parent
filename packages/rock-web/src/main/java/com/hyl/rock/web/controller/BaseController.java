package com.hyl.rock.web.controller;

import com.hyl.rock.base.Result;

/**
 * web层通用数据处理
 * 
 */
public class BaseController {

    /**
     * 返回成功
     */
    public Result<String> success() {
        return Result.success();
    }

    /**
     * 返回成功消息
     */
    public Result<String> success(String message) {
        return Result.success(message);
    }

    /**
     * 返回成功消息
     */
    public <T> Result<T> success(T data){
        return Result.success(data);
    }

    /**
     * 返回失败消息
     */
    public Result<String> error() {
        return Result.fail();
    }

    /**
     * 返回失败消息
     */
    public Result<String> error(String message) {
        return Result.fail(message);
    }

    /**
     * 返回警告消息
     */
    public Result<String> warn(String message) {
        return Result.fail(message);
    }

    /**
     * 响应返回结果
     *
     * @param rows 影响行数
     * @return 操作结果
     */
    protected Result<String> toAjax(int rows) {
        return rows > 0 ? Result.success() : Result.fail();
    }


    /**
     * 响应返回结果
     * 
     * @param result 结果
     * @return 操作结果
     */
    protected Result<String> toAjax(boolean result) {
        return result ? success() : error();
    }
}
