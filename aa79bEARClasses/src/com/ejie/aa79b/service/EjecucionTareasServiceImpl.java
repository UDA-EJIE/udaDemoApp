package com.ejie.aa79b.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ejie.aa79b.dao.EjecucionTareasDao;
import com.ejie.aa79b.dao.GenericoDao;
import com.ejie.aa79b.model.EjecucionTareas;

@Service(value = "ejecucionTareasService")
public class EjecucionTareasServiceImpl extends GenericoServiceImpl<EjecucionTareas> implements EjecucionTareasService {
    @Autowired
    private EjecucionTareasDao ejecucionTareas;

    @Override
    protected GenericoDao<EjecucionTareas> getDao() {
        return this.ejecucionTareas;
    }

}
