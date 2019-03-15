package com.qx.provider;

import com.alibaba.dubbo.config.spring.context.annotation.DubboComponentScan;
import com.alibaba.dubbo.config.spring.context.annotation.EnableDubboConfig;
import com.alibaba.nacos.api.annotation.NacosProperties;
import com.alibaba.nacos.api.config.annotation.NacosConfigurationProperties;
import com.alibaba.nacos.api.config.annotation.NacosProperty;
import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.alibaba.nacos.spring.context.annotation.config.EnableNacosConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableDubboConfig
@DubboComponentScan("com.qx.provider.impl")
@SpringBootApplication
@EnableNacosConfig(globalProperties = @NacosProperties(serverAddr = "127.0.0.1:8848"))
@NacosConfigurationProperties(dataId = "springboot2-nacos-config", autoRefreshed = true)
public class DubboProviderApplication {

    @NacosValue(value = "${dubbo.application.name}", autoRefreshed = true)
    private String dubboApplicationName;

    public String getDubboApplicationName() {
        return dubboApplicationName;
    }

    public void setDubboApplicationName(String dubboApplicationName) {
        this.dubboApplicationName = dubboApplicationName;
    }


    public static void main(String[] args) {
        SpringApplication.run(DubboProviderApplication.class, args);
    }

}

