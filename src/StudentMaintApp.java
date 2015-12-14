import java.util.Scanner;
import java.util.ArrayList;

public class StudentMaintApp implements StudentConstants
{
	// declare two class variables
	private static StudentDAO studentDAO = null;
	private static Scanner sc = null;

	public static void main(String args[])
	{
		// display a welcome message
		System.out.println("Welcome to the Student score table\n");

		// set the class variables
		studentDAO = DAOFactory.getProductDAO();
		sc = new Scanner(System.in);

		// display the command menu
		displayMenu();

		// perform 1 or more actions
		String action = "";
		while (!action.equalsIgnoreCase("exit"))
		{
			// get the input from the user
			action = Validator.getString(sc,
				"Enter a command: ");
			System.out.println();

			if (action.equalsIgnoreCase("list"))
				displayAllStudents();
			else if (action.equalsIgnoreCase("add"))
				addStudent();
			else if (action.equalsIgnoreCase("del") || action.equalsIgnoreCase("delete"))
				deleteStudent();
			else if (action.equalsIgnoreCase("help") || action.equalsIgnoreCase("menu"))
				displayMenu();
			else if (action.equalsIgnoreCase("exit"))
				System.out.println("Bye.\n");
			else
				System.out.println("Error! Not a valid command.\n");
		}
	}

	public static void displayMenu()
	{
		System.out.println("COMMAND MENU");
		System.out.println("list    - List all students");
		System.out.println("add     - Add a student");
		System.out.println("del     - Delete a student");
		System.out.println("help    - Show this menu");
		System.out.println("exit    - Exit this application\n");
	}

	public static void displayAllStudents()
	{
		System.out.println("STUDENT LIST");

		ArrayList<Student> students = studentDAO.getStudents();
		if (students == null)
		{
			System.out.println("Error! Unable to get students.\n");
		}
		else
		{
			Student s = null;
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < students.size(); i++)
			{
				s = students.get(i);
				sb.append
				(
					StringUtils.padWithSpaces(
						s.getId(), ID_SIZE + 4) +
					StringUtils.padWithSpaces(
						s.getName(), NAME_SIZE + 4) +
					s.getScore() + "\n"
				);
			}
			System.out.println(sb.toString());
		}
	}

	public static void addStudent()
	{
		String id = Validator.getString(
			sc, "Enter student id (less than 4 characters): ");
		String name = Validator.getLine(
			sc, "Enter student name: ");
		double score = Validator.getDouble(
			sc, "Enter score: ");

		Student student = new Student();
		student.setId(id);
		student.setName(name);
		student.setScore(score);
		boolean success = studentDAO.addStudent(student);

		System.out.println();
		if (success)
			System.out.println(name
				+ " was added to the database.\n");
		else
			System.out.println("Error! Unable to add student\n");
	}

	public static void deleteStudent()
	{
		String id = Validator.getString(sc,
			"Enter product code to delete: ");

		Student s = studentDAO.getStudent(id);

		System.out.println();
		if (s != null)
		{
			boolean success = studentDAO.deleteStudent(s);
			if (success)
				System.out.println(s.getName()
					+ " was deleted from the database.\n");
			else
				System.out.println("Error! Unable to delete student\n");
		}
		else
		{
			System.out.println("No product matches that id.\n");
		}
	}
}