package com.sznhl.agricultural.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class TbLinkOrderInfo implements Serializable{
    /**
     * id
     */
    private Integer id;

    /**
     * 企业id
     */
    private Integer enterpriseId;

    /**
     * 片区名称
     */
    private String zoneName;

    /**
     * name
     */
    private String name;

    /**
     * 信用码
     */
    private String creditCode;

    /**
     * 法人
     */
    private String legalPerson;

    /**
     * 法人身份证
     */
    private String legalPersonIdcard;

    /**
     * 联系人
     */
    private String linkPeople;

    /**
     * 联系电话
     */
    private String linkMobile;

    /**
     * 地址
     */
    private String address;

    /**
     * input_time
     */
    private Date inputTime;

    /**
     * area
     */
    private String area;

    /**
     * del_flag
     */
    private Integer delFlag;

    /**
     * 1-企业 2-个人
     */
    private Integer type;

    /**
     * 民族
     */
    private String nation;

    /**
     * 国家
     */
    private String country;

    /**
     * 性别
     */
    private String sex;

    /**
     * 身份证照片
     */
    private String idcardPic;

    /**
     * 最新照片
     */
    private String lastPic;

    /**
     * update_time
     */
    private Date updateTime;

    /**
     * is_validation
     */
    private String isValidation;

    /**
     * is_sync
     */
    private Integer isSync;

    /**
     * sync_number
     */
    private String syncNumber;

    /**
     * is_other
     */
    private String isOther;
}
