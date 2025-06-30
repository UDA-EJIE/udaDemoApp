<%@page import="com.ejie.aa79b.model.enums.TipoEntidadEnum" %>
<%@page import="com.ejie.aa79b.model.enums.TipoExpedienteGrupoEnum"%>
<%@include file="/WEB-INF/includeTemplate.inc"%>

<div id="divFacturacionCapa">
	<div id="divFacturacionGeneral">
		<div id="divFacturacionExpediente">
			<div class="container-fluid aa79b-fade in" id="divBusquedaFactExp">
				<h2><spring:message code="menu.emisiondefacturas"></spring:message></h2>
					<div id="busquedaFactExp_div" class="rup-table-container">
					<div id="busquedaFactExp_feedback"></div>						<!-- Feedback de la tabla --> 
					<div id="busquedaFactExp_toolbar"></div>						<!-- Botonera de la tabla --> 
					<div id="busquedaFactExp_filter_div" class="rup-table-filter"> <!-- Capa contenedora del formulario de filtrado -->
						<form id="busquedaFactExp_filter_form" class="form-horizontal">	
							<div id="busquedaFactExp_filter_toolbar" class="formulario_legend"></div>
							<input type="hidden" id="codEntidadBoe" name="codEntidadBoe">
							<fieldset id="busquedaFactExp_filter_fieldset" class="rup-table-filter-fieldset " style="border: none;">
								<div class="p-2">
								<div class="row">
									<div class="form-group col-md-3">
										<label for="idTipoExpediente_filter_table" class="control-label ">(*)<spring:message code="label.tipoExpediente"/>:</label>
										<select name="idTipoExpediente" class="form-control" id="idTipoExpediente_filter_table">
											<option value="<%=TipoExpedienteGrupoEnum.TRADUCCION_REVISION.getValue()%>"><spring:message code="<%=TipoExpedienteGrupoEnum.TRADUCCION_REVISION.getLabel()%>"/></option>
											<option value="<%=TipoExpedienteGrupoEnum.INTERPRETACION.getValue()%>"><spring:message code="<%=TipoExpedienteGrupoEnum.INTERPRETACION.getLabel()%>"/></option>
										</select>
									</div>
								</div>
								<div class="row" style="margin-top:0.5rem">
									<div class="col-md-6">
										<label class="control-label col-lg-12 p-0" data-i18n="label.fechaSolicitud"><spring:message code="label.fechaSolicitud" />:</label>
										<div class="form-group col-lg-6 p-0">
											<label class="control-label col-md-4 p-0 valFecha" data-i18n="label.desde"><spring:message code="label.desde" />:</label>
											<label hidden="hidden" for="fechaSolicitudDesde_filter" class="control-label col-md-4 p-0 valFecha" data-i18n="label.desde"><spring:message code="label.desdeFechaDeSolicitud" />:</label>
											<div class="form-group  col-md-8 p-0 grupoFechaHora grupoFecha">
												<input type="text" id="fechaSolicitudDesde_filter" name="fechaDesdeSolicitud"  class="form-control"  >
											</div>
										</div>
										<div class="form-group col-lg-6 p-0">
											<label class="control-label col-md-4 p-0 valFecha" data-i18n="label.hasta"><spring:message code="label.hasta" />:</label>
											<label hidden="hidden" for="fechaSolicitudHasta_filter" class="control-label col-md-4 p-0 valFecha" data-i18n="label.hasta"><spring:message code="label.hastaFechaDeSolicitud" />:</label>
											<div class="form-group  col-md-8 p-0 grupoFechaHora grupoFecha">
												<input type="text" id="fechaSolicitudHasta_filter" name="fechaHastaSolicitud"  class="form-control" >
											</div>
										</div>
									</div>
									<div class="col-md-6">
										<label class="control-label col-lg-12 p-0" data-i18n="label.fechaEntregaReal"><spring:message code="label.fechaEntregaReal" />:</label>
										<div class="form-group col-lg-6 p-0">
											<label class="control-label col-md-4 p-0 valFecha" data-i18n="label.desde"><spring:message code="label.desde" />:</label>
											<label hidden="hidden" for="fechaEntregaRealDesde_filter" class="control-label col-md-4 p-0 valFecha" data-i18n="label.desde"><spring:message code="label.desdeFechaEntregaReal" />:</label>
											<div class="form-group  col-md-8 p-0 grupoFechaHora grupoFecha">
												<input type="text" id="fechaEntregaRealDesde_filter" name="fechaDesdeEntregaReal"  class="form-control"  >
											</div>
										</div>
										<div class="form-group col-lg-6 p-0">
											<label class="control-label col-md-4 p-0 valFecha" data-i18n="label.hasta"><spring:message code="label.hasta" />:</label>
											<label hidden="hidden" for="fechaEntregaRealHasta_filter" class="control-label col-md-4 p-0 valFecha" data-i18n="label.hasta"><spring:message code="label.hastaFechaEntregaReal" />:</label>
											<div class="form-group  col-md-8 p-0 grupoFechaHora grupoFecha">
												<input type="text" id="fechaEntregaRealHasta_filter" name="fechaHastaEntregaReal"  class="form-control" >
											</div>
										</div>
									</div>
								</div>
								<fieldset>
									<legend>
										<spring:message code="label.entidadSolicitante"></spring:message>
									</legend>
									<div class="row">
										<div class="form-group col-xs-6 ">
											<label for="idTiposEntidadSolicitante_filter_table" class="control-label " data-i18n="label.tipoEntidadSolicitante"><spring:message code="label.tipoEntidadSolicitante"/>:</label>
											<div id="idTiposEntidadSolicitante_filter_table" >
												<div class="col-md-3">
													<input type="radio" name="tipoEntidadSolicitante" id="tipoEntidadSolicitante_T" value="" data-on-text='<spring:message code="label.todas"/>' checked="checked"/>
													<label for="tipoEntidadSolicitante_T" class="radio-inline rup-table-filter-fieldset" data-i18n="label.tipoEntidad"><spring:message code="label.todas"/></label>
												</div>
												<c:set var="labelsTipoEntidadSolicitante" value="<%=TipoEntidadEnum.values()%>"/>
												<c:forEach items="${labelsTipoEntidadSolicitante}" var="tipoEntidad">
												<div class="col-md-3">
													<input type="radio" name="tipoEntidadSolicitante" id="tipoEntidadSolicitante_${tipoEntidad.value}" value="${tipoEntidad.value}"  data-on-text='<spring:message code="${tipoEntidad.label}"/>'/>
													<label for="tipoEntidadSolicitante_${tipoEntidad.value}" class="radio-inline rup-table-filter-fieldset" data-i18n="label.tipoEntidad"><spring:message code="${tipoEntidad.label}"/></label>
												</div>
												</c:forEach>
											</div>
											<input type="hidden" name="contactoFacturacionExpediente.tipoEntidadAsoc058" id="entidadSolicitanteTipo"></input>	
										</div>
										<div class="form-group col-xs-12 col-md-5">
											<label for="idEntidadSolicitante_filter_table" class="control-label " data-i18n="label.entidadSolicitante"><spring:message code="label.entidadSolicitante" />:</label>
											<select id="idEntidadSolicitante_filter_table" class="form-control" name="contactoFacturacionExpediente.codigoCompletoEntidadAsoc058"></select>	
											<input type="hidden" name="contactoFacturacionExpediente.idEntidadAsoc058" id="idEntidadSolicitante"></input>
										</div>
									</div>
									<div class="row">
										<div class="form-group col-xs-12 col-md-4">
											<label for="contactoSolicitante_filter_table" class="control-label " data-i18n="label.contactoGestorExpEnEstudio"><spring:message code="label.contactoSolicitante" />:</label>
											<input id="contactoSolicitante_filter_table" class="form-control" name="contactoFacturacionExpediente.dniContacto058"/>
										</div>
									</div>
								</fieldset>
								<fieldset>
									<legend>
										<spring:message code="label.entidadFactExpediente"></spring:message>
									</legend>
									<div class="row">
										<div class="form-group col-xs-6 ">
											<label for="idTiposEntidadFactExpediente_filter_table" class="control-label " data-i18n="label.tipoEntidad"><spring:message code="label.tipoEntidad"/>:</label>
											<div id="idTiposEntidadFactExpediente_filter_table" >
												<div class="col-md-3">
													<input type="radio" name="tipoEntidadFactExpediente" id="tipoEntidadFactExpediente_T" data-i18n="label.tipoEntidad" value="" data-on-text='<spring:message code="label.todas"/>' checked="checked"/>
													<label for="tipoEntidadFactExpediente_T" class="radio-inline rup-table-filter-fieldset" data-i18n="label.tipoEntidad"><spring:message code="label.todas"/></label>
												</div>
												<c:set var="labelsTipoEntidadFactExpediente" value="<%=TipoEntidadEnum.values()%>"/>
												<c:forEach items="${labelsTipoEntidadFactExpediente}" var="tipoEntidad">
												<div class="col-md-3">
													<input type="radio" name="tipoEntidadFactExpediente" id="tipoEntidadFactExpediente_${tipoEntidad.value}" value="${tipoEntidad.value}"  data-on-text='<spring:message code="${tipoEntidad.label}"/>'/>
													<label for="tipoEntidadFactExpediente_${tipoEntidad.value}" class="radio-inline rup-table-filter-fieldset" data-i18n="label.tipoEntidad"><spring:message code="${tipoEntidad.label}"/></label>
												</div>
												</c:forEach>
											</div>
											<input type="hidden" name="entidadAFacturar.tipo" id="entidadFactExpedienteTipo"></input>	
										</div>
										<div class="form-group col-xs-12 col-md-5">
											<label for="idEntidadFactExpediente_filter_table" class="control-label " data-i18n="label.entidad"><spring:message code="label.entidad" />:</label>
											<select id="idEntidadFactExpediente_filter_table" class="form-control" name="entidadAFacturar.codigoCompleto"></select>	
											<input type="hidden" name="entidadAFacturar.codigo" id="idEntidadFactExpediente"></input>
											<input type="hidden" name="entidadAFacturar.facturable" id="idFacturable" value="S"></input>
										</div>
									</div>
									<div class="row">
										<div class="form-group col-xs-12 col-md-4">
											<label for="contactoFactExpediente_filter_table" class="control-label " data-i18n="label.contactoGestorExpEnEstudio"><spring:message code="label.contactoAFacturar" />:</label>
											<input id="contactoFactExpediente_filter_table" class="form-control" name="contactoAFacturar.dni"/>
										</div>
									</div>
								</fieldset>	
						</div>
						<div id="busquedaFactExp_filter_buttonSet" class="pull-right" style="margin:0 1rem 1rem 0">
							<!-- Botón de filtrado -->
							<button id="busquedaFactExp_filter_filterButton" type="button" class="ui-button ui-widget ui-state-default ui-corner-all">
								<spring:message code="filter" />
							</button>
							<!-- Enlace de limpiar -->
							<a id="busquedaFactExp_filter_cleanLinkModificado" href="javascript:void(0)" class="rup-enlaceCancelar"><spring:message code="clear" /></a>
						</div>
								</fieldset>
							</form>
					</div>
						<div class="busquedaFactExp_grid_div horizontal_scrollable_table" >
							<!-- Tabla -->
							<table id="busquedaFactExp"></table>
							<!-- Barra de paginación -->
							<div id="busquedaFactExp_pager"></div>
						</div>
						<div>
							<span>
							<i class='fa fa-exclamation-triangle' aria-hidden='true'></i>
							<label><spring:message code="label.consultaExpContactoNoPertOBaja" /> <a href="#" onclick="mostrarServicioActualizacionDatosFacturacion()"><spring:message code="label.titulo.actualizacionDatosFacturacion" /></a></label>
							</span>
						</div>
			</div>
		</div>
	</div>
</div>