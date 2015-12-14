
import java.text.NumberFormat;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author mtg
 */
public class Student
{
	private String id;
	private String name;
	private double score;
	

	public Student()
	{
		this("", "", 0.0);
	}

	public Student(String id, String name, double score)
	{
		this.id = id;
		this.name = name;
		this.score = score;
		
		
	}
	
	public void setId(String id)
	{
		this.id = id;
	}
	
	public String getId()
	{
		return id;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public String getName()
	{
		return name;
	}
	
	public void setScore(double score)
	{
		this.score = score;
	}
	
	public double getScore()
	{
		return score;
	}
	


	

    public boolean equals(Object object)
    {
        if (object instanceof Student)
        {
            Student student2 = (Student) object;
            if
            (
                id.equals(student2.getId()) &&
                name.equals(student2.getName()) &&
                score == student2.getScore()
            )
                return true;
        }
        return false;
    }

	public String toString()
	{
		return "ID:        " + id + "\n" +
			   "Name: " + name + "\n" +
			   "Score:       " + score + "\n";

	}
}
