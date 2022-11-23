package com.sznhl.agricultural.dao.common;

import com.sznhl.agricultural.entity.TbResToolFile;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Repository
public interface ResToolFilePoolDao {
    @Insert("INSERT INTO tb_res_tool_file_pool(`tool_id`, `file_id`, `del_flag`) " +
            "VALUES (#{resToolFile.toolId}, #{resToolFile.fileId}, 0)")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insert(@Param("resToolFile") TbResToolFile resToolFile);

    @Update("UPDATE tb_res_tool_file_pool SET del_flag = 1 WHERE tool_id = #{toolId}")
    int delete(@Param("toolId") Integer toolId);

}
