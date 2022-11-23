package com.sznhl.agricultural.dao.yiwu;

import com.sznhl.agricultural.entity.TbFile;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FileDao {
    @Insert("INSERT INTO tb_file(`file_name`, `file_size`, `file_type`, `file_url`, `file_url2`, `file_url3`) " +
            "VALUES (#{file.fileName}, #{file.fileSize}, #{file.fileType}, #{file.fileUrl}, " +
            "#{file.fileUrl2}, #{file.fileUrl3})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insert(@Param("file") TbFile file);
}
