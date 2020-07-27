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
<h2>Tabla en diálogo</h2> <!-- Titulo pagina -->

<button id="btnTablaDialog_div" class="btn-material btn-material-primary-high-emphasis" type="button">
	<span>Tabla en formulario (DIV)</span>
</button>
<button id="btnTablaDialog_ajax" class="btn-material btn-material-primary-high-emphasis" type="button">
	<span>Tabla en formulario (AJAX)</span>
</button>

<div id="tableDialog_layer_ajax"></div>
<div id="tableDialog_layer_div" style="display:none">
	<div id="tableDialog_div" class="rup-table-container">
		<!-- Feedback del formulario de filtrado -->
		<div id="tableDialog_feedback"></div>
		<div id="tableDialog_toolbar"></div>
		<div id="tableDialog_filter_div" class="rup-table-filter">
			<!-- Formulario de filtrado -->
			<spring:url value="/table/multipk/filter" var="url"/>
			<form:form modelAttribute="multiPk" id="MultiPk_filter_form" action="${url}">
				<!-- Barra de herramientas del formulario de filtrado -->
				<div id="MultiPk_filter_toolbar" class="formulario_legend"></div>
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
							<label for="nombre_filter_table">
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
		</div>
	
		<div id="tableDialog_grid_div">
			<!-- Tabla -->
			<table id="MultiPk" class="tableFit table-striped table-bordered table-material" 
				data-url-base="./multipk"
				data-filter-form="#MultiPk_filter_form">
		        <thead>
		            <tr>
		                <th data-col-prop="ida" data-col-sidx="IDA" >ida</th>
		                <th data-col-prop="idb" data-col-sidx="IDB" >idb</th>
		                <th data-col-prop="nombre" data-col-sidx="NOMBRE" >nombre</th>
		                <th data-col-prop="apellido1" data-col-sidx="APELLIDO1" >apellido1</th>
		                <th data-col-prop="apellido2" data-col-sidx="APELLIDO2" >apellido2</th>
		            </tr>
		        </thead>
			</table>
			<!-- Barra de paginaci�n -->
			<div id="tableDialog_pager"></div>
		</div>
	</div>	
	
	<!-- Formulario de detalle -->
	<div id="MultiPk_detail_div" class="rup-table-formEdit-detail d-none">
		<!-- Barra de navegaci�n del detalle -->
		<div id ="MultiPk_detail_navigation" class="row no-gutters"></div>
		<!-- Separador -->
		<hr class="m-1">
		<div class="dialog-content-material">
			<!-- Formulario -->
			<spring:url value="/table/multipk/filter" var="url"/>
			<form:form modelAttribute="multiPk" id="MultiPk_detail_form" action="${url}">
				<!-- Feedback del formulario de detalle -->
				<div id ="MultiPk_detail_feedback"></div>
				<div class="form-row">
					<!-- Campos del formulario de detalle -->
					<div class="form-groupMaterial col-sm">
						<form:input type="text" path="ida" id="ida_detail_table"/>
						<label for="ida_detail_table">
							<spring:message code="ida"/>
						</label>
					</div>
					<div class="form-groupMaterial col-sm">
						<form:input type="text" path="idb" id="idb_detail_table"/>
						<label for="idb_detail_table">
							<spring:message code="idb"/>
						</label>
					</div>
					<div class="form-groupMaterial col-sm">
						<form:input type="text" path="nombre" id="nombre_detail_table"/>
						<label for="nombre_detail_table">
							<spring:message code="nombre"/>
						</label>
					</div>
				</div>
				<div class="form-row">
					<div class="form-groupMaterial col-sm">
						<form:input type="text" path="apellido1" id="apellido1_detail_table"/>
						<label for="apellido1_detail_table">
							<spring:message code="apellido1"/>
						</label>
					</div>
					<div class="form-groupMaterial col-sm">
						<form:input type="text" path="apellido2" id="apellido2_detail_table"/>
						<label for="apellido2_detail_table">
							<spring:message code="apellido2"/>
						</label>
					</div>
					<!-- Fin campos del formulario de detalle -->
				</div>
			</form:form>
		</div>
		<!-- Botonera del formulario de detalle -->
		<div class="rup-table-buttonpanel-material">
			<div class="text-right">
				<!-- Enlace cancelar -->
				<button id="MultiPk_detail_button_cancel" type="button">
					<spring:message code="cancel" />
				</button>
				<!-- Bot�n Guardar -->
				<button id="MultiPk_detail_button_save" type="button">
					<spring:message code="save" />
				</button>
			</div>
		</div>
	</div>
</div>
