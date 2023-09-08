package cn.ikun.carshop.mapper;

import cn.ikun.carshop.pojo.CarsRental;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface CarsRentalMapper extends BaseMapper<CarsRental> {

    List<Map<String,Object>> selectAllBrand();

    List<Map<String,Object>> selectAllYear();

    List<CarsRental> selectOrderByUpdateTime();
}
