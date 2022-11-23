package com.sznhl.agricultural.dao.common;

import com.sznhl.agricultural.entity.TbResToolFile;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ResToolFileHistoryDao {
    @Insert("INSERT INTO tb_res_tool_file_history(`tool_id`, `file_id`, `del_flag`) " +
            "VALUES (#{resToolFile.toolId}, #{resToolFile.fileId}, 0)")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insert(@Param("resToolFile") TbResToolFile resToolFile);

}
