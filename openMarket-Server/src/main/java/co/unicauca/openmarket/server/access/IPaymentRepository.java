package co.unicauca.openmarket.server.access;
import co.unicauca.openmarket.commons.application.Invoice;
import java.util.List;

/**
 *
 * @author Julian Ruano
 */
public interface IPaymentRepository {  
    boolean save (String receiptId, String details);
    Invoice findById(String reference);
    Double findPrice(String reference);
    boolean linkProduct(String receiptId, int productId );

}
