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
<section>
	<h2 class="title mb-3">Validacion de formularios</h2>
	<p>
		Ejemplos de validación de formularios en distintas disposiciones.
	<p>
	
	<h3>Formulario columnas</h3>
	<p>
		Los textos
	<p>
	<div class="example">
		<div id="feedbackColumns"></div>
		<form:form id="formColumns" modelAttribute="alumno" action="../upload/formSimple" enctype="multipart/form-data" method="POST">
			<div class="form-row">
				<div class="form-groupMaterial col-sm">
				  	<form:input path="nombre" id="nombre" />
			     	<label for="nombre"><spring:message code="nombre" /></label>
				</div>
				<div class="form-groupMaterial col-sm">
				  	<form:input path="apellido1" id="apellido1" />
			     	<label for="apellido1"><spring:message code="apellido1" /></label>
				</div>
				<div class="form-groupMaterial col-sm">
				  	<form:input path="apellido2" id="apellido2" />
			     	<label for="apellido2"><spring:message code="apellido2" /></label>
				</div>
			</div>
			<div class="row justify-content-center">
				<button type="submit" class="btn-material btn-material-secondary-high-emphasis">Validar</button>
			</div>
		</form:form>
	</div>

	<h3>Formulario columnas con required="required"</h3>
	<p>
		Los textos
	<p>
	<div class="example">
		<div id="feedbackColumnsRequired"></div>
		<form:form id="formColumnsRequired" modelAttribute="alumno" method="get">
			<div class="form-row">
				<div class="form-groupMaterial col-sm">
				  	<form:input path="nombre" id="nombre" required="required" />
			     	<label for="nombre"><spring:message code="nombre" /></label>
				</div>
				<div class="form-groupMaterial col-sm">
				  	<form:input path="apellido1" id="apellido1" required="required" />
			     	<label for="apellido1"><spring:message code="apellido1" /></label>
				</div>
				<div class="form-groupMaterial col-sm">
				  	<form:input path="apellido2" id="apellido2" required="required" />
			     	<label for="apellido2"><spring:message code="apellido2" /></label>
				</div>
			</div>
			<div class="row justify-content-center">
				<button type="submit" class="btn-material btn-material-secondary-high-emphasis">Validar</button>
			</div>
		</form:form>
	</div>

	<h3>Formulario horizontal</h3>
	<p>
		Los textos
	<p>
	<div class="example">
		<div id="feedbackHorizontalMaterial"></div>
		<form:form id="formHorizontalMaterial" modelAttribute="randomForm" method="get">

			<div class="form-row">
				<div class="col-sm-6">

					<div class="form-groupMaterial">
				  		<form:input path="nombre" id="nombre" required="required" />
			     		<label for="nombre"><spring:message code="nombre" /></label>
				    </div>


				    <div class="form-groupMaterial">
					  	<form:input path="apellido1" id="apellido1" required="required" />
				     	<label for="apellido1"><spring:message code="apellido1" /></label>
				    </div>

				    <div class="form-groupMaterial">
					  	<form:input path="apellido2" id="apellido2" required="required" />
				     	<label for="apellido2"><spring:message code="apellido2" /></label>
				    </div>

				</div>

			  	<div class="form-row col-sm-6">

					<div class="col-12">
						<label id="alertDayErrorLabel" data-title="alertDay">Alert on</label>
					</div>

		            <div class="col-6">
		                <div class="checkbox-material">
		                	<form:checkbox path="alertDay" value="0" id="checkLunes"/>
		                    <label for="checkLunes">Lunes</label>
		                </div>
		                <div class="checkbox-material">
		                    <form:checkbox path="alertDay" value="1" id="checkMartes"/>
		                    <label for="checkMartes">Martes</label>
		                </div>
		                <div class="checkbox-material">
		                    <form:checkbox path="alertDay" value="2" id="checkMiercoles"/>
		                    <label for="checkMiercoles">Miércoles</label>
		                </div>
					</div>

		            <div class="col-6">
		                <div class="checkbox-material">
		                    <form:checkbox path="alertDay" value="3" id="checkJueves"/>
		                    <label for="checkJueves">Jueves</label>
		                </div>
		                <div class="checkbox-material">
		                    <form:checkbox path="alertDay" value="4" id="checkViernes"/>
		                    <label for="checkViernes">Viernes</label>
		                </div>
		                <div class="checkbox-material">
		                	<form:checkbox path="alertDay" value="5" id="checkFindesemana"/>
		                    <label for="checkFindesemana">Fín de semana</label>
		                </div>
					</div>

				</div>
			</div>

			<div class="form-row col-sm-12">

				<div class="col-12">
					<label id="alertEdadErrorLabel" for="alertEdad[]" data-title="alertEdad">¿Mayor de edad?</label>
				</div>

				<div class="col-12">
	                <div class="radio-material">
	                	<form:radiobutton path="alertEdad" id="radioAdulto" value="0"/>
	                    <label for="radioAdulto">Sí</label>
	                </div>
	                <div class="radio-material">
	                    <form:radiobutton path="alertEdad" id="radioMenor" value="1" />
	                    <label for="radioMenor">No</label>
	                </div>
                </div>

			</div>

        	<p>
				Componentes Rup:: select by id, autocomplete by id, select by name, autocomplete by name
			</p>

			<p>
				Con clase padre 'row'
			</p>

        	<div class="row">
	        	<div class="form-groupMaterial col-sm-6">
					<form:select path="rol" id="rol_detail_table" class="formulario_linea_input"></form:select>
					<label for="rol_detail_table">Select by Id</label>
				</div>

				<div class="form-groupMaterial col-sm-6">
					<form:input path="autocomplete" id="autocomplete" />
					<label for="autocomplete">Autocomplete by Id</label>
				</div>

				<div class="form-groupMaterial col-sm-12">
					<form:input path="autocompleteCombobox" id="autocompleteCombobox" />
					<label for="autocompleteCombobox">Autocomplete Combobox</label>
				</div>
			</div>

			<p>
				Con clase padre 'form-row'
			</p>

			<div class="form-row">
				<div class="form-groupMaterial col-sm-6">
					<form:select path="rolName2"  id="rolName2"></form:select>
					<label for="rolName2">Select by Name</label>
				</div>

				<div class="form-groupMaterial col-sm-6">
					<form:input path="autocompleteName2" id="autocompleteName2"/>
					<label for="autocompleteName2">Autocomplete by Name</label>
				</div>

				<div class="form-groupMaterial col-sm-6">
					<form:input path="autocompleteCombobox2" id="autocompleteCombobox2"/>
					<label for="autocompleteCombobox2">Autocomplete Combobox</label>
				</div>
				<div class="form-groupMaterial col-sm-6">
					<form:input path="dateField" id="dateField"/>
					<label for="dateField">Fecha Alta</label>
				</div>
				


				<div class="form-groupMaterial col-sm-12">
					<form:textarea path="textarea" id="textarea" rows="5" />
					<label for="textarea">Textarea</label>
				</div>
			</div>

			<div class="row justify-content-center">
				<button type="submit" class="btn-material btn-material-secondary-high-emphasis">Validar</button>
			</div>

		</form:form>

	</div>
	
</section>

