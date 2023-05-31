package co.unicauca.openmarket.commons.application;


public class creditCard {
    
    private String nameOnCard;
    private String cardNumber;
    private String CVC;
    private String month;
    private String year;
    
    public creditCard(String nameOnCard, String numTarjeta, String CVC, String mes,String anio){
        this.nameOnCard = nameOnCard;
        this.cardNumber = numTarjeta;
        this.CVC = CVC;
        this.month = mes;
        this.year = anio;
    }

    public String getNameOnCard() {
        return nameOnCard;
    }

    public void setNameOnCard(String nameOnCard) {
        this.nameOnCard = nameOnCard;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCVC() {
        return CVC;
    }

    public void setCVC(String CVC) {
        this.CVC = CVC;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
    

    public String getDetails(){
        return nameOnCard + cardNumber + CVC + month + year;   
    }
}
