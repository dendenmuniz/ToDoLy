package com.novare.sda7.todoly.service;

import java.io.IOException;
import java.util.Comparator;
import java.util.Date;

import com.novare.sda7.todoly.model.Task;
import com.novare.sda7.todoly.model.ToDoList;
import com.novare.sda7.todoly.repository.ToDoLyRepository;

/**
 * <h2>ToDoLy Service Class</h2>
 * <p>This class are used to encapsulate application logic. TodoLyService is responsible for show the task list
 * add new tasks, edit a task, mark as done, remove and call the save method for the repository. </p>
 * 
 * @author denisemuniz 
 * @version 1.0
 * @since 2020-03-06
 *
 */
public class ToDoLyService {

	ToDoLyRepository repository;
	ToDoList toDoList;

	/**
	 * This is the constructor, who retrieve the file if already exists a list.
	 * @throws IOException
	 */
	public ToDoLyService(ToDoLyRepository repository) throws IOException {
		this.repository = repository;
		toDoList = repository.retrieveToDoList(); 
	}

	/**
	 * This method is used to show the task list to the user.
	 * @return Nothing.
	 * @throws IOException
	 */
	public ToDoList getToDoList() throws IOException {
		return toDoList;
	}

	/**
	 * This method is used add a new task to the list.
	 * 
	 * @param newTask - object Task built in the the ToDoLy class.
	 * @return Nothing.
	 */

	public void addNewTask(Task newTask) {		
		toDoList.getTasks().add(newTask);

	}
	
	
	/**
	 * This method is used to check if the task exists before try to edit/mark as done/remove it from the list.
	 * @param taskIndex - task's index.
	 * @return Boolean.
	 */
	public  Boolean checkTaskIndex(int taskIndex) {

		return taskIndex < toDoList.getTasks().size() && toDoList.getTasks().get(taskIndex) != null;
	}

	
	/**
	 * This method is used to edit a task in the list.
	 * @param index - task's index.
	 * @param newName - task's new name.
	 * @param newDate - task's new due date.
	 * @param newProjec - task's new project.
	 */
	public void editTask(int index, String newName, Date newDate, String newProjec) {		
		toDoList.getTasks().get(index).setName(newName);
		toDoList.getTasks().get(index).setDueDate(newDate);
		toDoList.getTasks().get(index).setProject(newProjec);

	}
	
	
	/**
	 * This method is used to mark as done a task.
	 * @param index - task's index.
	 */
	public void markAsDone(int index) {
		toDoList.getTasks().get(index).setIsDone(true);
	}
	
	
	/**
	 * This method is used to remove a task from the list.
	 * @param index - task's index.
	 */
	public void removeTask(int index) {
		toDoList.getTasks().remove(index);
	}

	/**
	 * This method is used to call saveToDoList method of class ToDoLyRepository.
	 * @throws IOException
	 */
	public void save() throws IOException {
		repository.saveToDoList(toDoList);
	}
	
	/**
	 * This method sort the To Do List by date.
	 * @return toDoList sorted
	 * @throws IOException
	 */
	public ToDoList sortByDateToDoList() throws IOException {
		toDoList.getTasks().sort(Comparator.comparing(Task::getDueDate));
		return toDoList;
	}
	
	/**
	 * This method sort the To Do List by Project.
	 * @return toDoList sorted
	 * @throws IOException
	 */
	public ToDoList sortByProjectToDoList() throws IOException {
		toDoList.getTasks().sort(Comparator.comparing(Task::getProject));
		return toDoList;
	}

}
