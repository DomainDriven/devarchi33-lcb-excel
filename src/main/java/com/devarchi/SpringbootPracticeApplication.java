package com.devarchi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@EnableWebMvc
@SpringBootApplication
public class SpringbootPracticeApplication extends WebMvcConfigurerAdapter {

    public static void main(String[] args) {
        System.setProperty("spring.jpa.show-sql", "true");
        System.setProperty("spring.jpa.hibernate.ddl-auto", "validate");
        System.setProperty("spring.h2.console.enabled", "true");
        ApplicationContext context = SpringApplication.run(SpringbootPracticeApplication.class, args);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/assets/**")
                .addResourceLocations("classpath:/assets/")
                .setCachePeriod(31556926);

//         추가 경로 등록 가능.
//        registry.addResourceHandler("/webjars/**")
//                .addResourceLocations("classpath:/webjars/")
//                .setCachePeriod(31556926);
    }
}
