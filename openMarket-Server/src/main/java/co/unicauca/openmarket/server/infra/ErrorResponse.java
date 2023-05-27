/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.unicauca.openmarket.server.infra;

import co.unicauca.openmarket.commons.infra.JsonError;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jorge
 */
public class ErrorResponse {
    private List<JsonError> errors;

    public ErrorResponse(String code, String error, String message) {
        errors = new ArrayList<>();
        JsonError jsonError = new JsonError();
        jsonError.setCode(code);
        jsonError.setError(error);
        jsonError.setMessage(message);
        errors.add(jsonError);
    }

    public String toJson() {
        Gson gson = new Gson();
        return gson.toJson(errors);
    }
}
