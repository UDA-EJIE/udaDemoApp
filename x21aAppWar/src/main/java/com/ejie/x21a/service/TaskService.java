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


import com.ejie.x38.dto.TableRequestDto;
import java.util.List;

import com.ejie.x21a.model.Task;

/**
 * ComarcaService generated by UDA, 14-ago-2012 12:59:39.
 * @author UDA
 */

public interface TaskService {

	/**
	 * Inserts a single row in the Comarca table.
	 *
	 * @param task Comarca
	 * @return Comarca
	 */
    Task add(Task task);

	/**
	 * Updates a single row in the Comarca table.
	 *
	 * @param comarca Comarca
	 * @return Comarca
	 */
	Task update(Task task);
	
	/**
	 * Updates a single row in the Comarca table.
	 *
	 * @param comarca Comarca
	 * @return Comarca
	 */
	void delete(Task task);
	
	/**
	 * Updates a single row in the Comarca table.
	 *
	 * @param comarca Comarca
	 * @return Comarca
	 */
	void done(Task task);

	/**
	 * Finds a single row in the Comarca table.
	 *
	 * @param comarca Comarca
	 * @return Comarca
	 */
	Task find(Task task);

	/**
	 * Finds a List of rows in the Comarca table.
	 *
	 * @param comarca Comarca
	 * @param tableRequestDto TableRequestDto
	 * @return List
	 */
	List<Task> findAll(Task task, TableRequestDto tableRequestDto);

	

    
}

