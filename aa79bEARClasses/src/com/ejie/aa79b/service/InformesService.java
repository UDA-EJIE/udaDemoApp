package com.ejie.aa79b.service;

import java.util.Locale;

import com.ejie.aa79b.model.Fichero;
import com.ejie.aa79b.model.Informe;

public interface InformesService {

	Fichero exportarResultadoCurso(Informe bean, Locale locale) throws Exception;
}
