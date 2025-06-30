/*
 * ****************************
 * FEEDBACK - INICIO
 * ****************************
 */
function crearFeedbackDatosPagoAProveedoresConsulta(){
	$('#datosPagoAProveedoresConsulta_feedback').rup_feedback({
    	block : false,
    	gotoTop: true, 
    	delay: 3000
    });
}
/*
 * ****************************
 * FEEDBACK - FIN
 * ****************************
 */

/*
 * ****************************
 * TOOLBAR - INICIO
 * ****************************
 */
function crearToolbarDatosPagoAProveedoresConsulta(){
	$("#datosPagoAProveedoresConsulta_toolbar").rup_toolbar({
		buttons:[
			{
				i18nCaption: $.rup.i18n.app.boton.volver
				,css: "fa fa-arrow-left"
				,click : 
					function(e){
						e.preventDefault();
	                    e.stopImmediatePropagation();
	                    volverADetalleConsulta();
	                }
			}
		]
	});
}
/*
 * ****************************
 * TOOLBAR - FIN
 * ****************************
 */

/*
 * ****************************
 * TABLA - INICIO
 * ****************************
 */
function crearTablaDatosPagoProvConsulta(){
	$("#datosPagoAProveedoresConsulta").rup_table({
		url: "/aa79bItzulnetWar/consultas/datosPagoProveedoresConsulta",
		toolbar:{
			id: "datosPagoAProveedoresConsulta_toolbar"
			, defaultButtons:{
				add:false,
				edit:false,
				cancel:false,
				save:false,
				clone:false,
				"delete":false,
				filter:true
			}
		},
		colNames: [
			$.rup.i18n.app.label.tipoTarea,
			$.rup.i18n.app.label.numTotalPalabras,
			$.rup.i18n.app.label.porIva,
			$.rup.i18n.app.label.impPalabra +' (€)',
			$.rup.i18n.app.label.impTarea +' (€)',
			$.rup.i18n.app.label.impRecargoFormato,
			$.rup.i18n.app.label.impRecargoApremio,
			$.rup.i18n.app.label.impPenalRetraso,
			$.rup.i18n.app.label.totalMayus,
			$.rup.i18n.app.label.impBase,
			$.rup.i18n.app.label.impIva
		],
		colModel: [
			
			{ 	name: "idTarea", 
			 	label: "label.tipoTarea",
				align: "center", 
				index: "IDTAREA",
				width: "100", 
				editable: true, 
				hidden: false, 
				resizable: true, 
				sortable: true,
				formatter: function (cellvalue, options, rowObject) {
					return rowObject.tipoExpedienteDescEu + "-" + rowObject.idTarea
				}
			},
			{ 	name: "numPalConTramos", 
			 	label: "label.numTotalPalabras",
				align: "right", 
				index: "NUMTOTALPAL",
				width: "210", 
				editable: true,  
				hidden: false, 
				resizable: true, 
				sortable: true
			},
			{ 	name: "porIva", 
				align: "center", 
				index: "PORIVA",
				width: "70", 
				editable: true,  
				hidden: false, 
				resizable: true, 
				sortable: true
			},
			{ 	name: "importePalAplic", 
				align: "center", 
				index: "IMPORTEPALAPLIC",
				width: "100", 
				editable: true,  
				hidden: false, 
				resizable: true, 
				sortable: true
			},
			{ 	name: "importeTarea", 
				align: "center", 
				index: "IMPORTETAREA",
				width: "100", 
				editable: true,  
				hidden: false, 
				resizable: true, 
				sortable: true
			},
//			{ 	name: "indRecargoFormato", 
			{ 	name: "descImporteRecargoFormato", 
				align: "center", 
				index: "INDRECARGOFORMATO",
				width: "200", 
				editable: true,  
				hidden: false, 
				resizable: true, 
				sortable: true
//				,
//				formatter: function (cellvalue, options, rowObject) {
//					if('S'==cellvalue){
//						return rowObject.importeRecargoFormato + "€ ("+rowObject.numPalRecargoFormato+" "+$.rup.i18n.app.label.palabras+")"
//					}
//					return "-";
//				}
			},
			{ 	name: "descImporteRecargoApremio", 
				align: "center", 
				index: "INDRECARGOAPREMIO",
				width: "210", 
				editable: true,  
				hidden: false, 
				resizable: true, 
				sortable: true
//				,
//				formatter: function (cellvalue, options, rowObject) {
//					if('S'==cellvalue){
//						return rowObject.importeRecargoApremio + "€ ("+rowObject.porRecargoApremio+"%)";
//					}
//					return "-";
//				}
			},
			{ 	name: "descImportePenalizacion", 
				align: "center", 
				index: "INDPENALIZACION",
				width: "210", 
				editable: true,  
				hidden: false, 
				resizable: true, 
				sortable: true
//				,
//				formatter: function (cellvalue, options, rowObject) {
//					if('S'==cellvalue){
//						return rowObject.importePenalizacion + "€ ("+rowObject.porPenalizacion+"%)";
//					}
//					return "-";
//				}
			},
			
			
			
			
//			{ 	name: "indRecargoApremio", 
//				label: "label.numTotalPalabras",
//				align: "center", 
//				index: "INDRECARGOAPREMIO",
//				width: "210", 
//				editable: true,  
//				hidden: false, 
//				resizable: true, 
//				sortable: true,
//				formatter: function (cellvalue, options, rowObject) {
//					if('S'==cellvalue){
//						return rowObject.importeRecargoApremio + "€ ("+rowObject.porRecargoApremio+"%)";
//					}
//					return "-";
//				}
//			},
//			{ 	name: "indPenalizacion", 
//				label: "label.numTotalPalabras",
//				align: "center", 
//				index: "INDPENALIZACION",
//				width: "210", 
//				editable: true,  
//				hidden: false, 
//				resizable: true, 
//				sortable: true,
//				formatter: function (cellvalue, options, rowObject) {
//					if('S'==cellvalue){
//						return rowObject.importePenalizacion + "€ ("+rowObject.porPenalizacion+"%)";
//					}
//					return "-";
//				}
//			},
			
			
			
			
			{ 	name: "importeTotal", 
				label: "label.numTotalPalabras",
				align: "right", 
				index: "IMPORTETOTAL",
				width: "100", 
				editable: true,  
				hidden: false, 
				resizable: true, 
				sortable: true
			},
			{ 	name: "importeBase", 
				label: "label.numTotalPalabras",
				align: "right", 
				index: "IMPORTEBASE",
				width: "100", 
				editable: true,  
				hidden: false, 
				resizable: true, 
				sortable: true
			},
			{ 	name: "importeIva", 
				label: "label.numTotalPalabras",
				align: "right", 
				index: "IMPORTEIVA",
				width: "100", 
				editable: true,  
				hidden: false, 
				resizable: true, 
				sortable: true
			}
			
        ],
        model:"DatosPagoProveedores",
        usePlugins:[
        	 "toolbar",
       		 "filter",
         	],
        primaryKey: "idTarea",
		loadOnStartUp: false,
		sortname : "IDTAREA",
		sortorder : "asc"
	    ,title: false,
	    loadComplete: function(data){ 
	    		
	    },
	});
}
/*
 * ****************************
 * TABLA - FIN
 * ****************************
 */




jQuery(function($){
	jQuery.ajaxSetup({cache: false });
	$('#datosPagoProvConsultaAnyo').val(anyoExpediente);
	$('#datosPagoProvConsultaNumExp').val(idExpediente);
	$('#datosPagoProvConsultaIdTipoExp').val(tipoExp);
	crearFeedbackDatosPagoAProveedoresConsulta();
	crearToolbarDatosPagoAProveedoresConsulta();
	crearTablaDatosPagoProvConsulta();
	$("#datosPagoAProveedoresConsulta").rup_table("filter");
});
