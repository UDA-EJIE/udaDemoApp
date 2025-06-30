<%@page import="com.ejie.aa79b.model.enums.LotesVigentesEnum"%>
<%@page import="com.ejie.aa79b.model.enums.TipoPenalizacionRetrasoEnum"%>
<%@page import="com.ejie.aa79b.model.enums.TipoEntidadEnum"%>
<%@include file="/WEB-INF/includeTemplate.inc"%>
<div class="container-fluid aa79b-fade in" id="divLotes">
	<h2><spring:message code="menu.lotes"/></h2>
		<hr class="mb-2">
		<div id="lotes_div" class="rup-table-container">
		<div id="lotes_filter_div" class="rup-table-filter">	<!-- Capa contenedora del formulario de filtrado -->
				<form id="lotes_filter_form">						<!-- Formulario de filtrado -->
				<div id="lotes_filter_toolbar" class="formulario_legend"></div>	<!-- Barra de herramientas del formulario de filtrado -->
				<fieldset id="lotes_filter_fieldset" class="rup-table-filter-fieldset">
					<div class="row">
						<!-- Campos del formulario de filtrado -->
						<div class="col-xs-12 col-md-3">
							<div class="form-group">
								<label for="cif_filter_table" class="control-label col-xs-12" data-i18n="label.cif"><spring:message code="label.cif"/>:</label>
								<div class="col-xs-12">	
									<input type="text" name="cif" class="formulario_linea_input" id="cif_filter_table" maxlength="10" />
								</div>
							</div>
						</div>
						<div class="col-xs-12 col-md-3">
							<div class="form-group">
								<label for="idEmpresaProv_filter_table" class="control-label col-xs-12"><spring:message code="label.empresa"/>:</label>
								<div class="col-xs-12">		
									<select name="codigo" id="idEmpresaProv_filter_table" class="form-control">
									</select>
								</div>
							</div>
						</div>
						<div class="col-xs-12 col-md-3">
							<div class="form-group">
								<label for="idLote_filter_table" class="control-label col-xs-12"><spring:message code="label.lotesVigentes"/>:</label>
								<div class="col-xs-12">		
									<select name="lotesVigentes" class="form-control" id="idLote_filter_table">
										<option value="" selected="selected"><spring:message code="combo.todos"/></option>
									    <option value="<%=LotesVigentesEnum.SI.getValue()%>"><spring:message code="<%=LotesVigentesEnum.SI.getLabel()%>"/></option>
									    <option value="<%=LotesVigentesEnum.NO.getValue()%>"><spring:message code="<%=LotesVigentesEnum.NO.getLabel()%>"/></option>
									</select>
								</div>
							</div>
						</div>
						<!-- Fin campos del formulario de filtrado -->
					</div>
					<div id="lotes_fieldset" class="rup-table-filter-fieldset">
						<legend>(*) <spring:message code="label.requisitosConfiguracionLotes"/></legend>
					</div>
					<!-- Botonera del formulario de filtrado -->
					<div id="lotes_filter_buttonSet" class="pull-right">
						<!-- Botón de filtrado -->
						<input id="lotes_filter_filterButton" type="button" class="ui-button ui-widget ui-state-default ui-corner-all" value='<spring:message code="filter" />' />
						<!-- Enlace de limpiar -->
						<a id="lotes_filter_cleanLink" href="javascript:void(0)" class="rup-enlaceCancelar"><spring:message code="clear" /></a>
					</div>
				</fieldset>
			</form>
		</div>
	
		<div id="lotes_grid_div">
			<!-- Tabla -->
			<table id="lotes"></table>
			<!-- Barra de paginación -->
			<div id="lotes_pager"></div>
		</div>
	</div>	
</div>

<div id="divLotesDetalle" class="aa79b-fade collapsed">
	<div class="container-fluid">
		<h2>
			<spring:message code="menu.lotes"></spring:message>
			<small><spring:message code="label.detalle.empresa"></spring:message></small>
		</h2>
		<hr class="mb-2">
			<div id="lotesdetalle_feedback"></div>
			<div id="lotesdetalle_toolbar"></div>	
			<fieldset class="rup-table-filter-fieldset">
				<legend><spring:message code="label.empresa"></spring:message></legend>
				<input type="hidden" name="esProveedora" id="esEmpresaProveedora">
				<div class="row">
					<div class="col-xs-12 col-md-2">
						<div class="col-xs-12">
							<label for="cif_detalle_empresa" class="control-label col-xs-12 no-padding-left"><spring:message code="label.cif"/>:</label>		
							<input type="text" name="cif_detalle_empresa" class="form-control col-66por" id="cif_detalle_empresa"  readonly="readonly" disabled="disabled">
						</div>
					</div>
					<div class="col-xs-12 col-md-4">
						<div class="col-xs-12">
							<label for="nombre_detalle_empresa" class="control-label col-xs-12 no-padding-left"><spring:message code="label.nombre"/>:</label>		
							<input type="text" name="nombre_detalle_empresa" class="form-control"  id="nombre_detalle_empresa"  readonly="readonly" disabled="disabled">
						</div>
					</div>
					<div class="col-xs-12 col-md-2">
						<div class="col-xs-12">		
							<label for="situacion_detalle_empresa" class="control-label col-xs-12 no-padding-left"><spring:message code="label.situacion"/>:</label>
							<input type="text" name="situacion_detalle_empresa" class="form-control col-66por"  id="situacion_detalle_empresa" readonly="readonly" disabled="disabled">
						</div>
					</div>
					<div class="col-xs-12 col-md-2">
						<div class="col-xs-12">		
							<label for="facturable_detalle_empresa" class="control-label col-xs-12 no-padding-left"><spring:message code="label.entidadFacturable"/>:</label>
							<input type="checkbox" name="facturable_detalle_empresa" id="facturable_detalle_empresa" value="S" data-switch="true" disabled="disabled">
						</div>
					</div>
					<div class="col-xs-12 col-md-2">
						<div class="col-xs-12">		
							<label for="iva_detalle_empresa" class="control-label col-xs-12 no-padding-left"><spring:message code="label.aplicaIva"/>:</label>
							<input type="checkbox" name="iva_detalle_empresa" id="iva_detalle_empresa" value="S" data-switch="true" disabled="disabled">
						</div>
					</div>
				</div>
				<fieldset>
					<legend><spring:message code="label.direccion"></spring:message></legend>
					<div class="row">
						<div class="col-xs-12 col-md-2">
							<div class="col-xs-12">		
								<label for="provincia_detalle_empresa" class="control-label col-xs-12 no-padding-left"><spring:message code="label.provincia"/>:</label>
								<input type="text" name="provincia_detalle_empresa" class="form-control"  id="provincia_detalle_empresa" readonly="readonly" disabled="disabled">
							</div>
						</div>
						<div class="col-xs-12 col-md-2">
							<div class="col-xs-12">
								<label for="municipio_detalle_empresa" class="control-label col-xs-12 no-padding-left"><spring:message code="label.municipio"/>:</label>		
								<input type="text" name="municipio_detalle_empresa" class="form-control"  id="municipio_detalle_empresa" readonly="readonly" disabled="disabled">
							</div>
						</div>
						<div class="col-xs-12 col-md-2">
							<div class="col-xs-12">		
								<label for="localidad_detalle_empresa" class="control-label col-xs-12 no-padding-left"><spring:message code="label.localidad"/>:</label>
								<input type="text" name="localidad_detalle_empresa" class="form-control"  id="localidad_detalle_empresa" readonly="readonly" disabled="disabled">
							</div>
						</div>
						<div class="col-xs-12 col-md-2">
							<div class="col-xs-12">
								<label for="codPostal_detalle_empresa" class="control-label col-xs-12 no-padding-left"><spring:message code="label.codigoPostal"/>:</label>		
								<input type="text" name="codPostal_detalle_empresa" class="form-control"  id="codPostal_detalle_empresa" readonly="readonly" disabled="disabled">
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-xs-12 col-md-7">
							<div class="col-xs-12">
								<label for="calle_detalle_empresa" class="control-label col-xs-12 no-padding-left"><spring:message code="label.calle"/>:</label>		
								<input type="text" name="calle_detalle_empresa" class="form-control"  id="calle_detalle_empresa" readonly="readonly" disabled="disabled">
							</div>
						</div>
						<div class="col-xs-12 col-md-1">
							<div class="col-xs-12">		
								<label for="portal_detalle_empresa" class="control-label col-xs-12 no-padding-left"><spring:message code="label.portal"/>:</label>
								<input type="text" name="portal_detalle_empresa" class="form-control"  id="portal_detalle_empresa" readonly="readonly" disabled="disabled">
							</div>
						</div>
						<div class="col-xs-12 col-md-1">
							<div class="col-xs-12">
								<label for="escalera_detalle_empresa" class="control-label col-xs-12 no-padding-left"><spring:message code="label.escalera"/>:</label>		
								<input type="text" name="escalera_detalle_empresa" class="form-control"  id="escalera_detalle_empresa" readonly="readonly" disabled="disabled">
							</div>
						</div>
						<div class="col-xs-12 col-md-1">
							<div class="col-xs-12">		
								<label for="piso_detalle_empresa" class="control-label col-xs-12 no-padding-left"><spring:message code="label.piso"/>:</label>
								<input type="text" name="piso_detalle_empresa" class="form-control"  id="piso_detalle_empresa" readonly="readonly" disabled="disabled">
							</div>
						</div>
						<div class="col-xs-12 col-md-1">
							<div class="col-xs-12">
								<label for="mano_detalle_empresa" class="control-label col-xs-12 no-padding-left"><spring:message code="label.mano"/>:</label>		
								<input type="text" name="mano_detalle_empresa" class="form-control"  id="mano_detalle_empresa" readonly="readonly" disabled="disabled">
							</div>
						</div>
						<div class="col-xs-12 col-md-1">
							<div class="col-xs-12">		
								<label for="puerta_detalle_empresa" class="control-label col-xs-12 no-padding-left"><spring:message code="label.puerta"/>:</label>
								<input type="text" name="puerta_detalle_empresa" class="form-control"  id="puerta_detalle_empresa" readonly="readonly" disabled="disabled">
							</div>
						</div>
					</div>
				</fieldset>
			</fieldset>
			<fieldset class="rup-table-filter-fieldset">
				<legend><spring:message code="label.proveedores"></spring:message></legend>
				<div id="proveedores_div" class="rup-table-container">
					<div id="proveedores_filter_div" class="rup-table-filter oculto">	<!-- Capa contenedora del formulario de filtrado -->
						<form id="proveedores_filter_form">						<!-- Formulario de filtrado -->
							<div id="proveedores_filter_toolbar" class="formulario_legend"></div>	<!-- Barra de herramientas del formulario de filtrado -->
							<fieldset id="proveedores_filter_fieldset" class="rup-table-filter-fieldset">
								<div class="formulario_columna_cnt">
									<input type="hidden" name="entidad.codigo" id="idEmpresaProv_detail_filter_table" >
									<input type="hidden" name="entidad.tipo" id="tipoEmpresaProv_detail_filter_table" >
								</div>
							</fieldset>
						</form>
					</div>
					<div id="proveedores_grid_div">
						<input type="hidden" name="entidad.codigo" id="idEmpresaProv_detail_filter_table" >
						<input type="hidden" name="entidad.tipo" id="tipoEmpresaProv_detail_filter_table" >
						<!-- Tabla -->
						<table id="proveedores"></table>
						<!-- Barra de paginación -->
						<div id="proveedores_pager"></div>
					</div>
				</div>
				<div id="msgEmpresaNoProveedora" class="oculto">
					<legend>(*) <spring:message code="label.empresaNoProveedora"/></legend>
				</div>
			</fieldset>	
			<fieldset class="rup-table-filter-fieldset">
				<legend><spring:message code="label.lotes"/></legend>
				<div id="lotesdetalle_div" class="rup-table-container">
					<div id="lotesdetalle_filter_div" class="rup-table-filter oculto">	<!-- Capa contenedora del formulario de filtrado -->
						<form id="lotesdetalle_filter_form">						<!-- Formulario de filtrado -->
							<div id="lotesdetalle_filter_toolbar" class="formulario_legend"></div>	<!-- Barra de herramientas del formulario de filtrado -->
							<fieldset id="lotesdetalle_filter_fieldset" class="rup-table-filter-fieldset">
								<div class="formulario_columna_cnt">
									<input type="hidden" name="empresasProveedoras.codigo" id="idEmpresaProv_detail_filter" >
									<input type="hidden" name="empresasProveedoras.tipo" id="tipoEmpresaProv_detail_filter" >
								</div>
							</fieldset>
						</form>
					</div>
					<div id="lotesdetalle_grid_div">
						<input type="hidden" name="empresasProveedoras.codigo" id="idEmpresaProv_detail_filter" >
						<input type="hidden" name="empresasProveedoras.tipo" id="tipoEmpresaProv_detail_filter" >
						<!-- Tabla -->
						<table id="lotesdetalle"></table>
						<!-- Barra de paginación -->
						<div id="lotesdetalle_pager"></div>
					</div>
				</div>
				<legend>(*) <spring:message code="label.importesIvaIncluidoPDF"/></legend>
			</fieldset>	
	</div>
</div>

<div id="divLotesDetalleForm" class="aa79b-fade collapsed">
	<div class="container-fluid">
		<h2>
			<spring:message code="menu.lotes" />
			<small><spring:message code="label.detalle.lote" /></small>
		</h2>
		<hr class="mb-2">
		<div id="lotesdetalleform_div">
			<div id="lotesdetalleform_feedback"></div>
			<div id="lotesdetalleform_toolbar"></div>							<!-- Botonera de la tabla -->
			<div id="lotesdetalle_filter_div">
				<form id="lotesdetalleform">
					<input type="hidden" name="idLote" id="idLote_detail_form">
					<input type="hidden" name="empresasProveedoras.codigo" id="idEmpresaProv_detail_form" >
					<input type="hidden" name="empresasProveedoras.tipo" id="tipoEmpresaProv_detail_form" >
					<input type="hidden" id="empresaProveedora_detail_form">
					<fieldset>
						<legend><spring:message code="label.empresa"></spring:message></legend>
						<div class="row">
							<div class="col-xs-12 col-md-2">
								<div class="form-group">
									<label for="cif_detail_form" class="control-label col-xs-12"><spring:message code="label.cif"/>:</label>
									<div class="col-xs-12">		
										<input type="text" name="cif_detalle_lote" class="form-control" id="cif_detail_form" readonly="readonly" disabled="disabled">
									</div>
								</div>
							</div>
							<div class="col-xs-12 col-md-6">
								<div class="form-group">
									<label for="empresa_detail_form" class="control-label col-xs-12"><spring:message code="label.empresa"/>:</label>
									<div class="col-xs-12">		
										<input type="text" name="empresa_detalle_lote" class="form-control" id="empresa_detail_form"  readonly="readonly" disabled="disabled" >
									</div>
								</div>
							</div>
						</div>
					</fieldset>
					<fieldset>
						<legend><spring:message code="label.lote"></spring:message></legend>
						<div class="row">
							<div class="form-group col-lg-3">
								<label class="control-label" for="nombreLote_detail_form"><spring:message code="label.nombre"/>(*):</label>
								<input type="text" name="nombreLote" class="form-control" id="nombreLote_detail_form" maxlength="50" >
							</div>
							<div class="form-group col-lg-4">
								<label class="control-label" for="descLoteEu_detail_form"><spring:message code="comun.descripcionEus"/>(*):</label>
								<input type="text" name="descLoteEu" class="form-control" id="descLoteEu_detail_form" maxlength="250">
							</div>
							<div class="form-group col-lg-4">
								<label class="control-label" for="descLoteEs_detail_form"><spring:message code="comun.descripcionEs"/>(*):</label>
								<input type="text" name="descLoteEs" class="form-control" id="descLoteEs_detail_form" maxlength="250">
							</div>
						</div>
						<div class="row">
							<div class="form-group col-lg-3">
								<label class="control-label" for="vigencia"><spring:message code="label.vigencia"/>:</label>
							</div>
							<div class="form-group col-lg-3 grupoFechaHora">
								<label class="control-label" for="fechaInicio_detail_form"><spring:message code="label.desde"/>(*):</label>
								<input type="text" title="<spring:message code="label.vigencia"/> <spring:message code="label.desde"/></label>" name="fechaInicio" class="form-control" id="fechaInicio_detail_form" >
							</div>
							<div class="form-group col-lg-3 grupoFechaHora">
								<label class="control-label" for="fechaFin_detail_form"><spring:message code="label.hasta"/>(*):</label>
								<input type="text" title="<spring:message code="label.vigencia"/> <spring:message code="label.hasta"/></label>" name="fechaFin" class="form-control" id="fechaFin_detail_form" >
							</div>
							<div class="form-group col-lg-3">						
								<label for="ind_tramitacion_rapida" class="control-label col-xs-12 no-padding-left"><spring:message code="label.tramitacionRapida"/>:</label>
								<input type="checkbox" name="indTramitacionRapida" id="ind_tramitacion_rapida" value="S" data-switch="true" >
							</div>
						</div>
						<div class="row">
							<div class="form-group col-lg-5">
								<label class="control-label" for="contacto_nombre_detail_form"><spring:message code="label.contacto"/>:</label>
								<div>		
									<select name="contacto.dni" id="contacto_nombre_detail_form" class="form-control">
									</select>
								</div>
							</div>
							<div class="form-group col-lg-2">
								<div id="contacto_verDetalleLink_div" class="sinLabel">
									<a id="contacto_verDetalleLink" href="#" class="rup-enlaceCancelar izda enlace"><spring:message code="label.verDetalle" /></a>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="form-group col-lg-3">
								<label class="control-label" for="importeAsignado_detail_form"><spring:message code="label.importeAsignado"/>(*):</label>
								<input type="text" name="importeAsignado" class="form-control decimal col-66por" id="importeAsignado_detail_form" maxlength="12" data-decim="2" >
							</div>
							<div class="form-group col-lg-3">
								<label class="control-label" for="importeConsumido_detail_form"><spring:message code="label.importeConsumido"/>:</label>
								<input type="text" name="importeConsumido" class="form-control decimal col-66por" id="importeConsumido_detail_form" maxlength="12" data-decim="2" >
							</div>
							<div class="form-group col-lg-3">
								<label class="control-label" for="importePrevisto_detail_form"><spring:message code="label.importePrevisto"/>:</label>
								<input type="text" name="importePreviso" class="form-control decimal col-66por" id="importePrevisto_detail_form" readonly="readonly" disabled="disabled" data-decim="2">
							</div>
							<!-- <div class="form-group col-lg-3">
								<label class="control-label" for="importeRestante_detail_form"><spring:message code="label.importeTotalRestante"/>:</label>
								<input type="text" name="importeRestante" class="form-control decimal col-40por" id="importeRestante_detail_form" readonly="readonly" disabled="disabled" data-decim="2">
							</div>-->
							<div class="form-group col-lg-2">
								<label class="control-label" for="importeDisponible_detail_form"><spring:message code="label.importeDisponible"/>:</label>
								<input type="text" name="importeDisponible" class="form-control decimal col-66por" id="importeDisponible_detail_form" readonly="readonly" disabled="disabled" data-decim="2">
							</div>
						</div>
						<div class="row">
							<div class="form-group col-lg-3">
								<label class="control-label col-100por" for="idiomasCombo"><spring:message code="label.idiomaDestino"/>(*):</label>
								<input class="rup-combo" name="idIdiomaDestino" id="idiomasCombo" />
							</div>
						</div>
						<div class="row">
							<div class="form-group col-lg-4">
								<label class="control-label" for="importePalabra_detail_form"><spring:message code="label.importePalabra"/>(*):</label>
								<input type="text" name="importePalabra" class="form-control decimal col-66por" id="importePalabra_detail_form" maxlength="7"  data-decim="5" >
							</div>
						</div>
						<div class="row">
							<div class="form-group col-lg-6">
								<label class="control-label" for="porcentajePalabra"><spring:message code="label.porcentajePalabra"/>:</label>
							</div>
							<div class="form-group col-lg-3">
								<label class="control-label" for="porPagoPalConcor8594_detail_form"><spring:message code="label.8594por"/>(*):</label>
								<input type="text" name="porPagoPalConcor8594" class="form-control numeric col-40por" id="porPagoPalConcor8594_detail_form" maxlength="3" >
							</div>
							<div class="form-group col-lg-3">
								<label class="control-label" for="porPagoPalConcor95100_detail_form"><spring:message code="label.95100por"/>(*):</label>
								<input type="text" name="porPagoPalConcor95100" class="form-control numeric col-40por" id="porPagoPalConcor95100_detail_form" maxlength="3" >
							</div>
						</div>
						<div class="row">
							<div class="form-group col-lg-4">
								<label class="control-label" for="porRevision_detail_form"><spring:message code="label.porTarea"/>(*):</label>
								<input type="text" name="porRevision" class="form-control numeric col-66por" id="porRevision_detail_form" maxlength="3" >
							</div>
						</div>
						<div class="row">
							<div class="form-group col-lg-3">
								<label class="control-label" for="porRecargoFormato_detail_form"><spring:message code="label.recargoFormato"/>(*):</label>
								<input type="text" name="porRecargoFormato" class="form-control numeric col-66por" id="porRecargoFormato_detail_form" maxlength="3" >
							</div>
						</div>
						<div class="row">
							<div class="form-group col-lg-3">
								<label class="control-label col-100por" for="indRecargoApremio_detail_form"><spring:message code="label.recargoApremio"/>(*):</label>
								<input type="checkbox" name="indRecargoApremio" id="indRecargoApremio_detail_form" value="S" data-switch="true" >
							</div>
							<div class="form-group col-lg-3">
								<label class="control-label" for="porRecargoApremio_detail_form"><spring:message code="label.porcentaje"/>(*):</label>
								<input type="text" name="porRecargoApremio" class="form-control numeric col-66por" id="porRecargoApremio_detail_form" maxlength="3" >
							</div>
						</div>
						<div class="row">
							<div class="form-group col-lg-3">
								<label class="control-label col-100por" for="idTipoPenalizacion_detail_form"><spring:message code="label.penalizacionRetraso"/>(*):</label>
								<div>		
									<select name="idTipoPenalizacion" class="rup-combo" id="idTipoPenalizacion_detail_form">
										<option value="" selected="selected"><spring:message code="combo.seleccione"/></option>
									    <option value="<%=TipoPenalizacionRetrasoEnum.POR_HORAS.getValue()%>"><spring:message code="<%=TipoPenalizacionRetrasoEnum.POR_HORAS.getLabel()%>"/></option>
									    <option value="<%=TipoPenalizacionRetrasoEnum.POR_JORNADAS.getValue()%>"><spring:message code="<%=TipoPenalizacionRetrasoEnum.POR_JORNADAS.getLabel()%>"/></option>
									</select>
								</div>
							</div>
							<div class="form-group col-lg-3">
								<label class="control-label" for="porPenalizacion_detail_form"><spring:message code="label.porcentajeMax"/>(*):</label>
								<input type="text" name="porPenalizacion" class="form-control numeric col-66por" id="porPenalizacion_detail_form" maxlength="3" >
							</div>
						</div>
						<div class="row">
							<div class="form-group col-lg-12">
								<label class="control-label" for="resolucion_detail_form"><spring:message code="label.resolucionExpLote"/>:</label>
								<textarea name="resolucion" class="form-control col-66por" id="resolucion_detail_form" rows="4" cols="9" maxlength="4000" ></textarea>
							</div>
						</div>
						<div class="row">
							<div class="col-lg-12">
								<label class="control-label" for="importesIvaIncluido_detail_form">(*) <spring:message code="label.importesIvaIncluido"/></label>
							</div>
						</div>
					</fieldset>
				</form>
			</div>
			<div id="divTablaEntidadesLote" class="row pagMin">
				<div class="form-group col-md-12">
					<fieldset>
						<legend><spring:message code="label.entidadesSolicitantes" /></legend>
						<div id="entidadesLote_div">
							<div id="entidadesLote_feedback"></div><!-- Feedback de la tabla --> 
							<!-- Botonera de la tabla -->
							<div id="contenFormularios" class="filterForm " style="display:none;">
								<!-- Capa contenedora del formulario de filtrado -->
								<form id="entidadesLote_filter_form" Class="form-horizontal">
									<input type="hidden" name="idLote" id="idLoteEntidadesLote" />
									<div id="entidadesLote_filter_toolbar" class="formulario_legend" ></div>	<!-- Barra de herramientas del formulario de filtrado -->
									<fieldset id="entidadesLote_filter_fieldset" class="rup-table-filter-fieldset">
										<div class="p-2">
										<!-- Botonera del formulario de filtrado -->
											<div class="form-group">
												<div id="entidadesLote_filter_buttonSet" class="pull-right">
													<!-- Botón de filtrado -->
													<button id="entidadesLote_filter_filterButton" type="button" class="ui-button ui-widget ui-state-default ui-corner-all"><spring:message code="filter" /></button>
													<!-- Enlace de limpiar -->
													<a id="entidadesLote_filter_cleanLink" href="javascript:void(0)" class="btn btn-link"><spring:message code="clear" /></a>
												</div>
											</div>
										</div>
									</fieldset>
								</form>
							</div>
							<div id="entidadesLote_toolbar"></div>
							<div class="table">
								<table id="entidadesLote"></table>
							</div>
							<div id="entidadesLote_pager"></div>
						</div>
					</fieldset>
				</div>	
			</div>	
		</div>	
	</div>
</div>
<div id="entidadesLote_detail_div" class="rup-table-formEdit-detail">
	<div id ="entidadesLote_detail_navigation"></div>			<!-- Barra de navegación del detalle -->
	<div class="ui-dialog-content ui-widget-content" >
		<form id="entidadesLote_detail_form">					<!-- Formulario -->
			<input type="hidden" name="idLote" id="idLoteEntidadesLoteDetail"/>
			<div id ="entidadesLote_detail_feedback"></div>		<!-- Feedback del formulario de detalle -->
			<div class="row">
				<div class="form-group col-md-12">
					<label for="idTiposEntidad_detail_table" class="control-label" data-i18n="label.tipoEntidad"><spring:message code="label.tipoEntidad"/>:</label>
					<div id="idTiposEntidad_detail_table">
						<div class="col-md-3">
							<input type="radio" name="tipoEntidad" id="entidadesLote_detail_tipoEntidad_detail_T" value="" data-on-text='<spring:message code="label.todas"/>'/>
							<label for="entidadesLote_detail_tipoEntidad_detail_T" class="radio-inline rup-table-filter-fieldset" data-i18n="label.tipoEntidad"><spring:message code="label.todas"/></label>
						</div>
					<c:set var="labelsTipoEntidad" value="<%=TipoEntidadEnum.values()%>"/>
					<c:forEach items="${labelsTipoEntidad}" var="tipoEntidad">
						<div class="col-md-3">
							<input type="radio" name="tipoEntidad" id="tipoEntidad_detail_${tipoEntidad.value}" value="${tipoEntidad.value}" data-on-text='<spring:message code="${tipoEntidad.label}"/>'/>
							<label for="tipoEntidad_detail_${tipoEntidad.value}" class="radio-inline rup-table-filter-fieldset" data-i18n="label.tipoEntidad"><spring:message code="${tipoEntidad.label}"/></label>
						</div>
					</c:forEach>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="form-group col-md-12">
					<label for="entidadesLoteIdEntidadSolicitante_detail_table" class="control-label" data-i18n="label.entidadSolicitante"><spring:message code="label.entidadSolicitante" /> (*):</label>
					<input id="entidadesLoteIdEntidadSolicitante_detail_table" class="form-control" name="codigoCompleto"/>
				</div>
			</div>
		</form>
	</div>
	<!-- Botonera del formulario de detalle -->
	<div class="rup-table-buttonpane ui-widget-content ui-helper-clearfix">
		<div class="ui-dialog-buttonset">
			<!-- Botón Guardar -->
			<button id="entidadesLote_detail_button_save" type="button">
				<spring:message code="save" />
			</button>
			<!-- Enlace cancelar -->
			<a href="javascript:void(0)" id="entidadesLote_detail_link_cancel"
				class="rup-enlaceCancelar"><spring:message code="cancel" /></a>
		</div>
	</div>
</div>
<div id="comentarioEntidad_dialog" class="rup-table-formEdit-detail">
	<div id="comentarioEntidad_dialog_detail_navigation"></div>
	<!-- Barra de navegación del detalle -->
	<div class="ui-dialog-content ui-widget-content">
		<form id="comentarioEntidad_dialog_form">
			<input type="hidden" name="codigoCompleto" id="idEntidadComentario"/>
			<div class="row" style="padding-left:5px;">
				<div class="form-group col-md-12 col-lg-12">
					<label class="control-label"><spring:message code="label.entidad"/>: </label>	
					<label id="descEntidadComentario" class="control-label"></label>	
				</div>
			</div>
			<div class="row" style="padding-left:5px;">
				<div class="form-group col-md-12 col-lg-12">
					<label for="comentarioEntidad"><spring:message code="label.comentario"/></label>
					<textarea id="comentarioEntidad" name="comentarioEntidad" maxlength="4000" rows="5" cols="1" class="form-control"></textarea>
				</div>
			</div>
		</form>
	</div>
</div>