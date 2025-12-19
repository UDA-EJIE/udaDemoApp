package com.ejie.x21a.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableTransactionManagement
@ComponentScan(
  basePackages = "com.ejie.x21a",
  excludeFilters = @ComponentScan.Filter(
    type = FilterType.ANNOTATION,
    classes = EnableWebMvc.class
  )
)
public class RootConfig {
}

