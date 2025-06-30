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
					<input type="hidden" name="expediente.expedienteInterpretacion.indActoFacturable" id="tipoFacturacionInter">
					<input type="hidden" name="expediente.expedienteInterpretacion.datosFacturacion.tipoIva" id="tipoIvaInter">
					<input type="hidden" name="expediente.expedienteInterpretacion.datosFacturacion.precioMinimoInterpRealiz" id="precioMinInter">
					<input type="hidden" name="expediente.expedienteInterpretacion.datosFacturacion.precioHoraInterprete" id="precioHoraInterpreteInter">
					<input type="hidden" name="expediente.expedienteInterpretacion.datosFacturacion.precioJornadaCompleta" id="precioJornadaCompletaInter">
					<input type="hidden" name="expediente.expedienteInterpretacion.datosFacturacion.precioMediaJornada" id="precioMediaJornadaInter">
					<input type="hidden" name="expediente.expedienteInterpretacion.datosFacturacion.precioHoraFraccAdic" id="precioHoraAdicInter">
					<input type="hidden" name="expediente.expedienteInterpretacion.datosFacturacion.jornadaCompletaHorasDesde" id="jornadaCompletaHorasDesde">
					<input type="hidden" name="expediente.expedienteInterpretacion.datosFacturacion.jornadaCompletaHorasHasta" id="jornadaCompletaHorasHasta">
					<input type="hidden" name="expediente.expedienteInterpretacion.datosFacturacion.mediaJornadaHorasDesde" id="mediaJornadaHorasDesde">
					<input type="hidden" name="expediente.expedienteInterpretacion.datosFacturacion.mediaJornadaHorasHasta" id="mediaJornadaHorasHasta">
					<input type="hidden" name="expediente.expedienteInterpretacion.datosFacturacion.importeBase" id="importeBaseVal"/>
					<input type="hidden" name="expediente.expedienteInterpretacion.datosFacturacion.importeIva" id="importeIVAAplicadoVal"/>
					<input type="hidden" name="expediente.expedienteInterpretacion.datosFacturacion.importeTotal" id="importeTotalVal"/>
					<input type="hidden" name="expediente.anyo" id="anyo" >
					<input type="hidden" name="expediente.numExp" id="numExp" >
					<input type="hidden" id="importeBaseEntidadAux" >
					<input type="hidden" id="importeIvaEntidadAux" >
					<input type="hidden" id="importeEntidadAux" >
					<div class="p-2">
						<div class="row marginTop5 marginBot5">
							<%@include file="/WEB-INF/views/calculoexpedientefacturacion/datosGeneralesExp.jsp"%>
						</div>
						<div class="row marginBot5">
							<div class="col-md-3 factSwitchDiv">
								<label class="" for="indFacturable"><spring:message code="label.expedienteFacturable"/>:</label>
								<input type="checkbox" name="expediente.expedienteInterpretacion.indFacturable" class="form-control"
										id="indFacturable" value="S" data-switch-pestana="true" disabled="disabled"/>
							</div>
							<%@include file="/WEB-INF/views/calculoexpedientefacturacion/datosExpFacturableInterp01.jsp"%>
						</div>
						<%@include file="/WEB-INF/views/calculoexpedientefacturacion/datosExpFacturableInterp02.jsp"%>
						<div class="row marginBot5">
							<div class="control-label col-md-3">
								<label for="numInterpretes" class="no-padding-left"><spring:message code="label.nInterpretes"></spring:message>: </label>
								<div id="numInterpretesDiv">
									<input type="text" name="expediente.expedienteInterpretacion.datosFacturacion.numInterpretes" class="form-control col-20por numeric" id="numInterpretes" maxlength="2">
								</div>
							</div>
							<div class="control-label col-md-5">
								<label for="horasReales" class=""><spring:message code="label.horasRealesInterpPorInterprete"></spring:message>: </label>
								<div id="horasRealesDiv">
									<input type="text" name="expediente.expedienteInterpretacion.datosFacturacion.horasInterpretacion" class="form-control col-50por" id="horasReales" maxlength="150" onkeyup="validarFormatoHoras(this);">
								</div>
							</div>
						</div>
						
						<fieldset>
							<%@include file="/WEB-INF/views/calculoexpedientefacturacion/datosOrdenPreciosPublicos.jsp"%>
							<div class="row">
								<%@include file="/WEB-INF/views/calculoexpedientefacturacion/datosIva.jsp"%>
							</div>
							<div class="row marginBot5">
								<%@include file="/WEB-INF/views/calculoexpedientefacturacion/datosExpFacturableInterp06.jsp"%>
							</div>
							<div class="row marginBot5">
								<%@include file="/WEB-INF/views/calculoexpedientefacturacion/datosExpFacturableInterp05.jsp"%>
							</div>
						</fieldset>
					</div>
				</fieldset>
			</form>
			<div id="divEntidadContactoDatos">
				<fieldset id="contactofacturacionexp_filter_fieldset" class="rup-table-filter-fieldset" >
					<div class="p-2">
						<fieldset id="detallecontactofacturacionexp_filter_fieldset" class="rup-table-filter-fieldset" >
							<legend><spring:message code="label.entidadContactoVaAFacturar"/></legend>
							<div class="p-2">
								<div id="contactofacturacionexp_div" class="rup-table-container container-fluid">
									<div id="contactofacturacionexp_feedback"></div> <!-- Feedback de la tabla -->	
									<div id="contactofacturacionexp_toolbar"></div> <!-- Botonera de la tabla -->
									<div id="contactofacturacionexp_filter_div" class="rup-table-filter" style="display:none">
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
								<div class="observacionesContainer" id="labelContactoNoVinculado">
									<p><span class="ico-ficha-encriptado marginRigt5"><i class="fa fa-exclamation-circle" aria-hidden="true"></i></span><spring:message code="label.contactoNoVinculadoEntidad"/></p>
								</div>
								<div class="observacionesContainer">
									<label class="control-label col-xs-12 col-lg-12 pl-0" for="observacionesFacturacion"><spring:message code="label.observacionesFacturacion"/>:</label>
									<div id="divobservacionesFacturacion" class="col-xs-12 col-lg-12 pl-0 pr-0">
										<textarea class="control-label col-xs-12 col-lg-12" id="observacionesFacturacion" rows="4" cols="9" maxlength="4000" readonly ></textarea>
									</div>
								</div>	
							</div>
						</fieldset>
					</div>
				</fieldset>
			</div>
			<div style="text-align:right" class="marginTop5 marginBot5">
				<div class="container-fluid" >
					<label class="control-label col-md-11 no-padding-right" data-i18n="label.importeBase "><spring:message code="label.importeBase" />:</label>
					<label class="control-label col-md-1 labelInput" id="importeBaseTotalCalculo"></label>
				</div>
				<div class="container-fluid">
					<label class="control-label col-md-11 no-padding-right" data-i18n="label.importeIVAAplicado"><spring:message code="label.importeIVAAplicado" />:</label>
					<label class="control-label col-md-1 labelInput" id="importeIVAAplicadoTotalCalculo"></label>
				</div>
				<div class="container-fluid">
					<label class="control-label col-md-11 no-padding-right negrita" data-i18n="label.totalMayus"><spring:message code="label.totalMayus" />:</label>
					<label class="control-label col-md-1 labelInput negrita" id="totalCalculo"></label>
				</div>
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