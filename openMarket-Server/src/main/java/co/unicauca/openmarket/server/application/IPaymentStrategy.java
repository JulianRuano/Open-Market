/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package co.unicauca.openmarket.server.application;

/**
 *
 * @author Julian Ruano
 */
public interface IPaymentStrategy {
    boolean processPayment(String details);
}
