/*function addDecimalsFormat(nStr){
	    nStr += '';
	    var x = nStr.split('.');
	    var x1 = x[0];
	    var x2 = x.length > 1 ? ',' + x[1] : '';
	    var rgx = /(\d+)(\d{3})/;
	    while (rgx.test(x1)) {
	        x1 = x1.replace(rgx, '$1' + '.' + '$2');
	    }
	    return x1 + x2;
	    
}
function addDecimalsFormatEnInput(nStr){
    nStr += '';
    var x = nStr.split(',');
    var x1 = x[0];
    var x2 = x.length > 1 ? ',' + x[1] : '';
    var rgx = /(\d+)(\d{3})/;
    while (rgx.test(x1)) {
        x1 = x1.replace(rgx, '$1' + '.' + '$2');
    }
    return x1 + x2;
}
*/

function addDecimalsFormat(nStr, num, puntuacion){
	return addDecimalsFormat0(nStr, num, puntuacion, false);
}
function addDecimalsFormat0(nStr, num, puntuacion, sustituyeBlanco){
	if (sustituyeBlanco && isEmpty(nStr)){
		nStr = 0;
	}
	if ((nStr!==null)&&(nStr!=='')){
	    nStr += '';
	    if (num !== undefined){
	    	nStr = parseFloat(nStr).toFixed(num);
	    }
	    var x = nStr.split('.');
	    var x1 = x[0];
	    var x2 = x.length > 1 ? ',' + x[1] : '';
	    if (puntuacion===true){
	    	var rgx = /(\d+)(\d{3})/;
	        while (rgx.test(x1)) {
	            x1 = x1.replace(rgx, '$1' + '.' + '$2');
	        }
	    }
	    return x1 + x2;
	}else return '';
	
	
	
}
function addDecimalsFormatEnInput(nStr, num){
	if ((nStr!==null)&&(nStr!=='')){
		nStr += '';
		if (num !== undefined){
			nStr = nStr.replace(',','.');
	    	nStr = parseFloat(nStr).toFixed(num);
	    	nStr = nStr.replace('.',',');
		}
	    return nStr;
	}else return '';
}

function removeDecimalsFormat(value){
	var globalizedValue = value.replace(/\./g, "");
	return globalizedValue.replace(",", ".");
}

function parseDecimal(value){
	var val = removeDecimalsFormat(value);
	return parseFloat(val);
}

function clearValidation(formElement){
	var validator = $(formElement).validate();
	validator.resetForm();
	validator.reset();
	$(formElement).rup_form("resetForm");
}

function setFocusFirstInput(formElement){
	$(formElement+' :input:first').focus();
}

function eliminarMensajes(){
	$('rup-message-confirm').remove();
	$("[id^='rup_msgDIV_']").remove();
}

(function(jQuery) {
	

	// Propiedades de configuracion predeterminadas para cada una de las posibles parametrizaciones de los errores.
	var presetSettings = {
		// Configruacion del componente por defecto
		defaultPresetSettings: {
			showErrors: function showErrors(errors) {
				var self = this,
				    invalid,
				    errorText,
				    feedback,
				    field,
				    errorKey,
				    fieldError,
				    fieldErrorMsg,
				    error,
				    label;

				// Se comprueba si el parametro que contiene los errores está vacío. En este caso se
				if (self.currentElements.length === 1) {
					if ($.isEmptyObject(errors)) {
						delete self.invalid[self.currentElements.attr('name')];
					}
				}

				/*
         * Mostrar mensaje de error de validaciones en el feedback
         */
				feedback = self.settings.feedback;
				if (self.settings.showErrorsInFeedback && feedback !== undefined && feedback !== null) {
					errorText = $('<ul>').addClass('rup-maint_feedbackUL').prepend(self.settings.feedbackErrorConfig.errorMsg);

					if (jQuery.isEmptyObject(self.invalid)) {
						feedback.rup_feedback('close');
					} else {

						if (self.settings.showFieldErrorsInFeedback) {
							$.each(!jQuery.isEmptyObject(self.submitted) ? self.submitted : self.invalid, function (key, value) {

								if (self.invalid[key] !== undefined) {
									field = self.settings.feedbackErrorConfig.getField(self, self.currentForm, key);
									errorKey = self.settings.feedbackErrorConfig.getFieldName(self, self.currentForm, field);
									fieldError = self.settings.feedbackErrorConfig.getFieldErrorLabel(self, self.currentForm, field, errorKey);

									fieldErrorMsg = self.settings.feedbackErrorConfig.getFieldErrorMsg(self, self.currentForm, field, value);
									fieldError.append(fieldErrorMsg);
									errorText.append(fieldError);
								}
							});
						}
						feedback.rup_feedback('option', self.settings.feedbackOptions);
						feedback.rup_feedback('set', errorText, 'error');
					}
				}

				/*
         * Mostrar detalle de errores en el feedback
         */
				if (self.settings.showFieldErrorAsDefault) {
					for (var i = 0; self.errorList[i]; i++) {

						error = self.errorList[i];

						if (error.element !== undefined) {

							label = self.errorsFor(error.element);
							if (label.length) {
								label.remove();
							}
						}
					}
				}

				/* En caso de utilizar el tratamiento por defecto del componente de jquery.validate,
         * no es posible indicarle varios mensajes de error para un campo.
         * Por ello deberemos concatenar estos mensajes de error en caso de que se de el caso.
         */
				for (var i = 0; i < self.errorList.length; i++) {
					//					if (self.settings.showFieldErrorAsDefault){
					//						self.errorList[i].message="";
					//					}else
					if (self.errorList[i].element === undefined) {
						alert('El campo validado no existe en el formulario');
					}
					if ($.isArray(self.errorList[i].message)) {
						// En caso de que el mensaje de error sea un array de mensajes, se debera de recorrer y concatenar
						var newMessage = '';
						for (var j = 0; j < self.errorList[i].message.length; j++) {
							newMessage += self.errorList[i].message[j];
							if (j !== self.errorList[i].message.length - 1) {
								newMessage += ', ';
							}
						}
						self.errorList[i].message = newMessage;
					}
				}
				// Se eliminan los etilos de error previos
				//$('.' + self.settings.errorClass + ':not(.rup-maint_validateIcon)', self.currentForm).removeClass(self.settings.errorClass);
				// Se invoca al metodo por defecto del plugin subyacente
				self.defaultShowErrors();
			},
			showErrorsInFeedback: function showErrorsInFeedback(errors) {},
			errorPlacement: function errorPlacement(label, element) {

				if (element.attr('ruptype') === 'combo') {
					var comboElem = $('#' + element.attr('id') + '-button');
					if (comboElem) {
						label.insertAfter(comboElem);
					}
				} else {
					label.insertAfter(element);
				}
			}
		},
		// Configuracion de las propiedades a aplicar en caso de que se deban mostrar los errores mediante la visualizacion por defecto.
		showFieldErrorAsDefault: {
			errorElement: 'img',
			errorPlacement: function errorPlacement(error, element) {
				var errorElem = error.attr('src', this.errorImage).addClass('rup-maint_validateIcon').html('').rup_tooltip({
					'applyToPortal': true
				});

				if (element.attr('ruptype') === 'combo') {
					var comboElem = $('#' + element.attr('id') + '-button');
					if (comboElem) {
						errorElem.insertAfter(comboElem);
					}
				} else {
					errorElem.insertAfter(element);
				}
			}
		}
	};

	$.fn.rup_validate('extend', {
		_init: function _init(args) {

			var self = this,
			    settingsAdapter,
			    defaultPresetSettings,
			    settings;

			settingsAdapter = $.extend(true, {}, {
				adapter: $.fn.rup_validate.defaults.adapter
			}, {
				adapter: presetSettings.defaultPresetSettings.adapter
			}, {
				adapter: args[0].adapter
			});
			self[0]._ADAPTER = $.rup.adapter[settingsAdapter.adapter];

			defaultPresetSettings = $.extend(true, {}, presetSettings.defaultPresetSettings, {
				showFieldErrorAsDefault: {
					highlight: self[0]._ADAPTER.highlight,
					unhighlight: self[0]._ADAPTER.unhighlight,
					errorElement: self[0]._ADAPTER.errorElement,
					errorPlacement: self[0]._ADAPTER.errorPlacement
				}
			});
			// settings = $.extend(true,{},$.fn.rup_validate.defaults, presetSettings.defaultPresetSettings, args[0]);

			settings = $.extend(true, {}, $.fn.rup_validate.defaults, defaultPresetSettings, args[0]);

			// settings = $.extend(true, {}, defaultSettings, args[0]);


			// Anadimos al formulario el class rup_validate para identificarlo como componente formulario.
			self.addClass('rup_validate');
			// Anadimos el ruptype validate
			self.attr('ruptype', 'validate');

			/*
       * Configuracion del componente de validaciones.
       */

			// En caso de que se deban mostrar los errores mediante la visualizacion predeterminada se configuran los presets correspondientes.
			if (settings.showFieldErrorAsDefault) {
				settings = $.extend(true, settings, defaultPresetSettings.showFieldErrorAsDefault);
			}
			settings = $.extend(true, {}, settings, args[0]);
			// Se realiza la invocacion al plugin jquery.validate
			self.validate(settings);

			if (settings.showFieldErrorAsDefault) {
				self.validate().showLabel = self[0]._ADAPTER.showLabel;
			}

			// Si se ha configurado el componente para que no se realicen validaciones al vuelo de los campos, se eliminan los eventos correspondientes.
			if (!settings.liveCheckingErrors) {
				self.unbind('click').unbind('focusin').unbind('focusout').unbind('keyup');
			}

			// Se captura el evento invalid-form del plugin subyacente para generar un evento propio
			self.on('invalid-form.rupValidate_formValidationError', function (event) {
				self.off('invalid-form.rupValidate_formValidationError');
				self.triggerHandler('rupValidate_formValidationError', [this]);
			});

			// Se almacena la configuracion del componente en el objeto dom para poder recuperarla en sucesivas invocaciones a los metodos del componente.
			self.data('settings', settings);
		}
	});
	
	
	
	$.fn.rup_validate.defaults.feedbackErrorConfig.getFieldErrorLabel = function(self, form, field, errorLabel){
		if (errorLabel) {
			return $("<li>").append("<b>" + errorLabel.replace(/:$/, "") + ":</b>");
		} else {
			return $("<li>").append("<b>" + errorLabel + ":</b>");
		}
	};		

	jQuery.validator.addMethod("customDecimal", function(value, element, params) {
		if (!$(element).importeValido()) {
			return false;
		}
		var globalizedValue = $(element).getImporte();
	    return (this.optional(element) || (globalizedValue >= params[2] && globalizedValue <= params[3]));
	},  $.rup.i18nParse($.rup.i18n.base,"rup_validate.messages.range"));

	$(document).on("change", ".decimal", function(value, element, params) {
		$(this).formatImporte();
	});
	
	$(document).on("input", ".numeric", function() {
	    this.value = this.value.replace(/\D/g,'');	    
	});
	
	
	$(document).on("input", ".decimal", function() {
		this.value = this.value.replace(/[^\d|^\,|^\.]/g, '');
	});
	
	$(document).on("input", ".decimalPor", function() {
		this.value = this.value.replace(/[^\d|^\,]/g, '');
	});
	
	$(document).on("change", ".decimalPor", function(value, element, params) {
		$(this).formatPorDecimal();
	});
	
	$(document).on("input", ".campohora10", function() {
		this.value = this.value.replace(/[^\d|^\:]/g, '');
	});
	
	$(document).on("input", ".campohora", function() {
		this.value = this.value.replace(/[^\d|^\:]/g, '');
	});
	
	$(document).on("change", ".campohora", function() {
		var valor = this.value;
		var longitud = valor.length;
		if( valor.indexOf(":")===-1 && longitud > 0 && longitud < 3) {
			valor += ":00";
		}
		this.value = valor;
	});
	
	//Validacion para formato de hora
	$.validator.addMethod("hora", function( value, element ) {
		return this.optional( element ) || /^([01]\d|2[0-3]|[0-9])(:[0-5]\d){1,2}$/.test( value );
	},  $.rup.i18n.app.validaciones.horaIncorrecta );
	
	
	jQuery.validator.addMethod("comboBaja", function(value, element, params) {
		return this.optional(element) || value !== "-1";		
	},$.rup.i18n.app.validaciones.comboBaja);
	
	//Validacion para numeros decimales
	jQuery.validator.addMethod("validateDecimal", function(valor) {
		var regex  =/^\d{1,3}(\,\d{0,2}){0,1}$/;
		    if (regex.test(valor)) {
		        return true;
		    } else {
		        return false;
		    }
		
		},$.rup.i18n.app.validaciones.numeroDecimal);
	
	// Validacion para rango de fechas 
	jQuery.validator.addMethod("fechaHastaMayor", function(value, element, params) {
		var fechaHasta = value.split("/");
		var fechaDesde = $("[name='"+params+"']").val().split("/");
		if(jQuery.rup_utils.capitalizedLang()=="Es"){
			var fechaHastaFormat=new Date(fechaHasta[2],fechaHasta[1]-1,fechaHasta[0]);
			var fechaDesdeFormat=new Date(fechaDesde[2],fechaDesde[1]-1,fechaDesde[0]);
		}else{
			var fechaHastaFormat=new Date(fechaHasta[0],fechaHasta[1]-1,fechaHasta[2]);
			var fechaDesdeFormat=new Date(fechaDesde[0],fechaDesde[1]-1,fechaDesde[2]);
		}
		if(fechaHasta == ""  || fechaDesde == "" || fechaHastaFormat >= fechaDesdeFormat){
			return true;
		}else{
			return false;
		}
	},$.rup.i18n.app.validaciones.esMayorQueDesde);
	
	// Validacion para rango de fechas 
	jQuery.validator.addMethod("fechaHastaMayorPorId", function(value, element, params) {
		var fechaHasta = value.split("/");
		var fechaDesde = $("[id='"+params+"']").val().split("/");
		if(jQuery.rup_utils.capitalizedLang()=="Es"){
			var fechaHastaFormat=new Date(fechaHasta[2],fechaHasta[1]-1,fechaHasta[0]);
			var fechaDesdeFormat=new Date(fechaDesde[2],fechaDesde[1]-1,fechaDesde[0]);
		}else{
			var fechaHastaFormat=new Date(fechaHasta[0],fechaHasta[1]-1,fechaHasta[2]);
			var fechaDesdeFormat=new Date(fechaDesde[0],fechaDesde[1]-1,fechaDesde[2]);
		}
		if(fechaHasta == ""  || fechaDesde == "" || fechaHastaFormat >= fechaDesdeFormat){
			return true;
		}else{
			return false;
		}
	},$.rup.i18n.app.validaciones.esMayorQueDesde);
	
	
	// Validacion para rango de fechas 
	jQuery.validator.addMethod("horaFechaHastaMayor", function(value, element, params) {
		var esHoraMayor = true;
		var fechaIni = $("[name='"+params[0]+"']").val();
		var fechaFin = $("[name='"+params[1]+"']").val();
		
		if (fechaIni === fechaFin){
			fechaFin = fechaFin.split("/");
			fechaIni = fechaIni.split("/");
			
			var horaIni = $("[name='"+params[2]+"']").val().split(":");
			var horaFin = value.split(":");
			if(jQuery.rup_utils.capitalizedLang()=="Es"){
				var fechaFinFormat=new Date(fechaFin[2],fechaFin[1]-1,fechaFin[0],horaFin[0],horaFin[1]);
				var fechaIniFormat=new Date(fechaIni[2],fechaIni[1]-1,fechaIni[0],horaIni[0],horaIni[1]);
			}else{
				var fechaFinFormat=new Date(fechaFin[0],fechaFin[1]-1,fechaFin[2],horaFin[0],horaFin[1]);
				var fechaIniFormat=new Date(fechaIni[0],fechaIni[1]-1,fechaIni[2],horaIni[0],horaIni[1]);
			}
			if(fechaFin == ""  || fechaIni == "" || fechaFinFormat.getTime() > fechaIniFormat.getTime()){
				esHoraMayor = true;
			}else{
				esHoraMayor = false;
			}
		}
		return esHoraMayor;
	},$.rup.i18n.app.validaciones.esHoraMayorQueIni);
	
	// Validacion para rango de fechas 
	jQuery.validator.addMethod("horaFechaHastaMayorFitros", function(value, element, params) {
		var esHoraMayor = true;
		var fechaIni = $("[name='"+params[0]+"']").val();
		var fechaFin = $("[name='"+params[1]+"']").val();
		var horaIni = $("[name='"+params[2]+"']").val().split(":");
		var horaFin = value.split(":");
		
		if (fechaIni === fechaFin && horaIni[0] != "" && horaFin[0] != ""){
			fechaFin = fechaFin.split("/");
			fechaIni = fechaIni.split("/");
			
			if(jQuery.rup_utils.capitalizedLang()=="Es"){
				var fechaFinFormat=new Date(fechaFin[2],fechaFin[1]-1,fechaFin[0],horaFin[0],horaFin[1]);
				var fechaIniFormat=new Date(fechaIni[2],fechaIni[1]-1,fechaIni[0],horaIni[0],horaIni[1]);
			}else{
				var fechaFinFormat=new Date(fechaFin[0],fechaFin[1]-1,fechaFin[2],horaFin[0],horaFin[1]);
				var fechaIniFormat=new Date(fechaIni[0],fechaIni[1]-1,fechaIni[2],horaIni[0],horaIni[1]);
			}
			if(fechaFin == ""  || fechaIni == "" || fechaFinFormat.getTime() > fechaIniFormat.getTime()){
				esHoraMayor = true;
			}else{
				esHoraMayor = false;
			}
		}
		return esHoraMayor;
	},$.rup.i18n.app.validaciones.esHoraMayorQueIni);
	
	// Validacion para rango de hora mayor con parametros tipo id
	jQuery.validator.addMethod("horaFechaHastaMayorPorId", function(value, element, params) {
		var esHoraMayor = true;
		var fechaIni = $("[id='"+params[0]+"']").val();
		var fechaFin = $("[id='"+params[1]+"']").val();
		
		if (fechaIni === fechaFin){
			fechaFin = fechaFin.split("/");
			fechaIni = fechaIni.split("/");
			
			var horaIni = $("[id='"+params[2]+"']").val().split(":");
			var horaFin = value.split(":");
			
			if($.rup_utils.capitalizedLang()=="Es"){
				var fechaFinFormat=new Date(fechaFin[2],fechaFin[1]-1,fechaFin[0],horaFin[0],horaFin[1]);
				var fechaIniFormat=new Date(fechaIni[2],fechaIni[1]-1,fechaIni[0],horaIni[0],horaIni[1]);
			}else{
				var fechaFinFormat=new Date(fechaFin[0],fechaFin[1]-1,fechaFin[2],horaFin[0],horaFin[1]);
				var fechaIniFormat=new Date(fechaIni[0],fechaIni[1]-1,fechaIni[2],horaIni[0],horaIni[1]);
			}
			
			if(fechaFin == ""  || fechaIni == "" || fechaFinFormat.getTime() > fechaIniFormat.getTime()){
				esHoraMayor = true;
			}else{
				esHoraMayor = false;
			}
		}
		return esHoraMayor;
	},$.rup.i18n.app.validaciones.esHoraMayorQueIni);
	
	
	
	
	// Validacion para fecha mayor que la actual  
	jQuery.validator.addMethod("fechaMayorNow", function(value, element, params) {
		var fechaHasta = value.split("/");
		if(jQuery.rup_utils.capitalizedLang()=="Es"){
			var fechaHastaFormat=new Date(fechaHasta[2],(fechaHasta[1]-1),+fechaHasta[0]);
		}else{
			var fechaHastaFormat=new Date(fechaHasta[0],(fechaHasta[1]-1),fechaHasta[2]);
		}
		var fechaDesdeFormat=new Date(new Date().getFullYear(),new Date().getMonth(),new Date().getDate());
		if(fechaHasta == "" || fechaHastaFormat >= fechaDesdeFormat){
			return true;
		}else{
			return false;
		}
	},$.rup.i18n.app.validaciones.fechaMenorActual);
	
	// Validacion  de fecha y hora mayor que la actual 
	jQuery.validator.addMethod("horaFechaMayorNow", function(value, element, params) {
		var esHoraMayor = true;
		var fechaFin = $("[name='"+params[0]+"']").val();
		var dia = new Date().getDate()+"";
		if (dia.length ==1){ dia="0"+dia; }
		var mes = (new Date().getMonth()+1)+"";
		if (mes.length ==1){ mes="0"+mes; }
		if(jQuery.rup_utils.capitalizedLang()=="Es"){
			var fechaDesde= dia+"/"+mes+"/"+new Date().getFullYear();
		}else{
			var fechaDesde= new Date().getFullYear()+"/"+mes+"/"+dia;
		}
		if (fechaFin === fechaDesde){
			fechaFin = fechaFin.split("/");
			
			var horaFin = value.split(":");
			if(jQuery.rup_utils.capitalizedLang()=="Es"){
				var fechaFinFormat=new Date(fechaFin[2],fechaFin[1]-1,fechaFin[0],horaFin[0],horaFin[1]);
			}else{
				var fechaFinFormat=new Date(fechaFin[0],fechaFin[1]-1,fechaFin[2],horaFin[0],horaFin[1]);
			}
			if(fechaFin == ""  || fechaFinFormat.getTime() > new Date().getTime()){
				esHoraMayor = true;
			}else{
				esHoraMayor = false;
			}
		}
		
		return esHoraMayor;
	},$.rup.i18n.app.validaciones.horaMenorActual);
	
	
	
	
	// Validacion para rango enteros   
	jQuery.validator.addMethod("mayorQueCampo", function(value, element, params) {
		
		var valorMin = $("[name='"+params[0]+"']").val();
		
		if(value == ""  || valorMin == "" || value >= valorMin){
			
			return true;
		}else{
			if (params.length >1){
				params.splice(1,2);
			}	
			//No puedo marcar el otro campo como error porque luego lo vuelve a poner valid... 
			//$("[name='"+params[0]+"']").removeClass("valid");
			//$("[name='"+params[0]+"']").addClass("error");
			params.push( $("[name='jornCompHorasDesde']").prev("label").text());
			params.push(valorMin);
			return false;
		}
	},$.rup.i18n.app.validaciones.mayorQueCampo);
	
	jQuery.validator.addMethod("mayorQue", function(value, element, params) {
		var valorMin = $("[name='"+params+"']").val();
		
		if(value == ""  || valorMin == "" || parseInt(value) > parseInt(valorMin)){
			return true;
		}else{
			return false;
		}
	},$.rup.i18n.app.validaciones.mayorQue);
	
	
	
	jQuery.validator.addMethod("nombreFichero", function(value, element, params) {
		return value.length <= params;
	},$.rup.i18n.app.validaciones.nombreFichero);
	
	jQuery.validator.addMethod(
			  "validateEmail"
			, function(valor) {
				var regex  =/^[_A-Z0-9-]+(.[_A-Z0-9-]+)*@[A-Z0-9-]+(.[A-Z0-9-]+)*(.[A-Z]{2,4})$/i;
				if(valor!=""){
					return regex.test(valor);
				} else {
					return true;
				}
			  }
			, $.rup.i18n.app.validaciones.validateEmail);
	
	jQuery.validator.addMethod(
			"validateMovilMail"
			, function(valor) {
				return jQuery('#fldSolicitante_email').val() != "" || jQuery('#fldSolicitante_tefnoFijo').val() != "" || jQuery('#fldSolicitante_tefnoMovil').val() != "";
			  }
			, $.rup.i18n.app.validaciones.mailMovil);
	
	jQuery.validator.addMethod(
			  "validateDNI"
			, function(dni) {
				var preDni = $("#fldSolicitante_predni").val().toUpperCase();
				var sufDni = $("#fldSolicitante_sufdni").val().toUpperCase();
				var tipoDni = $("#fldSolicitante_tipiden").val();
				// DNI
				if(tipoDni == 1){
					if (dni.length > 8  || dni == '' || sufDni == '' || parseInt(dni) == "NaN"){
						return false;
					}else{
						var expresion_regular_dni = /^\d{8}$/;
						if(expresion_regular_dni.test(dni) == true){
							var numero = dni % 23;
							var letra='TRWAGMYFPDXBNJZSQVHLCKET'.substring(numero,numero+1);
							return letra == sufDni;
						} else {
							return false;
						}
					}
				// PERMISO RESIDENCIA
				}else if(tipoDni == 2){
					if(dni == '' || dni.length > 7 || preDni.length == '' || sufDni.length == '' ||parseInt(dni) == "NaN"){ 
						return false;
					}else{
					      var numero;
					      if (preDni == "X" || preDni == "x") {
							preDni = "0";
						  } else if (preDni == "Y" || preDni == "y") {
							preDni = "1";
						  } else if (preDni == "Z" || preDni == "z") {
							preDni = "2";
					      }
					      var dniVal = preDni + dni;
					      numero = dniVal % 23;
					      var letra='TRWAGMYFPDXBNJZSQVHLCKET'.substring(numero,numero+1);         
					      return letra == sufDni.toUpperCase(); 
				}
				// OTROS
				}else if(tipoDni == 3){
					if(dni == '' || dni.length > 8 || parseInt(dni) == "NaN") return false;
					return true;
				}
			  }
			, $.rup.i18n.app.validaciones.dniError);
	
	//Validacion para formato de hora
	$.validator.addMethod("horasPrevistas", function( value, element ) {
		var horasPrevistasValidas = true;
		if (value !== ''){
			if(value.indexOf(":") == -1
					|| value.split(':').length > 2){
				horasPrevistasValidas = false;
			}else{
				var hora = value.split(':');
				if(hora[0] !== "" && hora[1] !== "" && hora[0].length >= 1 && hora[1].length == 2){
					var hh = parseInt(hora[0],10);
					var mm = parseInt(hora[1],10);
					if (hh < 0 || hh > 9999999) horasPrevistasValidas = false;
					if (mm < 0 || mm > 59) horasPrevistasValidas = false;	
				}else{
					horasPrevistasValidas = false;
				}
			}
		}
		return horasPrevistasValidas;
	},  $.rup.i18n.app.validaciones.horaIncorrecta );
	
	$.format = $.validator.format;
})(jQuery);