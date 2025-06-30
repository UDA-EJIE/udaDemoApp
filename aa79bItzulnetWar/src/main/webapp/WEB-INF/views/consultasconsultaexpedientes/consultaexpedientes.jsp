<%@page import="com.ejie.aa79b.model.enums.TipoExpedienteGrupoEnum"%>
<%@page import="com.ejie.aa79b.model.enums.TipoExpedienteEnum"%>
<%@page import="com.ejie.aa79b.model.enums.TipoEntidadEnum"%>
<%@page import="com.ejie.aa79b.model.enums.ActivoEnum"%>
<%@page import="com.ejie.aa79b.model.enums.EstadoSubsanacionRequeridaEnum"%>
<%@page import="com.ejie.aa79b.model.enums.EstadoRequerimientoEnum"%>
<%@include file="/WEB-INF/includeTemplate.inc"%>

<div class="container-fluid aa79b-fade in" id="divConsultaExpedientesGeneral">
<div id="divConsultaExpedientesCapa">
	<div id="divConsultaExpedientes">
		<h2><spring:message code="menu.consultaExpedientes"/></h2>
		<div id="consultaExpedientes_div" class="rup-table-container">
			<div id="consultaExpedientes_feedback"></div>					<!-- Feedback de la tabla --> 
			<div id="consultaExpedientes_toolbar"></div>
			<div id="consultaExpedientes_filter_div" class="filterForm"> <!-- Capa contenedora del formulario de filtrado -->
				<form id="consultaExpedientes_filter_form" class="form-horizontal">	
					<div id="consultaExpedientes_filter_toolbar" class="formulario_legend"></div>
					<fieldset id="consultaExpedientes_filter_fieldset" class="rup-table-filter-fieldset ">
						<div class="p-2">
						<fieldset >
							<legend>
								<spring:message code="label.datosExpediente"/>
							</legend>
							<div class="row">
								<div class="form-group col-md-2">
									<label for="idTipoExpedienteConsulta_filter_table" class="control-label "><spring:message code="label.tipoExpediente"/>:</label>
									<select name="idTipoExpediente" class="form-control" id="idTipoExpedienteConsulta_filter_table">
										<option value="" selected="true"><spring:message code="combo.todos"/></option>
										<option value="<%=TipoExpedienteEnum.REVISION.getValue()%>"><spring:message code="<%=TipoExpedienteEnum.REVISION.getLabel()%>"/></option>
										<option value="<%=TipoExpedienteEnum.INTERPRETACION.getValue()%>"><spring:message code="<%=TipoExpedienteEnum.INTERPRETACION.getLabel()%>"/></option>
										<option value="<%=TipoExpedienteEnum.TRADUCCION.getValue()%>"><spring:message code="<%=TipoExpedienteEnum.TRADUCCION.getLabel()%>"/></option>
										<option value="<%=TipoExpedienteGrupoEnum.TRADUCCION_REVISION.getCode()%>"><spring:message code="<%=TipoExpedienteGrupoEnum.TRADUCCION_REVISION.getLabel()%>"/></option>
									</select>
								</div>
								<div class="form-group col-xs-12 col-md-3">
									<label for="numExpConsulta_filter_table" class="control-label"><spring:message code="label.numeroExpediente"/>:</label>
									<div>
										<label for="anyoConsulta_filter_table" class="control-label oculto"><spring:message code="label.anyo"/></label>
										<input type="text" name="anyo" class=" numeric" style="width: 40px;" id="anyoConsulta_filter_table" maxlength="2"/>
										<label class="control-label" style="width: 3%;">/</label>
										<input type="text" name="numExp" class=" numeric" style="width: 75px;" id="numExpConsulta_filter_table" maxlength="6"/>
									</div>
								</div>
								<div class="form-group col-xs-12 col-md-6">
									<label for="tituloConsulta_filter_table" class="control-label "><spring:message code="label.titulo"/>:</label>
									<input type="text" name="titulo" class="form-control" id="tituloConsulta_filter_table" maxlength="150"/>
								</div>
							</div>
							<div class="row">
								<div class="form-groupcol-xs-12 col-md-2">
									<label for="estadosConsulta_filter_table" class="control-label "data-i18n="label.estadoConsulta"><spring:message code="label.estado"/>:</label>
									<select name="bitacoraExpediente.estadoExp.id" class="form-control" id="estadosConsulta_filter_table"></select>
								</div>
								<div class="form-groupcol-xs-12 col-md-4">
									<label for="fasesConsulta_filter_table" class="control-label "><spring:message code="label.fase"/>:</label>
									<select name="bitacoraExpediente.faseExp.id" class="form-control" id="fasesConsulta_filter_table"></select>
								</div>
							</div>
							<div class="row">
								<div class="col-md-6">
									<label class="control-label col-lg-12 p-0" data-i18n="label.fechaDeSolicitud"><spring:message code="label.fechaDeSolicitud" />:</label>
									<div class="form-group col-lg-6 p-0">
										<label for="fechaDesdeSolicitudConsulta_filter" class="control-label col-md-4 p-0 valFecha" data-i18n="label.desdeFechaDeSolicitud"><spring:message code="label.desde" />:</label>
										<div class="form-group  col-md-8 p-0 grupoFechaHora grupoFecha">
											<input type="text" id="fechaDesdeSolicitudConsulta_filter" name="fechaAltaDesde"  class="form-control"  >
										</div>
									</div>
									<div class="form-group col-lg-6 p-0">
										<label for="fechaHastaSolicitudConsulta_filter" class="control-label col-md-4 p-0 valFecha" data-i18n="label.hastaFechaDeSolicitud"><spring:message code="label.hasta" />:</label>
										<div class="form-group  col-md-8 p-0 grupoFechaHora grupoFecha">
											<input type="text" id="fechaHastaSolicitudConsulta_filter" name="fechaAltaHasta"  class="form-control" >
										</div>
									</div>
								</div>
								<div class="col-md-6">
									<label class="control-label col-lg-12 p-0" data-i18n="label.fechaEntregaIZO"><spring:message code="label.fechaEntregaIZO" />:</label>
									<div class="form-group col-lg-6 p-0">
										<label for="fechaDesdeEntregaConsulta_filter" class="control-label col-md-4 p-0 valFecha" data-i18n="label.desdeFechaEntregaIZO"><spring:message code="label.desde" />:</label>
										<div class="form-group  col-md-8 p-0 grupoFechaHora grupoFecha">
											<input type="text" id="fechaDesdeEntregaConsulta_filter" name="expedienteTradRev.fechaEntregaIzoDesde"  class="form-control"  >
										</div>
									</div>
									<div class="form-group col-lg-6 p-0">
										<label for="fechaHastaEntregaConsulta_filter" class="control-label col-md-4 p-0 valFecha" data-i18n="label.hastaFechaEntregaIZO"><spring:message code="label.hasta" />:</label>
										<div class="form-group  col-md-8 p-0 grupoFechaHora grupoFecha">
											<input type="text" id="fechaHastaEntregaConsulta_filter" name="expedienteTradRev.fechaEntregaIzoHasta"  class="form-control" >
										</div>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-md-2">
									<label for="publicarBOPVConsulta_filter_table" class="control-label "><spring:message code="label.publicarEnBOPV"/>:</label>
									<div class="divComboW125">
										<select name="expedienteTradRev.indPublicarBopv" class="form-control col-md-8" id="publicarBOPVConsulta_filter_table">
											<option value=""  selected="true"><spring:message code="combo.todos"/></option>
											<option value="<%=ActivoEnum.SI.getValue()%>"><spring:message code="<%=ActivoEnum.SI.getLabel()%>"/></option>
											<option value="<%=ActivoEnum.NO.getValue()%>"><spring:message code="<%=ActivoEnum.NO.getLabel()%>"/></option>
										</select>	
									</div>
								</div>
								<div class="col-md-2">
									<label for="previstoParaBOPVConsulta_filter_table" class="control-label "><spring:message code="label.previstoBopv"/>:</label>
									<div class="divComboW125">
										<select name="expedienteTradRev.indPrevistoBopv" class="form-control col-md-8" id="previstoParaBOPVConsulta_filter_table">
											<option value=""  selected="true"><spring:message code="combo.todos"/></option>
											<option value="<%=ActivoEnum.SI.getValue()%>"><spring:message code="<%=ActivoEnum.SI.getLabel()%>"/></option>
											<option value="<%=ActivoEnum.NO.getValue()%>"><spring:message code="<%=ActivoEnum.NO.getLabel()%>"/></option>
										</select>	
									</div>
								</div>
								<div class="col-md-2">
									<label for="idiomaDestinoConsulta_filter_table" class="control-label "><spring:message code="label.idiomaDestino"/>:</label>
									<select name="expedienteTradRev.idIdioma" class="form-control col-md-8" id="idiomaDestinoConsulta_filter_table"></select>	
								</div>
								<div class="col-md-2">
									<label for="indCorredaccionConsulta_filter_table" class="control-label "><spring:message code="label.indCorredaccion"/>:</label>
									<div class="divComboW125">
										<select name="expedienteTradRev.indCorredaccion" class="form-control col-md-8" id="indCorredaccionConsulta_filter_table">
											<option value=""  selected="true"><spring:message code="combo.todos"/></option>
											<option value="<%=ActivoEnum.SI.getValue()%>"><spring:message code="<%=ActivoEnum.SI.getLabel()%>"/></option>
											<option value="<%=ActivoEnum.NO.getValue()%>"><spring:message code="<%=ActivoEnum.NO.getLabel()%>"/></option>
										</select>	
									</div>
								</div>
								<div class="col-md-2">
									<label for="indUrgenteConsulta_filter_table" class="control-label "><spring:message code="label.urgente"/>:</label>
									<div class="divComboW125">
										<select name="expedienteTradRev.indUrgente" class="form-control col-md-8" id="indUrgenteConsulta_filter_table">
											<option value=""  selected="true"><spring:message code="combo.todos"/></option>
											<option value="<%=ActivoEnum.SI.getValue()%>"><spring:message code="<%=ActivoEnum.SI.getLabel()%>"/></option>
											<option value="<%=ActivoEnum.NO.getValue()%>"><spring:message code="<%=ActivoEnum.NO.getLabel()%>"/></option>
										</select>	
									</div>
								</div>
								<div class="col-md-2">
									<label for="indPublicadoBoeConsulta_filter_table" class="control-label "><spring:message code="label.boe"/>:</label>
									<div class="divComboW125">
										<select name="expedienteTradRev.indPublicadoBoe" class="form-control col-md-8" id="indPublicadoBoeConsulta_filter_table">
											<option value=""  selected="true"><spring:message code="combo.todos"/></option>
											<option value="<%=ActivoEnum.SI.getValue()%>"><spring:message code="<%=ActivoEnum.SI.getLabel()%>"/></option>
											<option value="<%=ActivoEnum.NO.getValue()%>"><spring:message code="<%=ActivoEnum.NO.getLabel()%>"/></option>
										</select>	
									</div>
								</div>
							</div>
							<div class="row">
								<div class="form-group col-md-2">
									<label for="tipoDocumentoExpediente_filter_table" class="control-label "><spring:message code="label.tipoDocumento"/>:</label>
									<select name="tipoDocumento" class="form-control" id="tipoDocumentoExpediente_filter_table"></select>
								</div>
							</div>
						</fieldset>
						<fieldset>
							<legend>
								<spring:message code="label.datosSolicitante"/>
							</legend>
							<div class="row">
								<div class="form-group col-xs-6 ">
									<label for="idTiposEntidadConsulta_filter_table" class="control-label " data-i18n="label.entidadGestora"><spring:message code="label.tipoEntidadGestora"/>:</label>
									<div id="idTiposEntidadConsulta_filter_table" >
										<div class="col-md-3">
											<input type="radio" name="tipoEntidad" id="tipoEntidad_T" value="" data-on-text='<spring:message code="label.todas"/>'/>
											<label for="tipoEntidad_T" class="radio-inline rup-table-filter-fieldset" data-i18n="label.tipoEntidad"><spring:message code="label.todas"/></label>
										</div>
										<c:set var="labelsTipoEntidadConsulta" value="<%=TipoEntidadEnum.values()%>"/>
										<c:forEach items="${labelsTipoEntidadConsulta}" var="tipoEntidad">
										<div class="col-md-3">
											<input type="radio" name="tipoEntidad" id="tipoEntidad_${tipoEntidad.value}" value="${tipoEntidad.value}"  data-on-text='<spring:message code="${tipoEntidad.label}"/>'/>
											<label for="tipoEntidad_${tipoEntidad.value}" class="radio-inline rup-table-filter-fieldset" data-i18n="label.tipoEntidad"><spring:message code="${tipoEntidad.label}"/></label>
										</div>
										</c:forEach>
									</div>
									<input type="hidden" name="gestorExpediente.entidad.tipo" id="gestorExpedienteEntidadTipoConsulta"></input>	
								</div>
								<div class="form-group col-xs-12 col-md-5">
									<label id="labelEntidadGestoraConsulta_filter_table" for="idEntidadGestoraConsulta_filter_table" class="control-label " data-i18n="label.entidadGestora"></label>
									<select id="idEntidadGestoraConsulta_filter_table" class="form-control" name="gestorExpediente.entidad.codigoCompleto"></select>	
								</div>
							</div>
							<div class="row">
								<div id="div_contactoGestorConsulta_filter_table" class="form-group col-xs-12 col-md-5">
									<div id="autocompleteContainer_contactoGestorConsulta_filter_table">
										<label for="contactoGestorConsulta_filter_table" class="control-label " data-i18n="label.gestor"><spring:message code="label.gestor" />:</label>
										<input id="contactoGestorConsulta_filter_table" class="form-control" name="gestorExpediente.solicitante.dni"/>
									</div>
								</div>
								<div class="form-group col-xs-12 col-md-4">
									<label for="refTramitaguneConsulta_filter_table" class="control-label "><spring:message code="label.referenciaTramitagune"/>:</label>
									<input type="text" name="expedienteTradRev.refTramitagune" class="form-control" id="refTramitaguneConsulta_filter_table" maxlength="50"/>
								</div>
							</div>
						</fieldset>
							
							<div class="row">
								<div class="form-group col-lg-6 no-padding-left">
									<fieldset>
										<legend>
											<spring:message code="label.subsanacion"/>
										</legend>
										<div class="form-group col-lg-4">
											<label for="subsRequeridaConsulta_filter_table" class="control-label "><spring:message code="comun.requerida"/>:</label>
											<div class="divComboW125">
												<select name="subsRequerida" class="form-control" id="subsRequeridaConsulta_filter_table">
													<option value="" selected="true"><spring:message code="combo.todos"/></option>
													<option value="<%=ActivoEnum.SI.getValue()%>"><spring:message code="<%=ActivoEnum.SI.getLabel()%>"/></option>
													<option value="<%=ActivoEnum.NO.getValue()%>"><spring:message code="<%=ActivoEnum.NO.getLabel()%>"/></option>
												</select>
											</div>
										</div>
										<div class="form-group col-lg-8">
											<label for="subsRequeridaEstadoConsulta_filter_table" class="control-label "><spring:message code="label.estado"/>:</label>
											<select name="bitacoraExpediente.subsanacionExp.indSubsanado" class="form-control" id="subsRequeridaEstadoConsulta_filter_table">
												<option value=""  selected="true"><spring:message code="combo.todos"/></option>
												<option value="<%=EstadoSubsanacionRequeridaEnum.PENDIENTE_DE_RESPUESTA.getValue()%>"><spring:message code="<%=EstadoSubsanacionRequeridaEnum.PENDIENTE_DE_RESPUESTA.getLabel()%>"/></option>
												<option value="<%=EstadoSubsanacionRequeridaEnum.APORTADA.getValue()%>"><spring:message code="<%=EstadoSubsanacionRequeridaEnum.APORTADA.getLabel()%>"/></option>
											</select>
										</div>
									</fieldset>
								</div>
								<div class="form-group col-lg-6 no-padding-left">
									<fieldset>
										<legend>
											<spring:message code="label.facturas"/>
										</legend>
										<div class="form-group col-md-3">
											<label for="idFactura_filter_table" class="control-label"><spring:message code="comun.numFactura"/>:</label>
											<input type="text" id="idFactura_filter_table" name="filterFactura.idFactura" class="col-xs-10 numeric valid" maxlength="10" >
										</div>
										<div class="form-group col-md-4">
											<label for="estadoFactura_filter_table" class="control-label "><spring:message code="label.estadoFactura"/>:</label>
											<select id="estadoFactura_filter_table" class="form-control" name="filterFactura.estadoFactura.indEstadoFactura"></select>
										</div>
									</fieldset>
								</div>
							</div>
							<div class="row">
								<div class="form-group col-lg-6 no-padding-left">
									<fieldset>
										<legend>
											<spring:message code="menu.expedientesRelacionados"/>
										</legend>
										<div>
											<div class="col-xs-12 col-md-4">
												<button id="buttonMostrarBuscExpRel" type="button" class="ui-button ui-widget ui-state-default ui-corner-all ">
													<spring:message code="label.anyadir" />
												</button>
											</div>
											<label for="idsExpedientesRelacionados_filter_table" class="control-label oculto"><spring:message code="menu.expedientesRelacionados"/></label>
											<input id="idsExpedientesRelacionados_filter_table" class="form-control oculto" name="idsExpedientesRelacionados">
											<div id="expedientesRelacionados-preview" class="form-group col-xs-12 col-md-12 no-padding-left oculto">
												<fieldset >
													<div style="max-height: 72px; overflow-y: scroll;">
													</div>
												</fieldset>
											</div>
										</div>
									</fieldset>
								</div>
								<div class="form-group col-lg-6 no-padding-left">
									<fieldset>
										<legend>
											<spring:message code="menu.metadatosBusqueda"/>
										</legend>
										<%-- <div class="form-group col-xs-12 col-md-4" style="display: inline-table;margin-bottom: 12px;">
											<div class="divCombo100por">
											<label for="metadatosBusquedaConsulta_filter_table" class="control-label oculto"><spring:message code="menu.metadatosBusqueda"/></label>
												<select name="idsMetadatosBusqueda" id="metadatosBusquedaConsulta_filter_table"></select>
											</div>
										</div> --%>
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
									</fieldset>
								</div>
							</div>
							<sec:authorize access="hasRole('ROLE_AA79B_TECNICO_GESTOR')">
								<div id="consultaExpedientesFiltroAvanzado" style="background: none;margin-bottom: 10px; padding-right: 18px; padding-left: 3px;">
									<div class="row consExpFilAva">
										<div class="col-md-10">
												<a id="collapseConsExpFilAva_button1" data-toggle="collapse" data-parent="#accordion" href="#collapseCritAva">
												<span class=" collapse_icon ui-icon ui-icon-triangle-1-s"> 
													<i class="fa fa-angle-down fa-x2" aria-hidden="true"></i>
												</span>
												</a>
											<p class="cabecera-consExpFilAva-scroll"><spring:message code="label.criteriosAvanzados"/></p>
										</div>
										<div class="col-md-2 cabecera-second pull-right " style=" padding-right: 10px;">
											<a id="collapseConsExpFilAva_button2" data-toggle="collapse" data-parent="#accordion" href="#collapseCritAva">
												<span class=" collapse_icon_right ui-icon ui-icon-circle-triangle-s"> 
													<i class="fa fa-angle-down fa-x2" aria-hidden="true"></i>
												</span>
											</a>
										</div>
									</div>
									<div class="panel-collapse collapse row" id="collapseCritAva">
										<div class="consExpFilAva-content">
											<div id="consExpFilAva_div" class="consExpFilAva-scroll no-padding" style="padding-left: 0px;">
												<div class="row">
													<div class="form-group col-lg-6 no-padding-left">
														<fieldset style="margin-left: 0px!important;">
															<legend>
																<spring:message code="label.presupuesto"/>
															</legend>
															<div class="form-group col-lg-4">
																<label for="reqPresupuestoConsulta_filter_table" class="control-label "><spring:message code="label.requiere"/>:</label>
																<div class="divComboW125">
																	<select name="requierePresupuesto" class="form-control" id="reqPresupuestoConsulta_filter_table">
																		<option value="" selected="true"><spring:message code="combo.todos"/></option>
																		<option value="<%=ActivoEnum.SI.getValue()%>"><spring:message code="<%=ActivoEnum.SI.getLabel()%>"/></option>
																		<option value="<%=ActivoEnum.NO.getValue()%>"><spring:message code="<%=ActivoEnum.NO.getLabel()%>"/></option>
																	</select>
																</div>
															</div>
															<div class="form-group col-lg-8 comboDispBlock">
																<label for="estadoAceptacionPresupuestoConsulta_filter_table" class="control-label "><spring:message code="label.estadoAceptacion"/>:</label>
																<select name="estadoPresupuesto" class="form-control" id="estadoAceptacionPresupuestoConsulta_filter_table">
																	<option value=""  selected="true"><spring:message code="combo.todos"/></option>
																	<option value="<%=EstadoRequerimientoEnum.PENDIENTE.getValue()%>"><spring:message code="<%=EstadoRequerimientoEnum.PENDIENTE.getLabel()%>"/></option>
																	<option value="<%=EstadoRequerimientoEnum.ACEPTADA.getValue()%>"><spring:message code="<%=EstadoRequerimientoEnum.ACEPTADA.getLabel()%>"/></option>
																	<option value="<%=EstadoRequerimientoEnum.RECHAZADA.getValue()%>"><spring:message code="<%=EstadoRequerimientoEnum.RECHAZADA.getLabel()%>"/></option>
																</select>
															</div>
														</fieldset>
													</div>
													<div class="form-group col-lg-6">
														<fieldset style="margin-left: 0px!important;">
															<legend>
																<spring:message code="label.negociacionFechaHoraEntregaPorIzo"/>
															</legend>
															<div class="form-group col-lg-4">
																<label for="reqNegociacionConsulta_filter_table" class="control-label "><spring:message code="label.propuesta"/>:</label>
																<div class="divComboW125">
																	<select name="requiereNegociacion" class="form-control" id="reqNegociacionConsulta_filter_table">
																		<option value="" selected="true"><spring:message code="combo.todos"/></option>
																		<option value="<%=ActivoEnum.SI.getValue()%>"><spring:message code="<%=ActivoEnum.SI.getLabel()%>"/></option>
																		<option value="<%=ActivoEnum.NO.getValue()%>"><spring:message code="<%=ActivoEnum.NO.getLabel()%>"/></option>
																	</select>
																</div>
															</div>
															<div class="form-group col-lg-8 comboDispBlock">
																<label for="estadoNegociacionPresupuestoConsulta_filter_table" class="control-label "><spring:message code="label.estadoNegociacion"/>:</label>
																<select name="estadoNegociacion" class="form-control" id="estadoNegociacionPresupuestoConsulta_filter_table">
																	<option value=""  selected="true"><spring:message code="combo.todos"/></option>
																	<option value="<%=EstadoRequerimientoEnum.PENDIENTE.getValue()%>"><spring:message code="<%=EstadoRequerimientoEnum.PENDIENTE.getLabel()%>"/></option>
																	<option value="<%=EstadoRequerimientoEnum.ACEPTADA.getValue()%>"><spring:message code="<%=EstadoRequerimientoEnum.ACEPTADA.getLabel()%>"/></option>
																	<option value="<%=EstadoRequerimientoEnum.RECHAZADA.getValue()%>"><spring:message code="<%=EstadoRequerimientoEnum.RECHAZADA.getLabel()%>"/></option>
																</select>
															</div>
														</fieldset>
													</div>
												</div>
												<div class="row">
													<div class="col-md-2 no-padding-left">
														<label for="indConfidencialConsulta_filter_table" class="control-label "><spring:message code="label.confidencial"/>:</label>
														<div class="divComboW125">
															<select name="expedienteTradRev.indConfidencial" class="form-control col-md-8" id="indConfidencialConsulta_filter_table">
																<option value=""  selected="true"><spring:message code="combo.todos"/></option>
																<option value="<%=ActivoEnum.SI.getValue()%>"><spring:message code="<%=ActivoEnum.SI.getLabel()%>"/></option>
																<option value="<%=ActivoEnum.NO.getValue()%>"><spring:message code="<%=ActivoEnum.NO.getLabel()%>"/></option>
															</select>	
														</div>
													</div>
													<div class="col-md-2 no-padding-left">
														<label for="indFacturableConsulta_filter_table" class="control-label "><spring:message code="label.expedienteFacturable"/>:</label>
														<div class="divComboW125">
															<select name="expedienteTradRev.indFacturable" class="form-control col-md-8" id="indFacturableConsulta_filter_table">
																<option value=""  selected="true"><spring:message code="combo.todos"/></option>
																<option value="<%=ActivoEnum.SI.getValue()%>"><spring:message code="<%=ActivoEnum.SI.getLabel()%>"/></option>
																<option value="<%=ActivoEnum.NO.getValue()%>"><spring:message code="<%=ActivoEnum.NO.getLabel()%>"/></option>
															</select>	
														</div>
													</div>
													<div class="form-groupcol-xs-12 col-md-4">
														<label for="grupotrabajoConsulta_filter_table" class="control-label "><spring:message code="label.grupoTrabajo"/>:</label>
														<div class="divComboW200">
															<select name="idsGrupoTrabajo" class="form-control" id="grupotrabajoConsulta_filter_table"></select>
														</div>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
							</sec:authorize>
							<div id="consultaExpedientes_filter_buttonSet" class="pull-right" style="margin:0 1rem 1rem 0">
								<!-- Botón de filtrado -->
								
								<button id="consultaExpedientes_filter_filterButton" type="button" class="ui-button ui-widget ui-state-default ui-corner-all">
									<spring:message code="filter" />
								</button>
								
								<!-- Enlace de limpiar -->
								<a id="consultaExpedientes_filter_cleanLink_modificado" href="javascript:void(0)" class="rup-enlaceCancelar"><spring:message code="clear" /></a>
							</div>
						</div>
					</fieldset>
				</form>
			</div>
			<div class="consultaExpedientes_grid_div horizontal_scrollable_table" >
			<!-- Tabla -->
			<table id="consultaExpedientes"></table>
			<!-- Barra de paginación -->
			<div id="consultaExpedientes_pager"></div>
		</div>	
		<div class="row form-group" id="leyendaGestor">
			<div class="col-xs-8">
				<div class="col-xs-1" style="width: 35px!important;">
					<i class="fa fa-exclamation-circle iconoGestorNoActivo" aria-hidden="true"></i>
				</div>
				<label class="control-label col-xs-10 textoGestorNoActivo">
					<spring:message code="label.gestorInactivo"/>
				</label>
			</div>
		</div>
		</div> 
	</div>
</div>
</div>
<div id="expRelacionadosDiv">
	<div id="buscadorExpedientesRelacionados" class="oculto"></div>
	<div id="buscadorEtiquetasConsulta" class="oculto"></div>
</div>
