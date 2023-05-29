/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.unicauca.openmarket.presentacion;

import co.unicauca.openmarket.client.domain.application.ShoppingCar;
import co.unicauca.openmarket.client.domain.service.ProductService;
import static co.unicauca.openmarket.client.infra.Messages.successMessage;
import co.unicauca.openmarket.commons.domain.Product;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;
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
    private final ShoppingCar shoppingCart;
    int id;
    
    public Tienda(ProductService productService,ShoppingCar shoppingCart) {
       initComponents();
       this.productService=productService;
       this.shoppingCart=shoppingCart;
        mModeloTabla.addColumn("ID");
        mModeloTabla.addColumn("Nombre");
        mModeloTabla.addColumn("Descripcion");
        mModeloTabla.addColumn("Precio");
        mModeloTabla.addColumn("Direccion");
        mModeloTabla.addColumn("ID categoria");
        mModeloTabla.addColumn("Imagen");
        tblProductos.setModel(mModeloTabla); 
    }

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlTienda = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblProductos = new javax.swing.JTable();
        txtComprar = new javax.swing.JTextField();
        btnComprar2 = new javax.swing.JButton();
        txtBuscar = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();

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

        btnComprar2.setText("Comprar");
        btnComprar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnComprar2ActionPerformed(evt);
            }
        });

        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlTiendaLayout = new javax.swing.GroupLayout(pnlTienda);
        pnlTienda.setLayout(pnlTiendaLayout);
        pnlTiendaLayout.setHorizontalGroup(
            pnlTiendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(pnlTiendaLayout.createSequentialGroup()
                .addGroup(pnlTiendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlTiendaLayout.createSequentialGroup()
                        .addGap(103, 103, 103)
                        .addComponent(txtComprar, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addComponent(btnComprar2, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlTiendaLayout.createSequentialGroup()
                        .addGap(89, 89, 89)
                        .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        pnlTiendaLayout.setVerticalGroup(
            pnlTiendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTiendaLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(pnlTiendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(pnlTiendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtComprar, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnComprar2, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(59, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlTienda, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlTienda, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
          try{
              fillTable( productService.findProductsByName(this.txtBuscar.getText()));
          }catch(NullPointerException ex){
               JOptionPane.showMessageDialog(null,
                "Envia la informacion correspondiente",
                "Error tipo de dato",
                JOptionPane.ERROR_MESSAGE);
          }catch(Exception e){
               successMessage(e.getMessage(), "Atención"); 
              JOptionPane.showMessageDialog(null,
                "Seleccione por el dato que quiere buscar",
                "Error al introducir el dato",
                JOptionPane.ERROR_MESSAGE);
          }
        
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnComprar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnComprar2ActionPerformed
         
    }//GEN-LAST:event_btnComprar2ActionPerformed
     private void fillTable(List<Product> listProducts) {
        DefaultTableModel model = (DefaultTableModel) tblProductos.getModel();

        Object rowData[] = new Object[7];//No columnas
        for (int i = 0; i < listProducts.size(); i++) {
            rowData[0] = listProducts.get(i).getProductId();
            rowData[1] = listProducts.get(i).getName();
            rowData[2] = listProducts.get(i).getDescription();
            rowData[3] = listProducts.get(i).getPrice();
            rowData[4] = listProducts.get(i).getAddress();
            rowData[5] = listProducts.get(i).getCategoryId();
            
            try {
                byte[] imagen = listProducts.get(i).getImage();
                BufferedImage bufferedImage = null;
                InputStream inputStream = new ByteArrayInputStream(imagen);
                bufferedImage = ImageIO.read(inputStream);
                ImageIcon mIcono = new ImageIcon(bufferedImage.getScaledInstance(60, 60, 0));
                rowData[6] = new JLabel(mIcono);
                } catch (Exception e) {
                    rowData[6] = new JLabel("No imagen");
                }
            
            model.addRow(rowData);
        }
        
        tblProductos.setRowHeight(60);
        tblProductos.getColumnModel().getColumn(0).setPreferredWidth(60);
        tblProductos.getColumnModel().getColumn(1).setPreferredWidth(60);
        tblProductos.getColumnModel().getColumn(2).setPreferredWidth(60);
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnComprar2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel pnlTienda;
    private javax.swing.JTable tblProductos;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtComprar;
    // End of variables declaration//GEN-END:variables
}
