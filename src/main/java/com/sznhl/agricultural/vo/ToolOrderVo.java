package com.sznhl.agricultural.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.sznhl.agricultural.dto.ShoppingCarDto;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class ToolOrderVo implements Serializable {
    /**
     * id
     */
    @ApiModelProperty(value = "订单Id")
    private Integer id;

    /**
     * 农资企业id
     */
    @ApiModelProperty(value = "农资店id")
    private Integer toolEnterpriseId;

    @ApiModelProperty(value = "农资店名称")
    private String enterpriseName;

    /**
     * 种植企业id
     */
    @ApiModelProperty(value = "种植企业id")
    private Integer plantEnterpriseId;

    @ApiModelProperty(value = "种植企业联系人")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String enterpriseLinkPeople;
    @ApiModelProperty(value = "种植企业联系电话")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String enterpriseLinkMobile;

    @ApiModelProperty(value = "会员姓名")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String linkPeople;
    @ApiModelProperty(value = "会员电话")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String linkMobile;
    @ApiModelProperty(value = "会员地址")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String address;

    /**
     * 添加人
     */
    @ApiModelProperty(value = "添加人")
    private String addPeople;

    /**
     * 订单号
     */
    @ApiModelProperty(value = "订单编号")
    private String orderNumber;

    /**
     * 价格
     */
    @ApiModelProperty(value = "订单总价")
    private String price;

    /**
     * 0-待审核 1-待支付 2-待发货 3-待确认 4-已完成-1已取消-99未通过
     */
    @ApiModelProperty(value = "订单状态")
    private Integer status;

    /**
     * 取消原因
     */
    @ApiModelProperty(value = "取消原因")
    private String cancelInfo;

    /**
     * rejected_info
     */
    @ApiModelProperty(value = "拒绝原因")
    private String rejectedInfo;

    /**
     * input_time
     */
    @ApiModelProperty(value = "下单时间")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date inputTime;

    /**
     * 审核时间
     */
    @ApiModelProperty(value = "审核时间")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date timeAudit;

    /**
     * 支付时间
     */
    @ApiModelProperty(value = "支付时间")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date timePay;

    /**
     * 发货时间
     */
    @ApiModelProperty(value = "发货时间")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date timeSend;

    /**
     * 完成时间
     */
    @ApiModelProperty(value = "完成时间")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date timeComplete;

    /**
     * 取消时间
     */
    @ApiModelProperty(value = "取消时间")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date timeCancel;

    /**
     * time_rejected
     */
    @ApiModelProperty(value = "拒绝时间")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date timeRejected;

    /**
     * 0-未核定 1-核定
     */
    @ApiModelProperty(value = "是否核定")
    private Integer check;

    /**
     * 补贴金额
     */
    @ApiModelProperty(value = "补贴金额")
    private String checkMoney;

    /**
     * 下单人 人脸图片
     */
    @ApiModelProperty(value = "会员照片")
    private String pic;

    @ApiModelProperty(value = "商品清单")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<ShoppingCarDto> shoppingCarList;

    /**
     * 0刷脸1手动选择
     */
    private Integer orderType;

}
