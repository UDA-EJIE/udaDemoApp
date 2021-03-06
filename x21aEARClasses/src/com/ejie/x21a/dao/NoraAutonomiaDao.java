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

package com.ejie.x21a.dao;

import com.ejie.x38.dto.TableRequestDto;
import java.util.List;

import com.ejie.x21a.model.NoraAutonomia;

/**
 *  * NoraAutonomiaDao generated by UDA, 18-ene-2012 11:57:47.
 * @author UDA
 */

public interface NoraAutonomiaDao {
    

    /**
     * Finds a single row in the NoraAutonomia table.
     *
     * @param autonomia NoraAutonomia
     * @return NoraAutonomia
     */
    NoraAutonomia find(NoraAutonomia autonomia);


    /**
     * Finds a List of rows in the NoraAutonomia table.
     *
     * @param autonomia NoraAutonomia
     * @param tableRequestDto TableRequestDto
     * @return List
     */
    List<NoraAutonomia> findAll(NoraAutonomia autonomia, TableRequestDto tableRequestDto);

    /**
     * Counts rows in the NoraAutonomia table.
     *
     * @param autonomia NoraAutonomia
     * @return List
     */
    Long findAllCount(NoraAutonomia autonomia);
	
	/**
     * Finds rows in the NoraAutonomia table using like.
     *
     * @param autonomia NoraAutonomia
     * @param tableRequestDto TableRequestDto
     * @param startsWith Boolean
     * @return List
     */
	List<NoraAutonomia> findAllLike(NoraAutonomia autonomia, TableRequestDto tableRequestDto, Boolean startsWith);
}

