import Model.Crud;
import Model.DbConection;
import View.Screen;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //Levantamos la conexion a la BD
        Crud.setConnection(DbConection.connect());


        //Screen.listProducts();
        //Screen.createProduct();
        //Screen.deleteProduct();
        Screen.updateProduct();

        //Cerramos la conexion a la BD
        try {
            Crud.getConnection().close();
        } catch (SQLException e) {
            System.out.println("[CONNECTION] "+e);
        }
    }
}
