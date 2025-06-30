var jsonObject = {
		listaCategorizacionExpedienteInicial : []
}
var metadatosAlta = [];
var metadatosBaja = [];
var anyoExp;
var numExpExp;
var cambios = false;
/*
 * ****************************
 * FUNCIONALIDAD - INICIO
 * ****************************
 */

function guardarListCategExp(lista){
	var jsonObject = {
			listaCategExp : lista
	}
	var jsonObjectIds = {
			listaExpediente: []
	};
	 var expedientesIds = [];
	 for(var i=0;i<expedientesSeleccionados.length;i++){
		 var j = expedientesSeleccionados[i];
		 jsonObjectIds.listaExpediente.push({ 
		        "anyo" : expedientesSeleccionados[i].substr(0,expedientesSeleccionados[i].indexOf('-')),
		        "numExp"  : expedientesSeleccionados[i].substr(expedientesSeleccionados[i].indexOf('-')+1,expedientesSeleccionados[i].length)
		    });
	 }
	 var jsonMetadatosExpedientes = {
			 "listaExpedientes" : jsonObjectIds,
			 "listaMetadatos" : jsonObject
	 }
	jQuery.ajax({
		type: "POST",
		url: "/aa79bItzulnetWar/categorizacionexpediente/guardarYOEliminarMetadatos",
		dataType: "json",
		contentType: "application/json",
			data: $.toJSON(jsonMetadatosExpedientes),
		cache: false,
		success: function(data){
			if(data){
				$('#metadatosBusquedaConsulta_feedback').rup_feedback("set", $.format($.rup.i18nParse($.rup.i18n.app, "mensajes.guardadoCorrectoMetadatos"), data), "ok");
			}
			cambios = false;
		},
		error: function(){
			$('#metadatosBusquedaConsulta_feedback').rup_feedback("set", $.rup.i18n.app.mensajes.errorGuardandoDatos, "error");
		}
		
	});
}

function eliminarAllCategExp(lista){
	var jsonObject = {
			listaCategExp : lista
	}
	var jsonObjectIds = {
			listaExpediente: []
	};
	 var expedientesIds = [];
	 for(var i=0;i<expedientesSeleccionados.length;i++){
		 var j = expedientesSeleccionados[i];
		 jsonObjectIds.listaExpediente.push({ 
		        "anyo" : expedientesSeleccionados[i].substr(0,expedientesSeleccionados[i].indexOf('-')),
		        "numExp"  : expedientesSeleccionados[i].substr(expedientesSeleccionados[i].indexOf('-')+1,expedientesSeleccionados[i].length)
		    });
	 }
	 var jsonMetadatosExpedientes = {
			 "listaExpedientes" : jsonObjectIds,
			 "listaMetadatos" : jsonObject
	 }
	 if(1===expedientesSeleccionados.length){
	  jQuery.ajax({
		type: "POST",
		url: "/aa79bItzulnetWar/categorizacionexpediente/eliminarMetadatos",
		dataType: "json",
		contentType: "application/json",
			data: $.toJSON(jsonMetadatosExpedientes),
		cache: false,
		success: function(data){
			jsonObject.listaCategorizacionExpedienteInicial = [];
			$('#metadatosBusquedaConsulta_feedback').rup_feedback("set", $.rup.i18n.app.mensajes.guardadoCorrecto, "ok");
			cambios = false;
		},
		error: function(){
			$('#metadatosBusquedaConsulta_feedback').rup_feedback("set", $.rup.i18n.app.mensajes.errorGuardandoDatos, "error");
		}
	});
	 }else{
		 jsonObject.listaCategorizacionExpedienteInicial = [];
		$('#metadatosBusquedaConsulta_feedback').rup_feedback("set", $.rup.i18n.app.mensajes.guardadoCorrecto, "ok");
		cambios = false;
	 }
}

function limpiarMetadatosList(){
//	if(jsonObject!=null && jsonObject.listaCategorizacionExpedienteInicial!= null && jsonObject.listaCategorizacionExpedienteInicial.length>0){
//		for(var i=0;i<jsonObject.listaCategorizacionExpedienteInicial.length;i++){
//			$("#desc_metadato_"+jsonObject.listaCategorizacionExpedienteInicial[i].idMetadato).css('background-color', '#ececec');
//		}
//	}
	jsonObject.listaCategorizacionExpedienteInicial = [];
}

function cargarMetadatosExpedienteConsulta(){
	//obtener metadatos relacionados
	anyoExp = expedientesSeleccionados[0].substr(0,expedientesSeleccionados[0].indexOf('-'));
	numExpExp = expedientesSeleccionados[0].substr(expedientesSeleccionados[0].indexOf('-')+1,expedientesSeleccionados[0].length)
	limpiarMetadatosList();
	var listaMetadatosBaja = [];
	jQuery.ajax({
		type: "GET",
		url: "/aa79bItzulnetWar/categorizacionexpediente/getMetadatosIds/"+anyoExp+"/"+numExpExp,
		dataType: "json",
		contentType: "application/json",
		cache: false,
		success: function(data){
			if(data!=null){
				cargarEtiquetasSeleccionadas(data.listaCategExp);
				dibujarEtiquetas(jsonObject.listaCategorizacionExpedienteInicial);
			}	
//			if(data!=null && data.listaCategExp!= null && data.listaCategExp.length>0){
//				for(var i=0;i<data.listaCategExp.length;i++){
//					jsonObject.listaCategorizacionExpedienteInicial.push(data.listaCategExp[i]);
//					var d = $('metadato_div_'+data.listaCategExp[i].idMetadato);
//					if(d!=null){
//						if($("#desc_metadato_"+data.listaCategExp[i].idMetadato).val()!=null){
//							if($("#metadato_div_"+data.listaCategExp[i].idMetadato)[0].classList.contains("metadatoBaja")){
//								$("#metadato_div_"+data.listaCategExp[i].idMetadato)[0].classList.remove("oculto");
//								$("#desc_metadato_"+data.listaCategExp[i].idMetadato).css('background-color', '#F0ABBF');
//							}else{
//								$("#desc_metadato_"+data.listaCategExp[i].idMetadato).css('background-color', '#7BD1F3');
//							}
//						}
//					}
//				}
////				ocultar si hay de baja no seleccionados
//				for(var i = 0; i<metadatosBaja.length; i++){
//					if(!estaMetadatoEnLista(jsonObject.listaCategorizacionExpedienteInicial, metadatosBaja[i])){
//						if($("#metadato_div_"+metadatosBaja[i].id)[0].classList.contains("metadatoBaja")&&
//								!$("#metadato_div_"+metadatosBaja[i].id)[0].classList.contains("oculto")){
//							$("#metadato_div_"+metadatosBaja[i].id)[0].className += " oculto ";
//						}
//					}
//				}
//			}else{
//				for(var i = 0; i<metadatosBaja.length; i++){
//					if($("#metadato_div_"+metadatosBaja[i].id)[0].classList.contains("metadatoBaja")&&
//							!$("#metadato_div_"+metadatosBaja[i].id)[0].classList.contains("oculto")){
//						$("#metadato_div_"+metadatosBaja[i].id)[0].className += " oculto ";
//					}
//				}
//			}
		},
		error: function(){
			
		}
		
	});
}

//function cargarMetadatosBusquedaConsulta(){
//	jQuery.ajax({
//		type: "POST",
//		url:"/aa79bItzulnetWar/categorizacionexpediente/getAllMetadatos",
//		dataType: "json",
//		contentType: 'application/json',
//		cache: false,
//		success: function(data){
//	    	if(data!=null && data.length>0){
//	    		for(var i=0;i<data.length>0;i++){
//	    			if("A".indexOf(data[i].estado)==0){
//	    				metadatosAlta.push(data[i]);
//	    			}else{
//	    				metadatosBaja.push(data[i]);
//	    			}
//	    		}
//	    		
//	    		var html = "<div class='aa79b-content'>";
//    			html+="<section>";
//				html+="<div class='row'>";
//				//mostrar metadatos de alta
//				for(var i = 0; i<metadatosAlta.length; i++){
//					var metadato = metadatosAlta[i];
//					html+="<div id='metadato_div_"+metadato.id+"' data-id='"+metadato.id +"' class='col-md-4 '>";
//    					html+="<p id='desc_metadato_"+metadato.id+"' class='metadatoButton'>"+metadato.descEu+"</p>";
//    				html+="</div>";
//				}
//	    		
//		    	//situar metadatos de baja por si hay que mostrarlos
//    			for(var j = 0; j<metadatosBaja.length; j++){
//    				var metadatoBaja = metadatosBaja[j];
//    				html+="<div id='metadato_div_"+metadatoBaja.id+"' data-id='"+metadatoBaja.id +"' class='col-md-4 oculto metadatoBaja' >";
//    					html+="<p id='desc_metadato_"+metadatoBaja.id+"' class='metadatoButton' style='background-color:#F0ABBF;'>"+metadatoBaja.descEu+"</p>";
//    				html+="</div>";
//    			}
//	    			html+="</div>";
//	    		html+="</section>";
//	    		html+="</div>";
//	    	}
//	    	var htmlDiv=$(html);
//	    	htmlDiv.appendTo("#categorizacionExpedientemetadatos_div");
//	    	
//	    	$(".metadatoButton").each(function() {
//				$(this).on("click", function() {
//					cambios = true;
//					var idMetadato	 = this.offsetParent.dataset.id;
//					var borrado = false;
//					if(jsonObject.listaCategorizacionExpedienteInicial.length===0){
//						//si lista de metadatos seleccionados vacia, anyadimos el seleccionado
//							jsonObject.listaCategorizacionExpedienteInicial.push({
//								idMetadato: idMetadato
//							});
//						//si el metadato esta de baja lo coloreamos en rojo. Si no en azul
//						if(this.offsetParent.classList.contains('metadatoBaja')){
//							$("#desc_metadato_"+idMetadato).css('background-color', '#F0ABBF');
//						}else{
//							$("#desc_metadato_"+idMetadato).css('background-color', '#7BD1F3');
//						}
//					}else{
//						//si lista de metadatos seleccionados no vacia, comprobamos si esta en la lista
//						$.each(jsonObject.listaCategorizacionExpedienteInicial, function(i){
//						    if( jsonObject.listaCategorizacionExpedienteInicial[i].idMetadato.toString() === idMetadato.toString()) {
//						    	//si esta en la lista lo borramos y le cambiamos el color a no seleccionado
//						    	jsonObject.listaCategorizacionExpedienteInicial.splice(i,1);
//						    	$("#desc_metadato_"+idMetadato).css('background-color', '#ececec');
//						    	borrado = true;
//						    	return false;
//						    }
//						});
//						if(!borrado){
//							//si no esta en la lista lo anyadimos 
//							jsonObject.listaCategorizacionExpedienteInicial.push({
//								idMetadato: idMetadato
//							});
//							//si el metadato esta de baja lo coloreamos en rojo. Si no en azul
//							if($("#metadato_div_"+idMetadato).hasClass('metadatoBaja')){
//								$("#desc_metadato_"+idMetadato).css('background-color', '#F0ABBF');
//							}else{
//								$("#desc_metadato_"+idMetadato).css('background-color', '#7BD1F3');
//							}
//					    	
//						}
//					}
//				});
//			});
//	    	$('.metadatoButton').hover(function() {
//		        $(this).css('cursor','pointer');
//		    });
//	    	
//		}
//	});
//}
function dibujarEtiquetas(listaCategExp){
	listaCategExp.sort(sortByDesc);
	if(listaCategExp!= null && listaCategExp.length>0){
//		var html = "<div class='aa79b-content'>";
//		html+="<section>";
		var html="<div class='row'>";
		//mostrar metadatos de alta
		for(var i = 0; i<listaCategExp.length; i++){
			var metadato = listaCategExp[i].metadato;
			html+="<span id='metadato_div_"+metadato.id+"' data-id='"+metadato.id +"' class='badge badge-pill badge-default itzulnet-pill'>";
				//html+="<p id='desc_metadato_"+metadato.id+"'>"+metadato.descEu+"</p>";
			if("A".indexOf(listaCategExp[i].metadato.estado)==0){
				html+=metadato.descEu;
			}else{
				html+="<del>"+metadato.descEu+"</del>";
			}
			if (typeof(detalleModoConsulta) == "undefined"){
				html+="<button type='button' class='btn'><i class='fa fa-times' aria-hidden='true'></i></button>";
			}else{
				html+="&nbsp;&nbsp;&nbsp";
			}
			html+="</span>";
			
		}
	    		
			html+="</div>";
		html+="</section>";
		html+="</div>";
    	var htmlDiv=$(html);
    	htmlDiv.appendTo("#categorizacionExpedientemetadatos_div");
    	// Funcionalidad de quitar badges
		$('#categorizacionExpedientemetadatos_div .badge-pill button').each(function(i,e){
			clickEliminarEtiquetaMasivo(e);
		});
	}
}

function clickEliminarEtiquetaMasivo(elem){
	$(elem).on('click',function(){
		var codTema = $(this).parent().attr('id').split('_')[2];
		
		$.each(jsonObject.listaCategorizacionExpedienteInicial, function(x, elem) {
			if (jsonObject.listaCategorizacionExpedienteInicial[x].idMetadato == codTema){
				//listEtiquetasSeleccionadas[x].remove();
				jsonObject.listaCategorizacionExpedienteInicial.splice(x,1);
				cambios = true;
				return false;
			}
		});
		
		$('#categorizacionExpedientemetadatos_div .badge').remove();
		dibujarEtiquetas(jsonObject.listaCategorizacionExpedienteInicial);
	});
}

function cargarEtiquetasSeleccionadas(listaCategExp){
	if(listaCategExp!= null && listaCategExp.length>0){
		for(var i = 0; i<listaCategExp.length; i++){
			var encontrado = false;
			if(jsonObject.listaCategorizacionExpedienteInicial.length===0){
//				jsonObject.listaCategorizacionExpedienteInicial.push({
//					anyo: anyoExpediente,
//					numExp: idExpediente,
//					idMetadato: idMetadato
//				});
				jsonObject.listaCategorizacionExpedienteInicial.push(listaCategExp[i]);
			}else{
				$.each(jsonObject.listaCategorizacionExpedienteInicial, function(x){
				    if(jsonObject.listaCategorizacionExpedienteInicial[x].anyo === listaCategExp[i].anyo
				    		&& jsonObject.listaCategorizacionExpedienteInicial[x].numExp === listaCategExp[i].numExp
				    		&& jsonObject.listaCategorizacionExpedienteInicial[x].idMetadato.toString() === listaCategExp[i].idMetadato.toString()) {
				    	encontrado = true;
				    	return false;
//				    	jsonObject.listaCategorizacionExpedienteInicial.splice(i,1);
//				    	$("#desc_metadato_"+idMetadato).css('background-color', '#ececec');
//				    	borrado = true;
				    	return false;
				    }
				});
				if(!encontrado){
					jsonObject.listaCategorizacionExpedienteInicial.push(listaCategExp[i]);
//					jsonObject.listaCategorizacionExpedienteInicial.push({
//						anyo: anyoExpediente,
//						numExp: idExpediente,
//						idMetadato: idMetadato
//					});
//					if($("#metadato_div_"+idMetadato).hasClass('metadatoBaja')){
//						$("#desc_metadato_"+idMetadato).css('background-color', '#F0ABBF');
//					}else{
//						$("#desc_metadato_"+idMetadato).css('background-color', '#7BD1F3');
//					}
			    	
				}
			}
		}
	}
}

function seleccionarEtiquetaMasivo(obj, etiquetas) {
	//añadimos el obj a una lista de etiqutas seleccionadas y es la que nos recorreremos para añadir
	$.each(obj, function(i, e) {
		var idEtiqueta = obj[i].id;
		var encontradaEtiqueta = false;
		$.each(jsonObject.listaCategorizacionExpedienteInicial, function(x, e) {
			if (jsonObject.listaCategorizacionExpedienteInicial[x].idMetadato == idEtiqueta){
				encontradaEtiqueta = true;
			}
		});
		if (!encontradaEtiqueta){
				jsonObject.listaCategorizacionExpedienteInicial.push({
				anyo: anyoExpediente,
				numExp: idExpediente,
				idMetadato: idEtiqueta,
				metadato:obj[i]
			});
			//jsonObject.listaCategorizacionExpedienteInicial.push(obj[i]);
		}
	});
	//eliminar contenido
	cambios = true;
	$('#categorizacionExpedientemetadatos_div .badge').remove();
	dibujarEtiquetas(jsonObject.listaCategorizacionExpedienteInicial);

}

//This will sort your array
function sortByDesc(a, b){
  var aDesc = a.metadato.descEu.toLowerCase();
  var bDesc = b.metadato.descEu.toLowerCase(); 
  return ((aDesc < bDesc) ? -1 : ((aDesc > bDesc) ? 1 : 0));
}

/*
 * ****************************
 * FUNCIONALIDAD - FIN
 * ****************************
 */

/*
 * ****************************
 * FEEDBACK - INICIO
 * ****************************
 */
function crearFeedbackMetadatosBusquedaConsulta(){
	$('#metadatosBusquedaConsulta_feedback').rup_feedback({
    	block : false,
    	gotoTop: true, 
    	delay: 3000
    });
}
/*
 * ****************************
 * FEEDBACK - FIN
 * ****************************
 */

/*
 * ****************************
 * TOOLBAR - INICIO
 * ****************************
 */
function crearToolbarMetadatosBusquedaConsulta(){
	$("#metadatosBusquedaConsulta_toolbar").rup_toolbar({
		buttons:[
			{
				i18nCaption: $.rup.i18n.app.boton.volver
				,css: "fa fa-arrow-left"
				,click : 
					function(e){
						e.preventDefault();
	                    e.stopImmediatePropagation();
	                    if(cambios){
			 				$.rup_messages("msgConfirm", {
			 					message: $.rup.i18n.app.mensajes.cambiosCategorizacion,
			 					OKFunction: function(){
			 						limpiarMetadatosList();
			 						expedientesSeleccionados = [];
			 						volverACapaGeneralConsulta();
						 			cambios = false;
						 			jsonObject.listaCategorizacionExpedienteInicial = [];
			 					}
			 				});	
			 			}else{
			 				limpiarMetadatosList();
			 				expedientesSeleccionados = [];
			 				volverACapaGeneralConsulta();
				 			cambios = false;
				 			jsonObject.listaCategorizacionExpedienteInicial = [];
			 			}
	                }
			},
			{
				i18nCaption: $.rup.i18n.app.boton.guardar
				,css: "fa fa-floppy-o"
			    ,click : 
				function(event){
			    	event.preventDefault();
		 			event.stopImmediatePropagation();
		    		//comprobamos que se han seleccionado metadatos a guardar
		    		if(cambios){
    		    		if(jsonObject.listaCategorizacionExpedienteInicial!=null){
			    			//guardamos lista.
    		    			if(jsonObject.listaCategorizacionExpedienteInicial.length==0){
    		    				eliminarAllCategExp(jsonObject.listaCategorizacionExpedienteInicial);
    		    			}else{
    		    				guardarListCategExp(jsonObject.listaCategorizacionExpedienteInicial);	
    		    			}
    		    		}else{
    		    			//error en lista
    		    			$('#metadatosBusquedaConsulta_feedback').rup_feedback("set", $.rup.i18n.app.mensajes.errorGuardandoDatos, "error");
    		    		}
		    		}else{ 
		    			if(1!=expedientesSeleccionados.length){
		    				//hay mas de un expediente seleccionado y se da a guardar nada mas entrar con los metadatos de alta seleccionados
		    				//eliminar metadatos de expedientes seleccionados
		    				eliminarAllCategExp(jsonObject.listaCategorizacionExpedienteInicial);
		    			}else{
		    				//no ha habido cambios
			    			$('#metadatosBusquedaConsulta_feedback').rup_feedback("set", $.rup.i18n.app.mensajes.sinCambiosCategorizacion, "error");
		    			}
		    		}
			    }
			},
				{
				
				id: "anyadir"
				,i18nCaption: $.rup.i18n.app.boton.anyadir
				,css: "fa fa-file-o"
				,click : 
					 function(event){
			 			event.preventDefault();
			 			event.stopImmediatePropagation();
			 			$("#buscadorEtiquetasMasivo").remove();
						$("#capasModalesDiv")
								.append(
										'<div id="buscadorEtiquetasMasivo" style="display:none"></div>');
						$("#buscadorEtiquetasMasivo").buscador_etiquetas({
							//destino : 'P',
							multiselect : false,
							callback : seleccionarEtiquetaMasivo
						});
						$("#buscadorEtiquetasMasivo").buscador_etiquetas("open");
    		    		
					}
			}
		]
	});
}
/*
 * ****************************
 * TOOLBAR - FIN
 * ****************************
 */

jQuery(function($){
	jQuery.ajaxSetup({cache: false });
	
	crearFeedbackMetadatosBusquedaConsulta();
	crearToolbarMetadatosBusquedaConsulta();
	if(1===expedientesSeleccionados.length){
		// si solo seleccionamos un expediente, mostramos sus metadatos
		$("#avisoExpedientesMasivo").hide();
		cargarMetadatosExpedienteConsulta();
	}
	
	//cargarMetadatosBusquedaConsulta();
});