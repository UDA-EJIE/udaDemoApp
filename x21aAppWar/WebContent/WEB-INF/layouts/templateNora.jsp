<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%--  
 -- Copyright 2013 E.J.I.E., S.A.
 -- Licencia con arreglo a la EUPL, Versión 1.1 exclusivamente (la «Licencia»);
 -- Solo podrá usarse esta obra si se respeta la Licencia.
 -- Puede obtenerse una copia de la Licencia en
 -- 
 -- http://ec.europa.eu/idabc/eupl.html
 -- 
 -- Salvo cuando lo exija la legislación aplicable o se acuerde por escrito,
 -- el programa distribuido con arreglo a la Licencia se distribuye «TAL CUAL»,
 -- SIN GARANTÍAS NI CONDICIONES DE NINGÚN TIPO, ni expresas ni implícitas.
 -- Véase la Licencia en el idioma concreto que rige los permisos y limitaciones
 -- que establece la Licencia. 
 --%>
<%@ include file="/WEB-INF/includeTemplate.inc"%>
<%@ taglib prefix="tiles" uri="/WEB-INF/tld/tiles-jsp.tld"%>

<html>
<head>
	<%@include file="/WEB-INF/includeTemplate.inc"%>
	<title><spring:message code="app.title" /></title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<meta http-equiv="X-UA-Compatible" content="IE=edge" />

	<%-- Estilos RUP sin minimizar (DESARROLLO) --%>
<%-- <%@include file="/WEB-INF/layouts/includes/rup.styles.inc"%> --%>
	<%-- Estilos RUP sin minimizar (PRODUCCIÓN) --%>
	<%@include file="/WEB-INF/layouts/includes/rup.styles.min.inc" %>
	<%-- Estilos Aplicación --%>	
	<%@include file="/WEB-INF/layouts/includes/x21a.styles.inc"%>

<link rel="stylesheet"
	href="http://www1.geo.jakina.ejiedes.net/sidl/css/geolocalizador.css"
	type="text/css" />
<link rel="stylesheet"
	href="http://www1.geo.jakina.ejiedes.net/sidl/css/openlayers/nora-theme/nora-style.css"
	type="text/css" />
<link
	href="http://www1.geo.jakina.ejiedes.net/sidl/css/themes/default.css"
	rel="stylesheet" type="text/css" />
<link
	href="http://www1.geo.jakina.ejiedes.net/sidl/css/themes/alphacube.css"
	rel="stylesheet" type="text/css" />

<style>
#map {
	float: left;
	margin-right: 100px;
	padding-right: 100px;
	background-color: #ffffff;
	width: 94%;
	height: 90%;
	vertical-align: top;
}

#panel {
	margin-top: 5px;
	margin-left: 1px;
	margin-right: 1px;
	width: 100%;
	height: 5%;
}

#panel div {
	margin-right: 5px;
	margin-bottom: 5px;
}

#container {
	float: left;
	background-color: #ffffff;
	width: 680;
	height: 600px;
	left: 10px;
}
</style>
</head>
<body>
	<div class="contenedor">
		<!-- Cabecera -->
		<tiles:insertAttribute name="header" />

		<!-- Idioma -->
		<tiles:insertAttribute name="language" />
		<br />

		<!-- Menu -->
		<tiles:insertAttribute name="menu" />

		<!-- Migas de pan -->
		<tiles:insertAttribute name="breadCrumb" />
		<br />

		<!-- Contenidos -->
		<tiles:insertAttribute name="content" />

		<!-- Pie -->
		<tiles:insertAttribute name="footer" />

		<!-- Includes JS -->
		<tiles:insertAttribute name="base-includes" />
		<tiles:insertAttribute name="includes" />

		<!-- Includes NORA -->
		<tiles:insertAttribute name="base-includesNora" />
		<tiles:insertAttribute name="includesNora" />
	</div>
</body>
</html>