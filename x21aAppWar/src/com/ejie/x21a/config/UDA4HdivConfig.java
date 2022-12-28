package com.ejie.x21a.config;

import org.hdiv.config.annotation.ExclusionRegistry;
import org.hdiv.config.annotation.RuleRegistry;
import org.hdiv.config.annotation.ValidationConfigurer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import com.ejie.x38.hdiv.config.EjieValidationConfigurer.EjieValidationConfig.EjieEditableValidationConfigurer;
import com.ejie.x38.hdiv.util.Constants;
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
	protected String getErrorPage() {
        return "/error";
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
		
		// Tabla
		((EjieEditableValidationConfigurer) validationConfigurer.addValidation(".*/table/roles").rules(Constants.MODIFY_RULE_NAME)).setModifyParameter("rol");
		
		// Autocomplete y select
		((EjieEditableValidationConfigurer) validationConfigurer.addValidation(".*/autocomplete/remote").rules(Constants.MODIFY_RULE_NAME)).setModifyParameter("code");
		((EjieEditableValidationConfigurer) validationConfigurer.addValidation(".*/autocomplete/remoteEnlazadoProvincia").rules(Constants.MODIFY_RULE_NAME)).setModifyParameter("code");
		
		// Combos y select simples
		((EjieEditableValidationConfigurer) validationConfigurer.addValidation(".*/patrones/comboSimple/.*").rules(Constants.MODIFY_RULE_NAME)).setModifyParameter("code");
		((EjieEditableValidationConfigurer) validationConfigurer.addValidation(".*/tableComarca/provincia").rules(Constants.MODIFY_RULE_NAME)).setModifyParameter("provincia.code");
		((EjieEditableValidationConfigurer) validationConfigurer.addValidation(".*/tableComarca/comarca").rules(Constants.MODIFY_RULE_NAME)).setModifyParameter("comarcaId");
		
		// Combos y select enlazados simples
		((EjieEditableValidationConfigurer) validationConfigurer.addValidation(".*/patrones/.*").forParameters("codeProvincia", "codeComarca").rules(Constants.MODIFY_RULE_NAME)).setModifyParameter("codeLocalidad");
		((EjieEditableValidationConfigurer) validationConfigurer.addValidation(".*/patrones/.*").forParameters("codeProvincia").rules(Constants.MODIFY_RULE_NAME)).setModifyParameter("codeComarcaLocalidad");
		((EjieEditableValidationConfigurer) validationConfigurer.addValidation(".*/patrones/.*").rules(Constants.MODIFY_RULE_NAME)).setModifyParameter("codeProvincia");
		((EjieEditableValidationConfigurer) validationConfigurer.addValidation(".*/patrones/.*").rules(Constants.MODIFY_RULE_NAME)).setModifyParameter("codeComarca");
		
		// Combos y select enlazados múltiples
		((EjieEditableValidationConfigurer) validationConfigurer.addValidation(".*/patrones/.*").forParameters("codeDepartamento", "codeProvincia").rules(Constants.MODIFY_RULE_NAME)).setModifyParameter("codeDepartamentoProvincia");
		((EjieEditableValidationConfigurer) validationConfigurer.addValidation(".*/patrones/.*").rules(Constants.MODIFY_RULE_NAME)).setModifyParameter("codeDepartamento");
		((EjieEditableValidationConfigurer) validationConfigurer.addValidation(".*/patrones/.*").rules(Constants.MODIFY_RULE_NAME)).setModifyParameter("codeProvincia");
	}
}
