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


import com.ejie.x38.dto.JQGridRequestDto;
import com.ejie.x38.dto.JQGridResponseDto;
import com.ejie.x38.dto.Pagination;
import com.ejie.x38.dto.TableRowDto;

import java.util.List;

import com.ejie.x21a.model.Localidad;
import com.ejie.x21a.model.Localidad;

/**
 * LocalidadService generated by UDA, 14-ago-2012 12:59:39.
 * @author UDA
 */

public interface JQGridLocalidadService {

	/**
	 * Inserts a single row in the Localidad table.
	 *
	 * @param localidad Localidad
	 * @return Localidad
	 */
    Localidad add(Localidad localidad);

	/**
	 * Updates a single row in the Localidad table.
	 *
	 * @param localidad Localidad
	 * @return Localidad
	 */
	Localidad update(Localidad localidad);

	/**
	 * Finds a single row in the Localidad table.
	 *
	 * @param localidad Localidad
	 * @return Localidad
	 */
	Localidad find(Localidad localidad);

	/**
	 * Finds a List of rows in the Localidad table.
	 *
	 * @param localidad Localidad
	 * @param pagination Pagination
	 * @return List
	 */
	List<Localidad> findAll(Localidad localidad, JQGridRequestDto jqGridRequestDto);

	/**
	 * Counts rows in the Localidad table.
	 *
	 * @param localidad Localidad
	 * @return Long
	 */
	Long findAllCount(Localidad localidad);
	
	/**
	 * Finds rows in the Localidad table using like.
	 *
	 * @param localidad Localidad
	 * @param pagination Pagination
     * @param startsWith Boolean	 
	 * @return List
	 */
	List<Localidad> findAllLike(Localidad localidad, JQGridRequestDto jqGridRequestDto, Boolean startsWith) ;

	/**
	 * Counts rows in the Localidad table using like.
	 *
	 * @param localidad Localidad
     * @param startsWith Boolean	 
	 * @return Long
	 */
	Long findAllLikeCount(Localidad localidad, Boolean startsWith) ;
  
	/**
	 * Deletes a single row in the Localidad table.
	 *
	 * @param localidad Localidad
	 * @return 
	 */
	void remove(Localidad localidad);
	
	/**
	 * Deletes multiple rows in the Localidad table.
	 *
	 * @param localidadList List
	 * @return 
	 */	
	void removeMultiple(List<Localidad> localidadList);
	
	
	Object reorderSelection(Localidad localidad, JQGridRequestDto jqGridRequestDto, Boolean startsWith);
    
	List<TableRowDto<Localidad>> search(Localidad filterParams, Localidad searchParams, JQGridRequestDto jqGridRequestDto, Boolean startsWith);

	JQGridResponseDto<Localidad> filter(Localidad localidad, JQGridRequestDto jqGridRequestDto, Boolean startsWith) ;
    
}

