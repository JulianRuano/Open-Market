/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package co.unicauca.openmarket.presentacion;

import co.unicauca.openmarket.client.domain.service.DeliverService;
import static co.unicauca.openmarket.client.infra.Messages.successMessage;
import co.unicauca.openmarket.commons.application.Invoice;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author fre90
 */
public class MisCompras extends javax.swing.JPanel {

   DefaultTableModel mModeloTabla = new DefaultTableModel();
   private DeliverService deliverService;
   int userID=0;
    public MisCompras(DeliverService deliverService, int userID) {
        initComponents();
        initStyles();
        initializeTable() ;
        mModeloTabla.addColumn("Referencia");
        mModeloTabla.addColumn("Producto");
        mModeloTabla.addColumn("Total");
        mModeloTabla.addColumn("Fecha");
        this.userID=userID;
        this.deliverService=deliverService;
       initTable(userID);
    }
     private void initializeTable() {
        tablaCompras.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "Reference", "Producto", "Total", "Fecha"
                }
        ));
    }
    private void initTable(int UserID){
        try{
            fillTable(deliverService.billListService(userID));
        } catch (NullPointerException ex) {
            JOptionPane.showMessageDialog(null,
                    "Error al traer los datos",
                    "Error Datos No Encontrados",
                    JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            successMessage(e.getMessage(), "Atención");
            JOptionPane.showMessageDialog(null,
                    "Error, notifiquelo al administrador",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }    
    }
    private void initStyles(){
        
        this.btnEntrega.putClientProperty("JButton.buttonType", "roundRect");
        this.cbxCalification.putClientProperty ( "JComponent.roundRect", true );
        this.txtCodProduct.putClientProperty ( "JComponent.roundRect", true );  
    }
     
    private void fillTable(List<Invoice> listaInvoice) {
        tablaCompras.setDefaultRenderer(Object.class, new RenderImagen());
        DefaultTableModel model = (DefaultTableModel) tablaCompras.getModel();

        Object rowData[] = new Object[4];//No columnas
        for (int i = 0; i < listaInvoice.size(); i++) {
            rowData[0] = listaInvoice.get(i).getReference();
            rowData[1] = listaInvoice.get(i).getNameProduct();
            rowData[2] = listaInvoice.get(i).getTotal();
            rowData[3] = listaInvoice.get(i).getFecha();
            
            model.addRow(rowData);
        }

    
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlCompras = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaCompras = new javax.swing.JTable();
        btnEntrega = new javax.swing.JButton();
        txtCodProduct = new javax.swing.JTextField();
        lblRecibido = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        cbxCalification = new javax.swing.JComboBox<>();

        setLayout(new java.awt.BorderLayout());

        tablaCompras.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        tablaCompras.setForeground(new java.awt.Color(0, 0, 0));
        tablaCompras.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tablaCompras);

        btnEntrega.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnEntrega.setForeground(new java.awt.Color(0, 0, 0));
        btnEntrega.setText("Recibido");
        btnEntrega.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEntrega.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEntregaActionPerformed(evt);
            }
        });

        txtCodProduct.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        txtCodProduct.setForeground(new java.awt.Color(0, 0, 0));

        lblRecibido.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        lblRecibido.setForeground(new java.awt.Color(0, 0, 0));
        lblRecibido.setText("Digite el codigo del producto recibido: ");

        jLabel1.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Ingrese su calificacion del producto: ");

        cbxCalification.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        cbxCalification.setForeground(new java.awt.Color(0, 0, 0));
        cbxCalification.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5" }));

        javax.swing.GroupLayout pnlComprasLayout = new javax.swing.GroupLayout(pnlCompras);
        pnlCompras.setLayout(pnlComprasLayout);
        pnlComprasLayout.setHorizontalGroup(
            pnlComprasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlComprasLayout.createSequentialGroup()
                .addGroup(pnlComprasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlComprasLayout.createSequentialGroup()
                        .addGap(116, 116, 116)
                        .addGroup(pnlComprasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 758, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(pnlComprasLayout.createSequentialGroup()
                                .addGroup(pnlComprasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblRecibido)
                                    .addComponent(jLabel1))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(pnlComprasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtCodProduct, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                                    .addComponent(cbxCalification, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                    .addGroup(pnlComprasLayout.createSequentialGroup()
                        .addGap(357, 357, 357)
                        .addComponent(btnEntrega, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(143, Short.MAX_VALUE))
        );
        pnlComprasLayout.setVerticalGroup(
            pnlComprasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlComprasLayout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 385, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addGroup(pnlComprasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblRecibido)
                    .addComponent(txtCodProduct, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlComprasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cbxCalification, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnEntrega, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(139, Short.MAX_VALUE))
        );

        add(pnlCompras, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void btnEntregaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEntregaActionPerformed
        try{
            String refernce=this.txtCodProduct.getText();
            int calification=Integer.parseInt(this.cbxCalification.getSelectedItem().toString());
            if(deliverService.confirmService(refernce, calification, userID)==true){
                    JOptionPane.showMessageDialog(null,
                    "Entrega Exitosa",
                    "Entrega",
                    JOptionPane.INFORMATION_MESSAGE);
            }else{
                 JOptionPane.showMessageDialog(null,
                    "Error al realizar la Entrega",
                    "Error Entrega",
                    JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (NullPointerException ex) {
            JOptionPane.showMessageDialog(null,
                    "Error al traer los datos",
                    "Error Datos No Encontrados",
                    JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            successMessage(e.getMessage(), "Atención");
            JOptionPane.showMessageDialog(null,
                    "Error, notifiquelo al administrador",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }   
        
    }//GEN-LAST:event_btnEntregaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEntrega;
    private javax.swing.JComboBox<String> cbxCalification;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblRecibido;
    private javax.swing.JPanel pnlCompras;
    private javax.swing.JTable tablaCompras;
    private javax.swing.JTextField txtCodProduct;
    // End of variables declaration//GEN-END:variables
}
