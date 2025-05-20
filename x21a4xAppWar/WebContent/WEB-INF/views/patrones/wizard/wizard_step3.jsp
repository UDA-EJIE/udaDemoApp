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
<fieldset>
	<legend class="wizardLegend">Plan de trabajo</legend>
	<label for="desde">Fecha desde</label><label for="desde" id="intervalo-mask"/></label>:
	<form:input path="desde"/>
	<label for="hasta">hasta</label>:
	<form:input path="hasta"/>
	<br/><br/>
	<label for="hora_entrada">Horario desde</label><label for="hora_entrada" id="hora-mask"></label>:
	<form:input path="hora_entrada"/>
	<label for="hora_entrada">hasta: </label>
	<form:input path="hora_salida"/>
	<br/><br/>
	<label for="dias">Días de trabajo</label>
	<form:select path="dias"/>
	<br/><br/>
	<label for="cliente">Meses de trabajo en cliente</label>
	<form:select path="cliente"/>
	<br/><br/>
	<div id="meses">
		<h1><a>Jornada reducida</a></h1>
		<div>
			<div style="float:left; margin-left: 1em;">
				<form:checkbox path="mes_enero" value="0"/><label for="mes_enero"> Enero</label><br/>
				<form:checkbox path="mes_febrero" value="1"/><label for="mes_febrero"> Febrero</label><br/>
				<form:checkbox path="mes_marzo" value="2"/><label for="mes_marzo"> Marzo</label><br/>
			</div>
			<div style="float:left; margin-left: 6em;">
				<form:checkbox path="mes_abril" value="3"/><label for="mes_abril"> Abril</label><br/>
				<form:checkbox path="mes_mayo" value="4"/><label for="mes_mayo"> Mayo</label><br/>
				<form:checkbox path="mes_junio" value="5"/><label for="mes_junio"> Junio</label><br/>
			</div>
			<div style="float:left; margin-left: 6em;">
				<form:checkbox path="mes_julio" value="6"/><label for="mes_julio"> Julio</label><br/>
				<form:checkbox path="mes_agosto" value="7"/><label for="mes_agosto"> Agosto</label><br/>
				<form:checkbox path="mes_septiembre" value="8"/><label for="mes_septiembre"> Septiembre</label><br/>
			</div>
			<div style="float:left; margin-left: 6em;">
				<form:checkbox path="mes_octubre" value="9"/><label for="mes_octubre"> Octubre</label><br/>
				<form:checkbox path="mes_noviembre" value="10"/><label for="mes_noviembre"> Noviembre</label><br/>
				<form:checkbox path="mes_diciembre" value="11"/><label for="mes_diciembre"> Diciembre</label><br/>
			</div>
		</div>
	</div>
	<br/><br/>
</fieldset>
