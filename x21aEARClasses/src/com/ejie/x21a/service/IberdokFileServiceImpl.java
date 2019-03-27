package com.ejie.x21a.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ejie.x21a.dao.IberdokFileDao;
import com.ejie.x21a.model.IberdokFile;
import com.ejie.x38.dto.JQGridRequestDto;
import com.ejie.x38.dto.JQGridResponseDto;
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
	 * @param pagination
	 *            Pagination
	 * @return List
	 */
	public List<IberdokFile> findAll(IberdokFile file,
			JQGridRequestDto jqGridRequestDto) {
		return (List<IberdokFile>) this.iberdokDao.findAll(file,
				jqGridRequestDto);
	}

	/**
	 * Finds rows in the IberdokFile table using like.
	 *
	 * @param file
	 *            IberdokFile
	 * @param pagination
	 *            Pagination
	 * @param startsWith
	 *            Boolean
	 * @return List
	 */
	public List<IberdokFile> findAllLike(IberdokFile file,
			JQGridRequestDto jqGridRequestDto, Boolean startsWith) {
		return (List<IberdokFile>) this.iberdokDao.findAllLike(file,
				jqGridRequestDto, startsWith);
	}

	/*
	 * OPERACIONES RUP_TABLE
	 */

	/**
	 * Deletes multiple rows in the IberdokFile table.
	 *
	 * @param fileList
	 *            List
	 * @return
	 */
	@Transactional(rollbackFor = Throwable.class)
	public void removeMultiple(IberdokFile filterFile,
			JQGridRequestDto jqGridRequestDto, Boolean startsWith) {
		this.iberdokDao.removeMultiple(filterFile, jqGridRequestDto,
				startsWith);
	}

	@Override
	public Object reorderSelection(IberdokFile file,
			JQGridRequestDto jqGridRequestDto, Boolean startsWith) {
		return this.iberdokDao.reorderSelection(file,
				jqGridRequestDto, startsWith);
	}

	@Override
	public List<TableRowDto<IberdokFile>> search(IberdokFile filterParams,
			IberdokFile searchParams, JQGridRequestDto jqGridRequestDto,
			Boolean startsWith) {
		return this.iberdokDao.search(filterParams, searchParams,
				jqGridRequestDto, startsWith);
	}

	public JQGridResponseDto<IberdokFile> filter(IberdokFile filterFile,
			JQGridRequestDto jqGridRequestDto, Boolean startsWith) {
		List<IberdokFile> listaIberdokFile = this.iberdokDao.findAllLike(
				filterFile, jqGridRequestDto, false);
		Long recordNum = this.iberdokDao.findAllLikeCount(
				filterFile != null ? filterFile : new IberdokFile(), false);
		if (jqGridRequestDto.getMultiselection().getSelectedIds() != null) {
			List<TableRowDto<IberdokFile>> reorderSelection = this.iberdokDao
					.reorderSelection(filterFile, jqGridRequestDto,
							startsWith);
			return new JQGridResponseDto<IberdokFile>(jqGridRequestDto, recordNum,
					listaIberdokFile, reorderSelection);
		}
		return new JQGridResponseDto<IberdokFile>(jqGridRequestDto, recordNum,
				listaIberdokFile);
	}

	@Override
	public Boolean existsFile(IberdokFile file) {
		IberdokFile fileExists= (IberdokFile) this.iberdokDao.find(file);
		Boolean resultado;
		if (fileExists!=null){
			resultado=true; 
		}else{
			resultado=false;
		}
		return resultado;
	}

}
