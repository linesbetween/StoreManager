public class DAOFactory
{
	// this method maps the ProductDAO interface
	// to the appropriate data storage mechanism
	public static StudentXMLFile getStudentDAO()
	{
		StudentXMLFile pDAO = new StudentXMLFile();
		return pDAO;
	}
}