<%@page import="com.ejie.aa79b.model.enums.TipoSeccionAuditoriaEnum"%>
<%@include file="/WEB-INF/includeTemplate.inc"%>
<div class="container-fluid"> 
<h2><spring:message code="menu.seccionesControlDeCalidad" /></h2>
<hr class="mb-2">
	<div id="seccionesControlDeCalidad_div" class="rup-table-container">
	<div id="seccionesControlDeCalidad_feedback"></div>						<!-- Feedback de la tabla --> 
	<div id="seccionesControlDeCalidad_toolbar"></div>							<!-- Botonera de la tabla -->
	<div id="contenFormularios" class="filterForm">	<!-- Capa contenedora del formulario de filtrado -->
			<form id="seccionesControlDeCalidad_filter_form" Class="form-horizontal">						<!-- Formulario de filtrado -->
			<div id="seccionesControlDeCalidad_filter_toolbar" class="formulario_legend"></div>	<!-- Barra de herramientas del formulario de filtrado -->
			<fieldset id="seccionesControlDeCalidad_filter_fieldset" class="rup-table-filter-fieldset">
				<div class="p-2">
				<div class="row">
					<!-- Campos del formulario de filtrado -->
					<div class="form-group col-xs-6 col-md-5">
						<label for="nombreEu_filter_table" class="control-label" data-i18n="label.nombreSeccion"><spring:message code="label.nombreSeccion"/>:</label>
						<input type="text" maxlength="100" name="nombreEu" class="form-control" id="nombreEu_filter_table" />
					</div>
					<div class="form-group col-xs-6 col-md-2">
						<label for="indVisible_filter_table" class="control-label" data-i18n="label.visible"><spring:message code="label.visible" />:</label>
						<select name="indVisible" id="indVisible_filter_table" class="form-control">
							<option value="" selected="selected"><spring:message code="combo.todos"/></option>
							<option value="<%=Constants.SI%>"><spring:message code="comun.si"/></option>	
							<option value="<%=Constants.NO%>"><spring:message code="comun.no"/></option>
						</select>
					</div>
				</div>
					<!-- Fin campos del formulario de filtrado -->
				<!-- Botonera del formulario de filtrado -->
				<div class="form-group ">
				<div id="seccionesControlDeCalidad_filter_buttonSet" class="pull-right">
					<!-- Boton de filtrado -->
					<input id="seccionesControlDeCalidad_filter_filterButton" type="button" class="ui-button ui-widget ui-state-default ui-corner-all" value='<spring:message code="filter" />' />
					<!-- Enlace de limpiar -->
					<a id="seccionesControlDeCalidad_filter_cleanLink" href="javascript:void(0)" class="rup-enlaceCancelar"><spring:message code="clear" /></a>
				</div>
				</div>
				</div>
			</fieldset>
		</form>
	</div>
	<!-- Tabla -->
	<div class="table pb-2">
	<table id="seccionesControlDeCalidad"></table>
	</div>
	<!-- Barra de paginacion -->
	<div id="seccionesControlDeCalidad_pager"></div>
</div>
</div>

<div class="container-fluid">
<!-- Formulario de detalle -->
<div id="seccionesControlDeCalidad_detail_div" class="rup-table-formEdit-detail">
	<div class="ui-dialog-content ui-widget-content" >
		<form id="seccionesControlDeCalidad_detail_form">					<!-- Formulario -->
			<div id ="seccionesControlDeCalidad_detail_feedback"></div>		<!-- Feedback del formulario de detalle -->
			<!-- Campos del formulario de detalle -->
			<div class="row">
				<div class="form-group  col-lg-4">
					<label for="id_detail_table" class="control-label"><spring:message code="label.id"/>:</label>
					<input type="text" name="id" maxlength="3" class="form-control col-40por" id="id_detail_table" readonly="readonly" disabled="disabled" />
				</div>
			</div>
			<div class="row">
				<div class="form-group col-lg-3">
					<label for="tipoSeccion_detail_table" class="control-label"><spring:message code="label.tipoSeccion"/>:</label>
					<div class="col-xs-12 no-padding">
						<select name="tipoSeccion" id="tipoSeccion_detail_table" class="form-control" disabled="disabled">
							<option value="<%=TipoSeccionAuditoriaEnum.SECCION_DE_VALORACION.getValue()%>"><spring:message code="<%=TipoSeccionAuditoriaEnum.SECCION_DE_VALORACION.getLabel()%>"/></option>
							<option value="<%=TipoSeccionAuditoriaEnum.SECCION_DE_DOCUMENTOS.getValue()%>"><spring:message code="<%=TipoSeccionAuditoriaEnum.SECCION_DE_DOCUMENTOS.getLabel()%>"/></option>
							<option value="<%=TipoSeccionAuditoriaEnum.SECCION_DE_INFORMACION.getValue()%>" selected="true"><spring:message code="<%=TipoSeccionAuditoriaEnum.SECCION_DE_INFORMACION.getLabel()%>"/></option>
						</select>
					</div>
				</div>
				<div class="form-group  col-lg-3">
					<label for="indRespuesta_detail_table" class="control-label"><spring:message code="label.respuesta"/>:</label>
					<div class="col-xs-12 no-padding">
						<input type="checkbox" name="indRespuesta" class="form-control" value="S" data-switch="true" id="indRespuesta_detail_table"/>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="form-group  col-md-10">
					<label for="nombreEu_detail_table" class="control-label"><spring:message code="label.nombreSeccion"/>:</label>
					<input type="text" name="nombreEu" maxlength="100" class="form-control col-90por" id="nombreEu_detail_table"/>
				</div>
			</div>
			<div class="row">
				<div class="form-group  col-lg-3">
					<label for="indObservaciones_detail_table" class="control-label"><spring:message code="label.indObservaciones"/>:</label>
					<div class="col-xs-12 no-padding">
						<input type="checkbox" name="indObservaciones" class="form-control" value="S" data-switch="true" id="indObservaciones_detail_table"/>
					</div>
				</div>
				<div class="form-group  col-lg-3">
					<label for="indVisible_detail_table" class="control-label"><spring:message code="label.visible"/>:</label>
					<div class="col-xs-12 no-padding">
						<input type="checkbox" name="indVisible" class="form-control" value="S" data-switch="true" id="indVisible_detail_table"/>
					</div>
				</div>
				<div class="form-group  col-lg-4">
					<label for="orden_detail_table" class="control-label"><spring:message code="label.orden"/>:</label>
					<input type="text" name="orden" maxlength="2" class="form-control numeric col-20por" id="orden_detail_table" />
				</div>
			</div>
			<!-- Fin campos del formulario de detalle -->
		</form>
	</div>
	<!-- Botonera del formulario de detalle -->
	<div class="rup-table-buttonpane ui-widget-content ui-helper-clearfix">
		<div class="ui-dialog-buttonset">
			<!-- Boton Guardar -->
			<button id="seccionesControlDeCalidad_detail_button_save" type="button">
				<spring:message code="save" />
			</button>
			<!-- Enlace cancelar -->
			<a href="javascript:void(0)" id="seccionesControlDeCalidad_detail_link_cancel"
				class="rup-enlaceCancelar"><spring:message code="cancel" /></a>
		</div>
	</div>
</div>
</div>