package com.xsh;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author : xsh
 * @describe:
 */
@SpringBootApplication
public class ymlApplication {

    public static void main(String[] args) {
        //SpringApplication.run(ymlApplication.class, args);
        SpringApplication application = new SpringApplication(ymlApplication.class);
        application.setBannerMode(Banner.Mode.OFF);//去掉spring启动的图标
        application.run(args);
    }
}
