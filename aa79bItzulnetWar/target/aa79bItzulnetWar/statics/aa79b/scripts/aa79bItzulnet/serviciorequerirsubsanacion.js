
	var capaPestGeneral;
	var anyoExpediente;
	var idExpediente;
	var selectedIds;
	
	$('#requerirSubsanacion_feedback').rup_feedback({
		block : false,
		gotoTop: true, 
		delay: 3000
	});
	
	$("#idTipoExpediente_filter_table").rup_combo({
		loadFromSelect: true
		,width: "100%"
		,ordered: false	
		,rowStriping: true
		,open: function(){
			jQuery('#idTipoExpediente_filter_table-menu').width(jQuery('#idTipoExpediente_filter_table-button').innerWidth());
		}
	});
	
	$('#contactoGestor_filter_table').rup_autocomplete({
		source : "/aa79bItzulnetWar/solicitante/findGestoresDeExp",
		sourceParam:{
			value : "dni",
			label : "nombreCompleto" 
		},
		getText: false,
		width: "100%",
		open : function() {
			$('.ui-autocomplete.ui-menu.ui-widget.ui-widget-content.ui-corner-all:visible').css("max-width", $('#contactoGestor_filter_table').width());
		}
	});
	
	$('#idEntidadSolicitante_filter_table').rup_combo({
		source : "/aa79bItzulnetWar/entidad/expCurso/",
		sourceParam : {
			value: "codigoCompleto",
			label : $.rup.lang === 'es' ? "descEs"
					: "descEu"
		},
		blank:"",
		width: "100%",
		getText: false,
		rowStriping: true,
		open : function() {
			jQuery('#idEntidadSolicitante_filter_table-menu').width(jQuery('#idEntidadSolicitante_filter_table-button').innerWidth());
			jQuery('.ui-autocomplete.ui-menu.ui-widget.ui-widget-content.ui-corner-all:visible').css("min-width", jQuery('#idEntidadSolicitante_filter_table').innerWidth());
			$("#idEntidadSolicitante_filter_table_menu").css("z-index", $("#entidades_detail_div").parent().css("z-index")+1);
			$("#idEntidadSolicitante_filter_table_menu").removeClass("ui-front");
		}
	});
	
	//Filtro - Cargar combo de fase entidad en funcion del tipo de entidad seleccionado
	jQuery('input[name=tipoEntidad]:first').click();
	jQuery('input[name=tipoEntidad]').change(function(){
		$('#idEntidadSolicitante_filter_table').rup_combo({
			source : "/aa79bItzulnetWar/entidad/expCurso/"+$(this).val(),
			sourceParam : {
				value: "codigoCompleto",
				label : $.rup.lang === 'es' ? "descEs"
						: "descEu"
			},
			blank:"",
			width: "100%",
			getText: false,
			rowStriping: true,
			open : function() {
				jQuery('#idEntidadSolicitante_filter_table-menu').width(jQuery('#idEntidadSolicitante_filter_table-button').innerWidth());
				jQuery('.ui-autocomplete.ui-menu.ui-widget.ui-widget-content.ui-corner-all:visible').css("min-width", jQuery('#idEntidadSolicitante_filter_table').innerWidth());
				$("#idEntidadSolicitante_filter_table_menu").css("z-index", $("#entidades_detail_div").parent().css("z-index")+1);
				$("#idEntidadSolicitante_filter_table_menu").removeClass("ui-front");
			}
		});
		jQuery('#gestorExpedienteEntidadTipo').val($(this).val());
	});
	
	function mostrarRequerirSubExp(){
		cambiarCapaGeneral('/aa79bItzulnetWar/servicios/requerirsubsanacionexpedientes/requerirSubsanacionDetalle/maint/'+ anyoExpediente +'/'+ idExpediente);
	}
	
	function comprobarSiTieneZIP(anyoExpediente,idExpediente){
		
		$.rup_ajax({
			 type: "POST",
			 url: '/aa79bItzulnetWar/servicios/requerirsubsanacionexpedientes/requerirSubsanacionDetalle/comprobarSiTieneZIP/' + anyoExpediente + '/' + idExpediente,
			 dataType: "json",
			 contentType: "application/json",
			 cache: false,	
			 success: function(data){	 
				 
				 if(data != 0){
					 $.rup_messages("msgAlert", {
						title: $.rup.i18nParse($.rup.i18n.app,"boton.requerirSub"),
						message: $.rup.i18nParse($.rup.i18n.app,"mensajes.extraerFicherosDelZip"),
						OKFunction: function(){
						}
					 });
				 } else {
					 mostrarRequerirSubExp();
				 }
			 }
		 });
		
	}

	function cambiarCapaGeneral(url){
		bloquearPantalla();
		$.rup_ajax({
		   	 url: url 
		   	 ,success:function(data, textStatus, jqXHR){
		   		capaPestGeneral = $('#requerirSubsanacionGeneralDiv').detach();
		   		 $("#divRequerirSubsanacionGeneral").html(data);
		   		desbloquearPantalla();
		   	 },
		   	 error: function (XMLHttpRequest, textStatus, errorThrown){
				alert('Error recuperando datos del paso');
		   	 }
		 });
	}
	
jQuery(function($){
	
	$("#requerirSubsanacion").rup_table({
		url: "/aa79bItzulnetWar/servicios/requerirsubsanacionexpedientes/requerirSubsanacion",
		toolbar:{
			id: "requerirSubsanacion_toolbar"
			, defaultButtons:{
				add:false,
				edit:false,
				cancel:false,
				save:false,
				clone:false,
				"delete":false,
				filter:true
			}
			,newButtons : [      
				{obj : {  
					i18nCaption: $.rup.i18n.app.boton.requerirSub
					,css: "fa fa-eye"
					,index: 1
					,right: false
				 }
				 ,json_i18n : $.rup.i18n.app.simpelMaint
				 ,click : 
					 function(e){
						e.stopImmediatePropagation();   
						selectedIds = $("#requerirSubsanacion").rup_table("getSelectedRows");
						if(selectedIds.length){
							if(1 != selectedIds.length){
								$.rup_messages("msgAlert", {
		 							title: $.rup.i18nParse($.rup.i18n.app,"boton.requerirSub"),
		 							message: $.rup.i18nParse($.rup.i18n.app,"validaciones.seleccionarSoloUno"),
		 							OKFunction: function(){
		 							}
		 						});
		                    } else {
		                    	var data = $("#requerirSubsanacion").rup_table("getRowData",selectedIds[0]);
		                    	if(data["bitacoraExpediente.faseExp.id"] != fasePdteTramCliente){
		                    		selectedIds = selectedIds[0].split(",");
			                    	anyoExpediente = selectedIds[0];
			                    	idExpediente = selectedIds[1];
			                    	comprobarSiTieneZIP(anyoExpediente,idExpediente);
		                    	} else {
		                    		$.rup_messages("msgAlert", {
			 							title: $.rup.i18nParse($.rup.i18n.app,"boton.requerirSub"),
			 							message: $.rup.i18nParse($.rup.i18n.app,"validaciones.expedientePdteTramiteCliente"),
			 							OKFunction: function(){
			 							}
			 						});
		                    	}
		                    }
						} else {
							$.rup_messages("msgAlert", {
	 							title: $.rup.i18nParse($.rup.i18n.app,"boton.requerirSub"),
	 							message: $.rup.i18nParse($.rup.i18n.app,"validaciones.seleccionarUno"),
	 							OKFunction: function(){
	 							}
	 						});
						}
					}
				}
			]
		},
		colNames: [
			$.rup.i18n.app.label.numExpediente,
			$.rup.i18n.app.label.tipo,
			$.rup.i18n.app.label.titulo,
			$.rup.i18n.app.label.gestorExpediente,
			""
		],
		colModel: [
			{ 	name: "anyoNumExpConcatenado", 
			 	label: "label.numExp",
			 	index: "ANYONUMEXPCONCATENADO",
			 	align: "center", 
				width: "100", 
				editable: true, 
				hidden: false, 
				resizable: true, 
				sortable: true,
				classes: 'negrita',
			},
			{ 	name: $.rup.lang === 'es' ? "tipoExpedienteDescEs":"tipoExpedienteDescEu",
			 	label: "label.tipoExpediente",
			 	index: $.rup.lang === 'es' ? "TIPO_EXPEDIENTE_ES":"TIPO_EXPEDIENTE_EU",
			 	align: "center", 
				width: "100", 
				editable: false, 
				hidden: false, 
				resizable: true, 
				sortable: true
			},
			{ 	name: "titulo", 
			 	label: "label.titulo",
			 	index: "TITULO_051",
			 	align: "left", 
				width: "auto", 
				editable: false, 
				hidden: false, 
				resizable: true, 
				sortable: true,
			},
			{ 	name: $.rup.lang === 'es' ? "vipEntidadGestorEs"
					: "vipEntidadGestorEu", 
				label: "label.gestorExpediente",
				index: $.rup.lang === 'es' ? "GESTORCOLORDERES"
						: "GESTORCOLORDEREU",
				align: "left", 
				width: "auto", 
				editable: false, 
				hidden: false, 
				resizable: true, 
				sortable: true
			},
			{ 	name: "bitacoraExpediente.faseExp.id", 
				label: "label.faseExpediente",
				editable: false, 
				hidden: true, 
				resizable: true, 
				sortable: true
			}
			
        ],
        model:"Expediente",
        usePlugins:[
        	"toolbar",
       		 "filter"
         	],
        multiSort: true,
     	sortname : "ANYONUMEXPCONCATENADO",
		sortorder : "asc",
        primaryKey: ["anyo", "numExp"],
        multiplePkToken:",",
		loadOnStartUp: true,
		filter:{
			beforeFilter: function(){
				$("#requerirSubsanacion").rup_table("resetSelection");
			}
		},
	    loadComplete: function(data){ 
	    	ocultarCheckAllRupTable(this.id);
		}
	});
});