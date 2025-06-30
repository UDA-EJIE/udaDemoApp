var selectedId;


jQuery(function($){
	$('#clonarProcesosExpediente_feedback').rup_feedback({
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
	
	$("#clonarProcesosExpediente").rup_table({
		url: "/aa79bItzulnetWar/servicios/clonacionexpedientes/procesos",
		toolbar:{
			id: "clonarProcesosExpediente_toolbar"
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
					i18nCaption: $.rup.i18n.app.boton.volver
					,css: "fa fa-arrow-left"
					,index: 1
					,right: false
				 }
					,i18nCaption: $.rup.i18n.app.simpelMaint
					,id: "volverOrigenPestana"	
					,css: "fa fa-arrow-left"
					,click : function(e){
						e.preventDefault();
						e.stopImmediatePropagation();
						volverACapaGeneralClonacion();
					}
				}
			]
		},
		colNames: [
			expClonar,
			expClonado,
			$.rup.i18n.app.label.tipo,
			$.rup.i18n.app.label.estado,
			$.rup.i18n.app.label.fechaClonacion
		],
		colModel: [
			{ 	name: "anyoNumExpOrigConcatenado", 
			 	label: "label.numExp",
			 	index: "NUM_EXP_ORIG_0A8",
			 	align: "left", 
//				width: "100", 
				editable: true, 
				hidden: false, 
				resizable: true, 
				sortable: true,
				formatter: function (cellvalue, options, rowObject) {
					return '<b style="display: block;">' + cellvalue + '</b>';
				}
			},
			{ 	name: "anyoNumExpClonConcatenado", 
				label: "label.numExp",
				index: "NUM_EXP_CLON_0A8",
				align: "left", 
//				width: "100", 
				editable: true, 
				hidden: false, 
				resizable: true, 
				sortable: true,
				formatter: function (cellvalue, options, rowObject) {
					return '<b style="display: block;">' + cellvalue + '</b>';
				}
			},
			{ 	name: "tipoExpedienteDesc",
			 	label: "label.tipoExpediente",
//			 	index: "TIPOEXPEDIENTEDESC",
			 	align: "center", 
//				width: "70", 
				editable: false, 
				hidden: false, 
				resizable: true, 
				sortable: true
			},
			{ 	name: "estadoDesc", 
			 	label: "label.estado",
			 	index: "ESTADOCLONDESC",
			 	align: "left", 
//				width: "auto", 
				editable: false, 
				hidden: false, 
				resizable: true, 
				sortable: true,
			}
			,
			{ 	name: "fechaHora", 
				label: "label.fecha",
//				index: "FECHA_CLON_0A8",
				align: "left", 
//				width: "auto", 
				editable: false, 
				hidden: false, 
				resizable: true, 
				sortable: true
			}
        ],
        model:"Clonacion",
        usePlugins:[
        	"toolbar",
       		 "filter"
         	],
        multiSort: true,
     	sortname : "FECHA_CLON_0A8",
		sortorder : "desc",
        primaryKey: ["id"],
		loadOnStartUp: true
	});
});