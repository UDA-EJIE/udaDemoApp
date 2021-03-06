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
		
		$('#nameLog_filter_table').rup_autocomplete({
			source : './name',
            sourceParam : {label: 'label', value: 'value'},
            menuMaxHeight: 200,
            minLength: 3,
            contains: true,
            showDefault: true
        });
	
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
	        multiSelect: {
            	style: 'multi'
            },
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