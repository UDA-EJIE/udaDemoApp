/*!
 * Copyright 2012 E.J.I.E., S.A.
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
	
	jQuery('#webdavEdit').bind("click", function() {
		
		jQuery.rup_dav.editOnline({
			url:"webdavServlet/webdav.doc",
			xlnetsAuth:true,
			downloadOnError:true,
			alternateDownloadURL:"../upload?fileName=webdav.doc"
		});
		
//		var url, urlParams, obj, n38UidSesionCookie = $.rup_utils.readCookie("n38UidSesion"), n38DominioUidCookie = $.rup_utils.readCookie("n38DominioUid");
//		
//		urlParams = {
//				n38UidSesion: $.rup_utils.readCookie("n38UidSesion"),
//				n38DominioUid: $.rup_utils.readCookie("n38DominioUid")
//		};
//		
//		url = location.protocol + "//" + location.host+CTX_PATH+"webdavServlet/webdav.doc";
//		
//		if (n38UidSesionCookie !== undefined && n38UidSesionCookie !=='' && n38DominioUidCookie !== undefined && n38DominioUidCookie !==''){
//			url += "?c="+jQuery.rup_utils.base64.encode(jQuery.param(urlParams));
//		}
//		
//		
//		if ($.browser.msie === true){
//			// Gestión para Internet Explorer
//			try{
//				// Microsoft Office 2007 y superiores
//				obj = new ActiveXObject("SharePoint.OpenDocuments.3");
//				obj.EditDocument(encodeURI(url));
//			}catch(e){
//				try{
//					// Microsoft Office 2003 e inferiores
//					obj = new ActiveXObject('Word.Application');
//					obj.Visible = true;
//					obj.Documents.Open(encodeURI(url));
//				}catch(e){
//					// No se puede editar en línea, se descarga el archivo
//					window.open(encodeURI("../upload?fileName=webdav.doc"), '_blank');
//				}
//			}
//		} else {
//			// Gestión para Firefox y Chrome
//			try{
//				// Microsoft Office 2007 y superiores
//				hownowPlugin = document.getElementById("winFirefoxPlugin");
//			    version = hownowPlugin.GetOfficeVersion();
//			    hownowPlugin.EditDocument(url, version);
//			}catch(e){
//				// No se puede editar en línea, se descarga el archivo
//				window.open(encodeURI("../upload?fileName=webdav.doc"), '_blank');
//			}
//		} 
	});
	
});	
	