jQuery(function($){

	$("#x21aAlumno").rup_datatable({
		
		primaryKey: "id",
		loadOnStartUp: true,
		filter:{
  	  		id:"x21aAlumno_filter_form",
  	  		filterToolbar:"x21aAlumno_filter_toolbar",
  	  		collapsableLayerId:"x21aAlumno_filter_fieldset"
     	},
        formEdit:{
        	detailForm: "#x21aAlumno_detail_div",
			fillDataMethod: "clientSide",
         	validate:{ 
    			rules:{
    				"id":{
						required: false
    					},
    				"usuario":{
						required: false
    					},
    				"password":{
						required: false
    					},
    				"nombre":{
						required: false
    					},
    				"apellido1":{
						required: false
    					},
    				"provinciaId":{
						required: false
    					},
    				"localidadId":{
						required: false
    					},
    				"comarcaId":{
						required: false
    					},
    				"municipioId":{
						required: false
    					},
    				"autonomiaId":{
						required: false
    					}
    				}
    		   }
    		 ,titleForm: jQuery.rup.i18nParse(jQuery.rup.i18n.base,'rup_table.edit.editCaption')  
    		}
    		,colReorder: {
				fixedColumnsLeft: 1
			}
    	    , select: {
           		 style:    'multi'
       		 }
    		,seeker: {
    			activate:true
			}
			,buttons: {
				activate:true
			}
	});
	
	$('#comarcaId_detail_table').rup_combo({
		source : "../jqGridComarca/comarca",
		sourceParam : {label:"desc"+$.rup_utils.capitalizedLang(), value:"code"},
		rowStriping: true
	});
	
	$('#localidadId_detail_table').rup_combo({
		parent: [ "comarcaId_detail_table" ],
		source : "comboEnlazado/comarcaLocalidad",
		sourceParam : {label:"desc"+$.rup_utils.capitalizedLang(), value:"code", style:"css"}
	});
});