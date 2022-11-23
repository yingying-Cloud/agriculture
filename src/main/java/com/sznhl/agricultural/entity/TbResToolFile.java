package com.sznhl.agricultural.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class TbResToolFile implements Serializable{
    /**
     * id
     */
    private Integer id;

    /**
     * 农资id
     */
    private Integer toolId;

    /**
     * 文件id
     */
    private Integer fileId;

    /**
     * del_flag
     */
    private Integer delFlag;

}
