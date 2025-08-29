<%--  
 -- Copyright 2012 E.J.I.E., S.A.
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
	<spring:message code="selectMultiselect.title" />
</h2>

<!-- URL a usar en formularios -->
<spring:url value="comboSimple/remote" var="remoto"/>
<spring:url value="comboEnlazadoSimple/provinciaComarcaLocalidadDTO" var="remotoAgrupado"/>

<div class="container-fluid">
	<div class="row">
		<div class="form-groupMaterial col-sm-6">
			<select id="multiSelect"></select>  
			<label for="multiSelect">MultiSelect local</label>
		</div>
		<form:form id="multiSelectRemoto_form" modelAttribute="provincia" action="${remoto}" method="GET" class="mt-0 form-groupMaterial col-sm-6">
			<div class="form-groupMaterial col-sm-10">
				<form:select id="multiSelectRemoto" path="code" />
				<label for="multiSelectRemoto">MultiSelect remoto con loadOnStartUp</label>
			</div>
		</form:form>
	</div>
</div>

<div class="container-fluid mt-4">
	<div class="row">
		<div class="form-groupMaterial col-sm-6">
			<select id="multiSelectGrupos"></select>  
			<label for="multiSelectGrupos">MultiSelect con 'optgroups'</label>
		</div>
		<form:form id="multiSelectGruposRemoto_form" modelAttribute="provinciaComarcaLocalidadDTO" action="${remotoAgrupado}" method="GET" class="mt-0 form-groupMaterial col-sm-6">
			<div class="form-groupMaterial col-sm-10">
				<form:select id="multiSelectGruposRemoto" path="codeProvincia">
					<form:option value="">---</form:option>
				</form:select>   
				<label for="multiSelectGruposRemoto">MultiSelect con 'optgroups' remoto y preselecionado</label>
			</div>
		</form:form>
	</div>
</div>

<div class="container-fluid mt-4">
	<div class="row">
		<div class="form-groupMaterial col-sm-6">
			<select id="multiSelectLoadFromSelect" multiple="multiple">
				<option value="1" selected="selected">Alava</option>
				<option value="3">Gipuzcoa</option>
				<option value="2" selected="selected">Vizcaya</option>
			</select> 
			<label for="multiSelectLoadFromSelect">MultiSelect carga inicial desde <strong>HTML</strong></label>
		</div>
	</div>
</div>
<div class="container-fluid">
	<div class="row">
		<fieldset id="local" class="col-sm me-sm-5">
			<legend>Local</legend>
			<div class="form-groupMaterial">
				<select id="selectAbuelo"></select>
				<label for="selectAbuelo">Provincia</label>
			</div>
			
			<div class="form-groupMaterial">
				<select id="multiselectPadre"></select>
				<label for="multiselectPadre">Comarca Multiple</label>
			</div>
			
			<div class="form-groupMaterial">
				<select id="selectHijo"></select>
				<label for="selectHijo">Localidad</label>
			</div>
		</fieldset>

		<fieldset id="remote" class="col-sm me-sm-5">
			<legend>Remoto</legend>
			<form:form id="selectRemoto_form" modelAttribute="provinciaComarcaLocalidadDTO" action="${remoto}" method="GET">
				<div class="form-groupMaterial">
					<form:select id="selectAbueloRemoto" path="codeProvincia" />
					<label for="selectAbueloRemoto">Provincia</label>
				</div>
				
				<div class="form-groupMaterial">
					<form:select id="multiselectPadreRemoto" path="codeComarca" />
					<label for="selectPadreRemoto">Comarca Multiple</label>
				</div>
				
				<div class="form-groupMaterial">
					<form:select id="selectHijoRemoto" path="codeLocalidad" />
					<label for="selectHijoRemoto">Localidad</label>
				</div>
			</form:form>
		</fieldset>
	</div>
</div>