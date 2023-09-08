package cn.ikun.carshop.service;

import cn.ikun.carshop.pojo.CarsRental;
import cn.ikun.carshop.pojo.CarsSale;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

public interface CarsSaleService extends IService<CarsSale> {


    /**
     * 单增
     * @param carsSale
     * @return
     */
    int carSaleAdd(CarsSale carsSale);

    /**
     * 单删
     * @param id
     * @return
     */
    int carSaleDeleteById(Long id);

    /**
     * 多删
     * @param listIds
     * @return
     */
    int carSaleDeleteByIds(List<Long> listIds);

    /**
     * 查
     * @param id
     * @return
     */
    CarsSale getCarSaleById(Long id);

    /**
     * 改
     * @param carsSale
     * @return
     */
    int updateCarSaleById(CarsSale carsSale);


    /**
     * 下架
     * @param id
     * @return
     */
    int banStatus(Long id);

    /**
     * 出售
     * @param id
     * @return
     */
    int saleStatus(Long id);

    /**
     * 启用
     * @param id
     * @return
     */
    int enableStatus(Long id);

    /**
     * 按照关键词模糊查询
     * @param keyword
     * @return
     */
    List<CarsSale> getMsgBySearch(String keyword);

    /**
     * 分页获取信息
     * @param pageNum
     * @param pageSize
     * @return
     */
    Page<CarsSale> getCarSalePageList(int pageNum, int pageSize);

    /**
     * 用户分页获取
     * @param pageNum
     * @param pageSize
     * @return
     */
    Page<CarsSale> userGetCarSalePageList(int pageNum, int pageSize);

    /**
     * 获取所有品牌和车辆数目
     * @return
     */
    List<Map<String,Object>> selectAllBrand();

    /**
     * 获取所有车辆的年月
     * @return
     */
    List<Map<String,Object>> selectAllYear();

    /**
     * 获取最新的车辆
     * @return
     */
    List<CarsSale> getUpToDateRentalCar();

}
