package com.ejie.x21a.config;

import java.util.List;

import org.hdiv.config.annotation.ExclusionRegistry;
import org.hdiv.config.annotation.RuleRegistry;
import org.hdiv.config.annotation.builders.SecurityConfigBuilder;
import org.hdiv.ee.config.annotation.ValidationConfigurer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.hateoas.Link;

import com.ejie.x38.hdiv.config.UDA4HdivConfigurerAdapter;
import com.hdivsecurity.services.config.EnableHdiv4ServicesSecurityConfiguration;

@Configuration
@EnableAspectJAutoProxy
@EnableHdiv4ServicesSecurityConfiguration
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
	public void addCustomExclusions(final ExclusionRegistry registry) {
	}

	@Override
	public void addCustomRules(final RuleRegistry registry) {
	}

	@Override
	public void customConfigureEditableValidation(final ValidationConfigurer validationConfigurer) {
	}

	@Override
	protected List<Link> getStaticLinks() {
		return null;
	}
	
	@Override
    public void configure(SecurityConfigBuilder builder) {
        builder.errorPage("/error.jsp");
    }
}
