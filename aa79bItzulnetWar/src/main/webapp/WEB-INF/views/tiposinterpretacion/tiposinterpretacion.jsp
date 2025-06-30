<%@page import="com.ejie.aa79b.model.enums.TipoFacturacionEnum"%>
<%@page import="com.ejie.aa79b.model.enums.EstadoEnum"%>
<%@include file="/WEB-INF/includeTemplate.inc"%>
<div class="container-fluid"> 
<h2><spring:message code="menu.tiposInterpretacion" /></h2>
<hr class="mb-2">
	<div id="tiposinterpretacion_div" class="rup-table-container">
	<div id="tiposinterpretacion_feedback"></div>						<!-- Feedback de la tabla --> 
	<div id="tiposinterpretacion_toolbar"></div>							<!-- Botonera de la tabla -->
	<div id="contenFormularios" class="filterForm ">	<!-- Capa contenedora del formulario de filtrado -->
			<form id="tiposinterpretacion_filter_form" Class="form-horizontal">						<!-- Formulario de filtrado -->
			<div id="tiposinterpretacion_filter_toolbar" class="formulario_legend"></div>	<!-- Barra de herramientas del formulario de filtrado -->
			<fieldset id="tiposinterpretacion_filter_fieldset" class="rup-table-filter-fieldset">
				<div class="p-2">
				<div class="row">
					<!-- Campos del formulario de filtrado -->
					<div class="form-group col-xs-6 col-md-1">
						<label for="id008_filter_table"  class="control-label" data-i18n="label.id"><spring:message code="label.id"/>:</label>
						<input type="text" name="id008" class="form-control numeric" id="id008_filter_table" maxlength="3"/>
					</div>
					<div class="form-group col-xs-12 col-md-4">
						<label for="descEu008_filter_table" class="control-label" data-i18n="label.descEu"><spring:message code="label.descEu"/>:</label>
						<input type="text" name="descEu008" class="form-control" id="descEu008_filter_table" maxlength="50"/>
					</div>
					<div class="form-group col-xs-12 col-md-4">
						<label for="descEs008_filter_table" class="control-label" data-i18n="label.descEs"><spring:message code="label.descEs"/>:</label>
						<input type="text" name="descEs008" class="form-control" id="descEs008_filter_table" maxlength="50"/>
					</div>
					<div class="row">
					<div class="form-group col-xs-12 col-md-2">
						<label for="estado008_filter_table" class="control-label" data-i18n="label.estado"><spring:message code="label.estado"/>:</label>
								<select name="estado008" id="estado008_filter_table" class="form-control col-80por">
								<option value=""><spring:message code="combo.todos"/></option>
								<option value="<%=EstadoEnum.ALTA.getValue()%>" selected="selected"><spring:message code="<%=EstadoEnum.ALTA.getLabel()%>"/></option>
								<option value="<%=EstadoEnum.BAJA.getValue()%>"><spring:message code="<%=EstadoEnum.BAJA.getLabel()%>"/></option>	
						</select>
						</div>
					</div>
					<div class="form-group col-xs-1 col-md-1">
						<label for="tipoFacturacion008_filter_table"class="control-label" data-i18n="label.facturacion"><spring:message code="label.facturacion"/>:</label>
							<select name="tipoFacturacion008" id="tipoFacturacion008_filter_table" class="form-control col-80por">
								<option value=""><spring:message code="combo.todos"/></option>
								<option value="<%=TipoFacturacionEnum.EURO_INTERPRETE.getValue()%>"><spring:message code="<%=TipoFacturacionEnum.EURO_INTERPRETE.getLabel()%>"/></option>
								<option value="<%=TipoFacturacionEnum.EURO_HORA_INTERPRETE.getValue()%>"><spring:message code="<%=TipoFacturacionEnum.EURO_HORA_INTERPRETE.getLabel()%>"/></option>	
						</select>
					</div>
					<!-- Fin campos del formulario de filtrado -->
				</div>
				<!-- Botonera del formulario de filtrado -->
				<div class="form-group ">
				<div id="tiposinterpretacion_filter_buttonSet" class="pull-right">
					<!-- Boton de filtrado -->
					<input id="tiposinterpretacion_filter_filterButton" type="button" class="ui-button ui-widget ui-state-default ui-corner-all" value='<spring:message code="filter" />' />
					<!-- Enlace de limpiar -->
					<a id="tiposinterpretacion_filter_cleanLink" href="javascript:void(0)" class="rup-enlaceCancelar"><spring:message code="clear" /></a>
				</div>
				</div>
				</div>
			</fieldset>
		</form>
	</div>


		<!-- Tabla -->
		<div class="table pb-2">
		<table id="tiposinterpretacion"></table>
		</div>
		<!-- Barra de paginacion -->
		<div id="tiposinterpretacion_pager"></div>
</div>
</div>

<div class="container-fluid">
<!-- Formulario de detalle -->
<div id="tiposinterpretacion_detail_div" class="rup-table-formEdit-detail">
	<div class="ui-dialog-content ui-widget-content" >
		<form id="tiposinterpretacion_detail_form">					<!-- Formulario -->
			<div id ="tiposinterpretacion_detail_feedback"></div>		<!-- Feedback del formulario de detalle -->
			
				<!-- Campos del formulario de detalle -->
				<div class="row">
				<div class="form-group  col-lg-4">
					<label for="id008_detail_table" class="control-label"><spring:message code="label.id"/>:</label>
					<input type="text" name="id008" class="form-control col-40por" id="id008_detail_table"  readonly="readonly" disabled="disabled" />
				</div>
				</div>
				<div class="row">
				<div class="form-group  col-lg-4">
					<label for="descEs008_detail_table" class="control-label"><spring:message code="label.descEs"/>(*):</label>
					<input type="text" name="descEs008" class="form-control" id="descEs008_detail_table" maxlength="50"/>
				</div>
				<div class="form-group  col-lg-4">
					<label for="descEu008_detail_table" class="control-label"><spring:message code="label.descEu"/>(*):</label>
					<input type="text" name="descEu008" class="form-control" id="descEu008_detail_table" maxlength="50"/>
				</div>
				</div>
				<div class="row">
				<div class="form-group  col-lg-2">
					<label for="estado008_detail_table" class="control-label"><spring:message code="label.estado"/>:</label>
					<select name="estado008" id="estado008_detail_table" class="form-control">
						<option value="<%=EstadoEnum.ALTA.getValue()%>" selected="true"><spring:message code="<%=EstadoEnum.ALTA.getLabel()%>"/></option>
									<option value="<%=EstadoEnum.BAJA.getValue()%>"><spring:message code="<%=EstadoEnum.BAJA.getLabel()%>"/></option>
						</select>
					</div>
				</div>
				<div class="row">
				<div class="form-group  col-lg-3">
					<label for="tipoFacturacion008_detail_table" class="control-label"><spring:message code="label.facturacion"/>:</label>
					<select name="tipoFacturacion008" id="tipoFacturacion008_detail_table" class="form-control">
					<option value="<%=TipoFacturacionEnum.EURO_INTERPRETE.getValue()%>" selected="true"><spring:message code="<%=TipoFacturacionEnum.EURO_INTERPRETE.getLabel()%>"/></option>
								<option value="<%=TipoFacturacionEnum.EURO_HORA_INTERPRETE.getValue()%>"><spring:message code="<%=TipoFacturacionEnum.EURO_HORA_INTERPRETE.getLabel()%>"/></option>	
						</select>
				</div>
				<!-- Fin campos del formulario de detalle -->
				
			</div>
		</form>
	</div>
	<!-- Botonera del formulario de detalle -->
	<div class="rup-table-buttonpane ui-widget-content ui-helper-clearfix">
		<div class="ui-dialog-buttonset">
			<!-- Boton Guardar -->
			<button id="tiposinterpretacion_detail_button_save" type="button">
				<spring:message code="save" />
			</button>
			<!-- Enlace cancelar -->
			<a href="javascript:void(0)" id="tiposinterpretacion_detail_link_cancel"
				class="rup-enlaceCancelar"><spring:message code="cancel" /></a>
		</div>
	</div>
</div>
</div>