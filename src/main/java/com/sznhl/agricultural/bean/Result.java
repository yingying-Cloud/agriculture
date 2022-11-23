package com.sznhl.agricultural.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel(value = "响应")
public class Result<T> implements Serializable {

    /**
     * 编码：200表示成功，其他值表示失败
     */
    @ApiModelProperty(value = "编码：200表示成功，其他值表示失败")
    private int code = 200;
    /**
     * 消息内容
     */
    @ApiModelProperty(value = "消息内容")
    private String msg = "success";
    /**
     * 响应数据
     */
    @ApiModelProperty(value = "响应数据")
    private T data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Result() {
    }

    public Result(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Result<T> ok(T data) {
        this.setData(data);
        return this;
    }

    public Result<T> error(String msg) {
        this.code = 500;
        this.msg = msg;
        return this;
    }

    public Result<T> error() {
        this.code = 500;
        this.msg = "系统错误";
        return this;
    }

    public Result<T> error(int code, String msg) {
        this.code = code;
        this.msg = msg;
        return this;
    }

    public Result<T> error(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
        return this;
    }
}
