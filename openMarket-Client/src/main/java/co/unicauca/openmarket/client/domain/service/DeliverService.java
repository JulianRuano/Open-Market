
package co.unicauca.openmarket.client.domain.service;

import co.unicauca.openmarket.client.access.IDeliverAccess;
import co.unicauca.openmarket.commons.application.Invoice;
import java.util.List;

/**
 *
 * @author brayan
 */
public class DeliverService {
    private IDeliverAccess repository;
    public DeliverService() {
    }
    public DeliverService(IDeliverAccess repository) {
        this.repository=repository;
    }
    
    public double priceToPayService(String reference, int userID)throws Exception{
        if(reference==null || userID<=0){
            return 0;
        }
        return repository.priceToPay(reference, userID);
    }
    public double  qualificationService(String reference,int punctuation, int userID)throws Exception{
        if(reference==null || (punctuation<1 && punctuation>5)||userID<=0){
            return -1;
        }
        return repository.qualification(reference,punctuation, userID);
    }
    public boolean  confirmService(String reference,int punctuation, int userID)throws Exception{
        if(reference==null || (punctuation<1 && punctuation>5)||userID<=0){
            return false;
        }
        return repository.confirm(reference,punctuation, userID);
    }
    public List<Invoice> billListService(int userID)throws Exception{
        if(userID<=0){
            return null;
        }
        return repository.billList(userID);
    }
    
    
}
