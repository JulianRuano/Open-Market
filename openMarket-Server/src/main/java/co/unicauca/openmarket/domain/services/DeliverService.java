/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.unicauca.openmarket.domain.services;

import co.unicauca.openmarket.commons.application.Invoice;
import co.unicauca.openmarket.server.access.IDeliverRepository;
import java.util.List;

/**
 *
 * @author brayan
 */
public class DeliverService {
    
    IDeliverRepository repo;
    
    public DeliverService(IDeliverRepository repo){
        this.repo = repo;
    
    }
    
    public synchronized double qualification(String idCompra, int puntuacion,int userID){
         return repo.qualification(idCompra, puntuacion, userID);
    }
    
    public synchronized List<Invoice> billList(int userID){
        return repo.billList(userID);
    }
    
    public synchronized double balance(int userID){
        return repo.balance(userID);
    }
    
    public synchronized boolean updateBalance(String idCompra,int userID){
        return repo.UpdateBalance(idCompra, userID);
    }
}
