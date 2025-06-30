var count = 0;
var nuevoIndexSeleccionado;
var idPanelSelected;
var cambios = false;
var vipStored = false;
var normalStored = false;
var vipStoredProvExt = false;
var normalStoredProvExt = false;
var configToolbar = {
	buttons: [
		{
			id : "guardar"+(count++),
			i18nCaption : $.rup.i18nParse($.rup.i18n.base, "rup_global.save"),
			css : "fa fa-floppy-o",
			click : function(event) {
				event.preventDefault();
				event.stopImmediatePropagation();
				$(this).parent().siblings("form").submit();
			}
		}
	]
};
// configuracion de columnas de tablas
var configHorasPrevistasSegColums=[
	"palSegConcor100","palSegConcor9599","palSegConcor8594","palSegConcor084","palTradSeg","palRevSeg"
];
var configHorasPrevistasHoraColums=[
	"palHoraConcor100","palHoraConcor9599","palHoraConcor8594","palHoraConcor084","palTradHora","palRevHora"
];
var configHorasPrevistasProvExtSegColums=[
	"palSegConcor100","palSegConcor9599","palSegConcor8594","palSegConcor084","palTradSeg","palRevSeg"
];
var configHorasPrevistasProvExtHoraColums=[
	"palHoraConcor100","palHoraConcor9599","palHoraConcor8594","palHoraConcor084","numPalTradHora","palReExtHora"
];
var configForm = {
	type: "POST"
	, feedback: $("#datosBasicos_feedback")
	, dataType: 'json'
	, contentType: 'application/json'
	, success: function(xhr) {
		$("#datosBasicos_feedback").rup_feedback('option',{delay:5000, gotoTop:true});
		$("#datosBasicos_feedback").rup_feedback("set", $.rup.i18nParse($.rup.i18n.app, "mensajes.guardadoCorrecto"), 'ok');
		cambios = false;
	}
	, error: function() {
		$("#datosBasicos_feedback").rup_feedback("set", $.rup.i18nParse($.rup.i18n.app, "mensajes.errorGuardandoDatos"), 'error');
	}
};

/**
 * obtenemos el id del form con el id del accordion
 */
function obtenerIdForm(id){
		if( 0 == id ){
			return "plazoMinimoRequerido_form";
		}else if( 1 == id ){
			return  "calculoHorasPrevistas_form";
		}else if( 2 == id ){
			return "calculoHorasPrevistasProveExt_form";
		}else if( 3 == id ){
			return "calculoPresupuestoTrados_form";
		}else if( 4 == id ){
			return "perfilSolicitante_form";
		}else if( 5 == id ){
			return "pagoProveedores_filter_form";
		}else if( 6 == id ){
			return "configEmailsAviso_form";
		}else if( 7 == id ){
			return "libroRegistro_form";
		}
	return "";
}

/**
 * obtenemos el id real del accordion con el id obtenido del evento beforeActivate
 */
function obtenerValorRealIdAccordionSeleccionado(indexSeleccionado){
	if(0 == indexSeleccionado || 1 == indexSeleccionado){
		return 0;
	}else if(2 == indexSeleccionado || 3 == indexSeleccionado){
		return 1;
	}else if(4 == indexSeleccionado || 5 == indexSeleccionado){
		return 2;
	}else if(6 == indexSeleccionado || 7 == indexSeleccionado){
		return 3;
	}else if(8 == indexSeleccionado || 9 == indexSeleccionado){
		return 4;
	}else if(10 == indexSeleccionado || 11 == indexSeleccionado){
		return 5;
	}else if(12 == indexSeleccionado || 13 == indexSeleccionado){
		return 6;
	}else if(14 == indexSeleccionado || 15 == indexSeleccionado){
		return 7;
	}
	return 8;
}

/**
 * obtenemos los datos de la tabla de configuracion de horas previstas
 */
function getConfigHorasPrevistasTableData(){
	var id = "configHorasPrevistas";
	var list = new Array();
	var ids = $("#"+id).rup_table("getDataIDs");
	ids.forEach(function(rowId, index){
		
		var tipoRel = $("#"+id).rup_table("getCol", rowId, "tipoRelevancia.idTipoRelevancia");
		var nivUsuarioFormatted = $("#"+id).rup_table("getCol", rowId, "nivUsuario");
		var nivUsuario = "";
		if(nivUsuarioFormatted == $.rup.i18n.app.label.usuarioVip){
			nivUsuario = "V";
		}else if(nivUsuarioFormatted == $.rup.i18n.app.label.usuarioNormal){
			nivUsuario = "N";
		}
		var palSegConcor100 = parseDecimal($("#"+rowId+"_"+configHorasPrevistasSegColums[0]).val());
		var palHoraConcor100 = parseDecimal($("#"+id).rup_table("getCol", rowId, configHorasPrevistasHoraColums[0]));
		var palSegConcor9599 = parseDecimal($("#"+rowId+"_"+configHorasPrevistasSegColums[1]).val());
		var palHoraConcor9599 = parseDecimal($("#"+id).rup_table("getCol", rowId, configHorasPrevistasHoraColums[1]));
		var palSegConcor8594 = parseDecimal($("#"+rowId+"_"+configHorasPrevistasSegColums[2]).val());
		var palHoraConcor8594 = parseDecimal($("#"+id).rup_table("getCol", rowId, configHorasPrevistasHoraColums[2]));
		var palSegConcor084 = parseDecimal($("#"+rowId+"_"+configHorasPrevistasSegColums[3]).val());
		var palHoraConcor084 = parseDecimal($("#"+id).rup_table("getCol", rowId, configHorasPrevistasHoraColums[3]));
		var palTradSeg = parseDecimal($("#"+rowId+"_"+configHorasPrevistasSegColums[4]).val());
		var palTradHora =parseDecimal( $("#"+id).rup_table("getCol", rowId, configHorasPrevistasHoraColums[4]));
		var palRevSeg = parseDecimal($("#"+rowId+"_"+configHorasPrevistasSegColums[5]).val());
		var palRevHora = parseDecimal($("#"+id).rup_table("getCol", rowId, configHorasPrevistasHoraColums[5]));
		var tipoRelevancia = {
			"idTipoRelevancia": tipoRel
		};
		list.push({
			"tipoRelevancia": tipoRelevancia,
			"nivUsuario": nivUsuario,
			"palSegConcor100": palSegConcor100,
			"palHoraConcor100": palHoraConcor100,
			"palSegConcor9599": palSegConcor9599,
			"palHoraConcor9599": palHoraConcor9599,
			"palSegConcor8594": palSegConcor8594,
			"palHoraConcor8594": palHoraConcor8594,
			"palSegConcor084": palSegConcor084,
			"palHoraConcor084": palHoraConcor084,
			"palTradSeg": palTradSeg,
			"palTradHora": palTradHora,
			"palRevSeg": palRevSeg,
			"palRevHora": palRevHora
		});
	});
	return list;
}
/**
 * obtenemos los datos de la tabla de configuracion de horas previstas de proveedor externo
 */
function getConfigHorasPrevistasProvExtTableData(){
	var id = "configHorasPrevistasProveExt";
	var list = new Array();
	var ids = $("#"+id).rup_table("getDataIDs");
	ids.forEach(function(rowId, index){
		
		var nivUsuarioFormatted = $("#"+id).rup_table("getCol", rowId, "nivUsuario");
		var nivUsuario = "";
		if(nivUsuarioFormatted == $.rup.i18n.app.label.usuarioVip){
			nivUsuario = "V";
		}else if(nivUsuarioFormatted == $.rup.i18n.app.label.usuarioNormal){
			nivUsuario = "N";
		}
		var palSegConcor100 = parseDecimal($("#"+rowId+"_"+configHorasPrevistasProvExtSegColums[0]).val());
		var palHoraConcor100 = parseDecimal($("#"+id).rup_table("getCol", rowId, configHorasPrevistasProvExtHoraColums[0]));
		var palSegConcor9599 = parseDecimal($("#"+rowId+"_"+configHorasPrevistasProvExtSegColums[1]).val());
		var palHoraConcor9599 = parseDecimal($("#"+id).rup_table("getCol", rowId, configHorasPrevistasProvExtHoraColums[1]));
		var palSegConcor8594 = parseDecimal($("#"+rowId+"_"+configHorasPrevistasProvExtSegColums[2]).val());
		var palHoraConcor8594 = parseDecimal($("#"+id).rup_table("getCol", rowId, configHorasPrevistasProvExtHoraColums[2]));
		var palSegConcor084 = parseDecimal($("#"+rowId+"_"+configHorasPrevistasProvExtSegColums[3]).val());
		var palHoraConcor084 = parseDecimal($("#"+id).rup_table("getCol", rowId, configHorasPrevistasProvExtHoraColums[3]));
		var palTradSeg = parseDecimal($("#"+rowId+"_"+configHorasPrevistasProvExtSegColums[4]).val());
		var palTradHora = parseDecimal($("#"+id).rup_table("getCol", rowId, configHorasPrevistasProvExtHoraColums[4]));
		var palRevSeg = parseDecimal($("#"+rowId+"_"+configHorasPrevistasProvExtSegColums[5]).val());
		var palRevHora = parseDecimal($("#"+id).rup_table("getCol", rowId, configHorasPrevistasProvExtHoraColums[5]));
		list.push({
			"nivUsuario": nivUsuario,
			"palSegConcor100": palSegConcor100,
			"palHoraConcor100": palHoraConcor100,
			"palSegConcor9599": palSegConcor9599,
			"palHoraConcor9599": palHoraConcor9599,
			"palSegConcor8594": palSegConcor8594,
			"palHoraConcor8594": palHoraConcor8594,
			"palSegConcor084": palSegConcor084,
			"palHoraConcor084": palHoraConcor084,
			"palTradSeg": palTradSeg,
			"numPalTradHora": palTradHora,
			"palRevSeg": palRevSeg,
			"palReExtHora": palRevHora
		});
	});
	return list;
}
/**
 * devuelve el nombre de la columna a la que insertar el nuevo valor
 */
function getUpdatedCellsNextColName(updatedCellColName, jqGridId){
	if(updatedCellColName != null && jqGridId != null){
		if("configHorasPrevistas" == jqGridId){
			if(updatedCellColName == 'palSegConcor100') return 'palHoraConcor100';
			if(updatedCellColName == 'palSegConcor9599') return 'palHoraConcor9599';
			if(updatedCellColName == 'palSegConcor8594') return 'palHoraConcor8594';
			if(updatedCellColName == 'palSegConcor084') return 'palHoraConcor084';
			if(updatedCellColName == 'palTradSeg') return 'palTradHora';
			if(updatedCellColName == 'palRevSeg') return 'palRevHora';
		}else if("configHorasPrevistasProveExt" == jqGridId){
			if(updatedCellColName == 'palSegConcor100') return 'palHoraConcor100';
			if(updatedCellColName == 'palSegConcor9599') return 'palHoraConcor9599';
			if(updatedCellColName == 'palSegConcor8594') return 'palHoraConcor8594';
			if(updatedCellColName == 'palSegConcor084') return 'palHoraConcor084';
			if(updatedCellColName == 'palTradSeg') return 'numPalTradHora';
			if(updatedCellColName == 'palRevSeg') return 'palReExtHora';
		}
		
	}
	return null;
}
/**
 * devuelve el nuevo valor a insertar en la columna correspondiente
 */
function getNewHourColValue(val){
	if(val == 0) return addDecimalsFormatEnInput("0,00", 2);
	var retVal = parseDecimal(val);
	if(isNaN(retVal)){
		return addDecimalsFormatEnInput("0,00", 2);
	}else{
		return addDecimalsFormatEnInput((3600/(retVal)).toString(), 2) ;
	}
}
/**
 * introduce el nuevo valor en la celda correspondiente
 */
function setNewValueInHourColumn(input,rowid,name, jqGridId){
	val = input.value;
	var regex = /(?=.*?\d)^\$?(([1-9]\d{0,2}(\.\d{3})*)|\d+)?(\,\d{1,6})?$/
	if(!val){
		$(input)[0].textContent = "0,000";
		$(input)[0].value = "0,000";
		$("#"+jqGridId).jqGrid("setCell", rowid, getUpdatedCellsNextColName(name, jqGridId), getNewHourColValue(0));
		alert($.rup.i18nParse($.rup.i18n.app,"mensajes.errorCampoVacioPorDefectoACero"))
	}else if(isNaN(parseDecimal(val)) || !regex.test(val) || !(parseDecimal(val) >= 0 && parseDecimal(val) < 100)){
		$(input)[0].textContent = "0,000";
		$(input)[0].value = "0,000";
		$("#"+jqGridId).jqGrid("setCell", rowid, getUpdatedCellsNextColName(name, jqGridId), getNewHourColValue(0));
		alert($.format($.rup.i18nParse($.rup.i18n.app, "mensajes.errorValorErroneoCampoPorDefectoACero"), "0,000", "99,999"))
	}else if(parseDecimal(val) == 0){
		$(input)[0].textContent = "0,000";
		$(input)[0].value = "0,000";
		$("#"+jqGridId).jqGrid("setCell", rowid, getUpdatedCellsNextColName(name, jqGridId), getNewHourColValue(0));
	}else{
		$("#"+jqGridId).jqGrid("setCell", rowid, getUpdatedCellsNextColName(name, jqGridId), getNewHourColValue(val));
	}
	
}
/**
 * crea el input de la celda
 */
function crearInputTabla(cellval, opts, rowObject, jqGridId){
	// creamos el input pasando como parametros los datos del input, para recuperar el nuevo valor, el id de fila, el name de la columna y el id de tabla
	val = 0;
	if(cellval){
		val = cellval;
	}
	return '<input id="' + opts.rowId + '_' + opts.colModel.name + '" name="editableTableInput" class="form-control decimal" data-decim="3" type="text" maxlength="6" data-rowId="' + opts.rowId + '" data-propname="' + opts.colModel.name + '" data-gridid="' + jqGridId + '" value="'+val+'")">';
}
/**
 * para obtener el valor nuevo del input de la tabla anyadimos un on blur, ya que asi funciona tambien en ie
 */
function addOnchangeToHorasPrevistasInputs(){
	id="editableTableInput";
	$('input[name="'+id+'"]').on('blur', function() {
		$(this);
		var rowId = this.dataset.rowid;
		var name = this.dataset.propname;
		var jqGridId = this.dataset.gridid;
		setNewValueInHourColumn(this,rowId,name, jqGridId);
	});
}
/**
 * funcion para agrupar las filas por vip o usuario normal
 */
function rowspanConfigHorasPrevistasFunction(rowId, val, rawObject){
	if(val == $.rup.i18n.app.label.usuarioVip){
		if(vipStored){
            return ' style="display:none"';
		}else{
			vipStored = true;
			return ' rowspan="'+tiposRelevanciaCount+'"';
		}
	}else if(val == $.rup.i18n.app.label.usuarioNormal){
	    if(normalStored){
            return ' style="display:none"';
		}else{
			normalStored = true;
			return ' rowspan="'+tiposRelevanciaCount+'"';
		}
	}
}
/**
 */
function crearTablaConfigHorasPrevistas(){
	$("#configHorasPrevistas").rup_table({
		url : "/aa79bItzulnetWar/confighorasprevistas/datosbasicos",
		feedback: {id:"#datosBasicos_feedback"},
		toolbar: {
			 id: "configHorasPrevistas_toolbar"
				 ,defaultButtons:{
					 add : false
					,edit : false
					,cancel : false
					,save : false
					,clone : false
					,"delete" : false
					,filter : false
				 }
				},
		colNames : [ 
			"",
			"",
			$.rup.i18n.app.label.usuario,
			$.rup.i18n.app.label.tipoRelevancia,
			$.rup.i18n.app.label.palabraSeg,
			$.rup.i18n.app.label.palabraHora,
			$.rup.i18n.app.label.palabraSeg,
			$.rup.i18n.app.label.palabraHora,
			$.rup.i18n.app.label.palabraSeg,
			$.rup.i18n.app.label.palabraHora,
			$.rup.i18n.app.label.palabraSeg,
			$.rup.i18n.app.label.palabraHora,
			$.rup.i18n.app.label.palabraSeg,
			$.rup.i18n.app.label.palabraHora,
			$.rup.i18n.app.label.palabraSeg,
			$.rup.i18n.app.label.palabraHora
			],
		colModel : [ 
		{name : "id", hidden : true},
		{name : "tipoRelevancia.idTipoRelevancia", hidden : true},
		{
			name : "nivUsuario",
			align : "center",
			index: "NIVELUSUARIO002",
			width : 50,
			sortable: false,
			formatter: function(cellval){
				if(cellval){
					if(cellval == 'V'){
						return $.rup.i18n.app.label.usuarioVip;
					}else if(cellval == 'N'){
						return $.rup.i18n.app.label.usuarioNormal;
					}
				}
			},
			cellattr: rowspanConfigHorasPrevistasFunction
		}, 
		{
			name : $.rup.lang === "es" ? "tipoRelevancia.descRelevanciaEs"
									 : "tipoRelevancia.descRelevanciaEu",
			sortable: false,
			align : "left",
			width : 50
		},
		{
			name : configHorasPrevistasSegColums[0],	//"palSegConcor100"
			sortable: false,
			align : "left",
			width : 50,
			formatter: function (cellval, opts, rowObject, action){
				return crearInputTabla(cellval, opts, rowObject, "configHorasPrevistas");
			}
		},
		{
			name : configHorasPrevistasHoraColums[0],	//"palHoraConcor100"
			sortable: false,
			align : "left",
			width : 50
		},
		{
			name : configHorasPrevistasSegColums[1],	//"palSegConcor9599"
			sortable: false,
			align : "left",
			width : 50,
			formatter: function (cellval, opts, rowObject, action){
				return crearInputTabla(cellval, opts, rowObject, "configHorasPrevistas");
			}
		},
		{
			name : configHorasPrevistasHoraColums[1],	//"palHoraConcor9599"
			sortable: false,
			align : "left",
			width : 50
		},
		{
			name : configHorasPrevistasSegColums[2],	//"palSegConcor8594"
			sortable: false,
			align : "left",
			width : 50,
			formatter: function (cellval, opts, rowObject, action){
				return crearInputTabla(cellval, opts, rowObject, "configHorasPrevistas");
			}
		},
		{
			name : configHorasPrevistasHoraColums[2],	//"palHoraConcor8594"
			sortable: false,
			align : "left",
			width : 50
		},
		{
			name : configHorasPrevistasSegColums[3],	//"palSegConcor084"
			sortable: false,
			align : "left",
			width : 50,
			formatter: function (cellval, opts, rowObject, action){
				return crearInputTabla(cellval, opts, rowObject, "configHorasPrevistas");
			}
		},
		{
			name : configHorasPrevistasHoraColums[3],	//"palHoraConcor084"
			sortable: false,
			align : "left",
			width : 50
		},
		{
			name : configHorasPrevistasSegColums[4],	//"palTradSeg"
			sortable: false,
			align : "left",
			width : 50,
			formatter: function (cellval, opts, rowObject, action){
				return crearInputTabla(cellval, opts, rowObject, "configHorasPrevistas");
			}
		},
		{
			name : configHorasPrevistasHoraColums[4],	//"palTradHora"
			sortable: false,
			align : "left",
			width : 50
		},
		{
			name : configHorasPrevistasSegColums[5],	//"palRevSeg"
			sortable: false,
			align : "left",
			width : 50,
			formatter: function (cellval, opts, rowObject, action){
				return crearInputTabla(cellval, opts, rowObject, "configHorasPrevistas");
			}
		},
		{
			name : configHorasPrevistasHoraColums[5],	//"palRevHora"
			sortable: false,
			align : "left",
			width : 50
		}
		
		],
		model : "ConfigHorasPrevistas",
		usePlugins : [ 
			"toolbar",
			"responsive", 
			"fluid" 
			,"filter"
			, "feedback"
			],
		primaryKey : ["nivUsuario", $.rup.lang === "es" ? "tipoRelevancia.descRelevanciaEs"
									 					: "tipoRelevancia.descRelevanciaEu"],
		multiplePkToken:"_",
		sortname : "NIVELUSUARIO002",
		sortorder : "desc",
		loadOnStartUp : true,
		loadComplete: function(data){ 
			$("#configHorasPrevistas_toolbar").hide();
			$("#configHorasPrevistas_pager").hide();
			crearTablaConfigHorasPrevistasProvExt();
		},
		beforeSelectRow: function (){
			return false;
		}
	});
	jQuery("#configHorasPrevistas").jqGrid('setGroupHeaders', {
				useColSpanStyle: false, 
			  	groupHeaders:[
					{startColumnName: configHorasPrevistasSegColums[0], numberOfColumns: 8, titleText: $.rup.i18n.app.label.izoTraPalHoraEntre },
					{startColumnName: configHorasPrevistasSegColums[4], numberOfColumns: 2, titleText: $.rup.i18n.app.label.izoTradPalHora },
					{startColumnName: configHorasPrevistasSegColums[5], numberOfColumns: 2, titleText: $.rup.i18n.app.label.izoRevPalHora },
			  ]
			});
	jQuery("#configHorasPrevistas").jqGrid('setGroupHeaders', {
				useColSpanStyle: true, 
			  	groupHeaders:[
			  		{startColumnName: configHorasPrevistasSegColums[0], numberOfColumns: 2, titleText: $.rup.i18n.app.label.tramosConcor100 },
			  		{startColumnName: configHorasPrevistasSegColums[1], numberOfColumns: 2, titleText: $.rup.i18n.app.label.tramosConcor9599 },
					{startColumnName: configHorasPrevistasSegColums[2], numberOfColumns: 2, titleText: $.rup.i18n.app.label.tramosConcor8594 },
					{startColumnName: configHorasPrevistasSegColums[3], numberOfColumns: 2, titleText: $.rup.i18n.app.label.tramosConcor084 }
			  ]
			});
	
}
/**
 */
function crearTablaConfigHorasPrevistasProvExt(){
	$("#configHorasPrevistasProveExt").rup_table({
		url : "/aa79bItzulnetWar/confighorasprevistas/provExt/datosbasicos",
		feedback: {id:"#datosBasicos_feedback"},
		toolbar: {
			 id: "configHorasPrevistasProveExt_toolbar"
				 ,defaultButtons:{
					 add : false
					,edit : false
					,cancel : false
					,save : false
					,clone : false
					,"delete" : false
					,filter : false
				 }
				},
		colNames : [ 
			"",
			$.rup.i18n.app.label.usuario,
			$.rup.i18n.app.label.palabraSeg,
			$.rup.i18n.app.label.palabraHora,
			$.rup.i18n.app.label.palabraSeg,
			$.rup.i18n.app.label.palabraHora,
			$.rup.i18n.app.label.palabraSeg,
			$.rup.i18n.app.label.palabraHora,
			$.rup.i18n.app.label.palabraSeg,
			$.rup.i18n.app.label.palabraHora,
			$.rup.i18n.app.label.palabraSeg,
			$.rup.i18n.app.label.palabraHora,
			$.rup.i18n.app.label.palabraSeg,
			$.rup.i18n.app.label.palabraHora
			],
		colModel : [ 
		{name : "id", hidden : true},
		{
			name : "nivUsuario",
			align : "center",
			index: "NIVELUSUARIO045",
			width : 50,
			sortable: false,
			formatter: function(cellval){
				if(cellval){
					if(cellval == 'V'){
						return $.rup.i18n.app.label.usuarioVip;
					}else if(cellval == 'N'){
						return $.rup.i18n.app.label.usuarioNormal;
					}
				}
			}
		},
		{
			name : configHorasPrevistasProvExtSegColums[0],	//"palSegConcor100"
			sortable: false,
			align : "left",
			width : 50,
			formatter: function (cellval, opts, rowObject, action){
				return crearInputTabla(cellval, opts, rowObject,"configHorasPrevistasProveExt");
			}
		},
		{
			name : configHorasPrevistasProvExtHoraColums[0],	//"palHoraConcor100"
			sortable: false,
			align : "left",
			width : 50
		},
		{
			name : configHorasPrevistasProvExtSegColums[1],	//"palSegConcor9599"
			sortable: false,
			align : "left",
			width : 50,
			formatter: function (cellval, opts, rowObject, action){
				return crearInputTabla(cellval, opts, rowObject,"configHorasPrevistasProveExt");
			}
		},
		{
			name : configHorasPrevistasProvExtHoraColums[1],	//"palHoraConcor9599"
			sortable: false,
			align : "left",
			width : 50
		},
		{
			name : configHorasPrevistasProvExtSegColums[2],	//"palSegConcor8594"
			sortable: false,
			align : "left",
			width : 50,
			formatter: function (cellval, opts, rowObject, action){
				return crearInputTabla(cellval, opts, rowObject,"configHorasPrevistasProveExt");
			}
		},
		{
			name : configHorasPrevistasProvExtHoraColums[2],	//"palHoraConcor8594"
			sortable: false,
			align : "left",
			width : 50
		},
		{
			name : configHorasPrevistasProvExtSegColums[3],	//"palSegConcor084"
			sortable: false,
			align : "left",
			width : 50,
			formatter: function (cellval, opts, rowObject, action){
				return crearInputTabla(cellval, opts, rowObject,"configHorasPrevistasProveExt");
			}
		},
		{
			name : configHorasPrevistasProvExtHoraColums[3],	//"palHoraConcor084"
			sortable: false,
			align : "left",
			width : 50
		},
		{
			name : configHorasPrevistasProvExtSegColums[4],	//"palTradSeg"
			sortable: false,
			align : "left",
			width : 50,
			formatter: function (cellval, opts, rowObject, action){
				return crearInputTabla(cellval, opts, rowObject,"configHorasPrevistasProveExt");
			}
		},
		{
			name : configHorasPrevistasProvExtHoraColums[4],	//"numPalTradHora"
			sortable: false,
			align : "left",
			width : 50
		},
		{
			name : configHorasPrevistasProvExtSegColums[5],	//"palRevSeg"
			sortable: false,
			align : "left",
			width : 50,
			formatter: function (cellval, opts, rowObject, action){
				return crearInputTabla(cellval, opts, rowObject,"configHorasPrevistasProveExt");
			}
		},
		{
			name : configHorasPrevistasProvExtHoraColums[5],	//"palReExtHora"
			sortable: false,
			align : "left",
			width : 50
		}
		
		],
		model : "ConfigHorasPrevistasProveedorExt",
		usePlugins : [ 
			"toolbar",
			"responsive", 
			"fluid" 
			,"filter"
			, "feedback"
			],
		primaryKey : "nivUsuario",
		sortname : "NIVELUSUARIO045",
		sortorder : "desc",
		loadOnStartUp : true,
		loadComplete: function(data){ 
			addOnchangeToHorasPrevistasInputs();
			$("#configHorasPrevistasProveExt_toolbar").hide();
			$("#configHorasPrevistasProveExt_pager").hide();
		},
		beforeSelectRow: function (){
			return false;
		}
	});
	jQuery("#configHorasPrevistasProveExt").jqGrid('setGroupHeaders', {
				useColSpanStyle: false, 
			  	groupHeaders:[
					{startColumnName: configHorasPrevistasProvExtSegColums[0], numberOfColumns: 8, titleText: $.rup.i18n.app.label.provExtTraPalHoraEntre },
					{startColumnName: configHorasPrevistasProvExtSegColums[4], numberOfColumns: 2, titleText: $.rup.i18n.app.label.provExtTradPalHora },
					{startColumnName: configHorasPrevistasProvExtSegColums[5], numberOfColumns: 2, titleText: $.rup.i18n.app.label.provExtRevPalHora },
			  ]
			});
	jQuery("#configHorasPrevistasProveExt").jqGrid('setGroupHeaders', {
				useColSpanStyle: true, 
			  	groupHeaders:[
			  		{startColumnName: configHorasPrevistasProvExtSegColums[0], numberOfColumns: 2, titleText: $.rup.i18n.app.label.tramosConcor100 },
					{startColumnName: configHorasPrevistasProvExtSegColums[1], numberOfColumns: 2, titleText: $.rup.i18n.app.label.tramosConcor9599 },
					{startColumnName: configHorasPrevistasProvExtSegColums[2], numberOfColumns: 2, titleText: $.rup.i18n.app.label.tramosConcor8594 },
					{startColumnName: configHorasPrevistasProvExtSegColums[3], numberOfColumns: 2, titleText: $.rup.i18n.app.label.tramosConcor084 }
			  ]
			});
	
}

jQuery(function($){

	$("#datosBasicos_feedback").rup_feedback({
		block: false,
		closeLink: true,
		gotoTop: true,
	});
	
	$("#accordionDatosBasicos").rup_accordion({
		animated: "bounceslide",
		active: false,
		heightStyle: "content",
		collapsible: true,
		activate: function(event, ui) {
	         cambios = false;
		},
		beforeActivate: function( event, ui ) {
			if(cambios){
				//Comprobar que no un replegado del que está abierto...
				if (ui.newHeader.size()){ 
	                nuevoIndexSeleccionado = ($(ui.newHeader)[0].id).substring(6) -1 ;
	            }
				//obtenemos el valor real del id seleccionado
				idPanelSelected = obtenerValorRealIdAccordionSeleccionado(nuevoIndexSeleccionado)
            	//mostrar mensaje de que han habido cambios
            	event.preventDefault();
                event.stopImmediatePropagation();
				$.rup_messages("msgConfirm", {
                    title: $.rup.i18nParse($.rup.i18n.base,"rup_table.changes"),
                    message: $.rup.i18nParse($.rup.i18n.base,"rup_table.saveAndContinue"),
                    OKFunction: function(){      
                       var idActivo = $( "#accordionDatosBasicos" ).accordion( "option", "active" );
                       var idForm = obtenerIdForm(idActivo);
                       cambios = false; 
                       $("#accordionDatosBasicos").rup_accordion('activate',  idPanelSelected);                    
                       if(idForm && !"".localeCompare(idForm)==0){
                    	   $("#"+idForm).rup_form("resetForm");   
                       }
                    },
                    CANCELFunction: function(){
                    	return false;
                    }
                });   
			}
		}
	});
	$("#accordionDatosBasicos").rup_accordion("change", function(){
		if(cambios == false){
			cambios = true;
		}
	});

	
	
	$("#plazoMinimoRequerido_toolbar").rup_toolbar(configToolbar);
	$("#calculoHorasPrevistasProveExt_toolbar").rup_toolbar({
		buttons: [
			{
				id : "guardar"+(count++),
				i18nCaption : $.rup.i18nParse($.rup.i18n.base, "rup_global.save"),
				css : "fa fa-floppy-o",
				click : function(event) {
					event.preventDefault();
					event.stopImmediatePropagation();
					bloquearPantalla();
					if ($("#calculoHorasPrevistasProveExt_form").valid()) {
						var listaConfigHoras = getConfigHorasPrevistasProvExtTableData();
						var limitePalConcor = {
							"id": $("#configHorasPrevistasProveExt_id").val(),
							"limPalConcor": $("#configHorasPrevistasProveExt_limPalConcor").val()
						};
						$.ajax({
					        type: 'POST' 
					        ,url: '/aa79bItzulnetWar/administracion/datosBasicos/editHorasPrevistasProveedExt'
					        ,dataType: 'json'
					        ,contentType: 'application/json'
					        ,data: $.toJSON({
						   		"limitePalConcor" : limitePalConcor,
						   		"listaConfigHoras" : JSON.stringify(listaConfigHoras) 
						   	})
					        ,async: false 
					        ,success:function(value){
								if(value){
									desbloquearPantalla();
									cambios = false;
									$("#datosBasicos_feedback").rup_feedback("set", $.rup.i18nParse($.rup.i18n.app, "mensajes.guardadoCorrecto"), 'ok');
								}else{
									mostrarMensajeFeedback("datosBasicos_feedback", $.rup.i18nParse($.rup.i18n.app,"mensajes.errorLlamadaAjax"), "error");
									desbloquearPantalla();
								}
					        }
							,error: function(error){
								mostrarMensajeFeedback("datosBasicos_feedback", $.rup.i18nParse($.rup.i18n.app,"mensajes.errorLlamadaAjax"), "error");
								desbloquearPantalla();
						   	 }
						});
					} else{
						desbloquearPantalla();
					}
				}
			}
		]
	});
	$("#calculoHorasPrevistas_toolbar").rup_toolbar({
		buttons: [
			{
				id : "guardar"+(count++),
				i18nCaption : $.rup.i18nParse($.rup.i18n.base, "rup_global.save"),
				css : "fa fa-floppy-o",
				click : function(event) {
					event.preventDefault();
					event.stopImmediatePropagation();
					bloquearPantalla();
					if ($("#calculoHorasPrevistas_form").valid()) {
						var listaConfigHoras = getConfigHorasPrevistasTableData();
						var limitePalConcor = {
							"id": $("#configHorasPrevistasLimPalConcor_id").val(),
							"limPalConcor": $("#configHorasPrevistasLimPalConcor_limPalConcor").val(),
							"tiempoExtraGest": $("#configHorasPrevistasLimPalConcor_tiempoExtraGest").val()
						};
						$.ajax({
					        type: 'POST' 
					        ,url: '/aa79bItzulnetWar/administracion/datosBasicos/editHorasPrevistas'
					        ,dataType: 'json'
					        ,contentType: 'application/json'
					        ,data: $.toJSON({
						   		"limitePalConcor" : limitePalConcor,
						   		"listaConfigHoras" : JSON.stringify(listaConfigHoras) 
						   	})
					        ,async: false 
					        ,success:function(value){
								if(value){
									desbloquearPantalla();
									cambios = false;
									$("#datosBasicos_feedback").rup_feedback("set", $.rup.i18nParse($.rup.i18n.app, "mensajes.guardadoCorrecto"), 'ok');
								}else{
									mostrarMensajeFeedback("datosBasicos_feedback", $.rup.i18nParse($.rup.i18n.app,"mensajes.errorLlamadaAjax"), "error");
									desbloquearPantalla();
								}
					        }
							,error: function(error){
								mostrarMensajeFeedback("datosBasicos_feedback", $.rup.i18nParse($.rup.i18n.app,"mensajes.errorLlamadaAjax"), "error");
								desbloquearPantalla();
						   	 }
						});
					} else{
						desbloquearPantalla();
					}
				}
			}
		]
	});
	$("#calculoPresupuestoTrados_toolbar").rup_toolbar(configToolbar);
	$("#perfilSolicitante_toolbar").rup_toolbar(configToolbar);
	$("#pagoProveeedores_toolbar").rup_toolbar(configToolbar);
	$("#libroRegistro_toolbar").rup_toolbar(configToolbar);
	$("#configEmailsAviso_toolbar").rup_toolbar({
		buttons: [
			{
				id : "guardar"+(count++),
				i18nCaption : $.rup.i18nParse($.rup.i18n.base, "rup_global.save"),
				css : "fa fa-floppy-o",
				click : function(event) {
					event.preventDefault();
					event.stopImmediatePropagation();
					$('textarea[id^=texto]').each(function(){
						$(this).tinymce().save();
					});
					if ($("#configEmailsAviso_form").valid()) {
						$("#configEmailsAviso_form").submit();
					} else if ($("#nav-tabContent :input.error").length > 0){
						var enlace = "#"+$("#nav-tabContent :input.error:first").parents("div[id^=texto_]").attr("id");
						$("a[href="+enlace+"]").click();
					}
				}
			}
		]
	});
	
	$("#plazoMinimoRequerido_form").rup_form($.extend(true, {}, configForm, {
		url: "/aa79bItzulnetWar/administracion/datosBasicos/editPlazoMinimo"
		, validate: {
			showErrorsInFeedback: true,
			showFieldErrorAsDefault: false,
			showFieldErrorsInFeedback: false,
			rules: {
				"usuarioNormal.palabrasTradHora": {required: true, digits: true}
				, "usuarioNormal.palabrasRevHora": {required: true, digits: true}
				, "usuarioNormal.horasGestionTrad": {required: true, digits: true}
				, "usuarioNormal.horasGestionRev": {required: true, digits: true}
				, "usuarioNormal.horasEmisionPresupuesto": {required: true, digits: true}
				, "usuarioNormal.horasAceptPresupuesto": {required: true, digits: true}
				, "usuarioVIP.palabrasTradHora": {required: true, digits: true}
				, "usuarioVIP.palabrasRevHora": {required: true, digits: true}
				, "usuarioVIP.horasGestionTrad": {required: true, digits: true}
				, "usuarioVIP.horasGestionRev": {required: true, digits: true}
				, "usuarioVIP.horasEmisionPresupuesto": {required: true, digits: true}
				, "usuarioVIP.horasAceptPresupuesto": {required: true, digits: true}
			}
		}
	}));
	$("#calculoHorasPrevistas_form").rup_form($.extend(true, {}, configForm, {
		url: "/aa79bItzulnetWar/administracion/datosBasicos/editHorasPrevistas"
		, validate: {
			showErrorsInFeedback: true,
			showFieldErrorAsDefault: false,
			showFieldErrorsInFeedback: false,
			rules: {
				// mantenemos para la validacion, aunque no se utilice para hacer submit
				"configHorasPrevistasLimPalConcor.limPalConcor": {required: true, digits: true},
				"configHorasPrevistasLimPalConcor.tiempoExtraGest": {required: true, horasPrevistas: true, maxlength: 10}
			}
		}
	}));
	$("#calculoHorasPrevistasProveExt_form").rup_form($.extend(true, {}, configForm, {
		url: "/aa79bItzulnetWar/administracion/datosBasicos/editHorasPrevistasProveedExt"
		, validate: {
			showErrorsInFeedback: true,
			showFieldErrorAsDefault: false,
			showFieldErrorsInFeedback: false,
			rules: {
				// mantenemos para la validacion, aunque no se utilice para hacer submit
				"configHorasPrevistasProvExtLimPalConcor.limPalConcor": {required: true, digits: true}
			}
		}
	}));
	// validacion campo numero palabras minimo para presupuesto debe ser mayor o igual al de Trados
	$.validator.addMethod("campoPresupMayorOIgualQueTrados", function(value, element, params) {
		return isNotEmpty($("#configCalculoPresupuesto_palPresupuesto").val()) && isNotEmpty($("#configCalculoPresupuesto_palTrados").val())
					&& parseInt($("#configCalculoPresupuesto_palPresupuesto").val()) >= parseInt($("#configCalculoPresupuesto_palTrados").val());
	},$.rup.i18n.app.validaciones.palPresupuestoMayorIgualQuePalTrados);
	
	$("#calculoPresupuestoTrados_form").rup_form($.extend(true, {}, configForm, {
		url: "/aa79bItzulnetWar/administracion/datosBasicos/editCalculoPresupuesto"
		, validate: {
			showErrorsInFeedback: true,
			showFieldErrorAsDefault: false,
			showFieldErrorsInFeedback: false,
			rules: {
				"configCalculoPresupuesto.palPresupuesto": {required: true, digits: true, campoPresupMayorOIgualQueTrados: true}
				,"configCalculoPresupuesto.palTrados": {required: true, digits: true}
				, "configCalculoPresupuesto.porDesviacionPal": {required: true, digits: true}
			}
		}
	}));
	$("#perfilSolicitante_form").rup_form($.extend(true, {}, configForm, {
		url: "/aa79bItzulnetWar/administracion/datosBasicos/editPerfilSolicitante"
		, validate: {
			showErrorsInFeedback: true,
			showFieldErrorAsDefault: false,
			showFieldErrorsInFeedback: false,
			rules: {
				"configPerfilSolicitante.diasNoConformidad": {required: true, digits: true}
				, "configPerfilSolicitante.urlAyudaNumPal": {required: true, url: true}
				, "configPerfilSolicitante.declaracionResponsableEs": {required: true, maxlength:4000}
				, "configPerfilSolicitante.declaracionResponsableEu": {required: true, maxlength:4000}
			}
		}
	}));
	$("#libroRegistro_form").rup_form($.extend(true, {}, configForm, {
		url: "/aa79bItzulnetWar/administracion/datosBasicos/editLibroRegistro"
			, validate: {
				showErrorsInFeedback: true,
				showFieldErrorAsDefault: false,
				showFieldErrorsInFeedback: false,
				rules: {
				}
			}
	}));
	$("#configEmailsAviso_form").rup_form($.extend(true, {}, configForm, {
		url: "/aa79bItzulnetWar/administracion/datosBasicos/editEmailsAviso"
		, validate: {
			showErrorsInFeedback: true,
			showFieldErrorAsDefault: false,
			showFieldErrorsInFeedback: false,
			ignore: "hidden",
			rules: {
				"configDireccionEmail.dirEmail": {required: true, email: true},
				"configDireccionEmailInterpretacion.dirEmail": {required: true, maxlength: 500}
			}
		}
		, beforeSubmit: function() {
			$('textarea[id^=texto]').each(function(){
				$(this).tinymce().save();
			});
		}
	}));
	
	tinyMCE.init({
		selector : 'textarea:not(#configPerfilSolicitante_declaracionResponsableEs,#configPerfilSolicitante_declaracionResponsableEu)'
		, menubar: false
		, toolbar: [
			"undo redo | styleselect | bold italic underline | alignleft aligncenter alignright alignjustify | outdent indent "
		],
		setup:function(ed){
			 ed.on('change', function(e) {
		          cambios = true;
		       });
			
			 /*ed.addButton('addInfoButton', {
				 text: 'Insertar Info Adicional',
				 title: 'Insertar Info Adicional',
		         onclick: function() {
		            ed.insertContent('{{INFO_ADICC}}');
		         }
		      });*/

		}
	});
	
	crearTablaConfigHorasPrevistas();
	
	$("#pagoProveedores").rup_table({
		url : "/aa79bItzulnetWar/configpagoproveedores",
		feedback: {id:"#datosBasicos_feedback"},
		toolbar: {
			 id: "pagoProveedores_toolbar"
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
		colNames : [ 
			"",
			$.rup.i18nParse($.rup.i18n.app, "label.iva"), 
			$.rup.i18nParse($.rup.i18n.app, "label.fechaDesde"), 
			$.rup.i18nParse($.rup.i18n.app, "label.fechaHasta"), 
			$.rup.i18nParse($.rup.i18n.app, "label.enVigor") 
			],
		colModel : [ 
		{name : "id", hidden : true},
		{
			name : "porIva",
			align : "right",
			index: "porIva005",
			width : 150
		}, 
		{
			name : "fechaDesde",
			index: "fechaDesde005",
			align : "left",
			width : 150
		},
		{
			name : "fechaHasta",
			index: "fechaHasta005",
			align : "left",
			width : 150
		}
		, {
			name: "vigente",
			index: "vigente005",
			align : "center",
			width : 150
			, formatter: formatterCheck
		}
		],
		model : "ConfigPagoProveedores",
		usePlugins : [ 
			"toolbar",
			"responsive", 
			"fluid" 
			,"filter"
			, "formEdit"
			, "feedback"
			],
		primaryKey : "id",
		sortname : "fechaDesde005",
		sortorder : "desc",
		loadOnStartUp : true
		, formEdit:{
        	detailForm: "#pagoProveedores_detail_div",
         	validate:{
    			showErrorsInFeedback: true,
    			showFieldErrorAsDefault: true,
    			showFieldErrorsInFeedback: false,
    			rules:{
    				"porIva":{required: true},
    				"fechaDesde":{required: true, date: true},
    				"fechaHasta":{required: false, date: true, fechaHastaMayor: "fechaDesde"}
				}
         	}
			, addEditOptions: {
				reloadAfterSubmit: true
			}
		}
	});
	fnFechaDesdeHasta("fechaDesde_detail_table", "fechaHasta_detail_table");
	
	$("#vigente_detail_table").bootstrapSwitch()
	.bootstrapSwitch('setSizeClass', 'switch-small')
	.bootstrapSwitch('setOffLabel', $.rup.i18nParse($.rup.i18n.app, "comun.no"))
	.bootstrapSwitch('setOnLabel',  $.rup.i18nParse($.rup.i18n.app, "comun.si"));
	
	 $("#pagoProveedores").on("jqGridAddEditBeforeShowForm.rupTable.formEditing", function(){
		 jQuery('#vigente_detail_table').bootstrapSwitch('setState', jQuery('#vigente_detail_table').is(':checked'));
	});
	
	 $("#pagoProveedores").on("rupTable_afterFormFillDataServerSide", function(){
		 jQuery('#vigente_detail_table').bootstrapSwitch('setState', jQuery('#vigente_detail_table').is(':checked'));
	 });
	 
	
	$('#list-tab a').on('click', function (e) {
	  e.preventDefault();
	  $(this).tab('show');
	  $(".list-group .list-group-item").removeClass("active");
	   $(e.target).addClass("active");
	});
	
	$('#list-tab a:first').tab('show');
	
	// Mostrar la pantalla
	$('.aa79b-content').addClass('in');
});