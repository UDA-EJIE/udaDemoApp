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
	            activate: true
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
	            activate: true
        },order: [[ 0, 'asc' ]],
        masterDetail:{
        	master:"#comarca",
        	masterPrimaryKey:"comarca.code"
        }
        
	});
});