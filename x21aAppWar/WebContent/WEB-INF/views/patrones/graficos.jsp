<%--  
 -- Copyright 2012 E.J.I.E., S.A.
 --
 -- Licencia con arreglo a la EUPL, Versión 1.1 exclusivamente (la «Licencia»);
 -- Solo podrá usarse esta obra si se respeta la Licencia.
 -- Puede obtenerse una copia de la Licencia en
 --
 --      http://ec.europa.eu/idabc/eupl.html
 --
 -- Salvo cuando lo exija la legislación aplicable o se acuerde por escrito, 
 -- el programa distribuido con arreglo a la Licencia se distribuye «TAL CUAL»,
 -- SIN GARANTÍAS NI CONDICIONES DE NINGÚN TIPO, ni expresas ni implícitas.
 -- Véase la Licencia en el idioma concreto que rige los permisos y limitaciones
 -- que establece la Licencia.
 --%>

<%@include file="/WEB-INF/includeTemplate.inc"%>
<h2>
	<spring:message code="charts.charts" />
</h2>
<div>
	<!--LINEA-->
	<div class="espacioLinea">
		<div class="enLinea butstyle celdaGrafico">
			<h3>
				<spring:message code="charts.line" />
			</h3>
			<canvas id="graficoLine"></canvas>
		</div>
		<div class="enLinea celdaEspacio"></div>
		<div class="enLinea butstyle celdaGrafico">
			<h3>
				<spring:message code="charts.bar" />
			</h3>
			<canvas id="graficoBar"></canvas>

		</div>
	</div>
	<!--FIN LINEA-->
	<!--LINEA-->
	<div class="espacioLinea">
		<div class="enLinea butstyle celdaGrafico">
			<h3>
				<spring:message code="charts.radar" />
			</h3>
			<canvas id="graficoRadar"></canvas>
		</div>
		<div class="enLinea celdaEspacio"></div>
		<div class="enLinea butstyle celdaGrafico">
			<h3>
				<spring:message code="charts.polar" />
			</h3>
			<canvas id="graficoPolar"></canvas>
		</div>
	</div>
	<!--FIN LINEA-->
	<!--LINEA-->
	<div class="espacioLinea">
		<div class=" butstyle  enLinea celdaGrafico">
			<h3>
				<spring:message code="charts.pie" />
			</h3>
			<canvas id="graficoPie"></canvas>
		</div>
		<div class="enLinea celdaEspacio"></div>
		<div class="enLinea butstyle celdaGrafico ">
			<h3>
				<spring:message code="charts.doughnut" />
			</h3>
			<canvas id="graficoDoughnut"></canvas>
		</div>
	</div>
	<!--FIN LINEA-->
	<!--LINEA-->
	<div class="espacioLinea">
		<div class="enLinea butstyle celdaGrafico">
			<h3>
				<spring:message code="charts.bubble" />
			</h3>
			<canvas id="graficoBubble"></canvas>
		</div>
		<div class="enLinea"></div>
		<div class="enLinea"></div>
	</div>
</div>
