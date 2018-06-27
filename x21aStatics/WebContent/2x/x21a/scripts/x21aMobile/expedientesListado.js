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

//var myApp = angular.module('x21aMobile',['ui.rup','mgcrea.ngStrap']);

x21aApp.controller("AppMenuController", ['$scope', function($scope){
	
	$scope.titulo = "Listado expedientes";
	
}])

.controller("AppBreadCrumbController", ['$scope', function($scope){
	$scope.listBreadCrumbs = [{text:"Inicio", href:"/x21aMobileWar/", active:false},
	                          {text:"Expedientes", href:"#", active:false},
	                          {text:"Listado", href:"#", active:true}];
}])

.controller('ExpedientesListadoCtrl', function ($scope, $http, $modal) {
	
	$scope.modalHandler = null;
	
	$scope.listaExpedientes = [];
	
	$scope.filterQuery="";
	
	$scope.doSearch = function(){
		var httpPromise = $http({
			url:'../expediente',
			method: 'get',
			cache:false
		});
		
		httpPromise.success(function(data, status) {
            $scope.listaExpedientes = data;
        });
		
	};
	
	$scope.openDetail = function(){
		
		$scope.expediente = this.expediente;
		
		$scope.modalHandler = $modal({
			scope: $scope, 
			template: STATICS+'/x21a/scripts/x21aMobile/tpl/detalleExpediente.html', 
			placement: 'center',
			backdrop:false,
			show: true
		});
		
	};
	
	$scope.cambiarFase = function(nuevaFase){
		
		var expediente = this.expediente;
		
		var httpPromise = $http({
			url:'../expediente/'+expediente.id+"/fase?_"+new Date().getTime(),
			method: 'put',
			data:{idFase:nuevaFase},
			cache:false
		});
		
		httpPromise.success(function(data, status) {
			$scope.doSearch();
			$scope.modalHandler.hide();
        });
	};
	
	$scope.doSearch();
	
});

