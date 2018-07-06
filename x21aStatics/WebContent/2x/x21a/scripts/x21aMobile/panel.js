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
	
	$scope.titulo = "Panel";
	
}])

.controller("AppBreadCrumbController", ['$scope', function($scope){
	$scope.listBreadCrumbs = [{text:"Inicio", href:"/x21aMobileWar/", active:false},
	                          {text:"Panel", href:"#", active:true}];
}])


.controller('PanelCtrl', function ($scope, $aside) {

		$scope.openRightPanel =  function(){
			var myAside = $aside({title: 'My Title', content: 'My Content', show: true, template: "/x21aStatics/rup-mobile/js/rup/tpl/aside.tpl.html"});
		};
});

