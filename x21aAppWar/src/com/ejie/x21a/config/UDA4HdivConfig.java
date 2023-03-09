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
	}

	@Override
	public void addCustomRules(final RuleRegistry registry) {
	}

	@Override
	public void customConfigureEditableValidation(final ValidationConfigurer validationConfigurer) {
		((EjieEditableValidationConfigurer) validationConfigurer
				.addValidation("/tableLocalidad/editForm")
				.forParameters("pkValueComarca")
				.rules("fulltext"))
				.setAsClientParameter(true);
		
		// Upload PIF
		((EjieEditableValidationConfigurer) validationConfigurer
				.addValidation(".*/upload/pifForm")
				.forParameters("base_url", "hadoop_folder_path")
				.rules("partialUrl"))
				.setAsClientParameter(true);
		
		((EjieEditableValidationConfigurer) validationConfigurer
				.addValidation(".*/upload/pifForm")
				.forParameters("securityToken")
				.rules("text"))
				.setAsClientParameter(true);
		
		((EjieEditableValidationConfigurer) validationConfigurer
				.addValidation(".*/upload/pifForm")
				.forParameters("hadoop_preserve_name")
				.rules("boolean"))
				.setAsClientParameter(true);
		
		((EjieEditableValidationConfigurer) validationConfigurer
				.addValidation(".*/upload/pifForm")
				.forParameters("y31_ttl")
				.rules("numeric"))
				.setAsClientParameter(true);

		// Multifiltro
		((EjieEditableValidationConfigurer) validationConfigurer
				.addValidation(".*/multiFilter")
				.forParameters("mapping", "tableID", "containerClass", "labelClass", "defaultContainerClass", "defaultCheckboxClass")
				.rules("text"))
				.setAsClientParameter(true);
		
		((EjieEditableValidationConfigurer) validationConfigurer
				.addValidation(".*/multiFilter/getDefault")
				.forParameters("filterSelector", "user")
				.rules("text"))
				.setAsClientParameter(true);
		
		((EjieEditableValidationConfigurer) validationConfigurer
				.addValidation(".*/multiFilter/getAll")
				.forParameters("filterSelector", "user")
				.rules("text"))
				.setAsClientParameter(true);

		// Tabla
		((EjieEditableValidationConfigurer) validationConfigurer
				.addValidation(".*/table/roles")
				.rules(Constants.MODIFY_RULE_NAME))
				.setModifyParameter("rol");
		
		((EjieEditableValidationConfigurer) validationConfigurer
				.addValidation(".*/table/dynamicColumns/inlineEdit")
				.forParameters("apellido1", "apellido2", "ejie", "fechaBaja", "rol")
				.rules("boolean"))
				.setAsClientParameter(true);

		// Autocomplete, combo y select simples
		((EjieEditableValidationConfigurer) validationConfigurer
				.addValidation(".*/patrones/autocomplete/remote")
				.rules(Constants.MODIFY_RULE_NAME))
				.setModifyParameter("code");
		
		((EjieEditableValidationConfigurer) validationConfigurer
				.addValidation(".*/patrones/autocomplete/remote")
				.rules(Constants.MODIFY_RULE_NAME))
				.setModifyParameter("codeDepartamentoProvincia");
		
		((EjieEditableValidationConfigurer) validationConfigurer
				.addValidation(".*/patrones/comboSimple/.*")
				.rules(Constants.MODIFY_RULE_NAME))
				.setModifyParameter("code");
		
		((EjieEditableValidationConfigurer) validationConfigurer
				.addValidation(".*/tableComarca/provincia")
				.rules(Constants.MODIFY_RULE_NAME))
				.setModifyParameter("provincia.code");
		
		((EjieEditableValidationConfigurer) validationConfigurer
				.addValidation(".*/tableComarca/comarca")
				.rules(Constants.MODIFY_RULE_NAME))
				.setModifyParameter("comarcaId");

		// Autocomplete, combo y select enlazados simples
//		((EjieEditableValidationConfigurer) validationConfigurer
//				.addValidation(".*/patrones/autocomplete/remoteEnlazadoComarca")
//				.forParameters("codeProvincia")
//				.rules(Constants.MODIFY_RULE_NAME))
//				.setModifyParameter("codeComarca");
//		((EjieEditableValidationConfigurer) validationConfigurer
//				.addValidation(".*/patrones/autocomplete/remoteEnlazadoLocalidad")
//				.forParameters("codeComarca")
//				.rules(Constants.MODIFY_RULE_NAME))
//				.setModifyParameter("codeLocalidad");
		
		((EjieEditableValidationConfigurer) validationConfigurer
				.addValidation(".*/patrones/.*")
				.forParameters("codeProvincia", "codeComarca")
				.rules(Constants.MODIFY_RULE_NAME))
				.setModifyParameter("codeLocalidad");
		
		((EjieEditableValidationConfigurer) validationConfigurer
				.addValidation(".*/patrones/.*")
				.forParameters("codeProvincia")
				.rules(Constants.MODIFY_RULE_NAME))
				.setModifyParameter("codeComarcaLocalidad");
		
		((EjieEditableValidationConfigurer) validationConfigurer
				.addValidation(".*/patrones/.*")
				.rules(Constants.MODIFY_RULE_NAME))
				.setModifyParameter("codeProvincia");
		
		((EjieEditableValidationConfigurer) validationConfigurer
				.addValidation(".*/patrones/.*")
				.rules(Constants.MODIFY_RULE_NAME))
				.setModifyParameter("codeComarca");

		// Autocomplete, combo y select enlazados múltiples
		((EjieEditableValidationConfigurer) validationConfigurer
				.addValidation(".*/patrones/.*")
				.forParameters("codeDepartamento", "codeProvincia")
				.rules(Constants.MODIFY_RULE_NAME))
				.setModifyParameter("codeDepartamentoProvincia");
		
		((EjieEditableValidationConfigurer) validationConfigurer
				.addValidation(".*/patrones/.*")
				.rules(Constants.MODIFY_RULE_NAME))
				.setModifyParameter("codeDepartamento");
		
		((EjieEditableValidationConfigurer) validationConfigurer
				.addValidation(".*/patrones/.*")
				.rules(Constants.MODIFY_RULE_NAME))
				.setModifyParameter("codeProvincia");		
	}
}
