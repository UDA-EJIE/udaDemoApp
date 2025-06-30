jQuery(function($){
	
	fncCargarRangosAprobacionAuditoria();
	
	function fncCargarRangosAprobacionAuditoria(){
		jQuery.ajax({
		   	 type: 'POST'
		   	 ,async: false
		   	 ,url: '../pesosvaloracioncalidad/cargarRangoAprobacionAuditoria'
		   	 ,contentType : "application/json"
		   	 ,dataType : 'json'
		   	 ,cache:false
		   	 ,data: jQuery.toJSON("rangoAprobacionAuditoria") 
		   	 ,success:function(data){
		   		if(data != null){
		   			jQuery('#id_rango').val(data.id);
		   			jQuery('#val_min_aprobado').val(data.valMinAprobado);
		   			jQuery('#val_min_peligro').val(data.valMinPeligro);
		   		}
		   		else{
		   			jQuery('#id_rango').val("");
		   			jQuery('#val_min_aprobado').val("");
		   			jQuery('#val_min_peligro').val("");
		   		}
			 }
		   	 ,error: function(jqXHR, textStatus, errorThrown){}
		 });	
	}
	
	
	$("#rangoprobacionauditoria_button_save").click(function fncUpdateRangosAprobacionAuditoria(){
		if($("#rangosAprobacionAuditoria_form").valid()){
			bloquearPantalla();
			var datos = new Object();
			datos=jQuery("#rangosAprobacionAuditoria_form").rup_form().formToJson();
			jQuery.ajax({
			   	 type: 'PUT'
			   	 ,async: false
			   	 ,url: '../pesosvaloracioncalidad/updateRangosAprobacionAuditoria'
			   	 ,contentType: 'application/json'
			   	 ,dataType:'json'
			   	 ,data: $.toJSON(datos)
			   	 ,cache:false
			   	 ,success:function(data){
			   		$("#pesosvaloracioncalidad_feedback").rup_feedback("set", $.rup.i18nParse( $.rup.i18n.app,"mensajes.guardadoCorrecto"), "ok");
					desbloquearPantalla();
			   	 }
			   	 ,error: function(jqXHR, textStatus, errorThrown){
					desbloquearPantalla();
			   	 }
			 });
		}
	});
	
	$("#rangoprobacionauditoria_link_cancel").click(function(){
		fncCargarRangosAprobacionAuditoria();
	});

	
	jQuery('#rangosAprobacionAuditoria_form').rup_validate({
		rules: {
			  "valMinAprobado": {required:true, range: [0, 100]},
			  "valMinPeligro": {required:true, range: [0, 100]},
		  }
	});
	
//	***FIELDSET TABLA***
	
	$('#pesosvaloracioncalidad_feedback').rup_feedback({
		block : false
	});
	
	$("#pesosvaloracioncalidad").rup_table({
		
		url: "../pesosvaloracioncalidad",
		toolbar: {
			 id: "pesosvaloracioncalidad_toolbar"
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
				txtid,
				txtdescEu,
				txtporNivel0,
				txtporNivel1,
				txtporNivel3,
				txtporNivel5,
				txtestado
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
				hidden: true, 
				resizable: true, 
				sortable: true
			},
			{ 	name: "descEu", 
			 	label: "label.descripcion",
			 	index: "DESCEU",
				align: "", 
				width: 200, 
				editable: true, 
				fixed: true, 
				hidden: false, 
				resizable: true, 
				sortable: true,
			},
			{ 	name: "porNivel0",
			 	label: "label.porNivel0",
			 	index: "PORNIVEL0",
				align: "", 
				width: 140, 
				editable: true, 
				fixed: false, 
				hidden: false, 
				resizable: true, 
				sortable: true,
				formatter : function(cellvalue,	options, rowObject) {
					return format(rowObject.porNivel0);}
			},
			{ 	name : "porNivel1", 
				label: "label.porNivel1",
			 	index: "PORNIVEL1", 
				align: "", 
				width: 140, 
				editable: true, 
				fixed: false, 
				hidden: false, 
				resizable: true,
				sortable: true,
				formatter : function(cellvalue,	options, rowObject) {
					return format(rowObject.porNivel1);}
			},
			{ 	name : "porNivel3",
				label: "label.porNivel3",
			 	index: "PORNIVEL3",
				align: "", 
				width: 140, 
				editable: true, 
				fixed: false, 
				hidden: false, 
				resizable: true, 
				sortable: true,
				formatter : function (cellvalue,	options, rowObject) {
					return format(rowObject.porNivel3);}
			},
			{ 	name : "porNivel5",
				label: "label.porNivel5",
			 	index: "PORNIVEL5",
				align: "", 
				width: 140, 
				editable: true, 
				fixed: false, 
				hidden: false, 
				resizable: true, 
				sortable: true,
				formatter: function (cellval, opts, rowObject) {
				return format(rowObject.porNivel5);}
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
			},
        ],

        model:"PesosValoracionAuditoria",
        usePlugins:[
			"formEdit",
        	"feedback",
			"toolbar",
        	"responsive",
        	"filter"
         	],
     	loadComplete: function(){ 
     		$("#pesosvaloracioncalidad_filter_toolbar").hide();
        },
		primaryKey: "id",
		sortname:"ID",
		sortorder: "asc",
		loadOnStartUp: true,
        formEdit:{
        	detailForm: "#pesosvaloracioncalidad_detail_div",
         	validate:{ 
         		showErrorsInFeedback: true,
                showFieldErrorsInFeedback: false,
    			rules:{
    				"id":{
						required: false
    					},
					"descEu":{
						required: true,
    					},
    				"porNivel0":{
						required: true,
						decimalRange: true,
    					},
					"porNivel1":{
						required: true,
						decimalRange: true,
    					},
					"porNivel3":{
						required: true,
						decimalRange: true,
    					},
					"porNivel5":{
						required: true,
						decimalRange: true,
    					},
    				"estado":{
						required: true
    					}
				},
    		},
    	addEditOptions:{
   			 width:800,
   			 fillDataMethod: "serverSide",
   			 reloadAfterSubmit: true
   		 },
   		editOptions: {
   			 mtype: "POST",
   			 fillDataMethod: "serverSide",
   			 ajaxEditOptions:{
   				 contentType: 'application/json'
   			},
   		 },
   		 addOptions: {
   			 mtype: "POST",
   			 ajaxEditOptions:{
   				 contentType: 'application/json'
   			},
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
	
	setFocusFirstInput("#pesosvaloracioncalidad_filter_form");
    jQuery("#pesosvaloracioncalidad_detail_form").on("jqGridAddEditBeforeShowForm.rupTable.formEditing", function(){
        setFocusFirstInput("#pesosvaloracioncalidad_detail_form");
    });
});


function format(value) {
	return value.toLocaleString("es-ES") + "%";
}

jQuery.validator.addMethod("decimalRange", function validateDecimalRange(value) {
	var RE = /^100$|^100.0$|^\d{0,2}(\,\d{1})?$/;
    return (RE.test(value));
}, $.rup.i18nParse( $.rup.i18n.app,"validaciones.unDecimalCeroCien"));