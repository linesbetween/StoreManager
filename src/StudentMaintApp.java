import java.util.Scanner;
import java.util.ArrayList;

public class StudentMaintApp implements StudentConstants
{
	// declare two class variables
	private static StudentXMLFile studentDAO = null;
	private static Scanner sc = null;

	public static void main(String args[])
	{
		// display a welcome message
		System.out.println("Welcome to the Student score table\n");

		// set the class variables
		studentDAO = DAOFactory.getStudentDAO();
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

			if (action.equalsIgnoreCase("list")){
                             ArrayList<Student> students = studentDAO.getStudents();
				displayAllStudents(students);
                        }
			else if (action.equalsIgnoreCase("add"))
				addStudent();
			else if (action.equalsIgnoreCase("del") || action.equalsIgnoreCase("delete"))
				deleteStudent();
                        else if (action.equalsIgnoreCase("sort") || action.equalsIgnoreCase("sort"))
				sortStudents();
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
                System.out.println("sort     -Sort students by score and display");
		System.out.println("help    - Show this menu");
		System.out.println("exit    - Exit this application\n");
	}

       
	public static void displayAllStudents(ArrayList<Student> students)
	{
		System.out.println("STUDENT LIST");

		//ArrayList<Student> students = studentDAO.getStudents();
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
        
        public static void sortStudents(){
            
            
            ArrayList<Student> students = studentDAO.getStudents();
            String action;
            System.out.println("Sort by name(n) or score(s)?");
            sc = new Scanner(System.in);
            action = Validator.getString(sc,
				"Enter a command: ");
            
            
            if (action.equalsIgnoreCase("s")||action.equalsIgnoreCase("score")){
                             //TODO 
                             // Indicator for sort by score
				System.out.println("Sort by score?");
                        }
			
            else if (action.equalsIgnoreCase("n") || action.equalsIgnoreCase("name")){
                 //TODO 
                 // Indicator for sort by name
                System.out.println("Sort by name");
            }		
            else{
                System.out.println("Wrong command");
                System.out.println("Returning to main menu");
                System.out.println();
                return;
            }
            
           if (students == null)
		{
			System.out.println("Error! Unable to get students.\n");
		}
                else{ //TODO sort
                    
                }
                
                displayAllStudents(students);
                
                
            System.out.println("Save to xml? (yes/y, no/n)");
            sc = new Scanner(System.in);
            action = Validator.getString(sc,
				"Enter a command: ");
              if (action.equalsIgnoreCase("y")||action.equalsIgnoreCase("yes")){
                             //TODO 
                            
               // boolean success = studentDAO.saveStudents(students);
                System.out.println("Saved to xml");
                System.out.println("Returning to main menu");
                System.out.println();
                        }
			
            else if (action.equalsIgnoreCase("n") || action.equalsIgnoreCase("no")){
                 //TODO 
                
                System.out.println("Not saved");
                System.out.println("Returning to main menu");
                System.out.println();
            }
               else{
                System.out.println("Wrong command");
                System.out.println("Returning to main menu");
                System.out.println();
                return;
            }
                
            System.out.println();
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