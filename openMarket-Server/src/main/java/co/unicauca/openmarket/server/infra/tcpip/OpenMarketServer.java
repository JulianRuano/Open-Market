/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.unicauca.openmarket.server.infra.tcpip;

import co.unicauca.openmarket.server.infra.tcpip.OpenMarketHandler;
import co.unicauca.openmarket.server.access.CategoryRepositoryArrays;
import co.unicauca.openmarket.server.access.ProductRepositoryArrays;
import co.unicauca.openmarket.domain.services.CategoryService;
import co.unicauca.openmarket.domain.services.ProductService;
import co.unicauca.openmarket.server.access.ICategoryRepository;
import co.unicauca.openmarket.server.access.IProductRepository;
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
        ICategoryRepository catRepo= new CategoryRepositoryArrays();
        IProductRepository prodRepo= new ProductRepositoryArrays(catRepo);

        myHandler.setCategoryService(new CategoryService(catRepo));
        myHandler.setProductService(new ProductService(prodRepo));
        myServer.setServerHandler(myHandler);
        myServer.startServer();
    }
}