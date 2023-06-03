
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
        initStyles();
        this.userService=userService;
    }
    private void initStyles(){
        this.btnRegistrarse.putClientProperty("JButton.buttonType", "roundRect");
        
        this.txtName.putClientProperty ( "JComponent.roundRect", true );
        this.txtLastName.putClientProperty ( "JComponent.roundRect", true );
        this.txtmail.putClientProperty ( "JComponent.roundRect", true );
        this.txtUsername.putClientProperty ( "JComponent.roundRect", true );
       this.cbxRol.putClientProperty ( "JComponent.roundRect", true );
        this.txtPassword.putClientProperty ( "JComponent.roundRect", true );
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

        pnlRegister.setBackground(new java.awt.Color(122, 154, 171));

        lblName.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        lblName.setForeground(new java.awt.Color(0, 0, 0));
        lblName.setText("Nombre");

        lblLastName.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        lblLastName.setForeground(new java.awt.Color(0, 0, 0));
        lblLastName.setText("Apellido");

        txtName.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        txtName.setForeground(new java.awt.Color(0, 0, 0));

        txtLastName.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        txtLastName.setForeground(new java.awt.Color(0, 0, 0));

        lblRole.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        lblRole.setForeground(new java.awt.Color(0, 0, 0));
        lblRole.setText("Rol");

        txtmail.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        txtmail.setForeground(new java.awt.Color(0, 0, 0));

        lblMail.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        lblMail.setForeground(new java.awt.Color(0, 0, 0));
        lblMail.setText("Correo");

        txtUsername.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        txtUsername.setForeground(new java.awt.Color(0, 0, 0));

        lblUsername.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        lblUsername.setForeground(new java.awt.Color(0, 0, 0));
        lblUsername.setText("Usuario");

        lblTitulo.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        lblTitulo.setForeground(new java.awt.Color(0, 0, 0));
        lblTitulo.setText("Registrate");

        cbxRol.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        cbxRol.setForeground(new java.awt.Color(0, 0, 0));
        cbxRol.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Vendedor", "Comprador", " " }));

        lblPassword.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        lblPassword.setForeground(new java.awt.Color(0, 0, 0));
        lblPassword.setText("Contraseña");

        btnRegistrarse.setBackground(new java.awt.Color(102, 102, 255));
        btnRegistrarse.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        btnRegistrarse.setForeground(new java.awt.Color(0, 0, 0));
        btnRegistrarse.setText("Registrarse");
        btnRegistrarse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarseActionPerformed(evt);
            }
        });

        txtPassword.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        txtPassword.setForeground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout pnlRegisterLayout = new javax.swing.GroupLayout(pnlRegister);
        pnlRegister.setLayout(pnlRegisterLayout);
        pnlRegisterLayout.setHorizontalGroup(
            pnlRegisterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlRegisterLayout.createSequentialGroup()
                .addGroup(pnlRegisterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
                        .addGap(130, 130, 130)
                        .addComponent(btnRegistrarse, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlRegisterLayout.createSequentialGroup()
                        .addGap(155, 155, 155)
                        .addComponent(lblTitulo)))
                .addContainerGap(139, Short.MAX_VALUE))
        );
        pnlRegisterLayout.setVerticalGroup(
            pnlRegisterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlRegisterLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(lblTitulo)
                .addGap(18, 18, 18)
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnRegistrarse, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
                .addGap(12, 12, 12))
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
