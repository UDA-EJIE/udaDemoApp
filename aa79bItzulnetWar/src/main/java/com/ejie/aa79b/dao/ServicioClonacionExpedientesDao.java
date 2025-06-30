package com.ejie.aa79b.dao;

import com.ejie.aa79b.model.Clonacion;

/**
 * 
 * @author javarona
 */

public interface ServicioClonacionExpedientesDao extends GenericoDao<Clonacion> {

	Clonacion getNuevoNumExp(Clonacion clonacion);
}