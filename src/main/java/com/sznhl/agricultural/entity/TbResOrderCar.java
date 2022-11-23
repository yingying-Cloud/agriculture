package com.sznhl.agricultural.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class TbResOrderCar implements Serializable{
    /**
     * id
     */
    private Integer id;

    /**
     * 购物车id
     */
    private Integer carId;

    /**
     * 订单id
     */
    private Integer orderId;

    /**
     * del_flag
     */
    private Integer delFlag;
}
