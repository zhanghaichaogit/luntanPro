package com.admin.weixin.test;

import com.admin.luntan.SimpleApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author zhanghaichao on 2017/8/3.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SimpleApplication.class)
public class ServiceTest {

    @Test
    public void jssdkTest() {
        System.out.println(1111);
    }
}
