<%@include file="/WEB-INF/includeTemplate.inc"%>
<!-- <form id="example_filter_form"> -->
<form:form id="example_filter_form" modelAttribute="usuario" method="post">
  <div id="example_filter_toolbar" class="formulario_legend"></div>
  <fieldset id="example_filter_fieldset" class="rup-table-filter-fieldset">
    
    <div class="form-row">    
		<div class="form-group col-sm">
			<label for="id_filter_table" class="formulario_linea_label">ID</label>
	    	<form:input path="id" class="formulario_linea_input form-control" id="id_filter_table"/>
	    </div>
	    
	    <div class="form-group col-sm">
	    	<label for="nombre_filter_table" class="formulario_linea_label">Nombre</label>
	    	<form:input path="nombre" class="formulario_linea_input form-control" id="nombre_filter_table"/>
	    </div>
	    
	    <div class="form-group col-sm">
	    	<label for="apellido1_filter_table" class="formulario_linea_label">Primer apellido</label>
	    	<form:input path="apellido1" class="formulario_linea_input form-control" id="apellido1_filter_table"/>
	    </div>  
	    
	    <div class="form-group col-sm">
	    	<label for="apellido2_filter_table" class="formulario_linea_label">Segundo apellido</label>
	    	<form:input path="apellido2" class="formulario_linea_input form-control" id="apellido2_filter_table"/>
	    </div>
	</div>
    
    <div class="form-row">
    	<div class="form-group fix-align col-sm">
	    	<label for="fechaAlta_filter_table" class="formulario_linea_label">Fecha de alta</label>
	    	<form:input path="fechaAlta" class="formulario_linea_input form-control" id="fechaAlta_filter_table"/>
	    </div>
	    
	    <div class="form-group fix-align col-sm">
	    	<label for="fechaBaja_filter_table" class="formulario_linea_label">Fecha de baja</label>
	    	<form:input path="fechaBaja" class="formulario_linea_input form-control" id="fechaBaja_filter_table"/>
	    </div>
	    
	    <div class="form-group fix-align col-sm">  
	    	<label for="ejie_filter_table" class="formulario_linea_label">EJIE</label>
	    	<form:input path="ejie" class="formulario_linea_input form-control" id="ejie_filter_table"/>
	    </div>
	    
	    <div class="form-group fix-align col-sm">  
	    	<label for="rol_filter_table" class="formulario_linea_label">ROL</label>
	    	<form:input path="rol" class="formulario_linea_input form-control" id="rol_filter_table"/>
	    </div>
	</div>
     
    <!-- Botonera del formulario de filtrado -->
    <div id="example_filter_buttonSet" class="right_buttons">
    	<!-- Botón de limpiar -->
        <button id="example_filter_cleanButton" type="button" class="btn btn-primary rup-limpiar">
        	<i class="fa fa-eraser"></i>
        	<span>Limpiar</span>
        </button>
        <!-- Botón de filtrado -->
        <button id="example_filter_filterButton" type="button" class="btn rup-filtrar btn-primary">
        	<i class="fa fa-filter"></i>
        	<span>Filtrar</span>        	
        </button>
    </div>
        
  </fieldset>
</form:form>
