/*!
 * Copyright 2011 E.J.I.E., S.A.
 *
 * Licencia con arreglo a la EUPL, Versión 1.1 exclusivamente (la «Licencia»);
 * Solo podrá usarse esta obra si se respeta la Licencia.
 * Puede obtenerse una copia de la Licencia en
 *
 *      http://ec.europa.eu/idabc/eupl.html
 *
 * Salvo cuando lo exija la legislación aplicable o se acuerde por escrito, 
 * el programa distribuido con arreglo a la Licencia se distribuye «TAL CUAL»,
 * SIN GARANTÍAS NI CONDICIONES DE NINGÚN TIPO, ni expresas ni implícitas.
 * Véase la Licencia en el idioma concreto que rige los permisos y limitaciones
 * que establece la Licencia.
 */
jQuery(document).ready(function() {

	// anadir una rup_table al cargar la pagina
	addTableIberdok();

	// js relacionado con el dialogo del lanzador del Editor de iberdok
	formularioEditorDocumentos();

	// anade los combos del dialogo
	addCombosForm();

});
/**
 * Anade un rup_table a la pantalla con el modelo de datos de IberdokFile
 * 
 * 
 */
function addTableIberdok() {

	$("#iberdokTable").rup_table(
			{
				url : "../iberdok",
				colNames : [ $.rup.i18n.app.iberdokTable.id,
						$.rup.i18n.app.iberdokTable.nombre,
						$.rup.i18n.app.iberdokTable.idModelo,
						$.rup.i18n.app.iberdokTable.idDocumento,
						$.rup.i18n.app.iberdokTable.estado ],
				colModel : [ {
					name : "id",
					label : $.rup.i18n.app.iberdokTable.id
				}, {

					name : "nombre",
					label : $.rup.i18n.app.iberdokTable.nombre
				}, {
					name : "idModelo",
					label : $.rup.i18n.app.iberdokTable.idModelo
				}, {

					name : "idDocumento",
					label : $.rup.i18n.app.iberdokTable.idDocumento
				}, {

					name : "estado",
					label : $.rup.i18n.app.iberdokTable.estado,
					unformat : unformatterEstado,
					formatter : formatterEstado
				}, ],
				model : "IberdokFile",
				usePlugins : [ "formEdit", "feedback", "toolbar",

				"contextMenu", "fluid", "search" ],
				primaryKey : "id",
				onSelectRow : function(id) {
					var selectedRow = $("#iberdokTable").rup_table(
							"getRowData", id);
					if (selectedRow.estado == 1) {
						$('#iberdokTable_toolbar\\#\\#Visualizar').hide();
					} else {
						$('#iberdokTable_toolbar\\#\\#Visualizar').show();
					}
				},
				toolbar : {
					createDefaultToolButtons : false,
					buttons : [ {
						obj : {
							i18nCaption : $.rup.i18n.app.iberdokTable.nuevo,
							css : "nuevo",
							index : 4
						},
						json_i18n : "nuevoDocumento",
						click : fnNuevoDocumento
					}, {
						obj : {
							i18nCaption : $.rup.i18n.app.iberdokTable.editar,
							css : "editar",
							index : 4
						},
						json_i18n : "editarEnIberdok",
						click : fnEditarIberdok
					}, {
						obj : {
							i18nCaption : $.rup.i18n.app.iberdokTable.ver,
							css : "buscar",
							index : 4
						},
						json_i18n : "abrirDocumento",
						click : fnAbrirDocumento,
						id : "visualizar"
					}, {
						obj : {
							i18nCaption : $.rup.i18n.app.iberdokTable.copiar,
							css : "clonar",
							index : 4
						},
						json_i18n : "copiarDocumento",
						click : fnCopiarDocumento,
						id : "copiar"
					}

					],
				}
			});
}

/**
 * Desformata el estado para que el valor sea el dado en bd, y no el litaral
 * mostrado por pantalla
 * 
 * 
 * 
 */
function unformatterEstado(cellvalue, options, rowObject) {
	if (cellvalue == $.rup.i18n.app.iberdokTable.finalizado) {
		return "2";
	} else {
		return "1";
	}

}

/**
 * Formatea el estado para que por pantalla muestre el literal asociado al
 * estado en lugar del valor de bd*
 * 
 * 
 */
function formatterEstado(cellvalue, options, rowObject) {
	if (cellvalue == "1") {
		return $.rup.i18n.app.iberdokTable.elaboracion;
	} else {
		return $.rup.i18n.app.iberdokTable.finalizado;
	}
}

/**
 * Funcion encargada de gestionar el formulario del dialog *
 * 
 * 
 * 
 * 
 */
function formularioEditorDocumentos() {

	$('#lanzarEditor').on(
			"click",
			function() {

				var modo = $('#modo').val();

				validarFormulario(modo);

				if (modo == '1') {
					// enviamos por el queryString el idMOdelo y el nombre del
					// documento para que vuelvan a la URL de finalizacion

					var urlFinalizacion = $('#urlFinalizacion').val();
					if (urlFinalizacion != '') {
						var idModelo = $('#idModelo').val();
						var nombre = $('#nombre').val()
						// var queryString = '?idModelo=' + idModelo +
						// '&nombre='
						// + nombre;
						// var queryString = '?idModelo=' + idModelo;
					$('#urlFinalizacion').val(
								urlFinalizacion + '?idModelo=' + idModelo
										+ '&idCorrelacion=' + nombre+'&');
					}

				}
			});
}

/**
 * Funcion encargada de comprobar que los datos requeridos esten rellenos en el
 * formulario del dialog *
 * 
 * @param {String}
 *            modo de apertura de iberdok
 * 
 * 
 */
function validarFormulario(modo) {
	if (modo == '1') {

		var properties = {

			rules : {
				"nombre" : {
					required : true
				},
				"idModelo" : {
					required : true
				},
				"urlFinalizacion" : {
					required : true
				},
				"lang" : {
					required : true
				}
			},

		};

	} else {

		var properties = {

			rules : {

				"urlFinalizacion" : {
					required : true
				},
				"lang" : {
					required : true
				}
			},

		};
	}

	$("#editorDocumentosForm").rup_validate(properties);
}

/**
 * Funcion encargada de instanciar los combos del dialog
 * 
 * 
 */
function addCombosForm() {
	$('#lang').rup_combo({

		source : [ {
			i18nCaption : $.rup.i18n.app.iberdokTable.es,
			value : "es"
		}, {
			i18nCaption : $.rup.i18n.app.iberdokTable.eu,
			value : "eu"
		}, {
			i18nCaption : $.rup.i18n.app.iberdokTable.en,
			value : "en"
		}, ],

		width : 150,
		blank : "",
		rowStriping : true,
		inputText : true
	});

	$('#modo').rup_combo({

		source : [ {
			i18nCaption : $.rup.i18n.app.iberdokTable.modo1,// crear documento
			value : "1"
		}, {
			i18nCaption : $.rup.i18n.app.iberdokTable.modo2,// editar documento
			// finalizado
			value : "2"
		// }, {
		// i18nCaption : "Modo Inicio",
		// value : "3"
		}, {
			i18nCaption : $.rup.i18n.app.iberdokTable.modo7,// editar documento
			// NO finalizado
			value : "7"
		}, {
			i18nCaption : $.rup.i18n.app.iberdokTable.modo8,// copiar documento
			value : "8"
		},

		],

		width : 250,
		blank : "0",
		rowStriping : true,
		inputText : true,
		disabled : true
	});
}


/**
 * Funcion encargada de gestionar la copia un documento. Abre el dialogo del lanzador de Iberdok
 * con los datos cargados necesarios
 * 
 * 
 */
function fnCopiarDocumento() {

	var selectedRowId = $("#iberdokTable").rup_table("getSelectedRows");
	var selectedRow = $("#iberdokTable").rup_table("getRowData", selectedRowId);
	var idDocumento = selectedRow.idDocumento;
	var modo='8';
	
	abridEditorIberdok(modo, idDocumento);

}


/**
 * Funcion encargada de gestionar la edicion un documento , el modo difiere si
 * el documento esta finalizado o no. Abre el dialogo del lanzador de Iberdok
 * con los datos cargados necesarios
 * 
 * 
 */
function fnEditarIberdok() {

	var selectedRowId = $("#iberdokTable").rup_table("getSelectedRows");
	var selectedRow = $("#iberdokTable").rup_table("getRowData", selectedRowId);
	var idDocumento = selectedRow.idDocumento;
	var modo;
	if (selectedRow.estado == '1') {
		// Editar documento sin finalizar
		modo = "7"
	} else {
		// reabrir Documento finalizado
		modo = "2";
	}
	abridEditorIberdok(modo, idDocumento);

}

/**
 * Funcion encargada de gestionar la creacion de un nuevo documento. Abre el
 * dialogo del lanzador de Iberdok con los datos cargados necesarios
 * 
 * 
 */
function fnNuevoDocumento() {
	abridEditorIberdok("1");
}

/**
 * Funcion encargada de visualizar un documento finalizado
 * 
 * 
 */
function fnAbrirDocumento() {

	var selectedRowId = $("#iberdokTable").rup_table("getSelectedRows");
	var selectedRow = $("#iberdokTable").rup_table("getRowData", selectedRowId);

	var idDocumento = selectedRow.idDocumento;

	window.open('getPdf?idDocumento=' + selectedRow.idDocumento, '_blank');

}

/**
 * Funcion encargada de abrir el dialogo del Lanzador de iberdok
 * 
 * @param {String}
 *            modo de apertura de iberdok
 * @param {String}
 *            idDocumento del documento a editar (opcional, solo en modo modo 2
 *            y modo 7)
 * 
 */
function abridEditorIberdok(modo, idDocumento) {

	gestionarVisibilidadDivs(modo);

	$("#divFormEditorIberdok").rup_dialog({
		type : $.rup.dialog.DIV,

		autoOpen : false,
		modal : true,
		resizable : true,
		title : $.rup.i18n.app.iberdokTable.dialog,
		width : 900,
		height : 500
	});

	$("#divFormEditorIberdok").rup_dialog("open");
	gestionarDatosDivs(modo, idDocumento);

}
/**
 * Funcion que oculta todo los divs del dialog
 * 
 */
function ocultarDivsModos() {
	$('#datosNecesarios').hide();
	$('#divModo1').hide();
	$('#divModo2').hide();
	$('#divModo3').hide();
	$('#divModo7').hide();
}

/**
 * Funcion encargada de rellenar los datos del dialog dependiendo del modo
 * 
 * @param {String}
 *            modo de apertura de iberdok
 * @param {String}
 *            idDocumento del documento a editar (opcional, solo en modo modo 2
 *            y modo 7)
 * 
 */
function gestionarDatosDivs(modo, idDocumento) {

	$('#modo').rup_combo("select", modo);
	$('#lang').rup_combo("select", IBERDOK.lang);
	$('#token').val(IBERDOK.token);
	$('#idUsuario').val(IBERDOK.idUsuario);
	$('#idModelo').val(IBERDOK.idModelo);
	// $('#urlRetorno').val(IBERDOK.urlRetorno);
	$('#urlRetornoEjemplo').text(IBERDOK.urlRetorno);
	$('#urlFinalizacion').val(IBERDOK.urlFinalizacion);

	switch (parseInt(modo)) {
	case 1:
		$('#datosNecesarios').show();
		$('#divModo1').show();
		// url de iberdok
		$('#editorDocumentosForm').attr("action", IBERDOK.urlEditorDocumentos);
		break;
	case 2:
		$.ajax({
			url : 'getXhtml?idDocumento=' + idDocumento,
			async : false,
			success : function(data) {
				$('#xhtml64').val(data);

			},

			dataType : 'json'
		});
		break;
	// case 3:
	// $('#divModo3').show();
	// break;
	case 7:
	case 8:
		// modo 7 reapertura y modo 8 reapertura copia necesitan los mismos
		// datos
		$('#idDocumento').val(idDocumento);
		// url de iberdok
		$('#editorDocumentosForm').attr("action", IBERDOK.urlEditorDocumentos);

		break;
	}
}
/**
 * Método que oculta y muestra los divs dependiendo del modo
 * 
 * @param {String}
 *            modo de Iberdok
 * 
 */
function gestionarVisibilidadDivs(modo) {
	ocultarDivsModos();
	$('#datosNecesarios').show();
	switch (parseInt(modo)) {
	case 1:

		$('#divModo1').show();

		break;
	case 2:
		$('#divModo2').show();

		break;
	// case 3:
	// $('#divModo3').show();
	// break;
	case 7:
	case 8:
		// ambos requieren los mismos datos
		$('#divModo7').show();
		break;
	}

}
