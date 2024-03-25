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
<h2>Cache</h2> <!-- Titulo pagina -->

<br/>
<span> Weblogic Name : <%=System.getProperty("weblogic.Name")%></span>


<br/><br/>
<button type="button" id="btnRecargarTabla">Recargar tabla</button>
<div id="table_div" class="rup-table-container">
	<div id="table_feedback"></div>

	<div id="table_grid_div">
		<!-- Tabla -->
		<table id="table"></table>
		<!-- Barra de paginaci�n -->
		<div id="table_pager"></div>
	</div>
</div>	
