
var url = "/aa79bItzulnetWar/tramitacionexpedientes/planificacionCarga/cargatrabajo/getGanttExpediente/"+false;

function hacerSubmitFiltros(){
	$('#busquedaGeneral_filter_div').html($("#busquedaGeneral_filter_form").clone());
	$("#busquedaGeneral_filter_form").submit();
}

function mostrarTooltipGantt(){
	
	$('.fn-gantt .nav-page-back').prop('title', $.rup.i18n.app.boton.paginaAnterior);
	$('.fn-gantt .nav-page-next').prop('title', $.rup.i18n.app.boton.paginaSiguiente);
	$('.fn-gantt .nav-now').prop('title', $.rup.i18n.app.boton.ahora);
	$('.fn-gantt .nav-prev-week').prop('title', $.rup.i18n.app.boton.semanaAnterior);
	$('.fn-gantt .nav-prev-day').prop('title', $.rup.i18n.app.boton.diaAnterior);
	$('.fn-gantt .nav-next-day').prop('title', $.rup.i18n.app.boton.diaSiguiente);
	$('.fn-gantt .nav-next-week').prop('title', $.rup.i18n.app.boton.semanaSiguiente);
	$('.fn-gantt .nav-zoomIn').prop('title', $.rup.i18n.app.boton.masDesglose);
	$('.fn-gantt .nav-zoomOut').prop('title', $.rup.i18n.app.boton.menosDesglose);
	
	$('#ganttExpediente .fn-gantt .leftPanel span.fn-label').each (function(){
        $(this).attr('title', $(this).html());
        $(this).tooltip();
	});
}

jQuery(function($){
	$('#busquedaGeneral_filter_form').rup_form({
		url: url
		, type: "POST"
		, dataType: "json"
		, success: function(data){
			$("#ganttExpediente").gantt({
				source: data, 
				navigate: "scroll",
				maxScale: "months",
				minScale: "hours",
				itemsPerPage: 10,
				minDate : $("#fechaFinTareaDesde").val(),
				maxDate : $("#fechaFinTareaHasta").val(),
				onItemClick: function(data) {
					if(data != null){
						var dataSplit = data.split(",")
						if (dataSplit.length === 3) {
    						irADetalleTareas(dataSplit[2],dataSplit[0],dataSplit[1]);
						} else if (dataSplit.length === 2){
							irADetalleTareasTrabajo(dataSplit[1]);
						} 
					}
				},
				onRender: function() {
					mostrarTooltipGantt();
				}
			});
		}
	});
	
	jQuery('input:checkbox[data-switch-pestana="true"]').each(function(index, element){
		jQuery(element)
		.bootstrapSwitch()
		.bootstrapSwitch('setSizeClass', 'switch-small')
		.bootstrapSwitch('setOnLabel', jQuery.rup.i18n.app.comun.si)
		.bootstrapSwitch('setOffLabel', jQuery.rup.i18n.app.comun.no);
	});
	
	$('#indFacturable_exp').on('switch-change', function(event, state) {
        if (state.value){ //Activando
        	url = "/aa79bItzulnetWar/tramitacionexpedientes/planificacionCarga/cargatrabajo/getGanttExpediente/"+true;
        	$('#busquedaGeneral_filter_form').rup_form({
        		url: url
        		, type: "POST"
        		, dataType: "json"
        		, success: function(data){
        			$("#ganttExpediente").gantt({
        				source: data, 
        				navigate: "scroll",
        				maxScale: "months",
        				minScale: "hours",
        				itemsPerPage: 10,
        				minDate : $("#fechaFinTareaDesde").val(),
        				maxDate : $("#fechaFinTareaHasta").val(),
        				onItemClick: function(data) {
        					if(data != null){
	        					var dataSplit = data.split(",")
								if (dataSplit.length === 3) {
	        						irADetalleTareas(dataSplit[2],dataSplit[0],dataSplit[1]);
								} else if (dataSplit.length === 2){
									irADetalleTareasTrabajo(dataSplit[1]);
								} 
        					}
        				},
        				onRender: function() {
        					mostrarTooltipGantt();
        				}
        			});
        		}
        	});
        	hacerSubmitFiltros();
        }else{
        	url = "/aa79bItzulnetWar/tramitacionexpedientes/planificacionCarga/cargatrabajo/getGanttExpediente/"+false;
        	$('#busquedaGeneral_filter_form').rup_form({
        		url: url
        		, type: "POST"
        		, dataType: "json"
        		, success: function(data){
        			$("#ganttExpediente").gantt({
        				source: data, 
        				navigate: "scroll",
        				maxScale: "months",
        				minScale: "hours",
        				itemsPerPage: 10,
        				minDate : $("#fechaFinTareaDesde").val(),
        				maxDate : $("#fechaFinTareaHasta").val(),
        				onItemClick: function(data) {
        					if(data != null){
	        					var dataSplit = data.split(",")
								if (dataSplit.length === 3) {
	        						irADetalleTareas(dataSplit[2],dataSplit[0],dataSplit[1]);
								} else if (dataSplit.length === 2){
									irADetalleTareasTrabajo(dataSplit[1]);
								} 
        					}
        				},
        				onRender: function() {
        					mostrarTooltipGantt();
        				}
        			});
        		}
        	});
        	hacerSubmitFiltros();
        }
    });
	
	hacerSubmitFiltros();
	
	$("#cargaTrabajoBusqueda_filter_filterButton").on("click", function(){
		hacerSubmitFiltros();
		$('#busquedaGeneral').rup_table('showSearchCriteria');
	});
			
	
	
	
});
