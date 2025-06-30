<!doctype html>

<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/includeTemplate.inc" %>
<%@ taglib prefix="tiles" uri="/WEB-INF/tld/tiles-jsp.tld" %>

<html class="no-js" lang="">
<head>

	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta http-equiv="Content-Type" content="text/html;charset=UTF-8" />
	<title><spring:message code="app.title" /></title>
	<meta name="description" content="">
  <meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="apple-touch-icon" href="apple-touch-icon.png">

	<%-- Estilos RUP sin minimizar (DESARROLLO) --%>
<%-- <%@include file="/WEB-INF/layouts/includes/rup.styles.inc"%> --%>
	<%-- Estilos RUP sin minimizar (PRODUCCION) --%>
	<%@include file="/WEB-INF/layouts/includes/rup.styles.min.inc" %>
	<%-- Estilos Aplicacion --%>
	<%@include file="/WEB-INF/layouts/includes/aa79b.styles.inc"%>

</head>
	<body>
	<div class="aa79b-content aa79b-fade in">
		<div class="contenedor">
			<!-- Cabecera -->
			<tiles:insertAttribute name="header" />

			<!-- Menu -->
			<tiles:insertAttribute name="menu" />

			<!-- Contenidos -->
			<div class="content" >
				<tiles:insertAttribute name="content"/>
			</div>

			<!-- Pie -->
			<tiles:insertAttribute name="footer" />

			<!-- Includes JS -->
			<tiles:insertAttribute name="base-includes" />
		</div>
		</div>
		
		<!-- MOMO -->
<script type="text/javascript"
	src="${appConfiguration['momo.w43ta.web.protocol']}${appConfiguration['momo.w43ta.web.url']}/w43taAuditApiWAR/js/w43ta-momo-navigation.js"></script>
<script type="text/javascript">
	var t = new W43taMomoNavigation(
			'${appConfiguration["momo.servicio"]}', 
			'${appConfiguration["momo.app"]}', 
			'${appConfiguration["momo.securityTokenId"]}', 
			'${appConfiguration["momo.w43ta.web.url"]}', 
			'${appConfiguration["momo.w43ta.web.port"]}',
			${appConfiguration["momo.diskCookie"]},
			${appConfiguration["momo.develomentMode"]});
</script>
	</body>
</html>