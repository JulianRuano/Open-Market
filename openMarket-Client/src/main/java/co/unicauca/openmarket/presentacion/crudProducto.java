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
    private Integer selectedCategoryId;

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
        List<Category> categories = this.categoryService.findAllCategories();
        if (!(categories == null)) {
            DefaultComboBoxModel<String> modelo = new DefaultComboBoxModel<>();
            modelo.addElement(""); // Agregar elemento vacío al inicio del modelo
            Map<String, Integer> categoryMap = new HashMap<>();

            for (Category category : categories) {
                String categoryName = category.getName();
                Integer categoryId = category.getCategoryId();
                modelo.addElement(categoryName);
                categoryMap.put(categoryName, categoryId);
            }

            cbxCodigoCategoria.setModel(modelo);
            cbxCodigoCategoria.setSelectedItem(null);

            cbxCodigoCategoria.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String selectedCategoryName = (String) cbxCodigoCategoria.getSelectedItem();
                    selectedCategoryId = categoryMap.get(selectedCategoryName);

                    // Aquí puedes utilizar el selectedCategoryId como necesites
                }
            });
        }
         stateInitial();
         //initStyles();
        // this.btnNuevo.putClientProperty("JButton.buttonType", "roundRect");
      
    }
    private void initStyles(){
     // this.btnEliminar.putClientProperty("JButton.buttonType", "roundRect");
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
            } catch (Exception e) {
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
        lblCodigoProducto = new javax.swing.JLabel();
        lblNombre = new javax.swing.JLabel();
        lblDescripcion = new javax.swing.JLabel();
        lblPrecio = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        txtCodigoProducto = new javax.swing.JTextField();
        txtDescripcion = new javax.swing.JTextField();
        lblDireccion = new javax.swing.JLabel();
        lblCodigoCategoria = new javax.swing.JLabel();
        txtPrecio = new javax.swing.JTextField();
        txtDireccion = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblProductos = new javax.swing.JTable();
        rdIdProducto = new javax.swing.JRadioButton();
        rdIdCategoria = new javax.swing.JRadioButton();
        rdNombreProducto = new javax.swing.JRadioButton();
        txtBuscarProducto = new javax.swing.JTextField();
        cbxCodigoCategoria = new javax.swing.JComboBox<>();
        btnBuscar = new javax.swing.JButton();
        btnListar = new javax.swing.JButton();
        btnExaminar = new javax.swing.JButton();
        lblExaminar2 = new javax.swing.JLabel();
        pnlSeccionBotones = new javax.swing.JPanel();
        btnNuevo = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnDeshacer = new javax.swing.JButton();
        btnRehacer = new javax.swing.JButton();
        lblStock = new javax.swing.JLabel();
        txtStock = new javax.swing.JTextField();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblCodigoProducto.setText("Codigo Producto");

        lblNombre.setText("Nombre");

        lblDescripcion.setText("Descripcion");

        lblPrecio.setText("Precio");

        lblDireccion.setText("Direccion");

        lblCodigoCategoria.setText("Categoria");

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

        buttonGroup1.add(rdIdProducto);
        rdIdProducto.setText("Codigo producto");

        buttonGroup1.add(rdIdCategoria);
        rdIdCategoria.setText("Codigo Categoria");

        buttonGroup1.add(rdNombreProducto);
        rdNombreProducto.setSelected(true);
        rdNombreProducto.setText("Nombre Producto");

        cbxCodigoCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxCodigoCategoriaActionPerformed(evt);
            }
        });

        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        btnListar.setText("Listar Todo");
        btnListar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnListarActionPerformed(evt);
            }
        });

        btnExaminar.setText("Examinar");
        btnExaminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExaminarActionPerformed(evt);
            }
        });

        lblExaminar2.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        btnNuevo.setText("Nuevo");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });

        btnEditar.setText("Editar");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        btnDeshacer.setText("Deshacer");
        btnDeshacer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeshacerActionPerformed(evt);
            }
        });

        btnRehacer.setText("Rehacer");
        btnRehacer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRehacerActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlSeccionBotonesLayout = new javax.swing.GroupLayout(pnlSeccionBotones);
        pnlSeccionBotones.setLayout(pnlSeccionBotonesLayout);
        pnlSeccionBotonesLayout.setHorizontalGroup(
            pnlSeccionBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSeccionBotonesLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(btnNuevo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnEditar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnEliminar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnDeshacer)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnRehacer)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnGuardar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnCancelar)
                .addContainerGap(365, Short.MAX_VALUE))
        );
        pnlSeccionBotonesLayout.setVerticalGroup(
            pnlSeccionBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSeccionBotonesLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(pnlSeccionBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNuevo)
                    .addComponent(btnEditar)
                    .addComponent(btnGuardar)
                    .addComponent(btnCancelar)
                    .addComponent(btnEliminar)
                    .addComponent(btnDeshacer)
                    .addComponent(btnRehacer))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        lblStock.setText("Stock");

        javax.swing.GroupLayout pnlCrudpProductoLayout = new javax.swing.GroupLayout(pnlCrudpProducto);
        pnlCrudpProducto.setLayout(pnlCrudpProductoLayout);
        pnlCrudpProductoLayout.setHorizontalGroup(
            pnlCrudpProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCrudpProductoLayout.createSequentialGroup()
                .addGap(64, 64, 64)
                .addGroup(pnlCrudpProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlCrudpProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(txtDireccion)
                        .addComponent(txtDescripcion)
                        .addComponent(txtNombre)
                        .addComponent(lblCodigoCategoria)
                        .addComponent(lblDireccion)
                        .addComponent(lblDescripcion)
                        .addComponent(lblNombre)
                        .addComponent(lblCodigoProducto)
                        .addComponent(txtCodigoProducto)
                        .addGroup(pnlCrudpProductoLayout.createSequentialGroup()
                            .addComponent(cbxCodigoCategoria, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGap(186, 186, 186))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlCrudpProductoLayout.createSequentialGroup()
                            .addGroup(pnlCrudpProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lblPrecio)
                                .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(6, 6, Short.MAX_VALUE)
                            .addGroup(pnlCrudpProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(pnlCrudpProductoLayout.createSequentialGroup()
                                    .addComponent(lblStock)
                                    .addGap(166, 166, 166))
                                .addGroup(pnlCrudpProductoLayout.createSequentialGroup()
                                    .addGap(6, 6, 6)
                                    .addComponent(txtStock)))))
                    .addGroup(pnlCrudpProductoLayout.createSequentialGroup()
                        .addGap(53, 53, 53)
                        .addComponent(lblExaminar2, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(63, 63, 63)
                        .addComponent(btnExaminar)))
                .addGroup(pnlCrudpProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlCrudpProductoLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 59, Short.MAX_VALUE)
                        .addGroup(pnlCrudpProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlCrudpProductoLayout.createSequentialGroup()
                                .addGroup(pnlCrudpProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(rdIdProducto)
                                    .addComponent(rdIdCategoria))
                                .addGap(31, 31, 31)
                                .addComponent(txtBuscarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnListar, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(rdNombreProducto))
                        .addGap(67, 67, 67))
                    .addGroup(pnlCrudpProductoLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1)
                        .addGap(20, 20, 20))))
            .addGroup(pnlCrudpProductoLayout.createSequentialGroup()
                .addComponent(pnlSeccionBotones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 153, Short.MAX_VALUE))
        );
        pnlCrudpProductoLayout.setVerticalGroup(
            pnlCrudpProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCrudpProductoLayout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addGroup(pnlCrudpProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlCrudpProductoLayout.createSequentialGroup()
                        .addGroup(pnlCrudpProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblCodigoProducto)
                            .addComponent(rdIdProducto))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlCrudpProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtCodigoProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rdIdCategoria)))
                    .addGroup(pnlCrudpProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtBuscarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnListar, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(pnlCrudpProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlCrudpProductoLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(lblNombre))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlCrudpProductoLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rdNombreProducto)))
                .addGroup(pnlCrudpProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlCrudpProductoLayout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addGap(4, 4, 4))
                    .addGroup(pnlCrudpProductoLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblDescripcion)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnlCrudpProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblPrecio)
                            .addComponent(lblStock))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlCrudpProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtStock)
                            .addComponent(txtPrecio, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblDireccion)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlCrudpProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlCrudpProductoLayout.createSequentialGroup()
                                .addComponent(lblCodigoCategoria)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbxCodigoCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lblExaminar2, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnlCrudpProductoLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(btnExaminar)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlSeccionBotones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        add(pnlCrudpProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1130, 560));
    }// </editor-fold>//GEN-END:initComponents

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        stateNew();
        this.txtNombre.requestFocus();
        addOption = 1;
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        stateInitial();
        cleanControls();
    }//GEN-LAST:event_btnCancelarActionPerformed


    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        if (addOption == 1) {
            //Agregar
            if(!validarCampos()){
                return;
            }
            addProduct();

        } else if(addOption == 2){
            //Editar
            if(!validarCampos()){
                return;
            }
            editProduct();
        }else{
            //Eliminar
            if (!validarId(txtCodigoProducto)) {
                return;
            }
            deleteProduct();
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        addOption = 2;
        stateEdit();
        txtCodigoProducto.requestFocus();
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        addOption = 3;
        stateDelete();
        txtCodigoProducto.requestFocus();
    }//GEN-LAST:event_btnEliminarActionPerformed

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

    private void btnListarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnListarActionPerformed

        try {
            Limpiar();
            fillTable(productService.findAllProducts());
        } catch (Exception ex) {
            successMessage(ex.getMessage(), "Atención");
        }
    }//GEN-LAST:event_btnListarActionPerformed

    private void cbxCodigoCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxCodigoCategoriaActionPerformed


    }//GEN-LAST:event_cbxCodigoCategoriaActionPerformed


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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblCodigoCategoria;
    private javax.swing.JLabel lblCodigoProducto;
    private javax.swing.JLabel lblDescripcion;
    private javax.swing.JLabel lblDireccion;
    private javax.swing.JLabel lblExaminar2;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblPrecio;
    private javax.swing.JLabel lblStock;
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
            int productId = Integer.parseInt(this.txtCodigoProducto.getText());
            String name = txtNombre.getText().trim();
            String description = txtDescripcion.getText().trim();
            double price = Double.parseDouble(this.txtPrecio.getText());
            String address = this.txtDireccion.getText();
            int stock = Integer.parseInt(txtStock.getText());

            byte[] image = getImagen(Ruta);
// public Product(int productId, String name, String description, double price,String address ,int categoryId, int stock,byte [] image) {
            Product OProduct = new Product(productId, name, description, price, address, selectedCategoryId, stock, image);
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

        Product OProduct = new Product(productId, name, description, price, direccion, selectedCategoryId, stock, image);
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
        System.err.println(selectedCategoryId);
        List<MensajesError> errores = validarFormulario.validar(txtCodigoProducto, txtNombre, txtDescripcion,
                txtPrecio, txtStock, txtDireccion, selectedCategoryId);

        if (!errores.isEmpty()) {
            String mensajeError = "Debe ingresar el/los siguiente(s) campo(s):\n";
            for (MensajesError mensaje : errores) {
                mensajeError += mensaje.getMensaje() + "\n";
            }
            Messages.showMessageDialog(mensajeError, "Atención");

            // Coloca el foco en el primer campo con error
            switch (errores.get(0)) {
                case CODIGO_PRODUCTO:
                    txtCodigoProducto.requestFocus();
                    break;
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
