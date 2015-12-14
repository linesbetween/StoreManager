import java.text.NumberFormat;

public class Product
{
	private String code;
	private String description;
	private double price;
	private String notes;
	private int stock;
	private int sold;

	public Product()
	{
		this("", "", 0, "", 0, 0);
	}

	public Product(String code, String description, double price, String notes, int stock, int sold)
	{
		this.code = code;
		this.description = description;
		this.price = price;
		this.notes = notes;
		this.stock = stock;
		this.sold = sold;
	}
	
	public void setNotes(String notes)
	{
		this.notes = notes;
	}
	
	public String getNotes()
	{
		return notes;
	}
	
	public void setStock(int stock)
	{
		this.stock = stock;
	}
	
	public int getStock()
	{
		return stock;
	}
	
	public void setSold(int sold)
	{
		this.sold = sold;
	}
	
	public int getSold()
	{
		return sold;
	}
	
	public void setCode(String code)
	{
		this.code = code;
	}

	public String getCode(){
		return code;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public String getDescription()
	{
		return description;
	}

	public void setPrice(double price)
	{
		this.price = price;
	}

	public double getPrice()
	{
		return price;
	}

	public String getFormattedPrice()
	{
		NumberFormat currency = NumberFormat.getCurrencyInstance();
		return currency.format(price);
	}

    public boolean equals(Object object)
    {
        if (object instanceof Product)
        {
            Product product2 = (Product) object;
            if
            (
                code.equals(product2.getCode()) &&
                description.equals(product2.getDescription()) &&
                price == product2.getPrice()
            )
                return true;
        }
        return false;
    }

	public String toString()
	{
		return "Code:        " + code + "\n" +
			   "Description: " + description + "\n" +
			   "Price:       " + this.getFormattedPrice() + "\n" +
			   "Notes:       " + notes + "\n" +
			   "No. Stock:   " + stock + "\n" +
			   "No. Sold:    " + sold + "\n" ;
	}
}