package com.example.demo.annotation;

import org.springframework.stereotype.Component;

@Agent
@Component
public class UseAgentDemo {

    public void get(String word) {
        System.out.println("This is my input word:" + word);
    }

    public static void get1() {
        System.out.println("This is my annotation!!");
    }

    public String get2(String word) {
        return "This is my input word:" + word;
    }
}
