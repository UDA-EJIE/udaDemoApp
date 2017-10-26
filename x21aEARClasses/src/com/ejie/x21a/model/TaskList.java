package com.ejie.x21a.model;

public class TaskList {
	
	private Integer id;
	private String name;
	private String userId;
	private String description;
	
	
	private Integer taskNum;
	
	
	
	
	public TaskList() {
		super();
	}

	public TaskList(Integer id) {
		super();
		this.id = id;
	}


	public TaskList(Integer id, String name, String description, String userId) {
		super();
		this.id = id;
		this.name = name;
		this.userId = userId;
		this.description = description;
	}

	public TaskList(Integer id, String name, String description, String userId, Integer taskNum) {
		super();
		this.id = id;
		this.name = name;
		this.userId = userId;
		this.description = description;
		this.taskNum = taskNum;
	}


	public Integer getId() {
		return id;
	}




	public void setId(Integer id) {
		this.id = id;
	}




	public String getName() {
		return name;
	}




	public void setName(String name) {
		this.name = name;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getTaskNum() {
		return taskNum;
	}

	public void setTaskNum(Integer taskNum) {
		this.taskNum = taskNum;
	}
	
	
	
	
	

}
