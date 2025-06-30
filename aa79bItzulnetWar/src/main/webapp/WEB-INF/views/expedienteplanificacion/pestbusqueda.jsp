<%@page import="com.ejie.aa79b.model.enums.TipoExpedienteGrupoEnum"%>
<%@page import="com.ejie.aa79b.model.enums.EstadoEnum"%>
<%@page import="com.ejie.aa79b.model.enums.TipoExpedienteEnum"%>
<%@page import="com.ejie.aa79b.model.enums.AportaSubsanacionEnum"%>
<%@page import="com.ejie.aa79b.model.enums.ActivoEnum"%>
<%@page import="com.ejie.aa79b.model.enums.TipoEntidadEnum"%>
<%@page import="com.ejie.aa79b.model.enums.EstadoAceptacionTareaEnum"%>
<%@page import="com.ejie.aa79b.model.enums.EstadoEjecucionTareaEnum"%>
<%@page import="com.ejie.aa79b.model.enums.EstadoRequerimientoEnum"%>
<%@include file="/WEB-INF/includeTemplate.inc"%>

<div class="container-fluid aa79b-fade in" id="divBusquedaGeneral">
		<div id="busquedaGeneral_div" class="rup-table-container">
		<div id="busquedaGeneral_feedback"></div>						<!-- Feedback de la tabla --> 
		<div id="busquedaGeneral_toolbar"></div>						<!-- Botonera de la tabla --> 
		<div id="busquedaGeneral_filter_div" class="filterForm"> <!-- Capa contenedora del formulario de filtrado -->
			<form id="busquedaGeneral_filter_form" class="form-horizontal">	
			<div id="busquedaGeneral_filter_toolbar" class="formulario_legend"></div>
<!-- 				<input type="hidden" id="tipoHidden"> -->
				<fieldset id="busquedaGeneral_filter_fieldset" class="rup-table-filter-fieldset ">
					<div class="p-2">
					<input type="hidden" id="palTrados" name="palTrados">
					<div class="row">
						<div class="form-group col-md-3">
							<label for="idTipoExpediente_filter_table" class="control-label ">(*)<spring:message code="label.tipoExpediente"/>:</label>
							<select name="idTipoExpediente" class="form-control" id="idTipoExpediente_filter_table" >
								<option value="<%=TipoExpedienteEnum.TRADUCCION.getValue()%>"><spring:message code="<%=TipoExpedienteEnum.TRADUCCION.getLabel()%>"/></option>
								<option value="<%=TipoExpedienteEnum.INTERPRETACION.getValue()%>"><spring:message code="<%=TipoExpedienteEnum.INTERPRETACION.getLabel()%>"/></option>
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
						<div class="form-group col-xs-12 col-md-3">
							<label for="fases_filter_table" class="control-label "><spring:message code="label.estadoFase"/>:</label>
							<div class="divComboW200">
								<select name="idsFaseExpediente" class="form-control" id="fases_filter_table">
								</select>
							</div>
						</div>
						<div class="form-groupcol-xs-12 col-md-2">
							<label for="prioridad_filter_table" class="control-label "><spring:message code="label.prioridad"/>:</label>
							<div class="divComboW125">
								<select name="prioridad" class="form-control" id="prioridad_filter_table" style="width:150px!important">
									<option value="" selected="true"><spring:message code="combo.todos"/></option>
									<option value="<%=ActivoEnum.SI.getValue()%>"><spring:message code="<%=ActivoEnum.SI.getLabel()%>"/></option>
									<option value="<%=ActivoEnum.NO.getValue()%>"><spring:message code="<%=ActivoEnum.NO.getLabel()%>"/></option>
								</select>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="form-groupcol-xs-12 col-md-2">
							<label for="gestorVIP_filter_table" class="control-label "><spring:message code="label.gestorVip"/>:</label>
							<div class="divComboW125">
								<select name="gestorExpediente.solicitante.gestorExpedientesVIP" class="form-control" id="gestorVIP_filter_table">
									<option value="" selected="true"><spring:message code="combo.todos"/></option>
									<option value="<%=ActivoEnum.SI.getValue()%>"><spring:message code="<%=ActivoEnum.SI.getLabel()%>"/></option>
									<option value="<%=ActivoEnum.NO.getValue()%>"><spring:message code="<%=ActivoEnum.NO.getLabel()%>"/></option>
								</select>
							</div>
						</div>
						<div class="form-groupcol-xs-12 col-md-2">
                            <label class="control-label col-xs-12 no-padding-left" for="indGruposBOE"><spring:message code="label.indGruposBOE"/>:</label>
                            <input type="checkbox" name="indGruposBOE" id="indGruposBOE" value="S" data-switch-pestana="true">
                        </div>
                        <div class="form-groupcol-xs-12 col-md-3">                            						
							<label for="grupotrabajo_filter_table" class="control-label "><spring:message code="label.grupoTrabajo"/>:</label>
							<div class="divComboW200">
								<select name="idsGrupoTrabajo" class="form-control" id="grupotrabajo_filter_table">
								</select>
							</div>
						</div>
						<div class="form-groupcol-xs-12 col-md-3">
							<label for="asignador_filter_table" class="control-label "><spring:message code="label.asignador"/>:</label>
							<select name=asignador.dni class="form-control" id="asignador_filter_table"></select>
						</div>
						<div class="col-md-3">
							<label for="BOPV_filter_table" class="control-label "><spring:message code="label.bopv"/>/<spring:message code="label.previstoBopv"/>:</label>
							<div class="divComboW125">
								<select name="indicesBopv" class="form-control col-md-8" id="BOPV_filter_table">
									<option value="" selected="true"><spring:message code="combo.todos"/></option>
									<option value="<%=ActivoEnum.SI.getValue()%>"><spring:message code="<%=ActivoEnum.SI.getLabel()%>"/></option>
									<option value="<%=ActivoEnum.NO.getValue()%>" ><spring:message code="<%=ActivoEnum.NO.getLabel()%>"/></option>
								</select>	
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-4">
							<label class="control-label col-lg-12 p-0" data-i18n="numPalabrasIZO"><spring:message code="comun.numPalabrasIZO" />:</label>
							<div class="form-group col-lg-6 p-0 numPalabrasContainer">
								<label for="numPalabrasDesde_filter" class="control-label col-md-6 p-0" data-i18n="label.desde"><spring:message code="label.desde" />:</label>
								<div class="form-group  col-md-5 p-0">
									<input type="text" id="numPalabrasDesde_filter" name="expedienteTradRev.numPalIzoDesde" class="form-control no-padding-left numeric" maxlength="6" value="">
								</div>
							</div>
							<div class="form-group col-lg-6 p-0">
								<label for="numPalabrasHasta_filter" class="control-label col-md-6 p-0" data-i18n="label.hasta"><spring:message code="label.hasta" />:</label>
								<div class="form-group  col-md-5 p-0">
									<input type="text" id="numPalabrasHasta_filter" name="expedienteTradRev.numPalIzoHasta" class="form-control no-padding-left numeric" maxlength="6" value="">
								</div>
							</div>
						</div>
						
						<div class="col-md-8">
							<label class="control-label col-lg-12 p-0" data-i18n="label.fechaEntregaIZO"><spring:message code="label.fechaEntregaIZO" />:</label>
							<div class="form-group col-lg-5 p-0">
								<label for="fechaEntregaDesde_filter" class="control-label col-md-4 p-0 valFecha" data-i18n="label.desde"><spring:message code="label.desde" />:</label>
								<div class="form-group  col-md-8 p-0 grupoFechaHora grupoFecha">
									<input type="text" id="fechaEntregaDesde_filter" name="expedienteTradRev.fechaEntregaIzoDesde"  class="form-control"  >
								</div>
							</div>
							<div class="form-group col-lg-7 p-0">
								<label for="fechaEntregaHasta_filter" class="control-label col-md-3 p-0 valFecha" data-i18n="label.hasta"><spring:message code="label.hasta" />:</label>
								<div class="form-group  col-md-8 p-0 grupoFechaHora grupoFecha">
									<input type="text" id="fechaEntregaHasta_filter" name="expedienteTradRev.fechaEntregaIzoHasta"  class="form-control" >
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="form-group col-lg-6">
							<fieldset>
								<legend>
									<spring:message code="label.presupuesto"/>
								</legend>
								<div class="form-group col-lg-4">
									<label for="requierePresupuesto_filter_table" class="control-label "><spring:message code="label.requiere"/>:</label>
									<div class="divComboW125">
										<select name="requierePresupuesto" class="form-control" id="requierePresupuesto_filter_table">
											<option value="" selected="true"><spring:message code="combo.todos"/></option>
											<option value="<%=ActivoEnum.SI.getValue()%>"><spring:message code="<%=ActivoEnum.SI.getLabel()%>"/></option>
											<option value="<%=ActivoEnum.NO.getValue()%>"><spring:message code="<%=ActivoEnum.NO.getLabel()%>"/></option>
										</select>
									</div>
								</div>
								<div class="form-group col-lg-8">
									<label for="presupuesto_filter_table" class="control-label "><spring:message code="label.estadoAceptacion"/>:</label>
									<select name="estadoPresupuesto" class="form-control" id="presupuesto_filter_table" onchange="comprobarEstadoPresupuestoCerrado()">
										<option value="" selected="true"><spring:message code="combo.todos"/></option>
										<option value="<%=EstadoRequerimientoEnum.PENDIENTE.getValue()%>"><spring:message code="<%=EstadoRequerimientoEnum.PENDIENTE.getLabel()%>"/></option>
										<option value="<%=EstadoRequerimientoEnum.ACEPTADA.getValue()%>"><spring:message code="<%=EstadoRequerimientoEnum.ACEPTADA.getLabel()%>"/></option>
										<option value="<%=EstadoRequerimientoEnum.RECHAZADA.getValue()%>"><spring:message code="<%=EstadoRequerimientoEnum.RECHAZADA.getLabel()%>"/></option>
									</select>
								</div>
							</fieldset>
						</div>
						<div class="form-group col-lg-6">
							<fieldset>
								<legend>
									<spring:message code="label.negociacionEntrega"/>
								</legend>
								<div class="form-group col-lg-4">
									<label for="requiereNegociacion_filter_table" class="control-label "><spring:message code="label.requiere"/>:</label>
									<div class="divComboW125">
										<select name="requiereNegociacion" class="form-control" id="requiereNegociacion_filter_table">
											<option value="" selected="true"><spring:message code="combo.todos"/></option>
											<option value="<%=ActivoEnum.SI.getValue()%>"><spring:message code="<%=ActivoEnum.SI.getLabel()%>"/></option>
											<option value="<%=ActivoEnum.NO.getValue()%>"><spring:message code="<%=ActivoEnum.NO.getLabel()%>"/></option>
										</select>
									</div>
								</div>
								<div class="form-group col-lg-8">
									<label for="negociacion_filter_table" class="control-label "><spring:message code="label.estadoAceptacion"/>:</label>
									<select name="estadoNegociacion" class="form-control" id="negociacion_filter_table">
										<option value="" selected="true"><spring:message code="combo.todos"/></option>
										<option value="<%=EstadoRequerimientoEnum.PENDIENTE.getValue()%>"><spring:message code="<%=EstadoRequerimientoEnum.PENDIENTE.getLabel()%>"/></option>
										<option value="<%=EstadoRequerimientoEnum.ACEPTADA.getValue()%>"><spring:message code="<%=EstadoRequerimientoEnum.ACEPTADA.getLabel()%>"/></option>
										<option value="<%=EstadoRequerimientoEnum.RECHAZADA.getValue()%>"><spring:message code="<%=EstadoRequerimientoEnum.RECHAZADA.getLabel()%>"/></option>
									</select>
								</div>
							</fieldset>
						</div>
					</div>
					<div class="row">
					<div class="form-group col-lg-12">
						<fieldset>
							<legend>
								<spring:message code="label.tareasPlanificadas"/>
							</legend>
							<div class="row">
								<div class="col-xs-4">
									<label for="tipoTareaBG_detail_table" class="control-label col-xs-12 no-padding-left"><spring:message code="comun.tipoDeTarea"/>:</label>
									<div class="col-xs-12 no-padding-left">
										<select  name="filterTarea.tipoTarea" class="form-control" id="tipoTareaBG_detail_table" ></select>
									</div>	
								</div>
								<div class="col-xs-4">
									<label for="estadoAceptTareaPestBusq_detail_table" class="control-label col-xs-12 no-padding-left"><spring:message code="label.estadoAceptTarea"/>:</label>
									<div class="col-xs-12 no-padding-left">
										<select  name="filterTarea.estadoAceptTarea" class="form-control" id="estadoAceptTareaPestBusq_detail_table" >
											<option value="" selected="true"><spring:message code="combo.todos"/></option>
											<option value="<%=EstadoAceptacionTareaEnum.SIN_TAREAS.getValue()%>"><spring:message code="<%=EstadoAceptacionTareaEnum.SIN_TAREAS.getLabel()%>"/></option>
											<option value="<%=EstadoAceptacionTareaEnum.PENDIENTE_ASIGNACION.getValue()%>"><spring:message code="<%=EstadoAceptacionTareaEnum.PENDIENTE_ASIGNACION.getLabel()%>"/></option>
											<option value="<%=EstadoAceptacionTareaEnum.PENDIENTE_ACEPTACION.getValue()%>"><spring:message code="<%=EstadoAceptacionTareaEnum.PENDIENTE_ACEPTACION.getLabel()%>"/></option>
											<option value="<%=EstadoAceptacionTareaEnum.ACEPTADA.getValue()%>"><spring:message code="<%=EstadoAceptacionTareaEnum.ACEPTADA.getLabel()%>"/></option>
											<option value="<%=EstadoAceptacionTareaEnum.RECHAZADA.getValue()%>"><spring:message code="<%=EstadoAceptacionTareaEnum.RECHAZADA.getLabel()%>"/></option>
										</select>
									</div>	
								</div>
								<div class="col-xs-4">
									<label for="estadoEjecTarea_detail_table" class="control-label col-xs-12 no-padding-left"><spring:message code="label.estadoEjecTarea"/>:</label>
									<div class="col-xs-12 no-padding-left">
										<select  name="filterTarea.estadoEjecTarea" class="form-control" id="estadoEjecTarea_detail_table" >
											<option value="" selected="true"><spring:message code="combo.todos"/></option>
											<option value="<%=EstadoEjecucionTareaEnum.PENDIENTE_EJECUCION.getValue()%>"><spring:message code="<%=EstadoEjecucionTareaEnum.PENDIENTE_EJECUCION.getLabel()%>"/></option>
											<option value="<%=EstadoEjecucionTareaEnum.RETRASADA.getValue()%>"><spring:message code="<%=EstadoEjecucionTareaEnum.RETRASADA.getLabel()%>"/></option>
											<option value="<%=EstadoEjecucionTareaEnum.EJECUTADA.getValue()%>"><spring:message code="<%=EstadoEjecucionTareaEnum.EJECUTADA.getLabel()%>"/></option>
										</select>
									</div>	
								</div>
							</div>
							<div class="row">
								<div class="col-md-8">
									<label class="control-label col-lg-12 p-0" data-i18n="comun.fechaPlanificacionTarea"><spring:message code="comun.fechaPlanificacionTarea" />:</label>
									<div class="form-group col-lg-5 p-0">
										<label for="fechaPlanTareaDesde_filter" class="control-label col-md-4 p-0 valFecha" data-i18n="label.desde"><spring:message code="label.desde" />:</label>
										<div class="form-group  col-md-8 p-0 grupoFechaHora grupoFecha">
											<input type="text" id="fechaPlanTareaDesde_filter" name="filterTarea.fechaDesdePlanifTarea"  class="form-control"  >
										</div>
									</div>
									<div class="form-group col-lg-7 p-0">
										<label for="fechaPlanTareaHasta_filter" class="control-label col-md-3 p-0 valFecha" data-i18n="label.hasta"><spring:message code="label.hasta" />:</label>
										<div class="form-group  col-md-9 p-0 grupoFechaHora grupoFecha">
											<input type="text" id="fechaPlanTareaHasta_filter" name="filterTarea.fechaHastaPlanifTarea"  class="form-control" >
										</div>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-md-5">
									<label for="asignador_IZO_filter_table" class="control-label col-xs-12 no-padding-left"><spring:message code="label.personalIzoAsignadoTarea" />:</label>
									<div class="col-xs-12 no-padding-left">
										<select  name="filterTarea.persIzoAsignTarea" class="form-control" id="asignador_IZO_filter_table" ></select>
									</div>	
								</div>
								<div class="col-md-4">
									<label for="loteAsignado_detail_table" class="control-label col-xs-12 no-padding-left"><spring:message code="label.loteAsignado" />:</label>
									<div class="col-xs-12 no-padding-left">
										<select  name="filterTarea.idLoteAsignTarea" class="form-control" id="loteAsignado_detail_table" ></select>
									</div>	
								</div>
							</div>
						</fieldset>
						</div>
					</div>
					<div id="busquedaGeneral_filter_buttonSet" class="pull-right" style="margin:0 1rem 1rem 0">
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
		<br/>
		<div class="row form-group" >
			<div class="col-xs-5" id="leyendaUrgente">
				<div class="col-xs-1">
					<div class="boxUrgente"></div>
				</div>
				<label class="control-label col-xs-11">
					<spring:message code="label.urgenteIconDesc"/>
				</label>
			</div>
			<div class="col-xs-5">
				<div class="col-xs-1">
					<i class="fa fa-circle" aria-hidden="true"></i>
				</div>
				<label class="control-label col-xs-11">
					<spring:message code="label.sinTareas"/>
				</label>
			</div>
			
		</div>
		 <div class="row form-group" >
			<div class="col-xs-5" id="leyendaFechaRechazada">
				<div class="col-xs-1">
					<div class="boxFechaRechazada"></div>
				</div>
				<label class="control-label col-xs-11">
					<spring:message code="label.fechaRechazadaIconDesc"/>
				</label>
			</div>
			<div class="col-xs-5">
				<div class="col-xs-1">
					<i class="fa fa-minus-circle" aria-hidden="true"></i>
				</div>
				<label class="control-label col-xs-11">
					<spring:message code="comun.estadoPendienteAsignacion"/>
				</label>
			</div>
		</div>
		<div class="row form-group" >
			<div class="col-xs-5" id="leyendaPrioridad">
				<div class="col-xs-1">
					<i class="fa fa-star" aria-hidden="true" style="color: #ba1944;"></i>
				</div>
				<label class="control-label col-xs-11">
					<spring:message code="label.prioridadIconDesc"/>
				</label>
			</div>
			<div class="col-xs-5">
				<div class="col-xs-1">
					<i class="fa fa-exclamation-circle" aria-hidden="true"></i>
				</div>
				<label class="control-label col-xs-11">
					<spring:message code="comun.estadoPendienteAceptacion"/>
				</label>
			</div>
		</div>
		 <div class="row form-group">
			<div class="col-xs-5" id="leyendaPlanificacion">
				<div class="col-xs-1">
					<i class="fa fa-exclamation-triangle" aria-hidden="true"></i>
				</div>
				<label class="control-label col-xs-11">
					<spring:message code="label.planifSinTareaIconDesc"/>
				</label>
			</div>
			<div class="col-xs-5">
				<div class="col-xs-1">
					<i class="fa fa-times-circle" aria-hidden="true"></i>
				</div>
				<label class="control-label col-xs-11">
					<spring:message code="comun.estadoRechazada"/>
				</label>
			</div>
		</div> 
		
		<div class="row form-group" >
			<div class="col-xs-5" id="leyendaVacia">
				
			</div>
			<div class="col-xs-5">
				<div class="col-xs-1">
					<i class="fa fa-check-circle" aria-hidden="true"></i>
				</div>
				<label class="control-label col-xs-11">
					<spring:message code="comun.aceptado"/>
				</label>
			</div>
		</div>
	</div>
</div>
