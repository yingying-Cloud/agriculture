package com.sznhl.agricultural.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class TbRole implements Serializable {
    /**
     * id
     */
    private Integer id;

    /**
     * 权限名字
     */
    private String roleName;

    /**
     * input_time
     */
    private Date inputTime;

    /**
     * del_flag
     */
    private Integer delFlag;
}
