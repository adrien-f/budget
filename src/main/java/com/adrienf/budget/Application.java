package com.adrienf.budget;

import com.adrienf.budget.domain.Operation;
import com.adrienf.budget.service.IOperationRepository;
import org.joda.money.Money;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

@SpringBootApplication
public class Application {

    @Bean
    CommandLineRunner init(IOperationRepository operationRepository) {
        return (evt) -> Arrays.asList(
                "foo,bar".split(",")
        ).forEach(
                o -> operationRepository.save(new Operation(o, Money.parse("EUR 25.00")))
        );
    }

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(Application.class, args);
    }
}
