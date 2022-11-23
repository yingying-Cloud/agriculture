package com.sznhl.agricultural.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class TbCashRegister implements Serializable{
    /**
     * id
     */
    private Integer id;

    /**
     * 机器唯一编码
     */
    private String cashRegisterId;

    /**
     * 百度人脸识别序列号
     */
    private String baiduAifaceSn;

    /**
     * 机器版本号
     */
    private Integer machineVersion;

    /**
     * input_time
     */
    private Date inputTime;

    /**
     * update_time
     */
    private Date updateTime;

    /**
     * del_flag
     */
    private Integer delFlag;

}
