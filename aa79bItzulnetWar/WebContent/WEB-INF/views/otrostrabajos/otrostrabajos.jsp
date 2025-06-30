<%@page import="com.ejie.aa79b.model.enums.FiltroEstadoTrabajoEnum"%>
<%@page import="com.ejie.aa79b.model.enums.ActivoEnum"%>
<%@page import="com.ejie.aa79b.model.enums.EstadoEnum"%>
<%@include file="/WEB-INF/includeTemplate.inc"%>
<div class="container-fluid" id="divCapaTrabajos"> 
<div id="divTrabajos"> 
<h2><spring:message code="menu.gestionOtrosTrabajos" /></h2>
<hr class="mb-2">
<div id="otrostrabajos_div" class="rup-table-container">
	<div id="otrostrabajos_feedback"></div>
	<!-- Feedback de la tabla -->
	<div id="otrostrabajos_toolbar"></div>
	<!-- Botonera de la tabla -->
	<div id="contenFormularios" class="filterForm ">
		<!-- Capa contenedora del formulario de filtrado -->
		<form id="otrostrabajos_filter_form" Class="form-horizontal">
			<!-- Formulario de filtrado -->
			<div id="otrostrabajos_filter_toolbar" class="formulario_legend"></div>
			<!-- Barra de herramientas del formulario de filtrado -->
			<fieldset id="otrostrabajos_filter_fieldset" class="rup-table-filter-fieldset">
			<div class="p-2">
				<div class="row">
					<!-- Campos del formulario de filtrado -->
					<div class="form-group col-xs-4 col-md-2">
						<label for="idTrabajo_filter_table" class="control-label" data-i18n="label.numeroTrabajo"><spring:message code="label.numeroTrabajo"/>:</label>
						<input type="text" maxlength="20" name="idTrabajo" class="form-control numeric" id="idTrabajo_filter_table" />
					</div>
					<div class="form-group col-xs-6 col-md-8">
						<label for="descTrabajo_filter_table" class="control-label" data-i18n="label.titulo"><spring:message code="label.titulo" />:</label>
						<input type="text"  maxlength="250" name="descTrabajo" class="form-control" id="descTrabajo_filter_table" />
					</div>
				</div>	
				<div class="row">
					<div class="col-md-6">
						<label class="control-label col-lg-12 p-0" data-i18n="label.fechaInicio"><spring:message code="label.fechaInicio" />:</label>
						<div class="form-group col-lg-5 p-0">
							<label for="fechaInicioDesde_filter" class="control-label col-md-4 p-0 valFecha" data-i18n="label.desde"><spring:message code="label.desde" />:</label>
							<div class="form-group  col-md-8 p-0 grupoFechaHora grupoFecha">
								<input type="text" id="fechaInicioDesde_filter" name="fechaInicioDesde"  class="form-control"  >
							</div>
						</div>
						<div class="form-group col-lg-5 p-0">
							<label for="fechaInicioHasta_filter" class="control-label col-md-3 p-0 valFecha" data-i18n="label.hasta"><spring:message code="label.hasta" />:</label>
							<div class="form-group  col-md-8 p-0 grupoFechaHora grupoFecha">
								<input type="text" id="fechaInicioHasta_filter" name="fechaInicioHasta"  class="form-control" >
							</div>
						</div>
					</div>
					
					<div class="col-md-6">
						<label class="control-label col-lg-12 p-0" data-i18n="label.fechaFinPrevista"><spring:message code="label.fechaFinPrevista" />:</label>
						<div class="form-group col-lg-5 p-0">
							<label for="fechaFinPrevistaDesde_filter" class="control-label col-md-4 p-0 valFecha" data-i18n="label.desde"><spring:message code="label.desde" />:</label>
							<div class="form-group  col-md-8 p-0 grupoFechaHora grupoFecha">
								<input type="text" id="fechaFinPrevistaDesde_filter" name="fechaFinPrevistaDesde"  class="form-control"  >
							</div>
						</div>
						<div class="form-group col-lg-7 p-0">
							<label for="fechaFinPrevistaHasta_filter" class="control-label col-md-3 p-0 valFecha" data-i18n="label.hasta"><spring:message code="label.hasta" />:</label>
							<div class="form-group  col-md-8 p-0 grupoFechaHora grupoFecha">
								<input type="text" id="fechaFinPrevistaHasta_filter" name="fechaFinPrevistaHasta"  class="form-control" >
							</div>
						</div>
					</div>
					
				</div>
				
				<div class="row">
									<div class="col-md-3">
										<label for="indicadorEstado_filter"
											class="control-label col-xs-12 no-padding-left"><spring:message
												code="comun.estadoTrabajo" />:</label>
										<div class="col-xs-12 no-padding-left">
											<select name="indicadorEstado" class="form-control"
												id="indicadorEstado_filter">
													<option value="" ><spring:message code="combo.todos"/></option>
													<option value="<%=FiltroEstadoTrabajoEnum.TRABAJOS_ABIERTOS.getValue()%>" selected="true"><spring:message code="<%=FiltroEstadoTrabajoEnum.TRABAJOS_ABIERTOS.getLabel()%>"/></option>
													<option value="<%=FiltroEstadoTrabajoEnum.TRABAJOS_CERRADOS.getValue()%>"><spring:message code="<%=FiltroEstadoTrabajoEnum.TRABAJOS_CERRADOS.getLabel()%>"/></option>
												</select>
										</div>
									</div>
				</div>
				
				<div class="row">
					<div class="form-group col-lg-12">
						<fieldset>
							<legend>
								<spring:message code="label.tareas"/>
							</legend>
							<div class="row">
									<div class="col-xs-4">
										<label for="tipoTareaCargaTrabajo"
											class="control-label col-xs-12 no-padding-left"><spring:message
												code="comun.tipoDeTarea" />:</label>
										<div class="col-xs-12 no-padding-left">
											<select name="filterTarea.tipoTarea" class="form-control"
												id="tipoTareaTrabajo"></select>
										</div>
									</div>
								
			 	<div class="form-group col-xs-6">
	                <label for="personaAsignada_filter_table" class="control-label col-xs-12 no-padding-left"><spring:message code="comun.asignadaA"/>:</label>
	                <div class="input-group">
						<input type="hidden" id="dniAsignacion" name="filterTarea.personaAsignada.dni"/>
	                    <input type="text" name="filterTarea.personaAsignada.nombre" class="form-control" id="personaAsignada_filter_table" readOnly="readonly">
	                    <span class="input-group-addon" id="divLinkEliminar"><a href="#" id="eliminar_detail_table" class=""><spring:message code="eliminar"/></a></span>
	                    <span class="input-group-addon" id="divLinkRecursoInterno"><a href="#" id="recursoInterno_detail_table" class=""><spring:message code="label.personalIzo"/></a></span>
	                </div>
	            </div>
	            </div>
								<div class="row">
						<div class="form-groupcol-xs-12 col-md-2">
							<label for="tieneSinAsignar_filter_table" class="control-label "><spring:message code="label.tieneTareasSinAsignar"/>:</label>
							<div class="divComboW125">
								<select name="filterTarea.tieneSinAsignar" class="form-control" id="tieneSinAsignar_filter_table">
									<option value="" selected="true"><spring:message code="combo.todos"/></option>
									<option value="<%=ActivoEnum.SI.getValue()%>"><spring:message code="<%=ActivoEnum.SI.getLabel()%>"/></option>
									<option value="<%=ActivoEnum.NO.getValue()%>"><spring:message code="<%=ActivoEnum.NO.getLabel()%>"/></option>
								</select>
							</div>
						</div>
						<div class="form-groupcol-xs-12 col-md-2">
							<label for="tieneSinEjecutar_filter_table" class="control-label "><spring:message code="label.tieneTareasSinEjecutar"/>:</label>
							<div class="divComboW125">
								<select name="filterTarea.tieneSinEjecutar" class="form-control" id="tieneSinEjecutar_filter_table">
									<option value="" selected="true"><spring:message code="combo.todos"/></option>
									<option value="<%=ActivoEnum.SI.getValue()%>"><spring:message code="<%=ActivoEnum.SI.getLabel()%>"/></option>
									<option value="<%=ActivoEnum.NO.getValue()%>"><spring:message code="<%=ActivoEnum.NO.getLabel()%>"/></option>
								</select>
							</div>
						</div>
						</div>
						</fieldset>
					</div>			
				</div>
					<!-- Botonera del formulario de filtrado -->
					<div class="form-group ">
					<div id="otrostrabajos_filter_buttonSet" class="pull-right">
						<!-- Botón de filtrado -->
						<button id="otrostrabajos_filter_filterButton" type="button" class="ui-button ui-widget ui-state-default ui-corner-all"><spring:message code="filter" /></button>
						<!-- Enlace de limpiar -->
						<a id="otrostrabajos_filter_cleanLink" href="javascript:void(0)" class="btn btn-link"><spring:message code="clear" /></a>
					</div>
				</div>
				</div>
			</fieldset>
		</form>
	</div>

		<!-- Tabla -->
		<div class="table pb-2">
		<table id="otrostrabajos"></table>
		</div>
		<!-- Barra de paginación -->
		<div id="otrostrabajos_pager"></div>
	</div>
	
	<div id="buscadorPersonas" class="oculto"></div>
</div>
<div class="container-fluid aa79b-content-modal">
<!-- Formulario de detalle -->
<div id="otrostrabajos_detail_div" class="rup-table-formEdit-detail ">
	<!-- Barra de navegación del detalle -->
	<div class="ui-dialog-content ui-widget-content">
		<form id="otrostrabajos_detail_form">
			<!-- Formulario -->
			<div id="otrostrabajos_detail_feedback"></div>
			<!-- Feedback del formulario de detalle -->

				<!-- Campos del formulario de detalle -->
				<div class="row">
				<div class="form-group  col-lg-4">
					<label for="idTrabajo_detail_table" class="control-label"><spring:message code="label.numeroTrabajo" />:</label> 
					<input type="text" name="idTrabajo" class="form-control col-40por" id="idTrabajo_detail_table" readonly="readonly" disabled="disabled" />
				</div>
				</div>
				<div class="row">
				<div class="form-group  col-lg-8">
					<label for="descTrabajo_detail_table"><spring:message code="label.titulo" />(*):</label> 
					<input type="text" maxlength="250" name="descTrabajo" class="form-control" id="descTrabajo_detail_table" />
				</div>
				</div>	
				<div class="row">
					<div class="form-group col-md-12 col-lg-4 grupoFechaHora ">
							<label class="control-label dosPuntos valFecha ajustarFieldSet" for="fechaInicio_detail_table"><spring:message code="label.fechaInicio"/>(*)</label>
							<input type="text" name="fechaInicio" class="form-control" id="fechaInicio_detail_table">
							<input type="text" name="horaInicio" class="form-control campohora " id="horaInicio_detail_table" placeholder="hh:mm" maxlength="5">
					</div>
					<div class="form-group col-md-12 col-lg-4 grupoFechaHora ">
							<label class="control-label dosPuntos valFecha ajustarFieldSet" for="fechaFinPrevista_detail_table"><spring:message code="label.fechaFinPrevista"/></label>
							<input type="text" name="fechaFinPrevista" class="form-control" id="fechaFinPrevista_detail_table">
							<input type="text" name="horaFinPrevista" class="form-control campohora " id="horaFinPrevista_detail_table" placeholder="hh:mm" maxlength="5">
					</div>
				</div>	
				<div class="row">	
					<div class="form-group col-md-12 col-lg-4 grupoFechaHora ">
							<label class="control-label dosPuntos valFecha ajustarFieldSet" for="fechaFin_detail_table"><spring:message code="label.fechaFin"/></label>
							<input type="text" name="fechaFin" class="form-control" id="fechaFin_detail_table">
							<input type="text" name="horaFin" class="form-control campohora " id="horaFin_detail_table" placeholder="hh:mm" maxlength="5" readonly="readonly" disabled="disabled" >
					</div>
				</div>	
				<input type="text" name="tareasCerradas" id="tareasCerradas_detail_table" hidden="hidden">
				<!-- Fin campos del formulario de detalle -->
		</form>
	</div>
	<!-- Botonera del formulario de detalle -->
	<div class="rup-table-buttonpane ui-widget-content ui-helper-clearfix">
		<div class="ui-dialog-buttonset">
			<!-- Botón Guardar -->
			<button id="otrostrabajos_detail_button_save" type="button">
				<spring:message code="save" />
			</button>
			<!-- Enlace cancelar -->
			<a href="javascript:void(0)" id="otrostrabajos_detail_link_cancel"
				class="rup-enlaceCancelar"><spring:message code="cancel" /></a>
		</div>
	</div>
</div>
</div>
</div>
