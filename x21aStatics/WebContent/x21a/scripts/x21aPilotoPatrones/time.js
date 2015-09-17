jQuery(document).ready(function(){
	
	$("#hora").rup_time({
		labelMaskId : "hora-mask",
		showSecond : true,
		timeFormat: 'hh:mm:ss',
		showButtonPanel: true
	});
	
	$("#hora2").rup_time({
		showTime: false,
		ampm : true,
		hour: 8,
		minute: 30,
		hourMin: 8,
		hourMax: 18,
		stepHour : 2,
		stepMinute : 10
		//,disabled:true
	});
	
	$("#hora_inline").rup_time({
		hourGrid: 5,
		minuteGrid: 10
	});
	
	$("#hora_inline").rup_time("setTime", new Date());
	
	//$("#fecha_hora").datetimepicker({});
});