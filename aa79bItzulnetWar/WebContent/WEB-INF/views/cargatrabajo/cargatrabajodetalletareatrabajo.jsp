<%@include file="/WEB-INF/includeTemplate.inc"%>
<%@page import="com.ejie.aa79b.model.enums.EstadoAceptacionTareaEnum"%>
<%@page import="com.ejie.aa79b.model.enums.EstadoEjecucionTareaEnum"%>
<c:set var="ASIGRECHAZADA" value="<%=EstadoAceptacionTareaEnum.RECHAZADA.getValue()%>"/>
<c:set var="TAREJECUTADA" value="<%=EstadoEjecucionTareaEnum.EJECUTADA.getValue()%>"/>
<div class="aa79b-fade in" id=divTareasExpedientes>
	<h2><spring:message code="menu.detalleTarea"></spring:message></h2>
	<div class="rup-table-container">
		<div id="detalleTareaTrabajo_feedback"></div>			
		<div id="detalleTareaTrabajo_form_toolbar"></div>	
		<div class="contentMargin">
			<%@include file="/WEB-INF/views/cabeceradetalletareatrabajo/cabeceradetalletareatrabajo.jsp"%>
		</div>
		<div id="detalleCargaTrabajo_filter_div" class="filterForm">
			<form:form id="formDetalleTareaTrabajo" modelAttribute="tareaTrabajo">
				<form:input type="hidden" id="detTareaTrabajoDescTrabajo" path="trabajo.descTrabajo"/>
				<form:input type="hidden" id="detTareaTrabajoIdTrabajo" path="trabajo.idTrabajo" />
				<form:input type="hidden" id="detTareaTrabajoFechaFinPrevista" path="trabajo.fechaHoraFinPrevista" />
				<form:input type="hidden" id="detTareaTrabajoIdTarea" path="idTarea" />
				<form:input type="hidden" id="detTareaTrabajoIdTipoTarea" path="tipoTarea.id015" />
				<form:input type="hidden" id="detTareaTrabajoEstadoAsignacion" path="estadoAsignado" />
				<form:input type="hidden" id="detTareaTrabajoEstadoEjecucion" path="estadoEjecucion" />
				<form:input type="hidden" id="detTareaTrabajoDniAsignada" path="personaAsignada.dni" />
				<form:input type="hidden" id="detTareaTrabajoDocEntradaTareaIdFichero" path="documentoEntrada.idFichero" />
				<form:input type="hidden" id="detTareaTrabajoDocEntradaTareaEncriptadoFichero" path="documentoEntrada.encriptado" />
				<form:input type="hidden" id="detTareaTrabajoDocEntradaTareaNombreFichero" path="documentoEntrada.nombre" />
				<form:input type="hidden" id="detTareaTrabajoDocSalidaTareaIdFichero" path="documentoSalida.idFichero" />
				<form:input type="hidden" id="detTareaTrabajoDocSalidaTareaEncriptadoFichero" path="documentoSalida.encriptado" />
				<form:input type="hidden" id="detTareaTrabajoDocSalidaTareaNombreFichero" path="documentoSalida.nombre" />
				<fieldset id="detalleTarea_form_div_fieldset">
				    <div class="p-2"></div>
				    <div class="row">
					    <div class="form-group col-lg-12 ">
							<label for="ordenTarea_detail_table"	class="control-label label-modo-consulta">
								<spring:message	code="label.ordenTarea" />:
							</label>
							<span class="span-modo-consulta" >
								<form:input id="ordenTarea_detail_table" type="text" path="orden"/>
							</span>
						</div> 
				    </div>
				    <div class="row">
					    <div class="form-group col-lg-12 ">
							<label for="tipoTarea_detail_table"	class="control-label label-modo-consulta">
								<spring:message	code="comun.tipoDeTarea" />:
							</label>
							<span class="span-modo-consulta" >
								<c:if test="${pageContext.response.locale eq LANGUAGE_ES}"> 
		              				<form:input id="tipoTarea_detail_table" type="text" path="tipoTarea.descEs015"/>
		            			</c:if> 
								<c:if test="${pageContext.response.locale eq LANGUAGE_EU}"> 
									<form:input id="tipoTarea_detail_table" type="text" path="tipoTarea.descEu015"/>
		            			</c:if> 
							</span>
						</div> 
				    </div>
				    <div class="form-group row pl10 pr6">
				    	<label for="observ_detail_table" class="control-label" data-i18n="label.observacionesTarea">
							<spring:message code="label.observacionesTarea" />:
						</label>
						<div class="form-control" id="listaDocsDiv" style="min-height: 80px;background-color: transparent;height: auto;"> 
							<div class="documento-all-tarea" style="background-color:transparent !important;">
								<div class="documento-first docTarea" style="border-radius: inherit;">
									<span class="span-modo-consulta">
										<form:textarea id="observ_detail_table" class="form-control" type="text" path="observaciones" cols="1" rows="6" disabled="disabled" />
									</span>
								</div>
							</div>
						</div> 
				    </div>
				    <div class="row">
				    	<div class="form-group col-lg-4">
					    	<label for="fechaInicio_detail_table" class="control-label label-modo-consulta" data-i18n="comun.fechaHoraInicio">
					    		<spring:message code="comun.fechaHoraInicio" />:
				    		</label>
				    		<span class="span-modo-consulta">
				    			<form:input id="fechaInicio_detail_table" type="text" path="fechaHoraInicio"/>
				    		</span>
				    	</div>
				    	<div class="form-group col-lg-4">
					    	<label for="fechaFin_detail_table" class="control-label label-modo-consulta" data-i18n="comun.fechaHoraFin">
					    		<spring:message code="comun.fechaHoraFin" />:
				    		</label>
				    		<span class="span-modo-consulta">
				    			<form:input id="fechaFin_detail_table" type="text" path="fechaHoraFin"/>
				    		</span>
				    	</div>
				    </div>
				    <div class="row">
				    	<div class="form-group col-lg-8">
					    	<label for="asignada_detail_table" class="control-label label-modo-consulta" data-i18n="comun.asignadaA">
					    		<spring:message code="comun.asignadaA" />:
				    		</label>
				    		<span class="span-modo-consulta">
				    			<form:input id="asignada_detail_table" type="text" path="personaAsignada.nombreCompleto"/>
				    		</span>
				    	</div>
				    </div>
				    <div class="row">
				    	<div class="form-group col-lg-8">
					    	<label for="asignador_detail_table" class="control-label label-modo-consulta" data-i18n="label.asignador">
					    		<spring:message code="label.asignador" />:
				    		</label>
				    		<span class="span-modo-consulta">
				    			<form:input id="asignador_detail_table" type="text" path="personaAsignador.nombreCompleto"/>
				    		</span>
				    	</div>
				    </div>
				    <div class="row"> 
				    	<div class="form-group col-lg-8">
					    	<label for="estadoAceptacion_detail_table" class="control-label label-modo-consulta">
					    		<spring:message	code="label.estadoAceptacion" />:
					    	</label> 
							<span class="span-modo-consulta"> 
								<form:input id="estadoAceptacion_detail_table" type="text" path="estadoAsignadoDesc"/> 	
							</span>  
				    	</div>
					</div>
					<div id="motivoRechazoDiv" class="${tareaTrabajo.estadoAsignado eq ASIGRECHAZADA ? 'claseNoUtilizada' : 'oculto'}">
						<div class="form-group row pl10 pr6">
					    	<label for="motivoRechazo_detail_table" class="control-label" data-i18n="label.motivoRechazo">
								<spring:message code="label.motivoRechazo" />:
							</label>
							<div class="form-control"  style="min-height: 80px;background-color: transparent;height: auto;"> 
								<div class="documento-all-tarea" style="background-color:transparent !important;">
									<div class="documento-first docTarea" style="border-radius: inherit;">
										<span class="span-modo-consulta">
											<form:textarea id="motivoRechazo_detail_table" class="form-control" type="text" path="obsvrechazo" cols="1" rows="6" disabled="disabled" />
										</span>
									</div>
								</div>
							</div> 
					    </div>
					</div>
					<div class="row">
						<div class="form-group col-lg-8"> 
							<label for="estadoEjecucion_detail_table" class="control-label label-modo-consulta">
								<spring:message	code="label.estadoEjecucion" />:
							</label> 
							<span class="span-modo-consulta">
								<form:input id="estadoEjecucion_detail_table" type="text" path="estadoEjecucionDesc"/> 
							</span> 
						</div>  
					</div>
					<div class="row">
				    	<div class="form-group col-lg-8">
					    	<label for="documentoTareaEntrada_detail_table" class="control-label label-modo-consulta" data-i18n="label.documentacionTarea">
					    		<spring:message code="label.documento.tarea.entrada" />:
				    		</label>
				    		<span class="span-modo-consulta">
				    			<span id ="documentoTareaEntrada_detail_table" class="document-content inline">
								</span>
				    		</span> 
				    	</div>
			    	</div>
					<div id="datosEjecTareaTrabajoDiv" class="${tareaTrabajo.estadoEjecucion eq TAREJECUTADA ? 'claseNoUtilizada' : 'oculto'}">
						<div class="form-group row pl10 pr6">
					    	<label for="observEjecucion_detail_table" class="control-label" data-i18n="label.motivoRechazo">
								<spring:message code="label.observacionesTarea" />:
							</label>
							<div class="form-control" id="motivoRechazoDiv" style="min-height: 80px;background-color: transparent;height: auto;"> 
								<div class="documento-all-tarea" style="background-color:transparent !important;">
									<div class="documento-first docTarea" style="border-radius: inherit;">
										<span class="span-modo-consulta">
											<form:textarea id="observEjecucion_detail_table" class="form-control" type="text" path="observacionesTareas.obsvEjecucion" cols="1" rows="6" disabled="disabled" />
										</span>
									</div>
								</div>
							</div> 
					    </div>
					    <div class="row">
					    	<div class="form-group col-lg-8">
						    	<label for="documentoTareaSalida_detail_table" class="control-label label-modo-consulta" data-i18n="label.documentacionTarea">
						    		<spring:message code="label.documento.tarea.salida" />:
					    		</label>
					    		<span class="span-modo-consulta">
					    			<span id ="documentoTareaSalida_detail_table" class="document-content inline">
									</span>
					    		</span> 
					    	</div>
				    	</div>
					</div>
				   
				</fieldset> 
			</form:form>
			<div id="divEntidadContactoDatos">
				<fieldset id="contactofacturacionexp_filter_fieldset" class="rup-table-filter-fieldset" >
					<div class="p-2">
						<label class="colon pl10" for="docuTarea_filter_fieldset_detail_table"><spring:message code="label.tareasDepende" />:</label>
						<div id="detalleTareaTrabajo_div" class="rup-table-container container-fluid">  
							<div id="detalleTareaTrabajo_feedback"></div>					
							<div id="detalleTareaTrabajo_toolbar"></div>						
							<div id="detalleTareaTrabajo_filter_div" class="rup-table-filter oculto"> 
								<form id="detalleTareaTrabajo_filter_form">						
									<fieldset id="detalleTareaTrabajo_filter_fieldset" class="rup-table-filter-fieldset">
										<div class="p-2">
										<input id="idTarea_filter_table" type="hidden" name="idTarea"></input>
										<input id="idTrabajo_filter_table" type="hidden" name="trabajo.idTrabajo"></input>
										<input id="orden_filter_table" type="hidden" name="orden"></input>
					    				<div id="detalleTareaTrabajo_filter_toolbar" class="formulario_legend"></div>
					    				</div>	
									</fieldset>
								</form>
							</div>
							<div class="detalleTareaTrabajo_grid_div horizontal_scrollable_table" >
								<table id="detalleTareaTrabajo" style="margin-left:0px !important;margin-right:0px !important;"></table>
								<div id="detalleTareaTrabajo_pager"></div>
							</div>
						</div>	
					</div>
				</fieldset>
			</div>
		</div>
	</div>	
	<div id="crearConfigurarDialog" style="display:none"></div>
	
	<div id="ejecutarTareaTrabajoDialog" style="display:none"></div>
	
	<div id="confirmartarea" style="display:none"></div>	
	
</div>

<div id="rechazarAsignacionDetalleTareaTrabajo_dialog" class="rup-table-formEdit-detail">
	<div id="rechazarAsignacionTareaTrabajo_detail_navigation"></div>
	<!-- Barra de navegación del detalle -->
	<div class="ui-dialog-content ui-widget-content">
		<form id="rechazarAsignacionDetalleTareaTrabajo_form">
			<div class="row" style="padding-left:5px;">
				<label for="motivoRechazoDetalleTareaTrabajo_detail_table" class="control-label col-xs-12 no-padding-left"><spring:message code="label.deseaRechazarAsignacion"/>:</label>
				<div class="col-xs-12 no-padding-left">
					<textarea class="form-control resizable-textarea" id="motivoRechazoDetalleTareaTrabajo_detail_table" name="motivoRechazoDetalle_detail_table" rows="5" cols="9" ></textarea>
				</div>	
			</div>
		</form>
	</div>
</div>	



