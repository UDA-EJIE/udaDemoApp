package com.ejie.aa79b.webservices.srp;

import com.ejie.aa79b.model.LibroRegistroModel;

/**
 * RegistroPresencialWebService.
 *
 * @author dlopez
 *
 */
public interface RegistroPresencialWebService {

	/**
	 * Da de alta en el registro presencial
	 * 
	 * @param libroRegistroEntrada
	 *            LibroRegistroEntrada
	 * @return LibroRegistroEntrada
	 * @throws Exception
	 *             Exception
	 */
	public LibroRegistroModel altaRegistroEntrada(LibroRegistroModel libroRegistroEntrada) throws Exception;

	/**
	 * Da de alta en el registro presencial
	 * 
	 * @param libroRegistroEntrada
	 *            LibroRegistroEntrada
	 * @return LibroRegistroEntrada
	 * @throws Exception
	 *             Exception
	 */
	public LibroRegistroModel altaRegistroSalida(LibroRegistroModel libroRegistroEntrada) throws Exception;
}
