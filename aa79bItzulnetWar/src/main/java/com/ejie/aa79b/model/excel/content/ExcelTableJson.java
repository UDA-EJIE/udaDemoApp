package com.ejie.aa79b.model.excel.content;

import java.util.List;

import com.ejie.aa79b.common.Reports;
import com.ejie.aa79b.common.exceptions.AppRuntimeException;
import com.ejie.x38.json.JSONArray;
import com.ejie.x38.json.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ExcelTableJson extends ExcelTable {

	public ExcelTableJson(int posicionX, int posicionY, boolean celdaActiva) {
		super(posicionX, posicionY, celdaActiva);
	}

	@Override
	public void addContenidoTabla(String columns, List<?> resultadoBusqueda) {
		JSONArray jsonArr = new JSONArray(columns);
		String align = "";
		Boolean isNumber = false;
		Boolean isDate = false;

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

				if (column.length() == 6) {
					isNumber = (Boolean) column.get(4);
					isDate = (Boolean) column.get(5);
				} else {
					isNumber = false;
					isDate = false;
				}

				Object valor = Reports.getValor(bean, propertie);
				String contenido = "";
				if (valor != null && !valor.toString().isEmpty()) {
					contenido = valor.toString().replaceAll("<br />", "\n");
					contenido = contenido.toString().replaceAll("\\<[^>]*>", "");
				}
				this.filas.get(String.valueOf(i)).add(contenido);
				this.aligns.get(String.valueOf(i)).add(align);
				this.numberFormat.get(String.valueOf(i)).add(isNumber);
				this.dateFormat.get(String.valueOf(i)).add(isDate);
			}
			for (String propertie : this.columnasAdicionales) {
				Object valor = Reports.getValor(bean, propertie);
				String contenido = valor == null ? "" : valor.toString().replaceAll("\\<[^>]*>", "");
				this.filas.get(String.valueOf(i)).add(contenido);
				this.aligns.get(String.valueOf(i)).add(align);
				this.numberFormat.get(String.valueOf(i)).add(isNumber);
				this.dateFormat.get(String.valueOf(i)).add(isDate);
			}
			i++;
		}
	}
}
