package com.sznhl.agricultural.dao.yiwu;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoginDao {
    @Select("SELECT enterprise_id FROM tb_res_user_enterprise WHERE del_flag = 0 AND user_tab_id = " +
            "(SELECT id FROM tb_user WHERE del_flag = 0 AND user_id = #{userId} AND api_key = #{apiKey} LIMIT 1)")
    List<Integer> selectByUserIdAndApiKey(@Param("userId") String userId, @Param("apiKey") String apiKey);
}
