<%--  
 -- Copyright 2020 E.J.I.E., S.A.
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
<%@include file="/WEB-INF/includeTemplate.inc"%>

<!-- URL a usar en formularios -->
<spring:url value="autocompleteEnlazadoMultiple/departamentoProvinciaDTO" var="remoto"/>

<section class="container-fluid">
	<h2 class="title mb-3"><spring:message code="patron.autocompleteEnlazadoMultiple" /></h2>
	
	<div class="form-row">
		<fieldset id="local" class="col-md col-12 px-3 my-md-0 my-2 mr-md-5">
			<legend>Local</legend>
			<div class="form-groupMaterial">
				<input id="abueloLocal" /> 
				<label for="abueloLocal">Abuelo</label>
			</div>
			<div class="form-groupMaterial">
				<input id="padreLocal" /> 
				<label for="padreLocal">Padre</label>
			</div>
			<div class="form-groupMaterial">
				<input id="hijoLocal" /> 
				<label for="hijoLocal">Hijo</label>
			</div>
		</fieldset>
		
		<fieldset id="remote" class="col-md col-12 px-3 my-md-0 my-2 mr-md-5">
			<legend>Remoto</legend>
			<form:form id="autocompleteEnlazadoMultipleRemoto_form" modelAttribute="departamentoProvinciaDTO" action="${remoto}" method="GET">
				<div class="form-groupMaterial">
					<form:input id="departamentoRemote" path="codeDepartamento" />
					<label for="departamentoRemote">Departamento</label>
				</div>
				
				<div class="form-groupMaterial">
					<form:input id="provinciaRemote" path="codeProvincia" />
					<label for="provinciaRemote">Provincia</label>
				</div>
				
				<div class="form-groupMaterial">
					<form:input id="dptoProvRemote" path="codeDepartamentoProvincia" />
					<label for="dptoProvRemote">Departamento-Provincia</label>
				</div>
			</form:form>
		</fieldset>
		
		<fieldset id="mixtoI" class="col-md col-12 px-3 my-md-0 my-2 mr-md-5">
			<legend>Mixto I (LOCAL, LOCAL y REMOTO)</legend>
			<form:form id="autocompleteEnlazadoMultipleRemoto_form" modelAttribute="departamentoProvinciaDTO" action="${remoto}" method="GET">
				<div class="form-groupMaterial">
					<!-- <form:input id="abueloMixtoI" path="codeDepartamento" items="${comboDepartamento}" itemLabel="entity.descEs" itemValue="id" /> -->
					<form:input id="abueloMixtoI" path="codeDepartamento" />
					<label for="abueloMixtoI">Departamento (local)</label>
				</div>
				<div class="form-groupMaterial">
					<!-- <form:input id="padreMixtoI" path="codeProvincia" items="${comboProvincia}" itemLabel="entity.descEs" itemValue="id" /> -->
					<form:input id="padreMixtoI" path="codeProvincia" />
					<label for="padreMixtoI">Provincia (local)</label>
				</div>
				<div class="form-groupMaterial">
					<form:input id="hijoMixtoI" path="codeDepartamentoProvincia" /> 
					<label for="hijoMixtoI">Departamento-Provincia (remoto)</label>
				</div>
			</form:form>
		</fieldset>
		
		<fieldset id="mixtoII" class="col-md col-12 px-3 my-md-0 my-2 mr-md-5">
			<legend>Mixto II (REMOTO, REMOTO y LOCAL)</legend>
			<form:form id="autocompleteEnlazadoMultipleRemoto_form" modelAttribute="departamentoProvinciaDTO" action="${remoto}" method="GET">
				<div class="form-groupMaterial">
					<form:input id="abueloMixtoII" path="codeDepartamento" /> 
					<label for="abueloMixtoII">Departamento (remoto)</label>
				</div>
				<div class="form-groupMaterial">
					<form:input id="padreMixtoII" path="codeProvincia" /> 
					<label for="padreMixtoII">Provincia (remoto)</label>
				</div>
				<div class="form-groupMaterial">
					<!-- <form:input id="hijoMixtoII" path="codeDepartamentoProvincia" items="${comboDepartamentoProvincia}" itemLabel="entity.descEs" itemValue="id" /> -->
					<form:input id="hijoMixtoII" path="codeDepartamentoProvincia" />
					<label for="hijoMixtoII">Departamento-Provincia (local)</label>
				</div>
			</form:form>
		</fieldset>
		
		<fieldset id="mixtoIII" class="col-md col-12 px-3 my-md-0 my-2">
			<legend>Mixto III (REMOTO, LOCAL y REMOTO)</legend>
			<form:form id="autocompleteEnlazadoMultipleRemoto_form" modelAttribute="departamentoProvinciaDTO" action="${remoto}" method="GET">
				<div class="form-groupMaterial">
					<form:input id="abueloMixtoIII" path="codeDepartamento" /> 
					<label for="abueloMixtoIII">Departamento (remoto)</label>
				</div>
				<div class="form-groupMaterial">
					<!-- <form:input id="padreMixtoIII" path="codeProvincia" items="${comboProvincia}" itemLabel="entity.descEs" itemValue="id" /> -->
					<form:input id="padreMixtoIII" path="codeProvincia" />
					<label for="padreMixtoIII">Provincia (local)</label>
				</div>
				<div class="form-groupMaterial">
					<form:input id="hijoMixtoIII" path="codeDepartamentoProvincia" /> 
					<label for="hijoMixtoIII">Departamento-Provincia (remoto)</label>
				</div>
			</form:form>
		</fieldset>
	</div>
</section>