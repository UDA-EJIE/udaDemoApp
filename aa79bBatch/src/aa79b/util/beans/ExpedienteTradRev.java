package aa79b.util.beans;

import java.io.Serializable;
import java.util.Date;

public class ExpedienteTradRev implements Serializable {
	
	private static final long serialVersionUID = 7665391714667476772L;
	
	private Date fechaFinalIzo;
	private String horaFinalIzo;
	private Date fechaFinalSolic;
	private String horaFinalSolic;
	
	/**
	 * @return the fechaFinalIzo
	 */
	public Date getFechaFinalIzo() {
		return this.fechaFinalIzo;
	}
	/**
	 * @param fechaFinalIzo the fechaFinalIzo to set
	 */
	public void setFechaFinalIzo(Date fechaFinalIzo) {
		this.fechaFinalIzo = fechaFinalIzo;
	}
	/**
	 * @return the horaFinalIzo
	 */
	public String getHoraFinalIzo() {
		return this.horaFinalIzo;
	}
	/**
	 * @param horaFinalIzo the horaFinalIzo to set
	 */
	public void setHoraFinalIzo(String horaFinalIzo) {
		this.horaFinalIzo = horaFinalIzo;
	}
	/**
	 * @return the fechaFinalSolic
	 */
	public Date getFechaFinalSolic() {
		return this.fechaFinalSolic;
	}
	/**
	 * @param fechaFinalSolic the fechaFinalSolic to set
	 */
	public void setFechaFinalSolic(Date fechaFinalSolic) {
		this.fechaFinalSolic = fechaFinalSolic;
	}
	/**
	 * @return the horaFinalSolic
	 */
	public String getHoraFinalSolic() {
		return this.horaFinalSolic;
	}
	/**
	 * @param horaFinalSolic the horaFinalSolic to set
	 */
	public void setHoraFinalSolic(String horaFinalSolic) {
		this.horaFinalSolic = horaFinalSolic;
	}
	
}
