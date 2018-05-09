/*!
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

//var myApp = angular.module('x21aMobile',['ui.rup','ui.bootstrap']);

x21aApp.controller("AppMenuController", ['$scope', function($scope){
	
	$scope.titulo = "Mensajes";
	
}])

.controller("AppBreadCrumbController", ['$scope', function($scope){
	$scope.listBreadCrumbs = [{text:"Inicio", href:"/x21aMobileWar/", active:false},
	                          {text:"Mensajes", href:"#", active:true}];
}])


.controller('MessageCtrl', function ($scope, $rupMessage, $log) {

	  $scope.titulo = "sadasd";
	
	  $scope.openError = function(size) {
		  $rupMessage.showError({title: "Error grave", content: "Se ha producido un error a la hora de intentar guardar el registro. Consulte con el administrador."});
	  };
	  
	  $scope.openOk = function(size) {
		  $rupMessage.showSuccess({title: "Correcto", content: "Todo ha ido OK."});
	  };
	  
	  $scope.openWarning = function(size) {
		  $rupMessage.showWarning({title: "Alerta", content: "Esto es un mensaje de alerta."});
	  };
	  
	  $scope.openInfo = function(size) {
		  $rupMessage.showInfo({title: "Info", content: "Esto es un mensaje informativo."});
	  };
	  
	  $scope.openConfirm = function(size) {
		  $rupMessage.showConfirm({title: "Confirmación", content:"¿Está seguro que desea cancelar?"});
	  };
	  

});

