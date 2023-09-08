package cn.ikun.carshop.service.impl;

import cn.ikun.carshop.mapper.CarsRentalMapper;
import cn.ikun.carshop.pojo.CarsRental;
import cn.ikun.carshop.service.CarsRentalService;
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
public class CarsRentalServiceImpl extends ServiceImpl<CarsRentalMapper, CarsRental> implements CarsRentalService {

    @Autowired
    private CarsRentalMapper carsRentalMapper;

    /**
     * 增加租借车辆
     * @param carsRental
     * @return
     */
    @Transactional
    @Override
    public int addCarsRental(CarsRental carsRental) {
        return carsRentalMapper.insert(carsRental);
    }

    /**
     * 按照id删除租借车辆的信息
     * @param id
     * @return
     */
    @Transactional
    @Override
    public int deleteCarsRentalById(String id){
       return carsRentalMapper.deleteById(id);
    }

    /**
     * 根据ids批量删除
     * @param ids
     * @return
     */
    @Transactional
    @Override
    public int deleteCarByIds(List<Long> ids) {

        return carsRentalMapper.deleteBatchIds(ids);
    }

    /**
     * 根据id修改车辆的信息
     * @param carsRental
     * @return
     */
    @Transactional
    @Override
    public int UpdateCarsRentalById(CarsRental carsRental){
        LambdaUpdateWrapper<CarsRental> wrapper = new LambdaUpdateWrapper<>();
        wrapper.set(CarsRental::getBrand,carsRental.getBrand())
                .set(CarsRental::getModel,carsRental.getModel())
                .set(CarsRental::getYear,carsRental.getYear())
                .set(CarsRental::getMileage,carsRental.getMileage())
                .set(CarsRental::getImg,carsRental.getImg())
                .set(CarsRental::getDescription,carsRental.getDescription())
                .set(CarsRental::getRentalPrice,carsRental.getRentalPrice())
                .set(CarsRental::getUpdateTime, LocalDateTime.now())
                .eq(CarsRental::getVehicleId,carsRental.getVehicleId());
        return carsRentalMapper.update(carsRental,wrapper);
    }


    /**
     * 根据id获取车辆信息
     * @param id
     * @return
     */
    @Override
    public CarsRental getCarsRentalById(String id) {
        return carsRentalMapper.selectById(id);
    }

    /**
     * 分页获取信息
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public Page<CarsRental> getCarsRentalPage(int pageNum, int pageSize) {
        Page<CarsRental> page = new Page<>(pageNum,pageSize);
//        LambdaUpdateWrapper<CarsRental> wrapper = new LambdaUpdateWrapper<>();
        QueryWrapper<CarsRental> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("create_time");
        wrapper.select("vehicle_id","brand","model","year","img","status","rental_price","update_time");
        carsRentalMapper.selectPage(page,wrapper);
        return page;
    }

    /**
     * 用户分页获取
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public Page<CarsRental> userGetCarRentalPage(int pageNum, int pageSize){
        Page<CarsRental> page = new Page<>(pageNum,pageSize);
        QueryWrapper<CarsRental> wrapper = new QueryWrapper<>();
        wrapper.select("vehicle_id","brand","model","year","img","status","rental_price","update_time");
        wrapper.eq("status",0);
        carsRentalMapper.selectPage(page,wrapper);
        return page;
    }

    @Transactional
    @Override
    public int banCarsRentalById(String id) {
        LambdaUpdateWrapper<CarsRental> wrapper = new LambdaUpdateWrapper<>();
        wrapper.set(CarsRental::getStatus,2)
                .set(CarsRental::getUpdateTime,LocalDateTime.now())
                .eq(CarsRental::getVehicleId,id);
        return carsRentalMapper.update(new CarsRental(),wrapper);
    }

    @Transactional
    @Override
    public int enableCarsRentalById(String id) {
        LambdaUpdateWrapper<CarsRental> wrapper = new LambdaUpdateWrapper<>();
        wrapper.set(CarsRental::getStatus,0)
                .set(CarsRental::getUpdateTime,LocalDateTime.now())
                .eq(CarsRental::getVehicleId,id);
        return carsRentalMapper.update(new CarsRental(),wrapper);
    }



    /**
     * 出租该车辆
     * @param id
     * @return
     */
    @Transactional
    @Override
    public int underCarsRental(Long id){
        LambdaUpdateWrapper<CarsRental> wrapper = new LambdaUpdateWrapper<>();
        wrapper.set(CarsRental::getStatus,1)
                .set(CarsRental::getUpdateTime,LocalDateTime.now())
                .eq(CarsRental::getVehicleId,id);
        return carsRentalMapper.update(new CarsRental(),wrapper);
    }

    @Override
    public List<Map<String, Object>> getBrandCount() {
        return carsRentalMapper.selectAllBrand();
    }

    @Override
    public List<Map<String, Object>> getYearCount() {
        return carsRentalMapper.selectAllYear();
    }

    /**
     * 按照msg查找信息
     * @param msg
     * @return
     */
    @Override
    public List<CarsRental> sarchCarsRentalBySearch(String msg) {
        LambdaUpdateWrapper<CarsRental> wrapper = new LambdaUpdateWrapper<>();
        wrapper.like(CarsRental::getBrand,msg).or()
                .like(CarsRental::getModel,msg);

        return carsRentalMapper.selectList(wrapper);
    }

    /**
     * 获取最新的车辆
     *
     * @return
     */
    @Override
    public List<CarsRental> getUpToDateRentalCar() {
        return carsRentalMapper.selectOrderByUpdateTime();
    }
}
