package com.example.demo.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SchedulerTask {

    private int count=0;

    //设置系统每隔六秒启动该任务
    @Scheduled(cron = "* 0/10 * * * ?") //或@Scheduled(fixedRate=6000)
    private void process(){
        System.out.println("this is scheduler task running:"+(count++));
    }

}
