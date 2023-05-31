package co.unicauca.openmarket.server.access;

import co.unicauca.openmarket.commons.infra.Utilities;

/**
 * Fabrica que se encarga de instanciar ProductRepository o cualquier otro que
 * se cree en el futuro.
 *
 * @author Libardo, Julio
 */
public class Factory {

    private static Factory instance;

    private Factory() {
    }

    /**
     * Clase singleton
     *
     * @return
     */
    public static Factory getInstance() {

        if (instance == null) {
            instance = new Factory();
        }
        return instance;

    }

    /**
     * Método que crea una instancia concreta de la jerarquia IProductRepository
     *
     * @param type cadena que indica qué tipo de clase hija debe instanciar
     * @return una clase hija de la abstracción IProductRepository
     */

    
    public ICategoryRepository getCatRepository(String type) {
         ICategoryRepository result = null;

        switch (type) {
            case "default" -> result = new CategoryRepository();

    }

        return result;

    }
    public IProductRepository getProdRepository(String type) {
         IProductRepository result = null;

        switch (type) {
            case "default" -> result = new ProductRepository();
        }
        return result;
    }
     public IUserRepository getUserRepository(String type) {
         IUserRepository result = null;
        switch (type) {
            case "default":
                result = new UserRepository();
            break;

    }

        return result;

    }
    
    public IPaymentRepository getPayRepository(String type) {
         IPaymentRepository result = null;

        switch (type) {
            case "default" -> result = new PaymentRepository();      
    }       
        return result;
    }

}
