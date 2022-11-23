package com.sznhl.agricultural.dao.yiwu;

import com.sznhl.agricultural.dto.ShoppingCarDto;
import com.sznhl.agricultural.entity.TbShoppingCar;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShoppingCarDao {

    @Select("SELECT sc.*, tl.name AS toolName, tl.production_units, " +
            "IFNULL((SELECT f.file_url FROM tb_file f INNER JOIN tb_res_tool_file rtf ON f.id = rtf.file_id WHERE rtf.tool_id = tl.id AND f.file_type = 1 LIMIT 1),'') AS fileUrl " +
            "FROM tb_shopping_car sc INNER JOIN tb_tool tl ON tl.id = sc.tool_id " +
            "WHERE sc.del_Flag = 0 " +
            "AND sc.id in (SELECT car_id FROM tb_res_order_car WHERE del_flag = 0 AND order_id = #{orderId})")
    List<ShoppingCarDto> selectByOrderId(@Param("orderId") Integer orderId);

    @Insert("INSERT INTO tb_shopping_car(`enterprise_id`, `tool_id`, `num`, `is_pay`, `del_flag`, `status`, " +
            "`input_time`, `price`, `original_price`, `uniform_price`) " +
            "VALUES (#{car.enterpriseId}, #{car.toolId}, #{car.num}, 0, 0, 2, NOW(), #{car.price}, #{car.originalPrice}, #{car.uniformPrice});")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insert(@Param("car") TbShoppingCar car);
}
