package com.sznhl.agricultural.dao.yiwu;

import com.sznhl.agricultural.entity.TbLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LogDao {
    @Insert("INSERT INTO tb_log(`uid`, `name`, `input_time`) " +
            "VALUES (#{log.uid}, #{log.name}, NOW())")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insert(@Param("log") TbLog log);
}
