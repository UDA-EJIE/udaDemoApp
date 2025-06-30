<%@include file="/WEB-INF/includeTemplate.inc"%>
<%@page import="com.ejie.aa79b.model.enums.EstadoAlbaranEnum"%>
<%@page import="com.ejie.aa79b.model.enums.TipoTareaGestionAsociadaEnum"%>

<div class="container-fluid aa79b-fade in" id="divPagamentosCentral">
	<div id="divPagamentos">
		<div id="divPagamentosCapa">
			<div id="divPagamentosGeneral">
				<h2><spring:message code="label.pagoProveeedores" /></h2>
				<hr class="mb-2" >
				<div id="pagamentos_div" class="rup-table-container">
					<div id="pagamentos_feedback"></div>
						<!-- Botonera de la tabla -->
						<div id="pagamentos_toolbar"></div>
						<div id="contenFormularios" class="filterForm ">
								<!-- Capa contenedora del formulario de filtrado -->
								<form id="pagamentos_filter_form" class="form-horizontal">
									<div id="pagamentos_filter_toolbar" class="formulario_legend"></div>
									<fieldset id="pagamentos_filter_fieldset" class="rup-table-filter-fieldset">
										<div class="p-2">
										<div class="row">
											<!-- Campos del formulario de filtrado -->
											<div class="form-group col-xs-12 col-md-4">
													<label for="empresaProv_filter_table" class="control-label ">(*)<spring:message code="label.empresaProv" />:</label> 
													<select name="codEntidad" id="empresaProv_filter_table" class="form-control"></select>
											</div>
			
											<div class="form-group col-xs-12 col-md-4">
													<label for="nombreLote029_filter_table" class="control-label ">(*)<spring:message code="label.lote" />:</label>
													<select name="idLote" id="nombreLote029_filter_table" class="form-control"></select>
											</div>
											
											<div class="form-group col-xs-12 col-md-3 col-lg-3 col-xl-2">
													<label for="exp_filter_table" class="control-label" data-i18n="label.numexpediente"><spring:message code="label.numexpediente" />:</label> 
													<div>
														<label for="numexp1_filter_table" class="control-label oculto"><spring:message code="label.anyo"/></label>
														<input type="text" name="idAlbaranAnoExpediente" class="numeric" style="width: 40px;" id="numexp1_filter_table" maxlength="2"/>
														<label class="control-label" style="width: 3%;">/</label>
														<label for="numexp2_filter_table" class="control-label oculto"><spring:message code="label.numexpediente"/></label>
														<input type="text" name="idAlbaranExpediente" class="numeric" style="width: 75px;" id="numexp2_filter_table" maxlength="6"/>
													</div>
											</div>
			
											
										</div>
			
										<div class="row ">
											<div class="form-group col-xs-12 col-md-4">
													<label for="refalbaran_filter_table" class="control-label "><spring:message code="label.refalbaran" />:</label>
													<select name="idAlbaran" id="refalbaran_filter_table" class="form-control"></select>
											</div>
											<div class="form-group col-xs-12 col-md-4">
													<label for="estado099_filter_table" class="control-label "><spring:message code="estado099" />:</label>
													<select name="estadoAlbaran" id="estado099_filter_table" class="form-control">
														<option value=""><spring:message code="combo.todos" /></option>
														<option value="<%=EstadoAlbaranEnum.PENDIENTE_ASOCIAR_ALBARAN.getValue()%>"><spring:message code="<%=EstadoAlbaranEnum.PENDIENTE_ASOCIAR_ALBARAN.getLabel()%>" /></option>
														<option value="<%=EstadoAlbaranEnum.PENDIENTE_ENVIAR_IZO.getValue()%>"><spring:message code="<%=EstadoAlbaranEnum.PENDIENTE_ENVIAR_IZO.getLabel()%>" /></option>
														<option value="<%=EstadoAlbaranEnum.ENVIADO_IZO.getValue()%>"><spring:message code="<%=EstadoAlbaranEnum.ENVIADO_IZO.getLabel()%>" /></option>
														<%-- <option value="<%=EstadoAlbaranEnum.PAGADO.getValue()%>"><spring:message code="<%=EstadoAlbaranEnum.PAGADO.getLabel()%>" /></option> --%>
													</select>
											</div>
										</div>
										<div class="row ">
											<div class="form-group col-xs-12 col-md-3 col-lg-3 col-xl-4">
													<label for="tipoTarea_filter_table" class="control-label "><spring:message code="idTipoTarea081" />:</label>
													<select name="idAlbaranTipoTarea" id="tipoTarea_filter_table" class="form-control col-80por">
														<option value=""><spring:message code="combo.todos" /></option>
														<option value="<%=TipoTareaGestionAsociadaEnum.TRADUCIR.getValue()%>"><spring:message code="<%=TipoTareaGestionAsociadaEnum.TRADUCIR.getLabel()%>" /></option>
														<option value="<%=TipoTareaGestionAsociadaEnum.REVISAR.getValue()%>"><spring:message code="<%=TipoTareaGestionAsociadaEnum.REVISAR.getLabel()%>" /></option>
													</select>
											</div>
											<div class="form-group col-xs-12 col-md-2 col-lg-2 col-xl-2">
													<label for="idTarea_filter_table" class="control-label "><spring:message code="label.idAlbaranTarea" />:</label>
													<input type="text" name="idAlbaranTarea" class="form-control numeric" id="idTarea_filter_table" />
											</div>
											
										</div>
										<!-- Botonera del formulario de filtrado -->
										<div class="form-group">
											<div id="pagamentos_filter_buttonSet" class="pull-right" style="margin:0.5rem">
												<!-- Botón de filtrado -->
												<button id="pagamentos_filter_filterButton" type="button" class="ui-button ui-widget ui-state-default ui-corner-all"><spring:message code="filter" /></button>
												<a id="pagamentos_filter_limpiar" href="javascript:void(0)" class="rup-enlaceCancelar"><spring:message code="clear" /></a>
											</div>
										</div>
										</div>
									</fieldset>
								</form>
						</div>
						<!-- 						Fin campos del formulario de filtrado -->
						<div class="table pb-2">
							<!-- Tabla -->
							<table id="pagamentos"></table>
							<!-- Barra de paginación -->
							<div id="pagamentos_pager"></div>
						</div>
			
				</div>
			</div>
		</div>
	</div>
</div>