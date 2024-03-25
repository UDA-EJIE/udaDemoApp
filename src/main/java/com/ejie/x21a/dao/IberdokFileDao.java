package com.ejie.x21a.dao;

import java.util.List;

import com.ejie.x21a.model.IberdokFile;
import com.ejie.x38.dto.TableRequestDto;
import com.ejie.x38.dto.TableRowDto;

public interface IberdokFileDao {
	
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
	List<IberdokFile> findAllLike(IberdokFile file, TableRequestDto tableRequestDto, Boolean startsWith);
	
	
	/*
	 * OPERACIONES RUP_TABLE
	 */
	
	/**
	 * Deletes multiple rows in the IberdokFile table.
	 *
	 * @param filterIberdokFile
	 *            IberdokFile
	 * @param tableRequestDto
	 *            TableRequestDto
	 * @param startsWith
	 *            Boolean
	 */
	void removeMultiple(IberdokFile filterIberdokFile, TableRequestDto tableRequestDto, Boolean startsWith);
	
    /**
     * Counts rows in the IberdokFile table using like.
     *
     * @param file IberdokFile
     * @param startsWith Boolean
     * @return Long
     */
    Long findAllLikeCount(IberdokFile file, Boolean startsWith);
    
    /**
     * Counts rows in the IberdokFile table.
     *
     * @param file IberdokFile
     * @return Long
     */
    Long findAllCount(IberdokFile file);
    
    List<TableRowDto<IberdokFile>> reorderSelection(IberdokFile file, TableRequestDto tableRequestDto, Boolean startsWith);
    
    List<TableRowDto<IberdokFile>> search(IberdokFile filterParams, IberdokFile searchParams, TableRequestDto tableRequestDto, Boolean startsWith);

	IberdokFile findLastByIdCorrelacion(String idModelo, String idCorrelacion);
    
}

