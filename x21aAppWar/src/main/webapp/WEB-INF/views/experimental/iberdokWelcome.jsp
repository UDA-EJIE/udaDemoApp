<%@include file="/WEB-INF/includeTemplate.inc"%>
<h2 class="title mb-3">
	<spring:message code="iberdok.title" />
</h2>

<!-- TABLA DOCUMENTOS -->
<spring:url value="/iberdok/view" var="url"/>
<form:form modelAttribute="randomForm" id="iberdokTable_filter_form" action="${url}">
	        <!-- Botón de filtrado -->
	        <button id="iberdokTable_filter_filterButton" type="submit" class="btn-material btn-material-primary-high-emphasis">
	        	<i class="mdi mdi-account-circle"></i>
	        	<span>
					Login para IberDok
				</span>        	
	        </button>
</form:form>