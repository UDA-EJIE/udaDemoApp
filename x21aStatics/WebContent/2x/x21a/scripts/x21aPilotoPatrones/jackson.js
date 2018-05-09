jQuery(function($){
	
	JSON.stringify = JSON.stringify || function (obj) {
	    var t = typeof (obj);
	    if (t != "object" || obj === null) {
	        // simple data type
	        if (t == "string") obj = '"'+obj+'"';
	        return String(obj);
	    }
	    else {
	        // recurse array or object
	        var n, v, json = [], arr = (obj && obj.constructor == Array);
	        for (n in obj) {
	            v = obj[n]; t = typeof(v);
	            if (t == "string") v = '"'+v+'"';
	            else if (t == "object" && v !== null) v = JSON.stringify(v);
	            json.push((arr ? "" : '"' + n + '":') + String(v));
	        }
	        return (arr ? "[" : "{") + String(json) + (arr ? "]" : "}");
	    }
	};

	
	$("#jacksonLocalidades").rup_grid({
		url:"../jackson/localidades",
		width: 650,
		pagerName:"pager",
		colNames: [ "code", "descEs", "comarca.descEs", "comarca.provincia.descEs"],
		colModel: [
			{ name: "code", index: "1" },
			{ name: "descEs", index: "2" },
			{ name: "comarca.descEs", index: "3" },
			{ name: "comarca.provincia.descEs", index: "4" }
	    ]
	});
	
	$("#btnAjaxEntidad").click(function(){
		
		var data = {code:"1",descEs:"Zubiete",descEu:"Zubieta"};
		$.rup_ajax({
			url: "../jackson/multiEntidad",
			dataType: 'json',
			type: "PUT",
			async: false,
			data: $.toJSON(data),	
			contentType: 'application/json',		    
			success: function (xhr, ajaxOptions) {
				$("#respuesta_ajaxMultiEntidad").text(JSON.stringify(xhr));
			}
		});
		
	});
	
	
	$("#btnAjaxMultiEntidad").click(function(){
		
		var data = {code:"1",descEs:"Zubiete",descEu:"Zubieta", campoDesconocido:"asdasd",
				comarca:{code:"2",descEs:"Encartaciones",descEu:"Enkarterri"}};
		$.rup_ajax({
			url: "../jackson/multiEntidad",
			dataType: 'json',
			type: "PUT",
			async: false,
			data: $.toJSON(data),	
			contentType: 'application/json',		    
			success: function (xhr, ajaxOptions) {
				$("#respuesta_ajaxMultiEntidad").text(JSON.stringify(xhr));
			}
		});
		
	});
	
	$("#btnAjaxPrueba").click(function(){
		
//		var data = {code:"1",descEs:"Encartaciones",descEu:"Enkarterri",localidads:[{code:"1",descEs:"Gordexola",descEu:"Gordexola"},{code:"1",descEs:"Zalla",descEu:"Zalla"}]};
		var data = [{
			rupJavaType:"com.ejie.x21a.model.Comarca",
			rupPropName:"comarca", rupObject:{
				code:"1",descEs:"Encartaciones",descEu:"Enkarterri",
				localidads:[
					{code:"1",descEs:"Gordexola",descEu:"Gordexola"},
					{code:"2",descEs:"Zalla",descEu:"Zalla"}
				]
			}
		},{
			rupJavaType:"com.ejie.x21a.model.Localidad", 
			rupPropName:"localidad", 
			rupObject:{code:"1",descEs:"Gordexola",descEu:"Gordexola", comarca:{
				code:"1",descEs:"Encartaciones",descEu:"Enkarterri"}
			}
		}];
		
		$.rup_ajax({
			url: "../jackson/multiEntidadCollection",
			dataType: 'json',
			type: "PUT",
			async: false,
			data: $.toJSON(data),	
			contentType: 'application/json',		    
			success: function (xhr, ajaxOptions) {
				$("#respuesta_ajaxMultiEntidad").text(JSON.stringify(xhr));
			}
			,beforeSend: function (xhr) {
				xhr.setRequestHeader("RUP_MULTI_MODEL", $.toJSON({Comarca:"com.ejie.x21a.model.Comarca",Localidad:"com.ejie.x21a.model.Localidad"}));
			}
		});
		
	});
	
	$("#btnAjaxMultiModel").click(function(){
		
		var dataMultiModel = [{
			rupJavaType:"com.ejie.x21a.model.Usuario",
			rupPropName:"usuario", 
			rupObject:{
				id:"1",nombre:"Encartaciones",apellido1:"Enkarterri",apellido2:"Enkarterri",fechaAlta:"01/01/2012",fechaBaja:"01/01/2013"
			}
		},{
			rupJavaType:"com.ejie.x21a.model.Localidad", 
			rupPropName:"localidad", 
			rupObject:{code:"1",descEs:"Gordexola",descEu:"Gordexola", comarca:{
				code:"1",descEs:"Encartaciones",descEu:"Enkarterri"}
			}
		}];
		
		$.rup_ajax({
			url: "../jackson/multiEntidadCollection",
			dataType: 'json',
			type: "PUT",
			async: false,
			data: $.toJSON(dataMultiModel),	
			contentType: 'application/json',		    
			success: function (xhr, ajaxOptions) {
				$("#respuesta_ajaxMultiEntidad").text(JSON.stringify(xhr));
			},beforeSend: function (xhr) {
				xhr.setRequestHeader("RUP_MULTI_MODEL", $.toJSON({Comarca:"com.ejie.x21a.model.Comarca",Localidad:"com.ejie.x21a.model.Localidad"}));
			}
		});
		
	});
	
	
	$("#btnAjaxPruebaArray").click(function(){
		var dataPruebaArray = {
				code:"1",
				descEs:"Encartaciones",
				descEs:"Enkarterri",
				provincia:{
					code:"48",
					descEs:"Bizkaia",
					descEu:"Bizkaia"
				},
				localidads:[
				    {code:"192",descEs:"Gordexola",descEu:"Gordexola"},
				    {code:"830",descEs:"Zalla",descEu:"Zalla"}
				]
		};
		
		$.rup_ajax({
			url: "../jackson/pruebaArray",
			dataType: 'json',
			type: "PUT",
			async: false,
			data: $.toJSON(dataPruebaArray),	
			contentType: 'application/json',		    
			success: function (xhr, ajaxOptions) {
				$("#respuesta_ajaxPruebaArray").text(JSON.stringify(xhr));
			},beforeSend: function (xhr) {
				xhr.setRequestHeader("RUP_MULTI_MODEL", $.toJSON({Comarca:"com.ejie.x21a.model.Comarca",Localidad:"com.ejie.x21a.model.Localidad"}));
			}
		});
		
	});
	
	$('#combosLocalidades').rup_combo({
		source : "../jackson/localidades/combo",
		sourceParam : {label:"comarca.desc"+$.rup_utils.capitalizedLang(), value:"comarca.code"},
		width: 300
	});
	
	
	

//	$('#combosLocalidades').rup_combo({
//		source : "../jackson/localidades/combo",
//		sourceParam : {label:"desc"+$.rup_utils.capitalizedLang(), value:"code"},
//		width: 300
//	});
	
});