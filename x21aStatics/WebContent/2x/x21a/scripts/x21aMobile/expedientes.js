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
	                          {text:"Listado por fases", href:"#", active:true}];
}])


.controller('ExpedientesCtrl', function ($scope, $http, $modal) {
	
	$scope.listaExpedientes = [];
	$scope.listaExpedientesDerivacion = [];
	$scope.listaExpedientesCierre = [];
	$scope.listaExpedientesGestion = [];
	
	$scope.numExpedientesDerivacion = 0;
	$scope.numExpedientesCierre = 0;
	$scope.numExpedientesGestion = 0;
	
	$scope.modalHandler = null;
	
	$scope.mostrarCambioFase = true;
	
	$scope.doSearch = function(){
		var httpPromise = $http({
			url:'../expediente',
			method: 'get',
			cache:false
		});
		
		httpPromise.success(function(data, status) {
			
			$scope.listaExpedientesDerivacion = [];
			$scope.listaExpedientesCierre = [];
			$scope.listaExpedientesGestion = [];
			
			for (var i=0;i<data.length;i++){
				if (data[i].idFase === 501){
					$scope.listaExpedientesGestion.push(data[i]);
				}else if (data[i].idFase === 504){
					$scope.listaExpedientesCierre.push(data[i]);
				}else if (data[i].idFase === 506){
					$scope.listaExpedientesDerivacion.push(data[i]);
				}
			}
			
			$scope.numExpedientesDerivacion = $scope.listaExpedientesDerivacion.length;
			$scope.numExpedientesCierre = $scope.listaExpedientesCierre.length;
			$scope.numExpedientesGestion = $scope.listaExpedientesGestion.length;
			
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

