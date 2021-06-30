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
var IBERDOK = (function() {
	var constants = {
		'lang' : '${lang}',
		'token' : '${token}',
		'idUsuario' : '${idUsuario}',
		'idModelo' : '${idModelo}',
		'urlRetorno' : '${urlRetorno}',
		'urlFinalizacion' : '${urlFinalizacion}',
		'urlNuevoDocumento' : '${urlNuevoDocumento}',
		'urlEditarDocumento' : '${urlEditarDocumento}',
		'urlEditorDocumentos':'${urlEditorDocumentos}'
	};

	var result = {};
	for ( var n in constants)
		if (constants.hasOwnProperty(n))
			Object.defineProperty(result, n, {
				value : constants[n]
			});

	return result;
}());

jQuery(document).ready(function () {
	window.initRupI18nPromise.then(function () {
	    // anadir una rup_table al cargar la pagina
	    addTableIberdok();
	
	    // js relacionado con el dialogo del lanzador del Editor de iberdok
	    formularioEditorDocumentos();
	});

    $('.contenedor').addClass('show');
});


/**
 * Anade un rup_table a la pantalla con el modelo de datos de IberdokFile
 * 
 */
function addTableIberdok() {
	let tableColModel = [
		{
            name: 'id',
            index: 'id',
            editable: false,
            hidden: true
        },
		{
            name: 'nombre',
            index: 'nombre',
            editable: true,
            hidden: false
        },
        {
            name: 'idModelo',
            index: 'idModelo',
            editable: true,
            hidden: false
        },
        {
            name: 'idDocumento',
            index: 'idDocumento',
            editable: true,
            hidden: false
        },
        {
            name: 'estado',
            index: 'estado',
            editable: true,
            hidden: false
        }
    ];

    $('#iberdokTable').rup_table({
        colModel: tableColModel,
        columnDefs: [
        	{
        		'targets': -1,
        	    'render': function (data) {
        	    	if (data == '1') {
        	            return $.rup.i18n.app.iberdokTable.elaboracion;
        	        } else {
        	            return $.rup.i18n.app.iberdokTable.finalizado;
        	        }
        	    }	
        	}
        ],
        buttons: {
            activate: true,
            blackListButtons: ['deleteButton', 'reportsButton'],
            myButtons: [
            	{
	        	 	text: function () {
	        	 		return $.rup.i18n.app.iberdokTable.ver;
	        	 	},
	        	 	id: 'iberdokTableVerDocumento', // Campo obligatorio si se quiere usar desde el contextMenu
	        	 	className: 'btn-material-primary-high-emphasis table_toolbar_btnView',
	        	 	icon: "mdi-file-find",
	        	 	displayRegex: /^[1-9][0-9]*$/, // Se muestra siempre que sea un numero mayor a 0
	        	 	insideContextMenu: true, // Independientemente de este valor, sera 'false' si no tiene un id definido
	        	 	type: 'view',
	        	 	action: function () {
	        	 		fnAbrirDocumento();
	        	 	}
	            }
            ]
        },
        select: {
            activate: true
        },
        formEdit: {
            detailForm: '#iberdokTable_detail_div'
        },
        seeker: {
        	activate: true
        },
        filter: 'noFilter'
    });
    
    $('#iberdokTable').on('tableAfterInit', function () {
        // Acción personalizada de añadir
        $('#iberdokTable').on('tableButtonsAfterAddClick', function () {
        	fnNuevoDocumento();
    	});
        
        // Acción personalizada de editar
        $('#iberdokTable').on('tableButtonsAfterEditClick', function () {
        	fnEditarIberdok();
    	});
        
        // Acción personalizada de copiar
        $('#iberdokTable').on('tableButtonsAfterCloneClick', function () {
        	fnCopiarDocumento();
    	});
        
    	// Gestiona la disponibilidad del botón de visualización
    	$('#iberdokTable tbody').on('click.DT keydown', 'tr[role="row"]', function () {
    		var ctx = $('#iberdokTable').rup_table('getContext');
    		// Cuando el valor del campo estado sea 1, no se permite la visualización del contenido
    		ctx._buttons[0].inst.s.buttons[2].conf.displayRegex = ctx.json.rows[this._DT_RowIndex].estado == 1 ? undefined : /^[1-9][0-9]*$/;
    	});
	});
}


/**
 * Funcion encargada de gestionar el formulario del dialog *
 */
function formularioEditorDocumentos() {
    $('#iberdokTable_lanzarEditor').on('click', function () {
        var modo = $('#modo_detail_table').val();

        validarFormulario(modo);

        if (modo == '1') {
            // enviamos por el queryString el idMOdelo y el nombre del
            // documento para que vuelvan a la URL de finalizacion
            var urlFinalizacion = $('#urlFinalizacion_detail_table').val();
            if (urlFinalizacion != '') {
                var idModelo = $('#idModelo_detail_table').val();
                var nombre = $('#nombre_detail_table').val();
                $('#urlFinalizacion_detail_table').val(urlFinalizacion + '?idModelo=' + idModelo + '&idCorrelacion=' + nombre + '&');
            }
        }
    });
}


/**
 * Funcion encargada de comprobar que los datos requeridos esten rellenos en el formulario del dialog
 * 
 * @param {String} modo de apertura de iberdok
 * 
 */
function validarFormulario(modo) {
    var properties;

    if (modo == '1') {
        properties = {
            rules: {
                'nombre': {
                    required: true
                },
                'idModelo': {
                    required: true
                },
                'urlFinalizacion': {
                    required: true
                },
                'lang': {
                    required: true
                }
            },
        };
    } else {
        properties = {
            rules: {
                'urlFinalizacion': {
                    required: true
                },
                'lang': {
                    required: true
                }
            },
        };
    }

    $('#iberdokTable_detail_form').rup_validate(properties);
}



/**
 * Funcion encargada de gestionar la copia un documento. Abre el dialogo del lanzador de Iberdok con los datos cargados necesarios
 * 
 */
function fnCopiarDocumento() {
    var selectedRow = $('#iberdokTable').rup_table('getSelectedRows');
    var idDocumento = selectedRow.idDocumento;
    var modo = '8';

    abrirEditorIberdok(modo, idDocumento);
}


/**
 * Funcion encargada de gestionar la edicion un documento, el modo difiere si
 * el documento esta finalizado o no. Abre el dialogo del lanzador de Iberdok
 * con los datos cargados necesarios
 * 
 */
function fnEditarIberdok() {
	var selectedRow = $('#iberdokTable').rup_table('getSelectedRows');
    var idDocumento = selectedRow.idDocumento;
    var modo;
    if (selectedRow.estado == '1') {
        // Editar documento sin finalizar
        modo = '7';
    } else {
        // reabrir Documento finalizado
        modo = '2';
    }
    abrirEditorIberdok(modo, idDocumento);
}

/**
 * Funcion encargada de gestionar la creacion de un nuevo documento. Abre el
 * dialogo del lanzador de Iberdok con los datos cargados necesarios
 * 
 */
function fnNuevoDocumento() {
    abrirEditorIberdok('1');
}

/**
 * Funcion encargada de visualizar un documento finalizado
 * 
 */
function fnAbrirDocumento() {
	var selectedRow = $('#iberdokTable').rup_table('getSelectedRows');

    window.open('getPdf?idDocumento=' + selectedRow.idDocumento, '_blank');
}

/**
 * Funcion encargada de abrir el dialogo del Lanzador de iberdok
 * 
 * @param {String} modo de apertura de iberdok
 * @param {String} idDocumento del documento a editar (opcional, solo en modo modo 2 y modo 7)
 * 
 */
function abrirEditorIberdok(modo, idDocumento) {
    gestionarVisibilidadDivs(modo);
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
    //$('#divModo3').hide();
    $('#divModo7').hide();
    $('#semillas').hide();
}

/**
 * Funcion encargada de rellenar los datos del dialog dependiendo del modo
 * 
 * @param {String} modo de apertura de iberdok
 * @param {String} idDocumento del documento a editar (opcional, solo en modo modo 2 y modo 7)
 * 
 */
function gestionarDatosDivs(modo, idDocumento) {
    /*$('#modo_detail_table').rup_combo('select', modo);
    $('#lang_detail_table').rup_combo('select', window.IBERDOK.lang);*/
    $('#token_detail_table').val(window.IBERDOK.token);
    $('#idUsuario_detail_table').val(window.IBERDOK.idUsuario);
    $('#idModelo_detail_table').val(window.IBERDOK.idModelo);
    $('#urlRetorno_detail_table').text(window.IBERDOK.urlRetorno);
    $('#urlFinalizacion_detail_table').val(window.IBERDOK.urlFinalizacion);

    switch (parseInt(modo)) {
    case 1:
        $('#datosNecesarios').show();
        $('#divModo1').show();
        // url de iberdok
        // TODO: revisar esto porque Hdiv lo va a cortar al hacer la petición
        $('#iberdokTable_detail_form').attr('action', window.IBERDOK.urlEditorDocumentos);
        break;
    case 2:
        $.ajax({
            url: 'getXhtml?idDocumento=' + idDocumento,
            async: false,
            success: function (data) {
                $('#xhtml64').val(data);
            },
            dataType: 'json'
        });
        break;
    case 7:
    case 8:
        // modo 7 reapertura y modo 8 reapertura copia necesitan los mismos
        // datos
        $('#idDocumento_detail_table').val(idDocumento);
        // url de iberdok
        // TODO: revisar esto porque Hdiv lo va a cortar al hacer la petición
        $('#iberdokTable_detail_form').attr('action', window.IBERDOK.urlEditorDocumentos);
        break;
    }
}
/**
 * Método que oculta y muestra los divs dependiendo del modo
 * 
 * @param {String} modo de Iberdok
 * 
 */
function gestionarVisibilidadDivs(modo) {
    ocultarDivsModos();
    $('#datosNecesarios').show();
    switch (parseInt(modo)) {
    case 1:
        $('#divModo1').show();
        $('#semillas').show();
        break;
    case 2:

        $('#divModo2').show();
        $('#semillas').show();
        break;
    case 7:
        $('#semillas').show();
        break;
    case 8:
        // ambos requieren los mismos datos
        $('#divModo7').show();
        break;
    }
}
