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
<h2 class="title mb-3">Tabla Comarca Extendida/h2> <!-- Titulo pagina -->
<h3>Comarca</h3>

<jsp:include page="includes/tableComarcaExtendidaFilterForm.jsp"></jsp:include>

<table id="comarca" class="tableFit table table-striped table-bordered table-material align-middle" 
	data-url-base="../tableComarcaExtendida"
	data-filter-form="#comarca_filter_form">
    <thead>
        <tr>
            <th data-col-prop="descEs" data-col-sidx="DESCES">descEs</th>
            <th data-col-prop="descEu" data-col-sidx="DESCEU">descEu</th>
            <th data-col-prop="css">css</th>
        </tr>
    </thead>
</table>

<jsp:include page="includes/tableComarcaExtendidaEdit.jsp"></jsp:include>

