<%@page import="com.ejie.aa79b.model.enums.EstadoEnum"%>
<%@include file="/WEB-INF/includeTemplate.inc"%>
<div class="container-fluid"> 
<h2><spring:message code="menu.modosInterpretacion" /></h2>
<hr class="mb-2">
	<div id="modosInterpretacion_div" class="rup-table-container">
	<div id="modosInterpretacion_feedback"></div>						<!-- Feedback de la tabla --> 
	<div id="modosInterpretacion_toolbar"></div>							<!-- Botonera de la tabla -->
	<div id="modosInterpretacion_filter_div" class="filterForm">	
	<!-- Capa contenedora del formulario de filtrado -->
			<form id="modosInterpretacion_filter_form" Class="form-horizontal">						<!-- Formulario de filtrado -->
			<div id="modosInterpretacion_filter_toolbar" class="formulario_legend"></div>	<!-- Barra de herramientas del formulario de filtrado -->
			<fieldset id="modosInterpretacion_filter_fieldset" class="rup-table-filter-fieldset">
				<div class="p-2">
				<div class="row">
					<!-- Campos del formulario de filtrado -->
					<div class="form-group col-xs-6 col-md-1">
						<label for="id_filter_table" class="control-label" data-i18n="label.id"><spring:message code="label.id"/>:</label>
						<input type="text" maxlength="3" name="id" class="form-control numeric" id="id_filter_table"/>
					</div>
					<div class="form-group col-xs-12 col-md-4">
						<label for="descEu_filter_table" class="control-label" data-i18n="label.descEu"><spring:message code="label.descEu"/>:</label>
						<input type="text" name="descEu" class="form-control" id="descEu_filter_table" maxlength="50"/>
					</div>
					<div class="form-group col-xs-12 col-md-4">
						<label for="descEs_filter_table" class="control-label" data-i18n="label.descEs"><spring:message code="label.descEs"/>:</label>
						<input type="text" name="descEs" class="form-control" id="descEs_filter_table" maxlength="50"/>
					</div>
					<div class="form-group col-xs-1 col-md-2 no-padding">
						<label for="estado014_filter_table" class="control-label" data-i18n="label.estado"><spring:message code="label.estado"/>:</label>
						<select name="estado" id="estado014_filter_table" class="form-control">
							<option value=""><spring:message code="combo.todos"/></option>
							<option value="<%=EstadoEnum.ALTA.getValue()%>" selected="selected"><spring:message code="<%=EstadoEnum.ALTA.getLabel()%>"/></option>
							<option value="<%=EstadoEnum.BAJA.getValue()%>"><spring:message code="<%=EstadoEnum.BAJA.getLabel()%>"/></option>
						</select>
					</div>
					<!-- Fin campos del formulario de filtrado -->
				</div>
				<!-- Botonera del formulario de filtrado -->
				<div class="form-group ">
				<div id="modosInterpretacion_filter_buttonSet" class="pull-right">
					<!-- Botón de filtrado -->
					<button id="modosInterpretacion_filter_filterButton" type="button" class="ui-button ui-widget ui-state-default ui-corner-all"><spring:message code="filter" /></button>
					<!-- Enlace de limpiar -->
					<a id="modosInterpretacion_filter_cleanLink" href="javascript:void(0)" class="rup-enlaceCancelar"><spring:message code="clear" /></a>
				</div>
				</div>
				</div>
			</fieldset>
		</form>
	</div>

	
		<!-- Tabla -->
		<div class="table pb-2">
		<table id="modosInterpretacion"></table>
		</div>
		<!-- Barra de paginación -->
		<div id="modosInterpretacion_pager"></div>
</div>	
</div>	
<div class="container-fluid">
<!-- Formulario de detalle -->
<div id="modosInterpretacion_detail_div" class="rup-table-formEdit-detail">
	<div class="ui-dialog-content ui-widget-content" >
		<form id="modosInterpretacion_detail_form">					<!-- Formulario -->
			<div id ="modosInterpretacion_detail_feedback"></div>		<!-- Feedback del formulario de detalle -->
			
				<!-- Campos del formulario de detalle -->
				<div class="row">
				<div class="form-group  col-lg-4">
					<label for="id_detail_table" class="control-label"><spring:message code="label.id"/>:</label>
					<input type="text" name="id" class="form-control col-40por numeric" id="id_detail_table" readonly="readonly" disabled="disabled" maxlength="3"/>
				</div>
				</div>		
				<div class="row">
				<div class="form-group  col-lg-4">
					<label for="descEs_detail_table" class="control-label"><spring:message code="label.descEs"/>:</label>
					<input type="text" name="descEs"  class="form-control" id="descEs_detail_table" maxlength="50"/>
				</div>
				<div class="form-group  col-lg-4">
					<label for="descEu_detail_table" class="control-label"><spring:message code="label.descEu"/>:</label>
					<input type="text" name="descEu"  class="form-control" id="descEu_detail_table" maxlength="50" />
				</div>
				</div>	
				<div class="row">
				<div class="form-group  col-lg-2">
					<label for="estado014_detail_table" class="control-label"><spring:message code="label.estado"/>:</label>
				<select name="estado" id="estado014_detail_table"  class="form-control"></select>
				</div>
				</div>	
				<!-- Fin campos del formulario de detalle -->
		</form>
	</div>
	<!-- Botonera del formulario de detalle -->
	<div class="rup-table-buttonpane ui-widget-content ui-helper-clearfix">
		<div class="ui-dialog-buttonset">
			<!-- Botón Guardar -->
			<button id="modosInterpretacion_detail_button_save" type="button">
				<spring:message code="save" />
			</button>
			<!-- Enlace cancelar -->
			<a href="javascript:void(0)" id="modosInterpretacion_detail_link_cancel"
				class="rup-enlaceCancelar"><spring:message code="cancel" /></a>
		</div>
	</div>
</div>
</div>
