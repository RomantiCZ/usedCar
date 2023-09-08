package cn.ikun.carshop.mapper;

import cn.ikun.carshop.pojo.Orders;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


@Mapper
public interface OrdersMapper extends BaseMapper<Orders> {

    /**
     * 获取全部订单信息
     * @return
     */
    List<Orders> selectAllOrder();

    /**
     * 根据用户id修改信息
     * @param id
     * @return
     */
    List<Orders> selectOrderById(@Param("id")Long id);
}
