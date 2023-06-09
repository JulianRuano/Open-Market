package co.unicauca.openmarket.client.access;

import co.unicauca.openmarket.client.infra.OpenMarketSocket;
import co.unicauca.openmarket.commons.domain.Product;
import co.unicauca.openmarket.commons.infra.JsonError;
import co.unicauca.openmarket.commons.infra.Protocol;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import com.google.gson.Gson;
import java.io.IOException;
import java.util.Base64;
import java.util.List;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Es una implementación que tiene libertad de hacer una implementación del
 * contrato. Lo puede hacer con Sqlite, postgres, mysql, u otra tecnología
 *
 * @author brayan majin, julian ruano
 */
public class ProductAccessImplSockets implements IProductAccess {

    private OpenMarketSocket mySocket;

    public ProductAccessImplSockets() {
        mySocket = new OpenMarketSocket();
    }

    @Override
    public int save(Product newProduct) throws Exception {

        int id = 0;

        String jsonResponse = null;
        String requestJson = doSaveProductRequestJson(newProduct);
        try {
            mySocket.connect();
            jsonResponse = mySocket.sendRequest(requestJson);
            mySocket.disconnect();

        } catch (IOException ex) {
            Logger.getLogger(ProductAccessImplSockets.class.getName()).log(Level.SEVERE, "No hubo conexión con el servidor", ex);
        }
        if (jsonResponse == null) {
            throw new Exception("No se pudo conectar con el servidor");
        } else {

            if (jsonResponse.contains("error")) {
                //Devolvió algún error                
                Logger.getLogger(ProductAccessImplSockets.class.getName()).log(Level.INFO, jsonResponse);
                throw new Exception(extractMessages(jsonResponse));
            } else {
                int idProduct = Integer.parseInt(jsonResponse);
                Logger.getLogger(ProductAccessImplSockets.class.getName()).log(Level.INFO, "Lo que va en el JSon: ({0})", idProduct);
                id = idProduct;
            }
        }
        return id;
    }

    @Override
    public boolean edit(Product producto) throws Exception {
        boolean bandera = false;
        String jsonResponse = null;
        String requestJson = doEditproductRequestJson(producto);
        try {
            mySocket.connect();
            jsonResponse = mySocket.sendRequest(requestJson);
            mySocket.disconnect();

        } catch (IOException ex) {
            Logger.getLogger(ProductAccessImplSockets.class.getName()).log(Level.SEVERE, "No hubo conexión con el servidor", ex);
        }
        if (jsonResponse == null) {
            throw new Exception("No se pudo conectar con el servidor. Revise la red o que el servidor esté escuchando. ");
        } else {
            if (jsonResponse.contains("error")) {
                //Devolvió algún error
                Logger.getLogger(ProductAccessImplSockets.class.getName()).log(Level.INFO, jsonResponse + "aqi estoy");
                throw new Exception(extractMessages(jsonResponse));

            } else {
                //Encontró el product

                Logger.getLogger(ProductAccessImplSockets.class.getName()).log(Level.INFO, "Lo que va en el JSon: (" + producto.getName());
                bandera = true;
            }
        }
        return bandera;
    }

    @Override
    public boolean delete(int id) throws Exception {
        boolean bandera = false;
        String jsonResponse = null;
        String requestJson = doDeleteproductRequestJson(id);
        try {
            mySocket.connect();
            jsonResponse = mySocket.sendRequest(requestJson);
            mySocket.disconnect();

        } catch (IOException ex) {
            Logger.getLogger(ProductAccessImplSockets.class.getName()).log(Level.SEVERE, "No hubo conexión con el servidor", ex);
        }
        if (jsonResponse == null) {
            try {
                throw new Exception("No se pudo conectar con el servidor. Revise la red o que el servidor esté escuchando. ");
            } catch (Exception ex) {
                Logger.getLogger(ProductAccessImplSockets.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            if (jsonResponse.contains("error")) {
                //Devolvió algún error
                Logger.getLogger(ProductAccessImplSockets.class.getName()).log(Level.INFO, jsonResponse + "aqi estoy");
                try {
                    throw new Exception(extractMessages(jsonResponse));
                } catch (Exception ex) {
                    Logger.getLogger(ProductAccessImplSockets.class.getName()).log(Level.SEVERE, null, ex);
                }

            } else {
                //Encontró la categoria

                Logger.getLogger(ProductAccessImplSockets.class.getName()).log(Level.INFO, "Lo que va en el JSon: {0}", requestJson);
                bandera = true;
            }
        }

        return bandera;

    }

    @Override
    public Product findById(int id) throws Exception {
        String jsonResponse = null;
        String requestJson = doFindProductIdRequestJson(id);
        System.out.println(requestJson);
        try {
            mySocket.connect();
            jsonResponse = mySocket.sendRequest(requestJson);
            mySocket.disconnect();

        } catch (IOException ex) {
            Logger.getLogger(ProductAccessImplSockets.class.getName()).log(Level.SEVERE, "No hubo conexión con el servidor", ex);
        }
        if (jsonResponse == null) {

            throw new Exception("No se pudo conectar con el servidor. Revise la red o que el servidor esté escuchando. ");
            //return null;
        } else {
            if (jsonResponse.contains("error")) {
                //Devolvió algún error
                Logger.getLogger(ProductAccessImplSockets.class.getName()).log(Level.INFO, jsonResponse);
                throw new Exception(extractMessages(jsonResponse));
                //return null;
            } else {
                //Encontró el category
                Product product = jsonToProduct(jsonResponse);
                Logger.getLogger(ProductAccessImplSockets.class.getName()).log(Level.INFO, "Lo que va en el JSon: (" + jsonResponse.toString() + ")");
                return product;
            }
        }

    }

    @Override
    public List<Product> findByName(String name) throws Exception {
        String jsonResponse = null;
        String requestJson = doListProductNameRequestJson(name);
        System.out.println(requestJson);
        try {
            mySocket.connect();
            jsonResponse = mySocket.sendRequest(requestJson);
            mySocket.disconnect();

        } catch (IOException ex) {
            Logger.getLogger(CategoryAccessImplSockets.class.getName()).log(Level.SEVERE, "No hubo conexión con el servidor", ex);
        }
        if (jsonResponse == null) {
            return null;
            // throw new Exception("No se pudo conectar con el servidor. Revise la red o que el servidor esté escuchando. ");
        } else {
            if (jsonResponse.contains("error")) {
                //Devolvió algún error
                Logger.getLogger(ProductAccessImplSockets.class.getName()).log(Level.INFO, jsonResponse);
                // throw new Exception(extractMessages(jsonResponse));
                return null;
            } else {
                //Encontró el category           
                List<Product> listProduct = null;
                try {
                    listProduct = jsonToListCategory(jsonResponse);
                } catch (JsonProcessingException ex) {
                    Logger.getLogger(ProductAccessImplSockets.class.getName()).log(Level.SEVERE, null, ex);
                }
                Logger.getLogger(ProductAccessImplSockets.class.getName()).log(Level.INFO, "Lo que va en el JSon: ({0})", jsonResponse);
                return listProduct;
            }
        }
    }

    @Override
    public List<Product> findByCategory(int categoryName) throws Exception {
        String jsonResponse = null;
        String requestJson = doListProductCategoryRequestJson(categoryName);
        System.out.println(requestJson);
        try {
            mySocket.connect();
            jsonResponse = mySocket.sendRequest(requestJson);
            mySocket.disconnect();

        } catch (IOException ex) {
            Logger.getLogger(CategoryAccessImplSockets.class.getName()).log(Level.SEVERE, "No hubo conexión con el servidor", ex);
        }
        if (jsonResponse == null) {
            return null;
            // throw new Exception("No se pudo conectar con el servidor. Revise la red o que el servidor esté escuchando. ");
        } else {
            if (jsonResponse.contains("error")) {
                //Devolvió algún error
                Logger.getLogger(CategoryAccessImplSockets.class.getName()).log(Level.INFO, jsonResponse);
                // throw new Exception(extractMessages(jsonResponse));
                return null;
            } else {
                //Encontró el category           
                List<Product> listProduct = null;
                try {
                    listProduct = jsonToListCategory(jsonResponse);
                } catch (JsonProcessingException ex) {
                    Logger.getLogger(CategoryAccessImplSockets.class.getName()).log(Level.SEVERE, null, ex);
                }
                Logger.getLogger(ProductAccessImplSockets.class.getName()).log(Level.INFO, "Lo que va en el JSon: ({0})", jsonResponse);
                return listProduct;
            }
        }
    }

    @Override
    public List<Product> findAll() throws Exception {
        String jsonResponse = null;
        String requestJson = doListAllProductRequestJson();
        System.out.println(requestJson);
        try {
            mySocket.connect();
            jsonResponse = mySocket.sendRequest(requestJson);
            mySocket.disconnect();

        } catch (IOException ex) {
            Logger.getLogger(ProductAccessImplSockets.class.getName()).log(Level.SEVERE, "No hubo conexión con el servidor", ex);
        }
        if (jsonResponse == null) {
            return null;
            // throw new Exception("No se pudo conectar con el servidor. Revise la red o que el servidor esté escuchando. ");
        } else {
            if (jsonResponse.contains("error")) {
                //Devolvió algún error
                Logger.getLogger(ProductAccessImplSockets.class.getName()).log(Level.INFO, jsonResponse);
                // throw new Exception(extractMessages(jsonResponse));
                return null;
            } else {
                //Encontró el category           
                List<Product> listProduct = null;
                try {
                    listProduct = jsonToListCategory(jsonResponse);
                } catch (JsonProcessingException ex) {
                    Logger.getLogger(ProductAccessImplSockets.class.getName()).log(Level.SEVERE, null, ex);
                }
                Logger.getLogger(ProductAccessImplSockets.class.getName()).log(Level.INFO, "Lo que va en el JSon: ({0})", jsonResponse);
                return listProduct;
            }
        }
    }

    /**
     * Crea la solicitud json de creación de la Product para ser enviado por el
     * socket
     *
     * @param Poduct objeto newProduct
     * @return devulve algo como:
     * {"resource":"product,"action":"post","parameters":[{"name":"id","value":"1"},{"name":"name","value":"lacteos"},...}]}
     */
    private String doSaveProductRequestJson(Product product) {
        Protocol protocol = new Protocol();

        protocol.setResource("product");
        protocol.setAction("post");

        protocol.addParameter("productId", Integer.toString(product.getProductId()));
        protocol.addParameter("name", product.getName());
        protocol.addParameter("description", product.getDescription());
        protocol.addParameter("price", Double.toString(product.getPrice()));
        protocol.addParameter("address", product.getAddress());
        protocol.addParameter("CategoryId", Integer.toString(product.getCategoryId()));
        protocol.addParameter("stock", Integer.toString(product.getStock()));

        byte[] byteArray = product.getImage()/* Tu arreglo de bytes de la imagen */;
        String encodedImage = Base64.getEncoder().encodeToString(byteArray);
        protocol.addParameter("image", encodedImage);

        Gson gson = new Gson();
        String requestJson = gson.toJson(protocol);
        return requestJson;
    }

    /**
     * Convierte jsonProduct, proveniente del server socket, de json a un objeto
     * product
     *
     * @param jsonProduct objeto cliente en formato json
     */
    private Product jsonToProduct(String jsonProduct) {

        Gson gson = new Gson();
        Product product = gson.fromJson(jsonProduct, Product.class);
        return product;

    }

    private String extractMessages(String jsonResponse) {
        JsonError[] errors = jsonToErrors(jsonResponse);
        String msjs = "";
        for (JsonError error : errors) {
            msjs += error.getMessage();
        }
        return msjs;
    }

    /**
     * Convierte el jsonError a un array de objetos jsonError
     *
     * @param jsonError
     * @return objeto MyError
     */
    private JsonError[] jsonToErrors(String jsonError) {
        Gson gson = new Gson();
        JsonError[] error = gson.fromJson(jsonError, JsonError[].class);
        return error;
    }

    /**
     * Crea una solicitud json para ser enviada por el socket
     *
     * @param productId identificación del producto
     * @return solicitud de consulta del producto en formato Json, algo como:
     * {"resource":"product","action":"get","parameters":[{"name":"id","value":""}]}
     */
    private String doFindProductIdRequestJson(int id) {
        Protocol protocol = new Protocol();
        protocol.setResource("product");
        protocol.setAction("get");
        protocol.addParameter("productId", Integer.toString(id));

        Gson gson = new Gson();
        String requestJson = gson.toJson(protocol);

        return requestJson;
    }

    /**
     * Crea una solicitud json para ser enviada por el socket
     *
     *
     * @param producto un producto con los siguentes atributos:id, nombre,
     * decripcion, idCategoria
     * @return solicitud de consulta del producto para editatr en formato Json,
     * algo como:
     * {"resource":"product","action":"edit","parameters":[{"productId":"id","value":"1"}]}
     */
    private String doEditproductRequestJson(Product product) {
        Protocol protocol = new Protocol();
        protocol.setResource("product");
        protocol.setAction("edit");

        protocol.addParameter("productId", Integer.toString(product.getProductId()));
        protocol.addParameter("name", product.getName());
        protocol.addParameter("description", product.getDescription());
        protocol.addParameter("price", Double.toString(product.getPrice()));
        protocol.addParameter("address", product.getAddress());
        protocol.addParameter("CategoryId", Integer.toString(product.getCategoryId()));
        protocol.addParameter("stock", Integer.toString(product.getStock()));

        byte[] byteArray = product.getImage()/* Tu arreglo de bytes de la imagen */;
        String encodedImage = Base64.getEncoder().encodeToString(byteArray);
        protocol.addParameter("image", encodedImage);

        Gson gson = new Gson();
        String requestJson = gson.toJson(protocol);
        return requestJson;
    }

    private String doDeleteproductRequestJson(int id) {
        Protocol protocol = new Protocol();
        protocol.setResource("product");
        protocol.setAction("delete");

        protocol.addParameter("id", Integer.toString(id));

        Gson gson = new Gson();
        String requestJson = gson.toJson(protocol);
        return requestJson;
    }

    private List<Product> jsonToListCategory(String jsonCustomer) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();
        List<Product> listCategory = objectMapper.readValue(jsonCustomer, new TypeReference<List<Product>>() {
        });

        return listCategory;
    }

    private String doListProductNameRequestJson(String name) {
        Protocol protocol = new Protocol();
        protocol.setResource("product");
        protocol.setAction("listProductName");
        protocol.addParameter("name", name);
        Gson gson = new Gson();
        String requestJson = gson.toJson(protocol);
        return requestJson;

    }

    private String doListProductCategoryRequestJson(int categoryId) {
        Protocol protocol = new Protocol();
        protocol.setResource("product");
        protocol.setAction("listProductCategory");
        protocol.addParameter("name", Integer.toString(categoryId));

        Gson gson = new Gson();
        String requestJson = gson.toJson(protocol);
        return requestJson;
    }

    private String doListAllProductRequestJson() {
        Protocol protocol = new Protocol();
        protocol.setResource("product");
        protocol.setAction("listAllProduct");

        Gson gson = new Gson();
        String requestJson = gson.toJson(protocol);
        return requestJson;
    }



    public List<Product> filterProducts(String prodName, Integer categoryId, Double minPrice, Double maxPrice) throws Exception {
        String jsonResponse = null;
        String requestJson = doListFilterProductRequestJson(prodName, categoryId, minPrice, maxPrice);
        System.out.println(requestJson);
        try {
            mySocket.connect();
            jsonResponse = mySocket.sendRequest(requestJson);
            mySocket.disconnect();

        } catch (IOException ex) {
            Logger.getLogger(ProductAccessImplSockets.class.getName()).log(Level.SEVERE, "No hubo conexión con el servidor", ex);
        }
        if (jsonResponse == null) {
            return null;
        } else {
            if (jsonResponse.contains("error")) {
                Logger.getLogger(ProductAccessImplSockets.class.getName()).log(Level.INFO, jsonResponse);
                return null;
            } else {
                List<Product> listProduct = null;
                try {
                    listProduct = jsonToListCategory(jsonResponse);
                } catch (JsonProcessingException ex) {
                    Logger.getLogger(CategoryAccessImplSockets.class.getName()).log(Level.SEVERE, null, ex);
                }
                Logger.getLogger(CategoryAccessImplSockets.class.getName()).log(Level.INFO, "Lo que va en el JSon: ({0})", jsonResponse);
                return listProduct;
            }
        }
    }

private String doListFilterProductRequestJson(String prodName, Integer categoryId, Double minPrice, Double maxPrice) {
    Protocol protocol = new Protocol();
    protocol.setResource("product");
    protocol.setAction("filterProducts");

    if (prodName != null) {
        protocol.addParameter("name", prodName);
    }

    if (categoryId != null) {
        protocol.addParameter("categoryId", String.valueOf(categoryId));
    }
    
    if (minPrice != null) {
        protocol.addParameter("minPrice", String.valueOf(minPrice));
    }

    if (maxPrice != null) {
        protocol.addParameter("maxPrice", String.valueOf(maxPrice));
    }

    Gson gson = new Gson();
    String requestJson = gson.toJson(protocol);
    return requestJson;
}

}
