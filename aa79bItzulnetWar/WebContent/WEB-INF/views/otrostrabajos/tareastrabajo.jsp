<%@include file="/WEB-INF/includeTemplate.inc"%>
<div id="divTareasTrabajo">
	<div id="tareasTrabajo">
		<h2><spring:message code="comun.tareasTrabajo"/></h2>
		<div id="tareasTrabajo_div" class="rup-table-container ">
		<div id="tareasTrabajo_feedback"></div>
		<div id="tareasTrabajo_toolbar" class="mt-1"></div>
		<div id="contenFormularios">
			<fieldset id="datosTrabajo_fieldset" class="form_fieldset">
				<legend>
					<spring:message code="label.datosTrabajo"/>
				</legend>
					<div class="row">
					<div class="form-group col-md-4 col-xl-3 ">
						<label for="numPalabrasIZO_filter" class="control-label col-md-12 p-0" data-i18n="label.numeroTrabajo"><spring:message code="label.numeroTrabajo"/>:</label>
						<div class="form-group  col-md-12 col-xl-12 p-0">
							<span id="idTrabajo_filter"></span>
						</div>
					</div>
					<div class="form-group col-md-8 col-xl-9 ">
						<label for="descTrabajo_filter" class="control-label col-md-12 p-0" data-i18n="label.titulo"><spring:message code="label.titulo"/>:</label>
						<div class="form-group  col-md-12 col-xl-12 p-0">
							<span id="descTrabajo_filter"></span>
						</div>
					</div>
					</div>
					<div class="row">
					<div class="col-md-4 col-xl-3 grupoFechaHora">
						<label for="fechaInicio_filter" class="control-label col-md-12 p-0" data-i18n="comun.fechaHoraInicio"><spring:message code="comun.fechaHoraInicio" />:</label>
						<div class="form-group  col-md-12 col-xl-12 p-0">
							<span id="fechaInicio_filter"></span>
						</div>
					</div>
					<div class="col-md-4 col-xl-3  grupoFechaHora">
						<label for="fechaFinPrevista_filter" class="control-label col-md-12 p-0" data-i18n="label.fechaHoraFinPrevistaY"><spring:message code="label.fechaHoraFinPrevistaY" />:</label>
						<div class="form-group  col-md-12 col-xl-12 p-0">
							<span id="fechaFinPrevista_filter"></span>
						</div>
					</div>
					<div class="col-md-4 col-xl-3 grupoFechaHora">
						<label for="fechaFin_filter" class="control-label col-md-12 p-0" data-i18n="comun.fechaHoraFin"><spring:message code="comun.fechaHoraFin" />:</label>
						<div class="form-group  col-md-12 col-xl-12 p-0">
							<span id="fechaFin_filter"></span>
						</div>
					</div>
					</div>
					
			</fieldset>
		</div>
			
			<div id="tareasTrabajoForm_div" class="rup-table-container aliniarForm">
				<div id="tareasTrabajoForm_filter_div" class="rup-table-filter" hidden="hidden"> <!-- Capa contenedora del formulario de filtrado -->
					<div id="tareasTrabajoForml_feedback"></div>	
					<form id="tareasTrabajoForm_filter_form">	
						<div id="tareasTrabajoForm_filter_toolbar" class="formulario_legend"></div>
						<fieldset id="tareasTrabajoForm_filter_fieldset" class="rup-table-filter-fieldset">
							<div class="formulario_columna_cnt">
								<input type="hidden" name="trabajo.idTrabajo" id="idTrabajo_detail_filter_table" >
							</div>
						</fieldset>
					</form>
				</div>
				<div class="tareasTrabajoForm_grid_div horizontal_scrollable_table" >
					<input type="hidden" name="trabajo.idTrabajo" id="idTrabajo_detail_filter_table" >
					<!-- Tabla -->
					<table id="tareasTrabajoForm" ></table>
					<!-- Barra de paginación -->
					<div id="tareasTrabajoForm_pager"></div>
				</div>
			</div>
			<div class="leyendaDocs">
				<p><span class="ico-ficha-encriptado"><i class="fa fa-exclamation-circle" aria-hidden="true"></i></span> <spring:message code="comun.recursoNoDisponible"/></p>
				<p><span class="ico-ficha-encriptado"><i class="fa fa-info-circle" aria-hidden="true"></i></span> <spring:message code="comun.recursoConOtrasTareas"/></p>
			</div>
		</div>
	</div>
	
	<div id="crearConfigurarDialog" style="display:none"></div>
	
	<div id="ejecutarTareaTrabajoDialog" style="display:none"></div>
	
	<div id="confirmartarea" style="display:none"></div>
</div>