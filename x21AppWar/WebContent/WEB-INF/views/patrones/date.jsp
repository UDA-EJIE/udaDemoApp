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
<h2>Date</h2>

<div>
	<!-- Fecha -->
	<label for="fecha">Fecha </label><label for="fecha" id="fecha-mask"></label>:
	<input id="fecha" type="text" />
	&nbsp;&nbsp;<input id="fecha_button" type="button" value="getDate()" />
	<br/><br/>
	
	<!-- Fecha múltiple-->
	<label for="fecha">Fecha multiple </label><label for="fecha_multi" id="fecha_multi-mask"/></label>:
	<input id="fecha_multi" type="text" />
	&nbsp;&nbsp;<input id="fecha_multi_button" type="button" value="getDates()" />
	<br/><br/>
	
	<!-- Intervalo Fecha-->
	<label for="fecha">Intervalo desde </label><label for="desde" id="intervalo-mask"/></label>:
	<input type="text" id="desde" />
	<label for="fecha">hasta: </label>
	<input type="text" id="hasta"/>
	<br/><br/>
	
	<!-- Intervalo Fecha y hora -->
	<label for="fecha">Intervalo desde </label><label for="desde" id="intervalo-mask-date-time"/></label>:
	<input type="text" id="desdeDateTime" />
	<label for="fecha">hasta: </label>
	<input type="text" id="hastaDateTime"/>
	<br/><br/>
		
	<!-- Fecha desplegada --> 
	<div id="fecha_inline" style="float:left;"></div>
	<div style="float:right; margin-right: 33em;"><input id="fecha_inline_button" type="button" value="getDate()" /></div>
</div>	
