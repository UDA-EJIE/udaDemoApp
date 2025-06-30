<%@include file="/WEB-INF/includeTemplate.inc"%>
<div class="container">
	<div class="row cabecera" id="subcabecera">
		<div class="col-md-1 cabecera-inicio">
			<img src="${staticsUrl}/aa79b/images/doc-search.png">
		</div>
		<div class="triangle-right"></div>
		<div class="col-md-4 cabecera-second">
			<p class="cabecera-numero">
				<span class="cabecera_numExpediente"><spring:message code="label.numExp"/>: </span>
				<span id="cabecera_numExpediente"></span>
				
				<span class="cabecera_numExpediente"><spring:message code="label.tipo"/>:</span>
				<span id="cabecera_tipoExpediente"></span>
			</p>
			<p class="cabecera-title"><span id="cabecera_tituloExpediente"></span></p>
		</div>
		
		<div class="col-md-6 cabecera-second pull-right ">
			<div class="row cabecera-data ">
				
				<!-- <div class="col-md-6 data">
					<p class="tit-data"><spring:message code="label.solicitante"/></p>
					<p class="txt-data"><span id="cabecera_solicitante"></span></p>
				</div>
				<div class="col-md-6 data">
					<p class="tit-data">
						<spring:message code="label.contacto"/> <span id="cabecera_esVip" class="vip"><spring:message code="label.vip"/></span> 
					</p>
					<p class="txt-data"><span id="cabecera_contacto"></span></p>
				</div>-->
				
				<p class="cabecera-numero">
				<span class="cabecera_numExpediente"><spring:message code="label.solicitante"/>: </span>
				<span id="cabecera_solicitante"></span>
				</p>
				
				<p class="cabecera-numero">
				<span class="cabecera_numExpediente"><spring:message code="label.contacto"/>: <span id="cabecera_esVip" class="vip"><spring:message code="label.vip"/></span></span>
				<span id="cabecera_contacto"></span>
				</p>
				
				
			</div>
		</div>
	</div>
</div>
