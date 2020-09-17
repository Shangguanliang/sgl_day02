package com.example.sgl_day02.properties;

import lombok.Data;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * 配置自定义配置文件
 */
@Data
@ToString
@Component
@PropertySource(value = {"classpath:datasource.properties"})
@ConfigurationProperties(prefix = "geek.datasource")
public class Jdbcproperties {
    private String url;
    private String username;
    private String password;
}
