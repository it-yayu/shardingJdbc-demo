package com.think;

import com.think.conf.SnowflakeConfig;
import com.think.dao.OrderMapper;
import com.think.entity.TOrder;
import com.think.main.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class) // 指定spring-boot的启动类
public class AppTest {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private SnowflakeConfig snowflakeConfig;

    @Test
    public void test() {
        long id = snowflakeConfig.snowflakeId();
        System.out.println(id);
        TOrder order = new TOrder();
        order.setUserId(1888556);
        order.setOrderId(id);
        orderMapper.insert(order);
    }

    @Test
    public void test2() {
        TOrder order = orderMapper.findById(1300626441284751360L);
//		System.out.println(order.getOrderId() + "" + order.getUserId());
        System.out.println(order);
    }


}
