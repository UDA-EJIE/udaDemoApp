<%@include file="/WEB-INF/includeTemplate.inc"%>
<div class="aa79b-fade in" id=divTareasExpedientes>
	<h2><spring:message code="menu.detalleTarea"></spring:message></h2>
	<div class="rup-table-container">
		<div id="tareasExpedientes_feedback"></div>			
		<div id="detalleTarea_form_toolbar"></div>	
		<div class="contentMargin">
			<%@include file="/WEB-INF/views/cabeceradetalletarea/cabeceradetalletarea.jsp"%>
		</div>
		<div id="detalleCargaTrabajo_filter_div" class="filterForm">
			<form>
				<fieldset id="detalleTarea_form_div_fieldset">
					    <div class="p-2">
						<div class="form-group row">
									<label for="tipoTarea_detail_table"
										class="control-label col-xs-2"><spring:message
													code="comun.tipoDeTarea" />:</label>  
									<div class="col-xs-3">
											<c:if test="${pageContext.response.locale eq LANGUAGE_ES}"> 
					              				<label id="tipoTarea_detail_table" class="control-label"></label> 
					            			</c:if> 
											<c:if test="${pageContext.response.locale eq LANGUAGE_EU}"> 
												<label id="tipoTarea_detail_table" class="control-label"></label> 
					            			</c:if> 
										
									</div> 
									<div id="modoInterDiv"> 
										<label for="modoInterpretacion_detail_table" 
											class="control-label col-xs-2 "><spring:message 
												code="label.modoInterpretacion" />:</label> 
										<div class="col-xs-5">
											<label id="modoInterpretacion_detail_table" class="control-label"></label> 
										</div>
									</div>
									<div id="numPalTradosDiv"> 
										<label for="numPalTrados_detail_table" 
											class="control-label col-xs-2 "><spring:message 
												code="comun.numPalabrasIZO" />:</label> 
										<div class="col-xs-5" id="numPalTrados_detail_table">
										</div>
									</div>
						</div> 
						<div id="tipoRevisionDiv" class="form-group row"> 
							<label for="tipoRevision_detail_table" 
								class="control-label col-xs-2 "><spring:message 
									code="comun.tipoDeRevision" />:</label> 
							<div class="col-xs-5">
								<label id="tipoRevision_detail_table" class="control-label"></label> 
							</div>
						</div> 
						<div class="form-group row"> 
								<label for="fechaHoraIni_detail_table" 
									class="control-label col-xs-3 col-xl-2"><spring:message 
										code="comun.fechaHoraInicioTarea" />:</label> 
								<div class="col-xs-3">
									<label id="fechaHoraIni_detail_table" class="control-label"></label> 
								</div>
						
								<label for="fechaHoraFin_detail_table" 
											class="control-label col-xs-2"><spring:message 
												code="comun.fechaHoraFinTarea" />:</label> 
									<div class="col-xs-2">
											<label id="fechaHoraFin_detail_table" class="control-label"></label> 	
										</div>				 
						</div> 
						<div class="form-group row"> 
								<label for="horaPrevista_detail_table" 
									class="control-label col-xs-2 "><spring:message 
										code="label.horaPrevista" />:</label> 
								<div class="col-xs-2 ">
									<label id="horaPrevista_detail_table" class="control-label"></label> 	
								</div>					 
						</div> 
						<div class="form-group row"> 
							<label for="asignada_detail_table" 
								class="control-label col-xs-2 "><spring:message 
									code="comun.asignadaA" />:</label> 
								<div class="col-xs-3 ">
									<label id="asignada_detail_table" class="control-label"></label> 	
								</div>
							<label for="grupoTrabajo_detail_table" 
								class="control-label col-xs-2 "><spring:message 
									code="comun.grupoTrabajo" />:</label> 
								<div class="col-xs-4 ">
									<label id="grupoTrabajo_detail_table" class="control-label"></label> 	
								</div>
						</div> 
						<div class="form-group row"> 
							<label for="asignador_detail_table" 
								class="control-label col-xs-2 "><spring:message 
									code="label.asignador" />:</label> 
							<div class="col-xs-10 ">
								<label id="asignador_detail_table" class="control-label"></label> 	
							</div>
						</div>							 
						<div class="form-group row"> 
							<label for="estadoAceptacion_detail_table" 
								class="control-label col-xs-2 "><spring:message 
									code="label.estadoAceptacion" />:</label> 
							<div class="col-xs-3 "> 
								<label id="estadoAceptacion_detail_table" class="control-label"></label> 	
							</div>  
							<label for="motivoRechazo_detail_table" 
								class="control-label col-xs-2 " id="motivoRechazoLabel" style="display: none;"><spring:message 
									code="label.motivoRechazo" />:</label> 
							<div class="col-xs-3 " id="motivoRechazoDiv" style="display: none;"> 
								<label id="motivoRechazo_detail_table" class="control-label"></label> 	
							</div> 			 
						</div> 
						<div class="form-group row"> 
							<label for="estadoEjecucion_detail_table" 
								class="control-label col-xs-2 "><spring:message 
									code="label.estadoEjecucion" />:</label> 
							<div class="col-xs-6 "> 
								<label id="estadoEjecucion_detail_table" class="control-label"></label> 	
							</div> 
						</div> 
						<div class="form-group row"> 
							<label class="colon control-label " for="docuTarea_filter_fieldset_detail_table"><spring:message code="label.documentacionTarea" />:</label>&nbsp;  
							<a href="#" id="descargarTodo_link" class="descargarDocumentos"><spring:message code="label.descargarTodos" /></a>
							<div class="form-control" id="listaDocsDiv" style="min-height: 80px;background-color: transparent;height: auto;"> 
								<div class="document-content">
									
								</div>
							</div> 
						</div>
						<div class="form-group row"> 
							<div class="col-md-9">
								<label for="observacionesTarea_filter_fieldset_detail_table" 
											class="control-label"><spring:message 
												code="label.observacionesTarea" />:</label> 
								<div class="form-control" id="observacionesDiv" style="max-height: 80px;background-color: transparent;height: auto;overflow-y: auto;overflow-x: hidden;"> 
									<p class="resizable-textarea" id="observaciones_detail_table" style="background-color: transparent;  white-space: pre-line"></p>
								</div> 
							</div>
							<div class="col-md-3">
								<a id="notasExpediente_button" href="javascript:void(0)" class="control-label" style="background-color:#B0FF57"><spring:message 
									code="label.notasExpediente" /></a>
							</div>
						</div> 
						<div id="divSeccionObservaciones" class="form-group row">
							<div class="col-md-9">
								<label class="control-label" for="observacionesExpTradRev">
									<spring:message code="label.observacionesSolicitud"/>:
								</label>
							</div>
							<div class="col-md-3">
								<div id="observaciones_mostrarLink_div" class="inline">
									<a id="observaciones_mostrarLink" href="#" class="control-label" style="background-color:#B0FF57"><spring:message code="label.observacionesSolicitud" /></a>
								</div>
							</div>
						</div>
						<div id="divDetalleObservaciones">
							<div class="row">
								<div class="form-group col-lg-12">
									<span id="enlaceDescargaDetalle_2" style="display:contents;"></span> 
									<div id="capaBtnPID" class="inline">
										<button id="pidButton_2" type="button" class="ui-button ui-corner-all ui-widget no-obligatorio" style="margin-top:-4px; height:22px"><spring:message code="label.adjuntarFichero"/></button>
										<input type="text" name="expedienteTradRev.observaciones.nombreUpload" class="form-control" id="nombreFicheroInfo_2" readonly="readonly" style="width:200px; display:inline-block"/>
									</div>
									<div style="display:inline-flex"><button id="btnEliminarObserv" type="button" class="rup-enlaceCancelar oculto"><spring:message code="eliminar" /></button></div>
								</div>
								<input type="hidden" name="expedienteTradRev.observaciones.rutaPif" id="rutaPif_2"/>
								<input type="hidden" name="expedienteTradRev.observaciones.nombre" id="nombre_2" readonly="readonly"/>
								<input type="hidden" name="expedienteTradRev.observaciones.oidFichero" id="oidFichero056_2" readonly="readonly"/>
								<input type="hidden" name="expedienteTradRev.observaciones.extension" id="extensionDoc056_2" readonly="readonly"/>
								<input type="hidden" name="expedienteTradRev.observaciones.contentType" id="contentType056_2" readonly="readonly"/>
								<input type="hidden" name="expedienteTradRev.observaciones.tamano" id="tamanoDoc056_2" readonly="readonly"/>
							</div>
							<div class="row">
								<div class="form-group col-lg-12">
									<textarea name="expedienteTradRev.observaciones.observaciones" class="form-control resizable-textarea" id="observacionesExpTradRev" rows="3" cols="9" maxlength="4000" ></textarea>
								</div>
							</div>
						</div>
					</div>
				</fieldset> 
			</form>	
			<div id="divEntidadContactoDatos">
				<fieldset id="contactofacturacionexp_filter_fieldset" class="rup-table-filter-fieldset" >
					<div class="p-2">
						<label class="colon" for="docuTarea_filter_fieldset_detail_table"><spring:message code="label.tareasDepende" />:</label>
						<div id="detalleTarea_div" class="rup-table-container container-fluid">  
							<div id="detalleTarea_feedback"></div>					
							<div id="detalleTarea_toolbar"></div>						
							<div id="detalleTarea_filter_div" class="rup-table-filter"> 
								<form id="detalleTarea_filter_form">						
									<fieldset id="detalleTarea_filter_fieldset" class="rup-table-filter-fieldset">
										<div class="p-2">
										<input id="anyo" type="hidden" name="tarea.anyo"></input>
										<input id="numExp" type="hidden" name="tarea.numExp"></input>
										<input id="orden" type="hidden" name="tarea.orden"></input>
										<input id="idTipoTarea" type="hidden" name="tarea.idTipoTarea"></input>
										<input id="idTarea" type="hidden" name="tarea.idTarea"></input>
										<input id="dniAsignada" type="hidden" name="tarea.personaAsignada.dni"></input>
										<input id="estadoAsignadaId" type="hidden" name="tarea.estadoAsignado"></input>
										<input id="estadoEjecId" type="hidden" name="tarea.estadoEjecucion"></input>
					    				<div id="detalleTarea_filter_toolbar" class="formulario_legend"></div>
					    				</div>	
									</fieldset>
								</form>
							</div>
							<div class="detalleTarea_grid_div horizontal_scrollable_table" >
								<table id="detalleTarea" style="margin-left:0px !important;margin-right:0px !important;"></table>
								<div id="detalleTarea_pager"></div>
							</div>
						</div>	
					</div>
				</fieldset>
			</div>
		</div>
	</div>	
	<div id="crearConfigurarDialog" style="display:none"></div>
	
	<div id="ejecutarTareaDialog" style="display:none"></div>
	
	<div id="confirmartarea" style="display:none"></div>	
	
</div>
<div id="rechazarAsignacionDetalle_dialog" class="rup-table-formEdit-detail">
	<div id="rechazarAsignacion_detail_navigation"></div>
	<!-- Barra de navegación del detalle -->
	<div class="ui-dialog-content ui-widget-content">
		<form id="rechazarAsignacionDetalle_form">
			<div class="row" style="padding-left:5px;">
				<label for="motivoRechazoDetalle_detail_table" class="control-label col-xs-12 no-padding-left"><spring:message code="label.deseaRechazarAsignacion"/>:</label>
				<div class="col-xs-12 no-padding-left">
					<textarea class="form-control resizable-textarea" id="motivoRechazoDetalle_detail_table" name="motivoRechazoDetalle_detail_table" rows="5" cols="9" ></textarea>
				</div>	
			</div>
		</form>
	</div>
</div>	



