package com.sznhl.agricultural.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class TbShoppingCar implements Serializable {
    /**
     * id
     */
    private Integer id;

    /**
     * 企业id
     */
    private Integer enterpriseId;

    /**
     * 农资id
     */
    private Integer toolId;

    /**
     * 数量
     */
    private String num;

    /**
     * 支付标识
     */
    private Integer isPay;

    /**
     * del_flag
     */
    private Integer delFlag;

    /**
     * 状态
     */
    private Integer status;

    /**
     * input_time
     */
    private Date inputTime;

    /**
     * price
     */
    private String price;

    /**
     * 原价
     */
    private String originalPrice;

    /**
     * 是否统一售价 1-是 2-否
     */
    private String uniformPrice;
}
