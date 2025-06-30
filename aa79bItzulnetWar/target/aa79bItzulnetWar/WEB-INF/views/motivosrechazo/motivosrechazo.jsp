<%@page import="com.ejie.aa79b.model.enums.EstadoEnum"%>
<%@include file="/WEB-INF/includeTemplate.inc"%>
<div class="container-fluid"> 
<h2><spring:message code="menu.motivosRechazo" /></h2>
<hr class="mb-2">
<div id="motivosrechazo_div" class="rup-table-container">
	<div id="motivosrechazo_feedback"></div>
	<!-- Feedback de la tabla -->
	<div id="motivosrechazo_toolbar"></div>
	<!-- Botonera de la tabla -->
	<div id="contenFormularios" class="filterForm ">
		<!-- Capa contenedora del formulario de filtrado -->
		<form id="motivosrechazo_filter_form" Class="form-horizontal">
			<!-- Formulario de filtrado -->
			<div id="motivosrechazo_filter_toolbar" class="formulario_legend"></div>
			<!-- Barra de herramientas del formulario de filtrado -->
			<fieldset id="motivosrechazo_filter_fieldset" class="rup-table-filter-fieldset">
			<div class="p-2">
				<div class="row">
					<!-- Campos del formulario de filtrado -->
					<div class="form-group col-xs-6 col-md-1">
						<label for="id013_filter_table" class="control-label" data-i18n="label.id"><spring:message code="label.id"/>:</label>
						<input type="text" maxlength="3" name="id013" class="form-control numeric" id="id013_filter_table" />
					</div>
					<div class="form-group col-xs-12 col-md-4">
						<label for="descEu013_filter_table" class="control-label" data-i18n="label.descEu"><spring:message code="label.descEu" />:</label>
						<input type="text"  maxlength="50" name="descEu013" class="form-control" id="descEu013_filter_table" />
					</div>
					<div class="form-group col-xs-12 col-md-4">
						<label for="descEs013_filter_table"class="control-label" data-i18n="label.descEs"><spring:message code="label.descEs" />:</label>
						<input type="text"  maxlength="50" name="descEs013" class="form-control" id="descEs013_filter_table" />
					</div>
					<div class="form-group col-xs-1 col-md-2">
							<label for="estado013_filter_table" class="control-label" data-i18n="label.estado"><spring:message code="label.estado" />:</label>
								<select name="estado013" id="estado013_filter_table" class="form-control">
									<option value=""><spring:message code="combo.todos"/></option>
									<option value="<%=EstadoEnum.ALTA.getValue()%>" selected="selected"><spring:message code="<%=EstadoEnum.ALTA.getLabel()%>"/></option>
									<option value="<%=EstadoEnum.BAJA.getValue()%>"><spring:message code="<%=EstadoEnum.BAJA.getLabel()%>"/></option>
								</select>
						</div>
						<!-- Fin campos del formulario de filtrado -->
					</div>
					<!-- Botonera del formulario de filtrado -->
					<div class="form-group ">
					<div id="motivosrechazo_filter_buttonSet" class="pull-right">
						<!-- Botón de filtrado -->
						<button id="motivosrechazo_filter_filterButton" type="button" class="ui-button ui-widget ui-state-default ui-corner-all"><spring:message code="filter" /></button>
						<!-- Enlace de limpiar -->
						<a id="motivosrechazo_filter_cleanLink" href="javascript:void(0)" class="btn btn-link"><spring:message code="clear" /></a>
					</div>
				</div>
				</div>
			</fieldset>
		</form>
	</div>

		<!-- Tabla -->
		<div class="table pb-2">
		<table id="motivosrechazo"></table>
		</div>
		<!-- Barra de paginación -->
		<div id="motivosrechazo_pager"></div>
	</div>
</div>
<div class="container-fluid">
<!-- Formulario de detalle -->
<div id="motivosrechazo_detail_div" class="rup-table-formEdit-detail">
	<!-- Barra de navegación del detalle -->
	<div class="ui-dialog-content ui-widget-content">
		<form id="motivosrechazo_detail_form">
			<!-- Formulario -->
			<div id="motivosrechazo_detail_feedback"></div>
			<!-- Feedback del formulario de detalle -->

				<!-- Campos del formulario de detalle -->
				<div class="row">
				<div class="form-group  col-lg-4">
					<label for="id013_detail_table" class="control-label"><spring:message code="label.id" />:</label> 
					<input type="text" name="id013" class="form-control col-40por" id="id013_detail_table" readonly="readonly" disabled="disabled" />
				</div>
				</div>
				<div class="row">
				<div class="form-group  col-lg-4">
					<label for="descEu013_detail_table"><spring:message code="label.descEu" />(*):</label> 
					<input type="text" maxlength="50" name="descEu013" class="form-control" id="descEu013_detail_table" />
				</div>
				<div class="form-group  col-lg-4">
					<label for="descEs013_detail_table" class="control-label"><spring:message code="label.descEs" />(*):</label> 
					<input type="text" maxlength="50" name="descEs013" class="form-control" id="descEs013_detail_table" />
				</div>
				</div>
				<div class="row">
				<div class="form-group  col-lg-2">
					<label for="estado013_detail_table" class="control-label"><spring:message code="label.estado" />:</label>
					<select name="estado013" id="estado013_detail_table" class="form-control">
					<option value="<%=EstadoEnum.ALTA.getValue()%>"><spring:message code="<%=EstadoEnum.ALTA.getLabel()%>"/></option>
					<option value="<%=EstadoEnum.BAJA.getValue()%>"><spring:message code="<%=EstadoEnum.BAJA.getLabel()%>"/></option>
					</select>
				</div>
				</div>
				<!-- Fin campos del formulario de detalle -->

		</form>
	</div>
	<!-- Botonera del formulario de detalle -->
	<div class="rup-table-buttonpane ui-widget-content ui-helper-clearfix">
		<div class="ui-dialog-buttonset">
			<!-- Botón Guardar -->
			<button id="motivosrechazo_detail_button_save" type="button">
				<spring:message code="save" />
			</button>
			<!-- Enlace cancelar -->
			<a href="javascript:void(0)" id="motivosrechazo_detail_link_cancel"
				class="rup-enlaceCancelar"><spring:message code="cancel" /></a>
		</div>
	</div>
</div>
</div>