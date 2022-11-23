package com.sznhl.agricultural.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserVo implements Serializable {
    /**
     * id
     */
    private Integer id;

    /**
     * user_id
     */
    private String userId;

    /**
     * api_key
     */
    private String apiKey;

    /**
     * 电话
     */
    private String mobile;

    /**
     * 名字
     */
    private String name;

    private Integer roleId;

    private String roleName;

    private Integer enterpriseId;

    private String enterpriseName;

    private String baiduAifaceSn;

    private Integer machineVersion;

}
