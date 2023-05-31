/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.unicauca.openmarket.client.domain.service;

import co.unicauca.openmarket.commons.domain.User;
import co.unicauca.openmarket.client.access.IUserAccess;

/**
 *
 * @author brayan
 */
public class UserService {
    private IUserAccess repository;

    public UserService(IUserAccess repository) {
        this.repository = repository;
    }
    
    
    public String loginService(String username,String contrasenia)throws Exception{
        User user=new User();
        user.setUsername(username);
        user.setContrasenia(contrasenia);
        return repository.login(user);
      
    }
    public boolean registerService(String  name,String lastName,String rol,String email,String userName,String contrasenia)throws Exception{
        User user=new User();
        user.setFirstName(name);
        user.setLastName(lastName);
        user.setRol(rol);
        user.setEmail(email);
        user.setUsername(userName);
        user.setContrasenia(contrasenia);
        return repository.register(user);
    }
}
