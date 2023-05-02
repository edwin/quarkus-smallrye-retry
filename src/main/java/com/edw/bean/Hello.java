package com.edw.bean;

import java.io.Serializable;

/**
 * <pre>
 *     com.edw.bean.Hello
 * </pre>
 *
 * @author Muhammad Edwin < edwin at redhat dot com >
 * 02 Mei 2023 11:18
 */
public class Hello implements Serializable {

    private String hello;

    public Hello() {
    }

    public Hello(String hello) {
        this.hello = hello;
    }

    public String getHello() {
        return hello;
    }

    public void setHello(String hello) {
        this.hello = hello;
    }
}
