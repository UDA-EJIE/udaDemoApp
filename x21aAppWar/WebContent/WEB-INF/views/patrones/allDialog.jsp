<%--  
 -- Copyright 2011 E.J.I.E., S.A.
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

<jsp:include page="../table/includes/tableFilterForm.jsp"></jsp:include>

<table id="example" class="tableFit table-striped table-bordered table-material"
	data-url-base="../table"
	data-filter-form="#example_filter_form">
        <thead>
            <tr>
                <th data-col-prop="nombre" data-col-edit="true">Nombre</th>
                <th data-col-prop="apellido1">Primer apellido</th>
                <th data-col-prop="apellido2">Segundo apellido</th>
                <th data-col-prop="ejie" data-col-type="Checkbox">Ejie</th>
                <th data-col-prop="fechaAlta" data-col-sidx="fecha_alta" data-col-type="Datepicker">Fecha alta</th>
                <th data-col-prop="fechaBaja" data-col-sidx="fecha_baja" data-col-type="Datepicker">Fecha baja</th>
                <th data-col-prop="rol" data-col-type="combo">Rol</th>
            </tr>
        </thead>
</table>

<jsp:include page="../table/includes/tableEdit.jsp"></jsp:include>

<fieldset class="container-fluid mt-5">
	<!-- Feedback -->
	<div id="feedback_dialog"></div>
	
	<div class="row">
		<div class="col-md-4">
			<!-- Fecha -->
	    	<div class="d-flex justify-content-center">
				<div id="date_dialog"></div>
	    	</div>
	    	<!-- Hora -->
			<div class="d-flex justify-content-center mt-5">
				<div id="time_dialog"></div>
			</div>
	    </div>
	    
	    <div class="col-md-8">
			<!-- Combo -->
			<div class="form-groupMaterial row">
		    	<div class="col-6">
		    		<select id="comboProvincia_dialog" name="provincia"></select>
		    	</div>
				<div class="col-6">
					<select id="comboComarca_dialog"></select>
				</div>
		    </div>
			
			<!-- Multicombo -->
			<div class="form-groupMaterial col-sm mt-5">
		    	<select id="multicomboGruposRemoto_dialog"></select>
		    </div>
			
			<!-- Autocomplete / Tooltip -->
			<div class="form-groupMaterial col-sm mt-5">
		    	<input id="autocomplete_dialog" name="autocomplete" />
				<label for="autocomplete">Autocomplete (lenguaje)</label>
		    </div>
			<div class="form-groupMaterial col-sm mt-5">
		    	<input id="tooltip_dialog" name="tooltip" title="Introduzca su nombre."/>
				<label for="tooltip">Tooltip</label>
		    </div>
		    
		    <!-- Pestañas -->
			<div class="form-groupMaterial col-sm mt-5">
		    	<div id="tabs_dialog" class="col-sm"></div>
		    </div>
	    </div>
	</div>
</fieldset>