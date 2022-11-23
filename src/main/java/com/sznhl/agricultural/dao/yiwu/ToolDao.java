package com.sznhl.agricultural.dao.yiwu;

import com.sznhl.agricultural.entity.TbResVipToolPrice;
import com.sznhl.agricultural.entity.TbTool;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ToolDao {
    @Insert("INSERT INTO tb_tool(`enterprise_id`, `supplier_name`, `name`, `model`, `unit`, " +
            "`price`, `number`, `describe_`, `type`, `production_units`, `registration_certificate_number`, " +
            "`explicit_ingredients`, `effective_ingredients`, `implementation_standards`, `dosage_forms`, " +
            "`product_attributes`, `toxicity`, `quality_grade`, `uniform_price`, `code`, `del_flag`, " +
            "`wholesale_price`, `dnm`, `is_sync`, `sync_number`, `hfzc`, `approval_end_date`, `approval_no`, " +
            "`approve_no`, `certificate_no`, `check_health_no`, `health_no`, `limit_date`, `produced`, " +
            "`production_no`, `record_no`, `specification`, `N`, `P`, `K`, `qt`, `qr_code`, `NPK`, `NP`, " +
            "`NK`, `PK`, `yxcf_unit`, `zjzl`, `is_other`) VALUES (#{tool.enterpriseId}, #{tool.supplierName}, " +
            "#{tool.name}, #{tool.model}, #{tool.unit}, #{tool.price}, #{tool.number}, #{tool.describe}, " +
            "#{tool.type}, #{tool.productionUnits}, #{tool.registrationCertificateNumber}, #{tool.explicitIngredients}, " +
            "#{tool.effectiveIngredients}, #{tool.implementationStandards}, #{tool.dosageForms}, " +
            "#{tool.productAttributes}, #{tool.toxicity}, #{tool.qualityGrade}, #{tool.uniformPrice}, #{tool.code}, " +
            "0, #{tool.wholesalePrice}, #{tool.dnm}, #{tool.isSync}, #{tool.syncNumber}, #{tool.hfzc}, " +
            "#{tool.approvalEndDate}, #{tool.approvalNo}, #{tool.approveNo}, #{tool.certificateNo}, " +
            "#{tool.checkHealthNo}, #{tool.healthNo}, #{tool.limitDate}, #{tool.produced}, #{tool.productionNo}, " +
            "#{tool.recordNo}, #{tool.specification}, #{tool.n}, #{tool.p}, #{tool.k}, #{tool.qt}, #{tool.qrCode}, " +
            "#{tool.npk}, #{tool.np}, #{tool.nk}, #{tool.pk}, #{tool.yxcfUnit}, #{tool.zjzl}, #{tool.isOther})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insert(@Param("tool") TbTool tool);

    @Update("UPDATE tb_tool SET price = #{price} WHERE id = #{toolId}")
    int updatePrice(@Param("toolId") Integer toolId, @Param("price") String price);

    @Select("SELECT * FROM tb_tool WHERE id = #{id}")
    TbTool selectById(@Param("id") Integer id);

    @Update("UPDATE tb_tool SET number = #{number} WHERE id = #{id}")
    int updateNumber(@Param("id") Integer id, @Param("number") String number);


    @Select("SELECT * FROM tb_res_vip_tool_price WHERE tool_id = #{toolId} AND vip_id = #{vipId} AND del_flag = 0")
    TbResVipToolPrice checkIsExist(@Param("toolId") Integer toolId, @Param("vipId") Integer vipId);

    @Insert("INSERT INTO tb_res_vip_tool_price(`vip_id`, `tool_id`, `price`, `del_flag`, `create_time`) " +
            "VALUES (#{vipId}, #{toolId}, #{price}, 0, NOW())")
    int insertVipPrice(@Param("toolId") Integer toolId, @Param("vipId") Integer vipId, @Param("price") String price);

    @Update("UPDATE tb_res_vip_tool_price SET price = #{price}, update_time = NOW() WHERE tool_id = #{toolId} AND vip_id = #{vipId} ")
    int updateVipPrice(@Param("toolId") Integer toolId, @Param("vipId") Integer vipId, @Param("price") String price);

    @Select("<script>" +
            "SELECT * FROM tb_res_vip_tool_price WHERE tool_id IN " +
            "<foreach collection='toolIdList' item='toolId' open='(' separator=',' close=')'>#{toolId}</foreach>" +
            "</script>")
    List<TbResVipToolPrice> selectVipPrice(@Param("toolIdList") List<Integer> toolIdList);
}
