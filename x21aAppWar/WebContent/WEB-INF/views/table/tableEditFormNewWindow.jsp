<%--  
 -- Copyright 2021 E.J.I.E., S.A.
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
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="/WEB-INF/tld/spring.tld"%>
<%@taglib prefix="form" uri="/WEB-INF/tld/spring-form.tld"%>

<!-- Formulario -->
<spring:url value="/table/${endpoint}" var="url"/>
<form:form modelAttribute="${isDouble eq true ? 'usuario2' : 'usuario'}" id="example_detail_form" action="${url}" method="${actionType}" enctype="${enctype}">
	<!-- Feedback del formulario de detalle -->
	<div id="example_detail_feedback"></div>
	<!-- Campos del formulario de detalle -->
	<c:if test="${actionType != 'POST'}">
		<form:hidden path="id" value="${pkValue.id}" id="id_detail_table" />
	</c:if>
	<div class="form-row">
		<div class="form-groupMaterial col-sm">
	    	<form:input path="nombre" id="nombre_detail_table" />
	    	<label for="nombre_detail_table"><spring:message code="nombre" /></label>
	    </div>       
	    <div class="form-groupMaterial col-sm">
	    	<form:input path="apellido1" id="apellido1_detail_table" />
	    	<label for="apellido1_detail_table"><spring:message code="apellido1" /></label>
	    </div>
	</div>
	<div class="form-row">
	    <div class="form-groupMaterial col-sm">
	    	<form:input path="apellido2" id="apellido2_detail_table" />
	    	<label for="apellido2_detail_table"><spring:message code="apellido2" /></label>
	    </div>       
	    <div class="form-groupMaterial col-sm">
	    	<form:input path="fechaBaja" id="fechaBaja_detail_table" />
	    	<label for="fechaBaja_detail_table"><spring:message code="fechaBaja" /></label>
	    </div>
	</div>
	<div class="form-row">
	    <div class="form-groupMaterial col-sm">
	    	<form:input path="fechaAlta" id="fechaAlta_detail_table" />
	    	<label for="fechaAlta_detail_table"><spring:message code="fechaAlta" /></label>
	    </div>
	    <div class="checkbox-material col-sm">
	    	<form:checkbox path="ejie" id="ejie_detail_table" value="1" />
	    	<label for="ejie_detail_table"><spring:message code="ejie" /></label>
	    </div>
	</div>
	<div class="form-row">
	    <div class="form-groupMaterial col-sm">
	    	<form:select path="rol" id="rol_detail_table" items="${comboRol}" />
	    	<label for="rol_detail_table"><spring:message code="rol" /></label>
	    </div>
	</div>
	
	<!-- Botonera del formulario -->
    <div id="example_detail_buttonSet" class="text-right">
    	<!-- Botón de limpiar -->
        <button id="closeEditFormWindow" type="button" class="btn-material btn-material-primary-low-emphasis mr-2">
        	<i class="mdi mdi-exit-to-app"></i>
        	<span>
				Cerrar formulario
			</span>
        </button>
        <!-- Botón de guardado -->
        <button id="sendEntity" type="submit" class="btn-material btn-material-primary-high-emphasis">
        	<i class="mdi mdi-content-save"></i>
        	<span>
				Enviar entidad
			</span>        	
        </button>
    </div>
</form:form>

<div id="closeEditFormWindowDialog" style="display: none"></div>
