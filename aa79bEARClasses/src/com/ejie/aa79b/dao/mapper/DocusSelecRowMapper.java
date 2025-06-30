package com.ejie.aa79b.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ejie.aa79b.model.BitacoraExpediente;
import com.ejie.aa79b.model.DocumentosExpediente;
import com.ejie.aa79b.model.DocusSelecSub;
import com.ejie.aa79b.model.Expediente;
import com.ejie.aa79b.model.SubsanacionExpediente;

public class DocusSelecRowMapper implements RowMapper<Expediente>{

	@Override
	public Expediente mapRow(ResultSet resultSet, int arg1) throws SQLException {
		 DocumentosExpediente docuExp = new DocumentosExpediente();
         docuExp.setIdDoc(resultSet.getBigDecimal("IDDOC056"));
         docuExp.setAnyo(resultSet.getLong("ANYO051"));
         docuExp.setNumExp(resultSet.getInt("NUMEXP051"));
         DocusSelecSub docusSelSub = new DocusSelecSub();
         docusSelSub.setId066(resultSet.getLong("ID066"));
         docusSelSub.setIddoc066(resultSet.getLong("IDDOC066"));
         docusSelSub.setDocumentosExpediente(docuExp);
         SubsanacionExpediente subExp = new SubsanacionExpediente();
         subExp.setId(resultSet.getLong("ID064"));
         subExp.setDocuSelecSub(docusSelSub);
         BitacoraExpediente bitacora = new BitacoraExpediente();
         bitacora.setSubsanacionExp(subExp);
         Expediente expediente = new Expediente();
         expediente.setAnyo(resultSet.getLong("ANYO051"));
         expediente.setNumExp(resultSet.getInt("NUMEXP051"));
         expediente.setBitacoraExpediente(bitacora);
         return expediente;
	}

}
