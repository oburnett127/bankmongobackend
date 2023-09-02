package com.oburnett127.accountservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

//@EnableEurekaClient
@SpringBootApplication
@EnableJpaRepositories
public class AccountServiceApplication {

    public static void main(String[] args) {

         SpringApplication.run(AccountServiceApplication.class, args);

    }
}
