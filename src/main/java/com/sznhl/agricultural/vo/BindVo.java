package com.sznhl.agricultural.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class BindVo implements Serializable{

    private String userId;

    private String apiKey;

    private Integer vipId;

    private Integer linkOrderInfoId;

}
