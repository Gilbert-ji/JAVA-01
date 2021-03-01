package com.example.starterdemo.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @desc:
 * @author: biao
 * @create: 2021-02-21 22:41
 **/
@Data
@ConfigurationProperties(prefix = "school")
public class MyStartProperties {
    private int stId;
    private String stName;
    private String klCode;
    private String scName;
}
