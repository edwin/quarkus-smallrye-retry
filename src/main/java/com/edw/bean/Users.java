package com.edw.bean;

import java.io.Serializable;

/**
 * <pre>
 *     com.edw.bean.Users
 * </pre>
 *
 * @author Muhammad Edwin < edwin at redhat dot com >
 * 02 Mei 2023 11:44
 */
public class Users implements Serializable {

    private Data data;
    private Support support;

    public Users() {
    }

    public Users(Data data, Support support) {
        this.data = data;
        this.support = support;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public Support getSupport() {
        return support;
    }

    public void setSupport(Support support) {
        this.support = support;
    }
}
