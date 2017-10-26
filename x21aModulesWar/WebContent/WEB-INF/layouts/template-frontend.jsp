<!doctype html>
<%--  
 -- Copyright 2011 E.J.I.E., S.A.
 -- Licencia con arreglo a la EUPL, Versión 1.1 exclusivamente (la «Licencia»);
 -- Solo podrá usarse esta obra si se respeta la Licencia.
 -- Puede obtenerse una copia de la Licencia en
 -- 
 -- http://ec.europa.eu/idabc/eupl.html
 -- 
 -- Salvo cuando lo exija la legislación aplicable o se acuerde por escrito,
 -- el programa distribuido con arreglo a la Licencia se distribuye «TAL CUAL»,
 -- SIN GARANTÍAS NI CONDICIONES DE NINGÚN TIPO, ni expresas ni implícitas.
 -- Véase la Licencia en el idioma concreto que rige los permisos y limitaciones
 -- que establece la Licencia. 
 --%>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/includeTemplate.inc" %>
<%@ taglib prefix="tiles" uri="/WEB-INF/tld/tiles-jsp.tld" %>

 <html class="no-js" lang="">

<head>
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	
	<title><spring:message code="app.title" /></title>
	<meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="apple-touch-icon" href="apple-touch-icon.png">
	
	
	<%-- Estilos RUP sin minimizar (DESARROLLO) --%>
<%-- <%@include file="/WEB-INF/layouts/includes/rup.styles.inc"%> --%>
	<%-- Estilos RUP sin minimizar (PRODUCCION) --%>
<%-- 	<%@include file="/WEB-INF/layouts/includes/rup.styles.min.inc" %> --%>
	<%-- Estilos RUP para integración con portales --%>
<%-- 	<%@include file="/WEB-INF/layouts/includes/rup.styles.portal.inc" %> --%>
	<%-- Estilos Aplicacion --%>
<%-- 	<%@include file="/WEB-INF/layouts/includes/x21a.styles.inc"%> --%>
	
</head>	
	<body>
	
	
<!-- 		<div class="contenedor" > -->
			<!-- Cabecera -->
<%-- 			<tiles:insertAttribute name="header" /> --%>
			
			<!-- Idioma -->
	<%-- 		<tiles:insertAttribute name="language" /><br/> --%>
			
			<!-- Menu -->
<%-- 			<tiles:insertAttribute name="menu" /> --%>
				
			<!-- Migas de pan -->
<%-- 			<tiles:insertAttribute name="breadCrumb" /> --%>
			
			<!-- Contenidos -->
<!-- 			<div class="content" > -->
<%-- 				<tiles:insertAttribute name="content"/> --%>
<!-- 			</div> -->
			<!-- Pie -->
<%-- 			<tiles:insertAttribute name="footer" /> --%>
			
			<!-- Includes JS -->
<%-- 			<tiles:insertAttribute name="base-includes" /> --%>
<script>
      var	APP_RESOURCES = 'assets/x21a',
      	CTX_PATH = '/x21aResponsive/',
      	STATICS = '',
      	RUP = '/assets',
      	WAR_NAME = "x21aPilotoPatrones",
      	//model
      	LAYOUT = "horizontal",
          RESOURCES_PATH = '../i18n/',
      	//mvc-config.xml
      	LOCALE_COOKIE_NAME = "language",
      	LOCALE_PARAM_NAME = "locale",
      	AVAILABLE_LANGS = "es,eu,en,fr",
      	//breadCrumbs
      	LOGGED_USER = "NULL",
      	DESTROY_XLNETS_SESSION = "false";

        
      </script>
			<app-root>


  			</app-root>

			<tiles:insertAttribute name="includes" />
<!-- 		</div> -->
		
	</body>
</html>