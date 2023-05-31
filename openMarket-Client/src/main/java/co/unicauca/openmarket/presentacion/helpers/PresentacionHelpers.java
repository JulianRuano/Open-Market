/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.unicauca.openmarket.presentacion.helpers;

import co.unicauca.openmarket.client.domain.service.CategoryService;
import co.unicauca.openmarket.commons.domain.Category;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

/**
 *
 * @author Jorge
 */
public class PresentacionHelpers {
    
    public Integer selectedCategoryId;
    public PresentacionHelpers(){
        
    }
    
    public void loadCategories(JComboBox cbxCodigoCategoria,CategoryService categoryService){
            List<Category> categories = categoryService.findAllCategories();
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

            cbxCodigoCategoria.addActionListener((ActionEvent e) -> {
                String selectedCategoryName = (String) cbxCodigoCategoria.getSelectedItem();
                selectedCategoryId = categoryMap.get(selectedCategoryName);

            });
        }
    }
}
