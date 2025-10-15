jQuery(function ($) {
	window.initRupI18nPromise.then(function () {
	    const tableColModels = [
	    	{
	            name: 'id',
	            editable: false,
	            hidden: false
	        },
	    	{
	            name: 'usuario',
	            editable: true,
	            hidden: false
	        },
	        {
	            name: 'password',
	            editable: true,
	            hidden: false
	        },
	        {
	            name: 'nombre',
	            editable: true,
	            hidden: false
	        },
	        { 
	        	name: "apellido1",
	        	editable: true, 
	        	hidden: false,
	        	rupType: 'select',
	            editoptions: {
	            	url : '../table/apellidos',
	                sourceParam : {text: 'label', id: 'value'},
	                menuMaxHeight: 200,
	                minimumResultsForSearch: 3,
	                combobox: true,
	                contains: true,
	                showDefault: true
	            }
	        },
	        {
	            name: 'provinciaId',
	            editable: true,
	            hidden: false,
	            rupType: 'select',
	            editoptions: {
	                url : '../provincia',
	                sourceParam : {text: 'desc' + $.rup_utils.capitalizedLang(), id: 'code'},
	                //blank: '',
	                width: '100%',
	                customClasses: ['select-material']
	            }
	        },
	        {
	            name: 'comarcaId',
	            editable: true,
	            hidden: false,
	            rupType: 'select',
	            editoptions: {
	                url : '../comarca',
	                sourceParam : {text: 'desc' + $.rup_utils.capitalizedLang(), id: 'code'},
	                //blank: '',
	                width: '100%',
	                customClasses: ['select-material']
	            }
	        },
	        {
	            name: 'localidadId',
	            editable: true,
	            hidden: false
	        },
	        {
	            name: 'municipioId',
	            editable: true,
	            hidden: false
	        }
	
	    ];
	
	    $('#x21aAlumno').rup_table({
	
	        primaryKey: 'id',
	        loadOnStartUp: true,
	        filter: {
	            id: 'x21aAlumno_filter_form',
	            filterToolbar: 'x21aAlumno_filter_toolbar',
	            collapsableLayerId: 'x21aAlumno_filter_fieldset'
	        },
	        formEdit: {
				detailForm: {
					id: '#x21aAlumno_detail_div'
				},
	            fillDataMethod: 'clientSide',
	            validate: {
	                rules: {
	                    'id': {
	                        required: false
	                    },
	                    'usuario': {
	                        required: false
	                    },
	                    'password': {
	                        required: false
	                    },
	                    'nombre': {
	                        required: false
	                    },
	                    'apellido1': {
	                        required: false
	                    },
	                    'provinciaId': {
	                        required: false
	                    },
	                    'localidadId': {
	                        required: false
	                    },
	                    'comarcaId': {
	                        required: false
	                    },
	                    'municipioId': {
	                        required: false
	                    }
	                }
	            },
	            titleForm: jQuery.rup.i18nParse(jQuery.rup.i18n.base, 'rup_table.edit.editCaption')
	        },
	        colReorder: {
	            fixedColumnsLeft: 1
	        },
	        multiSelect: {
	            style: 'multi'
	        },
	        seeker: {
	            activate: true
	        },
	        buttons: {
	            activate: true
	        },
	        colModel :tableColModels
	    });
	});
});