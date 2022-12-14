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

<h2 class="title mb-3">Combo</h2>

<div class="container-fluid">
		<div class="form-row">
			<div class="form-groupMaterial col-sm">
				<select id="combo"></select>
				<label for="combo">Combo local</label>
			</div>
			<div class="form-groupMaterial col-sm">
				<form:form id="provincia_form" modelAttribute="provincia" action="${remoto}" method="GET">
					<form:select id="comboRemoto" path="code">
						<form:option value=" ">---</form:option>
					</form:select>
					<label for="comboRemoto">Combo remoto</label>
				</form:form>
			</div>
			<div class="form-groupMaterial col-sm">
				<select id="comboLargo"></select>
				<label for="comboLargo">Combo con texto largo</label>
			</div>
		</div>
</div>

<div class="container-fluid mt-4">
		<div class="form-row">
			<div class="form-groupMaterial col-sm">
				<select id="comboGrupos"></select>
				<label for="comboGrupos">Combo con 'optgroups'</label>
			</div>
			<div class="form-groupMaterial col-sm">
				<form:form id="divisionTerritorial_form" modelAttribute="divisionTerritorialDto" action="${remotoAgrupado}" method="GET">
					<form:select id="comboGruposRemoto" path="code">
						<form:option value=" ">---</form:option>
					</form:select>
					<label for="comboGruposRemoto">Combo con 'optgroups' remoto</label>
				</form:form>
			</div>
			<div class="form-groupMaterial col-sm">
				<select id="comboImgs"></select>
				<label for="comboImgs">Combo (no i18n) con imágenes</label>
			</div>
		</div>
</div>

<div class="container-fluid mt-4">
	<div class="form-row">
		<div class="form-groupMaterial col-sm">
			<input type="text" name="comboInput" id="comboInput" value="python" />
			<label for="comboInput">Combo sobre <strong>Input</strong></label>
		</div>
		<div class="form-groupMaterial col-sm">
			<select id="comboLoadFromSelect">
				<option value="1">Álava</option>
				<option value="3">Gipúzcoa</option>
				<option value="2" selected="selected">Vizcaya</option>
			</select>
			<label for="comboLoadFromSelect">Combo carga inicial desde <strong>HTML</strong></label>
		</div>
	</div>
</div>
