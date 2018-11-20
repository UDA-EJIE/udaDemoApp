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
x21aApp.controller("AppMenuController",["$scope",function(a){a.titulo="Mensajes"}]).controller("AppBreadCrumbController",["$scope",function(a){a.listBreadCrumbs=[{text:"Inicio",href:"/x21aMobileWar/",active:false},{text:"Mensajes",href:"#",active:true}]}]).controller("MessageCtrl",function(a,c,b){a.titulo="sadasd";a.openError=function(d){c.showError({title:"Error grave",content:"Se ha producido un error a la hora de intentar guardar el registro. Consulte con el administrador."})};a.openOk=function(d){c.showSuccess({title:"Correcto",content:"Todo ha ido OK."})};a.openWarning=function(d){c.showWarning({title:"Alerta",content:"Esto es un mensaje de alerta."})};a.openInfo=function(d){c.showInfo({title:"Info",content:"Esto es un mensaje informativo."})};a.openConfirm=function(d){c.showConfirm({title:"Confirmación",content:"¿Está seguro que desea cancelar?"})}});