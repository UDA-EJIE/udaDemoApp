<%@include file="/WEB-INF/includeTemplate.inc"%>
<div class="container-fluid aa79b-fade in" id="divPestPlanificacion">
	
	<div class="row mt-15r">
		<div class="col-xl-12  col-xs-12  col-sm-12">
			<div class="butstyle">
				<h3><spring:message code="comun.expedientes"></spring:message></h3>
				<canvas id="graficoPlanificacionExpedientesBar" height="85%" ></canvas>
			</div>
		</div>
	</div>	
	
	<div class="row">
		<div class="col-xl-12  col-xs-12  col-sm-12">
			<div class="butstyle">
				<h3><spring:message code="comun.tareas"></spring:message></h3>
				<canvas id="graficoPlanificacionTareasBar" height="70%" ></canvas>
			</div>
		</div>
	</div>
</div>