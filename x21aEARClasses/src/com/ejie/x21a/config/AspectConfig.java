package com.ejie.x21a.config;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Configuración de los aspectos.
 */
@Aspect
public class AspectConfig {

	@Autowired
	private LogConfig logConfig;

	@Around("execution(* com.ejie.*.service..*.*(..))")
	public void serviceLogAspect() {
		logConfig.mainAdvice();
	}

	@Around("execution(* com.ejie.*.dao..*.*(..))")
	public void daoLogAspect() {
		logConfig.mainAdvice();
	}

	@AfterThrowing("execution(* com.ejie.*.service..*.*(..)) &amp;&amp;target(target)")
	public void incidenceLogAspect() {
		logConfig.mainAdvice();
	}

}
