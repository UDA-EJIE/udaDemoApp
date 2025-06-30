<%@page import="com.ejie.aa79b.model.enums.EstadoEnum"%>
<%@include file="/WEB-INF/includeTemplate.inc"%>
<div class="container-fluid"> 
<h2><spring:message code="menu.formatosFichero" /></h2>
<hr class="mb-2">

	<div id="formatosFichero_div" class="rup-table-container">
	<div id="formatosFichero_feedback"></div>						<!-- Feedback de la tabla --> 
	<div id="formatosFichero_toolbar"></div>							<!-- Botonera de la tabla -->
	<div id="contenFormularios" class="filterForm ">	<!-- Capa contenedora del formulario de filtrado -->
			<form id="formatosFichero_filter_form">						<!-- Formulario de filtrado -->
			<div id="formatosFichero_filter_toolbar" class="formulario_legend"></div>	<!-- Barra de herramientas del formulario de filtrado -->
			<fieldset id="formatosFichero_filter_fieldset" class="rup-table-filter-fieldset">
				<div class="p-2">
				<div class="row">
					<!-- Campos del formulario de filtrado -->
					<div class="form-group col-xs-6 col-md-1">
						<label for="id011_filter_table" class="control-label" data-i18n="label.id"><spring:message code="label.id"/>:</label>
						<input type="text" name="id011" class="form-control numeric" id="id011_filter_table" maxlength="3"/>
					</div>
					<div class="form-group col-xs-12 col-md-4">
						<label for="descEu011_filter_table" class="control-label" data-i18n="label.descEu"><spring:message code="label.descEu"/>:</label>
						<input type="text" name="descEu011" class="form-control" id="descEu011_filter_table" maxlength="50"/>
					</div>
					<div class="form-group col-xs-12 col-md-4">
						<label for="descEs011_filter_table" class="control-label" data-i18n="label.descEs"><spring:message code="label.descEs"/>:</label>
						<input type="text" name="descEs011" class="form-control" id="descEs011_filter_table" maxlength="50"/>
					</div>
					<div class="form-group col-xs-1 col-md-2">
						<label for="estado011_filter_table" class="control-label" data-i18n="label.estado"><spring:message code="label.estado"/>:</label>
						<select name="estado011" id="estado011_filter_table" class="form-control col-40por">
							<option value=""><spring:message code="combo.todos"/></option>
							<option value="<%=EstadoEnum.ALTA.getValue()%>"><spring:message code="<%=EstadoEnum.ALTA.getLabel()%>"/></option>
							<option value="<%=EstadoEnum.BAJA.getValue()%>"><spring:message code="<%=EstadoEnum.BAJA.getLabel()%>"/></option>
						</select>
					</div>
					</div>
					<!-- Fin campos del formulario de filtrado -->
				</div>
				<!-- Botonera del formulario de filtrado -->
				<div class="form-group ">
				<div id="formatosFichero_filter_buttonSet" class="pull-right">
					<!-- Botón de filtrado -->
					<input id="formatosFichero_filter_filterButton" type="button" class="ui-button ui-widget ui-state-default ui-corner-all" value='<spring:message code="filter" />' />
					<!-- Enlace de limpiar -->
					<a id="formatosFichero_filter_cleanLink" href="javascript:void(0)" class="rup-enlaceCancelar"><spring:message code="clear" /></a>
				</div>
				</div>
				</div>
			</fieldset>
		</form>
	</div>

		<!-- Tabla -->
		<table id="formatosFichero"></table>
		<div class="table pb-2">
		<!-- Barra de paginación -->
		<div id="formatosFichero_pager"></div>
	</div>
</div>	

<!-- Formulario de detalle -->
<div id="formatosFichero_detail_div" class="rup-table-formEdit-detail">
	<div class="ui-dialog-content ui-widget-content" >
		<form id="formatosFichero_detail_form">					<!-- Formulario -->
			<div id ="formatosFichero_detail_feedback"></div>		<!-- Feedback del formulario de detalle -->
			
				<!-- Campos del formulario de detalle -->
				<div class="row">
				<div class="form-group  col-lg-4">
					<label for="id011_detail_table" class="control-label"><spring:message code="label.id"/>:</label>
					<input type="text" name="id011" class="form-control col-40por" id="id011_detail_table" readonly="readonly"  disabled="disabled"/>
				</div>
				</div>
				<div class="row">
				<div class="form-group  col-lg-4">
					<label for="formato011_detail_table" class="control-label"><spring:message code="label.formato"/>:</label>
					<input type="text" name="formato011" class="form-control" id="formato011_detail_table" readonly="readonly"  disabled="disabled"/>
				</div>
				</div>
				<div class="row">
				<div class="form-group  col-lg-4">
					<label for="descEs011_detail_table" class="control-label"><spring:message code="label.descEs"/>:</label>
					<input type="text" name="descEs011" class="form-control" id="descEs011_detail_table" readonly="readonly"  disabled="disabled"/>
				</div>
				<div class="form-group  col-lg-4">
					<label for="descEu011_detail_table" class="control-label"><spring:message code="label.descEu"/>:</label>
					<input type="text" name="descEu011" class="form-control" id="descEu011_detail_table" readonly="readonly"  disabled="disabled"/>
				</div>
				</div>
				<div class="row">
				<div class="form-group  col-lg-4">
					<label for="observEu011_detail_table" class="control-label"><spring:message code="label.observEu"/>:</label>
					<textarea name="observEu011" class="form-control" id="observEu011_detail_table" maxlength="4000"></textarea>
				</div>
				<div class="form-group  col-lg-4">
					<label for="observEs011_detail_table" class="control-label"><spring:message code="label.observEs"/>:</label>
					<textarea name="observEs011" class="form-control" id="observEs011_detail_table" maxlength="4000"></textarea>
				</div>
				</div>
				<div class="row">
				<div class="form-group">
				<span>(*)<spring:message code="label.observacionesFichero"/></span>
				</div>
				</div>
				<div class="row">
				<div class="form-group  col-lg-2">
					<label for="estado011_detail_table" class="control-label"><spring:message code="label.estado"/>:</label>
					<select name="estado011" id="estado011_detail_table" class="form-control col-60por">
						<option value="<%=EstadoEnum.ALTA.getValue()%>"><spring:message code="<%=EstadoEnum.ALTA.getLabel()%>"/></option>
						<option value="<%=EstadoEnum.BAJA.getValue()%>"><spring:message code="<%=EstadoEnum.BAJA.getLabel()%>"/></option>
					</select>
				</div>
				
				<!-- Fin campos del formulario de detalle -->
				
			</div>
		</form>
	</div>
	<!-- Botonera del formulario de detalle -->
	<div class="rup-table-buttonpane ui-widget-content ui-helper-clearfix">
		<div class="ui-dialog-buttonset">
			<!-- Botón Guardar -->
			<button id="formatosFichero_detail_button_save" type="button">
				<spring:message code="save" />
			</button>
			<!-- Enlace cancelar -->
			<a href="javascript:void(0)" id="formatosFichero_detail_link_cancel"
				class="rup-enlaceCancelar"><spring:message code="cancel" /></a>
		</div>
	</div>
</div>
</div>