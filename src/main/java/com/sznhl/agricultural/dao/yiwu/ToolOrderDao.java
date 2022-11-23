package com.sznhl.agricultural.dao.yiwu;

import com.sznhl.agricultural.entity.TbToolOrder;
import com.sznhl.agricultural.vo.ToolOrderVo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ToolOrderDao {
	@Results({
			@Result(column = "id", property = "id"),
			@Result(column = "id", property = "shoppingCarList",
					many = @Many(select = "com.sznhl.agricultural.dao.yiwu.ShoppingCarDao.selectByOrderId"))
	})
	@Select("<script>" +
			"SELECT tto.*, te.name AS enterpriseName, tes.enterprise_link_people, tes.enterprise_link_mobile, " +
			"loi.link_people, loi.link_mobile, loi.address " +
			"FROM tb_tool_order tto " +
			"INNER JOIN tb_enterprise te ON te.id = tto.tool_enterprise_Id " +
			"LEFT JOIN tb_link_order_info loi ON loi.id=tto.plant_enterprise_Id AND tto.type=2 " +
			"LEFT JOIN tb_enterprise tes ON tes.id=tto.plant_enterprise_Id AND tto.type=1 " +
			"LEFT JOIN tb_res_order_car troc ON troc.order_id = tto.id " +
			"LEFT JOIN tb_shopping_car tsc ON tsc.id = troc.car_id " +
			"LEFT JOIN tb_tool tt ON tt.id = tsc.tool_id " +
			"WHERE tto.del_flag = 0 AND tto.input_time BETWEEN #{startTime} AND #{endTime} " +
			"<if test=\"enterpriseName!='' and enterpriseName!=null\">AND te.name LIKE CONCAT('%',#{enterpriseName},'%')</if>" +
			"<if test=\"name!='' and name!=null\">AND (tes.enterprise_link_people LIKE CONCAT('%',#{name},'%') OR loi.name LIKE CONCAT('%',#{name},'%'))</if>" +
			"<if test=\"orderNumber!='' and orderNumber!=null\">AND tto.order_number LIKE CONCAT('%',#{orderNumber},'%')</if>" +
			"<if test=\"enterpriseId!=null\">AND (tto.tool_enterprise_Id = #{enterpriseId} OR tes.id = #{enterpriseId})</if>" +
			"<if test=\"toolName!='' and toolName!=null\">AND tt.name LIKE CONCAT('%',#{toolName},'%') </if>" +
			"GROUP BY tto.id</script>")
	List<ToolOrderVo> select(@Param("enterpriseName") String enterpriseName, @Param("name") String name,
							 @Param("orderNumber") String orderNumber, @Param("startTime") String startTime,
							 @Param("endTime") String endTime, @Param("enterpriseId") Integer enterpriseId,
							 @Param("toolName") String toolName);

	@Select("SELECT * FROM tb_tool_order WHERE del_flag = 0 AND order_number = #{orderNumber} LIMIT 1")
	TbToolOrder selectByOrderNumber(@Param("orderNumber") String orderNumber);

	@Insert("INSERT INTO tb_tool_order(`tool_enterprise_id`, `plant_enterprise_id`, `add_people`, `order_number`, `price`, " +
			"`status`, `input_time`, `time_audit`, `time_pay`, `time_send`, `time_complete`, " +
			"`check_`, `type`, `del_flag`, `is_sync`, `pic`, `order_sync`, `order_type`) " +
			"VALUES (#{toolOrder.toolEnterpriseId}, #{toolOrder.plantEnterpriseId}, #{toolOrder.addPeople}, " +
			"#{toolOrder.orderNumber}, #{toolOrder.price}, 4, NOW(), NOW(), NOW(), NOW(), NOW(), 0, 2, 0, 0, " +
			"#{toolOrder.pic}, 0, #{toolOrder.orderType});")
	@Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
	int insert(@Param("toolOrder") TbToolOrder toolOrder);

}
