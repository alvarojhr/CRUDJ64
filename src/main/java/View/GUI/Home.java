package View.GUI;

import Controller.Inventario;
import Model.Crud;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

public class Home extends JFrame{
    private JButton crearButton;
    private JButton editarButton;
    private JButton eliminarButton;
    private JPanel homePanel;
    private JTable table1;
    private JButton refrescarButton;
    private final String[] columns = {"Id","Nombre","Cantidad","Costo unitario","Valor unitario"};

    private static boolean isCreating = false;

    public Home(String title){
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(homePanel);
        this.pack();

        loadData();

        crearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //Con esto, abrimos la ventana para crear
                if(!isCreating) {
                    showEditCreate();
                }
                isCreating = true;
            }
        });

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.out.println("Bye bye");
                try {
                    Crud.getConnection().close();
                } catch (SQLException exc) {
                    System.out.println("[CONNECTION] "+exc);
                }
            }
        });
        refrescarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadData();
            }
        });
        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int filaSeleccionada = table1.getSelectedRow();
                if (filaSeleccionada>=0) {
                    int idSelecccionado = Integer.parseInt(table1.getModel().getValueAt(filaSeleccionada, 0).toString());
                    Inventario.deleteProduct(idSelecccionado);
                    loadData();
                }
            }
        });

        editarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int filaSeleccionada = table1.getSelectedRow();
                if (filaSeleccionada >= 1) {
                    int idSelecccionado = Integer.parseInt(table1.getModel().getValueAt(filaSeleccionada, 0).toString());

                    String nombre = table1.getModel().getValueAt(filaSeleccionada, 1).toString();
                    int cantidad = Integer.parseInt(table1.getModel().getValueAt(filaSeleccionada, 2).toString());
                    String costoUnitario = table1.getModel().getValueAt(filaSeleccionada, 3).toString();

                    if (!isCreating) {
                        CreateProduct ventana = showEditCreate();
                        ventana.loadEdit(idSelecccionado,nombre,cantidad,costoUnitario);
                    }
                    isCreating = true;
                }
            }
        });
    }

    public static void setIsCreating(boolean isCreating) {
        Home.isCreating = isCreating;
    }

    public void loadData(){
        //Esta información no esté quemada acá, si no, que la traigamos de la BD
        String[][] data = Inventario.getInventario();
        DefaultTableModel dataModel = new DefaultTableModel(data,columns);
        table1.setModel(dataModel);
    }

    public CreateProduct showEditCreate(){
        CreateProduct ventana = new CreateProduct("Creación de productos");
        ventana.setVisible(true);
        return ventana;
    }


}
