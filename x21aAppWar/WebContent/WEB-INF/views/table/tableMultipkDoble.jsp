<%@include file="/WEB-INF/includeTemplate.inc"%>
<h2>Tabla con multiples PKs</h2>

<jsp:include page="includes/tableMultiPkFilterForm.jsp"></jsp:include>

<table id="MultiPk" class="tableFit table-striped table-bordered table-material" 
	data-url-base="../multipk"
	data-filter-form="#MultiPk_filter_form">
    <thead>
		<tr>
			<th data-col-prop="ida" data-col-sidx="IDA" >ida</th>
			<th data-col-prop="idb" data-col-sidx="IDB" >idb</th>
			<th data-col-prop="nombre" data-col-sidx="NOMBRE" >nombre</th>
			<th data-col-prop="apellido1" data-col-sidx="APELLIDO1" >apellido1</th>
			<th data-col-prop="apellido2" data-col-sidx="APELLIDO2" >apellido2</th>
		</tr>
	</thead>
</table>

<jsp:include page="includes/tableMultiPkEdit.jsp"></jsp:include>
</br>

<h2>Tabla Simple</h2> <!-- Titulo pagina -->

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
        	<span>
        		<spring:message code="clear" />
        	</span>  
        </button>
        <!-- Bot�n de filtrado -->
        <button id="example_filter_filterButton" type="button" class="btn-material btn-material-primary-high-emphasis">
        	<i class="mdi mdi-filter"></i>
        	<span>
        		<spring:message code="filter" />
        	</span>        	
        </button>
    </div>
        
  </fieldset>
</form:form>


<table id="example" class="tableFit table-striped table-bordered table-material"
	data-url-base=".."
	data-filter-form="#example_filter_form">
        <thead>
            <tr>
                <th data-col-prop="id" data-col-edit="false">Id</th>
                <th data-col-prop="nombre" data-col-edit="true">Nombre</th>
                <th data-col-prop="apellido1">Primer apellido</th>
                <th data-col-prop="ejie" data-col-type="Checkbox">Ejie</th>
                <th data-col-prop="fechaAlta" data-col-sidx="fecha_alta" data-col-type="Datepicker">Fecha alta</th>
                <th data-col-prop="fechaBaja" data-col-sidx="fecha_baja" data-col-type="Datepicker">Fecha baja </th>
                <th data-col-prop="rol" data-col-type="combo">Rol</th>
            </tr>
        </thead>
</table>

<!-- Formulario de detalle -->
<div id="example_detail_div" class="rup-table-formEdit-detail d-none">
	<!-- Barra de navegaci�n del detalle -->
	<div id ="example_detail_navigation" class="row no-gutters"></div>
	<!-- Separador -->
	<hr class="m-1">
	<div class="dialog-content-material">
		<!-- Formulario -->
		<form:form modelAttribute="usuario" id="example_detail_form">
			<!-- Feedback del formulario de detalle -->
			<div id ="example_detail_feedback"></div>	
			<div class="form-row">
				<!-- Campos del formulario de detalle -->
				<div class="form-groupMaterial col-sm">
			    	<form:input path="id" id="id_detailForm_table" />
					<label for="id_detailForm_table"><spring:message code="id" /></label>
			    </div>
			    
			    <div class="form-groupMaterial col-sm">
			    	<form:input path="nombre" id="nombre_detail_table" />
			    	<label for="nombre_detail_table"><spring:message code="nombre" /></label>
			    </div>
			</div>
			<div class="form-row">       
			    <div class="form-groupMaterial col-sm">
			    	<form:input path="apellido1" id="apellido1_detail_table" />
			    	<label for="apellido1_detail_table"><spring:message code="apellido1" /></label>
			    </div>  
			    
			    <div class="form-groupMaterial col-sm">
			    	<form:input path="apellido2" id="apellido2_detail_table" />
			    	<label for="apellido2_detail_table"><spring:message code="apellido2" /></label>
			    </div>
			</div>
			<div class="form-row">       
			    <div class="form-groupMaterial col-sm">
			    	<form:input path="fechaBaja" id="fechaBaja_detail_table" />
			    	<label for="fechaBaja_detail_table"><spring:message code="fechaBaja" /></label>
			    </div>
			    
			    <div class="form-groupMaterial col-sm">
			    	<form:input path="fechaAlta" id="fechaAlta_detail_table" />
			    	<label for="fechaAlta_detail_table"><spring:message code="fechaAlta" /></label>
			    </div>
			</div>
			<div class="form-row">
			    <div class="col-sm checkbox-material">
			    	<form:checkbox path="ejie" id="ejie_detail_table" value="1" />
			    	<label for="ejie_detail_table"><spring:message code="ejie" /></label>
			    </div> 
			    
			    <div class="form-groupMaterial col-sm">
			    	<form:input path="rol" id="rol_detail_table" />
			    	<label for="rol_detail_table"><spring:message code="rol" /></label>
			    </div>
			</div>	
			<div class="form-row d-none">	
				<div class="form-groupMaterial col-sm" id="divImagenAlumno">
					<input type="file" name="imagenAlumno" id="imagenAlumno" disabled/>
					<label for="imagenAlumno"><spring:message code="subidaImg" /></label>
				</div>	
			</div>	
		</form:form>
	</div>
	<!-- Botonera del formulario de detalle -->
	<div class="rup-table-buttonpanel-material">
		<div class="text-right">
			<!-- Bot�n cancelar -->
			<button id="example_detail_button_cancel" type="button">
				<spring:message code="cancel" />
			</button>
			<!-- Bot�n guardar -->
			<button id="example_detail_button_save" type="button">
				<spring:message code="save" />
			</button>
			<!-- Bot�n guardar y continuar -->
			<button id="example_detail_button_save_repeat" type="button">
				<spring:message code="saveAndContinue" />
			</button>
		</div>
	</div>
</div>


