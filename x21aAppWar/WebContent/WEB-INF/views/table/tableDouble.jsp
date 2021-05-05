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
<h2>Tabla doble</h2>
<!-- Titulo pagina -->
<div class="row mt-5">
    <div class="col-xl-6">
    	<div class="row">
		  <div class="col-xl-12">
		      <jsp:include page="includes/tableFilterForm.jsp"></jsp:include>
		  </div>
		</div>
        <div class="row">
            <div class="col-xl-12">
                <table id="example"
                    class="tableFit table-striped table-bordered table-material"
                    data-url-base="."
                    data-filter-form="#example_filter_form">
                    <thead>
                        <tr>
                            <th data-col-prop="nombre" data-col-edit="true">Nombre</th>
                            <th data-col-prop="apellido1">Primer apellido</th>
                            <th data-col-prop="ejie" data-col-type="Checkbox">Ejie</th>
                            <th data-col-prop="fechaAlta" data-col-sidx="fecha_alta" data-col-type="Datepicker">Fecha alta</th>
                            <th data-col-prop="fechaBaja" data-col-sidx="fecha_baja" data-col-type="Datepicker">Fecha baja</th>
                            <th data-col-prop="rol" data-col-type="combo">Rol</th>
                        </tr>
                    </thead>
                </table>
            </div>
        </div>
            
        <jsp:include page="includes/tableEdit.jsp"></jsp:include>

        <form:form modelAttribute="options" method="POST" id="example_tableConfiguration" class="mt-5">
            <h3 id="pluginErrorLabel">Selección de Plugins</h3>
            <fieldset class="form-group">
                <div class="row">
                    <legend class="col-form-label col-sm-2 pt-0">Plugins</legend>
                    <div class="col-sm-10">
                        <div class="checkbox-material pluginsControl">
                        	<form:checkbox id="editForm" path="plugins" value="0"/>
                        	<label for="editForm">Edición en Formulario</label>
                        </div>
                        <div class="checkbox-material pluginsControl">
                        	<form:checkbox id="colReorder" path="plugins" value="1"/>
                        	<label for="colReorder">Col Reorder</label>
                        </div>
                        <div class="checkbox-material pluginsControl">
                       		<form:checkbox id="seeker" path="plugins" value="2"/>
                        	<label for="seeker">Seeker</label>
                        </div>
                        <div class="checkbox-material pluginsControl">
                        	<form:checkbox id="buttons" path="plugins" value="3"/>
                        	<label for="buttons">Botones</label>
                        </div>
                        <div class="checkbox-material pluginsControl">
                      		<form:checkbox id="groups" path="plugins" value="4"/>
                        	<label for="groups">Agrupamiento</label>
                        </div>
                        <div class="checkbox-material pluginsControl">
                        	<form:checkbox id="multiFilter" path="plugins" value="5"/>
                        	<label for="multiFilter">MultiFilter</label>
                        </div>
                        <div class="checkbox-material pluginsControl">
                        	<form:checkbox id="inlineEdit" path="plugins" value="6"/>
                       		<label for="inlineEdit">Edición en Linea</label>
                        </div>
                    </div>
                </div>
            </fieldset>
            <fieldset class="form-group">
                <div class="row">
                    <legend class="col-form-label col-sm-2 pt-0">Tipos de selección</legend>
                    <div class="col-sm-10">
                        <div class="radio-material pluginsControl">
                        	<form:radiobutton id="multiSelection" path="tipoSeleccionTabla" value="7"/>
                        	<label for="multiSelection">Multiselección</label>
                        </div>
                        <div class="radio-material pluginsControl">
                        	<form:radiobutton id="selection" path="tipoSeleccionTabla" value="8"/>
                        	<label for="selection">Selección Simple</label>
                        </div>
                        <div class="radio-material pluginsControl">
                        	<form:radiobutton id="noSelection" path="tipoSeleccionTabla" value="9"/>
                        	<label for="noSelection">Sin selección</label>
                        </div>
                    </div>
                </div>
            </fieldset>
            <fieldset class="form-group">
                <div class="row">
                    <legend class="col-form-label col-sm-2 pt-0">Pruebas</legend>
                    <div class="col-sm-10">
                        <div class="checkbox-material pluginsControl">
                        	<form:checkbox id="triggers" path="otros" value="10"/>
                        	<label for="triggers">Activar Triggers en Consola</label>
                        </div>
                        <div class="checkbox-material pluginsControl">
                       		<form:checkbox id="multiPart" path="otros" value="10"/>
                        	<label for="multiPart">Edición con MultiPart</label>
                        </div>
                    </div>
                </div>
            </fieldset>
            <span id="pluginError"></span>
            <button id="example_aplicar" type="button" class="btn-material btn-material-primary-medium-emphasis">Aplicar Cambios</button>
        </form:form>
    </div>

    <div class="col-xl-6 tableDoubleBorder">
    	<div class="row">
		  <div class="col-xl-12">
		      <jsp:include page="includes/tableFilterForm2.jsp"></jsp:include>
		  </div>
		</div>
        <div class="row">
            <div class="col-xl-12">
                <table id="example2"
                    class="tableFit table-striped table-bordered table-material"
                    data-url-base="./2"
                    data-filter-form="#example2_filter_form">
                    <thead>
                        <tr>
                            <th data-col-prop="nombre2" data-col-edit="true">Nombre</th>
                            <th data-col-prop="apellido12">Primer apellido</th>
                            <th data-col-prop="ejie2" data-col-type="Checkbox">Ejie</th>
                            <th data-col-prop="fechaAlta2" data-col-sidx="fecha_alta2" data-col-type="Datepicker">Fecha alta</th>
                            <th data-col-prop="fechaBaja2" data-col-sidx="fecha_baja2" data-col-type="Datepicker">Fecha baja</th>
                            <th data-col-prop="rol2" data-col-type="combo">Rol</th>
                        </tr>
                    </thead>
                </table>
            </div>
        </div>

       	<jsp:include page="includes/tableDoubleEdit.jsp"></jsp:include>
        
        <form:form modelAttribute="options" method="POST" id="example_tableConfiguration2" class="mt-5">
            <h3 id="pluginErrorLabel2">Selección de Plugins</h3>
            <fieldset class="form-group">
                <div class="row">
                    <legend class="col-form-label col-sm-2 pt-0">Plugins</legend>
                    <div class="col-sm-10">
                        <div class="checkbox-material pluginsControl">
                        	<form:checkbox id="editForm2" path="plugins" value="0"/>
                        	<label for="editForm2">Edición en Formulario</label>
                        </div>
                        <div class="checkbox-material pluginsControl">
                        	<form:checkbox id="colReorder2" path="plugins" value="1"/>
                        	<label for="colReorder2">Col Reorder</label>
                        </div>
                        <div class="checkbox-material pluginsControl">
                       		<form:checkbox id="seeker2" path="plugins" value="2"/>
                        	<label for="seeker2">Seeker</label>
                        </div>
                        <div class="checkbox-material pluginsControl">
                        	<form:checkbox id="buttons2" path="plugins" value="3"/>
                        	<label for="buttons2">Botones</label>
                        </div>
                        <div class="checkbox-material pluginsControl">
                      		<form:checkbox id="groups2" path="plugins" value="4"/>
                        	<label for="groups2">Agrupamiento</label>
                        </div>
                        <div class="checkbox-material pluginsControl">
                        	<form:checkbox id="multiFilter2" path="plugins" value="5"/>
                        	<label for="multiFilter2">MultiFilter</label>
                        </div>
                        <div class="checkbox-material pluginsControl">
                        	<form:checkbox id="inlineEdit2" path="plugins" value="6"/>
                       		<label for="inlineEdit2">Edición en Linea</label>
                        </div>
                    </div>
                </div>
            </fieldset>
            <fieldset class="form-group">
                <div class="row">
                    <legend class="col-form-label col-sm-2 pt-0">Tipos de selección</legend>
                    <div class="col-sm-10">
                        <div class="radio-material pluginsControl">
                        	<form:radiobutton id="multiSelection2" path="tipoSeleccionTabla" value="7"/>
                        	<label for="multiSelection2">Multiselección</label>
                        </div>
                        <div class="radio-material pluginsControl">
                        	<form:radiobutton id="selection2" path="tipoSeleccionTabla" value="8"/>
                        	<label for="selection2">Selección Simple</label>
                        </div>
                        <div class="radio-material pluginsControl">
                        	<form:radiobutton id="noSelection2" path="tipoSeleccionTabla" value="9"/>
                        	<label for="noSelection2">Sin selección</label>
                        </div>
                    </div>
                </div>
            </fieldset>
            <fieldset class="form-group">
                <div class="row">
                    <legend class="col-form-label col-sm-2 pt-0">Pruebas</legend>
                    <div class="col-sm-10">
                        <div class="checkbox-material pluginsControl">
                        	<form:checkbox id="triggers2" path="otros" value="10"/>
                        	<label for="triggers2">Activar Triggers en Consola</label>
                        </div>
                        <div class="checkbox-material pluginsControl">
                       		<form:checkbox id="multiPart2" path="otros" value="10"/>
                        	<label for="multiPart2">Edición con MultiPart</label>
                        </div>
                    </div>
                </div>
            </fieldset>
            <span id="pluginError2"></span>
            <button id="example_aplicar2" type="button" class="btn-material btn-material-primary-medium-emphasis">Aplicar Cambios</button>
        </form:form>
    </div>
</div>
