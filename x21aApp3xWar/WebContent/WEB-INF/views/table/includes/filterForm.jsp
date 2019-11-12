<%@include file="/WEB-INF/includeTemplate.inc"%>

<form:form modelAttribute="usuario" id="table_filter_form">
  <div id="table_filter_toolbar" class="formulario_legend"></div>
  <fieldset id="table_filter_fieldset" class="rup-table-filter-fieldset">    
    <div class="form-row">    
		<div class="form-group col-sm">
			<label for="id_filter_table" class="formulario_linea_label">
				<spring:message code="id" />
			</label>
	    	<form:input path="id" class="formulario_linea_input form-control" id="id_filter_table" />
	    </div>
	    
	    <div class="form-group col-sm">
	    	<label for="nombre_filter_table" class="formulario_linea_label">
	    		<spring:message code="nombre" />
	    	</label>
	    	<form:input path="nombre" class="formulario_linea_input form-control" id="nombre_filter_table" />
	    </div>
	    
	    <div class="form-group col-sm">
	    	<label for="apellido1_filter_table" class="formulario_linea_label">
	    		<spring:message code="apellido1" />
	    	</label>
	    	<form:input path="apellido1" class="formulario_linea_input form-control" id="apellido1_filter_table" />
	    </div>  
	    
	    <div class="form-group col-sm">
	    	<label for="apellido2_filter_table" class="formulario_linea_label">
	    		<spring:message code="apellido2" />
	    	</label>
	    	<form:input path="apellido2" class="formulario_linea_input form-control" id="apellido2_filter_table" />
	    </div>
	</div>	
	<div class="form-row">
    	<div class="form-group fix-align col-sm">
	    	<label for="fechaAlta_filter_table" class="formulario_linea_label">
	    		<spring:message code="fechaAlta" />
	    	</label>
	    	<form:input path="fechaAlta" class="formulario_linea_input form-control" id="fechaAlta_filter_table" />
	    </div>
	    
	    <div class="form-group fix-align col-sm">
	    	<label for="fechaBaja_filter_table" class="formulario_linea_label">
	    		<spring:message code="fechaBaja" />
	    	</label>
	    	<form:input path="fechaBaja" class="formulario_linea_input form-control" id="fechaBaja_filter_table" />
	    </div>
	    
	    <div class="form-group fix-align col-sm">  
	    	<label for="ejie_filter_table" class="formulario_linea_label">
	    		<spring:message code="ejie" />
	    	</label>
	    	<form:input path="ejie" class="formulario_linea_input form-control" id="ejie_filter_table" />
	    </div>
	    
	    <div class="form-group fix-align col-sm">  
	    	<label for="rol_filter_table" class="formulario_linea_label">
	    		<spring:message code="rol" />
	    	</label>
	    	<form:input path="rol" class="formulario_linea_input form-control" id="rol_filter_table" />
	    </div>
	</div>
    <div id="table_filter_buttonSet" class="right_buttons">

        <button id="table_filter_cleanButton" type="button" class="btn btn-primary rup-limpiar">
        	<i class="fa fa-eraser"></i>
        	<span>
        		<spring:message code="clear" />
        	</span>
        </button>
        <button id="table_filter_filterButton" type="button" class="btn rup-filtrar btn-primary">
        	<i class="fa fa-filter"></i>
        	<span>
        		<spring:message code="filter" />
        	</span>
        </button>
    </div>    
  </fieldset>
</form:form>
