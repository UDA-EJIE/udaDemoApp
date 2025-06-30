jQuery(function($){

	$('#formatosFichero_feedback').rup_feedback({
		block : false
	});
	
	$("#formatosFichero").rup_table({
		
		url: "../formatosfichero",
		toolbar: {
			 id: "formatosFichero_toolbar"
				 ,defaultButtons:{
					 add : false
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
			txtformato,
			txtdescEu,
			txtdescEs,
			txtestado
		],
		colModel: [
			{ 	name: "id011", 
			 	label: "label.id",
			 	index: "ID011",
				align: "right", 
				width: 20, 
				isNumeric: true,
				editable: true, 
				fixed: false, 
				hidden: false, 
				resizable: true, 
				sortable: true
			},
			{ 	name: "formato011", 
			 	label: "label.formato",
			 	index: "FORMATO011",
				align: "", 
				width: 30, 
				editable: true, 
				fixed: false, 
				hidden: false, 
				resizable: true, 
				sortable: true
			},
			{ 	name: "descEu011", 
			 	label: "label.descEu",
			 	index: "DESCNORMEU",
				align: "", 
				width: 150, 
				editable: true, 
				fixed: false, 
				hidden: false, 
				resizable: true, 
				sortable: true
			},

			{ 	name: "descEs011", 
			 	label:"label.descEs",
			 	index: "DESCNORMES",
				align: "", 
				width: 150, 
				editable: true, 
				fixed: false, 
				hidden: false, 
				resizable: true, 
				sortable: true
			},
			{ 	name : $.rup.lang == 'es' ? "estadoDescEs"
					: "estadoDescEu",
				 	label: "label.estado",
				 	index : $.rup.lang == 'es' ? "ESTADODESCES"
							: "ESTADODESCEU",
					align: "left", 
					width:30,
					editable: true, 
					ruptype: "combo", 
					hidden: false, 
					resizable: true, 
					sortable: true
				}
	        ],

        model:"FormatosFichero",
        usePlugins:[
			"formEdit",
        	"feedback",
			"toolbar",
        	"responsive",
        	"filter",
         	],
		primaryKey: "id011",
		sortname: "id011",
		sortorder: "asc",
		loadOnStartUp: true,
        formEdit:{
        	detailForm: "#formatosFichero_detail_div",
			fillDataMethod: "serverSide",
         	validate:{ 
         		showErrorsInFeedback: true,
         		showFieldErrorsInFeedback: false,
    			rules:{
    				"id011":{
						required: false
    					},
    				"formato011":{
						required: false
    					},
    				"descEs011":{
						required: false
    					},
    				"descEu011":{
						required: false
    					},
    				"observEu011":{
						required: false,
						maxlength:4000
    					},
    				"observEs011":{
						required: false,
						maxlength:4000
    					},
    				"estado011":{
						required: false
    					}
    				}
    		},addEditOptions:{
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
	});
	var estadoOption = {
			loadFromSelect: true
			,width: "150"
			,ordered: false	
			,rowStriping: true
		};
	jQuery('#estado011_filter_table').rup_combo(estadoOption);
	jQuery('#estado011_detail_table').rup_combo(estadoOption);
	
	
	setFocusFirstInput("#formatosFichero_filter_form");
    jQuery("#formatosFichero_detail_form").on("jqGridAddEditBeforeShowForm.rupTable.formEditing", function(){
        setFocusFirstInput("#formatosFichero_detail_form");
    });
	});