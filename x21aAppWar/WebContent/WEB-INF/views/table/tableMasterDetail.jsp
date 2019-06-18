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
<h2>Tabla Maestro Detalle</h2> <!-- Titulo pagina -->
<h2>Comarca</h2>
	<div id="comarca_filter_div"  class="rup-table-filter">
		<form id="comarca_filter_form">
			<div  id="comarca_filter_toolbar" class="formulario_legend"></div>
			<fieldset id="comarca_filter_fieldset" class="rup-table-filter-fieldset">
				<div class="form-row">
					<div class="form-groupMaterial col-sm">
						<input type="text" name="code" id="code_filter_comarca" />
						<label for="code_filter_comarca">code</label>
					</div>
					<div class="form-groupMaterial col-sm">
						<input type="text" name="provincia.code" id="provinciaCode_filter_comarca" />
						<label for="provinciaCode_filter_comarca">provinciaCode</label>
					</div>
					<div class="form-groupMaterial col-sm">
						<input type="text" name="descEs" id="descEs_filter_comarca" />
						<label for="descEs_filter_comarca">descEs</label>
					</div>
				</div>
				<div class="form-row">
					<div class="form-groupMaterial col-sm">
						<input type="text" name="descEu" id="descEu_filter_comarca" />
						<label for="descEu_filter_comarca">descEu</label>
					</div>
					<div class="form-groupMaterial col-sm">
						<input type="text" name="css" id="css_filter_comarca" />
						<label for="css_filter_comarca">css</label>
					</div>
				</div>
				<div id="comarca_filter_buttonSet" class="text-right">
                   <button id="comarca_filter_cleanButton" type="button" class="btn-material btn-material-primary-low-emphasis mr-2">
                             <i class="mdi mdi-eraser"></i>
                      <span>
                             <spring:message code="clear" />
                      </span>
                       </button>
                       <button id="comarca_filter_filterButton" type="button" class="btn-material btn-material-primary-low-emphasis">
                             <i class="mdi mdi-filter"></i>
                      <span>
                             <spring:message code="filter" />
                      </span>
                    </button>
               </div>

			</fieldset>
		</form>
	</div>

<table id="comarca" class="tableFit table-striped table-bordered table-material" 
	data-url-base="../jqGridComarca"
	data-filter-form="#comarca_filter_form">
    <thead>
        <tr>
            <th data-col-prop="code">code</th>
            <th data-col-prop="descEs" data-col-sidx="t1.desc_Es">descEs</th>
            <th data-col-prop="descEu" data-col-sidx="t1.desc_Eu">descEu</th>
            <th data-col-prop="css" >css</th>
            <th data-col-prop="provincia.code" data-col-sidx="provincia.code" >provincia.code</th>
            <th data-col-prop="provincia.descEs" data-col-sidx="provincia.descEs">provincia.descEs</th>
        </tr>
    </thead>
</table>

<!-- <h2>table</h2> -->

<!-- Formulario de detalle -->
<div id="comarca_detail_div" class="rup-table-formEdit-detail d-none">
	<!-- Barra de navegaci�n del detalle -->
	<div id ="comarca_detail_navigation" class="row no-gutters"></div>
	<!-- Separador -->
	<hr class="m-1">
	<div class="dialog-content-material">
		<!-- Formulario -->
		<form id="comarca_detail_form">
			<!-- Feedback del formulario de detalle -->
			<div id ="comarca_detail_feedback"></div>
			<div class="form-row">
				<!-- Campos del formulario de detalle -->
				<div class="form-groupMaterial col-sm">
			    	<input type="text" name="code" id="code_detailForm_table" />
					<label for="code_detailForm_table">code</label>
			    </div>			    
			    <div class="form-groupMaterial col-sm">
			    	<input type="text" name="descEs" id="descEs_detail_table" />
			    	<label for="descEs_detail_table">descEs</label>
			    </div>
			</div>
			<div class="form-row">       
			    <div class="form-groupMaterial col-sm">
			    	<input type="text" name="descEu" id="descEu_detail_table" />
			    	<label for="descEu_detail_table">descEu</label>
			    </div>			    
			    <div class="form-groupMaterial col-sm">
			    	<input type="text" name="css" id="css_detail_table" />
			    	<label for="css_detail_table">css</label>
			    </div>
			</div>
			<div class="form-row">  
			    <div class="form-groupMaterial col-sm">
					<select id="provinciaRemote" name="provincia.code"><option>&nbsp;</option></select>
			    	<label for="provinciaRemote">Provincia</label>
			    </div>			    
			    <div class="form-groupMaterial col-sm d-none">
			    	<input type="text" name="provincia.descEs" id="provinciaDescEs_detail_table" />
			    	<label for="provinciaDescEs_detail_table">provincia.descEs</label>
			    </div>
				<!-- Fin campos del formulario de detalle -->	
			</div>	
		</form>
	</div>
	<div class="rup-table-buttonpanel-material">
		<div class="text-right">
			<button id="comarca_detail_button_cancel" type="button">
				<spring:message code="cancel" />
			</button>
			<button id="comarca_detail_button_save" type="button">
				<spring:message code="save" />
			</button>
			<button id="comarca_detail_button_save_repeat" type="button">
				<spring:message code="saveAndContinue" />
			</button>
		</div>
	</div>
</div>

<h2>Localidad</h2>

	<div id="localidad_filter_div"  class="rup-table-filter">
		<form id="localidad_filter_form">
			<div  id="localidad_filter_toolbar" class="formulario_legend"></div>
			<fieldset id="localidad_filter_fieldset" class="rup-table-filter-fieldset">
				<div class="form-row">
					<div class="form-groupMaterial col-sm">
						<input type="text" name="code" id="code_filter_localidad" />
						<label for="code_filter_localidad">code</label>
					</div>
					<div class="form-groupMaterial col-sm">
						<input type="text" name="descEs" id="descEs_filter_localidad" />
						<label for="descEs_filter_localidad">descEs</label>
					</div>
				</div>
				<div class="form-row">
					<div class="form-groupMaterial col-sm">
						<input type="text" name="descEu" id="descEu_filter_localidad" />
						<label for="descEu_filter_localidad">descEu</label>
					</div>
					<div class="form-groupMaterial col-sm">
						<input type="text" name="css" id="css_filter_localidad" />
						<label for="css_filter_localidad">css</label>
					</div>
				</div>
				<div id="localidad_filter_buttonSet" class="text-right">
                       <button id="localidad_filter_cleanButton" type="button" class="btn-material btn-material-primary-low-emphasis mr-2">
                             <i class="mdi mdi-eraser"></i>
                      <span>
                             <spring:message code="clear" />
                      </span>
                       </button>
                       <button id="localidad_filter_filterButton" type="button" class="btn-material btn-material-primary-high-emphasis">
                             <i class="mdi mdi-filter"></i>
                      <span>
                             <spring:message code="filter" />
                      </span>
                       </button>
                 </div>

			</fieldset>
		</form>
	</div>
	
<table id="localidad" class="tableFit table-striped table-bordered table-material" 
	data-url-base="../jqGridLocalidad"
	data-filter-form="#localidad_filter_form">
	<thead>
		<tr>
			<th data-col-prop="code" data-col-sidx="code">code</th>
		    <th data-col-prop="descEs" data-col-sidx="t1.desc_Es">descEs</th>
		    <th data-col-prop="descEu" data-col-sidx="t1.desc_Eu">descEu</th>
		    <th data-col-prop="css">css</th>
		</tr>
	</thead>
</table>	

<!-- <h2>table</h2> -->

<!-- Formulario de detalle -->
<div id="localidad_detail_div" class="rup-table-formEdit-detail d-none">
	<!-- Barra de navegaci�n del detalle -->
	<div id ="localidad_detail_navigation" class="row no-gutters"></div>
	<!-- Separador -->
	<hr class="m-1">
	<div class="dialog-content-material">
		<!-- Formulario -->
		<form id="localidad_detail_form">
			<!-- Feedback del formulario de detalle -->
			<div id ="localidad_detail_feedback"></div>
			<div class="form-row">
				<!-- Campos del formulario de detalle -->
				<div class="form-groupMaterial col-sm">
			    	<input type="text" name="code" id="code_detailForm_table" />
					<label for="code_detailForm_table">code</label>
			    </div>
			    
			    <div class="form-groupMaterial col-sm">
			    	<input type="text" name="descEs" id="descEs_detail_table" />
			    	<label for="descEs_detail_table">descEs</label>
			    </div>
			</div>
			<div class="form-row">       
			    <div class="form-groupMaterial col-sm">
			    	<input type="text" name="descEu" id="descEu_detail_table" />
			    	<label for="descEu_detail_table">descEu</label>
			    </div>  
			    
			    <div class="form-groupMaterial col-sm">
			    	<input type="text" name="css" id="css_detail_table" />
			    	<label for="css_detail_table">css</label>
			    </div>
			</div>
			<div class="form-row">  <!--  
			    <div class="form-group col-sm">
			    	<select id="comarcaRemote" name="comarca.code"><option>&nbsp;</option></select>
			    	<label for="comarcaRemote">Comarca</label>
			    </div> -->
			    <div class="form-groupMaterial col-sm d-none">
			    	<input type="text" name="comarca.css" id="comarcaCss_detail_table" />
			    	<label for="comarcaCss_detail_table">comarca.css</label>
			    </div>
			</div>
			<div class="form-row">  
			    <div class="form-groupMaterial col-sm d-none">
			    	<input type="text" name="comarca.descEs" id="comarcaDescEs_detail_table" />
			    	<label for="comarcaDescEs_detail_table">comarca.descEs</label>
			    </div>
			    <div class="form-groupMaterial col-sm d-none">
			    	<input type="text" name="comarca.descEu" id="comarcaDescEu_detail_table" />
			    	<label for="comarcaDescEu_detail_table">comarca.descEu</label>
			    </div>
				<!-- Fin campos del formulario de detalle -->	
			</div>	
		</form>
	</div>
	<div class="rup-table-buttonpanel-material">
		<div class="text-right">
			<button id="localidad_detail_button_cancel" type="button">
				<spring:message code="cancel" />
			</button>
			<button id="localidad_detail_button_save" type="button">
				<spring:message code="save" />
			</button>
			<button id="localidad_detail_button_save_repeat" type="button">
				<spring:message code="saveAndContinue" />
			</button>
		</div>
	</div>
</div>
