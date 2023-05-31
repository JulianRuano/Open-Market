package co.unicauca.openmarket.commons.application;

/**
 *
 * @author julian ruano
 */
public class Invoice {
    private String reference;
    private String nameProduct;
    private double price;
    private String fecha;
    private String status;
    
    
    public Invoice(){}
    public Invoice(String reference ,String nameProduct,double price,String fecha, String status){
        this.nameProduct = nameProduct;
        this.reference = reference;
        this.price = price;
        this.fecha = fecha;
        this.status = status;
    }

  

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public double getPrice() {
        return price;
    }
    
     public double getTotal() {
         //Logica para el total Implementar cuando este el carrito
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    
}
