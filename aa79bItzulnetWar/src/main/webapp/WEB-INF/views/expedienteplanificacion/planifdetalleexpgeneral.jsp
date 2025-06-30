<%@page import="com.ejie.aa79b.model.enums.EstadoEnum"%>
<%@page import="com.ejie.aa79b.model.enums.TipoExpedienteEnum"%>
<%@page import="com.ejie.aa79b.model.enums.AportaSubsanacionEnum"%>
<%@page import="com.ejie.aa79b.model.enums.ActivoEnum"%>
<%@page import="com.ejie.aa79b.model.enums.TipoEntidadEnum"%>
<%@page import="com.ejie.aa79b.model.enums.EstadoAceptacionTareaEnum"%>
<%@page import="com.ejie.aa79b.model.enums.EstadoEjecucionTareaEnum"%>
<%@include file="/WEB-INF/includeTemplate.inc"%>

<div id="divCapaDetalle">
	<%@include file="/WEB-INF/views/cabeceraexpediente/cabeceraexpediente.jsp"%>
	<%@include file="/WEB-INF/views/bitacoraexpediente/bitacoraexpediente.jsp"%>
	<div id="divDetalleExpediente">
		<%@include file="/WEB-INF/views/expedientesrelacionados/expedientesrelacionados.jsp"%>
		<div id="divDetalleExpedienteTabs">	
			<div id="detalleExpediente_div" class="rup-table-container ">
				<div id="detalleExpedientePlanificacion_toolbar" class="mt-1"></div>
				<div id="tabsDetalleEspediente"></div>
				
				<div id="pestanasExpediente">
					<div id="tabsExpediente"></div>
				</div>
			</div>
		</div>
	</div>
	
	
</div>