package com.ejie.aa79b.service;

import com.ejie.aa79b.model.ProcesoEmail;

public interface ProcesoEmailService extends GenericoService<ProcesoEmail> {

	void enviarMail(ProcesoEmail bean);

}
