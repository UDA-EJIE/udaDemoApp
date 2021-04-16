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

<spring:url value="../tableLocalidad/filter" var="url"/>
<form:form modelAttribute="localidad" id="localidad_filter_form" action="${url}">
	<div id="localidad_filter_toolbar" class="formulario_legend"></div>
	<fieldset id="localidad_filter_fieldset" class="rup-table-filter-fieldset">
	    <legend></legend>
		
		<div class="form-row">
			<div class="form-groupMaterial col-sm d-none">
				<!-- Hidden de la PK de la tabla padre -->
        		<form:input id="localidad_filter_masterPK" path="comarca.code" />
	    		<label for="localidad_filter_masterPK">code</label>
			</div>
			<div class="form-groupMaterial col-sm">
				<form:input path="descEs" id="descEs_filter_localidad" />
				<label for="descEs_filter_localidad">descEs</label>
			</div>
			<div class="form-groupMaterial col-sm">
				<form:input path="descEu" id="descEu_filter_localidad" />
				<label for="descEu_filter_localidad">descEu</label>
			</div>
		</div>
		<div class="form-row">
			<div class="form-groupMaterial col-sm">
				<form:input path="css" id="css_filter_localidad" />
				<label for="css_filter_localidad">css</label>
			</div>
		</div>
		
		<!-- Botonera del formulario de filtrado -->
		<div id="localidad_filter_buttonSet" class="text-right">
			<!-- Bot�n de limpiar -->
        	<button id="localidad_filter_cleanButton" type="button" class="btn-material btn-material-primary-low-emphasis mr-2">
            	<i class="mdi mdi-eraser"></i>
        		<span>
           			<spring:message code="clear" />
       			</span>
        	</button>
        	<!-- Bot�n de filtrado -->
           	<button id="localidad_filter_filterButton" type="button" class="btn-material btn-material-primary-high-emphasis">
            	<i class="mdi mdi-filter"></i>
               	<span>
                  	<spring:message code="filter" />
               	</span>
        	</button>
    	</div>
	</fieldset>
</form:form>