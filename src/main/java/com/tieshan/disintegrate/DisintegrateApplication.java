package com.tieshan.disintegrate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;

@SpringBootApplication
@ComponentScan(basePackages = {"com.tieshan.disintegrate"})
public class DisintegrateApplication {

    public static void main(String[] args) {
        SpringApplication.run(DisintegrateApplication.class, args);
    }

}
