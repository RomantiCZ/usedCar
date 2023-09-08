package cn.ikun.carshop;

import cn.ikun.carshop.mapper.OrdersMapper;
import cn.ikun.carshop.pojo.Orders;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class OrdersTest {
    @Autowired
    private OrdersMapper mapper;

    @Test
    public void testget() {
        var list = mapper.selectAllOrder();
        list.forEach(System.out::println);
    }

    @Test
    public void testgetById() {

        var list = mapper.selectOrderById(1672172695461588993L);
        list.forEach(System.out::println);
    }
}
