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
	<h2><spring:message code="patron.autocompleteEnlazadoMultiple" /></h2>
	<hr>
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
			<div class="form-groupMaterial">
				<input id="abueloRemoto" name="departamento" /> 
				<label for="abueloRemoto">Abuelo</label>
			</div>
			<div class="form-groupMaterial">
				<input id="padreRemoto" name="provincia" /> 
				<label for="padreRemoto">Padre</label>
			</div>
			<div class="form-groupMaterial">
				<input id="hijoRemoto" name="departamentoProvincia" /> 
				<label for="hijoRemoto">Hijo</label>
			</div>
		</fieldset>
		
		<fieldset id="mixtoI" class="col-md col-12 px-3 my-md-0 my-2 mr-md-5">
			<legend>Mixto I (LOCAL, LOCAL y REMOTO)</legend>
			<div class="form-groupMaterial">
				<input id="abueloMixtoI" name="departamento" /> 
				<label for="abueloMixtoI">Abuelo</label>
			</div>
			<div class="form-groupMaterial">
				<input id="padreMixtoI" name="provincia" /> 
				<label for="padreMixtoI">Padre</label>
			</div>
			<div class="form-groupMaterial">
				<input id="hijoMixtoI" name="departamentoProvincia" /> 
				<label for="hijoMixtoI">Hijo</label>
			</div>
		</fieldset>
		
		<fieldset id="mixtoII" class="col-md col-12 px-3 my-md-0 my-2 mr-md-5">
			<legend>Mixto II (REMOTO, REMOTO y LOCAL)</legend>
			<div class="form-groupMaterial">
				<input id="abueloMixtoII" name="departamento" /> 
				<label for="abueloMixtoII">Abuelo</label>
			</div>
			<div class="form-groupMaterial">
				<input id="padreMixtoII" name="provincia" /> 
				<label for="padreMixtoII">Padre</label>
			</div>
			<div class="form-groupMaterial">
				<input id="hijoMixtoII" name="departamentoProvincia" /> 
				<label for="hijoMixtoII">Hijo</label>
			</div>
		</fieldset>
		
		<fieldset id="mixtoIII" class="col-md col-12 px-3 my-md-0 my-2">
			<legend>Mixto III (REMOTO, LOCAL y REMOTO)</legend>
			<div class="form-groupMaterial">
				<input id="abueloMixtoIII" name="departamento" /> 
				<label for="abueloMixtoIII">Abuelo</label>
			</div>
			<div class="form-groupMaterial">
				<input id="padreMixtoIII" name="provincia" /> 
				<label for="padreMixtoIII">Padre</label>
			</div>
			<div class="form-groupMaterial">
				<input id="hijoMixtoIII" name="departamentoProvincia" /> 
				<label for="hijoMixtoIII">Hijo</label>
			</div>
		</fieldset>
	</div>
</section>