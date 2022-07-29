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

<div class="container-fluid">
	<div class="form-row">
		<div class="form-groupMaterial col-sm">  
			<label for="multiSelect">MultiSelect local</label>
			<select id="multiSelect"></select>
		</div>
		<div class="form-groupMaterial col-sm">  
			<label for="multiSelectRemoto">MultiSelect remoto</label>
			<select id="multiSelectRemoto"></select>
		</div>
	</div>
</div>

<div class="container-fluid mt-4">
	<div class="form-row">
		<div class="form-groupMaterial col-sm">  
			<label for="multiSelectGrupos">MultiSelect con 'optgroups'</label>
			<select id="multiSelectGrupos"></select>
		</div>
		<div class="form-groupMaterial col-sm"> 
			<label for="multiSelectGruposRemoto">MultiSelect con 'optgroups' remoto y preselecionado</label>
			<select id="multiSelectGruposRemoto"></select>
		</div>
	</div>
</div>

<div class="container-fluid mt-4">
	<div class="form-row">
		<div class="form-groupMaterial col-sm"> 
			<label for="multiSelectLoadFromSelect">MultiSelect carga inicial desde <strong>HTML</strong></label>
			<select id="multiSelectLoadFromSelect" multiple="multiple">
				<option value="1" selected="selected">Alava</option>
				<option value="3">Gipuzcoa</option>
				<option value="2" selected="selected">Vizcaya</option>
			</select>
		</div>
	</div>
</div>
