$(document).ready(function () {
	
	$('#testLog').on("click",function(){
		$.rup_ajax({
					url : "../patrones/logLevel/testLog",
					type : "GET",
					dataType : 'json',
					showLoading : false,
					contentType : 'application/json',
					
		});
	})


options_level_combo = {
		source : [
		   {label: "---", value:""},
		   {label: "TRACE", value:"TRACE"},
		   {label: "DEBUG", value:"DEBUG"},
		   {label: "INFO", value:"INFO"},
		   {label: "WARN", value:"WARN"},
		   {label: "ERROR", value:"ERROR"}
		]
	};


//Formulario de filtrado
jQuery('#level_filter_table').rup_combo(options_level_combo);
jQuery("#level_detail_table").rup_combo(options_level_combo);



$("#table").rup_jqtable({
	url: "../experimental",
	colNames: [
	"nameLog","levelLog"],
	colModel: [
	{name: "nameLog", label: "nameLog", editable:false,formoptions:{rowpos:1, colpos:1}},
	{ name: "levelLog", index: "levelLog", editable:true, width: 140,
		rupType: "combo",
		editoptions: options_level_combo,
		formoptions:{rowpos:1, colpos:2}
	}
	
	],
	
	usePlugins:[
	"inlineEdit",
	"feedback",
	"toolbar",
	"contextMenu",
	"fluid",
	"filter"
	],
	primaryKey: "nameLog",
	
	formEdit:{
    	detailForm: "#table_detail_div",
    	validate:{
			rules:{
				"nameLog":{required:true},
				"logLevel":{required:true}
				
			}
		}
	}
	
});


});