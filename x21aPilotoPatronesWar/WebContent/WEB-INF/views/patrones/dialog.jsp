<%@include file="/WEB-INF/includeTemplate.inc"%>
<h1><spring:message  code="patronDialogTitle" /></h1>		
<div id="idDialog" style="display:none"><spring:message code="divDialogContent" /></div>
<div id="pruebas">
	<button id="btnDialog"><spring:message code="dialogNormal" /></button>
	<button id="btnAjaxDialogWAR"><spring:message code="dialogAjaxWAR" /></button>
	<button id="btnAjaxDialogStatics"><spring:message code="dialogAjaxStatics" /></button>
	<button id="btnTextDialog"><spring:message  code="dialogText" /></button>
</div>
