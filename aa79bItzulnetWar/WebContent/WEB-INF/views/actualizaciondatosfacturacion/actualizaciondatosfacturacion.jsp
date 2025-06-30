<%@include file="/WEB-INF/includeTemplate.inc"%>
<%@page import="com.ejie.aa79b.model.enums.TipoExpedienteEnum"%>
<%@page import="com.ejie.aa79b.model.enums.EstadoEnum"%>
<%@page import="com.ejie.aa79b.model.enums.TipoExpedienteGrupoEnum"%>
<%@page import="com.ejie.aa79b.model.enums.AportaSubsanacionEnum"%>
<%@page import="com.ejie.aa79b.model.enums.ActivoEnum"%>
<%@page import="com.ejie.aa79b.model.enums.TipoEntidadEnum"%>
<%@page import="com.ejie.aa79b.model.enums.EstadoAceptacionTareaEnum"%>
<%@page import="com.ejie.aa79b.model.enums.EstadoEjecucionTareaEnum"%>
<%@page import="com.ejie.aa79b.model.enums.EstadoRequerimientoEnum"%>

<div class="container-fluid aa79b-fade in" id="divBusquedaGeneral">
	<div id="divBusquedaGeneralCapa">
		<div id="divBusqueda">	
			<h2><spring:message code="label.titulo.actualizacionDatosFacturacion"/></h2>
			<div id="busquedaGeneral_div" class="rup-table-container">
				<div id="busquedaGeneral_feedback"></div>						<!-- Feedback de la tabla --> 
				<div id="busquedaGeneral_toolbar"></div>						<!-- Botonera de la tabla --> 
				<div id="busquedaGeneral_filter_div" class="filterForm"> <!-- Capa contenedora del formulario de filtrado -->
					<form id="busquedaGeneral_filter_form" class="form-horizontal">	
						<div id="busquedaGeneral_filter_toolbar" class="formulario_legend"></div>
						<fieldset id="busquedaGeneral_filter_fieldset" class="rup-table-filter-fieldset ">
							<div class="p-2">
								<input type="hidden" id="palPresupuesto" name="palPresupuesto">
								<fieldset>
									<legend>
										<spring:message code="label.datosExpediente"/>
									</legend>
									<div class="row">
										<div class="form-group col-xs-12 col-md-2">
											<label for="idTipoExpediente_filter_table" class="control-label "><spring:message code="label.tipoExpediente"/>:</label>
											<div class="divComboW125">
												<select name="idTipoExpediente" class="form-control" id="idTipoExpediente_filter_table">
													<option value="" selected="true"><spring:message code="combo.todos"/></option>
													<option value="<%=TipoExpedienteEnum.INTERPRETACION.getValue()%>"><spring:message code="<%=TipoExpedienteEnum.INTERPRETACION.getLabel()%>"/></option>
													<option value="<%=TipoExpedienteEnum.TRADUCCION.getValue()%>"><spring:message code="<%=TipoExpedienteEnum.TRADUCCION.getLabel()%>"/></option>
													<option value="<%=TipoExpedienteEnum.REVISION.getValue()%>"><spring:message code="<%=TipoExpedienteEnum.REVISION.getLabel()%>"/></option>
												</select>
											</div>
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
										<div class="form-group col-xs-12 col-md-5">
											<label for="titulo_filter_table" class="control-label "><spring:message code="label.titulo"/>:</label>
											<input type="text" name="titulo" class="form-control" id="titulo_filter_table" maxlength="150"/>
										</div>
										<div class="form-group col-xs-12 col-md-2">
											<label for="expFacturable_filter_table" class="control-label "><spring:message code="label.facturableTitle"/>:</label>
											<div class="divComboW125">
												<select name="expedienteTradRev.indFacturable" class="form-control col-md-8" id="expFacturable_filter_table">
													<option value="" selected="true"><spring:message code="combo.todos"/></option>
													<option value="<%=ActivoEnum.SI.getValue()%>"><spring:message code="<%=ActivoEnum.SI.getLabel()%>"/></option>
													<option value="<%=ActivoEnum.NO.getValue()%>"><spring:message code="<%=ActivoEnum.NO.getLabel()%>"/></option>
												</select>	
											</div>
										</div>
									</div>
									<div class="row" style="margin-top:0.5rem">
										<div class="form-group col-md-3">
											<label for="indPublicarBopv" class="control-label "><spring:message code="label.publicarBOPV"/>:</label>
											<div class="divComboW125">
												<select name="expedienteTradRev.indPublicarBopv" class="form-control col-md-8" id="indPublicarBopv">
													<option value="" selected="true"><spring:message code="combo.todos"/></option>
													<option value="<%=ActivoEnum.SI.getValue()%>"><spring:message code="<%=ActivoEnum.SI.getLabel()%>"/></option>
													<option value="<%=ActivoEnum.NO.getValue()%>"><spring:message code="<%=ActivoEnum.NO.getLabel()%>"/></option>
												</select>	
											</div>
										</div>
										<div class="form-group col-md-3">
											<label for="indPublicadoBoe" class="control-label "><spring:message code="label.boe"/>:</label>
											<div class="divComboW125">
												<select name="expedienteTradRev.indPublicadoBoe" class="form-control col-md-8" id="indPublicadoBoe">
													<option value="" selected="true"><spring:message code="combo.todos"/></option>
													<option value="<%=ActivoEnum.SI.getValue()%>"><spring:message code="<%=ActivoEnum.SI.getLabel()%>"/></option>
													<option value="<%=ActivoEnum.NO.getValue()%>"><spring:message code="<%=ActivoEnum.NO.getLabel()%>"/></option>
												</select>	
											</div>
										</div>
										<div class="form-group col-md-5">
											<label class="control-label" for="idiomaDestino"><spring:message code="label.idiomaRevDestTraduccion"/>:</label>
											<div class="divComboIdiomaDestino">
												<select name="expedienteTradRev.idIdioma" id="idiomaDestino" class="form-control">
												</select>
											</div>
										</div>
									</div>
									<div class="row" style="margin-top:0.5rem">
										<div class="col-md-6">
											<label class="control-label col-lg-12 p-0" data-i18n="label.fechaSolicitud"><spring:message code="label.fechaSolicitud" />:</label>
											<div class="form-group col-md-6 p-0">
												<label for="fechaAltaDesde" class="control-label col-md-4 p-0 valFecha" data-i18n="label.desde"><spring:message code="label.desde" />:</label>
												<div class="form-group  col-md-8 p-0 grupoFechaHora grupoFecha">
													<input type="text" id="fechaAltaDesde" name="fechaAltaDesde"  class="form-control"  >
												</div>
											</div>
											<div class="form-group col-md-6 p-0">
												<label for="fechaAltaHasta" class="control-label col-md-4 p-0 valFecha" data-i18n="label.hasta"><spring:message code="label.hasta" />:</label>
												<div class="form-group  col-md-8 p-0 grupoFechaHora grupoFecha">
													<input type="text" id="fechaAltaHasta" name="fechaAltaHasta"  class="form-control" >
												</div>
											</div>
										</div>
										<div class="col-md-6">
											<label class="control-label col-lg-12 p-0" data-i18n="label.fechaEntregaIZOFechaFinInter"><spring:message code="label.fechaEntregaIZOFechaFinInter" />:</label>
											<div class="form-group col-lg-6 p-0">
												<label for="fechaEntregaDesde_filter" class="control-label col-md-4 p-0 valFecha" data-i18n="label.desde"><spring:message code="label.desde" />:</label>
												<div class="form-group  col-md-8 p-0 grupoFechaHora grupoFecha">
													<input type="text" id="fechaEntregaDesde_filter" name="expedienteTradRev.fechaEntregaIzoDesde"  class="form-control"  >
												</div>
											</div>
											<div class="form-group col-lg-6 p-0">
												<label for="fechaEntregaHasta_filter" class="control-label col-md-4 p-0 valFecha" data-i18n="label.hasta"><spring:message code="label.hasta" />:</label>
												<div class="form-group  col-md-8 p-0 grupoFechaHora grupoFecha">
													<input type="text" id="fechaEntregaHasta_filter" name="expedienteTradRev.fechaEntregaIzoHasta"  class="form-control" >
												</div>
											</div>
										</div>
									</div>
								</fieldset>
								<fieldset>
									<legend>
										<spring:message code="label.datosSolicitante"/>
									</legend>
									<div class="row" style="margin-top:0.5rem">
										<div class="form-group col-xs-5 ">
											<label for="idTiposEntidad_filter_table" class="control-label " data-i18n="label.tipoEntidadGestora"><spring:message code="label.tipoEntidadGestora"/>:</label>
											<div id="idTiposEntidad_filter_table" >
												<div class="col-md-3 no-padding">
													<input type="radio" name="tipoEntidad" id="tipoEntidad_T" value="" data-on-text='<spring:message code="label.todas"/>'/>
													<label for="tipoEntidad_T" class="radio-inline rup-table-filter-fieldset" data-i18n="label.tipoEntidad"><spring:message code="label.todas"/></label>
												</div>
												<c:set var="labelsTipoEntidad" value="<%=TipoEntidadEnum.values()%>"/>
												<c:forEach items="${labelsTipoEntidad}" var="tipoEntidad">
												<div class="col-md-3 no-padding">
													<input type="radio" name="tipoEntidad" id="tipoEntidad_${tipoEntidad.value}" value="${tipoEntidad.value}"  data-on-text='<spring:message code="${tipoEntidad.label}"/>'/>
													<label for="tipoEntidad_${tipoEntidad.value}" class="radio-inline rup-table-filter-fieldset" data-i18n="label.tipoEntidad"><spring:message code="${tipoEntidad.label}"/></label>
												</div>
												</c:forEach>
											</div>
											<input type="hidden" name="gestorExpediente.entidad.tipo" id="gestorExpedienteEntidadTipo"></input>	
										</div>
										<div class="form-group col-xs-12 col-md-5">
											<label for="idEntidadSolicitante_filter_table" class="control-label " data-i18n="label.entidadGestora"><spring:message code="label.entidadGestora" />:</label>
											<select id="idEntidadSolicitante_filter_table" class="form-control" name="gestorExpediente.entidad.codigoCompleto"></select>	
										</div>
									</div>
									<div class="row">
										<div class="form-group col-xs-12 col-md-5">
											<label for="contactoGestor_filter_table" class="control-label " data-i18n="label.contactoGestor"><spring:message code="label.contactoGestor" />:</label>
											<select id="contactoGestor_filter_table" name="gestorExpediente.solicitante.dni" class="form-control"></select>
										</div>
									</div>
								</fieldset>
								<fieldset>
									<legend>
										<spring:message code="label.entidadFactExpediente"/>
									</legend>
									<div class="row">
										<div class="form-group col-xs-5 ">
											<label for="idTiposEntidadFactura" class="control-label " data-i18n="label.tipoEntidad"><spring:message code="label.tipoEntidad"/>:</label>
											<div id="idTiposEntidadFactura" >
												<div class="col-md-3 no-padding">
													<input type="radio" name="tipoEntidadF" id="tipoEntidadF_T" value="-1" data-on-text='<spring:message code="label.todas"/>'/>
													<label for="tipoEntidadF_T" class="radio-inline rup-table-filter-fieldset" data-i18n="label.tipoEntidad"><spring:message code="label.todas"/></label>
												</div>
												<c:set var="labelsTipoEntidad" value="<%=TipoEntidadEnum.values()%>"/>
												<c:forEach items="${labelsTipoEntidad}" var="tipoEntidadF">
												<div class="col-md-3 no-padding">
													<input type="radio" name="tipoEntidadF" id="tipoEntidadF_${tipoEntidadF.value}" value="${tipoEntidadF.value}"  data-on-text='<spring:message code="${tipoEntidadF.label}"/>'/>
													<label for="tipoEntidadF_${tipoEntidadF.value}" class="radio-inline rup-table-filter-fieldset" data-i18n="label.tipoEntidad"><spring:message code="${tipoEntidadF.label}"/></label>
												</div>
												</c:forEach>
											</div>
											<input type="hidden" name="entidadContactoFactura.entidad.tipo" id="entidadContactoFacturaEntidadTipo"></input>	
										</div>
										<div class="form-group col-xs-12 col-md-5">
											<label for="idEntidadSolicitanteF" class="control-label " data-i18n="label.entidadGestoraExpEnEstudio"><spring:message code="label.entidad" />:</label>
											<select id="idEntidadSolicitanteF" class="form-control" name="entidadContactoFactura.entidad.codigoCompleto"></select>	
										</div>
										
										<div class="form-group col-md-2">
											<label for="indEntidadFacturable" class="control-label "><spring:message code="label.facturableTitle"/>:</label>
											<div class="divComboW125">
												<select name="entidadContactoFactura.entidad.facturable" class="form-control col-md-8" id="indEntidadFacturable">
													<option value="" selected="true"><spring:message code="combo.todos"/></option>
													<option value="<%=ActivoEnum.SI.getValue()%>"><spring:message code="<%=ActivoEnum.SI.getLabel()%>"/></option>
													<option value="<%=ActivoEnum.NO.getValue()%>"><spring:message code="<%=ActivoEnum.NO.getLabel()%>"/></option>
												</select>	
											</div>
											
										</div>
									</div>
									<div class="row">
										<div class="form-group col-md-5">
											<label for="dniContactoEntidadSolicitanteF" class="control-label" data-i18n="label.contactoFactura"><spring:message code="label.contactoFactura" />:</label>
											<select id="dniContactoEntidadSolicitanteF" name="entidadContactoFactura.solicitante.dni" class="form-control"></select>
										</div>
									</div>
								</fieldset>
								<div id="busquedaGeneral_filter_buttonSet" class="pull-right mt-1 mr-1 mb-1" >
									<!-- Botón de filtrado -->
									<!-- input id="estudioExpedientes_filter_filterButton" type="button" class="ui-button ui-widget ui-state-default ui-corner-all" value='<spring:message code="filter" />' /-->
									
									<button id="busquedaGeneral_filter_filterButton" type="button" class="ui-button ui-widget ui-state-default ui-corner-all">
										<spring:message code="filter" />
									</button>
									
									<!-- Enlace de limpiar -->
									<a id="busquedaGeneral_filter_cleanLinkModificado" href="javascript:void(0)" class="rup-enlaceCancelar"><spring:message code="clear" /></a>
								</div>
							</div>
						</fieldset>
					</form>
				</div>
				<div class="busquedaGeneral_grid_div horizontal_scrollable_table" >
					<!-- Tabla -->
					<table id="busquedaGeneral"></table>
					<!-- Barra de paginación -->
					<div id="busquedaGeneral_pager"></div>
				</div>
			</div>
			<div id="indBoe_dialog" class="rup-table-formEdit-detail">
				<div id="indBoe_dialog_detail_navigation"></div>
				<!-- Barra de navegación del detalle -->
				<div class="ui-dialog-content ui-widget-content">
					<form id="indBoe_dialog_form">
						<div class="row" style="padding-left:5px;">
							<div class="form-group col-md-12 col-lg-6">
								<label class="control-label"><spring:message code="label.numExp"/></label>	
								<label id="labelNumExp" class="control-label"></label>	
							</div>
						</div>
						<div class="row" style="padding-left:5px;">
							<div class="form-group col-md-12 col-lg-4">
								<label for="indPublicadoBoeDialog"><spring:message code="label.boe"/></label>
								<input type="checkbox" class="form-control" id="indPublicadoBoeDialog" name="indPublicadoBoeDialogName" value="S" data-switch-pestana=true />
							</div>
							<div class="form-group col-md-12 col-lg-6">
								<label for="urlBoeDialog"><spring:message code="label.urlBoe"/></label>
								<input type="text" id="urlBoeDialog" name="expedienteTradRev.urlBoe" class="form-control" >
							</div>
						</div>
					</form>
				</div>
				</div>
			</div>	
		</div>
	</div>
</div>
