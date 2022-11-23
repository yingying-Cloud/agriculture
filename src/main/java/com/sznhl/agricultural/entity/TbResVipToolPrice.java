package com.sznhl.agricultural.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class TbResVipToolPrice implements Serializable{
    /**
     * id
     */
    private Integer id;

    /**
     * vipid
     */
    private Integer vipId;

    /**
     * 农资id
     */
    private Integer toolId;

    /**
     * price
     */
    private String price;

    /**
     * del_flag
     */
    private Integer delFlag;

    /**
     * create_time
     */
    private Date createTime;

    /**
     * update_time
     */
    private Date updateTime;

}
