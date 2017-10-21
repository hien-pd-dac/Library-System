package model;

/**
 *
 * @author Pham Duc Hien
 */
public class Book {
    
    private String id;
    private String name;
    private String author;
    private String nxb;
    
    /**
     * two of type: REFERENCE and BORROWABLE
     */
    private int type;
    
    /**
     * two of status: AVAILABLE & UNAVALABLE
     */
    private int status;
    
    /**
     * price of book
     */
    private int price;

    public Book() {
        
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getNxb() {
        return nxb;
    }

    public void setNxb(String nxb) {
        this.nxb = nxb;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
    
    
}
