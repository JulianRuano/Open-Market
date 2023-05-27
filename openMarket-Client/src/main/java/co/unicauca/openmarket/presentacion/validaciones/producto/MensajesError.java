/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.unicauca.openmarket.presentacion.validaciones.producto;

/**
 *
 * @author Jorge
 */
public enum MensajesError {
    CODIGO_PRODUCTO("Categoria del producto"),
    NOMBRE_PRODUCTO("Nombre del producto"),
    DESCRIPCION_PRODUCTO("Descripción del producto"),
    PRECIO_PRODUCTO("Precio del producto"),
    STOCK_PRODUCTO("Stock del producto"),
    CATEGORIA_PRODUCTO("Categoria del producto"),
    DIRECCION_PRODUCTO("Direccion del producto");

    private final String mensaje;

    private MensajesError(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getMensaje() {
        return mensaje;
    }
}

