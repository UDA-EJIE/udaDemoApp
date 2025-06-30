package com.ejie.aa79b.utils;

import com.ejie.x38.dto.JQGridManager;
import com.ejie.x38.dto.JQGridRequestDto;

public class JQGridManagerCustom extends JQGridManager {

	private static final long serialVersionUID = 1L;

	public static <T> StringBuilder getPaginationQuery(JQGridRequestDto pagination, StringBuilder query) {
		return getPaginationQuery(pagination, query, false);
	}

	public static <T> StringBuilder getPaginationQuery(JQGridRequestDto pagination, StringBuilder query,
			boolean isJerarquia) {
		query.append(getOrderBy(pagination, isJerarquia));
		StringBuilder paginationQuery = new StringBuilder();
		paginationQuery.append(query);

		Long page = pagination.getPage();
		Long rows = pagination.getRows();

		if (page != null) {
			// página inicial
			paginationQuery.append(" offset ").append(String.valueOf(rows.longValue() * (page.longValue() - 1L)))
					.append(" row ");
		}
		if (rows != null) {
			// número de registros a recuperar por página
			paginationQuery.append(" fetch first ").append(rows.longValue()).append(" rows only ");
		}

		return paginationQuery;
	}
}
