<%@page import="com.ejie.aa79b.model.enums.EstadoEnum"%>
<%@page import="com.ejie.aa79b.model.enums.TipoTareaGestionAsociadaEnum"%>
<%@page import="com.ejie.aa79b.model.enums.EstadoEjecucionTareaEnum"%>
<%@include file="/WEB-INF/includeTemplate.inc"%>
<div class="container-fluid" id="divAsignadoProveedores">
	<h2>
		<spring:message code="menu.asignadoProveedores" />
	</h2>
	<hr class="mb-2">
	<div id="asignadoProveedor_div" class="rup-table-container">
		<div id="asignadoProveedor_feedback"></div>
		<div id="asignadoProveedor_toolbar"></div>
		<!-- Feedback de la tabla -->
		<div id="contenFormularios" class="filterForm ">
			<!-- Capa contenedora del formulario de filtrado -->
			<form id="asignadoProveedor_filter_form" class="form-horizontal">
				<!-- Formulario de filtrado -->
				<div id="asignadoProveedor_filter_toolbar" class="formulario_legend"></div>
				<!-- Barra de herramientas del formulario de filtrado -->
				<fieldset id="asignadoProveedor_filter_fieldset" class="rup-table-filter-fieldset">
					<div class="p-2">
						<div class=" row">
							<!-- Campos del formulario de filtrado -->
							<div class="form-group col-xs-3">
								<label for="lote_filter_table" class="control-label" data-i18n="label.lote"><spring:message code="label.lote" />:</label> 
								<select name="lotes.idLote" id="lote_filter_table" class="form-control col-80por"></select>
							</div>
							<div class="form-group col-xs-12 col-md-2">
									<label for="numExp_filter_table" class="control-label"><spring:message code="label.numeroExpediente"/>:</label>
									<div>
										<label for="anyo_filter_table" class="control-label oculto"><spring:message code="label.anyo"/></label>
										<input type="text" name="anyo" class=" numeric" style="width: 40px;" id="anyo_filter_table" maxlength="2"/>
										<label class="control-label" style="width: 3%;">/</label>
										<input type="text" name="numExp" class=" numeric" style="width: 75px;" id="numExp_filter_table" maxlength="6"/>
									</div>
							</div>
						</div>
						<div class=" row">
							<div class="form-group col-xs-2 col-xl-2 ">
								<label for="idTarea_filter_table" class="control-label" data-i18n="comun.idTarea"><spring:message code="label.idTarea" />:</label> 
								<input type="text" class="form-control numeric" name="idTarea" id="idTarea_filter_table" />
							</div>							
							<div class="form-group col-xs-2 col-xl-1 ">
								<label for="tipoTarea_filter_table" class="control-label" data-i18n="comun.tipoDeTarea"><spring:message code="comun.tipoDeTarea" />:</label> 								
								<select	name="tipoTarea.id015" id="tipoTarea_filter_table" class="form-control col-80por">
									<option value=""><spring:message code="combo.todos" /></option>
									<option value="<%=TipoTareaGestionAsociadaEnum.TRADUCIR.getValue()%>"><spring:message code="<%=TipoTareaGestionAsociadaEnum.TRADUCIR.getLabel()%>" /></option>
									<option value="<%=TipoTareaGestionAsociadaEnum.REVISAR.getValue()%>"><spring:message code="<%=TipoTareaGestionAsociadaEnum.REVISAR.getLabel()%>" /></option>
									<option value="<%=TipoTareaGestionAsociadaEnum.NO_CONFORMIDAD_PROVEEDOR.getValue()%>"><spring:message code="<%=TipoTareaGestionAsociadaEnum.NO_CONFORMIDAD_PROVEEDOR.getLabel()%>" /></option>
								</select>
							</div>
							<div class="form-group col-xs-3  col-xl-2 ">
								<label for="estado_filter_table" class="control-label" data-i18n="label.estadoEjecucion"><spring:message code="label.estadoEjecucion" />:</label> 
									<select name="estadoEjecucion" id="estado_filter_table" class="form-control col-80por">
									<option value=""><spring:message code="combo.todos" /></option>
									<option value="<%=EstadoEjecucionTareaEnum.PENDIENTE_EJECUCION.getValue()%>"><spring:message code="<%=EstadoEjecucionTareaEnum.PENDIENTE_EJECUCION.getLabel()%>" /></option>
									<option value="<%=EstadoEjecucionTareaEnum.EJECUTADA.getValue()%>"><spring:message code="<%=EstadoEjecucionTareaEnum.EJECUTADA.getLabel()%>" /></option>
									<option value="<%=EstadoEjecucionTareaEnum.RETRASADA.getValue()%>"><spring:message code="<%=EstadoEjecucionTareaEnum.RETRASADA.getLabel()%>" /></option>
								</select>
							</div>
							<div class="col-md-6">
										<label class="control-label col-lg-12 p-0"
											data-i18n="label.fechaFinTarea"><spring:message
														code="label.fechaFinTarea" /></label>
										<div class="form-group col-lg-6 p-0">
											<label for="fechaPlanTareaDesde_filter"
												class="control-label col-md-3 p-0" data-i18n="label.desde"><spring:message
													code="label.desde" />:</label>
											<div class="form-group col-md-9 p-0 grupoFecha">
												<input type="text" id="fechaPlanTareaDesde_filter" name="fechaIni"
													class="form-control">
											</div>
										</div>
										<div class="form-group col-lg-6 p-0">
											<label for="fechaPlanTareaHasta_filter"
												class="control-label col-md-4 p-0" data-i18n="label.hasta"><spring:message
													code="label.hasta" />:</label>
											<div class="form-group col-md-8 p-0 grupoFecha">
												<input type="text" id="fechaPlanTareaHasta_filter" name="fechaFin"
													class="form-control">
											</div>
										</div>
									</div>
							
							
						</div>	
							
						<!-- Fin campos del formulario de filtrado -->
					</div>
					<!-- Botonera del formulario de filtrado -->
					<div class="form-group ">
						<div id="asignadoProveedor_filter_buttonSet" class="pull-right">
							<!-- Botón de filtrado -->
							<button id="asignadoProveedor_filter_filterButton" type="button"
								class="ui-button ui-widget ui-state-default ui-corner-all">
								<spring:message code="filter" />
							</button>
							<!-- Enlace de limpiar -->
							<a id="asignadoProveedor_filter_cleanLink"
								href="javascript:void(0)" class="rup-enlaceCancelar"><spring:message
									code="clear" /></a>
						</div>
					</div>
				</fieldset>
			</form>
		</div>

		<div class="table pb-2">
			<table id="asignadoProveedor"></table>
		</div>
		<div id="asignadoProveedor_pager"></div>

	</div>
		
</div>
<div id="ejecutarTareaDialog" style="display:none"></div>

<div id="confirmartarea" style="display:none"></div>