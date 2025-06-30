<%@include file="/WEB-INF/includeTemplate.inc"%>
<div class="container">
	<div class="row cabecera" id="subcabecera">
		<div class="col-md-1 cabecera-inicio">
			<img src="${staticsUrl}/aa79b/images/doc-search.png">
		</div>
		<div class="triangle-right"></div>
		<div class="col-md-10 cabecera-second">
			<p class="cabecera-numero">
				<span class="cabecera_numExpediente"><spring:message code="label.numTrabajo"/>: </span>
				<span id="cabecera_numTrabajo"></span>
				
				<span class="cabecera_numExpediente"><spring:message code="label.titulo"/>:</span>
				<span id="cabecera_descTrabajo"></span>
			</p>
			<p class="cabecera-title"><span id="cabecera_tituloExpediente"></span></p>
		</div>
		<div class="col-md-10 cabecera-second">
			<p class="cabecera-numero">
				<span class="cabecera_numExpediente"><spring:message code="label.fechaFinPrevista"/>: </span>
				<span id="cabecera_fechaFinPrevista"></span>
			</p>
		</div>
	</div>
</div>