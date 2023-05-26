package co.unicauca.openmarket.server.application;
import co.unicauca.openmarket.commons.application.PaymentDetails;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author julian ruano
 */
public class PurchaseGenerator {
    

    //Inforamcion de tarjetas validas
    private final PaymentDetails card1 = new PaymentDetails("julian ruano", "12345", "123", "10", "2023");
    private final PaymentDetails card2 = new PaymentDetails("jorge ayerbe", "12345", "123", "10", "2023");
    private final PaymentDetails card3 = new PaymentDetails("freider escobar", "12345", "123", "10", "2023");
    private final PaymentDetails card4 = new PaymentDetails("brayan majin", "12345", "123", "10", "2023");
    private final List<String> listCard = new ArrayList<>();
    
    public PurchaseGenerator(){
        listCard.add(card1.Details());
        listCard.add(card2.Details());
        listCard.add(card3.Details());
        listCard.add(card4.Details());
    }
    
    
    public boolean validator(PaymentDetails paymentDetails){      
        for (int i = 0; i< listCard.size(); i++){
            if (paymentDetails.Details().equals(listCard.get(i)))
                return true;
        }
        return false;
    }
    
    
    
    public String generateCode() {
        String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String numbers = "0123456789";
        Random random = new Random();
        StringBuilder codigo = new StringBuilder();
        
        codigo.append(letters.charAt(random.nextInt(letters.length())));  // Letra inicial
        codigo.append(numbers.charAt(random.nextInt(numbers.length())));  // Primer dígito
        codigo.append(numbers.charAt(random.nextInt(numbers.length())));  // Segundo dígito
        codigo.append(numbers.charAt(random.nextInt(numbers.length())));  // Tercer dígito
        codigo.append(letters.charAt(random.nextInt(letters.length())));  // Letra intermedia
        codigo.append(numbers.charAt(random.nextInt(numbers.length())));  // Cuarto dígito
        codigo.append(letters.charAt(random.nextInt(letters.length())));  // Letra final
        codigo.append(numbers.charAt(random.nextInt(numbers.length())));  // Quinto dígito
        
        return codigo.toString();
    }
}
