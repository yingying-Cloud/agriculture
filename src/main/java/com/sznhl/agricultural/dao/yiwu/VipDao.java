package com.sznhl.agricultural.dao.yiwu;

import com.sznhl.agricultural.entity.TbResVipLinkorderinfo;
import com.sznhl.agricultural.entity.TbResVipToolPrice;
import com.sznhl.agricultural.entity.TbVip;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VipDao {
    @Insert("INSERT INTO tb_vip(`enterprise_id`, `name`, `remark`, `del_flag`, `create_time`) " +
            "VALUES (#{vip.enterpriseId}, #{vip.name}, #{vip.remark}, 0, NOW())")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insert(@Param("vip") TbVip vip);

    @Update("UPDATE tb_vip SET `name`=#{vip.name}, `remark`=#{vip.remark}, update_time = NOW() " +
            "WHERE id = #{vip.id}")
    int update(@Param("vip") TbVip vip);

    @Select("SELECT * FROM tb_vip WHERE del_flag = 0 AND enterprise_id = #{enterpriseId}")
    List<TbVip> selectByEnterpriseId(@Param("enterpriseId") Integer enterpriseId);

    @Update("UPDATE tb_vip SET del_flag = 1, update_time = NOW() WHERE id = #{id}")
    int delete(@Param("id") Integer id);

    @Select("SELECT * FROM tb_res_vip_linkorderinfo WHERE del_flag = 0 AND vip_id  = #{vipId} LIMIT 1")
    TbResVipLinkorderinfo checkIsLinkorderinfoUse(@Param("vipId") Integer vipId);

    @Select("SELECT * FROM tb_res_vip_tool_price WHERE del_flag = 0 AND vip_id  = #{vipId} LIMIT 1")
    TbResVipToolPrice checkIsToolUse(@Param("vipId") Integer vipId);


    @Select("SELECT vl.* FROM tb_res_vip_linkorderinfo vl INNER JOIN tb_vip v ON v.id = vl.vip_id " +
            "WHERE vl.del_flag = 0 AND vl.link_order_info_id = #{linkOrderInfoId} AND v.enterprise_id = " +
            "(SELECT enterprise_id FROM tb_vip WHERE id = #{vipId}) LIMIT 1")
    TbResVipLinkorderinfo checkIsBind(@Param("vipId") Integer vipId, @Param("linkOrderInfoId") Integer linkOrderInfoId);

    @Insert("INSERT INTO tb_res_vip_linkorderinfo(`vip_id`, `link_order_info_id`, `del_flag`, `create_time`) " +
            "VALUES (#{vipLinkorderinfo.vipId}, #{vipLinkorderinfo.linkOrderInfoId}, 0, NOW())")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int bind(@Param("vipLinkorderinfo") TbResVipLinkorderinfo vipLinkorderinfo);

    @Update("UPDATE tb_res_vip_linkorderinfo SET del_flag = 1, update_time = NOW() " +
            "WHERE vip_id = #{vipId} AND link_order_info_id = #{linkOrderInfoId}")
    int unbind(@Param("vipId") Integer vipId, @Param("linkOrderInfoId") Integer linkOrderInfoId);

    @Select("SELECT v.* FROM tb_res_vip_linkorderinfo vl INNER JOIN tb_vip v ON v.id = vl.vip_id " +
            "WHERE vl.del_flag = 0 AND vl.link_order_info_id = #{linkOrderInfoId} AND v.enterprise_id = #{enterpriseId} LIMIT 1")
    TbVip selectByEnterpriseIdAndLinkOrderInfoId(@Param("enterpriseId") Integer enterpriseId, @Param("linkOrderInfoId") Integer linkOrderInfoId);
}
