import java.util.ArrayList;

public interface StudentReader
{
	Student getStudent(String id);
	ArrayList<Student> getStudents();
}