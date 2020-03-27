package com.novare.todoly.test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.text.ParseException;

import org.junit.Before;
import org.junit.Test;

import com.novare.sda7.todoly.model.Task;
import com.novare.sda7.todoly.model.ToDoList;
import com.novare.sda7.todoly.repository.ToDoLyRepository;
import com.novare.sda7.todoly.utils.DateUtils;

public class ToDoLyRepositoryTest {

	ToDoLyRepository repo = new ToDoLyRepository("ToDoLyTestFile.json");
	
	@Before
	public void createService() throws IOException {
		repo.saveToDoList(null);
	}

	
	@Test
	public void toDoLyRepositoryRetrieveToDoListTest() throws IOException, ParseException {
		ToDoList toDoList = repo.retrieveToDoList();
		ToDoList toDoListExpected = new ToDoList();

		assertArrayEquals(toDoList.getTasks().toArray(), toDoListExpected.getTasks().toArray());
	}
	
	@Test
	public void toDoLyRepositorySaveToDoListTest() throws ParseException, IOException {
		ToDoList toDoList = new ToDoList();
		Task testTask = new Task("Unit Test", DateUtils.convertStringToDate("03/25/2020"), "Individual project", false);
		toDoList.getTasks().add(testTask);
		repo.saveToDoList(toDoList);
		
		ToDoList retrievedList = repo.retrieveToDoList();
		
		assertTrue(retrievedList.getTasks().size() == 1);
	}
	
}
