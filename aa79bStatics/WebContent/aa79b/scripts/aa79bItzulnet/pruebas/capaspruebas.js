var capaTabla;

function showDetalleExpediente(anyo,numeroExp){
	//Detach
	//ajax modelandview
	var expediente = new Object();
	expediente.anyo=anyo;
	expediente.numExp=numeroExp;
    
    controlCapas(2, expediente);
}

function showRupList(){
	controlCapas(3, new Object());
}

function controlCapas(dispatch, expediente){
	$.rup_ajax({
	   	 url: '/aa79bItzulnetWar/pruebas/cargarJsp/' + dispatch 
 	     ,data : expediente
	   	 ,success:function(data, textStatus, jqXHR){
	   		 $("#hijo").html(data);
	   		 capaTabla = $('#estudioExpedientes_div').detach();
	   	 },
	   	 error: function (XMLHttpRequest, textStatus, errorThrown){
			alert('Error recuperando datos del paso');
	   	 }
	 });
}

jQuery(function($) {
	$("#estudioExpedientes").rup_table({
		url: "/aa79bItzulnetWar/tramitacionexpedientes/gestionexpedientes/estudioexpedientes",
		toolbar:{
			id: "estudioExpedientes_toolbar"
			, defaultButtons:{
				add:false,
				edit:false,
				cancel:false,
				save:false,
				clone:false,
				"delete":false,
				filter:true
			}
		},
		colNames: [
			"",
			"",
			"",
			"Número Expediente",
			"Tipo"
		],
		colModel: [
			{
				name: "anyo",
				hidden: true
			},
			{
				name: "numExp",
				hidden: true
			},
			{
				name: "origen",
				hidden: true
			},
			{ 	name: "anyoNumExpConcatenado", 
			 	label: "label.anyoNumExpConcatenado",
				align: "center", 
				width: "90", 
				index: "ANYONUMEXPCONCATENADO",
				editable: true, 
				fixed: false, 
				hidden: false, 
				resizable: true, 
				sortable: true,
				formatter: function (cellvalue, options, rowObject) {
					dataOrigen = rowObject.origen;
					origen = rowObject.origen;
					
					return '<a href="#" onclick="showDetalleExpediente(' + rowObject.anyo + ',' + rowObject.numExp + ')">' + cellvalue + '</a>';
				}
			},
			{ 	name: $.rup.lang === 'es' ? "tipoExpedienteDescEs"
					: "tipoExpedienteDescEu", 
			 	label: "label.tipoExpediente",
				align: "center",
				index: $.rup.lang === 'es' ? "TIPO_EXPEDIENTE_ES"
						: "TIPO_EXPEDIENTE_EU",
				width: "50", 
				editable: true, 
				fixed: false, 
				hidden: false, 
				resizable: true, 
				sortable: true
			}
        ],
        model:"Expediente",
        usePlugins:[
        	"feedback",
        	"toolbar",
        	"responsive",
        	"filter"
         	],
		primaryKey: ["anyo", "numExp"],
		multiSort: true,
		sortname: "ID_FASE_EXPEDIENTE_059, FECHA_FIN_052, IND_VIP_054, IND_PUBLICAR_BOPV_053, ID_REQUERIMIENTO_059, FECHA_LIMITE_064 ",
		sortorder: "asc, asc, desc, desc, asc",
		loadOnStartUp: true,
		multiplePkToken:","
	});
	
	$("#rupListButton").on("click", function(){
		showRupList();
	});
	
});