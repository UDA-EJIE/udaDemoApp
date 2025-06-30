function showTrabajadores(){
		$('#divGruposTrabajo').removeClass('in');
		setTimeout(function() {
			$('#divGruposTrabajo').addClass('collapsed');
			$('#divTrabajadores').removeClass('collapsed');
			$('#divTrabajadores').addClass('in');
			}, 500);
	}

jQuery(function($){
	
	$('#trabajadoresgrupostrabajo_feedback').rup_feedback({
		block : false
	});


	$("#trabajadoresgrupostrabajo").rup_table({
		
		url: "../trabajadoresgrupostrabajo",
		toolbar: {
			 id: "trabajadoresgrupostrabajo_toolbar"
			 ,newButtons : [      
			       {obj : {
						i18nCaption: $.rup.i18n.app.boton.volver
						,css: "fa fa-arrow-left"
						,index: 0
					 }
					 ,json_i18n : $.rup.i18n.app.simpelMaint
					 ,click : 
						 function(e){
						 	showGruposTrabajo();
						}
					}
				]
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
		contextMenu:{
			showOperations:{
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
			txtGrupoTrabajo,
			txtCodTrabajador
		],
		colModel: [
			{ 	name: "idGrupo028", 
			 	label: "idGrupo028",
				align: "", 
				width: 150, 
				editable: true, 
				fixed: false, 
				hidden: false, 
				resizable: true, 
				sortable: true
			},
			{ 	name: "codTrabajador028", 
			 	label: "codTrabajador028",
				align: "", 
				width: 150, 
				editable: true, 
				fixed: false, 
				hidden: false, 
				resizable: true, 
				sortable: true
			}
        ],

        model:"TrabajadoresGruposTrabajo",
        usePlugins:[
			"formEdit",
        	"feedback",
			"toolbar",
        	"contextMenu",
        	"responsive",
        	"filter",
        	"search"
         	],
		primaryKey: "idGrupo028",
		sortname: "idGrupo028",
		sortorder: "asc",
		loadOnStartUp: true,
        formEdit:{
        	detailForm: "#trabajadoresgrupostrabajo_detail_div",
			fillDataMethod: "clientSide",
         	validate:{ 
    			rules:{
    				"idGrupo028":{
						required: false
    					},
    				"codTrabajador028":{
						required: false
    					}
    				}
    		}
        }
	});
	
	
	//traemos delante el botón de volver
	jQuery('#trabajadoresgrupostrabajo_toolbar\\#\\#'+ $.rup.i18n.app.boton.volver).addClass('izda');
	//Arreglo para fluid en ie
	jQuery('.ui-jqgrid-hbox').css({width:'100%'});
});