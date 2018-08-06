package com.example.springCloudClientB;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

/*
需要调用eureka上的服务时启动类要添加@EnableDiscoveryClient:启用服务注册与发现,
  @EnableFeignClients:启用feign进行远程调用
 */
@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication
public class SpringCloudClientBApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudClientBApplication.class, args);
	}
}
