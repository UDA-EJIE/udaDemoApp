<%--  
 -- Copyright 2019 E.J.I.E., S.A.
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

<!-- Formulario de detalle -->
<div id="usuario_detail_div" class="rup-table-formEdit-detail d-none">
	<!-- Barra de navegaci�n del detalle -->
	<div id ="usuario_detail_navigation" class="row no-gutters"></div>
	<!-- Separador -->
	<hr class="m-1">
	<div class="dialog-content-material">
		<!-- Formulario -->
		<spring:url value="/table" var="url"/>
		<form:form modelAttribute="usuario" id="usuario_detail_form" action="${url}">
			<!-- Feedback del formulario de detalle -->
			<div id="usuario_detail_feedback"></div>
			<!-- Campos del formulario de detalle -->
			<div class="form-row">
				<div class="form-groupMaterial col-sm">
					<form:input path="nombre" id="nombre_detail_table"/>
					<label for="nombre_detail_table"><spring:message code="nombre"/></label>
				</div>
				<div class="form-groupMaterial col-sm">
					<form:input path="apellido1" id="apellido1_detail_table"/>
					<label for="apellido1_detail_table"><spring:message code="apellido1"/></label>
				</div>
				<div class="form-groupMaterial col-sm">
					<form:input path="apellido2" id="apellido2_detail_table"/>
					<label for="apellido2_detail_table"><spring:message code="apellido2"/></label>
				</div>
			</div>
			<div class="form-row">
				<div class="form-groupMaterial col-sm">
					<form:checkbox path="ejie" id="ejie_detail_table"/>			
					<label for="ejie_detail_table"><spring:message code="ejie"/></label>				
				</div>
				<div class="form-groupMaterial col-sm">
					<form:input path="fechaAlta" id="fechaAlta_detail_table"/>
					<label for="fechaAlta_detail_table"><spring:message code="fechaAlta"/></label>
				</div>
				<div class="form-groupMaterial col-sm">
					<form:input path="fechaBaja" id="fechaBaja_detail_table"/>
					<label for="fechaBaja_detail_table"><spring:message code="fechaBaja"/></label>
				</div>
				<div class="form-groupMaterial col-sm">
					<form:input path="rol" id="rol_detail_table"/>
					<label for="rol_detail_table"><spring:message code="rol"/></label>
				</div>
				<div class="form-groupMaterial col-sm">
					<form:input path="fechaModif" id="fechaModif_detail_table"/>
					<label for="fechaModif_detail_table"><spring:message code="fechaModif"/></label>
				</div>
			</div>
		</form:form>
	</div>
	<!-- Botonera del formulario de detalle -->
	<div class="rup-table-buttonpanel-material">
		<div class="text-right">
			<!-- Bot�n cancelar -->
			<button id="usuario_detail_button_cancel" type="button">
				<spring:message code="cancel" />
			</button>
			<!-- Bot�n guardar -->
			<button id="usuario_detail_button_save" type="button">
				<spring:message code="save" />
			</button>
		</div>
    </div>
</div>
