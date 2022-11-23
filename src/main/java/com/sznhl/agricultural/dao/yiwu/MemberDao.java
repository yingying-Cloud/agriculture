package com.sznhl.agricultural.dao.yiwu;

import com.sznhl.agricultural.vo.MemberVo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberDao {
	@Select("<script>" +
			"SELECT tloi.id, tloi.name, tloi.sex, tloi.nation, tloi.legal_person_idcard, tloi.address, tloi.idcard_pic FROM tb_link_order_info tloi " +
			"INNER JOIN tb_res_enterprise_linkorderinfo trel on tloi.id = trel.link_order_info_id " +
			"WHERE tloi.del_flag=0 AND trel.enterprise_id IN " +
			"<foreach collection='enterpriseIdList' item='id' open='(' separator=',' close=')'>#{id}</foreach>" +
			"<if test=\"key!='' and key!=null\">AND (tloi.name LIKE CONCAT('%',#{key},'%') OR tloi.legal_person_idcard LIKE CONCAT('%',#{key},'%'))</if>" +
			"</script>")
	List<MemberVo> selectByKey(@Param("enterpriseIdList") List<Integer> enterpriseIdList, @Param("key") String key);
}
