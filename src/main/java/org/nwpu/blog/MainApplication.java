package org.nwpu.blog;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * 主程序类
 * @SpringBootApplication:这是一个springboot应用
 */
@SpringBootApplication
@MapperScan(basePackages = {"org.nwpu.blog.mapper"})
//@ServletComponentScan
public class MainApplication extends SpringBootServletInitializer {

    public static void main(String[] args){
        ConfigurableApplicationContext run = SpringApplication.run(MainApplication.class, args);
//        String[] names = run.getBeanDefinitionNames();
//        System.out.println("====================");
//        for(String name:names){
//            System.out.println(name);
//        }
    }

//    @Override
//    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
//        return application.sources(MainApplication.class);
//    }
}
