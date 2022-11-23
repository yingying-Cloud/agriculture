package com.sznhl.agricultural.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class ToolCodeVo implements Serializable{

    private String userId;

    private String apiKey;

    private Integer toolId;

    private List<String> codeList;

}
