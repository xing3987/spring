package com.example.springCloudClientA;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
/*
需要注册在eruka上，需要在启动类添加@EnableDiscoveryClient:启用服务注册与发现
 */
@SpringBootApplication
@EnableDiscoveryClient
public class SpringCloudClientAApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudClientAApplication.class, args);
	}
}
