<%--  
 -- Copyright 2019 E.J.I.E., S.A.
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

<h2 class="title mb-3">Tabla con columnas dinámicas</h2> <!-- Titulo pagina -->

<div id="columsSelectorContainer" class="form-groupMaterial">
	<input type="text" name="columsSelector" id="columsSelector" />
	<label for="columsSelector">Seleccione columnas</label>
</div>

<button id="btnTableLoad" class="btn-material btn-material-primary-high-emphasis" type="button">
	<span>Cargar Tabla</span>
</button>

<spring:url value="/table/dynamicColumns/filter" var="url"/>
<form:form modelAttribute="usuario" id="columnasDinamicas_filter_form" class="d-none" action="${url}" method="${actionType}" enctype="${enctype}"/>

<table id="columnasDinamicas" class="tableFit table table-striped table-bordered table-material align-middle d-none" 
	data-url-base="./dynamicColumns"
	data-filter-form="#columnasDinamicas_filter_form">
        <thead>
			<tr>
				<th data-col-prop="nombre" data-col-edit="true">Nombre</th>
				<th data-col-prop="apellido1">Primer apellido</th>
				<th data-col-prop="apellido2">Segundo apellido</th>
				<th data-col-prop="ejie" data-col-type="Checkbox">Ejie</th>
				<th data-col-prop="fechaAlta" data-col-sidx="fecha_alta" data-col-type="Datepicker">Fecha alta</th>
				<th data-col-prop="fechaBaja" data-col-sidx="fecha_baja" data-col-type="Datepicker">Fecha baja</th>
				<th data-col-prop="rol" data-col-type="select">Rol</th>
			</tr>
        </thead>
</table>
