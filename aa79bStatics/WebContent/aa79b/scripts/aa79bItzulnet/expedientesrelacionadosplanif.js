
var tienePermisosGestorBOPV = "N";

//function volverADetalleExpediente(){
//	$("#divExpedientesRelacionados").detach();
//	$("#divCapaDetalle").append("<div id='divDetalleExpediente'></div>");
//	$("#divDetalleExpediente").html(capaDetalleExpediente);
//}

function mostrarExpedientesRelacionados(){
	
	$("#anyo_detail_filter").val(anyoExpediente);
	$("#numExp_detail_filter").val(idExpediente);
	
	$("#buscadorExpedientesRelacionados").remove();
	$("#expRelacionadosDiv").html("<div id='buscadorExpedientesRelacionados' class='oculto'></div>");
	
	$('.capaExpedienteMYO').addClass('collapsed');
	$('.capaExpedienteMYO').removeClass('in');
	setTimeout(function() {
		'divExpedientesRelacionados'.split(",").forEach(function(unaCapa) {
			mostrarDiv(unaCapa);
			$('#expedientesRelacionados').rup_table('filter');
		});
	}, 500);	
	
	$("#buscadorExpedientesRelacionados").buscador_expedientesRelacionados({
		anyo : $("#anyo_detail_filter").val(),
		numExp : $("#numExp_detail_filter").val(),
		guardarOnartu: true,
		callback : expedientesRelacionados
	});
}

function expedientesRelacionados(obj, expedientes) {
	if (obj != null && obj.length > 0) {
		var expedientes = "";
		for (var i = 0; i < obj.length; i++) {
			var expediente = obj[i];
			expedientes = expedientes + "expediente [" + i + "]: ";
			expedientes = expedientes + expediente.anyo + " , ";
			expedientes = expedientes + expediente.numExp + " .";
		}
	}
	
	$("[id='expediente_toolbar##"+ $.rup.i18nParse($.rup.i18n.app,"boton.expedientesRelacionados")+"']").button("enable");
	
}

function eliminarExpedienteRelacionado(){
	
	var selectedRows = $("#expedientesRelacionados").rup_table('getSelectedRows');
	var expediente
	var anyoExpRel;
	var numExpRel;
	
	for(var i=0;i<selectedRows.length;i++){
		 expediente = selectedRows[i];
		 
		 anyoExpRel = expediente.substr(0,expediente.indexOf('~'));
		 numExpRel = expediente.substr(expediente.indexOf('~')+1,expediente.length);
 	}
	
	var expedienteRelacionado = { 
        "anyoExpRel" : anyoExpRel,
        "numExpRel" : numExpRel
    };
	
	var jsonObject = {
		"anyo": anyoExpediente,
		"numExp": idExpediente,
		"expedienteRelacionado": expedienteRelacionado
   	};
	
	jQuery.ajax({
    	type: "DELETE",
    	url: "/aa79bItzulnetWar/tramitacionexpedientes/gestionexpedientes/estudioexpedientes/expediente/eliminarExpedienteRelacionado",
    	dataType: "json",
    	contentType: 'application/json',
    	data: $.toJSON(jsonObject),
    	cache: false,
    	success:function(){
    		$('#expedientesRelacionados').rup_table('filter');
        	$('#expedientesRelacionados_feedback').rup_feedback("set", $.rup.i18nParse($.rup.i18n.base,"rup_table.feedback.deletedOK"), "ok");
        }, 
   	 	error: function(){
   	 		$('#expedientesRelacionados_feedback').rup_feedback("set", $.rup.i18nParse($.rup.i18n.app,"rup_table.feedback.deletedError"), "ok");
   	 	}
    });
}

jQuery(function($){
	bitacoraUpdate(false);
	//TODO: recuperamos si es gestor del expediente que nos pasan tiene permisos de gestor de BOPV
	tienePermisosGestorBOPV = "S";
	
	$("#anyo_detail_filter").val(anyoExpediente);
	$("#numExp_detail_filter").val(idExpediente);
	
	$("#buscadorExpedientesRelacionados").buscador_expedientesRelacionados({
		anyo : $("#anyo_detail_filter").val(),
		numExp : $("#numExp_detail_filter").val(),
		guardarOnartu: true,
		callback : expedientesRelacionados
	});
	
	 $('#expedientesRelacionados_feedback').rup_feedback({
		block : false,
		gotoTop: true, 
		delay: 3000
	});
		
	$("#expedientesRelacionados").rup_table({
		url:"/aa79bItzulnetWar/tramitacionexpedientes/gestionexpedientes/estudioexpedientes/expediente/obtenerExpedientesRelacionados",
		toolbar: {
			id: "expedientesRelacionados_toolbar"
			,defaultButtons:{
				add : false
				,edit : false
				,cancel : false
				,save : false
				,clone : false
				,"delete" : false
				,filter : false
			}
			,newButtons:[
				{obj: {
						i18nCaption: $.rup.i18n.app.boton.volver
						,css: "fa fa-arrow-left"
   		 			}
   		 			,json_i18n : $.rup.i18n.app.simpelMaint
   		 			,click : 
   		 				function(){
   		 					$("#buscadorExpedientesRelacionados").remove();
   		 					$("#expRelacionadosDiv").html("<div id='buscadorExpedientesRelacionados' class='oculto'></div>");
//   		 					volverADetalleExpediente();
	 						volverADetalleExpedienteDesdeAccionesPlanif('divExpedientesRelacionados');
   		 				}	
				},
				{obj: {
						i18nCaption: $.rup.i18n.app.boton.nuevo
						,css: "fa fa-file-o"
   		 			}
   		 			,json_i18n : $.rup.i18n.app.simpelMaint
   		 			,click : 
   		 				function(){
   		 					$("#buscadorExpedientesRelacionados").buscador_expedientesRelacionados("open");
   		 			}	
				},
				{obj: {
						i18nCaption: $.rup.i18n.app.boton.borrar
		 				,css: "fa fa-trash-o"
   		 			}
   		 			,json_i18n : $.rup.i18n.app.simpelMaint
   		 			,click : 
   		 				function(e){
		   		 			if(!$('#expedientesRelacionados').rup_table("isSelected")){
								e.preventDefault();
								$.rup_messages("msgAlert", {
									message: $.rup.i18n.app.comun.warningSeleccion
								});	
								return false;
							 }else{
								 $.rup_messages("msgConfirm", {
		             				title: $.rup.i18nParse($.rup.i18n.base,"rup_table.changes"),
		             				message: $.rup.i18nParse($.rup.i18n.base,"rup_table.del.msg"),
		             				OKFunction: function(){
		             					eliminarExpedienteRelacionado();
		             				}
		             			});
							 }
   		 				}	
		 		}
			]
   	 }, 
   	 colNames: [
   		 $.rup.i18nParse($.rup.i18n.app, "label.numExpediente"),
   		 $.rup.i18nParse($.rup.i18n.app, "label.titulo"),
   		 $.rup.i18nParse($.rup.i18n.app, "label.fechaHoraSolicitud"),
   		 $.rup.i18nParse($.rup.i18n.app, "label.estadoFase")
   	 ],
   	 colModel:[
   		 	{ 	name: "anyoNumExpConcatenado", 
			 	label: "label.anyoNumExpConcatenado",
			 	align: "center", 
				width: "80", 
				index: "ANYONUMEXPCONCATENADO",
				fixed: false,
				formatter: function (cellvalue, options, rowObject) {
 					
 					return '<b><a target="_blank" href="/aa79bItzulnetWar/tramitacionexpedientes/gestionexpedientes/estudioexpedientes/expediente/cabeceraDetalleExpNuevaVentana/'+rowObject.anyo+'/'+rowObject.numExp+'">' + cellvalue + '</a></b>';
 				}
			}, 
			{
				name:"titulo",
				label: "label.titulo",
				width: 200,
				index: "TITULO_051",
				align: "left",
				editable: false,
				fixed: false,
				cellattr: function (rowId, tv, rawObject, cm, rdata) { 
				    return 'style="white-space: normal;"';
				}
			}, 
			{
				name:"fechaHoraAlta",
				label: "label.fechaHoraSolicitud",
				width: 80,
				index: "FECHA_ALTA_051",
				align: "center",
				editable: false,
				fixed: false
			}, 
			{
				name: $.rup.lang == 'es' ? "bitacoraExpediente.faseExp.descEs"
						: "bitacoraExpediente.faseExp.descEu",
				label: "label.estadoFase",
				width: 150,
				index: $.rup.lang == 'es' ? "FASEEXPEDIENTEDESCES"
						: "FASEEXPEDIENTEDESCEU",
				align: "left",
				editable: false,
				fixed: false,
				cellattr: function (rowId, tv, rawObject, cm, rdata) { 
				    return 'style="white-space: normal;"';
				}
			}
   	 ],
   	 model: "Expediente",
   	 usePlugins:[
   		 "toolbar",
   		 "responsive",
   		 "filter",
   		 "fluid",
   		 "filter"
   	 ],
   	 primaryKey:["anyo", "numExp"],
   	 sortname: "ANYONUMEXPCONCATENADO",
   	 sortorder: "asc",
   	 loadOnStartUp: true
    });
	
});