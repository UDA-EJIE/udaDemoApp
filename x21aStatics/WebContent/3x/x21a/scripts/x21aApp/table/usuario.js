jQuery(function($){

	$("#usuario").rup_datatable({
		
		primaryKey: "id",
		loadOnStartUp: true,
	    filter:{
	    	  id:"usuario_filter_form",
	    	  filterToolbar:"usuario_filter_toolbar",
	    	  collapsableLayerId:"usuario_filter_fieldset"
	    },
        formEdit:{
        	detailForm: "#usuario_detail_div",
			fillDataMethod: "clientSide",
         	validate:{ 
    			rules:{
    				"id":{						required: true    					},
    				"nombre":{						required: false    					},
    				"apellido1":{						required: false    					},
    				"apellido2":{						required: false    					},
    				"ejie":{						required: false    					},
    				"fechaAlta":{						required: false    					},
    				"fechaBaja":{						required: false    					},
    				"rol":{						required: false    					},
    				"fechaModif":{						required: false    					}
    				}
         		} 
    	,titleForm: jQuery.rup.i18nParse(jQuery.rup.i18n.base,'rup_table.edit.editCaption')
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
		,multiSelect: {
            style:    'multi'
        }
	});
});