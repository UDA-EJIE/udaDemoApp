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
<h2>Datatable</h2> <!-- Titulo pagina -->

<jsp:include page="includes/filterForm.jsp"></jsp:include>

<table id="example" class="table table-striped table-bordered" 
	data-url-base="./jqGridUsuario"
	data-filter-form="#table_filter_form" 
	cellspacing="0" width="100%">
        <thead>
            <tr>
                <th data-col-prop="id">Id</th>
                <th data-col-prop="nombre">Nombre</th>
                <th data-col-prop="apellido1">Primer apellido</th>
                <th data-col-prop="ejie">Ejie</th>
                <th data-col-prop="fechaAlta" data-col-sidx="fecha_alta">Fecha alta</th>
                <th data-col-prop="fechaBaja">Fecha baja</th>
                <th data-col-prop="rol">Rol</th>
            </tr>
        </thead>
        <tfoot>
          <tr>
              <th>Id</th>
              <th>Nombre</th>
              <th>Primer apellido</th>
              <th>Ejie</th>
              <th>Fecha alta</th>
              <th>Fecha baja</th>
              <th>Rol</th>
          </tr>
        </tfoot>
</table>
