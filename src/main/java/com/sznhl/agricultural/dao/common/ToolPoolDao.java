package com.sznhl.agricultural.dao.common;

import com.sznhl.agricultural.entity.TbToolPool;
import com.sznhl.agricultural.vo.ToolVo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ToolPoolDao {
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "id", property = "fileList",
                    many = @Many(select = "com.sznhl.agricultural.dao.common.FilePoolDao.selectByToolId")),
            @Result(column = "id", property = "yxcfList",
                    many = @Many(select = "com.sznhl.agricultural.dao.common.ToolYxcfPoolDao.selectByToolId"))
    })
    @Select("<script>" +
            "SELECT * FROM tb_tool_pool WHERE del_flag = 0" +
            "<if test=\"name!='' and name!=null\">AND name LIKE CONCAT('%',#{name},'%')</if>" +
            "<if test=\"specification!='' and specification!=null\">AND specification LIKE CONCAT('%',#{specification},'%')</if>" +
            "<if test=\"registration!='' and registration!=null\">AND registration_certificate_number LIKE CONCAT('%',#{registration},'%')</if>" +
            "<if test=\"productionUnits!='' and productionUnits!=null\">AND production_units LIKE CONCAT('%',#{productionUnits},'%')</if>" +
            "</script>")
    List<ToolVo> selectAllTool(@Param("name") String name, @Param("specification") String specification,
                               @Param("registration") String registrationCertificateNumber,
                               @Param("productionUnits") String productionUnits);

    @Select("SELECT * FROM tb_tool_pool WHERE id = #{id}")
    TbToolPool selectById(@Param("id") Integer id);

    @Insert("INSERT INTO tb_tool_pool(`enterprise_id`, `supplier_name`, `name`, `model`, `unit`, " +
            "`price`, `number`, `describe_`, `type`, `production_units`, `registration_certificate_number`, " +
            "`explicit_ingredients`, `effective_ingredients`, `implementation_standards`, `dosage_forms`, " +
            "`product_attributes`, `toxicity`, `quality_grade`, `uniform_price`, `code`, `del_flag`, " +
            "`wholesale_price`, `dnm`, `is_sync`, `sync_number`, `hfzc`, `approval_end_date`, `approval_no`, " +
            "`approve_no`, `certificate_no`, `check_health_no`, `health_no`, `limit_date`, `produced`, " +
            "`production_no`, `record_no`, `specification`, `N`, `P`, `K`, `qt`, `qr_code`, `NPK`, `NP`, " +
            "`NK`, `PK`, `yxcf_unit`, `zjzl`, `is_other`, `remark`) VALUES (#{tool.enterpriseId}, #{tool.supplierName}, " +
            "#{tool.name}, #{tool.model}, #{tool.unit}, #{tool.price}, #{tool.number}, #{tool.describe}, " +
            "#{tool.type}, #{tool.productionUnits}, #{tool.registrationCertificateNumber}, #{tool.explicitIngredients}, " +
            "#{tool.effectiveIngredients}, #{tool.implementationStandards}, #{tool.dosageForms}, " +
            "#{tool.productAttributes}, #{tool.toxicity}, #{tool.qualityGrade}, #{tool.uniformPrice}, #{tool.code}, " +
            "0, #{tool.wholesalePrice}, #{tool.dnm}, 0, #{tool.syncNumber}, #{tool.hfzc}, " +
            "#{tool.approvalEndDate}, #{tool.approvalNo}, #{tool.approveNo}, #{tool.certificateNo}, " +
            "#{tool.checkHealthNo}, #{tool.healthNo}, #{tool.limitDate}, #{tool.produced}, #{tool.productionNo}, " +
            "#{tool.recordNo}, #{tool.specification}, #{tool.n}, #{tool.p}, #{tool.k}, #{tool.qt}, #{tool.qrCode}, " +
            "#{tool.npk}, #{tool.np}, #{tool.nk}, #{tool.pk}, #{tool.yxcfUnit}, #{tool.zjzl}, #{tool.isOther}, #{tool.remark})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insert(@Param("tool") TbToolPool tool);

    @Update("UPDATE tb_tool_pool SET " +
            "`enterprise_id`=#{tool.enterpriseId}, " +
            "`supplier_name`=#{tool.supplierName}, " +
            "`name`=#{tool.name}, " +
            "`model`=#{tool.model}, " +
            "`unit`=#{tool.unit}, " +
            "`price`=#{tool.price}, " +
            "`number`=#{tool.number}, " +
            "`describe_`=#{tool.describe}, " +
            "`type`=#{tool.type}, " +
            "`production_units`=#{tool.productionUnits}, " +
            "`registration_certificate_number`=#{tool.registrationCertificateNumber}, " +
            "`explicit_ingredients`=#{tool.explicitIngredients}, " +
            "`effective_ingredients`=#{tool.effectiveIngredients}, " +
            "`implementation_standards`=#{tool.implementationStandards}, " +
            "`dosage_forms`=#{tool.dosageForms}, " +
            "`product_attributes`=#{tool.productAttributes}, " +
            "`toxicity`=#{tool.toxicity}, " +
            "`quality_grade`=#{tool.qualityGrade}, " +
            "`uniform_price`=#{tool.uniformPrice}, " +
            "`code`=#{tool.code}, " +
            "`wholesale_price`=#{tool.wholesalePrice}, " +
            "`dnm`=#{tool.dnm}, " +
            "`is_sync`=#{tool.isSync}, " +
            "`sync_number`=#{tool.syncNumber}, " +
            "`hfzc`=#{tool.hfzc}, " +
            "`approval_end_date`=#{tool.approvalEndDate}, " +
            "`approval_no`=#{tool.approvalNo}, " +
            "`approve_no`=#{tool.approveNo}, " +
            "`certificate_no`=#{tool.certificateNo}, " +
            "`check_health_no`=#{tool.checkHealthNo}, " +
            "`health_no`=#{tool.healthNo}, " +
            "`limit_date`=#{tool.limitDate}, " +
            "`produced`=#{tool.produced}, " +
            "`production_no`=#{tool.productionNo}, " +
            "`record_no`=#{tool.recordNo}, " +
            "`specification`=#{tool.specification}, " +
            "`N`=#{tool.n}, " +
            "`P`=#{tool.p}, " +
            "`K`=#{tool.k}, " +
            "`qt`=#{tool.qt}, " +
            "`qr_code`=#{tool.qrCode}, " +
            "`NPK`=#{tool.npk}, " +
            "`NP`=#{tool.np}, " +
            "`NK`=#{tool.nk}, " +
            "`PK`=#{tool.pk}, " +
            "`yxcf_unit`=#{tool.yxcfUnit}, " +
            "`zjzl`=#{tool.zjzl}, " +
            "`is_other`=#{tool.isOther}, " +
            "`remark`=#{tool.remark} " +
            "WHERE id = #{tool.id}"
    )
    int update(@Param("tool") TbToolPool tool);
}
