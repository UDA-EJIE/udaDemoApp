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

<form:form id="formServidor" action='${pageContext.request.contextPath}/patrones/validacion/servidor' modelAttribute="alumno" >
				
<fieldset class="alumnoFieldset">
	<legend><spring:message code="datosPersonales" /></legend>
	
	
	<span><spring:message code="patron.validacion.servidor.texto" /></span><br/><br/>
	
	<span><i><spring:message code="nombre" /> : </i></span>
	<ul style="padding: 0 3em;">
		<li><spring:message code="patron.validacion.error.campoObligatorio" /></li>
		<li><spring:message code="patron.validacion.error.longitudMax" arguments="20" /></li>
	</ul>
	<span><i><spring:message code="apellido1" arguments="" />: </i></span>
	<ul style="padding: 0 3em;">
		<li><spring:message code="patron.validacion.error.campoObligatorio" /></li>
		<li><spring:message code="patron.validacion.error.longitudMax" arguments="30" /></li>
	</ul>
	<span><i><spring:message code="apellido2" /> : </i></span>
	<ul style="padding: 0 3em;">
		<li><spring:message code="patron.validacion.error.longitudMax" arguments="30" /></li>
	</ul>
	<br/>
	
	<div class="two-col" >
		    <div class="col1">
		       <label for="nombre" class="label"><spring:message code="nombre" /></label>
				<input type="text" name="nombre" class="formulario_linea_input" size="20" id="nombre" />
		    </div>
		
		    <div class="col1">
		        <label for="apellido1" class="label"><spring:message code="apellido1" /></label>
				<input type="text" name="apellido1" class="formulario_linea_input" size="30" id="apellido1" />
		    </div>
		    
		     <div class="col1">
		        <label for="apellido2" class="label"><spring:message code="apellido2" /></label>
				<input type="text" name="apellido2" class="formulario_linea_input" size="30" id="apellido2" />
		    </div>
	</div>
	</fieldset>
	<input type="submit" value="Validar formulario"  />
</form:form>