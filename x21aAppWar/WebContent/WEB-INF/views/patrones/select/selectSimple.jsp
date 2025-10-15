<%--  
 -- Copyright 2011 E.J.I.E., S.A.
 --
 -- Licencia con arreglo a la EUPL, Versión 1.1 exclusivamente (la «Licencia»);
 -- Solo podrá usarse esta obra si se respeta la Licencia.
 -- Puede obtenerse una copia de la Licencia en
 --
 --      http://ec.europa.eu/idabc/eupl.html
 --
 -- Salvo cuando lo exija la legislación aplicable o se acuerde por escrito, 
 -- el programa distribuido con arreglo a la Licencia se distribuye «TAL CUAL»,
 -- SIN GARANTÍAS NI CONDICIONES DE NINGÚN TIPO, ni expresas ni implícitas.
 -- Véase la Licencia en el idioma concreto que rige los permisos y limitaciones
 -- que establece la Licencia.
 --%>

<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="/WEB-INF/tld/spring.tld"%>
<%@taglib prefix="form" uri="/WEB-INF/tld/spring-form.tld"%>

<!-- URL a usar en formularios -->
<spring:url value="comboSimple/remote" var="remoto"/>
<spring:url value="comboSimple/remoteGroup" var="remotoAgrupado"/>

<h2>
	<spring:message code="selectSimple.title" />
</h2>
<div class="container-fluid">
	<div class="row">
		<div class="form-groupMaterial col-sm">
			<select id="selectSimple"></select>  
			<label for="selectSimple">Select simple local</label>
		</div>
		<form:form id="selectRemoto_form" modelAttribute="provincia" action="${remoto}" method="GET">
			<div class="form-groupMaterial col-sm">
				<form:select id="selectRemoto" path="code" />
				<label for="selectRemoto">Select remoto</label>
			</div>
		</form:form>
		<div class="form-groupMaterial col-sm">
			<select id="selectLargoMulti"></select>  
			<label for="selectLargoMulti">Select Multi con texto largo</label>
		</div>
		<div class="form-groupMaterial col-sm">
			<select id="selectLargo"></select>  
			<label for="selectLargo">Select Simple con texto largo</label>
		</div>
	</div>
</div>

<div class="container-fluid mt-4">
	<div class="row">
		<div class="form-groupMaterial col-sm">
			<select id="selectGrupos"></select>  
			<label for="selectGrupos">Select con 'optgroups'</label>
		</div>
		<form:form id="selectGruposRemoto_form" modelAttribute="divisionTerritorialDto" action="${remotoAgrupado}" method="GET">
			<div class="form-groupMaterial col-sm">
				<form:select id="selectGruposRemoto" path="code" /> 
				<label for="selectGruposRemoto">Select con 'optgroups' remoto</label>
			</div>
		</form:form>
		<div class="form-groupMaterial col-sm">
			<select id="selectImgs"></select>  
			<label for="selectImgs">Select (no i18n) con imagenes</label>
		</div>
		<div class="form-groupMaterial col-sm">
			<select id="SelectLoadFromSelect">
				<option value="1">Alava</option>
				<option value="3">Gipuzcoa</option>
				<option value="2" selected="selected">Vizcaya</option>
			</select>  
			<label for="SelectLoadFromSelect">Select carga inicial desde <strong>HTML</strong></label>
		</div>
	</div>
</div>
