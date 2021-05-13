$(document).ready(function () {
	window.initRupI18nPromise.then(function () {
		let tableColModel = [
			{
	            name: 'nid',
	            index: 'nid',
	            editable: false,
	            hidden: false
	        },
	        {
                name: 'levelLog',
                index: 'levelLog',
                editable: true,
                hidden: false,
                width: 140,
                rupType: 'combo',
                formatter: 'rup_combo',
                editoptions: {
                    source : './level',
                    sourceParam : {label: 'label', value: 'value'},
                    width: '100%',
                    customClasses: ['select-material']
                }
            }
	    ];	
	
	    $('#table').rup_table({
	        primaryKey: 'nameLog',
	        filter: {
	            id: 'table_filter_form',
	            filterToolbar: 'table_filter_toolbar',
	            collapsableLayerId: 'table_filter_fieldset'
	        },
	        colModel: tableColModel,
	        ordering: false,
	        buttons: {
                activate: true,
                blackListButtons: ['addButton', 'cloneButton', 'deleteButton', 'reportsButton']
            },
	        select: {
	        	activate: true
	        },
	        enableDynamicForms: true,
	        inlineEdit:{
	        	url: './inlineEdit',
	            validate:{
	                rules:{
	                    'nameLog': {
	                    	required: true
	                    },
	                    'logLevel': {
	                    	required: true
	                    }
	                }
	            }
	        }    
	    });
	});
	
	$('.contenedor').addClass('show');
});