package com.novare.todoly.test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.text.ParseException;

import org.junit.Before;
import org.junit.Test;

import com.novare.sda7.todoly.model.Task;
import com.novare.sda7.todoly.model.ToDoList;
import com.novare.sda7.todoly.repository.ToDoLyRepository;
import com.novare.sda7.todoly.service.ToDoLyService;
import com.novare.sda7.todoly.utils.DateUtils;

public class ToDoLyServiceTest {

	ToDoLyRepository repo = new ToDoLyRepository("ToDoLyTestFile.json");
	ToDoLyService service;

	@Before
	public void createService() throws IOException {
		repo.saveToDoList(null);
		service = new ToDoLyService(repo);
	}

	@Test
	public void toDoLyServiceGetToDoListTest() throws IOException {
		ToDoList toDoListTest = new ToDoList();
		ToDoList toDoListTestExpected = new ToDoList();

		toDoListTestExpected = service.getToDoList();

		assertArrayEquals(toDoListTest.getTasks().toArray(), toDoListTestExpected.getTasks().toArray());

	}

	@Test
	public void toDoLyServiceAddNewTaskTest() throws IOException, ParseException {
		ToDoList toDoListTest = new ToDoList();
		Task testTask = new Task("Unit Test", DateUtils.convertStringToDate("03/25/2020"), "Individual project", false);
		service.addNewTask(testTask);
		toDoListTest.getTasks().add(testTask);

		assertTrue(toDoListTest.getTasks().get(0).equals(testTask));
	}

	@Test
	public void toDoLyServiceEditTaskTest() throws ParseException, IOException {
		Task testTask = new Task("Unit Test", DateUtils.convertStringToDate("03/25/2020"), "Individual project", false);

		service.addNewTask(testTask);
		service.editTask(0, "Unit Test Edit Task", DateUtils.convertStringToDate("03/25/2020"),
				"Individual project");
		int isTrue = service.getToDoList().getTasks().get(0).getName().compareTo("Unit Test Edit Task");

		assertTrue(0 == isTrue);

	}

	@Test
	public void toDoLyServiceCheckTaskIndexTrueTest() throws IOException, ParseException {

		Task testTask = new Task("Unit Test", DateUtils.convertStringToDate("03/25/2020"), "Individual project", false);
		service.addNewTask(testTask);

		assertTrue(service.checkTaskIndex(0));

	}

	@Test
	public void toDoLyServiceCheckTaskIndexFalseTest() throws IOException, ParseException {

		Task testTask = new Task("Unit Test", DateUtils.convertStringToDate("03/25/2020"), "Individual project", false);
		service.addNewTask(testTask);

		assertFalse(service.checkTaskIndex(1));

	}

	@Test
	public void toDoLyServiceMarkAsDoneTest() throws IOException, ParseException {
		Task testTask = new Task("Unit Test", DateUtils.convertStringToDate("03/25/2020"), "Individual project", false);
		service.addNewTask(testTask);
		service.markAsDone(0);

		assertTrue(service.getToDoList().getTasks().get(0).getIsDone() == true);

	}

	@Test
	public void toDoLyServiceRemoveTaskTest() throws ParseException, IOException {
		Task testTask = new Task("Unit Test", DateUtils.convertStringToDate("03/25/2020"), "Individual project", false);
		Task testTask2 = new Task("Unit Test 2", DateUtils.convertStringToDate("03/25/2020"), "Individual project",
				false);
		service.addNewTask(testTask);
		service.addNewTask(testTask2);

		service.removeTask(1);

		assertTrue(service.getToDoList().getTasks().size() == 1);

	}

	@Test
	public void toDoLyServiceSaveTest() throws IOException, ParseException {
		Task testTask = new Task("Unit Test", DateUtils.convertStringToDate("03/25/2020"), "Individual project", false);
		service.addNewTask(testTask);
		service.save();
		ToDoLyService newService = new ToDoLyService(new ToDoLyRepository("ToDoLyTestFile.json"));

		assertTrue(newService.getToDoList().getTasks().size() == 1);

	}
	
	@Test
	public void toDoLyServiceSortTaskByDateTest() throws ParseException, IOException {
		Task testTask = new Task("Unit Test", DateUtils.convertStringToDate("03/27/2020"), "Individual project", false);
		Task testTask2 = new Task("Unit Test 2", DateUtils.convertStringToDate("03/25/2020"), "Individual project",
				false);
		service.addNewTask(testTask);
		service.addNewTask(testTask2);

		

		assertTrue(service.sortByDateToDoList().getTasks().get(0).getName() == "Unit Test 2");

	}
	
	@Test
	public void toDoLyServiceSortTaskByProjectTest() throws ParseException, IOException {
		Task testTask = new Task("Unit Test", DateUtils.convertStringToDate("03/27/2020"), "My project", false);
		Task testTask2 = new Task("Unit Test 2", DateUtils.convertStringToDate("03/25/2020"), "First project",
				false);
		service.addNewTask(testTask);
		service.addNewTask(testTask2);

		

		assertTrue(service.sortByProjectToDoList().getTasks().get(0).getProject() == "First project");

	}


}
