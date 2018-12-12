package com.xujin.ad_sender.entity;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * \* Created with IntelliJ IDEA.
 * \* Description:
 * \* User: xujin
 * \* Date: 2018/4/22
 * \* Time: 21:40
 * \
 */
@Component
@ConfigurationProperties(prefix = "myADConfig")
public class ConfigEntity {
    private Map<String, String> controller_config = new HashMap<>();
    private Map<String, String> service_config = new HashMap<>();

    public Map<String, String> getController_config() {
        return controller_config;
    }

    public void setController_config(Map<String, String> controller_config) {
        this.controller_config = controller_config;
    }

    public Map<String, String> getService_config() {
        return service_config;
    }

    public void setService_config(Map<String, String> service_config) {
        this.service_config = service_config;
    }
}



