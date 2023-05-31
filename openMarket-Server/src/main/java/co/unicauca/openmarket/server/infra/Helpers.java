/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.unicauca.openmarket.server.infra;

/**
 *
 * @author Jorge
 */
public class Helpers {

    public String generateBadRequestJson(Context context) {
        String message;
        switch (context) {
            case PRODUCT ->
                message = "Error, un producto con ese id ya existe";
            case CATEGORY ->
                message = "Error, una categoria con ese id ya existe";
            case SHOPPING ->
                 message = "Ha ocurrido un error con la tarjeta";
            default ->
                throw new IllegalArgumentException("Contexto desconocido: " + context);
        }

        return new ErrorResponse("400", "BAD_REQUEST", message).toJson();
    }

    public String generateNotFoundErrorJson(Context context) {
        String message;
        switch (context) {
            case PRODUCT ->
                message = "Error, un producto con ese id no existe";
            case CATEGORY ->
                message = "Error, una categoria con ese id no existe";
            case SHOPPING ->
                 message = "Error, La tarjeta de credito no existe";
            case USER ->
                 message = "Error, El usuario no existe";
            // Añade más casos aquí en el futuro
            default ->
                throw new IllegalArgumentException("Contexto desconocido: " + context);
        }

        return new ErrorResponse("404", "NOT_FOUND", message).toJson();
    }
}
