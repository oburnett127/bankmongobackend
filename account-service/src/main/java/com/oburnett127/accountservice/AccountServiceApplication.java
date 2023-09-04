package com.oburnett127.accountservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = {"com.oburnett127"})
@EnableJpaRepositories
public class AccountServiceApplication {

    public static void main(String[] args) {

         SpringApplication.run(AccountServiceApplication.class, args);

    }
}
