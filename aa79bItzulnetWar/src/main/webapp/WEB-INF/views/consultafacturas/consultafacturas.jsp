<%@page import="com.ejie.aa79b.model.enums.EstadoFacturaEnum"%>
<%@page import="com.ejie.aa79b.model.enums.EstadoEFacturaEnum"%>
<%@include file="/WEB-INF/includeTemplate.inc"%>

<div id="divConsultaFacturasCapa">
	<div id="divConsultaFacturasGeneral">
		<div id="divConsultaFacturasExpediente">
			<div class="container-fluid aa79b-fade in" id="divBusquedaFact">
				<h2><spring:message code="menu.consultaFacturas"></spring:message></h2>
					<div id="busquedaFact_div" class="rup-table-container">
					<div id="busquedaFact_feedback"></div>						<!-- Feedback de la tabla --> 
					<div id="busquedaFact_toolbar"></div>						<!-- Botonera de la tabla --> 
					<div id="busquedaFact_filter_div" class="filterForm"> <!-- Capa contenedora del formulario de filtrado -->
						<form id="busquedaFact_filter_form" class="form-horizontal">	
							<div id="busquedaFact_filter_toolbar" class="formulario_legend"></div>
							<fieldset id="busquedaFact_filter_fieldset" class="rup-table-filter-fieldset ">
								<div class="p-2">
									<div class="row">
										<div class="form-group col-md-2">
											<label for="idFactura_filter_table" class="control-label"><spring:message code="comun.numFactura"/>:</label>
											<input type="text" id="idFactura_filter_table" name="idFactura" class="col-xs-10 numeric valid" maxlength="10" >
										</div>
										<div class="form-group col-md-2">
											<label for="refLiquidacion_filter_table" class="control-label no-padding-left"><spring:message code="comun.refLiquidacion"/>:</label>
											<input type="text" id="refLiquidacion_filter_table" name="idLiquidacion"  class="col-xs-10 numeric valid" maxlength="11" >
										</div>
										<div class="col-md-6">
											<label class="control-label col-lg-12 p-0" data-i18n="label.fechaEmision"><spring:message code="label.fechaEmision" />:</label>
											<div class="form-group col-lg-5 p-0">
												<label for="fechaEmisionDesde_filter" class="control-label col-md-3 p-0 valFecha" data-i18n="label.desde"><spring:message code="label.desde" />:</label>
												<div class="form-group  col-md-9 p-0 grupoFecha">
													<input type="text" class="form-control" id="fechaEmisionDesde_filter" name="fechaEmisionIni"  class="col-xs-5"  >
												</div>
											</div>
											<div class="form-group col-lg-5 p-0">
												<label for="fechaEmisionHasta_filter" class="control-label col-md-3 p-0 valFecha" data-i18n="label.hasta"><spring:message code="label.hasta" />:</label>
												<div class="form-group  col-md-9 p-0 grupoFecha">
													<input type="text" class="form-control" id="fechaEmisionHasta_filter" name="fechaEmisionFin"  class="col-xs-5" >
												</div>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="form-group col-lg-12 no-padding">
											<fieldset>
												<legend>
													<spring:message code="label.entidadContactoAFacturar"></spring:message>
												</legend>
												<div class="row">
													<div class="form-group col-xs-2 ">
														<label for="cifEntidad_filter_table" class="control-label" data-i18n="label.cifEntidad"><spring:message code="label.cifEntidad"/>:</label>
														<input type="text" id="cifEntidad_filter_table" name="persona.entidad.cif"  class="col-xs-8 form-control" maxlength="10" >
													</div>
													<div class="form-group col-xs-6 ">
														<label for="nombreEntidad_filter_table" class="control-label" data-i18n="label.nombreEntidad"><spring:message code="label.nombreEntidad"/>:</label>
														<input type="text" name="persona.entidad.codigoCompleto" class="form-control" id="nombreEntidad_filter_table" maxlength="50" />
													</div>
												</div>
												<div class="row">
													<div class="form-group col-xs-2 ">
														<label for="contacto_filter_table" class="control-label" data-i18n="label.nifContacto"><spring:message code="label.nifContacto" />:</label>
														<input type="text" id="contacto_filter_table" class="col-xs-8 form-control" name="persona.dni" maxlength="12" />
													</div>
													<div class="form-group col-xs-6 " id="autocompleteContactoContainer">
														<label for="nombreApellidosContacto_filter_table" class="control-label" data-i18n="label.nombreApellidosContacto"><spring:message code="label.nombreContacto" />:</label>
														<input type="text" id="nombreApellidosContacto_filter_table" class="form-control" name="persona.dniContacto" maxlength="120" />
													</div>
												</div>
											</fieldset>
										</div>
									</div>
									<div class="row">
										<div class="form-group col-md-3">
											<label for="estadoFactura_filter_table" class="control-label "><spring:message code="label.estadoFactura"/>:</label>
<!-- 											<select name="datosFacturacionInterpretacion.estadoFactura.indEstadoFactura" class="form-control" id="estadoFactura_filter_table"> -->
<%-- 												<option value=""><spring:message code="combo.todos" /></option> --%>
<%-- 												<option value="<%=EstadoFacturaEnum.ANULADA.getValue()%>"><spring:message code="<%=EstadoFacturaEnum.ANULADA.getLabel()%>"/></option> --%>
<%-- 												<option value="<%=EstadoFacturaEnum.DEVOLUCION_INGRESO.getValue()%>"><spring:message code="<%=EstadoFacturaEnum.DEVOLUCION_INGRESO.getLabel()%>"/></option> --%>
<%-- 												<option value="<%=EstadoFacturaEnum.ERRONEO.getValue()%>"><spring:message code="<%=EstadoFacturaEnum.ERRONEO.getLabel()%>"/></option> --%>
<%-- 												<option value="<%=EstadoFacturaEnum.PENDIENTE_COBRO.getValue()%>"><spring:message code="<%=EstadoFacturaEnum.PENDIENTE_COBRO.getLabel()%>"/></option> --%>
<%-- 												<option value="<%=EstadoFacturaEnum.COBRADO.getValue()%>"><spring:message code="<%=EstadoFacturaEnum.COBRADO.getLabel()%>"/></option> --%>
<!-- 											</select> -->
											<select id="estadoFactura_filter_table" class="form-control" name="datosFacturacionInterpretacion.estadoFactura.indEstadoFactura"></select>
										</div>
										<div class="form-group col-md-3">
											<label for="estadoEFactura_filter_table" class="control-label "><spring:message code="label.estadoBusquedaEFactura"/>:</label>
<!-- 											<select name="datosFacturacionInterpretacion.estadoFacturaE.indEstadoEFactura" class="form-control" id="estadoEFactura_filter_table"> -->
<%-- 												<option value=""><spring:message code="combo.todos" /></option> --%>
<%-- 												<option value="<%=EstadoEFacturaEnum.SIN_INICIAR_PROCESO.getValue()%>"><spring:message code="<%=EstadoEFacturaEnum.SIN_INICIAR_PROCESO.getLabel()%>"/></option> --%>
<%-- 												<option value="<%=EstadoEFacturaEnum.PROCESO_INCOMPLETO.getValue()%>"><spring:message code="<%=EstadoEFacturaEnum.PROCESO_INCOMPLETO.getLabel()%>"/></option> --%>
<%-- 												<option value="<%=EstadoEFacturaEnum.ENVIADA_WS.getValue()%>"><spring:message code="<%=EstadoEFacturaEnum.ENVIADA_WS.getLabel()%>"/></option> --%>
<%-- 												<option value="<%=EstadoEFacturaEnum.ENVIADA_IZENPE.getValue()%>"><spring:message code="<%=EstadoEFacturaEnum.ENVIADA_IZENPE.getLabel()%>"/></option> --%>
<%-- 												<option value="<%=EstadoEFacturaEnum.ENVIADA_MANUAL.getValue()%>"><spring:message code="<%=EstadoEFacturaEnum.ENVIADA_MANUAL.getLabel()%>"/></option> --%>
<%-- 												<option value="<%=EstadoEFacturaEnum.REALIZADA_FACTURA_E.getValue()%>"><spring:message code="<%=EstadoEFacturaEnum.REALIZADA_FACTURA_E.getLabel()%>"/></option> --%>
<%-- 												<option value="<%=EstadoEFacturaEnum.ERROR_ENVIAR.getValue()%>"><spring:message code="<%=EstadoEFacturaEnum.ERROR_ENVIAR.getLabel()%>"/></option> --%>
<%-- 												<option value="<%=EstadoEFacturaEnum.ENVIADA_PORTAL.getValue()%>"><spring:message code="<%=EstadoEFacturaEnum.ENVIADA_PORTAL.getLabel()%>"/></option> --%>
<%-- 												<option value="<%=EstadoEFacturaEnum.TASAS.getValue()%>"><spring:message code="<%=EstadoEFacturaEnum.TASAS.getLabel()%>"/></option> --%>
<%-- 												<option value="<%=EstadoEFacturaEnum.FACTURA_RECHAZADA.getValue()%>"><spring:message code="<%=EstadoEFacturaEnum.FACTURA_RECHAZADA.getLabel()%>"/></option> --%>
<!-- 											</select> -->
											<select id="estadoEFactura_filter_table" class="form-control" name="datosFacturacionInterpretacion.estadoFacturaE.indEstadoEFactura"></select>
										</div>
										<div class="form-group col-md-3">
											<label for="numExp_filter_table" class="control-label"><spring:message code="label.numeroExpediente"/>:</label>
											<div>
												<label for="anyo_filter_table" class="control-label oculto"><spring:message code="label.anyo"/></label>
												<input type="text" name="anyoExp" class=" numeric" style="width: 40px;" id="anyo_filter_table" maxlength="2"/>
												<label class="control-label" style="width: 3%;">/</label>
												<input type="text" name="numExp" class=" numeric" style="width: 75px;" id="numExp_filter_table" maxlength="6"/>
										</div>
								</div>
									</div>
								</div>
								<div id="busquedaFact_filter_buttonSet" class="pull-right" style="margin:0 1rem 1rem 0">
									<!-- Botón de filtrado -->
									<button id="busquedaFact_filter_filterButton" type="button" class="ui-button ui-widget ui-state-default ui-corner-all">
										<spring:message code="filter" />
									</button>
									<!-- Enlace de limpiar -->
									<a id="busquedaFact_filter_cleanLinkModificado" href="javascript:void(0)" class="rup-enlaceCancelar"><spring:message code="clear" /></a>
								</div>
							</fieldset>
						</form>
					</div>
					<div class="busquedaFact_grid_div horizontal_scrollable_table" >
						<!-- Tabla -->
						<table id="busquedaFact"></table>
						<!-- Barra de paginación -->
						<div id="busquedaFact_pager"></div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
