package com.example.alarmserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AlarmServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(AlarmServerApplication.class, args);
        System.out.println("Server Started");
    }
}
