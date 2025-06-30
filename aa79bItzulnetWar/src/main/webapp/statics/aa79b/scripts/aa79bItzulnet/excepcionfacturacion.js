var tipoEntidadSolicitante = "";
var codEntidadSolicitante = "";
var elFormulario;
var idEntidadAsocAnterior = "";
var creadoCombo = false;
var creadoComboSuccess = false;

function columnasPorEntidad() {	  
    var myGrid = $('#excepcionfacturacion');
    myGrid.jqGrid('hideCol', 'solicitantesConcatenados');
    myGrid.jqGrid('showCol','porFactura036');   
}
function columnasPorSolicitante() {	  
    var myGrid = $('#excepcionfacturacion');
    myGrid.jqGrid('showCol', 'solicitantesConcatenados');
    myGrid.jqGrid('hideCol','porFactura036');   
}
	
jQuery(function($){
	function mostrarCapaExcepciones(entidadAlta){
		if (entidadAlta == 1){
			$("[id=excepcionfacturacion_toolbar##btnAdd]").show();
		}else{
			$("[id=excepcionfacturacion_toolbar##btnAdd]").hide();
		}
		setTimeout(function() {
			$('#excepcionfacturacion_grid_div').removeClass('collapsed');
			$('#excepcionfacturacion_grid_div').addClass('in');
			}, 500);
	}
	function ocultarCapaExcepciones(){
		setTimeout(function() {
			$('#excepcionfacturacion_grid_div').addClass('collapsed');
			$('#excepcionfacturacion_grid_div').removeClass('in');
			}, 500);
	}
	 $('#excepcionfacturacion_feedback').rup_feedback({
			block : false
	});
	 
	 ocultarCapaExcepciones();
	 
	 var configCombo = {
				loadFromSelect: true
				,ordered: false	
				,rowStriping: true
				,open: function(){
					var id = $(this).attr("id");
			        $("#"+id+"-menu").width($("#"+id+"-button").width());
			    }
				,change:function(){
					ocultarCapaExcepciones();
				}
			};
	 $("#tipoExcepcion").rup_combo(configCombo);	
	 $("#tipoExcepcion").rup_combo('select',0);
	 
	 
	 
	//Tipo de excepcion y autocomplete del filtro
	$('#idEntidadSolicitante_filter_table').rup_autocomplete({
		source : "/aa79bItzulnetWar/entidad/suggestEntidadesConContactosSolicitantesConBajas",
		sourceParam : {
			value: "codigoCompleto",
			label : "descAmp" + $.rup_utils.capitalizedLang()
		},
		menuMaxHeight: "300px",
		getText: false,
		open : function() {
			jQuery('.ui-autocomplete.ui-menu.ui-widget.ui-widget-content.ui-corner-all:visible').css("min-width", jQuery('#idEntidadSolicitante_filter_table').innerWidth());
			$("#idEntidadSolicitante_filter_table_menu").css("z-index", $("#entidades_detail_div").parent().css("z-index")+1);
			$("#idEntidadSolicitante_filter_table_menu").removeClass("ui-front");
		}
	});	
	/*$("#idEntidadSolicitante_filter_table_label").on("rupAutocomplete_change", function(filter, data){	
		alert("cambiado");
		ocultarCapaExcepciones();
	});*/

	
	jQuery('input[name=tipoEntidad_filter]').change(function(){
		var parametros = $(this).val();
		$("#idEntidadSolicitante_filter_table").rup_autocomplete("option", "extraParams", {tipo:parametros});
		$("#idEntidadSolicitante_filter_table").rup_autocomplete("set", "", "");
		$('#idEntidadSolicitante_filter_table_label').removeData("tmp.loadObjects.term");
		ocultarCapaExcepciones();
	});
	jQuery('input[name=tipoEntidad_filter]:first').click();
	
	
	//buscar en facturacionexcepcion 036
	$("#facturacionentidad_filter_filterButton").button().click(function(){
		
		if ( $('#idEntidadSolicitante_filter_table').rup_autocomplete('getRupValue')!=='' ){
			$('#excepcionfacturacion_feedback').rup_feedback("close");
			var datosEntidad = $('#idEntidadSolicitante_filter_table').rup_autocomplete('getRupValue').split("_");			
			var jsonObject = 
			{
				"tipoEntidad036": datosEntidad[0],
				"idEntidad036": datosEntidad[1],
				"tipoExcepcion036": $('#tipoExcepcion').rup_combo("value") 
				
			};	
			codEntidadSolicitante = datosEntidad[1];
	  		tipoEntidadSolicitante = datosEntidad[0]; 
	  		$('#idEntidad036').val(datosEntidad[1]);
	  		$('#tipoEntidad036').val(datosEntidad[0]);
			$.ajax({
			   	 type: 'POST'
			   	 ,url: '/aa79bItzulnetWar/administracion/configuraciontarifas/excepcionfacturacion/buscaExcepcionFactEntidad'
			 	 ,dataType: 'json'
			 	 ,contentType: 'application/json' 
			 	 ,data: $.toJSON(jsonObject)			
			     ,async: false
			     ,cache: false
			   	 ,success:function(ExcepcionFacturacion){
			   		var existenExcepciones = ExcepcionFacturacion[0];
			   		var entidadAlta = ExcepcionFacturacion[1];
			   		$('#facturacionentidad_feedback').rup_feedback("close");
			   		 switch (existenExcepciones){
				   		case 0: $('#excepcionfacturacion').rup_table('clearGridData', false);
				   				break;
				   		case 1: $('#excepcionfacturacion').rup_table('filter');	
				   				break;
				   		case 2: ocultarCapaExcepciones();
				   		 		$.rup_messages("msgAlert", {
					    			message: $.rup.i18nParse($.rup.i18n.app,"mensajes.excepFacturOtroTipo"),
					    			title: $.rup.i18nParse($.rup.i18n.app,"comun.atencion")
					    		});
				   		 		break;
			   		 }
			   		 if (existenExcepciones <2){
				   			 if ($("#tipoExcepcion").rup_combo('value') === '0'){
						  			$('#titTabla').text(titXEntidad);
						  			columnasPorEntidad();
						  			$('#capaSolicitante').hide();
						  			$('#capaPorcentaje').show();
						  			if(creadoCombo){
						  				$("#capaSol").remove();
						  				$('#personasSolicitantes').rup_combo("destroy");	
						  			}
						  			
						  			creadoCombo = false;
					  		}else{ 
					  			$('#titTabla').text(txtXSolicitante);
					  			columnasPorSolicitante();
					  			$('#capaSolicitante').show();
					  			$('#capaPorcentaje').hide();
					  			creaComboMultipleSolicitantes(tipoEntidadSolicitante,codEntidadSolicitante);
					  			creadoCombo = true;
					  		}
			   				mostrarCapaExcepciones(entidadAlta);

			   		 }
			   	 }
		  	 	,error: function(){	  	 		
		  	 		$('#excepcionfacturacion_feedback').rup_feedback("set", $.rup.i18n.app.mensajes.errorLlamadaAjax, "error");
			   	 }
			 });	
		}else{
			$('#excepcionfacturacion_feedback').rup_feedback("set", $.rup.i18n.app.mensajes.seleccionEntidad, "error");
		}
    });
	
	
	$("#facturacionentidad_filter_cleanLink").click(function(){
		$("#idEntidadSolicitante_filter_table").rup_autocomplete("set", "", "");
		$('#idEntidadSolicitante_filter_table_label').removeData("tmp.loadObjects.term");
		jQuery('input[name=tipoEntidad_filter]:first').click();
		ocultarCapaExcepciones();
	});
	
	function creaBtnCancel(){
		$("#excepcionfacturacion_detail_cancel").click(function(event){
			event.preventDefault();
	        event.stopImmediatePropagation(); 
			if (elFormulario !== $("#excepcionfacturacion_detail_form").rup_form("formSerialize")){
				$.rup_messages("msgConfirm", {
					title: $.rup.i18nParse($.rup.i18n.base,"rup_table.changes"),
					message: $.rup.i18nParse($.rup.i18n.base,"rup_table.saveAndContinue"),
					OKFunction: function(){
						setTimeout(function() {
							$("#excepcionfacturacion_detail_div").rup_dialog("close");
							//eliminarMensajes();
							}, 20);
					}
				});
			}else{
				$("#excepcionfacturacion_detail_div").rup_dialog("close");
			}
		});
	}
	

	$("#excepcionfacturacion").rup_table({
		url: "/aa79bItzulnetWar/administracion/configuraciontarifas/excepcionfacturacion/listadoconjoins",
		toolbar: {
			 id: "excepcionfacturacion_toolbar"			 
			 ,defaultButtons:{
				 add : true
				,edit : true
				,cancel : false
				,save : false
				,clone : false
				,"delete" : true
				,filter : false
			 }
		},			
		colNames: [
			"",
			"",
			"",
			txtEntidadSolicitante,
			"",
			txtSolicitante,
			"",
			"",
			"",
			txtEntidadFact,
			txtContacto,			
			txtPorcFact			
		],		
		colModel: [
			{ 	name: "id036", 
			 	label: "label.id",
			 	index : "ID036",
				editable: true, 
				fixed: false, 
				hidden: true 
			},
			{ 	name: "tipoEntidad036", 
			 	label: "tipoEntidad036",
				editable: true, 
				fixed: false, 
				hidden: true 
			},
			{ 	name: "idEntidad036", 
			 	label: "idEntidad036",
				align: "", 
				editable: true, 
				fixed: false, 
				hidden: true
			},
			{ 	name: "entidadSolicitante.descAmpEu", 
			 	label: "label.entidadSolicitante",
			 	index: "ENTIDADDESCAMP",
				align: "", 
				width: 400, 
				editable: true, 
				fixed: false, 
				hidden: false, 
				resizable: true, 
				sortable: true
			},
			{ 	name: "dnisSolicConcatenados", 
			 	label: "label.id",
				align: "", 
				editable: true, 
				fixed: false, 
				hidden: true
			},
			{ 	name: "solicitantesConcatenados", 
			 	label: "label.dni",
				align: "", 
				width: 400, 
				editable: true, 
				fixed: false, 
				hidden: true, 
				resizable: true, 
				sortable: true
			},
			{ 	name: "tipoEntidadAsoc036", 
			 	label: "tipoEntidadAsoc036",
				align: "", 
				editable: true, 
				fixed: false, 
				hidden: true
			},
			{ 	name: "idEntidadAsoc036", 
			 	label: "label.entidadAsociada",
				align: "", 
				editable: true, 
				fixed: false, 
				hidden: true
			},
			{ 	name: "dniContacto036", 
			 	label: "label.contacto",
				align: "", 
				editable: true, 
				fixed: false, 
				hidden: true
			},			
			{ 	name: "entidadFactura.descAmpEu", 
			 	label: "label.entidadAsociada",
			 	index: "ENTIDADFACTDESCAMP",
				align: "", 
				width: 400, 
				editable: true, 
				fixed: false, 
				hidden: false, 
				resizable: true, 
				sortable: true
			},
			{ 	name: "contacto.nombreCompleto", 
			 	label: "label.contacto",
			 	index: "CONTACTOAPEL1",
				align: "", 
				width: 400, 
				editable: true, 
				fixed: false, 
				hidden: false, 
				resizable: true, 
				sortable: true
			},
			{ 	name: "porFactura036", 
			 	label: "label.porcentajeFactAplica",
				align: "right", 
				width: 400, 
				editable: true, 
				fixed: false, 
				hidden: false, 
				resizable: true, 
				sortable: true
			}
        ],
        title: false,
        loadComplete: function(){ 
            $('td[grid_tooltip]').each(function(){
            $(this).attr('title', $(this).attr('grid_tooltip'));
            $(this).tooltip();
            }); 
        },
        model:"ExcepcionFacturacion",
        usePlugins:[
			"formEdit",
        	"feedback",
			"toolbar",
        	"responsive",
        	"fluid",
        	"filter"
         	],
		primaryKey: "id036",
		sortname: "id036",
		sortorder: "asc",
		loadOnStartUp: false,
        formEdit:{
        	detailForm: "#excepcionfacturacion_detail_div",	        	
         	validate:{ 
    			rules:{
    				"id036":{required: true},
    				"tipoEntidad036":{required: true},
    				"idEntidad036":{required: true},
    				"tipoEntidadAsoc036":{required: true},
    				"idEntidadAsoc036":{required: true},
    				"idEntidadAsoc036_autocomplete":{required: true},
    				"personasSolicitantes.dniTrabajador037":{required: true},
    				"dniContacto036":{required: true},
    				"porFactura036":{ required: true, maxlength: 3, integer: true, range: [0, 100] }
					},
				showFieldErrorAsDefault: false,
				showErrorsInFeedback: true,
				showFieldErrorsInFeedback: false
    		},
    		addEditOptions:{
				 width:800,
				 fillDataMethod: "clientSide",
				 reloadAfterSubmit: true
				 ,beforeSubmit: function(){	
					 return validarExcepcionNueva();
				 }
			 },
			 editOptions: {				
				 reloadAfterSubmit: true,
				 mtype: "POST",
				 fillDataMethod: "clientSide",
				 ajaxEditOptions:{
					 contentType: 'application/json'
				},
				 beforeShowForm: function(){
					eliminarMensajesValidacion();
    				$('#infoTipoExcepcion').text($('#tipoExcepcion').rup_combo("label"));
					$('#infoEntidadSolicitante').text($('#idEntidadSolicitante_filter_table_label').val());
    				
    				jQuery('input[name=tipoEntidad]:first').click();
    				var id = $('#excepcionfacturacion').rup_table("getSelectedRows");
    				if(id !== ''){
    					//OJO, este campo que crear UDA es el que marca el ID que viaja al controller
    					$('#id_g').val(id); 
    					$('#id036_detail_table').val(id);
    					$('#tipoExcepcion036_detail_table').val($('#tipoExcepcion').rup_combo("value"));
    					$('#tipoEntidad036_detail_table').val($("#excepcionfacturacion").rup_table("getCol", id, "tipoEntidad036"));
    					$('#idEntidad036_detail_table').val($("#excepcionfacturacion").rup_table("getCol", id, "idEntidad036"));
    					$('#tipoEntidadAsoc036_detail_table').val($("#excepcionfacturacion").rup_table("getCol", id, "tipoEntidadAsoc036"));
    					$('#idEntidadAsoc036_detail_table').val($("#excepcionfacturacion").rup_table("getCol", id, "idEntidadAsoc036"));    
    					$('#porFactura036_detail_table').val($("#excepcionfacturacion").rup_table("getCol", id, "porFactura036"));
     					$('#idEntidadAsociada_label').val($("#excepcionfacturacion").rup_table("getCol", id, "entidadFactura.descAmpEu"));
    					$("#idEntidadAsociada").rup_autocomplete("set", $('#tipoEntidadAsoc036_detail_table').val()+"_"+$('#idEntidadAsoc036_detail_table').val(), $("#excepcionfacturacion").rup_table("getCol", id, "entidadFactura.descAmpEu"));
    					idEntidadAsocAnterior = $('#tipoEntidadAsoc036_detail_table').val()+"_"+$('#idEntidadAsoc036_detail_table').val();
    					if ($("#tipoExcepcion").rup_combo('value') === '1'){
	    					$("#personasSolicitantes").rup_combo("clear");
	    					$("#personasSolicitantes").rup_combo("setRupValue", $("#excepcionfacturacion").rup_table("getCol", id, "dnisSolicConcatenados"));
    					}
    					creaComboContacto($('#tipoEntidadAsoc036_detail_table').val(),$('#idEntidadAsoc036_detail_table').val(), $("#excepcionfacturacion").rup_table("getCol", id, "dniContacto036"));
    				}
    				creaBtnCancel();
      				return true;
      			}				 
			 },
			 addOptions: {								 		
				 beforeShowForm: function(){
					$(".error").removeClass('error');	
					$("[id$='-error']").remove();
						
					$('#infoTipoExcepcion').text($('#tipoExcepcion').rup_combo("label"));
					$('#infoEntidadSolicitante').text($('#idEntidadSolicitante_filter_table_label').val());
					jQuery('input[name=tipoEntidad]:first').click();
					$("#dniContacto036_detail_table").rup_combo("disableChild");
					idEntidadAsocAnterior = "";
					$('#porFactura036_detail_table').val('100');
					$('#tipoExcepcion036_detail_table').val($('#tipoExcepcion').rup_combo("value"));
					$('#tipoEntidad036_detail_table').val(tipoEntidadSolicitante);
					$('#idEntidad036_detail_table').val(codEntidadSolicitante);
					creaBtnCancel();
					elFormulario = $("#excepcionfacturacion_detail_form").rup_form("formSerialize");
	   				return true;
	   			}
			 }
        }
	});
	
	function creaComboMultipleSolicitantes(tipoEntidadAsoc, idEntidadAsoc, valorSeleccionar){
		if(!creadoCombo){
			$("#capaSol").remove();
			$("#capaSolicitante").html("<div id='capaSol'><label for='personasSolicitantes' class='control-label' data-i18n='label.solicitante'>"+txtSolicitante+" (*):</label>" +
					"<div class='divComboW125'>" +
						"<select id='personasSolicitantes' class='form-control' name='personasSolicitantes.dniTrabajador037'>" +
						"</select>" +
					"</div></div>");
						
		}
		if(creadoComboSuccess){
			$('#personasSolicitantes').rup_combo("destroy");
			creadoComboSuccess = false;
		}
		
		$('#personasSolicitantes').rup_combo({
			source : "/aa79bItzulnetWar/solicitante/"+tipoEntidadAsoc+"/"+idEntidadAsoc,
			sourceParam : {
				value: "dni",
				label : "nombreCompletoCentroOrganico" 
			}, 
			multiselect: true,
			readAsString: true, //Permite en un combo multiselección mediante la función setRupValue, asignar elementos seleccionados indicados mediante un string de identificadores separados por comas (“id1,id2,id3,id4”).
			submitAsJSON: true,
			width: "400",
			rowStriping: true,
			open: function(){
				var id = $(this).attr("id");
		        $("#"+id+"-menu").width($("#"+id+"-button").width());
		    },
		    onLoadSuccess: function(){
				creadoComboSuccess = true;
		    },
		    ordered: false
		});	
	}
	 
	function creaComboContacto(tipoEntidadAsoc, idEntidadAsoc, valorSeleccionar){
		if ( $('#dniContacto036_detail_table-button').length   ){
			$('#dniContacto036_detail_table').rup_combo("clear");
		}
		if (typeof(valorSeleccionar) == "undefined" || isEmpty(valorSeleccionar)){
			valorSeleccionar ='null';
		}
		$('#dniContacto036_detail_table').rup_combo({
			source : "/aa79bItzulnetWar/solicitante/comboExcepcionFact/"+tipoEntidadAsoc+"/"+idEntidadAsoc+"/"+valorSeleccionar,
			sourceParam : {
				value: "dni",
				label : "nombreCompletoCentroOrganico" 
			},
		    rowStriping: true,
			open: function(){
				var id = $(this).attr("id");
		        $("#"+id+"-menu").width($("#"+id+"-button").width());
		    },
		    onLoadSuccess: function(){ 
		    	if (typeof(valorSeleccionar) !== 'null'){
		    		$("#dniContacto036_detail_table").rup_combo("select", valorSeleccionar+'');
		    		$("#dniContacto036_detail_table").rup_combo("refresh");
		    		elFormulario = $("#excepcionfacturacion_detail_form").rup_form("formSerialize");
		    	}
		    },
		    ordered: false
		});	
	}
	
	creaComboContacto('A','0');
	
	//Autocomplete Entidad asociada
	$('#idEntidadAsociada').rup_autocomplete({
		source : "/aa79bItzulnetWar/entidad/suggestEntidadesConContactosSolicitantes",
		sourceParam : {
			value: "codigoCompleto",
			label : "descAmp" + $.rup_utils.capitalizedLang()
		},
		getText: false,
		open : function() {
			 var tam = parseFloat(jQuery('#idEntidadAsociada').css("padding-left"))+ parseFloat(jQuery('#idEntidadAsociada').css("padding-right"));
			jQuery('.ui-autocomplete.ui-menu.ui-widget.ui-widget-content.ui-corner-all:visible').css("min-width", jQuery('#idEntidadAsociada').innerWidth());
			$("#idEntidadAsociada_menu").css("z-index", $("#excepcionfacturacion_detail_div").parent().css("z-index")+1);
			$("#idEntidadAsociada_menu").removeClass("ui-front");
		}
	});	
	$("#idEntidadAsociada_label").on("rupAutocomplete_change", function(event, data){
		if (idEntidadAsocAnterior !== $('#idEntidadAsociada').rup_autocomplete('getRupValue')){
			var datosEntAsociada = $('#idEntidadAsociada').rup_autocomplete('getRupValue').split("_");	
			$('#idEntidadAsoc036_detail_table').val(datosEntAsociada[1]);
			$('#tipoEntidadAsoc036_detail_table').val(datosEntAsociada[0]);		
			if( $('#idEntidadAsociada_label').val() == ''){ 
				$('#idEntidadAsoc036_detail_table').val('');
				$('#tipoEntidadAsoc036_detail_table').val('');
				$("#dniContacto036_detail_table").rup_combo("disableChild");
			}else {
				creaComboContacto(datosEntAsociada[0],datosEntAsociada[1]);
				idEntidadAsocAnterior = $('#idEntidadAsociada').rup_autocomplete('getRupValue');
			}	
		}else{
			if( $('#idEntidadAsociada_label').val() == ''){ 
				$('#idEntidadAsoc036_detail_table').val('');
				$('#tipoEntidadAsoc036_detail_table').val('');
				$("#dniContacto036_detail_table").rup_combo("disableChild");
				idEntidadAsocAnterior = "";
			}
		}
	});
	
	jQuery('input[name=tipoEntidad]').change(function(){
		var parametros = $(this).val();
		$("#idEntidadAsociada").rup_autocomplete("option", "extraParams", {tipo:parametros});
		$("#idEntidadAsociada").rup_autocomplete("set", "", "");
		$('#idEntidadAsociada_label').removeData("tmp.loadObjects.term");
		$('#idEntidadAsoc036_detail_table').val('');
		$('#tipoEntidadAsoc036_detail_table').val('');	
		$("#dniContacto036_detail_table").rup_combo("disableChild");
		idEntidadAsocAnterior = "";
	});
	jQuery('input[name=tipoEntidad]:first').click();
	
	
	
	function validarExcepcionNueva(){
		var datos = jQuery("#excepcionfacturacion_detail_form").rup_form().formToJson();
		var error = [true];
		$.ajax({
		   	 type: 'POST'
		   	 ,url: '/aa79bItzulnetWar/administracion/configuraciontarifas/excepcionfacturacion/listadoconjoins/validarExcepcionNueva'
		 	 ,dataType: 'json'
		 	 ,contentType: 'application/json' 
		     ,data: $.toJSON(datos)			
		     ,async: false
		   	 ,success:function(codigoVuelta){
		   		 switch (codigoVuelta){
		   		 case 1: $("#excepcionfacturacion_detail_feedback").rup_feedback("set", $.rup.i18n.app.validaciones.existeExcepcion, "error");	
		   		 		error =  [false];
		   		 		break;
		   		 case 2: $("#excepcionfacturacion_detail_feedback").rup_feedback("set",$.rup.i18n.app.validaciones.excepFactSupera100, "error");	
		   		 		error =  [false];
		   		 		break;
		   		case 3: $("#excepcionfacturacion_detail_feedback").rup_feedback("set",$.rup.i18n.app.validaciones.excepFactSolicitanteRepetido, "error");	
		   		 		error =  [false];
		   		 		break;				   		 
				case 4: $("#excepcionfacturacion_detail_feedback").rup_feedback("set",$.rup.i18n.app.validaciones.excepFactSolicEntidaBaja, "error");	
				 		error =  [false];
				 		break;		
				 }
		   	 }
	   	 	,error: function(){
		   		$('#excepcionfacturacion_detail_feedback').rup_feedback("set", $.rup.i18n.app.validaciones.errorLlamadaAjax, "error");
		   		error =  [false];
		   	 }
		 });
		return error;
	}
	
	$('#idEntidadSolicitante_filter_table_label').focus();
//	setFocusFirstInput("#facturacionentidad_filter_form");
	jQuery("#excepcionfacturacion_detail_form").on("jqGridAddEditBeforeShowForm.rupTable.formEditing", function(){
		setFocusFirstInput("#excepcionfacturacion_detail_form");
	});
	
});