<%@include file="/WEB-INF/includeTemplate.inc"%>

<div  id="divDetalleExpedienteDesdeClienteConsulta">
	<h2>
	<spring:message	code="label.detalleExpedienteCliente" />
	</h2>
	<div id="detalleExpedienteDesdeClienteConsulta_div" class="row">
		<div id="detalleExpedienteDesdeClienteConsulta_feedback"></div>
		<div id="detalleExpedienteDesdeClienteConsulta_toolbar"></div>
		<div id="detalleExpedienteDesdeClienteConsulta_filter_div">
			<form id="detalleExpedienteDesdeClienteConsultaForm">
				<fieldset class="conPadding20">
					<legend><spring:message	code="label.datosDeSolicitud" /></legend>
					<div class="row">
						<div class="form-group col-lg-12 ">
							<label for="detalleExpedienteDesdeClienteConsultaCif" class="control-label label-modo-consulta"><spring:message code="label.cif"/>:</label>
							<span class="span-modo-consulta"><input type="text" name="gestorExpediente.entidad.cif" class="form-control input-modo-consulta" id="detalleExpedienteDesdeClienteConsultaCif" readonly="readonly" disabled="disabled"/></span>
						</div>
					</div>
					<div class="row">
						<div class="form-group col-lg-12 ">
							<label for="detalleExpedienteDesdeClienteConsultaNomEntidad" class="control-label label-modo-consulta "><spring:message code="label.nomEntidad"/>:</label>
							<span class="span-modo-consulta"><input type="text" name="gestorExpediente.entidad.descAmpEu" class="form-control input-modo-consulta" id="detalleExpedienteDesdeClienteConsultaNomEntidad" readonly="readonly" disabled="disabled"/></span>
						</div>
					</div>
					<div class="row">
						<div class="form-group col-lg-12 ">
							<label for="detalleExpedienteDesdeClienteConsultaNifGestor" class="control-label label-modo-consulta"><spring:message code="label.nifGestor"/>:</label>
							<span class="span-modo-consulta"><input type="text" name="gestorExpediente.solicitante.dniCompleto" class="form-control input-modo-consulta" id="detalleExpedienteDesdeClienteConsultaNifGestor" readonly="readonly" disabled="disabled"/></span>
						</div>
					</div>
					<div class="row">
						<div class="form-group col-lg-12 ">
							<label for="detalleExpedienteDesdeClienteConsultaNomApellidos" class="control-label label-modo-consulta"><spring:message code="label.nombreApellidosGestor"/>:</label>
							<span class="span-modo-consulta"><input type="text" name="gestorExpediente.solicitante.nombreCompleto" class="form-control input-modo-consulta" id="detalleExpedienteDesdeClienteConsultaNomApellidos" readonly="readonly" disabled="disabled"/></span>
						</div>
					</div>
					<hr class="hr10">
					<div class="row">
						<div class="form-group col-lg-6 ">
							<label for="detalleExpedienteDesdeClienteConsultaTitulo" class="control-label label-modo-consulta"><spring:message code="label.titulo"/>:</label>
							<span class="span-modo-consulta"><input type="text" name="titulo" class="form-control input-modo-consulta" id="detalleExpedienteDesdeClienteConsultaTitulo" readonly="readonly" disabled="disabled"/></span>
						</div>
						<div class="form-group col-lg-6 ">
							<label for="detalleExpedienteDesdeClienteConsultaNumExp" class="control-label label-modo-consulta"><spring:message code="label.numeroExpediente"/>:</label>
								<span class="span-modo-consulta"><input type="text" name="anyoNumExpConcatenado" class="form-control input-modo-consulta" id="detalleExpedienteDesdeClienteConsultaNumExp" readonly="readonly" disabled="disabled"/></span>
						</div>
					</div>
					<div class="row">
						<div class="form-group col-lg-6 ">
							<label for="detalleExpedienteDesdeClienteConsultaTipoExp" class="control-label label-modo-consulta"><spring:message code="label.tipoDeExpediente"/>:</label>
							<span class="span-modo-consulta"><input type="text" name="tipoExpedienteDescEu" class="form-control input-modo-consulta" id="detalleExpedienteDesdeClienteConsultaTipoExp" readonly="readonly" disabled="disabled"/></span>
						</div>
					
						<div class="form-group col-lg-6 ">
							<label for="detalleExpedienteDesdeClienteConsultaFechaHoraAlta" class="control-label label-modo-consulta"><spring:message code="label.fechaDeSolicitud"/>:</label>
							<span class="span-modo-consulta"><input type="text" name="fechaHoraAlta" class="form-control input-modo-consulta" id="detalleExpedienteDesdeClienteConsultaFechaHoraAlta" readonly="readonly" disabled="disabled"/></span>
						</div>
					</div>
					<hr class="hr10">
					<!-- DATOS INTERPRETACION - INICIO -->
					<div id="datosInterConsulta" style="display:none;">
						<div class="row">
							<div class="form-group col-lg-3 ">
								<label for="detalleExpedienteDesdeClienteConsultaIndPresupuesto" class="control-label label-modo-consulta"><spring:message code="label.presupuestoSolicitado"/></label>
								<span class="span-modo-consulta"><input type="text" name="expedienteInterpretacion.presupuestoDescEu" class="form-control input-modo-consulta" id="detalleExpedienteDesdeClienteConsultaIndPresupuesto" readonly="readonly" disabled="disabled"/></span>
							</div>
							<div class="form-group col-lg-6 ">
								<label for="detalleExpedienteDesdeClienteConsultaModoInterpretacion" class="control-label label-modo-consulta"><spring:message code="label.modoInterpretacion"/>:</label>
								<span class="span-modo-consulta"><input type="text" name="expedienteInterpretacion.modoInterpretacionDescEu" class="form-control input-modo-consulta" id="detalleExpedienteDesdeClienteConsultaModoInterpretacion" readonly="readonly" disabled="disabled"/></span>
							</div>
						</div>
						<div class="row">
							<div class="form-group col-lg-3 ">
								<label for="detalleExpedienteDesdeClienteConsultaTipoActo" class="control-label label-modo-consulta"><spring:message code="label.tipoActo"/>:</label>
								<span class="span-modo-consulta"><input type="text" name="expedienteInterpretacion.tipoActoDescEu" class="form-control input-modo-consulta" id="detalleExpedienteDesdeClienteConsultaTipoActo" readonly="readonly" disabled="disabled"/></span>
							</div>
							<div class="form-group col-lg-6 ">
								<label for="detalleExpedienteDesdeClienteConsultaTipoPeticionario" class="control-label label-modo-consulta"><spring:message code="label.modoInterpretacion"/>:</label>
								<span class="span-modo-consulta"><input type="text" name="expedienteInterpretacion.tipoPeticionarioDescEu" class="form-control input-modo-consulta" id="detalleExpedienteDesdeClienteConsultaTipoPeticionario" readonly="readonly" disabled="disabled"/></span>
							</div>
						</div>
						<div class="row">
							<div class="form-group col-lg-6 ">
								<label for="detalleExpedienteDesdeClienteConsultaIndProgramada" class="control-label label-modo-consulta"><spring:message code="label.interpretacionProgramada"/></label>
								<span class="span-modo-consulta"><input type="text" name="expedienteInterpretacion.programadaDescEu" class="form-control input-modo-consulta" id="detalleExpedienteDesdeClienteConsultaIndProgramada" readonly="readonly" disabled="disabled"/></span>
							</div>
						</div>
						<div class="row">
							<div class="form-group col-lg-6 ">
								<label for="detalleExpedienteDesdeClienteConsultaFechaHoraIni" class="control-label label-modo-consulta"><spring:message code="label.fechaHoraIniInterpretacion"/>:</label>
								<span class="span-modo-consulta"><input type="text" name="expedienteInterpretacion.fechaHoraIni" class="form-control input-modo-consulta" id="detalleExpedienteDesdeClienteConsultaFechaHoraIni" readonly="readonly" disabled="disabled"/></span>
							</div>
						</div>
						<div class="row">
							<div class="form-group col-lg-6 ">
								<label for="detalleExpedienteDesdeClienteConsultaFechaHoraFin" class="control-label label-modo-consulta"><spring:message code="label.fechaHoraFinInterpretacion"/>:</label>
								<span class="span-modo-consulta"><input type="text" name="expedienteInterpretacion.fechaHoraFin" class="form-control input-modo-consulta" id="detalleExpedienteDesdeClienteConsultaFechaHoraFin" readonly="readonly" disabled="disabled"/></span>
							</div>
						</div>
						<div class="row">
							<div class="form-group col-lg-6 ">
								<label for="detalleExpedienteDesdeClienteConsultaDireccion" class="control-label label-modo-consulta"><spring:message code="label.lugarInterpretacion"/>:</label>
								<span class="span-modo-consulta"><input type="text" name="expedienteInterpretacion.dirNora.txtDirec" class="form-control input-modo-consulta" id="detalleExpedienteDesdeClienteConsultaDireccion" readonly="readonly" disabled="disabled"/></span>
							</div>
						</div>
						<!-- observaciones - inicio -->
						<div id="detalleExpedienteDesdeClienteConsultaObservacionesExpInterDiv" style="display:none;">
							<div id="ficheroDetalleExpedienteDesdeClienteConsultaObservacionesExpInter_LinkDiv" class="row" >
								<div class="form-group col-lg-6 ">
									<a id="ficheroDetalleExpedienteDesdeClienteConsultaObservacionesExpInter_Link" href="#" class="aa79b-rup-wizard_summary"><spring:message code="label.ficheroObservaciones"/></a>
								</div>
							</div>
							<div id="detalleExpedienteDesdeClienteConsultaObservacionesExpInterDivText" class="row"  style="display:none;">
								<div class="form-group  col-lg-6 ">
								<label for="detalleExpedienteDesdeClienteConsultaObservacionesExpInterText" class="control-label label-modo-consulta"><spring:message code="label.observacionesSolicitud"/>:</label>
									<textarea name="expedienteInterpretacion.observaciones.observaciones" class="form-control input-modo-consulta resizable-textarea" id="detalleExpedienteDesdeClienteConsultaObservacionesExpInterText" rows="1" cols="9" maxlength="4000" readonly="readonly" disabled="disabled"></textarea>
								</div>
							</div>
						</div>
						<!-- observaciones - fin -->
						<div class="row">
							<div class="form-group col-lg-6 ">
								<label for="detalleExpedienteDesdeClienteConsultaFaseExpediente" class="control-label label-modo-consulta"><spring:message code="label.estadoFaseExp"/>:</label>
								<span class="span-modo-consulta"><input type="text" name="bitacoraExpediente.faseExp.descEu" class="form-control input-modo-consulta" id="detalleExpedienteDesdeClienteConsultaFaseExpediente" readonly="readonly" disabled="disabled"/></span>
							</div>
							<div id="detalleExpedienteDesdeClienteMotivoRechazoAnulacionInter" style="display:none;">
								<div class="form-group col-lg-6 ">
									<label for="detalleExpedienteDesdeClienteConsultaMotivoRechazoInter" class="control-label label-modo-consulta"><spring:message code="label.motivoRechazo"/>:</label>
									<c:if test="${pageContext.response.locale eq LANGUAGE_ES}"> 
			              				<span class="span-modo-consulta"><input type="text" name="anulacionRechazo.motivoDescEs" class="form-control input-modo-consulta" id="detalleExpedienteDesdeClienteConsultaMotivoRechazoInter" readonly="readonly" disabled="disabled"/></span>
			            			</c:if> 
									<c:if test="${pageContext.response.locale eq LANGUAGE_EU}"> 
										<span class="span-modo-consulta"><input type="text" name="anulacionRechazo.motivoDescEu" class="form-control input-modo-consulta" id="detalleExpedienteDesdeClienteConsultaMotivoRechazoInter" readonly="readonly" disabled="disabled"/></span>
			            			</c:if> 
									
								</div>
							</div>
						</div>
						<div id="detalleExpedienteDesdeClienteObservacionesInter" style="display:none;">
							<div class="row">
								<div class="form-group col-lg-12 ">
									<label for="detalleExpedienteDesdeClienteConsultaObservacionesInter" class="control-label label-modo-consulta"><spring:message code="label.observaciones"/>:</label>
									<span class="span-modo-consulta"><textarea name="anulacionRechazo.observaciones" class="form-control input-modo-consulta" id="detalleExpedienteDesdeClienteConsultaObservacionesInter" rows="1" cols="9" maxlength="4000" readonly="readonly" disabled="disabled"></textarea></span>
								</div>
							</div>
						</div>
					</div>
					<!-- DATOS INTERPRETACION - FIN -->
					<!-- DATOS TRADREV - INICIO -->
					<div id="datosTradRevConsulta" style="display:none;">
						<div class="row">
							<div class="form-group col-lg-12 ">
								<label for="detalleExpedienteDesdeClienteConsultaNumPalSolic" class="control-label label-modo-consulta"><spring:message code="label.documento.numTotalPalSolic"/>:</label>
								<span class="span-modo-consulta"><input type="text" name="expedienteTradRev.numTotalPalSolic" class="form-control input-modo-consulta" id="detalleExpedienteDesdeClienteConsultaNumPalSolic" readonly="readonly" disabled="disabled"/></span>
							</div>
						</div>
						<div class="row">
							<div class="form-group col-lg-12 ">
								<label for="detalleExpedienteDesdeClienteConsultaFechaHoraFinalSolic" class="control-label label-modo-consulta"><spring:message code="label.fechaHoraFinSol"/>:</label>
								<span class="span-modo-consulta"><input type="text" name="expedienteTradRev.fechaHoraFinalSolic" class="form-control input-modo-consulta" id="detalleExpedienteDesdeClienteConsultaFechaHoraFinalSolic" readonly="readonly" disabled="disabled"/></span>
							</div>
						</div>
						<div class="row">
							<div class="form-group col-lg-6 ">
								<label for="detalleExpedienteDesdeClienteConsultaIndPublicarBOPV" class="control-label label-modo-consulta"><spring:message code="label.publicarBOPV"/></label>
								<span class="span-modo-consulta"><input type="text" name="expedienteTradRev.publicarBopvDescEu" class="form-control input-modo-consulta" id="detalleExpedienteDesdeClienteConsultaIndPublicarBOPV" readonly="readonly" disabled="disabled"/></span>
							</div>
							<div class="form-group col-lg-6 ">
								<label for="detalleExpedienteDesdeClienteConsultaIdiomaDest" class="control-label label-modo-consulta"><spring:message code="label.idiomaDestinoTrad"/>:</label>
								<span class="span-modo-consulta"><input type="text" name="expedienteTradRev.idIdiomaDescEu" class="form-control input-modo-consulta" id="detalleExpedienteDesdeClienteConsultaIdiomaDest" readonly="readonly" disabled="disabled"/></span>
							</div>
						</div>
						<div class="row">
							<div class="form-group col-lg-6 ">
								<label for="detalleExpedienteDesdeClienteConsultaIndCorredaccion" class="control-label label-modo-consulta"><spring:message code="label.esCorredaccion"/></label>
								<span class="span-modo-consulta"><input type="text" name="expedienteTradRev.corredaccionDescEu" class="form-control input-modo-consulta" id="detalleExpedienteDesdeClienteConsultaIndCorredaccion" readonly="readonly" disabled="disabled"/></span>
							</div>
							<div class="form-group col-lg-6">
								<label for="detalleExpedienteDesdeClienteConsultaIndPresupuesto" class="control-label label-modo-consulta"><spring:message code="label.presupuestoJusti"/></label>
								<span class="span-modo-consulta"><input type="text" name="expedienteTradRev.reqPresupuestoDescEu" class="form-control input-modo-consulta" id="detalleExpedienteDesdeClienteConsultaIndPresupuesto" readonly="readonly" disabled="disabled"/></span>
							</div>
						</div>
						<fieldset id="detalleExpedienteDesdeClienteConsultaDatosCorredaccion" style="display:none;">
							<legend><spring:message code="label.datosCorredaccion"/></legend>
							<div class="row">
								<div class="form-group col-lg-12 ">
									<label for="detalleExpedienteDesdeClienteConsultaTextoCorredaccion" class="control-label label-modo-consulta"><spring:message code="label.textoElaborar"/>:</label>
									<span class="span-modo-consulta"><input type="text" name="expedienteTradRev.texto" class="form-control input-modo-consulta" id="detalleExpedienteDesdeClienteConsultaTextoCorredaccion" readonly="readonly" disabled="disabled"/></span>
								</div>
							</div>
							<div class="row">
								<div class="form-group col-lg-12 ">
									<label for="detalleExpedienteDesdeClienteConsultaTipoRedaccion" class="control-label label-modo-consulta"><spring:message code="label.tipoRedaccionBilingue"/>:</label>
									<span class="span-modo-consulta"><input type="text" name="expedienteTradRev.tipoRedaccion" class="form-control input-modo-consulta" id="detalleExpedienteDesdeClienteConsultaTipoRedaccion" readonly="readonly" disabled="disabled"/></span>
								</div>
							</div>
							<div class="row">
								<div class="form-group col-lg-12 ">
									<label for="detalleExpedienteDesdeClienteConsultaParticipantes" class="control-label label-modo-consulta"><spring:message code="label.participantes"/>:</label>
									<span class="span-modo-consulta"><input type="text" name="expedienteTradRev.participantes" class="form-control input-modo-consulta" id="detalleExpedienteDesdeClienteConsultaParticipantes" readonly="readonly" disabled="disabled"/></span>
								</div>
							</div>
						</fieldset>
						<div class="row">
							<div class="form-group col-lg-12 ">
								<label for="detalleExpedienteDesdeClienteConsultaRefTramitagune" class="control-label label-modo-consulta"><spring:message code="label.referenciaTramitagune"/>:</label>
								<span class="span-modo-consulta"><input type="text" name="expedienteTradRev.refTramitagune" class="form-control input-modo-consulta" id="detalleExpedienteDesdeClienteConsultaRefTramitagune" readonly="readonly" disabled="disabled"/></span>
							</div>
						</div>
						<div id="detalleExpedienteDesdeClienteConsultaObservacionesTradRevContainer"  style="display:none;">
							<div id="detalleExpedienteDesdeClienteConsultaDivSeccionObservaciones" class="row">
								<div class="col-lg-12 ">
									<label class="control-label label-modo-consulta" for="detalleExpedienteDesdeClienteConsultaObservacionesExpTradRev">
										<spring:message code="label.observacionesSolicitud"/>:
										<div id="detalleExpedienteDesdeClienteConsultaObservaciones_mostrarLink_div" class="inline">
											(<a id="detalleExpedienteDesdeClienteConsultaObservaciones_mostrarLink" href="#" class="control-label label-modo-consulta"><spring:message code="label.mostrar" /></a>)
										</div>
									</label>
								</div>
							</div>
							<div id="detalleExpedienteDesdeClienteConsultaDivDetalleObservaciones" style="display:none;">
								<div class="row">
									<div class="form-group col-lg-12 ">
										<span id="detalleExpedienteDesdeClienteConsultaEnlaceDescargaDetalleTradRev" style="display:contents;"></span> 
									</div>
									<input type="hidden" name="expedienteTradRev.observaciones.oidFichero" id="oidFicheroObservacionesTradRev" readonly="readonly"/>
								</div>
								<div class="row">
									<div class="form-group col-lg-12 ">
										<textarea name="expedienteTradRev.observaciones.observaciones" class="form-control input-modo-consulta resizable-textarea" id="detalleExpedienteDesdeClienteConsultaObservacionesExpTradRev" rows="1" cols="9" maxlength="4000" readonly="readonly" disabled="disabled"></textarea>
									</div>
								</div>
							</div>
						</div>
					</div>
					<!-- DATOS TRADREV - FIN -->
				</fieldset>
				<fieldset id="divSeguimientoExp">
							<legend><spring:message code="label.datosSeguimientoExpediente" /></legend>
							<div class="row">
								<div class="form-group col-lg-6 ">
									<label for="detalleExpedienteDesdeClienteConsultaIndConfidencial" class="control-label label-modo-consulta"><spring:message code="label.esConfidencial"/></label>
									<span class="span-modo-consulta"><input type="text" name="indConfidencialDescEu" class="form-control input-modo-consulta" id="detalleExpedienteDesdeClienteConsultaIndConfidencial" readonly="readonly" disabled="disabled"/></span>
								</div>
							</div>
							<div id="detalleExpedienteDesdeClienteConsultaDatosTrados" style="display:none;">
								<div class="row">
									<div class="form-group col-lg-12 ">
										<label for="detalleExpedienteDesdeClienteConsultaNumTotalTrados" class="control-label label-modo-consulta"><spring:message code="label.numTotPal"/>:</label>
										<span class="span-modo-consulta"><input type="text" name="expedienteTradRev.tradosExp.numTotalPal" class="form-control input-modo-consulta" id="detalleExpedienteDesdeClienteConsultaNumTotalTrados" readonly="readonly" disabled="disabled"/></span>
									</div>	
								</div>
								<div class="row">
									<div class="form-group col-md-3 ">
										<label for="detalleExpedienteDesdeClienteConsultaNum084" class="control-label label-modo-consulta"><spring:message code="comun.tramosConcor1"/>:</label>
										<span class="span-modo-consulta"><input type="text" name="expedienteTradRev.tradosExp.numPalConcor084" class="form-control input-modo-consulta" id="detalleExpedienteDesdeClienteConsultaNum084" readonly="readonly" disabled="disabled"/></span>
									</div>	
									<div class="form-group col-md-3 ">
										<label for="detalleExpedienteDesdeClienteConsultaNum8594" class="control-label label-modo-consulta"><spring:message code="comun.tramosConcor2"/>:</label>
										<span class="span-modo-consulta"><input type="text" name="expedienteTradRev.tradosExp.numPalConcor8594" class="form-control input-modo-consulta" id="detalleExpedienteDesdeClienteConsultaNum8594" readonly="readonly" disabled="disabled"/></span>
									</div>	
									<div class="form-group col-lg-3 ">
										<label for="detalleExpedienteDesdeClienteConsultaNum95100" class="control-label label-modo-consulta"><spring:message code="comun.tramosConcor3"/>:</label>
										<span class="span-modo-consulta"><input type="text" name="expedienteTradRev.tradosExp.numPalConcor95100" class="form-control input-modo-consulta" id="detalleExpedienteDesdeClienteConsultaNum95100" readonly="readonly" disabled="disabled"/></span>
									</div>	
								</div>
							</div>
							<div id="detalleExpedienteDesdeClienteNumPalIzo" style="display:none;">
								<div class="row">
									<div class="form-group col-lg-6 ">
										<label for="detalleExpedienteDesdeClienteNumPalIzo" class="control-label label-modo-consulta"><spring:message code="label.numTotPalIzo"/>:</label>
										<span class="span-modo-consulta"><input type="text" name="expedienteTradRev.numTotalPalIzo" class="form-control input-modo-consulta" id="detalleExpedienteDesdeClienteNumPalIzo" readonly="readonly" disabled="disabled"/></span>
									</div>	
								</div>
							</div>
							<div class="row">
								<div class="form-group col-lg-6 ">
									<label for="detalleExpedienteDesdeClienteConsultaFechaHoraFinalIzo" class="control-label label-modo-consulta"><spring:message code="label.fechaHoraFinalIzoProp"/>:</label>
									<span class="span-modo-consulta"><input type="text" name="expedienteTradRev.fechaHoraFinalIZO" class="form-control input-modo-consulta" id="detalleExpedienteDesdeClienteConsultaFechaHoraFinalIzo" readonly="readonly" disabled="disabled"/></span>
								</div>	
								<div class="form-group col-lg-6 ">
									<label for="detalleExpedienteDesdeClienteConsultaFechaHoraEntrega" class="control-label label-modo-consulta"><spring:message code="label.fechaHoraEntregaReal"/>:</label>
									<span class="span-modo-consulta"><input type="text" name="fechaHoraEntrega" class="form-control input-modo-consulta" id="detalleExpedienteDesdeClienteConsultaFechaHoraEntrega" readonly="readonly" disabled="disabled"/></span>
								</div>	
							</div>
							<div class="row">
								<div class="form-group col-lg-6 ">
									<label for="detalleExpedienteDesdeClienteConsultaFaseExpediente" class="control-label label-modo-consulta"><spring:message code="label.estadoFaseExp"/>:</label>
									<span class="span-modo-consulta"><input type="text" name="bitacoraExpediente.faseExp.descEu" class="form-control input-modo-consulta" id="detalleExpedienteDesdeClienteConsultaFaseExpediente" readonly="readonly" disabled="disabled"/></span>
								</div>
								<div id="detalleExpedienteDesdeClienteMotivoRechazoAnulacion" style="display:none;">
									<div class="form-group col-lg-6 ">
										<label for="detalleExpedienteDesdeClienteConsultaMotivoRechazo" class="control-label label-modo-consulta"><spring:message code="label.motivoRechazo"/>:</label>
										<c:if test="${pageContext.response.locale eq LANGUAGE_ES}"> 
				              				<span class="span-modo-consulta"><input type="text" name="anulacionRechazo.motivoDescEs" class="form-control input-modo-consulta" id="detalleExpedienteDesdeClienteConsultaMotivoRechazo" readonly="readonly" disabled="disabled"/></span>
				            			</c:if> 
										<c:if test="${pageContext.response.locale eq LANGUAGE_EU}"> 
											<span class="span-modo-consulta"><input type="text" name="anulacionRechazo.motivoDescEu" class="form-control input-modo-consulta" id="detalleExpedienteDesdeClienteConsultaMotivoRechazo" readonly="readonly" disabled="disabled"/></span>
				            			</c:if> 
										
									</div>
								</div>
							</div>
							<div id="detalleExpedienteDesdeClienteObservaciones" style="display:none;">
								<div class="row">
									<div class="form-group col-lg-12 ">
										<label for="detalleExpedienteDesdeClienteConsultaObservaciones" class="control-label label-modo-consulta"><spring:message code="label.observaciones"/>:</label>
										<span class="span-modo-consulta"><textarea name="anulacionRechazo.observaciones" class="form-control input-modo-consulta" id="detalleExpedienteDesdeClienteConsultaObservaciones" rows="1" cols="9" maxlength="4000" readonly="readonly" disabled="disabled"></textarea></span>
									</div>
								</div>
							</div>
						</fieldset>
			</form>
		</div>
	</div>
</div>