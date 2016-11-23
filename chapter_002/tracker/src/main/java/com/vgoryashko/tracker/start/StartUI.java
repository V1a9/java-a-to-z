package com.vgoryashko.tracker.start;

import com.vgoryashko.tracker.models.Item;
import com.vgoryashko.tracker.models.Task;
import com.vgoryashko.tracker.models.Bug;

/**
 * Class that implements system that implements UI for tracking system.
 * @author Vlad Goryashko
 * @version 0.3
 * @since 18/11/2016
 */

public class StartUI {

	/**
	 * Variable that is used for implementation of a number different types of input methods.
	 */
	private Input input;

	/**
	 * Constructor for the class.
	 * @param aInput							an object that implements Input interface for realization
	 *                                          of interaction with an end user.
     */
	public StartUI(Input aInput) {
		this.input = aInput;
	}

	/**
	 * Array of strings representing main menu of UI.
	 */
	private String[] userMainMenu = {
			"\nPlease enter command:",
			"1. Create item;",
			"2. Add comment;",
			"3. Show all items;",
			"4. Remove item;",
			"5. Find item;",
			"6. Replace item;",
			"7. Exit.\n"};

	/**
	 * Array of strings representing menu in UI for creating Items.
	 */
	private String[] createItemMenu = {
			"\nPlease choose type of request:",
			"1. Create Item;",
			"2. Create Task;",
			"3. Create Bug;",
			"4. Back.\n"};

	/**
	 * Array of strings representing menu in UI for searching Items.
	 */
	private String[] userFindItemMenu = {
			"\nPlease enter command:",
			"1. Find Item by name;",
			"2. Find Item by Id",
			"3. Back.\n"};

	/**
	 * Array of strings representing menu in UI for replacing Items.
	 */
	private String[] userReplaceItemMenu = {
			"\nPlease enter command:",
			"1. Replace by Item;",
			"2. Replace by Task;",
			"3. Replace by Bug;",
			"4. Back;\n"};

	/**
	 * Method that initialize application.
	 */
	public void init() {
		Tracker tracker = new Tracker();
		boolean exit = false;
		do {
			for (String str : this.userMainMenu) {
				System.out.printf("%s\n", str);
			}
			String action = input.ask("Command");
			if (action.equals("1")) {
				for (String str : this.createItemMenu) {
					System.out.printf("%s\n", str);
				}
				action = input.ask("Command");
					if (action.equals("1")) {
						String name = input.ask("\nPlease enter Item name");
						String desc = input.ask("\nPlease enter Item description");
						tracker.addItem(new Item(name, desc));
						System.out.printf("\n");
					} else {
						if (action.equals("2")) {
							String name = input.ask("\nPlease enter Task name");
							String desc = input.ask("\nPlease enter Task description");
							tracker.addItem(new Task(name, desc));
						} else {
							if (action.equals("3")) {
								String name = input.ask("\nPlease enter Bug name");
								String desc = input.ask("\nPlease enter Bug description");
								tracker.addItem(new Bug(name, desc));
							} else {
								if (action.equals("4")) {
								}
							}
						}
					}
			} else {
				if (action.equals("2")) {
					String name = input.ask("\nPlease enter name Item name where comment has to be added to");
					String comment = input.ask("\nPlease enter comment");
					tracker.addComment(name, comment);
				} else {
					if (action.equals("3")) {
						for (Item item : tracker.getAll()) {
							if (item.getComment() == null) {
								System.out.printf("\nName: %s, Id: %s.", item.getName(), item.getId());
							} else {
								System.out.printf("\nName: %s, Id: %s, Comment: %s.", item.getName(), item.getId(), item.getComment().getCommentField());
							}
						}
						System.out.printf("\n");
					} else {
						if (action.equals("4")) {
							String id = input.ask("\nPlease enter Item's Id to be removed");
							tracker.removeItem(id);
						} else {
							if (action.equals("5")) {
								boolean exitSubMenu = false;
								do {
									for (String str : this.userFindItemMenu) {
										System.out.printf("%s\n", str);
									}
									action = input.ask("Command");
									if (action.equals("1")) {
										String name = input.ask("\nPlease enter name Item's name to be found");
										System.out.printf("\nName: %s, Id: %s, Description: %s", name, tracker.findByName(name).getId(), tracker.findByName(name).getDescription());
									} else {
										if (action.equals("2")) {
											String id = input.ask("\nPlease enter name Item's Id to be found");
											System.out.printf("\nName: %s, Id: %s, Description: %s", tracker.findById(id).getName(), id, tracker.findById(id).getDescription());
										} else {
											if (action.equals("3")) {
												exitSubMenu = true;
											}
										}
									}
								} while (!exitSubMenu);
							} else {
								if (action.equals("6")) {
									for (String str : this.userReplaceItemMenu) {
										System.out.printf("%s\n", str);
									}
									action = input.ask("\nPlease chose type of Item's to be replace by (existing Id required)");
									if (action.equals("1")) {
										String name = input.ask("\nPlease enter Item's name");
										String desc = input.ask("\nPlease enter Item's description ");
										String id = input.ask("\nPlease enter Id of Item to be replaced");
										Item item = new Item(name, desc);
										item.setId(id);
										tracker.replace(item);
									} else {
										if (action.equals("2")) {
											String name = input.ask("\nPlease enter Task's name");
											String desc = input.ask("\nPlease enter Task's description");
											String id = input.ask("\nPlease enter Id of Item to be replaced");
											Item item = new Task(name, desc);
											item.setId(id);
											tracker.replace(item);
										} else {
											if (action.equals("3")) {
												String name = input.ask("\nPlease enter Bug's name");
												String desc = input.ask("\nPlease enter Bug's description");
												String id = input.ask("\nPlease enter Id of Item to be replaced");
												Item item = new Bug(name, desc);
												item.setId(id);
												tracker.replace(item);
											} else {
												if 	(action.equals("4")) {
													break;
												}
											}
										}
									}
								} else {
									if (action.equals("7")) {
										exit = true;
									}
								}
							}
						}
					}
				}
			}
		} while (!exit);
	}

	/**
	 * Main method - the enter point for the application.
	 * @param args							array of String default parameter
     */
	public static void main(String[] args) {
		Input input = new ConsoleInput();
		new StartUI(input).init();
	}
}