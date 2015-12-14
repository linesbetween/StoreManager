import java.util.*;
import java.io.*;
import javax.xml.stream.*;  // StAX API

public class ProductXMLFile implements ProductDAO
{
    private String productsFilename = "students.xml";
    private File productsFile = null;

    public ProductXMLFile()
    {
        productsFile = new File(productsFilename);
    }

    private void checkFile() throws IOException
    {
        // if the file doesn't exist, create it
        if (!productsFile.exists())
            productsFile.createNewFile();
    }

    private boolean saveStudents(ArrayList<Student> students)
    {
        // create the XMLOutputFactory object
        XMLOutputFactory outputFactory = XMLOutputFactory.newInstance();

        try
        {
            // check the file to make sure it exists
            this.checkFile();

            // create XMLStreamWriter object
            FileWriter fileWriter =
                new FileWriter(productsFilename);
            XMLStreamWriter writer =
                outputFactory.createXMLStreamWriter(fileWriter);

            //write the products to the file
            writer.writeStartDocument("1.0");
            writer.writeStartElement("Students");
            for (Student s : students)
            {
                writer.writeStartElement("Student");
                writer.writeAttribute("Id", s.getId());

                writer.writeStartElement("Name");
                writer.writeCharacters(s.getName());
                writer.writeEndElement();

                writer.writeStartElement("Score");
                double score = s.getScore();
                writer.writeCharacters(Double.toString(score));
                writer.writeEndElement();
				
		
				
		
				
				
                writer.writeEndElement();
            }
            writer.writeEndElement();
            writer.flush();
            writer.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return false;
        }
        catch (XMLStreamException e)
        {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public ArrayList<Student> getStudents()
    {
        ArrayList<Student> students = new ArrayList<Student>();
        Student s = null;

        // create the XMLInputFactory object
        XMLInputFactory inputFactory = XMLInputFactory.newInstance();
        try
        {
            // check the file to make sure it exists
            this.checkFile();

            // create a XMLStreamReader object
            FileReader fileReader =
                new FileReader(productsFilename);
            XMLStreamReader reader =
                inputFactory.createXMLStreamReader(fileReader);

            // read the products from the file
            while (reader.hasNext())
            {
                int eventType = reader.getEventType();
                switch (eventType)
                {
                    case XMLStreamConstants.START_ELEMENT:
                        String elementName = reader.getLocalName();
                        if (elementName.equals("Student"))
                        {
                            s = new Student();

                            String id = reader.getAttributeValue(0);
                            s.setId(id);
                        }
                        if (elementName.equals("Name"))
                        {
                            String name = reader.getElementText();
                            s.setName(name);
                        }
                        if (elementName.equals("Score"))
                        {
                            String priceString = reader.getElementText();
                            double score = Double.parseDouble(priceString);
                            s.setScore(score);
                        }
			
                        break;
                    case XMLStreamConstants.END_ELEMENT:
                        elementName = reader.getLocalName();
                        if (elementName.equals("Student"))
                        {
                            students.add(s);
                        }
                        break;
                    default:
                        break;
                }
                reader.next();
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return null;
        }
        catch (XMLStreamException e)
        {
            e.printStackTrace();
            return null;
        }
        return students;
    }

    public Student getStudent(String id)
    {
        ArrayList<Student> students = this.getStudents();
        for (Student s : students)
        {
            if (s.getId().equals(id))
                return s;
        }
        return null;
    }

    public boolean addStudent(Student s)
    {
        ArrayList<Student> students = this.getStudents();
        students.add(s);
        return this.saveStudents(students);
    }

    public boolean deleteStudent(Student s)
    {
        ArrayList<Student> students = this.getStudents();
        students.remove(s);
        return this.saveStudents(students);
    }

    public boolean updateStudent(Student newStudent)
    {
        ArrayList<Student> students = this.getStudents();

        // get the old product and remove it
        Student oldStudent = this.getStudent(newStudent.getId());
        int i = students.indexOf(oldStudent);
        students.remove(i);

        // add the updated product
        students.add(i, newStudent);

        return this.saveStudents(students);
    }
}