<%--  
 -- Copyright 2011 E.J.I.E., S.A.
 --
 -- Licencia con arreglo a la EUPL, Versi�n 1.1 exclusivamente (la �Licencia�);
 -- Solo podr� usarse esta obra si se respeta la Licencia.
 -- Puede obtenerse una copia de la Licencia en
 --
 --      http://ec.europa.eu/idabc/eupl.html
 --
 -- Salvo cuando lo exija la legislaci�n aplicable o se acuerde por escrito, 
 -- el programa distribuido con arreglo a la Licencia se distribuye �TAL CUAL�,
 -- SIN GARANT�AS NI CONDICIONES DE NING�N TIPO, ni expresas ni impl�citas.
 -- V�ase la Licencia en el idioma concreto que rige los permisos y limitaciones
 -- que establece la Licencia.
 --%>
<%@include file="/WEB-INF/includeTemplate.inc"%>
<h2 class="title mb-3">Todos los patrones</h2>

<jsp:include page="../table/includes/tableComarcaFilterForm.jsp"></jsp:include>

<table id="comarca" class="tableFit table-striped table-bordered table-material" 
	data-url-base="../tableComarca"
	data-filter-form="#comarca_filter_form">
    <thead>
        <tr>
            <th data-col-prop="descEs" data-col-sidx="DESCES">descEs</th>
            <th data-col-prop="descEu" data-col-sidx="DESCEU">descEu</th>
            <th data-col-prop="css">css</th>
            <th data-col-prop="provincia.descEs" data-col-sidx="ProvinciaDESCES">provincia.descEs</th>
        </tr>
    </thead>
</table>

<jsp:include page="../table/includes/tableComarcaEdit.jsp"></jsp:include>

<fieldset class="container-fluid mt-5">
	<!-- Feedback -->
	<div id="feedback"></div>
	
	<div class="row">
		<div class="col-md-4">
			<!-- Fecha -->
	    	<div class="d-flex justify-content-center">
				<div id="date"></div>
	    	</div>
	    	<!-- Hora -->
			<div class="d-flex justify-content-center mt-5">
				<div id="time"></div>
			</div>
	    </div>
	    
	    <div class="col-md-8">
			<!-- Combo -->
			<div class="form-groupMaterial row">
		    	<div class="col-6">
		    		<select id="comboProvincia" name="provincia"></select>
		    	</div>
				<div class="col-6">
					<select id="comboComarca"></select>
				</div>
		    </div>
			
			<!-- Multicombo -->
			<div class="form-groupMaterial col-sm mt-5">
		    	<select id="multicomboGruposRemoto"></select>
		    </div>
			
			<!-- Autocomplete / Tooltip -->
			<div class="form-groupMaterial col-sm mt-5">
		    	<input id="autocomplete" name="autocomplete" />
				<label for="autocomplete">Autocomplete (lenguaje)</label>
		    </div>
			<div class="form-groupMaterial col-sm mt-5">
		    	<input id="tooltip" name="tooltip" title="Introduzca su nombre" />
				<label for="tooltip">Tooltip</label>
		    </div>
		    
		    <!-- Pesta�as -->
			<div class="form-groupMaterial col-sm mt-5">
		    	<div id="tabs" class="col-sm"></div>
		    </div>
	    </div>
	</div>
    
    <div class="row mt-3">
    	<div class="col-sm-8">
    		<p>El siguiente bot�n muestra una pantalla similar a esta pero dentro de un di�logo:</p>
    	</div>
    	<div class="col-sm-4">
    		<button id="dialogLauncher" class="btn-material btn-material-primary-high-emphasis">
    			<i class="mdi mdi-forum"></i>
    			<span>Patrones en di�logo</span>
    		</button>
    	</div>
    </div>
</fieldset>

<div id="dialog" style="display: none"></div>