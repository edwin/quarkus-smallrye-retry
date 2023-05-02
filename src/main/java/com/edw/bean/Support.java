package com.edw.bean;

import java.io.Serializable;

/**
 * <pre>
 *     com.edw.bean.Support
 * </pre>
 *
 * @author Muhammad Edwin < edwin at redhat dot com >
 * 02 Mei 2023 11:46
 */
public class Support implements Serializable {

    private String url;
    private String text;

    public Support() {
    }

    public Support(String url, String text) {
        this.url = url;
        this.text = text;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
