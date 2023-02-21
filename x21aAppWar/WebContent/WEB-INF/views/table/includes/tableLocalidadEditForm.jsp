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
<%@taglib prefix="form" uri="/WEB-INF/tld/x38-form.tld"%>

<!-- Formulario -->
<spring:url value="../tableLocalidad/${endpoint}" var="url"/>
<form:form modelAttribute="localidad" id="localidad_detail_form" action="${url}" method="${actionType}" enctype="${enctype}">
	<!-- Feedback del formulario de detalle -->
	<div id="localidad_detail_feedback"></div>
	<!-- Campos del formulario de detalle -->
	<c:if test="${not empty pkValue}">
		<form:hidden path="comarca.code" value="${pkValue.id}" id="localidad_detail_masterPK" />
	</c:if>
	<div class="form-row"> 
		<div class="form-groupMaterial col-sm">
	    	<form:input path="descEs" id="descEs_detail_tableLocalidad" />
	    	<label for="descEs_detail_tableLocalidad">descEs</label>
	    </div>       
	    <div class="form-groupMaterial col-sm">
	    	<form:input path="descEu" id="descEu_detail_tableLocalidad" />
	    	<label for="descEu_detail_tableLocalidad">descEu</label>
	    </div> 
	</div>
	<div class="form-row">
	    <div class="form-groupMaterial col-sm">
	    	<form:input path="css" id="css_detail_tableLocalidad" />
	    	<label for="css_detail_tableLocalidad">css</label>
	    </div>
		<div class="form-groupMaterial col-sm d-none">
	    	<form:input path="comarca.css" id="comarcaCss_detail_tableLocalidad" />
	    	<label for="comarcaCss_detail_tableLocalidad">comarca.css</label>
	    </div>
	</div>
	<div class="form-row">  
	    <div class="form-groupMaterial col-sm d-none">
	    	<form:input path="comarca.descEs" id="comarcaDescEs_detail_tableLocalidad" />
	    	<label for="comarcaDescEs_detail_tableLocalidad">comarca.descEs</label>
	    </div>
	    <div class="form-groupMaterial col-sm d-none">
	    	<form:input path="comarca.descEu" id="comarcaDescEu_detail_tableLocalidad" />
	    	<label for="comarcaDescEu_detail_tableLocalidad">comarca.descEu</label>
	    </div>
	</div>	
</form:form>
