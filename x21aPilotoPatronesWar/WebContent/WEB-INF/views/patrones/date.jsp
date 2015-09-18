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
<h1>Date</h1>

<div>
	<!-- Fecha -->
	Fecha <label for="fecha" id="fecha-mask"></label>:
	<input id="fecha" type="text" />
	&nbsp;&nbsp;<input id="fecha_button" type="button" value="getDate()" />
	<br/><br/>
	
	<!-- Fecha múltiple-->
	Fecha multiple<label for="fecha_multi" id="fecha_multi-mask"/></label>:
	<input id="fecha_multi" type="text" />
	&nbsp;&nbsp;<input id="fecha_multi_button" type="button" value="getDates()" />
	<br/><br/>
	
	<!-- Intervalo -->
	Intervalo desde <label for="desde" id="intervalo-mask"/></label>:
	<input type="text" id="desde" />
	hasta: 
	<input type="text" id="hasta"/>
	<br/><br/>
		
	<!-- Fecha desplegada --> 
	<div id="fecha_inline" style="float:left;"></div>
	<div style="float:right; margin-right: 33em;"><input id="fecha_inline_button" type="button" value="getDate()" /></div>
</div>	
