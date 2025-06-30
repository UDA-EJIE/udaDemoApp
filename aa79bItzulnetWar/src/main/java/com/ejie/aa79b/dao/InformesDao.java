package com.ejie.aa79b.dao;

import java.util.List;

import com.ejie.aa79b.model.Informe;
import com.ejie.aa79b.model.InformeInterpretacion;
import com.ejie.aa79b.model.InformeTradRev;

public interface InformesDao {

	List<InformeTradRev> informeTradRev(Informe bean);

	List<InformeInterpretacion> informeInterpretacion(Informe bean);
}
