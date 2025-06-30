<%@include file="/WEB-INF/includeTemplate.inc"%>
<div id="crearConfigurarDiv">
	<div class="aa79b-content-modal">
	<div id="crearConfigurar_div">
	<div id="crearConfigurar_feedback"></div>
		<input type="hidden" id="asignadoTareaRevAnt_detail_table">
		<form id="crearConfigurar_filter_form">
			<input type="hidden" name="tipoTarea.indReqCierre015" id="indReqCierre_detail_table">
			<input type="hidden" name="idTarea" id="idTarea_detail_table">
			<input type="hidden" name="estadoEjecucion" id="estadoEjecucion_detail_table">
			<div class="row">
				<fieldset id="expedienteInfio_fieldset" class="form_fieldset">
					<legend>
						<spring:message code="comun.datosExpediente"/>
					</legend>
					<div class="row">
						<div class="col-md-4">
							<label for="numExpediente_detail_table" class="control-label col-md-12 p-0" data-i18n="label.numExp">
								<spring:message code="label.numExp" />:
							</label>
							<span id="numExpediente_detail_table"></span>
						</div>
						<div class="col-md-6">
							<label for="solicitante_detail_table" class="control-label col-md-12 p-0" data-i18n="label.solicitante">
								<spring:message code="label.solicitante"/>: 
							</label>
							<span id="solicitante_detail_table"></span>
						</div>
					</div>
					<div class="row">
						<div class="col-md-4">
							<label for="tipoExpediente_detail_table" class="control-label col-md-12 p-0" data-i18n="label.tipo">
								<spring:message code="label.tipo" />:
							</label>
							<span id="tipoExpediente_detail_table"></span>
						</div>
						<div class="col-md-6">
							<label for="contacto_detail_table" class="control-label col-md-12 p-0" data-i18n="label.contacto">
								<spring:message code="label.contacto"/>: 
							</label>
							<span id="contacto_detail_table"></span>
						</div>
					</div>
					<fieldset id="planificacionInfo_fieldset" class="form_fieldset">
						<legend>
							<spring:message code="comun.datosPlanificacion"/>
						</legend>
						<div id="divplanificacionInfoTradRev" class="row">
							<div class="form-group col-md-4 col-xl-3 ">
								<label for="numPalIZO_filter" class="control-label col-md-12 p-0" data-i18n="comun.numPalabrasIZO"><spring:message code="comun.numPalabrasIZO"/>:</label>
								<div class="numPalIzoConcor">
								<div class="numPalIzoConcor1">
									<span id="numPalIZO_filter"></span>
								</div>
								<div class="numPalIzoConcor2">
									<b><spring:message code="comun.tramosConcor084" />:</b>
									<span id="trConcor084_filter"></span><br>
									<b><spring:message code="comun.tramosConcor8594" />:</b>
									<span id="trConcor8594_filter"></span><br>
									<b><spring:message code="comun.tramosConcor9599" />:</b>
									<span id="trConcor9599_filter"></span><br>
									<b><spring:message code="comun.tramosConcor100" />:</b>
									<span id="trConcor100_filter"></span><br>
								</div>
								</div>
							</div>
							<div class="col-md-2">
								<label for="grTrabajo_filter" class="control-label col-md-12 p-0" data-i18n="comun.grupoTrabajo"><spring:message code="comun.grupoTrabajo" />:</label>
								<div class="form-group  col-md-12 col-xl-12 p-0">
									<span id="grTrabajo_filter"></span>
								</div>
							</div>
							<div class="col-md-3 grupoFechaHora">
								<label for="fechaFinExpediente_filter" class="control-label col-md-12 p-0" data-i18n="label.fechaHoraFinalIzo"><spring:message code="label.fechaHoraFinalIzo" />:</label>
								<div class="form-group  col-md-12 col-xl-12 p-0">
									<span id="fechaFinExpediente_filter"></span>
								</div>
							</div>
						</div>
						<div id="divplanificacionInfoInter" class="row">
							<div class="col-md-5 grupoFechaHora">
								<label for="fechaHoraIniInter_filter" class="control-label col-md-12 p-0" data-i18n="label.fechaHoraIniInterpretacion"><spring:message code="label.fechaHoraIniInterpretacion" />:</label>
								<div class="form-group  col-md-5 col-xl-5 p-0">
									<span id="fechaHoraIniInter_filter"></span>
								</div>
							</div>
							<div class="col-md-5 grupoFechaHora">
								<label for="fechaHoraFinInter_filter" class="control-label col-md-12 p-0" data-i18n="label.fechaHoraFinInterpretacion"><spring:message code="label.fechaHoraFinInterpretacion" />:</label>
								<div class="form-group  col-md-5 col-xl-5 p-0">
									<span id="fechaHoraFinInter_filter"></span>
								</div>
							</div>	
						</div>
					</fieldset>
				</fieldset>
			</div>
			<div class="row">
			<fieldset id="tareaInfo_fieldset" class="form_fieldset">
				<legend>
					<spring:message code="comun.datosTarea"/>
				</legend>
				<div class="row">					
						<div class="form-group col-xs-6">
							<label for="tipoTarea_detail_table" class="control-label col-xs-12 no-padding-left" ><spring:message code="comun.tipoDeTarea"/> (*):</label>
							<div class="col-xs-12 no-padding-left">
								<select  name="tipoTarea.id015" class="form-control" id="tipoTarea_detail_table" ></select>
							</div>	
						</div>
						<div id="tipRev">
							<div class="form-group col-xs-3">
								<label for="tipoRevision_detail_table" class="control-label col-xs-12 no-padding-left"><spring:message code="comun.tipoDeRevision"/> (*):</label>
								<div class="col-xs-12 no-padding-left">
									<select  name="tipoRevision.id018" class="form-control" id="tipoRevision_detail_table" ></select>
								</div>	
							</div>
						</div>	
						<div class="form-group col-xs-3">
							<label for="facturable_detail_table" class="control-label col-xs-12 no-padding-left"><spring:message code="comun.facturablePorProveedor"/>:</label>
							<input type="checkbox" name="indFacturacion" id="facturable_detail_table" value="S" data-switch-pestana="true">
						</div>
						
					</div>
					<div class="row">
						<div class="form-group col-xs-3">
							<label for="orden_detail_table" class="control-label col-xs-12 no-padding-left"><spring:message code="label.ordenacion"/> (*):</label>
							<div class="col-xs-12 no-padding-left">
								<input type=text  name="orden" class="form-control col-40por numeric" id="orden_detail_table" maxlength="2"/>
							</div>	
						</div>
						<div class="form-group col-md-12 col-lg-4 grupoFechaHora ">
							<label class="control-label dosPuntos valFecha ajustarFieldSet" for="fechaInicio_detail_table"><spring:message code="comun.fechaInicioTarea"/></label>
							<input type="text" name="fechaIni" class="form-control" id="fechaInicio_detail_table">
							<input type="text" name="horaIni" class="form-control campohora " id="horaInicio_detail_table" placeholder="hh:mm" maxlength="5">
						</div>
						<div class="form-group col-md-12 col-lg-4 grupoFechaHora ">
							<label class="control-label dosPuntos valFecha ajustarFieldSet" for="fechaFin_detail_table"><spring:message code="comun.fechaFinTarea"/></label>
							<input type="text" name="fechaFin" class="form-control" id="fechaFin_detail_table">
							<input type="text" name="horaFin" class="form-control campohora" id="horaFin_detail_table" placeholder="hh:mm" maxlength="5">
						</div>
					</div>
					<div id="mensajeFechaFinMayorQueFechaFinExp" class="row oculto">
						<p style="text-align: end;">
							<span class="ico-ficha-encriptado">
								<i class="fa fa-exclamation-triangle" aria-hidden="true" style="color: red;"></i>
							</span> <spring:message code="label.fechaTareaSupExp"/>
						</p>
					</div>
					<div class="row">
						<div id="horaPrevista_detail_table_container" class="form-group col-xs-4">
							<label for="horaPrevista_detail_table" class="control-label col-xs-12 no-padding-left" ><spring:message code="comun.horaPrevista"/> (*):</label>
							<div class="col-xs-7 no-padding-left">
								<input type=text  name="horasPrevistas" class="form-control col-50por campohora10" id="horaPrevista_detail_table" placeholder="hh:mm" maxlength="10"/>
							</div>	
						</div>
						<div id="horasPrevistasTradRev">
							<div class="form-group col-xs-3">
								<label for="horaPrevistasIZO_detail_table" class="control-label col-xs-12 no-padding-left" style="font-size: 0.9em;font-style: italic;"><spring:message code="label.horasIZO"/>:</label>
								<div class="col-xs-12 no-padding-left">
									<!-- TODO incluir a modo informativo la horas previstas para personal IZO. Se calcula el valor mediante PL -->
									<span id="horasPrevistasIZO" style="font-size: 0.9em;font-style: italic;"></span>
								</div>	
							</div>
							<div class="form-group col-xs-3">
								<label for="horaPrevistasProveedor_detail_table" class="control-label col-xs-12 no-padding-left" style="font-size: 0.9em;font-style: italic;"><spring:message code="label.horasProveedorExterno"/>:</label>
								<div class="col-xs-6 no-padding-left">
									<!-- TODO incluir a modo informativo la horas previstas para proveedor externo. Se calcula el valor mediante PL -->
									<span id="horasPrevistasProveedor" style="font-size: 0.9em;font-style: italic;"></span>
								</div>	
							</div>
						</div>
						<div id="horasEstimadasInter" class="form-group col-xs-3">
							<label for="horaEstimadas_detail_table" class="control-label col-xs-12 no-padding-left"><spring:message code="label.horasEstimadas"/>:</label>
							<div class="col-xs-6 no-padding-left">
								<!-- TODO incluir a modo informativo la horas previstas para proveedor externo. Se calcula el valor mediante PL -->
								<span id="horasEstimadas"></span>
							</div>	
						</div>
					</div>		
			<div class="row">
			 	<div class="form-group col-xs-8">
	                <label for="personaAsignada_detail_table" class="control-label col-xs-12 no-padding-left"><spring:message code="comun.asignadaA"/>:</label>
	                <div class="input-group">
	                	<input type="hidden" id="recursoAsignacion" name="recursoAsignacion" >
	                	<input type="hidden" id="tipoEntidad" name="personaAsignada.entidad.tipo"/>
						<input type="hidden" id="idEntidad" name="personaAsignada.entidad.codigo"/>
						<input type="hidden" id="dniAsignacion" name="personaAsignada.dni"/>
						<input type="hidden" id="idLote" name="lotes.idLote"/>
						<input type="hidden" id="tipoEntidadLote" name="lotes.empresasProveedoras.tipo"/>
						<input type="hidden" id="idEntidadLote" name="lotes.empresasProveedoras.codigo"/>
						<div id="asignadoAPersona">
	                    	<input type="text" name="personaAsignada.nombre" class="form-control" onchange="mostrarLote()" id="personaAsignada_detail_table" readOnly="readonly">
	                    </div>
	                    <div id="asignadoALote">
	                    	<input type="text" name="lotes.nombreLote" class="form-control" id="loteAsignado_detail_table" readOnly="readonly">
	                    </div>
	                    <span class="input-group-addon" id="divLinkEliminar"><a href="#" id="eliminar_detail_table" class=""><spring:message code="eliminar"/></a></span>
	                    <span class="input-group-addon" id="divLinkAutoasignar"><a href="#" id="autoasignar_detail_table" class=""><spring:message code="label.autoasignar"/></a></span>
	                    <span class="input-group-addon" id="divLinkRecursoInterno"><a href="#" id="recursoInterno_detail_table" class=""><spring:message code="label.personalIzo"/></a></span>
	                    <span class="input-group-addon" id="recurExter"><a href="#" id="recursoExterno_detail_table" class=""><spring:message code="comun.recursoExterno"/></a></span>
	                </div>
	            </div>
				<div id="revisarTradContainer" class="form-group col-xs-3" style="display: none;">
					<label for="revisarTrad_detail_table" class="control-label col-xs-12 no-padding-left"><spring:message code="label.revisarTraduccionInternaPregunta"/>:</label>
					<input type="checkbox" name="indReqRevision" id="revisarTrad_detail_table" value="N" data-switch-pestana="true">
				</div>

	            <div class="form-group col-xs-4">
	           		<button id="ultimasInter" type="button" style="margin-top: 24px;">
	           			<i class="fa fa-file-excel-o" aria-hidden="true"></i>
						<spring:message code="label.mostrarUltimasInterpretaciones" />
					</button>
	            </div>
            </div>
            <div class="row">
            	<div id="mostrarNotasContainer" class="form-group col-xs-3" hidden="true">
					<label for="mostrarNotas_detail_table" class="control-label col-xs-12 no-padding-left"><spring:message code="label.mostrarTraductorNotasPeticion"/>:</label>
					<input type="checkbox" name="indMostrarNotasATrad" id="mostrarNotas_detail_table" value="S" data-switch-pestana="true">
				</div>
				<div id="obligarXliffContainer" class="form-group col-xs-3" hidden="true">
					<label for="obligarXliff_detail_table" class="control-label col-xs-12 no-padding-left"><spring:message code="label.obligarXliff"/>:</label>
					<input type="checkbox" name="indObligarXliff" id="obligarXliff_detail_table" value="N" data-switch-pestana="true">
				</div>
            </div>
            <div class="row" id="configTareaProveedorDiv" style="display:none;">
	            <fieldset id="configTareaProveedor_fieldset">
					<legend><spring:message code="label.configTareaProveedor" /></legend>
					<div class="p-2">
						<fieldset id="recargosLote">
							<legend><spring:message code="label.recargosLote" /></legend>
							<div class="row">
								<div class="form-group col-lg-3">
									<label class="control-label" for="formato_tareaConfig"><spring:message code="label.formato"/>:</label>
									<label class="control-label" id="formato_tareaConfig"></label>
								</div>
								<div class="form-group col-lg-3">
									<label class="control-label" for="apremio_tareaConfig"><spring:message code="label.urgenciaMax"/>:</label>
									<label class="control-label" id="apremio_tareaConfig"></label>
								</div>
							</div>
						</fieldset>
						<div class="row">
							<div class="form-group col-lg-3">
								<label class="control-label" for="recargaPorFormatoSwitch_tareaConfig"><spring:message code="label.recargaPorFormato"/>:</label>
								<input type="checkbox" name="datosPagoProveedores.indRecargoFormato" id="recargaPorFormatoSwitch_tareaConfig" value="S" data-switch-pestana="true">
							</div>
							<div class="form-group col-lg-7">
								<label class="control-label" for="sobreNumPal_tareaConfig" data-i18n="label.sobreNumPal"><spring:message code="label.sobreNumPal"/>:</label>
								<input type="text" style="width:100px" name="datosPagoProveedores.numPalRecargoFormato" class="form-control numeric" id="sobreNumPal_tareaConfig" disabled maxlength="6" />
							</div>
							<div class="form-group col-lg-3">
								<label class="control-label" for="recargoPorSwitch_tareaConfig"><spring:message code="label.recargoPor"/>:</label>
								<input type="checkbox" name="datosPagoProveedores.indRecargoApremio" id="recargoPorSwitch_tareaConfig" value="S" data-switch-pestana="true" />
							</div>
							<div class="form-group col-lg-7">
								<label class="control-label" for="porcentajeRecarga_tareaConfig"><spring:message code="label.porcentajeRecarga"/>:</label>
								<input type="text" style="width:100px" name="datosPagoProveedores.porRecargoApremio" class="form-control numeric" id="porcentajeRecarga_tareaConfig" disabled maxlength="3" />
							</div>
						</div>
						
					</div>
				</fieldset>
            </div>
			<div class="row">
				<div class="form-group col-xs-6">
					<label for="documentacion_detail_table" class="control-label dosPuntos col-xs-3 no-padding-left"><spring:message code="label.documentos"/></label>	
					<div class="form-group col-xs-4" >
						<input type="checkbox" name="responsable" id="selectTodos"  value="S">
						<label for="selectTodos" class="control-label no-padding-left"><spring:message code="label.seleccionarTodo"/></label>
					</div>
					<a href="#" id="linkDetalleExpediente" class="col-xs-2 " ><spring:message code="label.ver"/></a>
					<div class="col-xs-12 no-padding-left">
						<fieldset id="documentacion_filter_fieldset" class="rup-table-filter-fieldset">
							<div class="form-group col-md-12">
								<div id="docuAsociados" class="docuAsociadosTree">
									<div id="listaDocuAsociados">
										<ul>
										</ul>
									</div>
								</div>
							</div>
						</fieldset>
					</div>
					<div id="divDocumentosSeleccionados">
						<input type="text" style="width:0px;visibility:hidden;" id="documentosSelect" name="documentosSelect" class="form-control" />
					</div>
				</div>
				<div class="form-group col-xs-6">
					<label for="observaciones_detail_table" class="control-label dosPuntos col-xs-12 no-padding-left"><spring:message code="label.observaciones"/></label>
					<div class="col-xs-12 no-padding-left">
						<textarea  name="observaciones" class="form-control" id="observaciones_detail_table" style="resize: none" rows="6" maxlength="2000"></textarea>
					</div>	
				</div>
			</div>
			</fieldset>
			</div>
		</form>
		</div>
	</div>
	
	<div id="buscadorPersonasIZO" class="oculto"></div>
	<div id="buscadorPersonas" class="oculto"></div>
	<div id="buscadorLotes" class="oculto"></div>
</div>
