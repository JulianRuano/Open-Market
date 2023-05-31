
package co.unicauca.openmarket.presentacion;

import co.unicauca.openmarket.client.domain.service.UserService;
import static co.unicauca.openmarket.client.infra.Messages.successMessage;
import javax.swing.JOptionPane;

/**
 *
 * @author brayan
 */
public class GUIRegister extends javax.swing.JFrame {

    private UserService userService;
    public GUIRegister(UserService userService) {
        initComponents();
        this.userService=userService;
    }

  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlRegister = new javax.swing.JPanel();
        lblName = new javax.swing.JLabel();
        lblLastName = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        txtLastName = new javax.swing.JTextField();
        lblRole = new javax.swing.JLabel();
        txtmail = new javax.swing.JTextField();
        lblMail = new javax.swing.JLabel();
        txtUsername = new javax.swing.JTextField();
        lblUsername = new javax.swing.JLabel();
        lblTitulo = new javax.swing.JLabel();
        cbxRol = new javax.swing.JComboBox<>();
        lblPassword = new javax.swing.JLabel();
        btnRegistrarse = new javax.swing.JButton();
        txtPassword = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblName.setText("Nombre");

        lblLastName.setText("Apellido");

        lblRole.setText("Rol");

        lblMail.setText("Correo");

        lblUsername.setText("Usuario");

        lblTitulo.setText("Registrate");

        cbxRol.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Vendedor", "Comprador", " " }));

        lblPassword.setText("Contraseña");

        btnRegistrarse.setText("Registrarse");
        btnRegistrarse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarseActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlRegisterLayout = new javax.swing.GroupLayout(pnlRegister);
        pnlRegister.setLayout(pnlRegisterLayout);
        pnlRegisterLayout.setHorizontalGroup(
            pnlRegisterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlRegisterLayout.createSequentialGroup()
                .addGroup(pnlRegisterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlRegisterLayout.createSequentialGroup()
                        .addGap(200, 200, 200)
                        .addComponent(lblTitulo))
                    .addGroup(pnlRegisterLayout.createSequentialGroup()
                        .addGap(93, 93, 93)
                        .addGroup(pnlRegisterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblPassword)
                            .addComponent(lblUsername)
                            .addComponent(lblMail)
                            .addComponent(lblLastName)
                            .addComponent(lblName)
                            .addGroup(pnlRegisterLayout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(lblRole))
                            .addComponent(txtName)
                            .addComponent(txtLastName)
                            .addComponent(txtmail)
                            .addComponent(cbxRol, 0, 225, Short.MAX_VALUE)
                            .addComponent(txtUsername)
                            .addComponent(txtPassword)))
                    .addGroup(pnlRegisterLayout.createSequentialGroup()
                        .addGap(160, 160, 160)
                        .addComponent(btnRegistrarse)))
                .addContainerGap(139, Short.MAX_VALUE))
        );
        pnlRegisterLayout.setVerticalGroup(
            pnlRegisterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlRegisterLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(lblTitulo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblName)
                .addGap(5, 5, 5)
                .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblLastName)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtLastName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblRole)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbxRol, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(lblMail)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblUsername)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblPassword)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnRegistrarse)
                .addContainerGap(9, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlRegister, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlRegister, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnRegistrarseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarseActionPerformed
            
        
        try{
            String name=this.txtName.getText();
            String lastName=this.txtLastName.getText();
            String rol=(String)this.cbxRol.getSelectedItem();
            String email=this.txtmail.getText();
            String userName=this.txtUsername.getText();
            String contrasenia=this.txtPassword.getText();

            if(userService.registerService(name, lastName, rol, email, userName, contrasenia)){
              
             JOptionPane.showMessageDialog(null,
                    "Se registro correctamente",
                    "Registro Exitoso",
                    JOptionPane.INFORMATION_MESSAGE);
            }else{
                JOptionPane.showMessageDialog(null,
                    "ERROR, no se pudo Registrar",
                    "Registro Fallido",
                    JOptionPane.ERROR_MESSAGE);
            }
            
        } catch(Exception e){
            successMessage(e.getMessage(), "Atención");
             JOptionPane.showMessageDialog(null,
                    "ERROR, no se pudo conectar al servidor",
                    "Erroral conectar al servidor",
                    JOptionPane.ERROR_MESSAGE);
        }
        
            
            
    }//GEN-LAST:event_btnRegistrarseActionPerformed

   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnRegistrarse;
    private javax.swing.JComboBox<String> cbxRol;
    private javax.swing.JLabel lblLastName;
    private javax.swing.JLabel lblMail;
    private javax.swing.JLabel lblName;
    private javax.swing.JLabel lblPassword;
    private javax.swing.JLabel lblRole;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JLabel lblUsername;
    private javax.swing.JPanel pnlRegister;
    private javax.swing.JTextField txtLastName;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtPassword;
    private javax.swing.JTextField txtUsername;
    private javax.swing.JTextField txtmail;
    // End of variables declaration//GEN-END:variables
}
