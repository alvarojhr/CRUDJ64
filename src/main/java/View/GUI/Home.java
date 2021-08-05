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
    private JButton button1;
    private JButton editarButton;
    private JButton button3;
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

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //Con esto, abrimos la ventana para crear
                if(!isCreating) {
                    JFrame ventana = new CreateProduct("Creación de productos");
                    ventana.setVisible(true);
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
}
