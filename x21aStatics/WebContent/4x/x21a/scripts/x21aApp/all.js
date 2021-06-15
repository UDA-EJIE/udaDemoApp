/*!
 * Copyright 2011 E.J.I.E., S.A.
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
jQuery(function($){
	var tableColModelsComarca = [
	    {
	        name: 'descEs',
	        index: 'descEs',
	        editable: true,
	        hidden: false
	    },
	    {
	        name: 'descEu',
	        index: 'descEu',
	        editable: true,
	        hidden: false
	    },
	    {
	        name: 'css',
	        index: 'css',
	        editable: true,
	        hidden: false
	    },
	    {
	        name: 'provincia.code',
	        index: 'provincia.code',
	        editable: true,
	        hidden: false,
	        rupType: 'combo',
            editoptions: {
            	source : '../tableComarca/provincia',
                sourceParam : {
                	label: 'desc' + $.rup_utils.capitalizedLang(), 
                	value: 'code',
    	            style: 'css'
                },
    	        rowStriping: true,
    	        blank: '',
                width: '100%',
                customClasses: ['select-material']
            }
	    },
	    {
	        name: 'provincia.descEs',
	        index: 'provincia.descEs',
	        editable: true,
	        hidden: false
	    }
    ];
	
	window.initRupI18nPromise.then(function () {
		$('#comarca').rup_table({
	        primaryKey: 'code',
	        loadOnStartUp: true,
	        filter: {
	            id: 'comarca_filter_form',
	            filterToolbar: 'comarca_filter_toolbar',
	            collapsableLayerId: 'comarca_filter_fieldset'
	        },
	        colModel: tableColModelsComarca,
	        colReorder: {
	            fixedColumnsLeft: 1
	        },
	        seeker: {
	            activate: true,
	            colModel: tableColModelsComarca
	        },
	        buttons: {
	            activate: true
	        },
	        select: {
	        	activate: true
	        },
            enableDynamicForms: true,
	        formEdit: {
	            detailForm: '#comarca_detail_div',
	            url: '../tableComarca/editForm'
	        }
	    });
	    
	    $('#feedback').rup_feedback({ type: 'ok', message:'Este es un ejemplo de <b>Feedback</b>'});
	    
	    $('#date').rup_date({
	        changeMonth: false,
	        changeYear: false,
	        numberOfMonths: 1
	    });
	    
	    $('#time').rup_time({});
	    
	    $('#comboProvincia').rup_combo({
	        source : 'comboEnlazadoSimple/remoteEnlazadoProvincia',
	        sourceParam : {label:'desc'+$.rup_utils.capitalizedLang(), value:'code', style:'css'},
	        selected: 3,
	        width: '100%',
            customClasses: ['select-material']
	    });
	    $('#comboComarca').rup_combo({
	        parent: ['comboProvincia'],
	        source : 'comboEnlazadoSimple/remoteEnlazadoComarca',
	        sourceParam : {label:'desc'+$.rup_utils.capitalizedLang(), value:'code', style:'css'},
	        width: '100%',
            customClasses: ['select-material']
	    });
	    
	    $('#multicomboGruposRemoto').rup_combo({
	        sourceGroup : 'comboSimple/remoteGroup',
	        sourceParam : {label:'desc'+$.rup_utils.capitalizedLang(), value:'code', style:'css'},
	        width: 500,
	        multiselect: true,
	        width: '100%',
            customClasses: ['select-material']
	    });
	    
	    $('#autocomplete').rup_autocomplete({
	        source : ['asp', 'c', 'c++', 'coldfusion', 'groovy', 'haskell', 'java', 'javascript', 'perl', 'php', 'python', 'ruby', 'scala']
	    });
	    
	    $('#tooltip').rup_tooltip(); 
	    
	    $('#tabs').rup_tabs({
	        tabs : [
	            {i18nCaption:'pestana0', tabs: [
	                {i18nCaption:'sub01', url:'fragmento1'},
	                {i18nCaption:'sub02', url:'fragmento1'}
	            ]},
	            {i18nCaption:'pestana1', tabs: [
	                {i18nCaption:'sub10', url:'tab2Fragment'},
	                {i18nCaption:'sub11', url:'tab3Fragment'}
	            ]}
	        ]
	    });
	    
	    //dialog
	    $('#dialogLauncher').bind('click', function () {
	        $('#dialog').rup_dialog({
	            type: jQuery.rup.dialog.AJAX,
	            url: '../patrones/allDialog' ,
	            autoOpen: true,
	            modal: true,
	            width: '1200',
	            height: '750',
	            resizable: true,
	            title: 'Todos los patrones en diálogo',
	            buttons: [{
	                text: 'Aceptar',
	                click: function () { 
	                    $(this).dialog('close');
	                }					
	            }],
	            open : function() { 
	                //Configurar dialog
	            	var tableColModels = [
	                	{
	                        name: 'nombre',
	                        index: 'nombre',
	                        editable: true,
	                        hidden: false
	                    },
	                    {
	                        name: 'apellido1',
	                        index: 'apellido1',
	                        editable: true,
	                        hidden: false
	                    },
	                    { 
	                    	name: "apellido2", 
	                    	index: "apellido2", 
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
	                        name: 'ejie',
	                        index: 'ejie',
	                        editable: true,
	                        hidden: false,
	                        width: 60,
	                        edittype: 'checkbox',
	                        formatter: 'checkbox',
	                        align: 'center',
	                        editoptions: {
	                            value: '1:0'
	                        }
	                    },
	                    {
	                        name: 'fechaAlta',
	                        index: 'fechaAlta',
	                        editable: true,
	                        hidden: false,
	                        width: 120,
	                        rupType: 'date',
	                        editoptions: {
	                            labelMaskId: 'fecha-mask',
	                            showButtonPanel: true,
	                            showOtherMonths: true,
	                            noWeekend: true
	                        }
	                    },
	                    {
	                        name: 'fechaBaja',
	                        index: 'fechaBaja',
	                        editable: false,
	                        hidden: false,
	                        width: 120,
	                        rupType: 'date',
	                        editoptions: {
	                            labelMaskId: 'fecha-mask',
	                            showButtonPanel: true,
	                            showOtherMonths: true,
	                            noWeekend: true
	                        }
	                    },
	                    {
	                        name: 'rol',
	                        index: 'rol',
	                        editable: true,
	                        hidden: false,
	                        width: 140,
	                        rupType: 'combo',
	                        formatter: 'rup_combo',
	                        editoptions: {
	                            source : '../table/roles',
	                            sourceParam : {label: 'label', value: 'value'},
	                            blank: '',
	                            width: '100%',
	                            customClasses: ['select-material']
	                        }
	                    }
	                ];
	            	
	        	    $('#example').rup_table({
	        	        loadOnStartUp: true,
	        	        filter: {
	        	            id: 'example_filter_form',
	        	            filterToolbar: 'example_filter_toolbar',
	        	            collapsableLayerId: 'example_filter_fieldset'
	        	        },
	        	        colModel: tableColModels,
	        	        seeker: {
	        	            activate: true,
	        	            colModel: tableColModels
	        	        },
	        	        buttons: {
	        	            activate: true
	        	        },
	        	        multiSelect: {
	        	        	style: 'multi'
	        	        },
	        	        enableDynamicForms: true,
	        	        formEdit: {
	        	            detailForm: '#example_detail_div',
	        	            url: '../table/editForm'
	        	        }
	        	    });  
	        	    
	        	    $('#feedback_dialog').rup_feedback({ type: 'ok', message:'Este es un ejemplo de <b>Feedback</b>'});
	        	    
	        	    $('#date_dialog').rup_date({
	        	        changeMonth: false,
	        	        changeYear: false,
	        	        numberOfMonths: 1
	        	    });
	        	    
	        	    $('#time_dialog').rup_time({});
	        	    
	        	    $('#comboProvincia_dialog').rup_combo({
	        	        source : 'comboEnlazadoSimple/remoteEnlazadoProvincia',
	        	        sourceParam : {label:'desc'+$.rup_utils.capitalizedLang(), value:'code', style:'css'},
	        	        selected: 3
	        	    });
	        	    $('#comboComarca_dialog').rup_combo({
	        	        parent: ['comboProvincia'],
	        	        source : 'comboEnlazadoSimple/remoteEnlazadoComarca',
	        	        sourceParam : {label:'desc'+$.rup_utils.capitalizedLang(), value:'code', style:'css'}
	        	    });
	        	    
	        	    $('#multicomboGruposRemoto_dialog').rup_combo({
	        	        sourceGroup : 'comboSimple/remoteGroup',
	        	        sourceParam : {label:'desc'+$.rup_utils.capitalizedLang(), value:'code', style:'css'},
	        	        width: 500,
	        	        multiselect: true
	        	    });
	        	    
	        	    $('#autocomplete_dialog').rup_autocomplete({
	        	        source : ['asp', 'c', 'c++', 'coldfusion', 'groovy', 'haskell', 'java', 'javascript', 'perl', 'php', 'python', 'ruby', 'scala']
	        	    });
	        	    
	        	    $('#tooltip_dialog').rup_tooltip(); 
	        	    
	        	    $('#tabs_dialog').rup_tabs({
	        	        tabs : [
	        	            {i18nCaption:'pestana0', tabs: [
	        	                {i18nCaption:'sub01', url:'fragmento1'},
	        	                {i18nCaption:'sub02', url:'fragmento1'}
	        	            ]},
	        	            {i18nCaption:'pestana1', tabs: [
	        	                {i18nCaption:'sub10', url:'tab2Fragment'},
	        	                {i18nCaption:'sub11', url:'tab3Fragment'}
	        	            ]}
	        	        ]
	        	    });
	            }
	        });	
	    });
	});
	
	$('.contenedor').addClass('show');
});