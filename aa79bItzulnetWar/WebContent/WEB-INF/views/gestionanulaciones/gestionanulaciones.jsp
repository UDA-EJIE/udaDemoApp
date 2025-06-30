<%@include file="/WEB-INF/includeTemplate.inc"%>
<%@page import="com.ejie.aa79b.model.enums.TipoExpedienteEnum"%>
<%@page import="com.ejie.aa79b.model.enums.TipoEntidadEnum" %>

<div id="divGestionAnulacionesCapa">
	<div id="divGestionAnulacionesGeneral">
		<div id="divGestionAnulaciones">
			<div class="container-fluid aa79b-fade in" id="divBusquedaGestAnul">
					<h2><spring:message code="titulo.gestionAnulaciones"></spring:message></h2>
					<div id="gest_div" class="rup-table-container">
					<div id="busquedaGestAnul_feedback"></div>						<!-- Feedback de la tabla --> 
					<div id="busquedaGestAnul_toolbar"></div>						<!-- Botonera de la tabla --> 
					<div id="busquedaGestAnul_filter_div" class="filterForm"> <!-- Capa contenedora del formulario de filtrado -->
						<form id="busquedaGestAnul_filter_form" class="form-horizontal">	
						<div id="busquedaGestAnul_filter_toolbar" class="formulario_legend"></div>
							<fieldset id="busquedaGestAnul_filter_fieldset" class="rup-table-filter-fieldset ">
								<div class="p-2">
									<div class="row">
										<div class="form-group col-md-3">
											<label for="idTipoExpediente_filter_table" class="control-label "><spring:message code="label.tipoExpediente"/>:</label>
											<div class="divComboW125">
												<select name="idTipoExpediente" class="form-control" id="idTipoExpediente_filter_table">
													<option value="" ><spring:message code="combo.todos"/></option>
													<option value="<%=TipoExpedienteEnum.TRADUCCION.getValue()%>"><spring:message code="<%=TipoExpedienteEnum.TRADUCCION.getLabel()%>"/></option>
													<option value="<%=TipoExpedienteEnum.REVISION.getValue()%>"><spring:message code="<%=TipoExpedienteEnum.REVISION.getLabel()%>"/></option>
													<option value="<%=TipoExpedienteEnum.INTERPRETACION.getValue()%>"><spring:message code="<%=TipoExpedienteEnum.INTERPRETACION.getLabel()%>"/></option>
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
										<div class="form-group col-lg-5">
											<label class="control-label" for="tituloExpediente_filter_table"><spring:message code="label.titulo" />:</label> 
											<input type="text" name="titulo" class="form-control" id="tituloExpediente_filter_table" maxlength="150">
										</div>
									</div>
									<div class="row">
										<div class="form-group col-xs-12 col-s-12  col-md-6 col-lg-5 col-xl-4">
											<label for="idMotivosAnulacion_filter_table" class="control-label "><spring:message code="label.motivosAnulacion"/>:</label>
											<select name="motivosAnulacion.id012" class="form-control" id="idMotivosAnulacion_filter_table"></select>
										</div>
										<div class="col-xs-0 col-s-0 col-md-0 col-lg-1 col-xl-2">
										</div>
										<div class="form-group col-xs-12 col-s-12 col-md-6 col-lg-5 col-xl-5">
											<label for="tecnicoAsignado_filter_table" class="control-label " data-i18n="label.tecnicoAsignadoEstudioExpediente"><spring:message code="label.tecnicoAsignadoEstudioExpediente" />:</label>
											<input id="tecnicoAsignado_filter_table" class="form-control" name="tecnico.dni"/>
										</div>		
									</div>
									<fieldset>
										<legend>
											<spring:message code="label.gestorExpediente"></spring:message>
										</legend>

										<div class="row">
											<div class="form-group col-xs-6 ">
												<label for="idTiposEntidadFactura" class="control-label " data-i18n="tipoEntidadSolicitante"><spring:message code="label.tipoEntidadSolicitante"/>:</label>
												<div id="idTiposEntidadFactura" >
													<div class="col-md-3 no-padding">
														<input type="radio" name="tipoEntidadF" id="tipoEntidadF_T" value="-1" data-on-text='<spring:message code="label.todas"/>'/>
														<label for="tipoEntidadF_T" class="radio-inline rup-table-filter-fieldset" data-i18n="label.tipoEntidadSolicitante"><spring:message code="label.todas"/></label>
													</div>
													<c:set var="labelsTipoEntidad" value="<%=TipoEntidadEnum.values()%>"/>
													<c:forEach items="${labelsTipoEntidad}" var="tipoEntidadF">
													<div class="col-md-3 no-padding">
														<input type="radio" name="tipoEntidadF" id="tipoEntidadF_${tipoEntidadF.value}" value="${tipoEntidadF.value}"  data-on-text='<spring:message code="${tipoEntidadF.label}"/>'/>
														<label for="tipoEntidadF_${tipoEntidadF.value}" class="radio-inline rup-table-filter-fieldset" data-i18n="label.tipoEntidadSolicitante"><spring:message code="${tipoEntidadF.label}"/></label>
													</div>
													</c:forEach>
												</div>
												<input type="hidden" name="gestorExpediente.entidad.tipo" id="entidadContactoFacturaEntidadTipo"></input>	
											</div>
											<div class="form-group col-xs-12 col-md-4">
												<label for="idEntidadSolicitanteF" class="control-label " data-i18n="label.entidadFactExpediente"><spring:message code="label.entidad" />:</label>
												<select id="idEntidadSolicitanteF" class="form-control" name="gestorExpediente.entidad.codigoCompleto"></select>	
											</div>																					
										</div>
										<div class="row">
											<div class="form-group col-md-5">
												<label for="dniContactoEntidadSolicitanteF" class="control-label" data-i18n="label.contactoGestor"><spring:message code="label.contactoGestor" />:</label>
												<select id="dniContactoEntidadSolicitanteF" name="gestorExpediente.solicitante.dni" class="form-control"></select>
											</div>
										</div>
									</fieldset>
								</div>
								<div id="busquedaGestAnul_filter_buttonSet" class="pull-right" style="margin:0 1rem 1rem 0">
									<!-- Botón de filtrado -->
									<button id="busquedaGestAnul_filter_filterButton" type="button" class="ui-button ui-widget ui-state-default ui-corner-all">
										<spring:message code="filter" />
									</button>
									<!-- Enlace de limpiar -->
									<a id="busquedaGestAnul_filter_cleanLinkModificado" href="javascript:void(0)" class="rup-enlaceCancelar"><spring:message code="clear" /></a>
								</div>
							</fieldset>
						</form>
					</div>
					<div class="busquedaGestAnul_grid_div horizontal_scrollable_table" >
						<!-- Tabla -->
						<table id="busquedaGestAnul"></table>
						<!-- Barra de paginación -->
						<div id="busquedaGestAnul_pager"></div>
					</div>
					<div>
						<div>
							<i class="fa fa-exclamation-triangle" aria-hidden="true" style="color: #ba1944;"></i>
							<label class="control-label" style="color: #ba1944;">
								<spring:message code="label.avisoAnulacionRechazoFecha"/>
							</label>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>		
</div>