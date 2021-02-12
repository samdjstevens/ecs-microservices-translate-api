package com.example.translation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.services.translate.TranslateClient;

@Configuration
public class Config {

    @Bean
    TranslateClient createTranslateClient() {
        return TranslateClient.builder()
            .credentialsProvider(credentialsProviderChain())
            .build();
    }

    private AwsCredentialsProvider credentialsProviderChain () {
        return DefaultCredentialsProvider.create();
    }
}
