<%@include file="/WEB-INF/includeTemplate.inc"%>
<div id="divTareasExpedientes">
	<div id="tareasExpedientes">
		<h2><spring:message code="comun.tareasExpedientes"/></h2>
		<div id="tareasExpedientes_div" class="rup-table-container ">
		<div id="tareasExpedientes_feedback"></div>
		<div id="tareasExpedientes_toolbar" class="mt-1"></div>
		<div id="contenFormularios">
			<fieldset id="datosPlanificacion_fieldset" class="form_fieldset">
				<legend>
					<spring:message code="comun.datosPlanificacion"/>
				</legend>
				<div id="divDatosPlanificacionExpTradRev" class="row">
					<div class="form-group col-md-3 col-xl-2 ">
						<label for="numPalabrasIZO_filter" class="control-label col-md-12 p-0" data-i18n="comun.numPalabrasIZO"><spring:message code="comun.numPalabrasIZO"/>:</label>
						<div class="numPalIzoConcor">
						<div class="numPalIzoConcor1">
							<span id="numPalabrasIZO_filter"></span>
						</div>
						<div class="numPalIzoConcor2">
							<b><spring:message code="comun.tramosConcor084" />:</b>
							<span id="tramosConcor084_filter"></span><br>
							<b><spring:message code="comun.tramosConcor8594" />:</b>
							<span id="tramosConcor8594_filter"></span><br>
							<b><spring:message code="comun.tramosConcor9599" />:</b>
							<span id="tramosConcor9599_filter"></span><br>
							<b><spring:message code="comun.tramosConcor100" />:</b>
							<span id="tramosConcor100_filter"></span><br>
						</div>
						</div>
					</div>
					<div class="col-md-2">
						<label for="grupoTrabajo_filter" class="control-label col-md-12 p-0" data-i18n="comun.grupoTrabajo"><spring:message code="comun.grupoTrabajo" />:</label>
						<div class="form-group  col-md-12 col-xl-12 p-0">
							<span id="grupoTrabajo_filter"></span>
						</div>
					</div>
					
					<div class="col-md-3 grupoFechaHora">
						<label for="fechaEnteIZO_filter" class="control-label col-md-12 p-0" data-i18n="comun.fechaHoraEnteIZO"><spring:message code="comun.fechaHoraEnteIZO" />:</label>
						<div class="form-group  col-md-12 col-xl-12 p-0">
							<span id="fechaEnteIZO_filter"></span>
						</div>
					</div>
					<!-- DATOS PAGO PROVEEDOR -->
					<div class="form-group col-md-5 col-xl-5 " id="divDatosPagoProveedor">
						<label for="pagoAProveedor_filter" class="control-label col-md-12 p-0" data-i18n="label.datosDePagoAProveedor"><spring:message code="label.datosDePagoAProveedor"/>:</label>
						
						<div class="form-group  col-md-7 col-xl-7 p-0 numPalIzoConcor">
							<div class="numPalIzoConcor2" style="padding-right: 5px;">
								<b><spring:message code="label.cantidadParaTarea" />:</b>
								<span id="cantidadParaTarea_filter" ></span><br>
								<b><spring:message code="label.recargoFormatoCantidad" />:</b>
								<span id="recargoFormatoCantidad_filter" ></span><br>
								<b><spring:message code="label.recargoApremioCantidad" />:</b>
								<span id="recargoApremioCantidad_filter" ></span><br>
								<b><spring:message code="label.penalPorRetraso" />:</b>
								<span id="penalPorRetraso_filter" ></span><br>
								<b><spring:message code="label.penalPorCalidad" />:</b>
								<span id="penalPorCalidad_filter" ></span><br>
							</div>
						</div>
						<div class="form-group  col-md-5 col-xl-5 p-0 numPalIzoConcor">
							<div class="numPalIzoConcor2">
								<b><spring:message code="label.importeBase" />:</b>
								<span id="importeBase_filter" ></span><br>
								<b><spring:message code="label.importeIVA" />:</b>
								<span id="importeIva_filter" ></span><br>
								<b><spring:message code="label.importeTotal" />:</b>
								<span id="importeTotal_filter" ></span><br>
							</div>
						</div>
					</div>
					<div class="form-group col-md-12" id="leyendaSinTareasEntregaClte">
						<p><span class="ico-ficha-encriptado"><i class="fa fa-exclamation-triangle" aria-hidden="true"></i></span> <spring:message code="label.expSinTareaEntregaClte"/></p>						
					</div>
				</div>
				<div id="divDatosPlanificacionExpInter" class="row">
					<div class="col-md-5 grupoFechaHora">
						<label for="fechaHoraIniInterpretacion_filter" class="control-label col-md-12 p-0" data-i18n="label.fechaHoraIniInterpretacion"><spring:message code="label.fechaHoraIniInterpretacion" />:</label>
						<div class="form-group  col-md-5 col-xl-5 p-0">
							<span id="fechaHoraIniInterpretacion_filter"></span>
						</div>
					</div>
					<div class="col-md-5 grupoFechaHora">
						<label for="fechaHoraFinInterpretacion_filter" class="control-label col-md-12 p-0" data-i18n="label.fechaHoraFinInterpretacion"><spring:message code="label.fechaHoraFinInterpretacion" />:</label>
						<div class="form-group  col-md-5 col-xl-5 p-0">
							<span id="fechaHoraFinInterpretacion_filter"></span>
						</div>
					</div>	
				</div>
			</fieldset>
		</div>
			
			<div id="tareasExpedientesForm_div" class="rup-table-container aliniarForm">
				<div id="tareasExpedientesForm_filter_div" class="rup-table-filter" hidden="hidden"> <!-- Capa contenedora del formulario de filtrado -->
					<div id="tareasExpedientesForml_feedback"></div>	
					<form id="tareasExpedientesForm_filter_form">	
						<div id="tareasExpedientesForm_filter_toolbar" class="formulario_legend"></div>
						<fieldset id="tareasExpedientesForm_filter_fieldset" class="rup-table-filter-fieldset">
							<div class="formulario_columna_cnt">
								<input type="hidden" name="anyo" id="anyo_detail_filter_table" >
								<input type="hidden" name="numExp" id="numExp_detail_filter_table" >
							</div>
						</fieldset>
					</form>
				</div>
				<div class="tareasExpedientesForm_grid_div horizontal_scrollable_table" >
					<input type="hidden" name="anyo" id="anyo_detail_filter_table" >
					<input type="hidden" name="numExp" id="numExp_detail_filter_table" >
					<!-- Tabla -->
					<table id="tareasExpedientesForm" ></table>
					<!-- Barra de paginación -->
					<div id="tareasExpedientesForm_pager"></div>
				</div>
			</div>
			<div class="leyendaDocs">
				<p><span class="ico-ficha-encriptado"><i class="fa fa-exclamation-circle" aria-hidden="true"></i></span> <spring:message code="comun.recursoNoDisponible"/></p>
				<p><span class="ico-ficha-encriptado"><i class="fa fa-exclamation-triangle" aria-hidden="true" style="color: red;"></i></span> <spring:message code="label.fechaTareaSupExp"/></p>
				<p><span class="ico-ficha-encriptado"><i class="fa fa-info-circle" aria-hidden="true"></i></span> <spring:message code="comun.recursoConOtrasTareas"/></p>
			</div>
		</div>
	</div>
	
	<div id="crearConfigurarDialog" style="display:none"></div>
	
	<div id="ejecutarTareaDialog" style="display:none"></div>
	
	<div id="confirmartarea" style="display:none"></div>
</div>