jQuery(function($){

	$('#tipoRelevancia_feedback').rup_feedback({
		block : false
	});
	
	
	$("#tipoRelevancia").rup_table({
		
		url: "../tiporelevancia",
		toolbar: {
			 id: "tipoRelevancia_toolbar"
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
		colNames: [ txtid,
			txtdescEu,
			txtdescEs,
			txtprioridad,
			txtestado
		],
		colModel: [
			{ 	name: "idTipoRelevancia", 
			 	label: "label.id",
			 	index: "IDTIPORELEVANCIA",
				align: "right", 
				width: 15, 
				isNumeric: true,
				editable: true, 
				fixed:false,
				hidden: false, 
				resizable: true, 
				sortable: true
			},
			{ 	name: "descRelevanciaEu", 
			 	label: "label.descEu",
			 	index: "DESCNORMEU",
				align: "left", 
				editable: true, 
				fixed:false,
				hidden: false, 
				resizable: true, 
				sortable: true,
				width: 100
			},
			{ 	name: "descRelevanciaEs", 
			 	label: "label.descEs",
			 	index: "DESCNORMES",
				align: "left", 
				editable: true, 
				fixed:false,
				hidden: false, 
				resizable: true, 
				sortable: true,
				width: 100
			},
			{ 	name: "prioridad", 
			 	label: "label.prioridad",
			 	index: "PRIORIDAD",
				align: "right", 
				editable: true, 
				fixed:false,
				hidden: false, 
				resizable: true, 
				sortable: true,
				width: 30
			},
			{ 	name : $.rup.lang == 'es' ? "estadoDescEs"
					: "estadoDescEu",
			 	label: "label.estado",
			 	index : $.rup.lang == 'es' ? "ESTADODESCES"
						: "ESTADODESCEU",
				align: "left", 
				width:30,
				editable: true,
				fixed:false,
				ruptype: "combo", 
				hidden: false, 
				resizable: true, 
				sortable: true
			}
        ],

        model:"TipoRelevancia",
        usePlugins:[
			"formEdit",
        	"feedback",
			"toolbar",
        	"responsive",
        	"filter",
        	"report"
         	],
		primaryKey: ["idTipoRelevancia"],
		sortname: "idTipoRelevancia",
		sortorder: "asc",
		loadOnStartUp: true,
		report: {
			buttons:[
				{id:"reports", i18nCaption:$.rup.i18n.app.comun.exportar, right:true,
					buttons:[
						{ i18nCaption:$.rup.i18n.app.tabla.excel, divId:"reports", css:"fa fa-file-excel-o",
							url: "../tiporelevancia/xlsxReport", click:
								function(event){
						 		if(!jQuery("#tipoRelevancia_filter_form").valid()){
						 			event.preventDefault();
						 			event.stopImmediatePropagation();
						 		}
							}
						},
						{ i18nCaption:$.rup.i18n.app.tabla.pdf, divId:"reports", css:"fa fa-file-pdf-o", 
							url: "../tiporelevancia/pdfReport", click:
								function(event){
						 		if(!jQuery("#tipoRelevancia_filter_form").valid()){
						 			event.preventDefault();
						 			event.stopImmediatePropagation();
						 		}
							}
						},
					 ]}
			]
		},
        formEdit:{
        	detailForm: "#tipoRelevancia_detail_div",
         	validate:{ 
         		showErrorsInFeedback: true,
         		showFieldErrorsInFeedback: false,
         		rules:{
         			"prioridad":{
    					required: true,
    					prioridad: true	
    				},
         			"descRelevanciaEu":{
						required: true
    					},
    				"descRelevanciaEs":{
						required: true
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
        }
		,filter:{
			clearSearchFormMode:"reset"
		 }
	});
	
	$.validator.addMethod("prioridad", function(value, element, param){
		var retorno = false;
		var jsonObject = 
		{
			"prioridad": value,
			"idTipoRelevancia": $('#idTipoRelevancia_detail_table').val()
		};	
		
		$.ajax({
		   	 type: 'POST'		   	 
		   	 ,url: '/aa79bItzulnetWar/administracion/datosmaestros/tiporelevancia/comprobarPrioridad'
		 	 ,dataType: 'json'
		 	 ,contentType: 'application/json' 
		     ,data: $.toJSON(jsonObject)			
		     ,async: false
		     ,cache: false
		   	 ,success:function(existe){
		   		if (existe) {
		   			retorno = false;
		   		} else {
		   			retorno =  true;
		   		}
		   	 }
		 });			
		return retorno;		

	}, $.rup.i18n.app.validaciones.prioridad);	 
	
	
	var estadoOption = {
			loadFromSelect: true
			,width: "150"
			,ordered: false	
			,rowStriping: true
		};
	jQuery('#estado_filter_table').rup_combo(estadoOption);
	jQuery('#estado_detail_table').rup_combo(estadoOption);
	

	
	//$("#buscadorPersonas").buscador_personas();
	//$("#buscadorPersonas").buscador_personas("open");
	
	
	// Mostrar la pantalla
	$('.aa79b-content').addClass('in');
});