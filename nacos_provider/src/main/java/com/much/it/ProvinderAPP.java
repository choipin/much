package com.much.it;

import cn.shuibo.annotation.EnableSecurity;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @description:
 * @project: much
 * @date: 2020/5/1
 * @author: Wenxin
 * @version: 1.0
 */
@SpringBootApplication(exclude = {DruidDataSourceAutoConfigure.class})
@EnableDiscoveryClient
@EnableSecurity
public class ProvinderAPP {
    public static void main(String[] args) {
        //-Djasypt.encryptor.password=8ySVNqkUgsuvowfPsSNF
        SpringApplication.run(ProvinderAPP.class,args);
    }
}
