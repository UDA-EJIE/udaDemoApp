<%@page import="com.ejie.aa79b.model.enums.EstadoAuditoriaEnum"%>
<%@page import="com.ejie.aa79b.model.enums.TipoExpedienteEnum"%>
<%@page import="com.ejie.aa79b.model.enums.EstadoEnum"%>
<%@include file="/WEB-INF/includeTemplate.inc"%>
<div id="divAuditoriaGeneral" class="container-fluid">
	<div id="divAuditoria">
		<h2><spring:message code="menu.auditoria" /></h2>
		<div id="auditoria_div" class="rup-table-container">
			<div id="auditoria_feedback"></div>
			<!-- Feedback de la tabla -->
			<div id="auditoria_toolbar"></div>
			<!-- Botonera de la tabla -->
			<div id="contenFormularios" class="filterForm ">
				<!-- Capa contenedora del formulario de filtrado -->
				<form id="auditoria_filter_form" Class="form-horizontal">
					<!-- Formulario de filtrado -->
					<div id="auditoria_filter_toolbar" class="formulario_legend"></div>
					<!-- Barra de herramientas del formulario de filtrado -->
					<fieldset id="auditoria_filter_fieldset" class="rup-table-filter-fieldset">
						<div class="p-2">
							<div class="row">
								<!-- Campos del formulario de filtrado -->
								<div class="form-group col-xs-2 col-md-2 pr25">
									<label for="idTipoExpediente_filter_table" class="control-label "><spring:message code="label.tipoExpediente"/>:</label>
									<select name="idTipoExpediente" class="form-control" id="idTipoExpediente_filter_table">
										<option value=""><spring:message code="combo.todos"/></option>
										<option value="<%=TipoExpedienteEnum.TRADUCCION.getValue()%>"><spring:message code="<%=TipoExpedienteEnum.TRADUCCION.getLabel()%>"/></option>
										<option value="<%=TipoExpedienteEnum.REVISION.getValue()%>"><spring:message code="<%=TipoExpedienteEnum.REVISION.getLabel()%>"/></option>
									</select>
								</div>
								<div class="form-group col-xs-2 col-md-2 anyoNumExpWidth">
									<label for="numExp_filter_table" class="control-label"><spring:message code="label.numeroExpediente"/>:</label>
									<div>
										<label for="anyo_filter_table" class="control-label oculto"><spring:message code="label.anyo"/></label>
										<input type="text" name="anyo" class=" numeric" style="width: 40px;" id="anyo_filter_table" maxlength="2"/>
										<label class="control-label" style="width: 3%;">/</label>
										<input type="text" name="numExp" class=" numeric" style="width: 75px;" id="numExp_filter_table" maxlength="6"/>
									</div>
								</div>
								<div class="form-group col-xs-1 col-md-2 pr25 no-padding-left">
									<label for="estado_filter_table" class="control-label" data-i18n="label.estado"><spring:message code="label.estado" />:</label>
									<select name="estado" id="estado_filter_table" class="form-control">
										<option value=""><spring:message code="combo.todos"/></option>
										<option value="<%=EstadoAuditoriaEnum.SIN_ENVIAR.getValue()%>"><spring:message code="<%=EstadoAuditoriaEnum.SIN_ENVIAR.getLabel()%>"/></option>
										<option value="<%=EstadoAuditoriaEnum.ENVIADA.getValue()%>"><spring:message code="<%=EstadoAuditoriaEnum.ENVIADA.getLabel()%>"/></option>
										<option value="<%=EstadoAuditoriaEnum.CONFIRMADA.getValue()%>"><spring:message code="<%=EstadoAuditoriaEnum.CONFIRMADA.getLabel()%>"/></option>
									</select>
								</div>
								
								<div id="comboTrabajador" class="form-group col-xs-3 col-md-3">
									<label for="trabajador_filter_table" class="control-label"><spring:message code="label.trabajador" /></label> 
									<div class="col-xs-12 no-padding-left">
										<select name="filtroDatos" class="form-control" id="trabajador_filter_table"></select>
									</div> 
								</div>
								
							</div>
							<div class="row">
								<div class="form-group col-lg-12">
									<fieldset id="aQuienAudita_filter_fieldset" class="rup-table-filter-fieldset">
										<legend><spring:message code="label.aQuienAudita" /></legend>
										<div class="row">
											<div class="form-group col-xs-4 col-md-4">
												<label for="empresaProv_filter_table" class="control-label"><spring:message code="label.empproveedora2"/>:</label>
												<select name="lotes.empresasProveedoras.codigo" id="empresaProv_filter_table" class="form-control"></select>
											</div>
											<div class="form-group col-xs-4 col-md-4">
												<label for="nombreLote_filter_table" class="control-label"><spring:message code="label.lote"/>:</label>
												<select name="lotes.idLote" id="nombreLote_filter_table" class="form-control"></select>
											</div>
										</div>
									</fieldset>
								</div>
							</div>
							<!-- Fin campos del formulario de filtrado -->
							<div id="auditoria_filter_buttonSet" class="pull-right" style="margin:0 1rem 1rem 0">
								<!-- Botón de filtrado -->
								<button id="auditoria_filter_filterButton" type="button" class="ui-button ui-widget ui-state-default ui-corner-all">
									<spring:message code="filter" />
								</button>
								
								<!-- Enlace de limpiar -->
								<a id="auditoria_filter_cleanLink" href="javascript:void(0)" class="rup-enlaceCancelar"><spring:message code="clear" /></a>
							</div>
					</fieldset>
				</form>
			</div>
			<div class="auditoria_grid_div horizontal_scrollable_table" >
				<!-- Tabla -->
				<table id="auditoria"></table>
				<!-- Barra de paginación -->
				<div id="auditoria_pager"></div>
			</div>
			<br />
			<div class="form-group">
				<div class="col-xs-12">
					<p class="txt-legend">
						<i class="fa fa-square-o" aria-hidden="true"></i> <spring:message code="label.sinEnviar" />
					</p>
				</div>
			</div>
			<div class="form-group">
				<div class="col-xs-12">
					<p class="txt-legend">
						<i class="fa fa-share-square-o" aria-hidden="true"></i> <spring:message code="label.enviada" />
					</p>
				</div>
			</div>
			<div class="form-group">
				<div class="col-xs-12">
					<p class="txt-legend">
						<i class="fa fa-check-square-o" aria-hidden="true"></i> <spring:message code="label.confirmada" />
					</p>
				</div>
			</div>
		</div>
	</div>
</div>