<%--  
 -- Copyright 2021 E.J.I.E., S.A.
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

<!-- Titulo pagina -->
<!-- <h2>table</h2> -->

<!-- Formulario de detalle -->
<div id="comarca_detail_div" class="rup-table-formEdit-detail d-none">
	<!-- Barra de navegaci�n del detalle -->
	<div id ="comarca_detail_navigation" class="row no-gutters"></div>
	<!-- Separador -->
	<hr class="m-1">
	<div id="comarca_detail_form_container" class="dialog-content-material">
		<!-- El Formulario ser� insertado mediante JavaScript -->
	</div>
	<!-- Botonera del formulario de detalle -->
	<div class="rup-table-buttonpanel-material">
		<div class="text-right">
			<!-- Bot�n cancelar -->
			<button id="comarca_detail_button_cancel" type="button">
				<spring:message code="cancel" />
			</button>
			<!-- Bot�n guardar -->
			<button id="comarca_detail_button_save" type="button">
				<spring:message code="save" />
			</button>
			<!-- Bot�n guardar y continuar -->
			<button id="comarca_detail_button_save_repeat" type="button">
				<spring:message code="saveAndContinue" />
			</button>
		</div>
	</div>
</div>
