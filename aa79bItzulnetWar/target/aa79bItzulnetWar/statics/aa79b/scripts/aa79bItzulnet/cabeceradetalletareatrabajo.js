jQuery(function($){
	$('#cabecera_descTrabajo').text($('#detTareaTrabajoDescTrabajo').val());
	$('#cabecera_numTrabajo').text($('#detTareaTrabajoIdTrabajo').val());
	$('#cabecera_fechaFinPrevista').text($('#detTareaTrabajoFechaFinPrevista').val());
	$('#cabecera_descTrabajo').addClass("cabeceraTrabajoDesc");
	$('#cabecera_numTrabajo').addClass("cabeceraTrabajoDesc");
	$('#cabecera_fechaFinPrevista').addClass("cabeceraTrabajoDesc");
});