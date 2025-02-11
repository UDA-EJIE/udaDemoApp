package com.ejie.x21a.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.config.MethodInvokingFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ejie.x38.aop.MainAdvice;
import com.ejie.x38.log.DaoLoggingAdviceImpl;
import com.ejie.x38.log.IncidenceLoggingAdviceImpl;
import com.ejie.x38.log.LoggingManagerImpl;
import com.ejie.x38.log.ServiceLoggingAdviceImpl;

/**
 * Configuración de las trazas.
 */
@Configuration
public class LogConfig {

	/**
	 * Especifica la inicializacion de las trazas mediante logback.
	 * 
	 * @return MethodInvokingFactoryBean.
	 */
	@Bean
	public MethodInvokingFactoryBean logSystemInitializer() {
		MethodInvokingFactoryBean methodInvoking = new MethodInvokingFactoryBean();

		List<String> arguments = new ArrayList<>();
		// Se especifica la ubicación del fichero de configuración de logback (puede ser
		// una ruta del classpath o absoluta).
		arguments.add("x21a/logback.xml");
		// Se especifica si se desea que se pinte el estado de la configuración de
		// logback por la salida de log correspondiente.
		arguments.add("true");

		methodInvoking.setStaticMethod("com.ejie.x38.log.LogbackConfigurer.initLogging");
		methodInvoking.setArguments(arguments);

		return methodInvoking;
	}

	@Bean
	public LoggingManagerImpl loggingManager() {
		return new LoggingManagerImpl();
	}

	@Bean
	public ServiceLoggingAdviceImpl serviceLoggingAdvice() {
		ServiceLoggingAdviceImpl loggingAdvice = new ServiceLoggingAdviceImpl();
		loggingAdvice.setLoggingManager(loggingManager());
		return loggingAdvice;
	}

	@Bean
	public DaoLoggingAdviceImpl daoLoggingAdvice() {
		DaoLoggingAdviceImpl loggingAdvice = new DaoLoggingAdviceImpl();
		loggingAdvice.setLoggingManager(loggingManager());
		return loggingAdvice;
	}

	@Bean
	public IncidenceLoggingAdviceImpl incidenceLoggingAdvice() {
		IncidenceLoggingAdviceImpl loggingAdvice = new IncidenceLoggingAdviceImpl();
		loggingAdvice.setLoggingManager(loggingManager());
		return loggingAdvice;
	}

	@Bean
	public MainAdvice mainAdvice() {
		MainAdvice mainAdvice = new MainAdvice();
		mainAdvice.setServiceLoggingAdvice(serviceLoggingAdvice());
		mainAdvice.setDaoLoggingAdvice(daoLoggingAdvice());
		mainAdvice.setIncidenceLoggingAdvice(incidenceLoggingAdvice());
		return mainAdvice;
	}

}
