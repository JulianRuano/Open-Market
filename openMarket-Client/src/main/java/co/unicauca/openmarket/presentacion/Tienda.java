
package co.unicauca.openmarket.presentacion;

import co.unicauca.openmarket.client.domain.application.ShoppingCar;
import co.unicauca.openmarket.client.domain.service.CategoryService;
import co.unicauca.openmarket.client.domain.service.ProductService;
import static co.unicauca.openmarket.client.infra.Messages.successMessage;
import co.unicauca.openmarket.commons.domain.Product;
import co.unicauca.openmarket.presentacion.helpers.PresentacionHelpers;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author brayan
 */
public class Tienda extends javax.swing.JPanel {

    /**
     * Creates new form Tienda
     */
    DefaultTableModel mModeloTabla = new DefaultTableModel();

    private final ProductService productService;
    private final GUIPaymet compra;
    private PresentacionHelpers presHelpers;
    private CategoryService categoryService;
    private int idUser; 

    public Tienda(ProductService productService, ShoppingCar shoppingCart, CategoryService categoryService,int idUser) {
        initComponents();
        this.productService = productService;
        this.categoryService = categoryService;
        mModeloTabla.addColumn("Nombre");
        mModeloTabla.addColumn("Descripcion");
        mModeloTabla.addColumn("Precio");
        mModeloTabla.addColumn("Direccion");
        mModeloTabla.addColumn("Categoria");        
        mModeloTabla.addColumn("Imagen");
        mModeloTabla.addColumn("Calificacion");  
        this.idUser = idUser;
        presHelpers = new PresentacionHelpers();
        presHelpers.loadCategories(cbxCategories, categoryService);
        tblProductos.setModel(mModeloTabla);
        compra = new GUIPaymet(shoppingCart);
        
        try {
            fillTable(productService.findAllProducts());
        } catch (Exception ex) {
            successMessage(ex.getMessage(), "Atención");
        }
        
        initStyles();
    }
     private void initStyles(){
        
        this.btnBuscar.putClientProperty("JButton.buttonType", "roundRect");
        this.btnComprar.putClientProperty("JButton.buttonType", "roundRect");
        this.txtNombre.putClientProperty ( "JComponent.roundRect", true );
        this.txtCodProduct.putClientProperty ( "JComponent.roundRect", true );
        this.txtMaxPrice.putClientProperty ( "JComponent.roundRect", true );
        this.txtMinPrice.putClientProperty ( "JComponent.roundRect", true );
        this.cbxCategories.putClientProperty ( "JComponent.roundRect", true );
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlTienda = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblProductos = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        btnBuscar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        cbxCategories = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtMaxPrice = new javax.swing.JTextField();
        txtMinPrice = new javax.swing.JTextField();
        txtCodProduct = new javax.swing.JTextField();
        btnComprar = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();

        tblProductos.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        tblProductos.setForeground(new java.awt.Color(0, 0, 0));
        tblProductos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tblProductos);

        btnBuscar.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnBuscar.setForeground(new java.awt.Color(0, 0, 0));
        btnBuscar.setText("Aplicar filtros");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 24)); // NOI18N
        jLabel1.setText("Buscar Productos");

        jLabel2.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Nombre");

        txtNombre.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        txtNombre.setForeground(new java.awt.Color(0, 0, 0));

        cbxCategories.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        cbxCategories.setForeground(new java.awt.Color(0, 0, 0));

        jLabel3.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Categoria");

        jLabel4.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Precio mínimo");

        jLabel5.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Precio máximo");

        txtMaxPrice.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        txtMaxPrice.setForeground(new java.awt.Color(0, 0, 0));

        txtMinPrice.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        txtMinPrice.setForeground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(165, 165, 165)
                        .addComponent(jLabel1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(65, 65, 65)
                                .addComponent(jLabel4)))
                        .addGap(82, 82, 82)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(cbxCategories, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(178, 178, 178)
                        .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(107, 107, 107)
                        .addComponent(txtMinPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(49, 49, 49)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(txtMaxPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbxCategories, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMinPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMaxPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        txtCodProduct.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        txtCodProduct.setForeground(new java.awt.Color(0, 0, 0));

        btnComprar.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnComprar.setForeground(new java.awt.Color(0, 0, 0));
        btnComprar.setText("Comprar");
        btnComprar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnComprarActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Ingrese el id del producto a comprar: ");

        javax.swing.GroupLayout pnlTiendaLayout = new javax.swing.GroupLayout(pnlTienda);
        pnlTienda.setLayout(pnlTiendaLayout);
        pnlTiendaLayout.setHorizontalGroup(
            pnlTiendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTiendaLayout.createSequentialGroup()
                .addGroup(pnlTiendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlTiendaLayout.createSequentialGroup()
                        .addGap(143, 143, 143)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlTiendaLayout.createSequentialGroup()
                        .addGap(85, 85, 85)
                        .addGroup(pnlTiendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlTiendaLayout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtCodProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnComprar, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 695, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(240, Short.MAX_VALUE))
        );
        pnlTiendaLayout.setVerticalGroup(
            pnlTiendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTiendaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22)
                .addGroup(pnlTiendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtCodProduct, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnComprar, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 96, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlTienda, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlTienda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
    DefaultTableModel model = (DefaultTableModel) tblProductos.getModel();
    model.setRowCount(0);
     String prodName = txtNombre.getText();
    if ( txtNombre.getText().isEmpty()) {
        prodName = null; 
    }
    Double minPrice = null;

    if (!txtMinPrice.getText().isEmpty()) {
        minPrice = Double.valueOf(  txtMinPrice.getText());
    }
    
    Double maxPrice = null;

    if (!txtMaxPrice.getText().isEmpty()) {
        maxPrice = Double.valueOf(  txtMaxPrice.getText());
    }
    

    
    try {
        fillTable(productService.filterProducts(prodName, presHelpers.selectedCategoryId, minPrice, maxPrice));
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null,
                e.getMessage(),
                "Ocurrió un error al buscar los productos",
                JOptionPane.ERROR_MESSAGE);
    }

    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnComprarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnComprarActionPerformed
        try{
            int idProducto=Integer.parseInt(this.txtCodProduct.getText());          
            compra.obtenerProducto(productService.findProductById(idProducto),idUser);
            compra.setVisible(true);
        }catch(Exception e){
            successMessage(e.getMessage(), "Atención"); 
            JOptionPane.showMessageDialog(null,
              "Seleccione por el dato que quiere buscar",
              "Error al introducir el dato",
              JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnComprarActionPerformed
    private void fillTable(List<Product> listProducts) {
        tblProductos.setDefaultRenderer(Object.class, new RenderImagen());
        DefaultTableModel model = (DefaultTableModel) tblProductos.getModel();
        
        

        Object rowData[] = new Object[6];//No columnas
        for (int i = 0; i < listProducts.size(); i++) {
            rowData[0] = listProducts.get(i).getName();
            rowData[1] = listProducts.get(i).getDescription();
            rowData[2] = listProducts.get(i).getPrice();
            rowData[3] = listProducts.get(i).getAddress();
            try {
                String catName=categoryService.findCategoryById(listProducts.get(i).getCategoryId()).getName();
                        rowData[4] = catName;
            } catch (Exception ex) {
                Logger.getLogger(Tienda.class.getName()).log(Level.SEVERE, null, ex);
            }
    
            

            try {
                byte[] imagen = listProducts.get(i).getImage();
                BufferedImage bufferedImage = null;
                InputStream inputStream = new ByteArrayInputStream(imagen);
                bufferedImage = ImageIO.read(inputStream);
                ImageIcon mIcono = new ImageIcon(bufferedImage.getScaledInstance(80, 80, 0));
                rowData[5] = new JLabel(mIcono);
            } catch (Exception e) {
                rowData[5] = new JLabel("No imagen");
            }

            model.addRow(rowData);
        }

        tblProductos.setRowHeight(80);
        tblProductos.getColumnModel().getColumn(0).setPreferredWidth(80);
        tblProductos.getColumnModel().getColumn(1).setPreferredWidth(80);
        tblProductos.getColumnModel().getColumn(2).setPreferredWidth(80);
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnComprar;
    private javax.swing.JComboBox<String> cbxCategories;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel pnlTienda;
    private javax.swing.JTable tblProductos;
    private javax.swing.JTextField txtCodProduct;
    private javax.swing.JTextField txtMaxPrice;
    private javax.swing.JTextField txtMinPrice;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables
}
