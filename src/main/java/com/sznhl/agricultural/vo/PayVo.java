package com.sznhl.agricultural.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class PayVo implements Serializable{

    private String userId;

    private String apiKey;

    private String orderNumber;

    private String idcard;

    private Integer enterpriseId;

    private String price;

    private String pic;

    private String toolIds;

    private String accountNums;

    private String prices;

    private String finalRatioFees;

    private Integer orderType;

    private String codeList;
}
