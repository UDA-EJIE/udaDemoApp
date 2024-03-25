/*
* Copyright 2012 E.J.I.E., S.A.
*
* Licencia con arreglo a la EUPL, Versión 1.1 exclusivamente (la «Licencia»);
* Solo podrá usarse esta obra si se respeta la Licencia.
* Puede obtenerse una copia de la Licencia en
*
* http://ec.europa.eu/idabc/eupl.html
*
* Salvo cuando lo exija la legislación aplicable o se acuerde por escrito,
* el programa distribuido con arreglo a la Licencia se distribuye «TAL CUAL»,
* SIN GARANTÍAS NI CONDICIONES DE NINGÚN TIPO, ni expresas ni implícitas.
* Véase la Licencia en el idioma concreto que rige los permisos y limitaciones
* que establece la Licencia.
*/
package com.ejie.x21a.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.ejie.x21a.model.Usuario;
import com.ejie.x38.dto.TableRequestDto;

public interface CacheService {
	
	/**
	 * Finds a single row in the Usuario table.
	 *
	 * @param usuario Usuario
	 * @return Usuario
	 */
	Usuario find(Usuario usuario);

	public List<Usuario> getAll(Usuario usuario, TableRequestDto tableRequestDto, Boolean cache);
    	
	@Transactional(rollbackFor = Throwable.class)
	public Usuario update(Usuario usuario, Boolean cache);
}
