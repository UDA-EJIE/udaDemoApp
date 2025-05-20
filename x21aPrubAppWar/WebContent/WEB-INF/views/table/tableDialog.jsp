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
<h2 class="title mb-3">Tabla en diálogo</h2> <!-- Titulo pagina -->

<button id="btnTablaDialog_div" class="btn-material btn-material-primary-high-emphasis" type="button">
	<span>Tabla en formulario (DIV)</span>
</button>
<button id="btnTablaDialog_ajax" class="btn-material btn-material-primary-high-emphasis" type="button">
	<span>Tabla en formulario (AJAX)</span>
</button>

<div id="tableDialog_layer_ajax"></div>
<div id="tableDialog_layer_div" style="display:none">
	<div id="tableDialog_div" class="rup-table-container">		
		<jsp:include page="includes/tableMultiPkFilterForm.jsp"></jsp:include>
	
		<table id="MultiPk" class="tableFit table-striped table-bordered table-material" 
			data-url-base="./multipk"
			data-filter-form="#MultiPk_filter_form">
	        <thead>
	            <tr>
					<th data-col-prop="nombre" data-col-sidx="NOMBRE">Nombre</th>
					<th data-col-prop="apellido1" data-col-sidx="APELLIDO1">Primer apellido</th>
					<th data-col-prop="apellido2" data-col-sidx="APELLIDO2">Segundo apellido</th>
				</tr>
	        </thead>
		</table>
	</div>	
	
	<jsp:include page="includes/tableMultiPkEdit.jsp"></jsp:include>
</div>
