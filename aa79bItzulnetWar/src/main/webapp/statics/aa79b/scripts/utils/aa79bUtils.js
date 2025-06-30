var numMaxRegSel = 1000;
/**
 * Función que dada una lista de objetos json devuelve una lista para
 * autocomplete con el label y value dados.
 * 
 * @param list
 *            La lista
 * @param label
 *            El label
 * @param value
 *            El valor
 * @returns {Array} La lista para el autocomplete
 */
function autocompleteSource(list, label, value) {
	var lRdo = [];
	for (var i = 0; i < list.length; i++) {
		var obj = list[i];
		var element = {
			i18nCaption : eval('list[' + i + '].' + label),
			value : eval('list[' + i + '].' + value)
		};
		lRdo[i] = element;
	}
	return lRdo;
}

/**
 * Función que devuelve un booleano informando si el parámetro no es vacío.
 * 
 * @param param
 *            El parámetro
 * @returns {Boolean} El resultado
 */
function isNotEmpty(param) {
	return param != undefined && param != '' && param != null;
}

/**
 * Función que devuelve un booleano informando si el parámetro es vacío.
 * 
 * @param param
 *            El parámetro
 * @returns {Boolean} El resultado
 */
function isEmpty(param) {
	return param == undefined || param == '' || param == null;
}

function normalizarCadena(param) {
	return "TRANSLATE(UPPER(" + param
			+ "), 'ÁÉÍÓÚÀÈÌÒÙÃÕÂÊÎÔÛÄËÏÖÜÇÑ', 'AEIOUAEIOUAOAEIOUAEIOUCN')";
}

function anyoActual(id){
	if(anyoActualFilter) {
		var today = new Date();
		var year = today.getFullYear().toString().substr(-2);
		$("#" + id).val(year);
		anyoActualFilter = false;
	}
}

function dateActual(){
	var today = new Date();
	var year = today.getFullYear();
	var month = today.getMonth()+1;
	var day = today.getDate();
	return anyadirCeros(year) + "/"+ anyadirCeros(month) + "/" + anyadirCeros(day);
}

function hourActual(){
	var today = new Date();
	var hour = today.getHours();
	var minutes = today.getMinutes();
	return anyadirCeros(hour) + ":"+ anyadirCeros(minutes);
}


function anyadirCeros(valor) {
	if (valor && valor < 10) {
		if (valor == 0) {
			return "00";
		} else {
			return "0" + valor;
		}
	} else if (valor >= 10) {
		return valor;
	} else {
		return "00";
	}
}

function parseDate(date, hours, lang) {
	var partsDate = date.split('/');
	var partsHours = hours.split(':');
	if ("es".localeCompare(lang) === 0) {
		return new Date(partsDate[2], partsDate[1] - 1, partsDate[0],
				partsHours[0], partsHours[1]);
	} else if ("eu".localeCompare(lang) === 0) {
		return new Date(partsDate[0], partsDate[1] - 1, partsDate[2],
				partsHours[0], partsHours[1]);
	} else {
		return 0;
	}

}

function dateFormatSinHora(d, lang) {
	if (d === null || d === 0) {
		return "";
	} else {
		var curr_date = anyadirCeros(d.getDate());
		var curr_month = anyadirCeros(d.getMonth() + 1); // Months are zero
															// based
		var curr_year = d.getFullYear();
		if ("es".localeCompare(lang) === 0) {
			return curr_date + "/" + curr_month + "/" + curr_year;
		} else if ("eu".localeCompare(lang) === 0) {
			return curr_year + "/" + curr_month + "/" + curr_date;
		} else {
			return 0;
		}
	}
}

function formatDate(value, lang) {
	if (value === null || value === 0) {
		return "";
	} else {
		var d = new Date(value);
		var curr_date = anyadirCeros(d.getDate());
		var curr_month = anyadirCeros(d.getMonth() + 1); // Months are zero
															// based
		var curr_year = d.getFullYear();
		var curr_hour = anyadirCeros(d.getHours());
		var curr_minutes = anyadirCeros(d.getMinutes());
		if ("es".localeCompare(lang) === 0) {
			return curr_date + "/" + curr_month + "/" + curr_year + " "
					+ curr_hour + ":" + curr_minutes;
		} else if ("eu".localeCompare(lang) === 0) {
			return curr_year + "/" + curr_month + "/" + curr_date + " "
					+ curr_hour + ":" + curr_minutes;
		} else {
			return 0;
		}
	}
}

function newDateHourFromString(value) {
	if (value === null || value === 0 || value == "") {
		return "";
	} else {
		var fechaHora = value.split(" ");
		
		var fecha = fechaHora[0].split("/");
		var hora = fechaHora[1].split(":");
		
		if (jQuery.rup_utils.capitalizedLang() == "Es") {
			var dateFormat = new Date(fecha[2], fecha[1] - 1,
					fecha[0], hora[0], hora[1]);
		} else {
			var dateFormat = new Date(fecha[0], fecha[1] - 1,
					fecha[2], hora[0], hora[1]);
		}
		
		return dateFormat;
	}
}



/**
 * Función que crea un calendario Html
 * 
 * 
 */
function calendar(month) {

	// Variables to be used later. Place holders right now.
	var padding = "";
	var totalFeb = "";
	var i = 1;
	var testing = "";

	var current = new Date();
	var cmonth = current.getMonth();
	var day = current.getDate();
	var year = current.getFullYear();
	var tempMonth = month + 1; // +1; //Used to match up the current month with
								// the correct start date.
	var prevMonth = month - 1;

	// Determing if Feb has 28 or 29 days in it.
	if (month == 1) {
		if ((year % 100 !== 0) && (year % 4 === 0) || (year % 400 === 0)) {
			totalFeb = 29;
		} else {
			totalFeb = 28;
		}
	}

	// ////////////////////////////////////////
	// Setting up arrays for the name of //
	// the months, days, and the number of //
	// days in the month. //
	// ////////////////////////////////////////

	var monthNames = [ "Jan", "Feb", "March", "April", "May", "June", "July",
			"Aug", "Sept", "Oct", "Nov", "Dec" ];
	var dayNames = [ "Sunday", "Monday", "Tuesday", "Wednesday", "Thrusday",
			"Friday", "Saturday" ];
	var totalDays = [ "31", "" + totalFeb + "", "31", "30", "31", "30", "31",
			"31", "30", "31", "30", "31" ];

	// ////////////////////////////////////////
	// Temp values to get the number of days//
	// in current month, and previous month.//
	// Also getting the day of the week. //
	// ////////////////////////////////////////

	var tempDate = new Date(tempMonth + ' 1 ,' + year);
	var tempweekday = tempDate.getDay();
	var tempweekday2 = tempweekday;
	var dayAmount = totalDays[month];
	// var preAmount = totalDays[prevMonth] - tempweekday + 1;

	// ////////////////////////////////////////////////
	// After getting the first day of the week for //
	// the month, padding the other days for that //
	// week with the previous months days. IE, if //
	// the first day of the week is on a Thursday, //
	// then this fills in Sun - Wed with the last //
	// months dates, counting down from the last //
	// day on Wed, until Sunday. //
	// ////////////////////////////////////////////////

	while (tempweekday > 0) {
		padding += "<td class='premonth'></td>";
		// preAmount++;
		tempweekday--;
	}
	// ////////////////////////////////////////////////
	// Filling in the calendar with the current //
	// month days in the correct location along. //
	// ////////////////////////////////////////////////

	while (i <= dayAmount) {

		// ////////////////////////////////////////
		// Determining when to start a new row //
		// ////////////////////////////////////////

		if (tempweekday2 > 6) {
			tempweekday2 = 0;
			padding += "</tr><tr>";
		}

		// ////////////////////////////////////////////////////////////////////////////////////////////////
		// checking to see if i is equal to the current day, if so then we are
		// making the color of //
		// that cell a different color using CSS. Also adding a rollover effect
		// to highlight the //
		// day the user rolls over. This loop creates the acutal calendar that
		// is displayed. //
		// ////////////////////////////////////////////////////////////////////////////////////////////////

		if (i == day && month == cmonth) {
			padding += "<td class='currentday'  onClick='this.style.background=\"#00FF00\"; this.style.color=\"#FFFFFF\"' onMouseOut='this.style.background=\"#FFFFFF\"; this.style.color=\"#00FF00\"'>"
					+ i + "</td>";
		} else {
			padding += "<td class='currentmonth' onClick='this.style.background=\"#00FF00\"' onMouseOut='this.style.background=\"#FFFFFF\"'>"
					+ i + "</td>";

		}

		tempweekday2++;
		i++;
	}
	// ///////////////////////////////////////
	// Ouptputing the calendar onto the //
	// site. Also, putting in the month //
	// name and days of the week. //
	// ///////////////////////////////////////

	var calendarTable = "<table class='calendar'> <tr class='currentmonth'><th colspan='7'>"
			+ monthNames[month] + " " + year + "</th></tr>";
	calendarTable += "<tr class='weekdays'>  <td>Sun</td>  <td>Mon</td> <td>Tues</td> <td>Wed</td> <td>Thurs</td> <td>Fri</td> <td>Sat</td> </tr>";
	calendarTable += "<tr>";
	calendarTable += padding;
	calendarTable += "</tr></table>";
	document.getElementById("calendario").innerHTML += calendarTable;

	/**
	 * .currentmonth { color: blue; text-align: center; } .currentday { border:
	 * 1px solid black; color: #00FF00; text-align: center; }
	 * 
	 * table.calendar { margin:1em 1em 2em 1em; } table.calendar td,
	 * table.calendar th { padding:0.5em; } table.calendar {
	 * display:inline-block; display:inline; zoom:1; vertical-align:top; }
	 */
}

function formatterCheck(value, options, rowObject) {
	if (value == 'S') {
		return '<i class="fa fa-check"></i>';
	} else {
		return '';
	}
}

function goTo($desde, $hasta) {
	$desde.removeClass('in');
	setTimeout(function() {
		$(window).scrollTop(0);
		$desde.addClass('collapsed');
		$hasta.removeClass('collapsed');
		$hasta.addClass('in');
	}, 500);
}

function enableButton(idButton) {
	var button = $("#" + idButton);
	button.removeAttr('disabled');
	button.removeClass('ui-button-disabled ui-state-disabled');
}

function disableButton(idButton) {
	var button = $("#" + idButton);
	button.attr('disabled', 'disabled');
	button.addClass('ui-button-disabled ui-state-disabled');
}

/* Cargar combo de relevancia con estado de alta */
function creaComboRelevanciaEstadoAlta(id) {
	jQuery('#' + id)
			.rup_combo(
					{
						source : "/aa79bItzulnetWar/administracion/datosmaestros/tiporelevancia/estado/"
								+ "A",
						sourceParam : {
							label : $.rup.lang == 'es' ? "descRelevanciaEs"
									: "descRelevanciaEu",
							value : "idTipoRelevancia",
							style : "css"
						},
						width : "170",
						ordered : false,
						rowStriping : true,
						open : function() {
							jQuery('#' + id + '-menu').width(
									jQuery('#' + id + '-button').width());
						}
					});
}

function crearComboIdiomaDestino(id) {
	$('#' + id).rup_combo({
		source : "/aa79bItzulnetWar/idiomadestino",
		sourceParam : {
			label : $.rup.lang === 'es' ? "descIdiomaEs" : "descIdiomaEu",
			value : "idIdioma"
		},
		ordered : false,
		rowStriping : true,
		open : function() {
			var id = $(this).attr("id");
			$("#" + id + "-menu").width($("#" + id + "-button").innerWidth());
		}
	});
}

function mostrarDiv(id) {
	$('#' + id).removeClass('collapsed');
	$('#' + id).addClass('in');
}

function ocultarDiv(id) {
	$('#' + id).addClass('collapsed');
	$('#' + id).removeClass('in');
}

function auto_grow(element) {
	element.style.height = "41px";
	element.style.height = (element.scrollHeight) + "px";
}

function eliminarDialogs(params) {
	if (params === null) {
		$(".ui-dialog").remove();
	} else {
		$(".ui-dialog").not(params).remove();
	}
}

function mostrarCapaExpedienteMYO(capaAMostrar) {
	$('.capaExpedienteMYO').addClass('collapsed');
	$('.capaExpedienteMYO').removeClass('in');
	setTimeout(function() {
		capaAMostrar.split(",").forEach(function(unaCapa) {
			mostrarDiv(unaCapa);
		});
	}, 500);
}
function volverDetalleExp() {
	clean();
	mostrarCapaExpedienteMYO('detalleExpediente_div');
}
function clean() {
	if ($("#rechazarExpform").length) {
		$("#rechazarExpform").rup_form('clearForm', true);
		clearValidation('#rechazarExpform');
		$("#rechazarExp_feedback").rup_feedback("close");
	}
	if ($("#requerirSubExpform").length) {
		$("#requerirSubExpform").rup_form('clearForm', true);
		clearValidation('#requerirSubExpform');
		$("#requerirSubExp_feedback").rup_feedback("close");
	}

}

function swingTo(jQSelector) {
	$('html, body').animate({
		scrollTop : $(jQSelector).offset().top - $('.rup-navbar').outerHeight()
	}, "1000", "swing");
}

function closeFeedbacks() {
	$('.rup-feedback').each(function() {
		$(this).rup_feedback('close');
	});
}

function hightlightGridRows(grid, colName, valueToCheck, cssClass) {
	var grid_ids = grid.jqGrid('getDataIDs');
	var rowcount = grid_ids.length;
	// var columnNames = grid.jqGrid('getGridParam', 'colNames');

	/*
	 * $.each(columnNames, function (i, v) { columnNames[i] = v.toUpperCase();
	 * });
	 */

	// var colNameInUpper = colName.toUpperCase();
	// if ($.inArray(colNameInUpper, columnNames) > -1) {
	for (var counter = 0; counter < rowcount; counter++) {
		var rowid = grid_ids[counter];
		var aRow = grid.jqGrid('getRowData', rowid);
		var celldata = grid.getCell(rowid, colName);

		if (celldata == valueToCheck) {
			rowid = rowid.replace(",", "\\,")
			var trElement = $("#" + rowid, grid);
			trElement.removeClass('ui-widget-content');
			trElement.addClass(cssClass);
		}
	}
	/*
	 * } else { alert("Invalid column name to evaluate the condition for
	 * highlighting rows in the grid"); }
	 */

}

function eliminarMensajesValidacion() {
	$(".error").removeClass('error');
	$("[id$='-error']").remove();
}

/**
 * 
 * @param idCapa
 * @returns
 */
function eliminarMensajesValidacionPorCapa(idCapa) {
	$("#" + idCapa + " .error").removeClass('error');
	$("#" + idCapa + " [id$='-error']").remove();
}

function rellenarString(idCampo, longitud, caracterRelleno) {
	var resultado = $('#' + idCampo).val();
	while (resultado.length < longitud) {
		resultado = caracterRelleno + resultado;
	}
	return resultado;
}

function rellenarConCeros(str, longitud) {
	return rellenarString(str, longitud, '0');
}

/**
 * Funcion que devuelve un array con los valores anyo, dia y mes de la fecha
 * pasada como parametro, teninedo en cuenta el idioma en el que esta
 * 
 * @param date
 * @param lang
 * @returns {Array[Integer]}
 */
function dateParams(date, lang) {
	var aDateParams = [];
	var dayOrder;
	var yearOrder;
	var monthOrder;
	if ("es".localeCompare(lang) === 0) {
		if (!/^\d{1,2}\/\d{1,2}\/\d{4}$/.test(date))
			return false;
		monthOrder = 1;
		dayOrder = 0;
		yearOrder = 2;
	} else if ("eu".localeCompare(lang) === 0) {
		if (!/^\d{4}\/\d{1,2}\/\d{1,2}$/.test(date))
			return false;
		monthOrder = 1;
		dayOrder = 2;
		yearOrder = 0;
	} else {
		return false
	}
	var parts = date.split("/");
	var day = parts[dayOrder];
	var month = parts[monthOrder];
	var year = parts[yearOrder];

	aDateParams[0] = day;
	aDateParams[1] = month;
	aDateParams[2] = year;

	return aDateParams;
}

/**
 * Funcion que cambia el formato de la fecha, comprobando el idioma para
 * hacerlo, y asi pasarlo a un formato valido para hacer un new Date en js
 * 
 * @param date
 * @param lang
 * @returns {String}
 */
function dateToISOFormat(date, lang) {
	var aDateParams = dateParams(date, lang);
	if (aDateParams) {
		var newdDate = new Date(aDateParams[2] + '-' + aDateParams[1] + '-'
				+ aDateParams[0]);
		if (!isNaN(newdDate.getTime())) {
			return new Date(newdDate);
		}

	}
	return null;

}

/**
 * 
 * @param esConsulta
 *            String. Parámetro que define si los parámetros serán de solo
 *            consulta
 * @param selector
 *            String. Parámetro que define el nombre del selector.
 */
function dialogoSoloConsulta(esConsulta, selector) {
	if (esConsulta) {
		$('#' + selector + ' input').attr('readonly', 'readonly');
		$('#' + selector + ' textarea').attr('readonly', 'readonly');
		$('#' + selector + ' select').attr('readonly', 'readonly');

		$('#' + selector + ' :checkbox').attr("disabled", "true");
		$('#' + selector + ' input').attr("disabled", "true");
		$('#' + selector + ' textarea').attr("disabled", "true");
		$('#' + selector + ' select').attr("disabled", "true");

		if ($('#' + selector + ' select.rup_combo').length !== 0) {
			$('#' + selector + ' select.rup_combo').rup_combo('disable');
		}
		$('#' + selector + ' input').rup_date('disable');
		if ($("[ id ^= '" + selector + "_toolbar##' ]").length) {
			$("[id^='" + selector + "_toolbar##']").not(
					"[id='" + selector + "_toolbar##volver'], [id^='"
							+ selector + "_toolbar##btnCSV']").hide();
		}
		// Jose, ocultar las toolbars de las pestañas de corredaccion
		if ($('#docsTraducir_toolbar').length) {
			$('#docsTraducir_toolbar').hide();
			$('#docsXliff_toolbar').hide();
		}
		// Jose, deshabilitar los switch. si hace falta, hacer lo mismo con los
		// switch normales... data-switch="true"
		$('input:checkbox[data-switch-pestana="true"]').each(
				function(index, element) {
					var parentIndCorredaccion = $(element).parent().parent();
					parentIndCorredaccion.addClass("disabled");
					$(element).attr('disabled', 'disabled');
				});
		// deshabilitar switches
		if ($('#checkReqPresupuesto').length) {
			if (!$('#checkReqPresupuesto')[0].disabled) {
				$('#checkReqPresupuesto').bootstrapSwitch('toggleDisabled',
						true, true);
			}
		}
		if ($('#checkVisible').length) {
			if (!$('#checkVisible')[0].disabled) {
				$('#checkVisible')
						.bootstrapSwitch('toggleDisabled', true, true);
			}
		}
	}
}

/**
 * 
 * @param esConsulta
 *            String. Parámetro que define si los parámetros serán de solo
 *            consulta
 * @param selector
 *            String. Parámetro que define el nombre del selector.
 */
function dialogoSoloConsultaConfiguracionTareas(esConsulta, selector) {
	if (esConsulta) {
		dialogoSoloConsulta(esConsulta, selector);
	} else {
		$('#' + selector + ' input').removeAttr('readonly');
		$('#' + selector + ' textarea').removeAttr('readonly');
		$('#' + selector + ' select').removeAttr('readonly');

		$('#' + selector + ' :checkbox').removeAttr("disabled");
		$('#' + selector + ' input').removeAttr("disabled");
		$('#' + selector + ' textarea').removeAttr("disabled");
		$('#' + selector + ' select').removeAttr("disabled");

		if ($('#' + selector + ' select.rup_combo').length !== 0) {
			$('#' + selector + ' select.rup_combo').rup_combo('enable');
		}
		$('#' + selector + ' input').rup_date('enable');

		if ($("[id^='" + selector + "_toolbar##']").length) {
			// $( "[id^='" + selector + "_toolbar##']" ).not("[id='" + selector
			// + "_toolbar##volver']").show();
			$("[id^='" + selector + "_toolbar##']").not(
					"[id='" + selector + "_toolbar##volver'], [id^='"
							+ selector + "_toolbar##btnCSV']").show();
		}

		if ($('#docsTraducir_toolbar').length) {
			$('#docsTraducir_toolbar').show();
			$('#docsXliff_toolbar').show();
		}
		$('input:checkbox[data-switch-pestana="true"]').each(
				function(index, element) {
					var parentInd = $(element).parent().parent();
					parentInd.removeClass("disabled");
					$(element).removeAttr('disabled');
				});

		if ($('#detalleReq').length) {
			$('#detalleReq').attr('readonly', 'readonly');
			$('#detalleReq').attr("disabled", "true");
		}
	}
}

/**
 * concatena anyo y numero de expediente
 */
function concatenarAnyoNumExp(anyoAux, numExpAux) {
	if (anyoAux && numExpAux) {
		var anyo = "" + anyoAux;
		anyo = anyo.substr(anyo.length - 2);
		var numExp = "" + numExpAux;
		while (numExp.length < 6) {
			numExp = '0' + numExp;
		}
		return anyo.concat("/" + numExp);
	} else {
		return "-1";
	}
}

/**
 * 
 * función para finalizar la ejecución de las tareas.
 */
function ejecutarTareaReturn(error, dialog, tablaSelector, url, feedbackPadre) {

	if (error) {
		// Feedback en el diálogo
		mostrarMensajeFeedback(dialog + "_feedback", $.rup.i18nParse(
				$.rup.i18n.base, "rup_message.error"), "error");
	} else {
		// Cerrar diálogo
		$("#" + dialog).rup_dialog("close");
		// Refrescar la tabla en caso de haber una tabla y si no refrescar el
		// detalle
		if (tablaSelector !== null && tablaSelector === "tareasExpedientesForm") {
			$("#" + tablaSelector).rup_table("reloadGrid", true);
			$("#" + tablaSelector).rup_table("resetSelection");
			bitacoraUpdate(true);
			fnDatosPlanificacion();
			//Mostrar feedback en la pantalla principal
			mostrarMensajeFeedback(feedbackPadre, $.rup.i18nParse($.rup.i18n.app, "mensajes.guardadoCorrecto"), "ok");
		}else{
			//Aquí ira la llamada para refrescar el detalle: función ajax con la url¿?
		}
		
	}	
}

/**
 * pasando el id de contenedor de pestanas y el numero de pestana (orden de izda
 * a dcha, empezando de 0) la deshabilita
 * 
 * @param numPestana
 * @returns
 */
function deshabilitarPestana(idContenedor, numPestana) {
	$("#" + idContenedor).rup_tabs('disableTabs', {
		idTab : idContenedor,
		position : numPestana
	});
}

/**
 * pasando el id de contenedor de pestanas y el numero de pestana (orden de izda
 * a dcha, empezando de 0) la habilita
 * 
 * @param numPestana
 * @returns
 */
function habilitarPestana(idContenedor, numPestana) {
	$("#" + idContenedor).rup_tabs('enableTabs', {
		idTab : idContenedor,
		position : numPestana
	});
}

function obtenerValorCampo(valor) {
	return valor === null ? "" : valor;
}

/**
 * oculta los botones de adjuntar fichero
 * 
 * @returns
 */
function ocultarBotonesPid() {
	$('[id^=pidButton]').hide();
}

function fnDatosPlanificacion() {
	var jsonObject = {
		"anyo" : anyoExpediente,
		"numExp" : idExpediente,
		"idTipoExpediente" : tipoExp
	};

	$.ajax({
        type: 'POST' 
        ,url: '/aa79bItzulnetWar/tramitacionexpedientes/planificacion/planificacionexpediente/datosplanificacion' 
        ,dataType: 'json' 
        ,contentType: 'application/json' 
        ,data: $.toJSON(jsonObject) 
        ,async: false 
        ,success:function(data){
        	
        	if (data != null){
        		if (tipoExp === datosExp.tipoExp.interpretacion){
        			$("#divDatosPlanificacionExpInter").show();
        			$("#divDatosPlanificacionExpTradRev").hide();
        			
        			$("#fechaHoraIniInterpretacion_filter").text(data.fechaHoraInicio);
        			$("#fechaHoraFinInterpretacion_filter").text(data.fechaHoraFin);
        			//campo no modificable fecha fin de crear/modificar tarea
        			fechaIniExpediente = data.fechaHoraInicio;
        			fechaFinExpediente = data.fechaHoraFin;
        		} else {
        			$("#divDatosPlanificacionExpTradRev").show();
        			$("#divDatosPlanificacionExpInter").hide();
        			if (tipoExp === datosExp.tipoExp.revision){
        				$("#divTramosConcordancia").hide();
        			}
        			
        			if (data.datosTareaTrados != null){
        				$("#numPalabrasIZO_filter").text(data.datosTareaTrados.numTotalPal === null ? "" : data.datosTareaTrados.numTotalPal);
            			$("#tramosConcor084_filter").text(data.datosTareaTrados.numPalConcor084 === null ? "" : data.datosTareaTrados.numPalConcor084);
            			$("#tramosConcor8594_filter").text(data.datosTareaTrados.numPalConcor8594 === null ? "" : data.datosTareaTrados.numPalConcor8594);
            			$("#tramosConcor9599_filter").text(data.datosTareaTrados.numPalConcor9599 === null ? "" : data.datosTareaTrados.numPalConcor9599);
            			$("#tramosConcor100_filter").text(data.datosTareaTrados.numPalConcor100 === null ? "" : data.datosTareaTrados.numPalConcor100);
            			numPalIZO_filter = data.datosTareaTrados.numTotalPal === null ? "" : data.datosTareaTrados.numTotalPal;
            			trConcor084_filter = data.datosTareaTrados.numPalConcor084 === null ? "" : data.datosTareaTrados.numPalConcor084;
            			trConcor8594_filter = data.datosTareaTrados.numPalConcor8594 === null ? "" : data.datosTareaTrados.numPalConcor8594;
            			trConcor9599_filter = data.datosTareaTrados.numPalConcor9599 === null ? "" : data.datosTareaTrados.numPalConcor9599;
            			trConcor100_filter = data.datosTareaTrados.numPalConcor100 === null ? "" : data.datosTareaTrados.numPalConcor100;
        			}

        			if (data.datosPagoProveedores != null) {
        				$("#divDatosPagoProveedor").show();
        				$("#cantidadParaTarea_filter").text(data.datosPagoProveedores.importeTarea === null ? "" : data.datosPagoProveedores.importeTarea);
            			$("#recargoFormatoCantidad_filter").text(data.datosPagoProveedores.importeRecargoFormato === null ? "" : data.datosPagoProveedores.importeRecargoFormato);
            			$("#recargoApremioCantidad_filter").text(data.datosPagoProveedores.importeRecargoApremio === null ? "" : data.datosPagoProveedores.importeRecargoApremio);
            			$("#penalPorRetraso_filter").text(data.datosPagoProveedores.importePenalizacion === null ? "" : data.datosPagoProveedores.importePenalizacion);
            			if(data.datosPagoProveedores.importePenalizacion != null && parseFloat($("#penalPorRetraso_filter").getImporte()) != 0){
            				$("#penalPorRetraso_filter").text("-" + data.datosPagoProveedores.importePenalizacion);
            			}
            			$("#penalPorCalidad_filter").text(data.datosPagoProveedores.importePenalCalidad === null ? "" : data.datosPagoProveedores.importePenalCalidad);
            			if(data.datosPagoProveedores.importePenalCalidad != null && parseFloat($("#penalPorCalidad_filter").getImporte()) != 0){
            				$("#penalPorCalidad_filter").text("-" + data.datosPagoProveedores.importePenalCalidad);
            			}
            			$("#importeBase_filter").text(data.datosPagoProveedores.importeBase === null ? "" : data.datosPagoProveedores.importeBase);
            			$("#importeIva_filter").text(data.datosPagoProveedores.importeIva === null ? "" : data.datosPagoProveedores.importeIva);
            			$("#importeTotal_filter").text(data.datosPagoProveedores.importeTotal === null ? "" : data.datosPagoProveedores.importeTotal);
        			}else{
        				$("#divDatosPagoProveedor").hide();
        			}
        			
        			$("#fechaEnteIZO_filter").text(data.fechaHoraEntregaIZO === null ? "" : data.fechaHoraEntregaIZO);
        			//campo no modificable fecha fin de crear/modificar tarea
        			fechaFinExpediente = data.fechaHoraEntregaIZO === null ? "" : data.fechaHoraEntregaIZO;
        			if (data.indTareasEntrega !== null && data.indTareasEntrega === 'S'){
        				$("#leyendaSinTareasEntregaClte").hide();
        			} else {
        				$("#leyendaSinTareasEntregaClte").show();
        			}
        		}
        		
        		$("#grupoTrabajo_filter").text(data.grupoTrabajo === null ? "" : data.grupoTrabajo);
        		grTrabajo = data.grupoTrabajo === null ? "" : data.grupoTrabajo;
        		estado = data.idEstadoExpediente;
    			fase = data.idFaseExpediente;
    			//ponemos que nos saque del expediente si este esta ya finalizado (no curso y no cierre)
    			if (estado!=datosExpediente.estado.encurso && estado!=datosExpediente.estado.cerrado){
    				if( typeof funcionVolverCondicional !== "undefined" && jQuery.isFunction(funcionVolverCondicional) ) {
    					if( typeof modoDetalle === "undefined"){
    						//Utilizamos msgConfirm ya que el evento beforeClose de msgOk no funciona
        					$.rup_messages("msgConfirm", { 
        							title: $.rup.i18n.app.label.aviso,
        							message: $.rup.i18n.app.mensajes.expedienteFinalizado,
        							OKFunction : function(){
        								//Es seguro ejectura la función
        		    					funcionVolverCondicional();
        							}
        					});
        					//Retocamos msgConfirm para que sea idéntico a msgOk
        					$(".ui-dialog-titlebar-close").hide();
        					$(".rup-enlaceCancelar").remove();
        					$("#rup_msgDIV_msg_icon").removeClass("rup-message_icon-confirm").addClass("rup-message_icon-ok");
    					}
    					
    				}
    			}
    			
    			
        	}
        	
        }
	});
	
}

function eliminarVariable(variable){
	variable = undefined; 
	delete(variable);
}

/**
 * Selecciona los expedientes de la tabla que esten en la lista y que aparezcan
 * en la pagina de la tabla
 * 
 * @param grid
 *            rup_table
 * @param lista
 *            lista con ids
 * @returns
 */
function seleccionarExpedientesDeLista(grid, lista) {
	var grid_ids = grid.jqGrid('getDataIDs');
	var rowcount = grid_ids.length;
	for (var i = 0; i < lista.length; i++) {
		var idConcatenado = lista[i].anyo + "," + lista[i].numExp;
		for (var counter = 0; counter < rowcount; counter++) {
			if (idConcatenado.localeCompare(grid_ids[counter]) == 0) {
				grid.setSelection(idConcatenado);
			}
		}
	}
}

/**
 * Busca en lista de metadatos si esta el metadato pasado como parametro
 * 
 * @param lista
 * @param metadato
 * @returns
 */
function estaMetadatoEnLista(lista, metadato) {
	for (var i = 0; i < lista.length; i++) {
		if (lista[i].idMetadato.toString() === metadato.id.toString()) {
			return true;
		}
	}
	return false;
}


/*
 * Función que deshabilita los controles de una capa
 * inputs, switches, combos, botones de guardar y cancelar...
 */
 function deshabilitarControlesCapa( nombreCapa ){
		$('#'+nombreCapa+' input').prop("readonly",true);
		$('#'+nombreCapa+' input').prop("disabled",true);
		$('#'+nombreCapa+' [id^=pidButton_]').hide();
		$('#'+nombreCapa+' [id^=nombreFicheroInfo_]').hide();
		$('#'+nombreCapa+' input:checkbox').each(function() { 
			if(!$(this)[0].disabled){
				$(this).bootstrapSwitch('toggleDisabled',true,true);
			}
		});
		$('#'+nombreCapa+' .rup_combo').each(function() { 
			$(this).rup_combo("disable");
		});
		$('#'+nombreCapa+' .hasDatepicker').each(function() { 
			$(this).rup_date("disable");
		});
		$('#'+nombreCapa+' [id$=_button_save]').hide();
		$('#'+nombreCapa+' [id$=_cancel_cancelButton]').hide();
		$('#'+nombreCapa+' [id$=_link_cancel]').hide();
}


/**
 * Función que detecta cuando han finalizado las respuestas de todas las
 * llamadas AJAX realizadas y en ese momento lanza la fución de callback
 * 
 * @param funcionCallbackNombre
 * @returns
 */
function llamadasFinalizadas(funcionCallbackNombre) {
	funcionCallback = funcionCallbackNombre;
	if ($.active > 0) {
		$(document).ajaxStop(function() {
			eval(funcionCallback + "()");
			$(this).unbind("ajaxStop");
		});
	} else {
		eval(funcionCallback + "()");
	}
}
function llamadasFinalizadasConParam(funcionCallbackNombre, param) {
	funcionCallback = funcionCallbackNombre;
	if ($.active > 0) {
		$(document).ajaxStop(function() {
			eval(funcionCallback + "('"+param+"')");
			$(this).unbind("ajaxStop");
		});
	} else {
		eval(funcionCallback + "('"+param+"')");
	}
}

/**
 * Convierte el valor de float a uno con dos decimales
 * 
 * @param valor
 * @returns
 */
function convertirFloatConDosDecimales(valor) {
	if (valor) {
		return Math.round(valor * 100) / 100
	}
	return 0;
}

/**
 * Reajusta las columnas de la tabla (creada para llamar al ocultar/mostrar
 * columnas)
 * 
 * @param grid
 * @returns
 */
function ajustarColumnasTabla(grid) {
	var width = grid.jqGrid('getGridParam', 'width'); // get current width
	grid.jqGrid('setGridWidth', width, true);
}

/**
 * @param idFechaDesde
 * @param idFechaHasta
 * @returns
 */
function fnFechaDesdeHasta(idFechaDesde, idFechaHasta) {
	$
			.rup_date({
				from : idFechaDesde,
				to : idFechaHasta,
				fixFocusIE : false,
				onSelect : function(dateText, inst) {
					this.fixFocusIE = true;
				},
				onClose : function(dateText, inst) {
					this.fixFocusIE = true;
					if ($.browser.msie
							|| (!!window.MSInputMethodContext && !!document.documentMode)) {
						$("#" + inst.id).next().focus();
					}

				},
				beforeShow : function(input, inst) {
					var result = $.browser.msie
							|| (!!window.MSInputMethodContext && !!document.documentMode) ? !this.fixFocusIE
							: true;
					this.fixFocusIE = false;
					return result;
				}
			});
}

/**
 * @param id
 * @returns
 */
function deshabilitarCheckBox(id) {
	$('#' + id).parent().parent().addClass("disabled");
	$('#' + id).attr('disabled', 'disabled');
}

function validarHora(value) {
	var valorHora = value;
	if (value.indexOf(":") == -1 || value.split(':').length > 2) {
		valorHora = "";
	} else {
		var hora = value.split(':');
		if (hora[0] !== "" && hora[1] !== "" && hora[0].length >= 1
				&& hora[1].length == 2) {
			var hh = parseInt(hora[0], 10);
			var mm = parseInt(hora[1], 10);
			if (hh < 0 || hh > 9999999)
				valorHora = "";
			if (mm < 0 || mm > 59)
				valorHora = "";
		} else {
			valorHora = "";
		}
	}

	return valorHora;
}

function validarHoraYMayorQueCero(value) {
	var valorHora = value;
	var sonCero = true;
	if (value.indexOf(":") == -1 || value.split(':').length > 2) {
		valorHora = "";
	} else {
		var hora = value.split(':');
		if (hora[0] !== "" && hora[1] !== "" && hora[0].length >= 1
				&& hora[1].length == 2) {
			var hh = parseInt(hora[0], 10);
			var mm = parseInt(hora[1], 10);
			if (hh < 0 || hh > 9999999)
				valorHora = "";
			if (mm < 0 || mm > 59)
				valorHora = "";
			if(hh > 0)
				sonCero = false;
			if(mm > 0)
				sonCero = false;
		} else {
			valorHora = "";
		}
	}

	return (valorHora!="" && !sonCero);
}




/**
 * accion(0 ocultar; 1 mostrar) para boton de toolbar: pasamos el id de la
 * toolbar, el id del boton de agrupacion (null o vacio si no) y el boton del id
 * a ocultar
 * 
 * @param idToolbar
 * @param idBotonAgrup
 * @param idBotonDeshabilitar
 * @param accion
 * @returns
 */
function accionBotonToolbar(idToolbar, idBotonAgrup, idBotonAccion, accion) {
	var campo = "[id='" + idToolbar + "##";
	if (idBotonAgrup != null && idBotonAgrup !== "") {
		campo += idBotonAgrup + "-mbutton-group##";
	}
	campo += idBotonAccion + "']";
	if (accion != null) {
		if (accion == 0) {
			$(campo).hide();
		} else if (accion == 1) {
			$(campo).show();
		}
	}
}

/**
 * muestra mensaje en feedback de ok, alert o error. Pasamos como parametros el
 * id del feecback, el mensaje y el tipo de mensaje ('ok', 'alert', 'error')
 * 
 * @param idFeedback
 * @param msj
 * @param type
 * @returns
 */
function mostrarMensajeFeedback(idFeedback, msj, type) {
	if ($('#' + idFeedback).css('visibility') != 'hidden') {
		$('#' + idFeedback).rup_feedback("close");
	}
	$('#' + idFeedback).rup_feedback("set", msj, type);

}

/**
 * oculta feedback en caso de que este visible
 * 
 * @param idFeedback
 * @returns
 */
function ocultarMensajeFeedback(idFeedback) {
	if ($('#' + idFeedback).css('visibility') != 'hidden') {
		$('#' + idFeedback).rup_feedback("close");
	}
}

/**
 * compara que la fecha del rup_date sea menor o igual que la del valor pasado
 * como parametro. en caso de no serlo, muestra un mensaje de error con el valor
 * que tiene que tener ejemplo: $("#fechaLimiteAceptacion").rules("add", {
 * "fechaMenorOIgualQueValorCampo": $('#fechaLimSeleccionable').val()});
 */
jQuery.validator.addMethod("fechaMenorOIgualQueValorCampo", function(value,
		element, params) {
	// fecha introducida
	var fechaAComp = value.split("/");
	// fecha contra la que se compara
	var fechaValorCampo = params.split("/");
	if (jQuery.rup_utils.capitalizedLang() == "Es") {
		var fechaACompFormat = new Date(fechaAComp[2], fechaAComp[1] - 1,
				fechaAComp[0]);
		var fechaValorCampoFormat = new Date(fechaValorCampo[2],
				fechaValorCampo[1] - 1, fechaValorCampo[0]);
	} else {
		var fechaACompFormat = new Date(fechaAComp[0], fechaAComp[1] - 1,
				fechaAComp[2]);
		var fechaValorCampoFormat = new Date(fechaValorCampo[0],
				fechaValorCampo[1] - 1, fechaValorCampo[2]);
	}
	if (fechaAComp == "" || fechaValorCampo == ""
			|| fechaACompFormat <= fechaValorCampoFormat) {
		return true;
	} else {
		return false;
	}
}, $.format($.rup.i18nParse($.rup.i18n.app, "mensajes.errorFechaMenorOIgual")));
/**
 * compara que la fecha del rup_date sea menor o igual que la del valor pasado
 * como parametro. en caso de no serlo, muestra un mensaje de error con el valor
 * que tiene que tener ejemplo: $("#fechaLimiteAceptacion").rules("add", {
 * "fechaMenorOIgualQueValorCampo": $('#fechaLimSeleccionable').val()});
 */
jQuery.validator.addMethod("fechaMenorOIgualQueValorCampoAux", function(value,
		element, params) {
	// fecha introducida
	var fechaAComp = value.split("/");
	// fecha contra la que se compara
	var fechaValorCampo = params.split("/");
	if (jQuery.rup_utils.capitalizedLang() == "Es") {
		var fechaACompFormat = new Date(fechaAComp[2], fechaAComp[1] - 1,
				fechaAComp[0]);
		var fechaValorCampoFormat = new Date(fechaValorCampo[2],
				fechaValorCampo[1] - 1, fechaValorCampo[0]);
	} else {
		var fechaACompFormat = new Date(fechaAComp[0], fechaAComp[1] - 1,
				fechaAComp[2]);
		var fechaValorCampoFormat = new Date(fechaValorCampo[0],
				fechaValorCampo[1] - 1, fechaValorCampo[2]);
	}
	if (fechaAComp == "" || fechaValorCampo == ""
			|| fechaACompFormat <= fechaValorCampoFormat) {
		return true;
	} else {
		return false;
	}
}, $.format($.rup.i18nParse($.rup.i18n.app,
		"mensajes.errorFechaMenorOIgualQueFechaIntroducida")));

/**
 * compara que la hora del campo sea menor o igual que la del valor como
 * parametro params[0], si las fechas pasadas (params[1], params[2]) son
 * iguales. en caso de no serlo, muestra un mensaje de error con el valor que
 * tiene que tener ejemplo: $("#horaLimiteAceptacion").rules("add", {
 * "horaFechaMenorOIgualQueValorCampo": [$('#horaLimSeleccionable').val() ,
 * 'fechaLimiteAceptacion', 'fechaLimSeleccionable' ] });
 */
jQuery.validator.addMethod("horaFechaMenorOIgualQueValorCampo", function(value,
		element, params) {
	var esHoraMenor = true;
	// fecha introducida
	var fechaMetida = $("[id='" + params[1] + "']").val();
	// fecha contra la que se compara
	var fechaValorCampo = $("[id='" + params[2] + "']").val();

	if (fechaMetida === fechaValorCampo) {
		fechaValorCampo = fechaValorCampo.split("/");
		fechaMetida = fechaMetida.split("/");
		// hora contra la que se compara
		var horaValorCampo = params[0].split(":");
		// hora introducida
		var horaMetida = value.split(":");

		if ($.rup_utils.capitalizedLang() == "Es") {
			var fechaValorCampoFormat = new Date(fechaValorCampo[2],
					fechaValorCampo[1] - 1, fechaValorCampo[0],
					horaValorCampo[0], horaValorCampo[1]);
			var fechaMetidaFormat = new Date(fechaMetida[2],
					fechaMetida[1] - 1, fechaMetida[0], horaMetida[0],
					horaMetida[1]);
		} else {
			var fechaValorCampoFormat = new Date(fechaValorCampo[0],
					fechaValorCampo[1] - 1, fechaValorCampo[2],
					horaValorCampo[0], horaValorCampo[1]);
			var fechaMetidaFormat = new Date(fechaMetida[0],
					fechaMetida[1] - 1, fechaMetida[2], horaMetida[0],
					horaMetida[1]);
		}

		if (fechaValorCampo == ""
				|| fechaMetida == ""
				|| fechaMetidaFormat.getTime() <= fechaValorCampoFormat
						.getTime()) {
			esHoraMenor = true;
		} else {
			esHoraMenor = false;
		}
	}
	return esHoraMenor;
}, $.format($.rup.i18nParse($.rup.i18n.app, "mensajes.errorHoraMenorOIgual")));

/**
 * compara que la fecha del rup_date sea menor o igual que la del valor pasado
 * como parametro. en caso de no serlo, muestra un mensaje de error con el valor
 * que tiene que tener ejemplo: $("#fechaLimiteAceptacion").rules("add", {
 * "fechaMayorQueValorCampo": $('#fechaLimSeleccionable').val()});
 */
jQuery.validator.addMethod("fechaMayorQueValorCampo", function(value, element,
		params) {
	// fecha introducida
	var fechaAComp = value.split("/");
	// fecha contra la que se compara
	var fechaValorCampo = params.split("/");
	if (jQuery.rup_utils.capitalizedLang() == "Es") {
		var fechaACompFormat = new Date(fechaAComp[2], fechaAComp[1] - 1,
				fechaAComp[0]);
		var fechaValorCampoFormat = new Date(fechaValorCampo[2],
				fechaValorCampo[1] - 1, fechaValorCampo[0]);
	} else {
		var fechaACompFormat = new Date(fechaAComp[0], fechaAComp[1] - 1,
				fechaAComp[2]);
		var fechaValorCampoFormat = new Date(fechaValorCampo[0],
				fechaValorCampo[1] - 1, fechaValorCampo[2]);
	}
	if (fechaAComp == "" || fechaValorCampo == ""
			|| fechaValorCampoFormat < fechaACompFormat) {
		return true;
	} else {
		return false;
	}
}, $
		.format($.rup.i18nParse($.rup.i18n.app,
				"mensajes.errorFechaMayorQueCampo")));

/**
 * compara que la hora del campo sea menor o igual que la del valor como
 * parametro params[0], si las fechas pasadas (params[1], params[2]) son
 * iguales. en caso de no serlo, muestra un mensaje de error con el valor que
 * tiene que tener ejemplo: $("#horaLimiteAceptacion").rules("add", {
 * "horaFechaMayorQueValorCampo": [$('#horaLimSeleccionable').val() ,
 * 'fechaLimiteAceptacion', 'fechaLimSeleccionable' ] });
 */
jQuery.validator
		.addMethod("horaFechaMayorQueValorCampo", function(value, element,
				params) {
			var esHoraMenor = true;
			// fecha introducida
			var fechaMetida = $("[id='" + params[1] + "']").val();
			// fecha contra la que se compara
			var fechaValorCampo = $("[id='" + params[2] + "']").val();

			if (fechaMetida === fechaValorCampo) {
				fechaValorCampo = fechaValorCampo.split("/");
				fechaMetida = fechaMetida.split("/");
				// hora contra la que se compara
				var horaValorCampo = params[0].split(":");
				// hora introducida
				var horaMetida = value.split(":");

				if ($.rup_utils.capitalizedLang() == "Es") {
					var fechaValorCampoFormat = new Date(fechaValorCampo[2],
							fechaValorCampo[1] - 1, fechaValorCampo[0],
							horaValorCampo[0], horaValorCampo[1]);
					var fechaMetidaFormat = new Date(fechaMetida[2],
							fechaMetida[1] - 1, fechaMetida[0], horaMetida[0],
							horaMetida[1]);
				} else {
					var fechaValorCampoFormat = new Date(fechaValorCampo[0],
							fechaValorCampo[1] - 1, fechaValorCampo[2],
							horaValorCampo[0], horaValorCampo[1]);
					var fechaMetidaFormat = new Date(fechaMetida[0],
							fechaMetida[1] - 1, fechaMetida[2], horaMetida[0],
							horaMetida[1]);
				}

				if (fechaValorCampo == ""
						|| fechaMetida == ""
						|| fechaValorCampoFormat.getTime() < fechaMetidaFormat
								.getTime()) {
					esHoraMenor = true;
				} else {
					esHoraMenor = false;
				}
			}
			return esHoraMenor;
		}, $.format($.rup.i18nParse($.rup.i18n.app,
				"mensajes.errorHoraMayorQueCampo")));

/**
 * compara que la fecha del rup_date sea menor o igual que la del valor pasado
 * como parametro. en caso de no serlo, muestra un mensaje de error con el valor
 * que tiene que tener ejemplo: $("#fechaLimiteAceptacion").rules("add", {
 * "fechaMenorQueValorCampo": $('#fechaLimSeleccionable').val()});
 */
jQuery.validator.addMethod("fechaMenorQueValorCampo", function(value, element,
		params) {
	// fecha introducida
	var fechaAComp = value.split("/");
	// fecha contra la que se compara
	var fechaValorCampo = params.split("/");
	if (jQuery.rup_utils.capitalizedLang() == "Es") {
		var fechaACompFormat = new Date(fechaAComp[2], fechaAComp[1] - 1,
				fechaAComp[0]);
		var fechaValorCampoFormat = new Date(fechaValorCampo[2],
				fechaValorCampo[1] - 1, fechaValorCampo[0]);
	} else {
		var fechaACompFormat = new Date(fechaAComp[0], fechaAComp[1] - 1,
				fechaAComp[2]);
		var fechaValorCampoFormat = new Date(fechaValorCampo[0],
				fechaValorCampo[1] - 1, fechaValorCampo[2]);
	}
	if (fechaAComp == "" || fechaValorCampo == ""
			|| fechaACompFormat < fechaValorCampoFormat) {
		return true;
	} else {
		return false;
	}
}, $
		.format($.rup.i18nParse($.rup.i18n.app,
				"mensajes.errorFechaMenorQueCampo")));

/**
 * compara que la hora del campo sea menor o igual que la del valor como
 * parametro params[0], si las fechas pasadas (params[1], params[2]) son
 * iguales. en caso de no serlo, muestra un mensaje de error con el valor que
 * tiene que tener ejemplo: $("#horaLimiteAceptacion").rules("add", {
 * "horaFechaMenorQueValorCampo": [$('#horaLimSeleccionable').val() ,
 * 'fechaLimiteAceptacion', 'fechaLimSeleccionable' ] });
 */
jQuery.validator
		.addMethod("horaFechaMenorQueValorCampo", function(value, element,
				params) {
			var esHoraMenor = true;
			// fecha introducida
			var fechaMetida = $("[id='" + params[1] + "']").val();
			// fecha contra la que se compara
			var fechaValorCampo = $("[id='" + params[2] + "']").val();

			if (fechaMetida === fechaValorCampo) {
				fechaValorCampo = fechaValorCampo.split("/");
				fechaMetida = fechaMetida.split("/");
				// hora contra la que se compara
				var horaValorCampo = params[0].split(":");
				// hora introducida
				var horaMetida = value.split(":");

				if ($.rup_utils.capitalizedLang() == "Es") {
					var fechaValorCampoFormat = new Date(fechaValorCampo[2],
							fechaValorCampo[1] - 1, fechaValorCampo[0],
							horaValorCampo[0], horaValorCampo[1]);
					var fechaMetidaFormat = new Date(fechaMetida[2],
							fechaMetida[1] - 1, fechaMetida[0], horaMetida[0],
							horaMetida[1]);
				} else {
					var fechaValorCampoFormat = new Date(fechaValorCampo[0],
							fechaValorCampo[1] - 1, fechaValorCampo[2],
							horaValorCampo[0], horaValorCampo[1]);
					var fechaMetidaFormat = new Date(fechaMetida[0],
							fechaMetida[1] - 1, fechaMetida[2], horaMetida[0],
							horaMetida[1]);
				}

				if (fechaValorCampo == ""
						|| fechaMetida == ""
						|| fechaMetidaFormat.getTime() < fechaValorCampoFormat
								.getTime()) {
					esHoraMenor = true;
				} else {
					esHoraMenor = false;
				}
			}
			return esHoraMenor;
		}, $.format($.rup.i18nParse($.rup.i18n.app,
				"mensajes.errorHoraMenorQueCampo")));

/**
 * compara que la fecha del rup_date sea menor o igual que la del valor pasado
 * como parametro. en caso de no serlo, muestra un mensaje de error con el valor
 * que tiene que tener ejemplo: $("#fechaLimiteAceptacion").rules("add", {
 * "fechaMayorOIgualQueValorCampo": $('#fechaLimSeleccionable').val()});
 */
jQuery.validator.addMethod("fechaMayorOIgualQueValorCampo", function(value,
		element, params) {
	// fecha introducida
	var fechaAComp = value.split("/");
	// fecha contra la que se compara
	var fechaValorCampo = params.split("/");
	if (jQuery.rup_utils.capitalizedLang() == "Es") {
		var fechaACompFormat = new Date(fechaAComp[2], fechaAComp[1] - 1,
				fechaAComp[0]);
		var fechaValorCampoFormat = new Date(fechaValorCampo[2],
				fechaValorCampo[1] - 1, fechaValorCampo[0]);
	} else {
		var fechaACompFormat = new Date(fechaAComp[0], fechaAComp[1] - 1,
				fechaAComp[2]);
		var fechaValorCampoFormat = new Date(fechaValorCampo[0],
				fechaValorCampo[1] - 1, fechaValorCampo[2]);
	}
	if (fechaAComp == "" || fechaValorCampo == ""
			|| fechaACompFormat >= fechaValorCampoFormat) {
		return true;
	} else {
		return false;
	}
}, $.format($.rup.i18nParse($.rup.i18n.app,
		"mensajes.errorFechaMayorOIgualQueCampo")));

/**
 * compara que la hora del campo sea menor o igual que la del valor como
 * parametro params[0], si las fechas pasadas (params[1], params[2]) son
 * iguales. en caso de no serlo, muestra un mensaje de error con el valor que
 * tiene que tener ejemplo: $("#horaLimiteAceptacion").rules("add", {
 * "horaFechaMayorOIgualQueValorCampo": [$('#horaLimSeleccionable').val() ,
 * 'fechaLimiteAceptacion', 'fechaLimSeleccionable' ] });
 */
jQuery.validator.addMethod("horaFechaMayorOIgualQueValorCampo", function(value,
		element, params) {
	var esHoraMenor = true;
	// fecha introducida
	var fechaMetida = $("[id='" + params[1] + "']").val();
	// fecha contra la que se compara
	var fechaValorCampo = $("[id='" + params[2] + "']").val();

	if (fechaMetida === fechaValorCampo) {
		fechaValorCampo = fechaValorCampo.split("/");
		fechaMetida = fechaMetida.split("/");
		// hora contra la que se compara
		var horaValorCampo = params[0].split(":");
		// hora introducida
		var horaMetida = value.split(":");

		if ($.rup_utils.capitalizedLang() == "Es") {
			var fechaValorCampoFormat = new Date(fechaValorCampo[2],
					fechaValorCampo[1] - 1, fechaValorCampo[0],
					horaValorCampo[0], horaValorCampo[1]);
			var fechaMetidaFormat = new Date(fechaMetida[2],
					fechaMetida[1] - 1, fechaMetida[0], horaMetida[0],
					horaMetida[1]);
		} else {
			var fechaValorCampoFormat = new Date(fechaValorCampo[0],
					fechaValorCampo[1] - 1, fechaValorCampo[2],
					horaValorCampo[0], horaValorCampo[1]);
			var fechaMetidaFormat = new Date(fechaMetida[0],
					fechaMetida[1] - 1, fechaMetida[2], horaMetida[0],
					horaMetida[1]);
		}

		if (fechaValorCampo == ""
				|| fechaMetida == ""
				|| fechaMetidaFormat.getTime() >= fechaValorCampoFormat
						.getTime()) {
			esHoraMenor = true;
		} else {
			esHoraMenor = false;
		}
	}
	return esHoraMenor;
}, $.format($.rup.i18nParse($.rup.i18n.app,
		"mensajes.errorHoraMayorOIgualQueCampo")));

function comprobarExtensionConBBDD(laExtension, idFeedback) {
	var resul = false;
	var jsonObject = {
		"formato011" : laExtension
	};
	$.ajax({
			type : 'GET',
			url : '/aa79bItzulnetWar/administracion/datosmaestros/formatosfichero/comprobarextension',
			dataType : 'json',
			contentType : 'application/json',
			data : jsonObject,
			async : false,
			cache : false,
			success : function(extensionValida) {
				if (extensionValida > 0) {
					resul = true;
				}
			},
			error : function() {
				$('#'+idFeedback).rup_feedback("set",
						$.rup.i18n.app.validaciones.errorLlamadaAjax,
						"error");
			}
		});
	return resul;
}

function habilitarPager(id) {
	$('#sp_1_' + id + '_pager').parent().find("input").removeAttr('disabled');
	$('#sp_1_' + id + '_pager').parent().find("input").removeAttr('readonly');
}

function redondeo(numero, decimales) {
	var flotante = parseFloat(numero);
	var resultado = Math.round(flotante * Math.pow(10, decimales))
			/ Math.pow(10, decimales);
	return resultado;
}

function getImporteSumatorio(valor){
	return Number(valor.replace(/\./g,"").replace(",", ".").replace("€", ""));
}

$.fn.extend({
	getImporte: function() {
		var $this = $(this);
		var valor = $this.is(":input") ? $this.val() : $this.text();
		return Number(valor.replace(/\./g,"").replace(",", ".").replace("€", ""));
	}
	, setImporte: function(valor) {
		var $this = $(this);
		var decimales = 2;
		var dataDecimales = $this.data("decim");
		if (dataDecimales !== undefined) {
			decimales = dataDecimales;
		}
		var rdo = valor.toFixed(decimales).toString().replace('.', ',').split(",");
		var formateado = rdo[0].replace(/\B(?=(\d{3})+(?!\d))/g, ".") + "," + rdo[1];
		if ($this.is(":input")) {
			$this.val(formateado);
		} else {
			$this.text(formateado);
		}
	}
	, importeValido: function() {
		var regex = /(?=.*?\d)^\$?(([1-9]\d{0,2}(\.\d{3})*)|\d+)?(\,\d{1,6})?$/
		return regex.test($(this).val());
	}
	, formatImporte: function() {
		var $this = $(this)
		if ($this.importeValido()) {
			$this.setImporte(this.getImporte());
		}
	}
	, formatPorDecimal: function() {
		var $this = $(this)
		if ($this.importeValido()) {
			$this.setPorDecimal(this.getImporte());
		}
	}
	, setPorDecimal: function(valor) {
		var $this = $(this);
		var decimales = 2;
		var dataDecimales = $this.data("decim");
		if (dataDecimales !== undefined) {
			decimales = dataDecimales;
		}
		var rdo = valor.toFixed(decimales).toString().replace('.', ',');
		if ($this.is(":input")) {
			$this.val(rdo);
		} else {
			$this.text(rdo);
		}
	}
});

/**
 * 
 * @param text
 * @returns
 */
function textoValido(text){
	return text && "" != text && "null" != text ;
}

/**
 * 
 * @param direccion
 * @returns
 */
function obtenerDireccionFormateada(direccion){
	dirText = "";
	var hayDatos = false;
	if(direccion!=null){
		if(textoValido(direccion.txtCalle)){
			dirText += direccion.txtCalle;
			hayDatos = true;
		}
		if(textoValido(direccion.txtLocalidad)){
			dirText += hayDatos?", " + direccion.txtLocalidad:direccion.txtLocalidad;
			if(!hayDatos){
				hayDatos = true;
			}
		}
		if(textoValido(direccion.codPostal)){
			dirText += hayDatos?", " + direccion.codPostal:direccion.codPostal;
			if(!hayDatos){
				hayDatos = true;
			}
		}
		if(textoValido(direccion.codMunicipio)){
			dirText += hayDatos?", " + direccion.codMunicipio:direccion.codMunicipio;
			if(!hayDatos){
				hayDatos = true;
			}
		}
		if(textoValido(direccion.codProvincia)){
			dirText += hayDatos?", " + direccion.codProvincia:direccion.codProvincia;
		}
	}
	return dirText;
}

/**
 * 
 * @param id
 * @param clase
 * @returns
 */
function eliminarClaseDeElemento(id, clase){
	if($('#'+id).length){
        $('#'+id).removeClass(clase);
    }
}

function obtenerIdsSeleccionadosRupTable(idTabla, filterFormObject, functionName){
	if(!$('#'+idTabla).rup_table("isSelected")){
		//si no hay elementos seleccionados mensaje de aviso
		$.rup_messages("msgAlert", {
			title: $.rup.i18n.app.label.aviso,
			message: $.rup.i18n.app.comun.warningSeleccion
		});	
		desbloquearPantalla();
		return false;

	}else if($('#'+idTabla).rup_table("getNumRegistrosSeleccionados") > numMaxRegSel ){
		//registros seleccionados supera el numero maximo permitido por rendimiento
		$.rup_messages("msgAlert", {
			title: $.rup.i18n.app.label.aviso,
			message: $.format($.rup.i18nParse($.rup.i18n.app, "mensajes.regSeleccionadosSuperaMax"), numMaxRegSel)
		});	
		desbloquearPantalla();
		return false;
	}else{
		//obtenemos la url de la rup table
		var tableUrl = $('#'+idTabla).jqGrid('getGridParam', 'url');
		//obtenemos el valor de multiplePkToken
		var multiplePkTokenVar = $('#'+idTabla).jqGrid('getGridParam', 'multiplePkToken');
		//cambiamos el filter final por el nombre de la funcion a la que debemos llamar
		var url = tableUrl.replace(new RegExp("filter" + '$'), 'getSelectedIds');
		//obtenemos los ids seleccionados y el selectedAll de la tabla
		var selectedTableOptions = $('#'+idTabla).rup_table("getSelectedIds");
		//creamos el objeto de para pasar los ids y si el select all esta activo
		var tableModel = {
				"core":{
					"pkToken":multiplePkTokenVar
//					,"pkNames":["codigoPK"]
				},
				"multiselection":$('#'+idTabla).rup_table("getSelectedIds"),
		}
		
		//montamos el objeto compuesto con el objeto de filtros del formulario y el de datos de la tabla
		var sendObject = {
				"filterObject" : filterFormObject,
				"tableData" : tableModel
		}
		$.ajax({
	        type: 'POST' 
	        ,url: url
	        ,data: JSON.stringify(sendObject)
	        ,dataType: 'json'
	        ,contentType: 'application/json'
	        ,cache: false 
	        ,success:function(data){
	        	if(data){
	        		//volcamos la lista de ids a la variable listIdObjects que debera estar declarada
	        		listIdObjects =  data;
	        		//llamamos a la funcion pasada como parametro
	        		eval(functionName + "()");
	        	}else{
	        		$.rup_messages("msgAlert", {
						title: $.rup.i18n.app.label.aviso,
						message: $.rup.i18n.app.mensajes.errorCargandoDatos
					});
	        		desbloquearPantalla();
					return false;
	        	}
	        }
			,error: function(error){
				$.rup_messages("msgAlert", {
					title: $.rup.i18n.app.label.aviso,
					message: $.rup.i18n.app.mensajes.errorCargandoDatos
				});
				desbloquearPantalla();
				return false;
		   	}
		});
	}
}

function obtenerFiltrosTabla(idTabla){
	return $('#'+idTabla).rup_table("getSerializedForm",$('#'+idTabla+'_filter_form'), true);
}
	
function volcarListaIdsAExpSeleccionados(concat){
	if(listIdObjects && listIdObjects.length){
		for(var i = 0;i<listIdObjects.length;i++){
			expedientesSeleccionados.push(listIdObjects[i].anyo + concat + listIdObjects[i].numExp);
		}
	}else{
		$.rup_messages("msgAlert", {
			title: $.rup.i18n.app.label.aviso,
			message: $.rup.i18n.app.mensajes.errorCargandoDatos
		});
		desbloquearPantalla();
		return false;
	}
	listIdObjects = [];
}

function volcarListaAStringTareasSeleccionadas(concat){
	selectedIdTarea = "";
	if(listIdObjects && listIdObjects.length){
		for(var i = 0;i<listIdObjects.length;i++){
			if(i!=0){
				selectedIdTarea = selectedIdTarea + concat
			}
			selectedIdTarea = selectedIdTarea + listIdObjects[i].tarea.idTarea;
		}
	}else{
		$.rup_messages("msgAlert", {
			title: $.rup.i18n.app.label.aviso,
			message: $.rup.i18n.app.mensajes.errorCargandoDatos
		});
		desbloquearPantalla();
		return false;
	}
	listIdObjects = [];
}

function volcarListaAStringAlbaranesSeleccionados(concat){
	selectedRefAlbaran = "";
	if(listIdObjects && listIdObjects.length){
		for(var i = 0;i<listIdObjects.length;i++){
			if(i!=0){
				selectedRefAlbaran = selectedRefAlbaran + concat
			}
			selectedRefAlbaran = selectedRefAlbaran + listIdObjects[i].idAlbaran;
		}
	}else{
		$.rup_messages("msgAlert", {
			title: $.rup.i18n.app.label.aviso,
			message: $.rup.i18n.app.mensajes.errorCargandoDatos
		});
		desbloquearPantalla();
		return false;
	}
	listIdObjects = [];
}

function ocultarCheckAllRupTable(id){
	$("#cb_"+id).hide();
}

function comprobarExtensionValidaXliff( nombreFichero){
	var extension = nombreFichero.substring( nombreFichero.lastIndexOf (".") + 1, nombreFichero.length );
	if ( extension.toLowerCase() === "xliff" || extension.toLowerCase() === "tmx" || extension.toLowerCase() === "sdlxliff"){
		return true;
	}else{
		return false;
	}
}

function deshabilitarCampoFecha(id, asignarValor, valor){
	if(asignarValor){
		$('#'+id).rup_date('setDate',valor);
	}
	if(!$('#'+id).rup_date('isDisabled')){
		$('#'+id).rup_date('disable');
	}
}

function habilitarCampoFecha(id, asignarValor, valor){
	if(asignarValor){
		$('#'+id).rup_date('setDate',valor);
	}
	if($('#'+id).rup_date('isDisabled')){
		$('#'+id).rup_date('enable');
	}
}

function deshabilitarCombo(id, asignarValor, valor){
	if(asignarValor){
		$('#'+id).rup_combo('setRupValue',valor);
	}
	if(!$('#'+id).rup_combo('isDisabled')){
		$('#'+id).rup_combo('disable');
	}
}

function habilitarCombo(id, asignarValor, valor){
	if(asignarValor){
		$('#'+id).rup_combo('setRupValue',valor);
	}
	if($('#'+id).rup_combo('isDisabled')){
		$('#'+id).rup_combo('enable');
	}
}

function comprobacionPreviaXliff(elAnyo, elNumExp,idFeedback, functionName){
	var jsonObject = {
			"anyo" : elAnyo,
			"numExp" : elNumExp
		};

		$.ajax({
	        type: 'POST' 
	        ,url: '/aa79bItzulnetWar/tramitacionexpedientes/documentosexpediente/comprobarXliffObligatorio' 
	        ,dataType: 'json' 
	        ,contentType: 'application/json' 
	        ,data: $.toJSON(jsonObject) 
	        ,async: false 
	        ,success:function(data){
	        	if(data){
	        		//es obligatorio adjuntar al menos un documento xliff, sdlxliff o tmx
	        		$('#'+idFeedback).rup_feedback("set", $.rup.i18n.app.mensajes.tareaFaltaFicheroXliff, "error");
	        		desbloquearPantalla();
	        		return false;
	        	}else{
	        		//no es obligatorio adjuntar documento xliff, sdlxliff o tmx
	        		//llamamos a la funcion pasada como parametro
	        		eval(functionName + "()");
	        	}
	        }
		,error:function(e){
			$.rup_messages("msgAlert", {
				title: $.rup.i18n.app.label.aviso,
				message: $.rup.i18n.app.mensajes.errorCargandoDatos
			});
			desbloquearPantalla();
			return false;
		}
		});
}

function comprobacionPreviaXliffAlinearPostEntrega(idTarea,idFeedback, functionName){
	var jsonObject = {
			"idTarea" : idTarea,
	};
	
	$.ajax({
		type: 'POST' 
			,url: '/aa79bItzulnetWar/tramitacionexpedientes/documentosexpediente/comprobarXliffObligatorioAlinearPostEntrega' 
				,dataType: 'json' 
					,contentType: 'application/json' 
						,data: $.toJSON(jsonObject) 
						,async: false 
						,success:function(data){
							if(data){
								//es obligatorio adjuntar al menos un documento xliff, sdlxliff o tmx
								$('#'+idFeedback).rup_feedback("set", $.rup.i18n.app.mensajes.tareaFaltaFicheroXliff, "error");
								desbloquearPantalla();
								return false;
							}else{
								//Hay documento xliff, sdlxliff o tmx, se llama a la función
								eval(functionName + "()");
							}
						}
	,error:function(e){
		$.rup_messages("msgAlert", {
			title: $.rup.i18n.app.label.aviso,
			message: $.rup.i18n.app.mensajes.errorCargandoDatos
		});
		desbloquearPantalla();
		return false;
	}
	});
}

/**
 */
function tituloYNombreDiferentes(titulo, nombre){
	return titulo!=='' &&
				titulo != (nombre.substring( 0, nombre.lastIndexOf (".") ))
}

/**
 */
function mostrarTitulo(titulo, elementA, e){
	e.preventDefault();
    e.stopImmediatePropagation();
    $('[data-toggle="popover"],[data-original-title]').each(function () {
        if (!$(this).is(e.target) && $(this).has(e.target).length === 0 && $('.popover').has(e.target).length === 0) {                
            (($(this).popover('hide').data('bs.popover')||{}).inState||{}).click = false;  // fix for BS 3.3.6
        }

    });
	 var id = $(elementA).data().id;
	$(elementA).attr("id", "popover"+id);
    $("#popover"+id).popover({
		title: $.rup.i18n.app.label.tituloDoc,
		content: titulo,
        placement: 'bottom',
        trigger: 'focus'
    });
	
	$("#popover"+id).popover('show');
}

function mostrarNombreFecha(idDoc, elementA, e){
	e.preventDefault();
    e.stopImmediatePropagation();
    $('[data-toggle="popover"],[data-original-title]').each(function () {
        if (!$(this).is(e.target) && $(this).has(e.target).length === 0 && $('.popover').has(e.target).length === 0) {                
            (($(this).popover('hide').data('bs.popover')||{}).inState||{}).click = false;  // fix for BS 3.3.6
        }

    });
    if($('#popover'+idDoc).length===0){
    	$(elementA).attr("id", "popover"+idDoc);
    	 var nombre = $(elementA).data().nombre;
    	    var apel1 = $(elementA).data().apel1;
    	    var apel2 = $(elementA).data().apel2;
    	    var fechaAlta = $(elementA).data().fechaalta;
    	    if(fechaAlta){
    	    	fechaAlta = formatDate(fechaAlta, $.rup.lang);
    	    }else{
    	    	fechaAlta = "";
    	    }
    	    var contenido;
    	    if ( isNotEmpty(apel1)){
    	    	contenido = nombre + ", " + apel1 +" "+ apel2 + " , " + fechaAlta;
    	    }else{
    	    	contenido = nombre + ", " + fechaAlta;
    	    }
    	    $("#popover"+idDoc).popover({
    			content: contenido,
    	        placement: 'bottom',
    	        trigger: 'focus'
    	    }).on("show.bs.popover", function() {
    	    });
    		
    		$("#popover"+idDoc).popover('show');
    }
}

/**
 */
function esNombreDocumentoTradRevValido(idNombreFichero, nombreDocOriginal,idIdiomaDestino){
	// obtenemos el nombre del fichero que se quiere subir
	var nombreFichero = "";
	var nombreDocOriginalWithoutExt = nombreDocOriginal.substring(0, nombreDocOriginal.lastIndexOf ("."));
	var fullPath =  document.getElementById(idNombreFichero).value;
    if (fullPath) {
	    var startIndex = (fullPath.indexOf('\\') >= 0 ? fullPath.lastIndexOf('\\') : fullPath.lastIndexOf('/'));
	    var filename = fullPath.substring(startIndex);
	    if (filename.indexOf('\\') === 0 || filename.indexOf('/') === 0) {
	        filename = filename.substring(1);
	    }
		nombreFichero = filename.substring(0, filename.lastIndexOf ("."));
	}	
	if(nombreDocOriginalWithoutExt.length + 3 == nombreFichero.length){
		// misma longitud + idioma
		var nombreSinIdiomaDestino = nombreFichero.substring(0,nombreFichero.length-3);
		if(nombreDocOriginalWithoutExt === nombreSinIdiomaDestino){
			// mismo nombre
			var idiomaDestino = nombreFichero.substring(nombreFichero.length-3, nombreFichero.length);
			if((idIdiomaDestino == "1" && '_es' === idiomaDestino.toLowerCase())
				|| (idIdiomaDestino == "2" && '_eu' === idiomaDestino.toLowerCase())){
					// idioma destino correcto
				return true;
			}
		}
	}
	return false;
}

function obtenerExtension(ficheroPath){
	return ficheroPath.substring( ficheroPath.lastIndexOf (".") + 1, ficheroPath.length )
}

function concatenarNombreDocOriginalIdiomaDestYExt(nombreDocOriginal, idiomaDestino,idNombreFichero){
	var nombreDoc = nombreDocOriginal.substring(0, nombreDocOriginal.lastIndexOf ("."));
	var idiomaDestNombre = idiomaDestino == "1" ? '_ES' : idiomaDestino == "2" ? '_EU' : '';
	var extensionAdjunto = obtenerExtension($('#'+idNombreFichero).val());
	return  nombreDoc + idiomaDestNombre + '.' + extensionAdjunto;
}

function comprobarTareaNoEjecutada(codTarea){
	var resul = false;
	bloquearPantalla();
	$.rup_ajax({
	   	 url: '/aa79bItzulnetWar/tramitacionexpedientes/planificacionCarga/tareas/obtenerEstadoEjecucionTarea/' + anyoExpediente + '/' + idExpediente + '/' + codTarea
		,dataType: 'json' 
		,async: false 
	   	,success:function(data, textStatus, jqXHR){
			if (data && data.estadoEjecucion){
				// se puede ejecutar si no esta en estado ejecutada
				if (datosTareas.estadoEjecucion.ejecutada != data.estadoEjecucion) {
	   				desbloquearPantalla();
					resul = true;
				} else {
	   				desbloquearPantalla();
					// tarea ejecutada
					//Utilizamos msgConfirm ya que el evento beforeClose de msgAlert no funciona
					$.rup_messages("msgConfirm", { 
							title: $.rup.i18nParse($.rup.i18n.base,"rup_table.nav.alertcap"),
							message: $.rup.i18n.app.mensajes.ejecucionTareaEjecutada,
							OKFunction : function(){
								$("#ejecutarTareaDialog").rup_dialog("close");
								if ($('#tareasExpedientesForm').length){
									$('#tareasExpedientesForm').trigger('reloadGrid');	
								} else if ($('#detalleCargaTrabajo_filter_div').length){
									volverACapaGeneral();
								}
								
							}
					});
					//Retocamos msgConfirm para que sea idéntico a msgAlert
					// cambiamos clase de confirm a alert para que coja los estilos de este ultimo
					$(".rup-message.rup-message-confirm").removeClass("rup-message-confirm").addClass("rup-message-alert");
					// ocultamos el close de la ventana
					$(".ui-dialog-titlebar-close").hide();
					// eliminamos el boton cancelar
					$(".rup-enlaceCancelar").remove();
					// sustituimos el icono del confirm por el de alert
					$("#rup_msgDIV_msg_icon").removeClass("rup-message_icon-confirm").addClass("rup-message_icon-alert");
				}
			} else {
	   			desbloquearPantalla();
				// no se puede ejecutar, tareas anteriores pendientes
				$.rup_messages("msgConfirm", { 
						title: $.rup.i18nParse($.rup.i18n.base,"rup_table.nav.alertcap"),
						message: $.rup.i18nParse($.rup.i18n.app,"mensajes.tareasAnterioresPdtes"),
						OKFunction : function(){
							$("#ejecutarTareaDialog").rup_dialog("close");
							if ($('#tareasExpedientesForm').length){
								$('#tareasExpedientesForm').trigger('reloadGrid');	
							} else if ($('#detalleCargaTrabajo_filter_div').length){
								volverACapaGeneral();
							}
						}
				});
				//Retocamos msgConfirm para que sea idéntico a msgAlert
				// cambiamos clase de confirm a alert para que coja los estilos de este ultimo
				$(".rup-message.rup-message-confirm").removeClass("rup-message-confirm").addClass("rup-message-alert");
				// ocultamos el close de la ventana
				$(".ui-dialog-titlebar-close").hide();
				// eliminamos el boton cancelar
				$(".rup-enlaceCancelar").remove();
				// sustituimos el icono del confirm por el de alert
				$("#rup_msgDIV_msg_icon").removeClass("rup-message_icon-confirm").addClass("rup-message_icon-alert");
			}
	   	},
	   	error: function (XMLHttpRequest, textStatus, errorThrown){
	   		alert('Error recuperando datos del paso');
	   		desbloquearPantalla();
	   	}
	 });
	return resul;
}

function comprobarTareaTrabajoEjecutada(idTarea, idTrabajo){
	var resul = false;
	bloquearPantalla();
	$.rup_ajax({
	   	 url: '/aa79bItzulnetWar/tramitacionexpedientes/planificacionCarga/tareas/obtenerEstadoEjecucionTareaTrabajo/' + idTarea + '/' + idTrabajo
		,dataType: 'json'  
		,async: false 
	   	,success:function(data, textStatus, jqXHR){
			if (data && data.estadoEjecucion){
				// se puede ejecutar si no esta en estado ejecutada
				if (estadoEjecucion.value.ejecutada != data.estadoEjecucion) {
					// ir a ejecutar tarea
					ejecutarTareaConsulta = false;
					desbloquearPantalla();
					resul = true;
				} else {
					ejecutarTareaConsulta = true;
					// tarea ejecutada
					//Utilizamos msgConfirm ya que el evento beforeClose de msgAlert no funciona
					$.rup_messages("msgConfirm", { 
							title: $.rup.i18nParse($.rup.i18n.base,"rup_table.nav.alertcap"),
							message: $.rup.i18n.app.mensajes.ejecucionTareaEjecutada,
							OKFunction : function(){
								volverACapaGeneral();
							}
					});
					//Retocamos msgConfirm para que sea identico a msgAlert
					// cambiamos clase de confirm a alert para que coja los estilos de este ultimo
					$(".rup-message.rup-message-confirm").removeClass("rup-message-confirm").addClass("rup-message-alert");
					// ocultamos el close de la ventana
					$(".ui-dialog-titlebar-close").hide();
					// eliminamos el boton cancelar
					$(".rup-enlaceCancelar").remove();
					// sustituimos el icono del confirm por el de alert
					$("#rup_msgDIV_msg_icon").removeClass("rup-message_icon-confirm").addClass("rup-message_icon-alert");
				}
			} else {
				// no se puede ejecutar, tareas anteriores pendientes
				$.rup_messages("msgConfirm", { 
						title: $.rup.i18nParse($.rup.i18n.base,"rup_table.nav.alertcap"),
						message: $.rup.i18nParse($.rup.i18n.app,"mensajes.tareasAnterioresPdtes"),
						OKFunction : function(){
							volverACapaGeneral();
						}
				});
				//Retocamos msgConfirm para que sea identico a msgAlert
				// cambiamos clase de confirm a alert para que coja los estilos de este ultimo
				$(".rup-message.rup-message-confirm").removeClass("rup-message-confirm").addClass("rup-message-alert");
				// ocultamos el close de la ventana
				$(".ui-dialog-titlebar-close").hide();
				// eliminamos el boton cancelar
				$(".rup-enlaceCancelar").remove();
				// sustituimos el icono del confirm por el de alert
				$("#rup_msgDIV_msg_icon").removeClass("rup-message_icon-confirm").addClass("rup-message_icon-alert");
				ejecutarTareaConsulta = true;
			}
	   	},
	   	error: function (XMLHttpRequest, textStatus, errorThrown){
	   		alert('Error recuperando datos del paso');
	   		desbloquearPantalla();
	   	}
	 });
	return resul;
}
function esExpedienteParaBoletin(tradRev){
	return (tradRev.indPrevistoBopv && 'S' == tradRev.indPrevistoBopv) ||
			(tradRev.indPublicarBopv && 'S' == tradRev.indPublicarBopv);
}