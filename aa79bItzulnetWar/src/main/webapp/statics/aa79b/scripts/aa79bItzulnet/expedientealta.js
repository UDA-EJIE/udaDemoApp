nora_response();

var esTecnico = "";
var esSubsanado = "";
var isGestorVip = 'N';
var expedienteConfidencial= 'N';

jQuery(function($){
	jQuery.ajaxSetup({cache: false });
	$("#tabsExpediente").rup_tabs({
		tabs : [
			{i18nCaption: "pestdatosg", url:"/aa79bItzulnetWar/datosgeneralesexpediente/datosexpedienteview?"+$.now()},
			{i18nCaption: "pestdocumentos", url:"/aa79bItzulnetWar/tramitacionexpedientes/documentosexpediente/listado/"+anyoExpediente+"/"+idExpediente+"/"+origen+"?"+$.now()}
		]
	,cache: false
	,select: function(e){
		if (!omitirComprobacionFormularios && comprobarFormularios && !comprobarFormulariosPestanas()){
			e.preventDefault();
            e.stopImmediatePropagation();
			$.rup_messages("msgConfirm", {
				title: $.rup.i18nParse($.rup.i18n.base,"rup_table.changes"),
				message: $.rup.i18nParse($.rup.i18n.base,"rup_table.saveAndContinue"),
				OKFunction: function(){
					bloquearPantalla($.rup.i18nParse($.rup.i18n.app,"comun.cargando"));
					if ($('#capaPestanaCompletaAlta').length > 0){
						datosFormulario = $("#datosgeneralesexpedienteform").serialize();
						$("#tabsExpediente").rup_tabs("selectTab",{
							idTab: "tabsExpediente",
							position: 1
						});
						
					} else {
						datosFormularioDoc = $("#datosExpedienteTradRev_form").serialize();
						$("#tabsExpediente").rup_tabs("selectTab",{
							idTab: "tabsExpediente",
							position: 0
						});
					}
				}
			});
		} else {
			bloquearPantalla($.rup.i18nParse($.rup.i18n.app,"comun.cargando"));
			omitirComprobacionFormularios = false;
		}
	}
	,fx: [{opacity:'toggle', height: ['toggle', 'swing'], duration:'normal'}, // hide option
		  {opacity:'toggle', width: ['toggle', 'swing'],	duration:'fast'}
		 ] 
	});

	if (origen === 'A'){
		mostrarCapaExpedienteMYO('detalleExpediente_div');
	}
	
	/*TOOLBAR ALTA*/
	$("#expediente_toolbar").rup_toolbar({
		buttons:[
			{
				i18nCaption: $.rup.i18n.app.boton.expedientesRelacionados
				,css: "fa fa-link"
				,click : 
					function(e){
						e.preventDefault();
	                    e.stopImmediatePropagation();
	                    if (comprobarFormulariosPestanas()){
	                    	mostrarCapaExpedientesRelacionados();
	                    } else {
	                    	$.rup_messages("msgConfirm", {
	            				title: $.rup.i18nParse($.rup.i18n.base,"rup_table.changes"),
	            				message: $.rup.i18nParse($.rup.i18n.base,"rup_table.saveAndContinue"),
	            				OKFunction: function(){
	            					mostrarCapaExpedientesRelacionados();
	            				}
	            			});
	                    }
	                }
			},
			{
				i18nCaption: $.rup.i18n.app.boton.contactoFacturacionExpediente
				,css: "fa fa fa-ticket"
					,click : 
						function(e){
							e.preventDefault();
		                    e.stopImmediatePropagation();
		                    if (comprobarFormulariosPestanas()){
		                    	mostrarContactoFacturacion();
		                    } else {
		                    	$.rup_messages("msgConfirm", {
		            				title: $.rup.i18nParse($.rup.i18n.base,"rup_table.changes"),
		            				message: $.rup.i18nParse($.rup.i18n.base,"rup_table.saveAndContinue"),
		            				OKFunction: function(){
		            					mostrarContactoFacturacion();
		            				}
		            			});
		                    }
					}
			}
		]
	});
	
	$("[id='expediente_toolbar##"+ $.rup.i18nParse($.rup.i18n.app,"boton.expedientesRelacionados")+"']").button("disable");
	$("[id='expediente_toolbar##"+ $.rup.i18nParse($.rup.i18n.app,"boton.contactoFacturacionExpediente")+"']").button("disable");
});