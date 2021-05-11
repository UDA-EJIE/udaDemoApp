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
<!-- T�tulo p�gina -->
<h2>
	<spring:message code="integracion.cache" />
</h2>

<h3>Weblogic Name : <%=System.getProperty("weblogic.Name")%></h3>

<!-- Formulario necesario para garantizar el correcto funcionamiento con Hdiv cuando filter = 'noFilter' -->
<spring:url value="/integracion/cache" var="url"/>
<form:form modelAttribute="usuario" id="table_filter_form" class="d-none" action="${url}"/>

<table id="table" class="tableFit table-striped table-bordered table-material" 
	data-url-base="."
	data-filter-form="#table_filter_form">
        <thead>
			<tr>
				<th data-col-prop="nombre" data-col-edit="true">Nombre</th>
				<th data-col-prop="apellido1">Primer apellido</th>
				<th data-col-prop="apellido2">Segundo apellido</th>
				<th data-col-prop="ejie" data-col-type="Checkbox">Ejie</th>
				<th data-col-prop="fechaAlta" data-col-sidx="fecha_alta" data-col-type="Datepicker">Fecha alta</th>
				<th data-col-prop="fechaBaja" data-col-sidx="fecha_baja" data-col-type="Datepicker">Fecha baja</th>
				<th data-col-prop="rol" data-col-type="combo">Rol</th>
			</tr>
        </thead>
</table>
