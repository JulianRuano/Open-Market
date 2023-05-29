package co.unicauca.openmarket.server.application;

/**
 *
 * @author Julian Ruano
 */
public class PaymentHandler {
    private IPaymentStrategy paymentS;
    
    public void setPaymentStrategy(IPaymentStrategy paymentS) {
        this.paymentS = paymentS;
    }
    
    public boolean processPayment(String details) {
        
        if (paymentS.processPayment(details)){
            return false;
        }
         
        // Lógica adicional después de procesar el pago
        return true;
    }
    
}
