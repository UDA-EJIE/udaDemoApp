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
jQuery(document).ready(function () {

    $('#contextMenu').rup_contextMenu({
        selector: '#contextMenu',
        callback: function (key) {
            alert('clicked: ' + key);
        },
        items: {
            'edit': {
                name: 'Clickable',
                icon: 'edit',
                disabled: false
            },
            'cut': {
                name: 'Disabled',
                icon: 'cut',
                disabled: true
            }
        }
    });

    $('.contextMenu-left').rup_contextMenu({
        selector: '.contextMenu-left',
        trigger: 'left',
        callback: function (key) {
            alert('clicked: ' + key);
        },
        items: {
            'edit': {
                name: 'Edit',
                icon: 'edit',
                accesskey: 'e'
            },
            'cut': {
                name: 'Cut',
                icon: 'cut',
                accesskey: 'c'
            },
            // first unused character is taken (here: o)
            'copy': {
                name: 'Copy',
                icon: 'copy',
                accesskey: 'c o p y'
            },
            // words are truncated to their first letter (here: p)
            'paste': {
                name: 'Paste',
                icon: 'paste',
                accesskey: 'cool paste'
            },
            'delete': {
                name: 'Delete',
                icon: 'delete'
            },
            'sep1': '---------',
            'quit': {
                name: 'Quit',
                icon: 'quit'
            }
        }
    });

    $('#contextMenu-hover').rup_contextMenu({
        selector: '#contextMenu-hover',
        trigger: 'hover',
        callback: function (key) {
            alert('clicked: ' + key);
        },
        items: {
            'edit': {
                'name': 'Edit',
                'icon': 'edit'
            },
            'cut': {
                'name': 'Cut',
                'icon': 'cut'
            },
            'sep1': '---------',
            'quit': {
                'name': 'Quit',
                'icon': 'quit'
            },
            'sep2': '---------',
            'fold1': {
                'name': 'Sub group',
                'items': {
                    'foo bar': {
                        'name': 'Foo bar'
                    },
                    'fold2': {
                        'name': 'Sub group 2',
                        'items': {
                            'alpha': {
                                'name': 'alpha'
                            },
                            'bravo': {
                                'name': 'bravo'
                            },
                            'charlie': {
                                'name': 'charlie'
                            }
                        }
                    },
                    'delta': {
                        'name': 'delta'
                    }
                }
            },
            'fold1a': {
                'name': 'Other group',
                'items': {
                    'echo': {
                        'name': 'echo'
                    },
                    'foxtrot': {
                        'name': 'foxtrot'
                    },
                    'golf': {
                        'name': 'golf'
                    }
                }
            }
        }
    });

    $('.contextMenu-other').rup_contextMenu({
        selector: '.contextMenu-other',
        trigger: 'none',
        build: function () {
            return {
                callback: function (key) {
                    alert('clicked: ' + key);
                },
                items: {
                    'edit': {
                        name: 'Edit',
                        icon: 'edit'
                    },
                    'cut': {
                        name: 'Cut',
                        icon: 'cut'
                    },
                    'copy': {
                        name: 'Copy',
                        icon: 'copy'
                    },
                    'paste': {
                        name: 'Paste',
                        icon: 'paste'
                    },
                    'delete': {
                        name: 'Delete',
                        icon: 'delete'
                    },
                    'sep1': '---------',
                    'quit': {
                        name: 'Quit',
                        icon: 'quit'
                    }
                }
            };
        }
    });

    $('#activate-menu').on('click', function () {
        $('.contextMenu-other').rup_contextMenu('show');
    });


    $('.contenedor').addClass('show');
});