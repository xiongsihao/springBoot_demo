package com.xsh.config;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

/**
 * @author : xsh
 * @create : 2021-02-01 - 1:51
 * @describe:
 */
@Component
public class errorClass implements HealthIndicator {

    @Override
    public Health health() {
        return Health.status("ERROR").withDetail("message","运行服务出现一个未知的错误").build();
    }
}
