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

<spring:url value="../tableComarca/filter" var="url"/>
<form:form modelAttribute="comarca" id="comarca_filter_form" action="${url}" method="POST">
	<div  id="comarca_filter_toolbar" class="formulario_legend"></div>
	<fieldset id="comarca_filter_fieldset" class="rup-table-filter-fieldset">
		<div class="form-row">
			<div class="form-groupMaterial col-sm">
				<form:input path="descEs" id="descEs_filter_comarca" />
				<label for="descEs_filter_comarca">descEs</label>
			</div>
			<div class="form-groupMaterial col-sm">
				<form:input path="descEu" id="descEu_filter_comarca" />
				<label for="descEu_filter_comarca">descEu</label>
			</div>
		</div>
		<div class="form-row">
			<div class="form-groupMaterial col-sm">
				<form:input path="css" id="css_filter_comarca" />
				<label for="css_filter_comarca">css</label>
			</div>
			<div class="form-groupMaterial col-sm">
				<form:input path="provincia.descEs" id="provinciaDescEs_filter_comarca" />
				<label for="provinciaDescEs_filter_comarca">provinciaDescEs</label>
			</div>
		</div>
		
		<!-- Botonera del formulario de filtrado -->
		<div id="comarca_filter_buttonSet" class="text-right">
			<!-- Bot�n de limpiar -->
        	<button id="comarca_filter_cleanButton" type="button" class="btn-material btn-material-primary-low-emphasis mr-2">
            	<i class="mdi mdi-eraser"></i>
            	<span>
                   	<spring:message code="clear" />
                </span>
        	</button>
        	<!-- Bot�n de filtrado -->
           	<button id="comarca_filter_filterButton" type="button" class="btn-material btn-material-primary-high-emphasis">
            	<i class="mdi mdi-filter"></i>
               	<span>
                	<spring:message code="filter" />
            	</span>
        	</button>
		</div>
	</fieldset>
</form:form>