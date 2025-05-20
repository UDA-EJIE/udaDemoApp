$(document).ready(function () {
	window.initRupI18nPromise.then(function () {
		let tableColModel = [
			{
	            name: 'id',
	            index: 'id',
	            editable: false,
	            hidden: false
	        },
	        {
                name: 'levelLog',
                index: 'levelLog',
                editable: true,
                hidden: false,
                rupType: 'select',
                editoptions: {
                    url: './level',
                    sourceParam : {text: 'label', id: 'value'}
                }
            }
	    ];
		
		$('#nameLog_filter_table').rup_select({
			url: './name',
            sourceParam : {text: 'label', id: 'value'},
            contains: true
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