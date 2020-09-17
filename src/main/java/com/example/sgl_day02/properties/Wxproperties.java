package com.example.sgl_day02.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 获取自定义属性数据
 */
@Data
@Component
//prefix:配置名前缀
@ConfigurationProperties(prefix = "wx")
public class Wxproperties {
    //设置属性 配合前缀获得属性的数据
    private String appid;
    private String secret;
}
