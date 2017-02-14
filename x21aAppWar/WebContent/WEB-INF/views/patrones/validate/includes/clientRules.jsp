<%--  
 -- Copyright 2011 E.J.I.E., S.A.
 --
 -- Licencia con arreglo a la EUPL, VersiÃ³n 1.1 exclusivamente (la Â«LicenciaÂ»);
 -- Solo podrÃ¡ usarse esta obra si se respeta la Licencia.
 -- Puede obtenerse una copia de la Licencia en
 --
 --      http://ec.europa.eu/idabc/eupl.html
 --
 -- Salvo cuando lo exija la legislaciÃ³n aplicable o se acuerde por escrito, 
 -- el programa distribuido con arreglo a la Licencia se distribuye Â«TAL CUALÂ»,
 -- SIN GARANTÃAS NI CONDICIONES DE NINGÃN TIPO, ni expresas ni implÃ­citas.
 -- VÃ©ase la Licencia en el idioma concreto que rige los permisos y limitaciones
 -- que establece la Licencia.
 --%>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@include file="/WEB-INF/includeTemplate.inc"%>
	<form:form id="formValidaciones" action='${pageContext.request.contextPath}/patrones/validacion/cliente' modelAttribute="alumno" >
	
	<fieldset class="alumnoFieldset">
		<legend><spring:message code="obligatoriedad" /></legend>
		<div class="row">
			<div class="col-md-4">
				<div class="form-group">
			     	<label for="campoObligatorio1" class="label"><spring:message code="campoObligatorio" /></label>
				  	<input type="text" name="campoObligatorio1" class="form-control" id="campoObligatorio1" />
			    </div>
			</div>
			<div class="col-md-4">
			    <div class="form-group">
		     		<input type="checkbox" name="esObligatorio" id="esObligatorio" style="float:left;"/>
			     	<label for="esObligatorio" class="label">
			     		&nbsp;<spring:message code="patron.validacion.obligatorioSi" />
		     		</label>
				  	<input type="text" name="campoObligatorio2" class="form-control" id="campoObligatorio2" />
			    </div>
			</div>
			<div class="col-md-4">
			    <div class="form-group">
			     	<select id="diaObligatorio" style="float: left">
			     	</select>
			     	<label for="campoObligatorio3" class="label">
				     	&nbsp;<spring:message code="patron.validacion.obligatorioSiCombo" />
			     	</label>
				  	<input type="text" name="campoObligatorio3" class="form-control" id="campoObligatorio3" />
			    </div>
			</div>
		</div>
		
	</fieldset>
	
	<fieldset class="alumnoFieldset">
		<legend><spring:message code="patron.validacion.validacionesNumericas" /></legend>
		<div class="row">
			<div class="col-md-4">
		   		<div class="form-group">
			     	<label for="valorMinimo" class="label"><spring:message code="patron.validacion.min" /></label>
				  	<input type="text" name="valorMinimo" class="form-control" id="valorMinimo" />
			    </div>
			</div>
			<div class="col-md-4">
			    <div class="form-group">
			     	<label for="valorMaximo" class="label"><spring:message code="patron.validacion.max" /></label>
				  	<input type="text" name="valorMaximo" class="form-control" id="valorMaximo" />
			    </div>
			</div>
			<div class="col-md-4">
			    <div class="form-group">
			     	<label for="valorIntervalo" class="label"><spring:message code="patron.validacion.range" /></label>
				  	<input type="text" name="valorIntervalo" class="form-control" id="valorIntervalo" />
			    </div>
			</div>
		 </div>
		 <div class="row">
		    <div class="col-md-4">
		   		<div class="form-group">
			     	<label for="entero" class="label"><spring:message code="patron.validacion.integer" /></label>
				  	<input type="text" name="entero" class="form-control" id="entero" />
			    </div>
		    </div>
			<div class="col-md-4">
			    <div class="form-group">
			     	<label for="numeroDecimal" class="label"><spring:message code="patron.validacion.number" /></label>
				  	<input type="text" name="numeroDecimal" class="form-control" id="numeroDecimal" />
		    	</div>
			</div>
			<div class="col-md-4"></div>
		   
		</div>
	</fieldset>
	
	<fieldset class="alumnoFieldset">
		<legend><spring:message code="patron.validacion.validacionesTexto" /></legend>
		<div class="row">
			<div class="col-md-4">
		   		<div class="form-group">
			     	<label for="longitudMinima" class="label"><spring:message code="patron.validacion.minlength" /></label>
				  	<input type="text" name="longitudMinima" class="form-control" id="longitudMinima" />
			    </div>
			</div>
			<div class="col-md-4">
			    <div class="form-group">
			     	<label for="longitudMaxima" class="label"><spring:message code="patron.validacion.maxlength" /></label>
				  	<input type="text" name="longitudMaxima" class="form-control" id="longitudMaxima" />
			    </div>
			</div>
			<div class="col-md-4">
			    <div class="form-group">
			     	<label for="longitudIntervalo" class="label"><spring:message code="patron.validacion.rangelength" /></label>
				  	<input type="text" name="longitudIntervalo" class="form-control" id="longitudIntervalo" />
			    </div>
			</div>
		</div>
		<div class="row">
		    <div class="col-md-4">
		   		<div class="form-group">
			     	<label for="palabrasMaximo" class="label"><spring:message code="patron.validacion.maxWords" /></label>
				  	<input type="text" name="palabrasMaximo" class="form-control" id="palabrasMaximo" />
		    	</div>
			</div>
			<div class="col-md-4">
			    <div class="form-group">
			     	<label for="palabrasMinimo" class="label"><spring:message code="patron.validacion.minWords" /></label>
				  	<input type="text" name="palabrasMinimo" class="form-control" id="palabrasMinimo" />
			    </div>
			</div>
			<div class="col-md-4">
			    <div class="form-group">
			     	<label for="palabrasIntervalo" class="label"><spring:message code="patron.validacion.number" /></label>
				  	<input type="text" name="palabrasIntervalo" class="form-control" id="palabrasIntervalo" />
		   		</div>
			</div>
		</div>
		<div class="row">
		    <div class="col-md-4">
		   		<div class="form-group">
			     	<label for="letrasYPuntuacion" class="label"><spring:message code="patron.validacion.letterswithbasicpunc" /></label>
				  	<input type="text" name="letrasYPuntuacion" class="form-control" id="letrasYPuntuacion" />
		    	</div>
			</div>
			<div class="col-md-4">
			    <div class="form-group">
			     	<label for="alfanumerico" class="label"><spring:message code="patron.validacion.alphanumeric" /></label>
				  	<input type="text" name="alfanumerico" class="form-control" id="alfanumerico" />
			    </div>
			</div>
			<div class="col-md-4">
			    <div class="form-group">
			     	<label for="soloLetras" class="label"><spring:message code="patron.validacion.lettersonly" /></label>
				  	<input type="text" name="soloLetras" class="form-control" id="soloLetras" />
			    </div>
			</div>
		</div>
		<div class="row">
		    <div class="col-md-4">
		   		<div class="form-group">
			     	<label for="soloDigitos" class="label"><spring:message code="patron.validacion.digits" /></label>
				  	<input type="text" name="soloDigitos" class="form-control" id="soloDigitos" />
			    </div>
			</div>
			<div class="col-md-4">
			    <div class="form-group">
			     	<label for="sinEspacios" class="label"><spring:message code="patron.validacion.nowhitespace" /></label>
				  	<input type="text" name="sinEspacios" class="form-control" id="sinEspacios" />
			    </div>
			</div>
			<div class="col-md-4">
			    <div class="form-group">
			     	<label for="patron" class="label"><spring:message code="patron.validacion.pattern" /></label>
				  	<input type="text" name="patron" class="form-control" id="patron" />
			    </div>
			</div>
		</div>
		
	</fieldset>
	
	<fieldset class="alumnoFieldset">
		<legend><spring:message code="patron.validacion.validacionesFormato" /></legend>
		<div class="row">
		    <div class="col-md-4">
		   		<div class="form-group">
			     	<label for="validacionEmail" class="label"><spring:message code="patron.validacion.email" /></label>
				  	<input type="text" name="validacionEmail" class="form-control" size="40" id="validacionEmail" />
			    </div>
			</div>
			<div class="col-md-4">
			    <div class="form-group">
			     	<label for="validacionUrl" class="label"><spring:message code="patron.validacion.url" /></label>
				  	<input type="text" name="validacionUrl" class="form-control" size="40" id="validacionUrl" />
			    </div>
			</div>
			<div class="col-md-4">
			    <div class="form-group">
			     	<label for="dni" class="label"><spring:message code="patron.validacion.dni" /></label>
				  	<input type="text" name="dni" class="form-control" size="40" id="dni" />
			    </div>
			</div>
		</div>
		<div class="row">
		    <div class="col-md-4">
		   		<div class="form-group">
			     	<label for="validacionFecha" class="label"><spring:message code="patron.validacion.date" /></label>
				  	<input type="text" name="validacionFecha" class="form-control" size="40" id="validacionFecha" />
			    </div>
			</div>
			<div class="col-md-4">
			    <div class="form-group">
			     	<label for="validacionFechaISO" class="label"><spring:message code="patron.validacion.dateISO" /></label>
				  	<input type="text" name="validacionFechaISO" class="form-control" size="40" id="validacionFechaISO" />
			    </div>
			</div>
			<div class="col-md-4">
			    <div class="form-group">
			     	<label for="tarjetaCredito" class="label"><spring:message code="patron.validacion.creditcard" /></label>
				  	<input type="text" name="tarjetaCredito" class="form-control" size="40" id="tarjetaCredito" />
			    </div>
			</div>
		</div>
		<div class="row">
		    <div class="col-md-4">
		   		<div class="form-group">
		     		<label for="extension" class="label"><spring:message code="patron.validacion.accept" /></label>
			  		<input type="text" name="extension" class="form-control" size="40" id="extension" />
		    	</div>
			</div>
			<div class="col-md-4">
			    <div class="form-group">
			     	<label for="clave" class="label"><spring:message code="patron.validacion.equalTo.clave" /></label>
				  	<input type="text" name="clave" class="form-control" size="40" id="clave" /><br/>
				  	<label for="clave_confirmar" class="label"><spring:message code="patron.validacion.equalTo.confirmarClave" /></label>
				  	<input type="text" name="clave_confirmar" class="form-control" size="40" id="clave_confirmar" />
		    	</div>
			</div>
		</div>
	</fieldset>
	
	<div class="row">
		<div class="col-md-3">
			<input type="submit" class="btn btn-block btn-secondary" value="Validar"  />
		</div>
		<div class="col-md-9">
		
		</div>
	</div>
	
	</form:form>