package View.GUI;

import Controller.Inventario;
import Model.Crud;
import Model.Models.Producto;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

public class CreateProduct extends JFrame{
    private JTextField nombreField;
    private JPanel principalPanel;
    private JSpinner cantidadSpinner;
    private JButton cancelarButton;
    private JButton aceptarButton;
    private JFormattedTextField costoFormatted;
    private Producto producto;
    private boolean isEditing = false;

    public CreateProduct(String title){
        super(title);

        //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(principalPanel);
        this.pack();

        producto = new Producto();

        aceptarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String nombre = nombreField.getText();
                int cantidad = Integer.parseInt(cantidadSpinner.getValue().toString());

                double costoU;
                try {
                    costoU = Double.parseDouble(costoFormatted.getText());
                }catch (NumberFormatException exp){
                    System.out.println("Por favor, ingrese un valor de costo unitario válido.");
                    costoU =-1;
                }

                if(!nombre.isBlank() && cantidad>0 && costoU>0){
                    System.out.println("Guardar nuevo producto");
                    producto.setNombre(nombre);
                    producto.setCantidad(cantidad);
                    producto.setCostoUnitario(costoU);

                    if (isEditing){
                        Inventario.updateProducto(producto);
                        closeWindow();
                    }else {
                        System.out.println(Inventario.insertProduct(producto));
                    }

                    nombreField.setText("");
                    cantidadSpinner.setValue(0);
                    costoFormatted.setText("");
                }else{
                    System.out.println("Los valores ingresados son inválidos. Por favor, verifíquelos.");
                }
            }
        });

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                Home.setIsCreating(false);
            }
        });
        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                closeWindow();
            }
        });
    }

    public void loadEdit(int id, String nombre, int cantidad, String costo){
        nombreField.setText(nombre);
        cantidadSpinner.setValue(cantidad);
        costoFormatted.setText(costo);
        producto.setId(id);
        isEditing = true;
    }

    private void closeWindow() {
        Home.setIsCreating(false);
        dispose();
    }
}
