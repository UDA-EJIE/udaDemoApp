/*!
 * Copyright 2011 E.J.I.E., S.A.
 *
 * Licencia con arreglo a la EUPL, Versión 1.1 exclusivamente (la «Licencia»);
 * Solo podrá usarse esta obra si se respeta la Licencia.
 * Puede obtenerse una copia de la Licencia en
 *
 *      http://ec.europa.eu/idabc/eupl.html
 *
 * Salvo cuando lo exija la legislación aplicable o se acuerde por escrito, 
 * el programa distribuido con arreglo a la Licencia se distribuye «TAL CUAL»,
 * SIN GARANTÍAS NI CONDICIONES DE NINGÚN TIPO, ni expresas ni implícitas.
 * Véase la Licencia en el idioma concreto que rige los permisos y limitaciones
 * que establece la Licencia.
 */
jQuery(document).ready(function(){
	
	// Definicion de las pestanas
	$("#uploadTabs").rup_tabs({
		tabs : [ {
			i18nCaption : "upload",
			layer : "#fileupload_only"
		}, {
			i18nCaption : "uploadFormSimple",
			layer : "#fileupload_form"
		}, {
			i18nCaption : "uploadFormMultiple",
			layer : "#fileupload_form_multiple"
		}, {
			i18nCaption : "uploadFormPif",
			layer : "#fileupload_pif_form"
		} 
		]
	});
	
	// Creacion de los componentes feedback
	$("#feedback_fileupload_form").rup_feedback();
	$("#feedback_fileupload_form_multiple").rup_feedback();
	
	
	// Se utiliza jquery.form para realizar el submit de los formularios por AJAX
	$("#usuarioForm").ajaxForm(function(){
		$("#feedback_fileupload_form").rup_feedback("set","Los datos se han enviado correctamente");
	});
	 
	$("#usuarioFormSimple").ajaxForm(function(){
		$("#feedback_fileupload_form_multiple").rup_feedback("set","Los datos se han enviado correctamente");
	});
	
	// Creacion de los diferentes componentes Upload
	

	
	// Upload simple
	$('#fileupload_only').rup_upload({
		fileInput: $("#file_only"),
		maxFileSize: 5000000
//		,forceIframeTransport: true
	});
	
	 
	// Upload integrado en formulario
	$('#fileupload_form').rup_upload({
		fileInput: $("#file_form"),
		submitFormButton: $("#sendButton"),
		maxFileSize: 5000000,
		submitInForm:true
	});
	
	// Upload integrado en formulario
//	$('#fileupload_pif_form').rup_upload({
//		fileInput: $("#file_pif_form"),
//		url: "../pifServlet",
//		pif:{
//			folderPath: "/x21a",
//			fileTtl: 60,
//			preserveName:true
//		}
//	});
	
//	$('#fileupload_pif_form').rup_upload({
//		fileInput: $("#file_pif_form"),
//		url: "../pifServlet",
//		formData:{
//			base_url:"../pifServlet",
//			hadoop_folder_path: "/x21a",
//			y31_ttl: 60,
//			hadoop_preserve_name:true,
//			securityToken:"user"
//		}
//	});
//	
	
	$('#fileupload_pif_form').rup_upload({
		fileInput: $("#file_pif_form"),
		url: "../pifServlet",
		pif:{
			folderPath: "/x21a",
			fileTtl: 60,
			preserveName:true
		}
	});
	
	 
	// Dos controles Upload intergrados en un mismo formulario
	$('#fileupload_file_form_padre').rup_upload({
		form:"fileupload_form_multiple",
		fileInput: $("#file_form_padre"),
		submitFormButton: $("#sendButtonMultiple"),
		maxFileSize: 5000000,
		singleFileUploads:true,
		submitInForm:true
	});
	 
	$('#fileupload_file_form_madre').rup_upload({
		form:"fileupload_form_multiple",
		fileInput: $("#file_form_madre"),
		submitFormButton: $("#sendButtonMultiple"),
		maxFileSize: 5000000,
		singleFileUploads:true,
		submitInForm:true
	});
	 
});