jQuery(function($){
	
	$('#nivelesDeCalidad_feedback').rup_feedback({
		block : false
	});

	$("#nivelesDeCalidad").rup_table({
		
		url: "../nivelesdecalidad",
		
		toolbar: {
			 id: "nivelesDeCalidad_toolbar"
				 ,defaultButtons:{
					 add : true
					,edit : true
					,cancel : false
					,save : false
					,clone : false
					,"delete" : false
					,filter : true
					
				 }
		},
		colNames: [
			txtId,
			txtNivel,
			txtNivelDeCalidad,
			txtPorPenalizacion,
			txtEstado		
		],
		colModel: [
			{ 	name: "id", 
			 	label: "label.id",
			 	index: "ID",
				align: "right", 
				width: 30, 
				isNumeric: true,
				editable: true, 
				fixed: false, 
				hidden: false, 
				resizable: true, 
				sortable: true
			},

			{ 	name: "nivel", 
			 	label: "label.nivel",
			 	index: "NIVEL",
				align: "", 
				width: 150, 
				editable: true, 
				fixed: false, 
				hidden: false, 
				resizable: true, 
				sortable: true
			},
			
			{ 	name: "rangoIni",
			 	label: "label.nivelDeCalidad",
			 	index: "RANGOINI",
				align: "", 
				width: 150, 
				editable: true, 
				fixed: false, 
				hidden: false, 
				resizable: true, 
				sortable: true,
				formatter : function(cellvalue,
						options, rowObject) {
					return rowObject.rangoIni
								+ " - "
								+ rowObject.rangoFin;

				}
			},
			{ 	name : "porPenalizacion", 
			 	index: "PORPENALIZACION", 
				label: "label.porcentajePenalizacion",
				align: "", 
				width: 150, 
				editable: true, 
				fixed: false, 
				hidden: false, 
				resizable: true,
				sortable: true
			},
			{ 	name : $.rup.lang == 'es' ? "descEstadoEs"
					: "descEstadoEu", 
			 	index:  $.rup.lang == 'es' ? "ESTADODESCES"
						: "ESTADODESCEU", 
				label: "label.estado",
				align: "", 
				width: 150, 
				editable: true, 
				fixed: false, 
				hidden: false, 
				resizable: true, 
				sortable: true
			}
       ],

       model:"NivelesDeCalidad",
       usePlugins:[
       	"formEdit",
       	"feedback",
		"toolbar",
       	"responsive",
       	"filter",
       	"report"
        	],
		primaryKey: "id",
		sortname: "id",
		sortorder: "asc",
		loadOnStartUp: true,
		report: {
			buttons:[
				{id:"reports", i18nCaption:$.rup.i18n.app.comun.exportar, right:true,
					buttons:[
						{ i18nCaption:$.rup.i18n.app.tabla.excel, divId:"reports", css:"fa fa-file-excel-o",
							url: "../nivelesdecalidad/xlsxReport", click:
								function(event){
						 		if(!jQuery("#nivelesDeCalidad_filter_form").valid()){
						 			event.preventDefault();
						 			event.stopImmediatePropagation();
						 		}
							}
						},
						{ i18nCaption:$.rup.i18n.app.tabla.pdf, divId:"reports", css:"fa fa-file-pdf-o", 
							url: "../nivelesdecalidad/pdfReport", click:
								function(event){
						 		if(!jQuery("#nivelesDeCalidad_filter_form").valid()){
						 			event.preventDefault();
						 			event.stopImmediatePropagation();
						 		}
							}
						},
					 ]}
				]
		},
		loadComplete: function(){ 
			$("#contenFormularios").hide();
		},
		formEdit:{
       	detailForm: "#nivelesDeCalidad_detail_div",
        	validate:{ 
        		showErrorsInFeedback: true,
         		showFieldErrorsInFeedback: false,
   			rules:{
   				/*"id":{
						required: false
   					},*/
   				"rangoIni":{
						required: true, integer: true, rangoValido: true
   					},
   				"rangoFin":{
						required: true, integer: true, rangoFinMayor: "rangoIni"
   					},
   				"nivel":{
						required: true, integer: true, nivelDistinto: true
   					},
   				"porPenalizacion":{
						required: true, integer: true, range: [0, 100]
   					},
   				"estado":{
						required: false
   					}
				}
    	},
		addEditOptions:{
			 width:800,
			 fillDataMethod: "serverSide",
			 reloadAfterSubmit: true
		 },
		editOptions: {
			 mtype: "POST",
			 fillDataMethod: "serverSide",
			 reloadAfterSubmit : true,
			 ajaxEditOptions:{
				 contentType: 'application/json'
			}
		 },
		 addOptions: {
			 mtype: "POST",
			 reloadAfterSubmit : true,
			 ajaxEditOptions:{
				 contentType: 'application/json'
			}
		 }
   },
		
		filter:{
            clearSearchFormMode:"reset"
            ,validate:{
                rules:{
                   
               }
            }
         }
   
});
	
	

var estadoOption = {
		loadFromSelect: true
		,width: "150"
		,ordered: false	
		,rowStriping: true
	};
jQuery('#estado_detail_table').rup_combo(estadoOption);

setFocusFirstInput("#nivelesDeCalidad_filter_form");
jQuery("#nivelesDeCalidad_detail_form").on("jqGridAddEditBeforeShowForm.rupTable.formEditing", function(){
    setFocusFirstInput("#nivelesDeCalidad_detail_form");
});

$.validator.addMethod("rangoValido", function(){
	var error = false;
	var jsonObject = $("#nivelesDeCalidad").rup_table("getSelectedRowData");
	
	var jsonObject = 
	{
		"id": $("#id_detail_table").val(),
		"nivel": $("#nivel_detail_table").val(),
		"rangoIni": $("#rangoIni_detail_table").val(),
		"rangoFin": $("#rangoFin_detail_table").val()
	};	
	
	$.ajax({
	   	 type: 'POST'		   	 
	   	 ,url: '/aa79bItzulnetWar/administracion/datosmaestros/nivelesdecalidad/comprobarRango'
	 	 ,dataType: 'json'
	 	 ,contentType: 'application/json' 
	     ,data: $.toJSON(jsonObject)	
	     ,async: false
	     ,cache: false
	   	 ,success:function(existe){
	   		if (existe) {
	   			$("#nivelesDeCalidad_detail_feedback").rup_feedback("set", $.rup.i18n.app.validaciones.rangoConflicto, "error");			   			
	   			error =  false;
	   		} else {
	   			error =  true;
	   		}
	   	 }
   	 	,error: function(){
	   		$('#nivelesDeCalidad_detail_feedback').rup_feedback("set", $.rup.i18n.app.validaciones.errorLlamadaAjax, "error");
	   		error =  false;
	   	 }
	 });			
	return error;		

}, $.rup.i18n.app.validaciones.rangoConflicto);	

jQuery.validator.addMethod("rangoFinMayor", function(value, element, params) {
	var rangoFin = value;
	var rangoIni = $("[name='"+params+"']").val();
	if(rangoFin == ""  || rangoIni == "" || rangoFin >= rangoIni){
		return true;
	}else{
		return false;
	}
},$.rup.i18n.app.validaciones.esMayorQueDesde);

$.validator.addMethod("nivelDistinto", function(){
	var error = false;
	var jsonObject = $("#nivelesDeCalidad").rup_table("getSelectedRowData");
	
	var jsonObject = 
	{
		"id": $("#id_detail_table").val(),
		"nivel": $("#nivel_detail_table").val(),
		"rangoIni": $("#rangoIni_detail_table").val(),
		"rangoFin": $("#rangoFin_detail_table").val()
	};	
	
	$.ajax({
	   	 type: 'POST'		   	 
	   	 ,url: '/aa79bItzulnetWar/administracion/datosmaestros/nivelesdecalidad/comprobarNivel'
	 	 ,dataType: 'json'
	 	 ,contentType: 'application/json' 
	     ,data: $.toJSON(jsonObject)	
	     ,async: false
	     ,cache: false
	   	 ,success:function(existe){
	   		if (existe) {
	   			$("#nivelesDeCalidad_detail_feedback").rup_feedback("set", $.rup.i18n.app.validaciones.nivelDuplicado, "error");			   			
	   			error =  false;
	   		} else {
	   			error =  true;
	   		}
	   	 }
   	 	,error: function(){
	   		$('#nivelesDeCalidad_detail_feedback').rup_feedback("set", $.rup.i18n.app.validaciones.errorLlamadaAjax, "error");
	   		error =  false;
	   	 }
	 });			
	return error;
},$.rup.i18n.app.validaciones.nivelDuplicado);
});