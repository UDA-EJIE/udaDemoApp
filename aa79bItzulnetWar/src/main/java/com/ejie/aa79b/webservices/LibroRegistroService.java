package com.ejie.aa79b.webservices;

import com.ejie.aa79b.model.SrtLibroRegistro;

/**
 * The type T65bWebServices.
 * 
 * @author vdorantes
 *
 */
public interface LibroRegistroService {

	SrtLibroRegistro registrarEntrada(String descripcionEus, String descripcionCas, String senderId,
			String senderName);


}
