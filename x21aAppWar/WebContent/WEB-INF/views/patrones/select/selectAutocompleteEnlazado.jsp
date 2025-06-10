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

<section class="container-fluid">
	<h2 class="title mb-3">
		<spring:message code="selectAutocompleteEnlazado.title" />
	</h2>
	
	<div class="row">
		<fieldset id="local" class="col-md col-12 px-3">
			<legend>Local</legend>
			<div class="form-groupMaterial">
				<select id="abueloLocal" ></select> 
				<label for="abueloLocal">Abuelo</label>
			</div>
			<div class="form-groupMaterial">
				<select id="padreLocal" ></select> 
				<label for="padreLocal">Padre</label>
			</div>
			<div class="form-groupMaterial">
				<select id="hijoLocal" ></select>  
				<label for="hijoLocal">Hijo</label>
			</div>
		</fieldset>
		
		<fieldset id="remote" class="col-md col-12 px-3 mx-md-5 my-md-0 my-4">
			<legend>Remoto</legend>
			<form:form id="selectRemoto_form" modelAttribute="provinciaComarcaLocalidadDTO" action="${remoto}" method="GET">
				<div class="form-groupMaterial">
					<form:select id="abueloRemoto" path="codeProvincia"  /> 
					<label for="abueloRemoto">Abuelo</label>
				</div>
				<div class="form-groupMaterial">
					<form:select id="padreRemoto" path="codeComarca" /> 
					<label for="padreRemoto">Padre</label>
				</div>
				<div class="form-groupMaterial">
					<form:select id="hijoRemoto" path="codeLocalidad" /> 
					<label for="hijoRemoto">Hijo</label>
				</div>
			</form:form>
		</fieldset>
		
		<fieldset id="mixto" class="col-md col-12 px-3">
			<legend>Mixto I</legend>
			<form:form id="selectRemoto_form" modelAttribute="provinciaComarcaLocalidadDTO" action="${remoto}" method="GET">
				<div class="form-groupMaterial">
					<form:select id="abueloMixto" path="codeProvincia" />
					<label for="abueloMixto">Abuelo</label>
				</div>
				<div class="form-groupMaterial">
					<form:select id="padreMixto" path="codeComarca" />
					<label for="padreMixto">Padre</label>
				</div>
				<div class="form-groupMaterial">
					<form:select id="hijoMixto" path="codeLocalidad" />
					<label for="hijoMixto">Hijo</label>
				</div>
			</form:form>
		</fieldset>
	</div>
</section>