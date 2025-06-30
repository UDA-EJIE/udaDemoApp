var configCombo = {
	loadFromSelect: true
	,width: "100%"
	,ordered: false	
	,rowStriping: true
	,open: function(){
		var id = $(this).attr("id");
        $("#"+id+"-menu").width($("#"+id+"-button").width());
    }
};

function fncDetalle(e) {
	if(!$('#grupostrabajo').rup_table("isSelected")){
		e.preventDefault();
		alert($.rup.i18n.app.comun.warningSeleccion);
		return false;
	}else{
		showDetalle($('#grupostrabajo').rup_table("getActiveRowId"));
	}
}

function showDetalle(id) {
	bloquearPantalla();
	var datos = {id: id};
	$.ajax({
		type: 'POST',
		url: '/aa79bItzulnetWar/administracion/grupostrabajo/detalle',
		contentType: "application/json",
		dataType: 'html',
		data: JSON.stringify(datos),
		async: false, cache: false,
		success : function(data) {
			$("#divDetalle").html(data);
			goTo($("#divGruposTrabajo"), $("#divDetalle"));
		}
		, error: function() {
			alert($.rup.i18nParse($.rup.i18n.base,"rup_message.tituloError"));
		}
		, complete: function() {
			desbloquearPantalla();
		}
	});
}


$(function($){
	
	$('#grupostrabajo_feedback').rup_feedback({
		block : false
	});
	
	$("#idTipoExpediente_filter_table").rup_combo(configCombo);
	$("#estado_filter_table").rup_combo(configCombo);
	
	$("#dniResponsable_filter_table").rup_combo({
		source : "/aa79bItzulnetWar/administracion/grupostrabajo/getAsignadores",
		sourceParam : {
			label : "nombreCompleto",
			value : "dni"
		},	
		blank: "",
		width : "100%",
		ordered : true,
		rowStriping : true,
		open: function() {
			var id = $(this).attr("id");
			$("#"+id+"-menu").width($("#"+id+"-button").width());
		}
	});
	
	$("#codTrabajador_filter_table").rup_combo({
		source : "/aa79bItzulnetWar/personalIZO/getTraductores",
		sourceParam : {
			label : "nombreCompleto",
			value : "dni",
			style : "css"
		},
		blank: "",
		width : "100%",
		ordered : true,
		rowStriping : true,
		open : function() {
			var id = $(this).attr("id");
			$("#"+id+"-menu").width($("#"+id+"-button").width());
		}
	});
	
	$('#idEntidadSolicitante_filter_table').rup_autocomplete({
		source : "/aa79bItzulnetWar/entidad/suggest",
		menuMaxHeight:300,
		sourceParam : {
			value: "codigoCompleto",
			label : "descAmp" + $.rup_utils.capitalizedLang()
		},
		getText: false,
		open : function(event, object, arg) {
			 var tam = parseFloat($('#descripcion_filterCurso').css("padding-left"))+ parseFloat($('#descripcion_filterCurso').css("padding-right"));
			$('.ui-autocomplete.ui-menu.ui-widget.ui-widget-content.ui-corner-all:visible').css("min-width", $('#descripcion_filterCurso').innerWidth());
		}
	});
	
	$('#grupostrabajo_filter_form input[name=tipoEntidad]').change(function(){
		var parametros = $(this).val();
		$("#idEntidadSolicitante_filter_table").rup_autocomplete("option", "extraParams", {tipo:parametros});
		$("#idEntidadSolicitante_filter_table").rup_autocomplete("set", "", "");
		$('#idEntidadSolicitante_filter_table_label').removeData("tmp.loadObjects.term");
	});
	$('input[name=tipoEntidad]:first').click();
	
	$("#grupostrabajo").rup_table({
		url: "../grupostrabajo",
		toolbar: {
			 id: "grupostrabajo_toolbar"
				 ,defaultButtons:{
					 add : false
					,edit : false
					,cancel : false
					,save : false
					,clone : false
					,"delete" : false
					,filter : true
					
				 }
				  ,newButtons : [
					  {obj : {
							i18nCaption: $.rup.i18nParse($.rup.i18n.base, "rup_table.new")
							,css: "fa fa-file-o"
							,index: 1
							,right: false
						 }
						 ,json_i18n : $.rup.i18n.app.simpelMaint
						 ,click : 
							 function(e){
								showDetalle();
							}
						},
						{obj : {
							i18nCaption: $.rup.i18nParse($.rup.i18n.base, "rup_table.modify")
							,css: "fa fa-pencil-square-o"
								,index: 1
								,right: false
						}
						,json_i18n : $.rup.i18n.app.simpelMaint
						,click : fncDetalle
						}
					]
				},
		contextMenu:{
			showOperations:{
				add : true
				,edit : false
				,cancel : false
				,save : false
				,clone : false
				,"delete" : false
				,filter : true
		  }
		},
		colNames: [
			$.rup.i18nParse($.rup.i18n.app, "label.codigo"),
			$.rup.i18nParse($.rup.i18n.app, "label.tipo"),
			$.rup.i18nParse($.rup.i18n.app, "label.descripcion"),
			$.rup.i18nParse($.rup.i18n.app, "label.responsable"),
			$.rup.i18nParse($.rup.i18n.app, "label.estado"),
			$.rup.i18nParse($.rup.i18n.app, "label.numPalabras")
		],
		colModel: [
			{ 	name: "id", 
			 	label: "label.codigo",
			 	index: "id026",
				align: "center", 
				width: 5,
				isNumeric: true
			},
			{ 	name: "tipoExpedienteDesc", 
			 	label: "label.tipo",
			 	index: "tipoExpedienteDesc",
				align: "center", 
				width: 5
			},
			{ 	name: "descEu", 
			 	label: "label.descripcion",
			 	index: "descEu026",
				align: "left", 
				width: 20,
				cellattr: function (rowId, tv, rawObject, cm, rdata) { 
				    return 'style="white-space: normal;"';
				}
			},
			{ 	name: "responsable.nombreCompleto", 
			 	label: "label.responsable",
			 	index: "nombreCompleto",
				align: "left", 
				width: 30,
				cellattr: function (rowId, tv, rawObject, cm, rdata) { 
				    return 'style="white-space: normal;"';
				}
			},
			{ 	name: "estadoDesc", 
			 	label: "label.estado",
			 	index: "estadoDesc",
				align: "center", 
				width: 5
			},
			{ 	name: "palabrasDesdeHasta", 
			 	label: "label.numPalabras",
			 	index: "palabrasDesde026",
				align: "center", 
				width: 10 
			}

        ],
        model:"GruposTrabajo",
        usePlugins:[
        	"feedback",
			"toolbar",
        	"responsive",
        	"fluid",
        	"filter",
        	"report"
         	],
     	report: {
			buttons:[
				{id:"reports", i18nCaption:$.rup.i18nParse($.rup.i18n.app, "comun.exportar"), right:true,
					buttons:[
						{ i18nCaption:$.rup.i18nParse($.rup.i18n.app, "tabla.excel"), divId:"reports", css:"fa fa-file-excel-o",
							url: "../grupostrabajo/xlsxReport", click:
								function(event){
						 		if(!$("#grupostrabajo_filter_form").valid()){
						 			event.preventDefault();
						 			event.stopImmediatePropagation();
						 		}
							}
						},
						{ i18nCaption:$.rup.i18nParse($.rup.i18n.app, "tabla.pdf"), divId:"reports", css:"fa fa-file-pdf-o", 
							url: "../grupostrabajo/pdfReport", click:
								function(event){
						 		if(!$("#grupostrabajo_filter_form").valid()){
						 			event.preventDefault();
						 			event.stopImmediatePropagation();
						 		}
							}
						},
					 ]}
				]
		},
		primaryKey: "id",
		sortname: "id026",
		sortorder: "asc",
		loadOnStartUp: true,
        formEdit:{
        	detailForm: "#grupostrabajo_detail_div",
         	validate:{ 
    			rules:{}
    		}
        }
	});

	$("#grupostrabajo").on("rupTable_filter_afterCleanFilterForm", function(){
		$('input[name=tipoEntidad]:first').click();
		$("#estado_filter_table").rup_combo("setRupValue", "A");
	});
	
	$.rup_report({
		appendTo : "grupostrabajo_toolbar",
		buttons : [
			{
				id : "reportInformes",
				i18nCaption : $.rup.i18nParse($.rup.i18n.app, "boton.informes"),
				modo : "noGenerico",
				right : true,
				buttons : [
					{
						i18nCaption : $.rup.i18nParse($.rup.i18n.app, "boton.porTraductorInterprete"),
						divId : "informes",
						url : "../grupostrabajo/informes/tradInterp",
						css : "xlsx"
					}
					, {
						i18nCaption : $.rup.i18nParse($.rup.i18n.app, "boton.situacionActual"),
						divId : "informes",
						url : "../grupostrabajo/informes/situacionActual",
						css : "xlsx"
					}
					, {
						id : "responsablePDF",
						i18nCaption : $.rup.i18nParse($.rup.i18n.app, "boton.porResponsable"),
						css : "fa fa-file-pdf-o",
						right:true,
						url: "../grupostrabajo/informes/responsablePDF",
					}, {
						id : "entidadPDF",
						i18nCaption : $.rup.i18nParse($.rup.i18n.app, "boton.porEntidad"),
						css : "fa fa-file-pdf-o",
						right:true,
						url: "../grupostrabajo/informes/entidadesPDF",
					}
				]
			}
		]
	});
	
	$("#grupostrabajo").on('dblclick', fncDetalle);
	
	/*MOSTRAR EN PANTALLA*/
	$('.aa79b-content').addClass('in');

});