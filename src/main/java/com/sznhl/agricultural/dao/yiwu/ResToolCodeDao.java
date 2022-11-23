package com.sznhl.agricultural.dao.yiwu;

import com.sznhl.agricultural.entity.TbResToolCode;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ResToolCodeDao {
    @Insert("<script>" +
            "INSERT INTO tb_res_tool_code(`tool_id`, `code`, `state`, `create_time`) VALUES " +
            "<foreach collection='list' item='item' index='index' separator=','>" +
            "(#{item.toolId}, #{item.code}, 0, NOW())" +
            "</foreach></script>")
    int insert(@Param("list") List<TbResToolCode> list);

    @Update("<script>" +
            "UPDATE tb_res_tool_code SET state = 2, update_time = NOW() WHERE state = 0 AND code IN " +
            "<foreach collection='codeList' item='code' open='(' separator=',' close=')'>#{code}</foreach>" +
            "</script>")
    int delete(@Param("codeList") List<String> codeList);

    @Update("<script>" +
            "UPDATE tb_res_tool_code SET state = 1, update_time = NOW() WHERE state = 0 AND tool_id = #{toolId} AND code IN " +
            "<foreach collection='codeList' item='code' open='(' separator=',' close=')'>#{code}</foreach>" +
            "</script>")
    int update(@Param("toolId") Integer toolId, @Param("codeList") List<String> codeList);

    @Select("<script>" +
            "SELECT tool_id AS toolId, GROUP_CONCAT(code) AS codeList FROM tb_res_tool_code WHERE state = 0 " +
            "<if test='toolId!=null'>AND tool_id = #{toolId}</if> " +
            "GROUP BY tool_id" +
            "</script>")
    List<Map<String, String>> selectByToolId(@Param("toolId") Integer toolId);

}
