package com.sznhl.agricultural.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class TbUser implements Serializable {
    /**
     * id
     */
    private Integer id;

    /**
     * user_id
     */
    private String userId;

    /**
     * api_key
     */
    private String apiKey;

    /**
     * 电话
     */
    private String mobile;

    /**
     * 密码
     */
    private String password;

    /**
     * 注册时间
     */
    private Date regTime;

    /**
     * 最后登陆时间
     */
    private Date lastTime;

    /**
     * 名字
     */
    private String name;

    /**
     * 身份证号
     */
    private String expertIdcard;

    /**
     * 专家擅长领域
     */
    private String expertField;

    /**
     * 微信号
     */
    private String wxId;

    /**
     * 头像
     */
    private String headPic;

    /**
     * 收银机运行程序版本
     */
    private String cashRegisterVersion;

    /**
     * 收银机编码
     */
    private String cashRegisterId;

    /**
     * del_flag
     */
    private Integer delFlag;

    /**
     * integral
     */
    private String integral;

    /**
     * dscd
     */
    private String dscd;

    /**
     * 数字农合联账号
     */
    private String username2;
}
