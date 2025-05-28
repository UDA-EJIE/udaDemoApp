<%@include file="/WEB-INF/includeTemplate.inc"%>
<div class="dropdown-menu" aria-labelledby="responsiveNavbarDropdown" id="responsiveNavbarDropdown">

	<spring:url value="/areas/maint" var="areasMaint" htmlEscape="true"/>
	<a class="dropdown-item" href="${areasMaint}">
		<spring:message code="areasMaint" />
	</a>
	<spring:url value="/aplicacion/maint" var="aplicacionMaint" htmlEscape="true"/>
	<a class="dropdown-item" href="${aplicacionMaint}">
		<spring:message code="aplicacionMaint" />
	</a>
	<spring:url value="/comarca/maint" var="comarcaMaint" htmlEscape="true"/>
	<a class="dropdown-item" href="${comarcaMaint}">
		<spring:message code="comarcaMaint" />
	</a>
</div>