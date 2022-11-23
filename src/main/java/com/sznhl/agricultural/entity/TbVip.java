package com.sznhl.agricultural.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class TbVip implements Serializable{
    /**
     * id
     */
    @ApiModelProperty(value = "主键Id")
    private Integer id;

    /**
     * enterprise_id
     */
    @ApiModelProperty(value = "农资店Id")
    private Integer enterpriseId;

    /**
     * 名称
     */
    @ApiModelProperty(value = "名称")
    private String name;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;

    /**
     * del_flag
     */
    @JsonIgnore
    private Integer delFlag;

    /**
     * create_time
     */
    @JsonIgnore
    private Date createTime;

    /**
     * update_time
     */
    @JsonIgnore
    private Date updateTime;
}
