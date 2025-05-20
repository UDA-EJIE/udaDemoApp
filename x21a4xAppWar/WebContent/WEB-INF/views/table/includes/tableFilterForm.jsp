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
<form:form modelAttribute="usuario" id="example_filter_form">
  <div id="example_filter_toolbar" class="formulario_legend"></div>
  <fieldset id="example_filter_fieldset" class="rup-table-filter-fieldset">
    
    <div class="form-row">    
		<div class="form-groupMaterial col-sm">
	    	<form:input path="id" id="id_filter_table" />
			<label for="id_filter_table">ID</label>
	    </div>
	    
	    <div class="form-groupMaterial col-sm">
	    	<form:input path="nombre" id="nombre_filter_table" />
	    	<label for="nombre_filter_table">Nombre</label>
	    </div>
	    
	    <div class="form-groupMaterial col-sm">
	    	<form:input path="apellido1" id="apellido1_filter_table" />
	    	<label for="apellido1_filter_table">Primer apellido</label>
	    </div>  
	    
	    <div class="form-groupMaterial col-sm">
	    	<form:input path="apellido2" id="apellido2_filter_table" />
	    	<label for="apellido2_filter_table">Segundo apellido</label>
	    </div>
	</div>
    
    <div class="form-row">
    	<div class="form-groupMaterial col-sm">
	    	<form:input path="fechaAlta" id="fechaAlta_filter_table" />
	    	<label for="fechaAlta_filter_table">Fecha de alta</label>
	    </div>
	    
	    <div class="form-groupMaterial col-sm">
	    	<form:input path="fechaBaja" id="fechaBaja_filter_table" />
	    	<label for="fechaBaja_filter_table">Fecha de baja</label>
	    </div>
	    
	    <div class="form-groupMaterial col-sm">  
	    	<form:input path="ejie" id="ejie_filter_table" />
	    	<label for="ejie_filter_table">EJIE</label>
	    </div>
	    
	    <div class="form-groupMaterial col-sm">  
	    	<form:input path="rol" id="rol_filter_table" />
	    	<label for="rol_filter_table">ROL</label>
	    </div>
	</div>
     
    <!-- Botonera del formulario de filtrado -->
    <div id="example_filter_buttonSet" class="text-right">
    	<!-- Bot�n de limpiar -->
        <button id="example_filter_cleanButton" type="button" class="btn-material btn-material-primary-low-emphasis mr-2">
        	<i class="mdi mdi-eraser"></i>
        	<span>Limpiar</span>
        </button>
        <!-- Bot�n de filtrado -->
        <button id="example_filter_filterButton" type="button" class="btn-material btn-material-primary-high-emphasis">
        	<i class="mdi mdi-filter"></i>
        	<span>Filtrar</span>        	
        </button>
    </div>
        
  </fieldset>
</form:form>
