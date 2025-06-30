<%@page import="com.ejie.aa79b.model.enums.TipoExpedienteGrupoEnum"%>
<%@page import="com.ejie.aa79b.model.enums.TipoRecursoEnum"%>
<%@include file="/WEB-INF/includeTemplate.inc"%>
<%@page import="com.ejie.aa79b.model.enums.TipoExpedienteEnum"%>
<%@page import="com.ejie.aa79b.model.enums.EstadoAceptacionTareaEnum"%>
<%@page import="com.ejie.aa79b.model.enums.EstadoEjecucionTareaEnum"%>
<%@page import="com.ejie.aa79b.model.enums.TipoRecursoEnum"%>

<div id="divConsultaPlanificacionExp" class="container-fluid">
	<div id="consultaPlanificacionExpGeneralDiv">
	<h2><spring:message code="menu.consultaPlanificacionExpedientes"/></h2>
	<hr class="mb-2 oculto">
		<div class="rup-table-container">
			<div id="consultaPlanificacionExp_feedback"></div>						
			<div id="consultaPlanificacionExp_toolbar"></div>	
			<div id="contenFormularios" class="rup-table-filter">
				<form id="consultaPlanificacionExp_filter_form" class="form-horizontal">
					<div id="consultaPlanificacionExp_filter_toolbar"
						class="formulario_legend filterCabecera"></div>
					<fieldset id="consultaPlanificacionExp_filter_fieldset"
						class="rup-table-filter-fieldset filterCuerpo">
						<div class="p-2">
							<div class="row">
								<div class="form-group col-md-2">
									<label for="idTipoExpediente_filter_table" class="control-label "><spring:message code="label.tipoExpediente"/> (*):</label>
									<select name="idTipoExpediente" class="form-control" id="idTipoExpediente_filter_table">
										<option value="<%=TipoExpedienteEnum.INTERPRETACION.getValue()%>"><spring:message code="<%=TipoExpedienteEnum.INTERPRETACION.getLabel()%>"/></option>
										<option value="<%=TipoExpedienteEnum.TRADUCCION.getValue()%>"><spring:message code="<%=TipoExpedienteEnum.TRADUCCION.getLabel()%>"/></option>
										<option value="<%=TipoExpedienteEnum.REVISION.getValue()%>"><spring:message code="<%=TipoExpedienteEnum.REVISION.getLabel()%>"/></option>
										<option value="<%=TipoExpedienteGrupoEnum.TRADUCCION_REVISION.getCode()%>"><spring:message code="<%=TipoExpedienteGrupoEnum.TRADUCCION_REVISION.getLabel()%>"/></option>
									</select>
								</div>
								<div class="form-group col-xs-12 col-md-3">
									<label for="numExp_filter_table" class="control-label"><spring:message code="label.numeroExpediente"/>:</label>
									<div>
										<label for="anyo_filter_table" class="control-label oculto"><spring:message code="label.anyo"/></label>
										<input type="text" name="anyo" class=" numeric" style="width: 40px;" id="anyo_filter_table" maxlength="2"/>
										<label class="control-label" style="width: 3%;">/</label>
										<input type="text" name="numExp" class=" numeric" style="width: 75px;" id="numExp_filter_table" maxlength="6"/>
									</div>
								</div>
								<div class="form-group col-xs-12 col-md-2">
									<label for="estado_filter_table" class="control-label "><spring:message code="label.estado"/>:</label>
									<select name="bitacoraExpediente.estadoExp.id" class="form-control" id="estado_filter_table"></select>
								</div>
								<div class="form-group col-xs-12 col-md-4">
									<label for="fases_filter_table" class="control-label "><spring:message code="label.fase"/>:</label>
									<select name="bitacoraExpediente.faseExp.id" class="form-control" id="fases_filter_table"></select>
								</div>
							</div>
							<div class="row">
								<div class="form-group col-md-2">
									<label for="tipoDocumentoExpediente_filter_table" class="control-label "><spring:message code="label.tipoDocumento"/>:</label>
									<select name="tipoDocumento" class="form-control" id="tipoDocumentoExpediente_filter_table"></select>
								</div>
							</div>
							<fieldset>
								<legend><spring:message code="label.tareasPlanificadas" /></legend>
								<div class="row">
									<div class="col-xs-6">
										<label for="tipoTareaCargaTrabajo"
											class="control-label col-xs-12 no-padding-left"><spring:message
												code="comun.tipoDeTarea" />:</label>
										<div class="col-xs-12 no-padding-left">
											<select name="filterTarea.tipoTarea" class="form-control"
												id="tipoTareaCargaTrabajo"></select>
										</div>
									</div>
									<div class="col-xs-3">
										<label for="estadoAceptTarea_detail_table"
											class="control-label col-xs-12 no-padding-left"><spring:message
												code="label.estadoAceptTarea" />:</label>
										<div class="col-xs-12 no-padding-left">
											<select name="filterTarea.estadoAceptTarea" class="form-control"
												id="estadoAceptTarea_detail_table">
												<option value=""><spring:message code="combo.todos" /></option>
												<option value="<%=EstadoAceptacionTareaEnum.PENDIENTE_ASIGNACION.getValue()%>">
												<spring:message code="<%=EstadoAceptacionTareaEnum.PENDIENTE_ASIGNACION.getLabel()%>"/></option>
												<option
													value="<%=EstadoAceptacionTareaEnum.PENDIENTE_ACEPTACION.getValue()%>"><spring:message
														code="<%=EstadoAceptacionTareaEnum.PENDIENTE_ACEPTACION.getLabel()%>" /></option>
												<option
													value="<%=EstadoAceptacionTareaEnum.ACEPTADA.getValue()%>"><spring:message
														code="<%=EstadoAceptacionTareaEnum.ACEPTADA.getLabel()%>" /></option>
												<option
													value="<%=EstadoAceptacionTareaEnum.RECHAZADA.getValue()%>"><spring:message
														code="<%=EstadoAceptacionTareaEnum.RECHAZADA.getLabel()%>" /></option>
											</select>
										</div>
									</div>
									<div class="col-xs-3">
										<label for="estadoEjecTarea_detail_table"
											class="control-label col-xs-12 no-padding-left"><spring:message
												code="label.estadoEjecTarea" />:</label>
										<div class="col-xs-12 no-padding-left">
											<select name="filterTarea.estadoEjecTarea" class="form-control"
												id="estadoEjecTarea_detail_table">
												<option value="" selected="true"><spring:message
														code="combo.todos" /></option>
												<option
													value="<%=EstadoEjecucionTareaEnum.PENDIENTE_EJECUCION.getValue()%>"><spring:message
														code="<%=EstadoEjecucionTareaEnum.PENDIENTE_EJECUCION.getLabel()%>" /></option>
												<option
													value="<%=EstadoEjecucionTareaEnum.RETRASADA.getValue()%>"><spring:message
														code="<%=EstadoEjecucionTareaEnum.RETRASADA.getLabel()%>" /></option>
												<option
													value="<%=EstadoEjecucionTareaEnum.EJECUTADA.getValue()%>"><spring:message
														code="<%=EstadoEjecucionTareaEnum.EJECUTADA.getLabel()%>" /></option>
											</select>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-md-6">
										<label class="control-label col-lg-12 p-0"
											data-i18n="label.fechaPrevistaEjecucion"><spring:message
														code="label.fechaPrevistaEjecucion" /></label>
										<div class="form-group col-lg-6 p-0">
											<label for="fechaPrevistaEjecucionDesde"
												class="control-label col-md-3 p-0" data-i18n="label.fechaPrevistaEjecucionDesde"><spring:message
													code="label.desde" />:</label>
											<div class="form-group col-md-9 p-0 grupoFecha">
												<input type="text" id="fechaPrevistaEjecucionDesde" name="filterTarea.fechaDesdePrevistaEjecucion"
													class="form-control">
											</div>
										</div>
										<div class="form-group col-lg-6 p-0">
											<label for="fechaPrevistaEjecucionHasta"
												class="control-label col-md-4 p-0" data-i18n="label.fechaPrevistaEjecucionHasta"><spring:message
													code="label.hasta" />:</label>
											<div class="form-group col-md-8 p-0 grupoFecha">
												<input type="text" id="fechaPrevistaEjecucionHasta" name="filterTarea.fechaHastaPrevistaEjecucion"
													class="form-control">
											</div>
										</div>
									</div>
									<div class="col-md-6">
										<label class="control-label col-lg-12 p-0"
											data-i18n="label.fechaRealEjecucion"><spring:message
														code="label.fechaRealEjecucion" /></label>
										<div class="form-group col-lg-6 p-0">
											<label for="fechaRealEjecucionDesde"
												class="control-label col-md-3 p-0" data-i18n="label.fechaRealEjecucionDesde"><spring:message
													code="label.desde" />:</label>
											<div class="form-group col-md-9 p-0 grupoFecha">
												<input type="text" id="fechaRealEjecucionDesde" name="filterTarea.fechaDesdeRealEjecucion"
													class="form-control">
											</div>
										</div>
										<div class="form-group col-lg-6 p-0">
											<label for="fechaRealEjecucionHasta"
												class="control-label col-md-4 p-0" data-i18n="label.fechaRealEjecucionHasta"><spring:message
													code="label.hasta" />:</label>
											<div class="form-group col-md-8 p-0 grupoFecha">
												<input type="text" id="fechaRealEjecucionHasta" name="filterTarea.fechaHastaRealEjecucion"
													class="form-control">
											</div>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="form-group col-xs-12 col-md-3">
										<label for="tareaAsignadaA_filter_table"
											class="control-label col-xs-12 no-padding-left"><spring:message
												code="comun.tareaAsignadaA" />:</label>
										<div class="col-xs-12 no-padding-left">
											<select name="filterTarea.recursoAsignacion" class="form-control"
												id="tareaAsignadaA_filter_table">
												<option value="" ><spring:message
														code="combo.todos" /></option>
												<option
													value="<%=TipoRecursoEnum.INTERNO.getValue()%>"><spring:message
														code="comun.recursosInternos" /></option>
												<option
													value="<%=TipoRecursoEnum.EXTERNO.getValue()%>"><spring:message
														code="label.proveedores" /></option>
											</select>
										</div>
									</div>
									<div class="col-xs-3">
										<label for="asignadaA_filter_table"
											class="control-label col-xs-12 no-padding-left"><spring:message
												code="label.asignadoA" />:</label>
										<div class="col-xs-12 no-padding-left">
											<select name="filterTarea.persIzoAsignTarea" class="form-control"
												id="asignadaA_filter_table">
											</select>
										</div>
									</div>
									<div class="form-group col-xs-12 col-md-4">
										<label for="lote_filter_table" class="control-label "><spring:message code="label.lote"/>:</label>
										<select name="filterTarea.idLoteAsignTarea" class="form-control" id="lote_filter_table">
											<option value="" selected="true"><spring:message code="combo.todos" /></option>
										</select>
									</div>
								</div>
								<div class="row">
									<div class="form-group col-xs-12 col-md-3">
										<label for="asignador_filter_table" class="control-label "><spring:message code="label.asignador"/>:</label>
										<select name="asignador.dni" class="form-control" id="asignador_filter_table"></select>
									</div>
									<div class="form-group col-md-6">
										<label class="control-label col-lg-12 p-0"
											data-i18n="label.fechaAsignacion"><spring:message
														code="label.fechaAsignacion" /></label>
										<div class="form-group col-lg-6 p-0">
											<label for="fechaAsignacionDesde"
												class="control-label col-md-3 p-0 valFecha" data-i18n="label.fechaAsignacionDesde"><spring:message
													code="label.desde" />:</label>
											<div class="form-group col-md-8 p-0 grupoFecha">
												<input type="text" id="fechaAsignacionDesde" name="filterTarea.fechaAsignacionDesde"
													class="form-control">
											</div>
										</div>
										<div class="form-group col-lg-6 p-0">
											<label for="fechaAsignacionHasta"
												class="control-label col-md-4 p-0 valFecha" data-i18n="label.fechaAsignacionHasta"><spring:message
													code="label.hasta" />:</label>
											<div class="form-group col-md-8 p-0 grupoFecha">
												<input type="text" id="fechaAsignacionHasta" name="filterTarea.fechaAsignacionHasta"
													class="form-control">
											</div>
										</div>
									</div>
								</div>
							</fieldset>
							<fieldset>
								<legend><spring:message code="titulo.metadatosBusqueda" /></legend>
								<input type="hidden" name="idsMetadatosBusqueda" id="metadatosBusquedaConsulta_filter_table"></input>	
								<div class="col-xs-12 col-md-4">
										<button id="etiquetas_filter_table" type="button" class="ui-button ui-widget ui-state-default ui-corner-all ">
											<spring:message code="label.anyadir" />
										</button>
									</div>
								<div id="metadatosBusqueda-preview" class="form-group col-xs-12 col-md-12 no-padding-left oculto">
									<fieldset >
										<div style="max-height: 72px; overflow-y: scroll;">
										</div>
									</fieldset>
								</div>
								
								
								<%-- <div class="row">
									<div class="form-group col-xs-12 col-md-4" style="display: inline-table;margin-bottom: 12px;">
										<div class="divCombo100por">
										<label for="metadatosBusquedaConsulta_filter_table" class="control-label oculto"><spring:message code="menu.metadatosBusqueda"/></label>
										<select name="idsMetadatosBusqueda" class="form-control" id="metadatosBusquedaConsulta_filter_table"></select>
										</div>
									</div>
									<div id="metadatosBusqueda-preview" class="form-group col-xs-12 col-md-12 no-padding-left oculto">
										<fieldset >
											<div style="max-height: 72px; overflow-y: scroll;">
											</div>
										</fieldset>
									</div>
								</div> --%>
							</fieldset>
						</div>
						<!-- Fin campos del formulario de filtrado -->
						<div id="consultaPlanificacionExp_filter_buttonSet" class="pull-right" style="margin:0 1rem 1rem 0">
							<!-- Botón de filtrado -->
							<button id="consultaPlanificacionExp_filter_filterButton" type="button" class="ui-button ui-widget ui-state-default ui-corner-all">
								<spring:message code="filter" />
							</button>
							
							<!-- Enlace de limpiar -->
							<a id="consultaPlanificacionExp_filter_cleanLinkModificado" href="javascript:void(0)" class="rup-enlaceCancelar"><spring:message code="clear" /></a>
						</div>
					</fieldset>
				</form>
			</div>
			<div class="consultaPlanificacionExp_grid_div horizontal_scrollable_table" >
				<!-- Tabla -->
				<table id="consultaPlanificacionExp" ></table>
				<!-- Barra de paginación -->
				<div id="consultaPlanificacionExp_pager"></div>
			</div>
		</div>
	</div>
</div>
<div id="modalesDiv">
	<div id="buscadorEtiquetasConsulta" class="oculto"></div>
</div>