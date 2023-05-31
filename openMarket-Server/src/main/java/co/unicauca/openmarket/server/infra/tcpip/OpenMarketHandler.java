/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.unicauca.openmarket.server.infra.tcpip;

import co.unicauca.openmarket.commons.application.Invoice;
import co.unicauca.openmarket.server.infra.Context;
import co.unicauca.openmarket.commons.application.creditCard;
import co.unicauca.openmarket.commons.domain.Category;
import co.unicauca.openmarket.commons.domain.Product;
import co.unicauca.openmarket.commons.domain.User;
import co.unicauca.openmarket.commons.infra.Protocol;
import co.unicauca.openmarket.domain.services.CategoryService;
import co.unicauca.strategyserver.infra.ServerHandler;
import co.unicauca.openmarket.domain.services.PaymentService;
import co.unicauca.openmarket.domain.services.ProductService;
import co.unicauca.openmarket.domain.services.UserService;
import co.unicauca.openmarket.server.application.GenerateReference.Reference;
import co.unicauca.openmarket.server.application.CreditCardPayment;
import co.unicauca.openmarket.server.application.PaymentHandler;
import co.unicauca.openmarket.server.infra.Helpers;
import com.google.gson.Gson;
import java.util.Base64;
import java.util.List;

/**
 *
 * @author brayan majin, julian ruano
 */
public class OpenMarketHandler extends ServerHandler {

    /**
     * Servicio de categoria servicio de producto
     */
    private static ProductService productService;
    private static CategoryService categoryService;
    private static UserService userService;
    private static PaymentService paymentService;
    private static Helpers helpers;

    public OpenMarketHandler() {
        this.helpers = new Helpers();
    }

    /**
     * Procesar la solicitud que proviene de la aplicación cliente
     *
     * @param requestJson
     */
    @Override
    public String processRequest(String requestJson) {
        // Convertir la solicitud a objeto Protocol para poderlo procesar
        Gson gson = new Gson();
        Protocol protocolRequest;
        protocolRequest = gson.fromJson(requestJson, Protocol.class);
        String response = "";
        switch (protocolRequest.getResource()) {
            case "category" -> {
                if (protocolRequest.getAction().equals("get")) {
                    // Consultar una categoria
                    response = processGetCategory(protocolRequest);
                }
                if (protocolRequest.getAction().equals("post")) {
                    // Agregar una categoria    
                    response = processPostCategory(protocolRequest);
                }
                if (protocolRequest.getAction().equals("edit")) {
                    // Editar categoria
                    response = processEditCategory(protocolRequest);
                }
                if (protocolRequest.getAction().equals("delete")) {
                    //Eliminar categoria
                    response = processDeleteCategory(protocolRequest);
                }
                if (protocolRequest.getAction().equals("listCategory")) {
                    //Lista de las categoria
                    response = processListCategory();
                }
                if (protocolRequest.getAction().equals("getListCategory")) {
                    //Listar categorias por nombre
                    response = processGetListCategory(protocolRequest);
                }
                break;
            }
            case "product" -> {
                if (protocolRequest.getAction().equals("get")) {
                    // Consultar un producto por ide
                    response = processGetProduct(protocolRequest);
                }
                if (protocolRequest.getAction().equals("post")) {
                    // Agregar un nuevo producto  
                    response = processPostProduct(protocolRequest);
                }
                if (protocolRequest.getAction().equals("edit")) {
                    // Editar un producto
                    response = processEditProduct(protocolRequest);
                }
                if (protocolRequest.getAction().equals("delete")) {
                    // eliminar un producto
                    response = processDeleteProduct(protocolRequest);
                }
                if (protocolRequest.getAction().equals("listProductName")) {
                    // Editar un producto
                    response = processListNameProduct(protocolRequest);
                }
                if (protocolRequest.getAction().equals("listProductCategory")) {
                    //listar los productos por categoria
                    response = processListCategoryProduct(protocolRequest);
                }
                if (protocolRequest.getAction().equals("listAllProduct")) {
                    // Editar un producto
                    response = processListAllProduct();
                }
                break;
            }
            case "shoppingCart" -> {
                if (protocolRequest.getAction().equals("buy")) {
                    // Petecion de compra
                    response = processBuyProduct(protocolRequest);
                }
            }
            case "user"->{
                if (protocolRequest.getAction().equals("get")) {
                    // Peticion de usuario
                    response = processUserLogin(protocolRequest);
                }
                if(protocolRequest.getAction().equals("post")){
                     response = processRegister(protocolRequest);
                }
                
            }
        }
        return response;
    }

    /**
     * Procesa la solicitud de consultar una categoria
     *
     * @param protocolRequest Protocolo de la solicitud
     */
    private String processGetCategory(Protocol protocolRequest) {
        // Extraer la cedula del primer parámetro
        int id = Integer.parseInt(protocolRequest.getParameters().get(0).getValue());
        Category category = categoryService.findById(id);
        if (category == null) {
            return helpers.generateNotFoundErrorJson(Context.CATEGORY);
        } else {
            return objectToJSON(category);
        }
    }

    /**
     * Procesa la solicitud de agregar una categoria
     *
     * @param protocolRequest Protocolo de la solicitud
     */
    private String processPostCategory(Protocol protocolRequest) {
        int id = Integer.parseInt(protocolRequest.getParameters().get(0).getValue());

        Category category = this.categoryService.findById(id);

        if (!(category == null)) {
            return helpers.generateBadRequestJson(Context.CATEGORY);
        }

        category = new Category();
        // Reconstruir La categoria a partir de lo que viene en los parámetros
        category.setCategoryId(id);
        category.setName(protocolRequest.getParameters().get(1).getValue());
        int response = categoryService.save(category);
        return String.valueOf(response);
    }

    private String processEditCategory(Protocol protocolRequest) {
        // Editar el name de la categoria
        int id = Integer.parseInt(protocolRequest.getParameters().get(0).getValue());
        String name = protocolRequest.getParameters().get(1).getValue();
        Category newCategory = new Category(id, name);
        boolean response = categoryService.edit(id, newCategory);
        String respuesta = String.valueOf(response);
        return respuesta;
    }

    private String processDeleteCategory(Protocol protocolRequest) {
        // Eliminar una categoria 
        int id = Integer.parseInt(protocolRequest.getParameters().get(0).getValue());
        boolean response = categoryService.delete(id);
        String respuesta = String.valueOf(response);
        return respuesta;
    }

    private String processListCategory() {
        // Lista de todas las categorias
        List<Category> category;
        category = categoryService.findAll();
        return objectToJSON(category);
    }

    private String processGetListCategory(Protocol protocolRequest) {
        //Listar categorias por nombre 
        String name = protocolRequest.getParameters().get(0).getValue();
        List<Category> category;
        category = categoryService.findByName(name);
        return objectToJSON(category);
    }

    private String processGetProduct(Protocol protocolRequest) {
        // Extraer la cedula del primer parámetro
        int id = Integer.parseInt(protocolRequest.getParameters().get(0).getValue());
        Product producto = productService.findById(id);
        if (producto == null) {
           return helpers.generateNotFoundErrorJson(Context.PRODUCT);
        } else {
            return objectToJSON(producto);
        }
    }

    private String processPostProduct(Protocol protocolRequest) {
        Product producto = new Product();
        // Reconstruir La categoria a partir de lo que viene en los parámetros
        producto.setProductId(Integer.parseInt(protocolRequest.getParameters().get(0).getValue()));
        producto.setName(protocolRequest.getParameters().get(1).getValue());
        producto.setDescription(protocolRequest.getParameters().get(2).getValue());
        producto.setPrice(Double.parseDouble(protocolRequest.getParameters().get(3).getValue()));
        producto.setAddress(protocolRequest.getParameters().get(4).getValue());
        producto.setCategoryId(Integer.parseInt(protocolRequest.getParameters().get(5).getValue()));
        producto.setStock(Integer.parseInt(protocolRequest.getParameters().get(6).getValue()));

        byte[] decodedImage = Base64.getDecoder().decode(protocolRequest.getParameters().get(7).getValue());
        producto.setImage(decodedImage);

        int response = productService.save(producto);
        return String.valueOf(response);
    }

    private String processEditProduct(Protocol protocolRequest) {
        Product producto = new Product();
        // Reconstruir La categoria a partir de lo que viene en los parámetros
        producto.setProductId(Integer.parseInt(protocolRequest.getParameters().get(0).getValue()));
        producto.setName(protocolRequest.getParameters().get(1).getValue());
        producto.setDescription(protocolRequest.getParameters().get(2).getValue());
        producto.setPrice(Double.parseDouble(protocolRequest.getParameters().get(3).getValue()));
        producto.setAddress(protocolRequest.getParameters().get(4).getValue());
        producto.setCategoryId(Integer.parseInt(protocolRequest.getParameters().get(5).getValue()));
        producto.setStock(Integer.parseInt(protocolRequest.getParameters().get(6).getValue()));
        byte[] decodedImage = Base64.getDecoder().decode(protocolRequest.getParameters().get(7).getValue());
        producto.setImage(decodedImage);

        boolean response = productService.edit(producto);
        String respuesta = String.valueOf(response);
        return respuesta;
    }

    private String processBuyProduct(Protocol protocolRequest) {
        // Petecion de compra
        int idProduct = Integer.parseInt(protocolRequest.getParameters().get(0).getValue());
        String nameOnCard = protocolRequest.getParameters().get(1).getValue();
        String cardNumber = protocolRequest.getParameters().get(2).getValue();
        String CVC = protocolRequest.getParameters().get(3).getValue();
        String month = protocolRequest.getParameters().get(4).getValue();
        String year = protocolRequest.getParameters().get(5).getValue();

        
        // Contruiomos los objectos      
        PaymentHandler paymentHandler = new PaymentHandler();      
        creditCard paymentDetails = new creditCard(nameOnCard, cardNumber, CVC, month, year);
        String details = paymentDetails.getDetails();
        CreditCardPayment creditCardPayment  = new CreditCardPayment();
        paymentHandler.setPaymentStrategy(creditCardPayment);

        if (!paymentHandler.processPayment(details)) {
            return helpers.generateBadRequestJson(Context.SHOPPING);
        } else {
            List<Object> productNamePrice = productService.findNamePrice(idProduct);
            String productName = productNamePrice.get(0).toString();
            double price = (double)productNamePrice.get(1);           
            
            String reference = Reference.getReference();
            paymentService.save(reference,details);            
            paymentService.linkProduct(reference, idProduct);        
            Invoice invoice = paymentService.findById(reference);
            return objectToJSON(invoice);
        }
    }

    /**
     * @return the service
     */
    public CategoryService getCategoryService() {
        return categoryService;
    }

    public ProductService getProductService() {
        return productService;
    }

    /**
     * @param service the service to set
     */
    public void setProductService(ProductService service) {
        productService = service;
    }

    public void setCategoryService(CategoryService service) {
        categoryService = service;
    }
    
    public void setPaymentService(PaymentService service) {
        paymentService = service;
    }

    public static UserService getUserService() {
        return userService;
    }

    public  void setUserService(UserService userService) {
        this.userService = userService;
    }
    

    private String processDeleteProduct(Protocol protocolRequest) {
        // Eliminar una categoria 
        int id = Integer.parseInt(protocolRequest.getParameters().get(0).getValue());
        boolean response = productService.delete(id);
        String respuesta = String.valueOf(response);
        return respuesta;
    }

    private String processListNameProduct(Protocol protocolRequest) {
        List<Product> productos;
        String name = protocolRequest.getParameters().get(0).getValue();
        productos = productService.findByName(name);
        return objectToJSON(productos);

    }

    private String processListCategoryProduct(Protocol protocolRequest) {
        List<Product> productos;
        int categoryId = Integer.parseInt(protocolRequest.getParameters().get(0).getValue());
        productos = productService.findByCategory(categoryId);
        return objectToJSON(productos);
    }

    private String processListAllProduct() {
        List<Product> productos;
        productos = productService.findAll();
        return objectToJSON(productos);
    }
    private String processUserLogin(Protocol protocolRequest) {
     User user=new User();
      user.setUsername(protocolRequest.getParameters().get(0).getValue());
      user.setContrasenia(protocolRequest.getParameters().get(1).getValue());
      
       if(userService.login(user)==null){
             return helpers.generateNotFoundErrorJson(Context.CATEGORY);
       }else{
           return objectToJSON(userService.login(user));
        }
         
    }

    private String processRegister(Protocol protocolRequest) {
        User user=new User();
        user.setFirstName(protocolRequest.getParameters().get(0).getValue());
        user.setLastName(protocolRequest.getParameters().get(1).getValue());
        user.setRol(protocolRequest.getParameters().get(2).getValue());
        user.setEmail(protocolRequest.getParameters().get(3).getValue());
        user.setUsername(protocolRequest.getParameters().get(4).getValue());
        user.setContrasenia(protocolRequest.getParameters().get(5).getValue());
        
        boolean response=userService.register(user);
        String respuesta=String.valueOf(response);
        return respuesta;
        
    }
}
