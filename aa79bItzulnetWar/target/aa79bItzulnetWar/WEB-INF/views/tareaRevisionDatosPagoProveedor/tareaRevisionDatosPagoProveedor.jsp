<%@include file="/WEB-INF/includeTemplate.inc"%>
<div class="container-fluid aa79b-fade in aa79b-content" id="divEjecutarTareaCapa">
	<div id="ejecutarTareaDialog_feedback"></div>
	<div id="ejecutarTareaDialog_toolbar" style="padding:5px !important"></div>
		<div class="row">
			<div class="col-xs-2">
				<label class="control-label"><spring:message code="comun.tipoDeTarea"/>:</label>
			</div>
			<div class="col-xs-10">
				<b><c:out value="${descripcionTarea}"></c:out></b>
			</div>
		</div>
		<div class="row" style="margin:1em 0">
			<div class="col-xs-2">
				<label class="control-label col-100por" for="facturableSwitch"><spring:message code="label.facturable"/></label>
			</div>
			<div class="col-xs-10">
				<input type="checkbox" name="expedienteInterpretacion.indPresupuesto" id="facturableSwitch" value="S" data-switch-pestana="true">
			</div>
		</div>
	<div id="tabsDatosPagoProveedor" class="tabsEjecutarTarea"></div>
	<div id="pestDatosPago">
		<form id="ejecutarTareaDialog_form">
			<input type="hidden" name="numExp" id="numExp_form" >
			<input type="hidden" name="anyo" id="anyo_form" >
			<input type="hidden" name="idTarea" id="idTarea_form" >
			<input type="hidden" name="tipoTarea.id015" id="idTipoTarea_form" >
			<input type="hidden" name="ejecucionTareas.horasTarea" id="horasTarea_form" >
			<input type="hidden" name="indFacturacion" id="indFacturacion_form" >
			<input type="hidden" name="datosPagoProveedores.ivaAplic" id="ivaAplic" >
			<input type="hidden" name="datosPagoProveedores.porRevision" id="tareaRevisionAplic" >
			<input type="hidden" name="datosPagoProveedores.importePalAplic" id="valImportePalabraIVA" >
			<div class="row" style="padding-top: 10px;">
				<label class="control-label col-lg-12 cursiva"><spring:message code="label.notaTareaPagoProveedores"/></label>
			</div>
			<div class="row">
				<div class="p-2">
					<fieldset id="datos_expediente_interpretacion_filter_fieldset">
						<legend><spring:message code="label.numTotalPalabrasSolicitadas"></spring:message></legend>
							<div class="row">
								<div class="form-group col-lg-4">
									<label class="control-label col-lg-7" for="numTotalPalSol"><spring:message code="label.documento.numTotalPal"/>:</label>
									<label id="numTotalPalSol" class="control-label col-xs-5"></label>
								</div>
								<div id="numPalSolicConcorDiv">
								    <div class="form-group col-lg-2">
										<label for="numPalSolicConcor084" class="control-label col-xs-7"><spring:message code="comun.tramosConcor1"/>:</label>
										<label id="numPalSolicConcor084" class="control-label col-xs-5"></label>
									</div>
									<div class="form-group col-lg-2">
										<label for="numPalSolicConcor8594" class="control-label col-xs-7"><spring:message code="comun.tramosConcor2"/>:</label>
										<label id="numPalSolicConcor8594" class="control-label col-xs-5"></label>
									</div>
									<div class="form-group col-lg-2">
										<label for="numPalSolicConcor95100" class="control-label col-xs-7"><spring:message code="comun.tramosConcor3"/>:</label>
										<label id="numPalSolicConcor95100" class="control-label col-xs-5"></label>
									</div>
								</div>
							</div>
					</fieldset>
				</div>
			</div>
			<div class="row">
				<div class="p-2">
					<fieldset id="datos_expediente_interpretacion_filter_fieldset">
						<legend><spring:message code="label.numPalabrasAPagarProveedor"></spring:message></legend>
							<div class="row">
								<div class="form-group col-lg-3">
									<label class="control-label" for="numtotalPal"><spring:message code="label.documento.numTotalPal"/>:</label>
									<input type="text" style="width:100px" name="datosPagoProveedores.numTotalPal" class="form-control numeric" id="numtotalPal" maxlength="6" />
								</div>
								<div id="numPalConcorDiv">
									<div class="form-group col-xs-9 col-md-9">
										<label for="titulo_filter_table" class="control-label col-md-9"><spring:message code="label.documento.palabrasRangoConcordancia"/>:</label>
									</div>
								    <div class="col-xs-3 col-sm-3 form-group aa79b-no-padding">
										<label id="labelPalHoraConcor084" for="palHoraConcor084" class="control-label col-xs-6 "><spring:message code="comun.tramosConcor1"/>:</label>
										<div id="divPalHoraConcor084" class="col-xs-5">
											<input type="text" name="datosPagoProveedores.numPalConcor084" class="form-control numeric" id="palHoraConcor084" maxlength="6" />
										</div>
									</div>
									<div class="col-xs-3 col-sm-3 form-group aa79b-no-padding">
										<label id="labelPalHoraConcor8594" for="palHoraConcor8594" class="control-label col-xs-6 "><spring:message code="comun.tramosConcor2"/>:</label>
										<div id="divPalHoraConcor8594" class="col-xs-5">
											<input type="text" name="datosPagoProveedores.numPalConcor8594" class="form-control numeric" id="palHoraConcor8594" maxlength="6" />
										</div>
									</div>
									<div class="col-xs-3 col-sm-3 form-group aa79b-no-padding">
										<label id="labelPalHoraConcor95100" for="palHoraConcor95100" class="control-label col-xs-6 "><spring:message code="comun.tramosConcor3"/>:</label>
										<div id="divPalHoraConcor95100" class="col-xs-5">
											<input type="text" name="datosPagoProveedores.numPalConcor95100" class="form-control numeric" id="palHoraConcor95100" maxlength="6" />
										</div>
									</div>
								</div>
							</div>
					</fieldset>
				</div>
			</div>
			<div class="row">
				<div class="col-xs-12 col-sm-12 form-group aa79b-no-padding">
					<div class="form-group col-lg-3">
						<label class="control-label" for="recargaPorFormatoSwitch"><spring:message code="label.recargaPorFormato"/>:</label>
						<input type="checkbox" name="datosPagoProveedores.indRecargoFormato" id="recargaPorFormatoSwitch" value="S" data-switch-pestana="true">
					</div>
					<div class="form-group col-lg-7">
						<label class="control-label" for="sobreNumPal" data-i18n="label.sobreNumPal"><spring:message code="label.sobreNumPal"/>:</label>
						<input type="text" style="width:100px" name="datosPagoProveedores.numPalRecargoFormato" class="form-control numeric" id="sobreNumPal" disabled maxlength="6" />
					</div>
					<div class="form-group col-lg-3">
						<label class="control-label" for="recargoPorSwitch"><spring:message code="label.recargoPor"/>:</label>
						<input type="checkbox" name="datosPagoProveedores.indRecargoApremio" id="recargoPorSwitch" value="S" data-switch-pestana="true" />
					</div>
					<div class="form-group col-lg-7">
						<label class="control-label" for="porcentajeRecarga"><spring:message code="label.porcentajeRecarga"/>:</label>
						<input type="text" style="width:100px" name="datosPagoProveedores.porRecargoApremio" class="form-control numeric" id="porcentajeRecarga" disabled maxlength="3" />
					</div>
					<div class="form-group col-lg-3">
						<label class="control-label" for="penalizacionPorRetrasoSwitch"><spring:message code="label.penalizacionPorRetraso"/>:</label>
						<input type="checkbox" name="datosPagoProveedores.indPenalizacion" id="penalizacionPorRetrasoSwitch" value="S" data-switch-pestana="true">
					</div>
					<div class="form-group col-lg-7">
						<label class="control-label" for="porcentajePenalizacion"><spring:message code="label.porcentajePenalizacion"/>:</label>
						<input type="text" style="width:100px" name="datosPagoProveedores.porPenalizacion" class="form-control numeric" id="porcentajePenalizacion" disabled maxlength="3" />
					</div>
					<div class="form-group col-lg-3">
						<input type="hidden" name="datosPagoProveedores.porPenalizacionCalidad" id="porPenalizacionCalidad" disabled />
						<label for="calidad_combo" class="control-label"><spring:message code="label.calidadTrabajoRealizado" /></label>
						<select class="form-control" name="datosPagoProveedores.nivelCalidad" id="calidad_combo" ></select>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-xs-5 col-sm-5 form-group aa79b-no-padding">
					<fieldset id="info_archivo_filter_fieldset">
						<legend><spring:message code="label.infoLote"></spring:message></legend>
						<div class="form-group col-xs-12 col-md-12">
							<label class="control-label col-100por" for="importeAsig"><spring:message code="label.importeAsig"/> =</label>
							<label id="importeAsig" class="control-label"></label>
						</div>
						<div class="form-group col-xs-12 col-md-12">
							<label for="importeTotalConsum" class="control-label"><spring:message code="label.importeTotalConsum"/> =</label>
							<label id="importeTotalConsum" class="control-label"></label>
						</div>
						<div class="form-group col-xs-12 col-md-12">
							<label class="control-label col-100por" for="importeTotalPre"><spring:message code="label.importeTotalPre"/> =</label>
							<label id="importeTotalPre" class="control-label"></label>
						</div>
						<div class="form-group col-xs-12 col-md-12">
							<label for="importeTotalDisponible" class="control-label"><spring:message code="label.importeTotalDisponible"/> =</label>
							<label id="importeTotalDisponible" class="control-label"></label>
						</div>
					</fieldset>
					<div class="form-group col-xs-12" style="margin-top:1.6875em">
							<label class="control-label col-50por flotaIzda" for="asociableAAlbaranSwitch"><spring:message code="label.asociableAAlbaran"/></label>
							<div class="form-group col-xs-6">
								<input type="checkbox" name="datosPagoProveedores.indAlbaran" id="asociableAAlbaranSwitch" value="S" data-switch-pestana="true">
							</div>
							
					</div>
				</div>
				
				<div class="col-xs-7 col-sm-7 form-group aa79b-no-padding">
					<fieldset id="importes_filter_fieldset" style="border:none; margin-top:10px;">
						<div class="col-xs-12 col-sm-12 form-group aa79b-no-padding">
							<label id="labelImporteRealizarTarea" for="importeRealizarTarea" class="control-label col-xs-8 "><spring:message code="label.importeRealizarTarea"/></label>
							<div id="divImporteRealizarTarea" class="col-xs-4">
								<input type="text" class="form-control" id="importeRealizarTareaVal" disabled />
								<input type="hidden" name="datosPagoProveedores.importeTarea" id="importeRealizarTarea" />
							</div>
						</div>
						<div class="col-xs-12 col-sm-12 form-group aa79b-no-padding">
							<label id="labelImporteRecargoPorFormato" for="importeRecargoPorFormato" class="control-label col-xs-8 "><spring:message code="label.importeRecargoPorFormato"/></label>
							<div id="divImporteRecargoPorFormato" class="col-xs-4">
								<input type="text" class="form-control" id="importeRecargoPorFormatoVal" disabled />
								<input type="hidden" name="datosPagoProveedores.importeRecargoFormato" id="importeRecargoPorFormato"  />
							</div>
						</div>
						<div class="col-xs-12 col-sm-12 form-group aa79b-no-padding">
							<label id="labelImporteRecargoPorApremio" for="importeRecargoPorApremio" class="control-label col-xs-8 "><spring:message code="label.importeRecargoPorApremio"/></label>
							<div id="divImporteRecargoPorApremio" class="col-xs-4">
								<input type="text" class="form-control" id="importeRecargoPorApremioVal" disabled />
								<input type="hidden" name="datosPagoProveedores.importeRecargoApremio" id="importeRecargoPorApremio"  />
							</div>
						</div>
						<div class="col-xs-12 col-sm-12 form-group aa79b-no-padding">
							<label id="labelImportePenalPorRetraso" for="importePenalPorRetraso" class="control-label col-xs-8 "><spring:message code="label.importePenalPorRetraso"/></label>
							<div id="divImportePenalPorRetraso" class="col-xs-4">
								<input type="hidden" name="datosPagoProveedores.importePenalizacion" id="importePenalPorRetrasoVal"  />
								<input type="text" class="form-control" id="importePenalPorRetrasoLabel" disabled />
							</div>
						</div>
						<div class="col-xs-12 col-sm-12 form-group aa79b-no-padding">
							<label id="labelImportePenalPorCalidad" for="importePenalPorCalidad" class="control-label col-xs-8 "><spring:message code="label.importePenalPorCalidad"/></label>
							<div id="divImportePenalPorCalidad" class="col-xs-4">
								<input type="hidden" name="datosPagoProveedores.importePenalCalidad" id="importePenalPorCalidadVal"  />
								<input type="text" class="form-control" id="importePenalPorCalidadLabel" disabled />
							</div>
						</div>
						    <div class="col-xs-4 col-sm-5 form-group aa79b-no-padding">
								<label id="labelImporteBase" for="importeBase" class="control-label col-xs-12 "><spring:message code="label.importeBase"/></label>
								<div id="divImporteBase" class="col-xs-12">
									<input type="text" class="form-control" id="importeBaseVal" disabled />
									<input type="hidden" name="datosPagoProveedores.importeBase" id="importeBase" disabled />
								</div>
							</div>
							<div class="col-xs-4 col-sm-4 form-group aa79b-no-padding">
								<label id="labelImporteIVA" for="importeIVA" class="control-label col-xs-12 "><spring:message code="label.importeIVA"/></label>
								<div id="divImporteIVA" class="col-xs-12">
									<input type="text" class="form-control" id="importeIVAVal" disabled />
									<input type="hidden" name="datosPagoProveedores.importeIva" id="importeIVA" disabled />
								</div>
							</div>
							<div class="col-xs-4 col-sm-3 form-group aa79b-no-padding">
								<label id="labelTotal" for="total" class="control-label col-xs-12"><spring:message code="label.total"/></label>
								<div id="divTotal" class="col-xs-12">
									<input type="text" class="form-control" id="totalVal" disabled />
									<input type="hidden" name="datosPagoProveedores.importeTotal" id="total" disabled />
								</div>
							</div>
					</fieldset>
				</div>
			</div>
		</form>
	</div>
	
	<div id="pestInfoAdicional">
		<div class="p-2">
			<div class="row">
				<div class="form-group col-lg-5">
					<label class="control-label" for="tipoTarea"><spring:message code="comun.tipoDeTarea"/>:</label>
					<label class="control-label" id="tipoTarea"></label>
					<input type="hidden" class="form-control" id="tipoTareaId" />
					
				</div>
				<div class="form-group col-lg-3">
					<label class="control-label" for="idiomaDestino"><spring:message code="label.idiomaDestino"/>:</label>
					<label class="control-label" id="idiomaDestino"></label>
				</div>
				<div class="form-group col-lg-3">
					<label class="control-label" for="facturable"><spring:message code="label.facturableTitle"/>:</label>
					<label class="control-label" id="facturable"></label>
				</div>
			</div>
		</div>
		<fieldset id="num_total_pal_filter_fieldset">
			<legend><spring:message code="label.documento.numTotalPal"></spring:message></legend>
			<div class="row">
				<div class="form-group col-lg-5">
					<label class="control-label" for="numPalSolicitado"><spring:message code="label.documento.numPalSolicitado"/>:</label>
					<label class="control-label" id="numPalSolicitado"></label>
				</div>
				<div class="form-group col-lg-4">
					<label class="control-label" for="numTotalPalIzo"><spring:message code="label.documento.numTotalPalIzo"/>:</label>
					<label class="control-label" id="numTotalPalIzo"></label>
				</div>
				
			</div>
			<div class="row" id="tradosInfoRow">
				<div class="form-group col-lg-5">
					<label class="control-label" for="numPalXmlTrados"><spring:message code="label.documento.numPalXmlTrados"/>:</label>
					<label class="control-label" id="numPalXmlTrados"></label>
				</div>
				<div class="form-group col-lg-2">
					<label class="control-label" for="tramosConcor1"><spring:message code="comun.tramosConcor1"/>:</label>
					<label class="control-label" id="tramosConcor1"></label>
				</div>
				<div class="form-group col-lg-2">
					<label class="control-label" for="tramosConcor2"><spring:message code="comun.tramosConcor2"/>:</label>
					<label class="control-label" id="tramosConcor2"></label>
				</div>
				<div class="form-group col-lg-2">
					<label class="control-label" for="tramosConcor3"><spring:message code="comun.tramosConcor3"/>:</label>
					<label class="control-label" id="tramosConcor3"></label>
				</div>
				
			</div>
			<div class="row">
				<fieldset id="palabrasPorDocumento_filter_fieldset">
					<legend><spring:message code="label.documento.numPalabrasDocumento"></spring:message></legend>
					<div id="palabrasPorDocumento_div" class="rup-table-container sinMargen">	
						<div id="palabrasPorDocumento_feedback"></div>
						<div id="palabrasPorDocumento_toolbar"></div>	
						<div id="contenFormularios" class="filterForm oculto">	
							<form id="palabrasPorDocumento_filter_form" class="form-horizontal">						<!-- Formulario de filtrado -->
								<div id="palabrasPorDocumento_filter_toolbar" class="formulario_legend"></div>	<!-- Barra de herramientas del formulario de filtrado -->
								<fieldset id="palabrasPorDocumento_filter_fieldset" class="rup-table-filter-fieldset">
									<input id="anyo_filter" name="anyo"></input>
									<input id="numExp_filter" name="numExp"></input>
									<input id="idTarea_filter" name="idTarea"></input>
								</fieldset>
							</form>
						</div>	
						<div class="palabrasPorDocumento_grid_div horizontal_scrollable_table" >
							<table id="palabrasPorDocumento" style="margin-left:0px !important;margin-right:0px !important;"></table>
							<div id="palabrasPorDocumento_pager"></div>
						</div>
					</div>		
				</fieldset>		
			</div>
		</fieldset>
		<fieldset id="tarea_aplicada_filter_fieldset">
			<legend><spring:message code="label.tarifaAplicada"></spring:message></legend>
			<div class="row">
				<div class="form-group col-lg-4">
					<label class="control-label" for="lote"><spring:message code="label.lote"/>:</label>
					<label class="control-label" id="lote"></label>
				</div>
				<div class="form-group col-lg-3">
					<label class="control-label" for="cif"><spring:message code="label.cif"/>:</label>
					<label class="control-label" id="cif"></label>
				</div>
				<div class="form-group col-lg-5">
					<label class="control-label" for="nombreEmpresa"><spring:message code="label.nombreEmpresa"/>:</label>
					<label class="control-label" id="nombreEmpresa"></label>
				</div>
			</div>
			<div class="row">
				<div class="form-group col-lg-4">
					<label class="control-label" for="fechaHoraEntregaIzo"><spring:message code="label.fechaHoraEntregaIzo"/>:</label>
					<label class="control-label" id="fechaHoraEntregaIzo"></label>
				</div>
				<div class="form-group col-lg-8">
					<label class="control-label" for="vigenciaLote"><spring:message code="label.vigenciaLote"/>:</label>
					<label class="control-label" id="vigenciaLote"></label>
				</div>
			</div>
			<div class="row">
				<div class="form-group col-lg-4">
					<label class="control-label" for="iva"><spring:message code="label.iva"/>:</label>
					<label class="control-label" id="iva"></label>
				</div>
				<div class="form-group col-lg-8">
					<label class="control-label" for="importePalabraIVA"><spring:message code="label.importePalabraIVA"/>:</label>
					<label class="control-label" id="importePalabraIVA"></label>
				</div>
			</div>
			<fieldset id="porcentaje_pago_filter_fieldset">
				<legend><spring:message code="label.porcentajePago"></spring:message></legend>
					<div class="form-group col-lg-3">
						<label class="control-label" for="porcentajePago8594"><spring:message code="comun.tramosConcor2"/>:</label>
						<label class="control-label" id="porcentajePago8594"></label>
					</div>
					<div class="form-group col-lg-3">
						<label class="control-label" for="porcentajePago95100"><spring:message code="comun.tramosConcor3"/>:</label>
						<label class="control-label" id="porcentajePago95100"></label>
					</div>
			</fieldset>
			<fieldset id="recargos_penalizaciones_filter_fieldset">
				<legend><spring:message code="label.recargosPenalizaciones"></spring:message></legend>
				<div class="row">
					<div class="form-group col-lg-3">
						<label class="control-label" for="formato"><spring:message code="label.formato"/>:</label>
						<label class="control-label" id="formato"></label>
					</div>
					<div class="form-group col-lg-3">
						<label class="control-label" for="apremio"><spring:message code="label.apremio"/>:</label>
						<label class="control-label" id="apremio"></label>
					</div>
					<div class="form-group col-lg-3">
						<label class="control-label" for="retraso"><spring:message code="label.retraso"/>:</label>
						<label class="control-label" id="retraso"></label>
					</div>
					<div class="form-group col-lg-3">
						<label class="control-label" for="tareaRevision"><spring:message code="label.tareaRevision"/>:</label>
						<label class="control-label" id="tareaRevision"></label>
					</div>
				</div>
			</fieldset>
		</fieldset>
		<fieldset id="penalizacionesPorCalidad_filter_fieldset">
			<legend><spring:message code="label.penalPorCalidad"></spring:message></legend>
			<div id="penalizacionesPorCalidad_div" class="rup-table-container sinMargen">	
				<div id="penalizacionesPorCalidad_feedback"></div>
				<div id="penalizacionesPorCalidad_toolbar"></div>	
				<div id="contenFormularios" class="filterForm oculto">	
					<form id="penalizacionesPorCalidad_filter_form" class="form-horizontal">						<!-- Formulario de filtrado -->
						<div id="penalizacionesPorCalidad_filter_toolbar" class="formulario_legend"></div>	<!-- Barra de herramientas del formulario de filtrado -->
						<fieldset id="penalizacionesPorCalidad_filter_fieldset" class="rup-table-filter-fieldset">
							<input id="estado_filter" name="estado" value="A"></input>
						</fieldset>
					</form>
				</div>	
				<div class="penalizacionesPorCalidad_grid_div horizontal_scrollable_table" >
					<table id="penalizacionesPorCalidad" style="margin-left:0px !important;margin-right:0px !important;"></table>
					<div id="penalizacionesPorCalidad_pager"></div>
				</div>
			</div>		
		</fieldset>		
	</div>
</div>

