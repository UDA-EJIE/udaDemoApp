<%@page import="com.ejie.aa79b.model.enums.TipoEntidadEnum"%>
<%@include file="/WEB-INF/includeTemplate.inc"%>

<fieldset id="datos_solicitante_filter_fieldset">
	<legend><spring:message code="label.datosSolicitante"></spring:message></legend>
	<input type="hidden" name="gestorExpediente.entidad.codigo" id="codigoEntidadSolicitante">
	<input type="hidden" name="gestorExpediente.entidad.tipo" id="tipoEntidadSolicitante">
	<input type="hidden" name="gestorExpediente.solicitante.dni" id="dniSolicitante">
	<input type="hidden" name="gestorExpediente.solicitante.gestorExpedientesVIP" id="gestorExpedientesVIP">
	<div class="row">
		<div class="oculto">
			<label for="tipoEntidadSolicitanteDesc" class="control-label"><spring:message code="label.tipoEntidad"/> (*):</label>
			<input type="text" name="gestorExpediente.entidad.tipoDesc" class="form-control" id="tipoEntidadSolicitanteDesc" readonly="readonly" disabled="disabled"/>
			<div class="oculto">		
				<select id="descripcionTipoEntidad" class="form-control">
					<option value="<%=TipoEntidadEnum.ENTIDAD.getValue()%>"><spring:message code="<%=TipoEntidadEnum.ENTIDAD.getLabel()%>"/></option>
					<option value="<%=TipoEntidadEnum.DEPARTAMENTO.getValue()%>"><spring:message code="<%=TipoEntidadEnum.DEPARTAMENTO.getLabel()%>"/></option>
					<option value="<%=TipoEntidadEnum.EMPRESA.getValue()%>"><spring:message code="<%=TipoEntidadEnum.EMPRESA.getLabel()%>"/></option>
				</select>
			</div>
		</div>
		<div class="form-group col-lg-5">
			<label for="nombreEntidadSolicitante" class="control-label"><spring:message code="label.nombreEntidad"/> (*):</label>
			<c:choose>
			    <c:when test="${SESSION_LANGUAGE eq LANGUAGE_ES}">
			    	<input type="text" name="gestorExpediente.entidad.descAmpEs" class="form-control" id="nombreEntidadSolicitante" readonly="readonly" disabled="disabled"/>
			    </c:when>
			    <c:otherwise>
			    	<input type="text" name="gestorExpediente.entidad.descAmpEu" class="form-control" id="nombreEntidadSolicitante" readonly="readonly" disabled="disabled"/>
			    </c:otherwise>
			</c:choose>
			<div id="gestorExpedientesVIP_div" class="oculto">
		    </div>
		</div>
		<div class="form-group col-lg-5">
			<label for="nombreGestor" class="control-label"><spring:message code="label.nombreGestor"/> (*):</label>
			<input type="text" name="gestorExpediente.solicitante.nombreCompleto" class="form-control" id="nombreGestor" readonly="readonly" disabled="disabled"/>
		</div>
		<div id="gestor_EHAATaldea_div" class="form-group col-lg-1 oculto">
			<div class="link"><b>(<spring:message code="label.grupoBoletin"/>)</b></div>
		</div>	
<!-- 		<div class="form-group col-lg-1"> -->
<!-- 			<div id="gestor_modificarLink_div" class="link"> -->
<%-- 				<a id="gestor_modificarLink" href="#" class="rup-enlaceCancelar izda enlace"><spring:message code="label.modificar" /></a> --%>
<!-- 			</div> -->
<!-- 		</div> -->
	</div>
	<div id="divDatosContactoGestor" class="row collapsed">
		<div class="form-group col-lg-2">
			<label for="telefonoFijoGestor"><spring:message code="label.telefonoFijo"/>:</label>
			<input type="text" name="gestorExpediente.solicitante.datosContacto.telfijo031" class="form-control col-66por" id="telefonoFijoGestor" readonly="readonly" disabled="disabled"/>
		</div>
		<div class="form-group col-lg-2">
			<label for="telefonoMovilGestor"><spring:message code="label.telefonoMovil"/>:</label>
			<input type="text" name="gestorExpediente.solicitante.datosContacto.telmovil031" class="form-control col-66por" id="telefonoMovilGestor" readonly="readonly" disabled="disabled"/>
		</div>
		<div class="form-group col-lg-3">
			<label for="telefonoEmailGestor"><spring:message code="label.email"/>:</label>
			<input type="text" name="gestorExpediente.solicitante.datosContacto.email031" class="form-control" id="telefonoEmailGestor" readonly="readonly" disabled="disabled"/>
		</div>
	</div>
<!-- 	<div id="buscadorPersonas" class="oculto"></div> -->
</fieldset>