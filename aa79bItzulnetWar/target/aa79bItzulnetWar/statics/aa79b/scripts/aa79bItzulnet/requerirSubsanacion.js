
var newDate;
var indNuevosDocus = 'N';

function volverACapaGeneral(){
	$("#requerirSubsanacionDetalleDiv").detach();
	$("#divRequerirSubsanacionGeneral").html(capaPestGeneral);
	$("#requerirSubsanacion").rup_table("filter");
	$("#requerirSubsanacion").rup_table("deselectAllRows");
}

function requerirSub(){
	
	var docuSubSelect = '';  
	$('#docuAsociados ul li.jstree-checked').each(function(){
		docuSubSelect += $(this).val()+';';
    }); 
	
    var Expediente = 
    {
        "anyo": anyoExpediente, 
        "numExp": idExpediente
    };
	var SubsanacionExpediente = 
    {
        "detalle": $('#detSubsanacion').val(),
        "fechaLimite": $('#detFechaLimite').val(),
        "horaLimite": $('#detHoraLimite').val(),
        "indDocNuevos": indNuevosDocus
    };
	var Tareas = 
	{
			"fechaIni": $('#fechaIni').val(),
			"fechaFin": $('#fechaFin').val(),
			"horaIni": $('#horaIni').val(),
			"horaFin": $('#horaFin').val(),
			"horasPrevistas": $('#horasPrevistas').val(),
			"orden": $('#orden').val(),
			"observaciones": $('#observaciones').val()
	};
	
	jQuery.ajax({
		type : "POST",
		url : "/aa79bItzulnetWar/servicios/requerirsubsanacionexpedientes/requerirSubsanacionDetalle/guardar",
		dataType : "json",
		contentType : 'application/json',
		data: jQuery.toJSON({
	   		"expediente":Expediente,
	   		"docuSelecSub":docuSubSelect,
	   		"subsanacionExp": SubsanacionExpediente, 
	   		"tareas": Tareas
	   	}),
		cache : false,
		success: function(data)
		{
			volverACapaGeneral();
			$('#requerirSubsanacion_feedback').rup_feedback("set", $.rup.i18n.app.mensajes.subRegistrada, "ok");
		},
	     error: function (){
	    	 $('#requerirSubsanacionDetalle_feedback').rup_feedback("set", $.rup.i18n.app.mensajes.errorGuardandoDatos, "error");
	     }
	});
}

$('#requerirSubsanacionDetalle_feedback').rup_feedback({
	block : false,
	gotoTop: true, 
	delay: 3000
});

jQuery('#detFechaLimite').rup_date({		
	labelMaskId : "fecha-mask",
	minDate: new Date()
});	

jQuery('input:checkbox[data-switch-pestana="true"]').each(function(index, element){
	jQuery(element)
	.bootstrapSwitch()
	.bootstrapSwitch('setSizeClass', 'switch-small')
	.bootstrapSwitch('setOnLabel', jQuery.rup.i18n.app.comun.si)
	.bootstrapSwitch('setOffLabel', jQuery.rup.i18n.app.comun.no);
});

$('#permitirDocs').on('switch-change', function (e, data) {
	 if(data.value){
		 indNuevosDocus = 'S';
	 }else{
		 indNuevosDocus = 'N';
	 }   
});

fnFechaDesdeHasta("fechaIni", "fechaFin");

var DocumentosExpediente = 
{
    "anyo": anyoExpediente, 
    "numExp": idExpediente
};

jQuery.ajax({
	type : "POST",
	url : "/aa79bItzulnetWar/tramitacionexpedientes/gestionexpedientes/estudioexpedientes/expediente/listaDocuAsociados",
	dataType : "json",
	contentType : 'application/json',
	data: jQuery.toJSON({
   		  "docuExp":DocumentosExpediente
   	}),		
	cache : false,
	success: function(data)
	{
	 	jQuery("#listaDocuAsociados").rup_tree("destroy");
		$("#listaDocuAsociados > ul").empty();
		//Carga del árbol
		for(var i = 0 ; i < data.length; i++){
			$("#listaDocuAsociados > ul").append("<li id='"+data[i].titulo+"' value = '"+data[i].idDoc+"'>");
			if(data[i].tipoDocumentoDesc != null){
				$("#listaDocuAsociados > ul > li").last().append("<a href='#' value ='"+data[i].idDoc+"'>"+data[i].titulo + " ("+ data[i].claseDocumentoDesc+" - "+data[i].tipoDocumentoDesc+")</a>");
			} else {
				$("#listaDocuAsociados > ul > li").last().append("<a href='#' value ='"+data[i].idDoc+"'>"+data[i].titulo+ " ("+ data[i].claseDocumentoDesc+")</a>");
			}
			$("#listaDocuAsociados > ul").append("</li>");
		}
		$("#listaDocuAsociados").rup_tree({
			"select" : {
			      "select_limit" : "1"
			   }
			,"checkbox" : {
			      "enable" : true
			   }
		});
		
		setTimeout(function() {
			$("#listaDocuAsociados > ul > li > ins.jstree-icon").remove();
			$("#listaDocuAsociados > ul > li > a > ins.jstree-icon").remove();
		}, 10);
	}
});

jQuery.validator.addMethod("validateSelectList", function(valor) {  
	var docuSubSelect = '';
	$('#docuAsociados ul li.jstree-checked').each(function(){
		docuSubSelect += $(this).val()+';';
	}); 
	if(docuSubSelect || indNuevosDocus=="S"){
		return true;
	}else{
		return false;    		
	}
},$.rup.i18n.app.mensajes.rellenaCampos);

$("#requerirSubsanacionDetalle_filter_form").rup_validate({
	feedback: $('#requerirSubsanacionDetalle_feedback'),
	liveCheckingErrors: false,				
	block:false,
	delay: 3000,
	gotoTop: true, 
	rules:{
		"detSubsanacion": {required: true},
		"fechaLimite": {required: true, date: true, fechaMayorNow: true},
		"horaLimite": {required: true, hora: true, maxlength: 5, horaFechaMayorNow: ['fechaLimite'], },
		"docusSelect": {validateSelectList:true},
		"tarea.fechaIni": {required: true, date: true},
		"tarea.fechaFin": {required: true, date: true, fechaHastaMayor: "tarea.fechaIni"},
		"tarea.horaIni": {required: true, hora:true, maxlength: 5},
		"tarea.horaFin": {required: true, hora:true, maxlength: 5, horaFechaHastaMayor: ["tarea.fechaIni","tarea.fechaFin","tarea.horaIni"], },
		"tarea.horasPrevistas": { required: true, horasPrevistas: true, maxlength: 5 },
		"tarea.observaciones": {required: true}
	},
	showFieldErrorAsDefault: false,
	showErrorsInFeedback: true,
		showFieldErrorsInFeedback: false
});
jQuery(function($){
	
	var fechaHoraIzo = fechaHoraFinIzo.split(" ");
	$("#fechaFinalIzo").val(fechaHoraIzo[0]);
	$("#horaFinalIzo").val(fechaHoraIzo[1]);
	
	 $("#detHoraLimite").change(function() { 
		 newDate = new Date();
	 });
	 
	 $("#requerirSubsanacionDetalle_toolbar").rup_toolbar({
	         buttons:[
	                 {		id : "volver",    
	                         i18nCaption: $.rup.i18n.app.boton.volver
	                         ,css: "fa fa-arrow-left"
	                         ,index: 1
	                         ,right: false
	                         ,click : 
	                                 function(e){
	                                         e.preventDefault();
	                                         e.stopImmediatePropagation();
	                                         volverACapaGeneral();
	                         		}
	                 },
	                 {
	                 	id : "aceptar" 
	                 	,i18nCaption: $.rup.i18n.app.boton.requerirSub
	                     ,css: "fa fa-floppy-o"
	                     ,index: 1
	                     ,disabled: false
	                     ,click : 
	                         function(e){
	                            e.stopImmediatePropagation();
	                            $("#detFechaLimite").rules("add", { "fechaMenorOIgualQueValorCampo": $('#fechaFinalIzo').val()});
	                        	$("#fechaFin").rules("add", { "fechaMenorOIgualQueValorCampo": $('#fechaFinalIzo').val()});
	                        	$("#detHoraLimite").rules("add", { "horaFechaMenorQueValorCampo":  [$('#horaFinalIzo').val() , 'detFechaLimite', 'fechaFinalIzo' ] }); 
	                        	$("#horaFin").rules("add", { "horaFechaMenorQueValorCampo":  [$('#horaFinalIzo').val() , 'fechaFin', 'fechaFinalIzo' ] }); 
	                        	
	                        	$("#detFechaLimite").rules("add", { "fechaMenorOIgualQueValorCampo": $('#fechaIni').val()});
	                        	$("#detHoraLimite").rules("add", { "horaFechaMenorQueValorCampo":  [$('#horaIni').val() , 'detFechaLimite', 'fechaIni' ] }); 
								if ($("#requerirSubsanacionDetalle_filter_form").valid()){
									requerirSub();
								}
	                     }
	                 }
	     ]
	 });
});
