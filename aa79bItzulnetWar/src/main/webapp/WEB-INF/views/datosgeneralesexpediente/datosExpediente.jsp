<%@page import="com.ejie.aa79b.model.enums.TipoExpedienteEnum"%>
<%@include file="/WEB-INF/includeTemplate.inc"%>

<fieldset id="datos_expediente_filter_fieldset">
	<legend>
		<spring:message code="label.datosExpediente"></spring:message>
	</legend>
	<div class="row">
		<div class="oculto">
			<label class="control-label" for="numeroExpediente"><spring:message
					code="label.numeroExpediente" />:</label> <input type="hidden"
				name="numExp" id="numeroExpediente">
		</div>
		<div class="form-group col-lg-2">

			<label class="control-label" for="idTipoExpediente"><spring:message
					code="label.tipoExpediente" /> (*):</label>
			<div class="divComboW125">
				<select name="idTipoExpediente" id="idTipoExpediente"
					class="form-control">
					<option value=""><spring:message code="combo.seleccione" /></option>
					<option value="<%=TipoExpedienteEnum.INTERPRETACION.getValue()%>"><spring:message
							code="<%=TipoExpedienteEnum.INTERPRETACION.getLabel()%>" /></option>
					<option value="<%=TipoExpedienteEnum.TRADUCCION.getValue()%>"><spring:message
							code="<%=TipoExpedienteEnum.TRADUCCION.getLabel()%>" /></option>
					<option value="<%=TipoExpedienteEnum.REVISION.getValue()%>"><spring:message
							code="<%=TipoExpedienteEnum.REVISION.getLabel()%>" /></option>
				</select>
			</div>
		</div>
		<div class="form-group col-md-12 col-lg-3 grupoFechaHora">
			<label class="control-label valFecha" for="fechaAlta"><spring:message
					code="label.fechaHoraAlta" /> (*):</label> <input type="text"
				name="fechaAlta" class="form-control" id="fechaAlta"> <input
				type="text" name="horaAlta" class="form-control campohora"
				id="horaAlta" placeholder="hh:mm" maxlength="5">
		</div>
		<div class="form-group col-lg-5">
			<label class="control-label" for="tituloExpediente"><spring:message
					code="label.titulo" /> (<spring:message
					code="label.descripcionFactura" />) (*):</label> <input type="text" name="titulo"
				class="form-control" id="tituloExpediente" maxlength="150">
		</div>
	</div>
</fieldset>