package com.much.it;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @description:
 * @project: much
 * @date: 2020/5/4
 * @author: Wenxin
 * @version: 1.0
 */
@SpringBootApplication(exclude = DruidDataSourceAutoConfigure.class)
//@EnableDiscoveryClient
//@EnableFeignClients
public class SeataApp {
    public static void main(String[] args) {
        SpringApplication.run(SeataApp.class,args);
    }
}
