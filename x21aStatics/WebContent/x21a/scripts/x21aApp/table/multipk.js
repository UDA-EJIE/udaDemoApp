jQuery(function($){

	$("#MultiPk").rup_datatable({
		
		primaryKey: "ida;idb",
		loadOnStartUp: true,
		filter:{
  	  		id:"MultiPk_filter_form",
  	  		filterToolbar:"MultiPk_filter_toolbar",
  	  		collapsableLayerId:"MultiPk_filter_fieldset"
     	},
        formEdit:{
        	detailForm: "#MultiPk_detail_div",
			fillDataMethod: "clientSide",
         	validate:{ 
    			rules:{
    				"ida":{
						required: false
    					},
    				"idb":{
						required: false
    					},
    				"nombre":{
						required: false
    					},
    				"apellido1":{
						required: false
    					},
    				"apellido2":{
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
});