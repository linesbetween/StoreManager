public class DAOFactory
{
	// this method maps the ProductDAO interface
	// to the appropriate data storage mechanism
	public static StudentDAO getProductDAO()
	{
		StudentDAO pDAO = new StudentXMLFile();
		return pDAO;
	}
}