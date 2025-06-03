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
		Reglas de validación 
	</h2>
	<p>
		El componente implementa por defecto una serie de reglas de validación.  
	</p>
	
	<h3>Obligatoriedad</h3>
	
	<p>
		Dentro de estas reglas de validación se puede determinar que un campo debe de ser informado de manera obliagtoria por parte del usuario.  
	</p>
	<p>
		Esta regla puede ser aplicada siempre o de manera condicional.  
	</p>
	<div class="example">
		<div id="feedbackRequiredRules"></div>
		<form:form modelAttribute="randomForm" id="formRequiredRules">
			<div class="row">
				<div class="col-md-4">
					<div class="mb-3">
				     	<label for="campoObligatorio1" class="label"><spring:message code="campoObligatorio" /></label>
					  	<form:input path="campoObligatorio1" class="form-control" id="campoObligatorio1" />
				    </div>
				</div>
				<div class="col-md-4">
				    <div class="mb-3">
			     		<input type="checkbox" name="esObligatorio" id="esObligatorio" style="float:left;"/>
				     	<label for="esObligatorio" class="label">
				     		&nbsp;<spring:message code="patron.validacion.obligatorioSi" />
			     		</label>
					  	<form:input path="campoObligatorio2" class="form-control" id="campoObligatorio2" />
				    </div>
				</div>
				<div class="col-md-4">
				    <div class="mb-3">
				     	<select id="diaObligatorio" style="float: left">
				     	</select>
				     	<label for="campoObligatorio3" class="label">
					     	&nbsp;<spring:message code="patron.validacion.obligatorioSiCombo" />
				     	</label>
					  	<form:input path="campoObligatorio3" class="form-control" id="campoObligatorio3" />
				    </div>
				</div>
			</div>
			<div class="row justify-content-center">
				<div class="col-md-3">
					<input type="submit" class="btn btn-block btn-secondary" value="Validar"  />
				</div>
			</div>
		</form:form>
	
	</div>
	
	<h3><spring:message code="patron.validacion.validacionesNumericas" /></h3>
	<p>
		Las validaciones numéricas disponibles son las siguientes:
	</p>
	<ul>
		<li><code>min</code>: El valor no debe ser inferior al indicado en la regla.</li>
		<li><code>max</code>: El valor no debe ser superior al indicado en la regla.</li>
		<li><code>range</code>: El valor debe de estar dentro del rango especificado.</li>
		<li><code>integer</code>: El valor debe de ser un número entero.</li>
		<li><code>number</code>: El valor debe de ser numérico.</li>
	</ul>
	
	<div class="example">
		<div id="feedbackNumeric"></div>
		<form:form modelAttribute="randomForm" id="formNumeric">
			<div class="row">
				<div class="col-md-4">
			   		<div class="mb-3">
				     	<label for="valorMinimo" class="label"><spring:message code="patron.validacion.min" /></label>
					  	<form:input path="valorMinimo" class="form-control" id="valorMinimo" />
				    </div>
				</div>
				<div class="col-md-4">
				    <div class="mb-3">
				     	<label for="valorMaximo" class="label"><spring:message code="patron.validacion.max" /></label>
					  	<form:input path="valorMaximo" class="form-control" id="valorMaximo" />
				    </div>
				</div>
				<div class="col-md-4">
				    <div class="mb-3">
				     	<label for="valorIntervalo" class="label"><spring:message code="patron.validacion.range" /></label>
					  	<form:input path="valorIntervalo" class="form-control" id="valorIntervalo" />
				    </div>
				</div>
			 </div>
			 <div class="row">
			    <div class="col-md-4">
			   		<div class="mb-3">
				     	<label for="entero" class="label"><spring:message code="patron.validacion.integer" /></label>
					  	<form:input path="entero" class="form-control" id="entero" />
				    </div>
			    </div>
				<div class="col-md-4">
				    <div class="mb-3">
				     	<label for="numeroDecimal" class="label"><spring:message code="patron.validacion.number" /></label>
					  	<form:input path="numeroDecimal" class="form-control" id="numeroDecimal" />
			    	</div>
				</div>
				<div class="col-md-4"></div>
			   
			</div>
			<div class="row justify-content-center">
				<div class="col-md-3">
					<input type="submit" class="btn btn-block btn-secondary" value="Validar"  />
				</div>
			</div>
		</form:form>
	</div>
	
	<h3><spring:message code="patron.validacion.validacionesTexto" /></h3>
	<p>
		Las validaciones alfanuméricas disponibles son las siguientes:
	</p>
	<ul>
		<li><code>min</code>: El valor no debe ser inferior al indicado en la regla.</li>
		<li><code>max</code>: El valor no debe ser superior al indicado en la regla.</li>
		<li><code>range</code>: El valor debe de estar dentro del rango especificado.</li>
		<li><code>integer</code>: El valor debe de ser un número entero.</li>
		<li><code>number</code>: El valor debe de ser numérico.</li>
	</ul>
	
	
	<div class="example">
		<div id="feedbackText"></div>
		<form:form modelAttribute="randomForm" id="formText">
			<div class="row">
				<div class="col-md-4">
			   		<div class="mb-3">
				     	<label for="longitudMinima" class="label"><spring:message code="patron.validacion.minlength" /></label>
					  	<form:input path="longitudMinima" class="form-control" id="longitudMinima" />
				    </div>
				</div>
				<div class="col-md-4">
				    <div class="mb-3">
				     	<label for="longitudMaxima" class="label"><spring:message code="patron.validacion.maxlength" /></label>
					  	<form:input path="longitudMaxima" class="form-control" id="longitudMaxima" />
				    </div>
				</div>
				<div class="col-md-4">
				    <div class="mb-3">
				     	<label for="longitudIntervalo" class="label"><spring:message code="patron.validacion.rangelength" /></label>
					  	<form:input path="longitudIntervalo" class="form-control" id="longitudIntervalo" />
				    </div>
				</div>
			</div>
			<div class="row">
			    <div class="col-md-4">
			   		<div class="mb-3">
				     	<label for="palabrasMaximo" class="label"><spring:message code="patron.validacion.maxWords" /></label>
					  	<form:input path="palabrasMaximo" class="form-control" id="palabrasMaximo" />
			    	</div>
				</div>
				<div class="col-md-4">
				    <div class="mb-3">
				     	<label for="palabrasMinimo" class="label"><spring:message code="patron.validacion.minWords" /></label>
					  	<form:input path="palabrasMinimo" class="form-control" id="palabrasMinimo" />
				    </div>
				</div>
				<div class="col-md-4">
				    <div class="mb-3">
				     	<label for="palabrasIntervalo" class="label"><spring:message code="patron.validacion.number" /></label>
					  	<form:input path="palabrasIntervalo" class="form-control" id="palabrasIntervalo" />
			   		</div>
				</div>
			</div>
			<div class="row">
			    <div class="col-md-4">
			   		<div class="mb-3">
				     	<label for="letrasYPuntuacion" class="label"><spring:message code="patron.validacion.letterswithbasicpunc" /></label>
					  	<form:input path="letrasYPuntuacion" class="form-control" id="letrasYPuntuacion" />
			    	</div>
				</div>
				<div class="col-md-4">
				    <div class="mb-3">
				     	<label for="alfanumerico" class="label"><spring:message code="patron.validacion.alphanumeric" /></label>
					  	<form:input path="alfanumerico" class="form-control" id="alfanumerico" />
				    </div>
				</div>
				<div class="col-md-4">
				    <div class="mb-3">
				     	<label for="soloLetras" class="label"><spring:message code="patron.validacion.lettersonly" /></label>
					  	<form:input path="soloLetras" class="form-control" id="soloLetras" />
				    </div>
				</div>
			</div>
			<div class="row">
			    <div class="col-md-4">
			   		<div class="mb-3">
				     	<label for="soloDigitos" class="label"><spring:message code="patron.validacion.digits" /></label>
					  	<form:input path="soloDigitos" class="form-control" id="soloDigitos" />
				    </div>
				</div>
				<div class="col-md-4">
				    <div class="mb-3">
				     	<label for="sinEspacios" class="label"><spring:message code="patron.validacion.nowhitespace" /></label>
					  	<form:input path="sinEspacios" class="form-control" id="sinEspacios" />
				    </div>
				</div>
				<div class="col-md-4">
				    <div class="mb-3">
				     	<label for="patron" class="label"><spring:message code="patron.validacion.pattern" /></label>
					  	<form:input path="patron" class="form-control" id="patron" />
				    </div>
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
