package com.ejie.x21a.config;

import org.hdiv.config.annotation.ExclusionRegistry;
import org.hdiv.config.annotation.RuleRegistry;
import org.hdiv.config.annotation.ValidationConfigurer;
import org.hdiv.config.annotation.builders.SecurityConfigBuilder;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import com.ejie.x38.hdiv.config.EjieValidationConfigurer.EjieValidationConfig.EjieEditableValidationConfigurer;
import com.ejie.x38.hdiv.config.UDA4HdivConfigurerAdapter;

@Configuration
@EnableAspectJAutoProxy
@ComponentScan("com.ejie.x38.hdiv.aspect")
public class UDA4HdivConfig extends UDA4HdivConfigurerAdapter {

	@Override
	protected String getHomePage() {
		return "/";
	}

	@Override
	protected String getLoginPage() {
		return "/mockLoginPage";
	}
	
	@Override
    public void customConfigure(final SecurityConfigBuilder builder) {
        builder.errorPage("/error");
    }

	@Override
	public void addCustomExclusions(final ExclusionRegistry registry) {
		registry.addUrlExclusions("/iberdok/view");
		registry.addUrlExclusions("/iberdok/urlFinalizacion");
		registry.addUrlExclusions("/patrones/upload");
		registry.addUrlExclusions("/pifServlet");
		registry.addUrlExclusions("/upload/pifForm");
	}

	@Override
	public void addCustomRules(final RuleRegistry registry) {
	}

	@Override
	public void customConfigureEditableValidation(final ValidationConfigurer validationConfigurer) {
		((EjieEditableValidationConfigurer) validationConfigurer.addValidation(".*/multiFilter/getDefault").forParameters("user").rules("text")).setAsClientParameter(true);
	}
}
