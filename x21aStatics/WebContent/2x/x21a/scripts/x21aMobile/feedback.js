/*!
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


x21aApp.controller("AppMenuController", ['$scope', function($scope){
	
	$scope.titulo = "Feedback";
	
}])

.controller("AppBreadCrumbController", ['$scope', function($scope){
	$scope.listBreadCrumbs = [{text:"Inicio", href:"/x21aMobileWar/", active:false},
	                          {text:"Feedback", href:"#", active:true}];
}])

.controller('FeedbackCtrl', function ($scope) {
	
	$scope.newAlert={type:'info'};
		
	  $scope.alerts = [
	    { type: 'danger', msg: 'Se ha producido un error en la aplicación. Contacte con el administrador.', hide:false},
	    { type: 'success', msg: 'El registro ha sido modificado correctamente.', hide:false }
	  ];

	  $scope.addAlert = function() {
		var tmpAlert = angular.copy($scope.newAlert);
		if (!angular.isNumber(parseInt(tmpAlert.hide)) || tmpAlert.hide === ''){
			tmpAlert.hide=false;
		}
	     $scope.alerts.push(tmpAlert);
	  };

	  $scope.closeAlert = function(index) {
	    $scope.alerts.splice(index, 1);
	  };
	});