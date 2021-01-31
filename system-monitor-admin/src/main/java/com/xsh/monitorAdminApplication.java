package com.xsh;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author : xsh
 * @create : 2021-02-01 - 2:48
 * @describe:
 */
@SpringBootApplication
@EnableAdminServer
public class monitorAdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(monitorAdminApplication.class, args);
    }

}
