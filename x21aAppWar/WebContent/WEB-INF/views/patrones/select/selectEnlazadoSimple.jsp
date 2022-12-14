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

<!-- URL a usar en formularios -->
<spring:url value="comboEnlazadoSimple/provinciaComarcaLocalidadDTO" var="remoto"/>
<spring:url value="comboEnlazadoSimple/provinciaComarcaDTO" var="mixto"/>
<spring:url value="comboEnlazadoSimple/comarcaLocalidadDTO" var="remotoAgrupado"/>

<h2>
	<spring:message code="selectEnlazadoSimple.title" />
</h2>

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
			<form:form id="selectRemoto_form" modelAttribute="provinciaComarcaLocalidadDTO" action="${remoto}" method="GET">
				<div class="form-groupMaterial">
					<form:select id="selectAbueloRemoto" path="codeProvincia">
						<form:option value="">---</form:option>
					</form:select>
					<label for="selectAbueloRemoto">Provincia</label>
				</div>
				
				<div class="form-groupMaterial">
					<form:select id="selectPadreRemoto" path="codeComarca">
						<form:option value="">---</form:option>
					</form:select>
					<label for="selectPadreRemoto">Comarca</label>
				</div>
				
				<div class="form-groupMaterial">
					<form:select id="selectHijoRemoto" path="codeLocalidad">
						<form:option value="">---</form:option>
					</form:select>
					<label for="selectHijoRemoto">Localidad</label>
				</div>
			</form:form>
		</fieldset>
		
	</div>
</div>

<div class="container-fluid">
	<div class="form-row">
		<fieldset id="local" class="col-sm mr-sm-5">
			<legend>Local a Remoto</legend>
			<form:form id="departamentoProvincia_form" modelAttribute="provinciaComarcaDTO" action="${mixto}" method="GET">
				<div class="form-groupMaterial">
					<form:select id="selectLocalAbuelo" path="codeProvincia" items="${comboProvincia}" itemLabel="entity.descEs" itemValue="id" />
					<label for="selectLocalAbuelo">Provincia Local</label>
				</div>
				
				<div class="form-groupMaterial">
					<form:select id="selectRemotoPadre" path="codeComarca">
						<form:option value="">---</form:option>
					</form:select>
					<label for="selectRemotoPadre">Comarca Remoto</label>
				</div>
			</form:form>
		</fieldset>
		
		<fieldset id="remote" class="col-sm mr-sm-5">
			<legend>Remoto a Local</legend>
			<form:form id="departamentoProvincia_form" modelAttribute="provinciaComarcaDTO" action="${mixto}" method="GET">
				<div class="form-groupMaterial">
					<form:select id="selectRemotoAbuelo" path="codeProvincia">
						<form:option value="">---</form:option>
					</form:select>
					<label for="selectRemotoAbuelo">Provincia Remoto</label>
				</div>
				
				<div class="form-groupMaterial">
					<form:select id="selectLocalPadre" path="codeComarca" items="${comboComarca}" itemLabel="entity.descEs" itemValue="id" />
					<label for="selectLocalPadre">Comarca Local</label>
				</div>
			</form:form>
		</fieldset>
		
	</div>
</div>

<div class="container-fluid mt-4">
	<div class="form-row">
		
		<div id="remoteGroup" class="col-sm">
			<fieldset>
				<legend>Remoto agrupado</legend>
				<form:form id="selectRemotoAgrupado_form" modelAttribute="comarcaLocalidadDTO" action="${remotoAgrupado}" method="GET">
					<div class="form-groupMaterial">
						<form:select id="remoteGroup_selectPadre" path="codeProvincia">
							<form:option value="">---</form:option>
						</form:select>
						<label for="remoteGroup_selectPadre">Provincia</label>
					</div>
					
					<div class="form-groupMaterial">
						<form:select id="remoteGroup_selectHijo" path="codeComarcaLocalidad">
							<form:option value="">---</form:option>
						</form:select>
						<label for="remoteGroup_selectHijo">Comarca y Localidades</label>
					</div>
				</form:form>
			</fieldset>
		</div>
	</div>
</div>
