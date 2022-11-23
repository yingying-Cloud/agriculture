package com.sznhl.agricultural.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class TbToolPool implements Serializable{
    /**
     * id
     */
    private Integer id;

    /**
     * 企业id
     */
    private Integer enterpriseId;

    /**
     * 供应商
     */
    private String supplierName;

    /**
     * 名字
     */
    private String name;

    /**
     * 模型
     */
    private String model;

    /**
     * 单位
     */
    private String unit;

    /**
     * 价格
     */
    private String price;

    /**
     * 数量
     */
    private String number;

    /**
     * 描述
     */
    private String describe;

    /**
     * 种类
     */
    private Integer type;

    /**
     * 生产单位
     */
    private String productionUnits;

    /**
     * 登记证号
     */
    private String registrationCertificateNumber;

    /**
     * 明示成分
     */
    private String explicitIngredients;

    /**
     * 有效成份
     */
    private String effectiveIngredients;

    /**
     * 执行标准
     */
    private String implementationStandards;

    /**
     * 剂型
     */
    private String dosageForms;

    /**
     * 产品属性
     */
    private String productAttributes;

    /**
     * 毒性
     */
    private String toxicity;

    /**
     * 质量等级
     */
    private String qualityGrade;

    /**
     * 是否统一售价
     */
    private String uniformPrice;

    /**
     * 农资编码
     */
    private String code;

    /**
     * del_flag
     */
    private Integer delFlag;

    /**
     * 批发价
     */
    private String wholesalePrice;

    /**
     * dnm
     */
    private String dnm;

    /**
     * 0-未同步 1-已同步
     */
    private Integer isSync;

    /**
     * sync_number
     */
    private String syncNumber;

    /**
     * hfzc
     */
    private String hfzc;

    /**
     * approval_end_date
     */
    private Date approvalEndDate;

    /**
     * approval_no
     */
    private String approvalNo;

    /**
     * approve_no
     */
    private String approveNo;

    /**
     * certificate_no
     */
    private String certificateNo;

    /**
     * check_health_no
     */
    private String checkHealthNo;

    /**
     * health_no
     */
    private String healthNo;

    /**
     * limit_date
     */
    private Date limitDate;

    /**
     * produced
     */
    private String produced;

    /**
     * production_no
     */
    private String productionNo;

    /**
     * record_no
     */
    private String recordNo;

    /**
     * 规格
     */
    private String specification;

    /**
     * 氮
     */
    private String n;

    /**
     * 磷
     */
    private String p;

    /**
     * 钾
     */
    private String k;

    /**
     * 其他
     */
    private String qt;

    /**
     * 二维码
     */
    private String qrCode;

    /**
     * 氮磷钾
     */
    private String npk;

    /**
     * 氮磷
     */
    private String np;

    /**
     * 氮钾
     */
    private String nk;

    /**
     * 磷钾
     */
    private String pk;

    /**
     * 总有效成分单位
     */
    private String yxcfUnit;

    /**
     * 制剂种类
     */
    private String zjzl;

    /**
     * is_other
     */
    private String isOther;

    /**
     * 修改信息
     */
    private String remark;
}
