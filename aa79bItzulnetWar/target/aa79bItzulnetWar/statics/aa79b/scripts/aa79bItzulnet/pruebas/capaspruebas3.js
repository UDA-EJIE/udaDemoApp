var capaList;

jQuery(function($) {
	
	$("#volverButton").click(function(){
		$("#padre").html(capaTabla);
		$("#detalle").remove();
	});
	
	$('#rup-list').rup_list({
		filterForm : 'articulos_filter_form',
		action : '/aa79bItzulnetWar/tramitacionexpedientes/gestionexpedientes/estudioexpedientes/filter',
		feedback: 'rup-list-feedback',
		key :  ["anyo", "numExp"],
		colNames : {
			'numExp' : "Número de expediente"
		},
		sidx : {
			source: [ {
				value : "NUM_EXP_051",
				i18nCaption : "Número de expediente"
			} ],
			value: 'NUM_EXP_051'},
		sord: 'desc',
		visiblePages : 3,
		load: function(){
			$('#rup-list .aa79b-pruebas-menu').on('click', function() {
				$(this).find('i').toggleClass('rotate-180');
				$(this).parent().parent().parent().siblings().find('.aa79b-item-actions').removeClass('show');
				$(this).parent().parent().parent().siblings().find('.aa79b-pruebas-menu i').removeClass('rotate-180');
				$(this).parent().parent().parent().find('.aa79b-item-actions')
						.toggleClass('show');

				$(this).parent().parent().parent().find('.aa79b-pruebas-detail').toggleClass('collapsed');
				$(this).parent().parent().parent().siblings().find('.aa79b-pruebas-detail').addClass('collapsed');
			});
			
			$('#rup-list a.abrirExpediente').on('click', function() {
				var expediente = new Object();
				expediente.anyo=$(this).parent().find('.anyo').text().trim();
				expediente.numExp=$(this).parent().find('.numExp').text().trim();
				$.rup_ajax({
				   	 url: '/aa79bItzulnetWar/pruebas/cargarJsp/' + 2 
			 	     ,data : expediente
				   	 ,success:function(data, textStatus, jqXHR){
				   		capaList = $('#detalle').detach();
				   		$("#hijo").html(data);
				   	 },
				   	 error: function (XMLHttpRequest, textStatus, errorThrown){
						alert('Error recuperando datos del paso');
				   	 }
				 });
			});
			
			$('.aa79b-subcontent').show();
			swingTo('#rup-list-content');
		}
	});
	
	$('#rup-list').rup_list('filter');
	
});