package cn.ikun.carshop;

import cn.ikun.carshop.pojo.CarsSale;
import cn.ikun.carshop.service.CarsSaleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

@SpringBootTest
public class TestCarSale {
    @Autowired
    private CarsSaleService service;

    @Test
    public void test1(){
        var list = service.selectAllYear();
        list.forEach(System.out::println);
    }
}
