
function volverADetalleExpediente(){
	if (destroy){
		$('#idEntidadAsociada').rup_autocomplete("destroy");
		$('#contactofacturacionexp_detail_div').rup_dialog("close");
		$('#contactofacturacionexp_detail_div').rup_dialog("destroy");
		
	}
	volverADetalleExpedienteDesdeAccionesPlanif('divContactoFacturacion');
}

var destroy = false;
var tipoEntidadSolicitante = "";
var codEntidadSolicitante = "";
var elFormulario;
var idEntidadAsocAnterior = "";

var configCombo = {
	loadFromSelect: true	
	,ordered: false	
	,rowStriping: true
	,open: function(){
		var id = $(this).attr("id");
        $("#"+id+"-menu").width($("#"+id+"-button").width());
    }
};

function comprobarEstadoExp() {

    if( estado === expedCerrado){
		$('#contactofacturacionexp').on("rupTable_beforeEditRow", function(){
			return false; 
			});
		$("[id=contactofacturacionexp_toolbar##eliminar]").hide();
		$('#expCerradoDiv').show();
	} else {
		$("[id=contactofacturacionexp_toolbar##eliminar]").show();
		$('#expCerradoDiv').hide();
	}
}
	
jQuery(function($){
	$("#anyo_filtro_fact").val(anyoExpediente);
	$("#numExp_filtro_fact").val(idExpediente);
	
	$('#contactofacturacionexp_feedback').rup_feedback({
			block : false,
			delay:3000
	});
	 
	function eliminarContactoFacturacion(){
			var selectedRows = $("#contactofacturacionexp").rup_table('getSelectedRows');			
			var idDoc = selectedRows[0];
						
			jQuery.ajax({
		    	type: "DELETE",
		    	url: "/aa79bItzulnetWar/contactofacturacionexpediente/listadoconjoins/"+idDoc,
		    	dataType: "json",
		    	contentType: 'application/json',
		    	//data: $.toJSON(jsonObject),
		    	cache: false,
		    	success:function(){
		    		$('#contactofacturacionexp').rup_table('filter');
		    		$("#contactofacturacionexp_feedback").rup_feedback("set", $.rup.i18nParse($.rup.i18n.base,"rup_table.feedback.deletedOK"), "ok");
		        }, 
		   	 	error: function(){
		   	 		$("#contactofacturacionexp_feedback").rup_feedback("set", $.rup.i18nParse($.rup.i18n.app,"rup_table.feedback.deletedError"), "error");
		   	 	}
		    });
	}
	 
	$("#contactofacturacionexp").rup_table({
		url: "/aa79bItzulnetWar/contactofacturacionexpediente/listadoconjoins",
		toolbar: {
			 id: "contactofacturacionexp_toolbar"			 
			 
			 ,buttons:[
				{obj: {
						i18nCaption: $.rup.i18n.app.boton.volver
						,css: "fa fa-arrow-left btnVolver"
						,index: 1
						,right: false
   		 			}
   		 			,json_i18n : $.rup.i18n.app.simpelMaint
   		 			,click : 
   		 				function(e){
   		 					volverADetalleExpediente();
   		 					
   		 				}
				},
				{obj: {
					id: "eliminar",
					i18nCaption: $.rup.i18n.base.rup_table.del.caption
	 				,css: "fa fa-trash-o"
		 			}
		 			,json_i18n : $.rup.i18n.app.simpelMaint
		 			,click : 
		 				function(e){
	   		 			if(!$('#contactofacturacionexp').rup_table("isSelected")){
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
	             					eliminarContactoFacturacion();
	             				}
	             			});
						 }
		 				}	
				}
			]
			,defaultButtons:{
				add : ( estado === expedCerrado)?false:true
				,edit : ( estado === expedCerrado)?false:true
				,cancel : false
				,save : false
				,clone : false
				,"delete" : false
				,filter : false
			 }
			
		},			
		colNames: [
			"",
			"",
			"",
			"",
			"",
			"",
			txtEntidadAsoc,			
			txtContacto,
			txtCentroOrganico,
			"",
			"",
			txtFacturable,
			txtIVA,
			txtPorcFact			
		],		
		colModel: [
			{ 	name: "id058", 
			 	label: "label.id",
				align: "", 
				editable: true, 
				fixed: false, 
				hidden: true, 
				resizable: true, 
				sortable: true
			},
			{ 	name: "anyo", 
			 	label: "label.id",
				align: "", 
				editable: true, 
				fixed: false, 
				hidden: true, 
				resizable: true, 
				sortable: true
			},
			{ 	name: "numExp", 
			 	label: "label.dni",
				align: "", 
				editable: true, 
				fixed: false, 
				hidden: true, 
				resizable: true, 
				sortable: true
			},
			{ 	name: "tipoEntidadAsoc058", 
			 	label: "tipoEntidadAsoc058",
				align: "", 
				editable: true, 
				fixed: false, 
				hidden: true, 
				resizable: true, 
				sortable: true
			},
			{ 	name: "idEntidadAsoc058", 
			 	label: "label.entidadAsociada",
				align: "", 
				editable: true, 
				fixed: false, 
				hidden: true, 
				resizable: true, 
				sortable: true
			},
			{ 	name: "dniContacto058", 
			 	label: "label.contacto",
				align: "", 
				editable: true, 
				fixed: false, 
				hidden: true, 
				resizable: true, 
				sortable: true
			},
			{ 	name: "entidadSolicitante.descAmpEu", 
			 	label: "label.entidadAsociada",
			 	index: "ENTIDADDESCAMP",
				align: "", 
				width:"100",
				editable: true, 
				fixed: false, 
				hidden: false, 
				resizable: true, 
				sortable: true,
				cellattr: function (rowId, tv, rawObject, cm, rdata) { 
				    return 'style="white-space: normal;"';
				}
			},
			{ 	name: "contacto.nombreCompleto", 
			 	label: "label.contacto",
			 	index: "CONTACTOAPEL1",
				align: "", 
				width:"100",
				editable: true, 
				fixed: false, 
				hidden: false, 
				resizable: true, 
				sortable: true,
				cellattr: function (rowId, tv, rawObject, cm, rdata) { 
				    return 'style="white-space: normal;"';
				}
			},
			{ 	name: "contacto.centroOrganico.descAmp", 
			 	label: "label.centroOrganico",
                index: $.rup.i18n.app.label.BDCentroOrganico,
				align: "", 
				width:"100",
				editable: true, 
				fixed: false, 
				hidden: false, 
				resizable: true, 
				sortable: true,
				cellattr: function (rowId, tv, rawObject, cm, rdata) { 
				    return 'style="white-space: normal;"';
				}
			},
			{ 	name: "entidadSolicitante.facturable", 
			 	label: "label.entidadFacturable",
			 	index: "FACTURABLE",
				align: "", 
				editable: true, 
				fixed: false, 
				hidden: true, 
				resizable: true, 
				sortable: true
			},
			{ 	name: "entidadSolicitante.iva", 
			 	label: "label.aplicaIva",
			 	index: "IVA",
				align: "", 
				editable: true, 
				fixed: false, 
				hidden: true, 
				resizable: true, 
				sortable: true
			},
			{ 	name: "entidadSolicitante.facturableDesc", 
				label: "label.entidadFacturable",
				index: "FACTURABLEDESC",
				align: "center", 
				width:"70",
				editable: true, 
				fixed: false, 
				hidden: false, 
				resizable: true, 
				sortable: true
			},
			{ 	name: "entidadSolicitante.ivaDesc", 
				label: "label.aplicaIva",
				index: "IVADESC",
				align: "center", 
				editable: true, 
				fixed: false, 
				hidden: false, 
				resizable: true, 
				sortable: true,
				width: "60" 
			},
			{ 	name: "porFactura058", 
			 	label: "label.porcentajeFactAplica",
				align: "center", 
				editable: true, 
				fixed: false, 
				hidden: false, 
				resizable: true, 
				sortable: true,
				width: "100"
			}
        ],
        title: false,
        loadComplete: function(){ 
            $('td[grid_tooltip]').each(function(){
            $(this).attr('title', $(this).attr('grid_tooltip'));
            $(this).tooltip();
            }); 
            comprobarEstadoExp();
        },
        model:"ContactoFacturacionExpediente",
        usePlugins:[
			"formEdit",
        	"feedback",
			"toolbar",
        	"responsive",
        	"fluid",
        	"filter"
         	],
		primaryKey: ["id058"],
		sortname: "id058",
		sortorder: "asc",
		loadOnStartUp: true,
        formEdit:{
        	detailForm: "#contactofacturacionexp_detail_div",
			fillDataMethod: "clientSide",
         	validate:{ 
    			rules:{
    				"id058":{
						required: false
    					},
    				"tipoEntidadAsoc058":{
						required: true
    					},
    				"idEntidadAsoc058":{
						required: true
    					},
    				"idEntidadAsoc058_autocomplete":{
    					required: true
    					},
    				"dniContacto058":{
						required: false
    					},
    				"porFactura058":{ required: true, maxlength: 3, integer: true, range: [0, 100] }
    				},
    				showErrorsInFeedback: true,
    	     		showFieldErrorsInFeedback: false
    		},
    		addEditOptions:{
				 width:800,
				 fillDataMethod: "clientSide",
				 reloadAfterSubmit: true,
				 beforeSubmit: function(){	
					 return validarExcepcionNueva();
				 }
			 },
			 editOptions: {				
    			beforeShowForm: function(){	
    				$('#anyo058_detail_table').val(anyoExpediente);
					$('#numExp058_detail_table').val(idExpediente);
					destroy = true;
    				jQuery('input[name=tipoEntidadFact]:first').click();
    				var id = $('#contactofacturacionexp').rup_table("getSelectedRows");
    				if(id !== ''){
    					//OJO, este campo que crear UDA es el que marca el ID que viaja al controller
    					$('#id_g').val(id); 
    					$('#id058_detail_table').val($("#contactofacturacionexp").rup_table("getCol", id, "id058"));
    					$('#tipoEntidadAsoc058_detail_table').val($("#contactofacturacionexp").rup_table("getCol", id, "tipoEntidadAsoc058"));
    					$('#idEntidadAsoc058_detail_table').val($("#contactofacturacionexp").rup_table("getCol", id, "idEntidadAsoc058"));    					
    					$('#porFactura058_detail_table').val($("#contactofacturacionexp").rup_table("getCol", id, "porFactura058"));
     					$('#idEntidadAsociada_label').val($("#contactofacturacionexp").rup_table("getCol", id, "entidadSolicitante.descAmpEu"));
    					$("#idEntidadAsociada").rup_autocomplete("set", $('#tipoEntidadAsoc058_detail_table').val()+"_"+$('#idEntidadAsoc058_detail_table').val(), $("#contactofacturacionexp").rup_table("getCol", id, "entidadSolicitante.descAmpEu"));
    					idEntidadAsocAnterior = $('#tipoEntidadAsoc058_detail_table').val()+"_"+$('#idEntidadAsoc058_detail_table').val();
    					
    					creaComboContacto($('#tipoEntidadAsoc058_detail_table').val(),$('#idEntidadAsoc058_detail_table').val(), $("#contactofacturacionexp").rup_table("getCol", id, "dniContacto058"));
    				}
    				elFormulario = $("#contactofacturacionexp_detail_form").rup_form("formSerialize");
    				$("#contactofacturacionexp").rup_table("updateSavedData", function(savedData){
      					savedData["anyo"]=$('#anyo058_detail_table').val();
      					savedData["numExp"]=$('#numExp058_detail_table').val();
//      					savedData["dniContacto058_label"]=$('#dniContacto058_detail_table_label').val();  
//      					savedData["dniContacto058"]=$('#dniContacto058_detail_table').val(); 
      					savedData["tipoEntidadFact"]=$('input:radio[name="tipoEntidadFact"]:checked').val();		      					
      					savedData["tipoEntidadAsoc058"]=$('#tipoEntidadAsoc058_detail_table').val();
      					savedData["idEntidadAsoc058"]=$('#idEntidadAsoc058_detail_table').val();   
      					savedData["idEntidadAsoc058_autocomplete_label"]=$('#idEntidadAsociada_label').val();    
      					savedData["idEntidadAsoc058_autocomplete"]=$('#idEntidadAsociada').val();   
      					
      					savedData["id058"]=$('#id058_detail_table').val();   
      					savedData["porFactura058"]=$('#porFactura058_detail_table').val();   
      				});
      				return true;
      			}				 
			 },
			 addOptions: {								 		
				 beforeShowForm: function(){
					$('#anyo058_detail_table').val(anyoExpediente);
					$('#numExp058_detail_table').val(idExpediente);
					destroy = true;
					jQuery('input[name=tipoEntidadFact]:first').click();
					$("#dniContacto058_detail_table").rup_combo("disableChild");
					idEntidadAsocAnterior = "";
					$('#porFactura058_detail_table').val('100');
					elFormulario = $("#contactofacturacionexp_detail_form").rup_form("formSerialize");
					$("#contactofacturacionexp").rup_table("updateSavedData", function(savedData){
      					savedData["anyo"]=$('#anyo058_detail_table').val();
      					savedData["numExp"]=$('#numExp058_detail_table').val();
      					savedData["dniContacto058"]=$('#dniContacto058_detail_table').val();
      					savedData["dniContacto058_label"]='';
      					savedData["tipoEntidadFact"]=$('input:radio[name="tipoEntidadFact"]:checked').val();		      					
      					savedData["tipoEntidadAsoc058"]='';
      					savedData["idEntidadAsoc058"]='';
      					savedData["idEntidadAsoc058_autocomplete_label"]='';   
      					savedData["idEntidadAsoc058_autocomplete"]='';       				
      					
      					savedData["porFactura058"]=$('#porFactura058_detail_table').val();
      				});					
	   				return true;
	   			}
			 }
        }
	});
	
	
	function creaComboContacto(tipoEntidadAsoc, idEntidadAsoc, valorSeleccionar){
		if ($('#dniContacto058_detail_table-button').length){
			$('#dniContacto058_detail_table').rup_combo("clear");
		}
		if (typeof(valorSeleccionar) == "undefined" || isEmpty(valorSeleccionar)){
			valorSeleccionar ='null'; 
		}
		
		$('#dniContacto058_detail_table').rup_combo({
			source : "/aa79bItzulnetWar/solicitante/comboExcepcionFact/"+tipoEntidadAsoc+"/"+idEntidadAsoc+"/"+valorSeleccionar,
			sourceParam : {
				value: "dni",
				label : "nombreCompletoCentroOrganico" 
			},
		    rowStriping: true,
		    blank: "",
			open: function(){
				var id = $(this).attr("id");
		        $("#"+id+"-menu").width($("#"+id+"-button").width());
		    },
		    onLoadSuccess: function(){ 
		    	if (typeof(valorSeleccionar) !== 'null'){
		    		$("#dniContacto058_detail_table").rup_combo("select", valorSeleccionar+'');
		    		$("#dniContacto058_detail_table").rup_combo("refresh");
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
		menuMaxHeight: "300px",
		getText: false,
		open : function() {
			 var tam = parseFloat(jQuery('#idEntidadAsociada').css("padding-left"))+ parseFloat(jQuery('#idEntidadAsociada').css("padding-right"));
			jQuery('.ui-autocomplete.ui-menu.ui-widget.ui-widget-content.ui-corner-all:visible').css("min-width", jQuery('#idEntidadAsociada').innerWidth());
			$("#idEntidadAsociada_menu").css("z-index", $("#contactofacturacionexp_detail_div").parent().css("z-index")+1);
			$("#idEntidadAsociada_menu").removeClass("ui-front");
		}
	});	
	
	
	$("#idEntidadAsociada_label").on("rupAutocomplete_change", function(event, data){
		if (idEntidadAsocAnterior !== $('#idEntidadAsociada').rup_autocomplete('getRupValue')){
			var datosEntAsociada = $('#idEntidadAsociada').rup_autocomplete('getRupValue').split("_");	
			$('#idEntidadAsoc058_detail_table').val(datosEntAsociada[1]);
			$('#tipoEntidadAsoc058_detail_table').val(datosEntAsociada[0]);		
			if( $('#idEntidadAsociada_label').val() == ''){ 
				$('#idEntidadAsoc058_detail_table').val('');
				$('#tipoEntidadAsoc058_detail_table').val('');
				$("#dniContacto058_detail_table").rup_combo("disableChild");
			}else{
				creaComboContacto(datosEntAsociada[0],datosEntAsociada[1]);
				idEntidadAsocAnterior = $('#idEntidadAsociada').rup_autocomplete('getRupValue');
			}
		}else{
			if( $('#idEntidadAsociada_label').val() == ''){ 
				$('#idEntidadAsoc058_detail_table').val('');
				$('#tipoEntidadAsoc058_detail_table').val('');
				$("#dniContacto058_detail_table").rup_combo("disableChild");
				idEntidadAsocAnterior = "";
			}
		}
	});
	
	
	 
	jQuery('input[name=tipoEntidadFact]').change(function(){
		var parametros = $(this).val();
		$("#idEntidadAsociada").rup_autocomplete("option", "extraParams", {tipo:parametros});
		$("#idEntidadAsociada").rup_autocomplete("set", "", "");
		$('#idEntidadAsociada_label').removeData("tmp.loadObjects.term");
		$('#idEntidadAsoc058_detail_table').val('');
		$('#tipoEntidadAsoc058_detail_table').val('');	
		$("#dniContacto058_detail_table").rup_combo("disableChild");
		idEntidadAsocAnterior = "";
	});
	jQuery('input[name=tipoEntidadFact]:first').click();
	
	
	
	function validarExcepcionNueva(){
		$("div.error").remove();
		eliminarMensajesValidacion();
		
		var datos = jQuery("#contactofacturacionexp_detail_form").rup_form().formToJson();
		var error = [true];
		$.ajax({
		   	 type: 'POST'
		   	 ,url: '/aa79bItzulnetWar/contactofacturacionexpediente/listadoconjoins/validarContactoFacturacion'
		 	 ,dataType: 'json'
		 	 ,contentType: 'application/json' 
		     ,data: $.toJSON(datos)			
		     ,async: false
		   	 ,success:function(codigoVuelta){
		   		switch (codigoVuelta){
		   		 case 1: $("#contactofacturacionexp_detail_feedback").rup_feedback("set", $.rup.i18n.app.validaciones.existeExcepcion, "error");	
		   		 		error =  [false];
		   		 		break;
		   		 case 2: $("#contactofacturacionexp_detail_feedback").rup_feedback("set",$.rup.i18n.app.validaciones.excepFactSupera100, "error");	
		   		 		error =  [false];
		   		 		break;
		   		case 4: $("#contactofacturacionexp_detail_feedback").rup_feedback("set",$.rup.i18n.app.validaciones.excepFactSolicEntidaBaja, "error");	
				 		error =  [false];
				 		break;	
		   		 }
		   	 }
	   	 	,error: function(){
		   		$('#contactofacturacionexp_detail_feedback').rup_feedback("set", $.rup.i18n.app.validaciones.errorLlamadaAjax, "error");
		   		error =  [false];
		   	 }
		 });
		return error;
	}
	
	jQuery("#contactofacturacionexp_detail_form").on("jqGridAddEditBeforeShowForm.rupTable.formEditing", function(){
		setFocusFirstInput("#contactofacturacionexp_detail_form");
	});
	
	
	jQuery('#contactofacturacionexp_toolbar\\#\\#'+ $.rup.i18n.app.boton.volver).addClass('izda');
	
});