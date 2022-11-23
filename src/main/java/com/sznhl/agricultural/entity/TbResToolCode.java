package com.sznhl.agricultural.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class TbResToolCode implements Serializable{
    /**
     * id
     */
    private Integer id;

    /**
     * 农资id
     */
    private Integer toolId;

    /**
     * 二维码
     */
    private String code;

    /**
     * 状态0入库1出库
     */
    private Integer state;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

}
