package com.flight.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 读取航班公司名称
 * @author sunlongfei
 */
@Configuration
@ConfigurationProperties(prefix = "airline")
@Data
public class AirlineConfig {

    private String airline1;

    private String airline2;

    private String airline3;
}
