/**
 * IMPORTES PARA EXPEDIENTE CON SOLO UNA ENTIDAD/CONTACTO A FACTURAR ASOCIADA
 */
function importeRealizacionTrabajoA(A1,B1){
	A1 = parseFloat(A1);
	B1 = parseFloat(B1);
	
	var IT = A1 * B1;
		
	if(isNaN(IT)){
		IT = 0;
	} else {
		IT = parseFloat(IT.toFixed(2));
	}
	
	return parseFloat(IT.toFixed(2));
}

function importeRealizacionTrabajoB(A1,B4,C3,B3,C2,B2,C1){
	A1 = parseFloat(A1);
	
	if(B4 != ""){
		B4 = parseFloat(B4);
	} else {
		B4 = 0;
	}
	
	C3 = parseFloat(C3);
	
	if(B3 != ""){
		B3 = parseFloat(B3);
	} else {
		B3 = 0;
	}
	
	C2 = parseFloat(C2);
	
	if(B2 != ""){
		B2 = parseFloat(B2);
	} else {
		B2 = 0;
	}
	
	C1 = parseFloat(C1);

	var IT = (A1 * B4 * (C3/100)) + (A1 * B3 * (C2/100)) + (A1 * B2 * (C1/100));
	
	if(isNaN(IT)){
		IT = 0;
	} else {
		IT = parseFloat(IT.toFixed(2));
	}
	
	return parseFloat(IT.toFixed(2));
}

function importeRecargoUrgenciaIU(IT,D1,indUrgente){
	IT = parseFloat(IT);
	D1 = parseFloat(D1);
	
	if(indUrgente){
		var IU = IT * (D1/100);
		
		if(isNaN(IU)){
			IU = 0;
		} else {
			IU = parseFloat(IU.toFixed(2));
		}
	} else {
		IU = 0;
	}
	
	return parseFloat(IU.toFixed(2));
}

function importeRecargoDificultadID(IT,D2,indDificil){
	IT = parseFloat(IT);
	D2 = parseFloat(D2);
	
	if(indDificil){
		var ID = IT * (D2/100);
		
		if(isNaN(ID)){
			ID = 0;
		} else {
			ID = parseFloat(ID.toFixed(2));
		}
	} else {
		ID = 0;
	}
	
	return parseFloat(ID.toFixed(2));
}

function importeTotalFacturarITF(IT,IU,ID){
	IT = parseFloat(IT);
	IU = parseFloat(IU);
	ID = parseFloat(ID);
	
	var ITF = IT + IU + ID;
	
	if(isNaN(ITF)){
		ITF = 0;
	} else {
		ITF = parseFloat(ITF.toFixed(2));
	}
	
	return parseFloat(ITF.toFixed(2));
}

function importeBaseFacturarIBF(ITF,E1, ISIVA){
	ITF = parseFloat(ITF);
	E1 = parseFloat(E1);
	
	if(ISIVA == "S"){
		var IBF = ITF * 100 / (100 + E1);
	}else{
		var IBF = ITF;	
	}
	
	if(isNaN(IBF)){
		IBF = 0;
	} else {
		IBF = parseFloat(IBF.toFixed(3));
	}
	
	return parseFloat(IBF.toFixed(3));
}

function importeIVAAplicadoIVA(ITF,IBF){

	ITF = parseFloat(ITF);
	IBF = parseFloat(IBF.toFixed(2));
	
	var IVA = ITF - IBF;
	
	if(isNaN(IVA)){
		IVA = 0;
	} else {
		IVA = parseFloat(IVA.toFixed(2));
	}
	
	return parseFloat(IVA.toFixed(2));	
}

function numeroTotalPalFacturar(B1,A2){

	B1 = parseFloat(B1);
	A2 = parseFloat(A2);
	
	var B1 = (B1 * A2) / 100;
	
	if(isNaN(B1)){
		B1 = 0;
	} else {
		B1 = parseFloat(B1.toFixed(2));
	}

	return B1;	
}

function numPalFacturarConcor95100(B2,A2){

	B2 = parseFloat(B2);
	A2 = parseFloat(A2);
	
	var B2 = (B2 * A2) / 100;
	
	if(isNaN(B2)){
		B2 = 0;
	} else {
		B2 = parseFloat(B2.toFixed(2));
	}
	
	return B2;	
}

function numPalFacturarConcor8595(B3,A2){

	B3 = parseFloat(B3);
	A2 = parseFloat(A2);
	
	var B3 = (B3 * A2) / 100;
	
	if(isNaN(B3)){
		B3 = 0;
	} else {
		B3 = parseFloat(B3.toFixed(2));
	}
	
	return B3;	
}

function numPalFacturarConcor85(B4,A2){

	B4 = parseFloat(B4);
	A2 = parseFloat(A2);
	
	var B4 = (B4 * A2) / 100;
	
	if(isNaN(B4)){
		B4 = 0;
	} else {
		B4 = parseFloat(B4.toFixed(2));
	}
	
	return B4;	
}

function numPalConcorPorFacturacion(numPal,porFacturacion){
	numPal = parseFloat(numPal);
	porFacturacion = parseFloat(porFacturacion);
	var result = numPal * porFacturacion / 100;	
	
	if(isNaN(result)){
		result = 0;
	} else {
		if(result % 1 != 0){
			if(result % 1 >= 0.5){
				 result = Math.ceil(result);
			} else {
				result = Math.floor(result) 
			}
		}
	}
	
	return result;	
}

function importeRealizacionTrabajo(tarifa,porIva,numTotalPalFacturar){
	tarifa = parseFloat(tarifa);
	numTotalPalFacturar = parseFloat(numTotalPalFacturar);
	
	porIva = "0." + porIva;
	porIva = parseFloat(porIva);
	
	return parseFloat(((numTotalPalFacturar * tarifa) * porIva).toFixed(2));
}

function importeFacturarPorcentaje(precioMinimo,porFactura){
	precioMinimo = parseFloat(precioMinimo);
	porFactura = parseFloat(porFactura);
	
	var importeFinal = (precioMinimo * porFactura) / 100;
	
	if(isNaN(importeFinal)){
		importeFinal = 0;
	} else {
		importeFinal = parseFloat(importeFinal.toFixed(2));
	}
	
	return importeFinal;	
}
