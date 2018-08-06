package com.example.springCloudClientB.controller;


import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="springCloudClientA")
public interface HelloRemote {

    @RequestMapping(value="/hello",method = RequestMethod.GET)
    public String index(@RequestParam(value="name") String name);

}
