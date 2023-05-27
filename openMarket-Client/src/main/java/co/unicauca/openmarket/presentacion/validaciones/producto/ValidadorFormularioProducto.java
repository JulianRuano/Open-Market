package co.unicauca.openmarket.presentacion.validaciones.producto;

import co.unicauca.openmarket.presentacion.validaciones.ValidadorCampos;
import co.unicauca.openmarket.presentacion.validaciones.producto.MensajesError;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTextField;

public class ValidadorFormularioProducto {

    private final ValidadorCampos validarCampos;

    public ValidadorFormularioProducto() {
        this.validarCampos = new ValidadorCampos();

    }

    public List<MensajesError> validar(JTextField txtCodigoProducto, JTextField txtNombre,
            JTextField txtDescripcion, JTextField txtPrecio, JTextField txtStock,
            JTextField txtDireccion,Long selectedCategoryId) {
        List<MensajesError> errores = new ArrayList<>();

        if (validarCampos.validarCampoVacio(txtCodigoProducto.getText())) {
            errores.add(MensajesError.CODIGO_PRODUCTO);
        }

        if (validarCampos.validarCampoVacio(txtNombre.getText())) {
            errores.add(MensajesError.NOMBRE_PRODUCTO);
        }

        if (validarCampos.validarCampoVacio(txtDescripcion.getText())) {
            errores.add(MensajesError.DESCRIPCION_PRODUCTO);
        }
         if(selectedCategoryId==null){
               errores.add(MensajesError.CATEGORIA_PRODUCTO);
         }
        if (validarCampos.validarCampoVacio(txtPrecio.getText())) {
            errores.add(MensajesError.PRECIO_PRODUCTO);
        }

        if (validarCampos.validarCampoVacio(txtStock.getText())) {
            errores.add(MensajesError.STOCK_PRODUCTO);
        }

        if (validarCampos.validarCampoVacio(txtDireccion.getText())) {
            errores.add(MensajesError.DIRECCION_PRODUCTO);
        }

        return errores;
    }
}
