<%@include file="/WEB-INF/includeTemplate.inc"%> 
<div class="container-fluid"> 

	<%@include file="/WEB-INF/views/cabeceraexpediente/cabeceraexpediente.jsp"%>
	
	<div id="cabecera_altaExpedientes" class="collapsed">
		<%@include file="/WEB-INF/views/bitacoraexpediente/bitacoraexpediente.jsp"%>
	</div>
	
	<div id="detalleExpediente_div" class="rup-table-container capaExpedienteMYO">
		<div id="expediente">
			<div id="expediente_div" class="">
				<div id="expediente_toolbar"></div> <!-- Botonera -->
			</div>
		</div>
		<div id="pestanasExpediente">
			<div id="tabsExpediente"></div>
		</div>
	</div>
</div>

<%@include file="/WEB-INF/views/expedientesrelacionados/expedientesrelacionados.jsp"%>
<%@include file="/WEB-INF/views/contactofacturacionexpediente/contactofacturacionexpediente.jsp"%>
