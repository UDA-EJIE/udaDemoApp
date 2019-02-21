<form id="example_filter_form">
  <div id="example_filter_toolbar" class="formulario_legend"></div>
  <fieldset id="example_filter_fieldset" class="rup-table-filter-fieldset">
    
    <div class="form-row col-12">    
		<div class="form-groupMaterial col-sm">
	    	<input type="text" name="id" class="formulario_linea_input form-control" id="id_filter_table" />
			<label for="id_filter_table" class="formulario_linea_label">ID</label>
	    </div>
	    
	    <div class="form-groupMaterial col-sm">
	    	<input type="text" name="nombre" class="formulario_linea_input form-control" id="nombre_filter_table" />
	    	<label for="nombre_filter_table" class="formulario_linea_label">Nombre</label>
	    </div>
	    
	    <div class="form-groupMaterial col-sm">
	    	<input type="text" name="apellido1" class="formulario_linea_input form-control" id="apellido1_filter_table" />
	    	<label for="apellido1_filter_table" class="formulario_linea_label">Primer apellido</label>
	    </div>  
	    
	    <div class="form-groupMaterial col-sm">
	    	<input type="text" name="apellido2" class="formulario_linea_input form-control" id="apellido2_filter_table" />
	    	<label for="apellido2_filter_table" class="formulario_linea_label">Segundo apellido</label>
	    </div>
	</div>
    
    <div class="form-row col-12">
    	<div class="form-groupMaterial fix-align col-sm">
	    	<input type="text" name="fechaAlta" class="formulario_linea_input form-control" id="fechaAlta_filter_table" />
	    	<label for="fechaAlta_filter_table" class="formulario_linea_label">Fecha de alta</label>
	    </div>
	    
	    <div class="form-groupMaterial fix-align col-sm">
	    	<input type="text" name="fechaBaja" class="formulario_linea_input form-control" id="fechaBaja_filter_table" />
	    	<label for="fechaBaja_filter_table" class="formulario_linea_label">Fecha de baja</label>
	    </div>
	    
	    <div class="form-groupMaterial fix-align col-sm">  
	    	<input type="text" name="ejie" class="formulario_linea_input form-control" id="ejie_filter_table" />
	    	<label for="ejie_filter_table" class="formulario_linea_label">EJIE</label>
	    </div>
	    
	    <div class="form-groupMaterial fix-align col-sm">  
	    	<input type="text" name="rol" class="formulario_linea_input form-control" id="rol_filter_table" />
	    	<label for="rol_filter_table" class="formulario_linea_label">ROL</label>
	    </div>
	</div>
     
    <!-- Botonera del formulario de filtrado -->
    <div id="example_filter_buttonSet" class="right_buttons">
    	<!-- Botón de limpiar -->
        <button id="example_filter_cleanButton" type="button" class="btn-material btn-material-primary-low-emphasis">
        	<i class="material-icons">&#xe14c;</i>
        	<span>Limpiar</span>
        </button>
        <!-- Botón de filtrado -->
        <button id="example_filter_filterButton" type="button" class="btn-material btn-material-primary-high-emphasis">
        	<i class="material-icons">&#xe152;</i>
        	<span>Filtrar</span>        	
        </button>
    </div>
        
  </fieldset>
</form>
