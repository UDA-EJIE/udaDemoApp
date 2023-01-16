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
<spring:url value="comboEnlazadoMultiple/departamentoProvinciaDTO" var="remoto"/>

<h2 class="title mb-3">Combo Enlazado (múltiple)</h2>

<!-- Feedback -->
<div id="x21aAppWar_feedback"></div><br/>
 
<div class="container-fluid">
	<div class="form-row">
		<fieldset class="col-sm mr-sm-5">
			<legend>Local (no i18n)</legend>
			
			<div class="form-groupMaterial">
				<select id="departamento"></select>
				<label for="departamento">Departamento</label>
			</div>
			
			<div class="form-groupMaterial">
				<select id="provincia"></select>
				<label for="provincia">Provincia</label>
			</div>
			
			<div class="form-groupMaterial">
				<select id="dptoProv"></select>
				<label for="dptoProv">Departamento-Provincia</label>
			</div>
		</fieldset>
	
		<fieldset class="col-sm">
			<legend>Remoto</legend>
			<form:form id="departamentoProvincia_form" modelAttribute="departamentoProvinciaDTO" action="${remoto}" method="GET">
				<div class="form-groupMaterial">
					<form:select id="departamentoRemote" path="codeDepartamento" />
					<label for="departamentoRemote">Departamento</label>
				</div>
				
				<div class="form-groupMaterial">
					<form:select id="provinciaRemote" path="codeProvincia" />
					<label for="provinciaRemote">Provincia</label>
				</div>
				
				<div class="form-groupMaterial">
					<form:select id="dptoProvRemote" path="codeDepartamentoProvincia" />
					<label for="dptoProvRemote">Departamento-Provincia</label>
				</div>
			</form:form>
		</fieldset>
	</div>
</div>

<div class="container-fluid mt-4">
	<div class="form-row">
		<fieldset class="col-sm mr-sm-5">
			<legend>Mixto I</legend>
			<form:form id="departamentoProvinciaMixto1_form" modelAttribute="departamentoProvinciaDTO" action="${remoto}" method="GET">
				<div class="form-groupMaterial">
					<form:select id="mixto_departamentoRemote" path="codeDepartamento" />
					<label for="mixto_departamentoRemote">Departamento (remoto)</label>
				</div>
				
				<div class="form-groupMaterial">
					<form:select id="mixto_provincia" path="codeProvincia" items="${comboProvincia}" itemLabel="entity.descEs" itemValue="id" />
					<label for="mixto_provincia">Provincia (local)</label>
				</div>
				
				<div class="form-groupMaterial">
					<form:select id="mixto_dptoProvRemote" path="codeDepartamentoProvincia" />
					<label for="mixto_dptoProvRemote">Departamento-Provincia (remoto)</label>
				</div>
			</form:form>
		</fieldset>

		<fieldset class="col-sm">
			<legend>Mixto II</legend>
			<form:form id="departamentoProvinciaMixto2_form" modelAttribute="departamentoProvinciaDTO" action="${remoto}" method="GET">
				<div class="form-groupMaterial">
					<form:select id="mixto2_departamento" path="codeDepartamento" items="${comboDepartamento}" itemLabel="entity.descEs" itemValue="id" />
					<label for="mixto2_departamento">Departamento (local)</label>
				</div>
				
				<div class="form-groupMaterial">
					<form:select id="mixto2_provinciaRemote" path="codeProvincia" />
					<label for="mixto2_provinciaRemote">Provincia (remoto)</label>
				</div>
				
				<div class="form-groupMaterial">
					<form:select id="mixto2_dptoProv" path="codeDepartamentoProvincia" items="${comboDepartamentoProvincia}" itemLabel="entity.descEs" itemValue="id" />
					<!-- <form:select id="mixto2_dptoProv" path="codeDepartamentoProvincia" items="${comboDepartamentoProvincia}" /> -->
					<label for="mixto2_dptoProv">Departamento-Provincia (local)</label>
				</div>
			</form:form>
		</fieldset>
	</div>
</div>