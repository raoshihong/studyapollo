package com.rao.study.apollo.spring;

import org.springframework.beans.factory.annotation.Value;

public class TestJavaConfigBean {

    @Value("${timeout}")
    private String timeOut;

    @Value("${name}")
    private String name;

    @Value("${jdbc}")
    private String jdbc;

    public String getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(String timeOut) {
        this.timeOut = timeOut;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJdbc() {
        return jdbc;
    }

    public void setJdbc(String jdbc) {
        this.jdbc = jdbc;
    }
}
