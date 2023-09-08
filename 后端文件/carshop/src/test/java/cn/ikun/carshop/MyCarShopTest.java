package cn.ikun.carshop;


import cn.ikun.carshop.mapper.AdminMapper;
import cn.ikun.carshop.mapper.CarsRentalMapper;
import cn.ikun.carshop.pojo.Admin;
import cn.ikun.carshop.service.AdminService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class MyCarShopTest {

    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private AdminService adminService;



    @Test
    public void test1(){
        String username = "wangziyi";
        String password = "123456";
        LambdaQueryWrapper<Admin> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Admin::getUsername,username).eq(Admin::getPassword,password);
        var admin = adminMapper.selectOne(lambdaQueryWrapper);
        System.out.println(admin);
    }

    @Test
    public void test2() {
        String username = "admin";
        String password = "admin";
        LambdaQueryWrapper<Admin> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Admin::getUsername,username).eq(Admin::getPassword,password);
        var admin = adminMapper.selectOne(lambdaQueryWrapper);
        System.out.println(admin);
    }


}
