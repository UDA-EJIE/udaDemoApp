/* jslint multistr: true */
/* eslint-env jasmine, jquery */



describe('Test Validate >  ', () => {
    var $validate, $feedBack, $validateEvent;
    var event_fail = false,
        event_success = false;

    beforeAll((done) => {
        testutils.loadCss(done);
    });

    beforeEach((done) => {
        var html = '<form id="exampleValidate">\
                            <label for="campoUno">Campo 1</label><input type="text" name="campoUno" id="campoUno"></input > \
                            <label for="campoDos">Campo 2</label><input type="text" name="campoDos" id="campoDos"></input>\
                        </form>\
                        <div id="feedback"></div>';
        $('#content').append(html);
        var htmlEvent = '<form id="exampleValidateEvent">\
                            <label for="campoUnoEvent">Campo 1</label><input type="text" name="campoUnoEvent" id="campoUnoEvent"></input >\
                        </form>';
        $('#content').append(htmlEvent);

        var optsFeedback = {
            type: 'ok',
            closeLink: true,
            block: false
        };
        $feedBack = $('#feedback').rup_feedback(optsFeedback);
        var optsValidate = {
            feedback: $feedBack,
            liveCheckingErrors: false,
            showFieldErrorAsDefault: true,
            showErrorsInFeedback: true,
            showFieldErrorsInFeedback: true,
            rules: {
                'campoUno': {
                    required: true
                },
                'campoDos': {
                    required: true
                }
            }
        };
        
        global.initRupI18nPromise.then(() => {
        $validate = $('#exampleValidate').rup_validate(optsValidate);

	        $validateEvent = $('#exampleValidateEvent').rup_validate({
	            rules: {
	                'campoUnoEvent': {
	                    required: true
	                }
	            },
	            onSubmitHandler: () => {
	                event_success = true;
	                $('#exampleValidateEvent').triggerHandler('submitSuccessfull');
	            },
	            invalidHandler: () => {
	                event_fail = true;
	            }
	        });
	        done();
        });
    });
    afterEach(() => {
        $('#content').html('');
        $('#content').nextAll().html('');
        event_success = false;
        event_fail = false;
    });
    describe('Creación > ', () => {
        it('El formulario tiene que tener la clase rup_validate', () => {
            expect($validate.hasClass('rup_validate')).toBeTruthy();
        });
        it('El feedback debe estar oculto', () => {
            expect($feedBack.is(':visible')).toBeFalsy();
        });
    });
    describe('La validación funciona, no debe pasar > ', () => {
        beforeEach(() => {
            $('#campoUno').val('foo');
            $validate.valid();
        });
        it('Debe mostrarse el feedback', () => {
            expect($feedBack.is(':visible')).toBeTruthy();
        });
        it('Cada campo del form debe tener la clase error', () => {
            expect($('#campoUno').hasClass('error')).toBeFalsy();
            expect($('#campoDos').hasClass('error')).toBeTruthy();
        });
    });
    describe('La validación funciona, debe pasar > ', () => {
        beforeEach(() => {
            $('#campoUno').val('foo');
            $('#campoDos').val('foo');
            $validate.valid();
        });
        it('No debe mostrarse el feedback', () => {
            expect($feedBack.is(':visible')).toBeFalsy();
        });
        it('Cada campo del form debe tener la clase error', () => {
            expect($('#campoUno').hasClass('error')).toBeFalsy();
            expect($('#campoDos').hasClass('error')).toBeFalsy();
        });
    });
    describe('Eventos > ', () => {
        describe('invalidHandler', () => {
            beforeEach((done) => {
                $('#exampleValidateEvent').on('rupValidate_formValidationError', () => {
                    done();
                });
                $('#campoUnoEvent').val('');
                $validateEvent.valid();
            });
            it('Debe lanzarse el evento', () => {
                expect(event_fail).toBeTruthy();
            });
        });
        describe('onSubmitHandler', () => {
            beforeEach((done) => {
                // $('#exampleValidateEvent').on('submitSuccessfull', () => {
                //     done();
                // });
                $('#campoUnoEvent').val('foo');
                $validateEvent.submit();
                setTimeout(() => {
                    done();
                }, 500);
            });
            it('Debe lanzarse el evento', () => {
                expect(event_success).toBeTruthy();
            });
        });
    });
    describe('Métodos públicos > ', () => {
        describe('Método resetForm > ', () => {
            beforeEach(() => {
                $validate.valid();
                $validate.rup_validate('resetForm');
            });
            it('Debe mantenerse el feedback', () => {
                expect($feedBack.is(':visible')).toBeTruthy();
            });
            it('Los campos del form no deben tener la clase error', () => {
                expect($('#campoUno').hasClass('error')).toBeFalsy();
                expect($('#campoDos').hasClass('error')).toBeFalsy();
            });
        });
        describe('Método resetElements > ', () => {
            beforeEach(() => {
                $validate.valid();
                $validate.rup_validate('resetElements');
            });
            it('Debe mantenerse el feedback', () => {
                expect($feedBack.is(':visible')).toBeTruthy();
            });
            it('Los campos del form no deben tener la clase error', () => {
                expect($('#campoUno').hasClass('error')).toBeFalsy();
                expect($('#campoDos').hasClass('error')).toBeFalsy();
            });
        });
        describe('Método destroy > ', () => {
            beforeEach(() => {
                $validate.rup_validate('destroy');
                $validate.valid();
            });
            it('No debe existir', () => {
                expect($('#campoUno').hasClass('error')).toBeFalsy();
                expect($('#campoDos').hasClass('error')).toBeFalsy();
            });
        });
    });
});