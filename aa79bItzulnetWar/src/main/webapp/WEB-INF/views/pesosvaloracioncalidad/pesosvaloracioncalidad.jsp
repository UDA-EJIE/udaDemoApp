<%@page import="com.ejie.aa79b.model.enums.EstadoEnum"%>
<%@include file="/WEB-INF/includeTemplate.inc"%>
<div class="container-fluid"> 
<h2><spring:message code="menu.pesosValoracionCalidad" /></h2>
<div id="pesosvaloracioncalidad_feedback"></div>
<!-- Feedback de la tabla -->
<fieldset class="rup-table-filter-fieldset">
	<legend><spring:message code="label.rangoAprobacionAuditoria"/></legend>
	<form id="rangosAprobacionAuditoria_form" Class="form-horizontal">
		<div class="row">	
				<input type="text" maxlength="3" name="id" class="form-control numeric" id="id_rango" hidden=true/>
			<div class="form-group col-md-2">
				<label for="val_min_aprobado" class="control-label" data-i18n="label.valMinAprobar"><spring:message code="label.valMinAprobar"/> (*)</label>
				<input type="text" maxlength="3" name="valMinAprobado" class="form-control numeric" id="val_min_aprobado" />
			</div>	
			<div class="form-group col-md-2">		
				<label for="val_min_peligro" class="control-label" data-i18n="label.valMinPeligro"><spring:message code="label.valMinPeligro"/> (*)</label>
				<input type="text" maxlength="3" name="valMinPeligro" class="form-control numeric" id="val_min_peligro" />
			</div>	
		</div>		
		<div class="row">	
			<label style="color: red; font-weight:bold" class="control-label" data-i18n="label.avisoSuspenderAuditoria"><spring:message code="label.avisoSuspenderAuditoria"/></label>	
		</div>		
	</form>
</fieldset>
<!-- Botonera del fieldset de rangos -->
<div class="rup-table-buttonpane ui-widget-content ui-helper-clearfix">
	<div class="ui-dialog-buttonset">
		<!-- Botón Guardar -->
		<input id="rangoprobacionauditoria_button_save" type="button" class="ui-button ui-widget ui-state-default ui-corner-all" value='<spring:message code="save" />'/>
		
		
		<%-- <button id="rangoprobacionauditoria_button_save" type="button">
			<spring:message code="save" />
		</button> --%>
		<!-- Enlace cancelar -->
		<a href="javascript:void(0)" id="rangoprobacionauditoria_link_cancel"
			class="rup-enlaceCancelar"><spring:message code="cancel" /></a>
	</div>
</div>	

<hr class="mb-2">
	<fieldset id="pesosvaloracionauditoria_fieldset" class="rup-table-filter-fieldset">
		<legend>
			<spring:message code="label.pesosValoracionAuditoria"/>
		</legend>
		<fieldset id="pesosvaloracionauditoria_filter_fieldset" class="rup-table-filter-fieldset" >
			<div class="p-2 padding-left-20 padding-right-20">
				<div id="pesosvaloracioncalidad_div" class="rup-table-container">
					<div id="pesosvaloracioncalidad_toolbar"></div>
					<!-- Botonera de la tabla -->
					<!-- Filtro -->
					<div id="contenFormularios" class="filterForm ">
						<!-- Capa contenedora del formulario de filtrado -->
						<form id="pesosvaloracioncalidad_filter_form" Class="form-horizontal">
							<!-- Formulario de filtrado -->
							<div id="pesosvaloracioncalidad_filter_toolbar" class="formulario_legend"></div>
							<!-- Barra de herramientas del formulario de filtrado -->
						</form>
					</div>
					<!-- Fin filtro -->
					<!-- Tabla -->
					<div class="table pb-2">
						<table id="pesosvaloracioncalidad"></table>
					</div>
					<!-- Barra de paginación -->
					<div id="pesosvaloracioncalidad_pager"></div>
				</div>
			</div>
		</fieldset>
	</fieldset>	
</div>
<div class="container-fluid">
<!-- Formulario de detalle -->
<div id="pesosvaloracioncalidad_detail_div" class="rup-table-formEdit-detail">
	<!-- Barra de navegación del detalle -->
	<div class="ui-dialog-content ui-widget-content">
		<form id="pesosvaloracioncalidad_detail_form">
			<!-- Formulario -->
			<div id="pesosvaloracioncalidad_detail_feedback"></div>
			<!-- Feedback del formulario de detalle -->
				<!-- Campos del formulario de detalle -->
				<div class="row">
				<div class="form-group  col-lg-4">
					<label for="id_detail_table" class="control-label"><spring:message code="label.id" />:</label> 
					<input type="text" name="id" class="form-control col-40por" id="id_detail_table" readonly="readonly" disabled="disabled" />
				</div>
				</div>
				<div class="row">
					<div class="form-group  col-lg-3">
						<label for="descEu_detail_table"><spring:message code="label.descripcion" />(*):</label> 
						<input type="text" maxlength="50" name="descEu" class="form-control" id="descEu_detail_table" />
					</div>
				</div>
				<div class="row">
					<div class="form-group  col-lg-3">
						<label for="porNivel0_detail_table" class="control-label"><spring:message code="label.porNivel0" />(*):</label> 
						<input type="text" maxlength="5" name="porNivel0" class="form-control decimalPor" id="porNivel0_detail_table" data-decim="1"/>
					</div>
					<div class="form-group  col-lg-3">
						<label for="porNivel1_detail_table" class="control-label"><spring:message code="label.porNivel1" />(*):</label> 
						<input type="text" maxlength="5" name="porNivel1" class="form-control decimalPor" id="porNivel1_detail_table" data-decim="1"/>
					</div>
					<div class="form-group  col-lg-3">
						<label for="porNivel3_detail_table" class="control-label"><spring:message code="label.porNivel3" />(*):</label> 
						<input type="text" maxlength="5" name="porNivel3" class="form-control decimalPor" id="porNivel3_detail_table" data-decim="1"/>
					</div>
					<div class="form-group  col-lg-3">
						<label for="porNivel5_detail_table" class="control-label"><spring:message code="label.porNivel5" />(*):</label> 
						<input type="text" maxlength="5" name="porNivel5" class="form-control decimalPor" id="porNivel5_detail_table"  data-decim="1" />
					</div>
				</div>
				<div class="row">
					<div class="form-group  col-lg-2">
						<label for="estado_detail_table" class="control-label"><spring:message code="label.estado" />:</label>
						<select name="estado" id="estado_detail_table" class="form-control">
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
			<button id="pesosvaloracioncalidad_detail_button_save" type="button">
				<spring:message code="save" />
			</button>
			<!-- Enlace cancelar -->
			<a href="javascript:void(0)" id="pesosvaloracioncalidad_detail_link_cancel"
				class="rup-enlaceCancelar"><spring:message code="cancel" /></a>
		</div>
	</div>
</div>
</div>