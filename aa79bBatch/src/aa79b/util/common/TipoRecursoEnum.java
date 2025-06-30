package aa79b.util.common;

import java.io.Serializable;

/**
 *
 * @author grozadilla
 *
 */
public enum TipoRecursoEnum implements Serializable {
	INTERNO("I"), EXTERNO("P");

	private String value;

	private TipoRecursoEnum(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

}