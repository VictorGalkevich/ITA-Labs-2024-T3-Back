package com.ventionteams.applicationexchange.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
//import com.amazonaws.services.textract.AmazonTextract;
//import com.amazonaws.services.textract.AmazonTextractClient;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Getter
@Configuration
public class AWSClientConfig {
    @Value("${aws.accessKey}")
    private String accessKey = "AKIAXYKJRCXIYC3CQZUQ";

    @Value("${aws.secretAccessKey}")
    private String secretAccessKey = "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";

    @Value("${aws.bucketName}")
    private String bucketName = "ita-labs-2024-t3";

    private final Region region = Region.getRegion(Regions.US_EAST_1);
    private final BasicAWSCredentials awsCredentials = new BasicAWSCredentials(accessKey, secretAccessKey);

     @Bean
    public AmazonS3 amazonS3() {
         return AmazonS3ClientBuilder
                 .standard()
                 .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                 .withRegion(region.toString())
                 .build();
     }
}
