package com.sznhl.agricultural.dao.yiwu;

import com.sznhl.agricultural.entity.TbToolRecord;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ToolRecordDao {
    @Insert("INSERT INTO tb_tool_record(`enterprise_id`, `supplier_name`, `use_name`, " +
            "`use_time`, `tool_id`, `record_type`, `all_number`, `number`, `input_time`, " +
            "`del_flag`, `out_name`, `out_mobile`, `is_sync`, `price`, " +
            "`from_enterprise_id`, `out_enterprise_id`, `is_other`) " +
            "VALUES (#{toolRecord.enterpriseId}, #{toolRecord.supplierName}, " +
            "#{toolRecord.useName}, NOW(), #{toolRecord.toolId}, #{toolRecord.recordType}, " +
            "#{toolRecord.allNumber}, #{toolRecord.number}, NOW(), 0, " +
            "#{toolRecord.outName}, #{toolRecord.outMobile}, 0, #{toolRecord.price}, " +
            "#{toolRecord.fromEnterpriseId}, #{toolRecord.outEnterpriseId}, " +
            "#{toolRecord.isOther})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insert(@Param("toolRecord") TbToolRecord toolRecord);
}
