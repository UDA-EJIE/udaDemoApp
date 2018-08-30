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
				<div class="formulario_columna_cnt">
					<div class="formulario_linea_izda_float">
						<label for="code_filter_comarca" class="formulario_linea_label">code:</label>
						<input type="text" name="code" class="formulario_linea_input" id="code_filter_comarca" />
					</div>
					<div class="formulario_linea_izda_float">
						<label for="codeProvincia_filter_comarca" class="formulario_linea_label">codeProvincia:</label>
						<input type="text" name="provincia.codeProvincia" class="formulario_linea_input" id="codeProvincia_filter_comarca" />
					</div>
					<div class="formulario_linea_izda_float">
						<label for="descEs_filter_comarca" class="formulario_linea_label">descEs:</label>
						<input type="text" name="descEs" class="formulario_linea_input" id="descEs_filter_comarca" />
					</div>
					<div class="formulario_linea_izda_float">
						<label for="descEu_filter_comarca" class="formulario_linea_label">descEu:</label>
						<input type="text" name="descEu" class="formulario_linea_input" id="descEu_filter_comarca" />
					</div>
					<div class="formulario_linea_izda_float">
						<label for="css_filter_comarca" class="formulario_linea_label">css:</label>
						<input type="text" name="css" class="formulario_linea_input" id="css_filter_comarca" />
					</div>
				</div>
				<div id="comarca_filter_buttonSet" class="right_buttons">
                   <button id="comarca_filter_cleanButton" type="button" class="btn btn-warning rup-limpiar">
                             <i class="fa fa-eraser"></i>
                      <span>
                             <spring:message code="clear" />
                      </span>
                       </button>
                       <button id="comarca_filter_filterButton" type="button" class="btn rup-filtrar rup-filter-dropdown">
                             <i class="fa fa-filter"></i>
                      <span>
                             <spring:message code="filter" />
                      </span>
                    </button>
               </div>

			</fieldset>
		</form>
	</div>

<table id="comarca" class="tableFit table-striped table-bordered" 
	data-url-base="../../jqGridComarca"
	data-filter-form="#comarca_filter_form" 
	cellspacing="0">
        <thead>
            <tr>
                <th data-col-prop="code">code</th>
                <th data-col-prop="descEs" data-col-sidx="desc_Es">descEs</th>
                <th data-col-prop="descEu" data-col-sidx="desc_Eu">descEu</th>
                <th data-col-prop="css" >css</th>
                <th data-col-prop="provincia.code" data-col-sidx="provincia.code" >provincia.code</th>
                <th data-col-prop="provincia.descEs" data-col-sidx="provincia.descEs">provincia.descEs</th>
            </tr>
        </thead>
        <tfoot>
          <tr>
              <th>code</th>
              <th>descEs</th>
              <th>descEu</th>
              <th>css</th>
              <th>provincia.code</th>
              <th>provincia.descEs</th>
          </tr>
        </tfoot>
</table>


<h2>Localidad</h2>

	<div id="localidad_filter_div"  class="rup-table-filter">
		<form id="localidad_filter_form">
			<div  id="localidad_filter_toolbar" class="formulario_legend"></div>
			<fieldset id="localidad_filter_fieldset" class="rup-table-filter-fieldset">
				<div class="formulario_columna_cnt">
					<div class="formulario_linea_izda_float">
						<label for="code_filter_localidad" class="formulario_linea_label">code:</label>
						<input type="text" name="code" class="formulario_linea_input" id="code_filter_localidad" />
					</div>
					<div class="formulario_linea_izda_float">
						<label for="codeProvincia_filter_localidad" class="formulario_linea_label">codeProvincia:</label>
						<input type="text" name="provincia.codeProvincia" class="formulario_linea_input" id="codeProvincia_filter_localidad" />
					</div>
					<div class="formulario_linea_izda_float">
						<label for="descEs_filter_localidad" class="formulario_linea_label">descEs:</label>
						<input type="text" name="descEs" class="formulario_linea_input" id="descEs_filter_localidad" />
					</div>
					<div class="formulario_linea_izda_float">
						<label for="descEu_filter_localidad" class="formulario_linea_label">descEu:</label>
						<input type="text" name="descEu" class="formulario_linea_input" id="descEu_filter_localidad" />
					</div>
					<div class="formulario_linea_izda_float">
						<label for="css_filter_localidad" class="formulario_linea_label">css:</label>
						<input type="text" name="css" class="formulario_linea_input" id="css_filter_localidad" />						
					</div>
				</div>
				<div id="localidad_filter_buttonSet" class="right_buttons">
                       <button id="localidad_filter_cleanButton" type="button" class="btn btn-warning rup-limpiar">
                             <i class="fa fa-eraser"></i>
                      <span>
                             <spring:message code="clear" />
                      </span>
                       </button>
                       <button id="localidad_filter_filterButton" type="button" class="btn rup-filtrar rup-filter-dropdown">
                             <i class="fa fa-filter"></i>
                      <span>
                             <spring:message code="filter" />
                      </span>
                       </button>
                 </div>

			</fieldset>
		</form>
	</div>
	
<table id="localidad" class="tableFit table-striped table-bordered" 
	data-url-base="../../jqGridLocalidad"
	data-filter-form="#localidad_filter_form" 
	cellspacing="0">
        <thead>
            <tr>
                <th data-col-prop="code" data-col-sidx="code">code</th>
                <th data-col-prop="descEs" data-col-sidx="desc_Es">descEs</th>
                <th data-col-prop="descEu" data-col-sidx="desc_Eu">descEu</th>
                <th data-col-prop="css" >css</th>
            </tr>
        </thead>
        <tfoot>
          <tr>
              <th>code</th>
              <th>descEs</th>
              <th>descEu</th>
              <th>css</th>
          </tr>
        </tfoot>
</table>	
