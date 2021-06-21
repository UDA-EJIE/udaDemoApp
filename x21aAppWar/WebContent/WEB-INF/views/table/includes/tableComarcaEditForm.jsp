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
<c:set value="${actionType == 'POST' ? 'add': 'edit'}" var="endpoint" />
<spring:url value="../tableComarca/${endpoint}" var="url"/>
<form:form modelAttribute="comarca" id="comarca_detail_form" action="${url}" method="${actionType}">
	<!-- Feedback del formulario de detalle -->
	<div id="comarca_detail_feedback"></div>
	<!-- Campos del formulario de detalle -->
	<div class="form-row">
	    <div class="form-groupMaterial col-sm">
	    	<form:input path="descEs" id="descEs_detailForm_tableComarca" />
	    	<label for="descEs_detailForm_tableComarca">descEs</label>
	    </div>       
	    <div class="form-groupMaterial col-sm">
	    	<form:input path="descEu" id="descEu_detailForm_tableComarca" />
	    	<label for="descEu_detailForm_tableComarca">descEu</label>
	    </div>
	</div>
	<div class="form-row">			    
	    <div class="form-groupMaterial col-sm">
	    	<form:input path="css" id="css_detailForm_tableComarca" />
	    	<label for="css_detailForm_tableComarca">css</label>
	    </div>
	</div>
	<div class="form-row">
	    <div class="form-groupMaterial col-sm">
	    	<form:input path="provincia.code" id="provinciaCode_detailForm_table" />
	    	<label for="provinciaCode_detailForm_table">Provincia</label>
	    </div>
	</div>	
</form:form>
