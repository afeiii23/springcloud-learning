package com.xule.common;

import lombok.Data;

import java.io.Serializable;

/**
 * @Package com.xule.maserati.com.xule.common
 * @author: xule
 * @date: 2019/1/27 15:26
 */
@Data
public class ApiResult<T> implements Serializable {
    private static final long serialVersionUID = -6006368446988599134L;
    /**
     * 返回code
     */
    private Integer code;
    /**
     * 返回类型是否成功
     */
    private Boolean success;

    /**
     * 返回信息
     */
    private String message;

    /**
     * 返回数据
     */
    private T data;


    public static <T> ApiResult<T> ok(T data){
        ApiResult<T> apiResult = new ApiResult<>();
        apiResult.setCode(200);
        apiResult.setSuccess(true);
        apiResult.setData(data);
        return apiResult;
    }

    public static <T> ApiResult<T> error(Integer code, String message){
        ApiResult apiResult =  new ApiResult<>();
        apiResult.setCode(code);
        apiResult.setSuccess(false);
        apiResult.setMessage(message);
        return apiResult;
    }

    public static <T> ApiResult<T> error(String message){
        ApiResult apiResult =  new ApiResult<>();
        apiResult.setCode(500);
        apiResult.setSuccess(false);
        apiResult.setMessage(message);
        return apiResult;
    }





}
