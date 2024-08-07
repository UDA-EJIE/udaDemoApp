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
package com.ejie.x21a.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ejie.x21a.model.Comarca;
import com.ejie.x38.dto.TableRequestDto;
import com.ejie.x38.dto.TableResponseDto;
import com.ejie.x38.dto.TableRowDto;

/**
 * ComarcaService generated by UDA, 14-ago-2012 12:59:39.
 * @author UDA
 */

public interface ComarcaService {

	/**
	 * Inserts a single row in the Comarca table.
	 *
	 * @param comarca Comarca
	 * @return Comarca
	 */
    Comarca add(Comarca comarca);

	/**
	 * Updates a single row in the Comarca table.
	 *
	 * @param comarca Comarca
	 * @return Comarca
	 */
	Comarca update(Comarca comarca);

	/**
	 * Finds a single row in the Comarca table.
	 *
	 * @param comarca Comarca
	 * @return Comarca
	 */
	Comarca find(Comarca comarca);

	/**
	 * Finds a List of rows in the Comarca table.
	 *
	 * @param comarca Comarca
	 * @param tableRequestDto TableRequestDto
	 * @return List
	 */
	List<Comarca> findAll(Comarca comarca, TableRequestDto tableRequestDto);

	/**
	 * Counts rows in the Comarca table.
	 *
	 * @param comarca Comarca
	 * @return Long
	 */
	Long findAllCount(Comarca comarca);
	
	/**
	 * Finds rows in the Comarca table using like.
	 *
	 * @param comarca Comarca
	 * @param tableRequestDto TableRequestDto
     * @param startsWith Boolean	 
	 * @return List
	 */
	List<Comarca> findAllLike(Comarca comarca, TableRequestDto tableRequestDto, Boolean startsWith);

	/**
	 * Counts rows in the Comarca table using like.
	 *
	 * @param comarca Comarca
     * @param startsWith Boolean	 
	 * @return Long
	 */
	Long findAllLikeCount(Comarca comarca, Boolean startsWith) ;
  
	/**
	 * Deletes a single row in the Comarca table.
	 *
	 * @param comarca Comarca
	 * @return 
	 */
	void remove(Comarca comarca);
	
	/*
	 * OPERACIONES RUP_TABLE
	 */

	/**
	 * Deletes multiple rows in the Comarca table.
	 *
	 * @param filterComarca Comarca
	 * @param tableRequestDto TableRequestDto
	 * @param startsWith Boolean	 
	 */
	void removeMultiple(Comarca filterComarca, TableRequestDto tableRequestDto, Boolean startsWith);
	
	/**
	 * Finds a List of rows in the Comarca table via inverse select.
	 *
	 * @param filterComarca Comarca
	 * @param tableRequestDto TableRequestDto
	 * @param startsWith Boolean
	 */	
	List<Comarca> getMultiple(Comarca filterComarca, TableRequestDto tableRequestDto, Boolean startsWith);
	
	/**
	 * Searches in the Comarca table.
	 *
	 * @param filterComarca Comarca
	 * @param searchComarca Comarca
	 * @param tableRequestDto TableRequestDto
	 * @param startsWith Boolean
	 */	
	List<TableRowDto<Comarca>> search(Comarca filterComarca, Comarca searchComarca, TableRequestDto tableRequestDto, Boolean startsWith);

	TableResponseDto<Comarca> filter(Comarca comarca, TableRequestDto tableRequestDto, Boolean startsWith) ;
	
	Object reorderSelection(Comarca comarca, TableRequestDto tableRequestDto, Boolean startsWith);
	
	/*
	 * EXPORTACIONES DE DATOS
	 */
	
	/**
	 * Devuelve los datos recuperados de la DB.
	 *
	 * @param filterComarca Comarca
	 * @param tableRequestDto TableRequestDto
	 */
	public List<Comarca> getDataForReports(Comarca filterComarca, TableRequestDto tableRequestDto);
	
	/**
	 * Devuelve un fichero en el formato deseado que contiene los datos exportados de la tabla.
	 *
	 * @param filterComarca Comarca
	 * @param columns String[]
	 * @param columnsName String[]
	 * @param fileName String
	 * @param sheetTitle String
	 * @param reportsParams ArrayList<?>
	 * @param tableRequestDto TableRequestDto
	 * @param locale Locale
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 */
	public void generateReport(Comarca filterComarca, String[] columns, String[] columnsName, String fileName, String sheetTitle, ArrayList<?> reportsParams, TableRequestDto tableRequestDto, Locale locale, HttpServletRequest request, HttpServletResponse response);
}
