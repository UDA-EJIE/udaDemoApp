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
$(function() {
	
//	jQuery("#ajaxRupPifUpload").on("click",function(){
//		jQuery.rup_ajax
//	});
	
//	var url, urlParams, obj, n38UidSesionCookie = $.rup_utils.readCookie("n38UidSesion"), n38DominioUidCookie = $.rup_utils.readCookie("n38DominioUid"),n38UidSesionGlobal = $.rup_utils.readCookie("n38UidSesionGlobal"),n38UidSistemasXLNetS = $.rup_utils.readCookie("n38UidSistemasXLNetS") ;
	
//	url= "http://svc.intra.integracion.jakina.ejiedes.net/y31ApiJSWAR/Y31JanoServicePutServlet/"+n38UidSesionCookie+"/"+n38DominioUidCookie+"/"+n38UidSesionGlobal +"/"+n38UidSistemasXLNetS+"?hadoop_folder_path=/x21a&hadoop_preserve_name=false&y31_ttl=60";
	
	
//	// Upload simple
//	$('#fileuploadPif_only').rup_upload({
////		url:url,
////		url:"http://svc.integracion.jakina.ejiedes.net/y31ApiJSWAR/Y31JanoServicePutServlet",
//		url:'http://local.ejiedes.net:7001/x21aPilotoPatronesWar/upload',
//		fileInput: $("#file_only"),
//		forceIframeTransport: true
//	});
//	
	
	// Upload simple
//	$('#fileuploadPif_only').rup_upload({
//		fileInput: $("#file_only"),
//		forceIframeTransport: true,
//		pif:{
//			base_url:"http://svc.intra.integracion.jakina.ejiedes.net",
//			folderPath: "/x21a",
//			fileTtl: 60,
//			preserveName:true
//		}
////		done: function (e, data) {
////		    // data.result
////		    // data.textStatus;
////		    // data.jqXHR;
////			console.log("done");
////		},
////		fail: function (e, data) {
////		    // data.result
////		    // data.textStatus;
////		    // data.jqXHR;
////			console.log("fail");
////		}
//	});
//	
//	function progressBar(){}
	
//	$("#rupPifUploadForm").attr("action",url);
//	$("#rupPifUploadForm").rup_form({
//		crossDomain:true
////		dataType:'jsonp',
////		xhr : function() {
////			console.log("mierda");
////          var myXhr = $.ajaxSettings.xhr();
////          if(myXhr.upload){
////        	  myXhr.upload.addEventListener('progress', progressBar, false);
////          }
////          return myXhr;
////		}
//	});
	
	
//	$("#hez").on("click", function(){
//		var createCORSRequest = function(method, url) {
//			  var xhr = new XMLHttpRequest();
//			  if ("withCredentials" in xhr) {
//			    // Most browsers.
//			    xhr.open(method, url, true);
//			  } else if (typeof XDomainRequest != "undefined") {
//			    // IE8 & IE9
//			    xhr = new XDomainRequest();
//			    xhr.open(method, url);
//			  } else {
//			    console.log("CORS not supported");
//			    xhr = null;
//			  }
//			  return xhr;
//			};
//	
//			var url = 'http://svc.intra.integracion.jakina.ejiedes.net/y31ApiJSWAR/Y31JanoServicePutServlet/1387401406793/D0_Intranet_01_dhcp/desarrollo-produccion-1387401406793/desarrollo-produccion?hadoop_folder_path=/x21a&hadoop_preserve_name=false&y31_ttl=60';
//			var method = 'POST';
//			var xhr = createCORSRequest(method, url);
//	
//			xhr.onload = function() {
//				console.log("onload");
//			};
//	
//			xhr.onerror = function() {
//				console.log("onerror");
//			};
//	
//			xhr.withCredentials = true;
//			xhr.send();
//	});
	
	
});	
	