/*!
 * Copyright 2013 E.J.I.E., S.A.
 *
 * Licencia con arreglo a la EUPL, Versión 1.1 exclusivamente (la «Licencia»);
 * Solo podrá usarse esta obra si se respeta la Licencia.
 * Puede obtenerse una copia de la Licencia en
 *
 *      http://ec.europa.eu/idabc/eupl.html
 *
 * Salvo cuando lo exija la legislación aplicable o se acuerde por escrito, 
 * el programa distribuido con arreglo a la Licencia se distribuye «TAL CUAL»,
 * SIN GARANTÍAS NI CONDICIONES DE NINGÚN TIPO, ni expresas ni implícitas.
 * Véase la Licencia en el idioma concreto que rige los permisos y limitaciones
 * que establece la Licencia.
 */
jQuery(function($) {
    window.initRupI18nPromise.then(() => {
	    /* Tipos para la gestión de tareas */
	    var tasksTreeTypes = {
	        'valid_children' : ['tasks'],
	        'tasks' : {
	            'icon' : $.rup.STATICS+'/x21a/images/PendingWorks.png',
	            'valid_children' : ['forms', 'invoice', 'repair', 'suppliers', 'transportation']
	        },
	        'forms' : {
	            'icon' : $.rup.STATICS+'/x21a/images/forms.png',
	            'valid_children' : ['job']
	        },
	        'invoice' : {
	            'icon' : $.rup.STATICS+'/x21a/images/invoice.png',
	            'valid_children' : ['job']
	        },
	        'repair' : {
	            'icon' : $.rup.STATICS+'/x21a/images/repair.png',
	            'valid_children' : ['job']
	        },
	        'job' : {
	            'icon' : $.rup.STATICS+'/x21a/images/job.png',
	            'valid_children' : ['none']
	        },
	        'suppliers' : {
	            'icon' : $.rup.STATICS+'/x21a/images/suppliers.png',
	            'valid_children' : ['job']
	        },
	        'transportation' : {
	            'icon' : $.rup.STATICS+'/x21a/images/transportation.png',
	            'valid_children' : ['job']
	        }
	    };
	    
	    /* Tipos para la gestion de trabajadores */
	    var workersTreeTypes = {
	        'valid_children' : ['workers'],
	        'enterprise' : {
	            'icon' : $.rup.STATICS+'/x21a/images/enterprise.png',
	            'valid_children' : ['job']
	        },
	        'worker' : {
	            'icon' : $.rup.STATICS+'/x21a/images/worker.png',
	            'valid_children' : ['job']
	        },
	        'workers' : {
	            'icon' : $.rup.STATICS+'/x21a/images/workers.png',
	            'valid_children' : ['worker', 'enterprise']
	        },
	        'job' : {
	            'icon' : $.rup.STATICS+'/x21a/images/job.png',
	            'valid_children' : ['none']
	        }
	    };
	    
	    /* Codigo JSON del árbol de tareas con ordenación */
	    var tasksReorderTreeJson = [{
	        'id' : 'raizTasks_reorder',
	        'text' : $.rup.i18nParse($.rup.i18n.app.tasksTree,'raiz'),
	        'type' : 'tasks',
	        'state' : {
	        	'opened': true,
	            'disabled': false,
	            'selected': false
	        },
	        'children' : [
	        	{
		            'text' : $.rup.i18nParse($.rup.i18n.app.tasksTree,'informes'),
		            'id' : 'informes_reorder',
		            'type' : 'forms',
		            'state' : {
		            	'opened': true,
		                'disabled': false,
		                'selected': false
		            },
		            'children' : [
		            	{
		            		'text' : $.rup.i18nParse($.rup.i18n.app.tasksTree,'informe_trimestral'),
		            		'id' : 'informe_trimestral_reorder',
		            		'type' : 'job',
		            	},
		            	{
		            		'text' : $.rup.i18nParse($.rup.i18n.app.tasksTree,'informe_gastos_generales'),
		            		'id' : 'informe_gastos_generales_reorder',
		            		'type' : 'job',
		            	},
		            	{
		            		'text' : $.rup.i18nParse($.rup.i18n.app.tasksTree,'informe_desperfectos'),
		            		'id' : 'informe_desperfectos_reorder',
		            		'type' : 'job',
		            	},
		            	{
		            		'text' : $.rup.i18nParse($.rup.i18n.app.tasksTree,'informe_deudas'),
		            		'id' : 'informe_deudas_reorder',
		            		'type' : 'job',
		            	},
		            	{
		            		'text' : $.rup.i18nParse($.rup.i18n.app.tasksTree,'informe_calidad'),
		            		'id' : 'informe_calidad_reorder',
		            		'type' : 'job',
		            	},
		            	{
		            		'text' : $.rup.i18nParse($.rup.i18n.app.tasksTree,'informe_perdidas'),
		            		'id' : 'informe_perdidas_reorder',
		            		'type' : 'job',
		            	}
		            ]
	        	},
	            {
	                'text' : $.rup.i18nParse($.rup.i18n.app.tasksTree,'transportes'),
	                'id' : 'transportes_reorder',
	                'type' : 'transportation',
		            'state' : {
		            	'opened': true,
		                'disabled': false,
		                'selected': false
		            },
		            'children' : [
		            	{
		            		'text' : $.rup.i18nParse($.rup.i18n.app.tasksTree,'pedido_lujua'),
		            		'id' : 'pedido_lujua_reorder',
		            		'type' : 'job',
		            	},
		            	{
		            		'text' : $.rup.i18nParse($.rup.i18n.app.tasksTree,'pedido_cemento'),
		            		'id' : 'pedido_cemento_reorder',
		            		'type' : 'job',
		            	},
		            	{
		            		'text' : $.rup.i18nParse($.rup.i18n.app.tasksTree,'pedido_martillo'),
		            		'id' : 'pedido_martillo_reorder',
		            		'type' : 'job',
		            	}
		            ]
	            },
	            {
	                'text' : $.rup.i18nParse($.rup.i18n.app.tasksTree,'reparaciones'),
	                'id' : 'reparaciones_reorder',
	                'type' : 'repair',
		            'state' : {
		            	'opened': true,
		                'disabled': false,
		                'selected': false
		            },
		            'children' : [
		            	{
		            		'text' : $.rup.i18nParse($.rup.i18n.app.tasksTree,'reparacion_fachada'),
		            		'id' : 'reparacion_fachada_reorder',
		            		'type' : 'job',
		            	},
		            	{
		            		'text' : $.rup.i18nParse($.rup.i18n.app.tasksTree,'reparacion_indundacion'),
		            		'id' : 'reparacion_indundacion_reorder',
		            		'type' : 'job',
		            	},
		            	{
		            		'text' : $.rup.i18nParse($.rup.i18n.app.tasksTree,'reparacion_suelo'),
		            		'id' : 'reparacion_suelo_reorder',
		            		'type' : 'job',
		            	},
		            	{
		            		'text' : $.rup.i18nParse($.rup.i18n.app.tasksTree,'reparacion_ratas'),
		            		'id' : 'reparacion_ratas_reorder',
		            		'type' : 'job',
		            	}
		            ]
	            },
	            {
	                'text' : $.rup.i18nParse($.rup.i18n.app.tasksTree,'suministros'),
	                'id' : 'suministros_reorder',
	                'type' : 'suppliers',
		            'state' : {
		            	'opened': true,
		                'disabled': false,
		                'selected': false
		            },
		            'children' : [
		            	{
		            		'text' : $.rup.i18nParse($.rup.i18n.app.tasksTree,'mp_ladrillos'),
		            		'id' : 'mp_ladrillos_reorder',
		            		'type' : 'job',
		            	},
		            	{
		            		'text' : $.rup.i18nParse($.rup.i18n.app.tasksTree,'mp_clavos'),
		            		'id' : 'mp_clavos_reorder',
		            		'type' : 'job',
		            	},
		            	{
		            		'text' : $.rup.i18nParse($.rup.i18n.app.tasksTree,'mp_cemento'),
		            		'id' : 'mp_cemento_reorder',
		            		'type' : 'job',
		            	},
		            	{
		            		'text' : $.rup.i18nParse($.rup.i18n.app.tasksTree,'mp_recambios'),
		            		'id' : 'mp_recambios_reorder',
		            		'type' : 'job',
		            	},
		            	{
		            		'text' : $.rup.i18nParse($.rup.i18n.app.tasksTree,'mp_jabon'),
		            		'id' : 'mp_jabon_reorder',
		            		'type' : 'job',
		            	},
		            	{
		            		'text' : $.rup.i18nParse($.rup.i18n.app.tasksTree,'mp_botas'),
		            		'id' : 'mp_botas_reorder',
		            		'type' : 'job',
		            	},
		            	{
		            		'text' : $.rup.i18nParse($.rup.i18n.app.tasksTree,'mp_cascos'),
		            		'id' : 'mp_cascos_reorder',
		            		'type' : 'job',
		            	}
		            ]
	            },
	            {
	                'text' : $.rup.i18nParse($.rup.i18n.app.tasksTree,'facturas'),
	                'id' : 'facturas_reorder',
	                'type' : 'invoice',
		            'state' : {
		            	'opened': true,
		                'disabled': false,
		                'selected': false
		            },
		            'children' : [
		            	{
		            		'text' : $.rup.i18nParse($.rup.i18n.app.tasksTree,'factura_VH'),
		            		'id' : 'factura_VH_reorder',
		            		'type' : 'job',
		            	},
		            	{
		            		'text' : $.rup.i18nParse($.rup.i18n.app.tasksTree,'factura_Lindo'),
		            		'id' : 'factura_Lindo_reorder',
		            		'type' : 'job',
		            	},
		            	{
		            		'text' : $.rup.i18nParse($.rup.i18n.app.tasksTree,'factura_devolucion'),
		            		'id' : 'factura_devolucion_reorder',
		            		'type' : 'job',
		            	},
		            	{
		            		'text' : $.rup.i18nParse($.rup.i18n.app.tasksTree,'factura_OM'),
		            		'id' : 'factura_OM_reorder',
		            		'type' : 'job',
		            	}
		            ]
	            }
	        ]},
	    ];
	    
	    /* Codigo JSON del árbol de tareas con ordenación */
	    var tasksTreeJson = [{
	        'id' : 'raizTasks',
	        'text' : $.rup.i18nParse($.rup.i18n.app.tasksTree,'raiz'),
	        'type' : 'tasks',
	        'state' : {
	        	'opened': true,
	            'disabled': false,
	            'selected': false
	        },
	        'children' : [
	        	{
		            'text' : $.rup.i18nParse($.rup.i18n.app.tasksTree,'informes'),
		            'id' : 'informes',
		            'type' : 'forms',
		            'state' : {
		            	'opened': true,
		                'disabled': false,
		                'selected': false
		            },
		            'children' : [
		            	{
		            		'text' : $.rup.i18nParse($.rup.i18n.app.tasksTree,'informe_trimestral'),
		            		'id' : 'informe_trimestral',
		            		'type' : 'job',
		            	},
		            	{
		            		'text' : $.rup.i18nParse($.rup.i18n.app.tasksTree,'informe_gastos_generales'),
		            		'id' : 'informe_gastos_generales',
		            		'type' : 'job',
		            	},
		            	{
		            		'text' : $.rup.i18nParse($.rup.i18n.app.tasksTree,'informe_desperfectos'),
		            		'id' : 'informe_desperfectos',
		            		'type' : 'job',
		            	},
		            	{
		            		'text' : $.rup.i18nParse($.rup.i18n.app.tasksTree,'informe_deudas'),
		            		'id' : 'informe_deudas',
		            		'type' : 'job',
		            	},
		            	{
		            		'text' : $.rup.i18nParse($.rup.i18n.app.tasksTree,'informe_calidad'),
		            		'id' : 'informe_calidad',
		            		'type' : 'job',
		            	},
		            	{
		            		'text' : $.rup.i18nParse($.rup.i18n.app.tasksTree,'informe_perdidas'),
		            		'id' : 'informe_perdidas',
		            		'type' : 'job',
		            	}
		            ]
	        	},
	            {
	                'text' : $.rup.i18nParse($.rup.i18n.app.tasksTree,'transportes'),
	                'id' : 'transportes',
	                'type' : 'transportation',
		            'state' : {
		            	'opened': true,
		                'disabled': false,
		                'selected': false
		            },
		            'children' : [
		            	{
		            		'text' : $.rup.i18nParse($.rup.i18n.app.tasksTree,'pedido_lujua'),
		            		'id' : 'pedido_lujua',
		            		'type' : 'job',
		            	},
		            	{
		            		'text' : $.rup.i18nParse($.rup.i18n.app.tasksTree,'pedido_cemento'),
		            		'id' : 'pedido_cemento',
		            		'type' : 'job',
		            	},
		            	{
		            		'text' : $.rup.i18nParse($.rup.i18n.app.tasksTree,'pedido_martillo'),
		            		'id' : 'pedido_martillo',
		            		'type' : 'job',
		            	}
		            ]
	            },
	            {
	                'text' : $.rup.i18nParse($.rup.i18n.app.tasksTree,'reparaciones'),
	                'id' : 'reparaciones',
	                'type' : 'repair',
		            'state' : {
		            	'opened': true,
		                'disabled': false,
		                'selected': false
		            },
		            'children' : [
		            	{
		            		'text' : $.rup.i18nParse($.rup.i18n.app.tasksTree,'reparacion_fachada'),
		            		'id' : 'reparacion_fachada',
		            		'type' : 'job',
		            	},
		            	{
		            		'text' : $.rup.i18nParse($.rup.i18n.app.tasksTree,'reparacion_indundacion'),
		            		'id' : 'reparacion_indundacion',
		            		'type' : 'job',
		            	},
		            	{
		            		'text' : $.rup.i18nParse($.rup.i18n.app.tasksTree,'reparacion_suelo'),
		            		'id' : 'reparacion_suelo',
		            		'type' : 'job',
		            	},
		            	{
		            		'text' : $.rup.i18nParse($.rup.i18n.app.tasksTree,'reparacion_ratas'),
		            		'id' : 'reparacion_ratas',
		            		'type' : 'job',
		            	}
		            ]
	            },
	            {
	                'text' : $.rup.i18nParse($.rup.i18n.app.tasksTree,'suministros'),
	                'id' : 'suministros',
	                'type' : 'suppliers',
		            'state' : {
		            	'opened': true,
		                'disabled': false,
		                'selected': false
		            },
		            'children' : [
		            	{
		            		'text' : $.rup.i18nParse($.rup.i18n.app.tasksTree,'mp_ladrillos'),
		            		'id' : 'mp_ladrillos',
		            		'type' : 'job',
		            	},
		            	{
		            		'text' : $.rup.i18nParse($.rup.i18n.app.tasksTree,'mp_clavos'),
		            		'id' : 'mp_clavos',
		            		'type' : 'job',
		            	},
		            	{
		            		'text' : $.rup.i18nParse($.rup.i18n.app.tasksTree,'mp_cemento'),
		            		'id' : 'mp_cemento',
		            		'type' : 'job',
		            	},
		            	{
		            		'text' : $.rup.i18nParse($.rup.i18n.app.tasksTree,'mp_recambios'),
		            		'id' : 'mp_recambios',
		            		'type' : 'job',
		            	},
		            	{
		            		'text' : $.rup.i18nParse($.rup.i18n.app.tasksTree,'mp_jabon'),
		            		'id' : 'mp_jabon',
		            		'type' : 'job',
		            	},
		            	{
		            		'text' : $.rup.i18nParse($.rup.i18n.app.tasksTree,'mp_botas'),
		            		'id' : 'mp_botas',
		            		'type' : 'job',
		            	},
		            	{
		            		'text' : $.rup.i18nParse($.rup.i18n.app.tasksTree,'mp_cascos'),
		            		'id' : 'mp_cascos',
		            		'type' : 'job',
		            	}
		            ]
	            },
	            {
	                'text' : $.rup.i18nParse($.rup.i18n.app.tasksTree,'facturas'),
	                'id' : 'facturas',
	                'type' : 'invoice',
		            'state' : {
		            	'opened': true,
		                'disabled': false,
		                'selected': false
		            },
		            'children' : [
		            	{
		            		'text' : $.rup.i18nParse($.rup.i18n.app.tasksTree,'factura_VH'),
		            		'id' : 'factura_VH',
		            		'type' : 'job',
		            	},
		            	{
		            		'text' : $.rup.i18nParse($.rup.i18n.app.tasksTree,'factura_Lindo'),
		            		'id' : 'factura_Lindo',
		            		'type' : 'job',
		            	},
		            	{
		            		'text' : $.rup.i18nParse($.rup.i18n.app.tasksTree,'factura_devolucion'),
		            		'id' : 'factura_devolucion',
		            		'type' : 'job',
		            	},
		            	{
		            		'text' : $.rup.i18nParse($.rup.i18n.app.tasksTree,'factura_OM'),
		            		'id' : 'factura_OM',
		            		'type' : 'job',
		            	}
		            ]
	            }
	        ]},
	    ];
	    
	    /* Codigo JSon del árbol de trabajadores */
	    var workersTreeJson = [
	        {
	            'text' : $.rup.i18nParse($.rup.i18n.app.tasksTree,'workers'),
	            'id' : 'raizWorkers',
	            'type' : 'workers',
	            'state' : {
	            	'opened': true,
	                'disabled': false,
	                'selected': false
	            },
		        'children' : [
	            	{
	            		'text' : 'Abanca Rodrigez Silvia',
	            		'id': 'worker_silviaAR',
	            		'type' : 'worker',
	            	},
	            	{
	            		'text' : 'Almonzon Mendia Juan',
	            		'id': 'worker_juanAM',
	            		'type' : 'worker',
	            	},
	            	{
	            		'text' : 'Alonso Ruiz Laura',
	            		'id': 'worker_lauraAR',
	            		'type' : 'worker',
	            	},
	            	{
	            		'text' : 'Gil Sandia Marta',
	            		'id': 'worker_martaGS',
	            		'type' : 'worker',
	            	},
	            	{
	            		'text' : 'Jiménez Arriurtua Francisco',
	            		'id': 'worker_franciscoJA',
	            		'type' : 'worker',
	            	},
	            	{
	            		'text' : 'Mantenimientos Jet',
	            		'id': 'enterprise_mantenimientosJet',
	            		'type' : 'enterprise',
	            	},
	            	{
	            		'text' : 'Matias S.L.',
	            		'id': 'enterprise_matiasSL',
	            		'type' : 'enterprise',
	            	},
	            	{
	            		'text' : 'Montajes Loiu',
	            		'id': 'enterprise_montajesLoiu',
	            		'type' : 'enterprise',
	            	},
	            	{
	            		'text' : 'Mortaro Filon Sara',
	            		'id': 'worker_saraMF',
	            		'type' : 'worker',
	            	},
	            	{
	            		'text' : 'Ortiz Dulon Jose',
	            		'id': 'worker_joseOD',
	            		'type' : 'worker',
	            	},
	            	{
	            		'text' : 'Padilla Alcantara Sergio',
	            		'id': 'worker_sergioPA',
	            		'type' : 'worker',
	            	},
	            	{
	            		'text' : 'Puertas y molduras Sanz',
	            		'id': 'enterprise_puertasMoldurasSanz',
	            		'type' : 'enterprise',
	            	},
	            	{
	            		'text' : 'Randal Sweder Moly',
	            		'id': 'worker_molyRS',
	            		'type' : 'worker',
	            	},
	            	{
	            		'text' : 'Ruiz de Santiesteban Pedro',
	            		'id': 'worker_pedroRS',
	            		'type' : 'worker',
	            	},
	            	{
	            		'text' : 'Sánchez Rodin Pablo',
	            		'id': 'worker_pabloSR',
	            		'type' : 'worker',
	            	}
		        ]
	        },
	    ];
	    
	    /* Codigo JSon del árbol de trabajadores */
	    var workersDepartmentTreeJson = [
	    	{
	    		'text' : $.rup.i18nParse($.rup.i18n.app.tasksTree,'depAdmin'),
	    		'id' : 'departmentAdmin',
	    		'type' : 'workers',
	            'state' : {
	            	'opened': true,
	                'disabled': false,
	                'selected': false
	            },
		        'children' : [
	            	{
	            		'text' : 'Abanca Rodrigez Silvia',
	            		'id': 'workerDepartment_silviaAR',
	            		'type' : 'worker'
	            	},
	            	{
	            		'text' : 'Alonso Ruiz Laura',
	            		'id': 'workerDepartment_lauraAR',
	            		'type' : 'worker'
	            	},
	            	{
	            		'text' : 'Gil Sandia Marta',
	            		'id': 'workerDepartment_martaGS',
	            		'type' : 'worker'
	            	},
	            	{
	            		'text' : 'Jiménez Arriurtua Francisco',
	            		'id': 'workerDepartment_franciscoJA',
	            		'type' : 'worker'
	            	},
	            	{
	            		'text' : 'Padilla Alcantara Sergio',
	            		'id': 'workerDepartment_sergioPA',
	            		'type' : 'worker'
	            	},
	            	{
	            		'text' : 'Sánchez Rodin Pablo',
	            		'id': 'workerDepartment_pabloSR',
	            		'type' : 'worker'
	            	}
		        ]
	    	},
	        {
	            'text' : $.rup.i18nParse($.rup.i18n.app.tasksTree,'depClientes'),
	            'id' : 'departmentClientes',
	            'type' : 'workers',
	            'state' : {
	            	'opened': true,
	                'disabled': false,
	                'selected': false
	            },
		        'children' : [
	            	{
	            		'text' : 'Alzola Urierate Leticia',
	            		'id': 'workerDepartment_leticiaAU',
	            		'type' : 'worker'
	            	},
	            	{
	            		'text' : 'Aranguren Loinaz Jose',
	            		'id': 'workerDepartment_joseAL',
	            		'type' : 'worker'
	            	},
	            	{
	            		'text' : 'Bermejo Solo Ana',
	            		'id': 'workerDepartment_anaBS',
	            		'type' : 'worker'
	            	},
	            	{
	            		'text' : 'Garzon Alonso Luis',
	            		'id': 'workerDepartment_luisGA',
	            		'type' : 'worker'
	            	},
	            	{
	            		'text' : 'Jerez Templado Bisball',
	            		'id': 'workerDepartment_bisballJT',
	            		'type' : 'worker'
	            	},
	            	{
	            		'text' : 'Noiz Sapuerta Gordi',
	            		'id': 'workerDepartment_gordiNS',
	            		'type' : 'worker'
	            	},
	            	{
	            		'text' : 'Ortiz Dulon Jose',
	            		'id': 'workerDepartment_joseOD',
	            		'type' : 'worker'
	            	},
	            	{
	            		'text' : 'Prudencio Fratan Armand',
	            		'id': 'workerDepartment_armandPF',
	            		'type' : 'worker'
	            	},
	            	{
	            		'text' : 'Ruiz de Santiesteban Pedro',
	            		'id': 'workerDepartment_pedroRS',
	            		'type' : 'worker'
	            	}
		        ]
	        },
	        {
	        	'text' : $.rup.i18nParse($.rup.i18n.app.tasksTree,'depReparaciones'),
	        	'id' : 'departmentReparaciones',
	        	'type' : 'workers',
	            'state' : {
	            	'opened': true,
	                'disabled': false,
	                'selected': false
	            },
		        'children' : [
	            	{
	            		'text' : 'Anemo Muñoz Jon',
	            		'id': 'workerDepartment_jonAM',
	            		'type' : 'worker'
	            	},
	            	{
	            		'text' : 'Almonzon Mendia Juan',
	            		'id': 'workerDepartment_juanAM',
	            		'type' : 'worker'
	            	},
	            	{
	            		'text' : 'Bornaz Satrustegui Armando',
	            		'id': 'workerDepartment_armandoBS',
	            		'type' : 'worker'
	            	},
	            	{
	            		'text' : 'Muleto Delito Afelio',
	            		'id': 'workerDepartment_afelioMD',
	            		'type' : 'worker'
	            	},
	            	{
	            		'text' : 'Gaztedi Jobar Maria',
	            		'id': 'workerDepartment_mariaGJ',
	            		'type' : 'worker'
	            	},
	            	{
	            		'text' : 'Zarate Oligarco Ramon',
	            		'id': 'workerDepartment_ramonZO',
	            		'type' : 'worker'
	            	}
		        ]
	        },
	        {
	            'text' : $.rup.i18nParse($.rup.i18n.app.tasksTree,'depSuministros'),
	            'id' : 'departmentSuministros',
	            'type' : 'workers',
	            'state' : {
	            	'opened': true,
	                'disabled': false,
	                'selected': false
	            },
		        'children' : [
	            	{
	            		'text' : 'Eredia Puyol Maider',
	            		'id': 'workerDepartment_maiderEP',
	            		'type' : 'worker'
	            	},
	            	{
	            		'text' : 'Montero Rucio Luis',
	            		'id': 'workerDepartment_luisMR',
	            		'type' : 'worker'
	            	},
	            	{
	            		'text' : 'Perez Mendia Jone',
	            		'id': 'workerDepartment_jonePM',
	            		'type' : 'worker'
	            	},
	            	{
	            		'text' : 'Sanz Olabe Gotzon',
	            		'id': 'workerDepartment_gotzonSO',
	            		'type' : 'worker'
	            	}
		        ]
	        }
	    ];
		var codeDialogSelector = $('#treeCodeDialog');
	    
	    function codeCleanLoad(id){
	        $('#AccordionCode').append(codeDialogSelector.children());
	        codeDialogSelector.append($('#AccordionCode #'+id));
	    }
	    
	    /* Dialogo de visualización de código */
	    codeDialogSelector.rup_dialog({
	        type: $.rup.dialog.DIV,
	        autoOpen: false,
	        modal: true,
	        resizable: true,
	        width: 'auto',
	        title: $.rup.i18nParse($.rup.i18n.app.treeCodeDialog,'title'),
	        buttons: [{
	            text: $.rup.i18nParse($.rup.i18n.base,'rup_global.close'),
	            click: function () { 
	                $(this).dialog('close');
	            }					
	        }]
	    });
	
	    /* Ejemplo de arbol de tareas pendientes con reordenacion */
	    $('#tasksReorderTree').rup_tree({
	    	'plugins': ['types', 'dnd'],
	        'core' : {
	            'data': tasksReorderTreeJson,
	            'check_callback': function(operation, node, node_parent, node_position, more) {
	            	if (operation === "move_node" && more.origin.element[0].id === 'tasksReorderTree') {
	            		return node.parent === node_parent.id;
	            	}
	            	return false;
                }
	        },
	        'types' : tasksTreeTypes
	    });
	    
	    $('#btnReorderTreeUniqueControl').bind('click', function(){
	        codeCleanLoad('reorderAccordionCode');
	        codeDialogSelector.rup_dialog('open');
	        $('#reorderAccordionCode').rup_accordion('activate', 0);
	    });
	    
	    /* Ejemplo de arboles con distribucion de tareas (D&D) */
	    $('#tasksTree').rup_tree({
	    	'plugins': ['types', 'dnd'],
	        'core' : {
	            'data': tasksTreeJson,
	            'check_callback': function(operation, node, node_parent, node_position, more) {
	            	if (operation === "delete_node") {
	            		return true;
	            	}
	            	return false;
                }
	        },
	        'types' : tasksTreeTypes
	    });	
	    
	    $('#workersTree').rup_tree({
	    	'plugins': ['types', 'dnd'],
	        'core' : {
	            'data': workersTreeJson,
	            'check_callback': function(operation, node, node_parent, node_position, more) {
	            	if (more.origin.element[0].id === 'workersTree') {
	            		return true;
	            	}
	            	return false;
                }
	        },
	        'types' : workersTreeTypes
	    });
	        
	    $('#btnExchangeTreeUniqueControl').bind('click', function(){
	        codeCleanLoad('exchangeAccordionCode');
	        codeDialogSelector.rup_dialog('open');
	        $('#exchangeAccordionCode').rup_accordion ('activate', 0);
	    });
	    
	    /* Gestion del panel visual */
	    var treeNodesExchangePanelPosition = $('#treeNodesExchangePanel').position();
	    $('.treeNodesExchangePanel_center_img').css('top',treeNodesExchangePanelPosition.top+(($('#treeNodesExchangePanel').height())/2)-(($('.treeNodesExchangePanel_center_img').height())/2))
	        .css('left',treeNodesExchangePanelPosition.left+(($('#treeNodesExchangePanel').width())/2)-(($('.treeNodesExchangePanel_center_img').width())/2));
	    $('.treeNodesExchangePanel_center').css('visibility', 'visible');
	    $('.treeNodesExchangePanel_center_img').bind('click', function(){
	        $.rup_messages('msgError', {
	            title: $.rup.i18nParse($.rup.i18n.app.treeNodesExchangePanel_center,'errorOp'),
	            message: $.rup.i18nParse($.rup.i18n.app.treeNodesExchangePanel_center,'funtError')
	        });
	    });
	    
	    /* Árbol y código asociado a la gestión de ascensos y despidos */
	    $('#promotionsDismissalsTree').rup_tree({
	    	'plugins': ['types', 'sort', 'dnd'],
	        'core' : {
	            'data': workersDepartmentTreeJson,
	            'check_callback': false
	        },
	        'types' : workersTreeTypes,
	        'dnd' : {
	        	'always_copy': true,
	        	'is_draggable': function(nodes, event) {
	        		if((nodes[0].type !== 'worker')) {
	                    return false;
	                }
	                return true;
	        	},
				'use_html5': false
	        }
	    });
	    
	    $(document).on('dnd_move.vakata', function (data, element) {
	    	const $target = $(element.event.target);
	    	if ($target.hasClass("promotionsDismissalsActionPanel_list")) {
	    		$(element.helper[0]).find('i').toggleClass('jstree-er jstree-ok');
	    	}
	    });
	    
	    $(document).on('dnd_stop.vakata', function (data, element) {
	    	const $target = $(element.event.target);
    		const $clonedElement = $(element.element).clone();
	    	if ($('#' + $clonedElement.prop('id') + '_list').length == 0 && $target.hasClass("promotionsDismissalsActionPanel_list")) {
	    		$target.append($clonedElement.prop('id', $clonedElement.prop('id') + '_list'));
	    	}
	    });
	    
	    $('#btnPromotionsDismissalsUniqueControl').bind('click', function(){
	        codeCleanLoad('promotionsDismissalsAccordionCode');
	        codeDialogSelector.rup_dialog('open');
	        $('#promotionsDismissalsAccordionCode').rup_accordion('activate', 0);
	    });
	    
	    /* Accordions de visualizacion de codigo */
	    $('.rup_accordion').rup_accordion({
	        autoHeight: false
	    });
	
	
	    $('.contenedor').addClass('show');
	});
});