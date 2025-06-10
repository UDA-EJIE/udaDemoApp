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
		<spring:message code="selectAutocompleteEnlazadoMultiple.title"/>
	</h2>
	
	<div class="row">
		<fieldset id="local" class="col-md col-12 px-3 my-md-0 my-2 me-md-5">
			<legend>Local</legend>
			<div class="form-groupMaterial">
				<select id="abueloLocal" ></select>
				<label for="abueloLocal">Abuelo</label>
			</div>
			<div class="form-groupMaterial">
				<select id="padreLocal"></select> 
				<label for="padreLocal">Padre</label>
			</div>
			<div class="form-groupMaterial">
				<select id="hijoLocal"></select> 
				<label for="hijoLocal">Hijo</label>
			</div>
		</fieldset>
		
		<fieldset id="remote" class="col-md col-12 px-3 my-md-0 my-2 me-md-5">
			<legend>Remoto</legend>
			<div class="form-groupMaterial">
				<select id="abueloRemoto" name="departamento"></select> 
				<label for="abueloRemoto">Abuelo</label>
			</div>
			<div class="form-groupMaterial">
				<select id="padreRemoto" name="provincia"></select> 
				<label for="padreRemoto">Padre</label>
			</div>
			<div class="form-groupMaterial">
				<select id="hijoRemoto" name="departamentoProvincia"></select> 
				<label for="hijoRemoto">Hijo</label>
			</div>
		</fieldset>
		
		<fieldset id="mixtoI" class="col-md col-12 px-3 my-md-0 my-2 me-md-5">
			<legend>Mixto I (LOCAL, LOCAL y REMOTO)</legend>
			<div class="form-groupMaterial">
				<select id="abueloMixtoI" name="departamento"></select> 
				<label for="abueloMixtoI">Abuelo</label>
			</div>
			<div class="form-groupMaterial">
				<select id="padreMixtoI" name="provincia"></select> 
				<label for="padreMixtoI">Padre</label>
			</div>
			<div class="form-groupMaterial">
				<select id="hijoMixtoI" name="departamentoProvincia"></select> 
				<label for="hijoMixtoI">Hijo</label>
			</div>
		</fieldset>
		
		<fieldset id="mixtoII" class="col-md col-12 px-3 my-md-0 my-2 me-md-5">
			<legend>Mixto II (REMOTO, REMOTO y LOCAL)</legend>
			<div class="form-groupMaterial">
				<select id="abueloMixtoII" name="departamento"></select> 
				<label for="abueloMixtoII">Abuelo</label>
			</div>
			<div class="form-groupMaterial">
				<select id="padreMixtoII" name="provincia"></select> 
				<label for="padreMixtoII">Padre</label>
			</div>
			<div class="form-groupMaterial">
				<select id="hijoMixtoII" name="departamentoProvincia"></select> 
				<label for="hijoMixtoII">Hijo</label>
			</div>
		</fieldset>
		
		<fieldset id="mixtoIII" class="col-md col-12 px-3 my-md-0 my-2">
			<legend>Mixto III (REMOTO, LOCAL y REMOTO)</legend>
			<div class="form-groupMaterial">
				<select id="abueloMixtoIII" name="departamento"></select> 
				<label for="abueloMixtoIII">Abuelo</label>
			</div>
			<div class="form-groupMaterial">
				<select id="padreMixtoIII" name="provincia"></select> 
				<label for="padreMixtoIII">Padre</label>
			</div>
			<div class="form-groupMaterial">
				<select id="hijoMixtoIII" name="departamentoProvincia"></select> 
				<label for="hijoMixtoIII">Hijo</label>
			</div>
		</fieldset>
	</div>
</section>