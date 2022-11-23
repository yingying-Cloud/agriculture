package com.sznhl.agricultural.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class TbToolYxcf implements Serializable{
    /**
     * id
     */
    @ApiModelProperty(value = "主键Id")
    private Integer id;

    /**
     * tb_tool的id
     */
    @ApiModelProperty(value = "农资id")
    private Integer toolId;

    /**
     * 有效成分名称
     */
    @ApiModelProperty(value = "有效成分名称")
    private String effectiveIngredientsName;

    /**
     * 有效成分值
     */
    @ApiModelProperty(value = "有效成分值")
    private String effectiveIngredientsValue;

    /**
     * 单位
     */
    @ApiModelProperty(value = "单位")
    private String unit;
}
