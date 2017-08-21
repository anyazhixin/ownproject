package com.anya.own.dto;

import java.io.Serializable;

/**
 * @author wuwenliang
 * @date 2017-08-15.
 */
public class Result<T> implements Serializable {

    private static final long serialVersionUID = -3403287257942663782L;

    private boolean success;

    private String code;

    private String description;

    private T data;

    public Result<T> success() {
        success(null);
        return this;
    }

    public Result<T> success(T data) {
        this.success = true;
        this.data = data;
        return this;
    }

    public Result<T> fail(String code,String description){
        this.setSuccess(false);
        this.setCode(code);
        this.setDescription(description);
        return this;
    }

    public Result<T> fail(String code){
        fail(code, null);
        return this;
    }

    public Result<T> code(String code){
        this.setCode(code);
        return this;
    }

    public Result<T> description(String description){
        this.setDescription(description);
        return this;
    }

    public Result<T> data(T data){
        this.data = data;
        return this;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
