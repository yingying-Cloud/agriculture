package com.sznhl.agricultural.dao.yiwu;

import com.sznhl.agricultural.entity.TbLinkOrderInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface LinkOrderInfoDao {
    @Select("SELECT * FROM tb_link_order_info WHERE legal_person_idcard = #{idcard} AND del_flag=0 AND type = 2 LIMIT 1")
    TbLinkOrderInfo selectByIdcard(@Param("idcard") String idcard);
}
