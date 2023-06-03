/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.unicauca.openmarket.server.infra.tcpip;
import co.unicauca.openmarket.domain.services.CategoryService;
import co.unicauca.openmarket.domain.services.DeliverService;
import co.unicauca.openmarket.domain.services.PaymentService;
import co.unicauca.openmarket.domain.services.ProductService;
import co.unicauca.openmarket.domain.services.UserService;
import co.unicauca.openmarket.server.access.Factory;
import co.unicauca.openmarket.server.access.ICategoryRepository;
import co.unicauca.openmarket.server.access.IDeliverRepository;
import co.unicauca.openmarket.server.access.IPaymentRepository;
import co.unicauca.openmarket.server.access.IProductRepository;
import co.unicauca.openmarket.server.access.IUserRepository;
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
        ICategoryRepository repository  = Factory.getInstance().getCatRepository("default");
        IProductRepository  repository2 = Factory.getInstance().getProdRepository("default");
        IPaymentRepository  repository3 = Factory.getInstance().getPayRepository("default");
        IUserRepository  repository4 = Factory.getInstance().getUserRepository("default");
        IDeliverRepository repository5 = Factory.getInstance().getDeliverRepository("default");
        
        myHandler.setCategoryService(new CategoryService(repository));
        myHandler.setProductService(new ProductService(repository2));
        myHandler.setPaymentService(new PaymentService(repository3));
        myHandler.setUserService(new UserService(repository4));
        myHandler.setDeliverService(new DeliverService(repository5));

        myServer.setServerHandler(myHandler);
        myServer.startServer();
    }
}
