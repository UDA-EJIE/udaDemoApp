jQuery(function($){
	
	var data = {};
	data["entidades"]= [
		{"departamentoProvincia": { 
			"provincia": {"code":"1"},
			"departamento": {"code":"1"}
		}}
	];
	data["data"] = { "label":"code", "value":"desc_es", "style":"css" };
	data = $.toJSON(data);

	$.ajax({
		url: "genericObject", 
		dataType:'json',
		contentType: 'application/json', 
		type:'POST', 
		data: data,
		success: function(data, textStatus, XMLHttpRequest){
			for (key in data) {
				$("#response").append("<label>"+data[key]["label"]+"</label> [<label>"+data[key]["value"]+"</label>]");
			}
  		},
  		error: function(XMLHttpRequest, textStatus, errorThrown){
			$.rup.errorGestor("Se ha producido un error en la prueba");
  		} 
	});
});