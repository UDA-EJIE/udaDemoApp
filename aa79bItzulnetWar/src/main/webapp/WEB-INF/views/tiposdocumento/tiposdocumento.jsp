<%@page import="com.ejie.aa79b.model.enums.EstadoEnum"%>
<%@include file="/WEB-INF/includeTemplate.inc"%>
<div class="container-fluid">
	<h2>
		<spring:message code="menu.tiposDocumento" />
	</h2>
	<hr class="mb-2">
	<div id="tiposDocumento_div" class="rup-table-container">
		<div id="tiposDocumento_feedback"></div>
		<!-- Feedback de la tabla -->
		<div id="tiposDocumento_toolbar"></div>
		<!-- Botonera de la tabla -->
		<div id="contenFormularios" class="filterForm ">
			<!-- Capa contenedora del formulario de filtrado -->
			<form id="tiposDocumento_filter_form" class="form-horizontal">
				<!-- Formulario de filtrado -->
				<div id="tiposDocumento_filter_toolbar" class="formulario_legend"></div>
				<!-- Barra de herramientas del formulario de filtrado -->
				<fieldset id="tiposDocumento_filter_fieldset"
					class="rup-table-filter-fieldset">
					<div class="row">
						<!-- Campos del formulario de filtrado -->
						<div class="col-xs-6 col-md-2">
							<div class="form-group">
								<label for="id_filter_table" class="control-label col-xs-12"
									data-i18n="label.id"><spring:message code="label.id" />:</label>
								<div class="col-xs-12">
									<input type="text" name="id" class="form-control numeric"
										id="id_filter_table" maxlength="2" />
								</div>
							</div>
						</div>
						<div class="col-xs-6 col-md-3">
							<div class="form-group">
								<label for="descEu_filter_table" class="control-label col-xs-12"
									data-i18n="label.descEu"><spring:message
										code="label.descEu" />:</label>
								<div class="col-xs-12">
									<input type="text" name="descEu" class="form-control"
										id="descEu_filter_table" maxlength="50" />
								</div>
							</div>
						</div>
						<div class="col-xs-6 col-md-3">
							<div class="form-group">
								<label for="descEs_filter_table" class="control-label col-xs-12"
									data-i18n="label.descEs"><spring:message
										code="label.descEs" />:</label>
								<div class="col-xs-12">
									<input type="text" name="descEs" class="form-control"
										id="descEs_filter_table" maxlength="50" />
								</div>
							</div>
						</div>
						<div class="col-xs-6 col-md-2">
							<div class="form-group">
								<label for="estado_filter_table" class="control-label col-xs-12"
									data-i18n="label.estado"><spring:message
										code="label.estado" />:</label>
								<div class="col-xs-12">
									<select name="estado" id="estado_filter_table"
										class="form-control col-40por">
										<option value=""><spring:message code="combo.todos" /></option>
										<option value="<%=EstadoEnum.ALTA.getValue()%>"
											selected="true"><spring:message
												code="<%=EstadoEnum.ALTA.getLabel()%>" /></option>
										<option value="<%=EstadoEnum.BAJA.getValue()%>"><spring:message
												code="<%=EstadoEnum.BAJA.getLabel()%>" /></option>
									</select>
								</div>
							</div>
						</div>

						<!-- Fin campos del formulario de filtrado -->
					</div>

					<!-- Botonera del formulario de filtrado -->
					<div class="form-group ">
						<div id="tiposDocumento_filter_buttonSet" class="pull-right">
							<!-- Botón de filtrado -->
							<input id="tiposDocumento_filter_filterButton" type="button"
								class="ui-button ui-widget ui-state-default ui-corner-all"
								value='<spring:message code="filter" />' />
							<!-- Enlace de limpiar -->
							<a id="tiposDocumento_filter_cleanLink" href="javascript:void(0)"
								class="rup-enlaceCancelar"><spring:message code="clear" /></a>
						</div>
					</div>
				</fieldset>
			</form>
		</div>

		<div class="table pb-2">
			<!-- Tabla -->
			<table id="tiposDocumento"></table>
		</div>
		<!-- Barra de paginación -->
		<div id="tiposDocumento_pager"></div>

	</div>
</div>


<div class="container-fluid">
	<!-- Formulario de detalle -->
	<div id="tiposDocumento_detail_div" class="rup-table-formEdit-detail">
		<!-- Barra de navegación del detalle -->
		<div class="ui-dialog-content ui-widget-content">
			<form id="tiposDocumento_detail_form">
				<!-- Formulario -->
				<div id="tiposDocumento_detail_feedback"></div>
				<!-- Feedback del formulario de detalle -->
				<div class="row">
					<div id="advertencia_detail_table_container" class="col-xs-12 warning-message-label-container oculto" style="font-size: 14px">
						<i class="fa fa-exclamation-triangle" aria-hidden="true"></i>
						<spring:message	code="label.tipoDocumento.advertenciaCambio"/>
					</div>
				</div>
				<!-- Campos del formulario de detalle -->
				<div class="row">
					<div class="col-xs-6 col-lg-3">
						<label for="id_detail_table" class="control-label"><spring:message
								code="label.id" />:</label> <input type="text" name="id"
							class="form-control" id="id_detail_table" maxlength="2"
							readonly="readonly" disabled="disabled" />
					</div>
					<div class="col-xs-6 col-lg-3">
						<label for="orden_detail_table" class="control-label">
							<spring:message	code="label.orden" />:
						</label>
						 <input type="text" name="orden" class="form-control" id="orden_detail_table" maxlength="3"/>
					</div>
				</div>
				<div class="row">
					<div id="selectTipoRelevancia"
						class="form-group col-xs-12 col-lg-4">
						<label class="control-label" for="idTipoRelevancia_detail_table"><spring:message
								code="label.tipoRelevancia" /> (*):</label>
						<div id="idTipoRelevancia_documentos_combo">
							<select name="tipoRelevancia.idTipoRelevancia"
								id="idTipoRelevancia_detail_table" class="form-control"></select>
						</div>
					</div>
					<div class="form-group col-xs-12 col-lg-4">
						<label for="descEu_detail_table" class="control-label"><spring:message
								code="label.descEu" /> (*):</label> <input type="text" name="descEu"
							class="form-control" id="descEu_detail_table" maxlength="50" />
					</div>
					<div class="form-group col-xs-12 col-lg-4">
						<label for="descEs_detail_table" class="control-label"><spring:message
								code="label.descEs" /> (*):</label> <input type="text" name="descEs"
							class="form-control" id="descEs_detail_table" maxlength="50" />
					</div>
				</div>
				<div class="row">
					<div class="form-group col-xs-12 col-lg-4" style="clear: left;">
						<label for="indFacturable_detail_table"
							class="control-label col-xs-12 no-padding"><spring:message
								code="label.indFacturable" /> (*):</label> <input type="checkbox"
							name="indFacturable" id="indFacturable_detail_table"
							class="form-control" value="S" data-switch="true">
					</div>
					<div class="form-group col-xs-12 col-lg-4">
						<label for="indConfidencial_detail_table" class="control-label"><spring:message
								code="label.indConfidencial" /> (*):</label> <input type="checkbox"
							name="indConfidencial" id="indConfidencial_detail_table"
							class="form-control" value="S" data-switch="true">
					</div>
					<div class="form-group col-xs-12 col-lg-4">
						<label for="indRecargoUrgencia_detail_table" class="control-label"><spring:message
								code="label.indRecargoUrgencia" /> (*):</label> <input type="checkbox"
							name="indRecargoUrgencia" id="indRecargoUrgencia_detail_table"
							class="form-control" value="S" data-switch="true">
					</div>
				</div>
				<div class="row">
					<div class="form-group col-xs-12 col-lg-4" style="clear: left;">
						<label for="indActMemoria_detail_table"
							class="control-label col-xs-12 no-padding"><spring:message
								code="label.indActMemoria" /> (*):</label> <input type="checkbox"
							name="indActMemoria" id="indActMemoria_detail_table"
							class="form-control" value="S" data-switch="true">
					</div>

					<div class="form-group col-xs-12 col-lg-4" >
						<label for="estado_detail_table" class="control-label"><spring:message
								code="label.estado" /> (*):</label> <select name="estado"
							id="estado_detail_table" class="form-control col-60por">
							<option value="<%=EstadoEnum.ALTA.getValue()%>"><spring:message
									code="<%=EstadoEnum.ALTA.getLabel()%>" /></option>
							<option value="<%=EstadoEnum.BAJA.getValue()%>"><spring:message
									code="<%=EstadoEnum.BAJA.getLabel()%>" /></option>
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
				<button id="tiposDocumento_detail_button_save" type="button">
					<spring:message code="save" />
				</button>
				<!-- Enlace cancelar -->
				<a href="javascript:void(0)" id="tiposDocumento_detail_link_cancel"
					class="rup-enlaceCancelar"><spring:message code="cancel" /></a>
			</div>
		</div>
	</div>
</div>
