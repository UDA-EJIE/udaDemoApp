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
<h2>Wizard</h2>

<div id="wizard_options">
	<fieldset>
		<legend style="float: left;">Generar resumen: </legend>
		<input type="radio" name="summary" value="true" id="radio_summary_yes" checked="checked"/> <label for="radio_summary_yes">Sí</label>
		<input type="radio" name="summary" value="false" id="radio_summary_no"/> <label for="radio_summary_no">No</label> <br/><br/>
	</fieldset>
	<fieldset>
		<legend style="float: left;">Resumen con 'accordion': </legend>
		<input type="radio" name="accordion" value="true" id="radio_accordion_yes" checked="checked"/> <label for="radio_accordion_yes">Sí</label>
		<input type="radio" name="accordion" value="false" id="radio_accordion_no"/> <label for="radio_accordion_no">No</label> <br/><br/>
	</fieldset>
	<fieldset>
		<legend style="float: left;">Pestañas convertidas a 'accordion': </legend> 
		<input type="radio" name="tabs2accordion" value="true" id="radio_tab2accordion_yes" checked="checked"/> <label for="radio_tab2accordion_yes">Sí</label>
		<input type="radio" name="tabs2accordion" value="false" id="radio_tab2accordion_no"/> <label for="radio_tab2accordion_no">No</label> <br/><br/>
		<input type="button" id="makeWizard" value="Convertir formulario en Wizard" /> <br/><br/>
	</fieldset>
</div>

<form:form id="wizardForm" modelAttribute="randomForm">
	<fieldset>
		<legend class="wizardLegend">Datos de la cuenta</legend>
		<label for="username">Usuario</label> <form:input path="username"/> <br/><br/>
		<label for="password">Password</label> <form:password path="password"/> <br/><br/>
		<label for="ejie">Ejie</label> 
			<form:select path="ejie">
				<form:option value="0" selected="selected" label="No"/>
				<form:option value="1" label="Sí"/>
			</form:select> 
		<br/><br/>
	</fieldset>
	<fieldset>
		<legend class="wizardLegend">Deshabilitado</legend>
	</fieldset>
	<fieldset>
		<legend class="wizardLegend">Datos del trabajador</legend>
		<div id="tabs"></div>
		<div id="empleado" style="display: none;">
			<form:radiobutton path="group1" value="hombre" id="radio_hombre" checked="checked"/> <label for="radio_hombre">Hombre</label>
			<form:radiobutton path="group1" value="mujer" id="radio_mujer"/> <label for="radio_mujer">Mujer</label> <br/><br/>
			<label for="nombre">Nombre</label> <form:input path="nombre"/> <br/><br/>
			<label for="apellido">Apellido</label> <form:input path="apellido"/> <br/><br/>
			<label for="provincia">Delegación</label> <form:select path="provincia" class="rup-combo"/>
		</div>
		<div id="empresa_datos" style="display: none;">
		<label for="empresa_nombre">Nombre</label>
		<form:input path="empresa_nombre"/>
		<br/><br/> 
		<label for="empresa_web">Website</label>
		<form:input path="empresa_web"/>
		<br/><br/>
		<label for="empresa_email">E-mail</label>
		<form:input path="empresa_email"/>
		<br/><br/>
	</div>
	<div id="empresa_araba" style="display: none;">
		<label for="direccion_araba">Dirección</label>
		<form:input path="direccion_araba"/>
		<br/><br/> 
		<label for="telefono_araba">Teléfono</label>
		<form:input path="telefono_araba"/>
		<br/><br/>
	</div>
	<div id="empresa_bizkaia" style="display: none;">
		<label for="direccion_bizkaia">Dirección</label>
		<form:input path="direccion_bizkaia"/>
		<br/><br/> 
		<label for="telefono_bizkaia">Teléfono</label>
		<form:input path="telefono_bizkaia"/>
		<br/><br/>
	</div>
	<div id="empresa_gipuzkoa" style="display: none;">
		<label for="direccion_gipuzkoa">Dirección</label>
		<form:input path="direccion_gipuzkoa"/>
		<br/><br/> 
		<label for="telefono_gipuzkoa">Teléfono</label>
		<form:input path="telefono_gipuzkoa"/>
		<br/><br/>
	</div>
	<div id="otros_datos" style="display: none;">
		<label for="aficiones">Aficiones</label><br/><br/> 
		<form:textarea path="aficiones" cols="150" rows="10"/> 
	</div>
	</fieldset>
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
	<br/>
	<input id="saveForm" type="button" value="Enviar" />
</form:form>
