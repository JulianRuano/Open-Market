package co.unicauca.openmarket.client.access;

import co.unicauca.openmarket.commons.application.creditCard;
import co.unicauca.openmarket.client.infra.OpenMarketSocket;
import co.unicauca.openmarket.commons.application.Invoice;
import co.unicauca.openmarket.commons.infra.JsonError;
import co.unicauca.openmarket.commons.infra.Protocol;
import com.google.gson.Gson;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ShoppingCartAccessImplSockets implements IShoppingCartAccess {

    private final OpenMarketSocket mySocket;
     public ShoppingCartAccessImplSockets() {
        mySocket = new OpenMarketSocket();
    }
    @Override 
    public Invoice buy(int id, creditCard paymentMethod, int userId) {
        String jsonResponse = null;
        String requestJson = doBuyRequestJson(id,paymentMethod,userId);
        try {
            mySocket.connect();
            jsonResponse = mySocket.sendRequest(requestJson);
            mySocket.disconnect();

        } catch (IOException ex) {
            Logger.getLogger(ShoppingCartAccessImplSockets.class.getName()).log(Level.SEVERE, "No hubo conexión con el servidor", ex);
        }
         if (jsonResponse == null) {
             try {
                 throw new Exception("No se pudo conectar con el servidor. Revise la red o que el servidor esté escuchando. ");
             } catch (Exception ex) {
                 Logger.getLogger(ShoppingCartAccessImplSockets.class.getName()).log(Level.SEVERE, null, ex);
             }
        } else {
             if (jsonResponse.contains("error")) {
                //Devolvió algún error                
                Logger.getLogger(ShoppingCartAccessImplSockets.class.getName()).log(Level.INFO, jsonResponse);
                 try {
                     throw new Exception(extractMessages(jsonResponse));
                     
                 } catch (Exception ex) {
                     Logger.getLogger(ShoppingCartAccessImplSockets.class.getName()).log(Level.SEVERE, null, ex);                     
                 }
                 
            } else {
                Invoice invoice = jsonToInvoice(jsonResponse);
                Logger.getLogger(CategoryAccessImplSockets.class.getName()).log(Level.INFO, "Lo que va en el JSon: ({0})", jsonResponse);
                return invoice;               
            }
        } 
        return null;
    }
    
    private String doBuyRequestJson(int id, creditCard paymentMethod,int userId) {

        Protocol protocol = new Protocol();
        protocol.setResource("shoppingCart");
        protocol.setAction("buy");
        protocol.addParameter("id", Integer.toString(id));
        protocol.addParameter("nameOnCard",paymentMethod.getNameOnCard() );
        protocol.addParameter("cardNumber",paymentMethod.getCardNumber() );
        protocol.addParameter("CVC", paymentMethod.getCVC());
        protocol.addParameter("month",paymentMethod.getMonth() );
        protocol.addParameter("year", paymentMethod.getYear());
        protocol.addParameter("userID",Integer.toString(userId));
       
        Gson gson = new Gson();
        String requestJson = gson.toJson(protocol);
        return requestJson;
    }

    private String extractMessages(String jsonResponse) {
        JsonError[] errors = jsonToErrors(jsonResponse);
        String msjs = "";
        for (JsonError error : errors) {
            msjs += error.getMessage();
        }
        return msjs;
    }
    
    private JsonError[] jsonToErrors(String jsonError) {
        Gson gson = new Gson();
        JsonError[] error = gson.fromJson(jsonError, JsonError[].class);
        return error;
    }
    
    private Invoice jsonToInvoice(String jsonCustomer) {
        Gson gson = new Gson();
        Invoice invoice = gson.fromJson(jsonCustomer, Invoice.class);
        return invoice;
    }
    
}
