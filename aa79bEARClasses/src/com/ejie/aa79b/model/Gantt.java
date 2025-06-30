package com.ejie.aa79b.model;

import java.io.Serializable;
import java.util.List;

public class Gantt implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;
    private String desc;
    private List<GanttValues> values;
    
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public List<GanttValues> getValues() {
		return values;
	}
	public void setValues(List<GanttValues> listaValues) {
		this.values = listaValues;
	}
	
	@Override
	public String toString() {
		return "Gantt [name=" + name + ", desc=" + desc + ", values=" + values + "]";
	}
    
    
}
