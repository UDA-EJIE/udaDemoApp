<%@include file="/WEB-INF/includeTemplate.inc"%>
<%@page import="com.ejie.aa79b.model.enums.TipoExpedienteEnum"%>
<%@page import="com.ejie.aa79b.model.enums.TipoEntidadEnum"%>

<div class="container-fluid aa79b-fade in" id="divBusquedaGeneral">
	<div id="divBusquedaGeneralCapa">
		<div id="divBusqueda">	
			<h2><spring:message code="label.cambioEstado"/></h2>
			<div id="busquedaGeneral_div" class="rup-table-container">
				<div id="busquedaGeneral_feedback"></div>						<!-- Feedback de la tabla --> 
				<div id="busquedaGeneral_toolbar"></div>						<!-- Botonera de la tabla --> 
				<div id="busquedaGeneral_filter_div" class="filterForm"> <!-- Capa contenedora del formulario de filtrado -->
					<form id="busquedaGeneral_filter_form" class="form-horizontal">	
						<div id="busquedaGeneral_filter_toolbar" class="formulario_legend"></div>
						<fieldset id="busquedaGeneral_filter_fieldset" class="rup-table-filter-fieldset ">
							<div class="p-2">
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
									<div class="form-group col-xs-12 col-md-2">
										<label for="numExp_filter_table" class="control-label"><spring:message code="label.numeroExpediente"/>:</label>
										<div>
											<label for="anyo_filter_table" class="control-label oculto"><spring:message code="label.anyo"/></label>
											<input type="text" name="anyo" class=" numeric" style="width: 40px;" id="anyo_filter_table" maxlength="2"/>
											<label class="control-label" style="width: 3%;">/</label>
											<input type="text" name="numExp" class=" numeric" style="width: 75px;" id="numExp_filter_table" maxlength="6"/>
										</div>
									</div>
									<div class="form-group col-xs-12 col-md-6">
										<label for="titulo_filter_table" class="control-label "><spring:message code="label.titulo"/>:</label>
										<input type="text" name="titulo" class="form-control" id="titulo_filter_table" maxlength="150"/>
									</div>
								</div>
								
								<div class="row" style="margin-top:0.5rem">
									<div class="col-md-6 pl-0">
										<div class="form-group col-xs-12 col-md-4">
											<label for="estado_filter_table" class="control-label "><spring:message code="label.estado"/>:</label>
											<select name="bitacoraExpediente.estadoExp.id" class="form-control" id="estado_filter_table"></select>
										</div>
										<div class="form-group col-xs-12 col-md-7">
											<label for="fase_filter_table" class="control-label "><spring:message code="label.fase"/>:</label>
											<select name="bitacoraExpediente.faseExp.id" class="form-control" id="fase_filter_table"></select>
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
								
								<div class="row">
									<div class="form-group col-xs-4">
										<label for="idTiposEntidad_filter_table" class="control-label " data-i18n="label.tipoEntidadGestora"><spring:message code="label.tipoEntidadGestora"/>:</label>
										<div id="idTiposEntidad_filter_table" >
											<div class="col-md-3">
												<input type="radio" name="tipoEntidad" id="tipoEntidad_T" value="" data-on-text='<spring:message code="label.todas"/>'/>
												<label for="tipoEntidad_T" class="radio-inline rup-table-filter-fieldset" data-i18n="label.tipoEntidadGestora"><spring:message code="label.todas"/></label>
											</div>
											<c:set var="labelsTipoEntidad" value="<%=TipoEntidadEnum.values()%>"/>
											<c:forEach items="${labelsTipoEntidad}" var="tipoEntidad">
											<div class="col-md-3">
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
									<div id="div_contactoGestor_filter_table" class="form-group col-xs-12 col-md-5">
										<div id="autocompleteContainer_contactoGestor_filter_table">
											<label for="contactoGestor_filter_table" class="control-label " data-i18n="label.contactoGestor"><spring:message code="label.contactoGestor" />:</label>
											<input id="contactoGestor_filter_table" class="form-control" name="gestorExpediente.solicitante.dni"/>
										</div>
									</div>
								</div>
								
								<div id="busquedaGeneral_filter_buttonSet" class="pull-right" style="margin:0 1rem 1rem 0">
									<!-- Botón de filtrado -->
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
		</div>
	</div>
</div>