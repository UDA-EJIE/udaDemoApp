<%@page import="com.ejie.aa79b.model.enums.EstadoEnum"%>
<%@include file="/WEB-INF/includeTemplate.inc"%>
<div class="container-fluid">
<h2><spring:message code="menu.metadatosBusqueda"/></h2>
<hr class="mb-2">
	<div id="metadatosBusqueda_div" class="rup-table-container">
		<div id="metadatosBusqueda_feedback"></div><!-- Feedback de la tabla -->	
		<div id="metadatosBusqueda_toolbar"></div>		<!-- Botonera de la tabla -->
		<div id="contenFormularios" class="filterForm "> <!-- Capa contenedora del formulario de filtrado -->
			<form id="metadatosBusqueda_filter_form" class="form-horizontal">						<!-- Formulario de filtrado -->
				<div id="metadatosBusqueda_filter_toolbar" class="formulario_legend"></div>	<!-- Barra de herramientas del formulario de filtrado -->
				<fieldset id="metadatosBusqueda_filter_fieldset" class="rup-table-filter-fieldset">
					<div class="p-2">
						<div class="row">
							<!-- Campos del formulario de filtrado -->
							<div class="form-group col-xs-6 col-md-1">
								<label for="id_filter_table" class="control-label" data-i18n="label.id"><spring:message code="label.id"/>:</label>
								<input type="text" name="id" class="form-control numeric" id="id_filter_table" maxlength="3"/>
							</div>
							<div class="form-group col-xs-12 col-md-4">
								<label for="descEu_filter_table" class="control-label" data-i18n="label.descEu"><spring:message code="label.descEu"/>:</label>
								<input type="text" name="descEu" class="form-control" id="descEu_filter_table" maxlength="100"/>
							</div>
							<div class="form-group col-xs-12 col-md-4">
								<label for="descEs_filter_table" class="control-label" data-i18n="label.descEs"><spring:message code="label.descEs"/>:</label>
								<input type="text" name="descEs" class="form-control" id="descEs_filter_table" maxlength="100"/>
							</div>
							<div class="row">
								<div class="form-group col-xs-12 col-md-2">
									<label for="estado_filter_table" class="control-label" data-i18n="label.estado"><spring:message code="label.estado"/>:</label>
										<select name="estado" id="estado_filter_table" class="form-control col-66por">
											<option value=""><spring:message code="combo.todos"/></option>
											<option value="<%=EstadoEnum.ALTA.getValue()%>" selected="selected"><spring:message code="<%=EstadoEnum.ALTA.getLabel()%>"/></option>
											<option value="<%=EstadoEnum.BAJA.getValue()%>"><spring:message code="<%=EstadoEnum.BAJA.getLabel()%>"/></option>	
										</select>
								</div>
							</div>
							<!-- Fin campos del formulario de filtrado -->
						</div>
						<div class="form-group"><!-- Botonera del formulario de filtrado -->
							<div id="metadatosBusqueda_filter_buttonSet" class="pull-right">
								<!-- Botón de filtrado -->
								<input id="metadatosBusqueda_filter_filterButton" type="button" class="ui-button ui-widget ui-state-default ui-corner-all" value='<spring:message code="filter" />' />
								<!-- Enlace de limpiar -->
								<a id="metadatosBusqueda_filter_cleanLink" href="javascript:void(0)" class="rup-enlaceCancelar"><spring:message code="clear" /></a>
							</div>
						</div>
					</div>
				</fieldset>
			</form>
		</div>	
		
		<div class="table pb-2">
			<!-- Tabla -->
			<table id="metadatosBusqueda"></table>
		</div>
		<!-- Barra de paginación -->
		<div id="metadatosBusqueda_pager"></div>
	</div>
	<div id="metadatosBusqueda_detail_div" class="rup-table-formEdit-detail">
		<div class="ui-dialog-content ui-widget-content" >
			<form id="metadatosBusqueda_detail_form">					<!-- Formulario -->
				<div id ="metadatosBusqueda_detail_feedback"></div>		<!-- Feedback del formulario de detalle -->
				<!-- Campos del formulario de detalle -->
				<div class="row">
					<div class="form-group col-lg-4">
						<label for="id_detail_table" class="control-label"><spring:message code="label.id"/>:</label>
						<input type="text" name="id" class="form-control col-40por" id="id_detail_table" readonly="readonly" disabled="disabled"/>
					</div>
				</div>
				<div class="row">
					<div class="form-group col-lg-6">
						<label for="descEu_detail_table" class="control-label"><spring:message code="label.descEu"/>(*):</label>
						<input type="text" name="descEu" class="form-control" id="descEu_detail_table" maxlength="100"/>
					</div>
					<div class="form-group col-lg-6">
						<label for="descEs_detail_table" class="control-label"><spring:message code="label.descEs"/>(*):</label>
						<input type="text" name="descEs" class="form-control" id="descEs_detail_table" maxlength="100"/>
					</div>
				</div>
				<div class="row">
					<div class="form-group  col-lg-2">
						<label for="estado_detail_table" class="control-label"><spring:message code="label.estado"/>(*):</label>
						<select name="estado" id="estado_detail_table" class="form-control">
							<option value="<%=EstadoEnum.ALTA.getValue()%>" selected="true"><spring:message code="<%=EstadoEnum.ALTA.getLabel()%>"/></option>
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
				<button id="metadatosBusqueda_detail_button_save" type="button">
					<spring:message code="save" />
				</button>
				<!-- Enlace cancelar -->
				<a href="javascript:void(0)" id="metadatosBusqueda_detail_link_cancel"
					class="rup-enlaceCancelar"><spring:message code="cancel" /></a>
			</div>
		</div>
	</div>
</div>	

