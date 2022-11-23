package com.sznhl.agricultural.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class TbLog implements Serializable{
    /**
     * id
     */
    private Integer id;

    /**
     * uid
     */
    private Integer uid;

    /**
     * name
     */
    private String name;

    /**
     * input_time
     */
    private Date inputTime;

}
