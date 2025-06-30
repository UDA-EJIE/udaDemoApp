package com.ejie.aa79b.model;

import java.io.Serializable;

public class Perfil implements Serializable {
	
	private static final long serialVersionUID = -5938230806502914255L;
	private Integer id;
	private String desc;
	
	/**
	 * @return the id
	 */
	public Integer getId() {
		return this.id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * @return the desc
	 */
	public String getDesc() {
		return this.desc;
	}
	/**
	 * @param desc the desc to set
	 */
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
}
