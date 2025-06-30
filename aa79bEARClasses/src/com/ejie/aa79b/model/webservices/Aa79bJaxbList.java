package com.ejie.aa79b.model.webservices;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author eaguirresarobe
 *
 * @param <T>
 */
@XmlRootElement(name = "mylist")
public class Aa79bJaxbList<T>  {

	@XmlElementWrapper(name = "list")
	@XmlElement(name = "Item")
	protected List<T> list;

	/**
	 *
	 */
	public Aa79bJaxbList() {
		//Constructor
	}

	/**
	 * @param list List<T>
	 */
	public Aa79bJaxbList(List<T> list) {
		this.list = list;
	}

	/**
	 * @return List<T>
	 */
	public List<T> getList() {
		return this.list;
	}
	
}
