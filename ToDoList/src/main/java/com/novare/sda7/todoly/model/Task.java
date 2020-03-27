package com.novare.sda7.todoly.model;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * This is a model of a task for the To Do List.
 * @author denisemuniz
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "name", "duedate", "project", "isdone" })
public class Task {
	@JsonProperty("name")
	private String name;
	@JsonProperty("duedate")
	private Date dueDate;
	@JsonProperty("project")
	private String project;
	@JsonProperty("isdone")
	private boolean isDone;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	public Task(String name, Date date, String project, Boolean done) {
		this.name = name;
		this.dueDate = date;
		this.project = project;
		this.isDone = done;
	}
	
	public Task() {
		
	}


	@JsonProperty("name")
	public String getName() {
		return name;
	}

	@JsonProperty("name")
	public void setName(String name) {
		this.name = name;
	}

	@JsonProperty("duedate")
	public Date getDueDate() {
		return dueDate;
	}

	@JsonProperty("duedate")
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	@JsonProperty("project")
	public String getProject() {
		return project;
	}

	@JsonProperty("project")
	public void setProject(String project) {
		this.project = project;
	}

	@JsonProperty("isdone")
	public Boolean getIsDone() {
		return isDone;
	}

	@JsonProperty("isdone")
	public void setIsDone(Boolean done) {
		this.isDone = done;
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

		String strDate = dateFormat.format(getDueDate());

		return getName() + "|" + strDate + "|" + getProject() + "|" + getIsDone();
	}

}
