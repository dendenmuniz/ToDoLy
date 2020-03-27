package com.novare.sda7.todoly.repository;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.novare.sda7.todoly.model.ToDoList;

/**
 * 
 * <h2>ToDoLy Repository Class</h2>
 * <p>This class are used between the service layer and the model layer. That contains the code to read/write </p>
 * 
 * @author denisemuniz 
 * @version 1.0
 * @since 2020-03-06
 *
 */
public class ToDoLyRepository {

	private String fileName;
	private ObjectMapper mapper = new ObjectMapper();


	public ToDoLyRepository(String jsonFileName) {
		this.fileName = jsonFileName;
	} 

	/**
	 * This method read the Json file where the To Do list is saved when the user close the application.
	 * @return toDoList - object to hold and interact with the task list.
	 */
	private ToDoList readJsonFile() {

		File yourFile = new File(fileName);

		try {
			if (!yourFile.exists()) {
				return new ToDoList();
			}
			String value = Files.readString(yourFile.toPath());
			if (value.isBlank()) {
				return new ToDoList();
			}
			ToDoList toDoList = mapper.readValue(value, ToDoList.class);
			if(toDoList == null) {
				return new ToDoList();
			}
			return toDoList;
		} catch (IOException e) {
			throw new RuntimeException(e);

		}
	}

	/**
	 * This method is used to write the Json file that save the To Do list when the user close the application.
	 * @param toDoList - object to hold and interact with the task list.
	 * @throws IOException
	 */
	private void writeJsonFile(ToDoList toDoList) throws IOException {

		try {

		    mapper.writeValue(Paths.get(fileName).toFile(), toDoList);

		} catch (IOException e) {
			throw new RuntimeException(e);
		}

	}

	/**
	 * This method is called to read the Json file.
	 * @return - ToDoLy object.
	 * @throws IOException
	 */
	public ToDoList retrieveToDoList() throws IOException {
		return readJsonFile();
	}

	/**
	 * This method is called to save the Json file.
	 * @param toDoList - object to hold and interact with the task list.
	 * @throws IOException
	 */
	public void saveToDoList(ToDoList toDoList) throws IOException {
		writeJsonFile(toDoList);
	}

}
