package com.novare.sda7.todoly;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.Scanner;

import com.novare.sda7.todoly.model.Task;
import com.novare.sda7.todoly.model.ToDoList;
import com.novare.sda7.todoly.repository.ToDoLyRepository;
import com.novare.sda7.todoly.service.ToDoLyService;
import com.novare.sda7.todoly.utils.DateUtils;

/**
 * <h1>ToDoLy!</h1> ToDoLy application implements an application that simply make
 * and control a to-do list, allowing you to tracking the tasks with due date,
 * project and control if its done or not.
 * <p>
 * <b>Note:</b> ToDoLy didn't have a interface, it´s all in the terminal.
 * 
 *
 * @author Denise Muniz
 * @version 1.0
 * @since 2020-03-06
 */

public class ToDoLy {

	/**
	 * This is the main method which call of all the others methods and make the
	 * interaction with the user.
	 * 
	 * @param args Unused.
	 * @return Nothing.
	 * @exception IOException On input error.
	 * @see IOException
	 */

	public static void main(String[] args) throws IOException {

		ToDoLyService service = new ToDoLyService(new ToDoLyRepository("ToDoLy.json"));
		ToDoList toDoList = service.getToDoList();

		try (Scanner sc = new Scanner(System.in)) {
			int userOption;
			do {
				Integer qtTask = toDoList.getTasks().size();
				Long qtTaskDone = toDoList.getTasks().stream().filter(Task::getIsDone).count();
				System.out.println("\n***** Welcome do ToDoLy *****");
				System.out.println("You have " + qtTask.toString() + " tasks todo and " + qtTaskDone.toString()
						+ " tasks are done!");
				System.out.println("Pick an option:");
				System.out.println("(1) Show Task List(by date or project)");
				System.out.println("(2) Add New Task");
				System.out.println("(3) Edit Task(update, mark as done, remove)");
				System.out.println("(4) Save and Quit");

				userOption = Integer.parseInt(sc.nextLine());

				switch (userOption) {
				case 1:
					showList(service, toDoList, sc);
					break;
				case 2:
					requestNewTask(service, sc, toDoList);
					break;
				case 3:
					requestEditTask(service, sc);
					break;
				case 4:
					service.save();
					break;
				default:
				}
			} while (userOption != 4);

		} catch (Exception e) {
			System.out.println(e);

		} finally {
			System.out.println("Bye Bye");
		}

	}

	/**
	 * This method is called to show the To Do list also known as task list to the user and
	 * allow the user to sort by date or project
	 * @param service - object to call the business rules.
	 * @param toDoList - object to hold and interact with the task list.
	 * @throws IOException
	 */
	private static void showList(ToDoLyService service, ToDoList toDoList, Scanner st) throws IOException {
		try {
			System.out.println("\nPlease choose how the list should be sorted: (1) by date (2) by project.");
			int taskAction = Integer.parseInt(st.nextLine());
			if(taskAction == 1) {
				service.sortByDateToDoList();
			}else {
				service.sortByProjectToDoList();
			}
			if (!toDoList.getTasks().isEmpty()) {
				System.out.println("\n***** ToDoLy - Task List *****");
				toDoList.getTasks().stream().forEach(task -> {
					System.out.println("Task number: " + toDoList.getTasks().indexOf(task) + "\t Task Title: "
							+ task.getName() + "\t Due date: " + DateUtils.parseDateToString(task.getDueDate())
							+ "\t Project: " + task.getProject() + "\t is Done: " + task.getIsDone());
				});
			} else {
				System.out.println("\nList does not exists yet. Create one+ tasks.\n");
			}
		} catch (Exception e) {
			System.out.println("Isn't possible sort the list, because is empty. Create one+ tasks.");
		}
		

	}

	/**
	 * This method call a request for add a new task to the To Do list. 
	 * If everything is correct this method call the method addNewTask.
	 * @param service - object to call the business rules.
	 * @param st - object to interact with the user.
	 * @param toDoList - object to hold and interact with the task list.
	 */
	private static void requestNewTask(ToDoLyService service, Scanner st, ToDoList toDoList) {
		String taskName;
		String projectName;
		Date dueDate;
		try {
			System.out.println("\nPlease enter task name");
			taskName = st.nextLine();
			System.out.println("Please enter due date of the task");
			dueDate = DateUtils.convertStringToDate(st.nextLine());
			System.out.println("Please enter the project of the task");
			projectName = st.nextLine();

			Task newTask = new Task(taskName, dueDate, projectName, false);

			service.addNewTask(newTask);
		} catch (ParseException e) {
			System.out.println("Date invalid! The format expected is mm/dd/yyyy. Please try again.\n");
		}
	}

	/**
	 * This method call a request do edit some task of the To Do list.
	 * If everything is correct this method call the method editTask, markAsDone or removeTask,
	 * according to the user's choice.
	 * @param service - object to call the business rules.
	 * @param sc - object to interact with the user.
	 */
	private static void requestEditTask(ToDoLyService service, Scanner sc) {

			String newName;
			String newProject;
			Date newDate;
			
			System.out.println("\nChoose an option: (1) for update name/project/due date \t (2) to mark as done \t (3) to remove the task.");
			int taskAction = Integer.parseInt(sc.nextLine());
			int taskIndex;
			try {	
			switch (taskAction) {
			case 1:
				System.out.println("\nPlease, enter the index of the task that will be updated.");
				taskIndex = Integer.parseInt(sc.nextLine());
				if(!service.checkTaskIndex(taskIndex)) {
					System.out.println("Doesn't have any task with this index.");
					break;
				}else {
				 
				 System.out.println("\nPlease enter the new name of the task.");
				 newName = sc.nextLine();
				 System.out.println("Please enter the new due date of the task.");
				 newDate = DateUtils.convertStringToDate(sc.nextLine());
				 System.out.println("Please entender the new project of the task.");
				 newProject = sc.nextLine();
				 
				 service.editTask(taskIndex, newName, newDate, newProject);
				 break;
				}
			case 2:
				System.out.println("\nPlease, enter the index of the task that will be marked as done.");
				taskIndex = Integer.parseInt(sc.nextLine());
				if(!service.checkTaskIndex(taskIndex)) {
					System.out.println("Doesn't have any task with this index.");
					break;
				}else {
					service.markAsDone(taskIndex);
					break;
				}	
				
			case 3:
				System.out.println("\nPlease, enter the index of the task that will be removed.");
				taskIndex = Integer.parseInt(sc.nextLine());
				if(!service.checkTaskIndex(taskIndex)) {
					System.out.println("Doesn't have any task with this index.");
					break;
				}else {
					service.removeTask(taskIndex);
					break;
				}	
				
			}
			} catch (ParseException e) {
				System.out.println("Date invalid! The format expected is mm/dd/yyyy. Please try again.\n");
			}
		
		
	}
	

}
