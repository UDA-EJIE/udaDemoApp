/*
* Copyright 2020 E.J.I.E., S.A.
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
package com.ejie.x21a.dao;

import java.util.List;

import com.ejie.x21a.model.Provincia;
import com.ejie.x38.dto.TableRequestDto;
import com.ejie.x38.dto.TableRowDto;

/**
 * ProvinciaDao generated by UDA, 14-ago-2012 12:59:38.
 * @author UDA
 */

public interface ProvinciaDao {
    
    /**
     * Inserts a single row in the Provincia table.
     *
     * @param provincia Provincia
     * @return Provincia
     */
    Provincia add(Provincia provincia);

    /**
     * Updates a single row in the Provincia table.
     *
     * @param provincia Provincia
     * @return Provincia
     */
    Provincia update(Provincia provincia);

    /**
     * Finds a single row in the Provincia table.
     *
     * @param provincia Provincia
     * @return Provincia
     */
    Provincia find(Provincia provincia);

    /**
     * Deletes a single row in the Provincia table.
     *
     * @param provincia Provincia
     * @return 
     */
    void remove(Provincia provincia);

    /**
     * Finds a List of rows in the Provincia table.
     *
     * @param provincia Provincia
     * @param tableRequestDto TableRequestDto
     * @return List
     */
    List<Provincia> findAll(Provincia provincia, TableRequestDto tableRequestDto);

    /**
     * Counts rows in the Provincia table.
     *
     * @param provincia Provincia
     * @return Long
     */
    Long findAllCount(Provincia provincia);
	
	/**
     * Finds rows in the Provincia table using like.
     *
     * @param provincia Provincia
     * @param tableRequestDto TableRequestDto
     * @param startsWith Boolean
     * @return List
     */
	List<Provincia> findAllLike(Provincia provincia, TableRequestDto tableRequestDto, Boolean startsWith);
	
    /**
     * Counts rows in the Provincia table using like.
     *
     * @param provincia Provincia
     * @param startsWith Boolean
     * @return Long
     */
    Long findAllLikeCount(Provincia provincia, Boolean startsWith);

    /*
	 * OPERACIONES RUP_TABLE
	 */

    /**
	 * Deletes multiple rows in the Provincia table.
	 *
	 * @param filterProvincia Provincia
	 * @param tableRequestDto TableRequestDto
	 * @param startsWith Boolean	 
	 */
	void removeMultiple(Provincia filterProvincia, TableRequestDto tableRequestDto, Boolean startsWith);
	
	List<Provincia> getMultiple(Provincia filterProvincia, TableRequestDto tableRequestDto, Boolean startsWith);
    
    List<TableRowDto<Provincia>> reorderSelection(Provincia provincia, TableRequestDto tableRequestDto, Boolean startsWith);
    
    List<TableRowDto<Provincia>> search(Provincia filterParams, Provincia searchParams, TableRequestDto tableRequestDto, Boolean startsWith);
}