package com.sznhl.agricultural.dao.yiwu;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SupplierDao {
	@Select("<script>" +
			"SELECT DISTINCT tr.supplier_name " +
			"FROM tb_tool_record tr " +
			"INNER JOIN tb_tool t on t.id = tr.tool_id " +
			"WHERE tr.supplier_name IS NOT NULL AND t.name = #{name} AND t.enterprise_id IN" +
			"<foreach collection='enterpriseIdList' item='id' open='(' separator=',' close=')'>#{id}</foreach>" +
			"</script>")
	List<String> selectByName(@Param("enterpriseIdList") List<Integer> enterpriseIdList, @Param("name") String name);

}
