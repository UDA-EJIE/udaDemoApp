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
	
	<h3>Formulario alineado a la derecha</h3>
	<p>
		Los textos 
	<p>
	<div class="example">
		<div id="feedbackLeftAligned"></div>
		<form id="formLeftAligned" >
			<div class="row">
				
				<div class="col-md-4">
					<div class="form-group">
				     	<label for="nombre" class="label"><spring:message code="nombre" /></label>
					  	<input type="text" name="nombre" class="form-control" id="nombre" />
				    </div>
				</div>
				<div class="col-md-4">
				    <div class="form-group">
				     	<label for="apellido1" class="label"><spring:message code="apellido1" /></label>
					  	<input type="text" name="apellido1" class="form-control" id="apellido1" />
				    </div>
				</div>
				<div class="col-md-4">
				    <div class="form-group">
				     	<label for="apellido2" class="label"><spring:message code="apellido2" /></label>
					  	<input type="text" name="apellido2" class="form-control" id="apellido2" />
				    </div>
				</div>
			</div>
			<div class="row justify-content-center">
				<div class="col-md-3">
					<input type="submit" class="btn btn-block btn-secondary" value="Validar"  />
				</div>
			</div>
		
		</form>
		
	
	</div>
	
	<h3>Formulario horizontal</h3>
	<p>
		Los textos 
	<p>
	<div class="example">
		<div id="feedbackHorizontal"></div>
		<form id="formHorizontal" >
			
				
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
	
		<h3>Formulario horizontal Jqueryui, no bootstrap</h3>
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

