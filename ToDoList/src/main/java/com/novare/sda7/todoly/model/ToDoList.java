package com.novare.sda7.todoly.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * This is a model for the To Do List.
 * @author denisemuniz
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "toDoList" })
public class ToDoList {

	@JsonProperty("tasks")
	private List<Task> tasks = new ArrayList<Task>();
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	public ToDoList() {
	}

	public ToDoList(List<Task> tasks) {
		this.tasks = tasks;
	}

	
	public List<Task> getTasks() {
		return tasks;
	}

	
	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

}