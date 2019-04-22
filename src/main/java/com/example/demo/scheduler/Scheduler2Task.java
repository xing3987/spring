package com.example.demo.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class Scheduler2Task {

    private static final SimpleDateFormat df=new SimpleDateFormat("HH:mm:ss");

    //设置系统每隔六秒启动该任务
    //@Scheduled(fixedRate = 6000)//同@Scheduled(cron = "*/6****?")
    public void reportCurrentTime(){
        System.out.println("当前时间："+df.format(new Date()));
    }
}
