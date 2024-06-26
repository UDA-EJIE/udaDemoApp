/* jslint multistr: true */
/* eslint-env jasmine, jquery */



describe('Test Toolbar > ', () => {
    var $toolbar;

    beforeAll((done) => {
        testutils.loadCss(done);
    });

    beforeEach((done) => {
        var handler = () => {
        	window.alert('XXX');
        };
        let html = '<div id="exampleToolbar"></div>';
        let options = {
            buttons: [{
                id: 'searchBtn',
                css: 'mdi mdi-magnify',
                i18nCaption: 'buscar',
                click: handler
            },
            {
                id: 'mbutton1',
                i18nCaption: 'botones',
                buttons: [{
                    id: 'searchMBtn',
                    i18nCaption: 'buscar',
                    click: handler
                },
                {
                    id: 'editMBtn',
                    i18nCaption: 'editar',
                    click: handler
                },
                {
                    id: 'copyMBtn',
                    i18nCaption: 'copiar',
                    click: handler
                }
                ]
            }
            ]
        };
        $('#content').append(html);
        $('#exampleToolbar').rup_toolbar(options);
        $toolbar = $('#exampleToolbar');
        setTimeout(done, 200);
    });
    afterEach(() => {
        $('#content').html('');
        $('#content').nextAll().remove();
    });

    describe('Creación > ', () => {
        it('Debe tener la clase adecuada', () => {
            expect($toolbar.hasClass('rup-toolbar ui-widget-header ui-widget ui-widget-content'))
                .toBeTruthy();
        });
    });
    describe('Métodos públicos > ', () => {
        describe('Método addButton > ', () => {
            let buttonObj;
            beforeEach((done) => {
                buttonObj = {
                    click: () => {
                    	window.alert('ASD');
                    },
                    id: 'addedButton',
                    text: 'Added Button'
                };
                $toolbar.rup_toolbar('addButton', buttonObj);
                setTimeout(done, 200);
            });
            it('Debe existir el boton', () => {
                expect($('[id="exampleToolbar##addedButton"]').length).toBe(1);
            });
        });
        describe('Método addMButton > ', () => {
            let mButton;
            beforeEach((done) => {
                mButton = {
                    idParent: 'exampleToolbar',
                    id: 'addedMButton',
                    i18nCaption: 'addedMButton',
                    buttons: [{
                        id: 'new',
                        i18nCaption: 'nuevo',
                        css: 'nuevo',
                        click: () => {}
                    },
                    {
                        id: 'editar',
                        i18nCaption: 'editar',
                        css: 'editar',
                        click: () => {}
                    },
                    {
                        id: 'cancelar',
                        i18nCaption: 'cancelar',
                        css: 'cancelar',
                        click: () => {}
                    }
                    ]
                };
                var idMBtn = mButton.idParent + '##' + mButton.id;
                $toolbar.rup_toolbar('addMButton', mButton);
				setTimeout(function() {
					$toolbar.rup_toolbar('addButtonsToMButton', mButton.buttons, $('[id="' + idMBtn + '-mbutton-group"]'));
					setTimeout(done, 200);
				}, 200);
            });

            it('Debe existir el mButton', () => {
                expect($('[id="exampleToolbar##addedMButton"]').length).toBe(1);
            });
            it('Deben existir los botones hijos del MButton', () => {
                expect($('[id="exampleToolbar##addedMButton##new"]').length).toBe(1);
                expect($('[id="exampleToolbar##addedMButton##editar"]').length).toBe(1);
                expect($('[id="exampleToolbar##addedMButton##cancelar"]').length).toBe(1);
            });
        });
        describe('Método addButtonsToMButton >', () => {
            beforeEach((done) => {
                let button = {
                    id: 'addedButton',
                    i18nCaption: 'boton',
                    click: () => {}
                };

                $toolbar.rup_toolbar('addButtonsToMButton', [button], $('[id="exampleToolbar##mbutton1-mbutton-group"]'));
                setTimeout(done, 200);
            });

            it('Debe existir el botón añadido', () => {
                expect($('[id="exampleToolbar##mbutton1##addedButton"]').length).toBe(1);
            });
        });
        describe('Método disableButton > ', () => {
            beforeEach((done) => {
                $toolbar.rup_toolbar('disableButton', 'searchBtn');
                setTimeout(done, 200);
            });
            it('Debe tener las clases que lo deshabilitan', () => {
                expect($('[id="exampleToolbar##searchBtn"]').is(':disabled'))
                    .toBeTruthy();
            });
        });
        describe('Método enableButton > ', () => {
            beforeEach(() => {
                $toolbar.rup_toolbar('disableButton', 'searchBtn');
                $toolbar.rup_toolbar('enableButton', 'searchBtn');
            });
            it('No debe tener las clases que lo deshabilitan', () => {
                expect($('[id="exampleToolbar##searchBtn"]').is(':enabled'))
                    .toBeTruthy();
            });
        });
        describe('Método pressButton > ', () => {
            beforeEach((done) => {
                $toolbar.rup_toolbar('pressButton', 'searchBtn', 'pressed-button');
                setTimeout(done, 200);
            });
            it('Debe tener la clase de presionado ', () => {
                expect($('[id="exampleToolbar##searchBtn"]').hasClass('pressed-button')).toBeTruthy();
            });
        });
        describe('Método unpressButton > ', () => {
            beforeEach(() => {
                $toolbar.rup_toolbar('pressButton', 'searchBtn', 'pressed-button');
                $toolbar.rup_toolbar('unpressButton', 'searchBtn', 'pressed-button');
            });
            it('No debe tener la clase de presionado ', () => {
                expect($('[id="exampleToolbar##searchBtn"]').hasClass('pressed-button')).toBeFalsy();
            });
        });
        describe('Método tooglePressButton > ', () => {
            beforeEach(() => {
                $toolbar.rup_toolbar('pressButton', 'searchBtn', 'pressed-button');
                $toolbar.rup_toolbar('tooglePressButton', 'searchBtn', 'pressed-button');
            });
            it('No debe tener la clase de presionado ', () => {
                expect($('[id="exampleToolbar##searchBtn"]').hasClass('pressed-button')).toBeFalsy();
            });
        });
        describe('Método refreshButton > ', () => {
            beforeEach(() => {
                $('[id = "exampleToolbar##searchBtn"]').attr('disabled', 'disabled');
                $toolbar.rup_toolbar('refreshButton', 'searchBtn');
            });

            it('Debe actualizar el botón:', () => {
                expect($('[id = "exampleToolbar##searchBtn"]').is(':disabled'))
                    .toBeTruthy();
            });
        });
    });
});