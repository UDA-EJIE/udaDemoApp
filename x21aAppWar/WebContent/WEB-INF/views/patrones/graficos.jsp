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
<section class="">
<h2>
	<spring:message code="charts.charts" />
</h2>
<p>
	<spring:message code="charts.descript" />
</p>

<section class="espacioLinea">
	<!--LINEA-->
	<div class=" row">
		<div class="col-xl-6  col-xs-12  col-sm-5">
			<div class="butstyle">
				<h3><spring:message code="charts.line" /></h3>
				<canvas id="graficoLine" ></canvas>
			</div>
		</div>
		<div class="col-xl-6  col-xs-12  col-sm-5">
			<div class="butstyle">
				<h3><spring:message code="charts.bar" /></h3>
				<canvas id="graficoBar" ></canvas>
			</div>
		</div>
	</div>
	<!--FIN LINEA-->
	<!--LINEA-->
	<div class=" row">
		<div class="col-xl-6  col-xs-12  col-sm-5">
			<div class="butstyle">
				<h3><spring:message code="charts.radar"/></h3>
				<canvas id="graficoRadar" ></canvas>
			</div>
		</div>
		<div class="col-xl-6  col-xs-12  col-sm-5">
			<div class="butstyle">
				<h3><spring:message code="charts.polar"/></h3>
				<canvas id="graficoPolar" ></canvas>
			</div>
		</div>
	</div>
	<!--FIN LINEA-->
	<!--LINEA-->
	<div class=" row">
		<div class="col-xl-6  col-xs-12  col-sm-5">
			<div class="butstyle">
				<h3><spring:message code="charts.pie"/></h3>
				<canvas id="graficoPie" ></canvas>
			</div>
		</div>
		<div class="col-xl-6 col-xs-12  col-sm-5 ">
			<div class="butstyle">
				<h3><spring:message code="charts.doughnut"/></h3>
				<canvas id="graficoDoughnut"></canvas>
			</div>
		</div>
	</div>
	<!--FIN LINEA-->
	<!--LINEA-->
	<div class="row">
		<div class="col-xl-6  col-xs-12  col-sm-5">
			<div class="butstyle">
				<h3><spring:message code="charts.bubble"/></h3>
				<canvas id="graficoBubble" ></canvas>
			</div>
		</div>
		<div class=""></div>
	</div>
</section>
</section>
