/*
* Copyright 2024 E.J.I.E., S.A.
*
* Licencia con arreglo a la EUPL, Versión 1.1 exclusivamente (la «Licencia»);
* Solo podrá usarse esta obra si se respeta la Licencia.
* Puede obtenerse una copia de la Licencia en
*
* http://ec.europa.eu/idabc/eupl.html
*
* Salvo cuando lo exija la legislación aplicable o se acuerde por escrito,
* el programa distribuido con arreglo a la Licencia se distribuye «TAL CUAL»,
* SIN GARANTÍAS NI CONDICIONES DE NINGÚN TIPO, ni expresas ni implícitas.
* Véase la Licencia en el idioma concreto que rige los permisos y limitaciones
* que establece la Licencia.
*/

package com.ejie.x21a.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;

import com.ejie.x38.util.StaticsContainer;

/**
 * Configuración de Spring MVC.
 */
@Configuration
@EnableWebMvc
public class MvcConfig implements WebMvcConfigurer {

	/*@Autowired
	private ServiceConfig serviceConfig;*/

	/**
	 * Gestiona la locale (idioma) mediante una cookie.
	 * 
	 * @param webApplicationContext Contexto de la aplicación Web.
	 * @return La cookie de idioma.
	 */
	@Bean
	public CookieLocaleResolver localeResolver(WebApplicationContext webApplicationContext) {
		final CookieLocaleResolver cookieLocaleResolver = new CookieLocaleResolver();
		cookieLocaleResolver.setCookieName("language");
		cookieLocaleResolver.setCookieHttpOnly(false);
		cookieLocaleResolver.setCookieSecure(StaticsContainer.isCookieSecure());
		cookieLocaleResolver.setCookiePath(
				(StaticsContainer.isCookiePathRoot() ? "/" : webApplicationContext.getServletContext().getContextPath())
						+ "; SameSite=Lax;");
		return cookieLocaleResolver;
	}

	/**
	 * Recursos idiomáticos (i18n).
	 * 
	 * @return MessageSource.
	 */
	/*@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		//messageSource.setParentMessageSource(serviceConfig.appMessageSource());
		messageSource.setBasename("/WEB-INF/resources/x21aApp.i18n");
		messageSource.setDefaultEncoding("UTF-8");
		messageSource.setUseCodeAsDefaultMessage(true);
		messageSource.setFallbackToSystemLocale(false);
		return messageSource;
	}

	@Bean
	public ConversionServiceFactoryBean getConversionService() {
		ConversionServiceFactoryBean bean = new ConversionServiceFactoryBean();
		bean.setConverters(getConverters());
		return bean;
	}*/

	/**
	 * Configurar validaciones.
	 * 
	 * @return Validaciones.
	 */
	/*@Bean
	public LocalValidatorFactoryBean validator() {
		LocalValidatorFactoryBean validatorFactoryBean = new LocalValidatorFactoryBean();
		validatorFactoryBean.setValidationMessageSource(messageSource());
		return validatorFactoryBean;
	}

	@Override
	public Validator getValidator() {
		return validator();
	}*/

	/**
	 * Permite definir converters personalizados.
	 * 
	 * @return Converters personalizados.
	 */
	/*private Set<Converter> getConverters() {
		Set<Converter> converters = new HashSet<Converter>();

		// Converter para el tratamiento de las fechas.
		converters.add(new DateConverter());

		return converters;
	}*/

}
