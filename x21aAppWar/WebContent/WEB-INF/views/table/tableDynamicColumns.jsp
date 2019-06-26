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

<h2>Tabla con columnas din�micas</h2> <!-- Titulo pagina -->

<div id="columsSelectorContainer" class="form-groupMaterial">
	<input type="text" name="columsSelector" id="columsSelector" />
	<label for="columsSelector">Seleccione columnas</label>
</div>

<button id="btnTableLoad" class="btn-material btn-material-primary-high-emphasis" type="button">
	<span>Cargar Tabla</span>
</button>

<table id="columnasDinamicas" class="tableFit table-striped table-bordered table-material d-none" 
	data-url-base="./dynamicColumns"
	data-filter-form="#columnasDinamicas_filter_form">
        <thead>
            <tr>
                <th data-col-prop="id" data-col-edit="false">
                	Id
                </th>
                <th data-col-prop="nombre" data-col-edit="true">
                	Nombre
                </th>
                <th data-col-prop="apellido1">
                	Primer apellido
                </th>
                <th data-col-prop="ejie" data-col-type="Checkbox">
                	Ejie
                </th>
                <th data-col-prop="fechaAlta" data-col-sidx="fecha_alta" data-col-type="Datepicker">
                	Fecha alta
                </th>
                <th data-col-prop="fechaBaja" data-col-sidx="fecha_baja" data-col-type="Datepicker">
                	Fecha baja
                </th>
                <th data-col-prop="rol" data-col-type="combo">
                	Rol
                </th>
            </tr>
        </thead>
</table>


