<%--  
 -- Copyright 2021 E.J.I.E., S.A.
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
	<spring:message code="experimental.logLevel" />
</h2>

<h3>Weblogic Name : <%=System.getProperty("weblogic.Name")%></h3>

<spring:url value="/experimental/filter" var="url"/>
<form:form modelAttribute="randomForm" id="table_filter_form" action="${url}" method="POST">
	<div id="table_filter_toolbar" class="formulario_legend"></div>
	<fieldset id="table_filter_fieldset" class="rup-table-filter-fieldset">
		<div class="form-row">
			<div class="form-groupMaterial col-sm">
				<form:input path="nameLog" id="nameLog_filter_table" />
				<label for="nameLog_filter_table">Nombre</label>
			</div>
			<div class="form-groupMaterial col-sm">
				<form:select path="levelLog" id="levelLog_filter_table" items="${comboLevel}" />
				<label for="levelLog_filter_table">Nivel</label>
			</div>
		</div>
		
		<!-- Botonera del formulario de filtrado -->
		<div id="table_filter_buttonSet" class="text-right">
			<!-- Bot�n de limpiar -->
        	<button id="table_filter_cleanButton" type="button" class="btn-material btn-material-primary-low-emphasis mr-2">
            	<i class="mdi mdi-eraser"></i>
            	<span>
                   	<spring:message code="clear" />
                </span>
        	</button>
        	<!-- Bot�n de filtrado -->
           	<button id="table_filter_filterButton" type="button" class="btn-material btn-material-primary-high-emphasis">
            	<i class="mdi mdi-filter"></i>
               	<span>
                	<spring:message code="filter" />
            	</span>
        	</button>
		</div>
	</fieldset>
</form:form>

<table id="table" class="tableFit table-striped table-bordered table-material" 
	data-url-base="."
	data-filter-form="#table_filter_form">
        <thead>
			<tr>
				<th data-col-prop="nid" data-col-edit="false">Nombre</th>
				<th data-col-prop="levelLog" data-col-edit="true">Nivel</th>
			</tr>
        </thead>
</table>
