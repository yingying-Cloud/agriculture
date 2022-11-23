package com.sznhl.agricultural.vo;

import com.sznhl.agricultural.desensitization.SensitiveInfo;
import com.sznhl.agricultural.desensitization.SensitiveType;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class MemberVo implements Serializable {
    @ApiModelProperty(value = "主键Id")
    private Integer id;

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "性别")
    private String sex;

    @ApiModelProperty(value = "民族")
    private String nation;

    @ApiModelProperty(value = "身份证")
    @SensitiveInfo(SensitiveType.ID_CARD)
    private String legalPersonIdcard;

    @ApiModelProperty(value = "地址")
    private String address;

    @ApiModelProperty(value = "身份证照片")
    private String idcardPic;

}
