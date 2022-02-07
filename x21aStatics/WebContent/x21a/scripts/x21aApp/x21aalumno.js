jQuery(function ($) {
	window.initRupI18nPromise.then(function () {
	    $('#x21aAlumno').rup_table({
	        loadOnStartUp: true,
	        filter: {
	            id: 'x21aAlumno_filter_form',
	            filterToolbar: 'x21aAlumno_filter_toolbar',
	            collapsableLayerId: 'x21aAlumno_filter_fieldset'
	        },
	        formEdit: {
	            detailForm: '#x21aAlumno_detail_div',
                loadSpinner: true,
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
	        }
	    });
	});
});