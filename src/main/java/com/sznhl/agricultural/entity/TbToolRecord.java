package com.sznhl.agricultural.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class TbToolRecord implements Serializable{
    /**
     * id
     */
    private Integer id;

    /**
     * 企业id
     */
    private Integer enterpriseId;

    /**
     * 供应商
     */
    private String supplierName;

    /**
     * 操作人
     */
    private String useName;

    /**
     * 操作时间
     */
    private Date useTime;

    /**
     * 农资id
     */
    private Integer toolId;

    /**
     * 类型1-入库 2-出库 3-下单
     */
    private Integer recordType;

    /**
     * 总数
     */
    private String allNumber;

    /**
     * 数量
     */
    private String number;

    /**
     * input_time
     */
    private Date inputTime;

    /**
     * del_flag
     */
    private Integer delFlag;

    /**
     * 出库方向
     */
    private String outName;

    /**
     * 联系电话
     */
    private String outMobile;

    /**
     * 0-未同步 1-已同步
     */
    private Integer isSync;

    /**
     * price
     */
    private String price;

    /**
     * record_type-1 入库来源 企业id 卖家r
     */
    private Integer fromEnterpriseId;

    /**
     * record_type-2 出库方向 企业id 买家
     */
    private Integer outEnterpriseId;

    /**
     * is_other
     */
    private String isOther;

}
