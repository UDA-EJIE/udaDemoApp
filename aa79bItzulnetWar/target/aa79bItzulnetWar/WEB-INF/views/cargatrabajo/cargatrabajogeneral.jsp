<%@include file="/WEB-INF/includeTemplate.inc"%>
<%@page import="com.ejie.aa79b.model.enums.EstadoAceptacionTareaEnum"%>
<%@page import="com.ejie.aa79b.model.enums.EstadoEjecucionTareaEnum"%>
<div id="divCargaTrabajoGeneral" class="container-fluid">
	<div id="divCargaTrabajo">
		<h2><spring:message code="menu.cargaTrabajo"></spring:message></h2>
		<hr class="mb-2">
		<div class="rup-table-container">
			<div id="busquedaGeneral_feedback"></div>						<!-- Feedback de la tabla --> 
			<div id="busquedaGeneral_toolbar"></div>	
			<div id="capa">
				<div id="contenFormularios" class="filterForm">
					<!-- HABRÁ QUE CAMBIAR LOS IDS, Y LO NECESARIO PARA LA BÚSQUEDA -->
					<form id="busquedaGeneral_filter_form" class="form-horizontal">
						<div id="busquedaGeneral_filter_toolbar"
							class="formulario_legend filterCabecera"></div>
						<fieldset id="busquedaGeneral_filter_fieldset"
							class="rup-table-filter-fieldset filterCuerpo">
							<div id="fieldFormu" class="p-2">
								<input type="hidden" name="esAsignador" id="esAsignador" >
								<fieldset id="filtrosAsignador">
										<legend><spring:message 
		 											code="label.trabajador" /></legend>
								<div class="row">
									<div class="form-group col-lg-12">
										
										<div class="col-xs-3">
											<label for="grupoTrabajo_combo"
												class="control-label col-xs-12 no-padding-left"><spring:message
													code="label.grupoTrabajo" /></label>
											<div class="col-xs-12 no-padding-left">
												<select name="idGrupo" class="form-control"
													id="grupoTrabajo_combo"></select>
											</div>
										</div>
										<div class="col-xl-4 col-lg-5 col-sm-4">
											<label for="recursosSinTareas_combo"
												class="control-label col-xs-12 no-padding-left"><spring:message
													code="label.recursosSinTareas" /></label>
											<div class="col-xs-12 no-padding-left">
												<input type="checkbox" name="tarea.recursoAsignacion"
													id="recursosSinTareas_combo" value="S">
											</div>
										</div>
		 								<div class="col-xs-3"> 
		 									<label for="trabajador_combo" 
		 										class="control-label col-xs-12 no-padding-left"><spring:message 
		 											code="label.trabajador" /></label> 
		 									<div class="col-xs-12 no-padding-left"> 
		 										<select name="filtroDatos" class="form-control" 
		 											id="trabajador_combo"></select> 
		 									</div> 
		 								</div> 
									</div>
									
								</div>
								<div class="row form-group">
									<div class="col-xs-12">
											<i class="fa fa-exclamation-triangle" aria-hidden="true"></i>
											<label class="control-label">
											<spring:message 
		 											code="label.filtroTareaAsignada" />
											</label>
									</div>
								</div>
								</fieldset>
								<fieldset>
										<legend><spring:message 
		 											code="comun.tareaAsignada" /></legend>
								<div class="row">
									<div class="form-group col-lg-12">
										
										<div class="col-xs-6">
											<label for="tipoTarea_detail_table"
												class="control-label col-xs-12 no-padding-left"><spring:message
													code="comun.tipoDeTarea" />:</label>
											<div class="col-xs-12 no-padding-left">
												<select name="tarea.tipoTarea.id015" class="form-control"
													id="tipoTareaCargaTrabajo"></select>
											</div>
										</div>
										<div class="col-xs-3">
											<label for="estadoAceptTarea_detail_table"
												class="control-label col-xs-12 no-padding-left"><spring:message
													code="label.estadoAceptTarea" />:</label>
											<div class="col-xs-12 no-padding-left">
												<select name="tarea.estadoAsignado" class="form-control"
													id="estadoAceptTarea_detail_table">
													<option value=""><spring:message code="combo.todos" /></option>
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
												<select name="tarea.estadoEjecucion" class="form-control"
													id="estadoEjecTarea_detail_table">
													<option value="" selected="true"><spring:message
															code="combo.todos" /></option>
													<option
														value="<%=EstadoEjecucionTareaEnum.PENDIENTE_EJECUCION.getValue()%>"><spring:message
															code="<%=EstadoEjecucionTareaEnum.PENDIENTE_EJECUCION.getLabel()%>" /></option>
													<option
														value="<%=EstadoEjecucionTareaEnum.RETRASADA.getValue()%>"><spring:message
															code="<%=EstadoEjecucionTareaEnum.RETRASADA.getLabel()%>" /></option>
												</select>
											</div>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="form-group col-lg-12">
										<div class="col-md-6">
											<label class="control-label col-lg-12 p-0"
												data-i18n="label.fechaEntregaIZO"><spring:message
															code="label.fechaFinTarea" /></label>
											<div class="form-group col-lg-6 p-0">
												<label for="fechaFinTareaDesde"
													class="control-label col-md-3 p-0" data-i18n="label.desde"><spring:message
														code="label.desde" />:</label>
												<div class="form-group  col-md-8 p-0">
													<input type="text" id="fechaFinTareaDesde" name="tarea.fechaIni"
														class="form-control">
												</div>
											</div>
											<div class="form-group col-lg-6 p-0">
												<label for="fechaFinTareaHasta"
													class="control-label col-md-3 p-0" data-i18n="label.hasta"><spring:message
														code="label.hasta" />:</label>
												<div class="form-group  col-md-8 p-0">
													<input type="text" id="fechaFinTareaHasta" name="tarea.fechaFin"
														class="form-control">
												</div>
											</div>
										</div>
									</div>
								</div>
								</fieldset>
								<br/>
								<div id="cargaTrabajoBusqueda_filter_buttonSet" class="pull-right"
									style="margin: 0 1rem 1rem 0">
									<button id="cargaTrabajoBusqueda_filter_filterButton"
										type="button"
										class="ui-button ui-widget ui-state-default ui-corner-all">
										<spring:message code="filter" />
									</button>
									<a id="cargaTrabajoBusqueda_filter_cleanLink"
										href="javascript:void(0)" class="rup-enlaceCancelar"><spring:message
											code="clear" /></a>
								</div>
							</div>
						</fieldset>
					</form>
				</div>
			</div>
			<br />
			
			<div id="tabsCargaTrabajo">
			
			<div id="avisoTareasPendientes" class="col-xs-12 warning-message-label-container oculto">
							<i class="fa fa-exclamation-triangle" aria-hidden="true"></i>
							<spring:message code="mensaje.tareasPendientes" />
							<span id="tradRevPendiente"><spring:message code="mensaje.tareasPendientesTradrev"/></span><span id="comaTareasPendiente">,</span><span id="yTareasPendiente">&nbsp;<spring:message code="comun.y"/></span>
							<span id="interPendiente"><spring:message code="mensaje.tareasPendientesInterpretacion"/></span><span id="yTareasPendiente2">&nbsp;<spring:message code="comun.y"/></span>
							<span id="trabajosPendiente"><spring:message code="mensaje.tareasPendientesTrabajos"/></span>
			</div>
			
			
			</div>
			
			
			<div class="row">
			<div class="col-xs-4" style="padding-left: 25px;">
				<div class="col-xs-1">
					<i class="fa  fa-exclamation-triangle iconoAvisoPestana" aria-hidden="true"></i>
				</div>
				<label class="control-label col-xs-10">
					<spring:message code="label.conTareasPendientes" />
				</label>
			</div>
		</div>
		</div>
		
		<div id="rechazarAsignacion_dialog" class="rup-table-formEdit-detail">
			<div id="rechazarAsignacion_detail_navigation"></div>
			<!-- Barra de navegación del detalle -->
			<div class="ui-dialog-content ui-widget-content">
				<form id="rechazarAsignacion_form">
					<div class="row" style="padding-left:5px;">
						<label for="motivoRechazo_detail_table" class="control-label col-xs-12 no-padding-left"><spring:message code="label.deseaRechazarAsignacion"/>:</label>
						<div class="col-xs-12 no-padding-left">
							<textarea class="form-control resizable-textarea" id="motivoRechazo_detail_table" name="motivoRechazo_detail_table" rows="5" cols="9" ></textarea>
						</div>	
					</div>
				</form>
			</div>
		</div>
	</div>
</div>

<div id="capaTareasTradRev">
	<%@include file="/WEB-INF/views/cargatrabajo/cargatrabajotareatradrev.jsp"%>
</div>
<div id="capaTareasInterpretacion">
	<%@include file="/WEB-INF/views/cargatrabajo/cargatrabajotareainterpretacion.jsp"%>
</div>
<div id="capaTareasTrabajo">
	<%@include file="/WEB-INF/views/cargatrabajo/cargatrabajotareatrabajo.jsp"%>
</div>



