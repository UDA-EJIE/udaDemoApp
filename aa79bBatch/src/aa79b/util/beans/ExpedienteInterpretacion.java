package aa79b.util.beans;

import java.io.Serializable;
import java.util.Date;

/**
 * @author mrodriguez
 *
 */
public class ExpedienteInterpretacion implements Serializable {
	
	private static final long serialVersionUID = -7140278604112265175L;
	
	private Date fechaIni;
    private Date fechaFin;
    private String horaIni;
    private String horaFin;
    
	/**
	 * @return the fechaIni
	 */
	public Date getFechaIni() {
		return this.fechaIni;
	}
	/**
	 * @param fechaIni the fechaIni to set
	 */
	public void setFechaIni(Date fechaIni) {
		this.fechaIni = fechaIni;
	}
	/**
	 * @return the fechaFin
	 */
	public Date getFechaFin() {
		return this.fechaFin;
	}
	/**
	 * @param fechaFin the fechaFin to set
	 */
	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}
	/**
	 * @return the horaIni
	 */
	public String getHoraIni() {
		return this.horaIni;
	}
	/**
	 * @param horaIni the horaIni to set
	 */
	public void setHoraIni(String horaIni) {
		this.horaIni = horaIni;
	}
	/**
	 * @return the horaFin
	 */
	public String getHoraFin() {
		return this.horaFin;
	}
	/**
	 * @param horaFin the horaFin to set
	 */
	public void setHoraFin(String horaFin) {
		this.horaFin = horaFin;
	}

}
