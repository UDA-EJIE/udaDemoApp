jQuery(function($) {
	
	$("#volverButton").click(function(){
		if(capaList !== null && capaList !== ''){
			$("#detalle").remove();	
			$("#padre").html(capaList);
			capaList = '';
		}else{
			$("#padre").html(capaTabla);
			$("#detalle").remove();
		}
	});
});