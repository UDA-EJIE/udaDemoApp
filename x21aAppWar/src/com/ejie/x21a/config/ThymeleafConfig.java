package com.ejie.x21a.config;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DelegatingWebMvcConfiguration;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.context.Context;
import com.ejie.x38.util.StaticsContainer;



public class ThymeleafConfig extends DelegatingWebMvcConfiguration  {
	private static final Logger logger = LoggerFactory.getLogger(ThymeleafConfig.class);

	@Bean
    public SpringTemplateEngine templateEngine() {
        //StaticsContainer.setBaseUrl(request);
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver());
        Context context = new Context();
        String staticsUrl = StaticsContainer.getStaticsUrl();
        //staticsUrl = StaticsContainer.getBaseUrl() + staticsUrl;
        context.setVariable("staticsUrl", staticsUrl);
        return templateEngine;
    }

    @Bean
    public SpringResourceTemplateResolver templateResolver() {
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
        templateResolver.setPrefix("/WEB-INF/views/");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode(TemplateMode.HTML);
        // Habilita los logs para depurar el archivo cargado
        templateResolver.setCacheable(false); // Deshabilita la caché para que se pueda ver cada carga
        templateResolver.setCheckExistence(true); // Verifica si los archivos realmente existen
        templateResolver.setCharacterEncoding("UTF-8");
        // Logger para depuración, muestra el archivo cargado
        //templateResolver.addTemplateAlias("welcome", "welcome");
        logger.info("Cargando plantillas desde: /WEB-INF/views/");        
        return templateResolver;
    }
    
    @Bean
    public ThymeleafViewResolver viewResolver(HttpServletRequest request){
        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
        viewResolver.setTemplateEngine(templateEngine());
        // NOTE 'order' and 'viewNames' are optional
        viewResolver.setOrder(1);
        viewResolver.setViewNames(new String[] {"*.html"});
        logger.info("ThymeleafViewResolver configurado para procesar vistas .html");
        ThymeleafConfig.logger.info("[ENTROOO] : viewResolver");
        return viewResolver;
    }
}