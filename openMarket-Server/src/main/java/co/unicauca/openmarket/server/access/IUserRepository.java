/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.unicauca.openmarket.server.access;

import co.unicauca.openmarket.commons.domain.User;

/**
 *
 * @author brayan
 */
public interface IUserRepository {
   User login(User user);
   boolean  register(User user);
}
