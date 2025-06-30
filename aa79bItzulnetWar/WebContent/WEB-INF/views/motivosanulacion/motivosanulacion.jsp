<%@page import="com.ejie.aa79b.model.enums.EstadoEnum"%>
<%@include file="/WEB-INF/includeTemplate.inc"%>
<div class="container-fluid"> 
<h2><spring:message code="menu.motivosAnulacion" /></h2>
<hr class="mb-2">
	<div id="motivosanulacion_div" class="rup-table-container">	
	<div id="motivosanulacion_feedback"></div>						<!-- Feedback de la tabla --> 
	<div id="motivosanulacion_toolbar"></div>							<!-- Botonera de la tabla -->
	<div id="contenFormularios" class="filterForm ">
	<!-- Capa contenedora del formulario de filtrado -->
			<form id="motivosanulacion_filter_form" Class="form-horizontal"><!-- Formulario de filtrado -->
			<div id="motivosanulacion_filter_toolbar" class="formulario_legend"></div>	<!-- Barra de herramientas del formulario de filtrado -->
			<fieldset id="motivosanulacion_filter_fieldset">
				<div class="p-2">
					<div class="row">
					<!-- Campos del formulario de filtrado -->
					<div class="form-group col-xs-6 col-md-1">
						<label for="id012_filter_table" class="control-label" data-i18n="label.id"><spring:message code="label.id"/>:</label>
						<input type="text" maxlength="3" name="id012" class="form-control numeric" id="id012_filter_table"/>
					</div>
					<div class="form-group col-xs-12 col-md-4">
						<label for="descEu012_filter_table" class="control-label" data-i18n="label.descEu"><spring:message code="label.descEu"/>:</label>
						<input type="text" name="descEu012" class="form-control" id="descEu012_filter_table"/>
					</div>
					<div class="form-group col-xs-12 col-md-4">
						<label for="descEs012_filter_table" class="control-label" data-i18n="label.descEs"><spring:message code="label.descEs"/>:</label>
						<input type="text" name="descEs012" class="form-control" id="descEs012_filter_table"/>
					</div>
					<div class="form-group col-xs-1 col-md-2">
						<label for="estado012_filter_table"  class="control-label" data-i18n="label.estado"><spring:message code="label.estado"/>:</label>
						<select name="estado012" id="estado012_filter_table" class="form-control">
							<option value=""><spring:message code="combo.todos"/></option>
							<option value="<%=EstadoEnum.ALTA.getValue()%>" selected="selected"><spring:message code="<%=EstadoEnum.ALTA.getLabel()%>"/></option>
							<option value="<%=EstadoEnum.BAJA.getValue()%>"><spring:message code="<%=EstadoEnum.BAJA.getLabel()%>"/></option>
						</select>
					</div>
					<!-- Fin campos del formulario de filtrado -->
				</div>
				<!-- Botonera del formulario de filtrado -->
				<div class="form-group ">
				<div id="motivosanulacion_filter_buttonSet" class="pull-right">
					<!-- Botón de filtrado -->
					<input id="motivosanulacion_filter_filterButton" type="button" class="ui-button ui-widget ui-state-default ui-corner-all" value='<spring:message code="filter" />' />
					<!-- Enlace de limpiar -->
					<a id="motivosanulacion_filter_cleanLink" href="javascript:void(0)" class="rup-enlaceCancelar"><spring:message code="clear" /></a>
				</div>
				</div>
				</div>
			</fieldset>
		</form>
	</div>


		<!-- Tabla -->
		<div class="table pb-2">
		<table id="motivosanulacion"></table>
		</div>
		<!-- Barra de paginación -->
		<div id="motivosanulacion_pager"></div>

</div>
</div>

<div class="container-fluid">
<!-- Formulario de detalle -->
<div id="motivosanulacion_detail_div" class="rup-table-formEdit-detail">
	<!-- Barra de navegación del detalle -->
	<div class="ui-dialog-content ui-widget-content">
		<form id="motivosanulacion_detail_form">
			<!-- Formulario -->
			<div id="motivosanulacion_detail_feedback"></div>
			<!-- Feedback del formulario de detalle -->

				<!-- Campos del formulario de detalle -->
				<div class="row">
				<div class="form-group  col-lg-4">
					<label for="id012_detail_table" class="control-label"><spring:message code="label.id" />:</label>
					<input type="text" name="id012"  class="form-control col-40por" id="id012_detail_table" readonly="readonly" disabled="disabled" />
				</div>
				</div>	
				<div class="row">
				<div class="form-group  col-lg-4">
					<label for="descEu012_detail_table" class="control-label"><spring:message code="label.descEu" />(*):</label> 
					<input type="text" maxlength="50" name="descEu012" class="form-control" id="descEu012_detail_table" maxlength="50"/>
				</div>
				<div class="form-group  col-lg-4">
					<label for="descEs012_detail_table" class="control-label"><spring:message code="label.descEs" />(*):</label>
					<input type="text" maxlength="50" name="descEs012" class="form-control" id="descEs012_detail_table" maxlength="50"/>
				</div>
				</div>
				<div class="row">
				<div class="form-group  col-lg-2">
					<label for="estado012_detail_table" class="control-label"><spring:message code="label.estado"/>:</label> 
					<select name="estado012" id="estado012_detail_table"class="form-control">
					<option value="<%=EstadoEnum.ALTA.getValue()%>"><spring:message code="<%=EstadoEnum.ALTA.getLabel()%>"/></option>
					<option value="<%=EstadoEnum.BAJA.getValue()%>"><spring:message code="<%=EstadoEnum.BAJA.getLabel()%>"/></option>
					</select>
				</div>
				</div>
		</form>
	</div>
	<!-- Botonera del formulario de detalle -->
	<div class="rup-table-buttonpane ui-widget-content ui-helper-clearfix">
		<div class="ui-dialog-buttonset">
			<!-- Botón Guardar -->
			<button id="motivosanulacion_detail_button_save" type="button">
				<spring:message code="save" />
			</button>
			<!-- Enlace cancelar -->
			<a href="javascript:void(0)" id="motivosanulacion_detail_link_cancel"
				class="rup-enlaceCancelar"><spring:message code="cancel" /></a>
		</div>
	</div>
</div>
</div>