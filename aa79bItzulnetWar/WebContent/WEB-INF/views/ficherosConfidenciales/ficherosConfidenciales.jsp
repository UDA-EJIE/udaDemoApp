<%@include file="/WEB-INF/includeTemplate.inc"%>
<%@page import="com.ejie.aa79b.model.enums.TipoExpedienteEnum"%>
<%@page import="com.ejie.aa79b.model.enums.TipoEntidadEnum" %>
<%@page import="com.ejie.aa79b.model.enums.EstadoExpedienteEnum" %>

<div id="divFicherosConfidencialesCapa">
	<div id="divFicherosConfidencialesGeneral">
		<div id="divFicherosConfidenciales">
			<div class="container-fluid aa79b-fade in" id="divBusquedaFichConfi">
				<h2><spring:message code="label.titulo.ficherosConfidenciales"/></h2>
				<hr class="mb-2">
					<div id="fichConfi_div" class="rup-table-container">
					<div id="busquedaFicherosConfi_feedback"></div>						<!-- Feedback de la tabla --> 
					<div id="busquedaFicherosConfi_toolbar"></div>						<!-- Botonera de la tabla --> 
					<div id="busquedaFicherosConfi_filter_div" class="filterForm"> <!-- Capa contenedora del formulario de filtrado -->
						<form id="busquedaFicherosConfi_filter_form" class="form-horizontal">	
						<div id="busquedaFicherosConfi_filter_toolbar" class="formulario_legend"></div>
							<fieldset id="busquedaFicherosConfi_filter_fieldset" class="rup-table-filter-fieldset filterCuerpo ">
								<div class="p-2">
									<div class="row">
										<div class="form-group col-md-2">
											<label for="idTipoExpediente_filter_table" class="control-label "><spring:message code="label.tipoExpediente"/>:</label>
											<div class="divComboW125">
												<select name="idTipoExpediente" class="form-control" id="idTipoExpediente_filter_table">
													<option value="" ><spring:message code="combo.todos"/></option>
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
										<div class="form-group col-lg-4 col-md-4">
											<label class="control-label" for="tituloExpediente_filter_table"><spring:message code="label.titulo" />:</label> 
											<input type="text" name="titulo" class="form-control" id="tituloExpediente_filter_table" maxlength="150">
										</div>
										<div class="form-group col-md-3">
											<label for="idEstadoExp_filter_table" class="control-label "><spring:message code="label.estadoExpediente"/>:</label>
											<select name="bitacoraExpediente.estadoExp.id" class="form-control" id="idEstadoExp_filter_table">
												<option value=""><spring:message code="combo.todos" /></option>
												<!-- <option value="0"><spring:message code="label.eliminado"/></option>-->
												<option value="<%=EstadoExpedienteEnum.EN_ESTUDIO.getValue()%>"><spring:message code="label.enEstudio"/></option>
												<option value="<%=EstadoExpedienteEnum.EN_CURSO.getValue()%>"><spring:message code="label.estadoFaseExpediente.enCurso"/></option>
												<option value="<%=EstadoExpedienteEnum.RECHAZADO.getValue()%>"><spring:message code="label.estadoFaseExpediente.rechazado"/></option>
												<option value="<%=EstadoExpedienteEnum.ANULADO.getValue()%>"><spring:message code="label.estadoFaseExpediente.anulado"/></option>
												<option value="<%=EstadoExpedienteEnum.CERRADO.getValue()%>"><spring:message code="label.estadoFaseExpediente.cerrado"/></option>
												<option value="<%=EstadoExpedienteEnum.FINALIZADO.getValue()%>"><spring:message code="label.estadoFaseExpediente.finalizado"/></option>
											</select>
										</div>
									</div>
									<div class="row">
										<div class="form-group col-xs-6 ">
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
											<label id="labelEntidadSolicitante_filter_table" for="idEntidadSolicitante_filter_table" class="control-label " data-i18n="label.entidadGestora"><spring:message code="label.entidadGestora" />:</label>
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
									<div id="busquedaFicherosConfi_filter_buttonSet" class="pull-right" style="margin:0 1rem 1rem 0">
										<!-- Botón de filtrado -->
										<button id="busquedaFicherosConfi_filter_filterButton" type="button" class="ui-button ui-widget ui-state-default ui-corner-all">
											<spring:message code="filter" />
										</button>
										<!-- Enlace de limpiar -->
										<a id="busquedaFicherosConfi_filter_cleanLinkModificado" href="javascript:void(0)" class="rup-enlaceCancelar"><spring:message code="clear" /></a>
									</div>
								</div>
							</fieldset>
						</form>
					</div>
					<div class="busquedaFicherosConfi_grid_div horizontal_scrollable_table" >
						<!-- Tabla -->
						<table id="busquedaFicherosConfi"></table>
						<!-- Barra de paginación -->
						<div id="busquedaFicherosConfi_pager"></div>
					</div>
				</div>
			</div>
		</div>
	</div>		
</div>