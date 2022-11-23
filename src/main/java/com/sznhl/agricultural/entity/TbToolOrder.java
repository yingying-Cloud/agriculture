package com.sznhl.agricultural.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class TbToolOrder implements Serializable {
    /**
     * id
     */
    private Integer id;

    /**
     * 农资企业id
     */
    private Integer toolEnterpriseId;

    /**
     * 种植企业id
     */
    private Integer plantEnterpriseId;

    /**
     * 添加人
     */
    private String addPeople;

    /**
     * 订单号
     */
    private String orderNumber;

    /**
     * 价格
     */
    private String price;

    /**
     * 0-待审核 1-待支付 2-待发货 3-待确认 4-已完成-1已取消-99未通过
     */
    private Integer status;

    /**
     * 取消原因
     */
    private String cancelInfo;

    /**
     * rejected_info
     */
    private String rejectedInfo;

    /**
     * input_time
     */
    private Date inputTime;

    /**
     * 审核时间
     */
    private Date timeAudit;

    /**
     * 支付时间
     */
    private Date timePay;

    /**
     * 发货时间
     */
    private Date timeSend;

    /**
     * 完成时间
     */
    private Date timeComplete;

    /**
     * 取消时间
     */
    private Date timeCancel;

    /**
     * time_rejected
     */
    private Date timeRejected;

    /**
     * 0-未核定 1-核定
     */
    private Integer check;

    /**
     * 补贴金额
     */
    private String checkMoney;

    /**
     * type
     */
    private Integer type;

    /**
     * del_flag
     */
    private Integer delFlag;

    /**
     * 0-未同步 1-已同步
     */
    private Integer isSync;

    /**
     * 下单人 人脸图片
     */
    private String pic;

    /**
     * 是否评价
     */
    private String isEvaluate;

    /**
     * 非本频台加入的订单 1-是
     */
    private String isOther;

    /**
     * 订单同步
     */
    private Integer orderSync;

    /**
     * 0刷脸1手动选择
     */
    private Integer orderType;
}
