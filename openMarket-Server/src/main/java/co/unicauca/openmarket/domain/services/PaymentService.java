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
    
    public synchronized boolean save (String receiptId, String details, int userID){
        return repo.save(receiptId, details,userID);
    }
    public synchronized Invoice findById(String reference){
        return repo.findById(reference);
    }
    
    public synchronized boolean linkProduct(String receiptId, int productId){
        return repo.linkProduct(receiptId, productId);
    }
    


  

}
