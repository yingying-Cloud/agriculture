package com.sznhl.agricultural.dao.yiwu;

import com.sznhl.agricultural.entity.TbRole;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleDao {
    @Select("SELECT tr.* FROM tb_role tr " +
            "INNER JOIN tb_res_user_role rur ON rur.role_id = tr.id AND rur.del_flag = 0 " +
            "WHERE tr.del_flag = 0 AND rur.user_tab_id = #{userId}")
    TbRole selectByUserId(@Param("userId") Integer userId);
}
