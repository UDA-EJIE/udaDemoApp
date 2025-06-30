package com.ejie.aa79b.model;

import java.io.Serializable;
import java.util.Date;

import com.ejie.x38.serialization.JsonDateDeserializer;
import com.ejie.x38.serialization.JsonDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class GanttValues implements Serializable {

    private static final long serialVersionUID = 1L;

    private Date from;
    private Date to;
    private String label;
    private String desc;
    private String customClass;
    private String dataObj;
    
    @JsonSerialize(using = JsonDateSerializer.class)
	public Date getFrom() {
		return from;
	}
    @JsonDeserialize(using = JsonDateDeserializer.class)
	public void setFrom(Date from) {
		this.from = from;
	}
	@JsonSerialize(using = JsonDateSerializer.class)
	public Date getTo() {
		return to;
	}
	@JsonDeserialize(using = JsonDateDeserializer.class)
	public void setTo(Date to) {
		this.to = to;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getCustomClass() {
		return customClass;
	}
	public void setCustomClass(String customClass) {
		this.customClass = customClass;
	}
	public String getDataObj() {
		return dataObj;
	}
	public void setDataObj(String dataObj) {
		this.dataObj = dataObj;
	}
	
	@Override
	public String toString() {
		return "GanttValues [from=" + from + ", to=" + to + ", label=" + label + ", desc=" + desc + ", customClass="
				+ customClass + "]";
	}
    
    
}
