jQuery(function($){

	$("#comarca").rup_datatable({
		
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
    		activate:true
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
	    		titleForm: jQuery.rup.i18nParse(jQuery.rup.i18n.base,'rup_table.edit.editCaption')
	        }
	});
	
	$("#localidad").rup_datatable({
		
		primaryKey: "code",
		loadOnStartUp: true,
	    filter:{
	    	  id:"localidad_filter_form",
	    	  filterToolbar:"localidad_filter_toolbar",
	    	  collapsableLayerId:"localidad_filter_fieldset"
	    }
    	,colReorder: {
			fixedColumnsLeft: 1
		}
    	,seeker: {
    		activate:true
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
    		titleForm: jQuery.rup.i18nParse(jQuery.rup.i18n.base,'rup_table.edit.editCaption')
        }
        
	});
});