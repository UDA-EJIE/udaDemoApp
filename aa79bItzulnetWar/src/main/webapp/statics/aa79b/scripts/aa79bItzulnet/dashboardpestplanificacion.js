jQuery(function($){
	
	bloquearPantalla();
	
	var vista=new Object();
	vista.ui= new Object();

	vista.ui.graficoPlanificacionExpedientesBar = "#graficoPlanificacionExpedientesBar",
	vista.ui.graficoPlanificacionTareasBar = "#graficoPlanificacionTareasBar",
	
	vista.colorsExpedientesData = [];
	vista.colorsTareasData = [];
	
    $(vista.ui.graficoPlanificacionExpedientesBar).on("click", function(evt) {
      selectBar($(this), evt);
    });
    $(vista.ui.graficoPlanificacionTareasBar).on("click", function(evt) {
	    selectBar($(this), evt);
	  });
    
    
    
    //Recuperar lista de beans
    $.ajax({
	   	 type: 'GET'		   	 
   		 ,url: '/aa79bItzulnetWar/dashboard/planificacion/getChartsData'
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
               
       enlazarDestinoGrafica(selector[0].id, idx, type);
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
	
	vista.colorsExpedientesData = {
			labels: [formatLabel($.rup.i18nParse($.rup.i18n.app,"planifExpedientesTipos.pendientesEntrega"), 100), 
				formatLabel($.rup.i18nParse($.rup.i18n.app,"planifExpedientesTipos.hoy"), 100), 
				formatLabel($.rup.i18nParse($.rup.i18n.app,"planifExpedientesTipos.sieteDias"), 100), 
				formatLabel($.rup.i18nParse($.rup.i18n.app,"planifExpedientesTipos.sinPlanificar"), 100)], 
			datasets : [ {
				label: $.rup.i18nParse($.rup.i18n.app,"label.todos"),
                data: [listaExpedientes[0].total, listaExpedientes[1].total, listaExpedientes[2].total, listaExpedientes[3].total],
				backgroundColor : "rgba(255, 99, 132, 0.5)" ,
				borderColor: "rgba(255,99,132,1)",
                borderWidth: 0
			},
			{
				label: $.rup.i18nParse($.rup.i18n.app,"label.misExpedientes"),
                data: [listaExpedientes[0].misExp, listaExpedientes[1].misExp, listaExpedientes[2].misExp, listaExpedientes[3].misExp],
				backgroundColor : "rgba(54, 162, 235, 0.5)",
				borderColor: "rgba(155,99,132,1)",
                borderWidth: 0
			},
			{
				label: $.rup.i18nParse($.rup.i18n.app,"label.misGrupos"),
				data: [listaExpedientes[0].misGrupos, listaExpedientes[1].misGrupos, listaExpedientes[2].misGrupos, listaExpedientes[3].misGrupos],
				backgroundColor : "rgba(125, 196, 91, 0.5)",
				borderColor: "rgba(155,99,132,1)",
				borderWidth: 0
			}]
	};
	vista.colorsTareasData = {
			labels: [formatLabel($.rup.i18nParse($.rup.i18n.app,"planifTareasTipos.sinAsignarHoy"), 100), 
				formatLabel($.rup.i18nParse($.rup.i18n.app,"planifTareasTipos.sinAsignar"), 100),
				formatLabel($.rup.i18nParse($.rup.i18n.app,"planifTareasTipos.asignadas"), 100), 
				formatLabel($.rup.i18nParse($.rup.i18n.app,"planifTareasTipos.finalizanHoy"), 100), 
				formatLabel($.rup.i18nParse($.rup.i18n.app,"planifTareasTipos.retrasadas"), 100)],
			datasets : [ {
				label: $.rup.i18nParse($.rup.i18n.app,"label.todos"),
                data: [listaTareas[0].total, listaTareas[1].total, listaTareas[2].total, listaTareas[3].total, listaTareas[4].total],
				backgroundColor : "rgba(255, 99, 132, 0.5)" ,
				borderColor: "rgba(255,99,132,1)",
                borderWidth: 0
			},
			{
				label: $.rup.i18nParse($.rup.i18n.app,"label.misExpedientes"),
                data: [listaTareas[0].misExp, listaTareas[1].misExp, listaTareas[2].misExp, listaTareas[3].misExp, listaTareas[4].misExp],
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
	$(vista.ui.graficoPlanificacionExpedientesBar).rup_chart({
		type : "horizontalBar",
		data : vista.colorsExpedientesData,
		options : options
	});
	$(vista.ui.graficoPlanificacionTareasBar).rup_chart({
		type : "horizontalBar",
		data : vista.colorsTareasData,
		options : options
	});
}