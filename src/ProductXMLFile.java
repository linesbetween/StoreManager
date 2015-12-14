import java.util.*;
import java.io.*;
import javax.xml.stream.*;  // StAX API

public class ProductXMLFile implements ProductDAO
{
    private String productsFilename = "products.xml";
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

    private boolean saveProducts(ArrayList<Product> products)
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
            writer.writeStartElement("Products");
            for (Product p : products)
            {
                writer.writeStartElement("Product");
                writer.writeAttribute("Code", p.getCode());

                writer.writeStartElement("Description");
                writer.writeCharacters(p.getDescription());
                writer.writeEndElement();

                writer.writeStartElement("Price");
                double price = p.getPrice();
                writer.writeCharacters(Double.toString(price));
                writer.writeEndElement();
				
				writer.writeStartElement("Notes");
				writer.writeCharacters(p.getNotes());
				writer.writeEndElement();
				
				writer.writeStartElement("Stock");
                int stock = p.getStock();
                writer.writeCharacters(Integer.toString(stock));
                writer.writeEndElement();
				
				writer.writeStartElement("Sold");
                int sold = p.getSold();
                writer.writeCharacters(Integer.toString(price));
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

    public ArrayList<Product> getProducts()
    {
        ArrayList<Product> products = new ArrayList<Product>();
        Product p = null;

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
                        if (elementName.equals("Product"))
                        {
                            p = new Product();

                            String code = reader.getAttributeValue(0);
                            p.setCode(code);
                        }
                        if (elementName.equals("Description"))
                        {
                            String description = reader.getElementText();
                            p.setDescription(description);
                        }
                        if (elementName.equals("Price"))
                        {
                            String priceString = reader.getElementText();
                            double price = Double.parseDouble(priceString);
                            p.setPrice(price);
                        }
						if (elementName.equals("Notes"))
                        {
                            String notes = reader.getElementText();
                            p.setNotes(notes);
                        }
						if (elementName.equals("Stock"))
                        {
                            String stockString = reader.getElementText();
                            int stock = Integer.parseInt(stockString);
                            p.setStock(stock);
                        }
						if (elementName.equals("Stock"))
                        {
                            String soldString = reader.getElementText();
                            int sold = Integer.parseInt(soldString);
                            p.setStock(sold);
                        }
                        break;
                    case XMLStreamConstants.END_ELEMENT:
                        elementName = reader.getLocalName();
                        if (elementName.equals("Product"))
                        {
                            products.add(p);
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
        return products;
    }

    public Product getProduct(String code)
    {
        ArrayList<Product> products = this.getProducts();
        for (Product p : products)
        {
            if (p.getCode().equals(code))
                return p;
        }
        return null;
    }

    public boolean addProduct(Product p)
    {
        ArrayList<Product> products = this.getProducts();
        products.add(p);
        return this.saveProducts(products);
    }

    public boolean deleteProduct(Product p)
    {
        ArrayList<Product> products = this.getProducts();
        products.remove(p);
        return this.saveProducts(products);
    }

    public boolean updateProduct(Product newProduct)
    {
        ArrayList<Product> products = this.getProducts();

        // get the old product and remove it
        Product oldProduct = this.getProduct(newProduct.getCode());
        int i = products.indexOf(oldProduct);
        products.remove(i);

        // add the updated product
        products.add(i, newProduct);

        return this.saveProducts(products);
    }
}