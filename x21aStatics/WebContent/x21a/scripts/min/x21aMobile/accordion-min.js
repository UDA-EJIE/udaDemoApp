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
x21aApp.controller("AppMenuController",["$scope",function(a){a.titulo="Accordion"}]).controller("AppBreadCrumbController",["$scope",function(a){a.listBreadCrumbs=[{text:"Inicio",href:"/x21aMobileWar/",active:false},{text:"Accordion",href:"#",active:true}]}]).controller("AccordionDemoController",function(a){a.oneAtATime=true;a.groups=[{title:"Dynamic Group Header - 1",content:"Dynamic Group Body - 1"},{title:"Dynamic Group Header - 2",content:"Dynamic Group Body - 2"}];a.items=["Item 1","Item 2","Item 3"];a.addItem=function(){var b=a.items.length+1;a.items.push("Item "+b)};a.status={isFirstOpen:true,isFirstDisabled:false}});