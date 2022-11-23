package com.sznhl.agricultural.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class TbEnterprise implements Serializable{
    /**
     * id
     */
    private Integer id;

    /**
     * 企业名称r
     */
    private String name;

    /**
     * 企业信用代码r
     */
    private String enterpriseCreditCode;

    /**
     * 企业法人姓名r
     */
    private String enterpriseLegalPerson;

    /**
     * 企业法人身份证号r
     */
    private String enterpriseLegalPersonIdcard;

    /**
     * 企业联系人姓名r
     */
    private String enterpriseLinkPeople;

    /**
     * 企业联系人电话r
     */
    private String enterpriseLinkMobile;

    /**
     * 企业地址r
     */
    private String enterpriseAddress;

    /**
     * 种植规模
     */
    private String plantScope;

    /**
     * x
     */
    private String x;

    /**
     * y
     */
    private String y;

    /**
     * 实际种植面积（亩）
     */
    private BigDecimal area;

    /**
     * 确权面积（亩）
     */
    private BigDecimal confirmArea;

    /**
     * 流入面积（亩）
     */
    private BigDecimal inflowArea;

    /**
     * 流出面积（亩）
     */
    private BigDecimal outflowArea;

    /**
     * 企业类型r
     */
    private Integer enterpriseType;

    /**
     * 基地地址
     */
    private String baseAddress;

    /**
     * input_time
     */
    private Date inputTime;

    /**
     * 0-未审核 1-已审核 2-不通过
     */
    private Integer status;

    /**
     * plant_name
     */
    private String plantName;

    /**
     * dscd
     */
    private String dscd;

    /**
     * village
     */
    private String village;

    /**
     * 注册资金
     */
    private String registeredFunds;

    /**
     * 异动
     */
    private String changes;

    /**
     * 企业性质
     */
    private String enterpriseNature;

    /**
     * device_sn
     */
    private String deviceSn;

    /**
     * list_order
     */
    private Integer listOrder;

    /**
     * 1-种植 2-养殖 3-其他
     */
    private String type;

    /**
     * 0-停用 1-启用
     */
    private Integer state;

    /**
     * 0-未同步 1-已同步
     */
    private Integer isSync;

    /**
     * sync_number
     */
    private String syncNumber;

    /**
     * 产业
     */
    private String industrial;

    /**
     * del_flag
     */
    private Integer delFlag;

    /**
     * 集体农药限制金额
     */
    private BigDecimal nyLimitAmount;

    /**
     * 集体农膜限制金额
     */
    private BigDecimal nmLimitAmount;

    /**
     * business_scope
     */
    private String businessScope;

    /**
     * permit_foroperation_num
     */
    private String permitForoperationNum;

    /**
     * operation_mode
     */
    private String operationMode;
}
