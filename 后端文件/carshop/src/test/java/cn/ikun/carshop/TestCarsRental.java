package cn.ikun.carshop;


import cn.ikun.carshop.mapper.CarsRentalMapper;
import cn.ikun.carshop.pojo.CarsRental;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

@SpringBootTest

public class TestCarsRental {
    @Autowired
    private CarsRentalMapper carsRentalMapper;

    @Test
    public void testCarRental(){
        var list = carsRentalMapper.selectAllBrand();
        for (Map<String, Object> row : list) {
            String brand = (String) row.get("brand");
            Long number = (Long) row.get("number");
            System.out.println("Brand: " + brand + ", number: " + number);
        }

    }

    @Test
    public void testSelectOrderByUpdateTime(){
        var list = carsRentalMapper.selectOrderByUpdateTime();
        list.forEach(System.out::println);
    }

    @Test
    public void test1(){
        var list = carsRentalMapper.selectAllYear();
        list.forEach(System.out::println);
    }
}
