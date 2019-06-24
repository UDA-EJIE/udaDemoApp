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
		<!-- <form id="formRequiredRules"> -->
		<form:form  id="formRequiredRules" modelAttribute="alumno" method="get">
			<div class="row">
				<div class="col-md-4">
					<div class="form-group">
				     	<label for="campoObligatorio1" class="label"><spring:message code="campoObligatorio" /></label>
					  	<!-- <input type="text" name="campoObligatorio1" class="form-control" id="campoObligatorio1" /> -->
					  	<form:input path="randomData[campoObligatorio1]" class="form-control" id="campoObligatorio1"/>
				    </div>
				</div>
				<div class="col-md-4">
				    <div class="form-group">
			     		<input type="checkbox" name="esObligatorio" id="esObligatorio" style="float:left;"/>
				     	<label for="esObligatorio" class="label">
				     		&nbsp;<spring:message code="patron.validacion.obligatorioSi" />
			     		</label>
					  	<!-- <input type="text" name="campoObligatorio2" class="form-control" id="campoObligatorio2" /> -->
					  	<form:input path="randomData[campoObligatorio2]" class="form-control" id="campoObligatorio2"/>
				    </div>
				</div>
				<div class="col-md-4">
				    <div class="form-group">
				     	<select id="diaObligatorio" style="float: left">
				     	</select>
				     	<label for="campoObligatorio3" class="label">
					     	&nbsp;<spring:message code="patron.validacion.obligatorioSiCombo" />
				     	</label>
					  	<!-- <input type="text" name="campoObligatorio3" class="form-control" id="campoObligatorio3" /> -->
					  	<form:input path="randomData[campoObligatorio3]" class="form-control" id="campoObligatorio3"/>
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
		<!-- <form id="formNumeric"> -->
		<form:form  id="formNumeric" modelAttribute="alumno" method="get">
			<div class="row">
				<div class="col-md-4">
			   		<div class="form-group">
				     	<label for="valorMinimo" class="label"><spring:message code="patron.validacion.min" /></label>
					  	<!-- <input type="text" name="valorMinimo" class="form-control" id="valorMinimo" /> -->
					  	<form:input path="randomData[valorMinimo]" class="form-control" id="valorMinimo"/>
				    </div>
				</div>
				<div class="col-md-4">
				    <div class="form-group">
				     	<label for="valorMaximo" class="label"><spring:message code="patron.validacion.max" /></label>
					  	<!-- <input type="text" name="valorMaximo" class="form-control" id="valorMaximo" /> -->
					  	<form:input path="randomData[valorMaximo]" class="form-control" id="valorMaximo"/>
				    </div>
				</div>
				<div class="col-md-4">
				    <div class="form-group">
				     	<label for="valorIntervalo" class="label"><spring:message code="patron.validacion.range" /></label>
					  	<!-- <input type="text" name="valorIntervalo" class="form-control" id="valorIntervalo" /> -->
					  	<form:input path="randomData[valorIntervalo]" class="form-control" id="valorIntervalo"/>
				    </div>
				</div>
			 </div>
			 <div class="row">
			    <div class="col-md-4">
			   		<div class="form-group">
				     	<label for="entero" class="label"><spring:message code="patron.validacion.integer" /></label>
					  	<!-- <input type="text" name="entero" class="form-control" id="entero" /> -->
					  	<form:input path="randomData[entero]" class="form-control" id="entero"/>
				    </div>
			    </div>
				<div class="col-md-4">
				    <div class="form-group">
				     	<label for="numeroDecimal" class="label"><spring:message code="patron.validacion.number" /></label>
					  	<!-- <input type="text" name="numeroDecimal" class="form-control" id="numeroDecimal" /> -->
					  	<form:input path="randomData[numeroDecimal]" class="form-control" id="numeroDecimal"/>
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
		<!-- <form id="formText"> -->
		<form:form id="formText" modelAttribute="alumno" method="get">
			<div class="row">
				<div class="col-md-4">
			   		<div class="form-group">
				     	<label for="longitudMinima" class="label"><spring:message code="patron.validacion.minlength" /></label>
					  	<!-- <input type="text" name="longitudMinima" class="form-control" id="longitudMinima" /> -->
					  	<form:input path="randomData[longitudMinima]" class="form-control" id="longitudMinima" />
				    </div>
				</div>
				<div class="col-md-4">
				    <div class="form-group">
				     	<label for="longitudMaxima" class="label"><spring:message code="patron.validacion.maxlength" /></label>
					  	<!-- <input type="text" name="longitudMaxima" class="form-control" id="longitudMaxima" /> -->
					  	<form:input path="randomData[longitudMaxima]" class="form-control" id="longitudMaxima" />
				    </div>
				</div>
				<div class="col-md-4">
				    <div class="form-group">
				     	<label for="longitudIntervalo" class="label"><spring:message code="patron.validacion.rangelength" /></label>
					  	<!-- <input type="text" name="longitudIntervalo" class="form-control" id="longitudIntervalo" /> -->
					  	<form:input path="randomData[longitudIntervalo]" class="form-control" id="longitudIntervalo" />
				    </div>
				</div>
			</div>
			<div class="row">
			    <div class="col-md-4">
			   		<div class="form-group">
				     	<label for="palabrasMaximo" class="label"><spring:message code="patron.validacion.maxWords" /></label>
					  	<!-- <input type="text" name="palabrasMaximo" class="form-control" id="palabrasMaximo" /> -->
					  	<form:input path="randomData[palabrasMaximo]" class="form-control" id="palabrasMaximo" />
			    	</div>
				</div>
				<div class="col-md-4">
				    <div class="form-group">
				     	<label for="palabrasMinimo" class="label"><spring:message code="patron.validacion.minWords" /></label>
					  	<!-- <input type="text" name="palabrasMinimo" class="form-control" id="palabrasMinimo" /> -->
					  	<form:input path="randomData[palabrasMinimo]" class="form-control" id="palabrasMinimo" />
				    </div>
				</div>
				<div class="col-md-4">
				    <div class="form-group">
				     	<label for="palabrasIntervalo" class="label"><spring:message code="patron.validacion.number" /></label>
					  	<!-- <input type="text" name="palabrasIntervalo" class="form-control" id="palabrasIntervalo" /> -->
					  	<form:input path="randomData[palabrasIntervalo]" class="form-control" id="palabrasIntervalo" />
			   		</div>
				</div>
			</div>
			<div class="row">
			    <div class="col-md-4">
			   		<div class="form-group">
				     	<label for="letrasYPuntuacion" class="label"><spring:message code="patron.validacion.letterswithbasicpunc" /></label>
					  	<!-- <input type="text" name="letrasYPuntuacion" class="form-control" id="letrasYPuntuacion" /> -->
					  	<form:input path="randomData[letrasYPuntuacion]" class="form-control" id="letrasYPuntuacion" />
			    	</div>
				</div>
				<div class="col-md-4">
				    <div class="form-group">
				     	<label for="alfanumerico" class="label"><spring:message code="patron.validacion.alphanumeric" /></label>
					  	<!-- <input type="text" name="alfanumerico" class="form-control" id="alfanumerico" /> -->
					  	<form:input path="randomData[alfanumerico]" class="form-control" id="alfanumerico" />
				    </div>
				</div>
				<div class="col-md-4">
				    <div class="form-group">
				     	<label for="soloLetras" class="label"><spring:message code="patron.validacion.lettersonly" /></label>
					  	<!-- <input type="text" name="soloLetras" class="form-control" id="soloLetras" /> -->
					  	<form:input path="randomData[soloLetras]" class="form-control" id="soloLetras" />
				    </div>
				</div>
			</div>
			<div class="row">
			    <div class="col-md-4">
			   		<div class="form-group">
				     	<label for="soloDigitos" class="label"><spring:message code="patron.validacion.digits" /></label>
					  	<!-- <input type="text" name="soloDigitos" class="form-control" id="soloDigitos" /> -->
					  	<form:input path="randomData[soloDigitos]" class="form-control" id="soloDigitos" />
				    </div>
				</div>
				<div class="col-md-4">
				    <div class="form-group">
				     	<label for="sinEspacios" class="label"><spring:message code="patron.validacion.nowhitespace" /></label>
					  	<!-- <input type="text" name="sinEspacios" class="form-control" id="sinEspacios" /> -->
					  	<form:input path="randomData[sinEspacios]" class="form-control" id="sinEspacios" />
				    </div>
				</div>
				<div class="col-md-4">
				    <div class="form-group">
				     	<label for="patron" class="label"><spring:message code="patron.validacion.pattern" /></label>
					  	<!-- <input type="text" name="patron" class="form-control" id="patron" /> -->
					  	<form:input path="randomData[patron]" class="form-control" id="patron" />
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
