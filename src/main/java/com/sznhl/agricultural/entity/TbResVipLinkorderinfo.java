package com.sznhl.agricultural.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class TbResVipLinkorderinfo implements Serializable{
    /**
     * id
     */
    private Integer id;

    /**
     * vipid
     */
    private Integer vipId;

    /**
     * 订单联系人id
     */
    private Integer linkOrderInfoId;

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
