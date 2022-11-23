package com.sznhl.agricultural.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class TbFile implements Serializable{
    /**
     * id
     */
    @ApiModelProperty(value = "主键Id")
    private Integer id;

    /**
     * 名字
     */
    @ApiModelProperty(value = "名字")
    private String fileName;

    /**
     * 大小
     */
    @ApiModelProperty(value = "大小")
    private String fileSize;

    /**
     * 类型
     */
    @ApiModelProperty(value = "类型")
    private Integer fileType;

    /**
     * 路径
     */
    @ApiModelProperty(value = "路径")
    private String fileUrl;

    /**
     * file_url2
     */
    @ApiModelProperty(value = "路径")
    private String fileUrl2;

    /**
     * file_url3
     */
    @ApiModelProperty(value = "路径")
    private String fileUrl3;

}
