var jsonObject = {
		listaCategorizacionExpedienteInicial : []
}
var metadatosAlta = [];
var metadatosBaja = [];
var cambios = false;
var anyoExp;
var numExpExp;
//function volverADetalleExpediente(){
//	$("#divCategorizacionExpediente").detach();
//	$("#divCapaDetalle").append("<div id='divDetalleExpediente'></div>");
//	$("#divDetalleExpediente").html(capaDetalleExpediente);
//}
function limpiarMetadatosList(){
//	if(jsonObject!=null && jsonObject.listaCategorizacionExpedienteInicial!= null && jsonObject.listaCategorizacionExpedienteInicial.length>0){
//		for(var i=0;i<jsonObject.listaCategorizacionExpedienteInicial.length;i++){
//			$("#desc_metadato_"+jsonObject.listaCategorizacionExpedienteInicial[i].idMetadato).css('background-color', '#ececec');
//		}
//	}
	jsonObject.listaCategorizacionExpedienteInicial = [];
}
function multiple(valor, multiple)
{
    var resto = valor % multiple;
    if(resto===0){
    	return true;
    }
    return false;
}
function isObjectInArray(lista, metadato) {
    for (var i = 0; i < lista.length; i++) {
        if (lista[i].idMetadato.toString() === metadato.id.toString()) {
            return true;
        }
    }
    return false;
}

function cargarMetadatosExpediente(anyo, numExp){
	//obtener metadatos relacionados
	anyoExp = anyo;
	numExpExp = numExp;
	limpiarMetadatosList();
	var listaMetadatosBaja = [];
	jQuery.ajax({
		type: "GET",
		url: "/aa79bItzulnetWar/categorizacionexpediente/getMetadatosIds/"+anyo+"/"+numExp,
		dataType: "json",
		contentType: "application/json",
		cache: false,
		success: function(data){
			if(data!=null){
				cargarEtiquetasSeleccionadas(data.listaCategExp);
				dibujarEtiquetas(jsonObject.listaCategorizacionExpedienteInicial);
			}	
		},
		error: function(){
			
		}
		
	});
}

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
			clickEliminarEtiqueta(e);
		});
	}
}

function clickEliminarEtiqueta(elem){
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

function seleccionarEtiquetaPlanificacion(obj, etiquetas) {
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




jQuery(function($){
	//introducirlos en la lista de seleccionados
	var guardadoExito = true;
	
	$('#categorizacionExpediente_feedback').rup_feedback({
		block : false,
		delay: 1000
	});
	$("#categorizacionExpediente_toolbar").rup_toolbar({
		buttons:[
			{ 
				i18nCaption: $.rup.i18n.app.boton.volver
						,css: "fa fa-arrow-left"
							,click : 
								 function(event	){
									event.preventDefault();
						 			event.stopImmediatePropagation();
						 			if(cambios){
						 				$.rup_messages("msgConfirm", {
						 					message: $.rup.i18n.app.mensajes.cambiosCategorizacion,
						 					OKFunction: function(){
						 						limpiarMetadatosList();
//		    						 			volverADetalleExpediente();
						 						$("#buscadorEtiquetasPlanificacion").remove();
		    						 			volverADetalleExpedienteDesdeAccionesPlanif('divCategorizacionExpediente');
		    						 			cambios = false;
		    						 			jsonObject.listaCategorizacionExpedienteInicial = [];
						 					}
						 				});	
						 			}else{
						 				limpiarMetadatosList();
						 				cambios = false;
						 				$("#buscadorEtiquetasPlanificacion").remove();
    						 			jsonObject.listaCategorizacionExpedienteInicial = [];
						 				if (typeof(detalleModoConsulta) != "undefined"){
						 					volverADetalleConsulta();
						 				}else{
						 					volverADetalleExpedienteDesdeAccionesPlanif('divCategorizacionExpediente');
						 				}
						 			}
								}
			},
			{
				
				id: "guardar"
				,i18nCaption: $.rup.i18n.app.boton.guardar
				,css: "fa fa-floppy-o"
				,click : 
					 function(event){
			 			event.preventDefault();
			 			event.stopImmediatePropagation();
    					var listaCategExpAEliminar = [];
    		    		var listaCategExpAGuardar = [];
    		    		//comprobamos que se han seleccionado metadatos a guardar. Si no, no hay que realizar ninguna operacion
    		    		if(cambios){
    		    			if(jsonObject.listaCategorizacionExpedienteInicial!=null){
    			    			//guardamos lista.
	    		    			if(jsonObject.listaCategorizacionExpedienteInicial.length==0){
	    		    				
	    		    				jsonObject.listaCategorizacionExpedienteInicial.push({
	    		    					"anyo" : anyoExpediente,
	    		    					"numExp" : idExpediente
	    		    				});
	    		    				eliminarAllCategExp(jsonObject.listaCategorizacionExpedienteInicial);
	    		    				jsonObject.listaCategorizacionExpedienteInicial = [];
	    		    			}else{
	    		    				guardarListCategExp(jsonObject.listaCategorizacionExpedienteInicial);	
	    		    			}
	    		    			if(guardadoExito){
	    		    				$('#categorizacionExpediente_feedback').rup_feedback("set", $.rup.i18n.app.mensajes.guardadoCorrecto, "ok");
	    		    				cambios = false;
	    		    			}else{
	    		    				$('#categorizacionExpediente_feedback').rup_feedback("set", $.rup.i18n.app.mensajes.errorGuardandoDatos, "error");
	    		    			}
	    		    		}else{
	    		    			//error en lista
	    		    			$('#categorizacionExpediente_feedback').rup_feedback("set", $.rup.i18n.app.mensajes.errorGuardandoDatos, "error");
	    		    		}
    		    		}else{
    		    			//no ha habido cambios
    		    			$('#categorizacionExpediente_feedback').rup_feedback("set", $.rup.i18n.app.mensajes.sinCambiosCategorizacion, "error");
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
			 			$("#buscadorEtiquetasPlanificacion").remove();
						$("#capasModalesDiv")
								.append(
										'<div id="buscadorEtiquetasPlanificacion" style="display:none"></div>');
						$("#buscadorEtiquetasPlanificacion").buscador_etiquetas({
							//destino : 'P',
							multiselect : false,
							callback : seleccionarEtiquetaPlanificacion
						});
						$("#buscadorEtiquetasPlanificacion").buscador_etiquetas("open");
    		    		
					}
			}
		]
	});
	
	if (typeof(detalleModoConsulta) != "undefined"){
		//si detalle en modo consulta ocultamos el boton guardar de toolbar
		$("[id='categorizacionExpediente_toolbar##"+"guardar"+"']").hide();
		$("[id='categorizacionExpediente_toolbar##"+"anyadir"+"']").hide();
		//cargar metadatos desde detalle en modo consulta
		cargarMetadatosExpediente(anyo, numExp);
	}else{
		//cargar metadatos desde planificacion y consulta planificacion de expedientes
		cargarMetadatosExpediente(anyoExpediente, idExpediente);
	}
	
	
	
	/*jQuery.ajax({
		type: "POST",
		url:"/aa79bItzulnetWar/categorizacionexpediente/getAllMetadatos",
		dataType: "json",
		contentType: 'application/json',
		cache: false,
		success: function(data){
	    	if(data!=null && data.length>0){
	    		for(var i=0;i<data.length>0;i++){
	    			if("A".indexOf(data[i].estado)==0){
	    				metadatosAlta.push(data[i]);
	    			}else{
	    				metadatosBaja.push(data[i]);
	    			}
	    		}
	    		
	    		var html = "<div class='aa79b-content'>";
    			html+="<section>";
				html+="<div class='row'>";
				//mostrar metadatos de alta
				for(var i = 0; i<metadatosAlta.length; i++){
					var metadato = metadatosAlta[i];
					html+="<div id='metadato_div_"+metadato.id+"' data-id='"+metadato.id +"' class='col-md-4 '>";
    					html+="<p id='desc_metadato_"+metadato.id+"' class='metadatoButton'>"+metadato.descEu+"</p>";
    				html+="</div>";
				}
	    		
		    	//situar metadatos de baja por si hay que mostrarlos
    			for(var j = 0; j<metadatosBaja.length; j++){
    				var metadatoBaja = metadatosBaja[j];
    				html+="<div id='metadato_div_"+metadatoBaja.id+"' data-id='"+metadatoBaja.id +"' class='col-md-4 oculto metadatoBaja' >";
    					html+="<p id='desc_metadato_"+metadatoBaja.id+"' class='metadatoButton' style='background-color:#F0ABBF;'>"+metadatoBaja.descEu+"</p>";
    				html+="</div>";
    			}
	    			html+="</div>";
	    		html+="</section>";
	    		html+="</div>";
	    	}
	    	var htmlDiv=$(html);
	    	htmlDiv.appendTo("#categorizacionExpedientemetadatos_div");
	    	if (typeof(detalleModoConsulta) == "undefined"){
	    		$(".metadatoButton").each(function() {
					$(this).on("click", function() {
						cambios = true;
						var idMetadato	 = this.offsetParent.dataset.id;
						var borrado = false;
						if(jsonObject.listaCategorizacionExpedienteInicial.length===0){
							jsonObject.listaCategorizacionExpedienteInicial.push({
								anyo: anyoExpediente,
								numExp: idExpediente,
								idMetadato: idMetadato
							});
							if(this.offsetParent.classList.contains('metadatoBaja')){
								$("#desc_metadato_"+idMetadato).css('background-color', '#F0ABBF');
							}else{
								$("#desc_metadato_"+idMetadato).css('background-color', '#7BD1F3');
							}
						}else{
							$.each(jsonObject.listaCategorizacionExpedienteInicial, function(i){
							    if(jsonObject.listaCategorizacionExpedienteInicial[i].anyo === anyoExpediente 
							    		&& jsonObject.listaCategorizacionExpedienteInicial[i].numExp === idExpediente
							    		&& jsonObject.listaCategorizacionExpedienteInicial[i].idMetadato.toString() === idMetadato.toString()) {
							    	jsonObject.listaCategorizacionExpedienteInicial.splice(i,1);
							    	$("#desc_metadato_"+idMetadato).css('background-color', '#ececec');
							    	borrado = true;
							    	return false;
							    }
							});
							if(!borrado){
								jsonObject.listaCategorizacionExpedienteInicial.push({
									anyo: anyoExpediente,
									numExp: idExpediente,
									idMetadato: idMetadato
								});
								if($("#metadato_div_"+idMetadato).hasClass('metadatoBaja')){
									$("#desc_metadato_"+idMetadato).css('background-color', '#F0ABBF');
								}else{
									$("#desc_metadato_"+idMetadato).css('background-color', '#7BD1F3');
								}
						    	
							}
						}
					});
				});
		    	$('.metadatoButton').hover(function() {
			        $(this).css('cursor','pointer');
			    });
	    	}
	    
	    	
		}
	});*/
	
	
	function guardarListCategExp(lista){
		var jsonObject = {
				listaCategExp : lista
		}
		jQuery.ajax({
			type: "POST",
			url: "/aa79bItzulnetWar/categorizacionexpediente/comprobarYGuardarOEliminar",
			dataType: "json",
			contentType: "application/json",
 			data: $.toJSON(jsonObject),
			cache: false,
			success: function(data){
				guardadoExito=true;
			},
			error: function(){
				guardadoExito=false;
			}
			
		});
	}
	
	function eliminarAllCategExp(lista){
		var jsonObject = {
				listaCategExp : lista
		}
		jQuery.ajax({
			type: "POST",
			url: "/aa79bItzulnetWar/categorizacionexpediente/eliminarAllCategExp",
			dataType: "json",
			contentType: "application/json",
 			data: $.toJSON(jsonObject),
			cache: false,
			success: function(data){
				guardadoExito=true;
			},
			error: function(){
				guardadoExito=false;
			}
		});
	}
	
});