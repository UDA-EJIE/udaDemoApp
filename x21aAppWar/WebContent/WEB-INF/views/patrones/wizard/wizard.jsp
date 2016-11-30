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

<form id="wizardForm">
	<fieldset>
		<legend class="wizardLegend">Datos de la cuenta</legend>
		<label for="username">Usuario</label> <input id="username" name="username" type="text" /> <br/><br/>
		<label for="password">Password</label> <input id="password" name="password" type="password" /> <br/><br/>
		<label for="ejie">Ejie</label> 
			<select id="ejie">
				<option value="0" selected="selected">No</option>
				<option value="1">Sí</option>
			</select> 
		<br/><br/>
	</fieldset>
	<fieldset>
		<legend class="wizardLegend">Deshabilitado</legend>
	</fieldset>
	<fieldset>
		<legend class="wizardLegend">Datos del trabajador</legend>
		<div id="tabs"></div>
		<div id="empleado" style="display: none;">
			<input type="radio" name="group1" value="hombre" id="radio_hombre" checked="checked"/> <label for="radio_hombre">Hombre</label>
			<input type="radio" name="group1" value="mujer" id="radio_mujer"/> <label for="radio_mujer">Mujer</label> <br/><br/>
			<label for="nombre">Nombre</label> <input id="nombre" name="nombre" type="text" /> <br/><br/>
			<label for="apellido">Apellido</label> <input id="apellido" name="apellido" type="text" /> <br/><br/>
			<label for="provincia">Delegación</label> <select id="provincia" name="provincia" class="rup-combo"></select>
		</div>
		<div id="empresa_datos" style="display: none;">
			<label for="empresa_nombre">Nombre</label> <input id="empresa_nombre" name="empresa_nombre" type="text" /> <br/><br/> 
			<label for="empresa_web">Website</label> <input id="empresa_web" name="empresa_web" type="text" />  <br/><br/>
			<label for="empresa_email">E-mail</label> <input id="empresa_email" name="empresa_email" type="text" /> <br/><br/>
		</div>
		<div id="empresa_araba" style="display: none;">
			<label for="direccion_araba">Dirección</label> <input id="direccion_araba" name="direccion_araba" type="text" /> <br/><br/> 
			<label for="telefono_araba">Teléfono</label> <input id="telefono_araba" name="telefono_araba" type="text" /> <br/><br/>
		</div>
		<div id="empresa_bizkaia" style="display: none;">
			<label for="direccion_bizkaia">Dirección</label> <input id="direccion_bizkaia" name="direccion_bizkaia"  type="text" /> <br/><br/>
			<label for="telefono_bizkaia">Teléfono</label> <input id="telefono_bizkaia" name="telefono_bizkaia" type="text" /> <br/><br/> 
		</div>
		<div id="empresa_gipuzkoa" style="display: none;">
			<label for="direccion_gipuzkoa">Dirección</label> <input id="direccion_gipuzkoa" name="direccion_gipuzkoa"  type="text" /> <br/><br/>
			<label for="telefono_gipuzkoa">Teléfono</label> <input id="telefono_gipuzkoa" name="telefono_gipuzkoa" type="text" /> <br/><br/> 
		</div>
		<div id="otros_datos" style="display: none;">
			<label for="aficiones">Aficiones</label><br/><br/> 
			<textarea id="aficiones" name="aficiones" cols="150" rows="10"></textarea> 
		</div>
	</fieldset>
	<fieldset>
		<legend class="wizardLegend">Plan de trabajo</legend>
		<label for="desde">Fecha desde</label><label for="desde" id="intervalo-mask"/></label>: <input type="text" id="desde" name="desde"/> 
		<label for="hasta">hasta: </label><input type="text" id="hasta" name="hasta"/>
		<br/><br/>
		<label for="hora_entrada">Horario desde</label><label for="hora_entrada" id="hora-mask"></label>: <input id="hora_entrada" name="hora_entrada" type="text"/>
		<label for="hora_entrada">hasta: </label><input id="hora_salida" name="hora_salida" type="text"/>
		<br/><br/>
		<label for="dias">Días de trabajo</label>
		<select id="dias"></select>
		<br/><br/>
		<label for="cliente">Meses de trabajo en cliente</label>
		<select id="cliente"></select>
		<br/><br/>
		<div id="meses">
			<h1><a>Jornada reducida</a></h1>
			<div>
				<div style="float:left; margin-left: 1em;">
					<input type="checkbox" name="mes_enero" value="0"/><label for="mes_enero"> Enero</label><br/>
					<input type="checkbox" name="mes_febrero" value="1"/><label for="mes_febrero"> Febrero</label><br/>
					<input type="checkbox" name="mes_marzo" value="2"/><label for="mes_marzo"> Marzo</label><br/>
				</div>
				<div style="float:left; margin-left: 6em;">
					<input type="checkbox" name="mes_abril" value="3"/><label for="mes_abril"> Abril</label><br/>
					<input type="checkbox" name="mes_mayo" value="4"/><label for="mes_mayo"> Mayo</label><br/>
					<input type="checkbox" name="mes_junio" value="5"/><label for="mes_junio"> Junio</label><br/>
				</div>
				<div style="float:left; margin-left: 6em;">
					<input type="checkbox" name="mes_julio" value="6"/><label for="mes_julio"> Julio</label><br/>
					<input type="checkbox" name="mes_agosto" value="7"/><label for="mes_agosto"> Agosto</label><br/>
					<input type="checkbox" name="mes_septiembre" value="8"/><label for="mes_septiembre"> Septiembre</label><br/>
				</div>
				<div style="float:left; margin-left: 6em;">
					<input type="checkbox" name="mes_octubre" value="9"/><label for="mes_octubre"> Octubre</label><br/>
					<input type="checkbox" name="mes_noviembre" value="10"/><label for="mes_noviembre"> Noviembre</label><br/>
					<input type="checkbox" name="mes_diciembre" value="11"/><label for="mes_diciembre"> Diciembre</label><br/>
				</div>
			</div>
		</div>
		<br/><br/>
	</fieldset>
	<br/>
	<input id="saveForm" type="button" value="Enviar" />
</form>
