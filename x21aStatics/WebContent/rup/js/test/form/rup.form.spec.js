/* jslint multistr: true */
/* eslint-env jasmine, jquery */


const formHtml = '<div id="feedbackMensajes"></div>\
                <div id="tabsFormulario"></div>\
                <div id="divformHttpSubmit">\
                <form name = "exampleForm" id ="exampleForm">\
                    <input type="text" value="txt1" name="input1" id="input1"></input>\
                    <input type="text" value="txt2" name="input2" id="input2"></input>\
                    <select name="input3" id="input3">\
                        <option value="opt1">Opcion 1</input>\
                        <option value="opt2">Opcion 2</input>\
                    </select>\
                </form>\
                <form id="formHttpSubmit" action="' + testutils.DEMO + '/nora" >\
                <fieldset class="alumnoFieldset">\
                    <legend>Datos personales</legend>\
                    <div class="two-col" >\
                            <div class="col1">\
                            <label for="nombre" class="label">Nombre</label>\
                                <input type="text" name="nombre" class="formulario_linea_input" size="20" id="nombre" value="pop" />\
                            </div>\
                            <div class="col1">\
                                <label for="apellido1" class="label">Primer apellido</label>\
                                <input type="text" name="apellido1" class="formulario_linea_input" size="30" id="apellido1" />\
                            </div>\
                            <div class="col1">\
                                <label for="apellido2" class="label">Segundo apellido</label>\
                                <input type="text" name="apellido2" class="formulario_linea_input" size="30" id="apellido2" />\
                            </div>\
                    </div>\
                    <div class="two-col" >\
                        <div class="col1">\
                        <label for="sexo" class="label">Sexo</label>\
                            <select name="sexo" class="formulario_linea_input" id="sexo" ></select>\
                        </div>\
                        <div class="col1">\
                            <label for="fechaNacimiento" class="label">Fecha de nacimiento</label>\
                            <input type="text" name="fechaNacimiento" class="formulario_linea_input" id="fechaNacimiento" />\
                        </div>\
                        <div class="col1">\
                            <label for="telefono" class="label">Telefono</label>\
                            <input type="text" name="telefono" class="formulario_linea_input" id="telefono" />\
                        </div>\
                    </div>\
                    <div class="two-col" >\
                        <div class="col1">\
                        <label for="dni" class="label">DNI</label>\
                            <input type="text" name="dni" class="formulario_linea_input" id="dni" />\
                        </div>\
                    </div>\
                </fieldset>\
                <fieldset class="alumnoFieldset">\
                    <legend>Datos cuenta usuario</legend>\
                    <div class="two-col">\
                        <div class="col1">\
                        <label for="usuario" class="label">Usuario</label>\
                            <input type="text" name="usuario" class="formulario_linea_input" id="usuario" />\
                        </div>\
                        <div class="col1">\
                            <div>\
                        <label for="password" class="label">Contrase&ntilde;a</label>\
                            <input type="password" name="password" class="formulario_linea_input" id="password" />\
                            </div>\
                            <div>\
                            <label for="password_confirm" class="label">Confirmar contrase&ntilde;a</label>\
                            <input type="password" name="password_confirm" class="formulario_linea_input" id="password_confirm" />\
                            </div>\
                        </div>\
                        <div class="col1">\
                        <div>\
                        <label for="email" class="label">Email</label>\
                            <input type="text" name="email" class="formulario_linea_input" id="email" />\
                        </div>\
                        <div>\
                            <label for="email_confirm" class="label">Confirmar email</label>\
                            <input type="text" name="email_confirm" class="formulario_linea_input" id="email_confirm" />\
                        </div>\
                        </div>\
                    </div>\
				</fieldset>\
                <fieldset class="alumnoFieldset">\
                    <legend>Datos domicilio</legend>\
                    <div class="two-col">\
                        <div class="col1">\
                        <label for="pais" class="label">País</label>\
                        <select path="pais.id" class="formulario_linea_input" id="pais" >\
                            </select>\
                        </div>\
                        <div class="col1">\
                        <label for="autonomia" class="label">Autonomía</label>\
                        <select path="autonomia.id" class="formulario_linea_input" id="autonomia" >\
                            </select>\
                        </div>\
                        <div class="col1">\
                        <label for="provincia" class="label">Provincia</label>\
                            <input type="text" name="provincia.id" class="formulario_linea_input" id="provincia" />\
                        </div>\
                    </div>\
                    <div class="two-col">\
                        <div class="col1">\
                        <label for="municipio" class="label">Municipio</label>\
                            <select name="municipio.id" class="formulario_linea_input" id="municipio" ></select>\
                        </div>\
                        <div class="col1">\
                        <label for="calle" class="label">Calle</label>\
                            <select name="calle.id" class="formulario_linea_input" id="calle" ></select>\
                        </div>\
                    </div>\
                </fieldset>\
                <input type="submit" value="Enviar" />\
                </form>';

function configurar() {
    $('#feedbackMensajes').rup_feedback({
        type: 'ok',
        closeLink: true,
        delay: 1000,
        fadeSpeed: 500,
        block: true
    });
    $('#sexo').rup_select({
        data: [{
            i18nCaption: 'masculino',
            id: 'M'
        },
        {
            i18nCaption: 'femenino',
            id: 'F'
        }
        ],
        i18nId: 'sexo'
    });
    let sourceJson = [{
        i18nCaption: 'ab',
        id: 'ab_value'
    },
    {
        i18nCaption: 'tc',
        id: 'tc_value'
    },
    {
        i18nCaption: 'ud',
        id: 'ud_value'
    },
    {
        i18nCaption: 'le',
        id: 'le_value'
    },
    {
        i18nCaption: 'af',
        id: 'af_value'
    },
    {
        i18nCaption: 'mg',
        id: 'mg_value'
    },
    {
        i18nCaption: 'ah',
        id: 'ah_value'
    },
    {
        i18nCaption: 'ui',
        id: 'ui_value'
    },
    {
        i18nCaption: 'uj',
        id: 'uj_value'
    },
    {
        i18nCaption: 'ak',
        id: 'ak_value'
    }
    ];
    $('#municipio').rup_select({
        data: sourceJson,
        sourceParam: {
            text: 'dsO',
            id: 'id'
        },
        minLength: 4
    });
    $('#calle').rup_select({
        data: sourceJson,
        sourceParam: {
            text: 'dsO',
            id: 'id'
        },
        minLength: 4
    });
}
/**XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
 * XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
 */
describe('Test Form', () => {
    var $form, $formAlt;

    beforeAll((done) => {
        testutils.loadCss(done);
    });

    beforeEach((done) => {
        var html = formHtml;
        $('#content').append(html);
        configurar();
        var opts = {};
        var optsAlt = {
            url: testutils.DEMO + '/nora',
            type: 'POST',
            feedback: $('#feedbackMensajes'),
            success: function (xhr) {
                $('#feedback').rup_feedback('set', $.rup_utils.printMsg(jQuery.toJSON(xhr)), 'ok');
            },
            validate: {
                rules: {
                    'nombre': {
                        required: true
                    }
                }
            }
        };
        $('#exampleForm').rup_form(opts);
        $('#formHttpSubmit').rup_form(optsAlt);
        $form = $('#exampleForm');
        $formAlt = $('#formHttpSubmit');
        //console.info($('body').html());
        
        setTimeout(() => {
            done();
        }, 100);
    });
    afterEach(() => {
        $('#content').html('');
        $('#content').nextAll().remove();
    });
    describe('Creación > ', () => {
        describe('Form por defecto > ', () => {
            it('Debe tener la clase de rup_form', () => {
                expect($form).toHaveClass('rup_form');
            });
        });
        describe('Form alternativo > ', () => {
            it('Debe tener clases por defecto:', () => {
                expect($formAlt).toHaveClass('rup_form rup_validate');
            });
        });
    });
    describe('Integracion con validate y feedback > ', () => {
        beforeEach(() => {
            $('#nombre').val('');
            $('input[type="submit"]').trigger('click');
        });
        it('Debe mostrarse el feedback con el contenido:', () => {
            expect($('#feedbackMensajes_content').text().toUpperCase())
                .toBe('SE HAN PRODUCIDO LOS SIGUIENTES ERRORES:NOMBRE:CAMPO OBLIGATORIO.');
        });
    });
    describe('Métodos públicos >', () => {
        describe('Métodos de envío de formulario >', () => {
            describe('Método ajaxSubmit >', () => {
                var res;
                beforeEach((done) => {
                    res = undefined;
                    $formAlt.rup_form('ajaxSubmit', {
                        url: testutils.DEMO + '/nora',
                        type: 'POST',
                        success: () => {},
                        error: () => {},
                        complete: (data) => {
                            // console.info(data !== undefined && data !== null ? JSON.stringify(data) : 'SIN RESPUESTA');
                            res = data;
                            done();
                        }
                    });
                });
                it('La llamada debe tener éxito:', (done) => {
                    expect(res.status).toBe(200);
                    done();
                });
            });
            describe('Método ajaxFormSubmit >', () => {
                var res;
                beforeEach((done) => {
                    res = undefined;
                    $formAlt.rup_form('ajaxFormSubmit', {
                        url: testutils.DEMO + '/nora',
                        type: 'POST',
                        success: () => {},
                        error: () => {},
                        complete: (data) => {
                            // console.info(data !== undefined && data !== null ? JSON.stringify(data) : 'SIN RESPUESTA');
                            res = data;
                            done();
                        }
                    });
                });
                it('La llamada debe tener éxito:', (done) => {
                    expect(res.status).toBe(200);
                    done();
                });
            });
        });
        describe('Método formSerialize >', () => {
            describe('Form por defecto > ', () => {
                it('Debe devolver un string con los datos', () => {
                    let out = 'input1=txt1&input2=txt2&input3=opt1';
                    expect($form.rup_form('formSerialize')).toBe(out);
                });
            });
            describe('Form alternativo > ', () => {
                it('Debe devolver un string con los datos', () => {
                    let out = 'nombre=pop&apellido1=&apellido2=&sexo=M&fechaNacimiento=' + 
                    '&telefono=&dni=&usuario=&password=&password_confirm=&email=&email_confirm=' + 
                    '&provincia.id=&municipio.id=ab_value&calle.id=ab_value';
                    expect($formAlt.rup_form('formSerialize')).toBe(out);
                });
            });

        });
        describe('Método fieldSerialize >', () => {
            describe('Form por defecto > ', () => {
                it('Debe devolver un string con los datos de los fields', () => {
                    let out = 'input1=txt1&input2=txt2';
                    expect($('input', $form).rup_form('fieldSerialize')).toBe(out);
                });
            });
            describe('Form alternativo > ', () => {
                it('Debe devolver un string con los datos de los fields:', () => {
					let out = 'nombre=pop&apellido1=&apellido2=&sexo=M&fechaNacimiento=&telefono=' + 
					'&dni=&usuario=&password=&password_confirm=&email=&email_confirm=' + 
					'&provincia.id=&municipio.id=ab_value&calle.id=ab_value';
                    expect($('input, select', $formAlt).rup_form('fieldSerialize')).toBe(out);
                });
            });
        });
        describe('Método fieldValue', () => {
            describe('Form por defecto > ', () => {
                it('Debe devolver los valores en un array', () => {
                    let out = 'txt1,txt2';
                    expect($('input', $form).rup_form('fieldValue').join()).toBe(out);
                });
            });
            describe('Form alternativo > ', () => {
                it('Debe devolver los valores en un array', () => {
					let out = [
						'pop', '', '', 'M', '', '', '', '', '', '', '', '', '', 'ab_value', 'ab_value'
					];
                    expect($('input, select', $formAlt).rup_form('fieldValue')).toEqual(out);
                });
            });
        });
        describe('Método formToJson', () => {
            describe('Form por defecto > ', () => {
                it('Debe devolver los datos en un string en formato Json', () => {
                    let out = {
                        input1: 'txt1',
                        input2: 'txt2',
                        input3: 'opt1'
                    };
                    expect($form.rup_form('formToJson')).toEqual(out);
                });
            });
            describe('Form alternativo > ', () => {
                it('Debe devolver los datos rellenados en formato JSON:', () => {
                    let out = {
                        'nombre': 'pop',
                        'sexo': 'M',
						'municipio': {
							'id': 'ab_value'
						},
						'calle': {
							'id': 'ab_value'
						}
                    };
                    expect($formAlt.rup_form('formToJson')).toEqual(out);
                });
            });
        });
        describe('Método clearForm > ', () => {
            describe('Form por defecto > ', () => {
                beforeEach(() => {
                    $form.rup_form('clearForm');
                });
                it('Debe haber limpiado todos los campos:', () => {
                    expect($('#input1').val()).toBe('');
                    expect($('#input2').val()).toBe('');
                    expect($('#input3').val()).toBe(null);
                });
            });
            describe('Form alternativo > ', () => {
                beforeEach(() => {
                    $('#nombre').val('NOMBRE');
                    $('#apellido1').val('APELLIDO1');
                    $('#apellido2').val('APELLIDO2');
                    $formAlt.rup_form('clearForm');
                });
                it('Debe haber limpiado todos los campos:', () => {
                    expect($('#nombre').val()).toBe('');
                    expect($('#apellido1').val()).toBe('');
                    expect($('#apellido2').val()).toBe('');
                });
            });
        });
        describe('Método resetForm > ', () => {
            describe('Form por defecto > ', () => {
                beforeEach(() => {
                    $form.rup_form('clearForm');
                    $form.rup_form('resetForm');
                });
                it('Debe tener los valores originales:', () => {
                    expect($('#input1').val()).toBe('txt1');
                    expect($('#input2').val()).toBe('txt2');
                    expect($('#input3').val()).toBe('opt1');
                });
            });
            describe('Form alternativo > ', () => {
                beforeEach(() => {
                    $('#nombre').val('NOMBRE');
                    $('#apellido1').val('APELLIDO1');
                    $('#apellido2').val('APELLIDO2');
                    $formAlt.rup_form('resetForm');
                });
                it('Debe quedar en su estado original > ', () => {
                    expect($('#nombre').val()).toBe('pop');
                    expect($('#apellido1').val()).toBe('');
                    expect($('#apellido2').val()).toBe('');
                });
            });
        });
        describe('Método clearFields > ', () => {
            describe('Form por defecto > ', () => {
                beforeEach(() => {
                    $('input', $form).rup_form('clearFields');
                });
                it('Deben quedar en blanco unicamente los indicados por el selector:', () => {
                    expect($('#input1').val()).toBe('');
                    expect($('#input2').val()).toBe('');
                    expect($('#input3').val()).toBe('opt1');
                });
            });
            describe('Form alternativo > ', () => {
                beforeEach(() => {
                    $('#nombre').val('NOMBRE');
                    $('#apellido1').val('APELLIDO1');
                    $('#apellido2').val('APELLIDO2');
                    $('input#apellido2', $formAlt).rup_form('clearFields');
                });
                it('Deben quedar en blanco unicamente los indicados por el selector:', () => {
                    expect($('#nombre').val()).toBe('NOMBRE');
                    expect($('#apellido1').val()).toBe('APELLIDO1');
                    expect($('#apellido2').val()).toBe('');
                });
            });
        });
    });
});