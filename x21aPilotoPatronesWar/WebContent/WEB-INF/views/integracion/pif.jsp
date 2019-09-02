<%--  
 -- Copyright 2012 E.J.I.E., S.A.
 --
 -- Licencia con arreglo a la EUPL, Versión 1.1 exclusivamente (la «Licencia»);
 -- Solo podrá usarse esta obra si se respeta la Licencia.
 -- Puede obtenerse una copia de la Licencia en
 --
 --      http://ec.europa.eu/idabc/eupl.html
 --
 -- Salvo cuando lo exija la legislación aplicable o se acuerde por escrito, 
 -- el programa distribuido con arreglo a la Licencia se distribuye «TAL CUAL»,
 -- SIN GARANTÍAS NI CONDICIONES DE NINGÚN TIPO, ni expresas ni implícitas.
 -- Véase la Licencia en el idioma concreto que rige los permisos y limitaciones
 -- que establece la Licencia.
 --%>
<%@include file="/WEB-INF/includeTemplate.inc"%>
<h2>PIF</h2>
	<link href="http://svc.integracion.jakina.ejiedes.net/trasegador/css/swfdefault.css" rel="stylesheet" type="text/css" ></link>

	<script type="text/javascript" src="http://svc.integracion.jakina.ejiedes.net/trasegador/js/prototype.js"></script>
	<script type="text/javascript" src="http://svc.integracion.jakina.ejiedes.net/trasegador/js/y31-jano-upload-widget-generic-swfobjects.js"></script>
	<script type="text/javascript" src="http://svc.integracion.jakina.ejiedes.net/trasegador/js/y31-jano-upload-widget-generic-swfupload.js"></script>
	<script type="text/javascript" src="http://svc.integracion.jakina.ejiedes.net/trasegador/js/y31-jano-upload-widget-generic-swfupload.queue.js"></script>
	<script type="text/javascript" src="http://svc.integracion.jakina.ejiedes.net/trasegador/js/y31-jano-upload-widget-minimal-fileprogress.js"></script>
	<script type="text/javascript" src="http://svc.integracion.jakina.ejiedes.net/trasegador/js/y31-jano-upload-widget-generic-handlers.js"></script>
	<script type="text/javascript" src="http://svc.integracion.jakina.ejiedes.net/trasegador/js/y31-jano-upload-widget.js"></script>

	<script type="text/javascript">
		var filesCount = 0;
	
		function inicio() {
			var settings = {
					base_url : "http://svc.integracion.jakina.ejiedes.net",
					folderPath: "/x21a",
					custom_settings : {
						language: "es",
						progressTarget : "fsUploadProgress" 
					},
					//informacion relativa al area y nodo en el que se va poner el swf 
					button_placeholder_id : "spanButtonPlaceholder",
					button_width: 75,
					button_height: 22,
					fileTtl: 60,
					file_size_limit: "500 MB",
					file_types: "*.doc",
					file_types_description: "selec. fichs.",
					upload_success_handler : uploadSuccess
			};
	
			var widget = new JANO.Uploader(settings).create();
		}
	
		function uploadSuccess(file, serverData) {
		alert ('success');
			var progress = new FileProgress(file, this.customSettings.progressTarget);
			progress.setComplete();
			progress.setStatus("Completado");
			progress.toggleCancel(false);
			var ooo = serverData.evalJSON(true);
			filesCount = filesCount + 1;
			
			// se crea un campo hidden con 
			var currentFile = document.createElement("input");
			currentFile.setAttribute("type", "hidden");
			currentFile.setAttribute("id", "oidTrasegador");
			currentFile.setAttribute("name", "oidTrasegador");
			currentFile.setAttribute("value", ooo.filePath);
			document.getElementById("fmFileUpload").appendChild(currentFile);
			
			alert("subido un fichero. El callback almacena un campo hidden con la ruta en el trasegador e incrementa en 1 el numero de ficheros subidos: "+ooo.filePath);
		}
	</script>		
</head>
<body onload="inicio()">
<form:form id="fmFileUpload" name="fmFileUpload" action="PDTE" method="post">
	<div id="divSWFUploadUI">
		<br/>
		&nbsp;&nbsp;&nbsp;&nbsp;Ejemplo de Uso de Subida Asíncrona
		<div style="width: 70%">
			<div style="float: left; width: 33%">
				&nbsp;&nbsp;&nbsp;&nbsp;
				<span id="spanButtonPlaceholder"></span>
				<input id="btnUpload" type="button" value="Selección Ficheros" style="width: 100px; height: 22px; font-size: 8pt;" />
				<span> subir a /r02</span>
			</div>
			<div class="fieldset flash" id="fsUploadProgress" style="float: right; height: 5%; width: 65%;">
			</div>
			<br style="clear: both;" />
		</div>
		<br style="clear: both;" />
	</div>
	<!-- Divs informativas del estado del componente de upload -->
	<div id="divLoadingContent" class="content" style="background-color: #FFFF66; border-top: solid 4px #FF9966; border-bottom: solid 4px #FF9966; margin: 10px 25px; padding: 10px 15px; display: none;">
		SWFUpload is loading. Please wait a moment...
	</div>
	<div id="divLongLoading" class="content" style="background-color: #FFFF66; border-top: solid 4px #FF9966; border-bottom: solid 4px #FF9966; margin: 10px 25px; padding: 10px 15px; display: none;">
		SWFUpload is taking a long time to load or the load has failed.  Please make sure that the Flash Plugin is enabled and that a working version of the Adobe Flash Player is installed.
	</div>
	<spring:url value="http://www.adobe.com/shockwave/download/download.cgi?P1_Prod_Version=ShockwaveFlash" var="urlAdobeFlash" htmlEscape="true"/>
	<div id="divAlternateContent" class="content" style="background-color: #FFFF66; border-top: solid 4px #FF9966; border-bottom: solid 4px #FF9966; margin: 10px 25px; padding: 10px 15px; display: none;">
		We're sorry.  SWFUpload could not load.  You may need to install or upgrade Flash Player.
		Visit the <a href="${urlAdobeFlash}">Adobe website</a> to get the Flash Player.
	</div>
</form:form>





