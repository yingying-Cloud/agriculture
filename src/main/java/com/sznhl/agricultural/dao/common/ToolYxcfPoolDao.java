package com.sznhl.agricultural.dao.common;

import com.sznhl.agricultural.entity.TbToolYxcf;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ToolYxcfPoolDao {
    @Insert("INSERT INTO tb_tool_yxcf_pool(`tool_id`, `effective_ingredients_name`, `effective_ingredients_value`, `unit`) " +
            "VALUES (#{toolYxcf.toolId}, #{toolYxcf.effectiveIngredientsName}, #{toolYxcf.effectiveIngredientsValue}, " +
            "#{toolYxcf.unit})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insert(@Param("toolYxcf") TbToolYxcf toolYxcf);

    @Select("SELECT * FROM tb_tool_yxcf_pool WHERE tool_id = #{toolId}")
    List<TbToolYxcf> selectByToolId(@Param("toolId") Integer toolId);

    @Delete("DELETE FROM tb_tool_yxcf_pool WHERE tool_id = #{toolId}")
    int delete(@Param("toolId") Integer toolId);
}
