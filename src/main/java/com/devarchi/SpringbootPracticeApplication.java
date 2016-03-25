package com.devarchi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SpringbootPracticeApplication {

    public static void main(String[] args) {
        System.setProperty("spring.jpa.show-sql", "true");
        System.setProperty("spring.jpa.hibernate.ddl-auto", "validate");
        System.setProperty("spring.h2.console.enabled", "true");
        ApplicationContext context = SpringApplication.run(SpringbootPracticeApplication.class, args);
    }
}
