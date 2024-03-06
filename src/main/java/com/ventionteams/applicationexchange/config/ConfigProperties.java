package com.ventionteams.applicationexchange.config;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "aws")
@Getter
@Setter
@RequiredArgsConstructor
public class ConfigProperties {
    private String accessKey;
    private String secretAccessKey;
    private String bucketName;
}
