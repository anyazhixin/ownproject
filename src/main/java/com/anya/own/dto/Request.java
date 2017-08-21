package com.anya.own.dto;

import java.io.Serializable;

/**
 * @author wuwenliang
 * @date 2017-08-15.
 */
public class Request<T> implements Serializable {

    private static final long serialVersionUID = -99984669112426766L;

    private T data;

    public Request() {
    }

    public Request(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
