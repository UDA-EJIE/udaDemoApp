<%@include file="/WEB-INF/includeTemplate.inc"%>
<%@page import="com.ejie.aa79b.model.enums.EstadoEnum"%>
<div class="container-fluid"> 
<h2><spring:message code="menu.tiposTarea" /></h2>
<hr class="mb-2">
	<div id="tiposTarea_div" class="rup-table-container">	
	<div id="tiposTarea_feedback"></div>						<!-- Feedback de la tabla --> 
	<div id="tiposTarea_toolbar"></div>							<!-- Botonera de la tabla -->
	<div id="contenFormularios" class="filterForm ">	<!-- Capa contenedora del formulario de filtrado -->
			<form id="tiposTarea_filter_form">						<!-- Formulario de filtrado -->
			<div id="tiposTarea_filter_toolbar" class="formulario_legend"></div>	<!-- Barra de herramientas del formulario de filtrado -->
			<fieldset id="tiposTarea_filter_fieldset" class="rup-table-filter-fieldset">
				<div class="p-2">
				<div class="row">
					<!-- Campos del formulario de filtrado -->
					<div class="form-group col-xs-6 col-md-1">
						<label for="id015_filter_table" class="control-label" data-i18n="label.id"><spring:message code="label.id"/>:</label>
						<input type="text" maxlength="3" name="id015" class="form-control numeric" id="id015_filter_table"/>
					</div>
					<div class="form-group col-xs-12 col-md-4">
						<label for="descEu015_filter_table" class="control-label" data-i18n="label.descEu"><spring:message code="label.descEu"/>:</label>
						<input type="text" name="descEu015" class="form-control" id="descEu015_filter_table" maxlength="150"/>
					</div>
					<div class="form-group col-xs-12 col-md-4">
						<label for="descEs015_filter_table" class="control-label" data-i18n="label.descEs"><spring:message code="label.descEs"/>:</label>
						<input type="text" name="descEs015" class="form-control" id="descEs015_filter_table" maxlength="150"/>
					</div>
					
					<div class="form-group col-xs-1 col-md-2">
						<label for="estado015_filter_table" class="control-label" data-i18n="label.estado"><spring:message code="label.estado"/>:</label>
						<select name="estado015" id="estado015_filter_table" class="form-control col-80por">
							<option value=""><spring:message code="combo.todos"/></option>
							<option value="<%=EstadoEnum.ALTA.getValue()%>" selected="true"><spring:message code="<%=EstadoEnum.ALTA.getLabel()%>"/></option>
							<option value="<%=EstadoEnum.BAJA.getValue()%>"><spring:message code="<%=EstadoEnum.BAJA.getLabel()%>"/></option>
						</select>
					</div>
				</div>
				<div class="row">
					<div class="form-group col-xs-12 col-md-2">
						<label for="indReqCierre015_filter_table" class="control-label" data-i18n="label.cierre"><spring:message code="label.cierre"/>:</label>
						<select name="indReqCierre015" id="indReqCierre015_filter_table" class="form-control col-80por">
							<option value=""><spring:message code="combo.todos"/></option>
							<option value="<%=Constants.SI%>"><spring:message code="comun.si"/></option>	
							<option value="<%=Constants.NO%>"><spring:message code="comun.no"/></option>	
						</select>
					</div>
					<div class="form-group col-xs-1 col-md-2">
						<label for="indGestionAsociada015_filter_table" class="control-label" data-i18n="label.gestion"><spring:message code="label.gestion"/>:</label>
						<select name="indGestionAsoc015" id="indGestionAsociada015_filter_table" class="form-control col-80por">
							<option value=""><spring:message code="combo.todos"/></option>
							<option value="<%=Constants.SI%>"><spring:message code="comun.si"/></option>	
							<option value="<%=Constants.NO%>"><spring:message code="comun.no"/></option>	
						</select>
					</div>
					<div class="form-group col-xs-1 col-md-2">
						<label for="indVisibleTrabajo015_filter_table" class="control-label" data-i18n="label.visibleTrabajo"><spring:message code="label.visibleTrabajo"/>:</label>
						<select name="indVisibleTrabajo015" id="indVisibleTrabajo015_filter_table" class="form-control col-80por">
							<option value=""><spring:message code="combo.todos"/></option>
							<option value="<%=Constants.SI%>"><spring:message code="comun.si"/></option>	
							<option value="<%=Constants.NO%>"><spring:message code="comun.no"/></option>	
						</select>
					</div>
					<!-- Fin campos del formulario de filtrado -->
				</div>
				<!-- Botonera del formulario de filtrado -->
				<div class="form-group ">
				<div id="tiposTarea_filter_buttonSet" class="pull-right">
					<!-- Botón de filtrado -->
					<input id="tiposTarea_filter_filterButton" type="button" class="ui-button ui-widget ui-state-default ui-corner-all" value='<spring:message code="filter" />' />
					<!-- Enlace de limpiar -->
					<a id="tiposTarea_filter_cleanLink" href="javascript:void(0)" class="rup-enlaceCancelar"><spring:message code="clear" /></a>
				</div>
				</div>
				</div>
			</fieldset>
		</form>
	</div>


		<!-- Tabla -->
		<div class="table pb-2">
		<table id="tiposTarea"></table>
		</div>
		<!-- Barra de paginación -->
		<div id="tiposTarea_pager"></div>
	</div>
</div>	

<div class="container-fluid">
<!-- Formulario de detalle -->
<div id="tiposTarea_detail_div" class="rup-table-formEdit-detail">
	<div class="ui-dialog-content ui-widget-content" >
		<form id="tiposTarea_detail_form">					<!-- Formulario -->
			<div id ="tiposTarea_detail_feedback"></div>		<!-- Feedback del formulario de detalle -->
			
				<!-- Campos del formulario de detalle -->
				<div class="row">
				<div class="form-group  col-lg-4">
					<label for="id015_detail_table" class="control-label"><spring:message code="label.id"/>:</label>
					<input type="text" name="id015" maxlength="3" class="form-control col-40por" id="id015_detail_table" readonly="readonly" disabled="disabled" />
				</div>
				</div>
				<div class="row">
				<div class="form-group  col-lg-6">
					<label for="descEu015_detail_table" class="control-label"><spring:message code="label.descEu"/>(*):</label>
					<input type="text" name="descEu015" class="form-control" id="descEu015_detail_table" maxlength="150"/>
				</div>
				<div class="form-group  col-lg-6">
					<label for="descEs015_detail_table" class="control-label"><spring:message code="label.descEs"/>(*):</label>
					<input type="text" name="descEs015" class="form-control" id="descEs015_detail_table" maxlength="150"/>
				</div>
				</div>
				<div class="row">
					<input type="hidden" name="indGestionAsoc015" class="form-control" data-switch="true" disabled="disabled" id="indGestionAsoc015_detail_table" />
					<div class="form-group  col-lg-3">
						<label for="indReqCierre015_detail_table" class="control-label"><spring:message code="label.cierre"/>:</label>
						<div class="col-xs-12 no-padding">
						<input type="checkbox" name="indReqCierre015" class="form-control" value="S" data-switch="true" id="indReqCierre015_detail_table"/>
					</div>
					</div>
					<div class="form-group col-lg-3">
						<label for="estado015_detail_table" class="control-label"><spring:message code="label.estado"/>:</label>
						<div class="col-xs-12 no-padding">
							<select name="estado015" id="estado015_detail_table" class="form-control">
								<option value="<%=EstadoEnum.ALTA.getValue()%>" selected="true"><spring:message code="<%=EstadoEnum.ALTA.getLabel()%>"/></option>
								<option value="<%=EstadoEnum.BAJA.getValue()%>"><spring:message code="<%=EstadoEnum.BAJA.getLabel()%>"/></option>
							</select>
						</div>
					</div>
					<div id="divTiempoGestion" class="form-group col-lg-3">
						<label for="indTiempoGestion015_detail_table" class="control-label"><spring:message code="label.tiempoExtraGest"/>:</label>
						<div class="col-xs-12 no-padding">
							<input type="checkbox" name="indTiempoGestion015" class="form-control" value="S" data-switch="true" id="indTiempoGestion015_detail_table"/>
							<!-- <select name="indTiempoGestion015" id="indTiempoGestion015_detail_table" class="form-control">
								<option value="<%=Constants.SI%>"><spring:message code="comun.si"/></option>	
								<option value="<%=Constants.NO%>"><spring:message code="comun.no"/></option>	
							</select>-->
						</div>
					</div>
					<div id="divVisibleTrabajo" class="form-group col-lg-4">
						<label for="indVisibleTrabajo015_detail_table" class="control-label"><spring:message code="label.visibleTrabajo"/>:</label>
						<div class="col-xs-12 no-padding">
							<input type="checkbox" name="indVisibleTrabajo015" class="form-control" value="S" data-switch="true" id="indVisibleTrabajo015_detail_table"/>
							<!-- <select name="indTiempoGestion015" id="indTiempoGestion015_detail_table" class="form-control">
								<option value="<%=Constants.SI%>"><spring:message code="comun.si"/></option>	
								<option value="<%=Constants.NO%>"><spring:message code="comun.no"/></option>	
							</select>-->
						</div>
					</div>
				<!-- Fin campos del formulario de detalle -->
				</div>
		</form>
	</div>
	<!-- Botonera del formulario de detalle -->
	<div class="rup-table-buttonpane ui-widget-content ui-helper-clearfix">
		<div class="ui-dialog-buttonset">
			<!-- Botón Guardar -->
			<button id="tiposTarea_detail_button_save" type="button">
				<spring:message code="save" />
			</button>
			<!-- Enlace cancelar -->
			<a href="javascript:void(0)" id="tiposTarea_detail_link_cancel"
				class="rup-enlaceCancelar"><spring:message code="cancel" /></a>
		</div>
	</div>
</div>
</div>
