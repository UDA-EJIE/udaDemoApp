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
<%@include file="/WEB-INF/includeTemplate.inc"%>
<h2>
	<spring:message code="selectEnlazadoSimple.title" />
</h2>
<form:form id="select_enlazado_form"  method="post" action="/post" modelAttribute="Comarca">

<div class="container-fluid">
	<div class="form-row">
		<fieldset id="local" class="col-sm mr-sm-5">
			<legend>Local</legend>
			<div class="form-groupMaterial">
				<select id="selectAbuelo"></select>
				<label for="selectAbuelo">Provincia</label>
			</div>
			
			<div class="form-groupMaterial">
				<select id="selectPadre"></select>
				<label for="selectPadre">Comarca</label>
			</div>
			
			<div class="form-groupMaterial">
				<select id="selectHijo"></select>
				<label for="selectHijo">Localidad</label>
			</div>
		</fieldset>
		
		<fieldset id="remote" class="col-sm mr-sm-5">
			<legend>Remoto</legend>

			<div class="form-groupMaterial">
				<select id="selectAbueloRemoto" name="provincia"></select>
				<label for="selectAbueloRemoto">Provincia</label>
			</div>
			
			<div class="form-groupMaterial">
				<select id="selectPadreRemoto" name="comarca"></select>
				<label for="selectPadreRemoto">Comarca</label>
			</div>
			
			<div class="form-groupMaterial">
				<select id="selectHijoRemoto"></select>
				<label for="selectHijoRemoto">Localidad</label>
			</div>
		</fieldset>
		
	</div>
</div>

<div class="container-fluid">
	<div class="form-row">
		<fieldset id="local" class="col-sm mr-sm-5">
			<legend>Local a Remoto</legend>
			<div class="form-groupMaterial">
				<form:select id="selectLocalAbuelo" path="provincia"></form:select>
				<label for="selectLocalAbuelo">Provincia Local</label>
			</div>
			
			<div class="form-groupMaterial">
				<select id="selectRemotoPadre"></select>
				<label for="selectRemotoPadre">Comarca Remoto</label>
			</div>

		</fieldset>
		
		<fieldset id="remote" class="col-sm mr-sm-5">
			<legend>Remoto a Local</legend>
			
			
			<div class="form-groupMaterial">
				<select id="selectRemotoAbuelo" name="provincia"><option>&nbsp;</option></select>
				<label for="selectRemotoAbuelo">Provincia Remoto</label>
			</div>
			
			<div class="form-groupMaterial">
				<select id="selectLocalPadre" name="comarca"><option>&nbsp;</option></select>
				<label for="selectLocalPadre">Comarca Local</label>
			</div>

		</fieldset>
		
	</div>
</div>

<div class="container-fluid mt-4">
	<div class="form-row">
		
		<div id="remoteGroup" class="col-sm">
			<fieldset>
				<legend>Remoto agrupado</legend>
				<div class="form-groupMaterial">
					<select id="remoteGroup_selectPadre" name="provincia"></select>
					<label for="remoteGroup_selectPadre">Provincia</label>
				</div>
				
				<div class="form-groupMaterial">
					<select id="remoteGroup_selectHijo"></select>
					<label for="remoteGroup_selectHijo">Comarca y Localidades</label>
				</div>
			</fieldset>
		</div>
	</div>
</div>
</form:form>
