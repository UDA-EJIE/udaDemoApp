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
	
	<h3>Formulario horizontal (Material)</h3>
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
				
			<span id="alertDayError"></span>
        	
        	<p>
				Componentes Rup:: select by id, autocomplete by id, select by name, autocomplete by name 
			<p>
        	
        	<div class="row">
	        	<div class="form-groupMaterial col-sm-6">
					<select id="rol_detail_table" name="rol"></select>
					<label for="rol_detail_table">Select by Id</label>
				</div>
				
				<div class="form-groupMaterial col-sm-6">
					<input id="autocomplete" name="autocomplete" /> 
					<label for="autocomplete">Autocomplete by Id</label>
				</div>
			</div>
			<div class="row">
				<div class="form-groupMaterial col-sm-6">
					<select name="rolName2" id="rolName2"></select>
					<label for="rolName2">Select by Name</label>
				</div>
				
				<div class="form-groupMaterial col-sm-6">
					<input name="autocompleteName2" id="autocompleteName2"/> 
					<label for="autocompleteName2">Autocomplete by Name</label>
				</div>
			</div>
			
			<div class="row justify-content-center">
				<button type="submit" class="btn-material btn-material-secondary-high-emphasis">Validar</button>
			</div>
		
		</form>
		
	
	</div>
	
	<h3>Formulario horizontal (Bootstrap)</h3>
	<p>
		Los textos 
	<p>
	<div class="example">
		<div id="feedbackHorizontalBootstrap"></div>
		<form id="formHorizontalBootstrap" >
			
				
			<div class="form-group row">
		     	<label for="nombre" class="col-sm-2 col-form-label"><spring:message code="nombre" /></label>
		     	<div class="col-sm-10">
			  		<input type="text" name="nombre" class="form-control" id="nombre" />
			  	</div>
		    </div>


		    <div class="form-group row">
		     	<label for="apellido1" class="col-sm-2 col-form-label"><spring:message code="apellido1" /></label>
		     	<div class="col-sm-10">
			  		<input type="text" name="apellido1" class="form-control" id="apellido1" />
			  	</div>
		    </div>

		    <div class="form-group row">
		     	<label for="apellido2" class="col-sm-2 col-form-label"><spring:message code="apellido2" /></label>
		     	<div class="col-sm-10">
			  		<input type="text" name="apellido2" class="form-control" id="apellido2" />
			  	</div>
		    </div>
			    
		    <div class="form-group">
		        <label id="alertDayErrorLabel" for="alertDay[]">Alert on</label>
		    </div>
			    
		  	<div class="row">
				    
	            <div class="col-xs-6">
	                <div class="checkbox">
	                    <label>
	                        <input type="checkbox" value="0" name="alertDay[]" > Lunes
	                    </label>
	                </div>
	                <div class="checkbox">
	                    <label>
	                        <input type="checkbox" value="1" name="alertDay[]" > Martes
	                    </label>
	                </div>
	                <div class="checkbox">
	                    <label>
	                        <input type="checkbox" value="2" name="alertDay[]" > Miércoles
	                    </label>
	                </div>
				</div>
						
	            <div class="col-xs-6">
	                <div class="checkbox">
	                    <label>
	                        <input type="checkbox" value="3" name="alertDay[]" > Jueves
	                    </label>
	                </div>
	                <div class="checkbox">
	                    <label>
	                        <input type="checkbox" value="4" name="alertDay[]" > Viernes
	                    </label>
	                </div>
	                <div class="checkbox">
	                    <label>
	                        <input type="checkbox" value="5" name="alertDay[]" > Fín de semana
	                    </label>
	                </div>
				</div>
			 		
			</div>
			<span id="alertDayError"></span>
        	
        	<p>
				Componentes Rup:: select by id, autocomplete by id, select by name, autocomplete by name 
			<p>
        	<div class="form-group row">
				<label for="rol_detail_table" class="col-sm-2 col-form-label">Select by Id:</label>
				<div class="col-sm-10">
					<select id="rol_detail_table" name="rol" class="formulario_linea_input" ></select>
				</div>
			</div>
			
			<div class="form-group row">
				<label for="autocomplete" class="col-sm-2 col-form-label">Autocomplete by Id:</label>
				<div class="col-sm-10">
					<input id="autocomplete" name="autocomplete" /> 
				</div>	
			</div>
			
			 <div class="form-group row">
				<label for="rolName2" class="col-sm-2 col-form-label">Select by Name:</label>
				<div class="col-sm-10">
					<select name="rolName2" class="formulario_linea_input" id="rolName"></select>
				</div>
			</div>
			
			<div class="form-group row">
				<label for="autocompleteName2" class="col-sm-2 col-form-label">Autocomplete by Name:</label>
				<div class="col-sm-10">
					<input name="autocompleteName2" id="autocompleteName"/> 
				</div>	
			</div>
			
			<div class="row justify-content-center">
				<div class="col-md-3">
					<input type="submit" class="btn btn-block btn-secondary" value="Validar"  />
				</div>
			</div>
		
		</form>
		
	
	</div>
	
		<h3>Formulario horizontal (jQueryUI)</h3>
	<p>
		Los textos 
	<p>
	<div class="example">
		<div id="feedbackHorizontalJqueryui"></div>
		<form id="formHorizontalJqueryui" >
			
				
			<div class="form-group row">
		     	<label for="nombreJqueryui" class="col-sm-2 col-form-label"><spring:message code="nombre" /></label>
		     	<div class="col-sm-10">
			  		<input type="text" name="nombreJqueryui" class="form-control" id="nombre" />
			  	</div>
		    </div>
			    
		    <div class="form-group">
		        <label id="alertDayErrorLabel" for="alertDayJqueryui[]">Alert on</label>
		    </div>
			    
		  	<div class="row">
				    
	            <div class="col-xs-6">
	                <div class="checkbox">
	                    <label>
	                        <input type="checkbox" value="0" name="alertDayJqueryui[]" > Lunes
	                    </label>
	                </div>
	                <div class="checkbox">
	                    <label>
	                        <input type="checkbox" value="1" name="alertDayJqueryui[]" > Martes
	                    </label>
	                </div>
	                <div class="checkbox">
	                    <label>
	                        <input type="checkbox" value="2" name="alertDayJqueryui[]" > Miércoles
	                    </label>
	                </div>
				</div>
						
	            <div class="col-xs-6">
	                <div class="checkbox">
	                    <label>
	                        <input type="checkbox" value="3" name="alertDayJqueryui[]" > Jueves
	                    </label>
	                </div>
	                <div class="checkbox">
	                    <label>
	                        <input type="checkbox" value="4" name="alertDayJqueryui[]" > Viernes
	                    </label>
	                </div>
	                <div class="checkbox">
	                    <label>
	                        <input type="checkbox" value="5" name="alertDayJqueryui[]" > Fín de semana
	                    </label>
	                </div>
				</div>
			 		
			</div>
			<span id="alertDayError"></span>
        	
        	<p>
				Componentes Rup:: select by id, autocomplete by id, select by name, autocomplete by name 
			<p>
        	<div class="form-group row">
				<label for="rol_detail_tableJqueryui" class="col-sm-2 col-form-label">Select by Id:</label>
				<div class="col-sm-10">
					<select id="rol_detail_tableJqueryui" name="rolJqueryui" class="formulario_linea_input" ></select>
				</div>
			</div>
			
			<div class="form-group row">
				<label for="autocompleteJqueryui" class="col-sm-2 col-form-label">Autocomplete by Id:</label>
				<div class="col-sm-10">
					<input id="autocompleteJqueryui" name="autocompleteJqueryui" /> 
				</div>	
			</div>
			
			 <div class="form-group row">
				<label for="rolName2Jqueryui" class="col-sm-2 col-form-label">Select by Name:</label>
				<div class="col-sm-10">
					<select name="rolName2Jqueryui" class="formulario_linea_input" id="rolNameJqueryui"></select>
				</div>
			</div>
			
			<div class="form-group row">
				<label for="autocompleteName2Jqueryui" class="col-sm-2 col-form-label">Autocomplete by Name:</label>
				<div class="col-sm-10">
					<input name="autocompleteName2Jqueryui" id="autocompleteNameJqueryui"/> 
				</div>	
			</div>
			
			<div class="row justify-content-center">
				<div class="col-md-3">
					<input type="submit" class="btn btn-block btn-secondary" value="Validar"  />
				</div>
			</div>
		
		</form>
		
	
	</div>
	
</section>

