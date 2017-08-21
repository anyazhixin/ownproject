package com.anya.own.dto.test;

import java.io.Serializable;

/**
 * @author wuwenliang
 * @date 2017-08-16.
 */
public class GetUserRequest implements Serializable {

    private static final long serialVersionUID = 4923778835694831203L;

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
