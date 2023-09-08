package cn.ikun.carshop.service.impl;


import cn.ikun.carshop.mapper.OrdersMapper;
import cn.ikun.carshop.pojo.CarsSale;
import cn.ikun.carshop.pojo.Email;
import cn.ikun.carshop.pojo.Orders;
import cn.ikun.carshop.pojo.User;
import cn.ikun.carshop.service.CarsRentalService;
import cn.ikun.carshop.service.CarsSaleService;
import cn.ikun.carshop.service.OrdersService;
import cn.ikun.carshop.utils.EmailUtils;
import cn.ikun.carshop.utils.JwtConfigUtils;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrdersServiceImpl extends ServiceImpl<OrdersMapper, Orders> implements OrdersService {

    @Autowired
    private OrdersMapper ordersMapper;

    @Autowired
    private CarsSaleService carsSaleService;

    @Autowired
    private CarsRentalService carsRentalService;


    @Autowired
    private EmailUtils emailUtils;

    @Autowired
    private JwtConfigUtils jwtConfigUtils;


    /**
     * 租借订单增加
     *
     * @param order
     * @return
     */
    @Transactional
    @Override
    public int orderRentalAdd(Orders order) {
        var user = jwtConfigUtils.parseUserToken(order.getUserToken());
        if (user != null) {
            var status = carsRentalService.underCarsRental(order.getVehicleId());
            if (status > 0) {
                Email email = new Email(user.getEmail(), user.getName(), order.getStartTime(), order.getEndTime());
                emailUtils.SendEmailByRental(email);

                var res = ordersMapper.insert(order);
                return res;
            } else {
                return 0;
            }
        }


        return 0;
    }

    /**
     * @param order
     * @return
     */
    @Transactional
    @Override
    public int orderSaleAdd(Orders order) {

        var user = jwtConfigUtils.parseUserToken(order.getUserToken());
        if (user != null) {
            var status = carsSaleService.saleStatus(order.getVehicleId());
            if (status > 0) {
                Email email = new Email(user.getEmail(), user.getName(), order.getStartTime(), order.getEndTime());
                emailUtils.SendEmailBySale(email);
                return ordersMapper.insert(order);
            } else {
                return 0;
            }
        } else {
            return 0;
        }


    }

    /**
     * 获取所有订单
     *
     * @return
     */
    @Override
    public List<Orders> orderList() {
        return ordersMapper.selectAllOrder();
    }

    /**
     * 处理订单
     *
     * @param orderId
     * @param carId
     * @return
     */
    @Transactional
    @Override
    public int handleOrderandRental(Long orderId, Long carId) {
        LambdaUpdateWrapper<Orders> wrapper = new LambdaUpdateWrapper<>();
        wrapper.set(Orders::getStatus,1)
                .eq(Orders::getOrderId,orderId);
        var res = ordersMapper.update(new Orders(), wrapper);
        if (res>0){
            var rental = carsRentalService.enableCarsRentalById(String.valueOf(carId));
            return res;
        }else{
            return 0;
        }

    }

    /**
     * 根据用户id获取
     *
     * @param id
     * @return
     */
    @Override
    public List<Orders> getOrderByUserId(Long id) {

        var ordersList = ordersMapper.selectOrderById(id);
        if (ordersList.size()>0){
            return ordersList;
        }else {
            return  null;
        }

    }
}
