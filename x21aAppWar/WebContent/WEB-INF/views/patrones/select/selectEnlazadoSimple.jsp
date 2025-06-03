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
		<fieldset id="local" class="col-sm me-sm-5">
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
		
		<fieldset id="remote" class="col-sm me-sm-5">
			<legend>Remoto</legend>
			<form:form id="selectRemoto_form" modelAttribute="provinciaComarcaLocalidadDTO" action="${remoto}" method="GET">
				<div class="form-groupMaterial">
					<form:select id="selectAbueloRemoto" path="codeProvincia" />
					<label for="selectAbueloRemoto">Provincia</label>
				</div>
				
				<div class="form-groupMaterial">
					<form:select id="selectPadreRemoto" path="codeComarca" />
					<label for="selectPadreRemoto">Comarca</label>
				</div>
				
				<div class="form-groupMaterial">
					<form:select id="selectHijoRemoto" path="codeLocalidad" />
					<label for="selectHijoRemoto">Localidad</label>
				</div>
			</form:form>
		</fieldset>
		
	</div>
</div>

<div class="container-fluid">
	<div class="form-row">
		<fieldset id="local" class="col-sm me-sm-5">
			<legend>Local a Remoto</legend>
			<form:form id="departamentoProvincia_form" modelAttribute="provinciaComarcaDTO" action="${mixto}" method="GET">
				<div class="form-groupMaterial">
					<form:select id="selectLocalAbuelo" path="codeProvincia" items="${comboProvincia}" itemLabel="descEs" itemValue="code" />
					<label for="selectLocalAbuelo">Provincia Local</label>
				</div>
				
				<div class="form-groupMaterial">
					<form:select id="selectRemotoPadre" path="codeComarca" />
					<label for="selectRemotoPadre">Comarca Remoto</label>
				</div>
			</form:form>
		</fieldset>
		
		<fieldset id="remote" class="col-sm me-sm-5">
			<legend>Remoto a Local js y jsp(para usar los datos al servidor)</legend>
			<form:form id="departamentoProvincia_form" modelAttribute="provinciaComarcaDTO" action="${mixto}" method="GET">
				<div class="form-groupMaterial">
					<form:select id="selectRemotoAbuelo" path="codeProvincia" />
					<label for="selectRemotoAbuelo">Provincia Remoto</label>
				</div>
				
				<div class="form-groupMaterial">
					<form:select id="selectLocalPadre" path="codeComarca">
					    <c:forEach var="theComarca" items="${comboComarca}">
	        				<form:option value="${theComarca.code}" data-idPadre="${theComarca.parentCode}"><c:out value="${theComarca.descEs}"/></form:option>
	   					 </c:forEach>
					</form:select>
					<label for="selectLocalPadre">Comarca Local cargado desde jsp</label>
				</div>
			
			</form:form>
			<div class="form-groupMaterial">
				<select id="selectLocalPadreJs" name="codeAuxiliar"><option>&nbsp;</option></select>
				<label for="selectLocalPadreJs">Comarca Local cargado con js</label>
			</div>
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
						<form:select id="remoteGroup_selectPadre" path="codeProvincia" />
						<label for="remoteGroup_selectPadre">Provincia</label>
					</div>
					
					<div class="form-groupMaterial">
						<form:select id="remoteGroup_selectHijo" path="codeComarcaLocalidad" />
						<label for="remoteGroup_selectHijo">Comarca y Localidades</label>
					</div>
				</form:form>
			</fieldset>
		</div>
	</div>
</div>
