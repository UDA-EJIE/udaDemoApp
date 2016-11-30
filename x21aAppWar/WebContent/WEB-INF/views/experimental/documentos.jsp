<%@include file="/WEB-INF/includeTemplate.inc"%>
<h2>Y52B Documentos</h2>

<!-- Boton que realiza el ejemplo 1 de apertura del dialogo -->
<button id="btnDocumentosIFrame1">Pantalla documentos iFrame 1</button>
<!-- Boton que realiza el ejemplo 2 de apertura del dialogo -->
<button id="btnDocumentosIFrame2">Pantalla documentos iFrame 2</button>
<!-- Div del diálogo que contiene el iframe -->
<div id="divDocumentosIFrame">
	<!-- Iframe que va a mostrar la pantalla de documentos -->
	<iframe id="iframeDocumentos" width="100%" height="100%" frameborder=0 scrolling="no"></iframe>
</div>