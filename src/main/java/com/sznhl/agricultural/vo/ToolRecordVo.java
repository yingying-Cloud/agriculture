package com.sznhl.agricultural.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class ToolRecordVo implements Serializable{

    private String userId;

    private String apiKey;

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
     * 农资id
     */
    private Integer toolId;

    /**
     * 数量
     */
    private String number;

    /**
     * 出库方向
     */
    private String outName;

    /**
     * 联系电话
     */
    private String outMobile;

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

    private List<String> codeList;

}
