<%@include file="/WEB-INF/includeTemplate.inc"%>
<%@page import="com.ejie.aa79b.model.enums.TipoEntidadEnum"%>
<%@page import="com.ejie.aa79b.model.enums.TipoExcepcionEnum"%>
<div id ="divDetalleRevDatosFactu">
	<%@include file="/WEB-INF/views/cabeceraexpediente/cabeceraexpediente.jsp"%>
	<div id="detalleRevDatosFactu_div" class="rup-table-container">
		<div id="detalleRevDatosFactu_feedback"></div>						<!-- Feedback de la tabla --> 
		<div id="detalleRevDatosFactu_toolbar"></div>	
		<div id="detalleRevDatosFactu_filter_div" class="filterForm"> <!-- Capa contenedora del formulario de filtrado -->
			<form id="detalleRevDatosFactu_filter_form" >
				<div id="detalleRevDatosFactu_filter_toolbar" class="formulario_legend"></div>
				<fieldset id="contactofacturacionexp_filter_fieldset" class="rup-table-filter-fieldset" >
					<input type="hidden" name="expediente.expedienteTradRev.datosFacturacionExpediente.idOrden" id="idOrden_detail_table" >
					<input type="hidden" name="expediente.expedienteTradRev.idIdioma" id="idIdioma_detail_table" >
					<input type="hidden" name="idTipoExp" id="idTipoExp_detail_table" >
					<input type="hidden" name="expediente.expedienteTradRev.anyo" id="anyo" >
					<input type="hidden" name="expediente.expedienteTradRev.numExp" id="numExp" >
					<input type="hidden" name="expediente.expedienteTradRev.datosFacturacionExpediente.importeBase" id="importeBaseValExp"/>
					<input type="hidden" name="expediente.expedienteTradRev.datosFacturacionExpediente.importeIva" id="importeIVAAplicadoValExp"/>
					<input type="hidden" name="expediente.expedienteTradRev.datosFacturacionExpediente.importeTotal" id="importeTotalValExp"/>
					<div class="p-2">
						<div class="row">
							<div class="form-group col-xl-4 col-lg-5">
								<label class="control-label col-xl-6 col-lg-8 fHSLabelWidth no-padding-left" for="fechaHoraSol"><spring:message code="label.fechaHoraSolicitud"/>:</label>
									<label class="control-label col-md-5 labelInput" id="fechaHoraSol" ></label>
								</div>
							<div class="form-group col-xl-4 col-lg-5">
								<label class="control-label col-md-3 fEILabelWidth no-padding-left" for="fechaHoraIZO"><spring:message code="label.fechaHoraEntregaIzo"/>:</label>
								<label class="control-label col-md-5 labelInput" id="fechaHoraIZO" ></label>
							</div>
							<div class="form-group col-xl-2 col-lg-2">
								<label class="control-label col-xl-4 col-lg-5 bopvLabelWidth no-padding-left" for="bopv"><spring:message code="label.bopv"/>:</label>
								<label class="col-xl-4 col-lg-4 control-label labelInput" id="bopv" ></label>
								<input type="hidden" class="form-control" id="indBopv" />
							</div>							
						</div>
						<div class="row">
							<div class="form-group col-xl-4 col-lg-4">
								<label class="control-label idiomaLabel no-padding-left" for="idioma"><spring:message code="label.idiomaRevDestTraduccion"/>:</label>
								<div class="no-padding-left divComboW125">
									<select id="idioma" class="form-control" name="expediente.expedienteTradRev.idIdioma"></select>	
								</div>
							</div>
						</div>
						<div class="row marginBot5">
							<div class="form-group col-md-3 factSwitchDiv ">
									<label class="no-padding-left" for="indFacturable"><spring:message code="label.expedienteFacturable"/>:</label>
									<div id="divFacturable" class="col-xl-5 col-lg-5 no-padding-left">
										<input type="checkbox" name="expediente.expedienteTradRev.indFacturable" class="form-control"
											id="indFacturable" value="S" data-switch-pestana="true" />
									</div>
							</div>
							<div class="form-group col-xl-2 col-lg-2">
									<label for="idRelevancia" class="no-padding-left" data-i18n="label.relevancia"><spring:message code="label.relevancia" />:</label>
									<div >
										<select id="idRelevancia" class="form-control" name="expediente.expedienteTradRev.idRelevancia"></select>	
									</div>
							</div>
							
								<div class="form-group col-xl-2 col-lg-2" style="padding-left: 0px;">
									<label class="no-padding-left" for="indDificil"><spring:message code="label.dificil"/>:</label>
									<div id="divIndDificil" class="col-xl-12 col-lg-12 pl-0">
										<input type="checkbox" name="expediente.expedienteTradRev.indDificil" class="form-control"
											id="indDificil" value="S" data-switch-pestana="true" />
									</div>
								</div>	
								<div class="form-group col-xl-2 col-lg-2" style="padding-left: 0px;">
									<!-- <label class="control-label col-xl-12 col-lg-12" for="indDificil"><spring:message code="label.dificil"/>:</label>
									<div id="divIndDificil" class="col-xl-5 col-lg-5">
										<input type="checkbox" name="expediente.expedienteTradRev.indDificil" class="form-control"
											id="indDificil" value="S" data-switch-pestana="true" />
									</div>-->
								</div>						
								<div class="form-group col-xl-2 col-lg-2" style="padding-left: 0px;">
									<label class="no-padding-left" for="indUrgente"><spring:message code="label.urgente"/>:</label>
									<div id="divIndUrgente" class="col-xl-12 col-lg-12 pl-0">
										<input type="checkbox" name="expediente.expedienteTradRev.indUrgente" class="form-control"
											id="indUrgente" value="S" data-switch-pestana="true" />
									</div>
								</div>	
						</div>
						<div id="divFacturableDatos">
							<fieldset id="tarifaAplicada_filter_fieldset">
								<legend><spring:message code="label.tarifaAplicadaOrdenPreciosPublicos"/></legend>
								<div class="p-2">
									<div class="row form-group">
										<div class="col-xs-12">
												<label for="titulo"><spring:message code="label.titulo"/>:</label>
												<label class="control-label" id="titulo"></label>
										</div>
									</div>
									<div class="row">
										<div class="form-group col-xl-6 col-lg-6">
											<label class="control-label col-xl-3 col-lg-4 tarifaPalLabel no-padding-left" for="tarifaPalabra"><spring:message code="label.tarifaEPorPalabra"/>:</label>
												<label class="col-xl-4 col-lg-4 control-label labelInput" id="tarifaPalabra" ></label>
												<input type="hidden" name="expediente.expedienteTradRev.datosFacturacionExpediente.tarifaPal" class="form-control" id="tarifaPalabraVal" ></input>
										</div>	
										<div class="form-group col-xl-6 col-lg-6">
											<label class="control-label col-xl-2 col-lg-3" for="porIva"><spring:message code="label.ivaPorcentaje"/>:</label>
												<label class="col-xl-2 col-lg-2 control-label labelInput" id="porIva" ></label>
												<input type="hidden" name="expediente.expedienteTradRev.datosFacturacionExpediente.porcentajeIva" class="form-control" id="porIvaVal" ></input>
										</div>	
									</div>		
									<div class="row">
										<div class="form-group col-xl-6 col-lg-6">
											<label class="control-label col-xl-6 col-lg-8 no-padding-left" for="recargoDificultad"><spring:message code="label.porcentajeRecargoEspDificultad"/>:</label>
												<label class="col-xl-4 col-lg-4 control-label labelInput" id="recargoDificultad" ></label>
												<input type="hidden" name="expediente.expedienteTradRev.datosFacturacionExpediente.importeDificultad" class="form-control" id="indRecargoDificultad" />
										</div>	
										<div class="form-group col-xl-6 col-lg-6">
											<label class="control-label col-xl-5 col-lg-7" for="recargoPorUrgencia"><spring:message code="label.porcentajeRecargoUrgencia"/>:</label>
												<label class="col-xl-4 col-lg-4 control-label labelInput" id="recargoPorUrgencia" ></label>
												<input type="hidden" name="expediente.expedienteTradRev.datosFacturacionExpediente.importeUrgencia" class="form-control" id="indRecargoPorUrgencia" />
										</div>	
										<div class="form-group col-lg-4 col-lg-6">
											<label class="control-label col-xl-3 col-lg-5 no-padding-left" for="precioMinimo"><spring:message code="label.precioMinimo"/>:</label>
												<label class="col-xl-4 col-lg-4 control-label labelInput" id="precioMinimo" ></label>
												<input type="hidden" class="form-control" id="precioMinimoVal" ></input>
										</div>	
									</div>	
									<fieldset id="precioTraduccionesTramos_filter_fieldset">
										<legend><spring:message code="label.precioTraduccionesTramos"/></legend>
										<div class="p-2">	
											<div class="row">
												<div class="form-group col-xl-10 col-lg-12">
													<label class="control-label col-xl-7 col-lg-8 tarifAplicLabel no-padding-left" for="numPalabrasTarif"><spring:message code="label.numPalabrasTarif"/>:</label>
													<label class="col-xl-2 col-lg-2 control-label labelInput" id="numPalabrasTarif" ></label>
												</div>	
												<div class="form-group col-xl-12 col-lg-12">
													<label for="porFacturarConcordancia" class="control-label col-xl-12 col-lg-12 no-padding-left"><spring:message code="label.porFacturarConcordancia"/>:</label>
												</div>
												<div class="col-xl-12 col-md-12" style="padding-left: 25px;">
												    <div class="col-xl-2 col-lg-3 form-group aa79b-no-padding">
														<label id="labelPalHoraConcor084" for="palHoraConcor084" class="control-label col-xl-5 col-lg-6 concor084DefaultWith no-padding-left"><spring:message code="comun.tramosConcor1"/>:</label>
															<label class="col-xl-4 col-lg-6 control-label labelInput" id="palHoraConcor084" ></label>
													</div>
													<div class="col-xl-2 col-lg-3 form-group aa79b-no-padding">
														<label id="labelPalHoraConcor8594" for="palHoraConcor8594" class="control-label col-xl-5 col-lg-6 concor8594DefaultWith no-padding-left"><spring:message code="comun.tramosConcor2"/>:</label>
														<label class="col-xl-4 col-lg-6 control-label labelInput" id="palHoraConcor8594" ></label>
													</div>
													<div class="col-xl-2 col-lg-3 form-group aa79b-no-padding">
														<label id="labelPalHoraConcor95100" for="palHoraConcor95100" class="control-label col-xl-5 col-lg-6 concor95100DefaultWith no-padding-left"><spring:message code="comun.tramosConcor3"/>:</label>
														<label class="col-xl-4 col-lg-6 control-label labelInput" id="palHoraConcor95100" ></label>
													</div>
												</div>
											</div>	
										</div>
									</fieldset>
								</div>		
							</fieldset>
							<div class="row">
								<div class="col-xl-6 col-lg-6">
									<fieldset id="numPalabrasExp_filter_fieldset" style="margin-left: 0px !important;">
										<legend><spring:message code="label.numPalExpediente"/></legend>
										<div class="p-2">	
											<div class="row">
												<div class="form-group col-xl-12 col-lg-12">
													<label class="control-label col-xl-5 col-lg-7  numPalLabel no-padding-left" for="numTotalPalSolic"><spring:message code="label.numTotPalSolic"/>:</label>
														<label class="col-xl-4 col-lg-4 control-label labelInput" id="numTotalPalSolic" ></label>
												</div>
												<div class="form-group col-xl-12 col-lg-12">
													<label class="control-label col-xl-5 col-lg-7  numPalLabel no-padding-left" for="numTotalPalIZO"><spring:message code="label.numTotPalIzo"/>:</label>
														<label class="col-xl-3 col-lg-3 control-label labelInput" id="numTotalPalIZO" ></label>
												</div>
											</div>	
											<div id="divPalabrasXml" >
												<div class="row">
													<div class="form-group col-xl-12 col-lg-12">
														<label class="control-label col-xl-5 col-lg-7  numPalLabel no-padding-left" for="numTotalPalXml"><spring:message code="label.numTotPalXml"/>:</label>
															<label class="col-xl-2 col-lg-2 control-label labelInput" id="numTotalPalXml" ></label>
													</div>
												</div>	
												<div class="row defaultRowHeight">
													<div class="col-xl-12 col-lg-12">
														<label class="control-label col-xl-5 col-lg-7  numPalLabel no-padding-left" for="concordancia084Label">&#8226; <spring:message code="label.concordancia084"/>:</label>
															<label class="col-xl-2 col-lg-2 control-label labelInput" id="concordancia084Label" ></label>
													</div>
												</div>
												<div class="row defaultRowHeight">
													<div class="col-xl-12 col-lg-12">
														<label class="control-label col-xl-5 col-lg-7  numPalLabel no-padding-left" for="concordancia8594Label">&#8226; <spring:message code="label.concordancia8594"/>:</label>
															<label class="col-xl-2 col-lg-2 control-label labelInput" id="concordancia8594Label" ></label>
													</div>
												</div>
												<div class="row defaultRowHeight">
													<div class="col-xl-12 col-lg-12">
														<label class="control-label col-xl-5 col-lg-7  numPalLabel no-padding-left" for="concordancia95100Label">&#8226; <spring:message code="label.concordancia95100"/>:</label>
														<label class="col-xl-2 col-lg-2 control-label labelInput" id="concordancia95100Label" ></label>
													</div>
												</div>
											</div>
										</div>
									</fieldset>
								</div>
								<div class="col-xl-6 col-lg-6">
									<fieldset id="numPalabrasFact_filter_fieldset">
										<legend><spring:message code="label.numPalFactCliente"/></legend>
										<div class="p-2">	
											<div class="row">
												<div class="form-group col-xl-12 col-lg-12">
													<label class="control-label col-xl-5 col-lg-7  numPalLabel no-padding-left" ></label>
												</div>
												<div class="form-group col-xl-12 col-lg-12">
													<label class="control-label col-xl-5 col-lg-7  numPalLabel no-padding-left" ></label>
												</div>
											</div>	
											<div class="row">
												<div class="col-xl-12 col-lg-12">
													<label class="control-label col-xl-3 col-lg-6 alignLabelWInput numPalLabel no-padding-left" for="numTotalPal"><spring:message code="label.documento.numTotalPal"/>:</label>
													<div class="form-group col-xl-2 col-lg-4">
														<input type="text" name="expediente.expedienteTradRev.datosFacturacionExpediente.numTotalPalFacturar" class="form-control numeric" id="numTotalPal" maxlength="6" />
													</div>
												</div>
											</div>	
											<div class="row">
												<div class="col-xl-12 col-lg-12">
													<label class="control-label col-xl-3 col-lg-6 alignLabelWInput numPalLabel no-padding-left" for="concordancia084">&#8226; <spring:message code="label.concordancia084"/>:</label>
													<div id="divConcordancia084" class="form-group col-xl-2 col-lg-4">
														<input type="text" name="expediente.expedienteTradRev.datosFacturacionExpediente.numPalConcor084" class="form-control numeric" id="concordancia084" maxlength="6" />
													</div>
												</div>
											</div>
											<div class="row">
												<div class="col-xl-12 col-lg-12">
													<label class="control-label col-xl-3 col-lg-6 alignLabelWInput numPalLabel no-padding-left" for="concordancia8594">&#8226; <spring:message code="label.concordancia8594"/>:</label>
													<div id="divConcordancia8594" class="form-group col-xl-2 col-lg-4">
														<input type="text" name="expediente.expedienteTradRev.datosFacturacionExpediente.numPalConcor8594" class="form-control numeric" id="concordancia8594" maxlength="6" />
													</div>
												</div>
											</div>
											<div class="row">
												<div class="col-xl-12 col-lg-12">
													<label class="control-label col-xl-3 col-lg-6 alignLabelWInput numPalLabel no-padding-left" for="concordancia95100">&#8226; <spring:message code="label.concordancia95100"/>:</label>
													<div id="divConcordancia95100" class="form-group col-xl-2 col-lg-4">
														<input type="text" name="expediente.expedienteTradRev.datosFacturacionExpediente.numPalConcor95100" class="form-control numeric" id="concordancia95100" maxlength="6" />
													</div>
												</div>
											</div>
										</div>
									</fieldset>
								</div>
							</div>
							
							<div class="form-group col-md-12" id="leyendaPptoRequerido">
								<p><span class="ico-ficha-encriptado"><i class="fa fa-exclamation-triangle" aria-hidden="true"></i></span> <spring:message code="label.expConPptoRequerido"/></p>						
							</div>
						</div>
					</div>
				</fieldset>
			</form>
			<div id="divEntidadContactoDatos" class="filterForm">
				<fieldset id="contactofacturacionexp_filter_fieldset" class="rup-table-filter-fieldset" >
					<div class="p-2">
						<fieldset id="detallecontactofacturacionexp_filter_fieldset" class="rup-table-filter-fieldset" >
							<legend><spring:message code="label.entidadContactoVaAFacturar"/></legend>
							<div class="p-2">
								<div id="contactofacturacionexp_div" class="rup-table-container container-fluid">
									<div id="contactofacturacionexp_feedback"></div> <!-- Feedback de la tabla -->	
									<div id="contactofacturacionexp_toolbar"></div> <!-- Botonera de la tabla -->
									<div id="contactofacturacionexp_filter_div" class="rup-table-filter filterForm" style="display:none">
										<form id="contactofacturacionexp_filter_form"> <!-- Formulario de filtrado -->
											<div id="contactofacturacionexp_filter_toolbar" class="formulario_legend"></div>	
											<fieldset id="contactofacturacionexp_filter_fieldset" class="rup-table-filter-fieldset">
												<div class="row">
													<!-- Campos del formulario de filtrado -->
														<input type="hidden" name="anyo" class="form-control numeric" id="anyo_filtro_fact" /> 
														<input type="hidden" name="numExp" class="form-control numeric" id="numExp_filtro_fact" />
													<!-- Fin campos del formulario de filtrado -->
												</div>
												<div id="contactofacturacionexp_filter_buttonSet" class="pull-right">
														<!-- Botón de filtrado -->
														<input id="contactofacturacionexp_filter_filterButton" type="button" class="ui-button ui-widget ui-state-default ui-corner-all" value='<spring:message code="filter" />' />
														<!-- Enlace de limpiar -->
														<a id="contactofacturacionexp_filter_cleanLink" href="javascript:void(0)" class="rup-enlaceCancelar"><spring:message code="clear" /></a>
													</div>
											</fieldset>
										</form>
									</div>
									<div class="contactofacturacionexp_grid_div horizontal_scrollable_table">
										<!-- Tabla -->
										<table id="contactofacturacionexp"></table>
										<!-- Barra de paginación -->
										<div id="contactofacturacionexp_pager" hidden></div>
									</div>
								</div>
								<div class="observacionesContainer" id="labelContactoNoVinculado" style="display:none;">
									<p><span class="ico-ficha-encriptado marginRigt5"><i class="fa fa-exclamation-circle" aria-hidden="true"></i></span><spring:message code="label.contactoNoVinculadoEntidad"/></p>
								</div>
								<div class="observacionesContainer">
									<label class="control-label col-xs-12 col-lg-12 pl-0" for="observacionesFacturacion"><spring:message code="label.observacionesFacturacion"/>:</label>
									<div id="divobservacionesFacturacion" class="col-xs-12 col-lg-12 pl-0 pr-0">
										<textarea class="control-label col-xs-12 col-lg-12" id="observacionesFacturacion" rows="2" cols="9" maxlength="4000" readonly ></textarea>
									</div>
								</div>	
							</div>
						</fieldset>
						<div class="col-xl-4 col-lg-6" style="float: right;">
								<fieldset id="importeFinal_filter_fieldset">
									<div class="p-2">	
										<div class="form-group col-xl-12 col-lg-12">
											<label class="control-label col-xl-10 col-lg-10" for="importeBase" style="text-align: right;"><spring:message code="label.importeBase"/>:</label>
											<div id="divImporteBase" class="col-xl-2 col-lg-2">
												<label class="control-label" id="importeBase" ></label>
												<input type="hidden" class="form-control" id="importeBaseVal"/>
											</div>
										</div>
										<div class="form-group col-xl-12 col-lg-12">
											<label class="control-label col-xl-10 col-lg-10" for="importeIva" style="text-align: right;"><spring:message code="label.importeIVAAplicado"/>:</label>
											<div id="divImporteIva" class="col-xl-2 col-lg-2">
												<label class="control-label" id="importeIva" ></label>
												<input type="hidden" class="form-control" id="importeIVAAplicadoVal"/>
											</div>
										</div>						
										<div class="form-group col-xl-12 col-lg-12">
											<label class="control-label col-xl-10 col-lg-10" for="importeTotal" style="text-align: right;text-transform: uppercase;font-weight: bold;"><spring:message code="label.total"/>:</label>
											<div id="divImporteTotal" class="col-xl-2 col-lg-2">
												<label class="control-label" id="importeTotal" style="font-weight: bold;" ></label>
												<input type="hidden" class="form-control" id="importeTotalVal"/>
											</div>
										</div>
									</div>
								</fieldset>						
							</div>
					</div>
				</fieldset>
			</div>
		</div>
	</div>
</div>
<div id="contactofacturacionexp_detail_div" class="rup-table-formEdit-detail aa79b-content-modal">
	<!-- div id ="contactofacturacionexp_detail_navigation"></div-->			<!-- Barra de navegación del detalle -->
	<div class="ui-dialog-content ui-widget-content" >
		<form id="contactofacturacionexp_detail_form">					<!-- Formulario -->
			<div id ="contactofacturacionexp_detail_feedback"></div>		<!-- Feedback del formulario de detalle -->
			<fieldset class="form_fieldset">		
				<!-- Campos del formulario de detalle -->
				<input type="hidden" name="anyo" id="anyo058_detail_table" >
				<input type="hidden" name="numExp" id="numExp058_detail_table" >
				
				<input type="hidden" name="id058" class="formulario_linea_input" id="id058_detail_table"/>
				<input type="hidden" name="tipoEntidadAsoc058" class="formulario_linea_input" id="tipoEntidadAsoc058_detail_table"/>
				<input type="hidden" name="idEntidadAsoc058" class="formulario_linea_input" id="idEntidadAsoc058_detail_table"/>
				<input type="hidden" id="importeDificultadTabla" >
				<input type="hidden" id="importeUrgenciaTabla" >
				<input type="hidden" id="importeTotalTabla" >
				<input type="hidden" id="importeBaseTabla" >
				<input type="hidden" id="importeIvaTabla" >
									
				<div class="form-group col-md-12">
					<label for="idTiposEntidad_detail_table" class="control-label" data-i18n="label.tipoEntidad"><spring:message code="label.tipoEntidad"/>:</label>
					<div id="idTiposEntidad_detail_table">
						<div class="col-md-3">
							<input type="radio" name="tipoEntidadFact" id="tipoEntidad_T" value="" data-on-text='<spring:message code="label.todas"/>'/>
							<label for="tipoEntidad_T" class="radio-inline rup-table-filter-fieldset" data-i18n="label.tipoEntidad"><spring:message code="label.todas"/></label>
						</div>
						<c:set var="labelsTipoEntidad" value="<%=TipoEntidadEnum.values()%>"/>
						<c:forEach items="${labelsTipoEntidad}" var="tipoEntidad">
						<div class="col-md-3">
							<input type="radio" name="tipoEntidadFact" id="tipoEntidad_${tipoEntidad.value}" value="${tipoEntidad.value}"  data-on-text='<spring:message code="${tipoEntidad.label}"/>'/>
							<label for="tipoEntidad_${tipoEntidad.value}" class="radio-inline rup-table-filter-fieldset" data-i18n="label.tipoEntidad"><spring:message code="${tipoEntidad.label}"/></label>
						</div>
						</c:forEach>
					</div>
				</div>
				<div class="form-group col-md-12">
					<label for="idEntidadAsociada" class="control-label" data-i18n="label.entidadAsociada"><spring:message code="label.entidadAsociada" /> (*):</label>
					<input id="idEntidadAsociada" class="form-control" name="idEntidadAsoc058_autocomplete"/>
				</div>
				
				
				<div class="form-group col-md-12" style="margin-top:2rem">
					<label for="dniContacto058_detail_table" class="control-label" data-i18n="label.contacto"><spring:message code="label.contacto" />:</label>
<!-- 						<input id="dniContacto058_detail_table" class="form-control" name="dniContacto058"/> -->
					<select id="dniContacto058_detail_table" name="dniContacto058" class="form-control"></select>
				</div>
				
				<div class="form-group col-md-12" id="capaPorcentaje">
					<label for="porFactura058_detail_table" class="control-label"><spring:message code="label.porcentajeFactAplica"/> (*):</label>
					<input type="text" name="porFactura058" class="form-control numeric col-30por" id="porFactura058_detail_table" maxlength="3"/>
				</div>
				<!-- Fin campos del formulario de detalle -->
				
			</fieldset>
		</form>
	</div>
	<!-- Botonera del formulario de detalle -->
	<div class="rup-table-buttonpane ui-widget-content ui-helper-clearfix">
		<div class="ui-dialog-buttonset">
			<!-- Botón Guardar -->
			<button id="contactofacturacionexp_detail_button_save" type="button" class="ui-button ui-corner-all ui-widget">
				<spring:message code="save" />
			</button>
			<!-- Enlace cancelar -->
			<a href="javascript:void(0)" id="contactofacturacionexp_detail_link_cancel"
				class="rup-enlaceCancelar"><spring:message code="cancel" /></a>
		</div>
	</div>
</div>
