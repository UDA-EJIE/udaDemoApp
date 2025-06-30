
var anyoExpediente;
var idExpediente;
var idTarea;
var idTipoTarea = 11;
var idTareaRel;
var indAlbaran;
var ejecutarTareaConsulta;
var ordenTarea;
var asignadoProveedores = true;
var tipoExp;

function mostrarTareaRevisionPagoProv() {
	var id = $('#asignadoProveedor').rup_table("getSelectedRows");
	if(id.length !== 0){
		if ($("#asignadoProveedor").rup_table("getCol", id, "idTareaRel") != "") {
			
			bloquearPantalla($.rup.i18nParse($.rup.i18n.app,"comun.cargando"));
			ordenTarea = $("#asignadoProveedor").rup_table("getCol", id, "orden");
			idTarea = $("#asignadoProveedor").rup_table("getCol", id, "idTareaRel");
			idTareaRel = $("#asignadoProveedor").rup_table("getCol", id, "idTarea");
			anyoExpediente = $("#asignadoProveedor").rup_table("getCol", id, "anyo");
			idExpediente = $("#asignadoProveedor").rup_table("getCol", id, "numExp");
			indAlbaran = $("#asignadoProveedor").rup_table("getCol", id, "indAlbaran");
			fnEjecutarTarea(idTipoTarea);
			desbloquearPantalla();
			
		} else {
			$.rup_messages("msgAlert", {
				title: $.rup.i18n.app.label.aviso,
				message: $.rup.i18n.app.mensajes.ejecutarTareaAsignadaPagoProv
			});
			return false;
		}
	} else {
		$.rup_messages("msgAlert", {
	 		title: $.rup.i18nParse($.rup.i18n.app,"boton.asignadoProveedores"),
			message: $.rup.i18n.app.comun.warningSeleccion
		});	
		return false;
	}
}

function fnEjecutarTarea(idTipoTarea){
	bloquearPantalla();
	$.rup_ajax({
	   	 url: '/aa79bItzulnetWar/tramitacionexpedientes/planificacion/planificacionexpediente/ejecutarTarea/'+ idTipoTarea  
	   	,success:function(data, textStatus, jqXHR){
	   		$("#ejecutarTareaDialog").remove();
	   		$("#ejecutarTareaDialog").rup_dialog('destroy');
	   		$("#divAsignadoProveedores").append('<div id="ejecutarTareaDialog" style="display:none"></div>');
	   		$("#ejecutarTareaDialog").html(data);
	   	    fnCrearEjecutarTareaDialog();
		   	if (indAlbaran === 'S') {
		   		ejecutarTareaConsulta = true;
		   	} else {
		   		ejecutarTareaConsulta = false;
		   	}

		   	dialogoSoloConsulta(ejecutarTareaConsulta, "ejecutarTareaDialog");
	   	    desbloquearPantalla();
	   	},
	   	error: function (XMLHttpRequest, textStatus, errorThrown){
	   		alert('Error recuperando datos del paso');
	   		desbloquearPantalla();
	   	}
	 });
	
}

function fnCrearEjecutarTareaDialog(){
	$("#ejecutarTareaDialog").rup_dialog({
		 type: $.rup.dialog.DIV,
		 title: $.rup.i18n.app.boton.ejecutarTareas,
		 autoOpen: true,
		 modal: true,
		 resizable: false,
		 width: 980
//		 ,beforeClose:function(event, ui){
//			return comprobarCambiosFormulario();
//		}
	});
}


jQuery(function($) {

	$('#asignadoProveedor_feedback').rup_feedback({
		block : false
	});
	
	$("#asignadoProveedor").rup_table({
		url : "../tareas/filtroAsignadoAproveedores",
		toolbar:{
			id: "asignadoProveedor_toolbar"
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
				i18nCaption: $.rup.i18n.app.boton.datosPagoAProveedores
				,css: "fa fa-file-o"
				,id: "datosPagoAProveedores"	
				,index: 1
				,right: false
			 }
			 ,json_i18n : $.rup.i18n.app.simpelMaint
			 ,click : 
				 function(e){
				 	e.preventDefault();
					e.stopImmediatePropagation();
					mostrarTareaRevisionPagoProv();
				}
			}
		]
		},
		colNames : ["","","","","",txtlote,txtNumExp,txtIdTarea
			, txttipoTarea, txtestado, txtFechaFin,
				txtnumPalabras,""
				],
		colModel : [
				{
					name: "anyo",
					hidden: true
				},
				{
					name: "numExp",
					hidden: true
				},
				{
					name: "idTareaRel",
					hidden: true
				},
				{
					name: "indAlbaran",
					hidden: true
				},
				{
					name: "orden",
					hidden: true
				},
				{
					name : "nombreLote",
					label : "lote",
					index : "NOMBRELOTE",
					align : "left",
					width : 120,
					editable : true,
					fixed : false,
					hidden : true,
					resizable : true,
					sortable : true
				},
				{
					name : "anyoNumExpConcatenado",
					label : "label.anyoNumExpConcatenado",
					index : "ANYONUMEXPCONCATENADO",
					align : "center",
					width : 50,
					editable : true,
					fixed : false,
					hidden : false,
					resizable : true,
					sortable : true
				},
				{
					name : "idTarea",
					label : "idTarea",
					index : "IDTAREA",
					align : "right",
					width : 50,
					editable : true,
					fixed : false,
					hidden : false,
					resizable : true,
					sortable : true
				},
				{
					name : "tipoTarea.descEs015",
					label : "tipoTarea",
					index : "TAREA",
					align : "left",
					editable : true,
					fixed : false,
					hidden : false,
					resizable : true,
					sortable : true,
					width : 100
				}, {
					name : "estadoEjecucionDesc",
					label : "estado",
					index : "ESTADOEJECUCIONDESC",
					align : "left",
					editable : true,
					fixed : false,
					hidden : false,
					resizable : true,
					sortable : true,
					width : 100
				}, 
				{
					name : "fechaHoraFin",
					label : "fechaHoraFin",
					index : "FECHAFIN",
					align : "center",
					editable : true,
					fixed : false,
					hidden : false,
					resizable : true,
					sortable : true,
					width : 80
				},
				{
					name : "numPalIZO",
					label : "numPalIZO",
					index : "NUM_PAL_IZO",
					align : "right",
					editable : true,
					fixed : false,
					hidden : false,
					resizable : true,
					sortable : true,
					width : 50
				},
				 {
					name : "nombreLoteNumTotal",
					label : "nombreLoteNumTotal",
					index : "TOTAL_NUM_PAL_IZO",
					align : "right",
					editable : true,
					fixed : false,
					hidden : true,
					resizable : true,
					sortable : true,
					width : 50
				}			
				],

		model : "TareasAsignado",
		usePlugins : [
			"feedback",
        	"toolbar",
       		 "filter",
       		 "fluid"
		],  
     	grouping: true,
		groupingView: {
		    groupField: ["nombreLoteNumTotal"],
		    groupColumnShow : [false],
		    groupText : ['<b>{0}</b>'],
		    isInTheSameGroup: function (x, y) {
		    	  return String(x).toLowerCase() === String(y).toLowerCase();
		    }
		  },
		primaryKey : [ "idTarea" ],
		sortname : "nombreLote",
		sortorder : "asc",
		loadOnStartUp : true,
		filter : {
			clearSearchFormMode : "reset"
			,validate:{
	    		rules:{
	    			"fechaIni": { date: true},			
	    			"fechaFin": { date: true}
    			}
	    	}
		}
	});

	fnFechaDesdeHasta("fechaPlanTareaDesde_filter", "fechaPlanTareaHasta_filter");

	jQuery('#estado_filter_table').rup_combo({
		loadFromSelect : true,
		ordered : false,
		rowStriping : true,
		open : function() {
			jQuery('#estado_filter_table-menu').width(jQuery('#estado_filter_table-button').innerWidth());
		}
	});
	jQuery('#tipoTarea_filter_table').rup_combo({
		loadFromSelect : true,
		ordered : false,
		rowStriping : true,
		open : function() {
			jQuery('#tipoTarea_filter_table-menu').width(jQuery('#tipoTarea_filter_table-button').innerWidth());
		}
	});

	jQuery('#lote_filter_table').rup_combo({
		source : "/aa79bItzulnetWar/lotes/lotesPorExpedientePlanificacion",
		sourceParam : {
			value : "idLote",
			label : "nombreLote"
		},
		blank : "",
		rowStriping : true,
		ordered : false,
		open : function() {
			jQuery('#lote_filter_table-menu').width(jQuery('#lote_filter_table-button').innerWidth());
		}
	});
	$('.aa79b-content').addClass('in');
});