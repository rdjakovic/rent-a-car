package com.ranko.rent_a_car.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration
//@ComponentScan(basePackages = "com.ranko.rent_a_car",
//        excludeFilters = {
//            @Filter(type = FilterType.ANNOTATION, value = EnableWebMvc.class)
//        })
@ComponentScan(basePackages = "com.ranko.rent_a_car")
public class RootConfig {

        @Bean
        public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
                return new PropertySourcesPlaceholderConfigurer();
        }
}
