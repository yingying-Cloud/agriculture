package com.sznhl.agricultural.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sznhl.agricultural.entity.TbFile;
import com.sznhl.agricultural.entity.TbToolYxcf;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class ToolVo implements Serializable{

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String userId;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String apiKey;

    /**
     * id
     */
    @ApiModelProperty(value = "主键Id")
    private Integer id;

    /**
     * 企业id
     */
    @ApiModelProperty(value = "企业id")
    private Integer enterpriseId;

    /**
     * 供应商
     */
    @ApiModelProperty(value = "供应商")
    private String supplierName;

    /**
     * 名字
     */
    @ApiModelProperty(value = "名字")
    private String name;

    /**
     * 模型
     */
    @ApiModelProperty(value = "模型")
    private String model;

    /**
     * 单位
     */
    @ApiModelProperty(value = "单位")
    private String unit;

    /**
     * 价格
     */
    @ApiModelProperty(value = "价格")
    private String price;

    /**
     * 数量
     */
    @ApiModelProperty(value = "数量")
    private String number;

    /**
     * 描述
     */
    @ApiModelProperty(value = "描述")
    private String describe;

    /**
     * 种类
     */
    @ApiModelProperty(value = "种类")
    private Integer type;

    /**
     * 生产单位
     */
    @ApiModelProperty(value = "生产单位")
    private String productionUnits;

    /**
     * 登记证号
     */
    @ApiModelProperty(value = "登记证号")
    private String registrationCertificateNumber;

    /**
     * 明示成分
     */
    @ApiModelProperty(value = "明示成分")
    private String explicitIngredients;

    /**
     * 有效成份
     */
    @ApiModelProperty(value = "有效成份")
    private String effectiveIngredients;

    /**
     * 执行标准
     */
    @ApiModelProperty(value = "执行标准")
    private String implementationStandards;

    /**
     * 剂型
     */
    @ApiModelProperty(value = "剂型")
    private String dosageForms;

    /**
     * 产品属性
     */
    @ApiModelProperty(value = "产品属性")
    private String productAttributes;

    /**
     * 毒性
     */
    @ApiModelProperty(value = "毒性")
    private String toxicity;

    /**
     * 质量等级
     */
    @ApiModelProperty(value = "质量等级")
    private String qualityGrade;

    /**
     * 是否统一售价
     */
    @ApiModelProperty(value = "是否统一售价")
    private String uniformPrice;

    /**
     * 农资编码
     */
    @ApiModelProperty(value = "农资编码")
    private String code;

    /**
     * 批发价
     */
    @ApiModelProperty(value = "批发价")
    private String wholesalePrice;

    /**
     * dnm
     */
    @ApiModelProperty(value = "店内码")
    private String dnm;

    /**
     * 0-未同步 1-已同步
     */
    @ApiModelProperty(value = "0-未同步 1-已同步")
    private Integer isSync;

    /**
     * sync_number
     */
    @ApiModelProperty(value = "同步编码")
    private String syncNumber;

    /**
     * hfzc
     */
    @ApiModelProperty(value = "")
    private String hfzc;

    /**
     * approval_end_date
     */
    @ApiModelProperty(value = "")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date approvalEndDate;

    /**
     * approval_no
     */
    @ApiModelProperty(value = "")
    private String approvalNo;

    /**
     * approve_no
     */
    @ApiModelProperty(value = "")
    private String approveNo;

    /**
     * certificate_no
     */
    @ApiModelProperty(value = "证书编号")
    private String certificateNo;

    /**
     * check_health_no
     */
    @ApiModelProperty(value = "")
    private String checkHealthNo;

    /**
     * health_no
     */
    @ApiModelProperty(value = "")
    private String healthNo;

    /**
     * limit_date
     */
    @ApiModelProperty(value = "限制日期")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date limitDate;

    /**
     * produced
     */
    @ApiModelProperty(value = "")
    private String produced;

    /**
     * production_no
     */
    @ApiModelProperty(value = "")
    private String productionNo;

    /**
     * record_no
     */
    @ApiModelProperty(value = "")
    private String recordNo;

    /**
     * 规格
     */
    @ApiModelProperty(value = "规格")
    private String specification;

    /**
     * 氮
     */
    @ApiModelProperty(value = "氮")
    private String n;

    /**
     * 磷
     */
    @ApiModelProperty(value = "磷")
    private String p;

    /**
     * 钾
     */
    @ApiModelProperty(value = "钾")
    private String k;

    /**
     * 其他
     */
    @ApiModelProperty(value = "其他")
    private String qt;

    /**
     * 二维码
     */
    @ApiModelProperty(value = "二维码")
    private String qrCode;

    /**
     * 氮磷钾
     */
    @ApiModelProperty(value = "氮磷钾")
    private String npk;

    /**
     * 氮磷
     */
    @ApiModelProperty(value = "氮磷")
    private String np;

    /**
     * 氮钾
     */
    @ApiModelProperty(value = "氮钾")
    private String nk;

    /**
     * 磷钾
     */
    @ApiModelProperty(value = "磷钾")
    private String pk;

    /**
     * 总有效成分单位
     */
    @ApiModelProperty(value = "有效成分单位")
    private String yxcfUnit;

    /**
     * 制剂种类
     */
    @ApiModelProperty(value = "制剂种类")
    private String zjzl;

    /**
     * is_other
     */
    @ApiModelProperty(value = "本平台添加的 0-否 1-是")
    private String isOther;

    /**
     * 修改信息
     */
    @ApiModelProperty(value = "修改信息")
    private String remark;

    /**
     * 文件
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String file;

    private List<TbFile> fileList;

    /**
     * 有效成分
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String yxcfJa;

    private List<TbToolYxcf> yxcfList;
}
