package com.sznhl.agricultural.dao.common;

import com.sznhl.agricultural.entity.TbFile;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FilePoolDao {
    @Insert("INSERT INTO tb_file_pool(`file_name`, `file_size`, `file_type`, `file_url`, `file_url2`, `file_url3`) " +
            "VALUES (#{file.fileName}, #{file.fileSize}, #{file.fileType}, #{file.fileUrl}, " +
            "#{file.fileUrl2}, #{file.fileUrl3})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insert(@Param("file") TbFile file);

    @Select("SELECT f.* FROM tb_file_pool f INNER JOIN tb_res_tool_file_pool tf ON tf.file_id = f.id AND tf.del_flag = 0 " +
            "WHERE tf.tool_id = #{toolId}")
    List<TbFile> selectByToolId(@Param("toolId") Integer toolId);
}
