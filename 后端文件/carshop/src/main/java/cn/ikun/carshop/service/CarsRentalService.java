package cn.ikun.carshop.service;

import cn.ikun.carshop.mapper.CarsRentalMapper;
import cn.ikun.carshop.pojo.CarsRental;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

public interface CarsRentalService extends IService<CarsRental> {

    /**
     * 增加租借车辆
     * @param carsRental
     * @return
     */
    int addCarsRental(CarsRental carsRental);

    /**
     * 按照id删除租借车辆的信息
     * @param id
     * @return
     */
    int deleteCarsRentalById(String id);

    /**
     * 根据ids批量删除
     * @param ids
     * @return
     */
    int deleteCarByIds(List<Long> ids);


    /**
     * 根据id修改车辆的信息
     * @param carsRental
     * @return
     */
    int UpdateCarsRentalById(CarsRental carsRental);

    /**
     * 根据id获取车辆信息
     * @param id
     * @return
     */
    CarsRental getCarsRentalById(String id);

    /**
     * 按照msg查找信息
     * @param msg
     * @return
     */
    List<CarsRental> sarchCarsRentalBySearch(String msg);


    /**
     * 下架该车辆
     * @param id
     * @return
     */
    int banCarsRentalById(String id);



    /**
     * 启用该车辆
     * @param id
     * @return
     */
    int enableCarsRentalById(String id);

    /**
     * 出租该车辆
     * @param id
     * @return
     */
    int underCarsRental(Long id);

    /**
     * 分页获取信息
     * @param pageNum
     * @param pageSize
     * @return
     */
    Page<CarsRental> getCarsRentalPage(int pageNum,int pageSize);

    /**
     * 用户获取
     * @param pageNum
     * @param pageSize
     * @return
     */
    Page<CarsRental> userGetCarRentalPage(int pageNum,int pageSize);

    /**
     * 返回租借汽车的品牌和数量
     * @return
     */
    List<Map<String,Object>> getBrandCount();

    /**
     * 返回租借汽车的年份和数量
     * @return
     */
    List<Map<String,Object>> getYearCount();

    /**
     * 获取最新的车辆
     * @return
     */
    List<CarsRental> getUpToDateRentalCar();

}
