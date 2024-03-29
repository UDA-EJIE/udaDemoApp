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
<h2 class="title mb-3"><spring:message code="multicombo" /></h2>

<!-- URL a usar en formularios -->
<spring:url value="comboSimple/remote" var="remoto"/>
<spring:url value="comboEnlazadoSimple/provinciaComarcaLocalidadDTO" var="remotoAgrupado"/>

<div class="container-fluid">
	<div class="form-row">
		<div class="form-groupMaterial col-sm">  
			<label for="multicombo">Multicombo local</label>
			<select id="multicombo"></select>
		</div>
		<form:form id="multicomboRemoto_form" modelAttribute="provincia" action="${remoto}" method="GET">
			<div class="form-groupMaterial col-sm">
				<form:select id="multicomboRemoto" path="code" />
				<label for="multicomboRemoto">MultiCombo remoto</label>
			</div>
		</form:form>
	</div>
</div>

<div class="container-fluid mt-4">
	<div class="form-row">
		<div class="form-groupMaterial col-sm">  
			<label for="multicomboGrupos">MultiCombo con 'optgroups'</label>
			<select id="multicomboGrupos"></select>
		</div>
		<form:form id="multicomboGruposRemoto_form" modelAttribute="provinciaComarcaLocalidadDTO" action="${remotoAgrupado}" method="GET">
			<div class="form-groupMaterial col-sm">
				<form:select id="multicomboGruposRemoto" path="codeProvincia" /> 
				<label for="multicomboGruposRemoto">MultiCombo con 'optgroups' remoto</label>
			</div>
		</form:form>
	</div>
</div>

<div class="container-fluid mt-4">
	<div class="form-row">
		<div class="form-groupMaterial col-sm">  
			<label for="multicomboInput">MultiCombo sobre <strong>Input</strong></label>
			<input type="text" name="multicomboInput" id="multicomboInput" value="coldfusion##python" />
		</div>
		<div class="form-groupMaterial col-sm"> 
			<label for="multicomboLoadFromSelect">MultiCombo carga inicial desde <strong>HTML</strong></label>
			<select id="multicomboLoadFromSelect" multiple="multiple">
				<option value="1" selected="selected">Álava</option>
				<option value="3">Gipúzcoa</option>
				<option value="2" selected="selected">Vizcaya</option>
			</select>
		</div>
	</div>
</div>
