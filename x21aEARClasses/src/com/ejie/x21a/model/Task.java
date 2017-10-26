package com.ejie.x21a.model;

public class Task {

	
	private Integer id;
	private Integer idList;
	private TaskList taskList;
	private String name;
	private String detail;
	private Boolean done;
	public Task() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Task(Integer id) {
		super();
		this.id = id;
	}
	public Task(Integer id, TaskList taskList, String name, String detail, Boolean done) {
		super();
		this.id = id;
		this.taskList = taskList;
		this.name = name;
		this.detail = detail;
		this.done = done;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public TaskList getList() {
		return taskList;
	}
	public void setList(TaskList list) {
		this.taskList = list;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public Boolean getDone() {
		return done;
	}
	public void setDone(Boolean done) {
		this.done = done;
	}
	public Integer getIdList() {
		return idList;
	}
	public void setIdList(Integer idList) {
		this.idList = idList;
	}
	
	
	
	
}
