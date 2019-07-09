<%@include file="/WEB-INF/includeTemplate.inc"%>
<form:form modelAttribute="alumno" id="x21aAlumno_filter_form">						<!-- Formulario de filtrado -->
			<div id="x21aAlumno_filter_toolbar" class="formulario_legend"></div>	<!-- Barra de herramientas del formulario de filtrado -->
			<fieldset id="x21aAlumno_filter_fieldset" class="rup-table-filter-fieldset">
				<div class="form-row">
					<!-- Campos del formulario de filtrado -->
					<div class="form-group col-sm">
						<label for="id_filter_table" class="formulario_linea_label"><spring:message code="id"/></label>
						<form:input path="id" class="formulario_linea_input form-control" id="id_filter_table"/>
					  </div>
					<div class="form-group col-sm">
						<label for="usuario_filter_table" class="formulario_linea_label"><spring:message code="usuario"/></label>
						<form:input path="usuario" class="formulario_linea_input form-control" id="usuario_filter_table"/>
					  </div>
					<div class="form-group col-sm">
						<label for="password_filter_table" class="formulario_linea_label"><spring:message code="password"/></label>
						<form:input path="password" class="formulario_linea_input form-control" id="password_filter_table"/>
					  </div>
					<div class="form-group col-sm">
						<label for="nombre_filter_table" class="formulario_linea_label"><spring:message code="nombre"/></label>
						<form:input path="nombre" class="formulario_linea_input form-control" id="nombre_filter_table"/>
					  </div>
					<div class="form-group col-sm">
						<label for="apellido1_filter_table" class="formulario_linea_label"><spring:message code="apellido1"/></label>
						<form:input path="apellido1" class="formulario_linea_input form-control" id="apellido1_filter_table"/>
					  </div>
					<div class="form-group col-sm">
						<label for="provinciaId_filter_table" class="formulario_linea_label"><spring:message code="provinciaId"/></label>
						<form:input path="provincia.id" class="formulario_linea_input form-control" id="provinciaId_filter_table"/>
					  </div>
					<div class="form-group col-sm">
						<label for="localidadId_filter_table" class="formulario_linea_label"><spring:message code="localidadId"/></label>
						<form:input path="localidad" class="formulario_linea_input form-control" id="localidadId_filter_table"/>
					  </div>
					<div class="form-group col-sm">
						<label for="comarcaId_filter_table" class="formulario_linea_label"><spring:message code="comarcaId"/></label>
						<form:input path="comarca" class="formulario_linea_input form-control" id="comarcaId_filter_table"/>
					  </div>
					<div class="form-group col-sm">
						<label for="municipioId_filter_table" class="formulario_linea_label"><spring:message code="municipioId"/></label>
						<form:input path="municipio.id" class="formulario_linea_input form-control" id="municipioId_filter_table"/>
					  </div>
					<div class="form-group col-sm">
						<label for="autonomiaId_filter_table" class="formulario_linea_label"><spring:message code="autonomiaId"/></label>
						<form:input path="autonomia.id" class="formulario_linea_input form-control" id="autonomiaId_filter_table"/>
					  </div>
					<!-- Fin campos del formulario de filtrado -->
				</div>
				<!-- Botonera del formulario de filtrado -->
				<div id="x21aAlumno_filter_buttonSet" class="right_buttons">
					<!-- Botón de limpiar -->
					<button id="x21aAlumno_filter_cleanButton" type="button" class="btn btn-primary rup-limpiar">
						<i class="fa fa-eraser"></i>
						<span><spring:message code="clear" /></span>
					</button>
					<!-- Botón de filtrado -->
					<button id="x21aAlumno_filter_filterButton" type="button" class="btn rup-filtrar btn-primary" >
						<i class="fa fa-filter"></i>
						<span><spring:message code="filter" /></span>
					</button>
				</div>
			</fieldset>
</form:form>
