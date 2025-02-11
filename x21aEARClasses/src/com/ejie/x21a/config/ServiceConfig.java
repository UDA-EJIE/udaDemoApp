package com.ejie.x21a.config;

import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Configuración de Spring MVC.
 */
@ComponentScan("com.ejie.x21a.service")
@Configuration
@EnableWebMvc
public class ServiceConfig implements WebMvcConfigurer {

	/**
	 * Recursos idiomáticos (i18n).
	 * 
	 * @return MessageSource.
	 */
	@Bean
	public MessageSource appMessageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("x21a.i18n");
		messageSource.setDefaultEncoding("UTF-8");
		messageSource.setUseCodeAsDefaultMessage(true);
		messageSource.setFallbackToSystemLocale(false);
		return messageSource;
	}

	/**
	 * Propiedades de configuración de la aplicación.
	 * 
	 * @return Configuración.
	 */
	@Bean
	public PropertiesFactoryBean appConfiguration() {
		PropertiesFactoryBean res = new PropertiesFactoryBean();
		res.setFileEncoding("UTF-8");
		res.setLocation(new FileSystemResource("classpath:x21a/x21a.properties"));
		return res;
	}

}
