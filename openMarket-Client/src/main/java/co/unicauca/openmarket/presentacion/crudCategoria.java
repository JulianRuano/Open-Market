/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package co.unicauca.openmarket.presentacion;

import co.unicauca.openmarket.client.domain.service.CategoryService;
import co.unicauca.openmarket.client.infra.Messages;
import static co.unicauca.openmarket.client.infra.Messages.successMessage;
import co.unicauca.openmarket.client.presentation.commands.OMAddCategoryCommand;
import co.unicauca.openmarket.client.presentation.commands.OMDeleteCategoryCommand;
import co.unicauca.openmarket.client.presentation.commands.OMEditCategoryCommand;
import co.unicauca.openmarket.client.presentation.commands.OMInvoker;
import co.unicauca.openmarket.commons.domain.Category;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import reloj.frameworkobsobs.Observador;

/**
 *
 * @author fre90
 */
public class crudCategoria extends javax.swing.JPanel implements Observador {

    private CategoryService categoryService;
    private int addOption;
    private OMInvoker ominvoker;

    /**
     * Creates new form crudCategoria
     */
    public crudCategoria(CategoryService categoryService, OMInvoker ominvoker) {
        initComponents();
        this.categoryService = categoryService;
        this.ominvoker = ominvoker;
        initializeTable();
        stateInitial();
    }

    private void initializeTable() {
        tablaCategorias.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "Id", "Name"
                }
        ));
    }

    private void fillTable(List<Category> listCategories) {
        initializeTable();
        DefaultTableModel model = (DefaultTableModel) tablaCategorias.getModel();

        Object rowData[] = new Object[2];//No columnas
        for (int i = 0; i < listCategories.size(); i++) {
            rowData[0] = listCategories.get(i).getCategoryId();
            rowData[1] = listCategories.get(i).getName();

            model.addRow(rowData);
        }
    }

    private void fillTableId(Category category) {
        initializeTable();
        DefaultTableModel model = (DefaultTableModel) tablaCategorias.getModel();

        Object rowData[] = new Object[2];//No columnas
        rowData[0] = category.getCategoryId();
        rowData[1] = category.getName();

        model.addRow(rowData);
    }

    private void fillTableName(List<Category> listCategories) {
        initializeTable();
        DefaultTableModel model = (DefaultTableModel) tablaCategorias.getModel();

        Object rowData[] = new Object[2];//No columnas
        for (int i = 0; i < listCategories.size(); i++) {
            rowData[0] = listCategories.get(i).getCategoryId();

            rowData[1] = listCategories.get(i).getName();

            model.addRow(rowData);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btngGrupo1 = new javax.swing.ButtonGroup();
        pnlSeccionBotones = new javax.swing.JPanel();
        btnNueva = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnDeshacer = new javax.swing.JButton();
        btnRehacer = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        pnlCrudCategoria = new javax.swing.JPanel();
        lblCodCategoria = new javax.swing.JLabel();
        txtCodCategoria = new javax.swing.JTextField();
        lblNameCategory = new javax.swing.JLabel();
        txtNameCategoria = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaCategorias = new javax.swing.JTable();
        lblBuscar = new javax.swing.JLabel();
        txtBuscar = new javax.swing.JTextField();
        rdBuscarId = new javax.swing.JRadioButton();
        btnBuscar = new javax.swing.JButton();
        btnListarTodo = new javax.swing.JButton();
        rdBuscarNombre = new javax.swing.JRadioButton();

        setLayout(new java.awt.BorderLayout());

        btnNueva.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnNueva.setText("Nueva");
        btnNueva.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnNueva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevaActionPerformed(evt);
            }
        });

        btnEditar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnEditar.setText("Editar");
        btnEditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        btnCancelar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        btnEliminar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnEliminar.setText("Eliminar");
        btnEliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        btnDeshacer.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnDeshacer.setText("Deshacer");
        btnDeshacer.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDeshacer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeshacerActionPerformed(evt);
            }
        });

        btnRehacer.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnRehacer.setText("Rehacer");
        btnRehacer.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnRehacer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRehacerActionPerformed(evt);
            }
        });

        btnGuardar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnGuardar.setText("Confirmar");
        btnGuardar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlSeccionBotonesLayout = new javax.swing.GroupLayout(pnlSeccionBotones);
        pnlSeccionBotones.setLayout(pnlSeccionBotonesLayout);
        pnlSeccionBotonesLayout.setHorizontalGroup(
            pnlSeccionBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSeccionBotonesLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(btnNueva, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnEliminar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnDeshacer)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnRehacer)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnCancelar)
                .addContainerGap(320, Short.MAX_VALUE))
        );
        pnlSeccionBotonesLayout.setVerticalGroup(
            pnlSeccionBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSeccionBotonesLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(pnlSeccionBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNueva, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDeshacer, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRehacer, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        add(pnlSeccionBotones, java.awt.BorderLayout.PAGE_END);

        pnlCrudCategoria.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblCodCategoria.setText("Codigo Categoria");
        pnlCrudCategoria.add(lblCodCategoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 60, -1, -1));
        pnlCrudCategoria.add(txtCodCategoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 80, 230, 40));

        lblNameCategory.setText("Nombre");
        pnlCrudCategoria.add(lblNameCategory, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 160, -1, -1));
        pnlCrudCategoria.add(txtNameCategoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 180, 230, 40));

        tablaCategorias.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tablaCategorias);

        pnlCrudCategoria.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 100, 580, 410));

        lblBuscar.setText("Buscar por:");
        pnlCrudCategoria.add(lblBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 60, -1, -1));
        pnlCrudCategoria.add(txtBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 50, 100, 30));

        btngGrupo1.add(rdBuscarId);
        rdBuscarId.setText("ID");
        rdBuscarId.setToolTipText("");
        pnlCrudCategoria.add(rdBuscarId, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 60, -1, -1));

        btnBuscar.setText("Buscar");
        btnBuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });
        pnlCrudCategoria.add(btnBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 50, -1, 30));

        btnListarTodo.setText("Listar Todo");
        btnListarTodo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnListarTodo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnListarTodoActionPerformed(evt);
            }
        });
        pnlCrudCategoria.add(btnListarTodo, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 50, 120, 30));

        btngGrupo1.add(rdBuscarNombre);
        rdBuscarNombre.setText("Nombre");
        pnlCrudCategoria.add(rdBuscarNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 60, -1, -1));

        add(pnlCrudCategoria, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void btnListarTodoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnListarTodoActionPerformed
        fillTable(categoryService.findAllCategories());
    }//GEN-LAST:event_btnListarTodoActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        try {
            if (this.rdBuscarId.isSelected() == true) {
                if (!validarId(txtBuscar)) {
                    return;
                }
                fillTableId(categoryService.findCategoryById(Long.valueOf(this.txtBuscar.getText())));
            } else {
                if (txtBuscar.getText().isEmpty()) {
                    Messages.showMessageDialog("Debe ingresar el nombre de la categoria", "Atención");
                    txtBuscar.requestFocus();
                    return ;
                }
                fillTableName(categoryService.findCategoriesByName(this.txtBuscar.getText()));
            }
        } catch (NullPointerException ex) {
            JOptionPane.showMessageDialog(null,
                    "Envia la informacion correspondiente",
                    "Error tipo de dato",
                    JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    "Seleccione por el dato que quiere buscar",
                    "Error al introducir el dato",
                    JOptionPane.ERROR_MESSAGE);
            successMessage(e.getMessage(), "Atención");
        }
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnNuevaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevaActionPerformed
        stateNew();
        this.txtCodCategoria.requestFocus();
        addOption = 1;
    }//GEN-LAST:event_btnNuevaActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        if (addOption == 1) {
            //Agregar
            addCategory();
        } else if (addOption == 2) {
            //Editar
            if (!validarIdNombre()) {
                return;
            }
            editCategory();
        } else {
            if (!validarId(txtCodCategoria)) {
                return;
            }
            deleteCategory();
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        stateInitial();
        cleanControls();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnDeshacerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeshacerActionPerformed
        ominvoker.unexecute();
        if (!ominvoker.hasMoreCommands()) {
            this.btnDeshacer.setVisible(false);
        }
        this.btnRehacer.setVisible(true);
    }//GEN-LAST:event_btnDeshacerActionPerformed

    private void btnRehacerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRehacerActionPerformed
        ominvoker.reExecuted();
        if (!ominvoker.hasMoreCommandsRedo()) {
            this.btnRehacer.setVisible(false);
        }
        this.btnDeshacer.setVisible(true);
    }//GEN-LAST:event_btnRehacerActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        addOption = 2;
        stateEdit();
        txtCodCategoria.requestFocus();
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        addOption = 3;
        stateDelete();
        txtCodCategoria.requestFocus();
    }//GEN-LAST:event_btnEliminarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnDeshacer;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnListarTodo;
    private javax.swing.JButton btnNueva;
    private javax.swing.JButton btnRehacer;
    private javax.swing.ButtonGroup btngGrupo1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblBuscar;
    private javax.swing.JLabel lblCodCategoria;
    private javax.swing.JLabel lblNameCategory;
    private javax.swing.JPanel pnlCrudCategoria;
    private javax.swing.JPanel pnlSeccionBotones;
    private javax.swing.JRadioButton rdBuscarId;
    private javax.swing.JRadioButton rdBuscarNombre;
    private javax.swing.JTable tablaCategorias;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtCodCategoria;
    private javax.swing.JTextField txtNameCategoria;
    // End of variables declaration//GEN-END:variables

    @Override
    public void actualizar() {
        try {
            fillTable(categoryService.findAllCategories());
        } catch (Exception ex) {
            successMessage(ex.getMessage(), "Atención");
        }
    }

    private void stateEdit() {
        lblCodCategoria.setVisible(true);
        lblNameCategory.setVisible(true);
        txtCodCategoria.setVisible(true);
        txtNameCategoria.setVisible(true);
        btnNueva.setVisible(false);
        btnEditar.setVisible(false);
        btnEliminar.setVisible(false);
        btnGuardar.setVisible(true);
        btnCancelar.setVisible(true);
        btnDeshacer.setVisible(false);
        btnRehacer.setVisible(false);
    }

    private void stateNew() {
        lblNameCategory.setVisible(true);
        txtNameCategoria.setVisible(true);
        btnNueva.setVisible(false);
        btnEditar.setVisible(false);
        btnEliminar.setVisible(false);
        btnGuardar.setVisible(true);
        btnCancelar.setVisible(true);
        btnDeshacer.setVisible(false);
        btnRehacer.setVisible(false);
    }

    private void stateInitial() {
        lblCodCategoria.setVisible(false);
        lblNameCategory.setVisible(false);
        txtCodCategoria.setVisible(false);
        txtNameCategoria.setVisible(false);
        rdBuscarId.setSelected(true);
        tablaCategorias.setEnabled(false);
        btnCancelar.setVisible(false);
        btnGuardar.setVisible(false);
        btnNueva.setVisible(true);
        btnEditar.setVisible(true);
        btnEliminar.setVisible(true);
        btnDeshacer.setVisible(ominvoker.hasMoreCommands());
        btnRehacer.setVisible(ominvoker.hasMoreCommandsRedo());
    }

    private void stateDelete() {
        lblCodCategoria.setVisible(true);
        txtCodCategoria.setVisible(true);
        btnNueva.setVisible(false);
        btnEditar.setVisible(false);
        btnEliminar.setVisible(false);
        btnCancelar.setVisible(true);
        btnGuardar.setVisible(true);
        btnDeshacer.setVisible(false);
        btnRehacer.setVisible(false);
    }

    private void cleanControls() {
        txtCodCategoria.setText("");
        txtNameCategoria.setText("");
    }

    private void addCategory() {
        if (txtNameCategoria.getText().isEmpty()) {
            Messages.showMessageDialog("Debe ingresar el nombre de la categoria", "Atención");
            txtNameCategoria.requestFocus();
            return;
        }
        try {
            String name = this.txtNameCategoria.getText().trim();
            Category OCategory = new Category(0L, name);
            OMAddCategoryCommand comm = new OMAddCategoryCommand(OCategory, categoryService);
            ominvoker.addCommand(comm);
            ominvoker.execute();
            if (comm.result()) {
                Messages.showMessageDialog("Se grabo con exito", "Atencion");
                cleanControls();
                stateInitial();
            } else {
                Messages.showMessageDialog("Error al grabar, lo siento mucho", "Atencion");
            }
        } catch (Exception ex) {
            successMessage(ex.getMessage(), "Atención");
        }
    }

    private void editCategory() {
        if (!encontrarCategoria()) {
            return;
        }
        Long categoryId = Long.valueOf(txtCodCategoria.getText());
        String name = this.txtNameCategoria.getText().trim();
        Category OCategory = new Category(categoryId, name);
        OMEditCategoryCommand comm = new OMEditCategoryCommand(OCategory, categoryService);
        ominvoker.addCommand(comm);
        ominvoker.execute();

        if (comm.result()) {
            Messages.showMessageDialog("Se editó con éxito", "Atención");
            cleanControls();
            stateInitial();
        } else {
            Messages.showMessageDialog("Error al editar, lo siento mucho", "Atención");
        }
    }

    private void deleteCategory() {
        if (!encontrarCategoria()) {
            return;
        }
        if (Messages.showConfirmDialog("Está seguro que desea eliminar esta Categoria?", "Confirmación") == JOptionPane.YES_NO_OPTION) {
            Long idCategory = Long.valueOf(txtCodCategoria.getText().trim());
            OMDeleteCategoryCommand comm = new OMDeleteCategoryCommand(idCategory, categoryService);
            ominvoker.addCommand(comm);
            ominvoker.execute();
            if (comm.result()) {
                Messages.showMessageDialog("Categoria eliminada con exito", "Atención");
                stateInitial();
                cleanControls();
            } else {
                Messages.showMessageDialog("Categoria no encontrada", "Error");

            }
        }
    }

    private boolean validarIdNombre() {
        if (txtCodCategoria.getText().isEmpty() || txtNameCategoria.getText().isEmpty()) {
            Messages.showMessageDialog("Debe llenar todos los campos", "Atención");
            txtCodCategoria.requestFocus();
            cleanControls();
            return false;
        }
        if (!validarNumeros(txtCodCategoria.getText().trim())) {
            Messages.showMessageDialog("El ID debe ser numeros", "Error");
            txtCodCategoria.setText("");
            txtCodCategoria.requestFocus();
            return false;
        }
        return true;
    }

    private boolean validarId(javax.swing.JTextField caja) {
        if (caja.getText().isEmpty()) {
            Messages.showMessageDialog("Debe ingresar el id de la categoria", "Atención");
            caja.requestFocus();
            return false;
        }
        if (!validarNumeros(caja.getText().trim())) {
            Messages.showMessageDialog("El ID debe ser numeros", "Error");
            caja.setText("");
            caja.requestFocus();
            return false;
        }
        return true;
    }

    private boolean encontrarCategoria() {
        try {
            if (this.categoryService.findCategoryById(Long.valueOf(txtCodCategoria.getText())) == null) {
                Messages.showMessageDialog("Categoria no encontrada", "Error");
                cleanControls();
                txtCodCategoria.requestFocus();
                return false;
            }
        } catch (Exception ex) {
            Logger.getLogger(crudCategoria.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

    private boolean validarNumeros(String datos) {
        return datos.matches("[0-9]*");
    }
}
