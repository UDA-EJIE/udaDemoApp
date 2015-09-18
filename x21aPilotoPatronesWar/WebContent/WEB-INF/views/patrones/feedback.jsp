<%--  
 -- Copyright 2011 E.J.I.E., S.A.
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
<h2>Feedback</h2>
<hr/>
Texto pre capa
<div id="feedback"></div>
Texto post capa<br/>
<hr/>
<div>
	Valores feedback:
		<label for="feedback_type">type:</label>
		<select id="feedback_type" >
			<option value="">---</option>
			<option value="ok" selected="selected">ok</option>
			<option value="alert">alert</option>
			<option value="error">error</option>
		</select>
	<br><br>
	
	<label for="feedback_imgClass">imgClass:</label> 
	<input id="feedback_imgClass" type="text" /> (feedbackImgPruebas) 
	<br><br>
	
	<label for="feedback_delay">delay (ms):</label>
	<input id="feedback_delay" type="text" />
	<br><br>
		
	<label for="feedback_fadeSpeed">fadeSpeed (ms):</label>
	<input id="feedback_fadeSpeed" type="text" />
	<br><br>
	
	<label for="feedback_gotoTop">gotoTop:</label>
	<select id="feedback_gotoTop">
		<option value="true">true</option>
		<option value="false">false</option>
	</select>
	<br><br>

	<label for="feedback_block">block:</label>
	<select id="feedback_block">
		<option value="true">true</option>
		<option value="false">false</option>
	</select>
	<br><br>
	
	<label for="feedback_closeLink">closeLink:</label>
	<select id="feedback_closeLink">
		<option value="true">true</option>
		<option value="false">false</option>
	</select>
	<br><br>
</div>
				
<input id="boton_create" type="button" value="_Create" />
<input id="boton_destroy" type="button" value="Destroy" disabled="disabled" />
<input id="boton_set" type="button" value="Set" />
<input id="boton_setOptions" type="button" value="Set_options" />
<input id="boton_setType" type="button" value="Set_type" />
<input id="boton_setImgClass" type="button" value="Set_imgClass" />
<input id="boton_hide" type="button"  value="Hide" />
<input id="boton_close" type="button" value="Close" />
<input id="boton_show" type="button" value="Show" />
