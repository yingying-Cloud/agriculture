package com.sznhl.agricultural.dao.yiwu;

import com.sznhl.agricultural.entity.TbResOrderCar;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ResOrderCarDao {
    @Insert("INSERT INTO tb_res_order_car(`car_id`, `order_id`, `del_flag`) " +
            "VALUES (#{resOrderCar.carId}, #{resOrderCar.orderId}, 0)")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insert(@Param("resOrderCar") TbResOrderCar resOrderCar);
}
