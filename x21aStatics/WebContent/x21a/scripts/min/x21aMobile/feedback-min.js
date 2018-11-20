/*
 * Copyright 2015 E.J.I.E., S.A.
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
x21aApp.controller("AppMenuController",["$scope",function(a){a.titulo="Feedback"}]).controller("AppBreadCrumbController",["$scope",function(a){a.listBreadCrumbs=[{text:"Inicio",href:"/x21aMobileWar/",active:false},{text:"Feedback",href:"#",active:true}]}]).controller("FeedbackCtrl",function(a){a.newAlert={type:"info"};a.alerts=[{type:"danger",msg:"Se ha producido un error en la aplicación. Contacte con el administrador.",hide:false},{type:"success",msg:"El registro ha sido modificado correctamente.",hide:false}];a.addAlert=function(){var b=angular.copy(a.newAlert);if(!angular.isNumber(parseInt(b.hide))||b.hide===""){b.hide=false}a.alerts.push(b)};a.closeAlert=function(b){a.alerts.splice(b,1)}});