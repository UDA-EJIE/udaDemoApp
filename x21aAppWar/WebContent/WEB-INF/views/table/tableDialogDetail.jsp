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
<h2>Tabla con selección desde otra tabla</h2> <!-- Titulo pagina -->
<h3>Comarca</h3>
	
<jsp:include page="includes/tableComarcaFilterForm.jsp"></jsp:include>

<table id="comarca" class="tableFit table-striped table-bordered table-material" 
	data-url-base="../tableComarca"
	data-filter-form="#comarca_filter_form">
    <thead>
        <tr>
            <th data-col-prop="descEs" data-col-sidx="DESCES">descEs</th>
            <th data-col-prop="descEu" data-col-sidx="DESCEU">descEu</th>
            <th data-col-prop="css">css</th>
            <th data-col-prop="provincia.descEs" data-col-sidx="ProvinciaDESCES">DescEs provincia</th>
        </tr>
    </thead>
</table>
<jsp:include page="includes/tableDialogComarcaEdit.jsp"></jsp:include>
