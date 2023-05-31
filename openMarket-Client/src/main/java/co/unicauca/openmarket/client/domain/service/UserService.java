/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.unicauca.openmarket.client.domain.service;

import co.unicauca.openmarket.client.access.ILoginAccess;
import co.unicauca.openmarket.commons.domain.User;

/**
 *
 * @author brayan
 */
public class UserService {
    private ILoginAccess repository;
    
    
    public boolean loginService(String username,String contrasenia){
        User user=new User();
        user.setUsername(username);
        user.setContrasenia(contrasenia);
        return repository.login(user);
    }
    
}
