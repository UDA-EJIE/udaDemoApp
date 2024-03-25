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
<h2>Visor geoeuskadi</h2>
 
 
 <div id="mi_mapa" style="width: 100%; height: 100%; position:relative;max-height: 600px;"></div>
 
 <div class="example">
			<button type="button" id="marcador"> <spring:message code="integracion.geoeuskadi.marcador" /></button>
			<button type="button" id="centrar"> <spring:message code="integracion.geoeuskadi.centrar" /></button>
			
		</div>
 
