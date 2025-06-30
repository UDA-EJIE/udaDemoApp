<%@include file="/WEB-INF/includeTemplate.inc"%>
<%@page import="com.ejie.aa79b.model.enums.TipoExpedienteEnum"%>
<%@page import="com.ejie.aa79b.model.enums.TipoEntidadEnum"%>
<div id="divRequerirSubsanacionGeneral">
	<div id="requerirSubsanacionGeneralDiv" class="container-fluid">
		<h2><spring:message code="menu.requerirSub"></spring:message></h2>
		<hr class="mb-2">
		<div class="rup-table-container">
			<div id="requerirSubsanacion_feedback"></div>						
			<div id="requerirSubsanacion_toolbar"></div>	
			<div id="contenFormularios" class="rup-table-filter">
				<form id="requerirSubsanacion_filter_form" class="form-horizontal">
					<div id="requerirSubsanacion_filter_toolbar"
						class="formulario_legend filterCabecera"></div>
					<fieldset id="requerirSubsanacion_filter_fieldset"
						class="rup-table-filter-fieldset filterCuerpo">
						<div class="p-2">
							<div class="row">
								<!-- Campos del formulario de filtrado -->
								<div class="form-group col-xs-12 col-md-3">
									<label for="numExp_filter_table" class="control-label"><spring:message code="label.numeroExpediente"/>:</label>
									<div>
										<label for="anyo_filter_table" class="control-label oculto"><spring:message code="label.anyo"/></label>
										<input type="text" name="anyo" class=" numeric" style="width: 20%;" id="anyo_filter_table" maxlength="2"/>
										<label class="control-label" style="width: 3%;">/</label>
										<input type="text" name="numExp" class=" numeric" style="width: 73%;" id="numExp_filter_table" maxlength="6"/>
									</div>
								</div>
								<div class="form-group col-xs-12 col-md-2">
									<label for="idTipoExpediente_filter_table" class="control-label "><spring:message code="label.tipoExpediente"/>:</label>
									<select name="idTipoExpediente" class="form-control" id="idTipoExpediente_filter_table">
										<option value=""><spring:message code="combo.todos"/></option>
										<option value="<%=TipoExpedienteEnum.TRADUCCION.getValue()%>"><spring:message code="<%=TipoExpedienteEnum.TRADUCCION.getLabel()%>"/></option>
										<option value="<%=TipoExpedienteEnum.REVISION.getValue()%>"><spring:message code="<%=TipoExpedienteEnum.REVISION.getLabel()%>"/></option>
									</select>
								</div>
								<div class="form-group col-xs-12 col-md-5">
									<label for="titulo_filter_table" class="control-label "><spring:message code="label.titulo"/>:</label>
									<input type="text" name="titulo" class="form-control" id="titulo_filter_table" maxlength="150"/>
								</div>
							</div>
							<div class="row">
								<div class="form-group col-xs-6 ">
									<label for="idTiposEntidad_filter_table" class="control-label " data-i18n="label.tipoEntidadGestora"><spring:message code="label.tipoEntidadGestora"/>:</label>
									<div id="idTiposEntidad_filter_table" >
										<div class="col-md-3">
											<input type="radio" name="tipoEntidad" id="tipoEntidad_T" value="" data-on-text='<spring:message code="label.todas"/>'/>
											<label for="tipoEntidad_T" class="radio-inline rup-table-filter-fieldset" data-i18n="label.tipoEntidad"><spring:message code="label.todas"/></label>
										</div>
										<c:set var="labelsTipoEntidad" value="<%=TipoEntidadEnum.values()%>"/>
										<c:forEach items="${labelsTipoEntidad}" var="tipoEntidad">
										<div class="col-md-3">
											<input type="radio" name="tipoEntidad" id="tipoEntidad_${tipoEntidad.value}" value="${tipoEntidad.value}"  data-on-text='<spring:message code="${tipoEntidad.label}"/>'/>
											<label for="tipoEntidad_${tipoEntidad.value}" class="radio-inline rup-table-filter-fieldset" data-i18n="label.tipoEntidad"><spring:message code="${tipoEntidad.label}"/></label>
										</div>
										</c:forEach>
									</div>
									<input type="hidden" name="gestorExpediente.entidad.tipo" id="gestorExpedienteEntidadTipo"></input>	
								</div>
							</div>
							<div class="row">
								<div class="form-group col-xs-12 col-md-5">
									<label for="idEntidadSolicitante_filter_table" class="control-label " data-i18n="label.entidadGestora"><spring:message code="label.entidadGestora" />:</label>
									<select id="idEntidadSolicitante_filter_table" class="form-control" name="gestorExpediente.entidad.codigoCompleto"></select>	
								</div>
								<div class="form-group col-xs-12 col-md-5">
									<label for="contactoGestor_filter_table" class="control-label " data-i18n="label.contactoGestor"><spring:message code="label.contactoGestor" />:</label>
									<input id="contactoGestor_filter_table" class="form-control" name="gestorExpediente.solicitante.dni"/>
								</div>
							</div>
							<div id="requerirSubsanacion_filter_buttonSet" class="pull-right"
								style="margin: 0 1rem 1rem 0">
								<button id="requerirSubsanacion_filter_filterButton"
									type="button"
									class="ui-button ui-widget ui-state-default ui-corner-all">
									<spring:message code="filter" />
								</button>
								<a id="requerirSubsanacion_filter_cleanLink"
									href="javascript:void(0)" class="rup-enlaceCancelar"><spring:message
										code="clear" /></a>
							</div>
						</div>
					</fieldset>
				</form>
			</div>
			<div class="requerirSubsanacion_grid_div horizontal_scrollable_table" >
				<!-- Tabla -->
				<table id="requerirSubsanacion" ></table>
				<!-- Barra de paginación -->
				<div id="requerirSubsanacion_pager"></div>
			</div>
			<div class="row form-group" id="leyendaGestor">
			<div class="col-xs-8">
				<div class="col-xs-1" style="width: 35px!important;">
					<i class="fa fa-exclamation-circle iconoGestorNoActivo" aria-hidden="true"></i>
				</div>
				<label class="control-label col-xs-10 textoGestorNoActivo">
					<spring:message code="label.gestorInactivo"/>
				</label>
			</div>
		</div>
		</div>
	</div>
</div>