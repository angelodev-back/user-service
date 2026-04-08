package com.bibliotecams.user;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class UserServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner run() {
        return args -> {
            System.out.println("=====================================");
            System.out.println("✅ USER-SERVICE EJECUTADO CON ÉXITO");
            System.out.println("🌐 URL: http://localhost:8081");
            System.out.println("📌 Endpoints: /api/v1/usuarios");
            System.out.println("=====================================");
        };
    }
}