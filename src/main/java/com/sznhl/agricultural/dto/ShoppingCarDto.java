package com.sznhl.agricultural.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class ShoppingCarDto implements Serializable {
    /**
     * id
     */
    @ApiModelProperty(value = "主键Id")
    private Integer id;

    /**
     * 农资id
     */
    @ApiModelProperty(value = "农资id")
    private Integer toolId;

    @ApiModelProperty(value = "农资名称")
    private String toolName;

    @ApiModelProperty(value = "生产单位")
    private String productionUnits;

    @ApiModelProperty(value = "农资图片")
    private String fileUrl;

    /**
     * 数量
     */
    @ApiModelProperty(value = "购买数量")
    private String num;

    /**
     * 支付标识
     */
    @ApiModelProperty(value = "支付标识")
    private Integer isPay;

    /**
     * 状态
     */
    @ApiModelProperty(value = "状态")
    private Integer status;

    /**
     * price
     */
    @ApiModelProperty(value = "购买价格")
    private String price;

    /**
     * 原价
     */
    @ApiModelProperty(value = "原价")
    private String originalPrice;

    /**
     * 是否统一售价 1-是 2-否
     */
    @ApiModelProperty(value = "是否统一售价")
    private String uniformPrice;
}
