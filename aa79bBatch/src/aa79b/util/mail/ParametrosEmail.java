package aa79b.util.mail;

import java.util.LinkedHashMap;
import java.util.Map;

public class ParametrosEmail implements java.io.Serializable {

	private static final long serialVersionUID = -821040244549232064L;

	private Map<String, String> infoEu = new LinkedHashMap<String, String>();
	private Map<String, String> infoEs = new LinkedHashMap<String, String>();

	private String mensajeEu = "";
	private String mensajeEs = "";

	public String getMensajeEu() {
		return this.mensajeEu;
	}

	public void setMensajeEu(String mensajeEu) {
		this.mensajeEu = mensajeEu;
	}

	public String getMensajeEs() {
		return this.mensajeEs;
	}

	public void setMensajeEs(String mensajeEs) {
		this.mensajeEs = mensajeEs;
	}



	public Map<String, String> getInfoEu() {
		return this.infoEu;
	}

	public void setInfoEu(Map<String, String> infoEu) {
		this.infoEu = infoEu;
	}

	public Map<String, String> getInfoEs() {
		return this.infoEs;
	}

	public void setInfoEs(Map<String, String> infoEs) {
		this.infoEs = infoEs;
	}}
