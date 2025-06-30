function showEntidades(){
		$('#divGruposTrabajo').removeClass('in');
		setTimeout(function() {
			$('#divGruposTrabajo').addClass('collapsed');
			$('#divEntidades').removeClass('collapsed');
			$('#divEntidades').addClass('in');
			}, 500);
	}

jQuery(function($){	
	$('#entidades_feedback').rup_feedback({
		block : false
	});
	
$("#entidades").rup_table({
		url: "../entidadesgrupostrabajo",
		toolbar: {
			 id: "entidades_toolbar"
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
			txtEntidad
		],
		colModel: [
			{				
				name: "gruposTrabajo.descEs026", 
			 	label: "descEs026",
				align: "left", 
				width: 150, 
				editable: false, 
				ruptype: "text", 
				fixed: false, 
				hidden: false, 
				resizable: true, 
				sortable: true
			},
			{ 	name: "idEntidad027", 
			 	label: "idEntidad027",
			 	align: "left", 
				width: 150, 
				editable: false, 
				ruptype: "text", 
				fixed: false, 
				hidden: false, 
				resizable: true, 
				sortable: true
			}
        ],

        model:"EntidadesGruposTrabajo",
        usePlugins:[
			"formEdit",
        	"feedback",
			"toolbar",
        	"contextMenu",
        	"fluid",
        	"filter"
         	],
		primaryKey: "idGrupo027;idEntidad027;",
		sortname: "idEntidad027",
		sortorder: "asc",
		loadOnStartUp: true,
        formEdit:{
        	detailForm: "#entidades_detail_div",
			fillDataMethod: "clientSide",
         	validate:{ 
    			rules:{
    				"gruposTrabajo.id026":{
						required: false
    					},
    				"idEntidad027":{
						required: false
    					}
    			}
    		}
        }
	});
	
	//traemos delante el botón de volver
	jQuery('#entidades_toolbar\\#\\#'+ $.rup.i18n.app.boton.volver).addClass('izda');
	//Arreglo para fluid en ie
	jQuery('.ui-jqgrid-hbox').css({width:'100%'});
	
});