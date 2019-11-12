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
<div id="MultiPk_detail_div" class="rup-table-formEdit-detail d-none">
	<!-- Barra de navegaci�n del detalle -->
	<div id ="MultiPk_detail_navigation" class="row no-gutters"></div>
	<!-- Separador -->
	<hr class="m-1">
	<div class="dialog-content-material">
		<!-- Formulario -->
		<form:form modelAttribute="multiPk" id="MultiPk_detail_form">
			<!-- Feedback del formulario de detalle -->
			<div id ="MultiPk_detail_feedback"></div>
			<div class="form-row">
				<!-- Campos del formulario de detalle -->
				<div class="form-groupMaterial col-sm">
					<form:input path="ida" id="ida_detail_table"/>
					<label for="ida_detail_table"><spring:message code="ida"/></label>
				</div>
				<div class="form-groupMaterial col-sm">
					<form:input path="idb" id="idb_detail_table"/>
					<label for="idb_detail_table"><spring:message code="idb"/></label>
				</div>
				<div class="form-groupMaterial col-sm">
					<form:input path="nombre" id="nombre_detail_table"/>
					<label for="nombre_detail_table"><spring:message code="nombre"/></label>
				</div>
			</div>
			<div class="form-row">
				<div class="form-groupMaterial col-sm">
					<form:input path="apellido1" id="apellido1_detail_table"/>
					<label for="apellido1_detail_table"><spring:message code="apellido1"/></label>
				</div>
				<div class="form-groupMaterial col-sm">
					<form:input path="apellido2" id="apellido2_detail_table"/>
					<label for="apellido2_detail_table"><spring:message code="apellido2"/></label>
				</div>
				<!-- Fin campos del formulario de detalle -->
			</div>
		</form:form>
	</div>
	<!-- Botonera del formulario de detalle -->
	<div class="rup-table-buttonpanel-material">
		<div class="text-right">
			<!-- Bot�n cancelar -->
			<button id="MultiPk_detail_button_cancel" type="button">
				<spring:message code="cancel" />
			</button>
			<!-- Bot�n guardar -->
			<button id="MultiPk_detail_button_save" type="button">
				<spring:message code="save" />
			</button>
		</div>
	</div>
</div>
