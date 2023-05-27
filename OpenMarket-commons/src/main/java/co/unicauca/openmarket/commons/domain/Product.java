package co.unicauca.openmarket.commons.domain;


public class Product {
    private Long productId;
    private String name;    
    private String description; 
    private double price;
    private String address;   
    private Long categoryId;
    private int stock;
    private byte [] image;
    
    
    public Product(Long productId, String name, String description, double price,String address ,Long categoryId, int stock,byte [] image) {
        this.productId = productId;
        this.name = name;
        this.description = description;
        this.price = price;
        this.address = address;
        this.categoryId=categoryId;
        this.stock = stock;
        this.image = image;     
        
    }
    public Product(){
        
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
    
    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
    

}
