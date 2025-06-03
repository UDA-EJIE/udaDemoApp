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
 -- SIN GARANTÍAS NI CONDICIONES DE NINGÚN TIPO, ni expresas ni implícitas.
 -- Véase la Licencia en el idioma concreto que rige los permisos y limitaciones
 -- que establece la Licencia.
 --%>
 
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@include file="/WEB-INF/includeTemplate.inc"%>	

<section>
	<h2>
		Componentes RUP 
	</h2>
	<p>
		Se presentan reglas de validación aplicadas a los componentes RUP. 
	</p>
	
	<h3>Fecha</h3>
	
	<div class="example">
		<div id="feedbackDate"></div>
		<form:form modelAttribute="randomForm" id="formDate">
			<div class="row">
				<div class="col-md-4">
					<div class="mb-3">
				     	<label for="dateField" class="label"><spring:message code="campoObligatorio" /></label>
					  	<form:input path="dateField" class="form-control" id="dateField" />
				    </div>
				</div>
				<div class="col-md-4">
				    
				</div>
				<div class="col-md-4">
				   
				</div>
			</div>
			<div class="row justify-content-center">
				<div class="col-md-3">
					<input type="submit" class="btn btn-block btn-secondary" value="Validar"  />
				</div>
			</div>
		</form:form>
	</div>
	
	<div class="example">
		<div id="feedbackAutocomplete"></div>
		
		<form:form modelAttribute="randomForm" id="formAutocomplete">
			<div class="row">
				<div class="col-md-4">
					<div class="mb-3">
				     	<label for="autocompleteField" class="label"><spring:message code="campoObligatorio" /></label>
					  	<form:input path="autocompleteField" class="form-control" id="autocompleteField" />
				    </div>
				</div>
				<div class="col-md-4">
				    
				</div>
				<div class="col-md-4">
				   
				</div>
			</div>
			<div class="row justify-content-center">
				<div class="col-md-3">
					<input type="submit" class="btn btn-block btn-secondary" value="Validar"  />
				</div>
			</div>
		</form:form>
	
	</div>
	
</section>