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
	<h2>Validacion de formularios</h2>
	<p>
		Ejemplos de validación de formularios en distintas disposiciones.
	<p>
	
	<h3>Formulario columnas</h3>
	<p>
		Los textos 
	<p>
	<div class="example">
		<div id="feedbackColumns"></div>
		<form id="formColumns" >
			<div class="form-row">
				<div class="form-groupMaterial col-sm">
				  	<input type="text" name="nombre" id="nombre" />
			     	<label for="nombre"><spring:message code="nombre" /></label>
				</div>
				<div class="form-groupMaterial col-sm">
				  	<input type="text" name="apellido1" id="apellido1" />
			     	<label for="apellido1"><spring:message code="apellido1" /></label>
				</div>
				<div class="form-groupMaterial col-sm">
				  	<input type="text" name="apellido2" id="apellido2" />
			     	<label for="apellido2"><spring:message code="apellido2" /></label>
				</div>
			</div>
			<div class="row justify-content-center">
				<button type="submit" class="btn-material btn-material-secondary-high-emphasis">Validar</button>
			</div>
		
		</form>
		
	
	</div>
	
	<h3>Formulario columnas con required</h3>
	<p>
		Los textos 
	<p>
	<div class="example">
		<div id="feedbackColumnsRequired"></div>
		<form id="formColumnsRequired" >
			<div class="form-row">
				<div class="form-groupMaterial col-sm">
				  	<input type="text" name="nombre" id="nombre" required />
			     	<label for="nombre"><spring:message code="nombre" /></label>
				</div>
				<div class="form-groupMaterial col-sm">
				  	<input type="text" name="apellido1" id="apellido1" required />
			     	<label for="apellido1"><spring:message code="apellido1" /></label>
				</div>
				<div class="form-groupMaterial col-sm">
				  	<input type="text" name="apellido2" id="apellido2" required />
			     	<label for="apellido2"><spring:message code="apellido2" /></label>
				</div>
			</div>
			<div class="row justify-content-center">
				<button type="submit" class="btn-material btn-material-secondary-high-emphasis">Validar</button>
			</div>
		</form>
	</div>
	
	<h3>Formulario horizontal</h3>
	<p>
		Los textos 
	<p>
	<div class="example">
		<div id="feedbackHorizontalMaterial"></div>
		<form id="formHorizontalMaterial" >
			
			<div class="form-row">
				<div class="col-sm-6">
					
					<div class="form-groupMaterial">
				  		<input type="text" name="nombre" id="nombre" required />
			     		<label for="nombre"><spring:message code="nombre" /></label>
				    </div>
		
		
				    <div class="form-groupMaterial">
					  	<input type="text" name="apellido1" id="apellido1" required />
				     	<label for="apellido1"><spring:message code="apellido1" /></label>
				    </div>
		
				    <div class="form-groupMaterial">
					  	<input type="text" name="apellido2" id="apellido2" required />
				     	<label for="apellido2"><spring:message code="apellido2" /></label>
				    </div>
				
				</div>
			    
			  	<div class="form-row col-sm-6">
					
					<div class="col-12">
						<label id="alertDayErrorLabel" for="alertDay[]">Alert on</label>
					</div>
					
		            <div class="col-6">
		                <div class="checkbox-material">
		                	<input id="checkLunes" type="checkbox" value="0" name="alertDay[]" >
		                    <label for="checkLunes">Lunes</label>
		                </div>
		                <div class="checkbox-material">
		                    <input id="checkMartes" type="checkbox" value="1" name="alertDay[]" >
		                    <label for="checkMartes">Martes</label>
		                </div>
		                <div class="checkbox-material">
		                    <input id="checkMiercoles" type="checkbox" value="2" name="alertDay[]" >
		                    <label for="checkMiercoles">Miércoles</label>
		                </div>
					</div>
							
		            <div class="col-6">
		                <div class="checkbox-material">
		                    <input id="checkJueves" type="checkbox" value="3" name="alertDay[]" >
		                    <label for="checkJueves">Jueves</label>
		                </div>
		                <div class="checkbox-material">
		                    <input id="checkViernes" type="checkbox" value="4" name="alertDay[]" >
		                    <label for="checkViernes">Viernes</label>
		                </div>
		                <div class="checkbox-material">
		                	<input id="checkFindesemana" type="checkbox" value="5" name="alertDay[]" >
		                    <label for="checkFindesemana">Fín de semana</label>
		                </div>
					</div>
				 		
				</div>
			</div>
				
			<div class="form-row col-sm-12">
				
				<div class="col-12">
					<label id="alertEdadErrorLabel" for="alertEdad[]">¿Mayor de edad?</label>
				</div>
				
				<div class="col-12">
	                <div class="radio-material">
	                	<input id="radioAdulto" type="radio" value="0" name="alertEdad[]" >
	                    <label for="radioAdulto">Sí</label>
	                </div>
	                <div class="radio-material">
	                    <input id="radioMenor" type="radio" value="1" name="alertEdad[]" >
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
					<select id="rol_detail_table" name="rol"></select>
					<label for="rol_detail_table">Select by Id</label>
				</div>
				
				<div class="form-groupMaterial col-sm-6">
					<input id="autocomplete" name="autocomplete" /> 
					<label for="autocomplete">Autocomplete by Id</label>
				</div>
				
				<div class="form-groupMaterial col-sm-12">
					<input id="autocompleteCombobox" name="autocompleteCombobox" /> 
					<label for="autocompleteCombobox">Autocomplete Combobox</label>
				</div>
			</div>
			
			<p>
				Con clase padre 'form-row'
			</p>
			
			<div class="form-row">
				<div class="form-groupMaterial col-sm-6">
					<select name="rolName2" id="rolName2"></select>
					<label for="rolName2">Select by Name</label>
				</div>
				
				<div class="form-groupMaterial col-sm-6">
					<input name="autocompleteName2" id="autocompleteName2"/> 
					<label for="autocompleteName2">Autocomplete by Name</label>
				</div>
				
				<div class="form-groupMaterial col-sm-12">
					<input id="autocompleteCombobox2" name="autocompleteCombobox2" /> 
					<label for="autocompleteCombobox2">Autocomplete Combobox</label>
				</div>
				
				<div class="form-groupMaterial col-sm-12">
					<textarea id="textarea" name="textarea"></textarea> 
					<label for="textarea">Textarea</label>
				</div>
			</div>
			
			<div class="row justify-content-center">
				<button type="submit" class="btn-material btn-material-secondary-high-emphasis">Validar</button>
			</div>
		
		</form>
		
	</div>
	
</section>

