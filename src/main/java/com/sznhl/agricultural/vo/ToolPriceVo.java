package com.sznhl.agricultural.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class ToolPriceVo implements Serializable{

    private String userId;

    private String apiKey;

    private Integer toolId;

    private String price;

    private Integer vipId;

}
