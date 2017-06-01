<!doctype html>
<%--  
 -- Copyright 2013 E.J.I.E., S.A.
 -- Licencia con arreglo a la EUPL, VersiÃ³n 1.1 exclusivamente (la Â«LicenciaÂ»);
 -- Solo podrÃ¡ usarse esta obra si se respeta la Licencia.
 -- Puede obtenerse una copia de la Licencia en
 -- 
 -- http://ec.europa.eu/idabc/eupl.html
 -- 
 -- Salvo cuando lo exija la legislaciÃ³n aplicable o se acuerde por escrito,
 -- el programa distribuido con arreglo a la Licencia se distribuye Â«TAL CUALÂ»,
 -- SIN GARANTÃAS NI CONDICIONES DE NINGÃN TIPO, ni expresas ni implÃ­citas.
 -- VÃ©ase la Licencia en el idioma concreto que rige los permisos y limitaciones
 -- que establece la Licencia. 
 --%>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/includeTemplate.inc" %>
<%@ taglib prefix="tiles" uri="/WEB-INF/tld/tiles-jsp.tld" %>

<html class="no-js" lang=""> 
<head>
	
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title><spring:message code="app.title" /></title>
	<meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="apple-touch-icon" href="apple-touch-icon.png">
	
	
	<%-- Estilos RUP sin minimizar (DESARROLLO) --%>
<%-- <%@include file="/WEB-INF/layouts/includes/rup.styles.inc"%> --%>
	<%-- Estilos RUP sin minimizar (PRODUCCION) --%>
	<%@include file="/WEB-INF/layouts/includes/rup.styles.min.inc" %>
	<%-- Estilos Aplicacion --%>
	<%@include file="/WEB-INF/layouts/includes/x21a.styles.inc"%>
	
</head>	
<body >


		<div class="contenedor" >
			<!-- Cabecera -->
			<tiles:insertAttribute name="header" />
			
			<!-- Idioma -->
	<%-- 		<tiles:insertAttribute name="language" /><br/> --%>
			
			<!-- Menu -->
			<tiles:insertAttribute name="menu" />
				
			<!-- Migas de pan -->
			<tiles:insertAttribute name="breadCrumb" /><br/>
			
			<!-- Contenidos -->
			
			<tiles:insertAttribute name="content"/>
			
			<!-- Pie -->
			<tiles:insertAttribute name="footer" />
			
			<!-- Includes JS -->
			<tiles:insertAttribute name="base-includes" />
			<tiles:insertAttribute name="includes" />
		</div>
	
</body>
</html>