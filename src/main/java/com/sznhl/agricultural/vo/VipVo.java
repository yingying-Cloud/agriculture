package com.sznhl.agricultural.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;

@Data
public class VipVo implements Serializable{

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String userId;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String apiKey;

    /**
     * id
     */
    private Integer id;

    /**
     * enterprise_id
     */
    private Integer enterpriseId;

    /**
     * 名称
     */
    private String name;

    /**
     * 备注
     */
    private String remark;

}
