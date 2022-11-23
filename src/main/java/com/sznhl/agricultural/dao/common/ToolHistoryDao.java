package com.sznhl.agricultural.dao.common;

import com.sznhl.agricultural.entity.TbToolHistory;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ToolHistoryDao {
    @Insert("INSERT INTO tb_tool_history(`tool_id`, `enterprise_id`, `supplier_name`, `name`, `model`, `unit`, " +
            "`price`, `number`, `describe_`, `type`, `production_units`, `registration_certificate_number`, " +
            "`explicit_ingredients`, `effective_ingredients`, `implementation_standards`, `dosage_forms`, " +
            "`product_attributes`, `toxicity`, `quality_grade`, `uniform_price`, `code`, `del_flag`, " +
            "`wholesale_price`, `dnm`, `is_sync`, `sync_number`, `hfzc`, `approval_end_date`, `approval_no`, " +
            "`approve_no`, `certificate_no`, `check_health_no`, `health_no`, `limit_date`, `produced`, " +
            "`production_no`, `record_no`, `specification`, `N`, `P`, `K`, `qt`, `qr_code`, `NPK`, `NP`, " +
            "`NK`, `PK`, `yxcf_unit`, `zjzl`, `is_other`, `remark`) VALUES (#{tool.toolId}, #{tool.enterpriseId}, #{tool.supplierName}, " +
            "#{tool.name}, #{tool.model}, #{tool.unit}, #{tool.price}, #{tool.number}, #{tool.describe}, " +
            "#{tool.type}, #{tool.productionUnits}, #{tool.registrationCertificateNumber}, #{tool.explicitIngredients}, " +
            "#{tool.effectiveIngredients}, #{tool.implementationStandards}, #{tool.dosageForms}, " +
            "#{tool.productAttributes}, #{tool.toxicity}, #{tool.qualityGrade}, #{tool.uniformPrice}, #{tool.code}, " +
            "0, #{tool.wholesalePrice}, #{tool.dnm}, #{tool.isSync}, #{tool.syncNumber}, #{tool.hfzc}, " +
            "#{tool.approvalEndDate}, #{tool.approvalNo}, #{tool.approveNo}, #{tool.certificateNo}, " +
            "#{tool.checkHealthNo}, #{tool.healthNo}, #{tool.limitDate}, #{tool.produced}, #{tool.productionNo}, " +
            "#{tool.recordNo}, #{tool.specification}, #{tool.n}, #{tool.p}, #{tool.k}, #{tool.qt}, #{tool.qrCode}, " +
            "#{tool.npk}, #{tool.np}, #{tool.nk}, #{tool.pk}, #{tool.yxcfUnit}, #{tool.zjzl}, #{tool.isOther}, #{tool.remark})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insert(@Param("tool") TbToolHistory tool);

}
