<%@include file="/WEB-INF/includeTemplate.inc"%>
<%@page import="com.ejie.aa79b.model.enums.TipoEntidadEnum"%>

<div  id="divReasignarGestorConsulta">
		<h2>
			<spring:message code="label.reasignarGestor" />
		</h2>
		<div id="reasignarGestorConsulta_div" class="row">
			<div id="reasignarGestorConsulta_feedback"></div>
			<div id="reasignarGestorConsulta_toolbar"></div>
			<div id="reasignarGestorConsulta_filter_div">
				<fieldset id="reasignarGestorConsulta_fieldset" class="form_fieldset">
					<form id="reasignarGestorConsultaform">
					<div class="row">
						<div class="form-group col-xs-4">
							<label for="rGCIdTiposEntidad_filter_table" class="control-label " data-i18n="label.tipoEntidadGestora"><spring:message code="label.tipoEntidadGestora"/>:</label>
							<div id="rGCIdTiposEntidad_filter_table" >
								<div class="col-md-3">
									<input type="radio" name="tipoEntidad" id="rGCTipoEntidad_T" value="" data-on-text='<spring:message code="label.todas"/>'/>
									<label for="tipoEntidad_T" class="radio-inline rup-table-filter-fieldset" data-i18n="label.tipoEntidadGestora"><spring:message code="label.todas"/></label>
								</div>
								<c:set var="labelsTipoEntidad" value="<%=TipoEntidadEnum.values()%>"/>
								<c:forEach items="${labelsTipoEntidad}" var="tipoEntidad">
								<div class="col-md-3">
									<input type="radio" name="tipoEntidad" id="rGCTipoEntidad_${tipoEntidad.value}" value="${tipoEntidad.value}"  data-on-text='<spring:message code="${tipoEntidad.label}"/>'/>
									<label for="tipoEntidad_${tipoEntidad.value}" class="radio-inline rup-table-filter-fieldset" data-i18n="label.tipoEntidad"><spring:message code="${tipoEntidad.label}"/></label>
								</div>
								</c:forEach>
							</div>
							<input type="hidden" name="gestorExpediente.entidad.tipo" id="rGCGestorExpedienteEntidadTipo"></input>
						</div>
						<div class="form-group col-xs-12 col-md-5">
							<label for="rGCIdEntidadSolicitante_filter_table" class="control-label " data-i18n="label.entidadGestora"><spring:message code="label.entidadGestora" />:</label>
							<select id="rGCIdEntidadSolicitante_filter_table" class="form-control" name="gestorExpediente.entidad.codigoCompleto"></select>	
						</div>
					</div>
					
					<div class="row">
						<div id="rGCDiv_contactoGestor_filter_table" class="form-group col-xs-12 col-md-5">
							<div id="rGCAutocompleteContainer_contactoGestor_filter_table">
								<label for="rGCContactoGestor_filter_table" class="control-label " data-i18n="label.contactoGestor"><spring:message code="label.contactoGestor" />:</label>
								<input id="rGCContactoGestor_filter_table" class="form-control" name="gestorExpediente.solicitante.dni"/>
							</div>
						</div>
					</div>
<!-- 						<div class="form-group col-md-4"> -->
<%-- 							<label for="reasignarGestorConsulta" class="control-label"><spring:message code="label.gestor" />:</label> --%>
<!-- 							<select id="reasignarGestorConsulta" class="form-control" /> -->
<!-- 						</div> -->
					</form>
				</fieldset>
			</div>
		</div>
</div>