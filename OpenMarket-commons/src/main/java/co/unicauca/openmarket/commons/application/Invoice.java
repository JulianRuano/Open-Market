package co.unicauca.openmarket.commons.application;

/**
 *
 * @author julian ruano
 */
public class Invoice {
    private String reference;
    private String name;
    public Invoice(String reference ,String name){
        this.name = name;
        this.reference = reference;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    
}
