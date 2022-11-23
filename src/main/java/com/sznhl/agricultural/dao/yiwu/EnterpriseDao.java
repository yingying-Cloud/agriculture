package com.sznhl.agricultural.dao.yiwu;

import com.sznhl.agricultural.entity.TbEnterprise;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface EnterpriseDao {
    @Select("SELECT te.* FROM tb_enterprise te " +
            "INNER JOIN tb_res_user_enterprise rue ON rue.enterprise_id = te.id AND rue.del_flag = 0 " +
            "WHERE te.del_flag = 0 AND rue.user_tab_id = #{userId}")
    TbEnterprise selectByUserId(@Param("userId") Integer userId);
}
