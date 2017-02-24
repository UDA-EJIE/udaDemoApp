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
<h2>Wizard &lt;jsp:include&gt;</h2>

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
	<jsp:include page="wizard_step0.jsp" />	
	<jsp:include page="wizard_step1.jsp" />
	<jsp:include page="wizard_step2.jsp" />
	<jsp:include page="wizard_step3.jsp" />
	<br/>
	<input id="saveForm" type="button" value="Enviar" />
</form>
