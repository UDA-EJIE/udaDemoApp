package com.ejie.aa79b.model.pdf.content;

import java.util.List;

import com.ejie.aa79b.common.Reports;
import com.ejie.aa79b.common.exceptions.AppRuntimeException;
import com.ejie.x38.json.JSONArray;
import com.ejie.x38.json.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;

public class PdfTableJson extends PdfTable {

	@Override
	public void addContenidoTabla(String columns, List<?> resultadoBusqueda) {
		JSONArray jsonArr = new JSONArray(columns);
		String align = "";

		ObjectMapper om = new ObjectMapper();
		Integer i = 0;
		for (Object object : resultadoBusqueda) {
			this.addFila(i);
			JSONObject bean;
			try {
				bean = new JSONObject(om.writeValueAsString(object));
			} catch (Exception e) {
				throw new AppRuntimeException(e);
			}
			for (Integer j = 0; j < jsonArr.length(); ++j) {
				JSONArray column = (JSONArray) jsonArr.get(j);
				String propertie = (String) column.get(0);
				if (column.length() != 3) {
					align = (String) column.get(3);
				} else {
					align = "center";
				}
				Object valor = Reports.getValor(bean, propertie);
				String contenido = "";
				if (valor != null && !valor.toString().isEmpty()) {
					contenido = valor.toString().replaceAll("<br />", "\n");
					contenido = contenido.toString().replaceAll("\\<[^>]*>", "");
				}

				this.filas.get(String.valueOf(i)).add(contenido);
				this.aligns.get(String.valueOf(i)).add(align);
			}
			for (String propertie : this.columnasAdicionales) {
				Object valor = Reports.getValor(bean, propertie);
				String contenido = valor == null ? "" : valor.toString().replaceAll("\\<[^>]*>", "");
				this.filas.get(String.valueOf(i)).add(contenido);
				this.aligns.get(String.valueOf(i)).add(align);
			}
			i++;
		}

	}

}
