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

<form:form modelAttribute="multiPk" id="MultiPk_filter_form">						<!-- Formulario de filtrado -->
	<div id="MultiPk_filter_toolbar" class="formulario_legend"></div>	<!-- Barra de herramientas del formulario de filtrado -->
	<fieldset id="MultiPk_filter_fieldset" class="rup-table-filter-fieldset">
		<div class="form-row">
			<!-- Campos del formulario de filtrado -->
			<div class="form-groupMaterial col-sm">
				<form:input path="ida" id="ida_filter_table"/>
				<label for="ida_filter_table">
					<spring:message code="ida"/>
				</label>
			</div>
			<div class="form-groupMaterial col-sm">
				<form:input path="idb" id="idb_filter_table"/>
				<label for="idb_filter_table">
					<spring:message code="idb"/>
				</label>
			</div>
			<div class="form-groupMaterial col-sm">
				<form:input path="nombre" id="nombre_filter_table"/>
				<label for="nombre_filter_table" >
					<spring:message code="nombre"/>
				</label>
			</div>
		</div>
		<div class="form-row">
			<div class="form-groupMaterial col-sm">
				<form:input path="apellido1" id="apellido1_filter_table"/>
				<label for="apellido1_filter_table">
					<spring:message code="apellido1"/>
				</label>
			</div>
			<div class="form-groupMaterial col-sm">
				<form:input path="apellido2" id="apellido2_filter_table"/>
				<label for="apellido2_filter_table">
					<spring:message code="apellido2"/>
				</label>
			</div>
			<!-- Fin campos del formulario de filtrado -->
		</div>
		<!-- Botonera del formulario de filtrado -->
		<div id="MultiPk_filter_buttonSet" class="text-right">
			<button id="MultiPk_filter_cleanButton" type="button" class="btn-material btn-material-primary-low-emphasis mr-2">
	        	<i class="mdi mdi-eraser"></i>
	        	<span>
	        		<spring:message code="clear" />
	        	</span>
	        </button>
	        <button id="MultiPk_filter_filterButton" type="button" class="btn-material btn-material-primary-high-emphasis">
	        	<i class="mdi mdi-filter"></i>
	        	<span>
	        		<spring:message code="filter" />
	        	</span>
	        </button>
		</div>
	</fieldset>
</form:form>