package com.chen.trademark;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.ClassUtils;

@SpringBootApplication
public class TrademarkApplication {

    public static void main(String[] args) {
        String path = ClassUtils.getDefaultClassLoader().getResource("").getPath();
        System.setProperty("webdriver.chrome.driver", "E:/chromedriver.exe");

        SpringApplication.run(TrademarkApplication.class, args);
    }

}
