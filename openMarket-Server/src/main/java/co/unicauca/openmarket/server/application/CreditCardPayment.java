package co.unicauca.openmarket.server.application;
import co.unicauca.openmarket.commons.application.creditCard;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author julian ruano
 */
public class CreditCardPayment implements IPaymentStrategy {
    

    //Inforamcion de tarjetas validas
    private final creditCard card1 = new creditCard("julian ruano", "12345", "123", "10", "2023");
    private final creditCard card2 = new creditCard("jorge ayerbe", "12345", "123", "10", "2023");
    private final creditCard card3 = new creditCard("freider escobar", "12345", "123", "10", "2023");
    private final creditCard card4 = new creditCard("brayan majin", "12345", "123", "10", "2023");
    private final List<String> listCard = new ArrayList<>();
    
    public CreditCardPayment(){
        listCard.add(card1.getDetails());
        listCard.add(card2.getDetails());
        listCard.add(card3.getDetails());
        listCard.add(card4.getDetails());
    }
        
    @Override
    public boolean processPayment(String details) {
        for (int i = 0; i< listCard.size(); i++){
            if (listCard.get(i).equals(details))
                return true;
        }
        return false;
    }
    
    
    
   
}
