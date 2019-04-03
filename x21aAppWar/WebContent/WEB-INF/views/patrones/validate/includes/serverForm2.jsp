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

<form:form id="formServidor2" action='${pageContext.request.contextPath}patrones/validacion/servidor2' modelAttribute="alumno" >
				
	<fieldset class="alumnoFieldset">
		<legend><spring:message code="datosPersonales" /></legend>
		
		<span><spring:message code="patron.validacion.servidor.texto" /></span><br/><br/>
		<ul style="padding: 0 3em;">
			<li><spring:message code="patron.validacion.servidor.ejemplo2.texto1" /></li>
			<li><spring:message code="patron.validacion.servidor.ejemplo2.texto2" /></li>
			<li><spring:message code="patron.validacion.servidor.ejemplo2.texto3" /></li>
		</ul>
		
		<div class="two-col" >
		    <div class="col1">
		       <label for="usuario" class="label"><spring:message code="usuario" /></label>
				<input type="text" name="usuario" class="formulario_linea_input" size="20" id="usuario" />
		    </div>
		
		    <div class="col1">
		        <label for="password" class="label"><spring:message code="password" /></label>
				<input type="text" name="password" class="formulario_linea_input" size="30" id="password" />
		    </div>
		</div>
		</fieldset>
		<input type="submit" value="Validar formulario"  />
</form:form>