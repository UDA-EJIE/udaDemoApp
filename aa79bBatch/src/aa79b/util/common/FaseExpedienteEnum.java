package aa79b.util.common;

import java.io.Serializable;

/**
 * 
 * @author grozadilla
 *
 */
public enum FaseExpedienteEnum implements Serializable {
	ALTA(1), PENDIENTE_ESTUDIO(2), ESTUDIO_EXPEDIENTE(3), PDTE_TRAM_SUBSANACION(4), PDTE_ASIG_TAREAS(
			5), PDTE_PROYECTO_TRADOS(6), PDTE_ACEPT_TAREAS(7), PDTE_TRAM_CLIENTE(8), PDTE_EJECT_TAREAS(
					9), PDTE_REV_FACTURACION(10), RECHAZADO(11), ANULADO(12), CERRADO(13), FINALIZADO(14);

	private int value;

	private FaseExpedienteEnum(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

}