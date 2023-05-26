package co.unicauca.openmarket.commons.domain;


public class Product {
    private Long productId;
    private String name;    
    private String description; 
    private String address;
    private double price;
    private Long categoryId;
    private byte [] image;
    
    
    public Product(Long productId, String name, String description, double price,String address ,Long categoryId,byte [] image) {
        this.productId = productId;
        this.name = name;
        this.description = description;
        this.price = price;
        this.address = address;
        this.image = image;     
        this.categoryId=categoryId;
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

}
