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
<h2>Select Enlazado (simple)</h2>
<form:form id="select_enlazado_form"  method="post" action="/post" modelAttribute="Comarca">

<div class="container-fluid">
	<div class="form-row">
		<fieldset id="local" class="col-sm mr-sm-5">
			<legend>Local</legend>
			<div class="form-groupMaterial">
				<label for="selectAbuelo">Provincia</label>
				<select id="selectAbuelo"></select>
			</div>
			
			<div class="form-groupMaterial">
				<label for="selectPadre">Comarca</label>
				<select id="selectPadre"></select>
			</div>
			
			<div class="form-groupMaterial">
				<label for="selectHijo">Localidad</label>
				<select id="selectHijo"></select>
			</div>
		</fieldset>
		
		<fieldset id="remote" class="col-sm mr-sm-5">
			<legend>Remoto</legend>

			<div class="form-groupMaterial">
				<label for="selectAbueloRemoto">Provincia</label>
				<select id="selectAbueloRemoto" name="provincia"></select>
			</div>
			
			<div class="form-groupMaterial">
				<label for="selectPadreRemoto">Comarca</label>
				<select id="selectPadreRemoto" name="comarca"></select>
			</div>
			
			<div class="form-groupMaterial">
				<label for="selectHijoRemoto">Localidad</label>
				<select id="selectHijoRemoto"></select>
			</div>
		</fieldset>
		
	</div>
</div>

<div class="container-fluid">
	<div class="form-row">
		<fieldset id="local" class="col-sm mr-sm-5">
			<legend>Local a Remoto</legend>
			<div class="form-groupMaterial">
				<label for="selectLocalAbuelo">Provincia Local</label>
				<form:select id="selectLocalAbuelo" path="provincia"></form:select>
			</div>
			
			<div class="form-groupMaterial">
				<label for="selectRemotoPadre">Comarca Remoto</label>
				<select id="selectRemotoPadre"></select>
			</div>

		</fieldset>
		
		<fieldset id="remote" class="col-sm mr-sm-5">
			<legend>Remoto a Local</legend>
			
			
			<div class="form-groupMaterial">
				<label for="selectRemotoAbuelo">Provincia Remoto</label>
				<select id="selectRemotoAbuelo" name="provincia"><option>&nbsp;</option></select>
			</div>
			
			<div class="form-groupMaterial">
				<label for="selectLocalPadre">Comarca Local</label>
				<select id="selectLocalPadre" name="comarca"><option>&nbsp;</option></select>
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
					<label for="remoteGroup_selectPadre">Provincia</label>
					<select id="remoteGroup_selectPadre" name="provincia"></select>
				</div>
				
				<div class="form-groupMaterial">
					<label for="remoteGroup_selectHijo">Comarca y Localidades</label>
					<select id="remoteGroup_selectHijo"></select>
				</div>
			</fieldset>
		</div>
	</div>
</div>
</form:form>
