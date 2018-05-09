$(function($) {

   jQuery("#nomBuzonCas").rup_autocomplete({
	   	source : 	"../experimental/getBuzonesNivel3",
		sourceParam : {
		label: 	"nomBuzonCastT28", 
		value:	"idbuzonT28"
		},
		minLength: 	3,
		combobox: false
		});
	

   
   
});