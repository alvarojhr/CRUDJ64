import Model.Crud;
import Model.DbConection;
import View.Console.Screen;
import View.GUI.CreateProduct;
import View.GUI.Home;

import javax.swing.*;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        //Levantamos la conexion a la BD
        Crud.setConnection(DbConection.connect());

        //Screen.listProducts();
        //Screen.createProduct();
        //Screen.deleteProduct();
        //Screen.updateProduct();

        JFrame ventana = new Home("Gestión de productos");
        ventana.setVisible(true);


        //Cerramos la conexion a la BD
//        try {
//            Crud.getConnection().close();
//        } catch (SQLException e) {
//            System.out.println("[CONNECTION] "+e);
//        }
    }
}
