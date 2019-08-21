/*!
 * Copyright 2012 E.J.I.E., S.A.
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
$(function() {
	
	$("#fecha").rup_date({
		labelMaskId : "fecha-mask",
		showButtonPanel : true,
		showOtherMonths : true,
		noWeekend : true
		//, buttonImage : "/rup/basic-theme/images/exclamation.png"
	});
	
	$("#fechaPlaceholder").rup_date({
		placeholderMask : true,
		showButtonPanel : true,
		showOtherMonths : true,
		noWeekend : true
		//, buttonImage : "/rup/basic-theme/images/exclamation.png"
	});
	
	
	
	$("#fecha_multi").rup_date({
		multiSelect: 3,
		placeholderMask : true,
		//multiSelect: [0,5],
//		labelMaskId : "fecha_multi-mask",
//		buttonImage : "/rup/basic-theme/images/exclamation.png"
	});
	
	$.rup_date({		
		from: "desde",
		to: "hasta",
		placeholderMask : true,
		//Resto igual que en date
//		labelMaskId : "intervalo-mask",
		numberOfMonths: 3
//		onSelect: function(selectedDate){
//			alert("La fecha seleccionada es: " + selectedDate);
//		}
	});
	
	$.rup_date({		
		from: "desdeDateTime",
		to: "hastaDateTime",
		//Resto igual que en date
		placeholderMask : true,
//		labelMaskId : "intervalo-mask-date-time",
		numberOfMonths: 3,
		datetimepicker:true,
		showButtonPanel : true,
		showOtherMonths : true,
		noWeekend : true,
		mask: "dd/mm/yyyy hh:mm",
		showSecond: false,
		dateFormat: "dd/mm/yy",
		timeFormat: 'hh:mm'
//		onSelect: function(selectedDate){
//			alert("La fecha seleccionada es: " + selectedDate);
//		}
	});

	$("#fecha_inline").rup_date({
		changeMonth : false,
		changeYear	: false,
		numberOfMonths : [2, 3],
		stepMonths : 6,
		showWeek: true,
		minDate: $.rup_utils.createDate(01, 01, 2012),
		maxDate: $.rup_utils.createDate(31, 12, 2012)		
	});
	
//	$("#fecha_button").click(function() {
//		alert("Fecha: "+$("#fecha").rup_date("getDate"));
//	});
//	$("#fecha_multi_button").click(function() {
//		alert("Fechas: "+$("#fecha_multi").rup_date("getDate"));
//	});
//	$("#fecha_inline_button").click(function() {
//		alert("Fecha: "+$("#fecha_inline").rup_date("getDate"));
//	});
});