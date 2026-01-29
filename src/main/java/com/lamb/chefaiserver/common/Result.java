package com.lamb.chefaiserver.common;

import lombok.Data;

/**
 * 通用接口响应包装。
 *
 * @param <T> 数据类型
 */
@Data
public class Result<T> {
    /**
     * 业务状态码（200 表示成功）。
     */
    private Integer code;
    /**
     * 描述信息。
     */
    private String message;
    /**
     * 返回数据。
     */
    private T data;

    /**
     * 构建成功响应（含数据）。
     *
     * @param data 返回数据
     * @return 成功结果
     */
    public static <T> Result<T> success(T data) {
        Result<T> result = new Result<>();
        result.code = 200;
        result.message = "成功";
        result.data = data;
        return result;
    }


//    public static <T> Result<T> success() {
//        Result<T> result = new Result<>();
//        result.code = 200;
//        result.message = "成功";
//        return result;
//    }

    /**
     * 构建错误响应。
     *
     * @param message 错误信息
     * @return 错误结果
     */
    public static <T> Result<T> error(String message) {
        Result<T> result = new Result<>();
        result.code = 500;
        result.message = message;
        return result;
    }
}
