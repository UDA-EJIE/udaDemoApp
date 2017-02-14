package com.ejie.x21a.service;

import java.util.List;

import com.ejie.x21a.model.IberdokFile;
import com.ejie.x38.dto.JQGridRequestDto;
import com.ejie.x38.dto.JQGridResponseDto;
import com.ejie.x38.dto.TableRowDto;
import com.ejie.x38.rss.RssContent;

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
	Boolean existsFile(IberdokFile file);
	
	
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
	 * @param pagination Pagination
	 * @return List
	 */
	List<IberdokFile> findAll(IberdokFile file, JQGridRequestDto jqGridRequestDto);

	/**
	 * Finds rows in the IberdokFile table using like.
	 *
	 * @param file IberdokFile
	 * @param jqGridRequestDto JQGridRequestDto
     * @param startsWith Boolean	 
	 * @return List
	 */
	List<IberdokFile> findAllLike(IberdokFile file, JQGridRequestDto jqGridRequestDto, Boolean startsWith) ;
	
	
	/*
	 * OPERACIONES RUP_TABLE
	 */

	/**
	 * Deletes multiple rows in the IberdokFile table.
	 *
	 * @param filterIberdokFile IberdokFile
	 * @param jqGridRequestDto JQGridRequestDto
	 * @param startsWith Boolean
	 */	
	void removeMultiple(IberdokFile filterIberdokFile, JQGridRequestDto jqGridRequestDto, Boolean startsWith);
	
	/**
	 * Searches in the IberdokFile table.
	 *
	 * @param filterIberdokFile IberdokFile
	 * @param searchIberdokFile IberdokFile
	 * @param jqGridRequestDto JQGridRequestDto
	 * @param startsWith Boolean
	 */	
	List<TableRowDto<IberdokFile>> search(IberdokFile filterIberdokFile, IberdokFile searchIberdokFile, JQGridRequestDto jqGridRequestDto, Boolean startsWith);

	JQGridResponseDto<IberdokFile> filter(IberdokFile file, JQGridRequestDto jqGridRequestDto, Boolean startsWith) ;
	
	Object reorderSelection(IberdokFile file, JQGridRequestDto jqGridRequestDto, Boolean startsWith);


	
	/*
	 * RSS
	 */
	List<RssContent> getRssFeed();
}
