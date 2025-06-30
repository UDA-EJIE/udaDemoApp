package com.ejie.aa79b.service;

import java.util.List;

import com.ejie.aa79b.model.ConsultaFacturacionExpediente;
import com.ejie.aa79b.model.Entidad;
import com.ejie.aa79b.model.Solicitante;

public interface EmisionFacturasService extends GenericoService<ConsultaFacturacionExpediente> {

	List<Entidad> getEntidadesSolicitantes(Entidad entidad);

	List<Solicitante> getContactosSolicitantes(Solicitante contacto);
	
	List<Entidad> getEntidadesFacturar(Entidad entidad);

	List<Solicitante> getContactosFacturar(Solicitante contacto);

}
