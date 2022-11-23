package com.sznhl.agricultural.dao.yiwu;

import com.sznhl.agricultural.entity.TbUser;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao {
    @Select("SELECT * FROM tb_user WHERE del_flag = 0 AND mobile = #{mobile} AND password = #{password} LIMIT 1")
    TbUser selectByMobileAndPassword(@Param("mobile") String mobile, @Param("password") String password);

    @Select("SELECT * FROM tb_user WHERE del_flag = 0 AND mobile = #{mobile} AND password = #{password} AND cash_register_id = #{cashRegisterId} LIMIT 1")
    TbUser selectByMobileAndPasswordAndCashRegisterId(@Param("mobile") String mobile, @Param("password") String password, @Param("cashRegisterId") String cashRegisterId);

    @Select("SELECT * FROM tb_user WHERE del_flag = 0 AND user_id = #{userId} AND api_key = #{apiKey} LIMIT 1")
    TbUser selectByUserIdAndApiKey(@Param("userId") String userId, @Param("apiKey") String apiKey);

    @Update("UPDATE tb_user SET last_time = NOW() WHERE id = #{id}")
    int updateTime(@Param("id") Integer id);

    @Update("UPDATE tb_user SET cash_register_version = #{cashRegisterVersion} WHERE id = #{id}")
    int updateCashRegisterVersion(@Param("id") Integer id,@Param("cashRegisterVersion") String cashRegisterVersion);

    @Update("UPDATE tb_user SET cash_register_id = #{cashRegisterId} WHERE id = #{id}")
    int updateCashRegisterId(@Param("id") Integer id,@Param("cashRegisterId") String cashRegisterId);
}
