package com.xsh.config;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

/**
 * @author : xsh
 * @create : 2021-02-01 - 1:33
 * @describe:
 */
@Component
public class XSH_HealthIndicator implements HealthIndicator {

    @Override
    public Health health() {
        /*这个errorCode可以是一些业务逻辑的执行情况的返回值或者某些服务的运行状态的返回值*/
        //int errorCode = 0;
        int errorCode = 1;
        if (errorCode != 0) {
            return Health.down().withDetail("Error Code", errorCode).build();
        }
        return Health.up().build();
    }

}
