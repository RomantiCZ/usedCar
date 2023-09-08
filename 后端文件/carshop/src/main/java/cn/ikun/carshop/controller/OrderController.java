package cn.ikun.carshop.controller;

import cn.ikun.carshop.pojo.Orders;
import cn.ikun.carshop.service.OrdersService;
import cn.ikun.carshop.utils.MessageUtil;
import com.alibaba.druid.sql.visitor.functions.If;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrdersService ordersService;


    @PostMapping("/addOrder/rental")
    public MessageUtil<String> addOrder(@RequestBody Orders orders) {
        var res = ordersService.orderRentalAdd(orders);
        if (res > 0) {
            return MessageUtil.success("订单预定成功");
        } else {
            return MessageUtil.error("订单预定失败");
        }
    }


    @PostMapping("/addOrder/sale")
    public MessageUtil<String> addOrderBySale(@RequestBody Orders orders) {
        var res = ordersService.orderSaleAdd(orders);
        if (res > 0) {
            return MessageUtil.success("订单预定成功");
        } else {
            return MessageUtil.error("订单预定失败");
        }
    }

    @GetMapping("/getAll")
    public MessageUtil<List<Orders>> getAllOrder() {
        var ordersList = ordersService.orderList();
        if (ordersList.size() > 0) {
            return MessageUtil.success(ordersList);
        }else {
            return MessageUtil.error("查询失败");
        }
    }

    @PostMapping("/handleOrder")
    public MessageUtil<String> handleOrder(@RequestParam("orderId")Long orderId,@RequestParam("carId")Long carId){
        var res = ordersService.handleOrderandRental(orderId, carId);
        if (res>0){
            return MessageUtil.success("订单处理完成");
        }else{
            return MessageUtil.error("订单处理失败");
        }
    }

    @GetMapping("/userGet/{id}")
    public MessageUtil<List<Orders>> getUserOrder(@PathVariable("id")Long id){
        var ordersList = ordersService.getOrderByUserId(id);

        if (Optional.ofNullable(ordersList).isPresent()){
            return MessageUtil.success(ordersList);
        }
        else{
            return MessageUtil.error("该用户没有订单");
        }
    }
}
