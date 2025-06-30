var anyoActualFilter = true;
var anyo = 0;

function verInforme() {
	anyo = $("#anyo_filter_table").val();
	if (anyo === "") {
		anyo = 0;
	}
	window.open("/aa79bItzulnetWar/informes/verInforme/"+$("#informe_filter_table").rup_combo("getRupValue")+'/'+anyo+'/'+$("#mes_filter_table").rup_combo("getRupValue"));
}

jQuery(function($) {

	$('#informes_feedback').rup_feedback({
		block : false,
		gotoTop: true, 
		delay: 3000
	});
	
	anyoActual("anyo_filter_table");
	
	$("#informe_filter_table").rup_combo({
		loadFromSelect: true
		,width: "100%"
		,ordered: false
		,orderedByValue: false
		,rowStriping: true
		,open: function(){
			jQuery('#informe_filter_table-menu').width(jQuery('#informe_filter_table-button').innerWidth());
		}
	});
	
	$("#mes_filter_table").rup_combo({
		loadFromSelect: true
		,width: "100%"
		,ordered: false
		,rowStriping: true
		,open: function(){
			jQuery('#mes_filter_table-menu').width(jQuery('#mes_filter_table-button').innerWidth());
		}
	});
	
	$('#informes_filter_cleanLink_modificado').on('click',function(event){
		$("#anyo_filter_table").val("");
		$("#mes_filter_table").rup_combo("setRupValue","0");
	});
	
	$('#informes_filter_filterButton').on('click',function(event){
		verInforme();
	});
});
