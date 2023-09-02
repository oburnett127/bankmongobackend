package com.oburnett127.accountservice.feign;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackages = "com.oburnett127.accountservice.feign")
public class FeignConfig {
}
