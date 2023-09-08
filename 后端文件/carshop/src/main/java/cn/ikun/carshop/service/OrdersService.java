package cn.ikun.carshop.service;

import cn.ikun.carshop.pojo.Orders;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface OrdersService extends IService<Orders> {

    /**
     * 租借订单增加
     * @param order
     * @return
     */
    int orderRentalAdd(Orders order);

    int orderSaleAdd(Orders order);

    /**
     * 获取所有订单
     * @return
     */
    List<Orders> orderList();

    /**
     * 处理订单
     * @param orderId
     * @param carId
     * @return
     */
    int handleOrderandRental(Long orderId,Long carId);

    /**
     * 根据用户id获取
     * @param id
     * @return
     */
    List<Orders> getOrderByUserId(Long id);
}
