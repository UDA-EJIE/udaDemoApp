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
	<h2 class="title mb-3">Select autocomplet enlazado</h2>
	
	<div class="form-row">
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
			<div class="form-groupMaterial">
				<input id="abueloRemoto" name="provincia" /> 
				<label for="abueloRemoto">Abuelo</label>
			</div>
			<div class="form-groupMaterial">
				<input id="padreRemoto" name="comarca" /> 
				<label for="padreRemoto">Padre</label>
			</div>
			<div class="form-groupMaterial">
				<input id="hijoRemoto" name="localidad" /> 
				<label for="hijoRemoto">Hijo</label>
			</div>
		</fieldset>
		
		<fieldset id="mixto" class="col-md col-12 px-3">
			<legend>Mixto I</legend>
			<div class="form-groupMaterial">
				<input id="abueloMixto" name="provincia" /> 
				<label for="abueloMixto">Abuelo</label>
			</div>
			<div class="form-groupMaterial">
				<input id="padreMixto" name="comarca" /> 
				<label for="padreMixto">Padre</label>
			</div>
			<div class="form-groupMaterial">
				<input id="hijoMixto" name="localidad" /> 
				<label for="hijoMixto">Hijo</label>
			</div>
		</fieldset>
	</div>
</section>