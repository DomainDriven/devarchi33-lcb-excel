package com.devarchi;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SpringbootPracticeApplication.class)
@WebAppConfiguration
public class SpringbootPracticeApplicationTests {

    @Test
    public void contextLoads() {
        double a = 1.0;
        double b = 0.1;

        for (int i = 0; i < 5; i++) {
            a += b;
            System.out.println(a);
        }

        double aa = 1.0;
        double bb = 0.1;

        BigDecimal bd1 = new BigDecimal(String.valueOf(aa));
        BigDecimal bd2 = new BigDecimal(String.valueOf(bb));

        for (int i = 0; i < 5; i++) {
            bd1 = bd1.add(bd2);
            System.out.println("BigDecimal: " + bd1.toString());
        }

    }

}
