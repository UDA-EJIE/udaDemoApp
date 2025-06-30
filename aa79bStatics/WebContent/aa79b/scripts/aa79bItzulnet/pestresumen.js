jQuery(function($){
	
	bloquearPantalla();
	
	var vista=new Object();
	vista.ui= new Object();

	vista.ui.graficoExpedientesBar = "#graficoExpedientesBar",
	vista.ui.graficoTareasBar = "#graficoTareasBar",
	vista.ui.graficoTramitesBar = "#graficoTramitesBar",

	vista.colorsExpedientesData = [];
	vista.colorsTareasData = [];
	vista.colorsTramitesData = [];

    $(vista.ui.graficoExpedientesBar).on("click", function(evt) {
      selectBar($(this), evt);
    });
    $(vista.ui.graficoTareasBar).on("click", function(evt) {
	    selectBar($(this), evt);
	  });
    $(vista.ui.graficoTramitesBar).on("click", function(evt) {
        selectBar($(this), evt);
      });
    
    //Recuperar lista de beans
    $.ajax({
	   	 type: 'GET'		   	 
	   	 ,url: '/aa79bItzulnetWar/tramitacionexpedientes/planificacion/planificacionexpediente/resumen/getChartsData'
	 	 ,dataType: 'json'
	 	 ,contentType: 'application/json' 
	     ,cache: false
	   	 ,success:function(data){
	   		fncInitialize(vista, data);
	   		fncOnDomRefresh(vista);
	   		desbloquearPantalla();
	   	 }
 	 	,error: function(){
 	 		desbloquearPantalla();
 	 	}
	 });
    
    filtroDatos = "";
    filtroTypeDatos = "";
    if($('#pestSituacionActual').length){
    	$('#pestSituacionActual').addClass("tabsEjecutarTarea");
    }
 });

function selectBar(selector, evt){
	var activePoints = selector.rup_chart('getChart').getElementsAtEvent(evt);
    if (activePoints[0]) {
       var chartData = activePoints[0]['_chart'].config.data;
       var idx = activePoints[0]['_index'];
       var activeElement = selector.rup_chart('getChart').getElementAtEvent(evt);
       
       var label = chartData.labels[idx];
       var type=activeElement[0]._datasetIndex; //recogemos si para un resultado (label) hemos hecho click sobre el dataset 'Todos' (el 0) o el de 'Mis Expedientes' (el 1);
       var value = chartData.datasets[type].data[activeElement[0]._index]; //recogemos el valor que contiene el dataset clickado
       var typeLabel = chartData.datasets[type].label;//La etiqueta del dataset clickado 'Todos' o 'Mis Expedientes'
               
       //Se rellena el filtroDatos con el valor de la fila seleccionada 
       if(selector[0].id === "graficoExpedientesBar"){
    	   if(idx !== 5){
    		   filtroDatos = "expedientes-" + (idx + 1);   
    	   }else{
    		   filtroDatos = "expedientesSin-" + (idx + 1);
    	   }
       }else if(selector[0].id === "graficoTareasBar"){
    	   filtroDatos = "tareas-" + (idx + 1);
       }else{
    	   filtroDatos = "tramites-" + (idx + 1);
       }
    	//Se rellena el filtroTypeDatos con el valor del tipo de fila seleccionada: mis expedientes o todos
       filtroTypeDatos = type;
      
      $("#tabsPlantificacion").rup_tabs("selectTab",{
			idTab: "pestSituacionActual",
			position: 1
		});
    }
}

function formatLabel(e, a) {
	var t = [],
        n = e.split(" "),
        s = "";
    return n.forEach(function(e, o) {
    	if (s.length > 0) {
            var r = s + " " + e;
            if (!(r.length > a)) return o == n.length - 1 ? void t.push(r) : void(s = r);
            t.push(s), s = ""
        }
        if (o == n.length - 1) return void t.push(e);
        e.length < a ? s = e : t.push(e)
    }), t
}


function fncInitialize(vista, data) {
	var listaExpedientes = data[0];
	var listaTareas = data[1];
	var listaTramites = data[2];
	
	vista.colorsExpedientesData = {
			labels: [formatLabel($.rup.i18nParse($.rup.i18n.app,"planifExpedientesTipos.pendientesEntrega"), 100), 
				formatLabel($.rup.i18nParse($.rup.i18n.app,"planifExpedientesTipos.hoy"), 100), 
				formatLabel($.rup.i18nParse($.rup.i18n.app,"planifExpedientesTipos.sieteDias"), 100), 
				formatLabel($.rup.i18nParse($.rup.i18n.app,"planifExpedientesTipos.noConformidad"), 100), 
				formatLabel($.rup.i18nParse($.rup.i18n.app,"planifExpedientesTipos.sinPlanificar"), 100), 
				formatLabel($.rup.i18nParse($.rup.i18n.app,"planifExpedientesTipos.sinAsignador"), 100)],
			datasets : [ {
				label: $.rup.i18nParse($.rup.i18n.app,"label.todos"),
                data: [listaExpedientes[0].total, listaExpedientes[1].total, listaExpedientes[2].total, listaExpedientes[3].total, listaExpedientes[4].total, listaExpedientes[5].total],
				backgroundColor : "rgba(255, 99, 132, 0.5)" ,
				borderColor: "rgba(255,99,132,1)",
                borderWidth: 0
			},
			{
				label: $.rup.i18nParse($.rup.i18n.app,"label.misExpedientes"),
                data: [listaExpedientes[0].misExp, listaExpedientes[1].misExp, listaExpedientes[2].misExp, listaExpedientes[3].misExp, listaExpedientes[4].misExp, listaExpedientes[5].misExp],
				backgroundColor : "rgba(54, 162, 235, 0.5)",
				borderColor: "rgba(155,99,132,1)",
                borderWidth: 0
			}]
	};
	
	vista.colorsTareasData = {
			labels: [formatLabel($.rup.i18nParse($.rup.i18n.app,"planifTareasTipos.sinAsignar"), 100), 
				formatLabel($.rup.i18nParse($.rup.i18n.app,"planifTareasTipos.asignadas"), 100), 
				formatLabel($.rup.i18nParse($.rup.i18n.app,"planifTareasTipos.finalizanHoy"), 100), 
				formatLabel($.rup.i18nParse($.rup.i18n.app,"planifTareasTipos.retrasadas"), 100)],
			datasets : [ {
				label: $.rup.i18nParse($.rup.i18n.app,"label.todos"),
                data: [listaTareas[0].total, listaTareas[1].total, listaTareas[2].total, listaTareas[3].total],
				backgroundColor : "rgba(255, 99, 132, 0.5)" ,
				borderColor: "rgba(255,99,132,1)",
                borderWidth: 0
			},
			{
				label: $.rup.i18nParse($.rup.i18n.app,"label.misExpedientes"),
                data: [listaTareas[0].misExp, listaTareas[1].misExp, listaTareas[2].misExp, listaTareas[3].misExp],
				backgroundColor : "rgba(54, 162, 235, 0.5)",
				borderColor: "rgba(255,99,132,1)",
                borderWidth: 0
			}]
	};
	
	vista.colorsTramitesData = {
			labels: [formatLabel($.rup.i18nParse($.rup.i18n.app,"planifTramitesTipos.pendientes"), 100), 
				formatLabel($.rup.i18nParse($.rup.i18n.app,"planifTramitesTipos.aceptados"), 100), 
				formatLabel($.rup.i18nParse($.rup.i18n.app,"planifTramitesTipos.expiradosRechazados") + '(*)', 100)],
			datasets : [ {
				label: $.rup.i18nParse($.rup.i18n.app,"label.todos"),
                data: [listaTramites[0].total, listaTramites[1].total, listaTramites[2].total],
				backgroundColor : "rgba(255, 99, 132, 0.5)" ,
				borderColor: "rgba(255,99,132,1)",
                borderWidth: 0
			},
			{
				label: $.rup.i18nParse($.rup.i18n.app,"label.misExpedientes"),
                data: [listaTramites[0].misExp, listaTramites[1].misExp, listaTramites[2].misExp],
				backgroundColor : "rgba(54, 162, 235, 0.5)",
				borderColor: "rgba(255,99,132,1)",
                borderWidth: 0
			}]
	};
}

function fncOnDomRefresh(vista) {
	var options = {
		scales: {
            xAxes: [{
                ticks: {
                    beginAtZero: !0
                }
            }]
        },
        legend: {
            display: true,
            labels: {
                fontColor: 'rgb(0, 0, 0)'
            }
        }
	};
	
	// bar chart
	$(vista.ui.graficoExpedientesBar).rup_chart({
		type : "horizontalBar",
		data : vista.colorsExpedientesData,
		options : options
	});
	$(vista.ui.graficoTareasBar).rup_chart({
		type : "horizontalBar",
		data : vista.colorsTareasData,
		options : options
	});
	$(vista.ui.graficoTramitesBar).rup_chart({
		type : "horizontalBar",
		data : vista.colorsTramitesData,
		options : options
	});
}