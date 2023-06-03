
package co.unicauca.openmarket.client.access;

import co.unicauca.openmarket.client.infra.OpenMarketSocket;
import co.unicauca.openmarket.commons.application.Invoice;
import co.unicauca.openmarket.commons.infra.JsonError;
import co.unicauca.openmarket.commons.infra.Protocol;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;



/**
 *
 * @author brayan
 */
public class DeliverAccessImplSockets implements IDeliverAccess{

     private OpenMarketSocket mySocket;

    public DeliverAccessImplSockets() {
        mySocket = new OpenMarketSocket();
    }
    
    @Override
    public double qualification(String reference, int punctuation, int userID)throws Exception {
        double avg= 0;
        String jsonResponse = null;
        String requestJson = doQualificationRequestJson(reference,punctuation,userID);
        try {
            mySocket.connect();
            jsonResponse = mySocket.sendRequest(requestJson);
            mySocket.disconnect();

        } catch (IOException ex) {
            Logger.getLogger(DeliverAccessImplSockets.class.getName()).log(Level.SEVERE, "No hubo conexión con el servidor", ex);
        }
        if (jsonResponse == null) {
            throw new Exception("No se pudo conectar con el servidor");
        } else {

            if (jsonResponse.contains("error")) {
                //Devolvió algún error                
                Logger.getLogger(DeliverAccessImplSockets.class.getName()).log(Level.INFO, jsonResponse);
                throw new Exception(extractMessages(jsonResponse));
            } else {
                double average = Double.parseDouble(jsonResponse);
                Logger.getLogger(DeliverAccessImplSockets.class.getName()).log(Level.INFO, "Lo que va en el JSon: ({0})",average);
               avg = average;

            }
        }
        return avg;
    }

    @Override
    public double priceToPay(String reference, int userID)throws Exception {
         double valor= 0;
        String jsonResponse = null;
        String requestJson = priceToPayRequestJson(reference,userID);
        try {
            mySocket.connect();
            jsonResponse = mySocket.sendRequest(requestJson);
            mySocket.disconnect();

        } catch (IOException ex) {
            Logger.getLogger(DeliverAccessImplSockets.class.getName()).log(Level.SEVERE, "No hubo conexión con el servidor", ex);
        }
        if (jsonResponse == null) {
            throw new Exception("No se pudo conectar con el servidor");
        } else {

            if (jsonResponse.contains("error")) {
                //Devolvió algún error                
                Logger.getLogger(DeliverAccessImplSockets.class.getName()).log(Level.INFO, jsonResponse);
                throw new Exception(extractMessages(jsonResponse));
            } else {
                double balance = Double.parseDouble(jsonResponse);
                Logger.getLogger(DeliverAccessImplSockets.class.getName()).log(Level.INFO, "Lo que va en el JSon: ({0})",balance);
               valor = balance;

            }
        }
        return valor;
    }
    
    
    
    private String priceToPayRequestJson(String reference, int userID) {
        Protocol protocol=new Protocol();
        protocol.setResource("shoppingCart");
        protocol.setAction("get");
        
        protocol.addParameter("reference", reference);
        protocol.addParameter("UserID", Integer.toString(userID));
        
        Gson gson = new Gson();
        String requestJson = gson.toJson(protocol);

        return requestJson;
    }
    private String doQualificationRequestJson(String reference, int punctuation, int userID) {
      Protocol protocol=new Protocol();
        protocol.setResource("shoppingCart");
        protocol.setAction("confirm");
        
        protocol.addParameter("reference", reference);
        protocol.addParameter("punctuation", Integer.toString(punctuation));
        protocol.addParameter("UserID", Integer.toString(userID));
        
        Gson gson = new Gson();
        String requestJson = gson.toJson(protocol);

        return requestJson;
    }
    private String doListRequestJson(int userID) {
         Protocol protocol = new Protocol();
        protocol.setResource("shoppingCart");
        protocol.setAction("billList");
        protocol.addParameter("userID", Integer.toString(userID));
        
        Gson gson = new Gson();
        String requestJson = gson.toJson(protocol);
        return requestJson;
    }
    
    private List<Invoice> jsonToListInvoice(String jsonCustomer) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();
        List<Invoice> listaInvoice = objectMapper.readValue(jsonCustomer, new TypeReference<List<Invoice>>() {
        });

        return listaInvoice;
    }
    
    
    /**
     * Convierte jsonProduct, proveniente del server socket, de json a un objeto
     * Invoice
     *
     * @param jsonProduct objeto cliente en formato json
     */
    private Invoice jsonToInvoice(String jsonProduct) {

        Gson gson = new Gson();
        Invoice invoice= gson.fromJson(jsonProduct, Invoice.class);
        return invoice;

    }

    private String extractMessages(String jsonResponse) {
        JsonError[] errors = jsonToErrors(jsonResponse);
        String msjs = "";
        for (JsonError error : errors) {
            msjs += error.getMessage();
        }
        return msjs;
    }

    /**
     * Convierte el jsonError a un array de objetos jsonError
     *
     * @param jsonError
     * @return objeto MyError
     */
    private JsonError[] jsonToErrors(String jsonError) {
        Gson gson = new Gson();
        JsonError[] error = gson.fromJson(jsonError, JsonError[].class);
        return error;
    }

    @Override
    public List<Invoice> billList(int userID) throws Exception {
       String jsonResponse = null;
        String requestJson = doListRequestJson(userID);
        System.out.println(requestJson);
        try {
            mySocket.connect();
            jsonResponse = mySocket.sendRequest(requestJson);
            mySocket.disconnect();

        } catch (IOException ex) {
            Logger.getLogger(CategoryAccessImplSockets.class.getName()).log(Level.SEVERE, "No hubo conexión con el servidor", ex);
        }
        if (jsonResponse == null) {
            return null;
           // throw new Exception("No se pudo conectar con el servidor. Revise la red o que el servidor esté escuchando. ");
        } else {
            if (jsonResponse.contains("error")) {
                //Devolvió algún error
                Logger.getLogger(CategoryAccessImplSockets.class.getName()).log(Level.INFO, jsonResponse);
               // throw new Exception(extractMessages(jsonResponse));
               return null;
            } else {
                //Encontró una category           
                List<Invoice> lista = null;
                try {
                    lista = jsonToListInvoice(jsonResponse);
                } catch (JsonProcessingException ex) {
                    Logger.getLogger(CategoryAccessImplSockets.class.getName()).log(Level.SEVERE, null, ex);
                }
                Logger.getLogger(CategoryAccessImplSockets.class.getName()).log(Level.INFO, "Lo que va en el JSon: ({0})", jsonResponse);
                return lista;
            }
        }     
    }

    

   

  
   

    
}
