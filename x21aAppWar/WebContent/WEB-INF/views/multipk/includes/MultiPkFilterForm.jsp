<%@include file="/WEB-INF/includeTemplate.inc"%>
<!-- <form id="MultiPk_filter_form"> -->						<!-- Formulario de filtrado -->
<form:form id="MultiPk_filter_form" modelAttribute="multiPk" method="post">
			<div id="MultiPk_filter_toolbar" class="formulario_legend"></div>	<!-- Barra de herramientas del formulario de filtrado -->
			<fieldset id="MultiPk_filter_fieldset" class="rup-table-filter-fieldset">
				<div class="form-row">
					<!-- Campos del formulario de filtrado -->
					<div class="form-group col-sm">
						<label for="ida_filter_table" class="formulario_linea_label">
							<spring:message code="ida"/>
						</label>
						<!-- <input type="text" name="ida" class="formulario_linea_input form-control" id="ida_filter_table"/> -->
						<form:input path="ida" class="formulario_linea_input form-control" id="ida_filter_table"/>
					</div>
					<div class="form-group col-sm">
						<label for="idb_filter_table" class="formulario_linea_label">
							<spring:message code="idb"/>
						</label>
						<!-- <input type="text" name="idb" class="formulario_linea_input form-control" id="idb_filter_table"/> -->
						<form:input path="idb" class="formulario_linea_input form-control" id="idb_filter_table"/>
					</div>
					<div class="form-group col-sm">
						<label for="nombre_filter_table" class="formulario_linea_label">
							<spring:message code="nombre"/>
						</label>
						<!-- <input type="text" name="nombre" class="formulario_linea_input form-control" id="nombre_filter_table"/> -->
						<form:input path="nombre" class="formulario_linea_input form-control" id="nombre_filter_table"/>
					</div>
				</div>
				<div class="form-row">
					<div class="form-group col-sm">
						<label for="apellido1_filter_table" class="formulario_linea_label">
							<spring:message code="apellido1"/>
						</label>
						<!-- <input type="text" name="apellido1" class="formulario_linea_input form-control" id="apellido1_filter_table"/> -->
						<form:input path="apellido1" class="formulario_linea_input form-control" id="apellido1_filter_table"/>
					</div>
					<div class="form-group col-sm">
						<label for="apellido2_filter_table" class="formulario_linea_label">
							<spring:message code="apellido2"/>
						</label>
						<!-- <input type="text" name="apellido2" class="formulario_linea_input form-control" id="apellido2_filter_table"/> -->
						<form:input path="apellido2" class="formulario_linea_input form-control" id="apellido2_filter_table"/>
					</div>
					<!-- Fin campos del formulario de filtrado -->
				</div>
				<!-- Botonera del formulario de filtrado -->
				<div id="MultiPk_filter_buttonSet" class="right_buttons">
					<button id="MultiPk_filter_cleanButton" type="button" class="btn btn-primary rup-limpiar">
			        	<i class="fa fa-eraser"></i>
			        	<span>
			        		<spring:message code="clear" />
			        	</span>
			        </button>
			        <button id="MultiPk_filter_filterButton" type="button" class="btn btn-primary rup-filtrar">
			        	<i class="fa fa-filter"></i>
			        	<span>
			        		<spring:message code="filter" />
			        	</span>
			        </button>
				</div>
			</fieldset>
</form:form>