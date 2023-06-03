/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.unicauca.openmarket.presentacion;

import co.unicauca.openmarket.client.domain.application.ShoppingCar;
import co.unicauca.openmarket.client.domain.service.CategoryService;
import co.unicauca.openmarket.client.domain.service.DeliverService;
import co.unicauca.openmarket.client.domain.service.ProductService;
import co.unicauca.openmarket.client.presentation.commands.OMInvoker;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JPanel;


/**
 *
 * @author brayan
 */
public class Dashboard extends javax.swing.JFrame {

     private final ProductService productService;
     private final CategoryService categoryService;
     private final DeliverService deliverService;
    private final ShoppingCar shoppingCart;
    //private final UserService userService;
    private GUILogin instance;
    private final OMInvoker ominvokerCategorias;
    private final OMInvoker ominvokerProducts;
    private  int idUser;
    
    public Dashboard(ProductService productService,CategoryService categoryService, ShoppingCar shoppingCart,GUILogin login,DeliverService deliverService) {
        initComponents();
        initStyles();
        setExtendedState(MAXIMIZED_BOTH);
        this.productService=productService;
        this.categoryService=categoryService;
        this.shoppingCart=shoppingCart;
        this.deliverService=deliverService;
        //this.userService=userService;
        instance = login;       
        ominvokerCategorias = new OMInvoker();
        ominvokerProducts=new OMInvoker();

        this.btnProducto.setVisible(false);
        this.btnCategoria.setVisible(false);
        this.lblUsername.setText(login.UserName());
        this.idUser = login.idLogin();
        if (idUser != 0){
            this.btnProducto.setVisible(true);
            this.btnCategoria.setVisible(true);

        }
    }
    private void initStyles(){
      //.putClientProperty("JButton.buttonType", "roundRect");
      //para el diseño del titulo y el color de la letra
      lblTituloHeader.putClientProperty("FlatLaf.style", "font: 40 $light.font");
      lblTituloHeader.setForeground(Color.white);
    }

    private void ShowJPanel(JPanel p){
        p.setSize(1200,600);
        p.setLocation(0,0);
        
        this.pnlContenidoIzquierdo.removeAll();
        this.pnlContenidoIzquierdo.add(p,BorderLayout.CENTER);
        this.pnlContenidoIzquierdo.revalidate();
        this.pnlContenidoIzquierdo.repaint();
        
        
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        background = new javax.swing.JPanel();
        pnlBarraLateral = new javax.swing.JPanel();
        btnCategoria = new javax.swing.JButton();
        btnProducto = new javax.swing.JButton();
        btnComprar = new javax.swing.JButton();
        btnUsuarios = new javax.swing.JButton();
        btnCompras = new javax.swing.JButton();
        lblIconFacebook = new javax.swing.JLabel();
        lblIconWhatssap = new javax.swing.JLabel();
        lblIconTwitter = new javax.swing.JLabel();
        pnlHeader = new javax.swing.JPanel();
        lblTituloHeader = new javax.swing.JLabel();
        btnIniciarSesion = new javax.swing.JButton();
        lblUsername = new javax.swing.JLabel();
        lblUsernameIcon = new javax.swing.JLabel();
        pnlContenidoIzquierdo = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1200, 600));
        setSize(new java.awt.Dimension(0, 0));

        pnlBarraLateral.setBackground(new java.awt.Color(63, 132, 171));

        btnCategoria.setFont(new java.awt.Font("Dialog", 0, 22)); // NOI18N
        btnCategoria.setForeground(new java.awt.Color(0, 0, 0));
        btnCategoria.setIcon(new javax.swing.ImageIcon(getClass().getResource("/book-content-solid-48.png"))); // NOI18N
        btnCategoria.setText("Categoria");
        btnCategoria.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 10, 1, 1, new java.awt.Color(0, 0, 0)));
        btnCategoria.setBorderPainted(false);
        btnCategoria.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCategoria.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnCategoria.setIconTextGap(10);
        btnCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCategoriaActionPerformed(evt);
            }
        });

        btnProducto.setFont(new java.awt.Font("Dialog", 0, 22)); // NOI18N
        btnProducto.setForeground(new java.awt.Color(0, 0, 0));
        btnProducto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/product_delivery_icon_152013.png"))); // NOI18N
        btnProducto.setText("Producto");
        btnProducto.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 10, 1, 1, new java.awt.Color(0, 0, 0)));
        btnProducto.setBorderPainted(false);
        btnProducto.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnProducto.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnProducto.setIconTextGap(10);
        btnProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProductoActionPerformed(evt);
            }
        });

        btnComprar.setFont(new java.awt.Font("Dialog", 0, 22)); // NOI18N
        btnComprar.setForeground(new java.awt.Color(0, 0, 0));
        btnComprar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tienda.png"))); // NOI18N
        btnComprar.setText("Tienda");
        btnComprar.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 10, 1, 1, new java.awt.Color(0, 0, 0)));
        btnComprar.setBorderPainted(false);
        btnComprar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnComprar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnComprar.setIconTextGap(10);
        btnComprar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnComprarActionPerformed(evt);
            }
        });

        btnUsuarios.setFont(new java.awt.Font("Dialog", 0, 22)); // NOI18N
        btnUsuarios.setForeground(new java.awt.Color(0, 0, 0));
        btnUsuarios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/user-solid-48.png"))); // NOI18N
        btnUsuarios.setText("Usuarios");
        btnUsuarios.setBorder(null);
        btnUsuarios.setBorderPainted(false);
        btnUsuarios.setCursor(new java.awt.Cursor(java.awt.Cursor.MOVE_CURSOR));
        btnUsuarios.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnUsuarios.setIconTextGap(10);

        btnCompras.setFont(new java.awt.Font("Dialog", 0, 22)); // NOI18N
        btnCompras.setForeground(new java.awt.Color(0, 0, 0));
        btnCompras.setIcon(new javax.swing.ImageIcon(getClass().getResource("/shopping-cart_icon-icons.com_72552.png"))); // NOI18N
        btnCompras.setText("Mis Compras");
        btnCompras.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 10, 1, 1, new java.awt.Color(0, 0, 0)));
        btnCompras.setBorderPainted(false);
        btnCompras.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCompras.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnCompras.setIconTextGap(10);
        btnCompras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnComprasActionPerformed(evt);
            }
        });

        lblIconFacebook.setIcon(new javax.swing.ImageIcon(getClass().getResource("/facebook-circle-logo-36.png"))); // NOI18N

        lblIconWhatssap.setIcon(new javax.swing.ImageIcon(getClass().getResource("/whatsapp-square-logo-36.png"))); // NOI18N

        lblIconTwitter.setIcon(new javax.swing.ImageIcon(getClass().getResource("/twitter-logo-36.png"))); // NOI18N

        javax.swing.GroupLayout pnlBarraLateralLayout = new javax.swing.GroupLayout(pnlBarraLateral);
        pnlBarraLateral.setLayout(pnlBarraLateralLayout);
        pnlBarraLateralLayout.setHorizontalGroup(
            pnlBarraLateralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnProducto, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnCategoria, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnComprar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnCompras, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnUsuarios, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pnlBarraLateralLayout.createSequentialGroup()
                .addGap(94, 94, 94)
                .addComponent(lblIconFacebook)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblIconWhatssap)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblIconTwitter)
                .addContainerGap(38, Short.MAX_VALUE))
        );
        pnlBarraLateralLayout.setVerticalGroup(
            pnlBarraLateralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBarraLateralLayout.createSequentialGroup()
                .addGap(108, 108, 108)
                .addComponent(btnCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnComprar, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnCompras, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnUsuarios, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlBarraLateralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblIconFacebook, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblIconWhatssap, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblIconTwitter, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(75, 75, 75))
        );

        pnlHeader.setBackground(new java.awt.Color(80, 157, 199));

        lblTituloHeader.setText("Open Market");

        btnIniciarSesion.setBackground(new java.awt.Color(153, 153, 153));
        btnIniciarSesion.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        btnIniciarSesion.setForeground(new java.awt.Color(0, 0, 0));
        btnIniciarSesion.setText("Iniciar Sesion");
        btnIniciarSesion.setCursor(new java.awt.Cursor(java.awt.Cursor.MOVE_CURSOR));
        btnIniciarSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIniciarSesionActionPerformed(evt);
            }
        });

        lblUsername.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        lblUsername.setText("jLabel1");

        lblUsernameIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/user-circle-regular-36.png"))); // NOI18N

        javax.swing.GroupLayout pnlHeaderLayout = new javax.swing.GroupLayout(pnlHeader);
        pnlHeader.setLayout(pnlHeaderLayout);
        pnlHeaderLayout.setHorizontalGroup(
            pnlHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlHeaderLayout.createSequentialGroup()
                .addGap(355, 355, 355)
                .addComponent(lblTituloHeader)
                .addGap(142, 142, 142)
                .addComponent(btnIniciarSesion, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(95, 95, 95)
                .addComponent(lblUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(lblUsernameIcon)
                .addContainerGap(420, Short.MAX_VALUE))
        );
        pnlHeaderLayout.setVerticalGroup(
            pnlHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlHeaderLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblUsernameIcon)
                    .addGroup(pnlHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblTituloHeader)
                        .addComponent(btnIniciarSesion)
                        .addComponent(lblUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(7, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlContenidoIzquierdoLayout = new javax.swing.GroupLayout(pnlContenidoIzquierdo);
        pnlContenidoIzquierdo.setLayout(pnlContenidoIzquierdoLayout);
        pnlContenidoIzquierdoLayout.setHorizontalGroup(
            pnlContenidoIzquierdoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1381, Short.MAX_VALUE)
        );
        pnlContenidoIzquierdoLayout.setVerticalGroup(
            pnlContenidoIzquierdoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 689, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout backgroundLayout = new javax.swing.GroupLayout(background);
        background.setLayout(backgroundLayout);
        backgroundLayout.setHorizontalGroup(
            backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(backgroundLayout.createSequentialGroup()
                .addComponent(pnlBarraLateral, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlHeader, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlContenidoIzquierdo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        backgroundLayout.setVerticalGroup(
            backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(backgroundLayout.createSequentialGroup()
                .addComponent(pnlHeader, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlContenidoIzquierdo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(pnlBarraLateral, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(background, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(background, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProductoActionPerformed
      
        crudProducto instance2=new crudProducto(this.productService,this.ominvokerProducts,categoryService);
        ShowJPanel(instance2);
        productService.addObservador(instance2);
    }//GEN-LAST:event_btnProductoActionPerformed


    private void btnComprarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnComprarActionPerformed
        ShowJPanel(new Tienda(productService,shoppingCart,this.categoryService,idUser) );
    }//GEN-LAST:event_btnComprarActionPerformed

    private void btnCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCategoriaActionPerformed
        crudCategoria instance = new crudCategoria(this.categoryService, this.ominvokerCategorias);
        ShowJPanel(instance);
        this.categoryService.addObservador(instance);
    }//GEN-LAST:event_btnCategoriaActionPerformed

    private void btnIniciarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIniciarSesionActionPerformed
         instance.setVisible(true);
    }//GEN-LAST:event_btnIniciarSesionActionPerformed

    private void btnComprasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnComprasActionPerformed
       ShowJPanel(new MisCompras(deliverService,idUser) );
    }//GEN-LAST:event_btnComprasActionPerformed


   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel background;
    private javax.swing.JButton btnCategoria;
    private javax.swing.JButton btnComprar;
    private javax.swing.JButton btnCompras;
    private javax.swing.JButton btnIniciarSesion;
    private javax.swing.JButton btnProducto;
    private javax.swing.JButton btnUsuarios;
    private javax.swing.JLabel lblIconFacebook;
    private javax.swing.JLabel lblIconTwitter;
    private javax.swing.JLabel lblIconWhatssap;
    private javax.swing.JLabel lblTituloHeader;
    private javax.swing.JLabel lblUsername;
    private javax.swing.JLabel lblUsernameIcon;
    private javax.swing.JPanel pnlBarraLateral;
    private javax.swing.JPanel pnlContenidoIzquierdo;
    private javax.swing.JPanel pnlHeader;
    // End of variables declaration//GEN-END:variables
}
