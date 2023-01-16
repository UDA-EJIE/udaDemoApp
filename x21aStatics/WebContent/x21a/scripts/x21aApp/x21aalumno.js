jQuery(function ($) {
	window.initRupI18nPromise.then(function () {
        const tableColModels = [
        	{
                name: 'usuario',
                index: 'usuario',
                editable: true,
                hidden: false
            },
            {
                name: 'password',
                index: 'password',
                editable: true,
                hidden: false
            },
            {
                name: 'nombre',
                index: 'nombre',
                editable: true,
                hidden: false
            },
            { 
            	name: "apellido1", 
            	index: "apellido1", 
            	editable: true, 
            	hidden: false,
            	rupType: 'autocomplete',
                editoptions: {
                	source : '../table/apellidos',
                    sourceParam : {label: 'label', value: 'value'},
                    menuMaxHeight: 200,
                    minLength: 3,
                    combobox: true,
                    contains: true,
                    showDefault: true
                }
            },
            {
                name: 'provincia.code',
                index: 'provincia.code',
                editable: true,
                hidden: false,
                rupType: 'select',
                editoptions: {
                    url : '../tableComarca/provincia',
                    sourceParam : {text: 'desc' + $.rup_utils.capitalizedLang(), id: 'code'},
                    //blank: '',
                    width: '100%',
                    customClasses: ['select-material']
                }
            },
            {
                name: 'comarcaId',
                index: 'comarcaId',
                editable: true,
                hidden: false,
                rupType: 'select',
                editoptions: {
                    url : '../tableComarca/comarca',
                    sourceParam : {text: 'desc' + $.rup_utils.capitalizedLang(), id: 'code'},
                    //blank: '',
                    width: '100%',
                    customClasses: ['select-material']
                }
            },
            {
                name: 'localidad',
                index: 'localidad',
                editable: true,
                hidden: false
            },
            {
                name: 'municipio',
                index: 'municipio',
                editable: true,
                hidden: false
            }

        ];
		
	    $('#x21aAlumno').rup_table({
	        loadOnStartUp: true,
	        filter: {
	            id: 'x21aAlumno_filter_form',
	            filterToolbar: 'x21aAlumno_filter_toolbar',
	            collapsableLayerId: 'x21aAlumno_filter_fieldset'
	        },
	        formEdit: {
	            detailForm: '#x21aAlumno_detail_div',
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
	                    'apellido2': {
	                        required: false
	                    },
	                    'fechaNacimiento': {
	                        required: false
	                    },
	                    'telefono': {
	                        required: false
	                    },
	                    'email': {
	                        required: false
	                    },
	                    'idioma': {
	                        required: false
	                    },
	                    'paisId': {
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
	                    },
	                    'calleId': {
	                        required: false
	                    },
	                    'imagen': {
	                        required: false
	                    },
	                    'sexo': {
	                        required: false
	                    },
	                    'dni': {
	                        required: false
	                    },
	                    'autonomiaId': {
	                        required: false
	                    },
	                    'nombreImagen': {
	                        required: false
	                    },
	                    'calle': {
	                        required: false
	                    },
	                    'direccion': {
	                        required: false
	                    },
	                    'importeMatricula': {
	                        required: false
	                    }
	                }
	            }
	        },
	        colReorder: {
	            fixedColumnsLeft: 1
	        },
	        multiSelect: {
	            style: 'multi'
	        },
	        buttons: {
	            activate: true
	        },
	        colModel :tableColModels
	    });
	});
});