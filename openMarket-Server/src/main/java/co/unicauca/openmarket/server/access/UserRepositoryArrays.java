/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.unicauca.openmarket.server.access;

import co.unicauca.openmarket.commons.domain.User;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author brayan
 */
public class UserRepositoryArrays implements IUserRepository{

    private static List<User>users;
    public UserRepositoryArrays(){
        if(users==null){
            users=new ArrayList<>();
        }
        if(users.size()==0){
            inicializar();
        }
    }
    
    
    private void inicializar() {
        users.add(new User(1,"Julian","Ruano","julio","1234","vendedor"));
        users.add(new User(2,"Freider","Escobar","freiderEscobar","1234","vendedor"));
        users.add(new User(3,"Jorge","Ayerbe","jorgito","1234","vendedor"));
        users.add(new User(4,"Naren","Imbachi","imbachi45","1234","comprador"));
        users.add(new User(5,"Ruben","Cruz","ruben123","1234","comprador"));
        
    }

    @Override
    public User login(User user) {
        User usuario=null;
      for(User element: users){
          if(element.getUsername().equals(user.getUsername())&&
                  element.getContrasenia().equals(user.getContrasenia())){
              return usuario=new User(element.getUsername(),element.getContrasenia());
          }
      }
      return usuario;
    }
   

    
    
}
