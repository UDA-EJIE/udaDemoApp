var elFormulario;
var idEntidadAsocAnterior = "";
var datosFormulario = '';
var cuantiaTrad;
var cuantiaRev;
var indBopv;
var indBoe = 'N';
var porIva;
var importeBase;
var importeIva;
var contacto;
var numTotalPal;
var concordancia95100;
var concordancia8594;
var concordancia084;
var porFactura;
var facturable;
var iva;
var indIva;
var indUrgente;
var indDificil;
var importeTotal;
var importeTotalFactura;
var importeBasefactura;
var IT;
var IU;
var ID;
var numConcor084Val;
var concordancia8594Val;
var concordancia95100Val; 
var contactoSinVincular = false;
var indPresupuestoAceptado = false;
var indNoRangos = false;

var configCombo = {
	loadFromSelect: true	
	,ordered: false	
	,rowStriping: true
	,open: function(){
		var id = $(this).attr("id");
        $("#"+id+"-menu").width($("#"+id+"-button").width());
    }
};

function volverARevisionDatosFacturacion(){
	
	$("#divDetalleRevDatosFactu").detach();
	$("#divBusquedaGeneral").append("<div id='divBusquedaGeneralCapa'></div>");
	$("#divBusquedaGeneralCapa").html(capaPestGeneral);
}

function calcularPorcentajes(){
	
	contacto = $("#contactofacturacionexp").rup_table("getRowData");	
	var numTotalPalAcum = 0;
	var concordancia95100Acum = 0;
	var concordancia8594Acum = 0;
	var concordancia084Acum = 0;
	for(var i=0;i<contacto.length;i++){
		porFactura = contacto[i].porFactura058;
		numTotalPal = redondeo(numeroTotalPalFacturar($("#numTotalPal").val(), porFactura), 2);
		concordancia95100 = redondeo(numPalFacturarConcor95100($("#concordancia95100").val(), porFactura), 2);
		concordancia8594 = redondeo(numPalFacturarConcor8595($("#concordancia8594").val(), porFactura), 2);
		concordancia084 = redondeo(numPalFacturarConcor85($("#concordancia084").val(), porFactura), 2);
		numTotalPalAcum = numTotalPalAcum + numTotalPal;
		concordancia95100Acum = concordancia95100Acum + concordancia95100;
		concordancia8594Acum = concordancia8594Acum + concordancia8594;
		concordancia084Acum = concordancia084Acum + concordancia084;
		if (parseFloat(numTotalPalAcum) > parseFloat($("#numTotalPal").val())){
			numTotalPal = numTotalPal -1;
		}
		if (parseFloat(concordancia95100Acum) > parseFloat($("#concordancia95100").val())){
			concordancia95100 = concordancia95100 -1;
		}
		if (parseFloat(concordancia8594Acum) > parseFloat($("#concordancia8594").val())){
			concordancia8594 = concordancia8594 -1;
		}
		if (parseFloat(concordancia084Acum) > parseFloat($("#concordancia084").val())){
			concordancia084 = concordancia084 -1;
		}
		
		iva = contacto[i]["entidadSolicitante.iva"];
		facturable = contacto[i]["entidadSolicitante.facturable"];
		porIva = $("#porIva").text();

		$("#contactofacturacionexp").rup_table("setRowData", contacto[i].id058, {
			"datosFacturacionExpediente.numPalConcor084":concordancia084,
			"datosFacturacionExpediente.numPalConcor8594":concordancia8594,
			"datosFacturacionExpediente.numPalConcor95100":concordancia95100,
			"datosFacturacionExpediente.numTotalPalFacturar":numTotalPal,
			"datosFacturacionExpediente.numPalFacturarConTramos":mostrarNumPalabrasDesglose(numTotalPal,concordancia95100,concordancia8594,concordancia084,porFactura)
		});

		calcularImportes(numTotalPal,
				concordancia95100,concordancia8594,concordancia084,
				facturable,iva,porIva,contacto[i].id058,porFactura);
	}
}

function calcularImportesGeneral(){
		importeIva = 0.0;
		importeTotalFactura = 0.0;
		importeBaseFactura = 0.0;
		if(parseFloat($("#numTotalPalIZO").text()) < parseFloat($("#numPalabrasTarif").text()) || $("#idTipoExp_detail_table").val() === "R" || indNoRangos){
			IT = importeRealizacionTrabajoA($("#tarifaPalabraVal").getImporte(),$("#numTotalPal").val());
		} else {
			IT = importeRealizacionTrabajoB($("#tarifaPalabraVal").getImporte(),$("#concordancia084").val(),$("#palHoraConcor084").text(),$("#concordancia8594").val(),$("#palHoraConcor8594").text(),$("#concordancia95100").val(),$("#palHoraConcor95100").text());
		}		
		IU = importeRecargoUrgenciaIU(IT,$("#recargoPorUrgencia").text(),indUrgente);
		$("#indRecargoPorUrgencia").setImporte(IU);
		
		ID = importeRecargoDificultadID(IT,$("#recargoDificultad").text(),indDificil)
		$("#indRecargoDificultad").setImporte(ID);
		
		importeTotal = importeTotalFacturarITF(IT,IU,ID);
		importeBase = importeBaseFacturarIBF(importeTotal,$("#porIva").text(),"S");
		
		
		
		//JOSE Aqui comprobar indBoe
		
		if($("#indBopv").val() != "S" && indBoe != "S" && importeTotal < $("#precioMinimoVal").getImporte()){
			$("#importeTotal").text($("#precioMinimo").text());
			$("#importeTotalVal").val($("#precioMinimoVal").val());
			var ITF = $("#precioMinimoVal").getImporte();
			importeBase = importeBaseFacturarIBF(ITF,$("#porIva").text(),"S");
		}
		
		calcularPorcentajes();
		
		$("#importeBase").setImporte(importeBaseFactura);
		$("#importeBase").append("€");
		$("#importeBaseVal").setImporte(importeBaseFactura);
		
		$("#importeIva").setImporte(importeIva);
		$("#importeIva").append("€");
		$("#importeIVAAplicadoVal").setImporte(importeIva);
		
		$("#importeTotal").setImporte(importeTotalFactura);
		$("#importeTotal").append("€");
		$("#importeTotalVal").setImporte(importeTotalFactura);
}


function calcularImportes(B1,B2,B3,B4,facturable,iva,porIva,row,porFactura){
	var importeIvaEntidad = 0.0;
	var importeBaseEntidad = 0.0;
	if (facturable == "S"){
		if(parseFloat($("#numTotalPalIZO").text()) < parseFloat($("#numPalabrasTarif").text()) || $("#idTipoExp_detail_table").val() === "R" || indNoRangos){
			IT = importeRealizacionTrabajoA($("#tarifaPalabraVal").getImporte(),B1);
		} else {
			IT = importeRealizacionTrabajoB($("#tarifaPalabraVal").getImporte(),B4,$("#palHoraConcor084").text(),B3,$("#palHoraConcor8594").text(),B2,$("#palHoraConcor95100").text());
		}
		
		IU = importeRecargoUrgenciaIU(IT,$("#recargoPorUrgencia").text(),indUrgente);
		
		ID = importeRecargoDificultadID(IT,$("#recargoDificultad").text(),indDificil);
		
		var ITF = importeTotalFacturarITF(IT,IU,ID);
		importeBaseEntidad = importeBaseFacturarIBF(ITF,porIva, "S");
		if (iva == "S"){
			importeIvaEntidad = importeIVAAplicadoIVA(ITF,importeBaseEntidad);
		}else{
			ITF = importeBaseEntidad;
		}
		
		if($("#indBopv").val() != "S" && indBoe != "S" && importeTotal < $("#precioMinimoVal").getImporte()){
			if (iva=="S"){
				ITF = parseFloat(importeFacturarPorcentaje($("#precioMinimoVal").getImporte(),porFactura));
			}else{
				
				ITF = parseFloat(importeFacturarPorcentaje(importeBase,porFactura));
			}
			
			importeBaseEntidad = importeBaseFacturarIBF(ITF,porIva,iva);
			importeIvaEntidad = importeIVAAplicadoIVA(ITF,importeBaseEntidad);
		}
		
	}else{
		
		ID = 0;
		IU = 0;
		importeBaseEntidad = 0;
		importeIvaEntidad = 0;
		ITF = 0;
	}
	ITF = importeBaseEntidad + importeIvaEntidad;
	importeBaseFactura = importeBaseFactura + importeBaseEntidad;
	importeIva = importeIva + importeIvaEntidad;

	$("#importeDificultadTabla").setImporte(ID);
	$("#importeUrgenciaTabla").setImporte(IU);
	$("#importeBaseTabla").setImporte(importeBaseEntidad);
	$("#importeIvaTabla").setImporte(importeIvaEntidad);
	$("#importeTotalTabla").setImporte(ITF);
	
	importeTotalFactura = importeTotalFactura + ITF;

	$("#contactofacturacionexp").rup_table("setRowData", row, {
		"datosFacturacionExpediente.importeDificultad":$("#importeDificultadTabla").val(),
		"datosFacturacionExpediente.importeUrgencia":$("#importeUrgenciaTabla").val(),
		"datosFacturacionExpediente.importeBase":$("#importeBaseTabla").val(),
		"datosFacturacionExpediente.importeIva":$("#importeIvaTabla").val(),
		"datosFacturacionExpediente.importeTotal":$("#importeTotalTabla").val()
	});
}

function mostrarNumPalabrasDesglose(B1,B2,B3,B4,porFacturacion){

	if(B1 !=null && B1 > 0){
		var numTotalPal = parseFloat(B1);
		var numPalConcor084 = parseFloat(B4);
		var numPalConcor8594 = parseFloat(B3);
		var numPalConcor95100 = parseFloat(B2);

		var celda = '';
		
		celda = celda.concat('<div class="numPalIzoConcor"><div class="numPalIzoConcor1">');
		celda = celda.concat(numTotalPal);
		celda = celda.concat('<br /></div>');
		
		if (numPalConcor084 || numPalConcor8594 || numPalConcor95100){
			celda = celda.concat('<div class="numPalIzoConcor2"><b>0-84%: </b>');
			celda = celda.concat(numPalConcor084);
			celda = celda.concat('<br /><b>85-94%: </b>');
			celda = celda.concat(numPalConcor8594);
			celda = celda.concat('<br /><b>95-100%: </b>');
			celda = celda.concat(numPalConcor95100);
			celda = celda.concat('<br /></div>');
		}
		celda = celda.concat('</div>');
		return celda;
	}else{
		return B1;
	}
}

function getData(){
	$.rup_ajax({
		type: "POST",
		url: '/aa79bItzulnetWar/tramitacionexpedientes/facturacionypagos/revisiondatosfacturacion/detallecontactofacturacion/getdatosdetalle/'+anyoExpediente+'/'+idExpediente,
		dataType: "json",
		contentType: "application/json",
		cache: false,	
		success: function(data){
			if(data.expediente.expedienteTradRev.indFacturable === 'S'){
				$('#indFacturable').bootstrapSwitch('setState', true);
				$("#divFacturableDatos").show();
	        	$("#divEntidadContactoDatos").show();
			}else{
				$('#indFacturable').bootstrapSwitch('setState', false);
				$("#divFacturableDatos").hide();
	        	$("#divEntidadContactoDatos").hide();
			}
			
			if(data.expediente.expedienteTradRev.indDificil === 'S'){
				$('#indDificil').bootstrapSwitch('setState', true);
				indDificil = true;
			}else{
				$('#indDificil').bootstrapSwitch('setState', false);
				indDificil = false;
			}
			
			if(data.expediente.expedienteTradRev.indUrgente === 'S'){
				$('#indUrgente').bootstrapSwitch('setState', true);
				indUrgente = true;
			}else{
				$('#indUrgente').bootstrapSwitch('setState', false);
				indUrgente = false;
			}
			
			$("#idioma").rup_combo("setRupValue", data.expediente.expedienteTradRev.idIdioma);
			
			if(data.expediente.expedienteTradRev.idIdioma == 1){
				$("#bopv").text(data.expediente.expedienteTradRev.publicarBopvDescEu);
			} else {
				$("#bopv").text(data.expediente.expedienteTradRev.publicarBopvDescEs);
			}
			
			$("#fechaHoraSol").text(data.expediente.fechaHoraAlta);
			$("#fechaHoraIZO").text(data.expediente.expedienteTradRev.fechaHoraFinalIZO);
			$("#idRelevancia").rup_combo("setRupValue", data.expediente.expedienteTradRev.idRelevancia);
			$("#idIdioma_detail_table").val(data.expediente.expedienteTradRev.idIdioma);	
			$("#idTipoExp_detail_table").val(data.expediente.idTipoExpediente);	
			$("#indBopv").val(data.expediente.expedienteTradRev.indPublicarBopv);	
			indBoe = data.expediente.expedienteTradRev.esExpedienteBoe;
			$("#numTotalPalSolic").text(data.expediente.expedienteTradRev.numTotalPalSolic);
			$("#numTotalPalIZO").text(data.expediente.expedienteTradRev.numTotalPalIzo);
			
			
			if(data.expediente.expedienteTradRev.tradosExp != null){
				$("#numTotalPalXml").text(data.expediente.expedienteTradRev.tradosExp.numTotalPal);
				$("#concordancia084Label").text(data.expediente.expedienteTradRev.tradosExp.numPalConcor084);
				$("#concordancia8594Label").text(data.expediente.expedienteTradRev.tradosExp.numPalConcor8594);
				$("#concordancia95100Label").text(data.expediente.expedienteTradRev.tradosExp.numPalConcor95100);
			}
			if(data.expediente.expedienteTradRev.datosFacturacionExpediente != null){
				$("#idOrden_detail_table").val(data.expediente.expedienteTradRev.datosFacturacionExpediente.idOrden);
				$("#numTotalPal").val(data.expediente.expedienteTradRev.datosFacturacionExpediente.numTotalPalFacturar);
				if(data.ordenPrecios && data.ordenPrecios.tarifExpTradRev && data.ordenPrecios.tarifExpTradRev.numPalabrasTarifConcor &&
						data.ordenPrecios.tarifExpTradRev.numPalabrasTarifConcor > data.expediente.expedienteTradRev.datosFacturacionExpediente.numTotalPalFacturar){
					//si se ha guardado en la pestanya de documetnacion, en planificacion, se guardan los datos como datos de facturacion
					//comprobar si numTotal < num palabras orden de precios publicos para deshabilitar tramos
					$('#concordancia084').attr('disabled','disabled');
					$('#concordancia8594').attr('disabled','disabled');
					$('#concordancia95100').attr('disabled','disabled');
					indNoRangos = true;
				}else{
					if(data.expediente.expedienteTradRev.tradosExp != null){
						$("#concordancia084").val(data.expediente.expedienteTradRev.datosFacturacionExpediente.numPalConcor084);
						$("#concordancia8594").val(data.expediente.expedienteTradRev.datosFacturacionExpediente.numPalConcor8594);
						$("#concordancia95100").val(data.expediente.expedienteTradRev.datosFacturacionExpediente.numPalConcor95100);
					}else{
						$('#concordancia084').attr('disabled','disabled');
						$('#concordancia8594').attr('disabled','disabled');
						$('#concordancia95100').attr('disabled','disabled');
						indNoRangos = true;
					}
				}
				
				$("#importeBase").text(data.expediente.expedienteTradRev.datosFacturacionExpediente.importeBase + "€");
				$("#importeIva").text(data.expediente.expedienteTradRev.datosFacturacionExpediente.importeIva + "€");
				$("#importeTotal").text(data.expediente.expedienteTradRev.datosFacturacionExpediente.importeTotal + "€");
				$("#importeBaseVal").val(data.expediente.expedienteTradRev.datosFacturacionExpediente.importeBase);
				$("#importeIVAAplicadoVal").val(data.expediente.expedienteTradRev.datosFacturacionExpediente.importeIva);
				$("#importeTotalVal").val(data.expediente.expedienteTradRev.datosFacturacionExpediente.importeTotal);
				$("#indRecargoDificultad").text(data.expediente.expedienteTradRev.datosFacturacionExpediente.importeDificultad);
				$("#indRecargoPorUrgencia").text(data.expediente.expedienteTradRev.datosFacturacionExpediente.importeUrgencia);
			}else{
				if(data.ordenPrecios && data.ordenPrecios.tarifExpTradRev && data.ordenPrecios.tarifExpTradRev.numPalabrasTarifConcor &&
						data.ordenPrecios.tarifExpTradRev.numPalabrasTarifConcor < data.expediente.expedienteTradRev.numTotalPalIzo){
					
					if(data.expediente.expedienteTradRev.tradosExp != null){
						$("#numTotalPal").val(data.expediente.expedienteTradRev.tradosExp.numTotalPal);
						$("#concordancia084").val(data.expediente.expedienteTradRev.tradosExp.numPalConcor084);
						$("#concordancia8594").val(data.expediente.expedienteTradRev.tradosExp.numPalConcor8594);
						$("#concordancia95100").val(data.expediente.expedienteTradRev.tradosExp.numPalConcor95100);
					} else {
						$("#numTotalPal").val(data.expediente.expedienteTradRev.numTotalPalIzo);
						$('#concordancia084').attr('disabled','disabled');
						$('#concordancia8594').attr('disabled','disabled');
						$('#concordancia95100').attr('disabled','disabled');
						indNoRangos = true;
					}
				}else{
					$("#numTotalPal").val(data.expediente.expedienteTradRev.numTotalPalIzo);
					$('#concordancia084').attr('disabled','disabled');
					$('#concordancia8594').attr('disabled','disabled');
					$('#concordancia95100').attr('disabled','disabled');
					indNoRangos = true;
				}
			}
			
			// En caso de que el expediente haya solicitado presupuesto y se haya aceptado,
			// se deshabilitan los campos idRelevancia, indDificil, indUrgente, numTotalPal, 
			// concordancia084, concordancia8594, concordancia95100
			// y también la toolbar de las entidades de facturación
			if(data.expediente.expedienteTradRev.presupuestoAceptado === 1){
				$('#idRelevancia').rup_combo('disable');
				$('#indDificil').attr('disabled','disabled');
				$('#indDificil').parent().parent().addClass("disabled");
				$('#indUrgente').attr('disabled','disabled');
				$('#indUrgente').parent().parent().addClass("disabled");
				$('#numTotalPal').attr('disabled','disabled');
				$('#concordancia084').attr('disabled','disabled');
				$('#concordancia8594').attr('disabled','disabled');
				$('#concordancia95100').attr('disabled','disabled');
				$('#leyendaPptoRequerido').show();
				$('#contactofacturacionexp_toolbar').hide();
				indPresupuestoAceptado = true;
			} else {
				$('#leyendaPptoRequerido').hide();
			}

			if(data.ordenPrecios != null){
				$("#idOrden_detail_table").val(data.ordenPrecios.id);
				cuantiaTrad = data.ordenPrecios.listaCuantiaTrad;
				cuantiaRev = data.ordenPrecios.listaCuantiaRev;

				if($("#idTipoExp_detail_table").val() === "T"){
					datos = cuantiaTrad.filter( function (x){ return x.idTipoRelevancia == data.expediente.expedienteTradRev.idRelevancia})[0];
					if(datos != null){
						if($('#idioma').rup_combo("getRupValue") == enums.idioma.es){
							$("#tarifaPalabra").text(datos.tarifaEuEs + "€");
							$("#tarifaPalabraVal").val(datos.tarifaEuEs);
						} else {
							$("#tarifaPalabra").text(datos.tarifaEsEu + "€");
							$("#tarifaPalabraVal").val(datos.tarifaEsEu);
						}
					}
				} else {
					datos = cuantiaRev.filter( function (x){ return x.idTipoRelevancia == data.expediente.expedienteTradRev.idRelevancia})[0];
					if(datos != null){
						if($('#idioma').rup_combo("getRupValue") == enums.idioma.es){
							$("#tarifaPalabra").text(datos.tarifaEuEs + "€");
							$("#tarifaPalabraVal").val(datos.tarifaEuEs);
						} else {
							$("#tarifaPalabra").text(datos.tarifaEsEu + "€");
							$("#tarifaPalabraVal").val(datos.tarifaEsEu);
						}	
					}
				}
				
				$("#titulo").text(data.ordenPrecios.titulo);				
				$("#porIva").text(data.ordenPrecios.ivaVigente.porIva);
				$("#porIvaVal").val(data.ordenPrecios.ivaVigente.porIva);
				$("#recargoDificultad").text(data.ordenPrecios.tarifExpTradRev.porRecargoDif);
				$("#recargoPorUrgencia").text(data.ordenPrecios.tarifExpTradRev.porRecargoUrg);
				$("#precioMinimo").text(data.ordenPrecios.tarifExpTradRev.precioMinimo + "€");
				$("#precioMinimoVal").val(data.ordenPrecios.tarifExpTradRev.precioMinimo);
				$("#numPalabrasTarif").text(data.ordenPrecios.tarifExpTradRev.numPalabrasTarifConcor);
				$("#palHoraConcor084").text(data.ordenPrecios.tarifExpTradRev.porPalabraConcor084);
				$("#palHoraConcor8594").text(data.ordenPrecios.tarifExpTradRev.porPalabraConcor8594);
				$("#palHoraConcor95100").text(data.ordenPrecios.tarifExpTradRev.porPalabraConcor95100);
				
				$("#contactofacturacionexp").rup_table("filter");	
			}else{
				volverARevisionDatosFacturacion();
				$.rup_messages("msgAlert", {
					title: $.rup.i18n.app.label.aviso,
					message: $.rup.i18n.app.mensajes.errorNoOrdenVigente
				});	
			}
			
			if($("#idTipoExp_detail_table").val() === "R") {
				
				$("#precioTraduccionesTramos_filter_fieldset").hide();
				$("#divPalabrasXml").hide();
				$("#concordanciasFacturar").hide();
			}
			
			$("#observacionesFacturacion").val(data.expediente.obsvFacturacion);
			
			
			desbloquearPantalla();
		}
	});
}

function guardarDatosEntidades(){
	var listaExpedientes = new Array();
	contacto = $("#contactofacturacionexp").rup_table("getRowData");	

	for(var i=0;i<contacto.length;i++){
		
		var Direccion = 
		{
    		"direccion": contacto[i]["entidadSolicitante.direccion.direccion"],
    		"dirNora": contacto[i]["entidadSolicitante.direccion.dirNora"]
    		
		};
		
		var Entidad = 
	    {
			"descAmpEu": contacto[i]["entidadSolicitante.descAmpEu"],
    		"direccion": Direccion,
    		"facturable": contacto[i]["entidadSolicitante.facturable"],
    		"facturableDesc": contacto[i]["entidadSolicitante.facturableDesc"],
    		"iva": contacto[i]["entidadSolicitante.iva"],
    		"ivaDesc": contacto[i]["entidadSolicitante.ivaDesc"],
	    };
		
		var DatosFacturacionExpediente = 
	    {
	        "importeBase": parseDecimal(contacto[i]["datosFacturacionExpediente.importeBase"]),
	        "importeIva": parseDecimal(contacto[i]["datosFacturacionExpediente.importeIva"]),
	        "importeTotal": parseDecimal(contacto[i]["datosFacturacionExpediente.importeTotal"]),
	        "numTotalPalFacturar": contacto[i]["datosFacturacionExpediente.numTotalPalFacturar"],
	        "importeDificultad": parseDecimal(contacto[i]["datosFacturacionExpediente.importeDificultad"]),
	        "importeUrgencia": parseDecimal(contacto[i]["datosFacturacionExpediente.importeUrgencia"]),
			"numPalConcor084": contacto[i]["datosFacturacionExpediente.numPalConcor084"],
	        "numPalConcor8594": contacto[i]["datosFacturacionExpediente.numPalConcor8594"],
			"numPalConcor95100": contacto[i]["datosFacturacionExpediente.numPalConcor95100"]
	    };
		
		var IvaPrecios = 
		{
			"porIva": contacto[i]["ordenPrecios.ivaVigente.porIva"],
		};
		
		var OrdenPrecios = 
	    {
	        "ivaVigente": IvaPrecios,
	    };

		listaExpedientes.push( {
			"id058": contacto[i].id058,
    		"anyo": contacto[i].anyo,
    		"numExp": contacto[i].numExp,
    		"tipoEntidadAsoc058": contacto[i].tipoEntidadAsoc058,
    		"idEntidadAsoc058": contacto[i].idEntidadAsoc058,
    		"dniContacto058": contacto[i].dniContacto058,
    		"porFactura058": contacto[i].porFactura058,
    		"entidadSolicitante": Entidad,
    		"ordenPrecios": OrdenPrecios,
    		"datosFacturacionExpediente": DatosFacturacionExpediente
		});
	}
	
	jQuery.ajax({
	    	type: "POST",
	    	url: "/aa79bItzulnetWar/tramitacionexpedientes/facturacionypagos/revisiondatosfacturacion/detallecontactofacturacion/finRevisionContactosEntidades",
	    	dataType: "json",
	    	contentType: 'application/json',
	    	data: JSON.stringify({
	    		"listaExpedientes" : JSON.stringify(listaExpedientes)
	    	}),
	    	cache: false,
	    	success: function (data){
	    		//obtenemos los importes totales antes de realizar el submit, porque con la nueva disposicion, los campos visibles han quedado fuera del form 
	    		$('#importeBaseValExp').val($('#importeBaseVal').val());
	    		$('#importeIVAAplicadoValExp').val($('#importeIVAAplicadoVal').val());
	    		$('#importeTotalValExp').val($('#importeTotalVal').val());
	    		$('#detalleRevDatosFactu_filter_form').submit();
	    	},
	    	error: function (){
	    		$('#detalleRevDatosFactu_feedback').rup_feedback("set", $.rup.i18n.app.mensajes.errorGuardandoDatos, "error");
	    		desbloquearPantalla();
	    	}
    });
}

function eliminarContactoFacturacion(){
	var selectedRows = $("#contactofacturacionexp").rup_table('getSelectedRows');			
	var idDoc = selectedRows[0];
				
	jQuery.ajax({
    	type: "DELETE",
    	url: "/aa79bItzulnetWar/contactofacturacionexpediente/listadoconjoins/"+idDoc,
    	dataType: "json",
    	contentType: 'application/json',
    	cache: false,
    	success:function(){
    		$('#contactofacturacionexp').trigger('reloadGrid');
    		$("#contactofacturacionexp_feedback").rup_feedback("set", $.rup.i18nParse($.rup.i18n.base,"rup_table.feedback.deletedOK"), "ok");
    		calcularImportesGeneral();
        }, 
   	 	error: function(){
   	 		$("#contactofacturacionexp_feedback").rup_feedback("set", $.rup.i18nParse($.rup.i18n.app,"rup_table.feedback.deletedError"), "error");
   	 	}
    });
}

function creaComboContacto(tipoEntidadAsoc, idEntidadAsoc, valorSeleccionar){
	if ( $('#dniContacto058_detail_table-button').length   ){
		$('#dniContacto058_detail_table').rup_combo("clear");
	}
	
	$('#dniContacto058_detail_table').rup_combo({
		source : "/aa79bItzulnetWar/solicitante/"+tipoEntidadAsoc+"/"+idEntidadAsoc,
		sourceParam : {
			value: "dni",
			label : "nombreCompletoCentroOrganico" 
		},
	    rowStriping: true,
	    blank: "",
		open: function(){
			var id = $(this).attr("id");
	        $("#"+id+"-menu").width($("#"+id+"-button").width());
	    },
	    onLoadSuccess: function(){ 
	    	if (typeof(valorSeleccionar) !== "undefined"){
	    		$("#dniContacto058_detail_table").rup_combo("select", valorSeleccionar+'');
	    		elFormulario = $("#excepcionfacturacion_detail_form").rup_form("formSerialize");
	    	}
	    },
	    ordered: false
	});	
}

function validarExcepcionNueva(){
	$("div.error").remove();
	eliminarMensajesValidacion();
	
	var datos = jQuery("#contactofacturacionexp_detail_form").rup_form().formToJson();
	var error = [true];
	$.ajax({
	   	 type: 'POST'
	   	 ,url: '/aa79bItzulnetWar/contactofacturacionexpediente/listadoconjoins/validarContactoFacturacion'
	 	 ,dataType: 'json'
	 	 ,contentType: 'application/json' 
	     ,data: $.toJSON(datos)			
	     ,async: false
	   	 ,success:function(codigoVuelta){
	   		switch (codigoVuelta){
	   		 case 1: $("#contactofacturacionexp_detail_feedback").rup_feedback("set", $.rup.i18n.app.validaciones.existeExcepcion, "error");	
	   		 		error =  [false];
	   		 		break;
	   		 case 2: $("#contactofacturacionexp_detail_feedback").rup_feedback("set",$.rup.i18n.app.validaciones.excepFactSupera100, "error");	
	   		 		error =  [false];
	   		 		break;
	   		 }
	   	 }
   	 	,error: function(){
	   		$('#contactofacturacionexp_detail_feedback').rup_feedback("set", $.rup.i18n.app.validaciones.errorLlamadaAjax, "error");
	   		error =  [false];
	   	 }
	 });
	return error;
}

function serializeForm(){
	datosFormulario = $("#detalleRevDatosFactu_filter_form").serialize();
	/*adaptar fieldsets*/
	if($('#numPalabrasFact_filter_fieldset').length && $('#numPalabrasExp_filter_fieldset').length){
		if($('#numPalabrasFact_filter_fieldset').height()>$('#numPalabrasExp_filter_fieldset').height()){
			$('#numPalabrasExp_filter_fieldset').height($('#numPalabrasFact_filter_fieldset').height());
		}else if($('#numPalabrasExp_filter_fieldset').height()>$('#numPalabrasFact_filter_fieldset').height()){
			$('#numPalabrasFact_filter_fieldset').height($('#numPalabrasExp_filter_fieldset').height())
		}
	}
}

jQuery.validator.addMethod("validateConcordanciaMayorNumPal", function(valor) {  
	numConcor084Val = $("#concordancia084").val() === "" ? 0 : $("#concordancia084").val();
	concordancia8594Val = $("#concordancia8594").val() === "" ? 0 : $("#concordancia8594").val();
	concordancia95100Val = $("#concordancia95100").val() === "" ? 0 : $("#concordancia95100").val();

	var sumaConcordancia = parseFloat(numConcor084Val) + parseFloat(concordancia8594Val) + parseFloat(concordancia95100Val);
	if($("#indFacturable").bootstrapSwitch('state') === true){
		if(parseFloat($("#numTotalPal").val()) != sumaConcordancia && sumaConcordancia > 0){
			return false;
		}else{
			return true;    		
		}
	} else {
		return true;
	}
},$.rup.i18n.app.mensajes.concordanciaMayorNumPal);

jQuery(function($){
	
	$("#anyo_filtro_fact").val(anyoExpediente);
	$("#numExp_filtro_fact").val(idExpediente);
	$("#anyo").val(anyoExpediente);
	$("#numExp").val(idExpediente);
	
	$('#idioma').rup_combo({
		source : "/aa79bItzulnetWar/idiomadestino",
		sourceParam :{
			label: $.rup.lang === 'es' ? "descIdiomaEs"
					: "descIdiomaEu", 
			value: "idIdioma",
			style:"css"
		},
		getText: false,
		rowStriping: true,
		open : function() {
			jQuery('#idioma-menu').width(jQuery('#idioma-button').innerWidth());
			jQuery('.ui-autocomplete.ui-menu.ui-widget.ui-widget-content.ui-corner-all:visible').css("min-width", jQuery('#idioma').innerWidth());
			$("#idioma").css("z-index", $("#entidades_detail_div").parent().css("z-index")+1);
			$("#idioma_menu").removeClass("ui-front");
		},
		ordered:false,
		select: function(){
			var datos;
			var idiomaSeleccionado = $('#idioma').rup_combo("getRupValue");
			if($('#idRelevancia').rup_combo("getRupValue") != ""){
				if($("#idTipoExp_detail_table").val() === "T"){
					datos = cuantiaTrad.filter( function (x){ return x.idTipoRelevancia == $('#idRelevancia').rup_combo("getRupValue")})[0];
					if(datos != null){
						if(idiomaSeleccionado == enums.idioma.es){
							$("#tarifaPalabra").text(datos.tarifaEuEs + "€");
							$("#tarifaPalabraVal").val(datos.tarifaEuEs);
						} else {
							$("#tarifaPalabra").text(datos.tarifaEsEu + "€");
							$("#tarifaPalabraVal").val(datos.tarifaEsEu);
						}
					}
				} else {
					datos = cuantiaRev.filter( function (x){ return x.idTipoRelevancia == $('#idRelevancia').rup_combo("getRupValue")})[0];
					if(datos != null){
						if(idiomaSeleccionado == enums.idioma.es){
							$("#tarifaPalabra").text(datos.tarifaEuEs + "€");
							$("#tarifaPalabraVal").val(datos.tarifaEuEs);
						} else {
							$("#tarifaPalabra").text(datos.tarifaEsEu + "€");
							$("#tarifaPalabraVal").val(datos.tarifaEsEu);
						}
					}
				}
				calcularImportesGeneral();			
			}
		}
	});
	
	$('#idRelevancia').rup_combo({
		source : "/aa79bItzulnetWar/administracion/datosmaestros/tiporelevancia/estado/A",
		sourceParam :{
			label: $.rup.lang === 'es' ? "descRelevanciaEs"
					: "descRelevanciaEu", 
			value: "idTipoRelevancia",
			style:"css"
		},
		getText: false,
		rowStriping: true,
		open : function() {
			jQuery('#idRelevancia-menu').width(jQuery('#idRelevancia-button').innerWidth());
			jQuery('.ui-autocomplete.ui-menu.ui-widget.ui-widget-content.ui-corner-all:visible').css("min-width", jQuery('#idRelevancia').innerWidth());
			$("#idRelevancia_menu").css("z-index", $("#entidades_detail_div").parent().css("z-index")+1);
			$("#idRelevancia_menu").removeClass("ui-front");
		},
		ordered:false,
		select: function(){
			var datos;
			if($('#idRelevancia').rup_combo("getRupValue") != ""){
				if($("#idTipoExp_detail_table").val() === "T"){
					datos = cuantiaTrad.filter( function (x){ return x.idTipoRelevancia == $('#idRelevancia').rup_combo("getRupValue")})[0];
					if(datos != null){
						if($('#idioma').rup_combo("getRupValue") == enums.idioma.es){
							$("#tarifaPalabra").text(datos.tarifaEuEs + "€");
							$("#tarifaPalabraVal").val(datos.tarifaEuEs);
						} else {
							$("#tarifaPalabra").text(datos.tarifaEsEu + "€");
							$("#tarifaPalabraVal").val(datos.tarifaEsEu);
						}
					}
				} else {
					datos = cuantiaRev.filter( function (x){ return x.idTipoRelevancia == $('#idRelevancia').rup_combo("getRupValue")})[0];
					if(datos != null){
						if($('#idioma').rup_combo("getRupValue") == enums.idioma.es){
							$("#tarifaPalabra").text(datos.tarifaEuEs + "€");
							$("#tarifaPalabraVal").val(datos.tarifaEuEs);
						} else {
							$("#tarifaPalabra").text(datos.tarifaEsEu + "€");
							$("#tarifaPalabraVal").val(datos.tarifaEsEu);
						}
					}
				}
				calcularImportesGeneral();			
			}
		}
	});
	
	getData();
	
	$("#detalleRevDatosFactu_toolbar").rup_toolbar({
		buttons : [ {
			i18nCaption : $.rup.i18n.app.boton.volver,
			css : "fa fa-arrow-left",
			id : "volver",
			click : function(e) {
				e.preventDefault();
				e.stopImmediatePropagation();
				if (datosFormulario != $("#detalleRevDatosFactu_filter_form").serialize()){
					$.rup_messages("msgConfirm", {
         				title: $.rup.i18nParse($.rup.i18n.base,"rup_table.changes"),
         				message: $.rup.i18nParse($.rup.i18n.base,"rup_table.saveAndContinue"),
         				OKFunction: function(){
         					volverARevisionDatosFacturacion();
         				}
         			});
				} else {
					volverARevisionDatosFacturacion();
				}
			}
		},{
			i18nCaption : $.rup.i18n.app.boton.finRevDatosFact,
			css: "fa fa-floppy-o", 
			id : "finRevDatosFact",
			click : function(e) {
				e.preventDefault();
				e.stopImmediatePropagation();
				if($("#detalleRevDatosFactu_filter_form").valid()){
					bloquearPantalla($.rup.i18nParse($.rup.i18n.app,"comun.cargando"));
					guardarDatosEntidades();
					
				}
			}
		} ]
	});

	$('#detalleRevDatosFactu_filter_form').rup_form({
		url: '/aa79bItzulnetWar/tramitacionexpedientes/facturacionypagos/revisiondatosfacturacion/detallecontactofacturacion/finRevisionFactura'
		, type: "POST"
		, dataType: "json"
	    , contentType: "application/json"
		, cache: false
		, success: function(data){
    		volverARevisionDatosFacturacion();
			$("#busquedaGeneral").rup_table("filter");		
    		$('#busquedaGeneral_feedback').rup_feedback("set", $.rup.i18n.app.mensajes.finalizadaLaRevision, "ok");
    		desbloquearPantalla();
		}
		,error: function (){
			$('#detalleRevDatosFactu_feedback').rup_feedback("set", $.rup.i18n.app.mensajes.errorGuardandoDatos, "error");
			desbloquearPantalla();
		}
	});


	$("#detalleRevDatosFactu_filter_form").rup_validate({
		feedback: $('#detalleRevDatosFactu_feedback'),
		liveCheckingErrors: false,				
		block:false,
		delay: 3000,
		gotoTop: true, 
		rules:{
			"expediente.expedienteTradRev.datosFacturacionExpediente.numTotalPalFacturar": {validateConcordanciaMayorNumPal:true,required:true}
		},
		showFieldErrorAsDefault: false,
		showErrorsInFeedback: true,
 		showFieldErrorsInFeedback: false
	});
	
	
	
	jQuery('input:checkbox[data-switch-pestana="true"]').each(function(index, element){
		if( this.id.indexOf("indPublicadoBoeDialog") === -1 ){
			jQuery(element)
			.bootstrapSwitch()
			.bootstrapSwitch('setSizeClass', 'switch-small')
			.bootstrapSwitch('setOnLabel', jQuery.rup.i18n.app.comun.si)
			.bootstrapSwitch('setOffLabel', jQuery.rup.i18n.app.comun.no);
		}
	});
	$('#indFacturable').on('switch-change', function(event, state) {
        if (state.value){ //Activando
        	$("#divFacturableDatos").show();
        	$("#divEntidadContactoDatos").show();
        } else {
        	$("#divFacturableDatos").hide();
        	$("#divEntidadContactoDatos").hide();
        }
	});
	
	$('#indUrgente').on('switch-change', function(event, state) {
		if (state.value){ //Activando
			indUrgente = true;
		} else {
			indUrgente = false;
		}
		calcularImportesGeneral();
	});
	
	$('#indDificil').on('switch-change', function(event, state) {
		if (state.value){ //Activando
			indDificil = true;
		} else {
			indDificil = false;
		}
		calcularImportesGeneral();
	});
	
	$('#contactofacturacionexp_feedback').rup_feedback({
		block : false,
		delay:3000
	});
	
	$('#detalleRevDatosFactu_feedback').rup_feedback({
		block : false,
		delay:3000
	});

	$("#contactofacturacionexp").rup_table({
		url: "/aa79bItzulnetWar/contactofacturacionexpediente/entidadesContactosAFacturar",
		toolbar: {
			 id: "contactofacturacionexp_toolbar",			 
			 buttons:[
				{obj: {
					i18nCaption: $.rup.i18n.base.rup_table.del.caption
	 				,css: "fa fa-trash-o"
		 			}
		 			,json_i18n : $.rup.i18n.app.simpelMaint
		 			,click : 
		 				function(e){
	   		 			if(!$('#contactofacturacionexp').rup_table("isSelected")){
							e.preventDefault();
							$.rup_messages("msgAlert", {
								message: $.rup.i18n.app.comun.warningSeleccion
							});	
							return false;
						 }else{
							 $.rup_messages("msgConfirm", {
	             				title: $.rup.i18nParse($.rup.i18n.base,"rup_table.changes"),
	             				message: $.rup.i18nParse($.rup.i18n.base,"rup_table.del.msg"),
	             				OKFunction: function(){
	             					eliminarContactoFacturacion();
	             				}
	             			});
						 }
		 				}	
				},
				{obj: {
					i18nCaption: $.rup.i18n.app.boton.actualizarDatosEntidadContacto
					,css: "fa fa-refresh"
		 			}
		 			,json_i18n : $.rup.i18n.app.simpelMaint
		 			,click : 
		 				function(e){
		 					$('#contactofacturacionexp').trigger('reloadGrid');
		 				}	
				},
				{obj: {
					i18nCaption: $.rup.i18n.app.boton.configEntidades
					,css:"fa fa-building-o"
					,right:true
		 			}
		 			,json_i18n : $.rup.i18n.app.simpelMaint
		 			,click : 
		 				function(e){
		 					window.open(
		 							urlConfigurarEntidad,
	 						  '_blank' 
	 						);
		 				}	
				},
				{obj: {
					i18nCaption: $.rup.i18n.app.boton.configPersonas
					,css:"fa fa-users"
					,right:true
		 			}
		 			,json_i18n : $.rup.i18n.app.simpelMaint
		 			,click : 
		 				function(e){
		 					window.open(
		 							urlConfigurarPersonal,
	 						  '_blank' 
	 						);
		 				}	
				}
			]
			,defaultButtons:{
				add : true
				,edit : true
				,cancel : false
				,save : false
				,clone : false
				,"delete" : false
				,filter : false
			 }
			
		},			
		colNames: [
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			txtEntidadContactoFact,	
			txtCentroOrganico,
			"",
			txtDirPostal,	
			"",
			"",
			txtFacturable,
			txtPorFacturable,
			"",
			txtIVA,
			txtNumPal,
			"",
			"",
			"",
			"",
			"",
			"",
			txtImporteBase,			
			txtImporteIva,		
			txtTotal			
			
		],		
		colModel: [
			{ 	name: "id058", 
			 	label: "label.id",
				align: "", 
				editable: true, 
				hidden: true
			},
			{ 	name: "anyo", 
			 	label: "label.id",
				align: "", 
				editable: true, 
				hidden: true
			},
			{ 	name: "numExp", 
			 	label: "label.dni",
				align: "", 
				editable: true, 
				hidden: true
			},
			{ 	name: "tipoEntidadAsoc058", 
			 	label: "tipoEntidadAsoc058",
				align: "", 
				editable: true, 
				hidden: true
			},
			{ 	name: "idEntidadAsoc058", 
			 	label: "label.entidadAsociada",
				align: "", 
				editable: true, 
				hidden: true
			},
			{ 	name: "dniContacto058", 
			 	label: "label.contacto",
				align: "", 
				editable: true, 
				hidden: true
			},
			{ 	name: "entidadSolicitante.descAmpEu", 
				label: "label.entidadAsociada",
				align: "", 
				editable: true, 
				hidden: true
			},
			{ 	name: "entidadAsociadaYContacto", 
			 	label: "label.entidadAsociada",
			 	index: "ENTIDADDESCAMP",
				align: "", 
				width: "250", 
				editable: true, 
				hidden: false, 
				resizable: true, 
				sortable: false,
				cellattr: function (rowId, tv, rawObject, cm, rdata) { 
				    return 'style="white-space: normal;"';
				},
				formatter: function (cellvalue, options, rowObject) {
					if(!rowObject.contactoVinculado && rowObject.dniContacto058){
						contactoSinVincular = true;
					}
					return cellvalue;
				}
			},
			{ 	name: "contacto.centroOrganico.descAmp", 
			 	label: "label.centroOrganico",
                index: $.rup.i18n.app.label.BDCentroOrganico,
				align: "", 
				width: "300", 
				editable: true, 
				hidden: false, 
				resizable: true
			},
			{ 	name: "entidadSolicitante.direccion.dirNora", 
				label: "label.direccionPostal",
				align: "", 
				editable: true, 
				hidden: true
			},
			{ 	name: "entidadSolicitante.direccion.direccion", 
				label: "label.direccionPostal",
				index: "DIRECCION",
				align: "", 
				width: "300",
				editable: true, 
				hidden: false, 
				resizable: true, 
				sortable: false,
				cellattr: function (rowId, tv, rawObject, cm, rdata) { 
				    return 'style="white-space: normal;"';
				}
			},
			{ 	name: "entidadSolicitante.facturable", 
			 	label: "label.entidadFacturable",
				align: "", 
				editable: true, 
				hidden: true, 
				resizable: true, 
				sortable: false
			},
			{ 	name: "ordenPrecios.ivaVigente.porIva", 
			 	label: "label.aplicaIva",
				align: "", 
				editable: true, 
				hidden: true, 
				resizable: true, 
				sortable: false
			},
			{ 	name: "entidadSolicitante.facturableDesc", 
				label: "label.facturable",
				index: "FACTURABLEDESC",
				align: "center", 
				width: "100",
				editable: true, 
				hidden: false, 
				resizable: true, 
				sortable: false
			},
			{ 	name: "porFactura058", 
				label: "label.porFact",
				index: "PORFACTURA058",
				width: "50",
				align: "center", 
				editable: true, 
				hidden: false, 
				resizable: true, 
				sortable: false
				
			},
			{ 	name: "entidadSolicitante.iva", 
				label: "label.iva",
				align: "center", 
				editable: true, 
				hidden: true, 
				resizable: true, 
				sortable: false
			},
			{ 	name: "entidadSolicitante.ivaDesc", 
			 	label: "label.iva",
			 	index: "IVADESC",
				align: "center", 
				width: "50",
				editable: true, 
				hidden: false, 
				resizable: true, 
				sortable: false
			},
			{ 	name: "datosFacturacionExpediente.numPalFacturarConTramos", 
				label: "label.documento.numPalabras",
				index: "NUMTOTALPALFACTURAR",
				align: "center", 
				width: "175",
				editable: true, 
				hidden: false, 
				resizable: true, 
				sortable: false
			},
			{ 	name: "datosFacturacionExpediente.numTotalPalFacturar", 
				align: "", 
				editable: true, 
				hidden: true, 
				resizable: true, 
				sortable: false
			},
			{ 	name: "datosFacturacionExpediente.importeDificultad", 
				align: "", 
				editable: true, 
				hidden: true, 
				resizable: true, 
				sortable: false
			},
			{ 	name: "datosFacturacionExpediente.importeUrgencia", 
				align: "", 
				editable: true, 
				hidden: true, 
				resizable: true, 
				sortable: false
			},
			{ 	name: "datosFacturacionExpediente.numPalConcor084", 
				align: "", 
				editable: true, 
				hidden: true, 
				resizable: true, 
				sortable: false
			},
			{ 	name: "datosFacturacionExpediente.numPalConcor8594", 
				align: "", 
				editable: true, 
				hidden: true, 
				resizable: true, 
				sortable: false
			},
			{ 	name: "datosFacturacionExpediente.numPalConcor95100", 
				align: "", 
				editable: true, 
				hidden: true, 
				resizable: true, 
				sortable: false
			},
			{ 	name: "datosFacturacionExpediente.importeBase", 
				label: "label.importeBase",
				index: "IMPORTEBASE",
				align: "right", 
				width: "200",
				editable: true, 
				hidden: false, 
				resizable: true, 
				sortable: false
			},
			{ 	name: "datosFacturacionExpediente.importeIva", 
				label: "label.importeIVA",
				index: "IMPORTEIVA",
				align: "right", 
				width: "150",
				editable: true, 
				hidden: false, 
				resizable: true, 
				sortable: false
			},
			{ 	name: "datosFacturacionExpediente.importeTotal", 
				label: "label.totalMayus",
				index: "IMPORTETOTAL",
				align: "right", 
				width: "150",
				editable: true, 
				hidden: false, 
				resizable: true, 
				sortable: false
			}
	    ],
	    title: false,
	    loadComplete: function(){ 
	    	if (contactoSinVincular){
	    		$("#labelContactoNoVinculado").show();
	    		contactoSinVincular = false;
	    	}else{
	    		$("#labelContactoNoVinculado").hide();
	    	}
	    	
	    	if (indPresupuestoAceptado){
	    		$('#contactofacturacionexp').on("rupTable_beforeEditRow", function(){
	    			return false; 
	    		});
	    		
	    	}else{
	    		calcularImportesGeneral();
	    	}
	        $('td[grid_tooltip]').each(function(){
	        $(this).attr('title', $(this).attr('grid_tooltip'));
	        $(this).tooltip();
	        }); 
	    },
	    model:"ContactoFacturacionExpediente",
	    usePlugins:[
			"formEdit",
	    	"feedback",
			"toolbar",
	    	"fluid",
	    	"filter"
	     	],
	    shrinkToFit:false,
		primaryKey: ["id058"],
		sortname: "id058",
		sortorder: "asc",
		loadOnStartUp: true,
	    formEdit:{
	    	detailForm: "#contactofacturacionexp_detail_div",
			fillDataMethod: "clientSide",
	     	validate:{ 
				rules:{
					"id058":{
						required: false
						},
					"tipoEntidadAsoc058":{
						required: true
						},
					"idEntidadAsoc058":{
						required: true
						},
					"idEntidadAsoc058_autocomplete":{
						required: true
						},
					"dniContacto058":{
						required: false
						},
					"porFactura058":{ required: true, maxlength: 3, integer: true, range: [0, 100] }
					},
					showErrorsInFeedback: true,
		     		showFieldErrorsInFeedback: false
			},
			addEditOptions:{
				 width:800,
				 fillDataMethod: "clientSide",
				 reloadAfterSubmit: true,
				 beforeSubmit: function(){	
					 return validarExcepcionNueva();
				 }
			 },
			 editOptions: {				
				beforeShowForm: function(){	
					$('#anyo058_detail_table').val(anyoExpediente);
					$('#numExp058_detail_table').val(idExpediente);
					jQuery('input[name=tipoEntidadFact]:first').click();
					var id = $('#contactofacturacionexp').rup_table("getSelectedRows");
					if(id !== ''){
						//OJO, este campo que crear UDA es el que marca el ID que viaja al controller
						$('#id_g').val(id); 
						$('#id058_detail_table').val($("#contactofacturacionexp").rup_table("getCol", id, "id058"));
						$('#tipoEntidadAsoc058_detail_table').val($("#contactofacturacionexp").rup_table("getCol", id, "tipoEntidadAsoc058"));
						$('#idEntidadAsoc058_detail_table').val($("#contactofacturacionexp").rup_table("getCol", id, "idEntidadAsoc058"));    					
						$('#porFactura058_detail_table').val($("#contactofacturacionexp").rup_table("getCol", id, "porFactura058"));
	 					$('#idEntidadAsociada_label').val($("#contactofacturacionexp").rup_table("getCol", id, "entidadSolicitante.descAmpEu"));
						$("#idEntidadAsociada").rup_autocomplete("set", $('#tipoEntidadAsoc058_detail_table').val()+"_"+$('#idEntidadAsoc058_detail_table').val(), $("#contactofacturacionexp").rup_table("getCol", id, "entidadSolicitante.descAmpEu"));
						idEntidadAsocAnterior = $('#tipoEntidadAsoc058_detail_table').val()+"_"+$('#idEntidadAsoc058_detail_table').val();
						creaComboContacto($('#tipoEntidadAsoc058_detail_table').val(),$('#idEntidadAsoc058_detail_table').val(), $("#contactofacturacionexp").rup_table("getCol", id, "dniContacto058"));
					}
					elFormulario = $("#contactofacturacionexp_detail_form").rup_form("formSerialize");
					$("#contactofacturacionexp").rup_table("updateSavedData", function(savedData){
	  					savedData["anyo"]=$('#anyo058_detail_table').val();
	  					savedData["numExp"]=$('#numExp058_detail_table').val();
	  					savedData["tipoEntidadFact"]=$('input:radio[name="tipoEntidadFact"]:checked').val();		      					
	  					savedData["tipoEntidadAsoc058"]=$('#tipoEntidadAsoc058_detail_table').val();
	  					savedData["idEntidadAsoc058"]=$('#idEntidadAsoc058_detail_table').val();   
	  					savedData["idEntidadAsoc058_autocomplete_label"]=$('#idEntidadAsociada_label').val();    
	  					savedData["idEntidadAsoc058_autocomplete"]=$('#idEntidadAsociada').val();   
	  					savedData["id058"]=$('#id058_detail_table').val();   
	  					savedData["porFactura058"]=$('#porFactura058_detail_table').val();   
	  				});
	  				return true;
	  			}				 
			 },
			 addOptions: {								 		
				 beforeShowForm: function(){
					$('#anyo058_detail_table').val(anyoExpediente);
					$('#numExp058_detail_table').val(idExpediente);
					
					jQuery('input[name=tipoEntidadFact]:first').click();
					$("#dniContacto058_detail_table").rup_combo("disableChild");
					idEntidadAsocAnterior = "";
					$('#porFactura058_detail_table').val('100');
					elFormulario = $("#contactofacturacionexp_detail_form").rup_form("formSerialize");
					$("#contactofacturacionexp").rup_table("updateSavedData", function(savedData){
	  					savedData["anyo"]=$('#anyo058_detail_table').val();
	  					savedData["numExp"]=$('#numExp058_detail_table').val();
	  					savedData["dniContacto058"]=$('#dniContacto058_detail_table').val();
	  					savedData["dniContacto058_label"]='';
	  					savedData["tipoEntidadFact"]=$('input:radio[name="tipoEntidadFact"]:checked').val();		      					
	  					savedData["tipoEntidadAsoc058"]='';
	  					savedData["idEntidadAsoc058"]='';
	  					savedData["idEntidadAsoc058_autocomplete_label"]='';   
	  					savedData["idEntidadAsoc058_autocomplete"]='';       				
	  					savedData["porFactura058"]=$('#porFactura058_detail_table').val();
	  				});					
	   				return true;
	   			}
			 }
	    }
	});
		
	creaComboContacto('A','0');
	
	//Autocomplete Entidad asociada
	$('#idEntidadAsociada').rup_autocomplete({
		source : "/aa79bItzulnetWar/entidad/suggestEntidadesConContactosSolicitantes",
		sourceParam : {
			value: "codigoCompleto",
			label : "descAmp" + $.rup_utils.capitalizedLang()
		},
		menuMaxHeight: "300px",
		getText: false,
		open : function() {
			 var tam = parseFloat(jQuery('#idEntidadAsociada').css("padding-left"))+ parseFloat(jQuery('#idEntidadAsociada').css("padding-right"));
			jQuery('.ui-autocomplete.ui-menu.ui-widget.ui-widget-content.ui-corner-all:visible').css("min-width", jQuery('#idEntidadAsociada').innerWidth());
			$("#idEntidadAsociada_menu").css("z-index", $("#contactofacturacionexp_detail_div").parent().css("z-index")+1);
			$("#idEntidadAsociada_menu").removeClass("ui-front");
		}
	});	
	
	$("#idEntidadAsociada_label").on("rupAutocomplete_change", function(event, data){
		if (idEntidadAsocAnterior !== $('#idEntidadAsociada').rup_autocomplete('getRupValue')){
			var datosEntAsociada = $('#idEntidadAsociada').rup_autocomplete('getRupValue').split("_");	
			$('#idEntidadAsoc058_detail_table').val(datosEntAsociada[1]);
			$('#tipoEntidadAsoc058_detail_table').val(datosEntAsociada[0]);		
			if( $('#idEntidadAsociada_label').val() == ''){ 
				$('#idEntidadAsoc058_detail_table').val('');
				$('#tipoEntidadAsoc058_detail_table').val('');
				$("#dniContacto058_detail_table").rup_combo("disableChild");
			}else{
				creaComboContacto(datosEntAsociada[0],datosEntAsociada[1]);
				idEntidadAsocAnterior = $('#idEntidadAsociada').rup_autocomplete('getRupValue');
			}
		}else{
			if( $('#idEntidadAsociada_label').val() == ''){ 
				$('#idEntidadAsoc058_detail_table').val('');
				$('#tipoEntidadAsoc058_detail_table').val('');
				$("#dniContacto058_detail_table").rup_combo("disableChild");
				idEntidadAsocAnterior = "";
			}
		}
	});
	
	jQuery('input[name=tipoEntidadFact]').change(function(){
		var parametros = $(this).val();
		$("#idEntidadAsociada").rup_autocomplete("option", "extraParams", {tipo:parametros});
		$("#idEntidadAsociada").rup_autocomplete("set", "", "");
		$('#idEntidadAsociada_label').removeData("tmp.loadObjects.term");
		$('#idEntidadAsoc058_detail_table').val('');
		$('#tipoEntidadAsoc058_detail_table').val('');	
		$("#dniContacto058_detail_table").rup_combo("disableChild");
		idEntidadAsocAnterior = "";
	});
	jQuery('input[name=tipoEntidadFact]:first').click();
	
	jQuery("#contactofacturacionexp_detail_form").on("jqGridAddEditBeforeShowForm.rupTable.formEditing", function(){
		setFocusFirstInput("#contactofacturacionexp_detail_form");
	});
	
	jQuery('#contactofacturacionexp_toolbar\\#\\#'+ $.rup.i18n.app.boton.volver).addClass('izda');
	
	$('#numTotalPal').change(function() {
		calcularImportesGeneral();
	});
	$('#concordancia084').change(function() {
		calcularImportesGeneral();
	});
	$('#concordancia8594').change(function() {
		calcularImportesGeneral();
	});
	$('#concordancia95100').change(function() {
		calcularImportesGeneral();
	});
	
	llamadasFinalizadas("serializeForm");
	
});