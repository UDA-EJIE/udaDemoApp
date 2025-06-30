jQuery(function($){

	var tipoEntidad = "";
	
	$("#Piloto").rup_table({
		
		url: "../trabajadores"
		,toolbar: {
			 id: "Piloto_toolbar"
			 ,defaultButtons:{
				 add : false
				,edit : true
				,cancel : true
				,save : true
				,clone : false
				,"delete" : false
				,filter : false
			 }
			,newButtons : [
					{obj : {
						i18nCaption: $.rup.i18n.app.boton.verTrabajador
						,css: "rup-icon rup-icon-door-out"
						,index: 1
					 }
					 ,json_i18n : $.rup.i18n.app.simpelMaint
					 ,click : 
						function(e){
							 if(jQuery('#Piloto').rup_table("getSelectedRows")[0]==null){
									e.preventDefault();
									alert($.rup.i18n.app.comun.warningSeleccion);
									return false;
							 }else{
								 var dni = "&detalle="+jQuery('#Piloto').rup_table("getSelectedRows")[0];
								 var destino ="&destino=detalleTrabajador"; 
								 window.open(x54j_url+dni+destino, '_blank');
							 }
						}
					},
					{obj : {
						i18nCaption: $.rup.i18n.app.boton.verTrabajadorDni
						,css: "rup-icon rup-icon-door-out"
						,index: 1
					 }
					 ,json_i18n : $.rup.i18n.app.simpelMaint
					 ,click : 
						function(e){
						 tipoEntidad = "T";
						 jQuery("#irDetalle_div").rup_dialog("open");
						}
					},
					{obj : {
						i18nCaption: $.rup.i18n.app.boton.verEntidad
						,css: "rup-icon rup-icon-door-out"
						,index: 1
					 }
					 ,json_i18n : $.rup.i18n.app.simpelMaint
					 ,click : 
						function(e){
						 tipoEntidad = "B";
						 jQuery("#irDetalle_div").rup_dialog("open");
						}
					}
					,
					{obj : {
						i18nCaption: $.rup.i18n.app.boton.verDepartamento
						,css: "rup-icon rup-icon-door-out"
						,index: 1
					 }
					 ,json_i18n : $.rup.i18n.app.simpelMaint
					 ,click : 
						function(e){
						 tipoEntidad = "E";
						 jQuery("#irDetalle_div").rup_dialog("open");
						}
					}
					,
					{obj : {
						i18nCaption: $.rup.i18n.app.boton.verEmpresa
						,css: "rup-icon rup-icon-door-out"
						,index: 1
					 }
					 ,json_i18n : $.rup.i18n.app.simpelMaint
					 ,click : 
						function(e){
						 tipoEntidad = "L";
						 jQuery("#irDetalle_div").rup_dialog("open");
						}
					}
		   		]
		},
		colNames: [
			"dni",
			"tipoTrabajador",
			"entdpto",
			"ape1",
			"ape2",
			"nombre",
			"descEdCasamp",
			"descEdEusamp"
		],
		colModel: [
			{ 	name: "dni", 
			 	label: "dni",
				align: "left", 
				width: 150, 
				editable: true, 
				fixed: false, 
				hidden: false, 
				resizable: true, 
				sortable: true,
				editableOnEdit: false
			},
			{ 	name: "tipoTrabajador", 
			 	label: "tipoTrabajador",
				align: "", 
				width: 150, 
				editable: true, 
				fixed: false, 
				hidden: false, 
				resizable: true, 
				sortable: true
			},
			{ 	name: "entdpto", 
			 	label: "entdpto",
				align: "", 
				width: 50, 
				editable: true, 
				fixed: false, 
				hidden: false, 
				resizable: true, 
				sortable: true
			},
			{ 	name: "ape1", 
			 	label: "ape1",
				align: "", 
				width: 150, 
				editable: true, 
				fixed: false, 
				hidden: false, 
				resizable: true, 
				sortable: true
			},
			{ 	name: "ape2", 
			 	label: "ape2",
				align: "", 
				width: 150, 
				editable: true, 
				fixed: false, 
				hidden: false, 
				resizable: true, 
				sortable: true
			},
			{ 	name: "nombre", 
			 	label: "nombre",
				align: "", 
				width: 150, 
				editable: true, 
				fixed: false, 
				hidden: false, 
				resizable: true, 
				sortable: true
			},
			{ 	name: "descEdCasamp", 
			 	label: "descEdCasamp",
				align: "left", 
				width: 150, 
				editable: true, 
				fixed: false, 
				hidden: false, 
				resizable: true, 
				sortable: true
			},
			{ 	name: "descEdEusamp", 
			 	label: "descEdEusamp",
				align: "left", 
				width: 150, 
				editable: true, 
				fixed: false, 
				hidden: false, 
				resizable: true, 
				sortable: true
			}
        ],

        model:"Trabajadores",
        usePlugins:["formEdit",
        	"feedback",
			"toolbar",
        	"fluid",
        	"filter",
        	"search"
         	],
		primaryKey: "dni",
		sortname: "dni",
		sortorder: "asc",
		loadOnStartUp: false,
        formEdit:{
        	detailForm: "#Piloto_detail_div",
			fillDataMethod: "clientSide",
         	validate:{ 
    			rules:{
    				}
    		}
        }
	});
	
	//formulario detalle hora
	jQuery('#irDetalle_form')
	.rup_validate({
		  feedback: $('#irDetalle_feedback')
		, liveCheckingErrors: false
		, showErrorsInFeedback: true
		, showFieldErrorsInFeedback: true
		, ignore: "hidden"
		, rules: {
			"entDpto":{ required:true}
		  }
	});
	
	$("#irDetalle_div").rup_dialog({
        type: $.rup.dialog.DIV,
        autoOpen: false,
        modal: true,
        resizable: true,
        width: 700,
        title: $.rup.i18n.app.piloto.dialogoDetalle,
        buttons: [{
                    text: $.rup.i18n.app.boton.aceptar,
                    click: function () {
                    	if($("#irDetalle_form").valid()){
                    		 var codigo = "&detalle="+jQuery("#entDpto_irDetalle").val(); 
                    		 if(tipoEntidad == "B"){	                    		
								 var destino ="&destino=detalleEntidad"; 
								 window.open(x54j_url+codigo+destino, '_blank');
                    		 }else if(tipoEntidad == "E"){
								 var destino ="&destino=detalleDepartamentoFact"; 
								 window.open(x54j_url+codigo+destino, '_blank');
                    		 }else if(tipoEntidad == "L"){
								 var destino ="&destino=detalleEmpresa"; 
								 window.open(x54j_url+codigo+destino, '_blank');
                    		 }else{
								 var destino ="&destino=detalleTrabajador"; 
								 window.open(x54j_url+codigo+destino, '_blank');
                    		 }
                    		 jQuery("#irDetalle_div").rup_dialog("close");
						}
                		
                    }
                }
        ]
	});
});