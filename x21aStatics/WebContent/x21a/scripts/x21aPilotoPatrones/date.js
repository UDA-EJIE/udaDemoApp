jQuery(document).ready(function(){
	
	$("#fecha").rup_date({
		labelMaskId : "fecha-mask",
		showButtonPanel : true,
		showOtherMonths : true,
		noWeekend : true
		//, buttonImage : "/rup/basic-theme/images/exclamation.png"
	});
	
	$("#fecha_multi").rup_date({
		multiSelect: 3,
		//multiSelect: [0,5],
		labelMaskId : "fecha_multi-mask",
		buttonImage : "/rup/basic-theme/images/exclamation.png"
	});
	
	$.rup_date({		
		from: "desde",
		to: "hasta",
		//Resto igual que en date
		labelMaskId : "intervalo-mask",
		numberOfMonths: 3,
		onSelect: function(selectedDate){
			alert("La fecha seleccionada es: " + selectedDate);
		}
	});
	
	$("#fecha_inline").rup_date({
		changeMonth : false,
		changeYear	: false,
		numberOfMonths : [2, 3],
		stepMonths : 6,
		showWeek: true,
		minDate: $.rup_utils.createDate(01, 01, 2011),
		maxDate: $.rup_utils.createDate(31, 12, 2011)		
	});
	
	$("#fecha_button").click(function() {
		alert("Fecha: "+$("#fecha").rup_date("getDate"));
	});
	$("#fecha_multi_button").click(function() {
		alert("Fechas: "+$("#fecha_multi").rup_date("getDate"));
	});
	$("#fecha_inline_button").click(function() {
		alert("Fecha: "+$("#fecha_inline").rup_date("getDate"));
	});
});