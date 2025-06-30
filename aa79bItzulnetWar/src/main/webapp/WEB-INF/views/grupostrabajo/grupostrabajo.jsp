<%@include file="/WEB-INF/includeTemplate.inc"%>
<%@page import="com.ejie.aa79b.model.enums.TipoEntidadEnum"%>
<%@page import="com.ejie.aa79b.model.enums.TipoExpedienteGrupoEnum"%>
<%@page import="com.ejie.aa79b.model.enums.EstadoEnum"%>
<%@include file="/WEB-INF/includeTemplate.inc"%>
<div class="container-fluid aa79b-fade in" id="divGruposTrabajo">
	<h2>
		<spring:message code="menu.gestionGruposTrabajo" />
	</h2>
	<hr class="mb-2">
	<div id="grupostrabajo_div" class="rup-table-container">
		<div id="grupostrabajo_feedback"></div>
		<!-- Feedback de la tabla -->
		<div id="grupostrabajo_toolbar"></div>
		<!-- Botonera de la tabla -->
		<div id="contenFormularios" class="filterForm ">
			<!-- Capa contenedora del formulario de filtrado -->
			<form id="grupostrabajo_filter_form" Class="form-horizontal">
				<!-- Formulario de filtrado -->
				<div id="grupostrabajo_filter_toolbar" class="formulario_legend"></div>
				<!-- Barra de herramientas del formulario de filtrado -->
				<fieldset id="grupostrabajo_filter_fieldset"
					class="rup-table-filter-fieldset">
					<div class="p-2">
						<div class="row">
							<!-- Campos del formulario de filtrado -->
							<div class="form-group col-md-3">
								<label for="idTipoExpediente_filter_table" class="control-label"
									data-i18n="label.tipoExpediente"><spring:message
										code="label.tipoExpediente" />:</label> <select
									name="idTipoExpediente" id="idTipoExpediente_filter_table"
									class="form-control">
									<option value="" selected="selected"><spring:message
											code="combo.todos" /></option>
									<c:set var="labelsTipoExpediente"
										value="<%=TipoExpedienteGrupoEnum.values()%>" />
									<c:forEach items="${labelsTipoExpediente}" var="tipoExpediente">
										<option value="${tipoExpediente.value}"><spring:message
												code="${tipoExpediente.label}" /></option>
									</c:forEach>
								</select>
							</div>
						</div>
						<div class="row">
							<div class="form-group col-md-6">
								<label for="idTiposEntidad_filter_table" class="control-label"
									data-i18n="label.tipoEntidad"><spring:message
										code="label.tipoEntidad" />:</label>
								<div id="idTiposEntidad_filter_table">
									<div class="col-md-3">
										<input type="radio" name="tipoEntidad" id="tipoEntidad_T"
											value="" data-on-text='<spring:message code="label.todas"/>' />
										<label for="tipoEntidad_T"
											class="radio-inline rup-table-filter-fieldset"
											data-i18n="label.tipoEntidad"><spring:message
												code="label.todas" /></label>
									</div>
									<c:set var="labelsTipoEntidad"
										value="<%=TipoEntidadEnum.values()%>" />
									<c:forEach items="${labelsTipoEntidad}" var="tipoEntidad">
										<div class="col-md-3">
											<input type="radio" name="tipoEntidad"
												id="tipoEntidad_${tipoEntidad.value}"
												value="${tipoEntidad.value}"
												data-on-text='<spring:message code="${tipoEntidad.label}"/>' />
											<label for="tipoEntidad_${tipoEntidad.value}"
												class="radio-inline rup-table-filter-fieldset"
												data-i18n="label.tipoEntidad"><spring:message
													code="${tipoEntidad.label}" /></label>
										</div>
									</c:forEach>
								</div>
							</div>
						
							<div class="form-group col-md-6">
								<label for="idEntidadSolicitante_filter_table"
									class="control-label" data-i18n="label.entidadSolicitante"><spring:message
										code="label.entidadSolicitante" />:</label> <input
									id="idEntidadSolicitante_filter_table" class="form-control"
									name="entidadSolicitante.codigoCompleto" />
							</div>
						</div>
						<div class="row">
							<div class="form-group col-md-5">
								<label for="dniResponsable_filter_table"
									class="control-label" data-i18n="label.responsable"><spring:message
										code="label.responsable" />:</label> <select name="responsable.dni"
									id="dniResponsable_filter_table" class="form-control"></select>
							</div>
							<div class="form-group col-md-5">
								<label for="codTrabajador_filter_table"
									class="control-label"
									data-i18n="label.traductorInterprete"><spring:message
										code="label.traductorInterprete" />:</label> <select
									name="traductor.dni" id="codTrabajador_filter_table"
									class="form-control"></select>
							</div>
						</div>
						<div class="row">
							<div class="form-group col-md-2">
								<label for="estado_filter_table" class="control-label"
									data-i18n="label.estado"><spring:message
										code="label.estado" />:</label> <select name="estado"
									id="estado_filter_table" class="form-control">
									<option value=""><spring:message code="combo.todos" /></option>
									<option value="<%=Constants.ALTA%>" selected="selected"><spring:message
											code="estado.alta" /></option>
									<option value="<%=Constants.BAJA%>"><spring:message
											code="estado.baja" /></option>
								</select>
							</div>
						</div>
						<!-- Botonera del formulario de filtrado -->
						<div class="form-group">
							<div id="grupostrabajo_filter_buttonSet" class="pull-right">
								<!-- Botón de filtrado -->
								<input id="grupostrabajo_filter_filterButton" type="button"
									class="ui-button ui-widget ui-state-default ui-corner-all"
									value='<spring:message code="filter" />' />
								<!-- Enlace de limpiar -->
								<a id="grupostrabajo_filter_cleanLink" href="javascript:void(0)"
									class="rup-enlaceCancelar"><spring:message code="clear" /></a>
							</div>
						</div>
					</div>
				</fieldset>
			</form>
		</div>
		<div class="table">
			<table id="grupostrabajo"></table>
		</div>
		<div id="grupostrabajo_pager"></div>
	</div>
</div>
<div id="divDetalle" class="aa79b-fade collapsed"></div>