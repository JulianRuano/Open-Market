package co.unicauca.openmarket.presentacion;

import co.unicauca.openmarket.client.domain.service.ProductService;
import co.unicauca.openmarket.client.domain.service.CategoryService;
import co.unicauca.openmarket.client.infra.Messages;
import static co.unicauca.openmarket.client.infra.Messages.successMessage;
import co.unicauca.openmarket.client.presentation.commands.OMAddProductCommand;
import co.unicauca.openmarket.client.presentation.commands.OMDeleteProductCommand;
import co.unicauca.openmarket.client.presentation.commands.OMEditProductCommand;
import co.unicauca.openmarket.client.presentation.commands.OMInvoker;
import co.unicauca.openmarket.commons.domain.Category;
import co.unicauca.openmarket.commons.domain.Product;
import co.unicauca.openmarket.presentacion.helpers.PresentacionHelpers;
import co.unicauca.openmarket.presentacion.validaciones.ValidadorCampos;
import co.unicauca.openmarket.presentacion.validaciones.producto.MensajesError;
import co.unicauca.openmarket.presentacion.validaciones.producto.ValidadorFormularioProducto;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import reloj.frameworkobsobs.Observador;

/**
 *
 * @author brayan
 */
public class crudProducto extends javax.swing.JPanel implements Observador {

    DefaultTableModel mModeloTabla = new DefaultTableModel();
    String Ruta = "";

    /**
     * Creates new form crudProducto
     */
    private ProductService productService;
    private int addOption;
    private OMInvoker ominvoker;
    private CategoryService categoryService;
    private ValidadorCampos validarCampos;
    private ValidadorFormularioProducto validarFormulario;
//    private Integer selectedCategoryId;
    private PresentacionHelpers presHelpers;

    public crudProducto(ProductService productService, OMInvoker ominvoker, CategoryService categoryService) {
        initComponents();
        
        this.productService = productService;
        this.categoryService = categoryService;
        this.ominvoker = ominvoker;
        this.validarCampos = new ValidadorCampos();
        this.validarFormulario = new ValidadorFormularioProducto();
        mModeloTabla.addColumn("ID");
        mModeloTabla.addColumn("Nombre");
        mModeloTabla.addColumn("Descripcion");
        mModeloTabla.addColumn("Precio");
        mModeloTabla.addColumn("Direccion");
        mModeloTabla.addColumn("ID categoria");
        mModeloTabla.addColumn("Stock");
        mModeloTabla.addColumn("Imagen");
        tblProductos.setModel(mModeloTabla);
        presHelpers= new PresentacionHelpers();
        
        presHelpers.loadCategories(cbxCodigoCategoria, categoryService);
         stateInitial();
         initStyles();
       
      
    }
    private void initStyles(){
        
        this.btnEliminar.putClientProperty("JButton.buttonType", "roundRect");
        this.btnCancelar.putClientProperty("JButton.buttonType", "roundRect");
        this.btnNuevo.putClientProperty("JButton.buttonType", "roundRect");
        this.btnGuardar.putClientProperty("JButton.buttonType", "roundRect");
        this.btnEditar.putClientProperty("JButton.buttonType", "roundRect");
        this.btnRehacer.putClientProperty("JButton.buttonType", "roundRect");
        this.btnDeshacer.putClientProperty("JButton.buttonType", "roundRect");
        this.btnExaminar.putClientProperty("JButton.buttonType", "roundRect");
        this.btnBuscar.putClientProperty("JButton.buttonType", "roundRect");
        this.btnListar.putClientProperty("JButton.buttonType", "roundRect");
        this.txtCodigoProducto.putClientProperty ( "JComponent.roundRect", true );
        this.txtNombre.putClientProperty ( "JComponent.roundRect", true );
        this.txtDescripcion.putClientProperty ( "JComponent.roundRect", true );
        this.txtPrecio.putClientProperty ( "JComponent.roundRect", true );
        this.txtStock.putClientProperty ( "JComponent.roundRect", true );
        this.txtDireccion.putClientProperty ( "JComponent.roundRect", true );
        this.cbxCodigoCategoria.putClientProperty ( "JComponent.roundRect", true );
        this.txtBuscarProducto.putClientProperty ( "JComponent.roundRect", true );
        
      //para el diseño del titulo y el color de la letra
     }
    private void initializeTable() {
        tblProductos.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "Id", "Name", "Description", "Price", "Direccion", "IdCategoria", "Image"
                }
        ));
    }
    
    private void stateDelete(){
        lblCodigoProducto.setVisible(true);
        txtCodigoProducto.setVisible(true);
        btnNuevo.setVisible(false);
        btnEditar.setVisible(false);
        btnEliminar.setVisible(false);
        btnCancelar.setVisible(true);
        btnGuardar.setVisible(true);
        btnDeshacer.setVisible(false);
        btnRehacer.setVisible(false);
    }

    private void stateEdit() {
        btnNuevo.setVisible(false);
        btnEditar.setVisible(false);
        btnEliminar.setVisible(false);
        btnCancelar.setVisible(true);
        btnGuardar.setVisible(true);
        lblCodigoCategoria.setVisible(true);
        lblCodigoProducto.setVisible(true);
        lblDireccion.setVisible(true);
        lblExaminar2.setVisible(true);
        lblNombre.setVisible(true);
        lblPrecio.setVisible(true);
        lblStock.setVisible(true);
        lblDescripcion.setVisible(true);
        txtCodigoProducto.setVisible(true);
        txtNombre.setVisible(true);
        txtPrecio.setVisible(true);
        txtDireccion.setVisible(true);
        txtDescripcion.setVisible(true);
        txtStock.setVisible(true);
        cbxCodigoCategoria.setVisible(true);
        btnExaminar.setVisible(true);
        btnDeshacer.setVisible(false);
        btnRehacer.setVisible(false);
    }
    
    private void stateNew() {
        btnNuevo.setVisible(false);
        btnEditar.setVisible(false);
        btnEliminar.setVisible(false);
        btnCancelar.setVisible(true);
        btnGuardar.setVisible(true);
        lblCodigoCategoria.setVisible(true);
        lblDireccion.setVisible(true);
        lblExaminar2.setVisible(true);
        lblNombre.setVisible(true);
        lblPrecio.setVisible(true);
        lblStock.setVisible(true);
        lblDescripcion.setVisible(true);
        txtNombre.setVisible(true);
        txtPrecio.setVisible(true);
        txtDireccion.setVisible(true);
        txtDescripcion.setVisible(true);
        txtStock.setVisible(true);
        cbxCodigoCategoria.setVisible(true);
        btnExaminar.setVisible(true);
        btnDeshacer.setVisible(false);
        btnRehacer.setVisible(false);
    }

    private void cleanControls() {
        txtCodigoProducto.setText("");
        txtNombre.setText("");
        txtDescripcion.setText("");
        txtPrecio.setText("");
        txtDireccion.setText("");
        cbxCodigoCategoria.setToolTipText("");
    }

    private void stateInitial() {
        btnNuevo.setVisible(true);
        btnEditar.setVisible(true);
        btnEliminar.setVisible(true);
        btnCancelar.setVisible(false);
        btnGuardar.setVisible(false);
        lblCodigoCategoria.setVisible(false);
        lblCodigoProducto.setVisible(false);
        lblDireccion.setVisible(false);
        lblExaminar2.setVisible(false);
        lblNombre.setVisible(false);
        lblPrecio.setVisible(false);
        lblStock.setVisible(false);
        lblDescripcion.setVisible(false);
        txtCodigoProducto.setVisible(false);
        txtNombre.setVisible(false);
        txtPrecio.setVisible(false);
        txtDireccion.setVisible(false);
        txtDescripcion.setVisible(false);
        txtStock.setVisible(false);
        cbxCodigoCategoria.setVisible(false);
        btnExaminar.setVisible(false);
        btnDeshacer.setVisible(ominvoker.hasMoreCommands());
        btnRehacer.setVisible(ominvoker.hasMoreCommandsRedo());
    }

    private void fillTable(List<Product> listProducts) {
        tblProductos.setDefaultRenderer(Object.class, new RenderImagen());
        DefaultTableModel model = (DefaultTableModel) tblProductos.getModel();

        Object rowData[] = new Object[8];//No columnas
        for (int i = 0; i < listProducts.size(); i++) {
            rowData[0] = listProducts.get(i).getProductId();
            rowData[1] = listProducts.get(i).getName();
            rowData[2] = listProducts.get(i).getDescription();
            rowData[3] = listProducts.get(i).getPrice();
            rowData[4] = listProducts.get(i).getAddress();
            rowData[5] = listProducts.get(i).getCategoryId();
             rowData[6] = listProducts.get(i).getStock();

            try {
                byte[] imagen = listProducts.get(i).getImage();
                BufferedImage bufferedImage = null;
                InputStream inputStream = new ByteArrayInputStream(imagen);
                bufferedImage = ImageIO.read(inputStream);
                ImageIcon mIcono = new ImageIcon(bufferedImage.getScaledInstance(80, 80, 0));
                rowData[7] = new JLabel(mIcono);
            } catch (IOException e) {
                rowData[7] = new JLabel("No imagen");
            }

            model.addRow(rowData);
        }

        tblProductos.setRowHeight(80);
        tblProductos.getColumnModel().getColumn(0).setPreferredWidth(80);
        tblProductos.getColumnModel().getColumn(1).setPreferredWidth(80);
        tblProductos.getColumnModel().getColumn(2).setPreferredWidth(80);
    }

    private void fillTableId(Product product) {
        tblProductos.setDefaultRenderer(Object.class, new RenderImagen());
        DefaultTableModel model = (DefaultTableModel) tblProductos.getModel();

        Object rowData[] = new Object[8];//No columnas

        rowData[0] = product.getProductId();
        rowData[1] = product.getName();
        rowData[2] = product.getDescription();
        rowData[3] = product.getPrice();
        rowData[4] = product.getAddress();
        rowData[5] = product.getCategoryId();
         rowData[6] = product.getStock();
        try {
            byte[] imagen = product.getImage();
            BufferedImage bufferedImage = null;
            InputStream inputStream = new ByteArrayInputStream(imagen);
            bufferedImage = ImageIO.read(inputStream);
            ImageIcon mIcono = new ImageIcon(bufferedImage.getScaledInstance(80, 80, 0));
            rowData[7] = new JLabel(mIcono);
        } catch (Exception e) {
            rowData[7] = new JLabel("No imagen");
        }

        model.addRow(rowData);

        tblProductos.setRowHeight(80);
        tblProductos.getColumnModel().getColumn(0).setPreferredWidth(80);
        tblProductos.getColumnModel().getColumn(1).setPreferredWidth(80);
        tblProductos.getColumnModel().getColumn(2).setPreferredWidth(80);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        pnlCrudpProducto = new javax.swing.JPanel();
        pnlSeccionBotones = new javax.swing.JPanel();
        btnNuevo = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnDeshacer = new javax.swing.JButton();
        btnRehacer = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        panel1 = new java.awt.Panel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblProductos = new javax.swing.JTable();
        rdIdProducto = new javax.swing.JRadioButton();
        rdIdCategoria = new javax.swing.JRadioButton();
        rdNombreProducto = new javax.swing.JRadioButton();
        txtBuscarProducto = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        btnListar = new javax.swing.JButton();
        panel2 = new java.awt.Panel();
        lblCodigoProducto = new javax.swing.JLabel();
        txtCodigoProducto = new javax.swing.JTextField();
        lblNombre = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        lblDescripcion = new javax.swing.JLabel();
        txtDescripcion = new javax.swing.JTextField();
        lblPrecio = new javax.swing.JLabel();
        txtPrecio = new javax.swing.JTextField();
        lblStock = new javax.swing.JLabel();
        txtStock = new javax.swing.JTextField();
        lblDireccion = new javax.swing.JLabel();
        txtDireccion = new javax.swing.JTextField();
        lblCodigoCategoria = new javax.swing.JLabel();
        cbxCodigoCategoria = new javax.swing.JComboBox<>();
        lblExaminar2 = new javax.swing.JLabel();
        btnExaminar = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();

        btnNuevo.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnNuevo.setText("Nuevo");
        btnNuevo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });

        btnEditar.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnEditar.setText("Editar");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        btnEliminar.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        btnDeshacer.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnDeshacer.setText("Deshacer");
        btnDeshacer.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDeshacer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeshacerActionPerformed(evt);
            }
        });

        btnRehacer.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnRehacer.setText("Rehacer");
        btnRehacer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRehacerActionPerformed(evt);
            }
        });

        btnGuardar.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnCancelar.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlSeccionBotonesLayout = new javax.swing.GroupLayout(pnlSeccionBotones);
        pnlSeccionBotones.setLayout(pnlSeccionBotonesLayout);
        pnlSeccionBotonesLayout.setHorizontalGroup(
            pnlSeccionBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSeccionBotonesLayout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnRehacer, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnDeshacer, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(420, Short.MAX_VALUE))
        );
        pnlSeccionBotonesLayout.setVerticalGroup(
            pnlSeccionBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlSeccionBotonesLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlSeccionBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRehacer, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDeshacer, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40))
        );

        tblProductos.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
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
        tblProductos.setGridColor(new java.awt.Color(0, 0, 0));
        jScrollPane1.setViewportView(tblProductos);

        buttonGroup1.add(rdIdProducto);
        rdIdProducto.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        rdIdProducto.setText("Codigo producto");

        buttonGroup1.add(rdIdCategoria);
        rdIdCategoria.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        rdIdCategoria.setText("Codigo Categoria");

        buttonGroup1.add(rdNombreProducto);
        rdNombreProducto.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        rdNombreProducto.setSelected(true);
        rdNombreProducto.setText("Nombre Producto");
        rdNombreProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdNombreProductoActionPerformed(evt);
            }
        });

        txtBuscarProducto.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N

        btnBuscar.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        btnListar.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnListar.setText("Listar Todo");
        btnListar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnListarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panel1Layout = new javax.swing.GroupLayout(panel1);
        panel1.setLayout(panel1Layout);
        panel1Layout.setHorizontalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel1Layout.createSequentialGroup()
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addGap(57, 57, 57)
                        .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panel1Layout.createSequentialGroup()
                                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(rdIdCategoria)
                                    .addComponent(rdIdProducto))
                                .addGap(46, 46, 46)
                                .addComponent(txtBuscarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(rdNombreProducto))
                        .addGap(38, 38, 38)
                        .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnListar, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(175, Short.MAX_VALUE))
        );
        panel1Layout.setVerticalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addComponent(rdIdProducto)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rdIdCategoria))
                    .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtBuscarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rdNombreProducto))
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(btnListar, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 83, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );

        lblCodigoProducto.setBackground(new java.awt.Color(0, 0, 0));
        lblCodigoProducto.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        lblCodigoProducto.setText("Codigo Producto");

        txtCodigoProducto.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N

        lblNombre.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        lblNombre.setText("Nombre");

        txtNombre.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N

        lblDescripcion.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        lblDescripcion.setText("Descripcion");

        txtDescripcion.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N

        lblPrecio.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        lblPrecio.setText("Precio");

        txtPrecio.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N

        lblStock.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        lblStock.setText("Stock");

        txtStock.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N

        lblDireccion.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        lblDireccion.setText("Direccion");

        txtDireccion.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N

        lblCodigoCategoria.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        lblCodigoCategoria.setText("Categoria");

        cbxCodigoCategoria.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        cbxCodigoCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxCodigoCategoriaActionPerformed(evt);
            }
        });

        lblExaminar2.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        btnExaminar.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnExaminar.setText("Examinar");
        btnExaminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnExaminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExaminarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 478, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 32, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout panel2Layout = new javax.swing.GroupLayout(panel2);
        panel2.setLayout(panel2Layout);
        panel2Layout.setHorizontalGroup(
            panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel2Layout.createSequentialGroup()
                        .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panel2Layout.createSequentialGroup()
                                .addGap(49, 49, 49)
                                .addComponent(lblExaminar2, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(48, 48, 48)
                                .addComponent(btnExaminar))
                            .addComponent(lblCodigoProducto)
                            .addComponent(lblNombre)
                            .addComponent(lblDescripcion)
                            .addGroup(panel2Layout.createSequentialGroup()
                                .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblPrecio)
                                    .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblStock, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtStock, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(panel2Layout.createSequentialGroup()
                        .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblDireccion)
                            .addComponent(lblCodigoCategoria)
                            .addComponent(txtNombre)
                            .addComponent(txtDireccion)
                            .addComponent(cbxCodigoCategoria, 0, 270, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(panel2Layout.createSequentialGroup()
                .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtCodigoProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        panel2Layout.setVerticalGroup(
            panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel2Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(lblCodigoProducto)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtCodigoProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblNombre)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblDescripcion)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPrecio)
                    .addComponent(lblStock))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtStock)
                    .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(lblDireccion)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblCodigoCategoria)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbxCodigoCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel2Layout.createSequentialGroup()
                        .addComponent(btnExaminar)
                        .addGap(86, 86, 86))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel2Layout.createSequentialGroup()
                        .addComponent(lblExaminar2, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)))
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36))
        );

        javax.swing.GroupLayout pnlCrudpProductoLayout = new javax.swing.GroupLayout(pnlCrudpProducto);
        pnlCrudpProducto.setLayout(pnlCrudpProductoLayout);
        pnlCrudpProductoLayout.setHorizontalGroup(
            pnlCrudpProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCrudpProductoLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(panel2, javax.swing.GroupLayout.PREFERRED_SIZE, 349, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(panel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(pnlCrudpProductoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlSeccionBotones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlCrudpProductoLayout.setVerticalGroup(
            pnlCrudpProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCrudpProductoLayout.createSequentialGroup()
                .addGroup(pnlCrudpProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(panel2, javax.swing.GroupLayout.PREFERRED_SIZE, 541, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlSeccionBotones, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(39, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlCrudpProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlCrudpProducto, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnExaminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExaminarActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter extensionFilter = new FileNameExtensionFilter("JPG, PNG & GIF", "jpg", "png", "gif");
        fileChooser.setFileFilter(extensionFilter);

        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            this.Ruta = fileChooser.getSelectedFile().getAbsolutePath();
            Image mImagen = new ImageIcon(Ruta).getImage();
            ImageIcon mIcono = new ImageIcon(mImagen.getScaledInstance(lblExaminar2.getWidth(), lblExaminar2.getHeight(), 0));
            lblExaminar2.setIcon(mIcono);

            // Validar si se ha seleccionado una imagen
            File selectedFile = fileChooser.getSelectedFile();
            if (selectedFile != null && !selectedFile.isFile()) {
                Messages.showMessageDialog("Debe elegir una imagen", "Atención");
            }
        }
    }//GEN-LAST:event_btnExaminarActionPerformed

    private void cbxCodigoCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxCodigoCategoriaActionPerformed

    }//GEN-LAST:event_cbxCodigoCategoriaActionPerformed

    private void btnListarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnListarActionPerformed

        try {
            Limpiar();
            fillTable(productService.findAllProducts());
        } catch (Exception ex) {
            successMessage(ex.getMessage(), "Atención");
        }
    }//GEN-LAST:event_btnListarActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        try {

            Limpiar();
            if (this.rdIdProducto.isSelected() == true) {

                fillTableId(productService.findProductById(Integer.parseInt(this.txtBuscarProducto.getText())));
            } else if (this.rdIdCategoria.isSelected() == true) {
                fillTable(productService.findProductsByCategory(Integer.parseInt(this.txtBuscarProducto.getText())));
            } else {
                fillTable(productService.findProductsByName(this.txtBuscarProducto.getText()));
            }
        } catch (NullPointerException ex) {
            JOptionPane.showMessageDialog(null,
                "Envia la informacion correspondiente",
                "Error tipo de dato",
                JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            successMessage(e.getMessage(), "Atención");
            JOptionPane.showMessageDialog(null,
                "Seleccione por el dato que quiere buscar",
                "Error al introducir el dato",
                JOptionPane.ERROR_MESSAGE);

        }
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void rdNombreProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdNombreProductoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdNombreProductoActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        stateInitial();
        cleanControls();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        switch (addOption) {
            case 1:
            //Agregar
            if(!validarCampos()){
                return;
            }   addProduct();
            break;
            case 2:
            //Editar
            if(!validarCampos()){
                return;
            }   editProduct();
            break;
            default:
            //Eliminar
            if (!validarId(txtCodigoProducto)) {
                return;
            }   deleteProduct();
            break;
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnRehacerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRehacerActionPerformed
        ominvoker.reExecuted();
        if (!ominvoker.hasMoreCommandsRedo()) {
            this.btnRehacer.setVisible(false);
        }
        this.btnDeshacer.setVisible(true);
    }//GEN-LAST:event_btnRehacerActionPerformed

    private void btnDeshacerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeshacerActionPerformed
        ominvoker.unexecute();
        if (!ominvoker.hasMoreCommands()) {
            this.btnDeshacer.setVisible(false);
        }
        this.btnRehacer.setVisible(true);
    }//GEN-LAST:event_btnDeshacerActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        addOption = 3;
        stateDelete();
        txtCodigoProducto.requestFocus();
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        addOption = 2;
        stateEdit();
        txtCodigoProducto.requestFocus();
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        stateNew();
        this.txtNombre.requestFocus();
        addOption = 1;
    }//GEN-LAST:event_btnNuevoActionPerformed



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnDeshacer;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnExaminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnListar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnRehacer;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cbxCodigoCategoria;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblCodigoCategoria;
    private javax.swing.JLabel lblCodigoProducto;
    private javax.swing.JLabel lblDescripcion;
    private javax.swing.JLabel lblDireccion;
    private javax.swing.JLabel lblExaminar2;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblPrecio;
    private javax.swing.JLabel lblStock;
    private java.awt.Panel panel1;
    private java.awt.Panel panel2;
    private javax.swing.JPanel pnlCrudpProducto;
    private javax.swing.JPanel pnlSeccionBotones;
    private javax.swing.JRadioButton rdIdCategoria;
    private javax.swing.JRadioButton rdIdProducto;
    private javax.swing.JRadioButton rdNombreProducto;
    private javax.swing.JTable tblProductos;
    private javax.swing.JTextField txtBuscarProducto;
    private javax.swing.JTextField txtCodigoProducto;
    private javax.swing.JTextField txtDescripcion;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtPrecio;
    private javax.swing.JTextField txtStock;
    // End of variables declaration//GEN-END:variables

    private void addProduct() {
        try {
            String name = txtNombre.getText().trim();
            String description = txtDescripcion.getText().trim();
            double price = Double.parseDouble(this.txtPrecio.getText());
            String address = this.txtDireccion.getText();
            int stock = Integer.parseInt(txtStock.getText());
            byte[] image = getImagen(Ruta);

            Product OProduct = new Product(0, name, description, price, address, presHelpers.selectedCategoryId, stock, image);
            OMAddProductCommand comm = new OMAddProductCommand(OProduct, productService);
            ominvoker.addCommand(comm);
            ominvoker.execute();
            if (comm.result()) {
                Messages.showMessageDialog("Se grabó con éxito", "Atención");
                cleanControls();
                stateInitial();
            } else {
                Messages.showMessageDialog("Error al grabar, lo siento mucho", "Atención");
            }
        } catch (Exception ex) {
            successMessage(ex.getMessage(), "Atención");
        }

    }

    private void editProduct() {
        String id = txtCodigoProducto.getText().trim();
        if (id.equals("")) {
            Messages.showMessageDialog("Debe buscar el producto a editar", "Atención");
            txtCodigoProducto.requestFocus();
            return;
        }
        int productId = Integer.parseInt(id);
        String name = txtNombre.getText();
        String description = this.txtDescripcion.getText();
        double price = Double.parseDouble(this.txtPrecio.getText());
        String direccion = txtDescripcion.getText();
        int stock = Integer.parseInt(txtStock.getText());
        byte[] image = getImagen(Ruta);

        Product OProduct = new Product(productId, name, description, price, direccion, presHelpers.selectedCategoryId, stock, image);
        OMEditProductCommand comm = new OMEditProductCommand(OProduct, productService);
        ominvoker.addCommand(comm);
        ominvoker.execute();

        try {
            if (comm.result()) {
                Messages.showMessageDialog("Se editó con éxito", "Atención");
                cleanControls();
                stateInitial();
            } else {
                Messages.showMessageDialog("Error al editar, lo siento mucho", "Atención");
            }
        } catch (Exception ex) {
            successMessage(ex.getMessage(), "Atención");
        }

    }
    
    private void deleteProduct(){
        try {
            String id = txtCodigoProducto.getText().trim();
            if (id.equals("")) {
                Messages.showMessageDialog("Debe buscar el producto a eliminar", "Atención");
                txtCodigoProducto.requestFocus();
                return;
            }
            Integer productId = Integer.valueOf(id);
            OMDeleteProductCommand comm = new OMDeleteProductCommand(productId, productService);
            ominvoker.addCommand(comm);
            ominvoker.execute();

            if (Messages.showConfirmDialog("Está seguro que desea eliminar este producto?", "Confirmación") == JOptionPane.YES_NO_OPTION) {
                if (comm.result()) {
                    Messages.showMessageDialog("Producto eliminado con éxito", "Atención");
                    stateInitial();
                    cleanControls();
                }
                else{
                    JOptionPane.showMessageDialog(null, "Error: no se puede eliminar el producto", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (Exception ex) {
            successMessage(ex.getMessage(), "Atención");
        }
    }
    
    private boolean validarId(javax.swing.JTextField caja) {
        if (caja.getText().isEmpty()) {
            Messages.showMessageDialog("Debe ingresar el codigo del producto", "Atención");
            caja.requestFocus();
            return false;
        }
        if (!validarNumeros(caja.getText().trim())) {
            Messages.showMessageDialog("El codigo debe ser numérico", "Error");
            caja.setText("");
            caja.requestFocus();
            return false;
        }
        return true;
    }
    
    private boolean validarCampos(){
        List<MensajesError> errores = validarFormulario.validar( txtNombre, txtDescripcion,
                txtPrecio, txtStock, txtDireccion, presHelpers.selectedCategoryId);
     
        if (!errores.isEmpty()) {
            String mensajeError = "Debe ingresar el/los siguiente(s) campo(s):\n";
            for (MensajesError mensaje : errores) {
                mensajeError += mensaje.getMensaje() + "\n";
            }
            Messages.showMessageDialog(mensajeError, "Atención");

            // Coloca el foco en el primer campo con error
            switch (errores.get(0)) {
                case NOMBRE_PRODUCTO:
                    txtNombre.requestFocus();
                    break;
                case DESCRIPCION_PRODUCTO:
                    txtDescripcion.requestFocus();
                    break;
                case PRECIO_PRODUCTO:
                    txtPrecio.requestFocus();
                    break;
                case STOCK_PRODUCTO:
                    txtStock.requestFocus();
                    break;
                case CATEGORIA_PRODUCTO:
                    cbxCodigoCategoria.requestFocus();
                    break;
                case DIRECCION_PRODUCTO:
                    txtDireccion.requestFocus();
                    break;
                // Resto de los casos
            }

            return false;
        }
        return true;
    }
    
    private boolean validarNumeros(String datos) {
        return datos.matches("[0-9]*");
    }

    private void Limpiar() {
        for (int i = 0; i < tblProductos.getRowCount(); i++) {
            mModeloTabla.removeRow(i);
            i -= 1;
        }
    }

    private byte[] getImagen(String Ruta) {
        File imagen = new File(Ruta);
        try {
            byte[] icono = new byte[(int) imagen.length()];
            InputStream input = new FileInputStream(imagen);
            input.read(icono);
            return icono;
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public void actualizar() {
        try {
            Limpiar();
            fillTable(productService.findAllProducts());
        } catch (Exception ex) {
            successMessage(ex.getMessage(), "Atención");
        }

    }

}
