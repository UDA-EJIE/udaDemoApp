jQuery(function($){
	
	var tableColModelsComarca = [
		{ name: "code", index: "code", editable:true, hidden:false, width: 80
			, formoptions:{rowpos:1, colpos:1}
		},
		{ name: "descEs", index: "descEs", editable:true, hidden:false
			, formoptions:{rowpos:2, colpos:1}
		},
		{ name: "descEu", index: "descEu", editable:true, hidden:false
			, formoptions:{rowpos:3, colpos:1}
		},
		{ name: "css", index: "css", editable:true, hidden:false
			, formoptions:{rowpos:4, colpos:1}
		},
		{ name: "provincia.code", index: "provincia.code", editable:true, hidden:false
			, formoptions:{rowpos:5, colpos:1}
		},
		{ name: "provincia.descEs", index: "provincia.descEs", editable:true, hidden:false
			, formoptions:{rowpos:6, colpos:1}
		}
	];
	
	$("#comarca").rup_table({
		
		primaryKey: "code",
		loadOnStartUp: true,
	    filter:{
	    	  id:"comarca_filter_form",
	    	  filterToolbar:"comarca_filter_toolbar",
	    	  collapsableLayerId:"comarca_filter_fieldset"
	    }
    	,colReorder: {
			fixedColumnsLeft: 1
		}
    	,seeker: {
    		activate: true,
    		colModel: tableColModelsComarca
		}
		,buttons: {
			activate:true
		}
		,select: {
			style: 'multi'
        },
        formEdit: {
	        	detailForm: "#comarca_detail_div",
	        	validate:{
	    			rules:{
	    				"code":{required:true},
	    				"provincia.code":{range:[1,3]}
	    			}
	    		},
	    		titleForm: jQuery.rup.i18nParse(jQuery.rup.i18n.base,'rup_jqtable.edit.editCaption')
	        }
	});
	
	var tableColModelsLocalidad = [
		{ name: "code", index: "code", editable:true, hidden:false, width: 80
			, formoptions:{rowpos:1, colpos:1}
		},
		{ name: "descEs", index: "descEs", editable:true, hidden:false
			, formoptions:{rowpos:2, colpos:1}
		},
		{ name: "descEu", index: "descEu", editable:true, hidden:false
			, formoptions:{rowpos:3, colpos:1}
		},
		{ name: "css", index: "css", editable:true, hidden:false
			, formoptions:{rowpos:4, colpos:1}
		}
	];
	
	$("#localidad").rup_table({
		
		primaryKey: "code",
		loadOnStartUp: false,
	    filter:{
	    	  id:"localidad_filter_form",
	    	  filterToolbar:"localidad_filter_toolbar",
	    	  collapsableLayerId:"localidad_filter_fieldset"
	    }
    	,colReorder: {
			fixedColumnsLeft: 1
		}
    	,seeker: {
    		activate: true,
    		colModel: tableColModelsLocalidad
		}
		,buttons: {
			activate:true
		}
		,select: {
			style: 'multi'
        },
        order: [[ 1, 'desc' ]],
        masterDetail:{
        	master:"#comarca",
        	masterPrimaryKey:"comarca.code"
        },
        formEdit: {
        	detailForm: "#localidad_detail_div",
        	validate:{
    			rules:{
    				"code":{required:true}
    			}
    		},
    		titleForm: jQuery.rup.i18nParse(jQuery.rup.i18n.base,'rup_jqtable.edit.editCaption')
        }
        
	});
	
	$('#provinciaRemote').rup_combo({
		source : "../jqGridComarca/provincia",
		sourceParam : {label:"desc"+$.rup_utils.capitalizedLang(), value:"code", style:"css"},
		rowStriping: true,
		blank: ""
	});
	/*
	$('#comarcaRemote').rup_combo({
		source : "../jqGridComarca/comarca",
		sourceParam : {label:"desc"+$.rup_utils.capitalizedLang(), value:"code"},
		rowStriping: true,
		blank: ""
	});*/
});