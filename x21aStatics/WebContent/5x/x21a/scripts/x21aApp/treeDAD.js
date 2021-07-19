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
    
    /* Tipos para la gestión de tareas */
    var tasksTreeTypes = {
        'valid_children' : ['tasks'],
        'types' : {
            'tasks' : {
                'icon' : {
                    'image' : $.rup.STATICS+'/x21a/images/PendingWorks.png'
                },
                'valid_children' : ['forms', 'invoice', 'repair', 'suppliers', 'Transportation']
            },
            'forms' : {
                'icon' : {
                    'image' : $.rup.STATICS+'/x21a/images/forms.png'
                },
                'valid_children' : ['job']
            },
            'invoice' : {
                'icon' : {
                    'image' : $.rup.STATICS+'/x21a/images/invoice.png'
                },
                'valid_children' : ['job']
            },
            'repair' : {
                'icon' : {
                    'image' : $.rup.STATICS+'/x21a/images/repair.png'
                },
                'valid_children' : ['job']
            },
            'job' : {
                'icon' : {
                    'image' : $.rup.STATICS+'/x21a/images/job.png'
                },
                'valid_children' : ['none']
            },
            'suppliers' : {
                'icon' : {
                    'image' : $.rup.STATICS+'/x21a/images/suppliers.png'
                },
                'valid_children' : ['job']
            },
            'Transportation' : {
                'icon' : {
                    'image' : $.rup.STATICS+'/x21a/images/Transportation.png'
                },
                'valid_children' : ['job']
            }
        }
    };
    
    /* Tipos para la gestion de trabajadores */
    var workersTreeTypes = {
        'valid_children' : ['workers'],
        'types' : {
            'enterprise' : {
                'icon' : {
                    'image' : $.rup.STATICS+'/x21a/images/enterprise.png'
                },
                'valid_children' : ['job']
            },
            'worker' : {
                'icon' : {
                    'image' : $.rup.STATICS+'/x21a/images/worker.png'
                },
                'valid_children' : ['job']
            },
            'workers' : {
                'icon' : {
                    'image' : $.rup.STATICS+'/x21a/images/workers.png'
                },
                'valid_children' : ['worker', 'enterprise']
            },
            'job' : {
                'icon' : {
                    'image' : $.rup.STATICS+'/x21a/images/job.png'
                },
                'valid_children' : ['none']
            }
        }
    };
    
    /* Codigo JSon del árbol de tareas */
    var tasksTreeJson = [
        {'data' : { 
            'title' : $.rup.i18nParse($.rup.i18n.app.tasksTree,'raiz')
        },
        'attr' : { 'id' : 'raizTasks', 'rel' : 'tasks'},
        'state' : 'open',
        'children' : [
            {'data' : { 
                'title' : $.rup.i18nParse($.rup.i18n.app.tasksTree,'informes')
            },
            'attr' : { 'id' : 'informes', 'rel' : 'forms'},
            'state' : 'open',
            'children' : [
                {'data' : { 
                    'title' : $.rup.i18nParse($.rup.i18n.app.tasksTree,'informe_trimestral')
                },
                'attr' : { 'id' : 'informe_trimestral', 'rel' : 'job'}
                },
                {'data' : { 
                    'title' : $.rup.i18nParse($.rup.i18n.app.tasksTree,'informe_gastos_generales')
                },
                'attr' : { 'id' : 'informe_trimestral', 'rel' : 'job'}
                },
                {'data' : { 
                    'title' : $.rup.i18nParse($.rup.i18n.app.tasksTree,'informe_desperfectos')
                },
                'attr' : { 'id' : 'informe_trimestral', 'rel' : 'job'}
                },
                {'data' : { 
                    'title' : $.rup.i18nParse($.rup.i18n.app.tasksTree,'informe_deudas')
                },
                'attr' : { 'id' : 'informe_trimestral', 'rel' : 'job'}
                },
                {'data' : { 
                    'title' : $.rup.i18nParse($.rup.i18n.app.tasksTree,'informe_calidad')
                },
                'attr' : { 'id' : 'informe_trimestral', 'rel' : 'job'}
                },
                {'data' : { 
                    'title' : $.rup.i18nParse($.rup.i18n.app.tasksTree,'informe_perdidas')
                },
                'attr' : { 'id' : 'informe_trimestral', 'rel' : 'job'}
                }
            ]},
            {'data' : { 
                'title' : $.rup.i18nParse($.rup.i18n.app.tasksTree,'transportes')
            },
            'attr' : { 'id' : 'transportes', 'rel' : 'Transportation'},
            'state' : 'open',
            'children' : [
                {'data' : { 
                    'title' : $.rup.i18nParse($.rup.i18n.app.tasksTree,'pedido_lujua')
                },
                'attr' : { 'id' : 'pedido_lujua', 'rel' : 'job'}
                },
                {'data' : { 
                    'title' : $.rup.i18nParse($.rup.i18n.app.tasksTree,'pedido_cemento')
                },
                'attr' : { 'id' : 'pedido_cemento', 'rel' : 'job'}
                },
                {'data' : { 
                    'title' : $.rup.i18nParse($.rup.i18n.app.tasksTree,'pedido_martillo')
                },
                'attr' : { 'id' : 'pedido_martillo', 'rel' : 'job'}
                }
            ]},
            {'data' : { 
                'title' : $.rup.i18nParse($.rup.i18n.app.tasksTree,'reparaciones')
            },
            'attr' : { 'id' : 'reparaciones', 'rel' : 'repair'},
            'state' : 'open',
            'children' : [
                {'data' : { 
                    'title' : $.rup.i18nParse($.rup.i18n.app.tasksTree,'reparacion_fachada')
                },
                'attr' : { 'id' : 'reparacion_fachada', 'rel' : 'job'}
                },
                {'data' : { 
                    'title' : $.rup.i18nParse($.rup.i18n.app.tasksTree,'reparacion_indundacion')
                },
                'attr' : { 'id' : 'reparacion_indundacion', 'rel' : 'job'}
                },
                {'data' : { 
                    'title' : $.rup.i18nParse($.rup.i18n.app.tasksTree,'reparacion_suelo')
                },
                'attr' : { 'id' : 'reparacion_suelo', 'rel' : 'job'}
                },
                {'data' : { 
                    'title' : $.rup.i18nParse($.rup.i18n.app.tasksTree,'reparacion_ratas')
                },
                'attr' : { 'id' : 'reparacion_ratas', 'rel' : 'job'}
                }         
            ]},
            {'data' : { 
                'title' : $.rup.i18nParse($.rup.i18n.app.tasksTree,'suministros')
            },
            'attr' : { 'id' : 'suministros', 'rel' : 'suppliers'},
            'state' : 'open',
            'children' : [
                {'data' : { 
                    'title' : $.rup.i18nParse($.rup.i18n.app.tasksTree,'mp_ladrillos')
                },
                'attr' : { 'id' : 'mp_ladrillos', 'rel' : 'job'}
                },
                {'data' : { 
                    'title' : $.rup.i18nParse($.rup.i18n.app.tasksTree,'mp_clavos')
                },
                'attr' : { 'id' : 'mp_clavos', 'rel' : 'job'}
                },
                {'data' : { 
                    'title' : $.rup.i18nParse($.rup.i18n.app.tasksTree,'mp_cemento')
                },
                'attr' : { 'id' : 'mp_cemento', 'rel' : 'job'}
                },
                {'data' : { 
                    'title' : $.rup.i18nParse($.rup.i18n.app.tasksTree,'mp_recambios')
                },
                'attr' : { 'id' : 'mp_recambios', 'rel' : 'job'}
                },
                {'data' : { 
                    'title' : $.rup.i18nParse($.rup.i18n.app.tasksTree,'mp_jabon')
                },
                'attr' : { 'id' : 'mp_jabon', 'rel' : 'job'}
                },
                {'data' : { 
                    'title' : $.rup.i18nParse($.rup.i18n.app.tasksTree,'mp_botas')
                },
                'attr' : { 'id' : 'mp_botas', 'rel' : 'job'}
                },
                {'data' : { 
                    'title' : $.rup.i18nParse($.rup.i18n.app.tasksTree,'mp_cascos')
                },
                'attr' : { 'id' : 'mp_cascos', 'rel' : 'job'}
                }          
            ]},
            {'data' : { 
                'title' : $.rup.i18nParse($.rup.i18n.app.tasksTree,'facturas')
            },
            'attr' : { 'id' : 'facturas', 'rel' : 'invoice'},
            'state' : 'open',
            'children' : [
                {'data' : { 
                    'title' : $.rup.i18nParse($.rup.i18n.app.tasksTree,'factura_VH')
                },
                'attr' : { 'id' : 'factura_VH', 'rel' : 'job'}
                },
                {'data' : { 
                    'title' : $.rup.i18nParse($.rup.i18n.app.tasksTree,'factura_Lindo')
                },
                'attr' : { 'id' : 'factura_Lindo', 'rel' : 'job'}
                },
                {'data' : { 
                    'title' : $.rup.i18nParse($.rup.i18n.app.tasksTree,'factura_devolucion')
                },
                'attr' : { 'id' : 'factura_devolucion', 'rel' : 'job'}
                },
                {'data' : { 
                    'title' : $.rup.i18nParse($.rup.i18n.app.tasksTree,'factura_OM')
                },
                'attr' : { 'id' : 'factura_OM', 'rel' : 'job'}
                }
            ]}
        ]},
    ];
    
    /* Codigo JSon del árbol de trabajadores */
    var workersTreeJson = [
        {'data' : { 
            'title' : $.rup.i18nParse($.rup.i18n.app.tasksTree,'workers')
        },
        'attr' : { 'id' : 'raizWorkers', 'rel' : 'workers'},
        'state' : 'open',
        'children' : [
            {'data' : { 
                'title' : 'Abanca Rodrigez Silvia'
            },
            'attr' : {'rel' : 'worker'}
            },
            {'data' : { 
                'title' : 'Almonzon Mendia Juan'
            },
            'attr' : {'rel' : 'worker'}
            },
            {'data' : { 
                'title' : 'Alonso Ruiz Laura'
            },
            'attr' : {'rel' : 'worker'}
            },
            {'data' : { 
                'title' : 'Gil Sandia Marta'
            },
            'attr' : {'rel' : 'worker'}
            },
            {'data' : { 
                'title' : 'Jiménez Arriurtua Francisco'
            },
            'attr' : {'rel' : 'worker'}
            },
            {'data' : { 
                'title' : 'Mantenimientos Jet'
            },
            'attr' : {'rel' : 'enterprise'}
            },
            {'data' : { 
                'title' : 'Matias S.L.'
            },
            'attr' : {'rel' : 'enterprise'}
            },
            {'data' : { 
                'title' : 'Montajes Loiu'
            },
            'attr' : {'rel' : 'enterprise'}
            },
            {'data' : { 
                'title' : 'Mortaro Filon Sara'
            },
            'attr' : {'rel' : 'worker'}
            },
            {'data' : { 
                'title' : 'Ortiz Dulon Jose'
            },
            'attr' : {'rel' : 'worker'}
            },
            {'data' : { 
                'title' : 'Padilla Alcantara Sergio'
            },
            'attr' : {'rel' : 'worker'}
            },
            {'data' : { 
                'title' : 'Puertas y molduras Sanz'
            },
            'attr' : {'rel' : 'enterprise'}
            },
            {'data' : { 
                'title' : 'Randal Sweder Moly'
            },
            'attr' : {'rel' : 'worker'}
            },
            {'data' : { 
                'title' : 'Ruiz de Santiesteban Pedro'
            },
            'attr' : {'rel' : 'worker'}
            },
            {'data' : { 
                'title' : 'Sánchez Rodin Pablo'
            },
            'attr' : {'rel' : 'worker'}
            }
        ]},
    ];
    
    /* Codigo JSon del árbol de trabajadores */
    var workersDepartmentTreeJson = [
        {'data' : { 
            'title' : $.rup.i18nParse($.rup.i18n.app.tasksTree,'depAdmin')
        },
        'attr' : { 'id' : 'department', 'rel' : 'workers'},
        'state' : 'open',
        'children' : [
            {'data' : { 
                'title' : 'Abanca Rodrigez Silvia'
            },
            'attr' : {'rel' : 'worker'}
            },
            {'data' : { 
                'title' : 'Alonso Ruiz Laura'
            },
            'attr' : {'rel' : 'worker'}
            },
            {'data' : { 
                'title' : 'Gil Sandia Marta'
            },
            'attr' : {'rel' : 'worker'}
            },
            {'data' : { 
                'title' : 'Jiménez Arriurtua Francisco'
            },
            'attr' : {'rel' : 'worker'}
            },
            {'data' : { 
                'title' : 'Padilla Alcantara Sergio'
            },
            'attr' : {'rel' : 'worker'}
            },
            {'data' : { 
                'title' : 'Sánchez Rodin Pablo'
            },
            'attr' : {'rel' : 'worker'}
            }
        ]},
        {'data' : { 
            'title' : $.rup.i18nParse($.rup.i18n.app.tasksTree,'depClientes')
        },
        'attr' : { 'id' : 'department', 'rel' : 'workers'},
        'state' : 'close',
        'children' : [
            {'data' : { 
                'title' : 'Alzola Urierate Leticia'
            },
            'attr' : {'rel' : 'worker'}
            },
            {'data' : { 
                'title' : 'Aranguren Loinaz Jose'
            },
            'attr' : {'rel' : 'worker'}
            },
            {'data' : { 
                'title' : 'Bermejo Solo Ana'
            },
            'attr' : {'rel' : 'worker'}
            },
            {'data' : { 
                'title' : 'Garzon Alonso Luis'
            },
            'attr' : {'rel' : 'worker'}
            },
            {'data' : { 
                'title' : 'Jerez Templado Bisball'
            },
            'attr' : {'rel' : 'worker'}
            },
            {'data' : { 
                'title' : 'Noiz Sapuerta Gordi'
            },
            'attr' : {'rel' : 'worker'}
            },          
            {'data' : { 
                'title' : 'Ortiz Dulon Jose'
            },
            'attr' : {'rel' : 'worker'}
            },
            {'data' : { 
                'title' : 'Prudencio Fratan Armand'
            },
            'attr' : {'rel' : 'worker'}
            },
            {'data' : { 
                'title' : 'Ruiz de Santiesteban Pedro'
            },
            'attr' : {'rel' : 'worker'}
            }
        ]},
        {'data' : { 
            'title' : $.rup.i18nParse($.rup.i18n.app.tasksTree,'depReparaciones')
        },
        'attr' : { 'id' : 'department', 'rel' : 'workers'},
        'state' : 'close',
        'children' : [
            {'data' : { 
                'title' : 'Anemo Muñoz Jon'
            },
            'attr' : {'rel' : 'worker'}
            },
            {'data' : { 
                'title' : 'Almonzon Mendia Juan'
            },
            'attr' : {'rel' : 'worker'}
            },
            {'data' : { 
                'title' : 'Bornaz Satrustegui Armando'
            },
            'attr' : {'rel' : 'worker'}
            },
            {'data' : { 
                'title' : 'Muleto Delito Afelio'
            },
            'attr' : {'rel' : 'worker'}
            },
            {'data' : { 
                'title' : 'Gaztedi Jobar Maria'
            },
            'attr' : {'rel' : 'worker'}
            },
            {'data' : { 
                'title' : 'Zarate Oligarco Ramon'
            },
            'attr' : {'rel' : 'worker'}
            }
        ]},
        {'data' : { 
            'title' : $.rup.i18nParse($.rup.i18n.app.tasksTree,'depSuministros')
        },
        'attr' : { 'id' : 'department', 'rel' : 'workers'},
        'state' : 'close',
        'children' : [
            {'data' : { 
                'title' : 'Eredia Puyol Maider'
            },
            'attr' : {'rel' : 'worker'}
            },
            {'data' : { 
                'title' : 'Montero Rucio Luis'
            },
            'attr' : {'rel' : 'worker'}
            },
            {'data' : { 
                'title' : 'Perez Mendia Jone'
            },
            'attr' : {'rel' : 'worker'}
            },
            {'data' : { 
                'title' : 'Puertas y molduras Sanz'
            },
            'attr' : {'rel' : 'worker'}
            }
        ]}
    ];
    
    /* Ejemplo de arbol de tareas pendientes con reordenacion */
    $('#tasksReorderTree').rup_tree({
        'json_data' : {
            'data': tasksTreeJson
        },
        'types' : tasksTreeTypes,
        'crrm' : {
            'move' : {
                'check_move' : function (moveObject) {
                    var moveObjectParent = this._get_parent(moveObject.o);
                    if(!moveObjectParent) return false;
                    moveObjectParent = moveObjectParent == -1 ? this.get_container() : moveObjectParent;
                    if(moveObjectParent === moveObject.np) return true;
                    if(moveObjectParent[0] && moveObject.np[0] && moveObjectParent[0] === moveObject.np[0]) return true;
                    return false;
                }
            }
        },
        'dnd' : {
            'enable' : true,
            'drop_target' : false,
            'drag_target' : false
        }
    });
    
    $('#btnReorderTreeUniqueControl').bind('click', function(){
        codeCleanLoad('reorderAccordionCode');
        codeDialogSelector.rup_dialog('open');
        $('#reorderAccordionCode').rup_accordion ('activate', 0);
    });
    
    /* Ejemplo de arboles con distribucion de tareas (D&D) */
    $('#tasksTree').rup_tree({
        'json_data' : {
            'data': tasksTreeJson
        },
        'crrm' : {
            'move' : {
                'check_move' : function () {
                    return false;
                }
            }
        },
        'types' : tasksTreeTypes,
        'dnd' : {
            'enable' : true,
            'drop_target' : false,
            'drag_target' : false
        }
    });	
    
    $('#workersTree').rup_tree({
        'json_data' : {
            'data': workersTreeJson
        },
        'crrm' : {
            'move' : {
                'check_move' : function (moveObject) {
                    if(moveObject.op.parents('#tasksTree').length > 0){
                        return true;
                    } else {
                        return false;
                    }
                }
            }
        },
        'types' : workersTreeTypes,
        'dnd' : {
            'enable' : true,
            'drop_target' : false,
            'drag_target' : false
        }
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
        'json_data' : {
            'data': workersDepartmentTreeJson
        },
        'types' : workersTreeTypes,
        'crrm' : {
            'move' : {
                'check_move' : function (moveObject) {
                    var moveObjectParent = this._get_parent(moveObject.o);
                    if(!moveObjectParent) return false;
                    moveObjectParent = moveObjectParent == -1 ? this.get_container() : moveObjectParent;
                    if(moveObjectParent === moveObject.np) return true;
                    if(moveObjectParent[0] && moveObject.np[0] && moveObjectParent[0] === moveObject.np[0]) return true;
                    return false;
                }
            }
        },
        'sort' : {
            'enable' : true
        },
        'dnd' : {
            'drop_check' : function (data) {
                if((!data.r.hasClass('promotionsDismissalsActionPanel_list'))||(data.o.attr('rel') !== 'worker')) {
                    return false;
                }
                return {
                    after : false,
                    before : false,
                    inside : true
                };
            },
            'drop_finish' : function (data) {
                data.r.append(data.o.clone());
            },
            'enable' : true,
            'drag_target' : false,
            'drop_target' : '.promotionsDismissalsActionPanel_list'
        }
    });
    
    $('#btnPromotionsDismissalsUniqueControl').bind('click', function(){
        codeCleanLoad('promotionsDismissalsAccordionCode');
        codeDialogSelector.rup_dialog('open');
        $('#promotionsDismissalsAccordionCode').rup_accordion ('activate', 0);
    });
    
    /* Accordions de visualizacion de codigo */
    $('.rup_accordion').rup_accordion({
        autoHeight: false
    });


    $('.contenedor').addClass('show');
});