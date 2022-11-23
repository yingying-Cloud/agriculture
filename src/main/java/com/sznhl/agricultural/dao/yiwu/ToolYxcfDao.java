package com.sznhl.agricultural.dao.yiwu;

import com.sznhl.agricultural.entity.TbToolYxcf;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ToolYxcfDao {
    @Insert("INSERT INTO tb_tool_yxcf(`tool_id`, `effective_ingredients_name`, `effective_ingredients_value`, `unit`) " +
            "VALUES (#{toolYxcf.toolId}, #{toolYxcf.effectiveIngredientsName}, #{toolYxcf.effectiveIngredientsValue}, " +
            "#{toolYxcf.unit})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insert(@Param("toolYxcf") TbToolYxcf toolYxcf);
}
