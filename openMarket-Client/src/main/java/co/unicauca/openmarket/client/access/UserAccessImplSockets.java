/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.unicauca.openmarket.client.access;

import co.unicauca.openmarket.client.infra.OpenMarketSocket;
import co.unicauca.openmarket.commons.domain.User;
import co.unicauca.openmarket.commons.infra.JsonError;
import co.unicauca.openmarket.commons.infra.Protocol;
import com.google.gson.Gson;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author brayan
 */
public class UserAccessImplSockets implements IUserAccess {
    private OpenMarketSocket mySocket;

    public UserAccessImplSockets() {
        this.mySocket =new OpenMarketSocket();
    }
    
    
    @Override
    public User login(User user)throws Exception {
       
        String jsonResponse = null;
        String requestJson = doLoginRequestJson(user);
         try {
            mySocket.connect();
            jsonResponse = mySocket.sendRequest(requestJson);
            mySocket.disconnect();

        } catch (IOException ex) {
            Logger.getLogger(UserAccessImplSockets.class.getName()).log(Level.SEVERE, "No hubo conexión con el servidor", ex);
        }
        if (jsonResponse == null) {
               throw new Exception("No se pudo conectar con el servidor. Revise la red o que el servidor esté escuchando. ");
        } else {
            if (jsonResponse.contains("error")) {
                //Devolvió algún error
                Logger.getLogger(UserAccessImplSockets.class.getName()).log(Level.INFO, jsonResponse+"aqi estoy");
                    throw new Exception(extractMessages(jsonResponse));
 
            } else {
                
              User newUser= jsonToUser(jsonResponse);
                Logger.getLogger(UserAccessImplSockets.class.getName()).log(Level.INFO, "Lo que va en el JSon: {0}", jsonResponse );
                return newUser;
            }
        }
    } 
     @Override
    public boolean register(User user)throws Exception {
       boolean bandera=false;
        String jsonResponse = null;
        String requestJson = doRegisterRequestJson(user);
        try {
            mySocket.connect();
            jsonResponse = mySocket.sendRequest(requestJson);
            mySocket.disconnect();

        } catch (IOException ex) {
            Logger.getLogger(UserAccessImplSockets.class.getName()).log(Level.SEVERE, "No hubo conexión con el servidor", ex);
        }
         if (jsonResponse == null) {
            throw new Exception("No se pudo conectar con el servidor. Revise la red o que el servidor esté escuchando. ");
        } else {
            if (jsonResponse.contains("error")) {
                //Devolvió algún error
                Logger.getLogger(UserAccessImplSockets.class.getName()).log(Level.INFO, jsonResponse+"aqi estoy");
                throw new Exception(extractMessages(jsonResponse));
               
            } else {
                //Encontró el customer
                
                Logger.getLogger(UserAccessImplSockets.class.getName()).log(Level.INFO, "Lo que va en el JSon: ("+user.getFirstName());
                bandera=true;
            }
        }
        return bandera;
    }

    private String doLoginRequestJson(User user) {
        Protocol protocol = new Protocol();
        protocol.setResource("user");
        protocol.setAction("get");
        
        protocol.addParameter("username", user.getUsername() );
        protocol.addParameter("name",user.getContrasenia());
        
        Gson gson = new Gson();
        String requestJson = gson.toJson(protocol);
        return requestJson;
    }
    private String doRegisterRequestJson(User user) {
       Protocol protocol = new Protocol();
        protocol.setResource("user");
        protocol.setAction("post"); 
        protocol.addParameter("name",user.getFirstName());
        protocol.addParameter("lastName",user.getLastName());
        protocol.addParameter("rol",user.getRol());
        protocol.addParameter("email",user.getEmail());
        protocol.addParameter("username",user.getUsername());
        protocol.addParameter("password", user.getContrasenia());
        
        Gson gson = new Gson();
        String requestJson = gson.toJson(protocol);
        return requestJson;
    }
     
    /**
    * Convierte jsonProduct, proveniente del server socket, de json a un
    * objeto product
    *
    * @param jsonProduct objeto cliente en formato json
    */
    private User jsonToUser(String jsonUser) {

        Gson gson = new Gson();
        User user = gson.fromJson(jsonUser, User.class);
        return user;

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

    

   
   

    

   
}
