package com.ejie.x21a.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ejie.x21a.dao.IberdokFileDao;
import com.ejie.x21a.model.IberdokFile;
import com.ejie.x38.dto.TableRequestDto;
import com.ejie.x38.dto.TableResourceResponseDto;
import com.ejie.x38.dto.TableRowDto;

@Service(value = "iberdokFileService")
public class IberdokFileServiceImpl implements IberdokFileService {

	@Autowired
	private IberdokFileDao iberdokDao;

	/*
	 * OPERACIONES CRUD
	 */

	/**
	 * Inserts a single row in the IberdokFile table.
	 *
	 * @param file
	 *            IberdokFile
	 * @return IberdokFile
	 */
	@Transactional(rollbackFor = Throwable.class)
	public IberdokFile add(IberdokFile file) {
		return this.iberdokDao.add(file);
	}

	/**
	 * Updates a single row in the IberdokFile table.
	 *
	 * @param file
	 *            IberdokFile
	 * @return IberdokFile
	 */
	@Transactional(rollbackFor = Throwable.class)
	public IberdokFile update(IberdokFile file) {
		return this.iberdokDao.update(file);
	}
	
	public IberdokFile updateIdDocumento(IberdokFile file) {
		return this.iberdokDao.updateIdDocumento(file);
	}
	
	public IberdokFile updateLastRecord(IberdokFile file){
		return this.iberdokDao.updateLastRecord(file);
	}

	/**
	 * Finds a single row in the IberdokFile table.
	 *
	 * @param file
	 *            IberdokFile
	 * @return IberdokFile
	 */
	public IberdokFile find(IberdokFile file) {
		return (IberdokFile) this.iberdokDao.find(file);
	}

	/**
	 * Deletes a single row in the IberdokFile table.
	 *
	 * @param file
	 *            IberdokFile
	 * @return
	 */
	@Transactional(rollbackFor = Throwable.class)
	public void remove(IberdokFile file) {
		this.iberdokDao.remove(file);
	}

	/**
	 * Finds a List of rows in the IberdokFile table.
	 *
	 * @param file
	 *            IberdokFile
	 * @param tableRequestDto
	 *            TableRequestDto
	 * @return List
	 */
	public List<IberdokFile> findAll(IberdokFile file, TableRequestDto tableRequestDto) {
		return (List<IberdokFile>) this.iberdokDao.findAll(file, tableRequestDto);
	}

	/**
	 * Finds rows in the IberdokFile table using like.
	 *
	 * @param file
	 *            IberdokFile
	 * @param tableRequestDto
	 *            TableRequestDto
	 * @param startsWith
	 *            Boolean
	 * @return List
	 */
	public List<IberdokFile> findAllLike(IberdokFile file, TableRequestDto tableRequestDto, Boolean startsWith) {
		return (List<IberdokFile>) this.iberdokDao.findAllLike(file, tableRequestDto, startsWith);
	}

	/*
	 * OPERACIONES RUP_TABLE
	 */

	/**
	 * Deletes multiple rows in the IberdokFile table.
	 *
	 * @param filterFile
	 *            IberdokFile
	 * @param tableRequestDto
	 *            TableRequestDto
	 * @param startsWith
	 *            Boolean
	 */
	@Transactional(rollbackFor = Throwable.class)
	public void removeMultiple(IberdokFile filterFile, TableRequestDto tableRequestDto, Boolean startsWith) {
		this.iberdokDao.removeMultiple(filterFile, tableRequestDto, startsWith);
	}

	@Override
	public Object reorderSelection(IberdokFile file, TableRequestDto tableRequestDto, Boolean startsWith) {
		return this.iberdokDao.reorderSelection(file, tableRequestDto, startsWith);
	}

	@Override
	public List<TableRowDto<IberdokFile>> search(IberdokFile filterParams, IberdokFile searchParams, TableRequestDto tableRequestDto, Boolean startsWith) {
		return this.iberdokDao.search(filterParams, searchParams, tableRequestDto, startsWith);
	}
	
	@Override
	public TableResourceResponseDto<IberdokFile> filter(IberdokFile filterFile, TableRequestDto tableRequestDto, Boolean startsWith) {
		List<IberdokFile> listaIberdokFile =  this.iberdokDao.findAllLike(filterFile, tableRequestDto, false);
		Long recordNum = this.iberdokDao.findAllLikeCount(filterFile != null ? filterFile : new IberdokFile(), false);
		TableResourceResponseDto<IberdokFile> iberdokDto = new TableResourceResponseDto<IberdokFile>(tableRequestDto, recordNum, listaIberdokFile);
		if (tableRequestDto.getMultiselection().getSelectedIds() != null && !tableRequestDto.getMultiselection().getSelectedIds().isEmpty()) {
			List<TableRowDto<IberdokFile>> reorderSelection = this.iberdokDao.reorderSelection(filterFile, tableRequestDto, startsWith);
			iberdokDto.setReorderedSelection(reorderSelection);
			iberdokDto.addAdditionalParam("reorderedSelection", reorderSelection);
			iberdokDto.addAdditionalParam("selectedAll", tableRequestDto.getMultiselection().getSelectedAll());
		}
		if (tableRequestDto.getSeeker().getSelectedIds() != null) {
			tableRequestDto.setMultiselection(tableRequestDto.getSeeker());
			List<TableRowDto<IberdokFile>> reorderSeeker = this.iberdokDao.reorderSelection(filterFile, tableRequestDto, startsWith);
			iberdokDto.setReorderedSeeker(reorderSeeker);
			iberdokDto.addAdditionalParam("reorderedSeeker", reorderSeeker);
		}
		return iberdokDto; 
	}

	@Override
	public String existsFile(IberdokFile file) {
		IberdokFile fileExists= (IberdokFile) this.iberdokDao.find(file);
		String id = null;
		if (fileExists !=null ){
			id = fileExists.getId(); 
		}
		return id;
	}
	
	@Override
	public IberdokFile findLastByIdCorrelacion(String idModelo, String idCorrelacion) {
		return (IberdokFile) this.iberdokDao.findLastByIdCorrelacion(idModelo,idCorrelacion);
	}

}
