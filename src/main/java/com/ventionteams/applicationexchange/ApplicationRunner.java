package com.ventionteams.applicationexchange;

import com.ventionteams.applicationexchange.config.ConfigProperties;
import com.ventionteams.applicationexchange.config.LinkConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableConfigurationProperties({
        ConfigProperties.class,
        LinkConfig.class
})
@EnableFeignClients
@EnableScheduling
public class ApplicationRunner {
    public static void main(String[] args) {
        SpringApplication.run(ApplicationRunner.class, args);
    }

}
