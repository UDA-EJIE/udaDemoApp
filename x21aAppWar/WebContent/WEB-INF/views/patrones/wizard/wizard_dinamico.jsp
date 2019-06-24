<%--  
 -- Copyright 2012 E.J.I.E., S.A.
 --
 -- Licencia con arreglo a la EUPL, VersiÃ³n 1.1 exclusivamente (la Â«LicenciaÂ»);
 -- Solo podrÃ¡ usarse esta obra si se respeta la Licencia.
 -- Puede obtenerse una copia de la Licencia en
 --
 --      http://ec.europa.eu/idabc/eupl.html
 --
 -- Salvo cuando lo exija la legislaciÃ³n aplicable o se acuerde por escrito, 
 -- el programa distribuido con arreglo a la Licencia se distribuye Â«TAL CUALÂ»,
 -- SIN GARANTÃAS NI CONDICIONES DE NINGÃN TIPO, ni expresas ni implÃ­citas.
 -- VÃ©ase la Licencia en el idioma concreto que rige los permisos y limitaciones
 -- que establece la Licencia.
 --%>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@include file="/WEB-INF/includeTemplate.inc"%>	

<h2>Wizard dinamico</h2>

<div id="wizard_options">
	<fieldset>
		<legend style="float: left;">Generar resumen: </legend>
		<input type="radio" name="summary" value="true" id="radio_summary_yes" checked="checked"/> <label for="radio_summary_yes">SÃ­</label>
		<input type="radio" name="summary" value="false" id="radio_summary_no"/> <label for="radio_summary_no">No</label> <br/><br/>
	</fieldset>
	<fieldset>
		<legend style="float: left;">Resumen con 'accordion': </legend>
		<input type="radio" name="accordion" value="true" id="radio_accordion_yes" checked="checked"/> <label for="radio_accordion_yes">SÃ­</label>
		<input type="radio" name="accordion" value="false" id="radio_accordion_no"/> <label for="radio_accordion_no">No</label> <br/><br/>
	</fieldset>
	<fieldset>
		<legend style="float: left;">PestaÃ±as convertidas a 'accordion': </legend> 
		<input type="radio" name="tabs2accordion" value="true" id="radio_tab2accordion_yes" checked="checked"/> <label for="radio_tab2accordion_yes">SÃ­</label>
		<input type="radio" name="tabs2accordion" value="false" id="radio_tab2accordion_no"/> <label for="radio_tab2accordion_no">No</label> <br/><br/>
		<input type="button" id="makeWizard" value="Convertir formulario en Wizard" /> <br/><br/>
	</fieldset>
</div>

<!-- <form id="wizardForm"> -->
<form:form id="wizardForm" modelAttribute="randomForm">
	<fieldset>
		<legend class="wizardLegend">Datos de la cuenta</legend>
		<label for="username">Usuario</label>
		<!-- <input id="username" name="username" type="text" /> -->
		<form:input path="username" id="username" />
		<br/><br/>
		<label for="password">Password</label>
		<!-- <input id="password" name="password" type="password" /> -->
		<form:password path="password" id="password" />
		<br/><br/>
		<label for="ejie">Ejie</label> 
			<form:select path="ejie" id="ejie">
				<form:option value="0" selected="selected" label="No"/>
				<form:option value="1" label="SÃ­"/>
			</form:select> 
		<br/><br/>
	</fieldset>
	<fieldset>
		<legend class="wizardLegend">Deshabilitado</legend>
	</fieldset>
	<fieldset>
		<legend class="wizardLegend">Paso dinÃ¡mico</legend>
	</fieldset>
	<fieldset>
		<legend class="wizardLegend">Plan de trabajo</legend>
		<label for="desde">Fecha desde</label><label for="desde" id="intervalo-mask"/></label>: 
		<!-- <input type="text" id="desde" name="desde"/>  -->
		<form:input path="desde" id="desde"/>
		<label for="hasta">hasta: </label>
		<!-- <input type="text" id="hasta" name="hasta"/> -->
		<form:input path="hasta" id="hasta"/>
		<br/><br/>
		<label for="hora_entrada">Horario desde</label><label for="hora_entrada" id="hora-mask"></label>: 
		<!-- <input id="hora_entrada" name="hora_entrada" type="text"/> -->
		<form:input path="hora_entrada" id="hora_entrada"/>
		<label for="hora_entrada">hasta: </label>
		<!-- <input id="hora_salida" name="hora_salida" type="text"/> -->
		<form:input path="hora_salida" id="hora_salida"/>
		<br/><br/>
		<label for="dias">DÃ­as de trabajo</label>
		<form:select path = "dias" id="dias"/>
		<br/><br/>
		<label for="cliente">Meses de trabajo en cliente</label>
		<form:select path="cliente" id="cliente"/>
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
	<br/>
	<input id="saveForm" type="button" value="Enviar" />
</form:form>
