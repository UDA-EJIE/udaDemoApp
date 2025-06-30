<%@page import="com.ejie.aa79b.model.enums.TipoFacturacionEnum"%>
<%@page import="com.ejie.aa79b.model.enums.EstadoEnum"%>
<%@include file="/WEB-INF/includeTemplate.inc"%>
<div class="container-fluid"> 
<h2><spring:message code="menu.nivelesDeCalidad" /></h2>
<hr class="mb-2">
	<div id="nivelesDeCalidad_div" class="rup-table-container">
	<div id="nivelesDeCalidad_feedback"></div>						<!-- Feedback de la tabla --> 
	<div id="nivelesDeCalidad_toolbar"></div>							<!-- Botonera de la tabla -->
	<div id="contenFormularios" class="filterForm">	<!-- Capa contenedora del formulario de filtrado -->
			<form id="nivelesDeCalidad_filter_form" Class="form-horizontal">						<!-- Formulario de filtrado -->
			<div id="nivelesDeCalidad_filter_toolbar" class="formulario_legend"></div>	<!-- Barra de herramientas del formulario de filtrado -->
			<fieldset id="nivelesDeCalidad_filter_fieldset" class="rup-table-filter-fieldset">
				<div class="p-2">
				<div class="row">
					
				</div>
				<!-- Botonera del formulario de filtrado -->
				<div class="form-group ">
				<div id="nivelesDeCalidad_filter_buttonSet" class="pull-right">
					<!-- Boton de filtrado -->
					<input id="nivelesDeCalidad_filter_filterButton" type="button" class="ui-button ui-widget ui-state-default ui-corner-all" value='<spring:message code="filter" />' />
					<!-- Enlace de limpiar -->
					<a id="nivelesDeCalidad_filter_cleanLink" href="javascript:void(0)" class="rup-enlaceCancelar"><spring:message code="clear" /></a>
				</div>
				</div>
				</div>
			</fieldset>
		</form>
	</div>
	<!-- Tabla -->
	<div class="table pb-2">
	<table id="nivelesDeCalidad"></table>
	</div>
	<!-- Barra de paginacion -->
	<div id="nivelesDeCalidad_pager"></div>
</div>
</div>

<div class="container-fluid">
<!-- Formulario de detalle -->
<div id="nivelesDeCalidad_detail_div" class="rup-table-formEdit-detail">
	<div class="ui-dialog-content ui-widget-content" >
		<form id="nivelesDeCalidad_detail_form">					<!-- Formulario -->
			<div id ="nivelesDeCalidad_detail_feedback"></div>		<!-- Feedback del formulario de detalle -->
				<div class="row">
					<div class="form-group  col-lg-4">
						<label for="id_detail_table" class="control-label"><spring:message code="label.id"/>:</label>
						<input type="text" name="id" class="form-control col-40por" id="id_detail_table"  readonly="readonly" disabled="disabled" />
					</div>
				</div>
				<div class="row">
					<div class="col-lg-2">
						<label for="nivel_detail_table" class="control-label col-lg-12"><spring:message code="label.nivel"/>(*):</label>
						<div class="form-group  col-lg-8">
							<input type="text" name="nivel" class="form-control" id="nivel_detail_table" maxlength="2"/>
						</div>
					</div>
					<div class="col-lg-3">
						<label for="rangoIni_detail_table" class="control-label col-lg-12"><spring:message code="label.rangoIni"/>(*):</label>
						<div class="form-group  col-lg-6">
							<input type="text" name="rangoIni" class="form-control" id="rangoIni_detail_table" maxlength="2"/>
						</div>
					</div>
					<div class="col-lg-3">
						<label for="rangoFin_detail_table" class="control-label col-lg-12"><spring:message code="label.rangoFin"/>(*):</label>
						<div class="form-group  col-lg-6">
							<input type="text" name="rangoFin" class="form-control" id="rangoFin_detail_table" maxlength="2"/>
						</div>
					</div>
				</div>
				<div class="row">
					<label for="porPenalizacion_detail_table" class="control-label col-lg-12"><spring:message code="label.porcentajePenalizacion"/>(*):</label>
					<div class="form-group  col-lg-1">
						<input type="text" name="porPenalizacion" class="form-control" id="porPenalizacion_detail_table" maxlength="3"/>
					</div>
				</div>
				<div class="row">
					<div class="form-group  col-lg-2">
						<label for="estado_detail_table" class="control-label"><spring:message code="label.estado"/>:</label>
						<select name="estado" id="estado_detail_table" class="form-control">
							<option value="<%=EstadoEnum.ALTA.getValue()%>" selected="true"><spring:message code="<%=EstadoEnum.ALTA.getLabel()%>"/></option>
										<option value="<%=EstadoEnum.BAJA.getValue()%>"><spring:message code="<%=EstadoEnum.BAJA.getLabel()%>"/></option>
						</select>
					</div>
				</div>
		</form>
	</div>
	<!-- Botonera del formulario de detalle -->
	<div class="rup-table-buttonpane ui-widget-content ui-helper-clearfix">
		<div class="ui-dialog-buttonset">
			<!-- Boton Guardar -->
			<button id="nivelesDeCalidad_detail_button_save" type="button">
				<spring:message code="save" />
			</button>
			<!-- Enlace cancelar -->
			<a href="javascript:void(0)" id="nivelesDeCalidad_detail_link_cancel"
				class="rup-enlaceCancelar"><spring:message code="cancel" /></a>
		</div>
	</div>
</div>
</div>