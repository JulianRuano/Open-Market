/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.unicauca.openmarket.presentacion.validaciones;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jorge
 */
public class ValidadorCampos {


    public ValidadorCampos() {
        
    }

    public boolean validarCampoVacio(String campo) {
        return campo.trim().equals("");
    }

}

