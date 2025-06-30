<%@page import="com.ejie.aa79b.model.enums.EstadoEnum"%>
<%@include file="/WEB-INF/includeTemplate.inc"%>
<div class="container-fluid"> 
<h2><spring:message code="menu.tiposRelevancia"/></h2>
<hr class="mb-2">
	<div id="tipoRelevancia_div" class="rup-table-container">
	<div id="tipoRelevancia_feedback"></div>						<!-- Feedback de la tabla --> 
	<div id="tipoRelevancia_toolbar"></div>							<!-- Botonera de la tabla -->
	<div id="contenFormularios" class="filterForm ">
		<!-- Capa contenedora del formulario de filtrado -->
			<form id="tipoRelevancia_filter_form" Class="form-horizontal">						<!-- Formulario de filtrado -->
			<div id="tipoRelevancia_filter_toolbar" class="formulario_legend" ></div>	<!-- Barra de herramientas del formulario de filtrado -->
			<fieldset id="tipoRelevancia_filter_fieldset" class="rup-table-filter-fieldset">
				<div class="p-2">
					<div class=" row">
					<!-- Campos del formulario de filtrado -->
					<div class="form-group col-xs-6 col-md-1">
						<label for="idTipoRelevancia_filter_table" class="control-label" data-i18n="label.id"><spring:message code="label.id"/>:</label>
						<input type="text" name="idTipoRelevancia" class="form-control numeric" id="idTipoRelevancia_filter_table" maxlength="2"/>
					</div>
					<div class="form-group col-xs-12 col-md-3">
						<label for="descRelevanciaEu_filter_table" class="control-label" data-i18n="label.descEu"><spring:message code="label.descEu"/>:</label>
						<input type="text" name="descRelevanciaEu" class="form-control" id="descRelevanciaEu_filter_table" maxlength="50"/>
					</div>
					<div class="form-group col-xs-12 col-md-3">
							<label for="descRelevanciaEs_filter_table" class="control-label" data-i18n="label.descEs"><spring:message code="label.descEs"/>:</label>
							<input type="text" name="descRelevanciaEs" class="form-control" id="descRelevanciaEs_filter_table" maxlength="50"/>
					</div>
					<div class="form-group col-xs-1 col-md-2">
							<label for="estado_filter_table" class="control-label" data-i18n="label.estado"><spring:message code="label.estado"/>:</label>
								<select name="estado" id="estado_filter_table" class="form-control col-80por">
									<option value=""><spring:message code="combo.todos"/></option>
									<option value="<%=EstadoEnum.ALTA.getValue()%>" selected="selected"><spring:message code="<%=EstadoEnum.ALTA.getLabel()%>"/></option>
									<option value="<%=EstadoEnum.BAJA.getValue()%>"><spring:message code="<%=EstadoEnum.BAJA.getLabel()%>"/></option>
								</select>
						</div>
					</div>
					<!-- Fin campos del formulario de filtrado -->
				</div>
				<!-- Botonera del formulario de filtrado -->
				<div class="form-group ">
					<div id="tipoRelevancia_filter_buttonSet" class="pull-right">
						<!-- Botón de filtrado -->
						<button id="tipoRelevancia_filter_filterButton" type="button" class="ui-button ui-widget ui-state-default ui-corner-all"><spring:message code="filter" /></button>
						<!-- Enlace de limpiar -->
						<a id="tipoRelevancia_filter_cleanLink" href="javascript:void(0)" class="btn btn-link"><spring:message code="clear" /></a>
					</div>
				</div>
			</fieldset>
		</form>
	</div>

		<div class="table pb-2">
			<table id="tipoRelevancia"></table>
		</div>
		<div id="tipoRelevancia_pager"></div>
	
</div>	

<!-- Formulario de detalle -->
<div id="tipoRelevancia_detail_div" class="rup-table-formEdit-detail">
	<div class="ui-dialog-content ui-widget-content" >
		<form id="tipoRelevancia_detail_form">					<!-- Formulario -->
			<div id ="tipoRelevancia_detail_feedback"></div>		<!-- Feedback del formulario de detalle -->
			
			<div class="row">
				<div class="form-group  col-lg-4">
					<label for="idTipoRelevancia_detail_table"  class="control-label"><spring:message code="label.id"/>:</label>
					<input type="text" name="idTipoRelevancia" class="form-control numeric col-40por" id="idTipoRelevancia_detail_table" readonly="readonly" disabled="disabled"/>
				</div>	
				<div class="form-group  col-lg-4">
					<label for="prioridad_detail_table"  class="control-label"><spring:message code="label.prioridad"/>(*):</label>
					<input type="text" name="prioridad" class="form-control numeric col-40por" id="prioridad_detail_table" maxlength="2"/>
				</div>
			</div>
			<div class="row">
				<div class="form-group  col-lg-4">
					<label for="descRelevanciaEu_detail_table" class="control-label"><spring:message code="label.descEu"/>(*):</label>
					<input type="text" name="descRelevanciaEu" class="form-control" id="descRelevanciaEu_detail_table" maxlength="50"/>
				</div>
				<div class="form-group  col-lg-4">
					<label for="descRelevanciaEs_detail_table" class="control-label"><spring:message code="label.descEs"/>(*):</label>
					<input type="text" name="descRelevanciaEs" class="form-control" id="descRelevanciaEs_detail_table" maxlength="50"/>										
				</div>	
			</div>
			<div class="row">
				<div class="form-group  col-lg-2">
					<label for="estado_detail_table" class="control-label"><spring:message code="label.estado"/>:</label>
					<select name="estado" id="estado_detail_table" class="form-control col-60por">
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
			<button id="tipoRelevancia_detail_button_save" type="button">
				<spring:message code="save" />
			</button>
			<!-- Enlace cancelar -->
			<a href="javascript:void(0)" id="tipoRelevancia_detail_link_cancel"
				class="rup-enlaceCancelar"><spring:message code="cancel" /></a>
		</div>
	</div>
</div>

 <!-- <div id="buscadorPersonas" style="display:none"></div>  -->
