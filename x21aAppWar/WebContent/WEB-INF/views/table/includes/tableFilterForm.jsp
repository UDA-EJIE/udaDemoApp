<form id="table_filter_form">
  <div id="table_filter_toolbar" class="formulario_legend"></div>
  <fieldset id="table_filter_fieldset" class="rup-table-filter-fieldset">
    
    <div class="form-row">    
		<div class="form-groupMaterial col-sm">
	    	<input type="text" name="id" id="id_filter_table" />
			<label for="id_filter_table">ID</label>
	    </div>
	    
	    <div class="form-groupMaterial col-sm">
	    	<input type="text" name="nombre" id="nombre_filter_table" />
	    	<label for="nombre_filter_table">Nombre</label>
	    </div>
	    
	    <div class="form-groupMaterial col-sm">
	    	<input type="text" name="apellido1" id="apellido1_filter_table" />
	    	<label for="apellido1_filter_table">Primer apellido</label>
	    </div>  
	    
	    <div class="form-groupMaterial col-sm">
	    	<input type="text" name="apellido2" id="apellido2_filter_table" />
	    	<label for="apellido2_filter_table">Segundo apellido</label>
	    </div>
	</div>
    
    <div class="form-row">
    	<div class="form-groupMaterial col-sm">
	    	<input type="text" name="fechaAlta" id="fechaAlta_filter_table" />
	    	<label for="fechaAlta_filter_table">Fecha de alta</label>
	    </div>
	    
	    <div class="form-groupMaterial col-sm">
	    	<input type="text" name="fechaBaja" id="fechaBaja_filter_table" />
	    	<label for="fechaBaja_filter_table">Fecha de baja</label>
	    </div>
	    
	    <div class="form-groupMaterial col-sm">  
	    	<input type="text" name="ejie" id="ejie_filter_table" />
	    	<label for="ejie_filter_table">EJIE</label>
	    </div>
	    
	    <div class="form-groupMaterial col-sm">  
	    	<input type="text" name="rol" id="rol_filter_table" />
	    	<label for="rol_filter_table">ROL</label>
	    </div>
	</div>
     
    <!-- Botonera del formulario de filtrado -->
    <div id="table_filter_buttonSet" class="text-right">
    	<!-- Botón de limpiar -->
        <button id="table_filter_cleanButton" type="button" class="btn-material btn-material-primary-low-emphasis mr-2">
        	<i class="mdi mdi-eraser"></i>
        	<span>Limpiar</span>
        </button>
        <!-- Botón de filtrado -->
        <button id="table_filter_filterButton" type="button" class="btn-material btn-material-primary-high-emphasis">
        	<i class="mdi mdi-filter"></i>
        	<span>Filtrar</span>        	
        </button>
    </div>
        
  </fieldset>
</form>
