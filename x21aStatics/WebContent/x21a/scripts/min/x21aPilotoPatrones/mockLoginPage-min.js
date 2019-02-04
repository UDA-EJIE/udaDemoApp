/*
 * Copyright 2012 E.J.I.E., S.A.
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
jQuery(document).ready(function(){var c=$.rup_utils.getUrlVar("mockUrl");var a=$.parseJSON(($.rup_utils.getUrlVar("userNames")));$("#userCombo").rup_combo({source:a,width:250,selected:"udaAnonymousUser",select:function(e,d){$("#selectedUserName").html(d.value)}});$("#userCombo").rup_combo("setRupValue","udaAnonymousUser");if($(".rup-feedback").size()===0){$("#content").prepend($("<div id='logFeedback'/>").addClass("logFeedback").rup_feedback({type:"ok",closeLink:true,delay:2500,fadeSpeed:500,block:true})).width($("#content").width())}else{$("#content").css("margin-top","10em")}var b=$.rup_utils.readCookie("udaMockUserName");if(b===null){$("#selectedUser").append($("<span/>").addClass("selectedUserName").attr("id","selectedUserName").html($("#userCombo").rup_combo("getRupValue")))}else{$("#selectedUser").append($("<span/>").addClass("selectedUserName").attr("id","selectedUserName").html(b));$("#userCombo").rup_combo("setRupValue",b)}$("#loginButtonObject").bind("click",function(){$.rup_utils.setCookie("udaMockUserName",$("#userCombo").rup_combo("getRupValue"),{duration:0,path:"/"});if(c!==undefined&&c!==null&&c!==""){$(location).attr("href",c)}else{$(".rup-feedback").rup_feedback("set",$.rup.i18nParse($.rup.i18n.app,"loginUserMock.logginOk1")+$("#userCombo").rup_combo("getRupValue")+$.rup.i18nParse($.rup.i18n.app,"loginUserMock.logginOk2")).feedback_principal.rup_feedback("show")}})});