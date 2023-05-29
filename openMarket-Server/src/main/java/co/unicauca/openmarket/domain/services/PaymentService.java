package co.unicauca.openmarket.domain.services;

import co.unicauca.openmarket.commons.application.Invoice;
import co.unicauca.openmarket.server.access.IPaymentRepository;


/**
 *
 * @author Julian Ruano
 */
public class PaymentService {
    
    
    IPaymentRepository repo;

    public PaymentService(IPaymentRepository repo) {
        this.repo = repo;
    }
    
    public synchronized String save (String receiptId, String details){
        return repo.save(receiptId, details);
    }
    public synchronized Invoice findById(String reference){
        return repo.findById(reference);
    }
    


  

}
