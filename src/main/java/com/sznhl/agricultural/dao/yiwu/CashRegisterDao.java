package com.sznhl.agricultural.dao.yiwu;

import com.sznhl.agricultural.entity.TbCashRegister;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Repository
public interface CashRegisterDao {
    @Select("SELECT * FROM tb_cash_register WHERE del_flag = 0 AND cash_register_id = #{cashRegisterId} LIMIT 1")
    TbCashRegister selectByCashRegisterId(@Param("cashRegisterId") String cashRegisterId);

    @Insert("INSERT INTO tb_cash_register(`cash_register_id`, `baidu_aiface_sn`, `machine_version`, `input_time`, `update_time`, `del_flag`) " +
            "VALUES (#{cashRegister.cashRegisterId}, #{cashRegister.baiduAifaceSn}, #{cashRegister.machineVersion}, NOW(), NOW(), 0)")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insert(@Param("cashRegister") TbCashRegister cashRegister);

    @Update("UPDATE tb_cash_register SET update_time = NOW() WHERE id = #{id}")
    int updateTime(@Param("id") Integer id);
}
