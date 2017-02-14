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
jQuery(document).ready(function () {

	
	var $view = {
			ui:{
		        dashboard: $("#dashboard"),
		        templateInline: $("#templateInline"),
		        dashboardList: $("#dashboardList"),
		        dashboardListUl: $("#dashboardList ul"),
		        menuDashboardLink: $("#dashboardList ul li a"),
		        currentDashboardName: $("#currentDashboard"),
		        addWidget: $("#addWidget"),
		        newDashboard: $("#newDashboard"),
		        deleteDashboard: $("#deleteDashboard"),
		        saveDashboard: $("#saveDashboard"),
		        dashboardMenu: $(".dashboard-aside"),
		        currentDashboardLink: $("#currentDashboardLink"),
		        collapsibleAsideMenu: $(".dashboard-aside [data-toggle='collapse']"),
		        fabButton: $("#fabButton"),
		        newDashboardDialog: $("#newDashboardDialog"),
		        addWidgetDialog: $("#addWidgetDialog"),
		        newWidgetHtmlInline: $("#newWidgetHtmlInline"),
		        newWidgetTemplateInline: $("#newWidgetTemplateInline"),
		        newWidgetTemplateXhr: $("#newWidgetTemplateXhr")
		      }
	};
	
	
	function fncOnDomRefresh(){
	    
		
        $view.ui.addWidget.on("click", fncAddWidget);
        $view.ui.newDashboard.on("click", fncNewDashboard);
        $view.ui.deleteDashboard.on("click", fncDeleteDashboard);
        $view.ui.saveDashboard.on("click", fncSaveDashboard);
        $view.ui.currentDashboardLink.on("click", fncCollapseAside);
        $view.ui.newWidgetHtmlInline.on("click", fncNewWidgetHtmlInline);
        $view.ui.newWidgetTemplateInline.on("click", fncNewWidgetTemplateInline);
        $view.ui.newWidgetTemplateXhr.on("click", fncNewWidgetTemplateXhr);
		
		
		
	    // List dashboards
	    DashboardService.getAll().then(function(data){

	      $.each(data, function(i, dashboard){
	          $view.ui.dashboardListUl.append("<li ><a data-dashboard-id='"+dashboard.getId()+"'>"+dashboard.getName()+"</a></li>");
	          $("#dashboardList ul li a").on("click", fncLoadSelectedDashboard);
	      })

	    });

	    $view.ui.addWidgetDialog.rup_dialog({
	      type: $.rup.dialog.DIV,
	  		autoOpen: false,
	  		modal: true,
	      appendTo:".dashboard-container",
	      width:"70%",
	      buttons: [{
					text: "Abandonar",
					click: function (event) {
						$view.ui.addWidgetDialog.rup_dialog("close");
	          event.preventDefault();
					},
					btnType: $.rup.dialog.LINK
				}
			]
	  });


	    $view.ui.newDashboardDialog.rup_dialog({
	      type: $.rup.dialog.DIV,
	  		autoOpen: false,
	  		modal: true,
	      appendTo:".dashboard-container",
	      width:"70%",
	      buttons: [{
					text: "Aceptar",
					click: function () {
						debugger;

					}
				},
				{
					text: "Abandonar",
					click: function (event) {
						$view.ui.newDashboardDialog.rup_dialog("close");
	          event.preventDefault();
					},
					btnType: $.rup.dialog.LINK
				}
			]
	    });



	    $view.ui.fabButton.rup_button();

	    $view.ui.dashboard.rup_dashboard({
	          verticalMargin: 10,

	          configure:{
	            requiredByUser: false
	          },
	          buttons:{
	            btnConfig:false
	          }
	      });

	    fncLoadDashboard("1");

	  }

	  function fncLoadDashboard(dashboardId){
	    
	    return DashboardService.get(dashboardId).then(function(dashboard){
	      $view.currentDashboard = dashboard;
	      $view.ui.currentDashboardName.text(dashboard.getName());
	      $view.ui.dashboard.rup_dashboard("removeAll");
	      $view.ui.dashboard.rup_dashboard("loadFromArray", JSON.parse(dashboard.getSerializedArray()));
	     
	    });
	  }

	  function fncLoadSelectedDashboard(event){
	    var dashboardId = event.currentTarget.getAttribute('data-dashboard-id');


	    fncLoadDashboard(dashboardId);
	  }


	  function fncAddWidget(){
	    
	    $view.ui.addWidgetDialog.rup_dialog("open");
	  }

	  function fncNewWidgetHtmlInline(){
	    

	    $view.ui.dashboard.rup_dashboard("addWidget",{
	      type:"template",
	      minWidth: 4,
	      minHeight: 4,
	      widgetOptions:{
	        configure:{
	          requiredByUser:false,
	          template:"#templateConfig"
	        },
	        template: "<p>"+
	          "Este ssses un ejemplo de un widget HTML."+
	        "</p>"+
	        "<p>"+
	          "El contenido se especifica directamente en la propiedad template de la definición del widget. "+
	        "</p>"
	      }
	    });
	  }

	  function fncNewWidgetTemplateInline(){
	    
	    $view.ui.dashboard.rup_dashboard("addWidget",{
	      type:"template",
	      minWidth: 4,
	      minHeight: 4,
	      widgetOptions:{
	        buttons:{
	          btnConfig:false
	        },
	        template: "#templateInline"
	      }
	    });
	  }

	  function fncNewWidgetTemplateXhr(){
	    
	    $view.ui.dashboard.rup_dashboard("addWidget",{
	      type:"xhr",
	      minWidth: 4,
	      minHeight: 4,
	      widgetOptions:{
	        url: "/demoResponsive/app/dashboard/html/widgetDiv.html",
	        configure:{
	          template:"#templateConfig"
	        }

	      }
	    });
	  }

	  function fncNewDashboard(){
	    
	    $view.ui.newDashboardDialog.rup_dialog("open");
	  }

	  function fncConfirmDelete (){
	    DashboardService.delete($view.currentDashboard);
	  }

	  function fncConfirmNewDashboard (name){

	    var newDashboard = new Dashboard({
	      name
	    });

	    DashboardService.ass($view.currentDashboard);
	  }

	  function fncDeleteDashboard(){
	    

	    $.rup_messages("msgConfirm", {
	      message: "¿Está seguro que desea eliminar el escritorio actual?",
	      title: "Confirmación de borrado",
	      OKFunction : fncConfirmDelete
	    });
	  }

	  function fncSaveDashboard(){
	    
	    var serializedDashboardJson = $view.ui.dashboard.rup_dashboard("serializeAsArray");
	    var serializedDashboard = $view.ui.dashboard.rup_dashboard("serialize");
	    $view.currentDashboard.setSerializedArray(serializedDashboard);
	    $view.currentDashboard.setDataJson(serializedDashboardJson);

	    DashboardService.save($view.currentDashboard);
	  }

	  function fncCollapseAside(){
	    

	    if ($view.ui.dashboardMenu.hasClass("collapsed")){
	      $view.ui.dashboardMenu.removeClass("collapsed");
	    }else{
	      $.each($(".dashboard-aside [data-toggle='collapse']").map(function(i,elem){ return $($(elem).attr("href"));}), function(i, elem){
	        $(elem).collapse("hide");
	      });
	      $view.ui.dashboardMenu.addClass("collapsed");

	    }
	  }
	  
	  fncOnDomRefresh();
	
});