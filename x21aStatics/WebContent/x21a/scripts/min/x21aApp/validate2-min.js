/*
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
jQuery(document).ready(function(){$("#autocomplete").rup_autocomplete({source:"autocomplete/remote",sourceParam:{label:"desc"+$.rup_utils.capitalizedLang(),value:"code"},minLength:4});$("#comboAbueloRemoto").rup_combo({source:"comboEnlazadoSimple/remoteEnlazadoProvincia",sourceParam:{label:"desc"+$.rup_utils.capitalizedLang(),value:"code",style:"css"},blank:""});$("#comboPadreRemoto").rup_combo({parent:["comboAbueloRemoto"],source:"comboEnlazadoSimple/remoteEnlazadoComarca",sourceParam:{label:"desc"+$.rup_utils.capitalizedLang(),value:"code",style:"css"},blank:""});$("#comboHijoRemoto").rup_combo({parent:["comboPadreRemoto"],source:"comboEnlazadoSimple/remoteEnlazadoLocalidad",sourceParam:{label:"desc"+$.rup_utils.capitalizedLang(),value:"code",style:"css"},blank:""});$("#multicombo").rup_combo({source:[{i18nCaption:"lunes",value:"1"},{i18nCaption:"martes",value:"2"},{i18nCaption:"miercoles",value:"3"},{i18nCaption:"jueves",value:"4"},{i18nCaption:"viernes",value:"5"},{i18nCaption:"sabado",value:"6"},{i18nCaption:"domingo",value:"7"}],width:400,ordered:false,loadFromSelect:true,multiselect:true});$("#fecha").rup_date({labelMaskId:"fecha-mask",showButtonPanel:true,showOtherMonths:true,noWeekend:true});$("#fechaHora").rup_date({datetimepicker:true,labelMaskId:"fecha-mask",showButtonPanel:true,showOtherMonths:true,noWeekend:true,showSecond:false,dateFormat:"dd/mm/yyyy",timeFormat:"hh:mm"});$("#tabsValidacion").rup_tabs({tabs:[{i18nCaption:"validacionesCliente",tabs:[{i18nCaption:"reglasValidacion",layer:"#divValidaciones"},{i18nCaption:"validacionComponentes",layer:"#divValidaciones2"}]},{i18nCaption:"validacionesServidor",tabs:[{i18nCaption:"formularioServidor",layer:"#divValidaciones3"},{i18nCaption:"formularioServidor2",layer:"#divValidaciones4"}]}]});$("#diaObligatorio").rup_combo({source:[{i18nCaption:"lunes",value:"1"},{i18nCaption:"martes",value:"2"},{i18nCaption:"miercoles",value:"3"},{i18nCaption:"jueves",value:"4"},{i18nCaption:"viernes",value:"5"},{i18nCaption:"sabado",value:"6"},{i18nCaption:"domingo",value:"7"}],width:100,ordered:false});jQuery("#feedbackErroresValidaciones").rup_feedback({type:"ok",closeLink:true,block:false});var b={};function a(){var h={feedback:$("#feedbackErroresValidaciones"),liveCheckingErrors:$("#liveCheckingErrors").is(":checked"),showFieldErrorAsDefault:$("#checkShowFieldErrorsTip").is(":checked"),showErrorsInFeedback:$("#checkFeedbackError").is(":checked"),showFieldErrorsInFeedback:$("#checkShowErrorsFeedback").is(":checked")};return h}function g(){var h={rules:{campoObligatorio1:{required:true},campoObligatorio2:{required:"#esObligatorio:checked"},campoObligatorio3:{required:function(){return $("#diaObligatorio").val()>5}},longitudMinima:{minlength:8},longitudMaxima:{maxlength:20},longitudIntervalo:{rangelength:[8,20]},valorMinimo:{min:10},valorMaximo:{max:30},valorIntervalo:{range:[10,30]},validacionEmail:{email:true},validacionUrl:{url:true},validacionFecha:{date:true},validacionFechaISO:{dateISO:true},numeroDecimal:{number:true},soloDigitos:{digits:true},tarjetaCredito:{creditcard:true},extension:{accept:"xls|csv"},clave_confirmar:{equalTo:"#clave"},dni:{dni:true},palabrasMaximo:{maxWords:6},palabrasMinimo:{minWords:2},palabrasIntervalo:{rangeWords:[2,6]},letrasYPuntuacion:{letterswithbasicpunc:true},alfanumerico:{alphanumeric:true},soloLetras:{lettersonly:true},sinEspacios:{nowhitespace:true},entero:{integer:true},patron:{pattern:"\\d"}}};return $.extend(a(),h)}function c(){var h={rules:{autocomplete:{required:true},provincia:{required:true},comarca:{required:true},localidad:{required:true},gender:{required:true},agree:{required:true},"notificacion[]":{required:true,minlength:2},multicombo:{required:true},lenguaje:{required:true},lenguajeMulti:{required:true,rangelength:[2,3]}}};return $.extend(a(),h)}function e(){var h={feedback:$("#feedbackErroresValidaciones"),validate:a(),url:$.rup.CTX_PATH+"/patrones/validacion/servidor",success:function(j,i,l,k){$("#feedbackErroresValidaciones").rup_feedback("set","El formulario se ha enviado correctamente mediante un submit AJAX","ok")}};return h}function d(){var h={feedback:$("#feedbackErroresValidaciones"),validate:a(),url:$.rup.CTX_PATH+"/patrones/validacion/servidor2",success:function(j,i,l,k){$("#feedbackErroresValidaciones").rup_feedback("set",$.rup_utils.printMsg(j),"ok")}};return h}var f={success:function(i,h,k,j){$("#feedbackErroresValidaciones").rup_feedback("set","El formulario se ha enviado correctamente.","ok")}};$("#formValidaciones").rup_validate(g());$("#formValidaciones").ajaxForm(f);$("#formValidaciones2").rup_validate(c());$("#formValidaciones2").ajaxForm(f);$("#formServidor").rup_form(e());$("#formServidor2").rup_form(d());$("#botonConfiguracion").bind("click",function(){$("#formValidaciones").rup_validate("destroy");$("#formValidaciones2").rup_validate("destroy");$("#formServidor").rup_validate("destroy");$("#formServidor").rup_form("destroy");$("#formServidor2").rup_validate("destroy");$("#formServidor2").rup_form("destroy");$("#formValidaciones").rup_validate(g());$("#formValidaciones").ajaxForm(f);$("#formValidaciones2").rup_validate(c());$("#formValidaciones2").ajaxForm(f);$("#formServidor").rup_form(e());$("#formServidor2").rup_form(d())})});