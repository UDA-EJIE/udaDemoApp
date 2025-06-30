<%@include file="/WEB-INF/includeTemplate.inc"%>
<div class="container-fluid aa79b-fade in" id="divPestResumen">
	
	<div class="row">
		<div class="col-xl-12  col-xs-12  col-sm-12">
			<div class="butstyle">
				<h3><spring:message code="comun.expedientes"></spring:message></h3>
				<canvas id="graficoExpedientesBar" height="80%" ></canvas>
			</div>
		</div>
	</div>	
	<div class="row">
		<div class="col-xl-10  col-xs-12  col-sm-12">
				<div class="butstyle">
					<h3><spring:message code="comun.tareas"></spring:message></h3>
					<canvas id="graficoTareasBar" height="90%" ></canvas>
				</div>
		</div>
	</div>
	<div class="row">
		<div class="col-xl-8  col-xs-12  col-sm-12">
				<div class="butstyle">
					<h3><spring:message code="comun.tramitesClientes"></spring:message></h3>
					<canvas id="graficoTramitesBar" height="100%"  ></canvas>
				</div>
		</div>
	</div>
	<label class="control-label" data-i19n="label.tramitesRechazadosAceptacPresup">(*)<spring:message code="label.tramitesRechazadosAceptacPresup" /></label>
</div>