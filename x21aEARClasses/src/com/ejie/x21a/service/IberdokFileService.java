package com.ejie.x21a.service;

import java.util.List;

import com.ejie.x21a.model.IberdokFile;
import com.ejie.x38.dto.TableRequestDto;
import com.ejie.x38.dto.TableResourceResponseDto;
import com.ejie.x38.dto.TableRowDto;

public interface IberdokFileService {

	/*
	 * OPERACIONES CRUD
	 */
	
	/**
	 * Inserts a single row in the IberdokFile table.
	 *
	 * @param file IberdokFile
	 * @return IberdokFile
	 */
    IberdokFile add(IberdokFile file);

	/**
	 * Updates a single row in the IberdokFile table.
	 *
	 * @param file IberdokFile
	 * @return IberdokFile
	 */
	IberdokFile update(IberdokFile file);
	
	public IberdokFile updateIdDocumento(IberdokFile file);
	
	public IberdokFile updateLastRecord(IberdokFile file);

	/**
	 * Finds a single row in the IberdokFile table.
	 *
	 * @param file IberdokFile
	 * @return IberdokFile
	 */
	IberdokFile find(IberdokFile file);

	
	/**
	 * Checks if a single row in the IberdokFile table exits.
	 *
	 * @param file IberdokFile
	 * @return true if exists
	 */
	String existsFile(IberdokFile file);
	
	
	/**
	 * Deletes a single row in the IberdokFile table.
	 *
	 * @param file IberdokFile
	 * @return 
	 */
	void remove(IberdokFile file);
	
	/**
	 * Finds a List of rows in the IberdokFile table.
	 *
	 * @param file IberdokFile
	 * @param tableRequestDto TableRequestDto
	 * @return List
	 */
	List<IberdokFile> findAll(IberdokFile file, TableRequestDto tableRequestDto);

	/**
	 * Finds rows in the IberdokFile table using like.
	 *
	 * @param file IberdokFile
	 * @param tableRequestDto TableRequestDto
     * @param startsWith Boolean	 
	 * @return List
	 */
	List<IberdokFile> findAllLike(IberdokFile file, TableRequestDto tableRequestDto, Boolean startsWith) ;
	
	
	/*
	 * OPERACIONES RUP_TABLE
	 */

	/**
	 * Deletes multiple rows in the IberdokFile table.
	 *
	 * @param filterIberdokFile IberdokFile
	 * @param tableRequestDto TableRequestDto
	 * @param startsWith Boolean
	 */	
	void removeMultiple(IberdokFile filterIberdokFile, TableRequestDto tableRequestDto, Boolean startsWith);
	
	/**
	 * Searches in the IberdokFile table.
	 *
	 * @param filterIberdokFile IberdokFile
	 * @param searchIberdokFile IberdokFile
	 * @param tableRequestDto TableRequestDto
	 * @param startsWith Boolean
	 */	
	List<TableRowDto<IberdokFile>> search(IberdokFile filterIberdokFile, IberdokFile searchIberdokFile, TableRequestDto tableRequestDto, Boolean startsWith);

	TableResourceResponseDto<IberdokFile> filter(IberdokFile file, TableRequestDto tableRequestDto, Boolean startsWith);
	
	Object reorderSelection(IberdokFile file, TableRequestDto tableRequestDto, Boolean startsWith);
	
	IberdokFile findLastByIdCorrelacion(String idModelo, String idCorrelacion);
}
