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
x21aApp.controller("AppMenuController",["$scope",function(a){a.titulo="Listado expedientes"}]).controller("AppBreadCrumbController",["$scope",function(a){a.listBreadCrumbs=[{text:"Inicio",href:"/x21aMobileWar/",active:false},{text:"Expedientes",href:"#",active:false},{text:"Listado",href:"#",active:true}]}]).controller("ExpedientesListadoCtrl",function(a,c,b){a.modalHandler=null;a.listaExpedientes=[];a.filterQuery="";a.doSearch=function(){var d=c({url:"../expediente",method:"get",cache:false});d.success(function(f,e){a.listaExpedientes=f})};a.openDetail=function(){a.expediente=this.expediente;a.modalHandler=b({scope:a,template:STATICS+"/x21a/scripts/x21aMobile/tpl/detalleExpediente.html",placement:"center",backdrop:false,show:true})};a.cambiarFase=function(f){var e=this.expediente;var d=c({url:"../expediente/"+e.id+"/fase?_"+new Date().getTime(),method:"put",data:{idFase:f},cache:false});d.success(function(h,g){a.doSearch();a.modalHandler.hide()})};a.doSearch()});