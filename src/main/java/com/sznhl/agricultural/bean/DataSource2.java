package com.sznhl.agricultural.bean;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

//从applications.properties中读取对应的配置信息
@Component
@ConfigurationProperties(prefix = "spring.datasource.ds2")
public class DataSource2 {

    private String url;
    private String username;
    private String password;

    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

}