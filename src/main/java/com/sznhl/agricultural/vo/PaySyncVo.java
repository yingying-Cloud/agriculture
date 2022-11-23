package com.sznhl.agricultural.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class PaySyncVo implements Serializable{

    private String userId;

    private String apiKey;

    private String jsonData;

}
