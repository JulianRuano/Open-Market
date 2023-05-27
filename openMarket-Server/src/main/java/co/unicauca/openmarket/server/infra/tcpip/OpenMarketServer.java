/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.unicauca.openmarket.server.infra.tcpip;

import co.unicauca.openmarket.server.infra.tcpip.OpenMarketHandler;
import co.unicauca.openmarket.domain.services.CategoryService;
import co.unicauca.openmarket.domain.services.ProductService;
import co.unicauca.openmarket.server.access.CategoryRepository;
import co.unicauca.openmarket.server.access.ProductRepository;
import co.unicauca.strategyserver.infra.ServerSocketMultiThread;
import java.util.Scanner;

/**
 *
 * @author brayan majin, julian ruano
 */
public class OpenMarketServer {
     /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        System.out.println("Ingrese el puerto de escucha");
        int port = teclado.nextInt();
        ServerSocketMultiThread myServer = new ServerSocketMultiThread(port);
        OpenMarketHandler myHandler = new OpenMarketHandler();       
        myHandler.setCategoryService(new CategoryService(new CategoryRepository()));
        myHandler.setProductService(new ProductService(new ProductRepository()));   
        myServer.setServerHandler(myHandler);
        myServer.startServer();
    }
}
