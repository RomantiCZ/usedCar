package cn.ikun.carshop.service.impl;

import cn.ikun.carshop.mapper.CarsSaleMapper;
import cn.ikun.carshop.pojo.CarsRental;
import cn.ikun.carshop.pojo.CarsSale;
import cn.ikun.carshop.service.CarsSaleService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;


@Service
public class CarsSaleServiceImpl extends ServiceImpl<CarsSaleMapper, CarsSale> implements CarsSaleService {

    @Autowired
    private CarsSaleMapper carsSaleMapper;


    /**
     * 单增
     *
     * @param carsSale
     * @return
     */
    @Transactional
    @Override
    public int carSaleAdd(CarsSale carsSale) {
        return carsSaleMapper.insert(carsSale);
    }

    /**
     * 单删
     *
     * @param id
     * @return
     */
    @Transactional
    @Override
    public int carSaleDeleteById(Long id) {
      return  carsSaleMapper.deleteById(id);
    }

    /**
     * 多删
     *
     * @param listIds
     * @return
     */
    @Transactional
    @Override
    public int carSaleDeleteByIds(List<Long> listIds) {
        return carsSaleMapper.deleteBatchIds(listIds);
    }

    /**
     * 查
     *
     * @param id
     * @return
     */

    @Override
    public CarsSale getCarSaleById(Long id) {

        return  carsSaleMapper.selectById(id);
    }

    /**
     * 改
     *
     * @param carsSale
     * @return
     */
    @Transactional
    @Override
    public int updateCarSaleById(CarsSale carsSale) {
        LambdaUpdateWrapper<CarsSale> wrapper = new LambdaUpdateWrapper<>();
        wrapper.set(CarsSale::getBrand,carsSale.getBrand())
                .set(CarsSale::getModel,carsSale.getModel())
                .set(CarsSale::getYear,carsSale.getYear())
                .set(CarsSale::getMileage,carsSale.getMileage())
                .set(CarsSale::getImg,carsSale.getImg())
                .set(CarsSale::getDescription,carsSale.getDescription())
                .set(CarsSale::getPrice,carsSale.getPrice())
                .set(CarsSale::getUpdateTime, LocalDateTime.now())
                .eq(CarsSale::getVehicleId,carsSale.getVehicleId());
        return carsSaleMapper.update(carsSale,wrapper);
    }

    /**
     * 分页获取信息
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public Page<CarsSale> getCarSalePageList(int pageNum, int pageSize) {
        Page<CarsSale> page = new Page<>(pageNum,pageSize);
        QueryWrapper<CarsSale> wrapper = new QueryWrapper<>();
        wrapper.select(
                "vehicle_id","brand","model","year","img","price","status","update_time"
        );
        carsSaleMapper.selectPage(page,wrapper);
        return page;
    }

    /**
     * 用户分页获取
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public Page<CarsSale> userGetCarSalePageList(int pageNum, int pageSize) {
        Page<CarsSale> page = new Page<>(pageNum,pageSize);
        QueryWrapper<CarsSale> wrapper = new QueryWrapper<>();

        wrapper.select(
                "vehicle_id","brand","model","year","img","price","status","update_time"
        );
        wrapper.eq("status",0);
        carsSaleMapper.selectPage(page,wrapper);
        return page;
    }

    /**
     * 下架
     *
     * @param id
     * @return
     */
    @Transactional
    @Override
    public int banStatus(Long id) {
        LambdaUpdateWrapper<CarsSale> wrapper = new LambdaUpdateWrapper<>();
        wrapper.set(CarsSale::getStatus,2)
                .set(CarsSale::getUpdateTime,LocalDateTime.now())
                .eq(CarsSale::getVehicleId,id);
        return carsSaleMapper.update(new CarsSale(),wrapper);
    }

    /**
     * 按照关键词模糊查询
     *
     * @param keyword
     * @return
     */
    @Override
    public List<CarsSale> getMsgBySearch(String keyword) {
        LambdaUpdateWrapper<CarsSale> wrapper = new LambdaUpdateWrapper<>();
        wrapper.like(CarsSale::getBrand,keyword).or()
                .like(CarsSale::getModel,keyword);
        return carsSaleMapper.selectList(wrapper);
    }

    /**
     * 出售
     * @param id
     * @return
     */
    @Transactional
    @Override
    public int saleStatus(Long id) {
        LambdaUpdateWrapper<CarsSale> wrapper = new LambdaUpdateWrapper<>();
        wrapper.set(CarsSale::getStatus,1)
                .set(CarsSale::getUpdateTime,LocalDateTime.now())
                .eq(CarsSale::getVehicleId,id);
        return carsSaleMapper.update(new CarsSale(),wrapper);
    }

    /**
     * 启用
     *
     * @param id
     * @return
     */
    @Transactional
    @Override
    public int enableStatus(Long id) {
        LambdaUpdateWrapper<CarsSale> wrapper = new LambdaUpdateWrapper<>();
        wrapper.set(CarsSale::getStatus,0)
                .set(CarsSale::getUpdateTime,LocalDateTime.now())
                .eq(CarsSale::getVehicleId,id);
        return carsSaleMapper.update(new CarsSale(),wrapper);
    }

    /**
     * 获取所有品牌和车辆数目
     *
     * @return
     */
    @Override
    public List<Map<String, Object>> selectAllBrand() {
        return carsSaleMapper.selectAllBrand();
    }

    /**
     * 获取所有车辆的年月
     *
     * @return
     */
    @Override
    public List<Map<String, Object>> selectAllYear() {
        return carsSaleMapper.selectAllYear();
    }

    /**
     * 获取最新的车辆
     *
     * @return
     */
    @Override
    public List<CarsSale> getUpToDateRentalCar() {
        return carsSaleMapper.selectOrderByUpdateTime();
    }
}
