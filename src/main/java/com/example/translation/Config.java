package com.example.translation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProviderChain;
import software.amazon.awssdk.auth.credentials.EnvironmentVariableCredentialsProvider;
import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.auth.credentials.SystemPropertyCredentialsProvider;
import software.amazon.awssdk.services.translate.TranslateClient;

@Configuration
public class Config {

    @Bean
    TranslateClient createTranslateClient() {
        return TranslateClient.builder()
            .credentialsProvider(credentialsProviderChain())
            .build();
    }

    private AwsCredentialsProviderChain credentialsProviderChain () {
        return AwsCredentialsProviderChain.of(
            SystemPropertyCredentialsProvider.create(),
            EnvironmentVariableCredentialsProvider.create(),
            ProfileCredentialsProvider.create()
        );
    }
}
