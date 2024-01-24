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

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.ejie.x21a.dao.TableUsuarioDao;
import com.ejie.x21a.model.Usuario;
import com.ejie.x38.dto.TableRequestDto;

@Repository
@Service(value = "cacheService")
public class CacheServiceImpl implements CacheService {

	@Autowired
	private TableUsuarioDao tableUsuarioDao;
	
	/**
	 * Finds a single row in the Usuario table.
	 *
	 * @param usuario Usuario
	 * @return Usuario
	 */
	public Usuario find(Usuario usuario) {
		return (Usuario) this.tableUsuarioDao.find(usuario);
	}	
	
	@Cacheable(value = "listaUsuarios", key = "#cache")
	public List<Usuario> getAll(Usuario usuario, TableRequestDto tableRequestDto, Boolean cache) {
		return this.tableUsuarioDao.findAll(usuario, tableRequestDto);
	}

	@Override
	@CacheEvict(value = "listaUsuarios", key = "#cache" )
	public Usuario update(Usuario usuario, Boolean cache) {
		return tableUsuarioDao.update(usuario);
	}	
}
