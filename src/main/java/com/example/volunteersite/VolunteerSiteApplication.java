package com.example.volunteersite;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import javax.sql.DataSource;
import java.util.Arrays;

@SpringBootApplication
public class VolunteerSiteApplication {

    @Bean
    public DataSource dataSource() {
        return DataSourceBuilder.create()
                .url("jdbc:mysql://localhost:3306/test")
                .username("root")
                .password("12345678")
                .driverClassName("com.mysql.cj.jdbc.Driver")
                .build();
    }
    public static void main(String[] args) {
        SpringApplication.run(VolunteerSiteApplication.class, args);
    }
}
